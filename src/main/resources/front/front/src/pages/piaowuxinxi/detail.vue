<template>
	<div>
	<!--  -->
		<div class="breadcrumb-preview">
			<el-breadcrumb :separator="'Ξ'">
				<el-breadcrumb-item class="item1" to="/"><a>首页</a></el-breadcrumb-item>
				<el-breadcrumb-item class="item2" v-for="(item, index) in breadcrumbItem" :key="index" :to="'/index/piaowuxinxi?centerType=' + (centerType?'1':'0')"><a>{{item.name}}</a></el-breadcrumb-item>
				<el-breadcrumb-item class="item3"><a href="javascript:void(0);">详情</a></el-breadcrumb-item>
			</el-breadcrumb>
		</div>
		<div class="back_box">
			<el-button class="backBtn" size="mini" @click="backClick">
				<span class="icon iconfont icon-jiantou33"></span>
				<span class="text">返回</span>
			</el-button>
		</div>
		<div class="detail-preview">
			<div class="attr">
				<div class="info">
					<div class="title-item">
						<div class="detail-title">
							{{detail.mingcheng}}
						</div>
						<div class="colectBtn" @click="storeup(1)" v-show="!isStoreup">
							<i class="icon iconfont icon-shoucang10"></i>
							<span class="text">收藏({{detail.storeupnum}})</span>
						</div>
						<div class="colectBtnActive" @click="storeup(-1)" v-show="isStoreup">
							<i class="icon iconfont icon-shoucang12"></i>
							<span class="text">已收藏({{detail.storeupnum}})</span>
						</div>
					</div>
					<div class="coupon-item">
						<div class="coupon-kong"></div>
						<el-button class="couponBtn" type="warning" size="small" @click="couponClick">优惠券</el-button>
					</div>
					<div class="item" v-if="detail.price">
						<div class="lable">价格</div>
						<div class="text price bold"><span style="font-size: 12px">￥</span>{{detail.price}}</div>
					</div>
					<div class="item" v-if="detail.jf">
						<div class="lable">积分</div>
						<div class="text price bold">{{detail.jf}}</div>
					</div>
					<div class="item">
						<div class="lable">明星姓名</div>
						<div class="text "  >{{detail.mingxingxingming}}</div>
					</div>
					<div class="item">
						<div class="lable">时间</div>
						<div class="text "  >{{detail.shijian}}</div>
					</div>
					<div class="item">
						<div class="lable">地址</div>
						<div class="text "  >{{detail.dizhi}}</div>
					</div>
					<div class="item">
						<div class="lable">明星阵容</div>
						<div class="text "  >{{detail.mingxingzhenrong}}</div>
					</div>
					<div class="item">
						<div class="lable">经纪人</div>
						<div class="text "  >{{detail.jingjiren}}</div>
					</div>
					<div class="item">
						<div class="lable">简介</div>
						<div class="text "  >{{detail.jianjie}}</div>
					</div>
					<div class="item">
						<div class="lable">座位总数</div>
						<div class="text "  >{{detail.number}}</div>
					</div>
					<div class="btn_box">

						<el-button class="reserveBtn" type="warning" size="small" @click="bookClick">立即预订</el-button>
					</div>
					<div class="btn_box">
						<el-button class="editBtn" v-if="btnAuth('piaowuxinxi','修改')" @click="editClick">修改</el-button>
						<el-button class="delBtn" v-if="btnAuth('piaowuxinxi','删除')" @click="delClick">删除</el-button>
					</div>
				</div>
			</div>
		
			<div class="detail-swpier2" v-if="detailBanner.length">
				<div class="swiper21">
					<div class="swiper-container mySwiper21">
						<div class="swiper-wrapper">
							<div class="swiper-slide" v-for="item in detailBanner" :key="item.id">
								<div class="swiper-item">
									<img v-if="item.substr(0,4)=='http'" :src="item" class="image">
									<img v-else :src="baseUrl + item" class="image">
								</div>
							</div>
						</div>
						<div class="swiper-button-prev">
							<span class="icon iconfont icon-jiantou39"></span>
						</div>
						<div class="swiper-button-next">
							<span class="icon iconfont icon-jiantou18"></span>
						</div>
					</div>
				</div>
				<div class="swiper22">
					<div class="swiper-container mySwiper22">
						<div class="swiper-wrapper">
							<div class="swiper-slide" v-for="item in detailBanner" :key="item.id">
								<div class="swiper-item">
									<img v-if="item.substr(0,4)=='http'" :src="item" class="image">
									<img v-else :src="baseUrl + item" class="image">
								</div>
							</div>
						</div>
						<div class="swiper-button-prev">
							<span class="icon iconfont icon-jiantou39"></span>
						</div>
						<div class="swiper-button-next">
							<span class="icon iconfont icon-jiantou18"></span>
						</div>
					</div>
				</div>
			</div>

			<div class="zancai">
				<div v-if="!isThumbsupnum && !isCrazilynum" class="zan" @click="thumbsupOrCrazily(21)">
					<i class="icon iconfont icon-zan07"></i>
					<span class="text">赞一下({{detail.thumbsupnum}})</span>
				</div>
				<div v-if="!isThumbsupnum && !isCrazilynum" class="cai" @click="thumbsupOrCrazily(22)">
					<i class="icon iconfont icon-cai01"></i>
					<span class="text">踩一下({{detail.crazilynum}})</span>
				</div>
				<div v-if="isThumbsupnum" class="zanActive" @click="cancelThumbsupOrCrazily(21)">
					<i class="icon iconfont icon-zan11"></i>
					<span class="text">已赞({{detail.thumbsupnum}})</span>
				</div>
				<div v-if="isCrazilynum" class="caiActive" @click="cancelThumbsupOrCrazily(22)">
					<i  class="icon iconfont icon-cai16"></i>
					<span class="text">已踩({{detail.crazilynum}})</span>
				</div>
			</div>

		
			<video v-if="detail.shipin" ref="shipin" :src="baseUrl +  detail.shipin"  controls="controls"  controlsList="nodownload">
				您的浏览器不支持视频播放
			</video>

			<el-tabs class="detail-tabs" v-model="activeName" type="border-card" v-if="tabsNum>0" >
				<el-tab-pane label="详情" name="first">
					<div class="ql-snow ql-editor" v-html="detail.xiangqing"></div>
				</el-tab-pane>
				<el-tab-pane label="评论" name="second">
					<el-form class="add commentForm" :model="form" :rules="rules" ref="form">
						<el-form-item class="item" label="评论" prop="content">
							<editor
								v-model="form.content" 
								class="editor" 
								action="file/upload">
							</editor>
						</el-form-item>
						<el-form-item class="commentBtn">
							<el-button class="submitBtn" type="primary" @click="submitForm('form')">立即提交</el-button>
							<el-button class="resetBtn" @click="resetForm('form')">重置</el-button>
						</el-form-item>
					</el-form>
				
					<div v-if="infoList.length" class="comment-list">
						<div class="comment-item" v-for="item in infoList" :key="item.id" @mouseenter="discussEnter(item.id)"
							@mouseleave="discussLeave">
							<div class="istop" v-if="item.istop">
								<span class="icon iconfont icon-jiantou24"></span>
							</div>
							<div class="user">
								<el-image v-if="item.avatarurl" :size="50" :src="baseUrl + item.avatarurl"></el-image>
								<el-image v-if="!item.avatarurl" :size="50" :src="require('@/assets/touxiang.png')"></el-image>
								<div class="name">{{item.nickname}}</div>
							</div>
							<div class="comment-content-box">
								<div class="ql-snow ql-editor" v-html="item.content"></div>
								<div class="comment-time">{{item.addtime}}</div>
								<div class="zancai-box">
									<div v-if="!comcaiChange(item)" class="zan-item" :class="comzanChange(item)?'active':''" @click="comzanClick(item)">
										<span class="icon iconfont" :class="comzanChange(item)?'icon-zan11':'icon-zan07'"></span>
										<span class="label">{{comzanChange(item)?'已赞':'赞'}}</span>
										<span class="num">({{item.thumbsupnum}})</span>
									</div>
									<div v-if="!comzanChange(item)" class="cai-item" :class="comcaiChange(item)?'active':''" @click="comcaiClick(item)">
										<span class="icon iconfont" :class="comcaiChange(item)?'icon-cai16':'icon-cai01'"></span>
										<span class="label">{{comcaiChange(item)?'已踩':'踩'}}</span>
										<span class="num">({{item.crazilynum}})</span>
									</div>
								</div>
								<div class="comment-btn">
									<!-- <el-button :style='{"border":"0","cursor":"pointer","padding":"0 20px","margin":"0 10px","outline":"none","color":"rgba(255, 255, 255, 1)","borderRadius":"4px","background":"#423DD2","width":"auto","lineHeight":"40px","fontSize":"14px","height":"40px"}'>回复</el-button> -->
									<el-button class="delBtn" v-if="showIndex==item.id&&userid==item.userid" @click="discussDel(item.id)">删除</el-button>
								</div>
							</div>
							<div class="comment-content-box" v-if="item.reply">
								回复：<span class="ql-snow ql-editor" v-html="item.reply"></span>
							</div>
						</div>
					</div>
				
					<el-pagination
						background
						id="pagination" class="pagination"
						:pager-count="pageSize"
						:page-size="pageSize"
						prev-text="<"
						next-text=">"
						:hide-on-single-page="true"
						:layout='["total","prev","pager","next","sizes","jumper"].join()'
						:total="total"
						@current-change="curChange"
						@prev-click="prevClick"
						@next-click="nextClick"
						@size-change="sizeChange"
						></el-pagination>
				</el-tab-pane>
				<el-tab-pane label="选座" name="last">
					<div class="seat-list">
						<div v-for="(item, index) in numberList" v-bind:key="index" class="seat-item">
							<img @click="selectTip()" v-if="item.select" class="seat-icon" src="../../assets/selected.png" />
							<img @click="selectSeat(item)" v-else-if="!item.active" class="seat-icon" src="../../assets/unselect.png" />
							<img @click="unselectSeat(item)" v-else class="seat-icon" src="../../assets/select.png" />
							<span>{{item.name}}</span>
						</div>
					</div>
				</el-tab-pane>
			</el-tabs>

		</div>
		<div class="share_view">
		</div>
		<el-dialog title="优惠券" :visible.sync="couponVisible" width="85%">
			<div class="coupon_list">
				<div class="coupon" v-for="(item,index) in couponList" :key="index">
					<div class="coupon_left">
						<div class="name">{{item.name}}</div>
						<div class="name">满{{item.fullamount}}减{{item.discountamount}}</div>
						<div class="date">{{item.startime}}-{{item.endtime}}</div>
						<div class="date">{{item.remark}}</div>
					</div>
					<div class="coupon_right">
						<div class="price">￥<span class="num">{{item.discountamount}}</span></div>
						<div class="btn" @click="getCoupon(item)">立即领取</div>
					</div>
				</div>
				<div class="noList" v-if="!couponList.length">
					暂无优惠券
				</div>
			</div>
		</el-dialog>
	</div>
