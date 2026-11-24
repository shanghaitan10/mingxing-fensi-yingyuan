package com.entity.model;

import com.entity.MingxingjianjieEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 明星简介
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2030-03-07 10:56:08
 */
public class MingxingjianjieModel  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 年龄
	 */
	
	private String nianling;
		
	/**
	 * 性别
	 */
	
	private String xingbie;
		
	/**
	 * 出生日期
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date chushengriqi;
		
	/**
	 * 星座
	 */
	
	private String xingzuo;
		
	/**
	 * 爱好
	 */
	
	private String aihao;
		
	/**
	 * 经纪人
	 */
	
	private String jingjiren;
		
	/**
	 * 个人照片
	 */
	
	private String gerenzhaopian;
		
	/**
	 * 获得成就
	 */
	
	private String huodechengjiu;
		
	/**
	 * 个人物料
	 */
	
	private String gerenwuliao;
		
	/**
	 * 常驻综艺
	 */
	
	private String changzhuzongyi;
		
	/**
	 * 个人介绍
	 */
	
	private String gerenjieshao;
		
	/**
	 * 赞
	 */
	
	private Integer thumbsupnum;
		
	/**
	 * 踩
	 */
	
	private Integer crazilynum;
		
	/**
	 * 最近点击时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date clicktime;
		
	/**
	 * 评论数
	 */
	
	private Integer discussnum;
				
	
	/**
	 * 设置：年龄
	 */
	 
	public void setNianling(String nianling) {
		this.nianling = nianling;
	}
	
	/**
	 * 获取：年龄
	 */
	public String getNianling() {
		return nianling;
	}
				
	
	/**
	 * 设置：性别
	 */
	 
	public void setXingbie(String xingbie) {
		this.xingbie = xingbie;
	}
	
	/**
	 * 获取：性别
	 */
	public String getXingbie() {
		return xingbie;
	}
				
	
	/**
	 * 设置：出生日期
	 */
	 
	public void setChushengriqi(Date chushengriqi) {
		this.chushengriqi = chushengriqi;
	}
	
	/**
	 * 获取：出生日期
	 */
	public Date getChushengriqi() {
		return chushengriqi;
	}
				
	
	/**
	 * 设置：星座
	 */
	 
	public void setXingzuo(String xingzuo) {
		this.xingzuo = xingzuo;
	}
	
	/**
	 * 获取：星座
	 */
	public String getXingzuo() {
		return xingzuo;
	}
				
	
	/**
	 * 设置：爱好
	 */
	 
	public void setAihao(String aihao) {
		this.aihao = aihao;
	}
	
	/**
	 * 获取：爱好
	 */
	public String getAihao() {
		return aihao;
	}
				
	
	/**
	 * 设置：经纪人
	 */
	 
	public void setJingjiren(String jingjiren) {
		this.jingjiren = jingjiren;
	}
	
	/**
	 * 获取：经纪人
	 */
	public String getJingjiren() {
		return jingjiren;
	}
				
	
	/**
	 * 设置：个人照片
	 */
	 
	public void setGerenzhaopian(String gerenzhaopian) {
		this.gerenzhaopian = gerenzhaopian;
	}
	
	/**
	 * 获取：个人照片
	 */
	public String getGerenzhaopian() {
		return gerenzhaopian;
	}
				
	
	/**
	 * 设置：获得成就
	 */
	 
	public void setHuodechengjiu(String huodechengjiu) {
		this.huodechengjiu = huodechengjiu;
	}
	
	/**
	 * 获取：获得成就
	 */
	public String getHuodechengjiu() {
		return huodechengjiu;
	}
				
	
	/**
	 * 设置：个人物料
	 */
	 
	public void setGerenwuliao(String gerenwuliao) {
		this.gerenwuliao = gerenwuliao;
	}
	
	/**
	 * 获取：个人物料
	 */
	public String getGerenwuliao() {
		return gerenwuliao;
	}
				
	
	/**
	 * 设置：常驻综艺
	 */
	 
	public void setChangzhuzongyi(String changzhuzongyi) {
		this.changzhuzongyi = changzhuzongyi;
	}
	
	/**
	 * 获取：常驻综艺
	 */
	public String getChangzhuzongyi() {
		return changzhuzongyi;
	}
				
	
	/**
	 * 设置：个人介绍
	 */
	 
	public void setGerenjieshao(String gerenjieshao) {
		this.gerenjieshao = gerenjieshao;
	}
	
	/**
	 * 获取：个人介绍
	 */
	public String getGerenjieshao() {
		return gerenjieshao;
	}
				
	
	/**
	 * 设置：赞
	 */
	 
	public void setThumbsupnum(Integer thumbsupnum) {
		this.thumbsupnum = thumbsupnum;
	}
	
	/**
	 * 获取：赞
	 */
	public Integer getThumbsupnum() {
		return thumbsupnum;
	}
				
	
	/**
	 * 设置：踩
	 */
	 
	public void setCrazilynum(Integer crazilynum) {
		this.crazilynum = crazilynum;
	}
	
	/**
	 * 获取：踩
	 */
	public Integer getCrazilynum() {
		return crazilynum;
	}
				
	
	/**
	 * 设置：最近点击时间
	 */
	 
	public void setClicktime(Date clicktime) {
		this.clicktime = clicktime;
	}
	
	/**
	 * 获取：最近点击时间
	 */
	public Date getClicktime() {
		return clicktime;
	}
				
	
	/**
	 * 设置：评论数
	 */
	 
	public void setDiscussnum(Integer discussnum) {
		this.discussnum = discussnum;
	}
	
	/**
	 * 获取：评论数
	 */
	public Integer getDiscussnum() {
		return discussnum;
	}
			
}
