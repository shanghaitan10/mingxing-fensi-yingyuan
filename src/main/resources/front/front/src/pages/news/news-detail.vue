<template>
	<div class="news-detail-box">
		<div class="back_box">
			<el-button class="backBtn" size="mini" @click="backClick">
				<span class="icon iconfont icon-jiantou33"></span>
				<span class="text">返回</span>
			</el-button>
		</div>
		<div class="news-detail-view">
			<div class="title-box">
				<div>通知公告</div>
			</div>
			<div class="news-detail">
				<div class="detail-title">{{detail.title}}</div>
				<div class="infoBox">
					<div class="time_item">
						<span class="icon iconfont icon-shijian21"></span>
						<span class="label">发布时间：</span>
						<span class="text">{{detail.addtime}}</span>
					</div>
					<div class="publisher_item">
						<span class="icon iconfont icon-geren16"></span>
						<span class="label">发布人：</span>
						<span class="text">{{detail.name}}</span>
					</div>
					<div class="like_item">
						<span class="icon iconfont icon-zan10"></span>
						<span class="label">点赞数：</span>
						<span class="text">{{detail.thumbsupnum}}</span>
					</div>
					<div class="collect_item">
						<span class="icon iconfont icon-shoucang10"></span>
						<span class="label">收藏量：</span>
						<span class="text">{{detail.storeupnum}}</span>
					</div>
					<div class="view_item">
						<span class="icon iconfont icon-chakan9"></span>
						<span class="label">点击量：</span>
						<span class="text">{{detail.clicknum}}</span>
					</div>
				</div>
				<div class="operate">
					<div class="zan-btn" @click="zanClick" >
						<span class="icon iconfont" :class="zanType?'icon-guanzhu-zhihui':'icon-guanzhu-zhihui'" ></span>
						<span class="text" >{{zanType?'取消点赞':'点赞'}}</span>
					</div>
					<div class="collect-btn" @click="collectClick" >
						<span class="icon iconfont" :class="collectType?'icon-shoucang10':'icon-shoucang10'" ></span>
						<span class="text" >{{collectType?'取消收藏':'收藏'}}</span>
					</div>
				</div>
				
				<div class="content-detail ql-snow ql-editor" v-html="detail.content"></div>
			</div>
			<!-- option -->
			<div class="option-box">
				<div class="prev-btn" @click="prepDetailClick">
					<span class="text">上一篇：prev</span>
					<span class="icon iconfont icon-jiantou08"></span>
				</div>
				<div class="next-btn" @click="nextDetailClick">
					<span class="text">下一篇：next</span>
					<span class="icon iconfont icon-jiantou09"></span>
				</div>
			</div>

			<!-- 热门文章 -->
			<div class="hot">
				<div class="hot-title">热门文章</div>
				<div class="hot-list">
					<div class="hot-item" v-for="item in hotList" :key="item.id" @click="toDetail(item.id)">
						<img :src="baseUrl + item.picture" alt="">
						<div class="hot-name">{{ item.title }}</div>
						<div class="hot-time">{{item.addtime}}</div>
					</div>
				</div>
			</div>
			<!-- 热门标签 -->
			<div class="tags">
				<div class="tags-title">热门标签</div>
				<div class="tags-list">
					<div class="tags-item" v-for="(item,index) in categoryList" :key="index">{{ item.typename }}</div>
				</div>
			</div>
			<!-- 推荐文章 -->
			<div class="news">
				<div class="news-title">推荐文章</div>
				<div class="news-list">
					<div class="news-item" v-for="item in recommendList" :key="item.id" @click="toDetail(item.id)">
						<img :src="baseUrl + item.picture" alt="">
						<div class="news-name">{{ item.title }}</div>
						<div class="news-time">{{item.addtime}}</div>
					</div>
				</div>
			</div>
			<!-- 联系我们 -->
			<div class="contactus">
				<div class="contactus-title">联系我们</div>
				<div class="contactus-list">
					<div class="phone_item">
						<div class="label">手机：</div>
						<div class="text">13823888888</div>
					</div>
					<div class="mobile_item">
						<div class="label">电话：</div>
						<div class="text">0753-1234567</div>
					</div>
					<div class="email_item">
						<div class="label">邮箱：</div>
						<div class="text">13823888888@qq.com</div>
					</div>
					<div class="addr_item">
						<div class="label">地址：</div>
						<div class="text">广东省xxx市</div>
					</div>
				</div>
				<span class="icon iconfont icon-xiugai6"></span>
			</div>
		</div>
	</div>
