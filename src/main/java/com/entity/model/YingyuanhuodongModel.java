package com.entity.model;

import com.entity.YingyuanhuodongEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 应援活动
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2030-03-07 10:56:08
 */
public class YingyuanhuodongModel  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 活动图片
	 */
	
	private String huodongtupian;
		
	/**
	 * 活动时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date huodongshijian;
		
	/**
	 * 活动地点
	 */
	
	private String huodongdidian;
		
	/**
	 * 活动人数
	 */
	
	private String huodongrenshu;
		
	/**
	 * 报名方式
	 */
	
	private String baomingfangshi;
		
	/**
	 * 经纪人
	 */
	
	private String jingjiren;
		
	/**
	 * 手机号
	 */
	
	private String shoujihao;
		
	/**
	 * 活动内容
	 */
	
	private String huodongneirong;
		
	/**
	 * 最近点击时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date clicktime;
		
	/**
	 * 点击次数
	 */
	
	private Integer clicknum;
		
	/**
	 * 评论数
	 */
	
	private Integer discussnum;
		
	/**
	 * 收藏数
	 */
	
	private Integer storeupnum;
				
	
	/**
	 * 设置：活动图片
	 */
	 
	public void setHuodongtupian(String huodongtupian) {
		this.huodongtupian = huodongtupian;
	}
	
	/**
	 * 获取：活动图片
	 */
	public String getHuodongtupian() {
		return huodongtupian;
	}
				
	
	/**
	 * 设置：活动时间
	 */
	 
	public void setHuodongshijian(Date huodongshijian) {
		this.huodongshijian = huodongshijian;
	}
	
	/**
	 * 获取：活动时间
	 */
	public Date getHuodongshijian() {
		return huodongshijian;
	}
				
	
	/**
	 * 设置：活动地点
	 */
	 
	public void setHuodongdidian(String huodongdidian) {
		this.huodongdidian = huodongdidian;
	}
	
	/**
	 * 获取：活动地点
	 */
	public String getHuodongdidian() {
		return huodongdidian;
	}
				
	
	/**
	 * 设置：活动人数
	 */
	 
	public void setHuodongrenshu(String huodongrenshu) {
		this.huodongrenshu = huodongrenshu;
	}
	
	/**
	 * 获取：活动人数
	 */
	public String getHuodongrenshu() {
		return huodongrenshu;
	}
				
	
	/**
	 * 设置：报名方式
	 */
	 
	public void setBaomingfangshi(String baomingfangshi) {
		this.baomingfangshi = baomingfangshi;
	}
	
	/**
	 * 获取：报名方式
	 */
	public String getBaomingfangshi() {
		return baomingfangshi;
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
	 * 设置：手机号
	 */
	 
	public void setShoujihao(String shoujihao) {
		this.shoujihao = shoujihao;
	}
	
	/**
	 * 获取：手机号
	 */
	public String getShoujihao() {
		return shoujihao;
	}
				
	
	/**
	 * 设置：活动内容
	 */
	 
	public void setHuodongneirong(String huodongneirong) {
		this.huodongneirong = huodongneirong;
	}
	
	/**
	 * 获取：活动内容
	 */
	public String getHuodongneirong() {
		return huodongneirong;
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
	 * 设置：点击次数
	 */
	 
	public void setClicknum(Integer clicknum) {
		this.clicknum = clicknum;
	}
	
	/**
	 * 获取：点击次数
	 */
	public Integer getClicknum() {
		return clicknum;
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
				
	
	/**
	 * 设置：收藏数
	 */
	 
	public void setStoreupnum(Integer storeupnum) {
		this.storeupnum = storeupnum;
	}
	
	/**
	 * 获取：收藏数
	 */
	public Integer getStoreupnum() {
		return storeupnum;
	}
			
}
