export default {
	baseUrl: 'http://localhost:8080/springboote4e34i32/',
	name: '/springboote4e34i32',
	indexNav: [
// frontMenu com.jlwl.entity.Menu@81a7a24
		{
			name: '票务信息',
			url: '/index/piaowuxinxi',
		},
// frontMenu com.jlwl.entity.Menu@4ae3852b
		{
			name: '明星简介',
			url: '/index/mingxingjianjie',
		},
// frontMenu com.jlwl.entity.Menu@2bfe3e48
		{
			name: '应援活动',
			url: '/index/yingyuanhuodong',
		},
		{
			name: '通知公告',
			url: '/index/news'
		},
	],
	cateList: [
		{
			name: '通知公告',
			refTable: 'newstype',
			refColumn: 'typename',
		},
	]
}
