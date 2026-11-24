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

import com.entity.DiscusspiaowuxinxiEntity;
import com.entity.view.DiscusspiaowuxinxiView;

import com.service.DiscusspiaowuxinxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 票务信息评论表
 * 后端接口
 * @author 
 * @email 
 * @date 2030-03-07 10:56:10
 */
@RestController
@RequestMapping("/discusspiaowuxinxi")
public class DiscusspiaowuxinxiController {
    @Autowired
    private DiscusspiaowuxinxiService discusspiaowuxinxiService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscusspiaowuxinxiEntity discusspiaowuxinxi,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<DiscusspiaowuxinxiEntity> ew = new EntityWrapper<DiscusspiaowuxinxiEntity>();


        //查询结果
		PageUtils page = discusspiaowuxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discusspiaowuxinxi), params), params));
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
    public R list(@RequestParam Map<String, Object> params,DiscusspiaowuxinxiEntity discusspiaowuxinxi, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<DiscusspiaowuxinxiEntity> ew = new EntityWrapper<DiscusspiaowuxinxiEntity>();

        //查询结果
		PageUtils page = discusspiaowuxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discusspiaowuxinxi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DiscusspiaowuxinxiEntity discusspiaowuxinxi){
       	EntityWrapper<DiscusspiaowuxinxiEntity> ew = new EntityWrapper<DiscusspiaowuxinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( discusspiaowuxinxi, "discusspiaowuxinxi")); 
        return R.ok().put("data", discusspiaowuxinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiscusspiaowuxinxiEntity discusspiaowuxinxi){
        EntityWrapper< DiscusspiaowuxinxiEntity> ew = new EntityWrapper< DiscusspiaowuxinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( discusspiaowuxinxi, "discusspiaowuxinxi")); 
		DiscusspiaowuxinxiView discusspiaowuxinxiView =  discusspiaowuxinxiService.selectView(ew);
		return R.ok("查询票务信息评论表成功").put("data", discusspiaowuxinxiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscusspiaowuxinxiEntity discusspiaowuxinxi = discusspiaowuxinxiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(discusspiaowuxinxi,deSens);
        return R.ok().put("data", discusspiaowuxinxi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscusspiaowuxinxiEntity discusspiaowuxinxi = discusspiaowuxinxiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(discusspiaowuxinxi,deSens);
        return R.ok().put("data", discusspiaowuxinxi);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增票务信息评论表") 
    public R save(@RequestBody DiscusspiaowuxinxiEntity discusspiaowuxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discusspiaowuxinxi);
        discusspiaowuxinxiService.insert(discusspiaowuxinxi);
        return R.ok().put("data",discusspiaowuxinxi.getId());
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增票务信息评论表")
    @RequestMapping("/add")
    public R add(@RequestBody DiscusspiaowuxinxiEntity discusspiaowuxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discusspiaowuxinxi);
        discusspiaowuxinxiService.insert(discusspiaowuxinxi);
        return R.ok().put("data",discusspiaowuxinxi.getId());
    }



     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        DiscusspiaowuxinxiEntity discusspiaowuxinxi = discusspiaowuxinxiService.selectOne(new EntityWrapper<DiscusspiaowuxinxiEntity>().eq("", username));
        return R.ok().put("data", discusspiaowuxinxi);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody DiscusspiaowuxinxiEntity discusspiaowuxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discusspiaowuxinxi);
        //全部更新
        discusspiaowuxinxiService.updateById(discusspiaowuxinxi);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除票务信息评论表")
    public R delete(@RequestBody Long[] ids){
        discusspiaowuxinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,DiscusspiaowuxinxiEntity discusspiaowuxinxi, HttpServletRequest request,String pre){
        EntityWrapper<DiscusspiaowuxinxiEntity> ew = new EntityWrapper<DiscusspiaowuxinxiEntity>();
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

		PageUtils page = discusspiaowuxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discusspiaowuxinxi), params), params));
        return R.ok().put("data", page);
    }








}
