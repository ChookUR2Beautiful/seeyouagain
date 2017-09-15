package com.xmniao.xmn.core.xmer.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xmniao.xmn.core.util.VersionUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.SellerInfoRequest;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.verification.dao.BillDao;
import com.xmniao.xmn.core.xmer.dao.AreaDao;
import com.xmniao.xmn.core.xmer.dao.SellerInfoDao;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：SellerInfoService   
* 类描述：商户资料信息service   
* 创建人：xiaoxiong   
* 创建时间：2016年5月23日 上午9:35:36   
* @version    
*
 */
@Service
public class SellerInfoService {
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(SellerInfoService.class);
		
	@Autowired
	private SellerInfoDao sellerInfoDao;
	/**
	 * 注入地区Dao
	 */
	@Autowired
	private AreaDao areaDao;
	
	/**
	 * 注入订单Dao
	 */
	@Autowired
	private BillDao billDao;
	
	@Resource
	private String fileUrl;
	
	@Autowired
	private SessionTokenService sessionTokenService;
	
	
	@Autowired
	private PropertiesUtil propertiesUtil;

	@Autowired
	private SellerService sellerService;
	@Autowired
	private XmerService xmerService;

	/**
	 * 
	* @Title: querSellerInfo
	* @Description: 查询商户信息
	* @return Object  返回商铺信息 、成功、失败信息
	* @throws
	* @Description 添加返回identitynurl字段手持身份证url，修改picMap.get("islogo")+"")==2为picMap.get("islogo")+"")==1 LOGO图片
	* @update zhengyaowen
	* @date 2016-05-30	 13:46
	*/
	public Object querSellerInfo(SellerInfoRequest sellerInfo) {
		try {
			Map<Object, Object> sellerInfoMap=null;
			DecimalFormat df = new DecimalFormat("0.00");//格式化double类型数据
			Integer sellerid = sellerInfo.sellerid();
			try {
				//查询商家基本信息
				 sellerInfoMap=sellerInfoDao.querySellerInfoBySellerid(sellerid);
				 if(sellerInfoMap!=null&&!sellerInfoMap.isEmpty()){
					 
					 Integer status = Integer.parseInt(sellerInfoMap.get("status").toString());
					 Integer isonline = Integer.parseInt(sellerInfoMap.get("isonline").toString());
					//非草稿箱的 签约店铺状态码。 审核中，已通过，未通过，已上线
					 if(!(status==0 || status==4)){
						 
						 Integer sellerStatus = 0; //默认0已通过
						 if(isonline==1 && status == 3){//已上线
							 sellerStatus = 1;
						 }else if(status==1){//审核中
							 sellerStatus = 2;
						 }else if(status==2){//未通过
							 sellerStatus = 3;
						 }
						 sellerInfoMap.put("sellerStatus", sellerStatus);
						 
						 if(sellerStatus.equals(3)){	//未通过时，页面显示温馨提示
							 String refuseTips = propertiesUtil.getValue("refuseTips", "conf_xmer.properties");
							 sellerInfoMap.put("refuseTips", refuseTips);
						 }
					 }
					 
					 //已上线，查询提成总额
					 if(isonline==1){
						 BigDecimal totalXmerCommission = new BigDecimal(0.00);
						 List<String> commissionList = sellerInfoDao.querySellerCommission(sellerid);
						 if(commissionList != null){
							 for (String commission : commissionList) {
								JSONObject json = JSON.parseObject(commission);
								BigDecimal mikeAmount = json.getBigDecimal("mike_amount");
								if(null != mikeAmount){
									totalXmerCommission = totalXmerCommission.add(mikeAmount);
								}
							}
						 }
						 sellerInfoMap.put("totalXmerCommission", totalXmerCommission);
					 }
					 
					 //查询省编号名称
					 if (StringUtils.isEmpty(sellerInfoMap.get("provincenum").toString().trim())) {
						 sellerInfoMap.put("province", "");
						 sellerInfoMap.put("provincenum", "0");
					}else {
						//查询省名称
						String provincename=areaDao.queryAreaByAreaId(Integer.parseInt(sellerInfoMap.get("provincenum").toString()));
						sellerInfoMap.put("province", provincename);
					}
					 
					 //查询市编号
					 if (StringUtils.isEmpty(sellerInfoMap.get("citynum").toString().trim())) {
						 sellerInfoMap.put("city", "");
						 sellerInfoMap.put("citynum", "0");
					}else {
						 //查询市名称
						 String cityname=areaDao.queryAreaByAreaId(Integer.parseInt(sellerInfoMap.get("citynum").toString()));
						 sellerInfoMap.put("city", cityname);
					}
					 
					//查询地区名称
					 if (StringUtils.isEmpty(sellerInfoMap.get("areanum").toString().trim())) {
						 sellerInfoMap.put("area", "");
						 sellerInfoMap.put("areanum", "0");
					 }else {
						 //查询市名称
						 String areaname=areaDao.queryAreaByAreaId(Integer.parseInt(sellerInfoMap.get("areanum").toString()));
						 sellerInfoMap.put("area", areaname);
					 }
					 
					 if(Integer.parseInt(sellerInfoMap.get("status").toString())==3){
						 try {
							 //查询总流水
							double totalflow=billDao.queryTotalflow(sellerid);
							//格式化double类型数据，保留两位小数，四舍五入
							BigDecimal b = new BigDecimal(totalflow);
							totalflow = b.setScale(2, RoundingMode.HALF_UP).doubleValue();
							String totalf = df.format(totalflow);
							sellerInfoMap.put("totalflow", totalf);
						} catch (Exception e) {
							e.printStackTrace();
							log.error("查询总流水失败！");
						}
						 
						try {
							//查询本月流水
							double mouthflow=billDao.queryMonthflow(sellerid);
							BigDecimal b1 = new BigDecimal(mouthflow);
							mouthflow = b1.setScale(2, RoundingMode.HALF_UP).doubleValue();
							String mouthf = df.format(mouthflow);
							sellerInfoMap.put("mouthflow", mouthf);
						} catch (Exception e) {
							e.printStackTrace();
							log.error("查询月流水失败！");
						}
						 
					 }else{
						 sellerInfoMap.put("totalflow", 0);
						 sellerInfoMap.put("mouthflow", 0);
					 }
				 }
			} catch (Exception e) {
				e.printStackTrace();
				log.error("查询商户基本信息失败");
			}
			
			if(sellerInfoMap==null||sellerInfoMap.isEmpty()){
				return new BaseResponse(ResponseCode.DATA_NULL, "没有找到数据信息");
			}
			Map<Object,Object> materialOrderMap = null;

			// H5特殊处理
			if (sellerInfo.getSessiontoken() != null && !sellerInfo.getSessiontoken().isEmpty()) {
				//查询店铺物料订单
				Map<Object,Object> statusMap = new HashMap<Object,Object>();
				statusMap.put("mid", sellerid);//商铺id
				statusMap.put("uid",Integer.valueOf(sessionTokenService.getStringForValue(sellerInfo.getSessiontoken()).toString()));//用户id
				materialOrderMap = sellerInfoDao.queryMaterialOrderBySellerId(statusMap);
			}

			
			Integer status = 0;
			String remark = "";
			String orderNo = "";
			if(materialOrderMap!=null && materialOrderMap.size()>0 ){
				status = (Integer) materialOrderMap.get("status");
				remark = materialOrderMap.get("remark").toString();
				orderNo = materialOrderMap.get("order_sn")+"";
			}
			
			if(status == null || status == 2 || status == 0){
				sellerInfoMap.put("issendmt", Constant.MATERIEL_STATUS_NOT_BUY);//为购买  0
				sellerInfoMap.put("buttonname", Constant.MATERIEL_BUTTONNAME_BUY);//购买物料
				sellerInfoMap.put("materialStatus", 0);//未购买
			}else if(status==4){
				sellerInfoMap.put("materialStatus", 2);//已购买
			}else{
				sellerInfoMap.put("issendmt", Constant.MATERIEL_STATUS_PAY);//已支付  1
				sellerInfoMap.put("buttonname", Constant.MATERIEL_BUTTONNAME_CALL);//拨打客户电话查询物料进度
				sellerInfoMap.put("materialStatus", 1);//购买中
			}
			sellerInfoMap.put("remark", remark);
			sellerInfoMap.put("orderNo", orderNo);
			
			String serviceTel = propertiesUtil.getValue("material_service_tel", "conf_xmer.properties");
			sellerInfoMap.put("telephone", serviceTel);
			
			try {
				Map<Object,	Object> sellerDetailedMap=sellerInfoDao.querySellerDetailedBySellerid(sellerid);
				if(sellerDetailedMap == null){
					sellerDetailedMap = new HashMap<Object,Object>();
					sellerDetailedMap.put("consume", 0D);
				}
//				if(sellerDetailedMap!=null&&!sellerDetailedMap.isEmpty()){
					sellerInfoMap.put("consume", sellerDetailedMap.get("consume"));
//				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("查询商户详细信息失败");
			}
			//商铺经度和纬度
			try{
				Map<Object,Object> landmarkMap = sellerInfoDao.querySellerLandMark(sellerid);
				if(landmarkMap == null || landmarkMap.size() <1){
					landmarkMap = new HashMap<Object,Object>();
					landmarkMap.put("longitude", "");
					landmarkMap.put("latitude", "");
				}
				sellerInfoMap.put("longitude", landmarkMap.get("longitude"));//经度
				sellerInfoMap.put("latitude", landmarkMap.get("latitude"));//纬度
			}catch(Exception e){
				e.printStackTrace();
				log.info("查询商铺经度，纬度异常");
			}
			
			try {
				//查询积分菜品
				List<Map<Object,Object>> foodList = sellerInfoDao.queryBargainBySellerid(sellerid);
				if(foodList!=null){
					for(Map<Object,Object> foodMap : foodList){
						foodMap.put("url", fileUrl+foodMap.get("url"));
						
					}
					sellerInfoMap.put("coinspic", foodList);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				log.error("查询特惠信息失败");
			}

			try {
				List<Map<Object,Object>> sellerpic=sellerInfoDao.querySellerPic(sellerid);
				
				sellerInfoMap.put("cover", "");
				sellerInfoMap.put("logo", "");
				List<Map<Object,Object>> picList=new ArrayList<>();
				if(sellerpic!=null){
					for(Map<Object,Object> picMap : sellerpic){
						Object picUrlObject = picMap.get("url");
						String picUrl = "";
						if (picUrlObject != null && !"null".equals(picUrlObject.toString()) && !"".equals(picUrlObject.toString().trim())) {
							picUrl = safeToShowPicUrl(picUrlObject.toString());
						}
//						if (Integer.parseInt(picMap.get("islogo").toString()) == 3) {  //合同图
//							contractList.add(picMap);
//						} else
						if(Integer.parseInt(picMap.get("islogo").toString())==2){  // 商家图
							sellerInfoMap.put("cover", picUrl);
						}else if(Integer.parseInt(picMap.get("islogo").toString()) == 1){  // logo图
							sellerInfoMap.put("logo", picUrl);
						}else{  // 0 环境图
							//查询商家基本信息
							Map<Object, Object> map=sellerInfoDao.querySellerInfoBySellerid(sellerid);
							if ("1".equals(map.get("isonline"))) {
								//查询已上线的店铺环境图
								picList = sellerInfoDao.querySellerPicApply(sellerid);
							}else {
								picMap.put("url", picUrl);
								picMap.remove("islogo");
							}
							if (!"".equals(picUrl)) {
								picList.add(picMap);
							}
						}
					}
					sellerInfoMap.put("sellerpic",picList);


					Object identityzurl = sellerInfoMap.get("identityzurl");
					Object identityfurl = sellerInfoMap.get("identityfurl");
					Object identitynurl = sellerInfoMap.get("identitynurl");
					Object licenseurl = sellerInfoMap.get("licenseurl");
					Object licensefurl = sellerInfoMap.get("hygienic_license_img");  //卫生许可证

					sellerInfoMap.put("identityzurl", "");
					sellerInfoMap.put("identityfurl", "");
					sellerInfoMap.put("identitynurl", "");
					sellerInfoMap.put("licenseurl", "");
					sellerInfoMap.put("licensefurl", "");

					if (identityfurl != null && !"null".equals(identityfurl.toString()) && !"".equals(identityfurl.toString().trim())) {
						String url = safeToShowPicUrl(identityfurl.toString());
						sellerInfoMap.put("identityfurl", url);
					}
					if (identityzurl != null && !"null".equals(identityzurl.toString()) && !"".equals(identityzurl.toString().trim())) {
						String url = safeToShowPicUrl(identityzurl.toString());
						sellerInfoMap.put("identityzurl", url);
					}
					if (identitynurl != null && !"null".equals(identitynurl.toString()) && !"".equals(identitynurl.toString().trim())) {
						String url = safeToShowPicUrl(identitynurl.toString());
						sellerInfoMap.put("identitynurl", url);
					}
					if (licenseurl != null && !"null".equals(licenseurl.toString()) && !"".equals(licenseurl.toString().trim())) {
						String url = safeToShowPicUrl(licenseurl.toString());
						sellerInfoMap.put("licenseurl", url);

					}
					if (licensefurl != null && !"null".equals(licensefurl.toString()) && !"".equals(licensefurl.toString().trim())) {
						String url = safeToShowPicUrl(licensefurl.toString());
						sellerInfoMap.put("licensefurl", url);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("查询商家图片失败");
			}
			// 商圈名称
			try {
				String zoneid = sellerInfoMap.get("zoneid") == null ? "0" : sellerInfoMap.get("zoneid").toString();
				Map<Object, Object> map = sellerService.queryBusinessByZoneid(Integer.parseInt(zoneid));
				sellerInfoMap.put("zoneName", map == null ? "" : map.get("title") == null ? "" : map.get("title").toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
			Object agioObj = sellerInfoMap.get("agio");
			int agioVersion = 360;
			try {
				String version = propertiesUtil.getValue("aigo_version", "conf_common.properties");
				agioVersion = Integer.parseInt(version);
			} catch (Exception e) {
				log.warn("读取折扣兼容版本配置失败");
			}

//			签约折扣
			if (agioObj != null) {
				if (VersionUtil.getVersionCode(sellerInfo) >= agioVersion ) {  //360版本之前返回的是小数
					Double agio = Double.parseDouble(agioObj.toString());
					agio = agio * 10;
					DecimalFormat decimalFormat = new DecimalFormat("#.0");
					String snumberStr = decimalFormat.format(agio);
					sellerInfoMap.put("agio", snumberStr);
				}
			}
			// tradename bewrite 兼容
			sellerInfoMap.put("bewrite", sellerInfoMap.get("tradename"));

			// saas类型，0 代表没saas类型 1 寻觅客 2中脉 3 V客  4主播(V客赠送)SAAS签约',
			Integer saas_type = null;
			try {
				if (sellerInfoMap.get("saas_type") != null) {
					saas_type = Integer.parseInt(sellerInfoMap.get("saas_type").toString());
				}
			} catch (Exception e) {
				log.warn("获取saas类型失败");
			}
			// 公司名称
			String company = sellerInfoMap.get("company_name") == null ? "" : sellerInfoMap.get("company_name").toString();
			sellerInfoMap.put("company", company);

			// 合同名称
			sellerInfoMap.put("business_license_name", sellerInfoMap.get("business_license_name") == null ? "" : sellerInfoMap.get("business_license_name").toString());
			// 连锁名称
			sellerInfoMap.put("lssellername", sellerInfoMap.get("lssellername") == null ? "" : sellerInfoMap.get("lssellername").toString());
			// 连锁父id
			sellerInfoMap.put("fatherid", sellerInfoMap.get("fatherid") == null ? "-1" : sellerInfoMap.get("fatherid").toString());
			// 合同图片
			sellerInfoMap.put("sellerPics", new ArrayList<Map<Object, Object>>());

			try {
				List<Map<Object, Object>> sPics = new ArrayList<>();
				String agreement = sellerInfoMap.get("agreement") == null ? "" : sellerInfoMap.get("agreement").toString();
				String agreement2 = sellerInfoMap.get("agreement2") == null ? "" : sellerInfoMap.get("agreement2").toString();
				String agreement3 = sellerInfoMap.get("agreement3") == null ? "" : sellerInfoMap.get("agreement3").toString();
				String agreement4 = sellerInfoMap.get("agreement4") == null ? "" : sellerInfoMap.get("agreement4").toString();
				String agreement5 = sellerInfoMap.get("agreement5") == null ? "" : sellerInfoMap.get("agreement5").toString();
				String agreement6 = sellerInfoMap.get("agreement6") == null ? "" : sellerInfoMap.get("agreement6").toString();

				List<String> contractList = new ArrayList<String>();
				contractList.add(safeToShowPicUrl(agreement));
				contractList.add(safeToShowPicUrl(agreement2));
				contractList.add(safeToShowPicUrl(agreement3));
				contractList.add(safeToShowPicUrl(agreement4));
				contractList.add(safeToShowPicUrl(agreement5));
				contractList.add(safeToShowPicUrl(agreement6));
				int index = 0;
				for (String url : contractList) {
					if (url.trim().equals("")) {
						continue;
					}
					Map<Object, Object> contract = new HashMap<Object, Object>();
					contract.put("id", index++);
					contract.put("url", url);
					contract.put("pType", 3);
					sPics.add(contract);
				}
				sellerInfoMap.put("sellerPics", sPics);
			} catch (Exception e) {
				log.warn("获取合同图失败", e);
			}



			sellerInfoMap.put("saas_desc", "");
			sellerInfoMap.put("saas_type", 0);
			if (saas_type != null) {
				sellerInfoMap.put("saas_type", saas_type);
				Map<Integer, String> m1 = xmerService.getSaasInfoDescMap();
				String desc = m1.get(saas_type);
				sellerInfoMap.put("saas_desc", desc == null ? "" : desc);
			}
			//分享
			sellerInfoMap.put("sharetitle", "我想寻蜜鸟发现一家不错的店【"+sellerInfoMap.get("sellername")+"】");//分享标题
			sellerInfoMap.put("sharetext", "寻蜜鸟优惠满天飞：买单立减，优惠卷狂送，还送积分，猛戳查看");//分享内容
			sellerInfoMap.put("shareimg","");//分享图片
			sellerInfoMap.put("shareurl", Constant.SELLER_DETAIL_URL+sellerid);	//分享链接
			MapResponse response=new MapResponse(ResponseCode.SUCCESS,"成功");
			response.setResponse(sellerInfoMap);
			return response;
				
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE,"查询商铺信息失败");
		}
	}


	// 返回前端的pic URL
	public String safeToShowPicUrl(String picUrl) {
		if (picUrl == null || picUrl.trim().equals("")) {
			return "";
		}
		if (picUrl.contains(fileUrl)) {
			return picUrl.trim();
		} else {
			return fileUrl + picUrl.trim();
		}
	}

	// 存储到数据库的pic URL
	public String safeToSavePicUrl(String picUrl) {
		if (picUrl == null || picUrl.trim().equals("")) {
			return "";
		}
		return picUrl.trim().replace(fileUrl, "");
	}

}
