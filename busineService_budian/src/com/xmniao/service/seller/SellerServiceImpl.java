package com.xmniao.service.seller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;
import com.xmniao.dao.seller.SellerDao;
import com.xmniao.domain.message.MXmnSeller;
import com.xmniao.domain.seller.SellerBean;
import com.xmniao.service.common.MongoBaseService;
import com.xmniao.service.live.LiveOrderServiceImpl;
import com.xmniao.service.user.UserActionServiceImpl;
import com.xmniao.thrift.busine.common.FailureException;
import com.xmniao.thrift.busine.common.ResponseData;
import com.xmniao.thrift.busine.seller.SellerService;

@Service
public class SellerServiceImpl implements SellerService.Iface{
    /*
     * 日志记录
     */
    private final Logger log = Logger.getLogger(SellerServiceImpl.class);
    
    @Autowired
    private SellerDao sellerDao;
    
    @Autowired
    private LiveOrderServiceImpl liveOrderService;
    
    @Autowired
    private StringRedisTemplate redisTemplate;
    
    @Autowired
    private MongoBaseService mongoService;
    
    @Autowired
    private UserActionServiceImpl userActionService;
    
    @Resource(name="sellerAction")
    private String sellerAction;
    
    @Resource(name="sellerActionBackup")
    private String sellerActionBackup;
    
    @Resource(name="sellerMongo")
    private String sellerMongo;
    
    @Resource(name="sellerInfoQueue")
    private String sellerInfoQueue;
    
    @Resource(name="sellerRandomNum")
    private String sellerRandomNum;
    
	//是否有合作商
	public boolean isJoint(Integer jointid){
		if(null==jointid){
			return false;
		}
		//检测合作商是否正常
		Map<String,Object> jointInfo = sellerDao.getJointInfo(jointid);
		if(jointInfo!=null && jointInfo.get("status")!=null && (int)jointInfo.get("status")==1){
			return true;
		}else{
			log.error("该经销商【"+jointid+"】已下线或不存在");
			return false;
		}
	}
	//是否有商家
	public boolean isSeller(Integer sellerid){
		if(null==sellerid){
			return false;
		}
		//检测商家是否正常
		SellerBean sellerBean = sellerDao.getSellerInfo(sellerid);
		if(sellerBean!=null && sellerBean.getIsonline() !=null && sellerBean.getIsonline()==1){
			return true;
		}else{
			log.error("该商家【"+sellerid+"】已下线或不存在");
			return false;
		}
	}
	
