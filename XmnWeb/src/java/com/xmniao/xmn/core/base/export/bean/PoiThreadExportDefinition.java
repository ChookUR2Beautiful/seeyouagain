package com.xmniao.xmn.core.base.export.bean;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.poi.ss.usermodel.Sheet;

import com.xmniao.xmn.core.base.export.QueryCallback;

public class PoiThreadExportDefinition {
	private int starIndex;
	private int endIndex;
	private CountDownLatch runningThreadNum;
	private List<?> datas;
	private List<CellDefinition> cellDefinitions;
	private List<CellDefinition> titleCellDefinitions;
	private Sheet sheet;
	private QueryCallback<?> callback;
	
	public QueryCallback<?> getCallback() {
		return callback;
	}
	public void setCallback(QueryCallback<?> callback) {
		this.callback = callback;
	}
	public int getStarIndex() {
		return starIndex;
	}
	public void setStarIndex(int starIndex) {
		this.starIndex = starIndex;
	}
	public int getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
	public CountDownLatch getRunningThreadNum() {
		return runningThreadNum;
	}
	public void setRunningThreadNum(CountDownLatch runningThreadNum) {
		this.runningThreadNum = runningThreadNum;
	}
	public List<?> getDatas() {
		return datas;
	}
	public void setDatas(List<?> datas) {
		this.datas = datas;
	}
	public List<CellDefinition> getCellDefinitions() {
		return cellDefinitions;
	}
	public void setCellDefinitions(List<CellDefinition> cellDefinitions) {
		this.cellDefinitions = cellDefinitions;
	}
	public Sheet getSheet() {
		return sheet;
	}
	public void setSheet(Sheet sheet) {
		this.sheet = sheet;
	}
	public List<CellDefinition> getTitleCellDefinitions() {
		return titleCellDefinitions;
	}
	public void setTitleCellDefinitions(List<CellDefinition> titleCellDefinitions) {
		this.titleCellDefinitions = titleCellDefinitions;
	}

}
