package com.dao;

import com.entity.MingxingjianjieEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.MingxingjianjieVO;
import com.entity.view.MingxingjianjieView;


/**
 * 明星简介
 * 
 * @author 
 * @email 
 * @date 2030-03-07 10:56:08
 */
public interface MingxingjianjieDao extends BaseMapper<MingxingjianjieEntity> {
	
	List<MingxingjianjieVO> selectListVO(@Param("ew") Wrapper<MingxingjianjieEntity> wrapper);
	
	MingxingjianjieVO selectVO(@Param("ew") Wrapper<MingxingjianjieEntity> wrapper);
	
	List<MingxingjianjieView> selectListView(@Param("ew") Wrapper<MingxingjianjieEntity> wrapper);

	List<MingxingjianjieView> selectListView(Pagination page,@Param("ew") Wrapper<MingxingjianjieEntity> wrapper);

	
	MingxingjianjieView selectView(@Param("ew") Wrapper<MingxingjianjieEntity> wrapper);
	

}