	/**
	 * 获取商家的浏览/消费/收藏信息
	 * @Title: getSellerAnalysisInfo 
	 * @Description:
	 */
	public ResponseData getSellerAnalysisInfo(Map<String,String> paraMap){
		ResponseData responseData = new ResponseData();
		try{
		Map<String,String> resultMap = new HashMap<String,String>();
		if(StringUtils.isNotBlank(paraMap.get("sellerid"))){
			int sellerid = Integer.parseInt(paraMap.get("sellerid"));
			if(StringUtils.isBlank(paraMap.get("type")) || paraMap.get("type").equals("0")){
			      Aggregation agg = Aggregation.newAggregation(
			    		  Aggregation.match(Criteria.where("sellerid").is(sellerid).and("operate").is(1)),
			    		  Aggregation.group("sellerid").sum("index").as("totalNum")
		          );
				 List<BasicDBObject> resultList = mongoService.sumCount(agg,sellerAction);
				 if(resultList.size()==0){
					 return new ResponseData(1, "MongoDB中没有该商家的浏览统计数据", null);
				 }
				 long views = resultList.get(0).getLong("totalNum");
					resultMap.put("views", views+"");
				
			      Aggregation agg2 = Aggregation.newAggregation(
			    		  Aggregation.match(Criteria.where("sellerid").is(sellerid).and("operate").is(2)),
			    		  Aggregation.group("sellerid").sum("index").as("totalNum")
		          );
				 List<BasicDBObject> resultList2 = mongoService.sumCount(agg2,sellerAction);
				 if(resultList.size()==0){
					 return new ResponseData(1, "MongoDB中没有该商家的消费统计数据", null);
				 }
				 long consumption = resultList2.get(0).getLong("totalNum");
				 resultMap.put("consumption", consumption+"");
				 
				long saved = sellerDao.countSellerBySaved(sellerid);
				resultMap.put("saved", saved+"");
			}else if(paraMap.get("type").equals("1")){
			      Aggregation agg = Aggregation.newAggregation(
			    		  Aggregation.match(Criteria.where("sellerid").is(sellerid).and("operate").is(1)),
			    		  Aggregation.group("sellerid").sum("index").as("totalNum")
		          );
				 List<BasicDBObject> resultList = mongoService.sumCount(agg,sellerAction);
				 if(resultList.size()==0){
					 return new ResponseData(1, "MongoDB中没有该商家的浏览统计数据", null);
				 }
				 long views = resultList.get(0).getLong("totalNum");
				resultMap.put("views", views+"");
			}else if(paraMap.get("type").equals("2")){
			      Aggregation agg = Aggregation.newAggregation(
			    		  Aggregation.match(Criteria.where("sellerid").is(sellerid).and("operate").is(2)),
			    		  Aggregation.group("sellerid").sum("index").as("totalNum")
		          );
				 List<BasicDBObject> resultList = mongoService.sumCount(agg,sellerAction);
				 if(resultList.size()==0){
					 return new ResponseData(1, "MongoDB中没有该商家的消费统计数据", null);
				 }
				 long consumption = resultList.get(0).getLong("totalNum");
				resultMap.put("consumption", consumption+"");
			}else if(paraMap.get("type").equals("3")){
				long saved = sellerDao.countSellerBySaved(sellerid);
				resultMap.put("saved", saved+"");
			}
			responseData.setState(0);
			responseData.setMsg("查询成功");
			responseData.setResultMap(resultMap);
		}else{
			responseData.setState(2);
			responseData.setMsg("商家编号不能为空");
			responseData.setResultMap(null);
		}
		}catch (Exception e){
			log.error("查询异常",e);
			responseData = new ResponseData(1,"查询失败",null);
		}
		return responseData;
	}
	
	/**
	 * 商户上/下线恢复/保存浏览消费数据
	 */
	@Override
	public ResponseData dealSellerAnalysisInfo(Map<String, String> paramMap)
			throws FailureException, TException {
		ResponseData responseData = null;
		try{
			log.info("商家上线/下线恢复及保存其用户浏览和消费记录，传入参数："+paramMap);
			if(StringUtils.isBlank(paramMap.get("sellerid")) ||StringUtils.isBlank(paramMap.get("operate"))){
				log.info("dealSellerAnalysisInfo:"+paramMap);
				return new ResponseData(2, "传入参数不齐", null);
			}
		
			int sellerid = Integer.parseInt(paramMap.get("sellerid"));
			String operate =paramMap.get("operate");
			List<MXmnSeller> sellerList=null;
			if(operate.equals("1")){	//上线
				//1.获取当前xmn_seller中该商家记录
				//2.将上述记录写入xmn_seller_backup中
				//3.删除xmn_seller中记录
				Criteria criteria = Criteria.where("sellerid").is(sellerid);
				sellerList = mongoService.findAll(sellerActionBackup, criteria, MXmnSeller.class);
				if(sellerList.size()==0){
					log.info("该商家上线时，MongoDB中没有它的用户浏览消费信息，现从历史记录中获取更新");
					boolean oldSeller = userActionService.initOldSellerOnlineData(sellerid);
					if(oldSeller){
						log.info("该商家上线时，已将它的历史用户浏览消费信息写入到MongoDB中现再次查询并统计");
						sellerList = mongoService.findAll(sellerAction, criteria, MXmnSeller.class);
					}
				}else{
					mongoService.insertAll(sellerAction, sellerList);
					mongoService.delete(sellerActionBackup, criteria);
				}
			}else if(operate.equals("-1")){		//下线
				//1.获取=xmn_seller_backup中该商家记录
				//2.将上述记录写入xmn_seller中
				//3.删除xmn_seller_backup中记录
				Criteria criteria = Criteria.where("sellerid").is(sellerid);
				sellerList = mongoService.findAll(sellerAction, criteria, MXmnSeller.class);
				mongoService.insertAll(sellerActionBackup, sellerList);
				mongoService.delete(sellerAction, criteria);
			}
			
			Map<String,String> resultMap = new HashMap<String,String>();
			resultMap.put("sellerid", paramMap.get("sellerid"));
			if(sellerList!=null){
				long consumption = 0;
				long views = 0;
				for(MXmnSeller mXmnSeller:sellerList){
					if(mXmnSeller.getOperate()==2){
						consumption += mXmnSeller.getIndex();
					}else if(mXmnSeller.getOperate()==1){
						views += mXmnSeller.getIndex();
					}
				}
				resultMap.put("consumption", consumption+"");
				resultMap.put("views", views+"");
			}
			responseData = new ResponseData(0,"成功",resultMap);
		}catch(Exception e){
			log.error("处理异常啦！",e);
			responseData = new ResponseData(1,"处理异常",null);
		}
		return responseData;
	}
	
