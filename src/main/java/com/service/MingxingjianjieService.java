package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.MingxingjianjieEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.MingxingjianjieVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.MingxingjianjieView;


/**
 * 明星简介
 *
 * @author 
 * @email 
 * @date 2030-03-07 10:56:08
 */
public interface MingxingjianjieService extends IService<MingxingjianjieEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<MingxingjianjieVO> selectListVO(Wrapper<MingxingjianjieEntity> wrapper);
   	
   	MingxingjianjieVO selectVO(@Param("ew") Wrapper<MingxingjianjieEntity> wrapper);
   	
   	List<MingxingjianjieView> selectListView(Wrapper<MingxingjianjieEntity> wrapper);
   	
   	MingxingjianjieView selectView(@Param("ew") Wrapper<MingxingjianjieEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<MingxingjianjieEntity> wrapper);

   	

}

