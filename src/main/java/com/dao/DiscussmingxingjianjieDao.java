package com.dao;

import com.entity.DiscussmingxingjianjieEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiscussmingxingjianjieVO;
import com.entity.view.DiscussmingxingjianjieView;


/**
 * 明星简介评论表
 * 
 * @author 
 * @email 
 * @date 2030-03-07 10:56:10
 */
public interface DiscussmingxingjianjieDao extends BaseMapper<DiscussmingxingjianjieEntity> {
	
	List<DiscussmingxingjianjieVO> selectListVO(@Param("ew") Wrapper<DiscussmingxingjianjieEntity> wrapper);
	
	DiscussmingxingjianjieVO selectVO(@Param("ew") Wrapper<DiscussmingxingjianjieEntity> wrapper);
	
	List<DiscussmingxingjianjieView> selectListView(@Param("ew") Wrapper<DiscussmingxingjianjieEntity> wrapper);

	List<DiscussmingxingjianjieView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussmingxingjianjieEntity> wrapper);

	
	DiscussmingxingjianjieView selectView(@Param("ew") Wrapper<DiscussmingxingjianjieEntity> wrapper);
	

}
