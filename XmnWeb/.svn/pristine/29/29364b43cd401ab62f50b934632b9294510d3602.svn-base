package com.xmniao.xmn.core.base.export;

import java.util.concurrent.BlockingDeque;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.xmniao.xmn.core.base.export.bean.CellDetailsBean;

public class POIProcessing extends Thread{
	
	private Log log = LogFactory.getLog(POIProcessing.class);
	
	//通信队列
		public BlockingDeque<CellDetailsBean> blockingDeque;
	
	public POIProcessing(BlockingDeque<CellDetailsBean> blockingDeque) {
		this.blockingDeque = blockingDeque;
	}
	
	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			try {
				CellDetailsBean bean = blockingDeque.take();
				PoiUtil.setValueUnLock(bean.getSheet(),bean.getR(), bean.getC(),bean.getValue(), bean.getStyle(), bean.getCellType(), bean.getRowHeight());
				bean = null;
			} catch (Exception e) {
				//恢复线程中断状态
				Thread.currentThread().interrupt();
				log.error("处理导出数据队列异常", e);
			}
		}
		
	}

}
