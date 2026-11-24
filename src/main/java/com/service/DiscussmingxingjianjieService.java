package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DiscussmingxingjianjieEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DiscussmingxingjianjieVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscussmingxingjianjieView;


/**
 * 明星简介评论表
 *
 * @author 
 * @email 
 * @date 2030-03-07 10:56:10
 */
public interface DiscussmingxingjianjieService extends IService<DiscussmingxingjianjieEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscussmingxingjianjieVO> selectListVO(Wrapper<DiscussmingxingjianjieEntity> wrapper);
   	
   	DiscussmingxingjianjieVO selectVO(@Param("ew") Wrapper<DiscussmingxingjianjieEntity> wrapper);
   	
   	List<DiscussmingxingjianjieView> selectListView(Wrapper<DiscussmingxingjianjieEntity> wrapper);
   	
   	DiscussmingxingjianjieView selectView(@Param("ew") Wrapper<DiscussmingxingjianjieEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscussmingxingjianjieEntity> wrapper);

   	

}

