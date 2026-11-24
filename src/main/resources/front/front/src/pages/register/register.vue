<template>
	<div>

		<div class="container">
			<el-form class='rgs-form animate__animated animate__bounce' v-if="pageFlag=='register'" ref="registerForm" :model="registerForm" :rules="rules">
				<div class="rgs-form2">
					<div class="title">明星粉丝应援系统注册</p></div>
					<el-form-item class="list-item" v-if="tableName=='jingjiren'" prop="jingjiren">
						<div class="label" :class="changeRules('jingjiren')?'required':''">经纪人：</div>
						<el-input v-model="registerForm.jingjiren"  placeholder="请输入经纪人" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jingjiren'" prop="mima">
						<div class="label" :class="changeRules('mima')?'required':''">密码：</div>
						<el-input v-model="registerForm.mima" type="password" placeholder="请输入密码" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jingjiren'" prop="mima2">
						<div class="label" :class="changeRules('mima')?'required':''">确认密码：</div>
						<el-input v-model="registerForm.mima2" type="password" placeholder="请再次输入密码" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jingjiren'" prop="xingbie">
						<div class="label" :class="changeRules('xingbie')?'required':''">性别：</div>
						<el-select v-model="registerForm.xingbie" placeholder="请选择性别" >
							<el-option
								v-for="(item,index) in jingjirenxingbieOptions"
								:key="index"
								:label="item"
								:value="item">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jingjiren'" prop="touxiang">
						<div class="label" :class="changeRules('touxiang')?'required':''">头像：</div>
						<file-upload
							tip="点击上传头像"
							action="file/upload"
							:limit="1"
							:multiple="true"
							:fileUrls="registerForm.touxiang?registerForm.touxiang:''"
							@change="jingjirentouxiangUploadChange"
						></file-upload>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jingjiren'" prop="shoujihao">
						<div class="label" :class="changeRules('shoujihao')?'required':''">手机号：</div>
						<el-input v-model="registerForm.shoujihao"  placeholder="请输入手机号" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jingjiren'" prop="nianling">
						<div class="label" :class="changeRules('nianling')?'required':''">年龄：</div>
						<el-input v-model="registerForm.nianling"  placeholder="请输入年龄" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='fensi'" prop="fensizhanghao">
						<div class="label" :class="changeRules('fensizhanghao')?'required':''">粉丝账号：</div>
						<el-input v-model="registerForm.fensizhanghao"  placeholder="请输入粉丝账号" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='fensi'" prop="fensixingming">
						<div class="label" :class="changeRules('fensixingming')?'required':''">粉丝姓名：</div>
						<el-input v-model="registerForm.fensixingming"  placeholder="请输入粉丝姓名" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='fensi'" prop="mima">
						<div class="label" :class="changeRules('mima')?'required':''">密码：</div>
						<el-input v-model="registerForm.mima" type="password" placeholder="请输入密码" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='fensi'" prop="mima2">
						<div class="label" :class="changeRules('mima')?'required':''">确认密码：</div>
						<el-input v-model="registerForm.mima2" type="password" placeholder="请再次输入密码" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='fensi'" prop="xingbie">
						<div class="label" :class="changeRules('xingbie')?'required':''">性别：</div>
						<el-select v-model="registerForm.xingbie" placeholder="请选择性别" >
							<el-option
								v-for="(item,index) in fensixingbieOptions"
								:key="index"
								:label="item"
								:value="item">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='fensi'" prop="lianxidianhua">
						<div class="label" :class="changeRules('lianxidianhua')?'required':''">联系电话：</div>
						<el-input v-model="registerForm.lianxidianhua"  placeholder="请输入联系电话" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='fensi'" prop="touxiang">
						<div class="label" :class="changeRules('touxiang')?'required':''">头像：</div>
						<file-upload
							tip="点击上传头像"
							action="file/upload"
							:limit="1"
							:multiple="true"
							:fileUrls="registerForm.touxiang?registerForm.touxiang:''"
							@change="fensitouxiangUploadChange"
						></file-upload>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='fensi'" prop="xiaimingxing">
						<div class="label" :class="changeRules('xiaimingxing')?'required':''">喜爱明星：</div>
						<el-input v-model="registerForm.xiaimingxing"  placeholder="请输入喜爱明星" />
					</el-form-item>
					<div class="register-btn">
						<div class="register-btn1">
							<el-button class="register_btn" type="primary" @click="submitForm('registerForm')">注册</el-button>
						</div>
						<div class="register-btn2">
							<router-link class="has_btn" to="/login">已有账号，直接登录</router-link>
						</div>
					</div>
				</div>
				<div class="idea1"></div>
				<div class="idea2"></div>
			</el-form>
		</div>
	</div>
