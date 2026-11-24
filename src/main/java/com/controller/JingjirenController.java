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

import com.entity.JingjirenEntity;
import com.entity.view.JingjirenView;

import com.service.JingjirenService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 经纪人
 * 后端接口
 * @author 
 * @email 
 * @date 2030-03-07 10:56:07
 */
@RestController
@RequestMapping("/jingjiren")
public class JingjirenController {
    @Autowired
    private JingjirenService jingjirenService;






    
	@Autowired
	private TokenService tokenService;
	
	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		// 根据登录查询用户信息
        JingjirenEntity u = jingjirenService.selectOne(new EntityWrapper<JingjirenEntity>().eq("jingjiren", username));
        // 当用户不存在或验证密码不通过时
		if(u==null || !u.getMima().equals(password)) {
            //账号或密码不正确提示
			return R.error("账号或密码不正确");
		}
		
        // 获取登录token
		String token = tokenService.generateToken(u.getId(), username,"jingjiren",  "经纪人" );
        //返回token
		return R.ok().put("token", token);
	}


	
	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody JingjirenEntity jingjiren){
    	//ValidatorUtils.validateEntity(jingjiren);
        //根据登录账号获取用户信息判断是否存在该用户，否则返回错误信息
    	JingjirenEntity u = jingjirenService.selectOne(new EntityWrapper<JingjirenEntity>().eq("jingjiren", jingjiren.getJingjiren()));
		if(u!=null) {
			return R.error("注册用户已存在");
		}
        //判断是否存在相同经纪人，否则返回错误信息
        if(jingjirenService.selectCount(new EntityWrapper<JingjirenEntity>().eq("jingjiren", jingjiren.getJingjiren()))>0) {
            return R.error("经纪人已存在");
        }
		Long uId = new Date().getTime();
		jingjiren.setId(uId);
        //保存用户
        jingjirenService.insert(jingjiren);
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
        JingjirenEntity u = jingjirenService.selectById(id);
        return R.ok().put("data", u);
    }
    
    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	//根据登录账号判断是否存在用户信息，否则返回错误信息
        JingjirenEntity u = jingjirenService.selectOne(new EntityWrapper<JingjirenEntity>().eq("jingjiren", username));
    	if(u==null) {
    		return R.error("账号不存在");
    	}
        //重置密码为123456
        u.setMima("123456");
        jingjirenService.updateById(u);
        return R.ok("密码已重置为：123456");
    }



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,JingjirenEntity jingjiren,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<JingjirenEntity> ew = new EntityWrapper<JingjirenEntity>();


        //查询结果
		PageUtils page = jingjirenService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jingjiren), params), params));
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
    public R list(@RequestParam Map<String, Object> params,JingjirenEntity jingjiren, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<JingjirenEntity> ew = new EntityWrapper<JingjirenEntity>();

        //查询结果
		PageUtils page = jingjirenService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jingjiren), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( JingjirenEntity jingjiren){
       	EntityWrapper<JingjirenEntity> ew = new EntityWrapper<JingjirenEntity>();
      	ew.allEq(MPUtil.allEQMapPre( jingjiren, "jingjiren")); 
        return R.ok().put("data", jingjirenService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(JingjirenEntity jingjiren){
        EntityWrapper< JingjirenEntity> ew = new EntityWrapper< JingjirenEntity>();
 		ew.allEq(MPUtil.allEQMapPre( jingjiren, "jingjiren")); 
		JingjirenView jingjirenView =  jingjirenService.selectView(ew);
		return R.ok("查询经纪人成功").put("data", jingjirenView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JingjirenEntity jingjiren = jingjirenService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(jingjiren,deSens);
        return R.ok().put("data", jingjiren);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        JingjirenEntity jingjiren = jingjirenService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(jingjiren,deSens);
        return R.ok().put("data", jingjiren);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增经纪人") 
    public R save(@RequestBody JingjirenEntity jingjiren, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(jingjirenService.selectCount(new EntityWrapper<JingjirenEntity>().eq("jingjiren", jingjiren.getJingjiren()))>0) {
            return R.error("经纪人已存在");
        }
        //ValidatorUtils.validateEntity(jingjiren);
        //验证账号唯一性，否则返回错误信息
        JingjirenEntity u = jingjirenService.selectOne(new EntityWrapper<JingjirenEntity>().eq("jingjiren", jingjiren.getJingjiren()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	jingjiren.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		jingjiren.setId(new Date().getTime());
        jingjirenService.insert(jingjiren);
        return R.ok().put("data",jingjiren.getId());
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增经纪人")
    @RequestMapping("/add")
    public R add(@RequestBody JingjirenEntity jingjiren, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(jingjirenService.selectCount(new EntityWrapper<JingjirenEntity>().eq("jingjiren", jingjiren.getJingjiren()))>0) {
            return R.error("经纪人已存在");
        }
        //ValidatorUtils.validateEntity(jingjiren);
        //验证账号唯一性，否则返回错误信息
        JingjirenEntity u = jingjirenService.selectOne(new EntityWrapper<JingjirenEntity>().eq("jingjiren", jingjiren.getJingjiren()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	jingjiren.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		jingjiren.setId(new Date().getTime());
        jingjirenService.insert(jingjiren);
        return R.ok().put("data",jingjiren.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改经纪人")
    public R update(@RequestBody JingjirenEntity jingjiren, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jingjiren);
        //验证字段唯一性，否则返回错误信息
        if(jingjirenService.selectCount(new EntityWrapper<JingjirenEntity>().ne("id", jingjiren.getId()).eq("jingjiren", jingjiren.getJingjiren()))>0) {
            return R.error("经纪人已存在");
        }
        //全部更新
        jingjirenService.updateById(jingjiren);
        if(null!=jingjiren.getJingjiren())
        {
            // 修改token
            TokenEntity tokenEntity = new TokenEntity();
            tokenEntity.setUsername(jingjiren.getJingjiren());
            tokenService.update(tokenEntity, new EntityWrapper<TokenEntity>().eq("userid", jingjiren.getId()));
        }
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除经纪人")
    public R delete(@RequestBody Long[] ids){
        jingjirenService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
