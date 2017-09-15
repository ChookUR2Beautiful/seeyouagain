package com.xmniao.xmn.core.seller.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.common.ObjResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.seller.dao.TradeDao;
import com.xmniao.xmn.core.seller.service.SellerTradeService;

/**
 * 
* @projectName: xmnService 
* @ClassName: SellerTradeServiceImpl    
* @Description:商铺分类列表接口实现类   
* @author: liuzhihao   
* @date: 2016年11月28日 上午9:57:03
 */
@Service
public class SellerTradeServiceImpl implements SellerTradeService{
	
	@Autowired
	private TradeDao tradeDao;

	@Override
	public Object querySellerTradeList(BaseRequest baseRequest) {
		Map<Object,Object> map = new HashMap<Object,Object>();
		List<Map<Object,Object>> result = new ArrayList<Map<Object,Object>>();
		try{
			//查询美食下面的所有二级分类
			List<Map<Object,Object>> trades = tradeDao.findAllTradeByDeliciousFood();
//			if(trades != null && !trades.isEmpty()){
//				for(Map<Object,Object> trade : trades){
//					pid = Integer.parseInt(trade.get("tid").toString());
//					List<Map<Object,Object>> SecendTrades = tradeDao.findAllByPid(pid);
//					if(SecendTrades != null && !SecendTrades.isEmpty()){
//						trade.put("SecendTrades", SecendTrades);
//					}
//				}
//			}else{
//				trades = new ArrayList<Map<Object,Object>>();
//			}
			if(trades != null && !trades.isEmpty()){
				
				Map<Object,Object> allmap = new HashMap<Object,Object>();
				allmap.put("pid", "");
				allmap.put("tid", "");
				allmap.put("tradename", "全部");
				result.add(allmap);
				result.addAll(trades);
				map.put("trades", result);
			}
			MapResponse response = new MapResponse(ResponseCode.SUCCESS,"查询成功");
			response.setResponse(map);
			return response;
		}catch(Exception e){
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "查询异常");
		}
		
	}

	/**
	 * 查询店铺分类
	 * @return
	 */
	public Object getSellerTradeList(BaseRequest request) {
		try{
			Map<Object, Object> result = new HashMap<Object, Object>();
			List<Map<Object, Object>> resultItemList = new ArrayList<>();

			List<Map<Object,Object>> trades = tradeDao.findAll();
			if (trades != null) {
				// key : 父菜单id value:子菜单列表
				Map<Integer, List<Map<Object, Object>>> tradesMap = new HashMap<Integer, List<Map<Object, Object>>>();
				for (Map<Object,Object> trade : trades ) {
					Integer pid = Integer.parseInt(trade.get("pid").toString());
					List<Map<Object, Object>> tradeList = tradesMap.get(pid);
					if (tradeList == null) {
						tradeList = new ArrayList<Map<Object, Object>>();
						tradesMap.put(pid, tradeList);
					}
					tradeList.add(trade);
				}

				List<Map<Object, Object>> parentTrade = tradesMap.get(0);  // 1级菜单
				parentTrade = parentTrade == null ? new ArrayList<Map<Object, Object>>() : parentTrade;
				for (Map<Object, Object> parent : parentTrade) {
					Integer tid = Integer.parseInt(parent.get("tid").toString());

					List<Map<Object, Object>> childTrade = tradesMap.get(tid);  // 2级菜单
					List<Map<Object, Object>> childData = new ArrayList<>();
					if (childTrade != null) {
						for (Map<Object, Object> child : childTrade) {
							Map<Object, Object> tmp = new HashMap<Object, Object>();
							tmp.put("tid", child.get("tid").toString());
							tmp.put("tradename", child.get("tradename") == null ? "" : child.get("tradename").toString());
							childData.add(tmp);
						}
					}
					Map<Object, Object> data = new HashMap<Object, Object>();
					data.put("tid", tid);
					data.put("tradename", parent.get("tradename") == null ? "" : parent.get("tradename").toString());
					data.put("secondList", childData);
					resultItemList.add(data);
				}
			}
			result.put("data", resultItemList);
			MapResponse mapResponse = new MapResponse(ResponseCode.SUCCESS, "查询成功");
			mapResponse.setResponse(result);
			return mapResponse;
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "查询异常");
		}
	}

}
