<template>
	<div>
		<div class="breadcrumb-preview">
			<el-breadcrumb :separator="'Ξ'">
				<el-breadcrumb-item class="item1" to="/"><a>首页</a></el-breadcrumb-item>
				<el-breadcrumb-item class="item2" v-for="(item, index) in breadcrumbItem" :key="index"><a>{{item.name}}</a></el-breadcrumb-item>
			</el-breadcrumb>
		</div>
		<div v-if="centerType" class="back_box">
			<el-button class="backBtn" size="mini" @click="backClick">
				<span class="icon iconfont icon-jiantou33"></span>
				<span class="text">返回</span>
			</el-button>
		</div>
		<div class="list-preview">
			<el-form :inline="true" :model="formSearch" class="list-form-pv">
				<el-form-item class="list-item">
					<div class="lable">名称：</div>
					<el-input v-model="formSearch.name" placeholder="名称" @keydown.enter.native="getList(1, curFenlei)" clearable></el-input>
				</el-form-item>
				<el-form-item class="list-item">
					<div class="lable">生效时间：</div>
					<el-date-picker
						class="datetimerange"
						v-model="timeRange"
						type="datetimerange"
						range-separator="至"
						start-placeholder="生效时间起始"
						end-placeholder="生效时间结束"
						value-format="yyyy-MM-dd HH:mm:ss">
					</el-date-picker>
				</el-form-item>
				<el-form-item class="list-item">
					<div class="lable">过期时间：</div>
					<el-date-picker
						class="datetimerange"
						v-model="timeRange"
						type="datetimerange"
						range-separator="至"
						start-placeholder="过期时间起始"
						end-placeholder="过期时间结束"
						value-format="yyyy-MM-dd HH:mm:ss">
					</el-date-picker>
				</el-form-item>
				<el-button class="list-search-btn" v-if=" true " type="primary" @click="getList(1, curFenlei)">
					<i class="el-icon-search"></i>
					查询
				</el-button>
				<el-button class="list-add-btn" v-if="btnAuth('coupon','新增')" type="primary" @click="add('/index/couponAdd')">
					<i class="el-icon-circle-plus-outline"></i>
					添加
				</el-button>
			</el-form>
			<div class="select2">
				<div class="select2-list" v-for="(item,index) in selectOptionsList" :key="index">
					<div class="label">{{item.name}}：</div>
					<div class="item-body">
						<div class="item" @click="selectClick2(item,-1)" :class="item.check ==-1 ? 'active' : ''">全部</div>
						<div class="item" @click="selectClick2(item,index1)" :class="item.check == index1 ? 'active' : ''" v-for="item1,index1 in item.list" :key="index1">{{item1}}</div>
					</div>
				</div>
			</div>
			<div class="list">
				<!-- 样式二 -->
				<div class="list2 index-pv1">
					<div v-for="(item, index) in dataList" :key="index" class="list-item animation-box" @click.stop="toDetail(item)">
						<div class="item-info">
							<div class="time_item">
								<span class="icon iconfont icon-shijian21"></span>
								<span class="label">发布时间：</span>
								<span class="text">{{item.addtime}}</span>
							</div>
							<div class="more_btn">
							  查看详情
							</div>
						</div>
					</div>
				</div>
			</div>

	
			<el-pagination
				background
				id="pagination"
				class="pagination"
				:pager-count="7"
				:page-size="pageSize"
				prev-text="<"
				next-text=">"
				:hide-on-single-page="true"
				:layout='["total","prev","pager","next","sizes","jumper"].join()'
				:total="total"
				:page-sizes="pageSizes"
				@current-change="curChange"
				@size-change="sizeChange"
				@prev-click="prevClick"
				@next-click="nextClick"
				></el-pagination>
		</div>
		<el-dialog title="预览图" :visible.sync="previewVisible" width="50%">
			<img :src="previewImg" alt="" style="width: 100%;">
		</el-dialog>
	</div>
