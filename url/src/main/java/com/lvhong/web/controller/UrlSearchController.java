package com.lvhong.web.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lvhong.web.pojo.PageList;
import com.lvhong.web.pojo.TmUrlInfo;
import com.lvhong.web.pojo.UrlInfoSearch;
import com.lvhong.web.service.UrlSearchService;

@Controller
@RequestMapping("/urlInfo")
public class UrlSearchController {
	
	@Resource
	private UrlSearchService urlSearchService;
	
	@RequestMapping("/urlSearch")
	@ResponseBody
	public PageList<TmUrlInfo> urlSearch(UrlInfoSearch search) {
		PageList<TmUrlInfo> page = urlSearchService.queryUrlInfo(search);
		return page;
	}
	
}
