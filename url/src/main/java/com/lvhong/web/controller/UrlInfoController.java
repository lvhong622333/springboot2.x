package com.lvhong.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lvhong.web.pojo.ResponseResult;
import com.lvhong.web.pojo.TmSysUser;
import com.lvhong.web.pojo.TmUrlInfo;
import com.lvhong.web.service.UrlInfoService;
import com.lvhong.web.util.FileTypeJudge;

@Controller
@RequestMapping("/urlInfo")
public class UrlInfoController {
	
	@Resource
	private UrlInfoService urlInfoService;
	
	@RequestMapping("/page/{urlInputPage}")
	public String urlInputPage(@PathVariable String urlInputPage , @ModelAttribute("inputFailInfo") String inputFailInfo , Model model) {
		String page = "/pages/" + urlInputPage;
		if(inputFailInfo != null && inputFailInfo != "") {
			model.addAttribute("inputFailInfo", inputFailInfo);
		}
		return page;
	}
	
	@RequestMapping("/urlInput")
	public String urlInput(TmUrlInfo urlInfo,Model model,HttpSession session) {
		try {			
			TmSysUser user = (TmSysUser) session.getAttribute("user");
			urlInfo.setCreateBy(user.getId());
			urlInfoService.insertUrlInfo(urlInfo);
			return "redirect:/urlInfo/page/urlSearch";
		}catch(Exception e) {
			model.addAttribute("inputFailInfo", "对不起，你输入的网址信息已存在！");
			return "redirect:/urlInfo/page/urlInput";
		}
	}
	
	@RequestMapping("/updateUrlInfo")
	@ResponseBody
	public ResponseResult urlInfoUpdate(TmUrlInfo urlInfo) {
		ResponseResult result = new ResponseResult();
		try {
			urlInfoService.updateUrlInfo(urlInfo);
			result.setStatusCode(200);
			result.setStatusInfo("更新成功");
			result.setSuccessOrError("success");
			return result;
		}catch(Exception e) {
			result.setStatusCode(400);
			result.setStatusInfo("更新失败");
			result.setSuccessOrError("fail");
			return result;
		}
	}
	@RequestMapping("/deleteUrlInfo")
	@ResponseBody
	public ResponseResult deleteUrlInfo(String param) {
		ResponseResult result = new ResponseResult();
		urlInfoService.deleteUrlInfo(param);
		return result;
	}
	
	@RequestMapping("/importUrlInfo")
	public void importUrlInfo(HttpServletResponse response) {
		List<TmUrlInfo> list = urlInfoService.queryImportUrlInfo();
		InputStream urlType = this.getClass().getResourceAsStream("/xls/urlInfo.xls");
		String fileName = "网址报表.xls";
		response.reset();
		OutputStream output = null;
		try {
			output = response.getOutputStream();
			Workbook wb = WorkbookFactory.create(urlType);
			String typeString = FileTypeJudge.bytesToHexString(urlType);
			if(FileTypeJudge.XLS_TYPE.equals(typeString)) {
				 response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			}
			if(FileTypeJudge.XLSX_TYPE.equals(typeString)) {
				 response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8");
			}		
			fileName = URLEncoder.encode(fileName, "UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName);// 下载文件
			Sheet sheet = wb.getSheetAt(0);
			for (int i = 0; i < list.size(); i++) {
				Row row = sheet.getRow(i + 1);
				if (row != null) {
					row.getCell(0).setCellValue(list.get(i).getUrlName());
					row.getCell(1).setCellValue(list.get(i).getUrl());
					row.getCell(2).setCellValue(list.get(i).getUrlType());
					row.getCell(3).setCellValue(list.get(i).getUrlDesc());
				}
			}
			wb.write(output);// 写入到Excel模板文件中
			output.close();// 关闭
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(output != null) {				
				try {
					output.close();
				} catch (IOException e) {
					
				}
			}
		}
	}
	
}
