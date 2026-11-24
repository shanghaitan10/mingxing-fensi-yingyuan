package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.YingyuanhuodongEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.YingyuanhuodongVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.YingyuanhuodongView;


/**
 * 应援活动
 *
 * @author 
 * @email 
 * @date 2030-03-07 10:56:08
 */
public interface YingyuanhuodongService extends IService<YingyuanhuodongEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<YingyuanhuodongVO> selectListVO(Wrapper<YingyuanhuodongEntity> wrapper);
   	
   	YingyuanhuodongVO selectVO(@Param("ew") Wrapper<YingyuanhuodongEntity> wrapper);
   	
   	List<YingyuanhuodongView> selectListView(Wrapper<YingyuanhuodongEntity> wrapper);
   	
   	YingyuanhuodongView selectView(@Param("ew") Wrapper<YingyuanhuodongEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<YingyuanhuodongEntity> wrapper);

   	

}

