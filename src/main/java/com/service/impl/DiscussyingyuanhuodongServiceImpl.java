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


import com.dao.DiscussyingyuanhuodongDao;
import com.entity.DiscussyingyuanhuodongEntity;
import com.service.DiscussyingyuanhuodongService;
import com.entity.vo.DiscussyingyuanhuodongVO;
import com.entity.view.DiscussyingyuanhuodongView;

@Service("discussyingyuanhuodongService")
public class DiscussyingyuanhuodongServiceImpl extends ServiceImpl<DiscussyingyuanhuodongDao, DiscussyingyuanhuodongEntity> implements DiscussyingyuanhuodongService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscussyingyuanhuodongEntity> page = this.selectPage(
                new Query<DiscussyingyuanhuodongEntity>(params).getPage(),
                new EntityWrapper<DiscussyingyuanhuodongEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscussyingyuanhuodongEntity> wrapper) {
		  Page<DiscussyingyuanhuodongView> page =new Query<DiscussyingyuanhuodongView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<DiscussyingyuanhuodongVO> selectListVO(Wrapper<DiscussyingyuanhuodongEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DiscussyingyuanhuodongVO selectVO(Wrapper<DiscussyingyuanhuodongEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DiscussyingyuanhuodongView> selectListView(Wrapper<DiscussyingyuanhuodongEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscussyingyuanhuodongView selectView(Wrapper<DiscussyingyuanhuodongEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
