package com.xmniao.xmn.core.integral.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.IntegralMallHomeRequest;
import com.xmniao.xmn.core.integral.dao.IntegralMallDao;
import com.xmniao.xmn.core.integral.dao.PropertyDao;
import com.xmniao.xmn.core.integral.entity.BannerEntity;
import com.xmniao.xmn.core.integral.entity.ProductInfoEntity;
import com.xmniao.xmn.core.seller.entity.Trade;
import com.xmniao.xmn.core.util.Base64;
import com.xmniao.xmn.core.util.DateUtil;

/***
 * 
* 项目名称：xmnService   
* 类名称：CategorysService   
* 类描述：积分商城service   
* 创建人：liuzhihao   
* 创建时间：2016年6月20日 下午1:57:52   
* @version    
*
 */
@Service
public class IntegralMallService {
 
	//日志
	private final Logger log = Logger.getLogger(IntegralMallService.class);
	
	//注入Dao
	@Autowired
	private IntegralMallDao integralMallDao;
	
	//注入缓存
	@Autowired
	private SessionTokenService sessionTokenService;
	
	//注入服务器地址
	@Autowired
	private String fileUrl;
	
	//注入积分商品属性service
	@Autowired
	private PropertyDao propertyDao;
	
	
	private MapResponse response=null;
	
	
	/**
	 * 
	* @Title: categorysFresh
	* @Description:积分商城—主页实现类
	* @return Map<Object,Object>    返回类型
	* @author liuzhihao
	* @throws
	 */
	public MapResponse IntegralMallHome(IntegralMallHomeRequest integralMallHomeRequest){
		Map<Object,Object> map = new HashMap<Object,Object>();//返回参数map
		Integer page = integralMallHomeRequest.getPage();//页数
		//当首页时
		if(page ==1){
			//分类
			List<Map<Object,Object>> trades = getCategorys(integralMallHomeRequest);
			map.put("trades", trades);
			//用户钱包信息
			String token = integralMallHomeRequest.getSessiontoken();
			if(StringUtils.isEmpty(token)){
				map.put("integral", 0);//用户总积分
			}else{
				try{
				String uid = sessionTokenService.getStringForValue(token)+"";
					if(!uid.equals("null")&&!uid.equals("")){
						Map<Object,Object> walletMap  = integralMallDao.queryWalletInfo(Integer.valueOf(uid));
						map.put("integral", walletMap.get("integral"));//用户总积分
					}
				}catch(Exception e){
					e.printStackTrace();
					log.info("查询用户积分异常，查看钱包表，该用户是否存在钱包");
					map.put("integral", 0);//用户总积分
				}
			}
			//banner图片
			List<Map<Object,Object>> banners = getBanner(integralMallHomeRequest);
			map.put("banners", banners);
			//动态积分
			List<Map<Object,Object>> freshIntegrals = getIntegralDynamic();
			map.put("freshIntegrals", freshIntegrals);
		}
		//热门产品
		List<Map<Object,Object>> products = new ArrayList<Map<Object,Object>>();
		Integer totalPage=0;//总条数
		try{
			Map<Object,Object> paraMap = new HashMap<Object,Object>();
			paraMap.put("page", integralMallHomeRequest.getPage());
			paraMap.put("limit", Constant.PAGE_LIMIT);
			List<ProductInfoEntity> productList = integralMallDao.queryProductInfoList(paraMap);
			if(productList.size() >0 && productList != null){
				Integer counts = integralMallDao.getProductInfoCounts();
				//计算总页数
				totalPage = counts%Constant.PAGE_LIMIT==0?counts/Constant.PAGE_LIMIT:counts/Constant.PAGE_LIMIT+1;
				for(ProductInfoEntity product : productList){
					Map<Object,Object> productMap = new HashMap<Object,Object>();
					productMap.put("id", product.getPid());//商品id
					productMap.put("codeid", product.getCodeId());//商品标识
					productMap.put("pname", StringUtils.isEmpty(product.getPname())?"":product.getPname());//产品具体名称
					productMap.put("price", product.getPrice()==null?0.0:product.getPrice());//产品原单品价格
					productMap.put("discount", product.getDiscount()==null?0.0:product.getDiscount());//产品优惠后价格
					productMap.put("weight", StringUtils.isEmpty(product.getWeight())?"0":product.getWeight());//产品重量
					productMap.put("breviary", StringUtils.isEmpty(product.getBreviary())?"":fileUrl+product.getBreviary());//产品缩略图地址
					productMap.put("integral", product.getIntegral()==null?0.0:product.getIntegral());//积分支付金额
					productMap.put("cash", product.getCash()==null?0.0:product.getCash());//现金支付
					productMap.put("pstatus", product.getPstatus());//产品状态默认0      0待上线    1已上线     2已售罄     3已下线
					productMap.put("store", product.getStore());//库存产品总数
					try {
						//查询商品规格属性
						List<Map<String, Object>> propertyList= propertyDao.queryPropertyList(product.getCodeId());
						if(propertyList!=null&&propertyList.size()>0){
							productMap.put("is_attr_exist", 1);//是否有属性
						}else{
							productMap.put("is_attr_exist", 0);
						}
						
//						JSONArray pjsonArray=new JSONArray();
//						if(propertyList!=null&&propertyList.size()>0){
//							for(Map<String,Object> pmap:propertyList){
//								JSONObject pjson=new JSONObject();
//								String id=pmap.get("id")+"";
//								String pname=pmap.get("property")+"";
//								pjson.put("id", id);	//属性id
//								pjson.put("name", pname);//属性名称
//								//查询商品属性值
//								List<Map<String,Object>> propertyValueList=propertyDao.queryPropertyValueList(id);
//								JSONArray vjsonArray=new JSONArray();
//								if(propertyValueList!=null&&propertyValueList.size()>0){
//									for(Map<String,Object> vmap:propertyValueList){
//										JSONObject vjson=new JSONObject();
//										vjson.put("id", vmap.get("id")+"");	//属性值表id
//										vjson.put("pid", vmap.get("pid")+"");//属性id
//										vjson.put("value", vmap.get("value")+"");//属性值
//										vjsonArray.add(vjson);
//									}
//									pjson.put("values", vjsonArray);
//								}
//								pjsonArray.add(pjson);
//							}
//						}
//					productMap.put("properts",pjsonArray);
					} catch (Exception e) {
						e.printStackTrace();
						log.info("获取商品属性失败");
					}
					products.add(productMap);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			log.info("热门产品查询异常");
		}
		map.put("products", products);
		map.put("totalPage", totalPage);//总页数
		response = new MapResponse(ResponseCode.SUCCESS,"成功");
		response.setResponse(map);
		return response;
	}
	
	/**
	 * 
	* @Title: getCategorys
	* @Description:获取分类数据
	* @return List<Map<Object,Object>>    返回类型
	* @author liuzhihao
	* @throws
	 */
	private List<Map<Object,Object>> getCategorys(IntegralMallHomeRequest integralMallHomeRequest){
		List<Map<Object,Object>> result = new ArrayList<Map<Object,Object>>();
		try{
			List<Trade> tradeList = integralMallDao.queryTradeList();
			if(tradeList.size() >0 && tradeList != null){
				String tradeName="";//分类名称
				Integer tid=0;//分类id
				Integer fid=0;//父类id
				String showSmallImg="";//分类图标地址
				for(Trade trade : tradeList){
					Map<Object,Object> tradeMap = new HashMap<Object,Object>();
					tradeName = trade.getTradename();//分类名称
					if(StringUtils.isEmpty(tradeName)){
						tradeName="";
					}
					showSmallImg = trade.getShowSmallImg();
					if(StringUtils.isEmpty(showSmallImg)){
						showSmallImg="";
					}
					tid = trade.getTid();
					fid = trade.getPid();
					tradeMap.put("tid", tid);//分类id
					tradeMap.put("fid", fid);//父类id
					tradeMap.put("tradeName", tradeName);
					tradeMap.put("showSmallImg", fileUrl+showSmallImg);
					result.add(tradeMap);
				}
			}
			return result;
		}catch(Exception e){
			e.printStackTrace();
			log.info("分类查询异常");
			return result;
		}
	}
	
	/**
	 * 
	* @Title: getBanner
	* @Description:获取banner数据
	* @return List<Map<Object,Object>>    返回类型
	* @author liuzhihao
	* @throws
	 */
	private List<Map<Object,Object>> getBanner(IntegralMallHomeRequest integralMallHomeRequest){
		List<Map<Object,Object>> result = new ArrayList<Map<Object,Object>>();//存储banner图的集合map
		try{
			Map<Object,Object> paraMap = new HashMap<Object,Object>();
			paraMap.put("position", 2);//banner位置信息，1 附近美食，2 积分商城
			paraMap.put("status", 1);//上线状态 0.待上线，1.已上线，2.已下线
			List<BannerEntity> bannerList = integralMallDao.queryBannerList(paraMap);
			if(bannerList.size() >0 && bannerList != null){
				for(BannerEntity banner : bannerList){
					Map<Object,Object> map  = new HashMap<Object,Object>();
					map.put("id", banner.getId());//图片id
					map.put("bannerStyle", banner.getBanner_style()==null?0:banner.getBanner_style());//展示风格。0图片横排一格，1图片横排两格
					map.put("sort", banner.getSort()==null?0:banner.getSort());//排序，数值越大，越优先展示
					String obj_json = banner.getObj_json();
					String contents = Base64.getFromBase64(obj_json);//解密
					List<Map<Object,Object>> picList = new ArrayList<Map<Object,Object>>();//用于存储图片集合
					JSONArray arr = JSON.parseArray(contents);
					for(int i=0; i<arr.size(); i++){
						Map<Object,Object> picMap = new HashMap<Object,Object>();
						JSONObject json = JSON.parseObject(arr.get(i).toString());
						String url = json.getString("pic_url").toString();//图片地址
						Integer type = json.getInteger("type");//图片类型
						String content = json.getString("content").toString();//描述
						Integer sort = json.getInteger("sort");//图片排序
						Integer logRequired = json.getInteger("logRequired");//图片是否需要登录
						picMap.put("url", fileUrl+url);
						picMap.put("type", type);
						picMap.put("content", content);
						picMap.put("sort", sort);
						if(sort==null){
							picMap.put("sort", 0);
						}
						if (logRequired == null) {
							picMap.put("logRequired", 0);
						}else {
							picMap.put("logRequired", logRequired);
						}
						picList.add(picMap);
					}
					 Collections.sort(picList, new Comparator<Map<Object, Object>>(){
				            public int compare(Map<Object, Object> arg0, Map<Object, Object> arg1) {
				                return arg1.get("sort").toString().compareTo(arg0.get("sort").toString());
				            }
				        });
					map.put("bannerlist", picList);
					result.add(map);
				}
			}
			 Collections.sort(result, new Comparator<Map<Object, Object>>(){
		            public int compare(Map<Object, Object> arg0, Map<Object, Object> arg1) {
		                return arg1.get("sort").toString().compareTo(arg0.get("sort").toString());
		            }
		        });
			return result;
		}catch(Exception e){
			e.printStackTrace();
			log.info("查询banner图异常");
			return result;
		}
	}
	
	/**
	 * 
	* @Title: getIntegralDynamic
	* @Description: 积分动态
	* @return Map<Object,Object>    返回类型
	* @author liuzhihao
	* @throws
	 */
	private List<Map<Object,Object>> getIntegralDynamic(){
		try{
			List<Map<Object,Object>> uidList=null;//用户id集合
			List<Map<Object,Object>> integralList= null;//积分动态集合
			List<Map<Object,Object>> phoneList=null;//用户电话集合
			List<Map<Object,Object>> result = new ArrayList<Map<Object,Object>>();
			//通过钱包记录表查询积分动态中用户的积分消费动态
			 integralList = integralMallDao.getIntegralDynamicList();
			 if(integralList.size() >0 && integralList != null){
				 uidList = integralMallDao.getUidList(integralList);//uid集合
				 if(uidList.size() > 0 && uidList != null){
					 phoneList=integralMallDao.getXmerPhoneByUid(uidList);
					 //把电话存入uid集合中
					 for(Map<Object,Object> uidMap : uidList){
						 for(Map<Object,Object> phoneMap : phoneList){
							 if(uidMap.get("uid").toString().equals(phoneMap.get("uid").toString())){
								 uidMap.put("phone", StringUtils.isEmpty(phoneMap.get("phone").toString())?"":phoneMap.get("phone"));
							 }
						 }
					 }
					 
					 //电话存入integral集合中
					 for(Map<Object,Object> integralMap : integralList){
						 for(Map<Object,Object> map : uidList){
							 if(integralMap.get("accountid").toString().equals(map.get("accountid").toString())){
								 integralMap.put("phone", map.get("phone"));
							 }
						 }
					 }
					 
					 for(Map<Object,Object> resultMap : integralList){
						 Map<Object, Object> dynamicMap = getDynamicDate(resultMap);
						 String phone = resultMap.get("phone")+"";
						 if(phone.equals("") || phone.equals("null")){
							 dynamicMap.put("phone", getPhone());
						 }else{
							 phone = phone.replace(phone.substring(3, phone.length()-4), "****");
							 dynamicMap.put("phone", phone);
						 }
						/* Date sdate = (Date) resultMap.get("sdate");//支付日期
						 long stimes = sdate.getTime();//支付日期毫秒数
						 long ntimes = System.currentTimeMillis();//系统时间毫秒数
						 String date = DateUtil.format(sdate, "MM/dd HH:mm");
						 if((ntimes-stimes) > 30*60*1000){
							 //如果当前系统毫秒数与支付时间毫秒数的差大于三十分钟则为过期时间，按产品要求制造假数据
							 Random random = new Random();//随机生成数字
							 long r = (random.nextInt(4)+1)*60*1000;//随机时间毫秒数
							 ntimes = ntimes+r;//系统时间加随机时间毫秒数
							 Date d = new Date(ntimes);
							 date = DateUtil.format(d, "MM/dd HH:mm");
						 }
						 dynamicMap.put("sdate", date);
						 dynamicMap.put("integral", "消费了"+resultMap.get("integral")+"积分");
						 result.add(dynamicMap);*/
						 
						 result.add(dynamicMap);
					 }
				 }
			 }
			 return result;
		}catch(Exception e){
			e.printStackTrace();
			log.info("积分动态查询异常");
		}
		return null;
	}
	
	/**
	 * 
	* @Title: getPhone
	* @Description: 伪造假的电话号码数据信息
	* @return String    返回类型
	* @author
	* @throws
	 */
	public String getPhone() {
		int[] prefix = new int[]{130,131,132,133,134,135,136,137,138,139,147,150,151,152,153,155,156,157,158,159,180,182,185,186,187,188,189};
		int p = new Random().nextInt(27);
		int s = new Random().nextInt(8999);
		String suffix = "";
		if (s < 10) {
			suffix = "000" + s;
		}else if (s < 100) {
			suffix = "00" + s;
		}else if (s < 1000) {
			suffix = "0" + s;
		}
		return prefix[p] + "****" + suffix;
	}
	/**
	 * 
	* @Title: getDynamicDate
	* @Description: 获取调整后的动态积分时间信息
	* @return Map<Object,Object>    返回类型
	* @author
	* @throws
	 */
	public Map<Object, Object> getDynamicDate(Map<Object, Object> map) {
		Date sdate = (Date) map.get("sdate");
		Map<Object, Object> dynamicMap = new HashMap<>();
		 long stimes = sdate.getTime();//支付日期毫秒数
		 long ntimes = System.currentTimeMillis();//系统时间毫秒数
		 String date = DateUtil.format(sdate, "MM/dd HH:mm");
		 if((ntimes-stimes) > 30*60*1000){
			 //如果当前系统毫秒数与支付时间毫秒数的差大于三十分钟则为过期时间，按产品要求制造假数据
			 Random random = new Random();//随机生成数字
			 long r = (random.nextInt(4)+1)*60*1000;//随机时间毫秒数
			 ntimes = ntimes+r;//系统时间加随机时间毫秒数
			 Date d = new Date(ntimes);
			 date = DateUtil.format(d, "MM/dd HH:mm");
		 }
		 dynamicMap.put("sdate", date);
		 dynamicMap.put("integral", "消费了"+map.get("integral")+"积分");
		return dynamicMap;
	}
}
