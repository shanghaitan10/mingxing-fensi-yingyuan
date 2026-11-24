<template>
	<div class="add-update-preview">
		<el-form
			class="add-update-form"
			ref="ruleForm"
			:model="ruleForm"
			:rules="rules"
			label-width="120px"
			>
			<el-form-item class="add-item" label="姓名" prop="xingming">
				<el-input v-model="ruleForm.xingming" 
					placeholder="姓名" clearable :disabled=" false  ||ro.xingming"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="年龄" prop="nianling">
				<el-input v-model="ruleForm.nianling" 
					placeholder="年龄" clearable :disabled=" false  ||ro.nianling"></el-input>
			</el-form-item>
			<el-form-item class="add-item"  label="性别" prop="xingbie">
				<el-select v-model="ruleForm.xingbie" placeholder="请选择性别" :disabled=" false  ||ro.xingbie" >
					<el-option
						v-for="(item,index) in xingbieOptions"
						:key="index"
						:label="item"
						:value="item">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item class="add-item" label="出生日期" prop="chushengriqi">
				<el-date-picker
					:disabled=" false  ||ro.chushengriqi"
					format="yyyy 年 MM 月 dd 日"
					value-format="yyyy-MM-dd"
					v-model="ruleForm.chushengriqi" 
					type="date"
					placeholder="出生日期">
				</el-date-picker> 
			</el-form-item>
			<el-form-item class="add-item" label="星座" prop="xingzuo">
				<el-input v-model="ruleForm.xingzuo" 
					placeholder="星座" clearable :disabled=" false  ||ro.xingzuo"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="爱好" prop="aihao">
				<el-input v-model="ruleForm.aihao" 
					placeholder="爱好" clearable :disabled=" false  ||ro.aihao"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="经纪人" prop="jingjiren">
				<el-input v-model="ruleForm.jingjiren" 
					placeholder="经纪人" clearable :disabled=" false  ||ro.jingjiren"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="个人照片" v-if="type!='cross' || (type=='cross' && !ro.gerenzhaopian)" prop="gerenzhaopian">
				<file-upload
					tip="点击上传个人照片"
					action="file/upload"
					:limit="3"
					:multiple="true"
					:fileUrls="ruleForm.gerenzhaopian?ruleForm.gerenzhaopian:''"
					@change="gerenzhaopianUploadChange"
					></file-upload>
			</el-form-item>
			<el-form-item class="add-item" v-else label="个人照片" prop="gerenzhaopian">
				<img v-if="ruleForm.gerenzhaopian.substring(0,4)=='http'" class="upload-img" v-bind:key="index" :src="ruleForm.gerenzhaopian.split(',')[0]">
				<img v-else class="upload-img" v-bind:key="index" v-for="(item,index) in ruleForm.gerenzhaopian.split(',')" :src="baseUrl+item">
			</el-form-item>
			<el-form-item class="add-item" label="获得成就" prop="huodechengjiu">
				<el-input
					type="textarea"
					:rows="8"
					placeholder="获得成就"
					v-model="ruleForm.huodechengjiu">
					</el-input>
			</el-form-item>
			<el-form-item class="add-item" label="个人物料" prop="gerenwuliao">
				<el-input
					type="textarea"
					:rows="8"
					placeholder="个人物料"
					v-model="ruleForm.gerenwuliao">
					</el-input>
			</el-form-item>
			<el-form-item class="add-item" label="常驻综艺" prop="changzhuzongyi">
				<el-input
					type="textarea"
					:rows="8"
					placeholder="常驻综艺"
					v-model="ruleForm.changzhuzongyi">
					</el-input>
			</el-form-item>
			<el-form-item class="add-item" label="个人介绍" prop="gerenjieshao">
				<editor 
					v-model="ruleForm.gerenjieshao" 
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
					xingming : false,
					nianling : false,
					xingbie : false,
					chushengriqi : false,
					xingzuo : false,
					aihao : false,
					jingjiren : false,
					gerenzhaopian : false,
					huodechengjiu : false,
					gerenwuliao : false,
					changzhuzongyi : false,
					gerenjieshao : false,
					thumbsupnum : false,
					crazilynum : false,
					clicktime : false,
					discussnum : false,
				},
				type: '',
				userTableName: localStorage.getItem('UserTableName'),
				ruleForm: {
					xingming: '',
					nianling: '',
					xingbie: '',
					chushengriqi: '',
					xingzuo: '',
					aihao: '',
					jingjiren: '',
					gerenzhaopian: '',
					huodechengjiu: '',
					gerenwuliao: '',
					changzhuzongyi: '',
					gerenjieshao: '',
					thumbsupnum: '',
					crazilynum: '',
					clicktime: '',
					discussnum: '',
				},
				xingbieOptions: [],


				rules: {
					xingming: [
						{ required: true, message: '姓名不能为空', trigger: 'blur' },
					],
					nianling: [
						{ required: true, message: '年龄不能为空', trigger: 'blur' },
					],
					xingbie: [
					],
					chushengriqi: [
					],
					xingzuo: [
					],
					aihao: [
					],
					jingjiren: [
					],
					gerenzhaopian: [
					],
					huodechengjiu: [
					],
					gerenwuliao: [
					],
					changzhuzongyi: [
					],
					gerenjieshao: [
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
						if(o=='xingming'){
							this.ruleForm.xingming = obj[o];
							this.ro.xingming = true;
							continue;
						}
						if(o=='nianling'){
							this.ruleForm.nianling = obj[o];
							this.ro.nianling = true;
							continue;
						}
						if(o=='xingbie'){
							this.ruleForm.xingbie = obj[o];
							this.ro.xingbie = true;
							continue;
						}
						if(o=='chushengriqi'){
							this.ruleForm.chushengriqi = obj[o];
							this.ro.chushengriqi = true;
							continue;
						}
						if(o=='xingzuo'){
							this.ruleForm.xingzuo = obj[o];
							this.ro.xingzuo = true;
							continue;
						}
						if(o=='aihao'){
							this.ruleForm.aihao = obj[o];
							this.ro.aihao = true;
							continue;
						}
						if(o=='jingjiren'){
							this.ruleForm.jingjiren = obj[o];
							this.ro.jingjiren = true;
							continue;
						}
						if(o=='gerenzhaopian'){
							this.ruleForm.gerenzhaopian = obj[o].split(",")[0];
							this.ro.gerenzhaopian = true;
							continue;
						}
						if(o=='huodechengjiu'){
							this.ruleForm.huodechengjiu = obj[o];
							this.ro.huodechengjiu = true;
							continue;
						}
						if(o=='gerenwuliao'){
							this.ruleForm.gerenwuliao = obj[o];
							this.ro.gerenwuliao = true;
							continue;
						}
						if(o=='changzhuzongyi'){
							this.ruleForm.changzhuzongyi = obj[o];
							this.ro.changzhuzongyi = true;
							continue;
						}
						if(o=='gerenjieshao'){
							this.ruleForm.gerenjieshao = obj[o];
							this.ro.gerenjieshao = true;
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
				this.xingbieOptions = "男,女".split(',')

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
				this.$http.get(`mingxingjianjie/detail/${this.$route.query.id}`, {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						this.ruleForm = res.data.data;
					}
				});
			},
			// 提交
			async onSubmit() {
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


						await this.$http.post(`mingxingjianjie/${this.ruleForm.id?'update':this.centerType?'save':'add'}`, this.ruleForm).then(async res => {
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
			gerenzhaopianUploadChange(fileUrls) {
				this.ruleForm.gerenzhaopian = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");
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
