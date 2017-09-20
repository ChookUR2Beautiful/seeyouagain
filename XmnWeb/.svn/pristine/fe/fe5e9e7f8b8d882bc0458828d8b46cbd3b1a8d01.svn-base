package com.xmniao.xmn.core.base.export;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.xmniao.xmn.core.base.export.bean.CellDefinition;
import com.xmniao.xmn.core.base.export.tag.ListTag;
import com.xmniao.xmn.core.base.export.tag.TitleTag;
import com.xmniao.xmn.core.base.export.tag.ValueNameTag;
import com.xmniao.xmn.core.util.holder.ExecutorServiceHolder;

public class ExcelWriter {
	
	/**
	 * 当前 Excel工作簿 
	 */
	private Workbook wb;
	
	/**
	 * 最大线程树
	 *//*
	private static int threadSize=150;*/
	
	/**
	 * 解析tag 容器
	 */
	private final List<PoiTag> tags = new ArrayList<PoiTag>( Arrays.asList(new ListTag(),new ValueNameTag()));//tag解析集合
	
	/**
	 * 线程池
	 */
	private static ExecutorService executorService = ExecutorServiceHolder.getExecutorService();//线程池
	
	/**
	 * 并行策略
	 */
	private ConcurrentStrategy concurrentStrategy;
	

	/**
	 * 初始化线程池
	 *//*
	static {
		executorService = Executors.newFixedThreadPool(threadSize, new ThreadFactory() {
			AtomicInteger atomicInteger = new AtomicInteger(1);
			@Override
			public Thread newThread(Runnable r) {
				ThreadGroup group = Thread.currentThread().getThreadGroup();
				Thread thread = new Thread(group, r, "XmnWeb-ExcelWriter-Thread-"+atomicInteger.getAndIncrement());
				return thread;
			}
		});
	}*/
	
	/**
	 * 创建ExcelWriter 并且设置写入策略
	 * @param concurrentStrategy
	 */
	public ExcelWriter(ConcurrentStrategy concurrentStrategy) {
		this.concurrentStrategy = concurrentStrategy;
	}
	
	/**
	 * 开始写入Excel
	 * @param in 
	 * @param out
	 * @throws Exception
	 */
	public void writeExcel(InputStream in, OutputStream out) throws Exception {
		if(in != null && wb == null){
			wb = new HSSFWorkbook(new BufferedInputStream(in));
			writeWorkbook();
		}
		
		if(out != null){
			wb.write(out);
			in.close();
		}
	}
	
	/**
	 * 解析模版工作簿 并将数据写入Excel
	 * @throws Exception
	 */
	private void writeWorkbook() throws Exception {
		concurrentStrategy.setExecutorService(executorService);
		int sheetCount = wb.getNumberOfSheets();
		for (int i = 0; i < sheetCount; i++) {
			Sheet sheet = wb.getSheetAt(i);
			List<CellDefinition> titleCellDefinitions  = resolveSheetTitle(sheet);
			//解析
			List<CellDefinition> cellDefinitions = resolveSheetCell(sheet);
			
			if(null !=cellDefinitions && !cellDefinitions.isEmpty()){
				concurrentStrategy.concurrentWriteWorkbook(wb, cellDefinitions, titleCellDefinitions, sheet);
			}
			wb.removeSheetAt(i);
			
		}
	}
	
	/**
	 * 解析标题
	 * @param sheet
	 * @return
	 */
	private List<CellDefinition> resolveSheetTitle(Sheet sheet){
		List<CellDefinition> cellDefinitions = new ArrayList<CellDefinition>();
		/*
		 * 解析表单标题
		 */
		Row row = sheet.getRow(0);
		if(row!=null){
			PoiTag tag = 	new TitleTag();
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
	
	/**
	 * 解析表单列
	 * @param sheet
	 * @return
	 */
	private List<CellDefinition> resolveSheetCell(Sheet sheet){
		List<CellDefinition> cellDefinitions = new ArrayList<CellDefinition>();
		int lastRowNum = sheet.getLastRowNum();
		/*
		 * 跳过标题列 从第二列开始解析
		 */
		for(int i=1;i<=lastRowNum;i++){
			Row row = sheet.getRow(i);
			if(row!=null){
				Iterator<Cell> cellIterator = row.cellIterator();
				while(cellIterator.hasNext()){
					Cell cell = cellIterator.next();
					if(cell != null){
						String value = String.valueOf(PoiUtil.getValue(cell));
						for(PoiTag tag : tags){
							CellDefinition c = tag.parse(cell, value);
							if(c!=null){
								cellDefinitions.add(c);
								break;
							}	
							
						}
					}
				}
				sheet.removeRow(row);
			}
		}
		return cellDefinitions;
	}
	
	
	public static void main(String[] args) {
		System.out.println(TimeUnit.MILLISECONDS.toNanos(0l));
	}

}
