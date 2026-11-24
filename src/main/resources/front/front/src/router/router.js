import VueRouter from 'vue-router'
//引入组件
import Index from '../pages'
import Home from '../pages/home/home'
import Login from '../pages/login/login'
import Register from '../pages/register/register'
import Center from '../pages/center/center'
import Storeup from '../pages/storeup/list'
import AddrList from '../pages/shop-address/list'
import AddrAdd from '../pages/shop-address/addOrUpdate'
import Order from '../pages/shop-order/list'
import OrderConfirm from '../pages/shop-order/confirm'
import Cart from '../pages/shop-cart/list'
import News from '../pages/news/news-list'
import NewsDetail from '../pages/news/news-detail'
import payList from '../pages/pay'

import jingjirenList from '../pages/jingjiren/list'
import jingjirenDetail from '../pages/jingjiren/detail'
import jingjirenAdd from '../pages/jingjiren/add'
import fensiList from '../pages/fensi/list'
import fensiDetail from '../pages/fensi/detail'
import fensiAdd from '../pages/fensi/add'
import piaowuxinxiList from '../pages/piaowuxinxi/list'
import piaowuxinxiDetail from '../pages/piaowuxinxi/detail'
import piaowuxinxiAdd from '../pages/piaowuxinxi/add'
import mingxingjianjieList from '../pages/mingxingjianjie/list'
import mingxingjianjieDetail from '../pages/mingxingjianjie/detail'
import mingxingjianjieAdd from '../pages/mingxingjianjie/add'
import yingyuanhuodongList from '../pages/yingyuanhuodong/list'
import yingyuanhuodongDetail from '../pages/yingyuanhuodong/detail'
import yingyuanhuodongAdd from '../pages/yingyuanhuodong/add'
import huodongbaomingList from '../pages/huodongbaoming/list'
import huodongbaomingDetail from '../pages/huodongbaoming/detail'
import huodongbaomingAdd from '../pages/huodongbaoming/add'
import syslogList from '../pages/syslog/list'
import syslogDetail from '../pages/syslog/detail'
import syslogAdd from '../pages/syslog/add'
import couponList from '../pages/coupon/list'
import couponDetail from '../pages/coupon/detail'
import couponAdd from '../pages/coupon/add'
import mycouponList from '../pages/mycoupon/list'
import mycouponDetail from '../pages/mycoupon/detail'
import mycouponAdd from '../pages/mycoupon/add'
import chargerecordList from '../pages/chargerecord/list'
import chargerecordDetail from '../pages/chargerecord/detail'
import chargerecordAdd from '../pages/chargerecord/add'
import newstypeList from '../pages/newstype/list'
import newstypeDetail from '../pages/newstype/detail'
import newstypeAdd from '../pages/newstype/add'
import aboutusList from '../pages/aboutus/list'
import aboutusDetail from '../pages/aboutus/detail'
import aboutusAdd from '../pages/aboutus/add'
import systemintroList from '../pages/systemintro/list'
import systemintroDetail from '../pages/systemintro/detail'
import systemintroAdd from '../pages/systemintro/add'
import discusspiaowuxinxiList from '../pages/discusspiaowuxinxi/list'
import discusspiaowuxinxiDetail from '../pages/discusspiaowuxinxi/detail'
import discusspiaowuxinxiAdd from '../pages/discusspiaowuxinxi/add'
import discussmingxingjianjieList from '../pages/discussmingxingjianjie/list'
import discussmingxingjianjieDetail from '../pages/discussmingxingjianjie/detail'
import discussmingxingjianjieAdd from '../pages/discussmingxingjianjie/add'
import discussyingyuanhuodongList from '../pages/discussyingyuanhuodong/list'
import discussyingyuanhuodongDetail from '../pages/discussyingyuanhuodong/detail'
import discussyingyuanhuodongAdd from '../pages/discussyingyuanhuodong/add'

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}