	/**
	 * 批量初始化商家MongoDB数据
	 * @Title: initOnlineSellerInfo 
	 * @Description:
	 */
	public void initOnlineSellerInfo(){

		log.info("初始化更新商家信息");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = sdf.format(new Date());
		int pageNo=0;
		int pageSize=1000;
		Map<String,Object> selMap = new HashMap<String,Object>();
		selMap.put("isonline", 1);
		selMap.put("pageSize", pageSize);
		List<SellerBean> sellerList = null;
		do{ 
			selMap.put("pageNo", pageNo);
			sellerList = sellerDao.getSellerList(selMap);
			pageNo++;
			ResponseData responseData = null;
			ResponseData responseData2 = null;
			Map<String,Object> uMap = new HashMap<String,Object>();
			int i=0;
			for(SellerBean seller:sellerList){
				try{
					Map<String,String> paraMap = new HashMap<String,String>();
					paraMap.put("sellerid", seller.getSellerid()+"");
					paraMap.put("type", "0");
					responseData = this.getSellerAnalysisInfo(paraMap);
					responseData2 = liveOrderService.getSellerLiveCountInfo(paraMap);
					if(responseData.getState()!=0 || responseData2.getState()!=0){
						log.info(responseData.getMsg()+","+responseData2.getMsg());
					}
					uMap.put("saved", responseData.getResultMap()==null?0:Integer.parseInt(responseData.getResultMap().get("saved")));
					uMap.put("consumption", responseData.getResultMap()==null?0:Integer.parseInt(responseData.getResultMap().get("consumption")));
					uMap.put("views", responseData.getResultMap()==null?0:Integer.parseInt(responseData.getResultMap().get("views")));
					
					uMap.put("is_live",Integer.parseInt(responseData2.getResultMap().get("hasLive")));
					uMap.put("is_advance", Integer.parseInt(responseData2.getResultMap().get("hasAdvance")));
					uMap.put("is_fans_coupon", Integer.parseInt(responseData2.getResultMap().get("hasFansCoupon")));
					uMap.put("weights", Integer.parseInt(responseData2.getResultMap().get("weights")));
					uMap.put("udate", now);
					Criteria criteria = Criteria.where("sellerid").is(seller.getSellerid());
					mongoService.updateOne(sellerMongo, criteria, uMap);
				}catch(Exception e){
					log.error("更新商家"+seller.getSellerid()+"信息出错了",e);
				}
			}
			log.info("上线商家拿到"+sellerList.size()+"条数据");
		}while(sellerList.size()>=pageSize);
	
	}
	
