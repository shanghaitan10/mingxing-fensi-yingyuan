package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DiscussyingyuanhuodongEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DiscussyingyuanhuodongVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscussyingyuanhuodongView;


/**
 * 应援活动评论表
 *
 * @author 
 * @email 
 * @date 2030-03-07 10:56:10
 */
public interface DiscussyingyuanhuodongService extends IService<DiscussyingyuanhuodongEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscussyingyuanhuodongVO> selectListVO(Wrapper<DiscussyingyuanhuodongEntity> wrapper);
   	
   	DiscussyingyuanhuodongVO selectVO(@Param("ew") Wrapper<DiscussyingyuanhuodongEntity> wrapper);
   	
   	List<DiscussyingyuanhuodongView> selectListView(Wrapper<DiscussyingyuanhuodongEntity> wrapper);
   	
   	DiscussyingyuanhuodongView selectView(@Param("ew") Wrapper<DiscussyingyuanhuodongEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscussyingyuanhuodongEntity> wrapper);

   	

}

