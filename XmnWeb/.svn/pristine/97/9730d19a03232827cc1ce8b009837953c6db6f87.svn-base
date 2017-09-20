package com.xmniao.xmn.core.util.excel;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;

/* 
 *  Excel操作工具类
 */
public class ExcelUtil {
	private static Logger logger = Logger.getLogger(ExcelUtil.class);
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private Sheet sheet;
	private int sheetCount;
	private Workbook wb;
	private Map<String, HSSFPictureData> picMap;
	

	/**
	 * 
	 * 构造方法
	 * 
	 * @param is
	 * @param indexShett
	 *            第几张工作表
	 */
	public ExcelUtil(InputStream is) {
		try {
			wb = WorkbookFactory.create(is);
			sheetCount = wb.getNumberOfSheets();
			logger.debug("读取Excel文件");
		} catch (InvalidFormatException e) {
			logger.equals(e);
		} catch (IOException e) {
			logger.equals(e);
		}
	}

	/**
	 * 
	 * 构造方法
	 * 
	 * @param is
	 * @param indexShett
	 *            第几张工作表
	 */
	public ExcelUtil(InputStream is, int indexShett) {
		try {
			this.wb = WorkbookFactory.create(is);
			sheet = this.wb.getSheetAt(indexShett);
			logger.debug("读取Excel文件,工作Sheet:" + sheet.getSheetName());
		} catch (InvalidFormatException e) {
			logger.equals(e);
		} catch (IOException e) {
			logger.equals(e);
		}
	}

	public Sheet getSheet() {
		return sheet;
	}

	public void setSheet(int index) {
		this.sheet = wb.getSheetAt(index);
	}

	public int getSheetCount() {
		return sheetCount;
	}

	public void setSheetCount(int sheetCount) {
		this.sheetCount = sheetCount;
	}

	private Row getRow(int indexRow) {
		if (sheet == null) {
			return null;
		}
		return sheet.getRow(indexRow);
	}

	private Cell getCell(Row row, int indexCell) {
		if (row == null) {
			return null;
		}
		return row.getCell(indexCell);
	}

	/**
	 * 获取行数据
	 * 
	 * @param indexRow
	 * @return
	 */
	public List<String> getHeadRowData(int indexRow) {
		return getRowData(indexRow, false);
	}

	/**
	 * 获取行数据
	 * 
	 * @param indexRow
	 *            行索引
	 * @param addBlank
	 *            是否加入空单元格
	 * @return
	 */
	public List<String> getRowData(int indexRow, boolean addBlank) {
		Row row = getRow(indexRow);
		List<String> columns = new ArrayList<String>();
		int lastColumn = row.getLastCellNum();
		for (int i = 0; i < lastColumn; i++) {
			String colum = getCellStr(indexRow, i);
			if (addBlank || (colum != null && colum != "")) {
				columns.add(colum);
			}
		}
		return columns;
	}

	/**
	 * 导入数据
	 * 
	 * @param tableName
	 * @return
	 */
	public Map importData(String tableName) {
		List<String> columns = getHeadRowData(0);
		int rowCounts = getRowCount();

		return null;
	}

