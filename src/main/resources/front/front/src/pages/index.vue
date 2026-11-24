<template>
	<div class="main-containers">
		<div class="body-containers">
			<div class="top-container">
				<!-- info -->
				<div class="top_title">
					<span @click="goMenu('/index/home')">明星粉丝应援系统</span>
				</div>
			

				<el-dropdown class="dropdown-box" @command="handleCommand" trigger="click">
					<div class="el-dropdown-link" v-if="Token">
						<img class="top_avatar2" v-if="headportrait&&Token" :src="headportrait?baseUrl + headportrait:require('@/assets/avator.png')">
						<span class="top_label2">欢迎</span>
						<span class="top_nickname2">{{username}}</span>
						<span class="icon iconfont icon-xiala"></span>
					</div>
					<div class="el-dropdown-link" v-if="!Token">
						<div class="login-item" @click="toLogin">
							<span class="icon iconfont icon-wuliu8"></span>
							登录
						</div>
					</div>
					<el-dropdown-menu class="top-el-dropdown-menu" slot="dropdown" v-if="Token">
						<el-dropdown-item v-if="notAdmin" class="user-item" :command="'user'">
							<span class="icon iconfont icon-wuliu8"></span>
							个人中心
						</el-dropdown-item>
						<el-dropdown-item class="register-item" :command="'register'">
							<span class="icon iconfont icon-wuliu8"></span>
							退出
						</el-dropdown-item>
					</el-dropdown-menu>
				</el-dropdown>
			</div>


			<div class="menu-preview">
				<div class="menu-list">
					<div class="menu-home" :class="activeMenu=='/index/home'?'menu-active':''" @click="goMenu('/index/home')">
						<div class="title">
							<span class="icon iconfont icon-shouye-zhihui"></span>
							<div class="text">首页</div>
						</div>
					</div>
					<div class="menu-item" v-for="(item,index) in menuList" :key="index" @mouseenter="menuShowClick4(index)" @mouseleave="menuShowClick4(-1)" :class="activeMenu==item.url?'menu-active':''" @click.stop="goMenu(item.url)">
						<div class="title">
							<span :class="iconArr[index]"></span>
							<div class="text">{{item.name}}</div>
						</div>
						<transition name="el-zoom-in-top">
							<div v-if="showType4==index&&item.hasCate" class="menu-child-list">
								<div class="child-item" v-for="(items,indexs) in item.cateList" :key="indexs" @click.stop="cateClick(item.url,items)">{{items}}</div>
							</div>
						</transition>
					</div>
					<div class="menu-user" :class="activeMenu=='/index/center'?'menu-active':''" @click="goMenu('/index/center')" v-if="Token && notAdmin">
						<div class="title">
							<span class="icon iconfont icon-shouye-zhihui"></span>
							<div class="text">个人中心</div>
						</div>
					</div>
				</div>
			</div>

			<div class="banner-preview" v-if="carouselChange()">
				<el-carousel trigger="click" indicator-position="inside" arrow="always" type="default" direction="horizontal" height="626px" :autoplay="true" :interval="5000" :loop="false">
					<el-carousel-item v-for="item in carouselList" :key="item.id">
						<el-image v-if="preHttp(item.value)" @click="carouselClick(item.url)" :src="item.value" fit="cover"></el-image>
						<el-image v-else @click="carouselClick(item.url)" :src="baseUrl + item.value" fit="cover"></el-image>
						<div class="banner-hidden">
							<div class="banner-background-01"></div>
							<div class="banner-background-02"></div>
						</div>
					</el-carousel-item>
				</el-carousel>
			</div>
			<router-view id="scrollView"></router-view>
			
			<div class="bottom-preview">
				<div class="footer"><div v-html="bottomContent"></div></div>
			</div>
		</div>
		
	</div>
</template>

<script>
	import Vue from 'vue'
	import Swiper from "swiper";
	import axios from 'axios'
