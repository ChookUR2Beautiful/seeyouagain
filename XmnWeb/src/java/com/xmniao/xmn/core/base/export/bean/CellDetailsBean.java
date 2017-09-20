package com.xmniao.xmn.core.base.export.bean;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;

public class CellDetailsBean {
	 private Sheet sheet;
	 private Integer r; 
	 private Integer  c;
	 private Object value; 
	 private CellStyle style; 
	 private Integer cellType; 
	 private Float rowHeight;
	public Sheet getSheet() {
		return sheet;
	}
	public void setSheet(Sheet sheet) {
		this.sheet = sheet;
	}
	public Integer getR() {
		return r;
	}
	public void setR(Integer r) {
		this.r = r;
	}
	public Integer getC() {
		return c;
	}
	public void setC(Integer c) {
		this.c = c;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public CellStyle getStyle() {
		return style;
	}
	public void setStyle(CellStyle style) {
		this.style = style;
	}
	public Integer getCellType() {
		return cellType;
	}
	public void setCellType(Integer cellType) {
		this.cellType = cellType;
	}
	public Float getRowHeight() {
		return rowHeight;
	}
	public void setRowHeight(Float rowHeight) {
		this.rowHeight = rowHeight;
	}
	 
	 
	 
}
