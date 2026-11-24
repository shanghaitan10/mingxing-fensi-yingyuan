package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.lang.*;
import java.math.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import com.utils.ValidatorUtils;
import com.utils.DeSensUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;
import com.annotation.SysLog;
import com.entity.OrdersEntity;
import com.service.OrdersService;
import com.utils.UserBasedCollaborativeFiltering;

import com.entity.PiaowuxinxiEntity;
import com.entity.view.PiaowuxinxiView;

import com.service.PiaowuxinxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;
import com.service.StoreupService;
import com.entity.StoreupEntity;

/**
 * 票务信息
 * 后端接口
 * @author 
 * @email 
 * @date 2030-03-07 10:56:08
 */
@RestController
@RequestMapping("/piaowuxinxi")
public class PiaowuxinxiController {
    @Autowired
    private PiaowuxinxiService piaowuxinxiService;

    @Autowired
    private StoreupService storeupService;

    @Autowired
    private OrdersService ordersService;




    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,PiaowuxinxiEntity piaowuxinxi,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date shijianstart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date shijianend,
                @RequestParam(required = false) Double pricestart,
                @RequestParam(required = false) Double priceend,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("jingjiren")) {
			piaowuxinxi.setJingjiren((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<PiaowuxinxiEntity> ew = new EntityWrapper<PiaowuxinxiEntity>();
        if(shijianstart!=null) ew.ge("shijian", shijianstart);
        if(shijianend!=null) ew.le("shijian", shijianend);
        if(pricestart!=null) ew.ge("price", pricestart);
        if(priceend!=null) ew.le("price", priceend);


        //查询结果
		PageUtils page = piaowuxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, piaowuxinxi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,PiaowuxinxiEntity piaowuxinxi, 
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date shijianstart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date shijianend,
                @RequestParam(required = false) Double pricestart,
                @RequestParam(required = false) Double priceend,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<PiaowuxinxiEntity> ew = new EntityWrapper<PiaowuxinxiEntity>();
        if(shijianstart!=null) ew.ge("shijian", shijianstart);
        if(shijianend!=null) ew.le("shijian", shijianend);
        if(pricestart!=null) ew.ge("price", pricestart);
        if(priceend!=null) ew.le("price", priceend);

        //查询结果
		PageUtils page = piaowuxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, piaowuxinxi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( PiaowuxinxiEntity piaowuxinxi){
       	EntityWrapper<PiaowuxinxiEntity> ew = new EntityWrapper<PiaowuxinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( piaowuxinxi, "piaowuxinxi")); 
        return R.ok().put("data", piaowuxinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(PiaowuxinxiEntity piaowuxinxi){
        EntityWrapper< PiaowuxinxiEntity> ew = new EntityWrapper< PiaowuxinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( piaowuxinxi, "piaowuxinxi")); 
		PiaowuxinxiView piaowuxinxiView =  piaowuxinxiService.selectView(ew);
		return R.ok("查询票务信息成功").put("data", piaowuxinxiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        PiaowuxinxiEntity piaowuxinxi = piaowuxinxiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(piaowuxinxi,deSens);
        return R.ok().put("data", piaowuxinxi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        PiaowuxinxiEntity piaowuxinxi = piaowuxinxiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(piaowuxinxi,deSens);
        return R.ok().put("data", piaowuxinxi);
    }
    


    /**
     * 赞或踩
     */
    @RequestMapping("/thumbsup/{id}")
    public R vote(@PathVariable("id") String id,String type){
        PiaowuxinxiEntity piaowuxinxi = piaowuxinxiService.selectById(id);
        if(type.equals("1")) {
        	piaowuxinxi.setThumbsupnum(piaowuxinxi.getThumbsupnum()+1);
        } else {
        	piaowuxinxi.setCrazilynum(piaowuxinxi.getCrazilynum()+1);
        }
        piaowuxinxiService.updateById(piaowuxinxi);
        return R.ok("投票成功");
    }

    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增票务信息") 
    public R save(@RequestBody PiaowuxinxiEntity piaowuxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(piaowuxinxi);
        piaowuxinxiService.insert(piaowuxinxi);
        return R.ok().put("data",piaowuxinxi.getId());
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增票务信息")
    @RequestMapping("/add")
    public R add(@RequestBody PiaowuxinxiEntity piaowuxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(piaowuxinxi);
        piaowuxinxiService.insert(piaowuxinxi);
        return R.ok().put("data",piaowuxinxi.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改票务信息")
    public R update(@RequestBody PiaowuxinxiEntity piaowuxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(piaowuxinxi);
        //全部更新
        piaowuxinxiService.updateById(piaowuxinxi);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除票务信息")
    public R delete(@RequestBody Long[] ids){
        piaowuxinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,PiaowuxinxiEntity piaowuxinxi, HttpServletRequest request,String pre){
        EntityWrapper<PiaowuxinxiEntity> ew = new EntityWrapper<PiaowuxinxiEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
        // 组装参数
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicktime");
        params.put("order", "desc");

		PageUtils page = piaowuxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, piaowuxinxi), params), params));
        return R.ok().put("data", page);
    }


    /**
     * 协同算法（基于用户的协同算法）
     */
    @RequestMapping("/autoSort2")
    public R autoSort2(@RequestParam Map<String, Object> params,PiaowuxinxiEntity piaowuxinxi, HttpServletRequest request){
        String userId = request.getSession().getAttribute("userId").toString();
        Integer limit = params.get("limit")==null?10:Integer.parseInt(params.get("limit").toString());
        // 查询订单数据
        List<OrdersEntity> orders = ordersService.selectList(new EntityWrapper<OrdersEntity>());
        Map<String, Map<String, Double>> ratings = new HashMap<>();
        if(orders!=null && orders.size()>0) {
            for(OrdersEntity o : orders) {
                Map<String, Double> userRatings = null;
                if(ratings.containsKey(o.getUserid().toString())) {
                    userRatings = ratings.get(o.getUserid().toString());
                } else {
                    userRatings = new HashMap<>();
                    ratings.put(o.getUserid().toString(), userRatings);
                }
                if(userRatings.containsKey(o.getGoodid().toString())) {
                    userRatings.put(o.getGoodid().toString(), userRatings.get(o.getGoodid().toString())+1.0);
                } else {
                    userRatings.put(o.getGoodid().toString(), 1.0);
                }

            }
        }
        // 创建协同过滤对象
        UserBasedCollaborativeFiltering filter = new UserBasedCollaborativeFiltering(ratings);

        // 为指定用户推荐物品
        String targetUser = userId;
        int numRecommendations = limit;
        List<String> recommendations = filter.recommendItems(targetUser, numRecommendations);

        // 输出推荐结果
        System.out.println("Recommendations for " + targetUser + ":");
        for (String item : recommendations) {
            System.out.println(item);
        }

        EntityWrapper<PiaowuxinxiEntity> ew = new EntityWrapper<PiaowuxinxiEntity>();
        ew.in("id", recommendations);
        if(recommendations!=null && recommendations.size()>0 && recommendations.size()>0) {
            ew.last("order by FIELD(id, "+String.join(",", recommendations)+")");
        }

        // 根据协同结果查询结果并返回
        PageUtils page = piaowuxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, piaowuxinxi), params), params));
        List<PiaowuxinxiEntity> pageList = (List<PiaowuxinxiEntity>)page.getList();
        if(recommendations!=null && recommendations.size()>0 && pageList.size()<limit) {
            int toAddNum = limit-pageList.size();
            ew = new EntityWrapper<PiaowuxinxiEntity>();
            ew.notIn("id", recommendations);
            ew.orderBy("id", false);
            ew.last("limit "+toAddNum);
            pageList.addAll(piaowuxinxiService.selectList(ew));
        } else if(pageList.size()>limit) {
            pageList = pageList.subList(0, limit);
        }
        page.setList(pageList);

        return R.ok().put("data", page);
    }






}
