<template>
	<div class="addEdit-block">
		<el-form
			class="add-update-preview"
			ref="ruleForm"
			:model="ruleForm"
			:rules="rules"
			label-width="120px"
		>
			<template >
				<el-form-item class="input" v-if="type!='info'"  label="姓名" prop="xingming" >
					<el-input v-model="ruleForm.xingming" placeholder="姓名" clearable  :readonly="ro.xingming"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="姓名" prop="xingming" >
					<el-input v-model="ruleForm.xingming" placeholder="姓名" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="年龄" prop="nianling" >
					<el-input v-model="ruleForm.nianling" placeholder="年龄" clearable  :readonly="ro.nianling"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="年龄" prop="nianling" >
					<el-input v-model="ruleForm.nianling" placeholder="年龄" readonly></el-input>
				</el-form-item>
				<el-form-item class="select" v-if="type!='info'"  label="性别" prop="xingbie" >
					<el-select :disabled="ro.xingbie" v-model="ruleForm.xingbie" placeholder="请选择性别" >
						<el-option
							v-for="(item,index) in xingbieOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item v-else class="input" label="性别" prop="xingbie" >
					<el-input v-model="ruleForm.xingbie"
						placeholder="性别" readonly></el-input>
				</el-form-item>
				<el-form-item class="date" v-if="type!='info'" label="出生日期" prop="chushengriqi" >
					<el-date-picker
						format="yyyy 年 MM 月 dd 日"
						value-format="yyyy-MM-dd"
						v-model="ruleForm.chushengriqi" 
						type="date"
						:readonly="ro.chushengriqi"
						placeholder="出生日期"
					></el-date-picker> 
				</el-form-item>
				<el-form-item class="input" v-else-if="ruleForm.chushengriqi" label="出生日期" prop="chushengriqi" >
					<el-input v-model="ruleForm.chushengriqi" placeholder="出生日期" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="星座" prop="xingzuo" >
					<el-input v-model="ruleForm.xingzuo" placeholder="星座" clearable  :readonly="ro.xingzuo"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="星座" prop="xingzuo" >
					<el-input v-model="ruleForm.xingzuo" placeholder="星座" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="爱好" prop="aihao" >
					<el-input v-model="ruleForm.aihao" placeholder="爱好" clearable  :readonly="ro.aihao"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="爱好" prop="aihao" >
					<el-input v-model="ruleForm.aihao" placeholder="爱好" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="经纪人" prop="jingjiren" >
					<el-input v-model="ruleForm.jingjiren" placeholder="经纪人" clearable  :readonly="ro.jingjiren"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="经纪人" prop="jingjiren" >
					<el-input v-model="ruleForm.jingjiren" placeholder="经纪人" readonly></el-input>
				</el-form-item>
				<el-form-item class="upload" v-if="type!='info' && !ro.gerenzhaopian" label="个人照片" prop="gerenzhaopian" >
					<file-upload
						tip="点击上传个人照片"
						action="file/upload"
						:limit="3"
						:multiple="true"
						:fileUrls="ruleForm.gerenzhaopian?ruleForm.gerenzhaopian:''"
						@change="gerenzhaopianUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item class="upload" v-else-if="ruleForm.gerenzhaopian" label="个人照片" prop="gerenzhaopian" >
					<img v-if="ruleForm.gerenzhaopian.substring(0,4)=='http'&&ruleForm.gerenzhaopian.split(',w').length>1" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.gerenzhaopian" width="100" height="100">
					<img v-else-if="ruleForm.gerenzhaopian.substring(0,4)=='http'" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.gerenzhaopian.split(',')[0]" width="100" height="100">
					<img v-else class="upload-img" style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in ruleForm.gerenzhaopian.split(',')" :src="$base.url+item" width="100" height="100">
				</el-form-item>
			</template>
			<el-form-item class="textarea" v-if="type!='info'" label="获得成就" prop="huodechengjiu" >
				<el-input
					style="min-width: 200px; max-width: 600px;"
					type="textarea"
					:rows="8"
					placeholder="获得成就"
					v-model="ruleForm.huodechengjiu" >
				</el-input>
			</el-form-item>
			<el-form-item v-else-if="ruleForm.huodechengjiu" label="获得成就" prop="huodechengjiu" >
				<span class="text">{{ruleForm.huodechengjiu}}</span>
			</el-form-item>
			<el-form-item class="textarea" v-if="type!='info'" label="个人物料" prop="gerenwuliao" >
				<el-input
					style="min-width: 200px; max-width: 600px;"
					type="textarea"
					:rows="8"
					placeholder="个人物料"
					v-model="ruleForm.gerenwuliao" >
				</el-input>
			</el-form-item>
			<el-form-item v-else-if="ruleForm.gerenwuliao" label="个人物料" prop="gerenwuliao" >
				<span class="text">{{ruleForm.gerenwuliao}}</span>
			</el-form-item>
			<el-form-item class="textarea" v-if="type!='info'" label="常驻综艺" prop="changzhuzongyi" >
				<el-input
					style="min-width: 200px; max-width: 600px;"
					type="textarea"
					:rows="8"
					placeholder="常驻综艺"
					v-model="ruleForm.changzhuzongyi" >
				</el-input>
			</el-form-item>
			<el-form-item v-else-if="ruleForm.changzhuzongyi" label="常驻综艺" prop="changzhuzongyi" >
				<span class="text">{{ruleForm.changzhuzongyi}}</span>
			</el-form-item>
			<el-form-item v-if="type!='info'"  label="个人介绍" prop="gerenjieshao" >
				<editor 
					style="min-width: 200px; max-width: 600px;"
					v-model="ruleForm.gerenjieshao" 
					class="editor" 
					action="file/upload">
				</editor>
			</el-form-item>
			<el-form-item v-else-if="ruleForm.gerenjieshao" label="个人介绍" prop="gerenjieshao" >
				<span class="text ql-snow ql-editor" v-html="ruleForm.gerenjieshao"></span>
			</el-form-item>
			<el-form-item class="btn">
				<el-button class="btn3"  v-if="type!='info'" type="success" @click="onSubmit">
					<span class="icon iconfont icon-xihuan"></span>
					提交
				</el-button>
				<el-button class="btn4" v-if="type!='info'" type="success" @click="back()">
					<span class="icon iconfont icon-xihuan"></span>
					取消
				</el-button>
				<el-button class="btn5" v-if="type=='info'" type="success" @click="back()">
					<span class="icon iconfont icon-xihuan"></span>
					返回
				</el-button>
			</el-form-item>
		</el-form>
    

	</div>
