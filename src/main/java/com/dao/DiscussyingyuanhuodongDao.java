package com.dao;

import com.entity.DiscussyingyuanhuodongEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiscussyingyuanhuodongVO;
import com.entity.view.DiscussyingyuanhuodongView;


/**
 * 应援活动评论表
 * 
 * @author 
 * @email 
 * @date 2030-03-07 10:56:10
 */
public interface DiscussyingyuanhuodongDao extends BaseMapper<DiscussyingyuanhuodongEntity> {
	
	List<DiscussyingyuanhuodongVO> selectListVO(@Param("ew") Wrapper<DiscussyingyuanhuodongEntity> wrapper);
	
	DiscussyingyuanhuodongVO selectVO(@Param("ew") Wrapper<DiscussyingyuanhuodongEntity> wrapper);
	
	List<DiscussyingyuanhuodongView> selectListView(@Param("ew") Wrapper<DiscussyingyuanhuodongEntity> wrapper);

	List<DiscussyingyuanhuodongView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussyingyuanhuodongEntity> wrapper);

	
	DiscussyingyuanhuodongView selectView(@Param("ew") Wrapper<DiscussyingyuanhuodongEntity> wrapper);
	

}
