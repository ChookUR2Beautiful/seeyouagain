/**
 * 
 */
package com.xmniao.xmn.core.manor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.manor.dao.ManorFlowerRelationDao;
import com.xmniao.xmn.core.manor.dao.ManorLevelEarningRecordDao;
import com.xmniao.xmn.core.manor.entity.TManorLevelEarningRecord;
import com.xmniao.xmn.core.xmnburs.dao.BursDao;
import com.xmniao.xmn.core.xmnburs.entity.Burs;
import com.xmniao.xmn.core.xmnburs.service.BursService;


/*花蜜收益管理*/

@Service
public class NectarProfitService extends BaseService<TManorLevelEarningRecord> {

	
	/**
	 * 注入庄园(用户)服务
	 */
	@Autowired
	private ManorLevelEarningRecordDao manorLevelEarningRecordDao;
	
	/**
	 * 注入花朵关系服务
	 */
	@Autowired
	private ManorFlowerRelationDao manorFlowerRelationDao;
	
	@Autowired
	private BursDao bursDao;
	
	/**
	 * 寻蜜鸟用户Service
	 */
	@Autowired
	private BursService bursService;
	

	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return manorLevelEarningRecordDao;
	}

	
	public Pageable<TManorLevelEarningRecord> getManorLevelEarningRecordList(TManorLevelEarningRecord manorLevelEarningRecord) {
		Pageable<TManorLevelEarningRecord> manorFlowerRelationInfoList = new Pageable<TManorLevelEarningRecord>(manorLevelEarningRecord);
		
		List<TManorLevelEarningRecord> manorLevelEarningRecordList = this.searchManorLevelEarningRecordList(manorLevelEarningRecord) ;
		Long count = this.countManorLevelEarningRecord(manorLevelEarningRecord);

		manorFlowerRelationInfoList.setContent(manorLevelEarningRecordList);
		manorFlowerRelationInfoList.setTotal(count);
		
	    return manorFlowerRelationInfoList;
	}

	
	/**
	 * 方法描述：查询花蜜收益数据 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年6月21日下午3:25:18 <br/>
	 * @param manorLevelEarningRecord
	 * @return
	 */
	public List<TManorLevelEarningRecord> searchManorLevelEarningRecordList(TManorLevelEarningRecord manorLevelEarningRecord) {
		//通过会员昵称查询
		if (manorLevelEarningRecord.getNickname() != null && !"".equals(manorLevelEarningRecord.getNickname())) {
			Burs burs = new Burs();
			String nickname = manorLevelEarningRecord.getNickname();
			burs.setNname(nickname);
			List<Burs> bursList = bursService.getUrsList(burs);
			if (bursList != null && bursList.size() > 0) {
				Integer uid = bursList.get(0).getUid();
				manorLevelEarningRecord.setUid(uid);
			}
		}
		List<TManorLevelEarningRecord> manorLevelEarningRecordList = new ArrayList<TManorLevelEarningRecord>();
		
		manorLevelEarningRecordList = manorLevelEarningRecordDao.getManorLevelEarningRecordList(manorLevelEarningRecord);
		List<Integer> uids = new ArrayList<Integer>();
		for (TManorLevelEarningRecord levelEarningRecord : manorLevelEarningRecordList) {
			if (levelEarningRecord.getUid() != null)
				uids.add(levelEarningRecord.getUid());
		}
		
		//设置用户名称 电话信息
		if (uids.size() > 0){
			List<Burs> bursList = bursDao.getUrsListByUids(uids.toArray());
			for (TManorLevelEarningRecord levelEarningRecord: manorLevelEarningRecordList){
				for (Burs object : bursList) {
					if (levelEarningRecord.getUid().equals(object.getUid()) ){
						levelEarningRecord.setNickname(object.getNname());
					}
				}
			}
		}
		
		return manorLevelEarningRecordList;
	}
	
	
	public Long countManorLevelEarningRecord(TManorLevelEarningRecord manorLevelEarningRecord) {
		
		return  manorLevelEarningRecordDao.countManorLevelEarningRecord(manorLevelEarningRecord);
	}
	
	
}