</template>
<script>
	import { 
		isIntNumer,
	} from "@/utils/validate";
	export default {
		data() {
			var validateIntNumber = (rule, value, callback) => {
				if(!value){
					callback();
				} else if (!isIntNumer(value)) {
					callback(new Error("请输入整数"));
				} else {
					callback();
				}
			};
			return {
				id: '',
				type: '',
			
			
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
					clicktime: '',
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
						{ validator: validateIntNumber, trigger: 'blur' },
					],
					crazilynum: [
						{ validator: validateIntNumber, trigger: 'blur' },
					],
					clicktime: [
					],
					discussnum: [
						{ validator: validateIntNumber, trigger: 'blur' },
					],
				},
			};
		},
		props: ["parent"],
		computed: {



		},
		components: {
		},
		created() {
		},
		methods: {
			// 下载
			download(file){
				window.open(`${file}`)
			},
			// 初始化
			init(id,type) {
				if (id) {
					this.id = id;
					this.type = type;
				}
				if(this.type=='info'||this.type=='else'||this.type=='msg'){
					this.info(id);
				}else if(this.type=='logistics'){
					for(let x in this.ro) {
						this.ro[x] = true
					}
					this.logistics=false;
					this.info(id);
				}else if(this.type=='cross'){
					var obj = this.$storage.getObj('crossObj');
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
							this.ruleForm.gerenzhaopian = obj[o];
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
				}
				// 获取用户信息
				this.$http({
					url: `${this.$storage.get('sessionTable')}/session`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						var json = data.data;
						if(((json.jingjiren!=''&&json.jingjiren) || json.jingjiren==0) && this.$storage.get("role")!="管理员"){
							this.ruleForm.jingjiren = json.jingjiren
							this.ro.jingjiren = true;
						}
					} else {
						this.$message.error(data.msg);
					}
				});
				this.xingbieOptions = "男,女".split(',')
			
			},
			// 多级联动参数

			info(id) {
				this.$http({
					url: `mingxingjianjie/info/${id}`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.ruleForm = data.data;
						//解决前台上传图片后台不显示的问题
						let reg=new RegExp('../../../upload','g')//g代表全部
						this.ruleForm.gerenjieshao = this.ruleForm.gerenjieshao.replace(reg,'../../../springboote4e34i32/upload');
					} else {
						this.$message.error(data.msg);
					}
				});
			},

			// 提交
			async onSubmit() {
					if(this.ruleForm.gerenzhaopian!=null) {
						this.ruleForm.gerenzhaopian = this.ruleForm.gerenzhaopian.replace(new RegExp(this.$base.url,"g"),"");
					}
					var objcross = this.$storage.getObj('crossObj');
					await this.$refs["ruleForm"].validate(async valid => {
						if (valid) {
							if(this.type=='cross'){
								var statusColumnName = this.$storage.get('statusColumnName');
								var statusColumnValue = this.$storage.get('statusColumnValue');
								if(statusColumnName!='') {
									var obj = this.$storage.getObj('crossObj');
									if(statusColumnName && !statusColumnName.startsWith("[")) {
										for (var o in obj){
											if(o==statusColumnName){
												obj[o] = statusColumnValue;
											}
										}
										var table = this.$storage.get('crossTable');
										await this.$http({
											url: `${table}/update`,
											method: "post",
											data: obj
										}).then(({ data }) => {});
									}
								}
							}
							
							await this.$http({
								url: `mingxingjianjie/${!this.ruleForm.id ? "save" : "update"}`,
								method: "post",
								data: this.ruleForm
							}).then(async ({ data }) => {
								if (data && data.code === 0) {
									this.$message({
										message: "操作成功",
										type: "success",
										duration: 1500,
										onClose: () => {
											this.parent.showFlag = true;
											this.parent.addOrUpdateFlag = false;
											this.parent.mingxingjianjieCrossAddOrUpdateFlag = false;
											this.parent.search();
											this.parent.contentStyleChange();
										}
									});
								} else {
									this.$message.error(data.msg);
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
				this.parent.showFlag = true;
				this.parent.addOrUpdateFlag = false;
				this.parent.mingxingjianjieCrossAddOrUpdateFlag = false;
				this.parent.contentStyleChange();
			},
			gerenzhaopianUploadChange(fileUrls) {
				this.ruleForm.gerenzhaopian = fileUrls;
			},
		}
	};
</script>
<style lang="scss" scoped>
	.addEdit-block {
		padding: 20px 30px;
	}
	.add-update-preview {
		border: 1px solid #BFBFBF;
		padding: 40px 20% 20px 15%;
		background: #fff;
	}
	.amap-wrapper {
		width: 100%;
		height: 500px;
	}
	
	.search-box {
		position: absolute;
	}
	
	.el-date-editor.el-input {
		width: auto;
	}
	.add-update-preview /deep/ .el-form-item {
		border: 0px solid #eee;
		padding: 0;
		margin: 0 0 20px 0;
		display: inline-block;
		width: 80%;
	}
	.add-update-preview .el-form-item /deep/ .el-form-item__label {
		padding: 0 10px 0 0;
		color: #666;
		font-weight: 600;
		width: 120px;
		font-size: 16px;
		line-height: 40px;
		text-align: right;
	}
	
	.add-update-preview .el-form-item /deep/ .el-form-item__content {
		margin-left: 120px;
	}
	.add-update-preview .el-form-item span.text {
		border: 1px solid #ddd;
		border-radius: 0px;
		padding: 5px 10px;
		color: #000000;
		display: block;
		width: 100%;
		font-size: 16px;
		line-height: 24px;
		height: auto;
	}
	
	.add-update-preview .el-input {
		width: 100%;
	}
	.add-update-preview .el-input /deep/ .el-input__inner {
		border: 1px dashed #ababab;
		border-radius: 0px;
		padding: 0 12px;
		color: #000000;
		width: 100%;
		font-size: 16px;
		height: 34px;
	}
	.add-update-preview .el-input /deep/ .el-input__inner[readonly="readonly"] {
		border: 1px dashed #ababab;
		border-radius: 0px;
		padding: 0 12px;
		color: #000000;
		width: 100%;
		font-size: 16px;
		height: 34px;
	}
	.add-update-preview .el-input-number {
		text-align: left;
		width: 100%;
	}
	.add-update-preview .el-input-number /deep/ .el-input__inner {
		text-align: left;
		border: 1px dashed #ababab;
		border-radius: 0px;
		padding: 0 12px;
		color: #000000;
		width: 100%;
		font-size: 16px;
		height: 34px;
	}
	.add-update-preview .el-input-number /deep/ .is-disabled .el-input__inner {
		text-align: left;
		border: 1px dashed #ababab;
		border-radius: 0px;
		padding: 0 12px;
		color: #000000;
		width: 100%;
		font-size: 16px;
		height: 34px;
	}
	.add-update-preview .el-input-number /deep/ .el-input-number__decrease {
		display: none;
	}
	.add-update-preview .el-input-number /deep/ .el-input-number__increase {
		display: none;
	}
	.add-update-preview .el-select {
		width: 100%;
	}
	.add-update-preview .el-select /deep/ .el-input__inner {
		border: 1px dashed #ababab;
		border-radius: 0px;
		padding: 0 12px;
		color: #000000;
		width: 100%;
		font-size: 16px;
		height: 34px;
	}
	.add-update-preview .el-select /deep/ .is-disabled .el-input__inner {
		border: 1px solid #E8E8E8;
		border-radius: 0px;
		padding: 0 12px;
		color: #000000;
		width: 100%;
		font-size: 16px;
		height: 34px;
	}
	.add-update-preview .el-date-editor {
		width: 100%;
	}
	.add-update-preview .el-date-editor /deep/ .el-input__inner {
		border: 1px solid #E8E8E8;
		border-radius: 0px;
		padding: 0 30px;
		color: #000000;
		width: 100%;
		font-size: 16px;
		height: 34px;
	}
	.add-update-preview .el-date-editor /deep/ .el-input__inner[readonly="readonly"] {
		border: 1px solid #E8E8E8;
		border-radius: 0px;
		padding: 0 30px;
		color: #000000;
		width: 100%;
		font-size: 16px;
		height: 34px;
	}
	.add-update-preview .viewBtn {
		border: 0px solid #ccc;
		cursor: pointer;
		border-radius: 0px;
		padding: 0 15px;
		margin: 0 20px 0 0;
		color: #666;
		background: #fff;
		width: auto;
		font-size: 15px;
		line-height: 34px;
		height: 34px;
		.iconfont {
			margin: 0 2px;
			color: #666;
			font-size: 16px;
			height: 34px;
		}
	}
	.add-update-preview .viewBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview .downBtn {
		border: 0px solid #ccc;
		cursor: pointer;
		border-radius: 0px;
		padding: 0 15px;
		margin: 0 20px 0 0;
		color: #666;
		background: #fff;
		width: auto;
		font-size: 15px;
		line-height: 34px;
		height: 34px;
		.iconfont {
			margin: 0 2px;
			color: #666;
			font-size: 16px;
			height: 34px;
		}
	}
	.add-update-preview .downBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview .unBtn {
		border: 0;
		cursor: not-allowed;
		border-radius: 4px;
		padding: 0 0px;
		margin: 0 20px 0 0;
		outline: none;
		color: #999;
		background: none;
		width: auto;
		font-size: 16px;
		line-height: 40px;
		height: 40px;
		.iconfont {
			margin: 0 2px;
			color: #fff;
			display: none;
			font-size: 14px;
			height: 34px;
		}
	}
	.add-update-preview .unBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview /deep/ .el-upload--picture-card {
		background: transparent;
		border: 0;
		border-radius: 0;
		width: auto;
		height: auto;
		line-height: initial;
		vertical-align: middle;
	}
	
	.add-update-preview /deep/ .upload .upload-img {
		border: 1px solid #ddd;
		cursor: pointer;
		border-radius: 0px;
		margin: 0 280px 0 0;
		color: #000000;
		background: #fff;
		width: 90px;
		font-size: 24px;
		line-height: 60px;
		text-align: center;
		height: 60px;
	}
	
	.add-update-preview /deep/ .el-upload-list .el-upload-list__item {
		border: 1px solid #ddd;
		cursor: pointer;
		border-radius: 0px;
		margin: 0 280px 0 0;
		color: #000000;
		background: #fff;
		width: 90px;
		font-size: 24px;
		line-height: 60px;
		text-align: center;
		height: 60px;
	}
	
	.add-update-preview /deep/ .el-upload .el-icon-plus {
		border: 1px solid #ddd;
		cursor: pointer;
		border-radius: 0px;
		margin: 0 280px 0 0;
		color: #000000;
		background: #fff;
		width: 90px;
		font-size: 24px;
		line-height: 60px;
		text-align: center;
		height: 60px;
	}
	.add-update-preview /deep/ .el-upload__tip {
		color: #666;
		font-size: 15px;
	}
	
	.add-update-preview .el-textarea /deep/ .el-textarea__inner {
		border: 1px solid #E8E8E8;
		border-radius: 0px;
		padding: 0 25px;
		color: #000000;
		width: 360px;
		font-size: 16px;
		line-height: 34px;
		height: 34px;
	}
	.add-update-preview .el-textarea /deep/ .el-textarea__inner[readonly="readonly"] {
				border: 1px solid #E8E8E8;
				border-radius: 0px;
				padding: 0 25px;
				color: #000000;
				width: 360px;
				font-size: 16px;
				line-height: 34px;
				height: 34px;
			}
	.add-update-preview .el-form-item.btn {
		padding: 0;
		margin: 20px 0 0;
		.btn1 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #6DB344;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn1:hover {
			opacity: 0.8;
		}
		.btn2 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #37A3D1;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 34px;
			}
		}
		.btn2:hover {
			opacity: 0.8;
		}
		.btn3 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #F5C5A9;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn3:hover {
			opacity: 0.8;
		}
		.btn4 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #ADADAC;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn4:hover {
			opacity: 0.8;
		}
		.btn5 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #FFBB33;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn5:hover {
			opacity: 0.8;
		}
	}
</style>
