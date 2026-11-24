<template>
	<div class="center-preview">
		<div class="center-title">{{ title }}</div>
		<div class="center-info">
			<div class="center-info-title">个人信息</div>
			<div class="img-box" v-if="userTableName=='jingjiren'">
				<img :src="sessionForm.touxiang?baseUrl + sessionForm.touxiang:require('@/assets/avator.png')">
			</div>
			<div class="img-box" v-if="userTableName=='fensi'">
				<img :src="sessionForm.touxiang?baseUrl + sessionForm.touxiang:require('@/assets/avator.png')">
			</div>
			<div class="info-item1" v-if="userTableName=='fensi'">
				<span class="icon iconfont icon-shouye-zhihui"></span>
				<div class="label">粉丝账号：</div>
				<div class="text">{{sessionForm.fensizhanghao}}</div>
			</div>
			<div class="info-item2" v-if="userTableName=='fensi'">
				<span class="icon iconfont icon-shouye-zhihui"></span>
				<div class="label">粉丝姓名：</div>
				<div class="text">{{sessionForm.fensixingming}}</div>
			</div>
			<div class="info-item3" v-if="userTableName=='fensi'">
				<span class="icon iconfont icon-shouye-zhihui"></span>
				<div class="label">性别：</div>
				<div class="text">{{sessionForm.xingbie}}</div>
			</div>
			<div class="info-item4" v-if="userTableName=='fensi'">
				<span class="icon iconfont icon-shouye-zhihui"></span>
				<div class="label">联系电话：</div>
				<div class="text">{{sessionForm.lianxidianhua}}</div>
			</div>
			<div class="info-item5" v-if="userTableName=='fensi'">
				<span class="icon iconfont icon-shouye-zhihui"></span>
				<div class="label">喜爱明星：</div>
				<div class="text">{{sessionForm.xiaimingxing}}</div>
			</div>
			<div class="info-item6" v-if="userTableName=='fensi'">
				<span class="icon iconfont icon-shouye-zhihui"></span>
				<div class="label">余额：</div>
				<div class="text">{{sessionForm.money}}</div>
			</div>
		
		</div>
	
		<el-tabs class="center-tabs" tab-position="left" @tab-click="handleClick">
			<el-tab-pane label="个人中心">
				<el-form class="center-preview-pv" ref="sessionForm" :model="sessionForm" :rules="rules" label-width="120px">
					<el-form-item class="center-item" v-if="userTableName=='fensi'" label="粉丝账号" prop="fensizhanghao">
						<el-input v-model="sessionForm.fensizhanghao" placeholder="粉丝账号" readonly></el-input>
					</el-form-item>
					<el-form-item class="center-item" v-if="userTableName=='fensi'" label="粉丝姓名" prop="fensixingming">
						<el-input v-model="sessionForm.fensixingming" placeholder="粉丝姓名" ></el-input>
					</el-form-item>
					<el-form-item class="center-item" v-if="userTableName=='fensi'" label="性别" prop="xingbie">
						<el-select v-model="sessionForm.xingbie" placeholder="请选择性别" >
							<el-option v-for="(item, index) in dynamicProp.xingbie" :key="index" :label="item" :value="item"></el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="center-item" v-if="userTableName=='fensi'" label="联系电话" prop="lianxidianhua">
						<el-input v-model="sessionForm.lianxidianhua" placeholder="联系电话" ></el-input>
					</el-form-item>
					<el-form-item class="center-item" v-if="userTableName=='fensi'" label="头像" prop="touxiang">
						<file-upload
							tip="点击上传头像"
							action="file/upload"
							:limit="1"
							:multiple="true"
							:fileUrls="sessionForm.touxiang?sessionForm.touxiang:''"
							@change="fensitouxiangHandleAvatarSuccess"
							></file-upload>
					</el-form-item>
					<el-form-item class="center-item" v-if="userTableName=='fensi'" label="喜爱明星" prop="xiaimingxing">
						<el-input v-model="sessionForm.xiaimingxing" placeholder="喜爱明星" ></el-input>
					</el-form-item>
					<el-form-item class="center-item" v-if="userTableName=='fensi'" label="余额">
						<div class="balance-item">
							<el-input v-model="sessionForm.money" placeholder="余额" readonly></el-input>
							<div class="balanceBtn" @click="dialogFormVisibleMoney = true">
								<span class="icon iconfont icon-tijiaoyanzi"></span>
								<span class="text">点我充值</span>
							</div>
						</div>
					</el-form-item>
					<el-form-item class="center-btn-item">
						<div class="updateBtn" type="primary" @click="onSubmit('sessionForm')">
							<span class="icon iconfont icon-kaitongfuwu"></span>
							<span class="text">更新信息</span>
						</div>
						<div class="closeBtn" type="danger" @click="logout">
							<span class="icon iconfont icon-shanchu1"></span>
							<span class="text">退出登录</span>
						</div>
					</el-form-item>
				</el-form>
			</el-tab-pane>
			<el-tab-pane label="修改密码">
				<el-form class="center-preview-pv" ref="passwordForm" :model="passwordForm" :rules="passwordRules" label-width="120px">
					<el-form-item class="center-item" label="原密码" prop="password">
						<el-input type="password" v-model="passwordForm.password" placeholder="原密码"></el-input>
					</el-form-item>
					<el-form-item class="center-item" label="新密码" prop="newpassword">
						<el-input type="password" v-model="passwordForm.newpassword" placeholder="新密码"></el-input>
					</el-form-item>
					<el-form-item class="center-item" label="确认密码" prop="repassword">
						<el-input type="password" v-model="passwordForm.repassword" placeholder="确认密码"></el-input>
					</el-form-item>
					<el-form-item class="center-btn-item">
						<div class="updateBtn" type="primary" @click="updatePassword">
							<span class="icon iconfont icon-kaitongfuwu"></span>
							<span class="text">修改密码</span>
						</div>
					</el-form-item>
				</el-form>
			</el-tab-pane>
			<el-tab-pane v-for="(item,index) in menuList" :key="index" v-if="hasBack(item.menu)" :label="item.child[0].menu" :name="item.child[0].tableName"></el-tab-pane>
			<el-tab-pane label="我的订单"></el-tab-pane>
			<el-tab-pane label="我的收藏"></el-tab-pane>
			<el-tab-pane label="我的关注"></el-tab-pane>
			<el-tab-pane label="我的优惠券">
				<div class="coupon_list">
					<div class="coupon" v-for="(item,index) in couponList" :key="index" :class="(item.status=='已过期'||item.status=='已使用')?'coupons':''">
						<div class="coupon_left">
							<div class="name">{{item.name}}</div>
							<div class="name">满{{item.fullamount}}减{{item.discountamount}}</div>
							<div class="date">{{item.startime}}-{{item.endtime}}</div>
							<div class="date">{{item.couponnumber}}</div>
							<div class="date">{{item.remark}}</div>
						</div>
						<div class="coupon_right">
							<div class="price">￥<span class="num">{{item.discountamount}}</span></div>
							<div class="btn" v-if="item.status=='可使用'">{{item.status}}</div>
						</div>
						<img v-if="item.status=='已过期'" class="no-coupon" src="@/assets/no-coupon.png" alt="">
						<img v-if="item.status=='已使用'" class="no-coupon" src="@/assets/finish-coupon.png" alt="">
					</div>
					<div class="noList" v-if="!couponList.length">
						暂无优惠券
					</div>
				</div>
			</el-tab-pane>
		</el-tabs>

		<el-dialog title="用户充值" :visible.sync="dialogFormVisibleMoney" width="726px" center>
			<el-form :model="chongzhiForm">
				<el-form-item label="充值金额" label-width="120px">
					<el-input type="number" v-model="chongzhiForm.money" autocomplete="off" placeholder="充值金额"></el-input>
				</el-form-item>
				<el-form-item label-width="120px">
					<el-radio-group v-model="chongzhiForm.radio">
						<el-radio style="margin-bottom: 30px" label="微信支付">
							<el-image
								style="width: 60px; height: 60px;vertical-align: middle;"
								:src="require('@/assets/weixin.png')"
								fit="fill"></el-image>
							<span style="display: inline-block;margin-left: 10px">微信支付</span>
						</el-radio>
						<el-radio label="支付宝支付">
							<el-image
								style="width: 60px; height: 60px;vertical-align: middle;"
								:src="require('@/assets/zhifubao.png')"
								fit="fill"></el-image>
							<span style="display: inline-block;margin-left: 10px">支付宝支付</span>
						</el-radio>
						<el-radio label="中国建设银行支付">
							<el-image
								style="width: 120px; height: 60px;vertical-align: middle;"
								:src="require('@/assets/jianshe.png')"
								fit="fill"></el-image>
						</el-radio>
						<el-radio label="中国农业银行支付">
							<el-image
								style="width: 126px; height: 60px;vertical-align: middle;"
								:src="require('@/assets/nongye.png')"
								fit="fill"></el-image>
						</el-radio>
						<el-radio label="中国银行支付">
							<el-image
								style="width: 140px; height: 60px;vertical-align: middle;"
								:src="require('@/assets/zhongguo.png')"
								fit="fill"></el-image>
						</el-radio>
						<el-radio label="交通银行支付">
							<el-image
								style="width: 120px; height: 60px;vertical-align: middle;"
								:src="require('@/assets/jiaotong.png')"
								fit="fill"></el-image>
						</el-radio>
					</el-radio-group>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click="dialogFormVisibleMoney = false">取 消</el-button>
				<el-button type="primary" @click="chongzhi">确认充值</el-button>
			</div>
		</el-dialog>
	</div>
