package com.xmniao.xmn.core.user_terminal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.user_terminal.dao.BannerDao;
import com.xmniao.xmn.core.user_terminal.entity.TBanner;
import com.xmniao.xmn.core.user_terminal.util.UserConstants;
import com.xmniao.xmn.core.util.DateHelper;

/**
 * @ClassName:BillFreshService
 * @Description:生鲜订单service层
 * @author hls
 * @date:2016年1月5日下午4:05:10
 */
@Service
public class BannerService extends BaseService<TBanner>{ 

	@Autowired
	private BannerDao bannerDao;
	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return bannerDao;
	}
	/**
	 * @Title:getFreshList
	 * @Description:查询导航图列表
	 * @param paraMap
	 * @return TBillFresh
	 * @throw
	 */
	public List<TBanner> selectBannerInfoList(TBanner banner){
		return bannerDao.getBannerList(banner);
	}
	/**
	 * @Title:tbillBannerInfoCount
	 * @Description:查询导航图列表总条数
	 * @param banner
	 * @return long
	 * @throw
	 */
	public long tbillBannerInfoCount(TBanner banner){
		return bannerDao.getBannerCount(banner);
	}
	/**
	 * @Title:updateWstatus
	 * @Description:修改导航图
	 * @param banner void
	 * @throw
	 */
	public void updateBanner(TBanner banner){
		try {
			//获取当前系统时间放入paraMap作为发货时间
			String ddate = DateHelper.getDateFormatter();
			banner.setUpdateTimeStr(ddate);
			checkFresh(banner);
			bannerDao.updateBanner(banner);
		} catch (Exception e) {
			this.log.error("修改导航图异常：", e);
			throw new ApplicationException("导航图更新异常",e,new Object[]{banner});
		}
	}
	
/**
	 * 方法描述：检查是否积分超市活动类型
	 * 创建人： jianming <br/>
	 * 创建时间：2017年3月17日下午4:15:28 <br/>
	 * @param banner
	 */
	public void checkFresh(TBanner banner) {
		String position = banner.getPosition();
		if(UserConstants.BANNER_POSITION_INDIANA.toString().equals(position)||UserConstants.BANNER_POSITION_AUCTION.toString().equals(position)){
			banner.setStatus(1);
			banner.setIsAll("0");
			banner.setProvince(null);
			banner.setCity(null);
			banner.setIsEmphasis(UserConstants.BANNER_IS_NOT_EMPHASIS);
		}
	}
	/**
	 * @Title:getBanner
	 * @Description:根据导航图编号查询导航图详情
	 * @param id
	 * @return TBanner
	 * @throw
	 */
	public TBanner getBanner(Integer id){		
		TBanner banner = bannerDao.getBanner(id);
		return banner;
	}
	/**
	 * @Title:deleteBannerById
	 * @Description:根据导航图id批量删除导航图信息
	 * @param objects
	 * @return Integer
	 * @throw
	 */
	public void deleteBannerById(TBanner banner) {
		try{
			if(null != banner.getIds() && !"".equals(banner.getIds())){//id存在才删除
				String[] ids = banner.getIds().split(",");
				bannerDao.deleteBannerById(ids);
			}
		}catch(Exception e){
			this.log.error("删除导航图异常：", e);
			throw new ApplicationException("删除导航图异常",e, banner);
		}
	}
	
}
