package com.xmniao.xmn.core.base.Import;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.Import.bean.ImportModelDefinition;
import com.xmniao.xmn.core.base.Import.context.DefaultContext;
import com.xmniao.xmn.core.base.export.PoiTag;
import com.xmniao.xmn.core.base.export.PoiUtil;
import com.xmniao.xmn.core.base.export.bean.CellDefinition;
import com.xmniao.xmn.core.base.export.tag.TitleTag;


public class PoiImport {
	
	
	
	public static <T> List<T> dataImport(MultipartFile fileInfo,Class<T> entityClass) throws Exception{
		InputStream in=null;
		try {
			in = fileInfo.getInputStream();
			if(in!=null&&fileInfo.getSize()>0){
				Workbook wb = new HSSFWorkbook(new BufferedInputStream(in));
				return parseWorkbook(wb,entityClass);
			}
		}finally{
			if(in!=null){
				in.close();
			}
		}
		return null;
	}
	
	
	/**
	 * 处理workbook
	 * @param wb 
	 * @throws InterruptedException 
	 */
	private static <T> List<T>  parseWorkbook(Workbook wb,Class<T> entityClass) throws InterruptedException {
		List<T> objs = new ArrayList<>();
		int sheetCount = wb.getNumberOfSheets();
		for (int i = 0; i < sheetCount; i++) {
			Sheet sheet = wb.getSheetAt(i);
			String sheetName = sheet.getSheetName();
			//add by huang'tao 数据样例不导入
			boolean toImport=!"数据样例".equals(sheetName);
			int rowNum = sheet.getLastRowNum();
			if(rowNum>0 && toImport){
				Row row = sheet.getRow(0);
				int cellNum = row.getLastCellNum();
				 List<CellDefinition> sheetTitles = resolveSheetTitle(sheet);
				 if(!sheetTitles.isEmpty()){
					 objs.addAll( getRowInfo(sheet,rowNum,cellNum,sheetTitles,entityClass));
				 } 
			}
		}
		return objs;
	}
	
	
	private static <T> List<T> getRowInfo(Sheet sheet,int rowNum,int cellNum,List<CellDefinition> sheetTitles,Class<T> entityClass){
		Row row =null;
		Map<String, Object> map =null;
		List<T> objs = new ArrayList<>(rowNum);
		for(int rowIndex  = 1; rowIndex<=rowNum; rowIndex++){
			map = new HashMap<>(cellNum);
			row = sheet.getRow(rowIndex);
			if(row!=null){
				getCellInfo(row,cellNum,sheetTitles,entityClass,map);
				if(!map.isEmpty()){
					objs.add(JSONObject.parseObject(JSONObject.toJSONString(map), entityClass));
				}
				
				map =null;
			}
		}
		return objs;
	}
	
	private static <T> void getCellInfo(Row row,int cellNum,List<CellDefinition> sheetTitles,Class<T> entityClass,Map<String, Object> map){
		Cell cell;
		CellDefinition  cellDefinition;
		for(int cellIndex=0;cellIndex<cellNum;cellIndex++){
			cell = row.getCell(cellIndex);
			if(cell!=null){
				
				Object cellValue = PoiUtil.getValue(cell);
				if(cellValue instanceof Number){
					cellValue = new BigDecimal((Double)cellValue);
				}
				cellDefinition = sheetTitles.get(cellIndex);
				if(cellDefinition!=null){
					ImportModelDefinition modelDefinition = DefaultContext.getModel(entityClass, cellDefinition.getKey());
					if(modelDefinition!=null){
						if(cellValue==null){
							cellValue = modelDefinition.getDefaultValue();
						}
						if(cellValue!=null){
							map.put(modelDefinition.getName(), cellValue);
						}
					}
				}
			}
		}
	}
	
	
	
	
	private static List<CellDefinition> resolveSheetTitle(Sheet sheet){
		List<CellDefinition> cellDefinitions = new ArrayList<CellDefinition>();
		/*
		 * 解析表单标题
		 */
		Row row = sheet.getRow(0);
		if(row!=null){
			PoiTag tag = new TitleTag();
			Iterator<Cell> cellIterator = row.cellIterator();
			while(cellIterator.hasNext()){
				Cell cell = cellIterator.next();
				if(cell != null){
					String value = String.valueOf(PoiUtil.getValue(cell));
						CellDefinition c = tag.parse(cell, value);
						if(c!=null){
							cellDefinitions.add(c);
					}
				}
			}
			sheet.removeRow(row);
		}
		
		return cellDefinitions;
	}

}