	public static void main(String[] args) {
		try {
			
			System.out.println(1/5);
			System.out.println(1%5);
//			ExcelUtil excelUtils = new ExcelUtil(new FileInputStream(new File("F:\\电商\\品珍广雅店及华穗店商品批量录入3-14\\电商商品带入导入模板.xlsx")), 0);
//			System.out.println("-----");
//			System.out.println(excelUtils.getCellString(1, 0));
//			System.out.println(excelUtils.getCellString(1, 1));
//			System.out.println(excelUtils.getCellString(1, 2));
//			System.out.println(excelUtils.getCellString(1, 3));
//			System.out.println(excelUtils.getCellString(1, 4));
//			
//			System.out.println(excelUtils.getCellString(1, 5));
//			System.out.println(excelUtils.getCellString(1, 6));
//			System.out.println("-----");
//			System.out.println(excelUtils.getSheetPictruesData03());
//			
//			ex.getCellString(i, 5);
//
//			System.out.println(excelUtils.getHeadRowData(0));
//			for(int i=0;i<9;i++){
//				for(int j=0;j<9;j++){					
//					boolean isMer = excelUtils.isMergedRegion(excelUtils.getSheet(), i, j);
//					boolean isLast = excelUtils.isLastMergedRow(excelUtils.getSheet(), i, j);
//					
//					System.err.println(i+","+j+":"+isMer+"->"+isLast);
//					if(isMer){
//						if(isLast){							
//							System.err.println(i+","+j+":"+isMer+"->"+excelUtils.getMergedRegionValue(excelUtils.getSheet(), i, j));
//						}
//					}else{
//						System.err.println(i+","+j+":"+isMer+"->"+excelUtils.getCellString(i, j));
//					}
//				}
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public boolean isFirstMergedRow(Sheet sheet ,int row , int column){
		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {  
			CellRangeAddress range = sheet.getMergedRegion(i);  
			int firstColumn = range.getFirstColumn();  
			int lastColumn = range.getLastColumn();  
			int firstRow = range.getFirstRow();  
			if(row == firstRow){  
				if(column >= firstColumn && column <= lastColumn){  
					return true;  
				}  
			}  
	  }  
	  return false;
	}
	
	public boolean isLastMergedRow(Sheet sheet ,int row , int column){
		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {  
			CellRangeAddress range = sheet.getMergedRegion(i);  
			int firstColumn = range.getFirstColumn();  
			int lastColumn = range.getLastColumn();  
			int firstRow = range.getFirstRow();  
			int lastRow = range.getLastRow();
			if(row == lastRow){  
				if(column >= firstColumn && column <= lastColumn){  
					return true;  
				}  
			}  
	  }  
	  return false;
	}
	
	/**   
	* 获取合并单元格的值   
	* @param sheet   
	* @param row   
	* @param column   
	* @return   
	*/    
	public String getMergedRegionValue(Sheet sheet ,int row , int column){    
	    int sheetMergeCount = sheet.getNumMergedRegions();    
	        
	    for(int i = 0 ; i < sheetMergeCount ; i++){    
	        CellRangeAddress ca = sheet.getMergedRegion(i);    
	        int firstColumn = ca.getFirstColumn();    
	        int lastColumn = ca.getLastColumn();    
	        int firstRow = ca.getFirstRow();    
	        int lastRow = ca.getLastRow();    
	            
	        if(row >= firstRow && row <= lastRow){    
	                
	            if(column >= firstColumn && column <= lastColumn){    
	                Row fRow = sheet.getRow(firstRow);    
	                Cell fCell = fRow.getCell(firstColumn);    
	                return getCellValue(fCell) ;    
	            }    
	        }    
	    }    
	        
	    return null ;    
	}  
	/**   
	* 获取单元格的值   
	* @param cell   
	* @return   
	*/    
	public String getCellValue(Cell cell){    
	        
	    if(cell == null) return "";    
	        
	    if(cell.getCellType() == Cell.CELL_TYPE_STRING){    
	            
	        return cell.getStringCellValue();    
	            
	    }else if(cell.getCellType() == Cell.CELL_TYPE_BOOLEAN){    
	            
	        return String.valueOf(cell.getBooleanCellValue());    
	            
	    }else if(cell.getCellType() == Cell.CELL_TYPE_FORMULA){    
	            
	        return cell.getCellFormula() ;    
	            
	    }else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){    
	            
	        return String.valueOf(cell.getNumericCellValue());    
	            
	    }    
	    return "";    
	}    

	/**  
	* 判断指定的单元格是否是合并单元格  
	* @param sheet   
	* @param row 行下标  
	* @param column 列下标  
	* @return  
	*/  
	public boolean isMergedRegion(Sheet sheet,int row ,int column) {  
	  int sheetMergeCount = sheet.getNumMergedRegions();  
	  for (int i = 0; i < sheetMergeCount; i++) {  
			CellRangeAddress range = sheet.getMergedRegion(i);  
			int firstColumn = range.getFirstColumn();  
			int lastColumn = range.getLastColumn();  
			int firstRow = range.getFirstRow();  
			int lastRow = range.getLastRow();  
			if(row >= firstRow && row <= lastRow){  
				if(column >= firstColumn && column <= lastColumn){  
					return true;  
				}  
			}  
	  }  
	  return false;  
	}  
	/**  
	* 判断sheet页中是否含有合并单元格   
	* @param sheet   
	* @return  
	*/  
	public boolean hasMerged(Sheet sheet) {  
        return sheet.getNumMergedRegions() > 0 ? true : false;  
    }  
	
	/**
	 * 获取单元格的文本
	 * 
	 * @param indexRow
	 *            行下标
	 * @param indexCell
	 *            列下标
	 * @return 返回类型：String
	 * @exception throws
	 */
	public String getCellStr(int indexRow, int indexCell) {
		Row row = getRow(indexRow);
		if (row == null)
			return null;

		return getCellStr(row, indexCell);
	}

	/**
	 * 获取单元格的文本
	 * 
	 * @param indexRow
	 *            行下标
	 * @param indexCell
	 *            列下标
	 * @return 返回类型：String
	 * @exception throws
	 */
	public String getCellString(int indexRow, int indexCell) {
		Row row = getRow(indexRow);
		if (row == null) {
			return null;
		}
		Cell cell = getCell(row, indexCell);
		if (cell == null) {
			return null;
		}
		cell.setCellType(Cell.CELL_TYPE_STRING);
		return cell.getStringCellValue();
	}

	private String getCellStr(Row row, int indexCell) {
		Cell cell = getCell(row, indexCell);
		if (cell == null) {
			return null;
		}
		return convertCellStr(cell);
	}

	/**
	 * 获取工作表的有效行数
	 * 
	 * @return 返回类型：int
	 * @exception throws
	 */
	public int getRowCount() {
		if (sheet == null) {
			return 0;
		}
		int first = sheet.getFirstRowNum();
		int last = sheet.getLastRowNum();
		return (last - first) + 1;
	}

	/**
	 * 获取该行的有效列数
	 * 
	 * @param indexRow
	 *            行下标
	 * @return 返回类型：int
	 * @exception throws
	 */
	public int getCellCount(int indexRow) {
		Row row = getRow(indexRow);
		if (row == null) {
			return 0;
		}
		int first = row.getFirstCellNum();
		int last = row.getLastCellNum();
		return (last - first) + 1;
	}

	/**
	 * 把单元格内的类型转换至String类型
	 */
	private String convertCellStr(Cell cell) {
		String cellStr = "";
		if (null == cell) {
			return cellStr;
		}
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			// 读取String
			cellStr = cell.getStringCellValue().toString();
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			// 得到Boolean对象的方法
			cellStr = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_NUMERIC:
			// 先看是否是日期格式
			if (DateUtil.isCellDateFormatted(cell)) {
				// 读取日期格式
				cellStr = DATE_FORMAT.format(cell.getDateCellValue());
			} else {
				// 读取数字
				// cellStr = String.valueOf(cell.getNumericCellValue());

				double number = cell.getNumericCellValue();
				int num = (int) cell.getNumericCellValue();
				if (number == num) {
					cellStr = String.valueOf(num);
				} else {
					DecimalFormat a = new DecimalFormat("###0");
					cellStr = a.format(number);
					//cellStr = String.valueOf(number);
				}
			}
			break;
		case Cell.CELL_TYPE_FORMULA:
			// 读取公式
			cellStr = cell.getCellFormula().toString();
			break;
		}
		return cellStr;
	}

