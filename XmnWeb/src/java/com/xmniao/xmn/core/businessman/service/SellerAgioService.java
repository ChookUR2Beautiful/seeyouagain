package com.xmniao.xmn.core.businessman.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.businessman.dao.AgioRecordDao;
import com.xmniao.xmn.core.businessman.dao.SellerAgioDao;
import com.xmniao.xmn.core.businessman.entity.TAgioRecord;
import com.xmniao.xmn.core.businessman.entity.TSellerAgio;


/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：SellerAgioService
 * 
 * 类描述： 商户折扣设置
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月11日15时40分23秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
 @Service
public class SellerAgioService extends BaseService<TSellerAgio> {

	@Autowired
	private SellerAgioDao sellerAgioDao;
	
	@Autowired
	private AgioRecordDao agioRecordDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return sellerAgioDao;
	}
	
	/**
	 * 修改折扣
	 * @param tsellerAgio
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer updateadd(TSellerAgio tsellerAgio){		
		//添加一条数据到折扣记录表中
		TAgioRecord tagioRecord=new TAgioRecord();
		tagioRecord.setAid(tsellerAgio.getAid());// 折扣ID
		tagioRecord.setSellerid(tsellerAgio.getSelleridid());// 商家ID
		tagioRecord.setUid(new Integer(0));// 运营平台用户ID|商户在APP上修改时此字段为0|运营人员后台修改时为运营人员用户ID
		tagioRecord.setBaseagio(tsellerAgio.getBaseagio());// 折扣
		tagioRecord.setIncome(tsellerAgio.getIncome());// 营业收入
		tagioRecord.setOperation(new Integer(3));// 操作途径|1=APP(商户版)|2=WEB(商户WEB版)|3=SYSTEM(运营平台)
		tagioRecord.setSledger(tsellerAgio.getSledger());// 商户分账
		tagioRecord.setYledger(tsellerAgio.getYledger());// 用户分账
		tagioRecord.setPledger(tsellerAgio.getPledger());// 平台分账
		tagioRecord.setStdate(tsellerAgio.getStdate());// 起始日期
		tagioRecord.setEndate(tsellerAgio.getEndate());// 结束日期
		tagioRecord.setSdate(tsellerAgio.getSdate());// 记录时间(修改时间)
		tagioRecord.setExcdate(new Date());//执行时间
		tagioRecord.setRemarks("修改折扣设置");//备注				
		agioRecordDao.add(tagioRecord);//执行添加（添加一条数据到折扣记录表中）
		
		//修改折扣
		tsellerAgio.setSdate(new Date());
		return sellerAgioDao.update(tsellerAgio);
		
	}
	
	/**
	 * 根据商家id查询商家常规折扣
	 * @param sellerid
	 * @return
	 */
	public TSellerAgio getUsingCommonAgion(Long sellerid){
		return sellerAgioDao.getUsingCommonAgion(sellerid);
	}
	
}
