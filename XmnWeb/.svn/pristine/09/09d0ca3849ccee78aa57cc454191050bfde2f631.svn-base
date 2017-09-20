package com.xmniao.xmn.core.user_terminal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.common.dao.AdvertisingDao;
import com.xmniao.xmn.core.common.entity.TAdvertising;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.user_terminal.util.UserConstants;
@Service
public class BannerAdvertisingService extends BaseService<TAdvertising> {
	@Autowired
	private AdvertisingDao advertisingDao;
	@Override
	protected BaseDao getBaseDao() {
		return advertisingDao;
	}
	/**
	 * @author dong'jt
	 * 创建时间：2015年9月22日 下午1:51:01
	 * 描述： 寻蜜鸟客户端活动banner列表获取逻辑
	 * @param advertising
	 * @param pageable
	 */
	public void putPageable(TAdvertising advertising,Pageable<TAdvertising> pageable){
		pageable.setContent(advertisingDao.getADList(advertising));
		pageable.setTotal(advertisingDao.getADListcount(advertising));
	}
	/**
	 * 
	 * @author dong'jt
	 * 创建时间：2015年9月23日 上午11:19:54
	 * 描述：banner新增
	 * @param advertising
	 * @return
	 */
	public Resultable addBanner(TAdvertising advertising){
		Resultable resultable=null;
		try {
			advertising.setIsshow(UserConstants.BANNER_STATUS_STAY_ONLINE);//默认为待上线
			super.add(advertising);
			resultable=ResInfo(advertising);
		} catch (Exception e) {
			this.log.error("banner新增：", e);
			throw new ApplicationException("banner新增",e,new Object[]{advertising},infoStr(advertising));
		}
		return resultable;
	}
	public Resultable ResInfo(TAdvertising advertising){
		this.log.info("添加成功");
		Resultable resultable = new Resultable(true, "操作成功");
		super.fireLoginEvent(infoStr(advertising),UserConstants.FIRELOGIN_SUCCESS);
		return resultable;
	}
	public String[] infoStr(TAdvertising advertising){
		String word = advertising.getContent();
		String str = "";
		if (word.length() <= 12){
			str = word;
		}else{
			str = word.substring(0, 12)+"...";
		}
		String[] s={"活动banner",str,"新增"};
		return s;
	}
	/**
	 * 
	 * @author dong'jt
	 * 创建时间：2015年9月23日 上午11:47:21
	 * 描述：需要更新的数据初始化
	 * @param modelAndView
	 * @param id
	 */
     public void  updateBannerInitData(ModelAndView modelAndView,String id){
	    modelAndView.addObject("isType", "update");
		TAdvertising advertising = super.getObject(new Long(id));
		this.log.info(advertising);
		modelAndView.addObject("advertising", advertising);
    }
     /**
      * 
      * @author dong'jt
      * 创建时间：2015年9月23日 上午11:42:12
      * 描述：      banner修改
      * @param advertising
      * @return
      */
    public Resultable updateBannerData(TAdvertising  advertising){
       Resultable resultable=null;
       try {
		super.update(advertising);
		resultable=this.updateBannerinfo(advertising);
	  } catch (Exception e) {
		this.log.error("banner修改：", e);
		throw new ApplicationException("banner修改",e,new Object[]{advertising},new String[]{"活动banner编号",String.valueOf(advertising.getId()),"修改","修改"});
	  }
      return resultable;
    }
    public Resultable updateBannerinfo(TAdvertising  advertising){
    	this.log.info("修改成功");
		String[] s={"活动banner编号",String.valueOf(advertising.getId()),"修改","修改"};
		super.fireLoginEvent(s,UserConstants.FIRELOGIN_SUCCESS);
    	return new Resultable(true, "操作成功");
    }
    
    public Resultable updateBannerStatus(TAdvertising  advertising){
        Resultable resultable=null;
        try {
 		super.update(advertising);
 		resultable=this.updateBannerStatusInfo(advertising);
 	  } catch (Exception e) {
 		this.log.error("banner状态修改：", e);
 		throw new ApplicationException("banner状态修改",e,new Object[]{advertising},new String[]{"活动banner编号",String.valueOf(advertising.getId()),"修改","修改"});
 	  }
       return resultable;
     }
     public Resultable updateBannerStatusInfo(TAdvertising  advertising){
     	this.log.info("修改成功");
 		super.fireLoginEvent(BannerStatusInfos(advertising),UserConstants.FIRELOGIN_SUCCESS);
     	return new Resultable(true, "操作成功");
     }
     public String[] BannerStatusInfos(TAdvertising  advertising){
    	String[] s=new String[4];
    	s[0]="活动banner编号";
    	s[1]=String.valueOf(advertising.getId());
    	s[3]="修改";
    	int issh=advertising.getIsshow();
    	switch (issh) {
        case 0:s[2]="修改为已上线";
			break;
        case 2:s[2]="修改为已下线";
			break;
		default:s[2]="修改";
			break;
		}
    	 return s;
     }
}
