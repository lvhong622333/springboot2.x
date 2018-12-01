package com.lvhong.web.service;

import java.util.List;

import com.lvhong.web.pojo.TmUrlInfo;

public interface UrlInfoService {

	void insertUrlInfo(TmUrlInfo urlInfo);

	void updateUrlInfo(TmUrlInfo urlInfo);

	void deleteUrlInfo(String param);

	List<TmUrlInfo> queryImportUrlInfo();

}
