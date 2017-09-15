package com.xmniao.xmn.core.integral.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.PageRequest;
import com.xmniao.xmn.core.common.request.ProductInfoRequest;
import com.xmniao.xmn.core.integral.dao.IntegralMallDao;
import com.xmniao.xmn.core.integral.entity.ProductInfoEntity;
import com.xmniao.xmn.core.util.PropertiesUtil;

/**
 * 
* 项目名称：xmnService   
* 类名称：ProductInfoService   
* 类描述：积分商品详情接口service   
* 创建人：liuzhihao   
* 创建时间：2016年6月20日 下午8:14:29   
* @version    
*
 */
@Service
public class ProductInfoService {

	//日志
	private final Logger log = Logger.getLogger(ProductInfoService.class);
	
	//注入积分商城Dao
	@Autowired
	private IntegralMallDao integralMallDao;
	
	//注入缓存
	@Autowired
	private SessionTokenService sessionTokenService;
	
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	@Autowired
	private String fileUrl;
	
	private MapResponse response = null;
	
	public MapResponse productInfo(ProductInfoRequest productInfoRequest){
		try{
			Map<Object,Object> map = new HashMap<Object,Object>();//返回参数map
			try{
				ProductInfoEntity productInfo = integralMallDao.queryProductInfo(productInfoRequest.getCodeid());
				if(productInfo != null){
					BigInteger codeid = productInfo.getCodeId();
					map.put("codeId", codeid);
					map.put("pname", productInfo.getPname());//商品名称
					map.put("weight", StringUtils.isEmpty(productInfo.getWeight())?"0":productInfo.getWeight());//重量
					map.put("store", productInfo.getStore()==null?0:productInfo.getStore());//库存数量
					map.put("price", productInfo.getPrice()==null?0.0:productInfo.getPrice());//商品原单品价格
					map.put("discount", productInfo.getDiscount()==null?0.0:productInfo.getDiscount());//商品优惠价格
					map.put("cash", productInfo.getCash()==null?0.0:productInfo.getCash());//现金支付金额
					map.put("integral", productInfo.getIntegral()==null?0.0:productInfo.getIntegral());//积分支付金额
					map.put("phone","4007766333");//厂家联系电话
					map.put("breviary", productInfo.getBreviary()==null?null:fileUrl+productInfo.getBreviary());//省略图
					String prompt="";// 运费提醒，查询运费模版
					try {
						Integer tid=productInfo.getExpTid();//模版ID
						if(tid!=null){
							prompt=getPrompt(tid);
						}
					} catch (Exception e) {
						e.printStackTrace();
						log.info("查询运费模版失败");
						prompt="";
					}
					map.put("prompt",prompt );//提示（多少钱包邮）
					//可配送城市和不配送城市
					String deliveryCity = productInfo.getDeliveryCity()==null?"":productInfo.getDeliveryCity();
					String notDeliveryCity = productInfo.getNotDeliveryCity()==null?"":productInfo.getNotDeliveryCity();
					String canDeivery = "";
					if(deliveryCity.equals("")&&notDeliveryCity.equals("")){
						canDeivery ="你当前的城市支持配送";
					}else{
						if(!deliveryCity.equals("")){	//可配送城市
							canDeivery = "你当前的城市不支持配送";
							String dcitys[] = deliveryCity.split(",");
							for (int i = 0; i < dcitys.length; i++) {
								if(dcitys[i].trim().equals(productInfoRequest.getCityid()+"")){
									canDeivery ="你当前的城市支持配送";
									break;
								}
							}
						}else{
							canDeivery = "你当前的城市支持配送";
							String ndcitys[] = notDeliveryCity.split(",");
							for (int i = 0; i < ndcitys.length; i++) {
								if(ndcitys[i].trim().equals(productInfoRequest.getCityid()+"")){
									canDeivery ="你当前的城市部不支持配送";
									break;
								}
							}
						}
					}
					
					//该城市是否支持配送
					map.put("deiverymark",canDeivery);
					//分享内容
					map.put("sharetitle", productInfo.getPname());
					map.put("sharetext", "使用寻蜜鸟积分即可购好礼，快来占便宜吧！");
					map.put("shareimg", fileUrl+productInfo.getBreviary());
					map.put("shareurl", "http://wxapi.xmniao.com/fresh/info.html?codeId="+codeid);

					try{
						Map<Object,Object> productImgMap = integralMallDao.queryProductImgByCodeId(productInfoRequest.getCodeid());
						if(productImgMap == null || productImgMap.size()<1){
							productImgMap = new HashMap<Object,Object>();
							productImgMap.put("delivery", "");//配送描述
							productImgMap.put("servicenote", "");//服务描述
							productImgMap.put("postnote", "");//包邮描述
							productImgMap.put("pic1	", "");//商品图片一
							productImgMap.put("pic2	", "");//商品图片二
							productImgMap.put("pic2	", "");//商品图片三
							productImgMap.put("pic3	", "");//商品图片四
							productImgMap.put("pic4	", "");//商品图片五
							productImgMap.put("img1	", "");//商品详情图片一
							productImgMap.put("img2	", "");//商品详情图片二
							productImgMap.put("img3	", "");//商品详情图片三
							productImgMap.put("img4	", "");//商品详情图片四
							productImgMap.put("img5	", "");//商品详情图片五
							productImgMap.put("img6	", "");//商品详情图片六
							productImgMap.put("img7	", "");//商品详情图片七
							productImgMap.put("img8	", "");//商品详情图片八
							productImgMap.put("img9	", "");//商品详情图片九
							productImgMap.put("img10	", "");//商品详情图片十
							productImgMap.put("img11	", "");//商品详情图片十一
							productImgMap.put("img12	", "");//商品详情图片十二
						}
						map.put("delivery", productImgMap.get("delivery"));
						map.put("postnote", productImgMap.get("postnote"));
						map.put("servicenote", productImgMap.get("servicenote"));
						List<Map<Object,Object>> picList = new ArrayList<Map<Object,Object>>();//存商品宣传图
						Map<Object,Object> picMap = new HashMap<Object,Object>();//存商品宣传图地址
						picMap.put("pic1", productImgMap.get("pic1"));
						picMap.put("pic2", productImgMap.get("pic2"));
						picMap.put("pic3", productImgMap.get("pic3"));
						picMap.put("pic4", productImgMap.get("pic4"));
						picMap.put("pic5", productImgMap.get("pic5"));
						for(Map.Entry<Object, Object> entry : picMap.entrySet()){
							Map<Object,Object> resultMap = new HashMap<Object,Object>();
							if(entry.getValue() != null && !entry.getValue().toString().equals("")){
								resultMap.put("url", fileUrl+entry.getValue());
								picList.add(resultMap);
							}
						}
						List<Map<Object,Object>> imgList = new ArrayList<Map<Object,Object>>();//存商品详情图
						Map<Object,Object> imgMap = new LinkedHashMap<>();//存商品详情图地址
						imgMap.put("img1", productImgMap.get("img1"));
						imgMap.put("img2", productImgMap.get("img2"));
						imgMap.put("img3", productImgMap.get("img3"));
						imgMap.put("img4", productImgMap.get("img4"));
						imgMap.put("img5", productImgMap.get("img5"));
						imgMap.put("img6", productImgMap.get("img6"));
						imgMap.put("img7", productImgMap.get("img7"));
						imgMap.put("img8", productImgMap.get("img8"));
						imgMap.put("img9", productImgMap.get("img9"));
						imgMap.put("img10", productImgMap.get("img10"));
						imgMap.put("img11", productImgMap.get("img11"));
						imgMap.put("img12", productImgMap.get("img12"));
						for(Map.Entry<Object, Object> imgEntry : imgMap.entrySet()){
							Map<Object,Object> imgResultMap = new HashMap<Object,Object>();
							if(imgEntry.getValue() != null && !imgEntry.getValue().toString().equals("")){
								imgResultMap.put("url", fileUrl+imgEntry.getValue());
								imgList.add(imgResultMap);
							}
						}
						map.put("imgs", imgList);
						map.put("pics", picList);//商品宣传图集合
					}catch(Exception e){
						e.printStackTrace();
						log.info("查询积分商品图片异常");
					}
					response= new MapResponse(ResponseCode.SUCCESS,"成功");
					response.setResponse(map);
					return response;
				}
				return new MapResponse(ResponseCode.FAILURE,"失败，商品id不正确");
			}catch(Exception e){
				e.printStackTrace();
				log.info("积分商品详情表查询异常");
				response = new MapResponse(ResponseCode.FAILURE,"积分商品详情查询异常");
				return response;
			}
		}catch(Exception e){
			e.printStackTrace();
			return new MapResponse(ResponseCode.FAILURE,"查询积分商品详情异常");
		}
	}
	//查询运费信息
	private String getPrompt(Integer tid) {
		String prompt="";
		Map<String,Object> map=integralMallDao.getPostage(tid);
		if(map!=null){
			double amount=Double.parseDouble(map.get("amount").toString());
			if(amount>0){
				prompt="满"+amount+"元包邮";
				if(!map.get("weight").toString().equals("0")){
					prompt+="("+map.get("weight")+"kg内)";
				}
			}else{
				if(!map.get("weight").toString().equals("0")){
					prompt=map.get("weight")+"kg内包邮";
				}
			}
		}
		
		return prompt;
	}