	public static String ConvertCellStr(Cell cell, String cellStr) {
		if (null == cell) {
			return "";
		}
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			// 读取String
			cellStr = cell.getStringCellValue().toString();
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			// 得到Boolean对象的方法
			cellStr = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_NUMERIC:
			// 先看是否是日期格式
			if (DateUtil.isCellDateFormatted(cell)) {
				// 读取日期格式
				cellStr = cell.getDateCellValue().toString();
			} else {
				// 读取数字
				// cellStr = String.valueOf(cell.getNumericCellValue());

				double number = cell.getNumericCellValue();
				int num = (int) cell.getNumericCellValue();
				if (number == num) {
					cellStr = String.valueOf(num);
				} else {
					cellStr = String.valueOf(number);
				}
			}
			break;
		case Cell.CELL_TYPE_FORMULA:
			// 读取公式
			cellStr = cell.getCellFormula().toString();
			break;
		}
		return cellStr;
	}

	public boolean isNubmer(int indexRow, int indexCell) {
		Cell cell = getCell(sheet.getRow(indexRow), indexCell);
		boolean result = true;
		if (null == cell) {
			return result;
		}
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC:
			// 先看是否是日期格式
			if (DateUtil.isCellDateFormatted(cell)) {
				// 读取日期格式
				result = false;
			} else {
				// 读取数字
				// cellStr = String.valueOf(cell.getNumericCellValue());

				double number = cell.getNumericCellValue();
				int num = (int) cell.getNumericCellValue();
				if (number == num) {
					result = true;
				} else {
					result = false;
				}
			}
			break;
		default:
			result = false;
			break;
		}
		return result;
	}

	/**
	 * 获取Excel2003图片
	 * 
	 * @param sheet
	 *            当前sheet对象
	 * @param workbook
	 *            工作簿对象
	 * @return Map key:图片单元格索引（ 左上角坐标_右小角坐标 0_1_2_0 行_列_行_列
	 *         ）String，value:图片流PictureData
	 * @throws IOException
	 */
	public  Map<String, HSSFPictureData> getSheetPictruesData03() {
		if(picMap == null) {
			HSSFWorkbook workbook = (HSSFWorkbook)this.wb;
			HSSFSheet _sheet = (HSSFSheet)this.sheet;
			Map<String, HSSFPictureData> sheetIndexPicMap = new HashMap<String, HSSFPictureData>();
			List<HSSFPictureData> pictures = workbook.getAllPictures();
			int picSize = pictures == null ? 0 : pictures.size();
			if (picSize != 0) {

				for (Iterator<HSSFShape> it = _sheet.getDrawingPatriarch().getChildren().iterator(); it.hasNext();) {
					HSSFShape shape = it.next();
					HSSFClientAnchor anchor = (HSSFClientAnchor) shape.getAnchor();
					if (shape instanceof HSSFPicture) {
						HSSFPicture pic = (HSSFPicture) shape;
						int pictureIndex = pic.getPictureIndex() - 1;
						
						if(pictureIndex <= picSize -1) {
							HSSFPictureData picData = pictures.get(pictureIndex);
							sheetIndexPicMap.put(
									String.valueOf(anchor.getRow1()) + "_"
											+ String.valueOf(
													anchor.getCol1() + "_" + anchor.getRow2() + "_" + anchor.getCol2()),
									picData);
						}
						
					}
				}

				return (this.picMap = sheetIndexPicMap);
			} else {
				return null;
			}
		}
		
		return picMap;
		
		
	}
	
	/**
	 * 获取一行中所有图片
	 * @param rowIndex 行 索引 从 0开始
	 * @return
	 */
	public List<HSSFPictureData> getRowAllPic(int rowIndex) {
		Map<String, HSSFPictureData> pDatas = getSheetPictruesData03();
		if(pDatas == null || pDatas.isEmpty() ) return null;
		
		List<HSSFPictureData> pdaList = new ArrayList<HSSFPictureData>();
		for (Iterator<Map.Entry<String, HSSFPictureData>> it = pDatas.entrySet().iterator(); it.hasNext();) {
			Map.Entry<String, HSSFPictureData> entry = it.next();
			String key = entry.getKey();
			String[] rc = StringUtils.split(key, "_");
			if(rc[0].equals(String.valueOf(rowIndex))) {
				pdaList.add(entry.getValue());
			}
			
		}
		return pdaList;
	}
	
	/**
	 * 获取单元格中的图片
	 * @param rowIndex    行 索引 从 0开始
	 * @param cellIndex   列 索引 从 0开始
	 * @return
	 */
	public List<HSSFPictureData> getCellPic(int rowIndex, int cellIndex) {
		Map<String, HSSFPictureData> pDatas = getSheetPictruesData03();
		if(pDatas == null || pDatas.isEmpty() ) return null;
		
		List<HSSFPictureData> pdaList = new ArrayList<HSSFPictureData>();
		for (Iterator<Map.Entry<String, HSSFPictureData>> it = pDatas.entrySet().iterator(); it.hasNext();) {
			Map.Entry<String, HSSFPictureData> entry = it.next();
			String key = entry.getKey();
			String[] rc = StringUtils.split(key, "_");
			if(rc[0].equals(String.valueOf(rowIndex)) && rc[1].equals(String.valueOf(cellIndex))) {
				pdaList.add(entry.getValue());
			}
			
		}
		return pdaList;
	}

	/**
	 * 数据导入帮助类
	 * 
	 * @author dingwei2
	 *
	 */
	private static class ExcelDataHelper {
		public static String buildSql(String tableName, List<String> columns) {
			String columnStrs = StringUtils.join(columns.toArray(new String[0]), ",");
			StringBuilder sql = new StringBuilder(200);
			sql.append("insert into ").append(tableName).append(" (").append(columnStrs).append(") values (");
			String split = "";
			for (int i = 0, size = columns.size(); i < size; i++) {
				sql.append(split).append("?");
				split = ",";
			}
			sql.append(")");
			return sql.toString();
		}
	}

}
