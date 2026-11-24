package com.dao;

import com.entity.FensiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.FensiVO;
import com.entity.view.FensiView;


/**
 * 粉丝
 * 
 * @author 
 * @email 
 * @date 2030-03-07 10:56:08
 */
public interface FensiDao extends BaseMapper<FensiEntity> {
	
	List<FensiVO> selectListVO(@Param("ew") Wrapper<FensiEntity> wrapper);
	
	FensiVO selectVO(@Param("ew") Wrapper<FensiEntity> wrapper);
	
	List<FensiView> selectListView(@Param("ew") Wrapper<FensiEntity> wrapper);

	List<FensiView> selectListView(Pagination page,@Param("ew") Wrapper<FensiEntity> wrapper);

	
	FensiView selectView(@Param("ew") Wrapper<FensiEntity> wrapper);
	

}