	/**
	 * 
	* @Title: integralRecode
	* @Description: 用户积分记录
	* @return MapResponse    返回类型
	* @author liuzhihao
	* @throws
	 */
	public MapResponse integralRecode(PageRequest pageRequest){
		String uid = sessionTokenService.getStringForValue(pageRequest.getSessiontoken()).toString();
		if(StringUtils.isEmpty(uid)){
			log.info("uid不能为空，请检查token是否正确");
			return new MapResponse(ResponseCode.TOKENERR,"token已过期，请重新登录");
		}
		try{
			//根据用户id查询用户钱包
			Map<Object,Object> walletMap = integralMallDao.queryWalletInfo(Integer.valueOf(uid));
			if(walletMap == null || walletMap.size() < 1){
				return new MapResponse(ResponseCode.FAILURE,"查询失败,该用户没有钱包");
			}
			Map<Object,Object> paraMap = new HashMap<Object,Object>();
			paraMap.put("page", pageRequest.getPage());
			paraMap.put("limit", Constant.PAGE_LIMIT);
			paraMap.put("accountid", walletMap.get("accountid"));
			//根据钱包id查询用户钱包使用记录
			List<Map<Object,Object>> recodeList = integralMallDao.getWalletRecodeList(paraMap);
			if(recodeList.size() <1 || recodeList == null){
				recodeList = new ArrayList<Map<Object,Object>>();
			}
			Map<Object,Object> map = new HashMap<Object,Object>();
			map.put("integrals", recodeList);
			response = new MapResponse(ResponseCode.SUCCESS,"成功");
			response.setResponse(map);
			return response;
		}catch(Exception e){
			e.printStackTrace();
			log.info("查询钱包id异常");
			return new MapResponse(ResponseCode.FAILURE,"查询异常");
		}
	}
}
