package com.xmniao.xmn.core.businessman.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.billmanagerment.dao.AllBillDao;
import com.xmniao.xmn.core.billmanagerment.entity.Bill;
import com.xmniao.xmn.core.businessman.dao.SpreadDao;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.businessman.entity.TSpread;
import com.xmniao.xmn.core.businessman.util.SellerConstants;
import com.xmniao.xmn.core.util.DateHelper;

/**
 *@ClassName:SpredService
 *@Description:商家服务员推广service层
 *@author hls
 *@date:2016年2月27日下午7:52:32
 */
@Service
public class SpredService extends BaseService<TSpread> {
	
	@Autowired
	private SpreadDao spreadDao;

	@Autowired
	private AllBillDao allBillDao;
	
	@Override
	protected SpreadDao getBaseDao() {
		return spreadDao;
	}
	
	/**
	 * @Title:addSellerSpread
	 * @Description:添加商家服务员推广配置信息
	 * @param tSpread
	 * @param request
	 * @return Resultable
	 * @throw
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Resultable addSellerSpread(TSpread tSpread, HttpServletRequest request) {
		Resultable resultable = new Resultable();
		this.log.info("SpredService-->TSpread=" + tSpread);
		//将form元素的服务员账号Phoneid拆分成服务员账号、账号ID、商家名称
		String phoneid = tSpread.getPhoneid();
		String[] phoneidStr = phoneid.split(",");
		String account = phoneidStr[0];//服务员账号
		int aid = Integer.parseInt(phoneidStr[1]);//服务员账号ID
		String sellername = phoneidStr[2];//商家名称
		tSpread.setSellername(sellername);
		tSpread.setAccount(account);
		tSpread.setAid(aid);
		//获取当前系统时间作为创建时间
		String rdate = DateHelper.getDateFormatter();
		tSpread.setRdate(rdate);
		spreadDao.add(tSpread);
		resultable.setSuccess(true);
		return resultable;
	}
	/**
	 * @Title:updateSellerSpread
	 * @Description:修改商家服务员推广信息
	 * @param tSpread
	 * @param request
	 * @return Resultable
	 * @throw
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Resultable updateSellerSpread(TSpread tSpread, HttpServletRequest request) {
		Resultable resultable = new Resultable();
		this.log.info("SpredService-->TSpread=" + tSpread);
		//获取当前系统时间作为更新时间
		String rdate = DateHelper.getDateFormatter();
		tSpread.setUdate(rdate);
		spreadDao.update(tSpread);
		resultable.setSuccess(true);
		return resultable;
	}
	
	/**
	 * @Title:addOrUpdteSpread
	 * @Description:添加或修改商家服务员推广
	 * @param tSpread
	 * @param request
	 * @return Resultable
	 * @throw
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Resultable addOrUpdteSpread(TSpread tSpread,
			HttpServletRequest request) {
		Resultable resultable = new Resultable();
		this.log.info("spredService-->TSpread" + tSpread);
		if(tSpread.getId()!=0){//添加
			//获取当前系统时间作为创建时间
			String rdate = DateHelper.getDateFormatter();
			tSpread.setRdate(rdate);
			spreadDao.add(tSpread);
			resultable.setSuccess(true);
		}else{//修改
			//获取当前系统时间作为更新时间
			String udate = DateHelper.getDateFormatter();
			tSpread.setUdate(udate);
			spreadDao.update(tSpread);
			resultable.setSuccess(true);
		}
		return resultable;
	}
	/**
	 * 初始化服务员推广配置(用于服务员推广配置修改时的初始化数据)
	 */
	public void initTSpreadInfo(Integer id, ModelAndView model) {
		if(null != id){
			TSpread tSpreadList = getObject(id.longValue());
			model.addObject("tSpreadList", tSpreadList);
		}
	}
	/**
	 * 获取商家列表(只查询已推广配置的商家)
	 * 
	 * @param seller
	 * @return
	 */
	public Pageable<TSeller> getListTospread(TSeller seller) {
		Pageable<TSeller> sellerlst = new Pageable<TSeller>(seller);
		// 已签约的商家
		seller.setStatus(SellerConstants.SELLER_STATUS_APPROVE);
		// 商家列表内容
		sellerlst.setContent(spreadDao.getListTospread(seller));
		sellerlst.setTotal(spreadDao.countTospread(seller));
		return sellerlst;
	}
	/**
	 * @Title:getListToConfig
	 * @Description:获取商家列表（已上线已签约的商家并且未配置）
	 * @param seller
	 * @return Pageable<TSeller>
	 * @throw
	 */
	public Pageable<TSeller> getListToConfig(TSeller seller) {
		Pageable<TSeller> sellerlst = new Pageable<TSeller>(seller);
		// 已签约的商家
		seller.setStatus(SellerConstants.SELLER_STATUS_APPROVE);
		//已上线
		seller.setIsonline(1);
		//不是虚拟商户
		seller.setIsVirtual(0);
		//不是连锁商家(默认)
		seller.setIsmultiple(0);
		// 商家列表内容
		sellerlst.setContent(spreadDao.getListToConfig(seller));
		sellerlst.setTotal(spreadDao.countToConfig(seller));
		return sellerlst;
	}
	
	/**
	 * @Title:vailStaff
	 * @Description:根据业务员账号查询业务员表是否有记录
	 * @param staff
	 * @return Integer
	 * @throw
	 */
	public boolean vailStaff(TSeller seller){
		//获取添加的业务员账号
		String phoneidStr = seller.getPhoneid();
	      String[] str = phoneidStr.split(",");
	      String phoneid = str[0];//选择第1个元
		int idcount = spreadDao.vailStaff(new Long(phoneid));
		if(idcount > 0){
			return false;
		}
		return true;
	}
	public List<TSpread> getListDeail(TSpread tSpread){
		return spreadDao.getListDeail(tSpread);
	}
	/**
	 * 获取对应商家服务员推广的订单
	 */
	public List<Bill> getSellerSpreadBillList(String date){
		
		return allBillDao.getSpreadBillList(date);
	}

	/**
	 * 根据商户ID删除商家服务员推广管理
	 * @param aid
	 */
	public void deleteSellerSpread(Object[] aid) {
		spreadDao.deleteSpreadByAids(aid);
	}
}
