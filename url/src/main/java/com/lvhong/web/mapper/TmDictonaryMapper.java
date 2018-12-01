package com.lvhong.web.mapper;

import java.util.List;

import com.lvhong.web.pojo.TmDictonary;
import com.lvhong.web.pojo.TmDictonarySearch;

public interface TmDictonaryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TmDictonary record);

    void insertSelective(TmDictonary record);

    TmDictonary selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TmDictonary record);

    int updateByPrimaryKey(TmDictonary record);

	List<TmDictonary> querySelectorsInfo();

	List<TmDictonary> urlTypeSearch(TmDictonarySearch search);

	Integer urlTypeSearchCount(TmDictonarySearch search);

	void deleteDictInfo(String[] infos);

	Long querySequenceId();

	TmDictonary queryDictInfo(String businessKey);

	String queryFlowUrl(String processDefinitionKey, String taskDefinitionKey);

	void updateIsvalid(Long dictId);

	List<TmDictonary> queryImportDictInfo();


}