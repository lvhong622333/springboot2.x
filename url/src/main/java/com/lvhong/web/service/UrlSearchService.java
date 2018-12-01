package com.lvhong.web.service;

import com.lvhong.web.pojo.PageList;
import com.lvhong.web.pojo.TmUrlInfo;
import com.lvhong.web.pojo.UrlInfoSearch;

public interface UrlSearchService {

	PageList<TmUrlInfo> queryUrlInfo(UrlInfoSearch search);

}
