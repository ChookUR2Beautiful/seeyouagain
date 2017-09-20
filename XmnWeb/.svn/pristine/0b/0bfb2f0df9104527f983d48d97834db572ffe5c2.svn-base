package com.xmniao.xmn.core.xmermanagerment.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.billmanagerment.entity.Bill;
import com.xmniao.xmn.core.businessman.util.SellerConstants;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.xmermanagerment.dao.BXmerDao;
import com.xmniao.xmn.core.xmermanagerment.dao.XmerInfoDao;
import com.xmniao.xmn.core.xmermanagerment.entity.BXmer;
import com.xmniao.xmn.core.xmermanagerment.entity.BXmerWallet;
import com.xmniao.xmn.core.xmermanagerment.entity.BXmerWalletRecord;
import com.xmniao.xmn.core.xmermanagerment.entity.TSaasOrder;
import com.xmniao.xmn.core.xmermanagerment.entity.XmerInfoBean;
import com.xmniao.xmn.core.xmermanagerment.entity.XmerSeller;
import com.xmniao.xmn.core.xmnpay.dao.WalletDao;
import com.xmniao.xmn.core.xmnpay.entity.Bwallet;

/**
 *@ClassName:BXmerService
 *@Description:寻蜜客成员管理service层
 *@author hls
 *@date:2016年5月25日上午11:14:45
 */
@Service
public class BXmerService extends BaseService<BXmer>{ 
	@Autowired
	FinanceService financeService;
	@Autowired
	private BXmerDao bxmerDao;
	@Autowired
	private WalletDao walletDao;
	
	@Autowired
	private XmerInfoDao xmerInfoDao;
	
	/**
	 * 注入寻蜜客钱包服务
	 */
	@Autowired
	private BXmerWalletService xmerWalletService;
	
