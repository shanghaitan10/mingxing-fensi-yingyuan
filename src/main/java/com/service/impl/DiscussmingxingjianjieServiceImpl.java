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


import com.dao.DiscussmingxingjianjieDao;
import com.entity.DiscussmingxingjianjieEntity;
import com.service.DiscussmingxingjianjieService;
import com.entity.vo.DiscussmingxingjianjieVO;
import com.entity.view.DiscussmingxingjianjieView;

@Service("discussmingxingjianjieService")
public class DiscussmingxingjianjieServiceImpl extends ServiceImpl<DiscussmingxingjianjieDao, DiscussmingxingjianjieEntity> implements DiscussmingxingjianjieService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscussmingxingjianjieEntity> page = this.selectPage(
                new Query<DiscussmingxingjianjieEntity>(params).getPage(),
                new EntityWrapper<DiscussmingxingjianjieEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscussmingxingjianjieEntity> wrapper) {
		  Page<DiscussmingxingjianjieView> page =new Query<DiscussmingxingjianjieView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<DiscussmingxingjianjieVO> selectListVO(Wrapper<DiscussmingxingjianjieEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DiscussmingxingjianjieVO selectVO(Wrapper<DiscussmingxingjianjieEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DiscussmingxingjianjieView> selectListView(Wrapper<DiscussmingxingjianjieEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscussmingxingjianjieView selectView(Wrapper<DiscussmingxingjianjieEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
