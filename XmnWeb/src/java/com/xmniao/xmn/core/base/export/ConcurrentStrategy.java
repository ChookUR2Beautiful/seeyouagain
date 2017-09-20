package com.xmniao.xmn.core.base.export;

import java.util.List;
import java.util.concurrent.ExecutorService;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.xmniao.xmn.core.base.export.bean.CellDefinition;
/**
 * 并发导出策略
 * @author Administrator
 *
 */
public interface ConcurrentStrategy {
	
	/**
	 * 设置线程池
	 * @param executorService
	 */
	void setExecutorService(ExecutorService executorService);
	
	/**
	 * 并发写入数据至工作簿
	 * @param wb 工作簿
	 * @param cellDefinitions 模版信息
	 * @param titleCellDefinitions 标题
	 * @param sourceSheet 原始Sheet
	 * @throws Exception
	 */
	void concurrentWriteWorkbook(Workbook wb,List<CellDefinition> cellDefinitions,List<CellDefinition> titleCellDefinitions,Sheet sourceSheet) throws Exception;
	
}