	/**
	 * 注入寻蜜客钱包交易记录服务
	 */
	@Autowired
	private BXmerWalletRecordService xmerWalletRecordService;
	
	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return bxmerDao;
	}
	
	/**
	 * @Title:selectXmerInfoList
	 * @Description:查询寻蜜客成员列表
	 * @param bxmer
	 * @return List<BXmer>
	 * @throw
	 */
	public List<BXmer> selectXmerInfoList(BXmer bxmer){
		List<BXmer> xmerList = new ArrayList<BXmer>();
		xmerList = bxmerDao.getXmerList(bxmer);
		if(null != xmerList && 0 != xmerList.size()){
			StringBuilder sb = new StringBuilder();
			int uid = 0;
			for (int i = 0; i < xmerList.size(); i++) {
				uid = xmerList.get(i).getUid();
				sb = sb.append(uid + ",");
			}
			
			Object[] uids = sb.toString().split(",");
			
			/*BigDecimal income = null;//总收入=收益余额+转出总额(b_xmer_wallet)
			BigDecimal commision = null;//佣金
		    BigDecimal balance = null;//流水收入
		    */		    
		    List<Integer> xids=new ArrayList<Integer>();//寻蜜客钱包ID
		    
		    List<BXmerWallet> xmerWalletList = xmerWalletService.getListByUids(uids);
		    
		    for(BXmer xmer:xmerList){
		    	for(BXmerWallet xmerWallet:xmerWalletList){
		    		boolean isEqual=xmer.getUid()!=null && xmerWallet.getUid()!=null && xmer.getUid().compareTo(xmerWallet.getUid())==0;
		    		if(isEqual){
		    			xmer.setIncome(xmerWallet.getProfit().add(xmerWallet.getTrunout()));
		    			xmer.setDifference(xmerWallet.getProfit().doubleValue());
		    			xmer.setProfit(xmerWallet.getProfit());
		    			xmer.setTrunout(xmerWallet.getTrunout());
		    			xmer.setXid(xmerWallet.getId());
		    			xids.add(xmerWallet.getId());
		    			break;
		    		}
		    	}
		    }
		    
		    if(!xids.isEmpty()){//寻蜜客钱包存在
		    	List<BXmerWalletRecord> xmerWalletRecordList = xmerWalletRecordService.getListByXids(xids.toArray());
		    	for(BXmer xmer:xmerList){
			    	for(BXmerWalletRecord record:xmerWalletRecordList){
			    		boolean isEqual=xmer.getXid() != null && record.getXid() != null && xmer.getXid().compareTo(record.getXid())==0;
			    		if(isEqual){
			    			xmer.setCommision(record.getCommision());
			    			xmer.setBalance(record.getBalance());
			    			break;
			    		}
			    	}
			    }
		    }
		    
		    //调整寻蜜客金额信息数据来源  	modify by huang'tao 2016-09-09 
		    /*Integer wid = null;//钱包id
		    List<Bwallet> walletList = financeService.selectWallet(sbStr);
			for (int i = 0; i < walletList.size(); i++) {
				income =  walletList.get(i).getIncome();
				commision = walletList.get(i).getCommision();
				balance = walletList.get(i).getBalance();
				wid = walletList.get(i).getAccountid();
				for (int j = 0; j < xmerList.size(); j++) {
					int xmUid = xmerList.get(j).getUid();
					int waUid = walletList.get(i).getUid();
					if(xmUid == waUid){
						xmerList.get(j).setIncome(income);;
						xmerList.get(j).setCommision(commision);
						xmerList.get(j).setBalance(balance);
						xmerList.get(j).setAccountid(wid);
					}
				}
			}
			
			List<Bill> billList = financeService.getBillListByUids(sbStr);
			Double sumMoney = null;
			for (int j = 0; j < billList.size(); j++) {
				sumMoney = billList.get(j).getSumMoney();
				int waUid = billList.get(j).getUid();
				for (int k = 0; k < xmerList.size(); k++) {
					int xmUid = xmerList.get(k).getUid();
					if(xmUid == waUid){
						xmerList.get(k).setSumMoney(sumMoney);
					}
				}
			}*/
			
			
			
			//统计客户端出售总额
			xmerList.get(0).setSellCount(bxmerDao.getSellCount(1));
			//统计平台商铺流水总额
			xmerList.get(0).setSellerCrrentCount(bxmerDao.getSellerCrrentCount(1));
			//576(8折)套餐单价剩余套数
			List<TSaasOrder> eightFoldNumsList = new ArrayList<TSaasOrder>();
			eightFoldNumsList = bxmerDao.eightFoldNumsList(uids);
			Integer eightFoldNums = 0;
			for (int j = 0; j < eightFoldNumsList.size(); j++) {
				eightFoldNums = eightFoldNumsList.get(j).getStock();
				int saasOrderUid = eightFoldNumsList.get(j).getUid();
				for (int k = 0; k < xmerList.size(); k++) {
					int xmUid = xmerList.get(k).getUid();
					if(xmUid == saasOrderUid){
						xmerList.get(k).setEightFoldNums(eightFoldNums);
					}
				}
			}
			//504(7折)套餐单价剩余套数
			List<TSaasOrder> sevenFoldNumsList = new ArrayList<TSaasOrder>();
			sevenFoldNumsList = bxmerDao.sevenFoldNumsList(uids);
			Integer sevenFoldNums = 0;
			for (int j = 0; j < sevenFoldNumsList.size(); j++) {
				sevenFoldNums = sevenFoldNumsList.get(j).getStock();
				int saasOrderUid = sevenFoldNumsList.get(j).getUid();
				for (int k = 0; k < xmerList.size(); k++) {
					int xmUid = xmerList.get(k).getUid();
					if(xmUid == saasOrderUid){
						xmerList.get(k).setSevenFoldNums(sevenFoldNums);
					}
				}
			}
			//签约数量
			List<TSaasOrder> numsList = new ArrayList<TSaasOrder>();
			numsList = bxmerDao.numsList(uids);
			Integer nums = 0;
			for (int j = 0; j < numsList.size(); j++) {
				nums = numsList.get(j).getNums();
				int saasOrderUid = numsList.get(j).getUid();
				for (int k = 0; k < xmerList.size(); k++) {
					int xmUid = xmerList.get(k).getUid();
					if(xmUid == saasOrderUid){
						xmerList.get(k).setNums(nums);
						Integer i = xmerList.get(k).getEightFoldNums()+xmerList.get(k).getSevenFoldNums();
						xmerList.get(k).setStockTotal(i.toString()+"/"+nums);
						DecimalFormat  df = new DecimalFormat("0.00");
					    Double d = new Double(xmerList.get(k).getEightFoldNums()*576+xmerList.get(k).getSevenFoldNums()*504);
					    xmerList.get(k).setStockTotalPrice(df.format(d));
					}
				}
			}
		}
		return xmerList;
	}
	/**
	 * @Title:xmerInfoCount
	 * @Description:查询寻蜜客成员列表总条数
	 * @param bxmer
	 * @return long
	 * @throw
	 */
	public long xmerInfoCount(BXmer bxmer){
		return bxmerDao.getXmerCount(bxmer);
	}
	/**
	 * @Title:updateXmer
	 * @Description:修改寻蜜客成员
	 * @param bxmer void
	 * @throw
	 */
	public void updateXmer(BXmer bxmer){
		try {
			bxmerDao.updateXmer(bxmer);
			bxmerDao.updateUrsInfo(bxmer);
		} catch (Exception e) {
			this.log.error("修改寻蜜客成员异常：", e);
			throw new ApplicationException("修改寻蜜客成员更新异常",e,new Object[]{bxmer});
		}
	}
	
	/**
	 * @Title:getXmer
	 * @Description:根据寻蜜客编号查询导航图详情
	 * @param id
	 * @return BXmer
	 * @throw
	 */
	public BXmer getXmer(Integer id){		
		BXmer bxmer = bxmerDao.getXmer(id);
		return bxmer;
	}
	
	/**
	 * @Title:exportXmerList
	 * @Description:导出寻蜜客成员列表
	 * @param bxmer
	 * @return List<BXmer>
	 * @throw
	 */
	public List<XmerInfoBean> exportOtherXmerList(XmerInfoBean xmerInfoBean) {
		xmerInfoBean.setLimit(-1);
		return selectNewXmerInfoList(xmerInfoBean);
	}
	
	/**
	 * @Title:exportXmerList
	 * @Description:导出寻蜜客成员列表
	 * @param bxmer
	 * @return List<BXmer>
	 * @throw
	 */
	public List<BXmer> exportXmerList(BXmer bxmer) {
		List<BXmer> xmerList = new ArrayList<BXmer>();
		xmerList = bxmerDao.exportXmerList(bxmer);
		if(null != xmerList && 0 != xmerList.size()){
			StringBuilder sb = new StringBuilder();
			int uid = 0;
			for (int i = 0; i < xmerList.size(); i++) {
				uid = xmerList.get(i).getUid();
				sb = sb.append(uid + ",");
			}
			
			Object[] uids = sb.toString().split(",");
			
			List<Integer> xids=new ArrayList<Integer>();//寻蜜客钱包ID
		    
		    List<BXmerWallet> xmerWalletList = xmerWalletService.getListByUids(uids);
		    
		    for(BXmer xmer:xmerList){
		    	for(BXmerWallet xmerWallet:xmerWalletList){
		    		boolean isEqual=xmer.getUid()!=null && xmerWallet.getUid()!=null && xmer.getUid().compareTo(xmerWallet.getUid())==0;
		    		if(isEqual){
		    			xmer.setIncome(xmerWallet.getProfit().add(xmerWallet.getTrunout()));
		    			xmer.setDifference(xmerWallet.getProfit().doubleValue());
		    			xmer.setProfit(xmerWallet.getProfit());
		    			xmer.setTrunout(xmerWallet.getTrunout());
		    			xmer.setXid(xmerWallet.getId());
		    			xids.add(xmerWallet.getId());
		    			break;
		    		}
		    	}
		    }
		    
		    if(!xids.isEmpty()){//寻蜜客钱包存在
		    	List<BXmerWalletRecord> xmerWalletRecordList = xmerWalletRecordService.getListByXids(xids.toArray());
		    	for(BXmer xmer:xmerList){
			    	for(BXmerWalletRecord record:xmerWalletRecordList){
			    		boolean isEqual=xmer.getXid() != null && record.getXid() != null && xmer.getXid().compareTo(record.getXid())==0;
			    		if(isEqual){
			    			xmer.setCommision(record.getCommision());
			    			xmer.setBalance(record.getBalance());
			    			break;
			    		}
			    	}
			    }
		    }
			
			//修改寻蜜客金额信息取数来源   
			/*List<Bwallet> walletList = financeService.selectWallet(sbStr);
			BigDecimal income = null;//总收入
			BigDecimal commision = null;//佣金
		    BigDecimal balance = null;//流水收入
		    Integer wid = null;//钱包id
			for (int i = 0; i < walletList.size(); i++) {
				income =  walletList.get(i).getIncome();
				commision = walletList.get(i).getCommision();
				balance = walletList.get(i).getBalance();
				wid = walletList.get(i).getAccountid();
				for (int j = 0; j < xmerList.size(); j++) {
					int xmUid = xmerList.get(j).getUid();
					int waUid = walletList.get(i).getUid();
					if(xmUid == waUid){
						xmerList.get(j).setIncome(income);;
						xmerList.get(j).setCommision(commision);
						xmerList.get(j).setBalance(balance);
						xmerList.get(j).setAccountid(wid);
					}
				}
			}
			List<Bill> billList = financeService.getBillListByUids(sbStr);
			Double sumMoney = null;
			for (int j = 0; j < billList.size(); j++) {
				sumMoney = billList.get(j).getSumMoney();
				int waUid = billList.get(j).getUid();
				for (int k = 0; k < xmerList.size(); k++) {
					int xmUid = xmerList.get(k).getUid();
					if(xmUid == waUid){
						xmerList.get(k).setSumMoney(sumMoney);
					}
				}
			}*/
			
			
			//576(8折)套餐单价剩余套数
					List<TSaasOrder> eightFoldNumsList = new ArrayList<TSaasOrder>();
					eightFoldNumsList = bxmerDao.eightFoldNumsList(uids);
					Integer eightFoldNums = 0;
					for (int j = 0; j < eightFoldNumsList.size(); j++) {
						eightFoldNums = eightFoldNumsList.get(j).getStock();
						int saasOrderUid = eightFoldNumsList.get(j).getUid();
						for (int k = 0; k < xmerList.size(); k++) {
							int xmUid = xmerList.get(k).getUid();
							if(xmUid == saasOrderUid){
								xmerList.get(k).setEightFoldNums(eightFoldNums);
							}
						}
					}
					//504(7折)套餐单价剩余套数
					List<TSaasOrder> sevenFoldNumsList = new ArrayList<TSaasOrder>();
					sevenFoldNumsList = bxmerDao.sevenFoldNumsList(uids);
					Integer sevenFoldNums = 0;
					for (int j = 0; j < sevenFoldNumsList.size(); j++) {
						sevenFoldNums = sevenFoldNumsList.get(j).getStock();
						int saasOrderUid = sevenFoldNumsList.get(j).getUid();
						for (int k = 0; k < xmerList.size(); k++) {
							int xmUid = xmerList.get(k).getUid();
							if(xmUid == saasOrderUid){
								xmerList.get(k).setSevenFoldNums(sevenFoldNums);
							}
						}
					}
					//签约数量
					List<TSaasOrder> numsList = new ArrayList<TSaasOrder>();
					numsList = bxmerDao.numsList(uids);
					Integer nums = 0;
					for (int j = 0; j < numsList.size(); j++) {
						nums = numsList.get(j).getNums();
						int saasOrderUid = numsList.get(j).getUid();
						for (int k = 0; k < xmerList.size(); k++) {
							int xmUid = xmerList.get(k).getUid();
							if(xmUid == saasOrderUid){
								xmerList.get(k).setNums(nums);
								Integer i = xmerList.get(k).getEightFoldNums()+xmerList.get(k).getSevenFoldNums();
								xmerList.get(k).setStockTotal(i.toString()+"/"+nums);
								DecimalFormat  df = new DecimalFormat("0.00");
							    Double d = new Double(xmerList.get(k).getEightFoldNums()*576+xmerList.get(k).getSevenFoldNums()*504);
							    xmerList.get(k).setStockTotalPrice(df.format(d));
							}
						}
					}
			}
		return xmerList;
	}
	/**
	 * @Title:selectXmerPartnerList
	 * @Description:查询寻蜜客伙伴列表
	 * @param bxmer
	 * @return List<BXmer>
	 * @throw
	 */
	public List<XmerInfoBean> selectXmerPartnerList(XmerInfoBean xmerInfoBean){
		List<XmerInfoBean> xmerList = new ArrayList<XmerInfoBean>();
		xmerList = xmerInfoDao.getXmerDirectPartnerList(xmerInfoBean);
		return xmerList;
	}
	/**
	 * @Title:xmerPartnerCount
	 * @Description:查询寻蜜客伙伴列表总条数
	 * @param bxmer
	 * @return long
	 * @throw
	 */
	public long xmerPartnerCount(XmerInfoBean xmerInfoBean){
		return xmerInfoDao.getXmerDirectPartnerCount(xmerInfoBean);
	}
	
	/**
	 * @Title:xmerSellerList
	 * @Description:查询寻蜜客商铺列表
	 * @param xmerseller
	 * @return List<XmerSeller>
	 * @throw
	 */
	public List<XmerSeller> xmerSellerList(XmerSeller xmerseller){
		List<XmerSeller> xmersellerList = new ArrayList<XmerSeller>();
		xmersellerList = bxmerDao.xmerSellerList(xmerseller);
		return xmersellerList;
	}
	/**
	 * @Title:xmerSellerCount
	 * @Description:查询寻蜜客商铺列表总数
	 * @param xmerseller
	 * @return long
	 * @throw
	 */
	public long xmerSellerCount(XmerSeller xmerseller){
		return bxmerDao.xmerSellerCount(xmerseller);
	}
	
	/**
	 * @Description: 下拉框查寻所有寻蜜客
	 * @Param:
	 * @return:Pageable<TSeller>
	 * @author:lifeng
	 * @time:2016年6月6日下午7:57:16
	 */
	public Pageable<BXmer> getXmerPageable(BXmer xmer) {
		xmer.setLimit(SellerConstants.PAGE_LIMIT_NO);//不分页，查询全部的
		Pageable<BXmer> resXmer = new Pageable<BXmer>(xmer);
		List<BXmer> xmerList = null;
		// 寻蜜客列表
		xmerList = bxmerDao.getList(xmer);
		resXmer.setContent(xmerList);
		return resXmer;
	}
	
	/**
	 * @Description: 下拉框查寻所有寻蜜客
	 * @Param:
	 * @return:Pageable<TSeller>
	 * @author:lifeng
	 * @time:2016年6月6日下午7:57:16
	 */
	public List<BXmer> getXmerBySaasOrderUid(List<Integer> xmerList) {
		return bxmerDao.getXmerBySaasOrderUid(xmerList);
	}
	
	
	public List<XmerInfoBean> selectNewXmerInfoList(XmerInfoBean xmerInfoBean){

		List<XmerInfoBean> xmerList = new ArrayList<XmerInfoBean>();
		if(xmerInfoBean.getObjectOriented().intValue()==7){
			xmerList = xmerInfoDao.getVerXmerList(xmerInfoBean);
		}
		else{
			xmerList = xmerInfoDao.getXmerList(xmerInfoBean);
		}
		
		if(null != xmerList && 0 != xmerList.size()){
			
			List<Integer> uids = new ArrayList<>();
			for (XmerInfoBean info:xmerList) {
				uids.add(info.getUid());
			}			

			//签约数量
			List<TSaasOrder> numsList = new ArrayList<TSaasOrder>();
			numsList = bxmerDao.saasNumsList(uids.toArray(),getSaasTypeByObjectoriented(xmerInfoBean.getObjectOriented()));
			Map<Integer,TSaasOrder> saasMap = new HashMap<Integer, TSaasOrder>();
			for (TSaasOrder saas:numsList) {
				saasMap.put(saas.getUid(), saas);
			}
			int i=0;
			for(XmerInfoBean info:xmerList){
				TSaasOrder s =  saasMap.get(info.getUid());
				info.setAllSaas(s==null?0:s.getNums());
				Integer sss=null;
				if(s==null){
					sss=0;
				}else{
					sss=s.getStock();
				}
				info.setUnUsedSaas(sss);
			}
			//伙伴
//			if(xmerInfoBean.getObjectOriented()!=6	//主播寻蜜客没有伙伴
//					&& xmerInfoBean.getObjectOriented()!=8){	//脉客寻蜜客没有伙伴
//				Integer newObjectOriented = xmerInfoBean.getObjectOriented();
//				if(xmerInfoBean.getObjectOriented()==7){
//					newObjectOriented=6;
//				}
//				for (XmerInfoBean info:xmerList) {
//					info.setPartner((int)bxmerDao.getXmerPartnerCount(info.getUid(),newObjectOriented));
//				}
//			}
			//签约商家
			if(xmerInfoBean.getObjectOriented()!=7){//V客寻蜜客能赠送SAAS;其他寻蜜客不能赠送SAAS，可以直接通过总量-库存计算签约数
				for (XmerInfoBean info:xmerList) {
					if(info.getAllSaas()!=null && info.getUnUsedSaas()!=null){
						info.setSeller(info.getAllSaas()-info.getUnUsedSaas());
					}
				}
			}else{
				List<XmerInfoBean> sllerlist = xmerInfoDao.xmerSellerCount(uids.toArray(),xmerInfoBean.getObjectOriented());
				
				Map<Integer,XmerInfoBean> sellerMap = new HashMap<Integer, XmerInfoBean>();
				for (XmerInfoBean seller:sllerlist) {
					sellerMap.put(seller.getUid(), seller);
				}
				for(XmerInfoBean info:xmerList){
					XmerInfoBean s = sellerMap.get(info.getUid());
					info.setSeller(s==null?0:s.getSeller());
				}
			}
		}
		return xmerList;
	
	}
	
	public long xmerNewInfoCount(XmerInfoBean xmerInfoBean){
		return xmerInfoDao.getXmerCount(xmerInfoBean);
	}
	
	
	public Map<String,Object> countNewXmerInfoMap(XmerInfoBean xmerInfoBean){
		Map<String,Object> map = new HashMap<String, Object>();
		//统计客户端出售总额
		map.put("sellCount",bxmerDao.getSellCount(getSaasTypeByObjectoriented(xmerInfoBean.getObjectOriented())));
		//统计平台商铺流水总额
		map.put("sellerCrrentCount",bxmerDao.getSellerCrrentCount(getSaasTypeByObjectoriented(xmerInfoBean.getObjectOriented())));
		map.put("objectOriented", xmerInfoBean.getObjectOriented());
		return map;
	}
	private Integer getSaasTypeByObjectoriented(Integer objectOriented){
		Integer saasType = null;
		if(objectOriented==null){
			return null;
		}
		switch (objectOriented) {
		case 5:
			saasType=1;
			break;
		case 6:
			saasType=4;
			break;
		case 7:
			saasType=3;
			break;
		case 8:
			saasType=2;
			break;	
		default:
			break;
		}
		return saasType;
	}
}

