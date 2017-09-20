package com.xmniao.xmn.core.base.export;

import java.util.List;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.LazyDynaBean;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.xmniao.xmn.core.base.export.bean.CellDefinition;
import com.xmniao.xmn.core.base.export.bean.PoiThreadExportDefinition;

public class PoiThread extends Thread{
	
	private static final Log log = LogFactory.getLog(PoiThread.class);
	
	private PoiThreadExportDefinition poiTheadExportDefinition;//导出数据信息
	
	private int sheetRowNumber=0;//当前行数
	
	public PoiThread(PoiThreadExportDefinition poiTheadExportDefinition){
		this.poiTheadExportDefinition=poiTheadExportDefinition;
	}

	@Override
	public void run() {
		log.info(currentThread().getName()+"--开始导出");
		List<CellDefinition> cellDefinitions = poiTheadExportDefinition.getCellDefinitions();
		List<?> datas = poiTheadExportDefinition.getDatas();
		CellDefinition c = cellDefinitions.get(0);//获取foreach标签值
		int cellLenth = cellDefinitions.size();
		int datasLength = datas.size();
		
		try{
			DynaBean context = new LazyDynaBean();
			Long s = System.currentTimeMillis();
			setTitleValue();
			/**
			 * 遍历行并设置列的值
			 */
			for(int rowCount=poiTheadExportDefinition.getStarIndex();rowCount<poiTheadExportDefinition.getEndIndex();rowCount++){
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
								setValue(cellDefinition,rowCount+1);
							}else{
								if(cellDefinition!=null){
									Object value = getValue(context,cellDefinition.getKey());
									setValue(cellDefinition,value);
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
			Long e = System.currentTimeMillis();
			log.info(currentThread().getName()+"-----导出完成 , 耗时时间--"+(e-s)+" ms");
		}catch(Exception e){
			log.error(currentThread().getName()+"导出异常", e);
		}finally{
			poiTheadExportDefinition.getRunningThreadNum().countDown();
		}
	}
	
	
	private void setTitleValue(){
		List<CellDefinition> cellDefinitions = poiTheadExportDefinition.getTitleCellDefinitions();
		for(int i=0;i<cellDefinitions.size();i++){
			CellDefinition  cellDefinition = cellDefinitions.get(i);
			setValue(cellDefinition,cellDefinition.getKey());
		}
		sheetRowNumber++;
	}
	
	
	private void setValue(CellDefinition cellDefinition,Object value){
		PoiUtil.setValue(poiTheadExportDefinition.getSheet(),sheetRowNumber, cellDefinition.getColumn(),value, cellDefinition.getStyle(), cellDefinition.getCellType(), cellDefinition.getRowHeight());
		
	}
	
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
	
}
