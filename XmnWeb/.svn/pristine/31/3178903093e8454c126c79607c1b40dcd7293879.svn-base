package com.xmniao.xmn.core.base.export.strategy;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.xmniao.xmn.core.base.export.PoiThread;
import com.xmniao.xmn.core.base.export.bean.CellDefinition;
import com.xmniao.xmn.core.base.export.bean.PoiThreadExportDefinition;
import com.xmniao.xmn.core.util.StringUtils;

public class OnceQueryConcurrentStrategy extends  AbstractConcurrentStrategy {
	
	private List<?> datas;
	private Map<String, Object> map;
	public OnceQueryConcurrentStrategy(Map<String, Object> map) {
		super(((Long)map.get("size")));
		this.map = map;
	}


	
	@Override
	protected void before(Workbook wb, List<CellDefinition> cellDefinitions,
			List<CellDefinition> titleCellDefinitions, Sheet sourceSheet) {
		if(runThreadCount>0){
			for(String key : map.keySet()){
				if(StringUtils.hasLength(key) && !key.equals("size")){
					datas =(List<?>)map.get(key);
				}	
			}
		}else{
			super.before(wb, cellDefinitions, titleCellDefinitions, sourceSheet);
		}
		
	}

	@Override
	protected void custom(int runThreadIndex,PoiThreadExportDefinition poiThreadExportDefinitio) {
		poiThreadExportDefinitio.setDatas(datas);
		PoiThread poiThread = new PoiThread(poiThreadExportDefinitio);
		executorService.execute(poiThread);
	}

	@Override
	protected void after() {
		map=null;
	}	

}
