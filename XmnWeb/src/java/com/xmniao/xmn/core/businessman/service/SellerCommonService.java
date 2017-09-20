package com.xmniao.xmn.core.businessman.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.businessman.dao.SellerDao;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.thrift.client.proxy.ThriftClientProxy;
import com.xmniao.xmn.core.thrift.service.synthesizeService.SynthesizeService;
import com.xmniao.xmn.core.util.HttpUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;

/**
 * 项目名称：XmnWeb 类名称：SellerCommonService 类描述： 创建人：Administrator 创建时间：2015年8月20日
 * 上午11:18:50
 * 
 * @version
 */
@Service
public class SellerCommonService extends BaseService<TSeller> {

	private static final String msgUrl = PropertiesUtil
			.readValue("http.phone.url") + "/smsSend";
	@Autowired
	private SellerDao sellerDao;

	@Resource(name = "synthesizeServiceClient")
	private ThriftClientProxy synthesizeServiceClient;

	@Override
	protected BaseDao<TSeller> getBaseDao() {
		return sellerDao;
	}

	public Map<String, String> addWalletMap(Map<String, String> paramMap)
			throws Exception {
		Map<String, String> resultMap = null;
		SynthesizeService.Client client = null;
		try {
			client = (SynthesizeService.Client) (synthesizeServiceClient
					.getClient());
		} catch (Exception e) {
			throw new ApplicationException("获取SynthesizeService.Client出现异常");
		}
		try {
			resultMap = client.addWalletMap(paramMap);
		} catch (Exception e) {
			throw e;
		} finally {
			synthesizeServiceClient.returnCon();
		}
		return resultMap;
	}

	/**
	 * 发送手机短信
	 * 
	 * @author zhang'zhiwen
	 * @date 2015年8月25日 下午6:15:21
	 * @param url
	 * @param sendShortMessageParamDTO
	 * @return
	 * @throws ApplicationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public String sendShortMessage(Map<String, Object> param)
			throws ApplicationException {
		this.log.info("手机短信发送的请求：" + msgUrl + "?p=" + param);
		String result = null;
		try {
			result = HttpUtil.getInstance().postForString(msgUrl, param, true);
		} catch (Exception e) {
			throw new ApplicationException("短信发送异常");
		}
		return result;
	}

}