//配置路由
export default new VueRouter({
	routes:[
		{
      path: '/',
      redirect: '/index/home'
    },
		{
			path: '/index',
			component: Index,
			children:[
				{
					path: 'home',
					component: Home
				},
				{
					path: 'center',
					component: Center,
				},
				{
					path: 'pay',
					component: payList,
				},
				{
					path: 'storeup',
					component: Storeup
				},
                {
                    path: 'shop-address/list',
                    component: AddrList
                },
                {
                    path: 'shop-address/addOrUpdate',
                    component: AddrAdd
                },
				{
					path: 'shop-order/order',
					component: Order
				},
				{
					path: 'cart',
					component: Cart
				},
				{
					path: 'shop-order/orderConfirm',
					component: OrderConfirm
				},
				{
					path: 'news',
					component: News
				},
				{
					path: 'newsDetail',
					component: NewsDetail
				},
				{
					path: 'jingjiren',
					component: jingjirenList
				},
				{
					path: 'jingjirenDetail',
					component: jingjirenDetail
				},
				{
					path: 'jingjirenAdd',
					component: jingjirenAdd
				},
				{
					path: 'fensi',
					component: fensiList
				},
				{
					path: 'fensiDetail',
					component: fensiDetail
				},
				{
					path: 'fensiAdd',
					component: fensiAdd
				},
				{
					path: 'piaowuxinxi',
					component: piaowuxinxiList
				},
				{
					path: 'piaowuxinxiDetail',
					component: piaowuxinxiDetail
				},
				{
					path: 'piaowuxinxiAdd',
					component: piaowuxinxiAdd
				},
				{
					path: 'mingxingjianjie',
					component: mingxingjianjieList
				},
				{
					path: 'mingxingjianjieDetail',
					component: mingxingjianjieDetail
				},
				{
					path: 'mingxingjianjieAdd',
					component: mingxingjianjieAdd
				},
				{
					path: 'yingyuanhuodong',
					component: yingyuanhuodongList
				},
				{
					path: 'yingyuanhuodongDetail',
					component: yingyuanhuodongDetail
				},
				{
					path: 'yingyuanhuodongAdd',
					component: yingyuanhuodongAdd
				},
				{
					path: 'huodongbaoming',
					component: huodongbaomingList
				},
				{
					path: 'huodongbaomingDetail',
					component: huodongbaomingDetail
				},
				{
					path: 'huodongbaomingAdd',
					component: huodongbaomingAdd
				},
				{
					path: 'syslog',
					component: syslogList
				},
				{
					path: 'syslogDetail',
					component: syslogDetail
				},
				{
					path: 'syslogAdd',
					component: syslogAdd
				},
				{
					path: 'coupon',
					component: couponList
				},
				{
					path: 'couponDetail',
					component: couponDetail
				},
				{
					path: 'couponAdd',
					component: couponAdd
				},
				{
					path: 'mycoupon',
					component: mycouponList
				},
				{
					path: 'mycouponDetail',
					component: mycouponDetail
				},
				{
					path: 'mycouponAdd',
					component: mycouponAdd
				},
				{
					path: 'chargerecord',
					component: chargerecordList
				},
				{
					path: 'chargerecordDetail',
					component: chargerecordDetail
				},
				{
					path: 'chargerecordAdd',
					component: chargerecordAdd
				},
				{
					path: 'newstype',
					component: newstypeList
				},
				{
					path: 'newstypeDetail',
					component: newstypeDetail
				},
				{
					path: 'newstypeAdd',
					component: newstypeAdd
				},
				{
					path: 'aboutus',
					component: aboutusList
				},
				{
					path: 'aboutusDetail',
					component: aboutusDetail
				},
				{
					path: 'aboutusAdd',
					component: aboutusAdd
				},
				{
					path: 'systemintro',
					component: systemintroList
				},
				{
					path: 'systemintroDetail',
					component: systemintroDetail
				},
				{
					path: 'systemintroAdd',
					component: systemintroAdd
				},
				{
					path: 'discusspiaowuxinxi',
					component: discusspiaowuxinxiList
				},
				{
					path: 'discusspiaowuxinxiDetail',
					component: discusspiaowuxinxiDetail
				},
				{
					path: 'discusspiaowuxinxiAdd',
					component: discusspiaowuxinxiAdd
				},
				{
					path: 'discussmingxingjianjie',
					component: discussmingxingjianjieList
				},
				{
					path: 'discussmingxingjianjieDetail',
					component: discussmingxingjianjieDetail
				},
				{
					path: 'discussmingxingjianjieAdd',
					component: discussmingxingjianjieAdd
				},
				{
					path: 'discussyingyuanhuodong',
					component: discussyingyuanhuodongList
				},
				{
					path: 'discussyingyuanhuodongDetail',
					component: discussyingyuanhuodongDetail
				},
				{
					path: 'discussyingyuanhuodongAdd',
					component: discussyingyuanhuodongAdd
				},
			]
		},
		{
			path: '/login',
			component: Login
		},
		{
			path: '/register',
			component: Register
		},
	]
})
