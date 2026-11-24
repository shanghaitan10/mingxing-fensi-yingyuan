package com.entity.vo;

import com.entity.HuodongbaomingEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 活动报名
 * @author 
 * @email 
 * @date 2030-03-07 10:56:08
 */
public class HuodongbaomingVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 活动名称
	 */
	
	private String huodongmingcheng;
		
	/**
	 * 活动图片
	 */
	
	private String huodongtupian;
		
	/**
	 * 活动时间
	 */
	
	private String huodongshijian;
		
	/**
	 * 活动地点
	 */
	
	private String huodongdidian;
		
	/**
	 * 经纪人
	 */
	
	private String jingjiren;
		
	/**
	 * 手机号
	 */
	
	private String shoujihao;
		
	/**
	 * 粉丝账号
	 */
	
	private String fensizhanghao;
		
	/**
	 * 粉丝姓名
	 */
	
	private String fensixingming;
		
	/**
	 * 报名时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date baomingshijian;
		
	/**
	 * 是否审核
	 */
	
	private String sfsh;
		
	/**
	 * 审核回复
	 */
	
	private String shhf;
				
	
	/**
	 * 设置：活动名称
	 */
	 
	public void setHuodongmingcheng(String huodongmingcheng) {
		this.huodongmingcheng = huodongmingcheng;
	}
	
	/**
	 * 获取：活动名称
	 */
	public String getHuodongmingcheng() {
		return huodongmingcheng;
	}
				
	
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
	 
	public void setHuodongshijian(String huodongshijian) {
		this.huodongshijian = huodongshijian;
	}
	
	/**
	 * 获取：活动时间
	 */
	public String getHuodongshijian() {
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
	 * 设置：粉丝账号
	 */
	 
	public void setFensizhanghao(String fensizhanghao) {
		this.fensizhanghao = fensizhanghao;
	}
	
	/**
	 * 获取：粉丝账号
	 */
	public String getFensizhanghao() {
		return fensizhanghao;
	}
				
	
	/**
	 * 设置：粉丝姓名
	 */
	 
	public void setFensixingming(String fensixingming) {
		this.fensixingming = fensixingming;
	}
	
	/**
	 * 获取：粉丝姓名
	 */
	public String getFensixingming() {
		return fensixingming;
	}
				
	
	/**
	 * 设置：报名时间
	 */
	 
	public void setBaomingshijian(Date baomingshijian) {
		this.baomingshijian = baomingshijian;
	}
	
	/**
	 * 获取：报名时间
	 */
	public Date getBaomingshijian() {
		return baomingshijian;
	}
				
	
	/**
	 * 设置：是否审核
	 */
	 
	public void setSfsh(String sfsh) {
		this.sfsh = sfsh;
	}
	
	/**
	 * 获取：是否审核
	 */
	public String getSfsh() {
		return sfsh;
	}
				
	
	/**
	 * 设置：审核回复
	 */
	 
	public void setShhf(String shhf) {
		this.shhf = shhf;
	}
	
	/**
	 * 获取：审核回复
	 */
	public String getShhf() {
		return shhf;
	}
			
}
