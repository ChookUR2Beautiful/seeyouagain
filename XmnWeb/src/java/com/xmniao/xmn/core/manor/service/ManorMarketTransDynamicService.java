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
import com.xmniao.xmn.core.manor.dao.ManorMarketTransDynamicDao;
import com.xmniao.xmn.core.manor.entity.TManorMarketTransDynamic;
import com.xmniao.xmn.core.xmnburs.dao.BursDao;
import com.xmniao.xmn.core.xmnburs.entity.Burs;


/*市集管理*/

@Service
public class ManorMarketTransDynamicService extends BaseService<TManorMarketTransDynamic> {

	
	/**
	 * 注入市集管理服务
	 */
	@Autowired
	private ManorMarketTransDynamicDao manorMarketTransDynamicDao;
	
	@Autowired
	private BursDao bursDao;


	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return manorMarketTransDynamicDao;
	}

	
	public Pageable<TManorMarketTransDynamic> getManorMarketTransDynamicList(TManorMarketTransDynamic manorMarketTransDynamic) {
		Pageable<TManorMarketTransDynamic> manorFlowerRelationInfoList = new Pageable<TManorMarketTransDynamic>(manorMarketTransDynamic);
		
		List<TManorMarketTransDynamic> manorMarketTransList = this.searchManorMarketTransList(manorMarketTransDynamic) ;
		Long count = this.countManorMarketTransDynamic(manorMarketTransDynamic);

		manorFlowerRelationInfoList.setContent(manorMarketTransList);
		manorFlowerRelationInfoList.setTotal(count);
		
	    return manorFlowerRelationInfoList;
	}

	
	public List<TManorMarketTransDynamic> searchManorMarketTransList(TManorMarketTransDynamic manorMarketTransDynamic) {
//		manorMarketTransDynamic.setDialogueType(1);  //动态类型 1.出价(留言) 2.回复
		List<TManorMarketTransDynamic> manorMarketTransList = manorMarketTransDynamicDao.getList(manorMarketTransDynamic);
		if (manorMarketTransList != null && manorMarketTransList.size() > 0)
            this.initManorMarketTransDynamicReversion(manorMarketTransList, manorMarketTransDynamic);
		
		
		return manorMarketTransList;
	}
	
	public void initManorMarketTransDynamicReversion(List<TManorMarketTransDynamic> manorMarketTransList, TManorMarketTransDynamic manorMarketTransDynamic){
		List<Integer> uids = new ArrayList<Integer>();
		for (TManorMarketTransDynamic bean:manorMarketTransList){  //回复内容
			if (bean.getUid() != null)
               uids.add(bean.getUid());
		}
		//设置用户名称 电话信息
		if (uids.size() > 0){
			List<Burs> bursList = bursDao.getUrsListByUids(uids.toArray());
			for (TManorMarketTransDynamic manorMarketTransDynamicRecord: manorMarketTransList){
				for (Burs object : bursList) {
					if (manorMarketTransDynamicRecord.getUid().equals(object.getUid()) ){
						manorMarketTransDynamicRecord.setNname(object.getNname());
						manorMarketTransDynamicRecord.setPhone(object.getPhone());
					}
				}
			}
		}
		
		//查询出售/求购 所有的回复
		TManorMarketTransDynamic reversion = new TManorMarketTransDynamic();
		reversion.setTransId(manorMarketTransDynamic.getTransId());
		reversion.setDialogueType(2);//动态类型 1.出价(留言) 2.回复
		reversion.setLimit(-1);
		List<TManorMarketTransDynamic> manorMarketTransRecordList = manorMarketTransDynamicDao.getList(reversion);
		if ( manorMarketTransRecordList != null && manorMarketTransRecordList.size() > 0) {
			for (TManorMarketTransDynamic bean:manorMarketTransList){  //回复内容
				String reversionContent = "";
				int count = 1;
				for (TManorMarketTransDynamic object:manorMarketTransRecordList){  //bean.getId().equals(object.getPid())  &&
					if ( bean.getId().equals(object.getPriceId()) ){
						reversionContent += object.getContent() + "(第"+count+"次回复)" + "<br />";
						count ++;
					}
				}
				if  (!"".equals(reversionContent)){
					bean.setReversion(reversionContent);
				}
			}
		}
	}
	
	
	
	/**
	 * 方法描述：统计市集管理 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年7月7日上午11:00:36 <br/>
	 * @param manorMarketTrans
	 * @return
	 */
	public Long countManorMarketTransDynamic(TManorMarketTransDynamic manorMarketTransDynamic) {
		Long count = manorMarketTransDynamicDao.count(manorMarketTransDynamic);
		return count;
	}
	
}
