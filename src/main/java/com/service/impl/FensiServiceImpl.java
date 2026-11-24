package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.FensiDao;
import com.entity.FensiEntity;
import com.service.FensiService;
import com.entity.vo.FensiVO;
import com.entity.view.FensiView;

@Service("fensiService")
public class FensiServiceImpl extends ServiceImpl<FensiDao, FensiEntity> implements FensiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FensiEntity> page = this.selectPage(
                new Query<FensiEntity>(params).getPage(),
                new EntityWrapper<FensiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<FensiEntity> wrapper) {
		  Page<FensiView> page =new Query<FensiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<FensiVO> selectListVO(Wrapper<FensiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public FensiVO selectVO(Wrapper<FensiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<FensiView> selectListView(Wrapper<FensiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public FensiView selectView(Wrapper<FensiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