</template>

<script>
	import axios from 'axios'
	import Swiper from "swiper";
	export default {
		//数据集合
		data() {
			return {
				tablename: 'piaowuxinxi',
				baseUrl: '',
				breadcrumbItem: [
					{
						name: '票务信息'
					}
				],
				title: '',
				detailBanner: [],
				userid: Number(localStorage.getItem('frontUserid')),
				id: 0,
				detail: {},
				tabsNum: 3,
				activeName: 'second',
				form: {
					content: '',
					userid: Number(localStorage.getItem('frontUserid')),
					nickname: localStorage.getItem('username'),
					avatarurl: '',
				},
				showIndex: -1,
				infoList: [],
				rules: {
					content: [
						{ required: true, message: '请输入内容', trigger: 'blur' }
					],
				},
				total: 1,
				pageSize: 10,
				totalPage: 1,
				storeupParams: {
					name: '',
					picture: '',
					refid: 0,
					tablename: 'piaowuxinxi',
					userid: Number(localStorage.getItem('frontUserid'))
				},
				isStoreup: false,
				storeupInfo: {},
				isCrazilynum: false,
				isThumbsupnum: false,
				thumbsupOrCrazilyInfo: {},
				buynumber: 1,
				numberList: [],
				centerType: false,
				storeupType: false,
				shareUrl: location.href,
				couponVisible: false,
				couponList: [],
			}
		},
		created() {
			if(this.$route.query.centerType&&this.$route.query.centerType!=0) {
				this.centerType = true
			}
			if(this.$route.query.storeupType&&this.$route.query.storeupType!=0) {
				this.storeupType = true
			}
			
			this.init();
		},
		mounted() {
			setTimeout(()=>{
				let mySwiper22 = new Swiper(".mySwiper22", {"navigation":{"nextEl":".swiper-button-next","prevEl":".swiper-button-prev"},"freeMode":true,"watchSlidesVisibility":true,"watchSlidesProgress":true,"loopedSlides":5,"slidesPerView":4,"spaceBetween":10})
				let option21 = {...{"navigation":{"nextEl":".swiper-button-next","prevEl":".swiper-button-prev"},"loopedSlides":5,"spaceBetween":10}}
				option21.thumbs = {
					swiper: mySwiper22
				}
				new Swiper(".mySwiper21", option21)
			},100)
		},
		//方法集合
		methods: {
			init() {
				this.id = this.$route.query.id
				this.baseUrl = this.$config.baseUrl;
				this.$http.get(this.tablename + '/detail/'  + this.id, {}).then(res => {
					if (res.data.code == 0) {
						this.detail = res.data.data;
						this.title = this.detail.mingcheng;
						if(this.detail.xuanchuantu) {
							this.detailBanner = this.detail.xuanchuantu.split(",w").length>1?[this.detail.xuanchuantu]:this.detail.xuanchuantu.split(',');
						}
						this.$forceUpdate();
						this.getDiscussList(1);
						if(localStorage.getItem('frontToken')){
							this.getStoreupStatus();
							this.getThumbsupOrCrazilyStatus();
						}

						let selectArray = this.detail.selected?this.detail.selected.split(','):[];
						for (let i = 1; i <= this.detail.number; i++) {
							this.numberList.push({
								name: `${i}号`,
								select: false,
								active: false
							});
						}
						if(selectArray.length){
							for (let i = 0; i < selectArray.length; i++) {
								this.numberList[selectArray[i]-1].select = true;
							}
						}
			
					}
				});
			},
			createOrder() {
				let order = '';
				let now = new Date();
				order += now.getFullYear();
				order += now.getMonth() + 1;
				order += now.getDate();
				order += now.getHours();
				order += now.getMinutes();
				order += now.getSeconds();
				order += now.getMilliseconds();
				return order;
			},
			bookClick() {
				let activeSeat = [];
				for(let i=0;i<this.numberList.length;i++){
					if(this.numberList[i].active){
						activeSeat.push(this.numberList[i].name.replace('号',''));
					}
				}
				if (activeSeat.length==0) {
					this.$message({
					  type: 'error',
					  message: '请选择要预定的位置!',
					  duration: 1500
					});
					return
				}
				let data = {
					orderid: this.createOrder(),
					tablename: this.tablename,
					userid: Number(localStorage.getItem('frontUserid')),
					goodid: this.detail.id,
					goodname: this.title,
					picture:this.detailBanner[0],
					buynumber: activeSeat.length,
					total: 0,
					discounttotal: 0,
					address: activeSeat.join(','),
					activeSeat: activeSeat.join(','),
					status: '已支付',
					discountprice: this.detail.vipprice?this.detail.vipprice :this.detail.price 
				}
				if (this.detail.price) {
					data['status'] = '未支付'
					data['price'] = this.detail.price
					data['total'] = parseFloat(data['price'] * activeSeat.length).toFixed(2)
					localStorage.setItem('orderGoods', JSON.stringify([data]));
					// 跳转到确认下单页面
					let query = {
						type: 1,
						seat: 1
					}
					this.$confirm('是否确认预定？').then(_ => {
						this.$router.push({path: '/index/shop-order/orderConfirm', query: query});
					}).catch(_ => {});
				} else {
					data['status'] = '已支付'
					data['price'] = 0
					data['total'] = parseFloat(data['price'] * activeSeat.length).toFixed(2)
					localStorage.setItem('orderGoods', JSON.stringify([data]));
					// 跳转到确认下单页面
					let query = {
						type: 1,
						seat: 1
					}
					this.$confirm('是否确认预定？').then(_ => {
						this.$router.push({path: '/index/shop-order/orderConfirm', query: query});
					}).catch(_ => {});
				}
			},
			selectTip() {
				this.$message({
					type: 'error',
					message: '该座位已被预定!',
					duration: 1500
				});
			},
			selectSeat(item) {
				item.active = true;
			},
			unselectSeat(item) {
				item.active = false;
			},
			storeup(type) {
				if (type == 1 && !this.isStoreup) {
					this.storeupParams.name = this.title;
					this.storeupParams.picture = this.detailBanner[0];
					this.storeupParams.refid = this.detail.id;
					this.storeupParams.type = String(type);
					this.$http.post('storeup/add', this.storeupParams).then(res => {
						if (res.data.code == 0) {
							this.isStoreup = true;
							this.detail.storeupnum++
							this.$http.post('piaowuxinxi/update', this.detail).then(res => {});
							this.$message({
								type: 'success',
								message: '收藏成功!',
								duration: 1500,
							});
						}
					});
				}
				if (type == -1 && this.isStoreup) {
					this.$http.get('storeup/list', {params: {page: 1, limit: 1, type: '1', refid: this.detail.id, tablename: 'piaowuxinxi', userid: Number(localStorage.getItem('frontUserid'))}}).then(res => {
						if (res.data.code == 0 && res.data.data.list.length > 0) {
							this.isStoreup = true;
							this.storeupInfo = res.data.data.list[0];
							let delIds = new Array();
							delIds.push(this.storeupInfo.id);
							this.$http.post('storeup/delete', delIds).then(res => {
								if (res.data.code == 0) {
									this.isStoreup = false;
									this.detail.storeupnum--
									this.$http.post('piaowuxinxi/update', this.detail).then(res => {});
									this.$message({
										type: 'success',
										message: '取消成功!',
										duration: 1500,
									});
								}
							});
						}
					});
				}
			},
			getStoreupStatus(){
				if(localStorage.getItem("frontToken")) {
					this.$http.get('storeup/list', {params: {page: 1, limit: 1, type: '1', refid: this.detail.id, tablename: 'piaowuxinxi', userid: Number(localStorage.getItem('frontUserid'))}}).then(res => {
						if (res.data.code == 0 && res.data.data.list.length > 0) {
							this.isStoreup = true;
							this.storeupInfo = res.data.data.list[0];
						}
					});
				}
			},
			thumbsupOrCrazily(type) {
				this.storeupParams.name = this.title;
				this.storeupParams.picture = this.detailBanner[0];
				this.storeupParams.refid = this.detail.id;
				this.storeupParams.type = String(type);
				this.$http.post('storeup/add', this.storeupParams).then(res => {
					if (res.data.code == 0) {
						this.getThumbsupOrCrazilyStatus();
						this.$message({
							type: 'success',
							message: '操作成功!',
							duration: 1500,
						});
					}
				});

				if (type == 21) this.detail.thumbsupnum = Number(this.detail.thumbsupnum) + 1;
				if (type == 22) this.detail.crazilynum = Number(this.detail.crazilynum) + 1;
				this.$http.post('piaowuxinxi/update', this.detail).then(res => {});
			},
			cancelThumbsupOrCrazily(type) {
				let delIds = new Array();
				delIds.push(this.thumbsupOrCrazilyInfo.id);
				this.$http.post('storeup/delete', delIds).then(res => {
					if (res.data.code == 0) {
						this.isThumbsupnum = false;
						this.isCrazilynum = false;
						this.$message({
							type: 'success',
							message: '取消成功!',
							duration: 1500,
						});
					}
				});
				if (type == 21) this.detail.thumbsupnum -= 1;
				if (type == 22) this.detail.crazilynum -= 1;
				this.$http.post('piaowuxinxi/update', this.detail).then(res => {});
			},
			getThumbsupOrCrazilyStatus() {
				if(localStorage.getItem("frontToken")) {
					this.$http.get('storeup/list', {params: {page: 1, limit: 1, type: '21', refid: this.detail.id, tablename: 'piaowuxinxi', userid: Number(localStorage.getItem('frontUserid'))}}).then(res => {
						if (res.data.code == 0 && res.data.data.list.length > 0) {
							this.isThumbsupnum = true;
							this.thumbsupOrCrazilyInfo = res.data.data.list[0];
						}
					});
					this.$http.get('storeup/list', {params: {page: 1, limit: 1, type: '22', refid: this.detail.id, tablename: 'piaowuxinxi', userid: Number(localStorage.getItem('frontUserid'))}}).then(res => {
						if (res.data.code == 0 && res.data.data.list.length > 0) {
							this.isCrazilynum = true;
							this.thumbsupOrCrazilyInfo = res.data.data.list[0];
						}
					});
				}
			},
			curChange(page) {
				this.getDiscussList(page);
			},
			prevClick(page) {
				this.getDiscussList(page);
			},
			nextClick(page) {
				this.getDiscussList(page);
			},
			sizeChange(size){
				this.pageSize = size
				this.getDiscussList(1);
			},
			// 返回按钮
			backClick(){
				if(this.storeupType){
					history.back()
				}else{
					let params = {}
					if(this.centerType){
						params.centerType = 1
					}
					this.$router.push({path: '/index/piaowuxinxi', query: params});
				}
			},
			// 下载
			download(file ){
				if(!file) {
					this.$message({
						type: 'error',
						message: '文件不存在',
						duration: 1500,
					});
					return;
				}
				let arr = file.replace(new RegExp('upload/', "g"), "")
				axios.get(this.baseUrl + '/file/download?fileName=' + arr, {
					headers: {
						token: localStorage.getItem("frontToken")
					},
					responseType: "blob"
				}).then(({
					data
				}) => {
					const binaryData = [];
					binaryData.push(data);
					const objectUrl = window.URL.createObjectURL(new Blob(binaryData, {
						type: 'application/pdf;chartset=UTF-8'
					}))
					const a = document.createElement('a')
					a.href = objectUrl
					a.download = arr
					// a.click()
					// 下面这个写法兼容火狐
					a.dispatchEvent(new MouseEvent('click', {
						bubbles: true,
						cancelable: true,
						view: window
					}))
					window.URL.revokeObjectURL(data)
				},err=>{
					axios.get((location.href.split(this.$config.name).length>1 ? location.href.split(this.$config.name)[0] :'') + this.$config.name + '/file/download?fileName=' + arr, {
						headers: {
							token: localStorage.getItem("frontToken")
						},
						responseType: "blob"
					}).then(({
						data
					}) => {
						const binaryData = [];
						binaryData.push(data);
						const objectUrl = window.URL.createObjectURL(new Blob(binaryData, {
							type: 'application/pdf;chartset=UTF-8'
						}))
						const a = document.createElement('a')
						a.href = objectUrl
						a.download = arr
						// a.click()
						// 下面这个写法兼容火狐
						a.dispatchEvent(new MouseEvent('click', {
							bubbles: true,
							cancelable: true,
							view: window
						}))
						window.URL.revokeObjectURL(data)
					})
				})
			},
			getDiscussList(page) {
				this.$http.get('discusspiaowuxinxi/list', {params: {page, limit: this.pageSize, refid: this.detail.id,sort: 'istop',order: 'desc'}}).then(res => {
					if (res.data.code == 0) {
						this.infoList = res.data.data.list;
						this.total = res.data.data.total;
						this.pageSize = Number(res.data.data.pageSize);
						this.totalPage = res.data.data.totalPage;
					}
				});
			},
			comzanChange(row){
				if(row.tuserids){
					let arr = row.tuserids.split(',')
					for(let x in arr){
						if(arr[x] == this.userid){
							return true
						}
					}
				}
				return false
			},
			comzanClick(row){
				if(!this.userid){
					return false
				}
				if(!this.comzanChange(row)){
					row.thumbsupnum++
					if(row.tuserids){
						row.tuserids = row.tuserids + ',' + this.userid
					}else {
						row.tuserids = String(this.userid)
					}
					this.$http.post('discusspiaowuxinxi/update',row).then(rs=>{
						this.$message.success('点赞成功')
					})
				}else {
					row.thumbsupnum--
					let arr = row.tuserids.split(',')
					for(let x in arr){
						if(arr[x] == this.userid){
							arr.splice(x,1)
						}
					}
					row.tuserids = arr.join(',')
					this.$http.post('discusspiaowuxinxi/update',row).then(rs=>{
						this.$message.success('取消成功')
					})
				}
			},
			comcaiChange(row){
				if(row.cuserids){
					let arr = row.cuserids.split(',')
					for(let x in arr){
						if(arr[x] == this.userid){
							return true
						}
					}
				}
				return false
			},
			comcaiClick(row){
				if(!this.userid){
					return false
				}
				if(!this.comcaiChange(row)){
					row.crazilynum++
					if(row.cuserids){
						row.cuserids = row.cuserids + ',' + this.userid
					}else {
						row.cuserids = String(this.userid)
					}
					this.$http.post('discusspiaowuxinxi/update',row).then(rs=>{
						this.$message.success('点踩成功')
					})
				}else {
					row.crazilynum--
					let arr = row.cuserids.split(',')
					for(let x in arr){
						if(arr[x] == this.userid){
							arr.splice(x,1)
						}
					}
					row.cuserids = arr.join(',')
					this.$http.post('discusspiaowuxinxi/update',row).then(rs=>{
						this.$message.success('取消成功')
					})
				}
			},
			discussEnter(index){
				this.showIndex = index
			},
			discussLeave(){
				this.showIndex = -1
			},
			discussDel(id){
				this.$confirm('是否删除此评论？').then(_ => {
					this.$http.post('discusspiaowuxinxi/delete',[id]).then(res=>{
						if(res.data&&res.data.code==0){
							this.addDiscussNum(1)
							this.$message({
								type: 'success',
								message: '删除成功!',
								duration: 1500,
								onClose: () => {
									this.getDiscussList(1);
								}
							});
						}
					})
				}).catch(_ => {});
			},
			submitForm(formName) {
				this.$refs[formName].validate((valid) => {
					if (valid) {
						this.$http.get('orders/list', {params: {page: 1, limit: 1, status: '已完成', goodid: this.detail.id, userid: Number(localStorage.getItem('frontUserid'))}}).then(res => {
							if (res.data.code == 0 && res.data.data.list.length == 0) {
								this.$message({
									type: 'error',
									message: '请完成订单后再评论!',
									duration: 1500
								});
								return false
							} else {
								this.form.refid = this.detail.id;
								this.form.avatarurl = localStorage.getItem('frontHeadportrait')?localStorage.getItem('frontHeadportrait'):'';
								this.$http.post('discusspiaowuxinxi/add', this.form).then(rs2 => {
									if (rs2.data.code == 0) {
										this.addDiscussNum(2)
										this.form.content = '';
										this.getDiscussList(1);
										this.$message({
											type: 'success',
											message: '评论成功!',
											duration: 1500,
										});
									}
								});
							}
						});
					} else {
						return false;
					}
				});
			},
			resetForm(formName) {
				this.$refs[formName].resetFields();
			},
			addDiscussNum(type){
				if(type==2){
					this.detail.discussnum++
				}else if(type==1){
					if(this.detail.discussnum!=0){
						this.detail.discussnum--
					}else{
						this.detail.discussnum = 0
					}
				}
				this.$http.post('piaowuxinxi/update',this.detail).then(res=>{})
			},
			//立即购买
			buyNow() {
				// 保存到storage中，在确认下单页面中获取要购买的商品
				localStorage.setItem('orderGoods', JSON.stringify([{
					tablename: this.tablename,
					goodid: this.detail.id,
					goodname: this.title,

					picture:this.detailBanner[0],
					buynumber: this.buynumber,
					userid: Number(localStorage.getItem('frontUserid')),
					price: this.detail.price,
					discountprice: this.detail.vipprice ? this.detail.vipprice : this.detail.price
				}]));
				// 跳转到确认下单页面
				let query = {
					type: 1,
				}
				this.$router.push({path: '/index/shop-order/orderConfirm', query: query});
			},


			// 权限判断
			btnAuth(tableName,key){
				if(this.centerType){
					return this.isBackAuth(tableName,key)
				}else{
					return this.isAuth(tableName,key)
				}
			},
			// 修改
			editClick(){
				this.$router.push(`/index/piaowuxinxiAdd?type=edit&&id=${this.detail.id}`);
			},
			// 删除
			async delClick(){
				await this.$confirm('是否删除此票务信息？') .then(_ => {
					this.$http.post('piaowuxinxi/delete', [this.detail.id]).then(async res => {
						if (res.data.code == 0) {
							this.$http.get('storeup/list',{params:{
								page: 1,
								limit: 100,
								refid: this.detail.id,
								tablename: 'piaowuxinxi',
							}}).then(async obj=>{
								if(obj.data&&obj.data.code==0){
									let arr = []
									for(let x in obj.data.data.list){
										arr.push(obj.data.data.list[x].id)
									}
									if(arr.length){
										await this.$http.post('storeup/delete',arr).then(()=>{})
									}
									this.$message({
										type: 'success',
										message: '删除成功!',
										duration: 1500,
										onClose: () => {
											history.back()
										}
									});
								}
							})
						}
					});
				}).catch(_ => {});
			},
			// 获取uuid
			getUUID() {
				return new Date().getTime();
			},
			couponClick() {
				this.couponList = []
				this.couponVisible = true
				this.getCouponList()
			},
			getCouponList() {
				this.$http.get('coupon/lists').then(res => {
					if (res.data && res.data.code == 0) {
						for (let x in res.data.data) {
							if (this.changeCoupon(res.data.data[x])) {
								this.couponList.push(res.data.data[x])
							}
						}
					}
				})
			},
			changeCoupon(row) {
				if (!this.isBetweenTime(this.getCurDateTime(), [row.startime, row.endtime])) {
					return false
				}
				return true
			},
			isBetweenTime(currentDate, timeQuantum) {
				let isBetween = true;
				let currentTime = new Date(currentDate);
				let startTime = new Date(timeQuantum[0]);
				let endTime = new Date(timeQuantum[1]);
				let t1 = currentTime.getTime() - startTime.getTime();
				let t2 = currentTime.getTime() - endTime.getTime();
				if (t1 < 0 || t2 > 0) {
					isBetween = false;
				}
				return isBetween;
			},
			getCoupon(item) {
				this.$http.get('mycoupon/page', {
					params: {
						page: 1,
						couponid: item.id
					}
				}).then(res => {
					if (res.data && res.data.code == 0) {
						if (res.data.data.list.length) {
							this.$message.error('已领取该优惠券，请前往使用')
							return false
						}
						let data = {
							couponid: item.id,
						}
						data = Object.assign(data, JSON.parse(JSON.stringify(item)))
						data.userid = Number(localStorage.getItem('frontUserid'))
						data.couponnumber = this.getUUID()
						delete data.id
						delete data.addtime
						this.$http.post('mycoupon/add', data).then(res => {
							if (res.data && res.data.code == 0) {
								this.$message.success('领取成功！')
							}
						})
					}
				})
			},
		},
		components: {
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.detail-preview {
		margin: 10px auto;
		display: flex;
		width: 1300px;
		position: relative;
		flex-wrap: wrap;
		.attr {
			padding: 0 0 0 30px;
			background: none;
			flex: 1 1 auto;
			display: flex;
			position: relative;
			order: 2;
			.info {
				padding: 0;
				margin: 0;
				background: none;
				flex: 1;
				width: 50%;
				.title-item {
					border: 1px solid #aaa8e9;
					border-radius: 30px;
					padding: 2px  0  2px 30px;
					margin: 0 0 10px 0;
					background: #F8F9FB;
					display: flex;
					justify-content: space-between;
					align-items: center;
					.detail-title {
						color: #333;
						font-size: 16px;
					}
					.colectBtn {
						cursor: pointer;
						border-radius: 30px;
						padding: 10px;
						background: #A7A9CB;
						.icon {
							color: #FFFFFF;
							font-size: 14px;
						}
						.text {
							color: #FFFFFF;
							font-size: 14px;
						}
					}
					.colectBtnActive {
						background: #A7A9CB;
						.icon {
							color: #FFFFFF;
						}
						.text {
							color: #FFFFFF;
						}
					}
					.colectBtn:hover {
						background: #A7A9CB;
						.icon {
							color: #FFFFFF;
						}
						.text {
							color: #FFFFFF;
						}
					}
					.colectBtnActive:hover {
						background: #A7A9CB;
						.icon {
							color: #FFFFFF;
						}
						.text {
							color: #FFFFFF;
						}
					}
				}
				.coupon-item {
					border: 1px solid #aaa8e9;
					border-radius: 30px;
					padding: 2px  0  2px 30px;
					margin: 0 0 10px 0;
					background: #F8F9FB;
					display: flex;
					justify-content: space-between;
					align-items: center;
					.coupon-kong {
						color: #333;
						font-size: 16px;
					}
					.couponBtn {
						border: 0;
						cursor: pointer;
						border-radius: 4px;
						padding: 0 10px;
						margin: 0 5px 0 0;
						outline: none;
						color: #fff;
						background: linear-gradient( 315deg, #312BED 0%, #AFD5FB 100%);
						width: auto;
						font-size: 14px;
						line-height: 40px;
						height: 40px;
					}
					.couponBtn:hover {
						opacity: 0.9;
					}
				}
				.item {
					border: 1px solid #aaa8e9;
					border-radius: 30px;
					padding: 5px 30px;
					margin: 0 0 10px 0;
					background: #F8F9FB;
					display: flex;
					justify-content: spaceBetween;
					.lable {
						padding: 0 10px;
						color: #9E9E9E;
						white-space: nowrap;
						width: auto;
						font-size: 14px;
						line-height: 30px;
						text-align: right;
						height: auto;
					}
					.text {
						padding: 0 10px;
						color: #000000;
						word-break: break-all;
						flex: 1;
						font-size: 14px;
						line-height: 30px;
						height: auto;
					}
					.price {
						color: #f00;
					}
					.bold {
						font-weight: bold;
					}
					.link {
						cursor: pointer;
						text-decoration: underline;
					}
				}
				.btn_box {
					padding: 10px 0;
					display: flex;
					flex-wrap: wrap;
				}
				.reserveBtn {
					border: 0;
					cursor: pointer;
					border-radius: 4px;
					padding: 0 10px;
					margin: 0 5px 0 0;
					outline: none;
					color: #fff;
					background: #BCB3F7;
					width: auto;
					font-size: 14px;
					line-height: 40px;
					height: 40px;
				}
				.reserveBtn:hover {
					opacity: 0.9;
				}
				.editBtn {
					border: 0;
					cursor: pointer;
					border-radius: 4px;
					padding: 0 10px;
					margin: 0 5px 0 0;
					outline: none;
					color: #fff;
					background: #6BBDEB;
					width: auto;
					font-size: 14px;
					line-height: 40px;
					height: 40px;
				}
				.editBtn:hover {
					opacity: 0.9;
				}
				.delBtn {
					border: 0;
					cursor: pointer;
					border-radius: 4px;
					padding: 0 10px;
					margin: 0 5px 0 0;
					outline: none;
					color: #fff;
					background: #7490F2;
					width: auto;
					font-size: 14px;
					line-height: 40px;
					height: 40px;
				}
				.delBtn:hover {
					opacity: 0.9;
				}
			}
		}
		.detail-swpier2 {
			padding: 0 0 80px 0;
			background: none;
			width: 646px;
			height: auto;
			order: 1;
			.swiper21 {
				width: 100%;
				height: 580px;
				.swiper-button-prev:after {
					display:none;
				}
				.swiper-button-next:after {
					display:none;
				}
				.swiper-item {
					width: 100%;
					height: auto;
					img {
						object-fit: cover;
						width: 100%;
						height: 580px;
					}
				}
				.swiper-button-prev {
					margin: -12px 0 0;
					top: 50%;
					width: 24px;
					height: 24px;
					.icon {
						color: #fff;
						width: 24px;
						font-size: 24px;
						height: 24px;
					}
				}
				.swiper-button-next {
					margin: -12px 0 0;
					top: 50%;
					width: 24px;
					height: 24px;
					.icon {
						color: #fff;
						width: 24px;
						font-size: 24px;
						height: 24px;
					}
				}
			}
			.swiper22 {
				margin: 20px 0 0;
				width: 100%;
				height: 100px;
				
				.swiper-button-prev:after {
					display:none;
				}
				.swiper-button-next:after {
					display:none;
				}
				
				.swiper-item {
					width: 100%;
					opacity: 0.4;
					height: auto;
					img {
						object-fit: cover;
						width: 100%;
						height: 100px;
					}
				}
				.swiper-slide.swiper-slide-thumb-active div {
					opacity: 1;
				}
				.swiper-button-prev {
					margin: -7px 0 0;
					top: 50%;
					width: 14px;
					height: 14px;
					.icon {
						color: #fff;
						width: 14px;
						font-size: 14px;
						height: 14px;
					}
				}
				.swiper-button-next {
					margin: -7px 0 0;
					top: 50%;
					width: 14px;
					height: 14px;
					.icon {
						color: #fff;
						width: 14px;
						font-size: 14px;
						height: 14px;
					}
				}
			}
		}
		.zancai {
			padding: 0;
			margin: 0;
			z-index: 2;
			top: 726px;
			left: 0;
			background: none;
			display: flex;
			width: 646px;
			position: absolute;
			.zan {
				cursor: pointer;
				border-radius: 0;
				padding: 10px 0;
				margin: 0;
				background: #DFDFDF;
				display: flex;
				width: 100%;
				justify-content: center;
				align-items: center;
				.icon {
					margin: 0 3px;
					color: #000000;
					font-size: 14px;
				}
				.text {
					margin: 0 3px;
					color: #000000;
					font-size: 14px;
				}
			}
			.zan:hover {
				background: #DFDFDF;
				.icon {
					color: #000000;
				}
				.text {
					color: #000000;
				}
			}
			.zanActive {
				cursor: pointer;
				border-radius: 0;
				padding: 10px 0;
				margin: 0;
				background: #DFDFDF;
				display: flex;
				width: 100%;
				justify-content: center;
				align-items: center;
				.icon {
					color: #000000;
				}
				.text {
					color: #000000;
				}
			}
			.zanActive:hover {
				background: #DFDFDF;
				.icon {
					color: #000000;
				}
				.text {
					color: #000000;
				}
			}
			
			.cai {
				cursor: pointer;
				border-radius: 0;
				padding: 10px 0;
				margin: 0;
				background: linear-gradient( 315deg, #312BED 0%, #AFD5FB 100%);
				display: flex;
				width: 100%;
				justify-content: center;
				align-items: center;
				.icon {
					margin: 0 3px;
					color: #FFFFFF;
					font-size: 14px;
				}
				.text {
					margin: 0 3px;
					color: #FFFFFF;
					font-size: 14px;
				}
			}
			.cai:hover {
				background: linear-gradient( 315deg, #312BED 0%, #AFD5FB 100%);
				.icon {
					color: #FFFFFF;
				}
				.text {
					color: #FFFFFF;
				}
			}
			.caiActive {
				cursor: pointer;
				border-radius: 0;
				padding: 10px 0;
				margin: 0;
				background: linear-gradient( 315deg, #312BED 0%, #AFD5FB 100%);
				display: flex;
				width: 100%;
				justify-content: center;
				align-items: center;
				.icon {
					color: #FFFFFF;
				}
				.text {
					color: #FFFFFF;
				}
			}
			.caiActive:hover {
				background: linear-gradient( 315deg, #312BED 0%, #AFD5FB 100%);
				.icon {
					color: #FFFFFF;
				}
				.text {
					color: #FFFFFF;
				}
			}
		}
		video {
			border: 0;
			border-radius: 10px;
			margin: 20px 0 0;
			outline: none;
			background: #000;
			display: block;
			width: 100%;
			order: 3;
			height: 580px;
		}
		.detail-tabs {
			border: 1px solid #aaa8e9;
			border-radius: 20px;
			box-shadow: 0 1px 6px 0 rgba(0, 0, 0, .1);
			padding: 20px;
			margin: 20px 0 0;
			background: #FFF;
			width: 100%;
			order: 4;
			& /deep/ .el-tabs__header .el-tabs__nav-wrap {
				margin-bottom: 0;
			}
			/deep/ .el-tabs__header {
				margin: 0;
				background: #DFE6FF;
				border-color: #aaa8e9;
				border-width: 0 0 1px 0;
				border-style: solid;
			}
			
			/deep/ .el-tabs__header .el-tabs__item {
				border: 0;
				padding: 0 20px;
				margin: 0;
				color: #000000;
				background: transparent;
				font-weight: 500;
				display: inline-block;
				font-size: 14px;
				line-height: 40px;
				position: relative;
				list-style: none;
				height: 40px;
			}
			
			/deep/ .el-tabs__header .el-tabs__item:hover {
				border: 0;
				color: #FFF;
				background: #423DD2;
			}
			
			/deep/ .el-tabs__header .el-tabs__item.is-active {
				border: 0;
				color: #FFF;
				background: #423DD2;
			}
			
			/deep/ .el-tabs__content {
				padding: 15px;
			}
			.commentForm {
				border: 1px solid #aaa8e9;
				border-radius: 10px;
				box-shadow: none;
				padding: 10px;
				margin: 0 0 20px;
				.item {
					display: flex;
					width: 100%;
					height: auto;
					/deep/ .el-form-item__label {
						padding: 0 10px 0 0;
						color: #666;
						width: 80px;
						font-size: 14px;
						line-height: 40px;
						text-align: right;
					}
					.editor {
						border: 0;
						border-radius: 4px;
						box-shadow: none;
						outline: none;
						color: #333;
						width: 100%;
						font-size: 14px;
						min-height: 300px;
						line-height: 32px;
						/deep/ .avatar-uploader {
							height: 0;
							line-height: 0;
						}
					}
				}
				.commentBtn {
					padding: 0 0 0 80px;
					margin: 10px 0 0;
					width: 100%;
					height: auto;
					.submitBtn {
						border: 0;
						cursor: pointer;
						border-radius: 4px;
						padding: 0;
						margin: 0 20px 0 0;
						outline: none;
						color: rgba(255, 255, 255, 1);
						background: linear-gradient( 313deg, #423DD2 0%, #B1D8FB 100%);
						width: 128px;
						font-size: 14px;
						line-height: 40px;
						height: 40px;
					}
					.submitBtn:hover {
						opacity: 0.9;
					}
					.resetBtn {
						border: 0;
						cursor: pointer;
						border-radius: 4px;
						padding: 0;
						margin: 0 20px 0 0;
						outline: none;
						color: rgba(255, 255, 255, 1);
						background: rgba(64, 158, 255, 1);
						width: 128px;
						font-size: 14px;
						line-height: 40px;
						height: 40px;
					}
					.resetBtn:hover {
						background: rgba(64, 158, 255, 0.5);
					}
				}
			}
			.comment-list {
				box-shadow: none;
				padding: 0;
				.comment-item {
					border: 1px solid #aaa8e9;
					border-radius: 10px;
					padding: 10px;
					margin: 0 0 20px;
					width: 100%;
					align-items: center;
					position: relative;
					height: auto;
					.istop {
						box-shadow: 0 4px 8px rgba(0,0,0,.1);
						top: 0;
						background: #fff;
						position: absolute;
						right: 10px;
						.icon {
							color: #000;
						}
					}
					.user {
						display: flex;
						width: 100%;
						align-items: center;
						height: auto;
						.el-image {
							border-radius: 100%;
							margin: 0 10px 0 0;
							object-fit: cover;
							width: 40px;
							height: 40px;
						}
						.name {
							color: #9E9E9E;
							font-size: 16px;
						}
					}
					.comment-time {
						color: #666;
					}
					.comment-content-box {
						border-radius: 4px;
						padding: 8px;
						box-shadow: none;
						margin: 10px 0px 0px;
						word-wrap: break-word;
						color: #333;
						background: none;
						font-size: 14px;
						line-height: 30px;
						.zancai-box {
							margin: 8px 0 0 0;
							display: flex;
							width: 100%;
							justify-content: flex-end;
							align-items: center;
							height: 30px;
							.zan-item {
								display: flex;
								align-items: center;
								.icon {
								font-size: 14px;
								}
								.label {
								display: none;
								font-size: 14px;
								}
								.num {
								font-size: 14px;
								}
							}
							.zan-item.active {
								background: none;
								.icon {
								color: #ff0000;
								font-size: 14px;
								}
								.label {
								color: #ff0000;
								display: none;
								font-size: 14px;
								}
								.num {
								color: #ff0000;
								font-size: 14px;
								}
							}
							.zan-item:hover {
								opacity: 0.8;
								.icon {
								color: #ff000030;
								}
								.label {
								color: #ff000030;
								}
								.num {
								color: #ff000030;
								}
							}
							.cai-item {
								display: flex;
								align-items: center;
								.icon {
								font-size: 14px;
								}
								.label {
								display: none;
								font-size: 14px;
								}
								.num {
								font-size: 14px;
								}
							}
							.cai-item.active {
								background: none;
								.icon {
								color: #ff0000;
								font-size: 14px;
								}
								.label {
								color: #ff0000;
								display: none;
								font-size: 14px;
								}
								.num {
								color: #ff0000;
								font-size: 14px;
								}
							}
							.cai-item:hover {
								opacity: 0.8;
								.icon {
								color: #ff000030;
								}
								.label {
								color: #ff000030;
								}
								.num {
								color: #ff000030;
								}
							}
						}
						.comment-btn {
							margin: 8px 0 0 0;
							display: flex;
							width: 100%;
							justify-content: flex-end;
							align-items: center;
							height: 40px;
							.delBtn {
								border: 0;
								cursor: pointer;
								border-radius: 4px;
								padding: 0 20px;
								margin: 0 10px;
								outline: none;
								color: #423DD2;
								background: #B1D8FB;
								width: auto;
								font-size: 14px;
								line-height: 40px;
								height: 40px;
							}
						}
					}
				}
			}
		}
	}
    .seat-list{
        display: flex;
        align-items: center;
        flex-wrap: wrap;
        .seat-item{
            width: 20%;
            display: flex;
            flex-direction: column;
            align-items: center;
			img {
				width: 60px;
			}
            span{
                line-height: 3;
            }
        }
    }
	.share_view{
		box-shadow: 0 1px 6px rgba(0,0,0,.3);
		z-index: 11;
		bottom: 20%;
		background: #fff;
		position: fixed;
		right: 30px;
		.share:last-of-type{
			border:none;
		}
	}
	.coupon_list {
		width: 100%;
		display: flex;
		flex-wrap: wrap;
	
		.coupon {
			width: 31%;
			background: url('../../assets/coupon.png');
			background-repeat: no-repeat;
			background-position: center center;
			background-size: cover;
			padding: 60px 30px;
			display: flex;
			align-items: center;
			margin: 0 1% 20px;
	
			position: relative;
	
			.no-coupon {
				position: absolute;
				width: 80px;
				right: 20px;
				top: 20px;
			}
	
			.coupon_left {
				flex: 1;
				color: #000;
	
				.name {
					font-size: 22px;
					line-height: 1.5;
				}
	
				.date {
					font-size: 14px;
					line-height: 1.5;
				}
				.date1 {
					font-size: 12px;
					line-height: 1.5;
					color: #FF0800;
				}
			}
	
			.coupon_right {
				display: flex;
				flex-direction: column;
				align-items: center;
	
				.price {
					color: #FF0800;
					font-size: 20px;
					padding: 0 0 10px;
	
					.num {
						font-size: 28px;
					}
				}
	
				.btn {
					width: 100px;
					height: 30px;
					line-height: 30px;
					border-radius: 30px;
					background: #FF0800;
					text-align: center;
					color: #fff;
					font-size: 18px;
					cursor: pointer;
				}
			}
		}
	
		.noList {
			color: #9e9e9e;
			width: 100%;
			text-align: center;
			padding: 60px 0;
		}
	}
</style>
