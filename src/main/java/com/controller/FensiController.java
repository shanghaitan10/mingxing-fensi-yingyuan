package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.lang.*;
import java.math.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import com.entity.TokenEntity;
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

import com.entity.FensiEntity;
import com.entity.view.FensiView;

import com.service.FensiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 粉丝
 * 后端接口
 * @author 
 * @email 
 * @date 2030-03-07 10:56:08
 */
@RestController
@RequestMapping("/fensi")
public class FensiController {
    @Autowired
    private FensiService fensiService;






    
	@Autowired
	private TokenService tokenService;
	
	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		// 根据登录查询用户信息
        FensiEntity u = fensiService.selectOne(new EntityWrapper<FensiEntity>().eq("fensizhanghao", username));
        // 当用户不存在或验证密码不通过时
		if(u==null || !u.getMima().equals(password)) {
            //账号或密码不正确提示
			return R.error("账号或密码不正确");
		}
		
        // 获取登录token
		String token = tokenService.generateToken(u.getId(), username,"fensi",  "粉丝" );
        //返回token
		return R.ok().put("token", token);
	}


	
	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody FensiEntity fensi){
    	//ValidatorUtils.validateEntity(fensi);
        //根据登录账号获取用户信息判断是否存在该用户，否则返回错误信息
    	FensiEntity u = fensiService.selectOne(new EntityWrapper<FensiEntity>().eq("fensizhanghao", fensi.getFensizhanghao()));
		if(u!=null) {
			return R.error("注册用户已存在");
		}
        //判断是否存在相同粉丝账号，否则返回错误信息
        if(fensiService.selectCount(new EntityWrapper<FensiEntity>().eq("fensizhanghao", fensi.getFensizhanghao()))>0) {
            return R.error("粉丝账号已存在");
        }
		Long uId = new Date().getTime();
		fensi.setId(uId);
        //保存用户
        fensiService.insert(fensi);
        return R.ok();
    }

	
	/**
	 * 退出
	 */
	@RequestMapping("/logout")
	public R logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return R.ok("退出成功");
	}
	
	/**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
    	Long id = (Long)request.getSession().getAttribute("userId");
        FensiEntity u = fensiService.selectById(id);
        return R.ok().put("data", u);
    }
    
    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	//根据登录账号判断是否存在用户信息，否则返回错误信息
        FensiEntity u = fensiService.selectOne(new EntityWrapper<FensiEntity>().eq("fensizhanghao", username));
    	if(u==null) {
    		return R.error("账号不存在");
    	}
        //重置密码为123456
        u.setMima("123456");
        fensiService.updateById(u);
        return R.ok("密码已重置为：123456");
    }



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,FensiEntity fensi,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<FensiEntity> ew = new EntityWrapper<FensiEntity>();


        //查询结果
		PageUtils page = fensiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, fensi), params), params));
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
    public R list(@RequestParam Map<String, Object> params,FensiEntity fensi, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<FensiEntity> ew = new EntityWrapper<FensiEntity>();

        //查询结果
		PageUtils page = fensiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, fensi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( FensiEntity fensi){
       	EntityWrapper<FensiEntity> ew = new EntityWrapper<FensiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( fensi, "fensi")); 
        return R.ok().put("data", fensiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(FensiEntity fensi){
        EntityWrapper< FensiEntity> ew = new EntityWrapper< FensiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( fensi, "fensi")); 
		FensiView fensiView =  fensiService.selectView(ew);
		return R.ok("查询粉丝成功").put("data", fensiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        FensiEntity fensi = fensiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(fensi,deSens);
        return R.ok().put("data", fensi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        FensiEntity fensi = fensiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(fensi,deSens);
        return R.ok().put("data", fensi);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增粉丝") 
    public R save(@RequestBody FensiEntity fensi, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(fensiService.selectCount(new EntityWrapper<FensiEntity>().eq("fensizhanghao", fensi.getFensizhanghao()))>0) {
            return R.error("粉丝账号已存在");
        }
        //ValidatorUtils.validateEntity(fensi);
        //验证账号唯一性，否则返回错误信息
        FensiEntity u = fensiService.selectOne(new EntityWrapper<FensiEntity>().eq("fensizhanghao", fensi.getFensizhanghao()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	fensi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		fensi.setId(new Date().getTime());
        fensiService.insert(fensi);
        return R.ok().put("data",fensi.getId());
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增粉丝")
    @RequestMapping("/add")
    public R add(@RequestBody FensiEntity fensi, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(fensiService.selectCount(new EntityWrapper<FensiEntity>().eq("fensizhanghao", fensi.getFensizhanghao()))>0) {
            return R.error("粉丝账号已存在");
        }
        //ValidatorUtils.validateEntity(fensi);
        //验证账号唯一性，否则返回错误信息
        FensiEntity u = fensiService.selectOne(new EntityWrapper<FensiEntity>().eq("fensizhanghao", fensi.getFensizhanghao()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	fensi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		fensi.setId(new Date().getTime());
        fensiService.insert(fensi);
        return R.ok().put("data",fensi.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改粉丝")
    public R update(@RequestBody FensiEntity fensi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(fensi);
        //验证字段唯一性，否则返回错误信息
        if(fensiService.selectCount(new EntityWrapper<FensiEntity>().ne("id", fensi.getId()).eq("fensizhanghao", fensi.getFensizhanghao()))>0) {
            return R.error("粉丝账号已存在");
        }
        //全部更新
        fensiService.updateById(fensi);
        if(null!=fensi.getFensizhanghao())
        {
            // 修改token
            TokenEntity tokenEntity = new TokenEntity();
            tokenEntity.setUsername(fensi.getFensizhanghao());
            tokenService.update(tokenEntity, new EntityWrapper<TokenEntity>().eq("userid", fensi.getId()));
        }
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除粉丝")
    public R delete(@RequestBody Long[] ids){
        fensiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
