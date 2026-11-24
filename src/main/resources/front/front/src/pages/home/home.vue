<template>
	<div class="home-preview">



		<!-- 关于我们 -->
		<div id="about" class="animate__animated">
			<div class="about_item">
				<div class="about_title_box">
					<div class="about_title">{{aboutUsDetail.title}}</div>
					<div class="about_subtitle">{{aboutUsDetail.subtitle}}</div>
				</div>
				<div class="about_img">
					<img :src="baseUrl + aboutUsDetail.picture1">
					<img :src="baseUrl + aboutUsDetail.picture2">
					<img :src="baseUrl + aboutUsDetail.picture3">
				</div>
				<div class="about_content ql-snow ql-editor" v-html="aboutUsDetail.content"></div>
				<div class="about_idea1" />
				<div class="about_idea2" />
				<div class="about_idea3" />
				<div class="about_idea4" />
				<div class="about_more" @click="toDetail('aboutusDetail',aboutUsDetail)">
					<span>查看更多</span>
					<span class="icon iconfont icon-gengduo1"></span>
				</div>
			</div>
		</div>
		<!-- 关于我们 -->

		<!-- 系统简介 -->
		<div id="system" class="animate__animated">
			<div class="system_item">
				<div class="system_title_box">
					<div class="system_title">{{systemIntroductionDetail.title}}</div>
					<div class="system_subtitle">{{systemIntroductionDetail.subtitle}}</div>
				</div>
				<div class="system_img">
					<img :src="baseUrl + systemIntroductionDetail.picture1">
					<img :src="baseUrl + systemIntroductionDetail.picture2">
					<img :src="baseUrl + systemIntroductionDetail.picture3">
				</div>
				<div class="system_content ql-snow ql-editor" v-html="systemIntroductionDetail.content"></div>
				<div class="system_idea1" />
				<div class="system_idea2" />
				<div class="system_idea3" />
				<div class="system_idea4" />
				<div class="system_more" @click="toDetail('systemintroDetail',systemIntroductionDetail)">
					<span>查看更多</span>
					<span class="icon iconfont icon-gengduo1"></span>
				</div>
			</div>
		</div>
		<!-- 系统简介 -->
		<!-- 新闻资讯 -->
		<div id="animate_newsnews" class="news animate__animated">
			<div class="news_title_box">
				<span class="news_title">通知公告</span>
				<span class="news_subhead">{{'news'.toUpperCase()}}</span>
			</div>
			<div class="list list15 index-pv1">
				<div class="tab">
					<div class="item" :class="newsIndex15 == 0 ? 'active' : ''" @click="newsIndexClick15(0)">
						<div>全部</div>
					</div>
					<template v-for="(item,index) in newsCategoryList15">
						<div v-if="index < 6" class="item" :class="newsIndex15 == index + 1 ? 'active' : ''" @click="newsIndexClick15(index+1)">
							<div>{{item.typename}}</div>
						</div>
					</template>
					<div class="more" @click="moreBtn('news')">
						<div>更多</div>
						<span class="icon iconfont icon-jiantou25"></span>
					</div>
				</div>
				<div v-if="newsList.length" class="content">
					<div @click="toDetail('newsDetail', newsList[0])" class="left list-item animation-box">
						<img :src="baseUrl + newsList[0].picture">
						<div class="name">{{newsList[0].title}}</div>
						<div class="desc">{{newsList[0].introduction}}</div>
						<div class="time_item">
							<span class="icon iconfont icon-shijian21"></span>
							<span class="label">发布时间：</span>
							<span class="text">{{newsList[0].addtime}}</span>
						</div>
						<div class="publisher_item">
							<span class="icon iconfont icon-geren16"></span>
							<span class="label">发布人：</span>
							<span class="text">{{newsList[0].name}}</span>
						</div>
						<div class="like_item">
							<span class="icon iconfont icon-zan10"></span>
							<span class="label">点赞数：</span>
							<span class="text">{{newsList[0].thumbsupnum}}</span>
						</div>
						<div class="collect_item">
							<span class="icon iconfont icon-shoucang10"></span>
							<span class="label">收藏量：</span>
							<span class="text">{{newsList[0].storeupnum}}</span>
						</div>
						<div class="view_item">
							<span class="icon iconfont icon-chakan9"></span>
							<span class="label">点击量：</span>
							<span class="text">{{newsList[0].clicknum}}</span>
						</div>
					</div>
					<div v-if="newsList.length > 1" class="right">
						<template v-for="(item,index) in newsList">
							<div v-if="index > 0" @click="toDetail('newsDetail', item)" class="list-item animation-box">
								<div class="date">
									<div class="day">{{item.addtime.split(" ")[0].split("-")[2]}}</div>
									<div class="year">{{item.addtime.split(" ")[0].split("-")[0] + '-' + item.addtime.split(" ")[0].split("-")[1]}}</div>
								</div>
								<div class="info">
									<div class="name">{{item.title}}</div>
									<div class="desc">{{item.introduction}}</div>
									<div class="publisher">
										<span class="icon iconfont icon-geren16"></span>
										<span class="label">发布人：</span>
										<span class="text">{{item.name}}</span>
									</div>
									<div class="like">
										<span class="icon iconfont icon-zan10"></span>
										<span class="label">点赞数：</span>
										<span class="text">{{item.thumbsupnum}}</span>
									</div>
									<div class="collect">
										<span class="icon iconfont icon-shoucang10"></span>
										<span class="label">收藏量：</span>
										<span class="text">{{item.storeupnum}}</span>
									</div>
									<div class="view">
										<span class="icon iconfont icon-chakan9"></span>
										<span class="label">点击量：</span>
										<span class="text">{{item.clicknum}}</span>
									</div>
								</div>
							</div>
						</template>
					</div>
				</div>
			</div>
			<div class="moreBtn" @click="moreBtn('news')">
				<span class="text">查看更多</span>
				<i class="icon iconfont icon-gengduo1"></i>
			</div>
		</div>
		<!-- 新闻资讯 -->
		<!-- 商品推荐 -->
		<div id="animate_recommendpiaowuxinxi" class="recommend animate__animated">
			<div class="recommend_title_box">
				<span class="recommend_title">票务信息推荐</span>
				<span class="recommend_subhead">{{'piaowuxinxi'.toUpperCase()}} RECOMMEND</span>
			</div>
			<div class="list list15 index-pv1">
				<div class="list-body-left">
					<template v-if="piaowuxinxiRecommend.length">
						<template v-for="item,index in piaowuxinxiRecommend">
							<div v-if="index == 0" @click="toDetail('piaowuxinxiDetail', item)" class="box1 list-item">
								<img :name="item.id" v-if="preHttp(item.xuanchuantu)" :src="item.xuanchuantu.split(',')[0]" alt="" />
								<img :name="item.id" v-else :src="baseUrl + (item.xuanchuantu?item.xuanchuantu.split(',')[0]:'')" alt="" />
								<div class="infoBox">
									<div class="name">{{item.mingcheng}}</div>
									<div class="name">{{item.mingxingxingming}}</div>
									<div class="price">￥{{item.price}}</div>
								</div>
							</div>
						</template>
					</template>
					<template v-if="piaowuxinxiRecommend.length > 1">
						<template v-for="item,index in piaowuxinxiRecommend">
							<div v-if="index == 1" @click="toDetail('piaowuxinxiDetail', item)" class="box2 list-item">
								<img :name="item.id" v-if="preHttp(item.xuanchuantu)" :src="item.xuanchuantu.split(',')[0]" alt="" />
								<img :name="item.id" v-else :src="baseUrl + (item.xuanchuantu?item.xuanchuantu.split(',')[0]:'')" alt="" />
								<div class="infoBox">
									<div class="name">{{item.mingcheng}}</div>
									<div class="name">{{item.mingxingxingming}}</div>
									<div class="price">￥{{item.price}}</div>
								</div>
							</div>
						</template>
					</template>
				</div>
				<div class="list-body-center">
					<template v-if="piaowuxinxiRecommend.length > 2">
						<template v-for="item,index in piaowuxinxiRecommend">
							<div v-if="index == 2" @click="toDetail('piaowuxinxiDetail', item)" class="box1 list-item">
								<img :name="item.id" v-if="preHttp(item.xuanchuantu)" :src="item.xuanchuantu.split(',')[0]" alt="" />
								<img :name="item.id" v-else :src="baseUrl + (item.xuanchuantu?item.xuanchuantu.split(',')[0]:'')" alt="" />
								<div class="infoBox">
									<div class="name">{{item.mingcheng}}</div>
									<div class="name">{{item.mingxingxingming}}</div>
									<div class="price">￥{{item.price}}</div>
								</div>
							</div>
						</template>
					</template>
					<div class="box2">
						<div class="name">{{piaowuxinxiRecommend[2].mingcheng}}</div>
						<div class="price">￥{{piaowuxinxiRecommend[2].price}}</div>
						<div class="desc ql-snow ql-editor" v-html="piaowuxinxiRecommend[2].mingxingzhenrong"></div>
					</div>
					<template v-if="piaowuxinxiRecommend.length > 3">
						<template v-for="item,index in piaowuxinxiRecommend">
							<div v-if="index == 3" @click="toDetail('piaowuxinxiDetail', item)" class="box3 list-item">
								<img :name="item.id" v-if="preHttp(item.xuanchuantu)" :src="item.xuanchuantu.split(',')[0]" alt="" />
								<img :name="item.id" v-else :src="baseUrl + (item.xuanchuantu?item.xuanchuantu.split(',')[0]:'')" alt="" />
								<div class="infoBox">
									<div class="name">{{item.mingcheng}}</div>
									<div class="name">{{item.mingxingxingming}}</div>
									<div class="price">￥{{item.price}}</div>
								</div>
							</div>
						</template>
					</template>
				</div>
				<div class="list-body-right">
					<template v-if="piaowuxinxiRecommend.length > 4">
						<template v-for="item,index in piaowuxinxiRecommend">
							<div v-if="index == 4" @click="toDetail('piaowuxinxiDetail', item)" class="box1 list-item">
								<img :name="item.id" v-if="preHttp(item.xuanchuantu)" :src="item.xuanchuantu.split(',')[0]" alt="" />
								<img :name="item.id" v-else :src="baseUrl + (item.xuanchuantu?item.xuanchuantu.split(',')[0]:'')" alt="" />
								<div class="infoBox">
									<div class="name">{{item.mingcheng}}</div>
									<div class="name">{{item.mingxingxingming}}</div>
									<div class="price">￥{{item.price}}</div>
								</div>
							</div>
						</template>
					</template>
					<template v-if="piaowuxinxiRecommend.length > 5">
						<template v-for="item,index in piaowuxinxiRecommend">
							<div v-if="index == 5" @click="toDetail('piaowuxinxiDetail', item)" class="box2 list-item">
								<img :name="item.id" v-if="preHttp(item.xuanchuantu)" :src="item.xuanchuantu.split(',')[0]" alt="" />
								<img :name="item.id" v-else :src="baseUrl + (item.xuanchuantu?item.xuanchuantu.split(',')[0]:'')" alt="" />
								<div class="infoBox">
									<div class="name">{{item.mingcheng}}</div>
									<div class="name">{{item.mingxingxingming}}</div>
									<div class="price">￥{{item.price}}</div>
								</div>
							</div>
						</template>
					</template>
				</div>
			</div>
			<div class="moreBtn" @click="moreBtn('piaowuxinxi')">
				<span class="text">查看更多</span>
				<i class="icon iconfont icon-gengduo1"></i>
			</div>
		</div>
		<!-- 商品推荐 -->
		<!-- 商品推荐 -->
		<div id="animate_recommendmingxingjianjie" class="recommend animate__animated">
			<div class="recommend_title_box">
				<span class="recommend_title">明星简介推荐</span>
				<span class="recommend_subhead">{{'mingxingjianjie'.toUpperCase()}} RECOMMEND</span>
			</div>
			<div class="list list15 index-pv1">
				<div class="list-body-left">
					<template v-if="mingxingjianjieRecommend.length">
						<template v-for="item,index in mingxingjianjieRecommend">
							<div v-if="index == 0" @click="toDetail('mingxingjianjieDetail', item)" class="box1 list-item">
								<img :name="item.id" v-if="preHttp(item.gerenzhaopian)" :src="item.gerenzhaopian.split(',')[0]" alt="" />
								<img :name="item.id" v-else :src="baseUrl + (item.gerenzhaopian?item.gerenzhaopian.split(',')[0]:'')" alt="" />
								<div class="infoBox">
									<div class="name">姓名:{{item.xingming}}</div>
									<div class="name">星座:{{item.xingzuo}}</div>
									<div class="name">{{item.aihao}}</div>
								</div>
							</div>
						</template>
					</template>
					<template v-if="mingxingjianjieRecommend.length > 1">
						<template v-for="item,index in mingxingjianjieRecommend">
							<div v-if="index == 1" @click="toDetail('mingxingjianjieDetail', item)" class="box2 list-item">
								<img :name="item.id" v-if="preHttp(item.gerenzhaopian)" :src="item.gerenzhaopian.split(',')[0]" alt="" />
								<img :name="item.id" v-else :src="baseUrl + (item.gerenzhaopian?item.gerenzhaopian.split(',')[0]:'')" alt="" />
								<div class="infoBox">
									<div class="name">姓名:{{item.xingming}}</div>
									<div class="name">星座:{{item.xingzuo}}</div>
									<div class="name">{{item.aihao}}</div>
								</div>
							</div>
						</template>
					</template>
				</div>
				<div class="list-body-center">
					<template v-if="mingxingjianjieRecommend.length > 2">
						<template v-for="item,index in mingxingjianjieRecommend">
							<div v-if="index == 2" @click="toDetail('mingxingjianjieDetail', item)" class="box1 list-item">
								<img :name="item.id" v-if="preHttp(item.gerenzhaopian)" :src="item.gerenzhaopian.split(',')[0]" alt="" />
								<img :name="item.id" v-else :src="baseUrl + (item.gerenzhaopian?item.gerenzhaopian.split(',')[0]:'')" alt="" />
								<div class="infoBox">
									<div class="name">姓名:{{item.xingming}}</div>
									<div class="name">星座:{{item.xingzuo}}</div>
									<div class="name">{{item.aihao}}</div>
								</div>
							</div>
						</template>
					</template>
					<div class="box2">
						<div class="name">{{mingxingjianjieRecommend[2].xingming}}</div>
						<div class="desc ql-snow ql-editor" v-html="mingxingjianjieRecommend[2].huodechengjiu"></div>
					</div>
					<template v-if="mingxingjianjieRecommend.length > 3">
						<template v-for="item,index in mingxingjianjieRecommend">
							<div v-if="index == 3" @click="toDetail('mingxingjianjieDetail', item)" class="box3 list-item">
								<img :name="item.id" v-if="preHttp(item.gerenzhaopian)" :src="item.gerenzhaopian.split(',')[0]" alt="" />
								<img :name="item.id" v-else :src="baseUrl + (item.gerenzhaopian?item.gerenzhaopian.split(',')[0]:'')" alt="" />
								<div class="infoBox">
									<div class="name">姓名:{{item.xingming}}</div>
									<div class="name">星座:{{item.xingzuo}}</div>
									<div class="name">{{item.aihao}}</div>
								</div>
							</div>
						</template>
					</template>
				</div>
				<div class="list-body-right">
					<template v-if="mingxingjianjieRecommend.length > 4">
						<template v-for="item,index in mingxingjianjieRecommend">
							<div v-if="index == 4" @click="toDetail('mingxingjianjieDetail', item)" class="box1 list-item">
								<img :name="item.id" v-if="preHttp(item.gerenzhaopian)" :src="item.gerenzhaopian.split(',')[0]" alt="" />
								<img :name="item.id" v-else :src="baseUrl + (item.gerenzhaopian?item.gerenzhaopian.split(',')[0]:'')" alt="" />
								<div class="infoBox">
									<div class="name">姓名:{{item.xingming}}</div>
									<div class="name">星座:{{item.xingzuo}}</div>
									<div class="name">{{item.aihao}}</div>
								</div>
							</div>
						</template>
					</template>
					<template v-if="mingxingjianjieRecommend.length > 5">
						<template v-for="item,index in mingxingjianjieRecommend">
							<div v-if="index == 5" @click="toDetail('mingxingjianjieDetail', item)" class="box2 list-item">
								<img :name="item.id" v-if="preHttp(item.gerenzhaopian)" :src="item.gerenzhaopian.split(',')[0]" alt="" />
								<img :name="item.id" v-else :src="baseUrl + (item.gerenzhaopian?item.gerenzhaopian.split(',')[0]:'')" alt="" />
								<div class="infoBox">
									<div class="name">姓名:{{item.xingming}}</div>
									<div class="name">星座:{{item.xingzuo}}</div>
									<div class="name">{{item.aihao}}</div>
								</div>
							</div>
						</template>
					</template>
				</div>
			</div>
			<div class="moreBtn" @click="moreBtn('mingxingjianjie')">
				<span class="text">查看更多</span>
				<i class="icon iconfont icon-gengduo1"></i>
			</div>
		</div>
		<!-- 商品推荐 -->
		<!-- 商品推荐 -->
		<div id="animate_recommendyingyuanhuodong" class="recommend animate__animated">
			<div class="recommend_title_box">
				<span class="recommend_title">应援活动推荐</span>
				<span class="recommend_subhead">{{'yingyuanhuodong'.toUpperCase()}} RECOMMEND</span>
			</div>
			<div class="list list15 index-pv1">
				<div class="list-body-left">
					<template v-if="yingyuanhuodongRecommend.length">
						<template v-for="item,index in yingyuanhuodongRecommend">
							<div v-if="index == 0" @click="toDetail('yingyuanhuodongDetail', item)" class="box1 list-item">
								<img :name="item.id" v-if="preHttp(item.huodongtupian)" :src="item.huodongtupian.split(',')[0]" alt="" />
								<img :name="item.id" v-else :src="baseUrl + (item.huodongtupian?item.huodongtupian.split(',')[0]:'')" alt="" />
								<div class="infoBox">
									<div class="name">{{item.huodongmingcheng}}</div>
								</div>
							</div>
						</template>
					</template>
					<template v-if="yingyuanhuodongRecommend.length > 1">
						<template v-for="item,index in yingyuanhuodongRecommend">
							<div v-if="index == 1" @click="toDetail('yingyuanhuodongDetail', item)" class="box2 list-item">
								<img :name="item.id" v-if="preHttp(item.huodongtupian)" :src="item.huodongtupian.split(',')[0]" alt="" />
								<img :name="item.id" v-else :src="baseUrl + (item.huodongtupian?item.huodongtupian.split(',')[0]:'')" alt="" />
								<div class="infoBox">
									<div class="name">{{item.huodongmingcheng}}</div>
								</div>
							</div>
						</template>
					</template>
				</div>
				<div class="list-body-center">
					<template v-if="yingyuanhuodongRecommend.length > 2">
						<template v-for="item,index in yingyuanhuodongRecommend">
							<div v-if="index == 2" @click="toDetail('yingyuanhuodongDetail', item)" class="box1 list-item">
								<img :name="item.id" v-if="preHttp(item.huodongtupian)" :src="item.huodongtupian.split(',')[0]" alt="" />
								<img :name="item.id" v-else :src="baseUrl + (item.huodongtupian?item.huodongtupian.split(',')[0]:'')" alt="" />
								<div class="infoBox">
									<div class="name">{{item.huodongmingcheng}}</div>
								</div>
							</div>
						</template>
					</template>
					<div class="box2">
						<div class="name">{{yingyuanhuodongRecommend[2].huodongmingcheng}}</div>
						<div class="desc ql-snow ql-editor" v-html="yingyuanhuodongRecommend[2].huodongneirong"></div>
					</div>
					<template v-if="yingyuanhuodongRecommend.length > 3">
						<template v-for="item,index in yingyuanhuodongRecommend">
							<div v-if="index == 3" @click="toDetail('yingyuanhuodongDetail', item)" class="box3 list-item">
								<img :name="item.id" v-if="preHttp(item.huodongtupian)" :src="item.huodongtupian.split(',')[0]" alt="" />
								<img :name="item.id" v-else :src="baseUrl + (item.huodongtupian?item.huodongtupian.split(',')[0]:'')" alt="" />
								<div class="infoBox">
									<div class="name">{{item.huodongmingcheng}}</div>
								</div>
							</div>
						</template>
					</template>
				</div>
				<div class="list-body-right">
					<template v-if="yingyuanhuodongRecommend.length > 4">
						<template v-for="item,index in yingyuanhuodongRecommend">
							<div v-if="index == 4" @click="toDetail('yingyuanhuodongDetail', item)" class="box1 list-item">
								<img :name="item.id" v-if="preHttp(item.huodongtupian)" :src="item.huodongtupian.split(',')[0]" alt="" />
								<img :name="item.id" v-else :src="baseUrl + (item.huodongtupian?item.huodongtupian.split(',')[0]:'')" alt="" />
								<div class="infoBox">
									<div class="name">{{item.huodongmingcheng}}</div>
								</div>
							</div>
						</template>
					</template>
					<template v-if="yingyuanhuodongRecommend.length > 5">
						<template v-for="item,index in yingyuanhuodongRecommend">
							<div v-if="index == 5" @click="toDetail('yingyuanhuodongDetail', item)" class="box2 list-item">
								<img :name="item.id" v-if="preHttp(item.huodongtupian)" :src="item.huodongtupian.split(',')[0]" alt="" />
								<img :name="item.id" v-else :src="baseUrl + (item.huodongtupian?item.huodongtupian.split(',')[0]:'')" alt="" />
								<div class="infoBox">
									<div class="name">{{item.huodongmingcheng}}</div>
								</div>
							</div>
						</template>
					</template>
				</div>
			</div>
			<div class="moreBtn" @click="moreBtn('yingyuanhuodong')">
				<span class="text">查看更多</span>
				<i class="icon iconfont icon-gengduo1"></i>
			</div>
		</div>
		<!-- 商品推荐 -->
		<!-- 特价商品 -->
		<div id="animate_listpiaowuxinxi" class="lists animate__animated">
			<div class="list_title_box">
				<span class="list_title">票务信息展示</span>
				<span class="list_subhead">{{'piaowuxinxi'.toUpperCase()}} SHOW</span>
			</div>
			<div v-if="piaowuxinxiList.length" class="list list12 index-pv1 list12piaowuxinxi">
				<div class="list-body">
					<div class="item list-item animation-box" v-for="item,index in piaowuxinxiList" :key="index" @click="toDetail('piaowuxinxiDetail', item)">
						<img :name="item.id" v-if="preHttp(item.xuanchuantu)" :src="item.xuanchuantu.split(',')[0]" alt="" />
						<img :name="item.id" v-else :src="baseUrl + (item.xuanchuantu?item.xuanchuantu.split(',')[0]:'')" alt="" />
						<div class="name">{{item.mingcheng}}</div>
						<div class="name">{{item.mingxingxingming}}</div>
						<div class="price">￥{{item.price}}</div>
					</div>
				</div>
			</div>
			<div class="moreBtn" @click="moreBtn('piaowuxinxi')">
				<span class="text">查看更多</span>
				<i class="icon iconfont icon-gengduo1"></i>
			</div>
	

		</div>
		<!-- 特价商品 -->
	</div>