</div>
</template>

<script>
	import 'animate.css';

export default {
    //数据集合
    data() {
		return {
            pageFlag : '',
			tableName: '',
			registerForm: {},
			forgetForm: {},
			rules: {},
			requiredRules: {},
            jingjirenxingbieOptions: [],
            fensixingbieOptions: [],
		}
    },
	mounted() {
		if(this.$route.query.pageFlag=='register'){
			this.tableName = this.$route.query.role;
			if(this.tableName=='jingjiren'){
				this.registerForm = {
					jingjiren: '',
					mima: '',
					mima2: '',
					xingbie: '',
					touxiang: '',
					shoujihao: '',
					nianling: '',
					money: '',
				}
			}
			if(this.tableName=='fensi'){
				this.registerForm = {
					fensizhanghao: '',
					fensixingming: '',
					mima: '',
					mima2: '',
					xingbie: '',
					lianxidianhua: '',
					touxiang: '',
					xiaimingxing: '',
					money: '',
				}
			}
			if ('jingjiren' == this.tableName) {
				this.rules.mima = [{ required: true, message: '请输入密码', trigger: 'blur' }];
				this.requiredRules.mima = [{ required: true, message: '请输入密码', trigger: 'blur' }]
			}
			this.jingjirenxingbieOptions = "男,女".split(',');
			if ('jingjiren' == this.tableName) {
				this.rules.shoujihao = [{ required: true, validator: this.$validate.isMobile, trigger: 'blur' }];
			}
			if ('jingjiren' == this.tableName) {
				this.rules.money = [{ required: true, validator: this.$validate.isNumber, trigger: 'blur' }];
			}
			if ('fensi' == this.tableName) {
				this.rules.fensizhanghao = [{ required: true, message: '请输入粉丝账号', trigger: 'blur' }];
				this.requiredRules.fensizhanghao = [{ required: true, message: '请输入粉丝账号', trigger: 'blur' }]
			}
			if ('fensi' == this.tableName) {
				this.rules.fensixingming = [{ required: true, message: '请输入粉丝姓名', trigger: 'blur' }];
				this.requiredRules.fensixingming = [{ required: true, message: '请输入粉丝姓名', trigger: 'blur' }]
			}
			if ('fensi' == this.tableName) {
				this.rules.mima = [{ required: true, message: '请输入密码', trigger: 'blur' }];
				this.requiredRules.mima = [{ required: true, message: '请输入密码', trigger: 'blur' }]
			}
			this.fensixingbieOptions = "男,女".split(',');
			if ('fensi' == this.tableName) {
				this.rules.lianxidianhua = [{ required: true, validator: this.$validate.isMobile, trigger: 'blur' }];
			}
			if ('fensi' == this.tableName) {
				this.rules.money = [{ required: true, validator: this.$validate.isNumber, trigger: 'blur' }];
			}
		}
	},
    created() {
		this.pageFlag = this.$route.query.pageFlag;
    },
    //方法集合
    methods: {
		changeRules(name){
			if(this.requiredRules[name]){
				return true
			}
			return false
		},
		// 获取uuid
		getUUID () {
			return new Date().getTime();
		},
        // 下二随
		jingjirentouxiangUploadChange(fileUrls) {
			this.registerForm.touxiang = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");
		},
		fensitouxiangUploadChange(fileUrls) {
			this.registerForm.touxiang = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");
		},

		// 多级联动参数


		submitForm(formName) {
			this.$refs[formName].validate((valid) => {
				if (valid) {
					var url=this.tableName+"/register";
					if(`jingjiren` == this.tableName && this.registerForm.mima!=this.registerForm.mima2) {
						this.$message.error(`两次密码输入不一致`);
						return
					}
					if(`fensi` == this.tableName && this.registerForm.mima!=this.registerForm.mima2) {
						this.$message.error(`两次密码输入不一致`);
						return
					}
					this.$http.post(url, this.registerForm).then(res => {
						if (res.data.code === 0) {
							this.$message({
								message: '注册成功',
								type: 'success',
								duration: 1500,
								onClose: () => {
									this.$router.push('/login');
								}
							});
						} else {
							this.$message.error(res.data.msg);
						}
					});
				} else {
					return false;
				}
			});
		},
		resetForm(formName) {
			this.$refs[formName].resetFields();
		}
    }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.container {
		background: url(http://codegen.caihongy.cn/20241210/2ed71b328b23423aa9720217d4a1d455.webp) no-repeat center top / cover;
		display: flex;
		width: 100%;
		min-height: 100vh;
		justify-content: center;
		align-items: center;
		position: relative;
		background: url(http://codegen.caihongy.cn/20241210/2ed71b328b23423aa9720217d4a1d455.webp) no-repeat center top / cover;
		.rgs-form {
			border: 4px solid #FFFFFF;
			border-radius: 10px;
			padding: 0;
			box-shadow: none;
			backdrop-filter: 10px;
			margin: 20px 0;
			z-index: 1;
			background: rgba(255,255,255,0.5);
			width: 681px;
			height: auto;
			.rgs-form2 {
				width: 100%;
				.title {
					margin: 40px 0;
					color: #1F1F1F;
					font-weight: 700;
					width: 100%;
					font-size: 24px;
					line-height: 44px;
					text-align: center;
				}
				.subtitle {
					margin: 0 0 10px 0;
					text-shadow: 4px 4px 2px rgba(64, 158, 255, .5);
					color: rgba(64, 158, 255, 1);
					width: 100%;
					font-size: 20px;
					line-height: 44px;
					text-align: center;
				}
				.list-item {
					margin: 0 auto 10px;
					display: flex;
					width: 505px;
					border-color: #000000;
					border-width: 0 0 1px 0;
					border-style: solid;
					/deep/.el-form-item__content {
						display: flex;
						width: 100%;
						.label {
							color: #000000;
							width: 120px;
							font-size: 14px;
							line-height: 60px;
						}
						
						.required {
							position: relative;
						}
						.required::after{
							color: red;
							left: -10px;
							position: absolute;
							content: "*";
						}
						.el-input {
							width: 100%;
						}
						.el-input .el-input__inner {
							border: none;
							padding: 0 10px;
							color: #000000;
							background: transparent;
							width: 100%;
							font-size: 14px;
							height: 60px;
						}
						.el-input .el-input__inner:focus {
							border: none;
							padding: 0 10px;
							color: #000000;
							background: transparent;
							width: 100%;
							font-size: 14px;
							height: 60px;
						}
						.el-input-number {
							width: 100%;
						}
						.el-input-number .el-input__inner {
							text-align: left;
							border: none;
							padding: 0 10px;
							color: #000000;
							background: transparent;
							width: 100%;
							font-size: 14px;
							height: 60px;
						}
						.el-input-number .el-input-number__decrease {
							display: none;
						}
						.el-input-number .el-input-number__increase {
							display: none;
						}
						.el-select {
							width: 100%;
						}
						.el-select .el-input__inner {
							border: none;
							padding: 0 10px;
							color: #000000;
							background: transparent;
							width: 100%;
							font-size: 14px;
							height: 60px;
						}
						.el-select .el-input__inner:focus {
							border: none;
							padding: 0 10px;
							color: #000000;
							background: transparent;
							width: 100%;
							font-size: 14px;
							height: 60px;
						}
						.el-date-editor {
							width: 100%;
						}
						.el-date-editor .el-input__inner {
							border: none;
							padding: 0 40px;
							color: #000000;
							background: transparent;
							width: 100%;
							font-size: 14px;
							height: 60px;
						}
						.el-date-editor .el-input__inner:focus {
							border: none;
							padding: 0 40px;
							color: #000000;
							background: transparent;
							width: 100%;
							font-size: 14px;
							height: 60px;
						}
						.el-upload--picture-card {
							background: transparent;
							border: 0;
							border-radius: 0;
							width: auto;
							height: auto;
							line-height: initial;
							vertical-align: middle;
						}
						.upload .upload-img {
							border: 1px dashed rgba(64, 158, 255, 1);
							cursor: pointer;
							border-radius: 8px;
							color: #000;
							width: 80px;
							font-size: 32px;
							line-height: 80px;
							text-align: center;
							height: 80px;
						}
						.el-upload-list .el-upload-list__item {
							border: 1px dashed rgba(64, 158, 255, 1);
							cursor: pointer;
							border-radius: 8px;
							color: #000;
							width: 80px;
							font-size: 32px;
							line-height: 80px;
							text-align: center;
							height: 80px;
							font-size: 14px;
							line-height: 1.8;
						}
						.el-upload .el-icon-plus {
							border: 1px dashed rgba(64, 158, 255, 1);
							cursor: pointer;
							border-radius: 8px;
							color: #000;
							width: 80px;
							font-size: 32px;
							line-height: 80px;
							text-align: center;
							height: 80px;
						}
						.el-upload__tip {
							color: #838fa1;
						}
						.emailInput {
							border: none;
							padding: 0 10px;
							color: #000000;
							background: transparent;
							width: 100%;
							font-size: 14px;
							height: 60px;
						}
						.emailInput:focus {
							border: none;
							padding: 0 10px;
							color: #000000;
							background: transparent;
							width: 100%;
							font-size: 14px;
							height: 60px;
						}
						.el-btn {
							border: 0;
							cursor: pointer;
							padding: 0;
							margin: 0;
							color: #fff;
							font-size: 12px;
							border-radius: 0 4px 4px 0;
							box-shadow: none;
							outline: none;
							background: linear-gradient( 135deg, #B1D8FB 0%, #7483E5 55%, #423CD2 100%);
							flex: none;
							width: 80px;
							height: 60px;
						}
						.el-btn:hover {
						}
						
						.el-input__inner::placeholder {
							color: #9E9E9E;
							font-size: 14px;
						}
						input::placeholder {
							color: #9E9E9E;
							font-size: 14px;
						}
						.editor {
							width: 100%;
							height: auto;
						}
					}
				}
				.register-btn {
					margin:  0 auto;
					width: 505px;
				}
				.register-btn1 {
					width: 100%;
				}
				.register-btn2 {
					margin: 20px 0;
					width: 100%;
				}
				.register_btn {
					border: 0;
					cursor: pointer;
					border-radius: 4px;
					padding: 0;
					margin: 0;
					outline: none;
					color: #fff;
					background: linear-gradient( 135deg, #B1D8FB 0%, #7483E5 55%, #423CD2 100%);
					width: 100%;
					font-size: 24px;
					height: 60px;
				}
				.register_btn:hover {
				}
				.has_btn {
					border: 0;
					cursor: pointer;
					padding: 0;
					margin: 0;
					color: #000;
					display: inline-block;
					text-decoration: none;
					font-size: 14px;
					line-height: 60px;
					border-radius: 4px;
					outline: none;
					background: #FFF;
					width: 100%;
					text-align: center;
					height: 60px;
				}
				.has_btn:hover {
				}
			}
			.idea1 {
				background: red;
				display: none;
				width: 100%;
				height: 40px;
			}
			.idea2 {
				background: blue;
				display: none;
				width: 100%;
				height: 40px;
			}
		}
	}
	
	::-webkit-scrollbar {
		display: none;
	}
</style>