</template>

<script>
	import config from '@/config/config';
	import menu from '@/config/menu';
	import Vue from 'vue';
	export default {
		//数据集合
		data() {
			return {
				title: '个人中心',
				baseUrl: config.baseUrl,
				sessionForm: {},
				passwordForm: {},
				passwordRules: {
					password: [
						{
							required: true,
							message: "密码不能为空",
							trigger: "blur"
						}
					],
					newpassword: [
						{
							required: true,
							message: "新密码不能为空",
							trigger: "blur"
						}
					],
					repassword: [
						{
							required: true,
							message: "确认密码不能为空",
							trigger: "blur"
						}
					]
				},
				rules: {},
				chongzhiForm: {
					money: '',
					radio: ''
				},
				menuList: [],
				disabled: false,
				dialogFormVisibleMoney: false,
				uploadUrl: config.baseUrl + 'file/upload',
				imageUrl: '',
				headers: {Token: localStorage.getItem('frontToken')},
				userTableName: localStorage.getItem('UserTableName'),
				dynamicProp: {},
				couponList: [],
			}
		},
		created() {
			let menus = menu.list()
			for(let x in menus){
				if(menus[x].tableName == this.userTableName){
					for(let i in menus[x].backMenu){
						if(menus[x].backMenu[i].menu=='考试管理'){
							menus[x].backMenu.splice(i,1)
						}
					}
					this.menuList = menus[x].backMenu
				}
			}
			if ('fensi' == this.userTableName) {
				this.$set(this.sessionForm, 'fensizhanghao', null);
			}
			if ('fensi' == this.userTableName) {
				this.$set(this.sessionForm, 'fensixingming', null);
			}
			if ('fensi' == this.userTableName) {
				this.$set(this.sessionForm, 'mima', null);
			}
			if ('fensi' == this.userTableName) {
				this.$set(this.sessionForm, 'xingbie', null);
			}
			if ('fensi' == this.userTableName) {
				this.$set(this.sessionForm, 'lianxidianhua', null);
			}
			if ('fensi' == this.userTableName) {
				this.$set(this.sessionForm, 'touxiang', null);
			}
			if ('fensi' == this.userTableName) {
				this.$set(this.sessionForm, 'xiaimingxing', null);
			}
			if ('fensi' == this.userTableName) {
				this.$set(this.sessionForm, 'money', null);
			}

			if ('fensi' == this.userTableName&&this.rules['fensizhanghao']){
				this.rules['fensizhanghao'].push({ required: true, message: '请输入粉丝账号', trigger: 'blur' })
			}else if('fensi' == this.userTableName&&!this.rules['fensizhanghao']) {
				this.$set(this.rules, 'fensizhanghao', [{ required: true, message: '请输入粉丝账号', trigger: 'blur' }]);
			}
			if ('fensi' == this.userTableName&&this.rules['fensixingming']){
				this.rules['fensixingming'].push({ required: true, message: '请输入粉丝姓名', trigger: 'blur' })
			}else if('fensi' == this.userTableName&&!this.rules['fensixingming']) {
				this.$set(this.rules, 'fensixingming', [{ required: true, message: '请输入粉丝姓名', trigger: 'blur' }]);
			}
			if ('fensi' == this.userTableName&&this.rules['mima']){
				this.rules['mima'].push({ required: true, message: '请输入密码', trigger: 'blur' })
			}else if('fensi' == this.userTableName&&!this.rules['mima']) {
				this.$set(this.rules, 'mima', [{ required: true, message: '请输入密码', trigger: 'blur' }]);
			}
			if ('fensi' == this.userTableName) {
				this.$set(this.rules, 'lianxidianhua', [{ required: false, validator: this.$validate.isMobile, trigger: 'blur' }]);
			}
			if ('fensi' == this.userTableName) {
				this.$set(this.rules, 'money', [{ required: false, validator: this.$validate.isNumber, trigger: 'blur' }]);
			}

			this.init();
			this.sessionForm = JSON.parse(localStorage.getItem('sessionForm'))
		},
		//方法集合
		methods: {
			init() {
				if ('fensi' == this.userTableName) {
					this.dynamicProp.xingbie = '男,女'.split(',');
				}
			},
			setSession(){
				localStorage.setItem('sessionForm',JSON.stringify(this.sessionForm))
			},
			onSubmit(formName) {
				if(`fensi` == this.userTableName && this.sessionForm.touxiang!=null){
					this.sessionForm.touxiang = this.sessionForm.touxiang.replace(new RegExp(this.$config.baseUrl,"g"),"");
				}
				this.$refs[formName].validate((valid) => {
					if (valid) {
						this.$http.post(this.userTableName + '/update', this.sessionForm).then(res => {
							if (res.data.code == 0) {
								this.setSession()
								this.$message({
									message: '更新成功',
									type: 'success',
									duration: 1500
								});
							}
						});
					} else {
						return false;
					}
				});
			},
			fensitouxiangHandleAvatarSuccess(fileUrls) {
				this.sessionForm.touxiang = fileUrls;
			},
			chongzhi() {
				if (this.chongzhiForm.money == '') {
					this.$message({
						message: '请输入充值金额',
						type: 'error',
						duration: 1500
					});
					return;
				}
				if (this.chongzhiForm.money <= 0) {
					this.$message({
						message: '请输入正确的充值金额',
						type: 'error',
						duration: 1500
					});
					return;
				}
				if (this.chongzhiForm.radio == '') {
					this.$message({
						message: '请选择充值方式',
						type: 'error',
						duration: 1500
					});
					return;
				}
				if(!this.sessionForm.money) {
					this.sessionForm.money = parseFloat(this.chongzhiForm.money)
				}else{
					this.sessionForm.money = parseFloat(this.sessionForm.money) + parseFloat(this.chongzhiForm.money);
				}
				
				this.$http.post(this.userTableName + '/update', this.sessionForm).then(res => {
					if (res.data.code == 0) {
						this.$http.post('chargerecord/add',{
							username: localStorage.getItem("username"),
							role: localStorage.getItem("frontRole"),
							amount: parseFloat(this.chongzhiForm.money),
							userid: this.sessionForm.id
						}).then(rs=>{
							this.setSession()
							this.$message({
								message: '充值成功',
								type: 'success',
								duration: 1500,
								onClose: () => {
									this.dialogFormVisibleMoney = false;
								}
							});
						})
					}
				});
			},
			handleClick(tab, event) {
				switch(event.target.outerText) {
					case '个人中心':
						tab.$router.push('/index/center');
						break;
					case '修改密码':
						this.passwordForm = {
							password: '',
							newpassword: '',
							repassword: '',
						}
						this.$forceUpdate()
						break;
					case '我的订单':
						tab.$router.push('/index/shop-order/order');
						break;
					case '我的收藏':
						localStorage.setItem('storeupType', 1);
						tab.$router.push('/index/storeup');
						break;
					case '我的关注':
						localStorage.setItem('storeupType', 41);
						tab.$router.push('/index/storeup');
						break;
					case '我的优惠券':
						this.getMyList()
						break;
					default:
						tab.$router.push(`/index/${tab.name}?centerType=1`);
				}

				this.title = event.target.outerText;
			},
			async updatePassword(){
				this.$refs["passwordForm"].validate(async valid => {
					if (valid) {
						var password = "";
						if (this.sessionForm.mima) {
							password = this.sessionForm.mima;
						} else if (this.sessionForm.password) {
							password = this.sessionForm.password;
						}
						if (this.userTableName == 'fensi') {
						}
						if (this.passwordForm.password != password) {
							this.$message.error("原密码错误");
							return;
						}
						if (this.passwordForm.newpassword != this.passwordForm.repassword) {
							this.$message.error("两次密码输入不一致");
							return;
						}
						if (this.passwordForm.newpassword == this.passwordForm.password) {
							this.$message.error("新密码与原密码相同！");
							return;
						}
						this.sessionForm.password = this.passwordForm.newpassword;
						this.sessionForm.mima = this.passwordForm.newpassword;
						this.$http.post(`${this.userTableName}/update`,this.sessionForm).then(({data})=>{
							if (data && data.code === 0) {
								this.$message({
									message: "修改密码成功,下次登录系统生效",
									type: "success",
									duration: 1500,
									onClose: () => {
									}
								});
								this.setSession()
							} else {
								this.$message.error(data.msg);
							}
						});
					}
				})
			},
			logout() {
				localStorage.clear();
				Vue.http.headers.common['Token'] = "";
				this.$router.push('/index/home');
				this.activeIndex = '0'
				localStorage.setItem('keyPath', this.activeIndex)
				this.$forceUpdate()
				this.$message({
					message: '登出成功',
					type: 'success',
					duration: 1500,
				});
			},
			getMyList() {
				this.$http.get('mycoupon/page').then(res => {
					if (res.data && res.data.code == 0) {
						for (let x in res.data.data.list) {
							if (!this.isBetweenTime(this.getCurDateTime(), [res.data.data.list[x].startime, res
									.data.data.list[x].endtime
								]) && res.data.data.list[x].status != '已使用' && res.data.data.list[x].status !=
								'已过期') {
								res.data.data.list[x].status = '已过期'
								this.$http.post('mycoupon/update', res.data.data.list[x]).then(obj => {})
							} else if (this.isBetweenTime(this.getCurDateTime(), [res.data.data.list[x]
									.startime, res.data.data.list[x].endtime
								]) && res.data.data.list[x].status != '已使用' && res.data.data.list[x].status !=
								'可使用') {
								res.data.data.list[x].status = '可使用'
								this.$http.post('mycoupon/update', res.data.data.list[x]).then(obj => {})
							}
						}
						this.couponList = res.data.data.list
					}
				})
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
			hasBack(name){
				switch(name){
					case '订单管理':
						return false
						break;
					case '我的收藏管理':
						return false
						break;
					case '我的关注管理':
						return false
						break;
					default:
						return true
				}
			}
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.center-preview {
		margin: 10px auto;
		background: none;
		width: 1300px;
		position: relative;
		.center-title {
			padding: 0 0 0 180px;
			margin: 10px auto 30px;
			background: url(http://codegen.caihongy.cn/20241215/eec2df959e904359912fcb41bdda5202.webp) no-repeat center / 100% 100%;
			font-weight: 700;
			width: 100%;
			font-size: 36px;
			line-height: 100px;
			text-align: left;
			height: 100px;
		}
		.center-info {
			border: 1px solid #423DD2;
			border-radius: 10px;
			padding: 20px;
			box-shadow: 0 1px 6px rgba(0, 0, 0, 0.3);
			margin: 0 20px 0 0;
			background: #ffffff;
			display: inline-block;
			vertical-align: top;
			width: 420px;
			height: auto;
			.center-info-title {
				color: #333;
				width: 100%;
				font-size: 15px;
				border-color: #efefef;
				border-width: 0 0 1px 0;
				line-height: 44px;
				border-style: solid;
				height: 44px;
			}
			.img-box {
				width: 100%;
				font-size: 0;
				border-color: #efefef;
				border-width: 0 0 1px 0;
				border-style: solid;
				height: auto;
				img {
					border-radius: 100%;
					margin: 10px auto;
					object-fit: cover;
					display: block;
					width: 80px;
					border-color: #423DD2;
					border-width: 0 0 1px 0;
					border-style: solid;
					height: 80px;
				}
			}
			.info-item1 {
				padding: 0 20px;
				display: flex;
				width: 100%;
				border-color: rgb(239, 239, 239);
				border-width: 0 0 1px 0;
				line-height: 40px;
				border-style: solid;
				height: auto;
				.icon {
					padding: 0 5px;
					color: #333;
					font-size: 14px;
				}
				.label {
					color: #333;
					font-size: 14px;
				}
				.text {
					color: #333;
					flex: 1;
					font-size: 14px;
					text-align: right;
				}
			}
			.info-item2 {
				padding: 0 20px;
				display: flex;
				width: 100%;
				border-color: rgb(239, 239, 239);
				border-width: 0 0 1px 0;
				line-height: 40px;
				border-style: solid;
				height: auto;
				.icon {
					padding: 0 5px;
					color: #333;
					font-size: 14px;
				}
				.label {
					color: #333;
					font-size: 14px;
				}
				.text {
					color: #333;
					flex: 1;
					font-size: 14px;
					text-align: right;
				}
			}
			.info-item3 {
				padding: 0 20px;
				display: flex;
				width: 100%;
				border-color: #efefef;
				border-width: 0 0 1px 0;
				line-height: 40px;
				border-style: solid;
				height: auto;
				.icon {
					padding: 0 5px;
					color: #333;
					font-size: 14px;
				}
				.label {
					color: #333;
					font-size: 14px;
				}
				.text {
					color: #333;
					flex: 1;
					font-size: 14px;
					text-align: right;
				}
			}
			.info-item4 {
				padding: 0 20px;
				display: flex;
				width: 100%;
				border-color: #efefef;
				border-width: 0 0 1px 0;
				line-height: 40px;
				border-style: solid;
				height: auto;
				.icon {
					padding: 0 5px;
					color: #333;
					font-size: 14px;
				}
				.label {
					color: #333;
					font-size: 14px;
				}
				.text {
					color: #333;
					flex: 1;
					font-size: 14px;
					text-align: right;
				}
			}
			.info-item5 {
				padding: 0 20px;
				display: flex;
				width: 100%;
				border-color: #efefef;
				border-width: 0 0 1px 0;
				line-height: 40px;
				border-style: solid;
				height: auto;
				.icon {
					padding: 0 5px;
					color: #333;
					font-size: 14px;
				}
				.label {
					color: #333;
					font-size: 14px;
				}
				.text {
					color: #333;
					flex: 1;
					font-size: 14px;
					text-align: right;
				}
			}
			.info-item6 {
				padding: 0 20px;
				display: flex;
				width: 100%;
				border-color: #efefef;
				border-width: 0 0 1px 0;
				line-height: 40px;
				border-style: solid;
				height: auto;
				.icon {
					padding: 0 5px;
					color: #333;
					font-size: 14px;
				}
				.label {
					color: #333;
					font-size: 14px;
				}
				.text {
					color: #333;
					flex: 1;
					font-size: 14px;
					text-align: right;
				}
			}
		}
		.center-tabs.el-tabs {
			border: 1px solid #423DD2;
			border-radius: 10px;
			padding: 20px;
			background: #FFF;
			display: inline-block;
			width: 850px;
			/deep/ .el-tabs__header {
				border-radius: 10px;
				padding: 10px 4px 0 4px;
				margin: 0;
				background: #DFE6FF;
				width: 180px;
				border-color: #eee;
				border-width: 0 1px 0 0;
				position: relative;
				float: left;
				border-style: solid;
			}
			/deep/ .el-tabs__header .el-tabs__item {
				border-radius: 10px;
				padding: 0 10px;
				margin: 0 0 10px 0;
				color: #000000;
				background: none;
				font-weight: 500;
				font-size: 14px;
				line-height: 40px;
				position: relative;
				text-align: center;
				height: 40px;
			}
			/deep/ .el-tabs__header .el-tabs__item:hover {
				border-radius: 10px;
				padding: 0 10px;
				margin: 0 0 10px 0;
				color: #FFF;
				background: #423DD2;
				font-weight: 500;
				font-size: 14px;
				line-height: 40px;
				position: relative;
				text-align: center;
				height: 40px;
			}
			/deep/ .el-tabs__header .el-tabs__item.is-active {
				border-radius: 10px;
				padding: 0 10px;
				margin: 0 0 10px 0;
				color: #FFF;
				background: #423DD2;
				font-weight: 500;
				font-size: 14px;
				line-height: 40px;
				position: relative;
				text-align: center;
				height: 40px;
			}
			/deep/ .el-tabs__content {
				padding: 10px;
				background: #fff;
			}
			/deep/ .el-tabs__content .el-tab-pane {
				width: 100%;
			}
			& /deep/ .el-tabs__header {
				.el-tabs__nav{
					overflow: auto;
				}
				::-webkit-scrollbar {
					-webkit-appearance: none;
					width: 6px;
					height: 6px;
				}
				::-webkit-scrollbar-track {
					background: rgba(0, 0, 0, 0.1);
					border-radius: 0;
				}
				::-webkit-scrollbar-thumb {
					cursor: pointer;
					border-radius: 5px;
					background: rgba(0, 0, 0, 0.15);
					transition: color 0.2s ease;
				}
				::-webkit-scrollbar-thumb:hover {
					background: rgba(0, 0, 0, 0.3);
				}
				.el-tabs__nav-wrap {
					margin: 0;
					&::after {
						content: none;
					}
				}
				.el-tabs__active-bar {
					display: none !important;
				}
			}
			.center-preview-pv {
				.center-item.el-form-item {
					padding: 10px;
					margin: 0 0 10px;
					background: none;
					/deep/ .el-form-item__label {
						padding: 0 10px 0 0;
						color: #000000;
						font-weight: 500;
						width: 120px;
						font-size: 14px;
						line-height: 40px;
						text-align: right;
					}
					.el-form-item__content {
						margin-left: 120px;
					}
					.el-input {
						width: 100%;
					}
					.el-input /deep/ .el-input__inner {
						border: 1px solid #E2E3E5;
						border-radius: 4px;
						padding: 0 12px;
						box-shadow: none;
						outline: none;
						color: #000;
						width: 100%;
						font-size: 14px;
						height: 40px;
					}
					.el-input /deep/ .el-input__inner[readonly="readonly"] {
						border: 0;
						cursor: not-allowed;
						border-radius: 4px;
						padding: 0 12px;
						box-shadow: none;
						outline: none;
						color: rgba(85, 85, 127, 1.0);
						width: 400px;
						font-size: 14px;
						height: 40px;
					}
					.el-select {
						width: 100%;
					}
					.el-select /deep/ .el-input__inner {
						border: 1px solid #E2E3E5;
						border-radius: 4px;
						padding: 0 10px;
						box-shadow: none;
						outline: none;
						color: #000;
						width: 100%;
						font-size: 14px;
						height: 40px;
					}
					.el-select /deep/ .is-disabled .el-input__inner {
						border: 0;
						cursor: not-allowed;
						border-radius: 4px;
						padding: 0 10px;
						box-shadow: none;
						outline: none;
						color: rgba(85, 85, 127, 1.0);
						background: #eee;
						width: 100%;
						font-size: 14px;
						height: 40px;
					}
					.el-date-editor {
						width: 100%;
					}
					.el-date-editor /deep/ .el-input__inner {
						border: 1px solid #E2E3E5;
						border-radius: 4px;
						padding: 0 10px 0 30px;
						box-shadow: none;
						outline: none;
						color: #000;
						width: 100%;
						font-size: 14px;
						height: 40px;
					}
					.el-date-editor /deep/ .el-input__inner[readonly="readonly"] {
						border: 0;
						cursor: not-allowed;
						border-radius: 4px;
						padding: 0 10px 0 30px;
						box-shadow: none;
						outline: none;
						color: rgba(85, 85, 127, 1.0);
						background: #eee;
						width: 100%;
						font-size: 14px;
						height: 40px;
					}
					/deep/ .el-upload--picture-card {
						background: transparent;
						border: 0;
						border-radius: 0;
						width: auto;
						height: auto;
						line-height: initial;
						vertical-align: middle;
					}
					/deep/ .upload .upload-img {
						border: 1px dashed rgba(64, 158, 255, 1);
						cursor: pointer;
						border-radius: 6px;
						color: #000;
						width: 80px;
						font-size: 32px;
						line-height: 80px;
						text-align: center;
						height: 80px;
					}
					/deep/ .el-upload-list .el-upload-list__item {
						border: 1px dashed rgba(64, 158, 255, 1);
						cursor: pointer;
						border-radius: 6px;
						color: #000;
						width: 80px;
						font-size: 32px;
						line-height: 80px;
						text-align: center;
						height: 80px;
						font-size: 14px;
						line-height: 1.8;
					}
					/deep/ .el-upload .el-icon-plus {
						border: 1px dashed rgba(64, 158, 255, 1);
						cursor: pointer;
						border-radius: 6px;
						color: #000;
						width: 80px;
						font-size: 32px;
						line-height: 80px;
						text-align: center;
						height: 80px;
					}
					/deep/ .el-upload__tip {
						color: #838fa1;
					}
					/deep/ .el-input__inner::placeholder {
						color: #e9e9e9;
						font-size: 14px;
					}
					.balance-item {
						display: flex;
						.el-input {
							width: 100%;
						}
						.el-input /deep/ .el-input__inner {
							border: 0;
							border-radius: 4px;
							padding: 0 12px;
							box-shadow: none;
							outline: none;
							color: #000;
							display: inline-block;
							width: 100%;
							font-size: 14px;
							height: 40px;
						}
						.balanceBtn {
							border: 0;
							cursor: pointer;
							padding: 0 15px;
							margin: 0 20px 0 0;
							display: inline-block;
							font-size: 14px;
							line-height: 40px;
							border-radius: 4px;
							outline: none;
							background: #423DD2;
							flex: none;
							width: auto;
							height: 40px;
							.icon {
								color: rgba(255, 255, 255, 1);
							}
							.text {
								color: rgba(255, 255, 255, 1);
							}
						}
						.balanceBtn:hover {
							opacity: 0.9;
							.icon {
								color: rgba(255, 255, 255, 1);
							}
							.text {
								color: rgba(255, 255, 255, 1);
							}
						}
					}
				}
				.center-btn-item {
					padding: 0;
					margin: 0;
					.updateBtn {
						border: 0;
						cursor: pointer;
						border-radius: 25px;
						padding: 0 15px;
						margin: 0 20px 0 0;
						outline: none;
						background: #423DD2;
						display: inline-block;
						width: auto;
						font-size: 14px;
						line-height: 40px;
						height: 40px;
						.icon {
							color: rgba(255, 255, 255, 1);
						}
						.text {
							color: rgba(255, 255, 255, 1);
						}
					}
					.updateBtn:hover {
						opacity: 0.9;
						.icon {
							color: rgba(255, 255, 255, 1);
						}
						.text {
							color: rgba(255, 255, 255, 1);
						}
					}
					.closeBtn {
						border: none;
						cursor: pointer;
						border-radius: 25px;
						padding: 0 15px;
						margin: 0 20px 0 0;
						outline: none;
						background: #BABABA;
						display: inline-block;
						width: auto;
						font-size: 14px;
						line-height: 40px;
						height: 40px;
						.icon {
							color: #FFFFFF;
						}
						.text {
							color: #FFFFFF;
						}
					}
					.closeBtn:hover {
						color: rgba(64, 158, 255, .5);
						border-color: rgba(64, 158, 255, .5);
						.icon {
							color: #FFFFFF;
						}
						.text {
							color: #FFFFFF;
						}
					}
				}
				.el-date-editor.el-input {
					width: auto;
				}
			}
		}
	}
	.coupon_list {
		width: 100%;
		display: flex;
		flex-wrap: wrap;
		.coupon {
			width: 49%;
			margin: 0 1% 20px 0;
			background: url('../../assets/coupon.png');
			background-repeat: no-repeat;
			background-position: center center;
			background-size: cover;
			padding: 60px 30px;
			display: flex;
			align-items: center;
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
		.coupons {
			filter: grayscale(100%);
		}
		.noList {
			color: #9e9e9e;
			width: 100%;
			text-align: center;
			padding: 60px 0;
		}
	}
</style>
