import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'
	import news from '@/views/modules/news/list'
	import aboutus from '@/views/modules/aboutus/list'
	import coupon from '@/views/modules/coupon/list'
	import discussmingxingjianjie from '@/views/modules/discussmingxingjianjie/list'
	import fensi from '@/views/modules/fensi/list'
	import syslog from '@/views/modules/syslog/list'
	import discussyingyuanhuodong from '@/views/modules/discussyingyuanhuodong/list'
	import jingjiren from '@/views/modules/jingjiren/list'
	import discusspiaowuxinxi from '@/views/modules/discusspiaowuxinxi/list'
	import mingxingjianjie from '@/views/modules/mingxingjianjie/list'
	import yingyuanhuodong from '@/views/modules/yingyuanhuodong/list'
	import systemintro from '@/views/modules/systemintro/list'
	import huodongbaoming from '@/views/modules/huodongbaoming/list'
	import orders from '@/views/modules/orders/list'
	import config from '@/views/modules/config/list'
	import piaowuxinxi from '@/views/modules/piaowuxinxi/list'
	import newstype from '@/views/modules/newstype/list'


//2.配置路由   注意：名字
export const routes = [{
	path: '/',
	name: '系统首页',
	component: Index,
	children: [{
		// 这里不设置值，是把main作为默认页面
		path: '/',
		name: '系统首页',
		component: Home,
		meta: {icon:'', title:'center', affix: true}
	}, {
		path: '/updatePassword',
		name: '修改密码',
		component: UpdatePassword,
		meta: {icon:'', title:'updatePassword'}
	}, {
		path: '/pay',
		name: '支付',
		component: pay,
		meta: {icon:'', title:'pay'}
	}, {
		path: '/center',
		name: '个人信息',
		component: center,
		meta: {icon:'', title:'center'}
	}
	,{
		path: '/news',
		name: '通知公告',
		component: news
	}
	,{
		path: '/aboutus',
		name: '关于我们',
		component: aboutus
	}
	,{
		path: '/coupon',
		name: '优惠券',
		component: coupon
	}
	,{
		path: '/discussmingxingjianjie',
		name: '明星简介评论',
		component: discussmingxingjianjie
	}
	,{
		path: '/fensi',
		name: '粉丝',
		component: fensi
	}
	,{
		path: '/syslog',
		name: '系统日志',
		component: syslog
	}
	,{
		path: '/discussyingyuanhuodong',
		name: '应援活动评论',
		component: discussyingyuanhuodong
	}
	,{
		path: '/jingjiren',
		name: '经纪人',
		component: jingjiren
	}
	,{
		path: '/discusspiaowuxinxi',
		name: '票务信息评论',
		component: discusspiaowuxinxi
	}
	,{
		path: '/mingxingjianjie',
		name: '明星简介',
		component: mingxingjianjie
	}
	,{
		path: '/yingyuanhuodong',
		name: '应援活动',
		component: yingyuanhuodong
	}
	,{
		path: '/systemintro',
		name: '系统简介',
		component: systemintro
	}
	,{
		path: '/huodongbaoming',
		name: '活动报名',
		component: huodongbaoming
	}
	,{
		path: '/orders/:status',
		name: '订单管理',
		component: orders
	}
	,{
		path: '/config',
		name: '轮播图管理',
		component: config
	}
	,{
		path: '/piaowuxinxi',
		name: '票务信息',
		component: piaowuxinxi
	}
	,{
		path: '/newstype',
		name: '通知公告分类',
		component: newstype
	}
	]
	},
	{
		path: '/login',
		name: 'login',
		component: Login,
		meta: {icon:'', title:'login'}
	},
	{
		path: '/register',
		name: 'register',
		component: register,
		meta: {icon:'', title:'register'}
	},
	{
		path: '*',
		component: NotFound
	}
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
	mode: 'hash',
	/*hash模式改为history*/
	routes // （缩写）相当于 routes: routes
})
const originalPush = VueRouter.prototype.push
//修改原型对象中的push方法
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}
export default router;
