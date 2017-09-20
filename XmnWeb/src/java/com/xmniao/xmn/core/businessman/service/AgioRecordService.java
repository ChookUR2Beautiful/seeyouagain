package com.xmniao.xmn.core.businessman.service;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.businessman.dao.AgioRecordDao;
import com.xmniao.xmn.core.businessman.entity.TAgioRecord;
import com.xmniao.xmn.core.businessman.entity.TSellerAgio;
import com.xmniao.xmn.core.businessman.util.SellerConstants;
import com.xmniao.xmn.core.exception.ApplicationException;


/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：AgioRecordService
 * 
 * 类描述： 折扣设置记录
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月11日15时37分03秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
 @Service
public class AgioRecordService extends BaseService<TAgioRecord> {

	@Autowired
	private AgioRecordDao agioRecordDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return agioRecordDao;
	}
	
	
	/**
	 * 
	 * @author dong'jt
	 * 创建时间：2015年9月28日 下午3:29:57
	 * 描述：添加折扣
	 * @param sellerAgio
	 * @return
	 */
	public Resultable agioRecordAdd(TAgioRecord sellerAgio){
		Resultable resultable=null;
		this.agioRecordParams(sellerAgio);
		try {
			agioRecordDao.addReturnId(sellerAgio);
			resultable=this.agioRecordAddResInfo(sellerAgio);
		} catch (Exception e) {
			this.log.error("添加折扣：", e);
			throw new ApplicationException("添加折扣",e,new Object[]{sellerAgio},new String[]{"折扣记录ID",String.valueOf(sellerAgio.getId()),"添加折扣","添加折扣"});
		}
		return resultable;
	}
	public void agioRecordParams(TAgioRecord sellerAgio){
		sellerAgio.setSdate(new Date());
		sellerAgio.setBaseagio(new BigDecimal(sellerAgio.getBaseagio().toString()).divide(new BigDecimal(100)).doubleValue());
		sellerAgio.setIncome(new BigDecimal(sellerAgio.getIncome().toString()).divide(new BigDecimal(100)).doubleValue());
		sellerAgio.setSledger(new BigDecimal(sellerAgio.getSledger().toString()).divide(new BigDecimal(100)).doubleValue());
		sellerAgio.setYledger(new BigDecimal(sellerAgio.getYledger().toString()).divide(new BigDecimal(100)).doubleValue());
		sellerAgio.setPledger(new BigDecimal(sellerAgio.getPledger().toString()).divide(new BigDecimal(100)).doubleValue());
		sellerAgio.setOperation(SellerConstants.SELLER_AGIO_OPER_SY);
	}
	public Resultable agioRecordAddResInfo(TAgioRecord sellerAgio){
		this.log.info("添加成功");
		super.fireLoginEvent(new String[]{"折扣记录ID",String.valueOf(sellerAgio.getId()),"添加折扣","添加折扣"}, 1);
		return new Resultable(true, "操作成功");
	}
	
	public void sellerAgioInit(TSellerAgio sellerAgio,ModelAndView modelAndView){	
		sellerAgio.setBaseagio(new BigDecimal(sellerAgio.getBaseagio().toString()).multiply(new BigDecimal(100)).doubleValue());
		sellerAgio.setIncome(new BigDecimal(sellerAgio.getIncome().toString()).multiply(new BigDecimal(100)).doubleValue());
		sellerAgio.setSledger(new BigDecimal(sellerAgio.getSledger().toString()).multiply(new BigDecimal(100)).doubleValue());
		sellerAgio.setYledger(new BigDecimal(sellerAgio.getYledger().toString()).multiply(new BigDecimal(100)).doubleValue());
		sellerAgio.setPledger(new BigDecimal(sellerAgio.getPledger().toString()).multiply(new BigDecimal(100)).doubleValue());
		modelAndView.addObject("sellerAgio", sellerAgio);
	}
	
	public Resultable AgioRecordDelete(String aid){
		Resultable resultable=null;
		Integer resultNum=0;
		try {
			resultNum= super.delete(aid.split(","));
		} catch (Exception e) {
			this.log.error("折扣删除操作：", e);
			throw new ApplicationException("折扣删除操作",e,new Object[]{aid},new String[]{"折扣ID",String.valueOf(aid),"折扣删除","折扣删除"});
		}
		resultable=this.AgioRecordDeleteResInfo(resultNum, aid);
		return resultable;
	}
	public Resultable AgioRecordDeleteResInfo(Integer resultNum,String aid){
		if (resultNum > 0) {
			this.log.info("删除成功");
			super.fireLoginEvent(new String[]{"折扣ID",String.valueOf(aid),"删除折扣","删除折扣"}, 1);
			return new Resultable(true, "操作成功");
		}else{
			this.log.info("删除失败");
			super.fireLoginEvent(new String[]{"折扣ID",String.valueOf(aid),"删除折扣","删除折扣"}, 0);
			return new Resultable(true, "操作失败");
		}
	}
	
}