</template>

<script>
	export default {
		//数据集合
		data() {
			return {
				id: 0,
				detail: {},
				zanType: false,
				zanForm: {},
				collectType:false,
				collectForm: {},
				baseUrl:'',
				hotList: [],
				recommendList: [],
				categoryList: [],
				currentIndex: 0,
				allList: [],
				storeupType: false,
			}
		},
		created() {
			if(this.$route.query.storeupType&&this.$route.query.storeupType!=0) {
				this.storeupType = true
			}
			this.id = this.$route.query.id
			this.baseUrl = this.$config.baseUrl;
			this.getCategoryList()
			this.getDetail()
			this.getNewsList()
			this.getHotList()
			this.getRecommendList()
		},
		watch:{
			$route(newValue) {
				this.id = this.$route.query.id
			    this.getDetail()
			}
		},
		methods: {
			backClick() {
				if(this.storeupType){
					history.back()
				}else {
					this.$router.push({path: '/index/news'});
				}
			},
			getNewsList() {
				let params = {page:1, limit: 100,sort:'addtime',order:'desc'};
				this.$http.get('news/list', {params: params}).then(res => {
					if (res.data.code == 0) {
						for(let x in res.data.data.list){
							if(res.data.data.list[x].id == this.id){
								this.currentIndex = Number(x)
								break
							}
						}
						this.allList = res.data.data.list
					}
				});
			},
			// 上一篇
			prepDetailClick(){
				if(this.currentIndex == 0){
					this.$message.error('已经是第一篇了')
					return false
				}
				this.currentIndex--
				this.$router.push({path: '/index/newsDetail', query: {id: this.allList[this.currentIndex].id}});
			},
			// 下一篇
			nextDetailClick(){
				if(this.currentIndex == this.allList.length - 1){
					this.$message.error('已经是最后一篇了')
					return false
				}
				this.currentIndex++
				this.$router.push({path: '/index/newsDetail', query: {id: this.allList[this.currentIndex].id}});
			},
			getCategoryList(){
				this.$http.get('newstype/list', {}).then(res => {
					if (res.data.code == 0) {
						this.categoryList = res.data.data;
					}
				});
			},
			// 获取热门列表
			getHotList(){
				let params = {page:1, limit: 4,sort:'addtime',order:'desc'};
				this.$http.get('news/autoSort', {params: params}).then(res => {
					if (res.data.code == 0) {
						this.hotList = res.data.data.list;
					}
				});
			},
			// 获取推荐列表
			getRecommendList(){
				let url = 'news/autoSort'
				if(localStorage.getItem('frontToken')){
					url = 'news/autoSort2'
				}
				let params = {page:1, limit: 4,sort:'addtime',order:'desc'};
				this.$http.get(url, {params: params}).then(res => {
					if (res.data.code == 0) {
						this.recommendList = res.data.data.list;
					}
				});
			},
			// 跳转详情
			toDetail(id){
				this.$router.push({path: '/index/newsDetail', query: {id: id}});
			},
			getDetail(){
				this.$http.get(`news/detail/${this.id}`,{}).then(res=>{
					if(res.data&&res.data.code==0){
						this.detail = res.data.data
						window.scrollTo(0, 100)
						this.getZan()
						this.getCollect()
					}
				})
			},
			getZan() {
				this.$http.get('storeup/list', {
					params: {
						page: 1,
						limit: 1,
						type: '21',
						userid: Number(localStorage.getItem('frontUserid')),
						tablename: 'news',
						refid: this.id
					}
				}).then(res=>{
					if(res.data&&res.data.code==0){
						if(res.data.data.list.length){
							this.zanType = true
							this.zanForm = res.data.data.list[0]
						}else{
							this.zanType = false
						}
					}
				})
			},
			getCollect(){
				this.$http.get('storeup/list', {
					params: {
						page: 1,
						limit: 1,
						type: '1',
						userid: Number(localStorage.getItem('frontUserid')),
						tablename: 'news',
						refid: this.id
					}
				}).then(res=>{
					if(res.data&&res.data.code==0){
						if(res.data.data.list.length){
							this.collectType = true
							this.collectForm = res.data.data.list[0]
						}else{
							this.collectType = false
						}
					}
				})
			},
			zanClick() {
				if(this.zanType){
					this.$http.post('storeup/delete', [this.zanForm.id]).then(res => {
						if (res.data && res.data.code == 0) {
							this.$message.success('取消成功')
							this.detail.thumbsupnum--
							this.$http.post('news/update',this.detail).then(obj=>{})
							this.getZan()
						}
					})
				}else{
					let data = {
						name: this.detail.title,
						picture: this.detail.picture,
						refid: this.detail.id,
						type: '21',
						tablename: 'news',
						userid: Number(localStorage.getItem('frontUserid')),
					}
					this.$http.post('storeup/add', data).then(res => {
						if (res.data && res.data.code == 0) {
							this.$message.success('点赞成功')
							this.detail.thumbsupnum++
							this.$http.post('news/update',this.detail).then(obj=>{})
							this.getZan()
						}
					})
				}
			},
			collectClick(){
				if(this.collectType){
					this.$http.post('storeup/delete', [this.collectForm.id]).then(res => {
						if (res.data && res.data.code == 0) {
							this.$message.success('取消成功')
							this.detail.storeupnum--
							this.$http.post('news/update',this.detail).then(obj=>{})
							this.getCollect()
						}
					})
				}else{
					let data = {
						name: this.detail.title,
						picture: this.detail.picture,
						refid: this.detail.id,
						type: '1',
						tablename: 'news',
						userid: Number(localStorage.getItem('frontUserid')),
					}
					this.$http.post('storeup/add', data).then(res => {
						if (res.data && res.data.code == 0) {
							this.detail.storeupnum++
							this.$http.post('news/update',this.detail).then(obj=>{})
							this.$message.success('收藏成功')
							this.getCollect()
						}
					})
				}
			},
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	
	.news-detail-box {
				margin: 10px auto;
				background: none;
				width: 1300px;
				position: relative;
				.news-detail-view {
						border: none;
						border-radius: 10px;
						width: 100%;
						.title-box {
								border-radius: 10px 10px 0 0;
								width: 100%;
								border-color: #423DD2;
								border-width: 1px 1px 0 1px;
								border-style: solid;
								height: auto;
								div {
										margin: 0;
										color: #000000;
										background: none;
										font-weight: 700;
										font-size: 20px;
										line-height: 54px;
										text-align: center;
									}
			}
			.news-detail {
								padding: 20px 20px 66px;
								width: 100%;
								border-color: #423DD2;
								border-width: 0 1px 0 1px;
								position: relative;
								border-style: solid;
								.detail-title {
										color: #333;
										font-size: 18px;
										line-height: 40px;
										text-align: center;
									}
				.infoBox {
										display: flex;
										width: 100%;
										justify-content: center;
										align-items: center;
										.time_item {
												padding: 0;
												.icon {
														margin: 0 2px 0 0;
														color: #666;
														font-size: 12px;
														line-height: 1.5;
													}
						.label {
														color: #666;
														font-size: 12px;
														line-height: 1.5;
													}
						.text {
														color: #666;
														font-size: 12px;
														line-height: 1.5;
													}
					}
					.publisher_item {
												padding: 0;
												.icon {
														margin: 0 2px 0 0;
														color: #666;
														font-size: 12px;
														line-height: 1.5;
													}
						.label {
														color: #666;
														font-size: 12px;
														line-height: 1.5;
													}
						.text {
														color: #666;
														font-size: 12px;
														line-height: 1.5;
													}
					}
					.like_item {
												padding: 0;
												.icon {
														margin: 0 2px 0 0;
														color: #666;
														font-size: 12px;
														line-height: 1.5;
													}
						.label {
														color: #666;
														font-size: 12px;
														line-height: 1.5;
													}
						.text {
														color: #666;
														font-size: 12px;
														line-height: 1.5;
													}
					}
					.collect_item {
												padding: 0;
												.icon {
														margin: 0 2px 0 0;
														color: #666;
														font-size: 12px;
														line-height: 1.5;
													}
						.label {
														color: #666;
														font-size: 12px;
														line-height: 1.5;
													}
						.text {
														color: #666;
														font-size: 12px;
														line-height: 1.5;
													}
					}
					.view_item {
												padding: 0;
												.icon {
														margin: 0 2px 0 0;
														color: #666;
														font-size: 12px;
														line-height: 1.5;
													}
						.label {
														color: #666;
														font-size: 12px;
														line-height: 1.5;
													}
						.text {
														color: #666;
														font-size: 12px;
														line-height: 1.5;
													}
					}
				}
				.operate {
										padding: 10px 20px;
										bottom: 0;
										display: flex;
										gap: 20px;
										width: 100%;
										justify-content: flex-end;
										align-items: center;
										position: absolute;
										right: 10px;
										.zan-btn {
												cursor: pointer;
												border-radius: 15px;
												padding: 0 15px;
												background: linear-gradient( 299deg, #423DD2 0%, #B1D8FB 100%);
												display: flex;
												justify-content: center;
												align-items: center;
												height: 30px;
												.icon {
														margin: 0 2px 0 0;
														color: #FFF;
														font-size: 14px;
														line-height: 40px;
													}
						.text {
														color: #FFF;
														font-size: 14px;
														line-height: 40px;
													}
					}
					.zan-btn:hover {
												opacity: 0.9;
												.icon {
														color: #fff;
													}
						.text {
														color: #fff;
													}
					}
					.collect-btn {
												cursor: pointer;
												border-radius: 15px;
												padding: 0 15px;
												background: #BDC5D6;
												display: flex;
												justify-content: center;
												align-items: center;
												height: 30px;
												.icon {
														margin: 0 2px 0 0;
														color: #FFF;
														font-size: 14px;
														line-height: 40px;
													}
						.text {
														color: #FFF;
														font-size: 14px;
														line-height: 40px;
													}
					}
					.collect-btn:hover {
												opacity: 0.9;
												.icon {
														color: #FFF;
													}
						.text {
														color: #FFF;
													}
					}
				}
				.content-detail {
										width: 100%;
										font-size: 14px;
										height: auto;
									}
			}
			.option-box {
								border-radius: 0 0 10px 10px;
								padding: 20px;
								display: flex;
								gap: 20px;
								width: 100%;
								border-color: #423DD2;
								border-width: 0 1px 1px 1px;
								justify-content: center;
								border-style: solid;
								height: auto;
								.prev-btn {
										cursor: pointer;
										border-radius: 25px;
										padding: 0 10px;
										background: #423DD2;
										.text {
												color: #FFF;
												font-size: 14px;
												line-height: 40px;
											}
					.icon {
												color: #FFF;
												font-size: 14px;
												line-height: 40px;
											}
				}
				.prev-btn:hover {
										opacity: 0.9;
										.text {
												color: #FFF;
											}
					.icon {
												color: #FFF;
											}
				}
				.next-btn {
										cursor: pointer;
										border-radius: 25px;
										padding: 0 10px;
										background: #BABABA;
										.text {
												color: #FFF;
												font-size: 14px;
												line-height: 40px;
											}
					.icon {
												color: #FFF;
												font-size: 14px;
												line-height: 40px;
											}
				}
				.next-btn:hover {
										opacity: 0.9;
										.text {
												color: #FFF;
											}
					.icon {
												color: #FFF;
											}
				}
			}
			.hot {
								box-shadow: none;
								padding: 40px 0 20px;
								background: none;
								width: 100%;
								position: relative;
								height: auto;
								.hot-title {
										padding: 0 0 0 180px;
										margin: 10px auto;
										background: url(http://codegen.caihongy.cn/20241210/a35f0a1a1ae54a6599c8a9c34f6344e8.webp) no-repeat center / 100% 100%;
										font-weight: 700;
										width: 100%;
										font-size: 36px;
										line-height: 100px;
										text-align: left;
										height: 100px;
									}
				.hot-list {
										margin: 40px 0 0;
										overflow: hidden;
										background: none;
										display: flex;
										width: 100%;
										justify-content: space-between;
										flex-wrap: wrap;
										height: auto;
										.hot-item {
												cursor: pointer;
												border-radius: 10px;
												padding: 15px;
												background: linear-gradient( 180deg, #C2C0FF 0%, rgba(216,216,216,0) 100%);
												width: 300px;
												position: relative;
												height: auto;
												img {
														border: 5px solid #F1F1F1;
														border-radius: 10px;
														object-fit: cover;
														display: block;
														width: 100%;
														height: 260px;
													}
						.hot-name {
														padding: 0 10px;
														margin: 10px 0 0;
														overflow: hidden;
														color: #000000;
														white-space: nowrap;
														font-weight: 600;
														font-size: 18px;
														line-height: 32px;
														text-overflow: ellipsis;
														text-align: center;
													}
						.hot-time {
														padding: 10px;
														color: #13115B;
														font-weight: 600;
														font-size: 16px;
														line-height: 1.5;
														text-align: center;
													}
					}
				}
			}
			.tags {
								box-shadow: 0 1px 6px rgba(0,0,0,.1);
								margin: 0 0 0 -160px;
								top: 0;
								left: 0;
								background: #fff;
								display: none;
								width: 150px;
								position: absolute;
								height: auto;
								.tags-title {
										color: #fff;
										background: red;
										width: 100%;
										font-size: 14px;
										line-height: 44px;
										text-align: center;
									}
				.tags-list {
										padding: 5px ;
										background: #fff;
										display: flex;
										width: 100%;
										justify-content: space-between;
										flex-wrap: wrap;
										height: auto;
										.tags-item {
												color: #999;
												font-size: 14px;
												line-height: 28px;
											}
				}
			}
			.news {
								box-shadow: none;
								padding: 40px 0 20px;
								background: none;
								width: 100%;
								position: relative;
								height: auto;
								order: 5;
								.news-title {
										padding: 0 0 0 180px;
										margin: 10px auto;
										background: url(http://codegen.caihongy.cn/20241210/a35f0a1a1ae54a6599c8a9c34f6344e8.webp) no-repeat center / 100% 100%;
										font-weight: 700;
										width: 100%;
										font-size: 36px;
										line-height: 100px;
										text-align: left;
										height: 100px;
									}
				.news-list {
										margin: 40px 0 0;
										overflow: hidden;
										background: none;
										display: flex;
										width: 100%;
										justify-content: space-between;
										flex-wrap: wrap;
										height: auto;
										.news-item {
												cursor: pointer;
												border-radius: 10px;
												padding: 15px;
												background: linear-gradient( 180deg, #C2C0FF 0%, rgba(216,216,216,0) 100%);
												width: 300px;
												position: relative;
												height: auto;
												img {
														border: 5px solid #F1F1F1;
														border-radius: 10px;
														object-fit: cover;
														display: block;
														width: 100%;
														height: 260px;
													}
						.news-name {
														padding: 0 10px;
														margin: 10px 0 0;
														overflow: hidden;
														color: #000000;
														white-space: nowrap;
														font-weight: 600;
														font-size: 18px;
														line-height: 32px;
														text-overflow: ellipsis;
														text-align: center;
													}
						.news-time {
														padding: 10px;
														color: #13115B;
														font-weight: 600;
														font-size: 16px;
														line-height: 1.5;
														text-align: center;
													}
					}
				}
			}
			.contactus {
								box-shadow: 0 1px 6px rgba(0,0,0,.1);
								margin: 0 0 0 -210px;
								top: 250px;
								left: 0;
								background: #fff;
								display: none;
								width: 200px;
								position: absolute;
								height: auto;
								.contactus-title {
										color: #fff;
										background: red;
										width: 100%;
										font-size: 14px;
										line-height: 44px;
										text-align: center;
									}
				.contactus-list {
										padding: 5px 5px 20px;
										width: 100%;
										height: auto;
										.phone_item {
												display: flex;
												width: 100%;
												height: auto;
												.label {
														color: #333;
														font-size: 14px;
														line-height: 30px;
													}
						.text {
														color: #333;
														flex: 1;
														font-size: 14px;
														line-height: 30px;
													}
					}
					.mobile_item {
												display: flex;
												width: 100%;
												height: auto;
												.label {
														color: #333;
														font-size: 14px;
														line-height: 30px;
													}
						.text {
														color: #333;
														flex: 1;
														font-size: 14px;
														line-height: 30px;
													}
					}
					.email_item {
												display: flex;
												width: 100%;
												height: auto;
												.label {
														color: #333;
														font-size: 14px;
														line-height: 30px;
													}
						.text {
														color: #333;
														flex: 1;
														font-size: 14px;
														line-height: 30px;
													}
					}
					.addr_item {
												display: flex;
												width: 100%;
												height: auto;
												.label {
														color: #333;
														font-size: 14px;
														line-height: 30px;
													}
						.text {
														color: #333;
														flex: 1;
														font-size: 14px;
														line-height: 30px;
													}
					}
				}
				.icon {
										border-radius: 100%;
										margin: 0 0 0 -15px;
										color: #fff;
										left: 50%;
										bottom: -15px;
										background: red;
										width: 32px;
										font-size: 14px;
										line-height: 32px;
										position: absolute;
										text-align: center;
									}
			}
		}
	}
</style>