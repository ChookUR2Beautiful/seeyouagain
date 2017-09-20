package com.xmniao.xmn.core.base.export;

import java.util.List;
import java.util.concurrent.BlockingDeque;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.LazyDynaBean;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.xmniao.xmn.core.base.export.bean.CellDefinition;
import com.xmniao.xmn.core.base.export.bean.CellDetailsBean;
import com.xmniao.xmn.core.base.export.bean.PoiThreadExportDefinition;

public class DataProcessing<T> extends Thread{
	private static final Log log = LogFactory.getLog(DataProcessing.class);

	private PoiThreadExportDefinition poiTheadExportDefinition;//导出数据信息
	
	private int sheetRowNumber=0;//当前行数
	
	private  BlockingDeque<CellDetailsBean> blockingDeque;
	
	private Object servise;
	
	private int limit;
	
	private int page;
	
	private QueryCallback<T> callback;
	private BeanCopyCallback<T> copyCallback;
	public DataProcessing(PoiThreadExportDefinition poiTheadExportDefinition,BlockingDeque<CellDetailsBean> blockingDeque,QueryCallback<T> callback,BeanCopyCallback<T> copyCallback,Object servise,int page,int limit){
		this.poiTheadExportDefinition = poiTheadExportDefinition;
		this.blockingDeque = blockingDeque;
		this.callback = callback;
		this.copyCallback = copyCallback;
		this.servise = servise;
		this.limit = limit;
		this.page = page;
	}
	
	
	public PoiThreadExportDefinition getPoiTheadExportDefinition() {
		return poiTheadExportDefinition;
	}


	
	public int getSheetRowNumber() {
		return sheetRowNumber;
	}


	@Override
	public void run() {
		try{
			List<?> datas = callback.query(servise,copyCallback.copy(),page+1,limit);
			List<CellDefinition> cellDefinitions = poiTheadExportDefinition.getCellDefinitions();
			CellDefinition c = cellDefinitions.get(0);//获取foreach标签值
			int starIndex =  poiTheadExportDefinition.getStarIndex(),cellLenth = cellDefinitions.size(),datasLength = datas.size();
			DynaBean context = new LazyDynaBean();
			setTitleValue();
			/**
			 * 遍历行并设置列的值
			 */
			for(int rowCount=0;rowCount<datasLength;rowCount++){
				if(rowCount<datasLength){
					Object obj = datas.get(rowCount);
					if(obj!=null){
						context.set(c.getPrefix(), obj);
						/**
						 * 跳过foreach 标签 设置列的值
						 */
						for(int cellCount=1;cellCount<cellLenth;cellCount++){
							CellDefinition cellDefinition =cellDefinitions.get(cellCount);
							CellDefinition title = poiTheadExportDefinition.getTitleCellDefinitions().get(cellCount-1);
							if(title!=null){
								cellDefinition.setRowHeight(title.getRowHeight());
							}
							if(cellCount==1){
								setCellValue(cellDefinition,starIndex+rowCount+1);
							}else{
								if(cellDefinition!=null){
									Object value = getValue(context,cellDefinition.getKey());
									setCellValue(cellDefinition,value);
								}	
							}
						}
					}
					sheetRowNumber++;
				}else{
					break;
				}
			}
			context = null;
		}catch(Exception e){
			
			log.error(currentThread().getName()+"导出异常", e);
		}finally{
			poiTheadExportDefinition.getRunningThreadNum().countDown();
		}
	}
	
	/**
	 * 设置标题
	 * @throws Exception 
	 */
	private void setTitleValue() throws Exception{
		List<CellDefinition> cellDefinitions = poiTheadExportDefinition.getTitleCellDefinitions();
		for(int i=0;i<cellDefinitions.size();i++){
			CellDefinition  cellDefinition = cellDefinitions.get(i);
			setCellValue(cellDefinition,cellDefinition.getKey());
		}
		sheetRowNumber++;
	}
	
	
	/**
	 * 获取指定字段值
	 * @param bean
	 * @param key
	 * @return
	 */
	public  Object getValue(Object bean, String key) {
		Object value = null;
		try {
			value = PropertyUtils.getProperty(bean, key);
			if(value==null){
				value = "-";
			}
		} catch (Exception e) {
			value = "-";
		}
		return value;
	}

	protected void setCellValue(CellDefinition cellDefinition, Object value) throws InterruptedException {
		CellDetailsBean bean = new CellDetailsBean();
		bean.setSheet(getPoiTheadExportDefinition().getSheet());
		bean.setCellType(cellDefinition.getCellType());
		bean.setC(cellDefinition.getColumn());
		bean.setR(getSheetRowNumber());
		bean.setRowHeight(cellDefinition.getRowHeight());
		bean.setValue(value);
		bean.setStyle(cellDefinition.getStyle());
		blockingDeque.put(bean);
		//PoiUtil.setValue(getPoiTheadExportDefinition().getSheet(),getSheetRowNumber(), cellDefinition.getColumn(),value, cellDefinition.getStyle(), cellDefinition.getCellType(), cellDefinition.getRowHeight());
		
	}

}