</template>

<script>
import 'animate.css'
import Swiper from "swiper";

	export default {
		//数据集合
		data() {
			return {
				baseUrl: '',
				aboutUsDetail: {},
				systemIntroductionDetail: {},
				newsList: [],
				piaowuxinxiRecommend: [],
				mingxingjianjieRecommend: [],
				yingyuanhuodongRecommend: [],

				piaowuxinxiList: [],

				newsIndex15: 0,
				newsCategoryList15: [],



			}
		},
		created() {
			this.baseUrl = this.$config.baseUrl;
			this.getNewsList();
			this.getNewsCategory()
			this.getAboutUs();
			this.getSystemIntroduction();
			this.getList();
		},
		mounted() {
			window.addEventListener('scroll', this.handleScroll)
			setTimeout(()=>{
				this.handleScroll()
			},100)
			
			this.swiperChanges()
		},
		beforeDestroy() {
			window.removeEventListener('scroll', this.handleScroll)
		},
		//方法集合
		methods: {
			swiperChanges() {
				setTimeout(()=>{
				},750)
			},
			newsIndexClick15(index) {
				this.newsIndex15 = index
				this.getNewsList()
			},
			getNewsCategory() {
				this.$http.get('newstype/list', {}).then(res => {
					if (res.data.code == 0) {
						this.newsCategoryList15 = res.data.data
					}
				});
			},

			listIndexClick12(index, name) {
				this['listIndex12' + name] = index
				this.getList()
				
				document.querySelectorAll('.lists .list12' + name + ' .list .item').forEach(el => {
				  el.classList.remove("active")
				})
				setTimeout(() => {
				  document.querySelectorAll('.lists .list12' + name + ' .list .item').forEach(el => {
					el.classList.add("active")
				  })
				}, 1);
			},

			handleScroll() {
				let arr = [
					{id:'about',css:'animate__bounce'},
					{id:'system',css:'animate__bounce'},
					{id:'animate_recommendpiaowuxinxi',css:'animate__bounce'},
					{id:'animate_listpiaowuxinxi',css:'animate__bounce'},
					{id:'animate_recommendmingxingjianjie',css:'animate__bounce'},
					{id:'animate_recommendyingyuanhuodong',css:'animate__bounce'},
					{id:'animate_newsnews',css:'animate__bounce'},
				]
			
				for (let i in arr) {
					let doc = document.getElementById(arr[i].id)
					if (doc) {
						let top = doc.offsetTop
						let win_top = window.innerHeight + window.pageYOffset
						// console.log(top,win_top)
						if (win_top > top && doc.classList.value.indexOf(arr[i].css) < 0) {
							// console.log(doc)
							doc.classList.add(arr[i].css)
						}
					}
				}
			},
			preHttp(str) {
				return str && str.substr(0,4)=='http';
			},
			preHttp2(str) {
				return str && str.split(',w').length>1;
			},
			getAboutUs() {
				this.$http.get('aboutus/detail/1', {}).then(res => {
					if(res.data.code == 0) {
						this.aboutUsDetail = res.data.data;
					}
				})
			},
			getSystemIntroduction() {
				this.$http.get('systemintro/detail/1', {}).then(res => {
					if(res.data.code == 0) {
						this.systemIntroductionDetail = res.data.data;
					}
				})
			},
			getNewsList() {
				let data = {
					page: 1,
					limit: 6,
					sort: 'addtime',
					order: 'desc'
				}
				if(this.newsIndex15!=0) {
					data['typename'] = this.newsCategoryList15[this.newsIndex15 - 1].typename
				}
				this.$http.get('news/list', {params: data}).then(res => {
					if (res.data.code == 0) {
						this.newsList = res.data.data.list;
					
					}
				});
			},
			getList() {
				let autoSortUrl = "";
				let data = {}
				autoSortUrl = "piaowuxinxi/autoSort";
				if(localStorage.getItem('frontToken')) {
					autoSortUrl = "piaowuxinxi/autoSort2";
				}
				data = {
					page: 1,
					limit: 6,
				}
				this.$http.get(autoSortUrl, {params: data}).then(res => {
					if (res.data.code == 0) {
						this.piaowuxinxiRecommend = res.data.data.list;
					}
				});
				autoSortUrl = "mingxingjianjie/autoSort";
				if(localStorage.getItem('frontToken')) {
					autoSortUrl = "mingxingjianjie/autoSort2";
				}
				data = {
					page: 1,
					limit: 6,
				}
				this.$http.get(autoSortUrl, {params: data}).then(res => {
					if (res.data.code == 0) {
						this.mingxingjianjieRecommend = res.data.data.list;
					}
				});
				autoSortUrl = "yingyuanhuodong/autoSort";
				if(localStorage.getItem('frontToken')) {
					autoSortUrl = "yingyuanhuodong/autoSort2";
				}
				data = {
					page: 1,
					limit: 6,
				}
				this.$http.get(autoSortUrl, {params: data}).then(res => {
					if (res.data.code == 0) {
						this.yingyuanhuodongRecommend = res.data.data.list;
					}
				});
			
				data = {
					page: 1,
					limit: 8,
				}
				data['sort'] = 'shijian'
				data['order']= 'desc'

				this.$http.get('piaowuxinxi/list', {params: data}).then(res => {
					if (res.data.code == 0) {
						this.piaowuxinxiList = res.data.data.list;
					}
				});
			},
			toDetail(path, item) {
				this.$router.push({path: '/index/' + path, query: {id: item.id}});
			},
			moreBtn(path) {
				this.$router.push({path: '/index/' + path});
			}
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.home-preview {
		margin: 0;
		flex-direction: column;
		display: flex;
		width: 100%;
		#about {
			padding: 40px 0;
			box-shadow: 0 1px 6px rgba(255, 255, 255, .3);
			margin: 0;
			background: url(http://codegen.caihongy.cn/20241212/3c48b97f8218434ea80db07b4097148c.webp) no-repeat center;
			width: 100%;
			position: relative;
			height: auto;
			order: 4;
			.about_item {
				margin: 0 auto;
				display: flex;
				width: 1300px;
				flex-wrap: wrap;
				.about_title_box {
					padding: 0 0 0 180px;
					margin: 0 0 40px 0 ;
					background: url(http://codegen.caihongy.cn/20241210/a35f0a1a1ae54a6599c8a9c34f6344e8.webp) no-repeat center / 100% 100%;
					width: 100%;
					line-height: 100px;
					text-align: left;
					height: 100px;
					order: 1;
					.about_title {
						color: #000000;
						font-weight: 700;
						font-size: 36px;
					}
					.about_subtitle {
						margin: 0 0 10px;
						color: #999;
						display: none;
						width: 100%;
						font-size: 20px;
						line-height: 1.5;
						text-align: center;
					}
				}
				.about_img {
					padding: 0;
					display: flex;
					width: 46%;
					justify-content: space-between;
					position: relative;
					flex-wrap: wrap;
					height: 280px;
					order: 3;
					img:nth-child(1) {
						border-radius: 0;
						object-fit: cover;
						width: 48%;
						height: 100%;
					}
					img:nth-child(2) {
						object-fit: cover;
						display: flex;
						width: 48%;
						height: 100%;
					}
					img:nth-child(3) {
						border-radius: 50%;
						bottom: 0;
						object-fit: cover;
						display: none;
						width: 160px;
						position: absolute;
						right: 80px;
						height: 160px;
					}
				}
				.about_content {
					padding: 0;
					margin: 0;
					color: rgb(102, 102, 102);
					width: 54%;
					font-size: 16px;
					line-height: 2;
					order: 2;
				}
				.about_idea1 {
					display: none;
					width: 285px;
					height: 100px;
				}
				.about_idea2 {
					display: none;
					width: 285px;
					height: 100px;
				}
				.about_idea3 {
					display: none;
					width: 285px;
					height: 100px;
				}
				.about_idea4 {
					display: none;
					width: 285px;
					height: 100px;
				}
				.about_more {
					border: 0;
					cursor: pointer;
					padding: 0 0 0 40px;
					margin: 10px auto;
					display: block;
					line-height: 60px;
					right: calc(50% - 620px);
					top: 50px;
					background: url(http://codegen.caihongy.cn/20241210/54e0395d6e624dbcb8ec2ba83b2ff14b.webp);
					width: 190px;
					position: absolute;
					text-align: left;
					height: 60px;
					span:nth-child(1) {
						color: #FFFFFF;
						font-size: 14px;
					}
					span:nth-child(2) {
						color: #f5f5f5;
						font-size: 12px;
					}
				}
				.about_more:hover {
				}
			}
		}
		#system {
			padding: 30px calc(50% - 650px);
			box-shadow: 0 1px 6px rgba(255, 255, 255, .3);
			margin: 0;
			background: url(http://codegen.caihongy.cn/20241214/faf73f99acf347b599898c85bd33df26.webp) no-repeat center / 100% 100%;
			width: 100%;
			position: relative;
			height: auto;
			order: 7;
			.system_item {
				display: flex;
				width: 100%;
				justify-content: space-between;
				flex-wrap: wrap;
				.system_title_box {
					padding: 0 0 0 180px;
					margin: 10px auto 40px ;
					background: url(http://codegen.caihongy.cn/20241210/a35f0a1a1ae54a6599c8a9c34f6344e8.webp) no-repeat center / 100% 100%;
					width: 100%;
					line-height: 100px;
					text-align: left;
					height: 100px;
					.system_title {
						color: #000000;
						font-weight: 700;
						font-size: 36px;
					}
					.system_subtitle {
						margin: 0 0 10px;
						color: #999;
						display: none;
						width: 100%;
						font-size: 20px;
						line-height: 1.5;
						text-align: center;
					}
				}
				.system_img {
					padding: 0 10px;
					flex-direction: column;
					display: flex;
					gap: 20px;
					width: 400px;
					flex-wrap: wrap;
					height: auto;
					img:nth-child(1) {
						border: 2px solid;
						border-radius: 10px;
						padding: 10px;
						margin: 0;
						flex: none;
						border-image: linear-gradient(316deg, rgba(66.00000366568565, 61.00000016391277, 210.00000268220901, 1), rgba(172.44500666856766, 209.82588082551956, 249.4240728020668, 1), rgba(174.00000482797623, 212.00000256299973, 250.00000029802322, 1)) 2 2;
						object-fit: cover;
						display: block;
						width: 100%;
						height: 200px;
					}
					img:nth-child(2) {
						border: 2px solid;
						border-radius: 10px;
						padding: 10px;
						margin: 0;
						flex: none;
						border-image: linear-gradient(316deg, rgba(66.00000366568565, 61.00000016391277, 210.00000268220901, 1), rgba(172.44500666856766, 209.82588082551956, 249.4240728020668, 1), rgba(174.00000482797623, 212.00000256299973, 250.00000029802322, 1)) 2 2;
						object-fit: cover;
						display: block;
						width: 100%;
						height: 200px;
					}
					img:nth-child(3) {
						margin: 0 10px;
						flex: 1;
						object-fit: cover;
						display: none;
						height: 120px;
					}
				}
				.system_content {
					padding: 20px 30px 20px 74px;
					margin: 0 0 10px 0;
					color: rgb(102, 102, 102);
					background: url(http://codegen.caihongy.cn/20241214/b0f795a7862144a59df7174e4602cc9c.webp) no-repeat left / 100% 100%;
					width: 860px;
					font-size: 16px;
					line-height: 2;
					height: auto;
				}
				.system_idea1 {
					background: url(http://codegen.caihongy.cn/20201114/7856ba26477849ea828f481fa2773a95.jpg) 0% 0% / cover no-repeat;
					display: none;
					width: 285px;
					height: 100px;
				}
				.system_idea2 {
					background: url(http://codegen.caihongy.cn/20201114/7856ba26477849ea828f481fa2773a95.jpg) 0% 0% / cover no-repeat;
					display: none;
					width: 285px;
					height: 100px;
				}
				.system_idea3 {
					background: url(http://codegen.caihongy.cn/20201114/7856ba26477849ea828f481fa2773a95.jpg) 0% 0% / cover no-repeat;
					display: none;
					width: 285px;
					height: 100px;
				}
				.system_idea4 {
					background: url(http://codegen.caihongy.cn/20201114/7856ba26477849ea828f481fa2773a95.jpg) 0% 0% / cover no-repeat;
					display: none;
					width: 285px;
					height: 100px;
				}
				.system_more {
					border: 0;
					cursor: pointer;
					padding: 0 0 0 40px;
					margin: 10px auto;
					display: block;
					line-height: 60px;
					right: calc(50% - 620px);
					top: 60px;
					background: url(http://codegen.caihongy.cn/20241210/54e0395d6e624dbcb8ec2ba83b2ff14b.webp);
					width: 190px;
					position: absolute;
					text-align: left;
					height: 60px;
					span:nth-child(1) {
						color: #FFFFFF;
						font-size: 14px;
					}
					span:nth-child(2) {
						color: #f5f5f5;
						font-size: 12px;
					}
				}
				.system_more:hover {
					opacity: 0.5;
				}
			}
		}
		.news {
			padding: 40px 0;
			margin: 0;
			background: linear-gradient( 103deg, #EFEFFD 0%, #FFFFFF 100%);
			position: relative;
			order: 2;
			.news_title_box {
				padding: 0 0 0 180px;
				margin: 10px auto;
				background: url(http://codegen.caihongy.cn/20241210/a35f0a1a1ae54a6599c8a9c34f6344e8.webp) no-repeat center / 100% 100%;
				width: 1300px;
				line-height: 100px;
				text-align: left;
				height: 100px;
				.news_title {
					color: #000000;
					font-weight: 700;
					font-size: 36px;
				}
				.news_subhead {
					margin: 0 0 10px;
					color: #999;
					display: none;
					width: 100%;
					font-size: 20px;
					line-height: 1.5;
					text-align: center;
				}
			}
			.index-pv1 .animation-box:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 10px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
			}
			.index-pv1 .animation-box img:hover {
				transform: rotate(0deg) scale(1.1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
			.list15 {
				padding: 10px;
				margin: 0 auto;
				background: none;
				width: 1300px;
				height: auto;
				.tab {
					display: none;
					gap: 20px;
					width: 100%;
					justify-content: flex-start;
					height: auto;
					.item {
						cursor: pointer;
						border-radius: 90px;
						padding: 0 10px;
						margin: 0;
						color: #848484;
						background: #FFFFFF;
						display: flex;
						font-size: 18px;
						justify-content: center;
						align-items: center;
						min-width: 120px;
						height: 80px;
					}
					
					.item:hover {
						color: #FFF;
						background: linear-gradient( 118deg, #B1D8FB 0%, #423CD2 100%);
					}
					
					.item.active {
						color: #FFF;
						background: linear-gradient( 118deg, #B1D8FB 0%, #423CD2 100%);
					}
					.more {
						cursor: pointer;
						border-radius: 6px;
						padding: 5px 10px;
						margin: 0 10px;
						color: #fff;
						background: #333;
						display: none;
						line-height: 44px;
						align-items: center;
					}
					
					.more:hover {
						background: red;
					}
				}
				.content {
					margin: 20px 0 0;
					display: flex;
					width: 100%;
					justify-content: space-between;
					height: auto;
					.left.list-item {
						border: 2px solid #423CD2;
						border-radius: 20px;
						padding: 20px;
						margin: 0;
						background: #fff;
						border-image: none;
						width: 450px;
						height: 670px;
						img {
							border-radius: 30px;
							object-fit: cover;
							display: block;
							width: 100%;
							height: 400px;
						}
						.name {
							padding: 0;
							margin: 10px 0 0;
							overflow: hidden;
							color: #423CD2;
							white-space: nowrap;
							font-weight: 700;
							width: 100%;
							font-size: 16px;
							line-height: 1.5;
							text-overflow: ellipsis;
						}
						.desc {
							padding: 0;
							max-height: 72px;
							margin: 10px 0 0;
							overflow: hidden;
							color: #A1A1A1;
							width: 100%;
							font-size: 14px;
							line-height: 24px;
						}
						.time_item {
							padding: 0;
							margin: 4px 0 0;
							color: #6A96D6;
							display: inline-block;
							width: 100%;
							font-size: 14px;
							text-align: left;
							order: 99;
							.icon {
								margin: 0 2px 0 0;
								color: inherit;
								font-size: inherit;
								line-height: 1.5;
							}
							.label {
								color: inherit;
								font-size: inherit;
								line-height: 1.5;
							}
							.text {
								color: inherit;
								font-size: inherit;
								line-height: 1.5;
							}
						}
						.publisher_item {
							padding: 0;
							margin: 6px 0 0;
							color: #C88BFB;
							display: inline-block;
							width: 49%;
							font-size: 14px;
							.icon {
								margin: 0 2px 0 0;
								color: inherit;
								font-size: inherit;
								line-height: 1.5;
							}
							.label {
								color: inherit;
								font-size: inherit;
								line-height: 1.5;
							}
							.text {
								color: inherit;
								font-size: inherit;
								line-height: 1.5;
							}
						}
						.like_item {
							padding: 0;
							margin: 6px 0 0;
							color: #3CB9D2;
							display: inline-block;
							width: 49%;
							font-size: 14px;
							text-align: left;
							.icon {
								margin: 0 2px 0 0;
								color: inherit;
								font-size: inherit;
								line-height: 1.5;
							}
							.label {
								color: inherit;
								font-size: inherit;
								line-height: 1.5;
							}
							.text {
								color: inherit;
								font-size: inherit;
								line-height: 1.5;
							}
						}
						.collect_item {
							padding: 0;
							margin: 6px 0 0;
							color: #FF9C24;
							display: inline-block;
							width: 49%;
							font-size: 14px;
							.icon {
								margin: 0 2px 0 0;
								color: inherit;
								font-size: inherit;
								line-height: 1.5;
							}
							.label {
								color: inherit;
								font-size: inherit;
								line-height: 1.5;
							}
							.text {
								color: inherit;
								font-size: inherit;
								line-height: 1.5;
							}
						}
						.view_item {
							padding: 0;
							margin: 6px 0 0;
							color: #3CB9D2;
							display: inline-block;
							width: 49%;
							font-size: 14px;
							.icon {
								margin: 0 2px 0 0;
								color: inherit;
								font-size: inherit;
								line-height: 1.5;
							}
							.label {
								color: inherit;
								font-size: inherit;
								line-height: 1.5;
							}
							.text {
								color: inherit;
								font-size: inherit;
								line-height: 1.5;
							}
						}
					}
					.right {
						display: flex;
						width: 780px;
						justify-content: space-between;
						flex-wrap: wrap;
						height: auto;
						.list-item  {
							border: 2px solid #B8B8B8;
							border-radius: 20px;
							padding: 5px 0;
							margin: 10px 0;
							background: #FFFFFF;
							display: flex;
							width: 380px;
							justify-content: space-between;
							align-items: center;
							height: auto;
							.date  {
								border-radius: 50%;
								margin: 0 20px;
								flex-direction: column;
								background: linear-gradient( 303deg, #423CD2 0%, #B0D7FB 100%);
								flex: none;
								display: flex;
								width: 110px;
								justify-content: center;
								align-items: center;
								height: 110px;
								.day  {
									color: #FFF;
									width: auto;
									font-size: 24px;
									line-height: 40px;
								}
								.year  {
									color: #FFF;
									width: auto;
									font-size: 14px;
									line-height: 40px;
								}
							}
							.info {
								flex: 1;
								border-image: linear-gradient( 180deg, #FFFFFF 0%, #433DD2 48%, #FFFFFF 100%) 1;
								width: 0;
								border-width: 0 0 0 2px;
								border-style: solid;
								.name  {
									padding: 0 10px;
									overflow: hidden;
									color: #000000;
									white-space: nowrap;
									font-weight: 700;
									width: 100%;
									font-size: 14px;
									line-height: 1.5;
									text-overflow: ellipsis;
								}
								.desc  {
									padding: 0 10px;
									margin: 10px 0 0;
									overflow: hidden;
									color: #A1A1A1;
									white-space: nowrap;
									width: 100%;
									font-size: 14px;
									line-height: 1.5;
									text-overflow: ellipsis;
								}
								.publisher {
									padding: 0 10px;
									margin: 10px 0 0;
									color: #C88BFB;
									display: inline-block;
									width: 100%;
									.icon {
										margin: 0 2px 0 0;
										color: inherit;
										font-size: 12px;
										line-height: 1.5;
									}
									.label {
										color: inherit;
										font-size: 12px;
										line-height: 1.5;
									}
									.text {
										color: inherit;
										font-size: 12px;
										line-height: 1.5;
									}
								}
								.like {
									padding: 0 10px;
									margin: 10px 0 0;
									color: #3CB9D2;
									display: inline-block;
									width: 100%;
									.icon {
										margin: 0 2px 0 0;
										color: inherit;
										font-size: 12px;
										line-height: 1.5;
									}
									.label {
										color: inherit;
										font-size: 12px;
										line-height: 1.5;
									}
									.text {
										color: inherit;
										font-size: 12px;
										line-height: 1.5;
									}
								}
								.collect {
									padding: 0 10px;
									margin: 10px 0 0;
									color: #FF9C24;
									display: inline-block;
									width: 100%;
									.icon {
										margin: 0 2px 0 0;
										color: inherit;
										font-size: 12px;
										line-height: 1.5;
									}
									.label {
										color: inherit;
										font-size: 12px;
										line-height: 1.5;
									}
									.text {
										color: inherit;
										font-size: 12px;
										line-height: 1.5;
									}
								}
								.view {
									padding: 0 10px;
									margin: 10px 0 0;
									color: #423CD2;
									display: inline-block;
									.icon {
										margin: 0 2px 0 0;
										color: inherit;
										font-size: 12px;
										line-height: 1.5;
									}
									.label {
										color: inherit;
										font-size: 12px;
										line-height: 1.5;
									}
									.text {
										color: inherit;
										font-size: 12px;
										line-height: 1.5;
									}
								}
							}
						}
						.list-item:hover  {
							.date {
								.day {
									color: #FFF;
									border-color: #FFF;
								}
								.year {
									color: #FFF;
								}
							}
							.info {
								.name  {
								}
								.desc  {
								}
								.publisher {
									.icon {
									}
									.label {
									}
									.text {
									}
								}
								.like {
									.icon {
									}
									.label {
									}
									.text {
									}
								}
								.collect {
									.icon {
									}
									.label {
									}
									.text {
									}
								}
								.view {
									.icon {
									}
									.label {
									}
									.text {
									}
								}
							}
						}
					}
				}
			}
			.moreBtn {
				border: 0;
				cursor: pointer;
				padding: 0 0 0 40px;
				margin: 10px auto;
				display: block;
				line-height: 60px;
				right: calc(50% - 620px);
				top: 60px;
				background: url(http://codegen.caihongy.cn/20241210/54e0395d6e624dbcb8ec2ba83b2ff14b.webp);
				width: 190px;
				position: absolute;
				text-align: left;
				height: 60px;
				.text {
					color: #FFFFFF;
					font-size: 14px;
				}
				.icon {
					color: #f5f5f5;
					font-size: 12px;
				}
			}
		}
		.recommend {
			padding: 40px 0;
			margin: 0;
			background: url(http://codegen.caihongy.cn/20241212/bb4df976a63843a48b9af8b142771f6b.webp) no-repeat center / cover;
			position: relative;
			order: 3;
			.recommend_title_box {
				padding: 0 0 0 180px;
				margin: 10px auto;
				background: url(http://codegen.caihongy.cn/20241210/a35f0a1a1ae54a6599c8a9c34f6344e8.webp) no-repeat center / 100% 100%;
				width: 1300px;
				line-height: 100px;
				text-align: left;
				height: 100px;
				.recommend_title {
					color: #000000;
					font-weight: 700;
					font-size: 36px;
				}
				.recommend_subhead {
					margin: 0 0 10px;
					color: #999;
					display: none;
					width: 100%;
					font-size: 20px;
					line-height: 1.5;
					text-align: center;
				}
			}
			.index-pv1 .animation-box {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				z-index: initial;
			}
			
			.index-pv1 .animation-box:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 10px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
			}
			
			.index-pv1 .animation-box img {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			}
			
			.index-pv1 .animation-box img:hover {
				transform: rotate(0deg) scale(1.1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
			.list15 {
				padding: 0;
				margin: 0 auto;
				display: flex;
				width: 1300px;
				justify-content: center;
				align-items: center;
				height: auto;
				.list-body-left {
					width: 32%;
					height: auto;
					.box1 {
						border: 2px solid transparent;
						border-radius: 10px;
						margin: 10px 0;
						overflow: hidden;
						background: none;
						width: 100%;
						position: relative;
						transition: all .3s;
						height: auto;
						img {
							border-radius: 10px;
							object-fit: cover;
							display: block;
							width: 100% ;
							transition: all .3s;
							height: 180px;
						}
						.infoBox {
							flex-direction: column;
							left: 0;
							background: rgba(0,0,0,.3);
							bottom: 0;
							display: flex;
							width: 100%;
							justify-content: center;
							align-items: center;
							position: absolute;
							opacity: 0;
							transition: all .3s;
							height: 100%;
							.name {
								color: #fff;
								font-size: 16px;
								line-height: 24px;
							}
							.price {
								padding: 10px;
								color: #f00;
								font-size: 13px;
								line-height: 1.5;
							}
						}
					}
					.box1:hover {
						border: 1px solid #ff0;
						img {
							transform: scale(1.2);
						}
						.infoBox {
							opacity: 1;
						}
					}
					.box2 {
						border-radius: 10px;
						margin: 10px 0;
						overflow: hidden;
						background: none;
						width: 100%;
						position: relative;
						transition: all .3s;
						height: auto;
						img {
							border-radius: 10px;
							object-fit: cover;
							display: block;
							width: 100% ;
							transition: all .3s;
							height: 180px;
						}
						.infoBox {
							flex-direction: column;
							left: 0;
							background: rgba(0,0,0,.3);
							bottom: 0;
							display: flex;
							width: 100%;
							justify-content: center;
							align-items: center;
							position: absolute;
							opacity: 0;
							transition: all .3s;
							height: 100%;
							.name {
								color: #fff;
								font-size: 16px;
								line-height: 24px;
							}
							.price {
								padding: 10px;
								color: #f00;
								font-size: 13px;
								line-height: 1.5;
							}
						}
					}
					.box2:hover {
						border: 1px solid #ff0;
						img {
							transform: scale(1.2);
						}
						.infoBox {
							opacity: 1;
						}
					}
				}
				.list-body-center {
					margin: 0 2%;
					width: 32%;
					height: auto;
					.box1 {
						border: 1px solid transparent;
						border-radius: 10px;
						margin: 10px 0;
						overflow: hidden;
						background: #fff;
						width: 100%;
						position: relative;
						transition: all .3s;
						height: auto;
						img {
							object-fit: cover;
							display: block;
							width: 100% ;
							transition: all .3s;
							height: 180px;
						}
						.infoBox {
							flex-direction: column;
							left: 0;
							background: rgba(0,0,0,.3);
							bottom: 0;
							display: flex;
							width: 100%;
							justify-content: center;
							align-items: center;
							position: absolute;
							opacity: 0;
							transition: all .3s;
							height: 100%;
							.name {
								color: #fff;
								font-size: 16px;
								line-height: 24px;
							}
							.price {
								padding: 10px;
								color: #f00;
								font-size: 13px;
								line-height: 1.5;
							}
						}
					}
					.box1:hover {
						border: 1px solid #ff0;
						img {
							transform: scale(1.2);
						}
						.infoBox {
							opacity: 1;
						}
					}
					.box2 {
						border: 5px solid #423DD2;
						border-radius: 50%;
						padding: 30px;
						margin: 10px 0;
						background-clip: content-box;
						flex-direction: column;
						background: #423DD2;
						display: flex;
						width: 100%;
						justify-content: center;
						align-items: center;
						height: 432px;
						.name {
							color: #fff;
							font-size: 16px;
							line-height: 24px;
						}
						.price {
							padding: 10px;
							color: red;
							font-size: 24px;
							line-height: 1.5;
						}
						.desc {
							color: #fff;
							display: none;
							font-size: 16px;
							line-height: 1.5;
						}
					}
					.box3 {
						border: 1px solid transparent;
						border-radius: 10px;
						margin: 10px 0;
						overflow: hidden;
						background: #fff;
						width: 100%;
						position: relative;
						transition: all .3s;
						height: auto;
						img {
							object-fit: cover;
							display: block;
							width: 100% ;
							transition: all .3s;
							height: 180px;
						}
						.infoBox {
							flex-direction: column;
							left: 0;
							background: rgba(0,0,0,.3);
							bottom: 0;
							display: flex;
							width: 100%;
							justify-content: center;
							align-items: center;
							position: absolute;
							opacity: 0;
							transition: all .3s;
							height: 100%;
							.name {
								color: #fff;
								font-size: 16px;
								line-height: 24px;
							}
							.price {
								padding: 10px;
								color: #f00;
								font-size: 13px;
								line-height: 1.5;
							}
						}
					}
					.box3:hover {
						border: 1px solid #ff0;
						img {
							transform: scale(1.2);
						}
						.infoBox {
							opacity: 1;
						}
					}
				}
				.list-body-right {
					width: 32%;
					height: auto;
					.box1 {
						border: 1px solid transparent;
						border-radius: 10px;
						margin: 10px 0;
						overflow: hidden;
						background: #fff;
						width: 100%;
						position: relative;
						transition: all .3s;
						height: auto;
						img {
							object-fit: cover;
							display: block;
							width: 100% ;
							transition: all .3s;
							height: 180px;
						}
						.infoBox {
							flex-direction: column;
							left: 0;
							background: rgba(0,0,0,.3);
							bottom: 0;
							display: flex;
							width: 100%;
							justify-content: center;
							align-items: center;
							position: absolute;
							opacity: 0;
							transition: all .3s;
							height: 100%;
							.name {
								color: #fff;
								font-size: 16px;
								line-height: 24px;
							}
							.price {
								padding: 10px;
								color: #f00;
								font-size: 13px;
								line-height: 1.5;
							}
						}
					}
					.box1:hover {
						border: 1px solid #ff0;
						img {
							transform: scale(1.2);
						}
						.infoBox {
							opacity: 1;
						}
					}
					.box2 {
						border: 1px solid transparent;
						border-radius: 10px;
						margin: 10px 0;
						overflow: hidden;
						background: #fff;
						width: 100%;
						position: relative;
						transition: all .3s;
						height: auto;
						img {
							object-fit: cover;
							display: block;
							width: 100% ;
							transition: all .3s;
							height: 180px;
						}
						.infoBox {
							flex-direction: column;
							left: 0;
							background: rgba(0,0,0,.3);
							bottom: 0;
							display: flex;
							width: 100%;
							justify-content: center;
							align-items: center;
							position: absolute;
							opacity: 0;
							transition: all .3s;
							height: 100%;
							.name {
								color: #fff;
								font-size: 16px;
								line-height: 24px;
							}
							.price {
								padding: 10px;
								color: #f00;
								font-size: 13px;
								line-height: 1.5;
							}
						}
					}
					.box2:hover {
						border: 1px solid #ff0;
						img {
							transform: scale(1.2);
						}
						.infoBox {
							opacity: 1;
						}
					}
				}
			}
			.moreBtn {
				border: 0;
				cursor: pointer;
				padding: 0 0 0 40px;
				margin: 10px auto;
				display: block;
				line-height: 60px;
				right: calc(50% - 620px);
				top: 60px;
				background: url(http://codegen.caihongy.cn/20241210/54e0395d6e624dbcb8ec2ba83b2ff14b.webp);
				width: 190px;
				position: absolute;
				text-align: left;
				height: 60px;
				.text {
					color: #FFFFFF;
					font-size: 14px;
				}
				.icon {
					color: #f5f5f5;
					font-size: 12px;
				}
			}
		}
		.lists {
			padding: 40px 0;
			margin: 0;
			background: url(http://codegen.caihongy.cn/20241212/431a3acb917e4f81824c2f0bdf8b3791.webp) no-repeat center / cover;
			width: 100%;
			position: relative;
			order: 5;
			.list_title_box {
				padding: 0 0 0 180px;
				margin: 10px auto;
				background: url(http://codegen.caihongy.cn/20241210/a35f0a1a1ae54a6599c8a9c34f6344e8.webp) no-repeat center / 100% 100%;
				width: 1300px;
				line-height: 100px;
				text-align: left;
				height: 100px;
				.list_title {
					color: #000000;
					font-weight: 700;
					font-size: 36px;
				}
				.list_subhead {
					margin: 0 0 10px;
					color: #999;
					width: 100%;
					font-size: 20px;
					line-height: 1.5;
					text-align: center;
				}
			}
			.index-pv1 .animation-box {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				z-index: initial;
			}
			
			.index-pv1 .animation-box:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 10px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
			}
			
			.index-pv1 .animation-box img {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			}
			
			.index-pv1 .animation-box img:hover {
				transform: rotate(0deg) scale(1.1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
			.list12 {
				padding: 10px;
				margin: 0 auto;
				background: none;
				width: 1300px;
				height: auto;
				.tab_view {
					margin: 20px 0 0;
					display: flex;
					width: 100%;
					justify-content: center;
					height: auto;
					.tab {
						cursor: pointer;
						padding: 0 20px 10px ;
						margin: 0 10px;
						color: #8D8D8D;
						display: flex;
						width: auto;
						font-size: 18px;
						border-color: transparent;
						border-width: 0 0 1px 0;
						line-height: 44px;
						align-items: center;
						border-style: solid;
						img {
							border-radius: 5px;
							margin: 0 5px 0 0;
							object-fit: cover;
							display: block;
							width: 50px;
							height: 50px;
						}
						.text {
							color: inherit;
							font-size: 18px;
						}
					}
					.tab:hover {
						color: #423DD2;
						border-color: #423DD2;
					}
					.tab.active {
						color: #423DD2;
						border-color: #423DD2;
					}
					.more_btn {
						cursor: pointer;
						padding: 5px 10px;
						margin: 0 10px;
						color: #666;
						background: #fff;
						display: none;
						line-height: 44px;
						align-items: center;
						.text {
							color: inherit;
							font-size: 14px;
						}
						.icon {
							margin: 0 0 0 2px;
							color: inherit;
							font-size: 14px;
						}
					}
					.more_btn:hover {
						color: red;
					}
					.tab.active {
						animation-name: mymove;
					
						&:nth-of-type(1) {
							animation-duration: 1s;
						}
						&:nth-of-type(2) {
							animation-duration: 1.2s;
						}
						&:nth-of-type(3) {
							animation-duration: 1.4s;
						}
						&:nth-of-type(4) {
							animation-duration: 1.6s;
						}
						&:nth-of-type(5) {
							animation-duration: 1.8s;
						}
						&:nth-of-type(6) {
							animation-duration: 2s;
						}
					}
					
					@keyframes mymove {
						from {top: 320px;}
						to {top: 0;}
					}
				}
				.list-body {
					margin: 40px 0 0;
					overflow: hidden;
					flex: 1;
					background: none;
					display: flex;
					justify-content: space-between;
					flex-wrap: wrap;
					height: auto;
					.list-item {
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
						.name {
							padding: 0 10px;
							margin: 10px 0 0;
							overflow: hidden;
							color: #333;
							white-space: nowrap;
							font-weight: 600;
							font-size: 16px;
							line-height: 24px;
							text-overflow: ellipsis;
							text-align: center;
						}
						.price {
							padding: 10px;
							color: #D10000;
							font-weight: 600;
							font-size: 16px;
							line-height: 24px;
							text-align: center;
						}
					}
					.list-item.active {
						animation-name: mymove;
					
						&:nth-of-type(1) {
							animation-duration: 1s;
						}
						&:nth-of-type(2) {
							animation-duration: 1.2s;
						}
						&:nth-of-type(3) {
							animation-duration: 1.4s;
						}
						&:nth-of-type(4) {
							animation-duration: 1.6s;
						}
						&:nth-of-type(5) {
							animation-duration: 1.8s;
						}
						&:nth-of-type(6) {
							animation-duration: 2s;
						}
					}
					
					@keyframes mymove {
						from {top: 320px;}
						to {top: 0;}
					}
				}
			}
			.moreBtn {
				border: 0;
				cursor: pointer;
				padding: 0 0 0 40px;
				margin: 10px auto;
				display: block;
				line-height: 60px;
				right: calc(50% - 620px);
				top: 60px;
				background: url(http://codegen.caihongy.cn/20241210/54e0395d6e624dbcb8ec2ba83b2ff14b.webp);
				width: 190px;
				position: absolute;
				text-align: left;
				height: 60px;
				.text {
					color: #FFFFFF;
					font-size: 14px;
				}
				.icon {
					color: #f5f5f5;
					font-size: 12px;
				}
			}
		}
	}
</style>
