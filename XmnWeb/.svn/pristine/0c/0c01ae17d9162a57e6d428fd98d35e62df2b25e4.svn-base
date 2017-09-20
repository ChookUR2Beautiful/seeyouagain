package com.xmniao.xmn.core.marketingmanagement.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.businessman.dao.SellerDao;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.businessman.entity.TSellerLandmark;
import com.xmniao.xmn.core.marketingmanagement.dao.BargainProductDao;
import com.xmniao.xmn.core.marketingmanagement.entity.BargainProduct;
import com.xmniao.xmn.core.marketingmanagement.entity.TBargainPrice;
import com.xmniao.xmn.core.system_settings.entity.TUser;
import com.xmniao.xmn.core.util.ResultUtil;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：BargainProductService
 * 
 * 类描述：查询爆品
 * 
 * 创建人： cao'yingde
 * 
 * 创建时间：2015年06月12日17时39分38秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class BargainProductService extends BaseService<BargainProduct> {

	@Autowired
	private BargainProductDao bargainProductDao;
	
	@Autowired
	private SellerDao sellerDao;

	@Override
	protected BaseDao getBaseDao() {
		return bargainProductDao;
	}
	
	
	
	/**
	 * 添加或则编辑特价爆品订单
	 * @param seller
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void addOrUpdteBargainProduct(BargainProduct bargainProduct,HttpServletRequest request){
		if(null != bargainProduct){
			if(bargainProduct.getQuota()==0){
				bargainProduct.setQuotanum(0);
			}
			TUser user =ResultUtil.getCurrentUser(request);
			if(bargainProduct.getBpid() == null){//添加
				bargainProduct.setAdduser(user.getUserId());
				bargainProduct.setUpdateuser(user.getUserId());
				bargainProduct.setAddtime(new Date());
				bargainProduct.setUpdatetime(new Date());
				bargainProductDao.addReturnId(bargainProduct);
				
				List<TBargainPrice> list = bargainProduct.getBargainPrice();
				if(null!=list && list.size()>0){
					TBargainPrice temp=null;
					for (int i = 0; i < list.size(); i++) {
						temp=list.get(i);
						temp.setBid(bargainProduct.getBpid());
						temp.setPrice(bargainProduct.getPrice());
						bargainProductDao.addTBargainPrice(temp);
					}
				}	
			}else{
				bargainProduct.setUpdateuser(user.getUserId());
				bargainProduct.setUpdatetime(new Date());
				bargainProductDao.update(bargainProduct);
				List<TBargainPrice> list = bargainProduct.getBargainPrice();
				if(null!=list && list.size()>0){
					TBargainPrice temp=null;
					Integer bid = bargainProduct.getBpid();
					if(bid != null){
						bargainProductDao.deleteAllByBpid(bargainProduct.getBpid().longValue());
					}
					for (int i = 0; i < list.size(); i++) {
						if (list.get(i).getStartTime() !=null && list.get(i).getEndTime() !=null) {
							temp=list.get(i);
							temp.setBid(bargainProduct.getBpid());
							temp.setPrice(bargainProduct.getPrice());
							bargainProductDao.addTBargainPrice(temp);
						}
					}
				}	
			}
		}
	}
	
	/**
	 * 修改初始
	 * @author yingde'cao
	 * @param TBargainPrice
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<TBargainPrice> getBargainProduct(Long bid) {
		List<TBargainPrice> list =null;
		try {
			list = bargainProductDao.getBargainProductList(bid);
		}catch (Exception e) {
			this.log.error("查询活动规则异常", e);
		}
		return list;
	}



	/**
	 * 根据商家ID获取该商家已经被添加为爆品商家的次数
	 * @param seller
	 * @return
	 */
	public Long getSellerCount(TSeller seller) {

		return bargainProductDao.getSellerCount(seller);
	}
	
	public int updateSellerLandmark(TSellerLandmark tSellerLandmark){
		int i = 0;
		try {
		   i = bargainProductDao.updateSellerLandmark(tSellerLandmark);
		}catch (Exception e) {
			this.log.error("更新爆品经纬度service层异常", e);
		}
		return i;
	}

	/**
	 * @Description:特价爆品审核通过 
	 * @Param:bpid
	 * @return:Map<String,Object>
	 * @author:lifeng
	 * @time:2016年7月1日下午2:50:12
	 */
	public Integer putaway(String bpid) {
		BargainProduct bargainProduct = new BargainProduct();
		bargainProduct.setBpid(Integer.parseInt(bpid));
		bargainProduct.setStatus(1);//审批通过后，设置特价爆品的状态为上架
		return bargainProductDao.update(bargainProduct);
	}

	/**
	 * @Description:特价爆品审核不通过 
	 * @Param:bpid
	 * @return:Map<String,Object>
	 * @author:lifeng
	 * @time:2016年7月5日下午17:41:12
	 */
	public Integer nopass(String bpid) {
		BargainProduct bargainProduct = new BargainProduct();
		int count=0;
			try {
				bargainProduct.setBpid(Integer.parseInt(bpid));
				bargainProduct.setStatus(3);//审核不通过，设置特价爆品的状态为不通过
				count= bargainProductDao.update(bargainProduct);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return count;
	}

	/**
	 * @Description: 根据商家sellerid查询商家的信息
	 * @Param:sellerid
	 * @return:Object
	 * @author:lifeng
	 * @time:2016年7月15日下午2:40:19
	 */
	public TSeller getSellerBySellerid(Integer sellerid) {
		TSeller seller = new TSeller();
		seller.setSellerid(sellerid);
		return sellerDao.getSellerByWhere(seller);
	}
}