package com.lvhong.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lvhong.web.pojo.PageList;
import com.lvhong.web.pojo.ResponseResult;
import com.lvhong.web.pojo.TmDictonary;
import com.lvhong.web.pojo.TmDictonarySearch;
import com.lvhong.web.service.SelectorsService;
import com.lvhong.web.util.FileTypeJudge;

@RequestMapping("/urlInfo")
@Controller
public class SelectorsController {

	@Resource
	private SelectorsService selecetorsService;

	@RequestMapping("/getSelectors")
	@ResponseBody
	public List<TmDictonary> getSelectors(HttpSession session) {
		Date dictDate = (Date) session.getAttribute("sysCacheDictInfo");
		List<TmDictonary> list = selecetorsService.querySelectorsInfo(dictDate);
		return list;
	}

	@RequestMapping("/urlTypeSearch")
	@ResponseBody
	public PageList<TmDictonary> urlTypeSearch(TmDictonarySearch search) {
		PageList<TmDictonary> page = selecetorsService.urlTypeSearch(search);
		return page;
	}

	@RequestMapping("/deleteDictInfo")
	@ResponseBody
	public String deleteDictInfo(String dictInfos, HttpSession session) {
		selecetorsService.deleteDictInfo(dictInfos);
		session.setAttribute("sysCacheDictInfo", new Date());
		return "";
	}

	@RequestMapping("/addDictInfo")
	@ResponseBody
	public ResponseResult addDictInfo(TmDictonary tmDictonary, HttpSession session) {
		ResponseResult resp = new ResponseResult();
		try {
			selecetorsService.addDictInfo(tmDictonary);
			resp.setStatusCode(200);
			resp.setStatusInfo("数据信息添加成功！");
			resp.setSuccessOrError("success");
			// session.setAttribute("sysCacheDictInfo", new Date());
			return resp;
		} catch (Exception e) {
			resp.setStatusCode(500);
			resp.setStatusInfo("数据信息添加失败！");
			resp.setSuccessOrError("fail");
			return resp;
		}
	}

	@RequestMapping("/updateDictInfo")
	@ResponseBody
	public ResponseResult updateDictInfo(TmDictonary tmDictonary, HttpSession session) {
		ResponseResult resp = new ResponseResult();
		try {
			selecetorsService.updateDictInfo(tmDictonary);
			resp.setStatusCode(200);
			resp.setStatusInfo("数据信息添加成功！");
			resp.setSuccessOrError("success");
			session.setAttribute("sysCacheDictInfo", new Date());
			return resp;
		} catch (Exception e) {
			resp.setStatusCode(500);
			resp.setStatusInfo("数据信息添加失败！");
			resp.setSuccessOrError("fail");
			return resp;
		}
	}

	@RequestMapping("/importDictInfo")
	public void importDictInfo(HttpServletResponse response) {
		List<TmDictonary> list = selecetorsService.queryImportDictInfo();
		InputStream urlType = this.getClass().getResourceAsStream("/xls/urlType.xlsx");
		String fileName = "网址类型报表.xlsx";
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
					row.getCell(0).setCellValue(list.get(i).getDictName());
					row.getCell(1).setCellValue(list.get(i).getDictValue());
					row.getCell(2).setCellValue(list.get(i).getDictDesc());
					row.getCell(3).setCellValue(Integer.parseInt(list.get(i).getDictType()));
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
