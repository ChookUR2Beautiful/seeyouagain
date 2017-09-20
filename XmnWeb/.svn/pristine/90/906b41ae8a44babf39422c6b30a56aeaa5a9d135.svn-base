package com.xmniao.xmn.core.base.export.bean;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
/**
 * 行定义信息
 * @author ch
 *
 */
public class CellDefinition {
	
	private String key;//值
	
	private String prefix;//前缀
	
	private int row;//行号
	
	private int column;//列号
	
	private int cellType;//单元格类型
	
	private CellStyle style;//单元格样式
	
	private float rowHeight;//行高
	
	/**
	 * 属性表情
	 * @param cell
	 * @param key
	 */
	public CellDefinition(Cell cell,String key){
		this(cell,key,null);
	}
	
	
	
	/**
	 * 列表标签
	 * @param cell
	 * @param key
	 * @param prefix
	 */
	public CellDefinition(Cell cell,String key,String prefix){
		this.row = cell.getRowIndex();
		this.column = cell.getColumnIndex();
		this.style = cell.getCellStyle();
		this.cellType = cell.getCellType();
		this.rowHeight = cell.getRow().getHeightInPoints();
		this.key = key;
		this.prefix =prefix;
	}
	
	
	public String getKey() {
		return key;
	}



	public void setKey(String key) {
		this.key = key;
	}



	public String getPrefix() {
		return prefix;
	}



	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}





	public int getRow() {
		return row;
	}



	public void setRow(int row) {
		this.row = row;
	}



	public int getColumn() {
		return column;
	}



	public void setColumn(int column) {
		this.column = column;
	}



	public int getCellType() {
		return cellType;
	}



	public void setCellType(int cellType) {
		this.cellType = cellType;
	}



	public CellStyle getStyle() {
		return style;
	}



	public void setStyle(CellStyle style) {
		this.style = style;
	}



	public float getRowHeight() {
		return rowHeight;
	}



	public void setRowHeight(float rowHeight) {
		this.rowHeight = rowHeight;
	}



	@Override
	public String toString() {
		return "CellDefinition [key=" + key + ", prefix=" + prefix + ", row="
				+ row + ", column=" + column + ", cellType=" + cellType
				+ ", style=" + style + ", rowHeight=" + rowHeight + "]";
	}


	
	
	

}
