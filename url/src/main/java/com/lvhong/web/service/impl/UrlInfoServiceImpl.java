package com.lvhong.web.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.lvhong.web.mapper.TmUrlInfoMapper;
import com.lvhong.web.pojo.TmUrlInfo;
import com.lvhong.web.service.UrlInfoService;

@Service
public class UrlInfoServiceImpl implements UrlInfoService {
	
	@Resource
	private TmUrlInfoMapper tmUrlInfoMapper;

	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,timeout=200)
	public void insertUrlInfo(TmUrlInfo urlInfo) {
		tmUrlInfoMapper.insertSelective(urlInfo);
	}

	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,timeout=200)
	public void updateUrlInfo(TmUrlInfo urlInfo) {
		tmUrlInfoMapper.updateByPrimaryKeySelective(urlInfo);
	}

	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,timeout=200)
	public void deleteUrlInfo(String param) {
		String[] paramList = param.split(",");
		tmUrlInfoMapper.deleteBatchByPrimaryKey(paramList);
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<TmUrlInfo> queryImportUrlInfo() {
		return tmUrlInfoMapper.queryImportUrlInfo();
	}

}