export default {
	data() {
		return {
			activeIndex: '0',
			baseUrl: '',
			carouselList: [],
			carouselForm: {
				inHome: true,
				inOther: false,
			},
			menuList: [],
			headportrait: localStorage.getItem('frontHeadportrait')?localStorage.getItem('frontHeadportrait'):'',
			Token: localStorage.getItem('frontToken'),
			username: localStorage.getItem('username'),
			notAdmin: localStorage.getItem('frontSessionTable')!='"users"',
			iconArr: [
				'el-icon-star-off',
				'el-icon-goods',
				'el-icon-warning',
				'el-icon-question',
				'el-icon-info',
				'el-icon-help',
				'el-icon-picture-outline-round',
				'el-icon-camera-solid',
				'el-icon-video-camera-solid',
				'el-icon-video-camera',
				'el-icon-bell',
				'el-icon-s-cooperation',
				'el-icon-s-order',
				'el-icon-s-platform',
				'el-icon-s-operation',
				'el-icon-s-promotion',
				'el-icon-s-release',
				'el-icon-s-ticket',
				'el-icon-s-management',
				'el-icon-s-open',
				'el-icon-s-shop',
				'el-icon-s-marketing',
				'el-icon-s-flag',
				'el-icon-s-comment',
				'el-icon-s-finance',
				'el-icon-s-claim',
				'el-icon-s-opportunity',
				'el-icon-s-data',
				'el-icon-s-check'
			],
			bottomContent: '',
			showType4: -1,
		}
	},
	async created() {
		this.baseUrl = this.$config.baseUrl;
		this.menuList = this.$config.indexNav;
		this.getCarousel();
		if(localStorage.getItem('frontToken') && localStorage.getItem('frontToken')!=null) {
			this.getSession()
		}
		this.cateList = this.$config.cateList
		if(this.cateList.length){
			for(let x in this.menuList){
				for(let i in this.cateList){
					if(this.menuList[x].name==this.cateList[i].name){
						await this.$http.get(`option/${this.cateList[i].refTable}/${this.cateList[i].refColumn}`).then(rs=>{
							this.menuList[x].cateList = rs.data.data
							this.menuList[x].hasCate = true
						})
					}
				}
			}
		}
	},
	mounted() {
		this.activeIndex = localStorage.getItem('keyPath') || '0';



	},
	computed: {
		activeMenu() {
			const route = this.$route
			const {
				meta,
				path
			} = route
			// if st path, the sidebar will highlight the path you sete
			if (meta.activeMenu) {
				return meta.activeMenu
			}
			return path
		},
	},
	watch: {
		$route(newValue) {
			let that = this
			let url = window.location.href
			let arr = url.split('#')
			for (let x in this.menuList) {
				if (newValue.path == this.menuList[x].url) {
					this.activeIndex = x
				}
			}
			this.Token = localStorage.getItem('frontToken')
			if(arr[1]!='/index/home'){
				var element = document.getElementById('scrollView');
				var distance = element.offsetTop;
				window.scrollTo( 0, distance )
			}else{
				window.scrollTo( 0, 0 )
			}
		},
		headportrait(){
			this.$forceUpdate()
		},
	},
	methods: {
		cateClick(url,fenlei){
			this.$router.push(url + '?homeFenlei=' + fenlei);
		},
		preHttp(str) {
			return str && str.substr(0,4)=='http';
		},

		async getSession() {
			await this.$http.get(`${localStorage.getItem('UserTableName')}/session`, {emulateJSON: true}).then(async res => {
				if (res.data.code == 0) {
					if(localStorage.getItem('UserTableName')== 'jingjiren') {
						localStorage.setItem('username', res.data.data.jingjiren);
					}
					if(localStorage.getItem('UserTableName')== 'fensi') {
						localStorage.setItem('username', res.data.data.fensizhanghao);
					}
					localStorage.setItem('sessionForm',JSON.stringify(res.data.data))
					localStorage.setItem('frontUserid', res.data.data.id);
					if(res.data.data.vip) {
						localStorage.setItem('vip', res.data.data.vip);
					}
					if(res.data.data.touxiang) {
						this.headportrait = res.data.data.touxiang
						localStorage.setItem('frontHeadportrait', res.data.data.touxiang);
					} else if(res.data.data.headportrait) {
						this.headportrait = res.data.data.headportrait
						localStorage.setItem('frontHeadportrait', res.data.data.headportrait);
					}
				}
			});
		},
		handleSelect(keyPath) {
			if (keyPath) {
				localStorage.setItem('keyPath', keyPath)
			}
		},
		toLogin() {
		  this.$router.push('/login');
		},
		logout() {
			localStorage.clear();
			Vue.http.headers.common['Token'] = "";
			this.$router.push('/index/home');
			this.activeIndex = '0'
			localStorage.setItem('keyPath', this.activeIndex)
			this.Token = ''
			this.$forceUpdate()
			this.$message({
				message: '登出成功',
				type: 'success',
				duration: 1000,
			});
		},
		getCarousel() {
			this.$http.get('config/list', {params: { page: 1, limit: 3 }}).then(res => {
				if (res.data.code == 0) {
					this.carouselList = res.data.data.list;
				}
			});
		},
		// 轮播图跳转
		carouselClick(url) {
			if (url) {
				if (url.indexOf('https') != -1) {
					window.open(url)
				} else {
					this.$router.push(url)
				}
			}
		},
		carouselChange(){
			let url = window.location.href
			let arr = url.split('#')
			return (this.carouselForm.inHome&&arr[1]=='/index/home')||(this.carouselForm.inOther&&arr[1]!='/index/home')
		},
		goBackend() {
			localStorage.setItem('Token', localStorage.getItem('frontToken'));
			localStorage.setItem('role', localStorage.getItem('frontRole'));
			localStorage.setItem('sessionTable', localStorage.getItem('frontSessionTable'));
			localStorage.setItem('headportrait', localStorage.getItem('frontHeadportrait'));
			localStorage.setItem('userid', Number(localStorage.getItem('frontUserid')));
			window.location.href = `${this.$config.baseUrl}admin/dist/index.html`
			
		},
		menuShowClick4(index){
			this.showType4 = index
		},
		goMenu(path) {
			this.$router.push(path);
		},
		handleCommand(name){
			if(name == 'register') {
				this.logout()
			}
			else if (name == 'user'){
				this.goMenu('/index/center')
			}
			else if (name == 'login'){
				this.toLogin()
			}
		},
	}
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.top-el-dropdown-menu {
		border: 1px solid #EBEEF5;
		border-radius: 4px;
		padding: 10px 0;
		box-shadow: 0 2px 12px 0 rgba(0,0,0,.1);
		margin: 0;
		background: #FFF;
		.user-item {
			border: 0;
			padding: 0 8px;
			margin: 0;
			color: #333;
			background: none;
			display: none;
			width: auto;
			font-size: 14px;
			line-height: 32px;
			height: 32px;
			.icon {
				color: inherit;
				font-size: 14px;
			}
		}
		.user-item:hover {
			color: #fff;
			background: #B1D8FB;
		}
		.register-item {
			border: 0;
			padding: 0 8px;
			margin: 0;
			color: #333;
			background: none;
			width: auto;
			font-size: 14px;
			line-height: 32px;
			height: 32px;
			.icon {
				color: inherit;
				font-size: 14px;
			}
		}
		.register-item:hover {
			color: #fff;
			background: #B1D8FB;
		}
	}
	.main-containers {
		.body-containers {
			padding: 0;
			margin: 0;
			background: linear-gradient( 90deg, #EFEFFD 0%, #FFFFFF 100%);
			min-height: 100vh;
			position: relative;
			.top-container {
				padding: 0 calc(50% - 650px);
				margin: 0 auto;
				color: #000000;
				background: #B1D8FB;
				display: flex;
				gap: 20px;
				width: 100%;
				font-size: 16px;
				justify-content: flex-start;
				align-items: center;
				position: relative;
				height: 50px;
				.top_title {
					top: 75px;
					background: none;
					display: block;
					width: 370px;
					position: absolute;
					span {
						padding: 0;
						color: #212236;
						font-weight: 700;
						font-size: 17px;
						line-height: 25px;
						float: left;
					}
				}
				.dropdown-box {
					color: #666;
					flex: 1 1 auto;
					display: flex;
					font-size: 14px;
					justify-content: flex-end;
					order: 4;
					.el-dropdown-link {
						display: flex;
						align-items: center;
						.top_avatar2 {
							border-radius: 100%;
							margin: 0 10px;
							object-fit: cover;
							display: inline-block;
							width: 32px;
							height: 32px;
						}
						.top_label2 {
							color: #000;
							font-size: 16px;
							line-height: 32px;
						}
						.top_nickname2 {
							color: #000;
							font-size: 16px;
							line-height: 32px;
						}
						.icon {
							margin: 0 0 0 5px;
							color: #666;
							font-size: 14px;
						}
						.login-item {
							border: 0;
							cursor: pointer;
							padding: 0 8px;
							margin: 0 10px;
							color: #fff;
							background: linear-gradient( 135deg, #D492FF 0%, #423CD2 100%);
							width: auto;
							font-size: 14px;
							line-height: 32px;
							height: 32px;
							.icon {
								color: inherit;
								font-size: 14px;
							}
						}
						.login-item:hover {
							color: #fff;
						}
					}
				}
			}
			.menu-preview {
				.el-scrollbar {
					height: 100%;
			  
					& /deep/ .scrollbar-wrapper-vertical {
						overflow-x: hidden;
					}
			  
					& /deep/ .scrollbar-wrapper-horizontal {
						overflow-y: hidden;
			  
						.el-scrollbar__view {
							white-space: nowrap;
						}
					}
				}
				padding: 15px calc(50% - 650px)  15px calc(50% - 280px) ;
				background: linear-gradient( 137deg, #B1D8FB 0%, #423CD2 100%);
				display: flex;
				width: 100%;
				.menu-list {
					border-radius: 30px;
					padding: 0;
					background: #FFFFFF;
					display: flex;
					width: 100%;
					justify-content: center;
					position: relative;
					flex-wrap: wrap;
					// 首页
					.menu-home {
						cursor: pointer;
						color: #888;
						line-height: 50px;
						height: 50px;
						.title {
							padding: 0 5px;
							background: none;
							display: flex;
							height: 50px;
							.icon {
								padding: 0 10px;
								margin: 0;
								color: inherit;
								width: 14px;
								font-size: 14px;
								line-height: 50px;
								height: 50px;
							}
							.text {
								padding: 0 10px;
								color: inherit;
								font-size: 14px;
								line-height: 50px;
								height: 50px;
							}
						}
					}
					.menu-home:hover {
						.title {
							color: #423CD2;
							background: none;
							font-weight: 700;
						}
					}
					.menu-home.menu-active {
						.title {
							color: #423CD2;
							background: none;
							font-weight: 700;
						}
					}
					// 其他盒子
					.menu-item {
						color: #888;
						line-height: 50px;
						position: relative;
						height: 50px;
						.title {
							cursor: pointer;
							padding: 0 5px;
							display: flex;
							position: relative;
							span {
								padding: 0 10px;
								margin: 0;
								color: inherit;
								width: 14px;
								font-size: 14px;
								line-height: 50px;
								height: 50px;
							}
							.text {
								padding: 0 10px;
								color: inherit;
								font-size: 14px;
								line-height: 50px;
								height: 50px;
							}
						}
						.menu-child-list {
							padding: 0 8px 10px;
							z-index: 11;
							display: flex;
							line-height: auto;
							flex-direction: column;
							top: 50px;
							left: 0;
							background: #FFF;
							width: auto;
							justify-content: center;
							position: absolute;
							min-width: 200px;
							height: auto;
							.child-item {
								cursor: pointer;
								border-radius: 4px;
								padding: 0 10px;
								color: #000000;
								font-size: 14px;
								text-align: center;
							}
							.child-item:hover {
								color: #FFF;
								background: linear-gradient( 315deg, #423CD2 0%, #B1D8FB 100%);
							}
						}
					}
					.menu-item:hover {
						.title {
							color: #423CD2;
							background: none;
							font-weight: 700;
						}
					}
					.menu-item.menu-active {
						.title {
							color: #423CD2;
							background: none;
							font-weight: 700;
						}
					}
					// 个人中心
					.menu-user {
						color: #888;
						line-height: 50px;
						position: relative;
						height: 50px;
						.title {
							cursor: pointer;
							padding: 0 5px;
							display: flex;
							position: relative;
							.icon {
								padding: 0 10px;
								margin: 0;
								color: inherit;
								width: 14px;
								font-size: 14px;
								line-height: 50px;
								height: 50px;
							}
							.text {
								padding: 0 10px;
								color: inherit;
								font-size: 14px;
								line-height: 50px;
								height: 50px;
							}
						}
					}
					.menu-user:hover {
						.title {
							color: #423CD2;
							background: none;
							font-weight: 700;
						}
					}
					.menu-user.menu-active {
						.title {
							color: #423CD2;
							background: none;
							font-weight: 700;
						}
					}
				}
			}
			.banner-preview {
				width: 100%;
				height: auto;
				.el-carousel {
					margin: 0 auto;
					width: 100%;
					/deep/ .el-carousel__item {
						border-radius: 10px;
						width: 100%;
						height: 100%;
						.banner-background-01 {
							z-index: 999;
							left: -236px;
							background: url(http://codegen.caihongy.cn/20240710/0864cdc5030e4dae94e4bdd224d7045e.png) repeat-x;
							bottom: 0;
							width: 400%;
							position: absolute;
							height: 85px;
							animation: wave1 25s linear .4s infinite normal;
						}
						.banner-background-02 {
							z-index: 998;
							left: 0;
							background: url(http://codegen.caihongy.cn/20240710/647957462b4047c484875a83e2a7f786.png) repeat-x;
							bottom: 0;
							width: 400%;
							position: absolute;
							height: 100px;
							animation: wave2 60s linear .4s infinite normal;
						}
						@keyframes wave1 {from { left: -236px } to { left: -1233px }}
						@keyframes wave2 {from { left: 0 } to { left: -1009px }}
						.el-image {
							object-fit: cover;
							width: 100%;
							height: 100%;
						}
					}
					/deep/ .el-carousel__container .el-carousel__arrow--left {
						width: 36px;
						font-size: 12px;
						height: 36px;
					}
					/deep/ .el-carousel__container .el-carousel__arrow--left:hover {
						background: red;
					}
					/deep/ .el-carousel__container .el-carousel__arrow--right {
						width: 36px;
						font-size: 12px;
						height: 36px;
					}
					/deep/ .el-carousel__container .el-carousel__arrow--right:hover {
						background: red;
					}
					/deep/ .el-carousel__indicators {
						padding: 0;
						margin: 0;
						z-index: 2;
						position: absolute;
						list-style: none;
						li {
							padding: 0;
							margin: 0 4px;
							background: #fff;
							display: inline-block;
							width: 12px;
							opacity: 0.4;
							transition: 0.3s;
							height: 12px;
						}
						li:hover {
							padding: 0;
							margin: 0 4px;
							background: #fff;
							display: inline-block;
							width: 24px;
							opacity: 0.7;
							height: 12px;
						}
						li.is-active {
							padding: 0;
							margin: 0 4px;
							background: #fff;
							display: inline-block;
							width: 24px;
							opacity: 1;
							height: 12px;
						}
					}
					/deep/ .el-carousel__indicator button {
						width: 0;
						height: 0;
						display: none;
					}
				}
			}
			.bottom-preview {
				width: 100%;
				height: auto;
				.footer {
					padding: 20px;
					overflow: hidden;
					background: linear-gradient( 137deg, #B1D8FB 0%, #423CD2 100%);
					width: 100%;
					text-align: center;
					height: 270px;
				}
			}
		}
	}
</style>
