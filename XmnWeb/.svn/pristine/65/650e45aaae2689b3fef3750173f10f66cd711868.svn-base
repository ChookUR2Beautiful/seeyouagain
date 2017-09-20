/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.live_anchor.dao.TLiveLedgerRecordDao;
import com.xmniao.xmn.core.live_anchor.entity.TLiveLedgerRecord;
import com.xmniao.xmn.core.xmnburs.entity.Burs;
import com.xmniao.xmn.core.xmnburs.service.BursService;

/**
 * 
 * 项目名称：XmnWeb_LIVE_170105
 * 
 * 类名称：TLiveLedgerRecordService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-31 上午11:55:53 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class TLiveLedgerRecordService extends BaseService<TLiveLedgerRecord> {
	
	@Autowired
	private TLiveLedgerRecordDao liveLedgerRecordDao;
	
	/**
	 * 注入寻蜜鸟用户服务
	 */
	@Autowired
	private BursService bursService;

	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return liveLedgerRecordDao;
	}

	/**
	 * 方法描述：获取直播分账信息列表 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-31下午2:16:36 <br/>
	 * @param liveLedgerRecord
	 * @return
	 */
	public List<TLiveLedgerRecord> getListContainUrsInfo(
			TLiveLedgerRecord liveLedgerRecord) {
		List<TLiveLedgerRecord> liveLedgerRecordList=new ArrayList<TLiveLedgerRecord>();
		List<Object> uids=new ArrayList<Object>();
		List<TLiveLedgerRecord> list = liveLedgerRecordDao.getList(liveLedgerRecord);
		
		if(list!=null && list.size()>0){
			for(TLiveLedgerRecord record:list){
				uids.add(record.getUid());
			}
			
			List<Object> uidList = new ArrayList<>(new HashSet<>(uids));//去除重复uid
			List<Burs> ursList = bursService.getUrsListByUids(uidList.toArray());
			
			if(ursList!=null && ursList.size()>0){
				for(TLiveLedgerRecord record:list){
					for(Burs urs:ursList){
						if(urs.getUid().compareTo(record.getUid())==0){
							record.setNname(urs.getNname());
							record.setPhone(urs.getPhone());
							break;
						}
					}
					liveLedgerRecordList.add(record);
				}
			}
			
		}
		
		return liveLedgerRecordList;
	}

}
