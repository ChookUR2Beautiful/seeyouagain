package com.xmniao.xmn.core.base.export.strategy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.xmniao.xmn.core.base.export.BeanCopyCallback;
import com.xmniao.xmn.core.base.export.DataProcessing;
import com.xmniao.xmn.core.base.export.POIProcessing;
import com.xmniao.xmn.core.base.export.QueryCallback;
import com.xmniao.xmn.core.base.export.bean.CellDefinition;
import com.xmniao.xmn.core.base.export.bean.CellDetailsBean;
import com.xmniao.xmn.core.base.export.bean.PoiThreadExportDefinition;

@SuppressWarnings("hiding")
public class MultipleQueriesConcurrentStrategy<T> extends  AbstractConcurrentStrategy {
	
	private POIProcessing poiProcessing;
	
	//通信队列
	public BlockingDeque<CellDetailsBean> blockingDeque ;
	
	/**
	 * 深拷贝回调函数
	 */
	private final BeanCopyCallback<T> copyCallback = new BeanCopyCallback<T>() {
		@Override
		public T copy() throws Exception {
			synchronized(t){
				ByteArrayOutputStream b = new ByteArrayOutputStream(1024);
				ObjectOutputStream objOut = new ObjectOutputStream(b);
			
				objOut.writeObject(t);
				
				ByteArrayInputStream  bin=new ByteArrayInputStream(b.toByteArray());
				ObjectInputStream objIn = new ObjectInputStream(bin);
				@SuppressWarnings("unchecked")
				T copyObj = (T)objIn.readObject();
				objOut.close();
				objIn.close();
				return copyObj;
			}
			
		}
	};
	 
	//查询实体
	private final T t;
	 
	private Object obj;
	private QueryCallback<T> callback;
	
	public MultipleQueriesConcurrentStrategy(Long size,QueryCallback<T> callback,T t,Object obj) {
		this(size,minSize,callback,t,obj);
	}
	
	public MultipleQueriesConcurrentStrategy(Long size,int minSize,QueryCallback<T> callback,T t,Object obj) {
		super(minSize,size);
		this.t = t;
		this.callback = callback;
		this.obj = obj;
		this.blockingDeque = new LinkedBlockingDeque<CellDetailsBean>(size.intValue());
		
	}
	
	

	@Override
	protected void before(Workbook wb, List<CellDefinition> cellDefinitions,List<CellDefinition> titleCellDefinitions, Sheet sourceSheet) {
		if(runThreadCount>0){
			poiProcessing = new POIProcessing(blockingDeque);
			poiProcessing.start();
		}else{
			super.before(wb, cellDefinitions, titleCellDefinitions, sourceSheet);
		}
	}

	@Override
	protected void custom(int runThreadIndex,PoiThreadExportDefinition poiThreadExportDefinitio) {
		executorService.execute(new DataProcessing<T>(poiThreadExportDefinitio, blockingDeque,callback,copyCallback,obj,runThreadIndex,minSize));
	}

	@Override
	protected void after() {
		while(true){
			if(blockingDeque.isEmpty()){
				break;
			}
		}
	}
	
	


}
