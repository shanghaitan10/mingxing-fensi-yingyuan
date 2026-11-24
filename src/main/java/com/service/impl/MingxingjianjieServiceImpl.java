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


import com.dao.MingxingjianjieDao;
import com.entity.MingxingjianjieEntity;
import com.service.MingxingjianjieService;
import com.entity.vo.MingxingjianjieVO;
import com.entity.view.MingxingjianjieView;

@Service("mingxingjianjieService")
public class MingxingjianjieServiceImpl extends ServiceImpl<MingxingjianjieDao, MingxingjianjieEntity> implements MingxingjianjieService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<MingxingjianjieEntity> page = this.selectPage(
                new Query<MingxingjianjieEntity>(params).getPage(),
                new EntityWrapper<MingxingjianjieEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<MingxingjianjieEntity> wrapper) {
		  Page<MingxingjianjieView> page =new Query<MingxingjianjieView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<MingxingjianjieVO> selectListVO(Wrapper<MingxingjianjieEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public MingxingjianjieVO selectVO(Wrapper<MingxingjianjieEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<MingxingjianjieView> selectListView(Wrapper<MingxingjianjieEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public MingxingjianjieView selectView(Wrapper<MingxingjianjieEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
