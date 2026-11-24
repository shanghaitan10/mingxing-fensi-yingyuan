package com.entity.view;

import com.entity.FensiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 粉丝
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2030-03-07 10:56:08
 */
@TableName("fensi")
public class FensiView  extends FensiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public FensiView(){
	}
 
 	public FensiView(FensiEntity fensiEntity){
 	try {
			BeanUtils.copyProperties(this, fensiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
