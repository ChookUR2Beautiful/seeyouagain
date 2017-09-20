package com.xmniao.xmn.core.businessman.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.businessman.dao.WaiterConfigDao;
import com.xmniao.xmn.core.businessman.entity.TSpreadConfig;
import com.xmniao.xmn.core.util.DateHelper;

/**
 *@ClassName:WaiterConfigService
 *@Description:商家服务员推广配置service层
 *@author hls
 *@date:2016年2月26日下午5:02:44
 */
@Service
public class WaiterConfigService extends BaseService<TSpreadConfig> {
	
	@Autowired
	private WaiterConfigDao waiterConfigDao;

	@Override
	protected WaiterConfigDao getBaseDao() {
		return waiterConfigDao;
	}
	
	/**
	 * @Title:addWaiterConfig
	 * @Description:添加商家服务员推广配置信息
	 * @param tSpreadConfig
	 * @param request
	 * @return Resultable
	 * @throw
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Resultable addWaiterConfig(TSpreadConfig tSpreadConfig,
			HttpServletRequest request) {
		Resultable resultable = new Resultable();
		this.log.info("waiterConfigService-->TSpreadConfig=" + tSpreadConfig);
		//获取当前系统时间作为创建时间
		String rdate = DateHelper.getDateFormatter();
		tSpreadConfig.setRdate(rdate);
		waiterConfigDao.add(tSpreadConfig);
		resultable.setSuccess(true);
		return resultable;
	}
	/**
	 * @Title:updateWaiterConfig
	 * @Description:修改商家服务员推广配置信息
	 * @param tSpreadConfig
	 * @param request
	 * @return Resultable
	 * @throw
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Resultable updateWaiterConfig(TSpreadConfig tSpreadConfig,
			HttpServletRequest request) {
		Resultable resultable = new Resultable();
		this.log.info("waiterConfigService-->TSpreadConfig=" + tSpreadConfig);
		//获取当前系统时间作为更新时间
		String rdate = DateHelper.getDateFormatter();
		tSpreadConfig.setUdate(rdate);
		waiterConfigDao.update(tSpreadConfig);
		resultable.setSuccess(true);
		return resultable;
	}
	/**
	 * 初始化服务员推广配置(用于服务员推广配置修改时的初始化数据)
	 */
	public void initTSpreadConfigInfo(Integer id, ModelAndView model) {
		if(null != id){
			TSpreadConfig tSpreadConfigList = getObject(id.longValue());
			model.addObject("tSpreadConfigList", tSpreadConfigList);
		}
	}
}
