/**
 * 
 */
package com.xmniao.xmn.core.xmnpay.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.live_anchor.dao.BLiverDao;
import com.xmniao.xmn.core.live_anchor.entity.BLiver;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.NMD5;
import com.xmniao.xmn.core.xmnpay.dao.LiveWalletDao;
import com.xmniao.xmn.core.xmnpay.entity.LiveWallet;

/**
 * 项目名称：XmnWeb_LVB
 * 
 * 类名称：LiveWalletService
 *
 * 类描述：在此处添加类描述
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-8-15下午3:24:52
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class LiveWalletService extends BaseService<LiveWallet> {
	
	@Autowired
	private LiveWalletDao liveWalletDao;
	
	/**
	 * 注入直播用户服务
	 */
	@Autowired
	private BLiverDao liverDao;

	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return liveWalletDao;
	}
	
	/**
	 * 
	 * 方法描述：删除直播钱包表
	 * 创建人： huang'tao
	 * 创建时间：2016-8-15下午3:28:17
	 * @param id
	 * @return
	 */
    public int deleteByPrimaryKey(Integer id){
    	return liveWalletDao.deleteByPrimaryKey(id);
    }

	/**
	 * 
	 * 方法描述：添加直播钱包表
	 * 创建人： huang'tao
	 * 创建时间：2016-8-15下午3:28:09
	 * @param record
	 * @return
	 */
    public int addLiveWallet(LiveWallet record){
    	record.setCreateTime(new Date());
    	record.setSignType(1);//签名类型  1 MD5(默认)  2 RAS
    	record.setSign(liveWalletMD5(record));
    	return liveWalletDao.addLiveWallet(record);
    }

	
	/**
	 * 
	 * 方法描述：根据主键ID查询直播钱包表
	 * 创建人： huang'tao
	 * 创建时间：2016-8-15下午3:28:22
	 * @param id
	 * @return
	 */
    public LiveWallet selectByPrimaryKey(Integer id){
    	return liveWalletDao.selectByPrimaryKey(id);
    }

    /**
     * 
     * 方法描述：根据用户ID查询直播钱包表
     * 创建人： huang'tao
     * 创建时间：2016-8-15下午4:29:23
     * @param uid
     * @return
     */
    public LiveWallet selectByUid(Integer uid){
    	return liveWalletDao.selectByUid(uid);
    }
	/**
	 * 
	 * 方法描述：更新直播钱包表
	 * 创建人： huang'tao
	 * 创建时间：2016-8-15下午3:28:27
	 * @param record
	 * @return
	 */
    public int updateLiveWallet(LiveWallet record){
		return liveWalletDao.updateLiveWallet(record);
	}
	
    /**
	 * 
	 * 方法描述：根据会员Uid更新直播钱包表
	 * 创建人： huang'tao
	 * 创建时间：2016-8-15下午3:28:27
	 * @param record
	 * @return
	 */
    public int updateLiveWalletByUid(LiveWallet record){
		return liveWalletDao.updateLiveWalletByUid(record);
	}
    
    public List<LiveWallet> selectLiveWalletByUids(Object[] uids){
    	return liveWalletDao.selectLiveWalletByUids(uids);
    }
    
    public String liveWalletMD5(LiveWallet wallet){
		final Double ZERO = new Double(0.0);
		
		StringBuffer sb = new StringBuffer();
		sb.append(wallet.getUid()==null?0:wallet.getUid());
		sb.append(String.format("%.2f",wallet.getBalance()==null?ZERO:wallet.getBalance()));
		sb.append(String.format("%.2f",wallet.getCommision()==null?ZERO:wallet.getCommision()));
		sb.append(DateUtil.formatDate(new Date(), DateUtil.Y_M_D_HMS));		

		System.out.println("钱包加密：" + sb.toString());
		return NMD5.Encode(sb.toString());
	}

	/**
	 * 方法描述：更新直播钱包鸟币消费,余额限制设置<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-1-6下午2:08:45 <br/>
	 * @param liveAnchor
	 */
	public void updateLiveWalletInfo(BLiver liveAnchor) {
		Integer anchorId = liveAnchor.getId();
		BLiver liver = liverDao.selectByPrimaryKey(anchorId);
		Integer uid = liver.getUid();
		String restrictive = liveAnchor.getRestrictive();
		BigDecimal limitBalance = liveAnchor.getLimitBalance();
		
		LiveWallet liveWallet=new LiveWallet();
		liveWallet.setUid(uid);
		liveWallet.setRestrictive(restrictive);
		liveWallet.setLimitBalance(limitBalance);
		
		liveWalletDao.updateLiveWalletByUid(liveWallet);
	}
	
	
	

}
