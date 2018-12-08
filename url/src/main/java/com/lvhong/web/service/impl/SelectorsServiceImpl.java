package com.lvhong.web.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.lvhong.web.mapper.TmDictonaryMapper;
import com.lvhong.web.pojo.PageList;
import com.lvhong.web.pojo.TmDictonary;
import com.lvhong.web.pojo.TmDictonarySearch;
import com.lvhong.web.service.SelectorsService;

@Service("selecetorsService")
public class SelectorsServiceImpl implements SelectorsService {
	
	@Resource
	private TmDictonaryMapper tmDictonaryMappper;
	
	@Resource
	private HttpSession session;
	
	@Cacheable(value="sysCache",key="#root.methodName + ':' + #dictDate")
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<TmDictonary> querySelectorsInfo(Date dictDate) {
		List<TmDictonary> querySelectorsInfo = tmDictonaryMappper.querySelectorsInfo();
		return querySelectorsInfo;
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public PageList<TmDictonary> urlTypeSearch(TmDictonarySearch search) {
		search.setPageNo(search.getLimit()*(search.getPageNo() - 1));
		PageList<TmDictonary> page = new PageList<TmDictonary>();
		Integer count = tmDictonaryMappper.urlTypeSearchCount(search);
		page.setTotal(count);
		List<TmDictonary> urlTypeSearch = tmDictonaryMappper.urlTypeSearch(search);
		page.setRows(urlTypeSearch);
		return page;
	}

	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED)
	public void deleteDictInfo(String dictInfos) {
		String[] infos = dictInfos.split(",");
		tmDictonaryMappper.deleteDictInfo(infos);
	}

	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED)
	public void addDictInfo(TmDictonary tmDictonary) {
		//获取序列值作为主键
		Long id = tmDictonaryMappper.querySequenceId();
		tmDictonary.setIsvalid("0");
		tmDictonary.setId(id);
		tmDictonaryMappper.insertSelective(tmDictonary);
	}

	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED)
	public void updateDictInfo(TmDictonary tmDictonary) {
		tmDictonaryMappper.updateByPrimaryKeySelective(tmDictonary);
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public TmDictonary queryDictInfo(String businessKey) {
		return tmDictonaryMappper.queryDictInfo(businessKey);
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public String queryFlowUrl(String processDefinitionKey, String taskDefinitionKey) {
		return tmDictonaryMappper.queryFlowUrl(processDefinitionKey,taskDefinitionKey);
	}

	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED)
	public void agencyAdminapprove(String taskId, String approveAdvice, String processInstanceId, Boolean flags,
			Long dictId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("approve", flags);
		if(flags == true) {
			tmDictonaryMappper.updateIsvalid(dictId);
			session.setAttribute("sysCacheDictInfo", new Date());
		}
	}

	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED)
	public void agencyApply(String taskId, String approveAdvice, String processInstanceId, Boolean flags,
			TmDictonary tmDictionary) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("againApply", flags);
		map.put("admin", "superAdmin");
		map.put("dictName", tmDictionary.getDictName());
		if(flags == true) {
			tmDictonaryMappper.updateByPrimaryKeySelective(tmDictionary);
		}else {
			tmDictonaryMappper.deleteByPrimaryKey(tmDictionary.getId());
		}
		session.setAttribute("sysCacheDictInfo", new Date());
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<TmDictonary> queryImportDictInfo() {
		List<TmDictonary> querySelectorsInfo = tmDictonaryMappper.queryImportDictInfo();
		return querySelectorsInfo;
	}

}
