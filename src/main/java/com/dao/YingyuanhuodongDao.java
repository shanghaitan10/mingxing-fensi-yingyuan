package com.dao;

import com.entity.YingyuanhuodongEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.YingyuanhuodongVO;
import com.entity.view.YingyuanhuodongView;


/**
 * 应援活动
 * 
 * @author 
 * @email 
 * @date 2030-03-07 10:56:08
 */
public interface YingyuanhuodongDao extends BaseMapper<YingyuanhuodongEntity> {
	
	List<YingyuanhuodongVO> selectListVO(@Param("ew") Wrapper<YingyuanhuodongEntity> wrapper);
	
	YingyuanhuodongVO selectVO(@Param("ew") Wrapper<YingyuanhuodongEntity> wrapper);
	
	List<YingyuanhuodongView> selectListView(@Param("ew") Wrapper<YingyuanhuodongEntity> wrapper);

	List<YingyuanhuodongView> selectListView(Pagination page,@Param("ew") Wrapper<YingyuanhuodongEntity> wrapper);

	
	YingyuanhuodongView selectView(@Param("ew") Wrapper<YingyuanhuodongEntity> wrapper);
	

}