</template>
<script>
	export default {
		//数据集合
		data() {
			return {
				selectIndex2: 0,
				selectOptionsList: [],
				layouts: '',
				swiperIndex: -1,
				baseUrl: '',
				breadcrumbItem: [
					{
						name: '优惠券'
					}
				],
				formSearch: {
					name: '',
					type: '',
					startime: '',
					endtime: '',
				},
				fenlei: [],
				dataList: [],
				total: 1,
				pageSize: 6,
				pageSizes: [],
				totalPage: 1,
				curFenlei: '全部',
				isPlain: false,
				indexQueryCondition: '',
				typeOptions: [],
				timeRange: [],
				centerType:false,
				previewImg: '',
				previewVisible: false,
				sortType: 'id',
				sortOrder: 'desc',
			}
		},
		async created() {
			if(this.$route.query.centerType&&this.$route.query.centerType!=0){
				this.centerType = true
			}
			this.baseUrl = this.$config.baseUrl;
			this.typeOptions = '满减券'.split(',');
			this.selectOptionsList.push({name:'券类型',list:this.typeOptions,tableName: 'type',check: -1})
			await this.getFenlei();
			let fenlei = '全部'
			if(this.$route.query.homeFenlei){
				fenlei = this.$route.query.homeFenlei
			}
			this.getList(1, fenlei);
		},
		watch:{
			$route(newValue){
				this.getList(1, newValue.query.homeFenlei);
			}
		},
		//方法集合
		methods: {
			selectClick2(row,index) {
				row.check = index
				if(index == -1){
					this.formSearch[row.tableName] = ''
				}else {
					this.formSearch[row.tableName] = row.list[index]
				}
				this.getList()
			},
			add(path) {
				let query = {}
				if(this.centerType){
					query.centerType = 1
				}
				this.$router.push({path: path,query:query});
			},
			async getFenlei() {
			},
			getList(page, fenlei, ref = '') {
				let params = {
					page,
					limit: this.pageSize,
				};
				let searchWhere = {};
				if (this.formSearch.name != '') searchWhere.name = '%' + this.formSearch.name + '%';
				if (this.formSearch.type != '') searchWhere.type = this.formSearch.type;
				if (this.timeRange.length > 0) {
					searchWhere.startimestart = this.timeRange[0];
					searchWhere.startimeend = this.timeRange[1];
				}
				if (this.timeRange.length > 0) {
					searchWhere.endtimestart = this.timeRange[0];
					searchWhere.endtimeend = this.timeRange[1];
				}
				let user = JSON.parse(localStorage.getItem('sessionForm'))
				if (this.sortType) searchWhere.sort = this.sortType
				if (this.sortOrder) searchWhere.order = this.sortOrder
				this.$http.get(`coupon/${this.centerType?'page':'list'}`, {params: Object.assign(params, searchWhere)}).then(res => {
					if (res.data.code == 0) {
						this.dataList = res.data.data.list;
						this.total = Number(res.data.data.total);
						this.pageSize = Number(res.data.data.pageSize);
						this.totalPage = res.data.data.totalPage;
						if(this.pageSizes.length==0){
							this.pageSizes = [this.pageSize, this.pageSize*2, this.pageSize*3, this.pageSize*5];
						}
					}
				});
			},
			sortClick(type){
				if(this.sortType==type){
					if(this.sortOrder == 'desc'){
						this.sortOrder = 'asc'
					}else{
						this.sortOrder = 'desc'
					}
				}else{
					this.sortType = type
					this.sortOrder = 'desc'
				}
				this.getList(1, '全部')
			},
			curChange(page) {
				this.getList(page);
			},
			prevClick(page) {
				this.getList(page);
			},
			sizeChange(size){
				this.pageSize = size
				this.getList(1);
			},
			nextClick(page) {
				this.getList(page);
			},
			imgPreView(url){
				this.previewImg = url
				this.previewVisible = true
			},
			toDetail(item) {
				let params = {
					id: item.id
				}
				if(this.centerType){
					params.centerType = 1
				}
				this.$router.push({path: '/index/couponDetail', query: params});
			},
			btnAuth(tableName,key){
				if(this.centerType){
					return this.isBackAuth(tableName,key)
				}else{
					return this.isAuth(tableName,key)
				}
			},
			backClick() {
				this.$router.push({path: '/index/center'});
			},
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.list-preview {
		padding: 0 calc(50% - 650px);
		margin: 10px auto;
		background: linear-gradient( 90deg, #EFEFFD 0%, #FFFFFF 100%);
		width: 100%;
		position: relative;
		.list-form-pv {
			padding: 10px;
			margin: 20px 0;
			background: none;
			display: flex;
			gap: 10px;
			width: 100%;
			align-items: center;
			flex-wrap: wrap;
			height: auto;
			.list-item {
				margin: 0;
				/deep/.el-form-item__content {
					display: flex;
				}
				.lable {
					padding: 0 10px;
					color: #9E9E9E;
					flex: none;
					display: inline;
					width: auto;
					line-height: 42px;
				}
				.el-input {
					width: 100%;
				}
				.datetimerange {
					border: 1px solid #423DD2;
					border-radius: 8px;
					padding: 3px 10px;
					outline: none;
					background: #fff;
					width: auto;
					justify-content: center;
				}
				.el-input /deep/ .el-input__inner {
					border: 1px solid #423DD2;
					border-radius: 8px;
					padding: 0 10px;
					margin: 0;
					outline: none;
					color: #333;
					width: 140px;
					font-size: 14px;
					line-height: 42px;
					height: 42px;
				}
				.el-select {
					width: 100%;
				}
				.el-select /deep/ .el-input__inner {
				}
				.el-date-editor {
					width: 100%;
				}
				.el-date-editor /deep/ .el-input__inner {
					border: 1px solid #423DD2;
					border-radius: 8px;
					padding: 0 30px;
					margin: 0;
					outline: none;
					color: #333;
					width: 140px;
					font-size: 14px;
					line-height: 42px;
					height: 42px;
				}
			}
			.list-search-btn {
				cursor: pointer;
				border: 0;
				border-radius: 21px;
				padding: 0px 15px;
				margin: 0 10px 0 0;
				outline: none;
				color: #fff;
				background: #423DD2;
				width: auto;
				font-size: 14px;
				line-height: 42px;
				height: 42px;
				i {
					margin: 0 10px 0 0;
					color: #fff;
					font-size: 14px;
				}
			}
			.list-add-btn {
				cursor: pointer;
				border: 0;
				border-radius: 21px;
				padding: 0px 15px;
				margin: 0 10px 0 0;
				outline: none;
				color: #423DD2;
				background: #AED4FA;
				width: auto;
				font-size: 14px;
				line-height: 42px;
				height: 42px;
				i {
					margin: 0 10px 0 0;
					color: #423DD2;
					font-size: 14px;
				}
			}
		}
		.select2 {
			border: 1px solid #423DD2;
			border-radius: 30px;
			padding: 0 30px;
			background: none;
			width: 100%;
			height: auto;
			.select2-list {
				border-radius: 10px;
				padding: 0 0 0 80px;
				margin: 10px 0;
				background: #DFE6FF;
				width: 100%;
				position: relative;
				height: auto;
				.label {
					padding: 0 10px;
					color: #000000;
					top: 0;
					left: 0;
					display: inline-block;
					font-size: 14px;
					line-height: 42px;
					position: absolute;
				}
				.item-body {
					display: flex;
					gap: 10px;
					width: 100%;
					flex-wrap: wrap;
					height: auto;
					.item {
						border-radius: 4px;
						padding: 5px 10px;
						color: #000;
						background: none;
						font-size: 14px;
						line-height: 32px;
					}
					.item:hover {
						color: #fff;
						background: #423DD2;
					}
					.item.active {
						color: #fff;
						background: #423DD2;
					}
				}
			}
		}
		.sort_view {
			margin: 20px 0 0 0;
			.click-sort-btn {
				border: 0;
				border-radius: 8px;
				padding: 0 15px;
				margin: 0 5px;
				.icon {
					margin: 0 2px 0 0;
					color: #333;
					font-size: 14px;
					line-height: 40px;
				}
				.text {
					color: #333;
					font-size: 14px;
					line-height: 40px;
				}
			}
		}
		.list {
			border: 1px solid #423DD2;
			border-radius: 10px;
			margin: 30px 0 0;
			background: #fff;
			.index-pv1 .animation-box {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				z-index: initial;
			}
				
			.index-pv1 .animation-box:hover {
				transform: rotate(0) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
			}
				
			.index-pv1 .animation-box img {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			}
			
			.index-pv1 .animation-box img:hover {
				transform: rotate(0) scale(0.8) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
			.list2 {
				padding: 0 10px;
				background: none;
				display: flex;
				width: 100%;
				justify-content: space-between;
				flex-wrap: wrap;
				height: auto;
				.list-item {
					border-radius: 10px;
					padding: 10px;
					margin: 10px;
					background: linear-gradient( 135deg, #F3F3F3 0%, #EDF1FF 100%);
					display: flex;
					width: calc(50% - 20px);
					font-size: 0;
					position: relative;
					height: auto;
					.img {
						border: 1px solid #6DB6FF;
						border-radius: 10px;
						padding: 10px;
						overflow: hidden;
						width: 290px;
						height: 290px;
						.image {
							object-fit: cover;
							display: block;
							width: 100%;
							transition: all 0.4s;
							height: 100%;
						}
					}
					.item-info {
						padding: 10px;
						z-index: 9;
						overflow: hidden;
						flex: 1;
						display: inline-block;
						height: 290px;
						.name {
							padding: 0 10px;
							color: #423DD2;
							font-weight: 600;
							font-size: 16px;
							line-height: 1.5;
						}
						.price {
							padding: 0 10px;
							color: #D10000;
							font-weight: 600;
							font-size: 18px;
							line-height: 32px;
						}
						.time_item {
							padding: 0 10px;
							.icon {
								margin: 0 2px 0 0;
								color: #FF9C24;
								font-size: 12px;
								line-height: 1.5;
							}
							.label {
								color: #FF9C24;
								font-size: 12px;
								line-height: 1.5;
							}
							.text {
								color: #FF9C24;
								font-size: 12px;
								line-height: 1.5;
							}
						}
						.publisher_item {
							padding: 0 10px;
							margin: 8px 0 0;
							.icon {
								margin: 0 2px 0 0;
								color: #C88BFB;
								font-size: 12px;
								line-height: 1.5;
							}
							.label {
								color: #C88BFB;
								font-size: 12px;
								line-height: 1.5;
							}
							.text {
								color: #C88BFB;
								font-size: 12px;
								line-height: 1.5;
							}
						}
						.like_item {
							padding: 0 10px;
							margin: 8px 0 0;
							.icon {
								margin: 0 2px 0 0;
								color: #3CB9D2;
								font-size: 12px;
								line-height: 1.5;
							}
							.label {
								color: #3CB9D2;
								font-size: 12px;
								line-height: 1.5;
							}
							.text {
								color: #3CB9D2;
								font-size: 12px;
								line-height: 1.5;
							}
						}
						.collect_item {
							padding: 0 10px;
							margin: 8px 0 0;
							.icon {
								margin: 0 2px 0 0;
								color: #13115B;
								font-size: 12px;
								line-height: 1.5;
							}
							.label {
								color: #13115B;
								font-size: 12px;
								line-height: 1.5;
							}
							.text {
								color: #13115B;
								font-size: 12px;
								line-height: 1.5;
							}
						}
						.view_item {
							padding: 0 10px;
							margin: 8px 0 0;
							.icon {
								margin: 0 2px 0 0;
								color: #423CD2;
								font-size: 12px;
								line-height: 1.5;
							}
							.label {
								color: #423CD2;
								font-size: 12px;
								line-height: 1.5;
							}
							.text {
								color: #423CD2;
								font-size: 12px;
								line-height: 1.5;
							}
						}
						.more_btn {
							border-radius: 20px;
							margin: 10px 0 0;
							color: #fff;
							background: linear-gradient(155deg,#5bbaf0 10%,#423DD2);
							display: block;
							width: 108px;
							font-size: 14px;
							line-height: 32px;
							transition: all .4s ease;
							text-align: center;
							height: 32px;
						}
					}
				}
				.list-item::before {
					border: 5px solid #2e89ff;
					transform: scale3d(0, 1, 1);
					top: 0;
					left: 0;
					width: 100%;
					border-width: 5px 0;
					position: absolute;
					transform-origin: left;
					box-sizing: inherit;
					content: "";
					height: 100%;
				}
				.list-item::after {
					border: 5px solid #2e89ff;
					transform: scale3d(1, 0, 1);
					top: 0;
					left: 0;
					width: 100%;
					border-width: 0 5px;
					position: absolute;
					transform-origin: bottom;
					box-sizing: inherit;
					content: "";
					height: 100%;
				}
				.list-item:hover {
					background: linear-gradient( 135deg, #B1D8FB 0%, #DFE6FF 100%);
					.img {
						.image {
							transform: scale(1.05);
						}
					}
					.item-info {
						.name {
							color: #2e89ff;
						}
						.more_btn {
							background: linear-gradient(-155deg,#5bbaf0 10%,#2e89ff);
						}
					}
				}
				.list-item:hover::before {
					transform: scale3d(1, 1, 1);
					transition: transform 0.4s;
				}
				.list-item:hover::after {
					transform: scale3d(1, 1, 1);
					transition: transform 0.4s;
				}
			}
		}
	}
</style>
