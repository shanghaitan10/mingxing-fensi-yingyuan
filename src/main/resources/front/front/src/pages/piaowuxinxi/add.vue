<template>
	<div class="add-update-preview">
		<el-form
			class="add-update-form"
			ref="ruleForm"
			:model="ruleForm"
			:rules="rules"
			label-width="120px"
			>
			<el-form-item class="add-item" label="名称" prop="mingcheng">
				<el-input v-model="ruleForm.mingcheng" 
					placeholder="名称" clearable :disabled=" false  ||ro.mingcheng"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="明星姓名" prop="mingxingxingming">
				<el-input v-model="ruleForm.mingxingxingming" 
					placeholder="明星姓名" clearable :disabled=" false  ||ro.mingxingxingming"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="时间" prop="shijian">
				<el-date-picker
					:disabled=" false  ||ro.shijian"
					value-format="yyyy-MM-dd HH:mm:ss"
					v-model="ruleForm.shijian" 
					type="datetime"
					placeholder="时间">
				</el-date-picker>
			</el-form-item>
			<el-form-item class="add-item" label="地址" prop="dizhi">
				<el-input v-model="ruleForm.dizhi" 
					placeholder="地址" clearable :disabled=" false  ||ro.dizhi"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="宣传图" v-if="type!='cross' || (type=='cross' && !ro.xuanchuantu)" prop="xuanchuantu">
				<file-upload
					tip="点击上传宣传图"
					action="file/upload"
					:limit="3"
					:multiple="true"
					:fileUrls="ruleForm.xuanchuantu?ruleForm.xuanchuantu:''"
					@change="xuanchuantuUploadChange"
					></file-upload>
			</el-form-item>
			<el-form-item class="add-item" v-else label="宣传图" prop="xuanchuantu">
				<img v-if="ruleForm.xuanchuantu.substring(0,4)=='http'" class="upload-img" v-bind:key="index" :src="ruleForm.xuanchuantu.split(',')[0]">
				<img v-else class="upload-img" v-bind:key="index" v-for="(item,index) in ruleForm.xuanchuantu.split(',')" :src="baseUrl+item">
			</el-form-item>
			<el-form-item class="add-item" label="视频" v-if="type!='cross' || (type=='cross' && !ro.shipin)" prop="shipin">
				<file-upload
					tip="点击上传视频"
					action="file/upload"
					:limit="1"
					:type="2"
					:multiple="true"
					:fileUrls="ruleForm.shipin?ruleForm.shipin:''"
					@change="shipinUploadChange"
					></file-upload>
			</el-form-item>
			<el-form-item class="add-item" v-else label="视频" prop="shipin">
				<div class="viewBtn" v-if="ruleForm.shipin" @click="download(baseUrl + ruleForm.shipin)" type="success">预览</div>
				<div class="unviewBtn" v-else disabled>暂无</div>
			</el-form-item>
			<el-form-item class="add-item" label="经纪人" prop="jingjiren">
				<el-input v-model="ruleForm.jingjiren" 
					placeholder="经纪人" clearable :disabled=" false  ||ro.jingjiren"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="价格" prop="price">
				<el-input-number v-model="ruleForm.price" placeholder="价格" :disabled=" false ||ro.price"></el-input-number>
			</el-form-item>
			<el-form-item class="add-item" label="座位总数" prop="number">
				<el-input v-model.number="ruleForm.number" 
					placeholder="座位总数" clearable :disabled=" false  ||ro.number"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="明星阵容" prop="mingxingzhenrong">
				<el-input
					type="textarea"
					:rows="8"
					placeholder="明星阵容"
					v-model="ruleForm.mingxingzhenrong">
					</el-input>
			</el-form-item>
			<el-form-item class="add-item" label="简介" prop="jianjie">
				<el-input
					type="textarea"
					:rows="8"
					placeholder="简介"
					v-model="ruleForm.jianjie">
					</el-input>
			</el-form-item>
			<el-form-item class="add-item" label="已选座位[用,号隔开]" prop="selected">
				<el-input
					type="textarea"
					:rows="8"
					placeholder="已选座位[用,号隔开]"
					v-model="ruleForm.selected">
					</el-input>
			</el-form-item>
			<el-form-item class="add-item" label="详情" prop="xiangqing">
				<editor 
					v-model="ruleForm.xiangqing" 
					class="editor" 
					action="file/upload">
				</editor>
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
					mingcheng : false,
					mingxingxingming : false,
					shijian : false,
					dizhi : false,
					xuanchuantu : false,
					shipin : false,
					mingxingzhenrong : false,
					jingjiren : false,
					jianjie : false,
					xiangqing : false,
					thumbsupnum : false,
					crazilynum : false,
					clicktime : false,
					discussnum : false,
					price : false,
					storeupnum : false,
					number : false,
					selected : false,
				},
				type: '',
				userTableName: localStorage.getItem('UserTableName'),
				ruleForm: {
					mingcheng: '',
					mingxingxingming: '',
					shijian: '',
					dizhi: '',
					xuanchuantu: '',
					shipin: '',
					mingxingzhenrong: '',
					jingjiren: '',
					jianjie: '',
					xiangqing: '',
					thumbsupnum: '',
					crazilynum: '',
					clicktime: '',
					discussnum: '',
					price: '',
					storeupnum: '',
					number: '',
					selected: '',
				},


				rules: {
					mingcheng: [
					],
					mingxingxingming: [
					],
					shijian: [
					],
					dizhi: [
					],
					xuanchuantu: [
					],
					shipin: [
					],
					mingxingzhenrong: [
					],
					jingjiren: [
					],
					jianjie: [
					],
					xiangqing: [
					],
					thumbsupnum: [
						{ validator: this.$validate.isIntNumer, trigger: 'blur' },
					],
					crazilynum: [
						{ validator: this.$validate.isIntNumer, trigger: 'blur' },
					],
					clicktime: [
					],
					discussnum: [
						{ validator: this.$validate.isIntNumer, trigger: 'blur' },
					],
					price: [
						{ required: true, message: '价格不能为空', trigger: 'blur' },
						{ validator: this.$validate.isNumber, trigger: 'blur' },
					],
					storeupnum: [
						{ validator: this.$validate.isIntNumer, trigger: 'blur' },
					],
					number: [
						{ validator: this.$validate.isIntNumer, trigger: 'blur' },
					],
					selected: [
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
						if(o=='mingcheng'){
							this.ruleForm.mingcheng = obj[o];
							this.ro.mingcheng = true;
							continue;
						}
						if(o=='mingxingxingming'){
							this.ruleForm.mingxingxingming = obj[o];
							this.ro.mingxingxingming = true;
							continue;
						}
						if(o=='shijian'){
							this.ruleForm.shijian = obj[o];
							this.ro.shijian = true;
							continue;
						}
						if(o=='dizhi'){
							this.ruleForm.dizhi = obj[o];
							this.ro.dizhi = true;
							continue;
						}
						if(o=='xuanchuantu'){
							this.ruleForm.xuanchuantu = obj[o].split(",")[0];
							this.ro.xuanchuantu = true;
							continue;
						}
						if(o=='shipin'){
							this.ruleForm.shipin = obj[o];
							this.ro.shipin = true;
							continue;
						}
						if(o=='mingxingzhenrong'){
							this.ruleForm.mingxingzhenrong = obj[o];
							this.ro.mingxingzhenrong = true;
							continue;
						}
						if(o=='jingjiren'){
							this.ruleForm.jingjiren = obj[o];
							this.ro.jingjiren = true;
							continue;
						}
						if(o=='jianjie'){
							this.ruleForm.jianjie = obj[o];
							this.ro.jianjie = true;
							continue;
						}
						if(o=='xiangqing'){
							this.ruleForm.xiangqing = obj[o];
							this.ro.xiangqing = true;
							continue;
						}
						if(o=='thumbsupnum'){
							this.ruleForm.thumbsupnum = obj[o];
							this.ro.thumbsupnum = true;
							continue;
						}
						if(o=='crazilynum'){
							this.ruleForm.crazilynum = obj[o];
							this.ro.crazilynum = true;
							continue;
						}
						if(o=='clicktime'){
							this.ruleForm.clicktime = obj[o];
							this.ro.clicktime = true;
							continue;
						}
						if(o=='discussnum'){
							this.ruleForm.discussnum = obj[o];
							this.ro.discussnum = true;
							continue;
						}
						if(o=='price'){
							this.ruleForm.price = obj[o];
							this.ro.price = true;
							continue;
						}
						if(o=='storeupnum'){
							this.ruleForm.storeupnum = obj[o];
							this.ro.storeupnum = true;
							continue;
						}
						if(o=='number'){
							this.ruleForm.number = obj[o];
							this.ro.number = true;
							continue;
						}
						if(o=='selected'){
							this.ruleForm.selected = obj[o];
							this.ro.selected = true;
							continue;
						}
					}
				}else if(type=='edit'){
					this.info()
				}
				// 获取用户信息
				this.$http.get(this.userTableName + '/session', {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						var json = res.data.data;
						if((json.jingjiren!=''&&json.jingjiren) || json.jingjiren==0){
							this.ruleForm.jingjiren = json.jingjiren;
							this.ro.jingjiren = true;
						}
					}
				});

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
				this.$http.get(`piaowuxinxi/detail/${this.$route.query.id}`, {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						this.ruleForm = res.data.data;
					}
				});
			},
			// 提交
			async onSubmit() {
				if(this.ruleForm.price<0){
					this.$message.error("价格不能输入负数");
					return
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


						await this.$http.post(`piaowuxinxi/${this.ruleForm.id?'update':this.centerType?'save':'add'}`, this.ruleForm).then(async res => {
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
			xuanchuantuUploadChange(fileUrls) {
				this.ruleForm.xuanchuantu = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");
			},
			shipinUploadChange(fileUrls) {
				this.ruleForm.shipin = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");
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
