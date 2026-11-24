package com.entity.view;

import com.entity.DiscussmingxingjianjieEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 明星简介评论表
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2030-03-07 10:56:10
 */
@TableName("discussmingxingjianjie")
public class DiscussmingxingjianjieView  extends DiscussmingxingjianjieEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public DiscussmingxingjianjieView(){
	}
 
 	public DiscussmingxingjianjieView(DiscussmingxingjianjieEntity discussmingxingjianjieEntity){
 	try {
			BeanUtils.copyProperties(this, discussmingxingjianjieEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