	/**
	 * 用于对已上线的商家，进行数据初始化，可重复调用
	 */
	@Override
	public ResponseData initSellerCountInfo(Map<String, String> paramMap)
			throws FailureException, TException {
		try{
		initOnlineSellerInfo();
		}catch(Exception e){
			log.error("执行异常",e);
			return new ResponseData(1,"失败",null);
		}
		return new ResponseData(0,"成功",null);
	}
	
	/**
	 * 更新商家统计MongoDB信息
	 * 
	 */
	@Override
	public ResponseData updateSellerCountInfo(Map<String, String> paramMap)
			throws FailureException, TException {
		ResponseData responData=null;
		try{
			if(StringUtils.isBlank(paramMap.get("sellerid")) 
				|| StringUtils.isBlank(paramMap.get("infoType"))
				|| StringUtils.isBlank(paramMap.get("infoType"))){
				responData = new ResponseData(2, "参数不齐", null);
			}else{
				pushUpdateSellerCountInfoToReids(paramMap);
				responData = new ResponseData(0, "更新成功", null);
			}
		}catch(Exception e){
			log.error("更新失败",e);
			responData = new ResponseData(1, "更新失败", null);
		}
		return responData;
	}
	
	/**
	 * 将更新商家信息的参数写入MongoDB
	 * @Title: pushUpdateSellerCountInfoToReids 
	 * @Description:
	 */
	public void pushUpdateSellerCountInfoToReids(Map<String, String> paramMap){
		redisTemplate.opsForList().leftPush(sellerInfoQueue, JSONObject.toJSONString(paramMap));
	}
	
	/**
	 * 初始化商家mongodb虚拟消费人数
	 */
	@Override
	public ResponseData initMongoSellerRandom() throws FailureException,
			TException {
		log.info("初始化商家mongodb虚拟消费人数...");
		List<HashMap> sellerMap = mongoService.findAll(sellerMongo,new Criteria(),HashMap.class);
		Integer total = sellerMap.size();
		Integer success = 0;
		for (HashMap hashMap : sellerMap) {
			Integer sellerid;
			try {
				sellerid = (Integer) hashMap.get("sellerid");
			} catch (Exception e1) {
				continue;
			}
			Integer sellerRandom = (int)(Math.random()*400)+101;//200-500随机数
			hashMap.put("seller_random_num",sellerRandom);
			hashMap.put("seller_random_num_consumption",(Integer)hashMap.get("consumption")+sellerRandom);
			Criteria criteria = Criteria.where("sellerid").is(sellerid);
			try {
				int result = mongoService.updateOne(sellerMongo, criteria,hashMap);
				redisTemplate.opsForHash().put(sellerRandomNum,sellerid+"",sellerRandom+"");
				success+=result;
			} catch (Exception e) {
				log.error("商家："+sellerid+"初始化数据异常",e);
			}
		}
		log.info("共"+total+"条数据，成功"+success+"条");
		ResponseData responseData = new ResponseData();
		responseData.setState(0);
		responseData.setMsg("初始化成功");
		return responseData;
	}
	
	/**
	 * 
	 * 方法描述：批量插入t_seller_detailed记录商家的详情记录
	 * 创建人： ChenBo
	 * 创建时间：2017年2月20日 void
	 */
	public void initSellerDetail(){
		int count=0;
		final int p=500;
		Map<String,Object> selList = new HashMap<String,Object>();
		List<Integer> list = new ArrayList<Integer>();
		do{
		selList.put("s", 0);
		selList.put("p", p);
		list = sellerDao.getSellerDetailNull(selList);
		if(list.size()>0){
			sellerDao.insertSellerDetail(list);
			count+=list.size();
		}
		}while(list.size()==p);
		System.out.println("Over:"+count);
	}
}
