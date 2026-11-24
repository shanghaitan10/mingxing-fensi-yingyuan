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


import com.dao.YingyuanhuodongDao;
import com.entity.YingyuanhuodongEntity;
import com.service.YingyuanhuodongService;
import com.entity.vo.YingyuanhuodongVO;
import com.entity.view.YingyuanhuodongView;

@Service("yingyuanhuodongService")
public class YingyuanhuodongServiceImpl extends ServiceImpl<YingyuanhuodongDao, YingyuanhuodongEntity> implements YingyuanhuodongService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<YingyuanhuodongEntity> page = this.selectPage(
                new Query<YingyuanhuodongEntity>(params).getPage(),
                new EntityWrapper<YingyuanhuodongEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<YingyuanhuodongEntity> wrapper) {
		  Page<YingyuanhuodongView> page =new Query<YingyuanhuodongView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<YingyuanhuodongVO> selectListVO(Wrapper<YingyuanhuodongEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public YingyuanhuodongVO selectVO(Wrapper<YingyuanhuodongEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<YingyuanhuodongView> selectListView(Wrapper<YingyuanhuodongEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public YingyuanhuodongView selectView(Wrapper<YingyuanhuodongEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
