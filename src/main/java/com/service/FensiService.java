package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.FensiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.FensiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.FensiView;


/**
 * 粉丝
 *
 * @author 
 * @email 
 * @date 2030-03-07 10:56:08
 */
public interface FensiService extends IService<FensiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<FensiVO> selectListVO(Wrapper<FensiEntity> wrapper);
   	
   	FensiVO selectVO(@Param("ew") Wrapper<FensiEntity> wrapper);
   	
   	List<FensiView> selectListView(Wrapper<FensiEntity> wrapper);
   	
   	FensiView selectView(@Param("ew") Wrapper<FensiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<FensiEntity> wrapper);

   	

}

