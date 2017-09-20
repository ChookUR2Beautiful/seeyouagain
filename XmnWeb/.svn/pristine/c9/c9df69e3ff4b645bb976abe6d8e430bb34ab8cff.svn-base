package com.xmniao.xmn.core.billmanagerment.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.billmanagerment.dao.AdjustmentBillDao;
import com.xmniao.xmn.core.billmanagerment.entity.AdjustApply;
import com.xmniao.xmn.core.util.DateUtil;

@Service
public class AdjustmentBillService extends BaseService<AdjustApply> {
	@Autowired
	private AdjustmentBillDao adjustmentBillDao;
	@Override
	protected BaseDao getBaseDao() {
		return adjustmentBillDao;
	}
    /**
     * @author dong'jietao 
     * @date 2015年7月3日 上午11:34:58
     * @TODO 调单list
     * @param tAdjustApply
     * @param pageable
     */
    public void putListPage(AdjustApply tAdjustApply,Pageable<AdjustApply> pageable){
    	try {
			pageable.setContent(adjustmentBillDao.getList(tAdjustApply));
			pageable.setTotal(adjustmentBillDao.count(tAdjustApply));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    /**
     * @author dong'jietao 
     * @date 2015年7月3日 下午4:03:39
     * @TODO 调单审核逻辑
     * @param tAdjustApply
     * @param resultable
     */
    public void toExamine(AdjustApply tAdjustApply,Resultable resultable){
    	int num=0;
    	try {
    		tAdjustApply.setPdate(DateUtil.smartFormat(DateUtil.smartFormat(new Date())));
			num=adjustmentBillDao.update(tAdjustApply);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(num==1){
	    		resultable.setSuccess(true);
	    		resultable.setMsg("操作成功");
	    	}else{
	    		resultable.setSuccess(false);
	    		resultable.setMsg("操作失败");
	    	}
			String[] s={"调单ID",String.valueOf(tAdjustApply.getAdid()),"调单审核","审核"};
			this.fireLoginEvent(s, resultable.getSuccess()?1:0);
		}
    	
    }

}
