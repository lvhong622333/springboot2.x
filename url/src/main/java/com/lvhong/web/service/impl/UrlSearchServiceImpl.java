package com.lvhong.web.service.impl;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lvhong.web.mapper.TmUrlInfoMapper;
import com.lvhong.web.pojo.PageList;
import com.lvhong.web.pojo.TmUrlInfo;
import com.lvhong.web.pojo.UrlInfoSearch;
import com.lvhong.web.service.UrlSearchService;

@Service
public class UrlSearchServiceImpl implements UrlSearchService {
	
	@Resource
	private TmUrlInfoMapper tmUrlInfoMapper;

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	@Cacheable(value="sysCache",key="#root.method.name")
	public PageList<TmUrlInfo> queryUrlInfo(UrlInfoSearch search) {
		//查询分页数据总数
		Integer count = tmUrlInfoMapper.queryUrlInfoCount(search);
		PageList<TmUrlInfo> page = null;
		if(count > 0 ) {
			search.setPageNo(search.getLimit()*(search.getPageNo()-1));
			//查询分页展示数据
			List<TmUrlInfo> list = tmUrlInfoMapper.queryUrlInfo(search);
			page = new PageList<TmUrlInfo>();
			page.setTotal(count);
			page.setRows(list);
		}
		return page;
	}
	
}
