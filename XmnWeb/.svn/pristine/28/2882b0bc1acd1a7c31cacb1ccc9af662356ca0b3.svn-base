package com.xmniao.xmn.core.base.export.strategy;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.xmniao.xmn.core.base.export.ConcurrentStrategy;
import com.xmniao.xmn.core.base.export.PoiUtil;
import com.xmniao.xmn.core.base.export.bean.CellDefinition;
import com.xmniao.xmn.core.base.export.bean.PoiThreadExportDefinition;
/**
 * 并发导出策略抽象类
 * @author Administrator
 *
 */
public abstract class AbstractConcurrentStrategy implements ConcurrentStrategy{
	
	
	protected int runThreadCount;//本次导出处理线程数
	
	protected static int minSize = 10000;//线程处理表单行数据最小区间树
	
	protected ExecutorService executorService;
	
	
	
	public AbstractConcurrentStrategy(Long size) {
		this(minSize,size);
	}
	
	public AbstractConcurrentStrategy(int minSize,Long size) {
		AbstractConcurrentStrategy.minSize =minSize;
		getThradCount(size);
	}
	
	protected void getThradCount(Long size){
		long thradCount = size / minSize;
		this.runThreadCount = (int)(size % minSize >0? ++ thradCount :thradCount);
	}
	

	@Override
	public void setExecutorService(ExecutorService executorService) {
		this.executorService = executorService;
	}
	
	/**
	 * 并发开始之前
	 */
	protected  void before(Workbook wb,List<CellDefinition> cellDefinitions,List<CellDefinition> titleCellDefinitions, Sheet sourceSheet){
		Sheet sheet = wb.createSheet();
		setColumnWidth(sourceSheet,sheet,titleCellDefinitions.size());
		setTitleValue(sheet,titleCellDefinitions);
	}
	
	
	private void setTitleValue(Sheet sheet,List<CellDefinition> titleCellDefinitions){
		for(int i=0;i<titleCellDefinitions.size();i++){
			CellDefinition  cellDefinition = titleCellDefinitions.get(i);
			PoiUtil.setValue( sheet,0, cellDefinition.getColumn(),cellDefinition.getKey(), cellDefinition.getStyle(), cellDefinition.getCellType(), cellDefinition.getRowHeight());
		}
	}
	
	/**
	 * 自定义处理
	 * @param runThreadIndex 当前线程下标
	 * @param poiThreadExportDefinitio 导出信息
	 */
	protected abstract void custom(int runThreadIndex,PoiThreadExportDefinition poiThreadExportDefinitio);
	
	/**
	 * 并发之后处理
	 */
	protected abstract void after();
	
	

	@Override
	public void concurrentWriteWorkbook(Workbook wb,List<CellDefinition> cellDefinitions,List<CellDefinition> titleCellDefinitions, Sheet sourceSheet)throws Exception {
		before(wb, cellDefinitions, titleCellDefinitions, sourceSheet);
		if(runThreadCount>0){
			CountDownLatch runningThreadNum = new CountDownLatch(runThreadCount);
			for(int thradIndex=0;thradIndex<runThreadCount;thradIndex++){
				Sheet sheet = wb.createSheet(String.valueOf(thradIndex+1));
				setColumnWidth(sourceSheet,sheet,titleCellDefinitions.size());
				/**
				 * 基于最小数量区间计算每个线程所填充至表单的行的开始与结束区间下标
				 */
				int startIndex = minSize*thradIndex;
				int endIndex = startIndex+minSize;
				
				/**
				 * 线程所需信息
				 */
				PoiThreadExportDefinition poiThreadExportDefinition = createPoiThreadExportDefinition();
				poiThreadExportDefinition.setCellDefinitions(cellDefinitions);
				poiThreadExportDefinition.setTitleCellDefinitions(titleCellDefinitions);
				poiThreadExportDefinition.setSheet(sheet);
				poiThreadExportDefinition.setStarIndex(startIndex);
				poiThreadExportDefinition.setEndIndex(endIndex);
				poiThreadExportDefinition.setRunningThreadNum(runningThreadNum);
				custom(thradIndex,poiThreadExportDefinition);
			}
			runningThreadNum.await();
			after();
		}
		
		
	}
	
	/**
	 * 设置 Sheet 行宽度
	 * @param sourceSheet
	 * @param newSheet
	 * @param columNumber
	 */
	protected void setColumnWidth(Sheet sourceSheet,Sheet newSheet,int columNumber){
		for(int i = 0; i < columNumber; i++){
			newSheet.setColumnWidth(i, sourceSheet.getColumnWidth(i));
		}
	}
	
	/**
	 * 创建导出信息实体
	 * @return
	 */
	protected PoiThreadExportDefinition createPoiThreadExportDefinition(){
		return new PoiThreadExportDefinition();
	}

}
