/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.service;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.live_anchor.entity.TLiveDepositorsTaxes;
import com.xmniao.xmn.core.thrift.client.proxy.ThriftClientProxy;
import com.xmniao.xmn.core.thrift.service.DataResponse;


@Service
public class TLiveDepositorsTaxesService {
	
	protected final Logger log = Logger.getLogger(getClass());
	

	/**
	 * 注入扩展钱包服务
	 */
	@Resource(name = "walletExpansionServiceClient")
	private ThriftClientProxy walletExpansionServiceClient;


	/**
	 * 方法描述：查询收益类型提现手续费率 <br/>
	 * 创建人： caiyl <br/>
	 * 创建时间：2017年4月25日下午6:32:02 <br/>
	 * @param liveLevel
	 * @return
	 */
	public Pageable<TLiveDepositorsTaxes> getLiveDepositorsTaxesInfoList(TLiveDepositorsTaxes liveLevel) {
		Pageable<TLiveDepositorsTaxes> liveLevelInfoList = new Pageable<TLiveDepositorsTaxes>(liveLevel);
		List<TLiveDepositorsTaxes> liveLevelList = this.searchPurseDataList(liveLevel);
		liveLevelInfoList.setContent(liveLevelList);
		liveLevelInfoList.setTotal(new Long((long)liveLevelList.size()));
	    return liveLevelInfoList;
	}	


	/**
	 * 方法描述：修改指定收益类型提现手续费率 <br/>
	 * 创建人： caiyl <br/>
	 * 创建时间：2017年4月25日下午6:31:49 <br/>
	 * @param liveDepositorsTaxes
	 * @throws Exception
	 */
	public void saveUpdateActivity(TLiveDepositorsTaxes liveDepositorsTaxes) throws Exception{
		try {
			//连接接口进行查询
			Map<String, String> params = new HashMap<>();
			params.put("type", liveDepositorsTaxes.getType());
			params.put("rateType", liveDepositorsTaxes.getRateType());
			params.put("rate", liveDepositorsTaxes.getRate().toString());
			com.xmniao.xmn.core.thrift.service.liveService.WalletExpansionService.Client client = (com.xmniao.xmn.core.thrift.service.liveService.WalletExpansionService.Client) (walletExpansionServiceClient.getClient());
			log.info("修改指定收益类型提现手续费率开始");
			
			DataResponse response = client.updateExpansionExpenseRate (params);
			
			if(response.getState() != 0){
				log.error("调用修改指定收益类型提现手续费率失败");
				throw new RuntimeException("修改指定收益类型提现手续费率失败, 错误信息:"+ response.getMsg());
			}
			
			
			log.info("修改指定收益类型提现手续费率结束，返回值：" + response.getState());
			
		} catch (Exception e) {
			log.error("修改指定收益类型提现手续费率失败", e);
			throw new ApplicationException("修改指定收益类型提现手续费率异常", e, new Object[] { liveDepositorsTaxes.getType() });
			
		} finally {
			walletExpansionServiceClient.returnCon();
		}
		
	}
	
	
	/**
	 * 方法描述：查询储值卡充值消费详细记录 <br/>
	 * 创建人： caiyl <br/>
	 * 创建时间：2017年4月25日下午6:31:05 <br/>
	 * @param bean
	 * @return
	 */
	public List<TLiveDepositorsTaxes> searchPurseDataList(TLiveDepositorsTaxes bean) {
		List<TLiveDepositorsTaxes> liveDepositorsTaxesList = new ArrayList<TLiveDepositorsTaxes>();

		try {
			//连接接口进行查询
			com.xmniao.xmn.core.thrift.service.liveService.WalletExpansionService.Client client = (com.xmniao.xmn.core.thrift.service.liveService.WalletExpansionService.Client) (walletExpansionServiceClient.getClient());
			log.info("查询储值卡充值消费详细记录开始");
			List<Map<String, String>> livePurseDataList = client.getExpansionExpenseRateList();
			if (livePurseDataList.size() > 0) {
				liveDepositorsTaxesList = getLiveDepositorsTaxesFromMap(livePurseDataList);
			}
			log.info("查询储值卡充值消费详细记录结束，返回值：" + livePurseDataList.size());

		} catch (Exception e) {
			log.error("查询直播钱包失败", e);
//			throw new ApplicationException("查询直播钱包异常", e, new Object[] { uid });
		} finally {
			walletExpansionServiceClient.returnCon();
		}
		
		return liveDepositorsTaxesList;
	}
	
	
	/**
	 * 方法描述：配置成储值卡充值消费信息 <br/>
	 * 创建人： caiyl <br/>
	 * 创建时间：2017年4月25日下午6:31:23 <br/>
	 * @param depositorsTaxesDataList
	 * @return
	 * @throws Exception
	 */
	public List<TLiveDepositorsTaxes> getLiveDepositorsTaxesFromMap(List<Map<String, String>> depositorsTaxesDataList) throws Exception{
		List<TLiveDepositorsTaxes> liveDepositorsTaxesList = new ArrayList<TLiveDepositorsTaxes>();
		for (Map<String, String> object : depositorsTaxesDataList) {
			TLiveDepositorsTaxes bean = new TLiveDepositorsTaxes();
			//收益类型 0：综合收益，1:V客推荐， 2:V客红包，3:壕赚VIP红包，4:壕赚商户充值红包 5:V客创业管理奖金 6:寻蜜客签约收益 7：寻蜜客分账收益
			bean.setType(object.get("type"));
			bean.setTypeName(object.get("typeName"));
			bean.setRateType(object.get("rateType"));
			
			BigDecimal rate = new BigDecimal(object.get("rate"));
			rate = rate.setScale(2, BigDecimal.ROUND_HALF_UP);  
			bean.setRate(rate);
			
			liveDepositorsTaxesList.add(bean);
		}

		return liveDepositorsTaxesList;
	}

}
