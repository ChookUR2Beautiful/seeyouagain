package com.xmniao.xmn.core.user_terminal.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.business_cooperation.util.PartnerConstants;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.coupon.entity.TCouponSeller;
import com.xmniao.xmn.core.user_terminal.dao.ShareDao;
import com.xmniao.xmn.core.user_terminal.entity.TShare;
import com.xmniao.xmn.core.user_terminal.entity.TShareRange;

/**
 * 
 * @项目名称：XmnWeb
 * 
 * @类名称：ShareService
 * 
 * @类描述：分享信息
 * 
 * @创建人：cao'yingde
 * 
 * @创建时间 ：2015年6月26日 下午4:29:15
 * 
 */
@Service
public class ShareService extends BaseService<TShare> {
	@Autowired
	private ShareDao shareDao;

	@Override
	protected BaseDao<TShare> getBaseDao() {
		return shareDao;
	}
	
	/**
	 * 取得更新分享信息
	 * 
	 * @param sid
	 * @param model
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void getUpdateShareInfo(Long sid, Model model) {
		TShare share = shareDao.getObject(sid);
		List<TShareRange> shareRanges = shareDao.getListByCid(sid);
		share.setShareRange(shareRanges);
		model.addAttribute("share", share);
	}
	/**
	 * 获取商家信息
	 * 
	 * @param Sellerid
	 */
	public List<TShare> getSellersBySellerid(Long sid) {
		List<TShare> shareSellers = shareDao.getListBySellerIdAndJoinSellerName(sid);
		return shareSellers;
	}
	
	/**
	 * 添加、更新分享信息
	 * 
	 * @param coupon
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void addOrUpdateShare(TShare share, Resultable resultable) {
		if (share.getSid() == null) {// 添加操作
			try {
				addShare(share, resultable);
			} catch (Exception e) {
				this.log.error(e);
				String[] couponInfo = { "分享信息名称为", share.getTitle(),"添加", "添加" };
				fireLoginEvent(couponInfo, PartnerConstants.FIRELOGIN_NUMB);
				resultable.setSuccess(false);
				resultable.setMsg("添加失败");
			}

		} else {// 更新操作
			try {
				updateShare(share, resultable);
			} catch (Exception e) {
				this.log.error(e);
				String[] couponInfo = { "分享信息名称为", share.getTitle(),"修改", "更新" };
				fireLoginEvent(couponInfo, PartnerConstants.FIRELOGIN_NUMB);
				resultable.setSuccess(false);
				resultable.setMsg("更新失败");
			}
		}
	}
	
	private void addShare(TShare share, Resultable resultable) {
		shareDao.add(share);
		this.log.info(share);
		Long id = shareDao.getMaxId();
		share.setSid(id.intValue());
		if (share.getRange() != 1) {
			List<TShareRange> list = share.getShareRange();
			if (share.getRange() == 3) {
				String sellerids = list.get(0).getRangecontent();
				if (sellerids != "") {
					shareDao.deleteAllBySid(share.getSid().longValue());
					for (String sellerid : sellerids.split(",")) {
						TShareRange temp = new TShareRange();
						temp.setSid(share.getSid());
						temp.setRangetype(share.getRange());
						temp.setRangecontent(sellerid);
						shareDao.addTShareRange(temp);
					}
				}else{
					Exception e = new Exception();
					resultable.setSuccess(false);
					resultable.setMsg("添加失败，商家不能为空！");
					return;
				}
			} else {
				TShareRange temp = null;
				if (!list.isEmpty()) {
					for (int i = 0; i < list.size(); i++) {
						temp = list.get(i);
						temp.setSid(share.getSid());
						temp.setRangetype(share.getRange());
						if (temp.getRid() == null) {
							shareDao.addTShareRange(temp);
						} else {
							shareDao.updateTShareRange(temp);
						}
					}
				}
			}
		} else {
			shareDao.deleteAllBySid(share.getSid().longValue());
		}
		// 添加优惠券日志
		String[] couponInfo = { "分享信息名称为", share.getTitle(),"添加", "添加" };
		fireLoginEvent(couponInfo, PartnerConstants.FIRELOGIN_NUMA);
		resultable.setSuccess(true);
		resultable.setMsg("添加成功");

	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = RuntimeException.class)
	private void updateShare(TShare share, Resultable resultable)throws Exception {
		shareDao.update(share);
		if(share.getRange() != 1){
			List<TShareRange> list = share.getShareRange();
			if(share.getRange()==3){
				String sellerids = list.get(0).getRangecontent();
				if(sellerids != ""){
					shareDao.deleteAllBySid(share.getSid().longValue());
					for (String sellerid : sellerids.split(",")) {
						TShareRange temp = new TShareRange();
						temp.setSid(share.getSid());
						temp.setRangetype(share.getRange());
						temp.setRangecontent(sellerid);
						shareDao.addTShareRange(temp);
					}
				}else{
					Exception e = new Exception();
					resultable.setSuccess(false);
					resultable.setMsg("更新失败，商家不能为空！");
					return;
				}
			}else{
				TShareRange temp=null;
				if(!list.isEmpty()){
					//将所有先删除再添加
					shareDao.deleteAllBySid(share.getSid().longValue());
					for (int i = 0; i < list.size(); i++) {
						if(list.get(i).getRangecontent() !=null && list.get(i).getProvince()!=null){
							temp=list.get(i);
							temp.setSid(share.getSid());
							temp.setRangetype(share.getRange());
							shareDao.addTShareRange(temp);
						}
					}
				}
			}
		}else{
			shareDao.deleteAllBySid(share.getSid().longValue());
		}
		// 更新优惠券日志
		String[] couponInfo = { "分享信息名称为", share.getTitle(),"修改", "更新" };
		fireLoginEvent(couponInfo, PartnerConstants.FIRELOGIN_NUMA);
		resultable.setSuccess(true);
		resultable.setMsg("更新成功");
	}
	
}
