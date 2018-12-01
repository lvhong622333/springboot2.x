package com.lvhong.web.mapper;

import java.util.List;
import com.lvhong.web.pojo.TmUrlInfo;
import com.lvhong.web.pojo.UrlInfoSearch;

public interface TmUrlInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TmUrlInfo record);

    int insertSelective(TmUrlInfo record);

    TmUrlInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TmUrlInfo record);

    int updateByPrimaryKey(TmUrlInfo record);

	List<TmUrlInfo> queryUrlInfo(UrlInfoSearch search);

	Integer queryUrlInfoCount(UrlInfoSearch search);

	void deleteBatchByPrimaryKey(String[] paramList);

	List<TmUrlInfo> queryImportUrlInfo();
}