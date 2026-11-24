<template>
	<div class="add-update-preview">
		<el-form
			class="add-update-form"
			ref="ruleForm"
			:model="ruleForm"
			:rules="rules"
			label-width="120px"
			>
			<el-form-item class="add-item" label="名称" prop="name">
				<el-input v-model="ruleForm.name" 
					placeholder="名称" clearable :disabled=" false  ||ro.name"></el-input>
			</el-form-item>
			<el-form-item class="add-item"  label="券类型" prop="type">
				<el-select v-model="ruleForm.type" placeholder="请选择券类型" :disabled=" false  ||ro.type" >
					<el-option
						v-for="(item,index) in typeOptions"
						:key="index"
						:label="item"
						:value="item">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item class="add-item" label="满额" prop="fullamount">
				<el-input-number v-model="ruleForm.fullamount" placeholder="满额" :disabled=" false ||ro.fullamount"></el-input-number>
			</el-form-item>
			<el-form-item class="add-item" label="优惠额" prop="discountamount">
				<el-input-number v-model="ruleForm.discountamount" placeholder="优惠额" :disabled=" false ||ro.discountamount"></el-input-number>
			</el-form-item>
			<el-form-item class="add-item" label="生效时间" prop="startime">
				<el-date-picker
					:disabled=" false  ||ro.startime"
					value-format="yyyy-MM-dd HH:mm:ss"
					v-model="ruleForm.startime" 
					type="datetime"
					placeholder="生效时间">
				</el-date-picker>
			</el-form-item>
			<el-form-item class="add-item" label="过期时间" prop="endtime">
				<el-date-picker
					:disabled=" false  ||ro.endtime"
					value-format="yyyy-MM-dd HH:mm:ss"
					v-model="ruleForm.endtime" 
					type="datetime"
					placeholder="过期时间">
				</el-date-picker>
			</el-form-item>
			<el-form-item class="add-item" label="备注" prop="remark">
				<el-input v-model="ruleForm.remark" 
					placeholder="备注" clearable :disabled=" false  ||ro.remark"></el-input>
			</el-form-item>

			<el-form-item class="add-btn-item">
				<el-button class="submitBtn"  type="primary" @click="onSubmit">
					<span class="icon iconfont icon-kaitongfuwu"></span>
					<span class="text">提交</span>
				</el-button>
				<el-button class="closeBtn" @click="back()">
					<span class="icon iconfont icon-shanchu1"></span>
					<span class="text">取消</span>
				</el-button>
			</el-form-item>
		</el-form>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				id: '',
				baseUrl: '',
				ro:{
					userid : false,
					name : false,
					type : false,
					fullamount : false,
					discountamount : false,
					startime : false,
					endtime : false,
					remark : false,
				},
				type: '',
				userTableName: localStorage.getItem('UserTableName'),
				ruleForm: {
					userid: '',
					name: '',
					type: '',
					fullamount: '',
					discountamount: '',
					startime: '',
					endtime: '',
					remark: '',
				},
				typeOptions: [],


				rules: {
					userid: [
						{ required: true, message: '用户id不能为空', trigger: 'blur' },
					],
					name: [
						{ required: true, message: '名称不能为空', trigger: 'blur' },
					],
					type: [
						{ required: true, message: '券类型不能为空', trigger: 'blur' },
					],
					fullamount: [
						{ required: true, message: '满额不能为空', trigger: 'blur' },
						{ validator: this.$validate.isNumber, trigger: 'blur' },
					],
					discountamount: [
						{ required: true, message: '优惠额不能为空', trigger: 'blur' },
						{ validator: this.$validate.isNumber, trigger: 'blur' },
					],
					startime: [
						{ required: true, message: '生效时间不能为空', trigger: 'blur' },
					],
					endtime: [
						{ required: true, message: '过期时间不能为空', trigger: 'blur' },
					],
					remark: [
					],
				},
				centerType: false,
			};
		},
		computed: {



		},
		components: {
		},
		created() {
			if(this.$route.query.centerType){
				this.centerType = true
			}
			//this.bg();
			let type = this.$route.query.type ? this.$route.query.type : '';
			this.init(type);
			this.baseUrl = this.$config.baseUrl;
		},
		methods: {
			getMakeZero(s) {
				return s < 10 ? '0' + s : s;
			},
			// 下载
			download(file){
				window.open(`${file}`)
			},
			// 初始化
			init(type) {
				this.type = type;
				if(type=='cross'){
					var obj = JSON.parse(localStorage.getItem('crossObj'));
					for (var o in obj){
						if(o=='userid'){
							this.ruleForm.userid = obj[o];
							this.ro.userid = true;
							continue;
						}
						if(o=='name'){
							this.ruleForm.name = obj[o];
							this.ro.name = true;
							continue;
						}
						if(o=='type'){
							this.ruleForm.type = obj[o];
							this.ro.type = true;
							continue;
						}
						if(o=='fullamount'){
							this.ruleForm.fullamount = obj[o];
							this.ro.fullamount = true;
							continue;
						}
						if(o=='discountamount'){
							this.ruleForm.discountamount = obj[o];
							this.ro.discountamount = true;
							continue;
						}
						if(o=='startime'){
							this.ruleForm.startime = obj[o];
							this.ro.startime = true;
							continue;
						}
						if(o=='endtime'){
							this.ruleForm.endtime = obj[o];
							this.ro.endtime = true;
							continue;
						}
						if(o=='remark'){
							this.ruleForm.remark = obj[o];
							this.ro.remark = true;
							continue;
						}
					}
				}else if(type=='edit'){
					this.info()
				}
				this.typeOptions = "满减券".split(',')

				if (localStorage.getItem('raffleType') && localStorage.getItem('raffleType') != null) {
					localStorage.removeItem('raffleType')
					setTimeout(() => {
						this.onSubmit()
					}, 300)
				}
			},

			// 多级联动参数
			// 多级联动参数
			info() {
				this.$http.get(`coupon/detail/${this.$route.query.id}`, {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						this.ruleForm = res.data.data;
					}
				});
			},
			// 提交
			async onSubmit() {
				if(!this.ruleForm.id) {
					delete this.ruleForm.userid
				}
				await this.$refs["ruleForm"].validate(async valid => {
					if(valid) {
						if(this.type=='cross'){
							var statusColumnName = localStorage.getItem('statusColumnName');
							var statusColumnValue = localStorage.getItem('statusColumnValue');
							if(statusColumnName && statusColumnName!='') {
								var obj = JSON.parse(localStorage.getItem('crossObj'));
								if(!statusColumnName.startsWith("[")) {
									for (var o in obj){
										if(o==statusColumnName){
											obj[o] = statusColumnValue;
										}
									}
									var table = localStorage.getItem('crossTable');
									await this.$http.post(table+'/update', obj).then(res => {});
								}
							}
						}


						await this.$http.post(`coupon/${this.ruleForm.id?'update':this.centerType?'save':'add'}`, this.ruleForm).then(async res => {
							if (res.data.code == 0) {
								this.$message({
									message: '操作成功',
									type: 'success',
									duration: 1500,
									onClose: () => {
										this.$router.go(-1);
										
									}
								});
							} else {
								this.$message({
									message: res.data.msg,
									type: 'error',
									duration: 1500
								});
							}
						});
					}
				});
			},
			// 获取uuid
			getUUID () {
				return new Date().getTime();
			},
			// 返回
			back() {
				this.$router.go(-1);
			},
		}
	};
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.add-update-preview {
		padding: 20px;
		margin: 10px auto;
		background: none;
		width: 1300px;
		position: relative;
		.add-update-form {
			border: 1px solid #423DD2;
			border-radius: 10px;
			padding: 20px;
			margin: 0 auto;
			width: 100%;
			position: relative;
			.add-item.el-form-item {
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
				/deep/ .el-form-item__content {
					margin-left: 120px;
				}
				.el-input {
					width: 100%;
				}
				.el-input /deep/ .el-input__inner {
					border: 0;
					border-radius: 4px;
					padding: 0 12px;
					box-shadow: 0 0 6px #423DD2;
					outline: none;
					color: #000;
					width: 400px;
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
				.el-input-number /deep/ .el-input__inner {
					text-align: left;
					border: 0;
					border-radius: 4px;
					padding: 0 12px;
					box-shadow: 0 0 6px #423DD2;
					outline: none;
					color: #000;
					width: 400px;
					font-size: 14px;
					height: 40px;
				}
				.el-input-number /deep/ .is-disabled .el-input__inner {
					text-align: left;
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
				.el-input-number /deep/ .el-input-number__decrease {
					display: none;
				}
				.el-input-number /deep/ .el-input-number__increase {
					display: none;
				}
				.el-select {
					width: 100%;
				}
				.el-select /deep/ .el-input__inner {
					border: 0;
					border-radius: 4px;
					padding: 0 10px;
					box-shadow: 0 0 6px #423DD2;
					outline: none;
					color: #000;
					width: 200px;
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
					width: 200px;
					font-size: 14px;
					height: 40px;
				}
				.el-date-editor {
					width: 100%;
				}
				.el-date-editor /deep/ .el-input__inner {
					border: 0;
					border-radius: 4px;
					padding: 0 10px 0 30px;
					box-shadow: 0 0 6px #423DD2;
					outline: none;
					color: #000;
					width: 200px;
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
					width: 200px;
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
					color: rgba(64, 158, 255, 1);
					width: 200px;
					font-size: 32px;
					line-height: 200px;
					text-align: center;
					height: 200px;
				}
				/deep/ .el-upload-list .el-upload-list__item {
					border: 1px dashed rgba(64, 158, 255, 1);
					cursor: pointer;
					border-radius: 6px;
					color: rgba(64, 158, 255, 1);
					width: 200px;
					font-size: 32px;
					line-height: 200px;
					text-align: center;
					height: 200px;
					font-size: 14px;
					line-height: 1.8;
				}
				/deep/ .el-upload .el-icon-plus {
					border: 1px dashed rgba(64, 158, 255, 1);
					cursor: pointer;
					border-radius: 6px;
					color: rgba(64, 158, 255, 1);
					width: 200px;
					font-size: 32px;
					line-height: 200px;
					text-align: center;
					height: 200px;
				}
				/deep/ .el-upload__tip {
					color: #838fa1;
				}
				.el-textarea /deep/ .el-textarea__inner {
					border: 0;
					border-radius: 4px;
					padding: 12px;
					box-shadow: 0 0 6px #423DD2;
					outline: none;
					color: #000;
					width: 400px;
					font-size: 14px;
					height: 120px;
				}
				.el-textarea /deep/ .el-textarea__inner[readonly="readonly"] {
					border: 0;
					cursor: not-allowed;
					border-radius: 4px;
					padding: 12px;
					box-shadow: none;
					outline: none;
					color: rgba(85, 85, 127, 1.0);
					background: rgb(238, 238, 238);
					width: 400px;
					font-size: 14px;
					height: 120px;
				}
				/deep/ .el-input__inner::placeholder {
					color: #e9e9e9;
					font-size: 16px;
				}
				/deep/ textarea::placeholder {
					color: #e9e9e9;
					font-size: 16px;
				}
				.editor {
					background-color: #fff;
					border-radius: 0;
					padding: 0;
					box-shadow: 0 0 6px #423DD2;
					margin: 0;
					width: 100%;
					border-color: #ccc;
					border-width: 0;
					border-style: solid;
					height: auto;
				}
				.upload-img {
					width: 150px;
					height: 150px;
				}
				.viewBtn {
					border: 0;
					cursor: pointer;
					padding: 0 10px;
					margin: 0;
					color: #fff;
					display: inline-block;
					font-size: 14px;
					line-height: 30px;
					border-radius: 4px;
					outline: none;
					background: #423DD2;
					width: auto;
					height: 30px;
				}
				.viewBtn:hover {
					opacity: 0.9;
				}
				.unviewBtn {
					border: 0;
					cursor: pointer;
					padding: 0 10px;
					margin: 0;
					color: #fff;
					display: inline-block;
					font-size: 14px;
					line-height: 30px;
					border-radius: 4px;
					outline: none;
					background: rgba(85, 85, 127, 1.0);
					width: auto;
					height: 30px;
				}
				.unviewBtn:hover {
				}
			}
			.add-btn-item {
				padding: 0;
				margin: 0;
				.submitBtn {
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
				.submitBtn:hover {
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
						color: #FFF;
					}
					.text {
						color: #FFF;
					}
				}
				.closeBtn:hover {
					opacity: 0.9;
					.icon {
						color: #FFF;
					}
					.text {
						color: #FFF;
					}
				}
			}
		}
	}
	.el-date-editor.el-input {
		width: auto;
	}
</style>
