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

import com.entity.DiscussyingyuanhuodongEntity;
import com.entity.view.DiscussyingyuanhuodongView;

import com.service.DiscussyingyuanhuodongService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 应援活动评论表
 * 后端接口
 * @author 
 * @email 
 * @date 2030-03-07 10:56:10
 */
@RestController
@RequestMapping("/discussyingyuanhuodong")
public class DiscussyingyuanhuodongController {
    @Autowired
    private DiscussyingyuanhuodongService discussyingyuanhuodongService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscussyingyuanhuodongEntity discussyingyuanhuodong,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<DiscussyingyuanhuodongEntity> ew = new EntityWrapper<DiscussyingyuanhuodongEntity>();


        //查询结果
		PageUtils page = discussyingyuanhuodongService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussyingyuanhuodong), params), params));
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
    public R list(@RequestParam Map<String, Object> params,DiscussyingyuanhuodongEntity discussyingyuanhuodong, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<DiscussyingyuanhuodongEntity> ew = new EntityWrapper<DiscussyingyuanhuodongEntity>();

        //查询结果
		PageUtils page = discussyingyuanhuodongService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussyingyuanhuodong), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DiscussyingyuanhuodongEntity discussyingyuanhuodong){
       	EntityWrapper<DiscussyingyuanhuodongEntity> ew = new EntityWrapper<DiscussyingyuanhuodongEntity>();
      	ew.allEq(MPUtil.allEQMapPre( discussyingyuanhuodong, "discussyingyuanhuodong")); 
        return R.ok().put("data", discussyingyuanhuodongService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiscussyingyuanhuodongEntity discussyingyuanhuodong){
        EntityWrapper< DiscussyingyuanhuodongEntity> ew = new EntityWrapper< DiscussyingyuanhuodongEntity>();
 		ew.allEq(MPUtil.allEQMapPre( discussyingyuanhuodong, "discussyingyuanhuodong")); 
		DiscussyingyuanhuodongView discussyingyuanhuodongView =  discussyingyuanhuodongService.selectView(ew);
		return R.ok("查询应援活动评论表成功").put("data", discussyingyuanhuodongView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscussyingyuanhuodongEntity discussyingyuanhuodong = discussyingyuanhuodongService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(discussyingyuanhuodong,deSens);
        return R.ok().put("data", discussyingyuanhuodong);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscussyingyuanhuodongEntity discussyingyuanhuodong = discussyingyuanhuodongService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(discussyingyuanhuodong,deSens);
        return R.ok().put("data", discussyingyuanhuodong);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增应援活动评论表") 
    public R save(@RequestBody DiscussyingyuanhuodongEntity discussyingyuanhuodong, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussyingyuanhuodong);
        discussyingyuanhuodongService.insert(discussyingyuanhuodong);
        return R.ok().put("data",discussyingyuanhuodong.getId());
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增应援活动评论表")
    @RequestMapping("/add")
    public R add(@RequestBody DiscussyingyuanhuodongEntity discussyingyuanhuodong, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussyingyuanhuodong);
        discussyingyuanhuodongService.insert(discussyingyuanhuodong);
        return R.ok().put("data",discussyingyuanhuodong.getId());
    }



     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        DiscussyingyuanhuodongEntity discussyingyuanhuodong = discussyingyuanhuodongService.selectOne(new EntityWrapper<DiscussyingyuanhuodongEntity>().eq("", username));
        return R.ok().put("data", discussyingyuanhuodong);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody DiscussyingyuanhuodongEntity discussyingyuanhuodong, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussyingyuanhuodong);
        //全部更新
        discussyingyuanhuodongService.updateById(discussyingyuanhuodong);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除应援活动评论表")
    public R delete(@RequestBody Long[] ids){
        discussyingyuanhuodongService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,DiscussyingyuanhuodongEntity discussyingyuanhuodong, HttpServletRequest request,String pre){
        EntityWrapper<DiscussyingyuanhuodongEntity> ew = new EntityWrapper<DiscussyingyuanhuodongEntity>();
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

		PageUtils page = discussyingyuanhuodongService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussyingyuanhuodong), params), params));
        return R.ok().put("data", page);
    }








}
