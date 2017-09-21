/**
 * 
 */
package com.xmniao.service.vstar;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.common.ConversionTypeUtil;
import com.xmniao.common.DateUtil;
import com.xmniao.dao.live.LiveRecordDao;
import com.xmniao.dao.vstar.TVstarPlayerInfoDao;
import com.xmniao.domain.vstar.TVstarPlayerInfo;
import com.xmniao.thrift.pay.LiveWalletService;
import com.xmniao.thrift.pay.TopList;

/**
 * 
 * 项目名称：busineService_vstar
 * 
 * 类名称：VstarPlayerInfoService
 * 
 * 类描述： 新时尚大赛选手统计信息Service
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-6-9 下午4:44:07 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service(value="VstarPlayerInfoService")
public class VstarPlayerInfoService {
	
	private final Logger log=LoggerFactory.getLogger(VstarPlayerInfoService.class);
	
	
	/**
	 * 注入新时尚大赛选手信息DAO
	 */
	@Autowired
	private TVstarPlayerInfoDao playInfoDao;
	
	/**
	 * 注入直播通告DAO
	 */
	@Autowired
	private LiveRecordDao liveRecordDao;
	
	 /**
     * 注入支付服务的IP地址
     */
	@Resource(name="transLedgerIP")
    private String transLedgerIP;
    
    /**
     * 注入支付服务的端口号
     */
	@Resource(name="transLedgerPort")
    private int transLedgerPort;
	
	/**
	 * 
	 * 方法描述：根据主键ID获取选手信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-9下午5:47:54 <br/>
	 * @param id
	 * @return
	 */
	public TVstarPlayerInfo getObject(int id){
		return playInfoDao.getObject(id);
	}
	
	/**
	 * 
	 * 方法描述：获取选手信息列表 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-9下午5:47:54 <br/>
	 * @param id
	 * @return
	 */
	public List<TVstarPlayerInfo> getList(TVstarPlayerInfo playerInfo){
		return playInfoDao.getList(playerInfo);
	}
	

	/**
	 * 
	 * 方法描述：当前节点需处理的记录总数 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-9下午5:47:54 <br/>
	 * @param id
	 * @return
	 */
	public long count(TVstarPlayerInfo playerInfo){
		return playInfoDao.count(playerInfo);
	}
	
	/**
	 * 
	 * 方法描述：更新选手信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-10上午11:58:09 <br/>
	 * @param playerInfo
	 * @return
	 */
	public int update(TVstarPlayerInfo playerInfo){
		int count=0;
		try {
			count= playInfoDao.update(playerInfo);
		} catch (Exception e) {
			this.log.error("更新选手信息失败："+playerInfo.toString());
			e.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 
	 * 方法描述：更新选手统计信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-10下午2:11:36 <br/>
	 * @param playerInfo
	 * @return
	 */
	int updateCountInfo(TVstarPlayerInfo playerInfo){
		return playInfoDao.updateCountInfo(playerInfo);
	}
	
	
	/**
	 * 
	 * 方法描述：获取新时尚大赛关注、点赞信息<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-10下午2:59:00 <br/>
	 * @param paraMap
	 * @return resultMap <br/>
	 * playerId;  type:关注类型 1 关注 2 点赞 ;likeCount 关注、点赞数
	 */
	public List<Map<String,Object>> getVstarLikeList(Map<String,Object> paraMap){
		return playInfoDao.getVstarLikeList(paraMap);
	}
	
	
	/**
	 * 
	 * 方法描述：获取新时尚大赛选手被评论信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-10下午4:12:13 <br/>
	 * @param paraMap
	 * @return
	 */
	List<Map<String,Object>> getVstarCommentList(Map<String,Object> paraMap){
		return playInfoDao.getVstarCommentList(paraMap);
	}
	
	/**
	 * 
	 * 方法描述：获取赛区列表 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-13下午4:15:08 <br/>
	 * @return
	 */
	public List<Map<String,Object>> getDivisionList(){
		return playInfoDao.getDivisionList();
	}
	
	/**
	 * 
	 * 方法描述：执行统计选手信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-9下午6:19:34 <br/>
	 * @param playerReq 
	 */
	public void executeCount(TVstarPlayerInfo playerReq){
		this.log.info("执行统计选手信息--->");
		try {
			long count = count(playerReq);//总记录数
			Integer limit = playerReq.getLimit();
			long pageTotal=(count/limit)+1;//总页数加1(处理出现余数)
			for(int page=1;page<=pageTotal;page++){
				this.log.debug("处理第["+page+"]页数据");
				playerReq.setPage(page);
				List<TVstarPlayerInfo> list = playInfoDao.getList(playerReq);
				
				countLiveRecordInfo(list);//统计(预设值)直播场次，时长等信息
				
				countVstarLikeInfo(list);//统计(预设值)选手关注、点赞信息
				
				countVstarCommentInfo(list);//统计(预设值)选手评论信息
				
				countVstarEggCountInfo(list);//统计(预设值)选手鸟蛋信息
				
				doUpdatePlayerInfo(list);//真正更新选手统计信息
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 方法描述：执行统计选手信息 (周榜)<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-9下午6:19:34 <br/>
	 * @param playerReq 
	 */
	public void executeCountWeek(TVstarPlayerInfo playerReq){
		//TODO
		this.log.info("执行统计选手信息--->");
		try {
			long count = count(playerReq);//总记录数
			Integer limit = playerReq.getLimit();
			long pageTotal=(count/limit)+1;//总页数加1(处理出现余数)
			for(int page=1;page<=pageTotal;page++){
				this.log.debug("处理第["+page+"]页数据");
				playerReq.setPage(page);
				List<TVstarPlayerInfo> list = playInfoDao.getList(playerReq);
				
				countLiveRecordWeekInfo(list);//统计(预设值)直播场次等信息
				
				countVstarLikeWeekInfo(list);//统计(预设值)选手关注、点赞信息-周榜
				
				countVstarEggCountWeekInfo(list);//统计(预设值)选手鸟蛋信息-周榜
				
				doUpdatePlayerInfo(list);//真正更新选手统计信息
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 方法描述：执行统计选手信息 (月榜)<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-9下午6:19:34 <br/>
	 * @param playerReq 
	 */
	public void executeCountMonth(TVstarPlayerInfo playerReq){
		this.log.info("执行统计选手信息--->");
		try {
			long count = count(playerReq);//总记录数
			Integer limit = playerReq.getLimit();
			long pageTotal=(count/limit)+1;//总页数加1(处理出现余数)
			for(int page=1;page<=pageTotal;page++){
				this.log.debug("处理第["+page+"]页数据");
				playerReq.setPage(page);
				List<TVstarPlayerInfo> list = playInfoDao.getList(playerReq);
				
				countLiveRecordMonthInfo(list);//统计(预设值)直播场次等信息
				
				countVstarLikeMonthInfo(list);//统计(预设值)选手关注、点赞信息
				
				countVstarEggCountMonthInfo(list);//统计(预设值)选手鸟蛋信息
				
				doUpdatePlayerInfo(list);//真正更新选手统计信息
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	/**
	 * 方法描述：真正更新选手统计信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-10上午11:51:54 <br/>
	 * @param list
	 */
	private void doUpdatePlayerInfo(List<TVstarPlayerInfo> list) {
		try {
			for(TVstarPlayerInfo playerInfo:list){
				updateCountInfo(playerInfo);
			}
		} catch (Exception e) {
			this.log.error("真正更新选手统计信息异常："+e);
			e.printStackTrace();
		}
	}
	
	 /**
	 * 方法描述：统计(预设值)选手鸟蛋信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-10下午5:00:23 <br/>
	 * @param list
	 */
	private void countVstarEggCountInfo(List<TVstarPlayerInfo> list) {
		List<String> uidList=new ArrayList<String>();
		TTransport transport = null;
		try {
			for(TVstarPlayerInfo playerInfo:list){
				Integer uid = playerInfo.getUid();
				if(null != uid){
					uidList.add(uid.toString());
				}
			}
			
			if(uidList!=null && uidList.size()>0){
				 //调用分账服务的IP和端口号
	            transport = new TSocket(transLedgerIP, transLedgerPort);
	            TFramedTransport frame = new TFramedTransport(transport);
	            // 设置传输协议为 TBinaryProtocol
	            TProtocol protocol = new TBinaryProtocol(frame);
	            //分账服务的综合服务接口模块
	            TMultiplexedProtocol orderProtocol = new TMultiplexedProtocol(protocol, "LiveWalletService");
	            LiveWalletService.Client client = new LiveWalletService.Client(orderProtocol);
	            //打开端口,开始调用
	            transport.open();
	            
	            Map<String, Map<String, String>> liveWalletMsg = client.getLiveWalletMsg(uidList);
	            
	            for(TVstarPlayerInfo playerInfo:list){
	            	Integer uid = playerInfo.getUid();
	            	if(uid!=null){
	            		Map<String, String> walletMsg = liveWalletMsg.get(uid.toString());//直播用户直播钱包信息
		            	if(walletMsg !=null && !walletMsg.isEmpty()){
		            		String balance = walletMsg.get("balance");//鸟蛋余额
		            		String balanceCoin = walletMsg.get("balanceCoin");//转出鸟蛋
		            		BigDecimal balanceEggs=new BigDecimal(balance);
		            		BigDecimal turnOutEggs=new BigDecimal(balanceCoin);
		            		BigDecimal eggCount = balanceEggs.add(turnOutEggs).setScale(0, BigDecimal.ROUND_UP);//打赏鸟蛋
		            		playerInfo.setEggCount(eggCount.intValue());
		            	}
	            	}
	            }
	            
			}
		} catch (Exception e) {
			this.log.error("统计(预设值)选手鸟蛋信息异常："+e);
			e.printStackTrace();
		}finally{
			//关闭连接
			if(transport!=null){
				transport.close();
			}
		}
	}
	
	/**
	 * 方法描述：统计(预设值，周榜-今天前7天的累计)选手鸟蛋信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-10下午5:00:23 <br/>
	 * @param list
	 */
	private void countVstarEggCountWeekInfo(List<TVstarPlayerInfo> list) {
		List<String> uidList=new ArrayList<String>();
		TTransport transport = null;
		try {
			for(TVstarPlayerInfo playerInfo:list){
				Integer uid = playerInfo.getUid();
				if(null != uid){
					uidList.add(uid.toString());
				}
			}
			
			if(uidList!=null && uidList.size()>0){
				 //调用分账服务的IP和端口号
	            transport = new TSocket(transLedgerIP, transLedgerPort);
	            TFramedTransport frame = new TFramedTransport(transport);
	            // 设置传输协议为 TBinaryProtocol
	            TProtocol protocol = new TBinaryProtocol(frame);
	            //分账服务的综合服务接口模块
	            TMultiplexedProtocol orderProtocol = new TMultiplexedProtocol(protocol, "LiveWalletService");
	            LiveWalletService.Client client = new LiveWalletService.Client(orderProtocol);
	            //打开端口,开始调用
	            transport.open();
	            
	            Map<String,String> paraMap=new HashMap<String,String>();
	            int size = uidList.size();
	            paraMap.put("pageSize", size+"");
	            paraMap.put("uids",ConversionTypeUtil.listToString(uidList, ",") );
	            String startTime = DateUtil.getSpecifiedDate(-7);
	            String endTime = DateUtil.getSpecifiedDate(0);
	            paraMap.put("sdate", startTime);
	            paraMap.put("edate", endTime);
	            List<TopList> birdeggTopList = client.BirdeggTopList(paraMap);
	            
	            
	            for(TVstarPlayerInfo playerInfo:list){
	            	Integer uid = playerInfo.getUid();
	            	for(TopList birdEggItem:birdeggTopList){
	            		Map<String, String> resultMap = birdEggItem.resultMap;
	            		String uidItem = resultMap.get("uid");
	            		String birdeggCountItem = resultMap.get("birdeggCount")==null?"":resultMap.get("birdeggCount");
	            		boolean isSame= uid!=null && uidItem!=null && uidItem.equals(uid.toString());
	            		if(isSame){
	            			BigDecimal birdEggCount=new BigDecimal(birdeggCountItem);
	            			birdEggCount.setScale(0, BigDecimal.ROUND_UP);
	            			playerInfo.setEggCountWeek(birdEggCount.intValue());
	            			break;
	            		}
	            	}
	            }
	            
			}
		} catch (Exception e) {
			this.log.error("统计(预设值)选手鸟蛋信息异常："+e);
			e.printStackTrace();
		}finally{
			//关闭连接
			if(transport!=null){
				transport.close();
			}
		}
	}
	
	/**
	 * 方法描述：统计(预设值，月榜-今天前30天的累计)选手鸟蛋信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-10下午5:00:23 <br/>
	 * @param list
	 */
	private void countVstarEggCountMonthInfo(List<TVstarPlayerInfo> list) {
		List<String> uidList=new ArrayList<String>();
		TTransport transport = null;
		try {
			for(TVstarPlayerInfo playerInfo:list){
				Integer uid = playerInfo.getUid();
				if(null != uid){
					uidList.add(uid.toString());
				}
			}
			
			if(uidList!=null && uidList.size()>0){
				 //调用分账服务的IP和端口号
	            transport = new TSocket(transLedgerIP, transLedgerPort);
	            TFramedTransport frame = new TFramedTransport(transport);
	            // 设置传输协议为 TBinaryProtocol
	            TProtocol protocol = new TBinaryProtocol(frame);
	            //分账服务的综合服务接口模块
	            TMultiplexedProtocol orderProtocol = new TMultiplexedProtocol(protocol, "LiveWalletService");
	            LiveWalletService.Client client = new LiveWalletService.Client(orderProtocol);
	            //打开端口,开始调用
	            transport.open();
	            
	            Map<String,String> paraMap=new HashMap<String,String>();
	            int size = uidList.size();
	            paraMap.put("pageSize", size+"");
	            paraMap.put("uids",ConversionTypeUtil.listToString(uidList, ",") );
	            String startTime = DateUtil.getSpecifiedDate(-30);
	            String endTime = DateUtil.getSpecifiedDate(0);
	            paraMap.put("sdate", startTime);
	            paraMap.put("edate", endTime);
	            List<TopList> birdeggTopList = client.BirdeggTopList(paraMap);
	            
	            
	            for(TVstarPlayerInfo playerInfo:list){
	            	Integer uid = playerInfo.getUid();
	            	for(TopList birdEggItem:birdeggTopList){
	            		Map<String, String> resultMap = birdEggItem.resultMap;
	            		String uidItem = resultMap.get("uid");
	            		String birdeggCountItem = resultMap.get("birdeggCount")==null?"":resultMap.get("birdeggCount");
	            		boolean isSame= uid!=null && uidItem!=null && uidItem.equals(uid.toString());
	            		if(isSame){
	            			BigDecimal birdEggCount=new BigDecimal(birdeggCountItem);
	            			birdEggCount.setScale(0, BigDecimal.ROUND_UP);
	            			playerInfo.setEggCountMonth(birdEggCount.intValue());
	            			break;
	            		}
	            	}
	            }
	            
			}
		} catch (Exception e) {
			this.log.error("统计(预设值)选手鸟蛋信息异常："+e);
			e.printStackTrace();
		}finally{
			//关闭连接
			if(transport!=null){
				transport.close();
			}
		}
	}
	
	 /**
	 * 方法描述：统计(预设值)选手评论信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-10下午4:22:24 <br/>
	 * @param list
	 */
	private void countVstarCommentInfo(List<TVstarPlayerInfo> list) {
		List<Integer> liverIdList=new ArrayList<Integer>();
		try {
			for(TVstarPlayerInfo playerInfo:list){
				Integer liverId = playerInfo.getLiverId();
				if(null != liverId){
					liverIdList.add(liverId);
				}
			}
			
			if(liverIdList!=null && liverIdList.size()>0){
				Map<String,Object> paraMap=new HashMap<String,Object>();
				paraMap.put("liverIdList", liverIdList);
				List<Map<String,Object>> vstarCommentInfoList = playInfoDao.getVstarCommentList(paraMap);
				for(TVstarPlayerInfo playerInfo:list){
					for(Map<String,Object> vstarCommentInfo : vstarCommentInfoList){
						Integer id = playerInfo.getId();//选手ID
						Integer playerId = (Integer)vstarCommentInfo.get("playerId");//评论表选手ID
						boolean isSame= id!=null && playerId != null && id.intValue()==playerId.intValue();
						if(isSame){
							Integer commentCount =new Integer(vstarCommentInfo.get("commentCount").toString());
							playerInfo.setCommentCount(commentCount);
						}
					}
				}
				
			}
		} catch (Exception e) {
			this.log.error("统计(预设值)直播场次，时长等信息 异常："+e);
			e.printStackTrace();
		}
		
	}


	 /**
	 * 方法描述：统计(预设值)选手关注、点赞信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-10下午3:04:09 <br/>
	 * @param list
	 */
	private void countVstarLikeInfo(List<TVstarPlayerInfo> list) {
		List<Integer> playerIdList=new ArrayList<Integer>();
		List<Integer> uidList = new ArrayList<Integer>();
		try {
			for(TVstarPlayerInfo playerInfo:list){
				Integer playerId = playerInfo.getId();
				if(null != playerId){
					playerIdList.add(playerId);
				}
				
				Integer uid = playerInfo.getUid();
				if(null != uid){
					uidList.add(uid);
				}
			}
			
			if(playerIdList!=null && playerIdList.size()>0){
				Map<String,Object> paraMap=new HashMap<String,Object>();
				paraMap.put("playerIdList", playerIdList);
				List<Map<String,Object>> vstarLikeInfoList = playInfoDao.getVstarLikeList(paraMap);
				for(TVstarPlayerInfo playerInfo:list){
					for(Map<String,Object> vstarLikeInfo : vstarLikeInfoList){
						Integer id = playerInfo.getId();//选手ID
						Integer playerId = (Integer)vstarLikeInfo.get("playerId");//关注、点赞表选手ID
						boolean isSame= id!=null && playerId != null && id.intValue()==playerId.intValue();
						if(isSame){
							Integer type=(Integer)vstarLikeInfo.get("type");//关注类型 1 关注 2 点赞
							Integer likeCount=new Integer(vstarLikeInfo.get("likeCount").toString());//关注、点赞数量
							if(type!=null && type.intValue()==1){//关注粉丝数
//								Integer fansCountInit=playerInfo.getFansCountInit()==null?0:playerInfo.getFansCountInit();//初始粉丝数量
//								likeCount += fansCountInit;
								playerInfo.setFansCount(likeCount);
							}
							
							if(type != null && type.intValue()==2){//点赞数
								playerInfo.setLikeCount(likeCount);
							}
						}
					}
					
				}
				
			}
			
			if(uidList != null && uidList.size()>0){
				Map<String,Object> paramMap = new HashMap<String,Object>();
				paramMap.put("uidList", uidList);
				List<Map<String,Object>> fansCountInitList = getFansCountInitList(paramMap);
				for(TVstarPlayerInfo playerInfo:list){
					for(Map<String,Object> fansCountInitMap:fansCountInitList){
						Integer uid = playerInfo.getUid();
						Integer endUid = (Integer)fansCountInitMap.get("endUid");
						boolean isSame = uid!=null && endUid!=null && uid.intValue()==endUid.intValue();
						if(isSame){
							Integer fansCountInit = new Integer(fansCountInitMap.get("fansCountInit").toString());//APP端用户被关注数
							playerInfo.setFansCountInit(fansCountInit);
							break;
						}
					}
				}
			}
		} catch (Exception e) {
			this.log.error("统计(预设值)直播场次，时长等信息 异常："+e);
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 方法描述：统计(预设值)选手关注信息(周榜-今天前7天的累计) <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-10下午3:04:09 <br/>
	 * @param list
	 */
	private void countVstarLikeWeekInfo(List<TVstarPlayerInfo> list) {
		List<Integer> playerIdList=new ArrayList<Integer>();
		List<Integer> uidList = new ArrayList<Integer>();
		try {
			for(TVstarPlayerInfo playerInfo:list){
				Integer playerId = playerInfo.getId();
				if(null != playerId){
					playerIdList.add(playerId);
				}
				
				Integer uid = playerInfo.getUid();
				if(null != uid){
					uidList.add(uid);
				}
			}
			
			if(playerIdList!=null && playerIdList.size()>0){
				Map<String,Object> paraMap=new HashMap<String,Object>();
				String startTime = DateUtil.getSpecifiedDate(-7);//当前时间加上天数，获取指定日期 
				String endTime = DateUtil.getSpecifiedDate(0);
				paraMap.put("playerIdList", playerIdList);
				paraMap.put("startTime", startTime);
				paraMap.put("endTime", endTime);
				List<Map<String,Object>> vstarLikeInfoList = playInfoDao.getVstarLikeList(paraMap);
				for(TVstarPlayerInfo playerInfo:list){
					for(Map<String,Object> vstarLikeInfo : vstarLikeInfoList){
						Integer id = playerInfo.getId();//选手ID
						Integer playerId = (Integer)vstarLikeInfo.get("playerId");//关注、点赞表选手ID
						boolean isSame= id!=null && playerId != null && id.intValue()==playerId.intValue();
						
						if(isSame){
							Integer type=(Integer)vstarLikeInfo.get("type");//关注类型 1 关注 2 点赞
							Integer likeCount=new Integer(vstarLikeInfo.get("likeCount").toString());//关注、点赞数量
							if(type!=null && type.intValue()==1){//关注粉丝数
								playerInfo.setFansCountWeek(likeCount);
							}
							
							if(type != null && type.intValue()==2){//点赞数
								playerInfo.setLikeCountWeek(likeCount);
							}
						}
						
					}
					
				}
				
			}
			
			// 统计APP端用户被关注数
			if(uidList != null && uidList.size()>0){
				Map<String,Object> paramMap = new HashMap<String,Object>();
				String startTime = DateUtil.getSpecifiedDate(-7);//当前时间加上天数，获取指定日期 
				String endTime = DateUtil.getSpecifiedDate(0);
				paramMap.put("uidList", uidList);
				paramMap.put("startTime", startTime);
				paramMap.put("endTime", endTime);
				List<Map<String,Object>> fansCountInitList = getFansCountInitList(paramMap);
				for(TVstarPlayerInfo playerInfo:list){
					for(Map<String,Object> fansCountInitMap:fansCountInitList){
						Integer uid = playerInfo.getUid();
						Integer endUid = (Integer)fansCountInitMap.get("endUid");
						boolean isSame = uid!=null && endUid!=null && uid.intValue()==endUid.intValue();
						if(isSame){
							Integer fansCountInit = new Integer(fansCountInitMap.get("fansCountInit").toString());//APP端用户被关注数
							playerInfo.setFansCountInitWeek(fansCountInit);
							break;
						}
					}
				}
			}
		} catch (Exception e) {
			this.log.error("统计(预设值)直播场次，时长等信息 异常："+e);
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 方法描述：统计(预设值)选手关注信息(月榜-今天前30天的累计) <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-10下午3:04:09 <br/>
	 * @param list
	 */
	private void countVstarLikeMonthInfo(List<TVstarPlayerInfo> list) {
		List<Integer> playerIdList=new ArrayList<Integer>();
		List<Integer> uidList = new ArrayList<Integer>();
		try {
			for(TVstarPlayerInfo playerInfo:list){
				Integer playerId = playerInfo.getId();
				if(null != playerId){
					playerIdList.add(playerId);
				}
				
				Integer uid = playerInfo.getUid();
				if(null != uid){
					uidList.add(uid);
				}
			}
			
			if(playerIdList!=null && playerIdList.size()>0){
				Map<String,Object> paraMap=new HashMap<String,Object>();
				String startTime = DateUtil.getSpecifiedDate(-30);//当前时间加上天数，获取指定日期 
				String endTime = DateUtil.getSpecifiedDate(0);
				paraMap.put("playerIdList", playerIdList);
				paraMap.put("startTime", startTime);
				paraMap.put("endTime", endTime);
				List<Map<String,Object>> vstarLikeInfoList = playInfoDao.getVstarLikeList(paraMap);
				for(TVstarPlayerInfo playerInfo:list){
					for(Map<String,Object> vstarLikeInfo : vstarLikeInfoList){
						Integer id = playerInfo.getId();//选手ID
						Integer playerId = (Integer)vstarLikeInfo.get("playerId");//关注、点赞表选手ID
						boolean isSame= id!=null && playerId != null && id.intValue()==playerId.intValue();
						
						if(isSame){
							Integer type=(Integer)vstarLikeInfo.get("type");//关注类型 1 关注 2 点赞
							Integer likeCount=new Integer(vstarLikeInfo.get("likeCount").toString());//关注、点赞数量
							if(type!=null && type.intValue()==1){//关注粉丝数
								playerInfo.setFansCountMonth(likeCount);
							}
							
							if(type!=null && type.intValue()==2){//点赞数
								playerInfo.setLikeCountMonth(likeCount);
							}
						}
						
					}
				}
				
				
			}
			
			// 统计APP端用户被关注数
			if(uidList != null && uidList.size()>0){
				Map<String,Object> paramMap = new HashMap<String,Object>();
				String startTime = DateUtil.getSpecifiedDate(-30);//当前时间加上天数，获取指定日期 
				String endTime = DateUtil.getSpecifiedDate(0);
				paramMap.put("uidList", uidList);
				paramMap.put("startTime", startTime);
				paramMap.put("endTime", endTime);
				List<Map<String,Object>> fansCountInitList = getFansCountInitList(paramMap);
				for(TVstarPlayerInfo playerInfo:list){
					for(Map<String,Object> fansCountInitMap:fansCountInitList){
						Integer uid = playerInfo.getUid();
						Integer endUid = (Integer)fansCountInitMap.get("endUid");
						boolean isSame = uid!=null && endUid!=null && uid.intValue()==endUid.intValue();
						if(isSame){
							Integer fansCountInit = new Integer(fansCountInitMap.get("fansCountInit").toString());//APP端用户被关注数
							playerInfo.setFansCountInitMonth(fansCountInit);
							break;
						}
					}
				}
			}
		} catch (Exception e) {
			this.log.error("统计(预设值)直播场次，时长等信息 异常："+e);
			e.printStackTrace();
		}
		
	}

	

	/**
	 * 方法描述：统计(预设值)直播场次，时长等信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-10上午11:31:38 <br/>
	 * @param list
	 */
	private void countLiveRecordInfo(List<TVstarPlayerInfo> list) {
		List<Integer> liverIdList=new ArrayList<Integer>();
		try {
			for(TVstarPlayerInfo playerInfo:list){
				Integer liverId = playerInfo.getLiverId();
				if(null != liverId){
					liverIdList.add(liverId);
				}
			}
			
			if(liverIdList!=null && liverIdList.size()>0){
				Map<String,Object> paraMap=new HashMap<String,Object>();
				paraMap.put("liverIds", liverIdList);
				List<Map<String,Object>> liveInfoList = liveRecordDao.getLiveInfoList(paraMap);
				for(TVstarPlayerInfo playerInfo:list){
					for(Map<String,Object> liveRecordMap:liveInfoList){
						Integer liverId = (Integer)liveRecordMap.get("liverId");//直播信息主播ID
						Integer liverId2 = playerInfo.getLiverId();//选手信息直播用户ID
						boolean isSame=liverId!=null && liverId2!=null && liverId.intValue()==liverId2.intValue();
						if(isSame){
//							Integer liveCount = (Integer) liveRecordMap.get("liveCount");//直播场次
//							Long liveTimeCount = (Long) liveRecordMap.get("liveTimeCount");//直播时长(单位分钟)
							Integer liveCount =  new Integer(liveRecordMap.get("liveCount")==null?"0":liveRecordMap.get("liveCount").toString());//直播场次
							BigDecimal liveTimeCountDb =liveRecordMap.get("liveTimeCount")==null?new BigDecimal(0):(BigDecimal)liveRecordMap.get("liveTimeCount");
							Long liveTimeCount = liveTimeCountDb.longValue();//直播时长(单位分钟)
							playerInfo.setLiveCount(liveCount);
							playerInfo.setLiveTimeCount(liveTimeCount);
							break;
						}
					}
					
				}
			}
		} catch (Exception e) {
			this.log.error("统计(预设值)直播场次，时长等信息 异常："+e);
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * 方法描述：统计(预设值)直播场次等信息-周榜 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-10上午11:31:38 <br/>
	 * @param list
	 */
	private void countLiveRecordWeekInfo(List<TVstarPlayerInfo> list) {
		List<Integer> liverIdList=new ArrayList<Integer>();
		try {
			for(TVstarPlayerInfo playerInfo:list){
				Integer liverId = playerInfo.getLiverId();
				if(null != liverId){
					liverIdList.add(liverId);
				}
			}
			
			if(liverIdList!=null && liverIdList.size()>0){
				Map<String,Object> paraMap=new HashMap<String,Object>();
				paraMap.put("liverIds", liverIdList);
				String startTime = DateUtil.getSpecifiedDate(-7);//当前时间加上天数，获取指定日期 
				String endTime = DateUtil.getSpecifiedDate(0);
				paraMap.put("startTime", startTime);
				paraMap.put("endTime", endTime);
				List<Map<String,Object>> liveInfoList = liveRecordDao.getLiveInfoList(paraMap);
				for(TVstarPlayerInfo playerInfo:list){
					for(Map<String,Object> liveRecordMap:liveInfoList){
						Integer liverId = (Integer)liveRecordMap.get("liverId");//直播信息主播ID
						Integer liverId2 = playerInfo.getLiverId();//选手信息直播用户ID
						boolean isSame=liverId!=null && liverId2!=null && liverId.intValue()==liverId2.intValue();
						if(isSame){
							Integer liveCount =  new Integer(liveRecordMap.get("liveCount")==null?"0":liveRecordMap.get("liveCount").toString());//直播场次
							playerInfo.setLiveCountWeek(liveCount);
							break;
						}
					}
					
				}
			}
		} catch (Exception e) {
			this.log.error("统计(预设值)直播场次，时长等信息 异常："+e);
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * 方法描述：统计(预设值)直播场次等信息-周榜 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-10上午11:31:38 <br/>
	 * @param list
	 */
	private void countLiveRecordMonthInfo(List<TVstarPlayerInfo> list) {
		List<Integer> liverIdList=new ArrayList<Integer>();
		try {
			for(TVstarPlayerInfo playerInfo:list){
				Integer liverId = playerInfo.getLiverId();
				if(null != liverId){
					liverIdList.add(liverId);
				}
			}
			
			if(liverIdList!=null && liverIdList.size()>0){
				Map<String,Object> paraMap=new HashMap<String,Object>();
				paraMap.put("liverIds", liverIdList);
				String startTime = DateUtil.getSpecifiedDate(-30);//当前时间加上天数，获取指定日期 
				String endTime = DateUtil.getSpecifiedDate(0);
				paraMap.put("startTime", startTime);
				paraMap.put("endTime", endTime);
				List<Map<String,Object>> liveInfoList = liveRecordDao.getLiveInfoList(paraMap);
				for(TVstarPlayerInfo playerInfo:list){
					for(Map<String,Object> liveRecordMap:liveInfoList){
						Integer liverId = (Integer)liveRecordMap.get("liverId");//直播信息主播ID
						Integer liverId2 = playerInfo.getLiverId();//选手信息直播用户ID
						boolean isSame=liverId!=null && liverId2!=null && liverId.intValue()==liverId2.intValue();
						if(isSame){
							Integer liveCount =  new Integer(liveRecordMap.get("liveCount")==null?"0":liveRecordMap.get("liveCount").toString());//直播场次
							playerInfo.setLiveCountMonth(liveCount);
							break;
						}
					}
					
				}
			}
		} catch (Exception e) {
			this.log.error("统计(预设值)直播场次，时长等信息 异常："+e);
			e.printStackTrace();
		}
		
		
	}

	 /**
	 * 方法描述：选手排名<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-13下午4:02:54 <br/>
	 */
	public void executeRank() {
		try {
			//大赛选手鸟蛋降序排名 
			playInfoDao.updateEggRankNum();
			//大赛选手粉丝降序排名
			playInfoDao.updateFansRankNum();
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error("执行选手排名方法发生异常.");
		}
			
	}
	
	/**
	 * 
	 * 方法描述：获取APP端用户被关注数<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-7-7下午5:35:18 <br/>
	 * @param paramMap
	 * @return
	 */
	public List<Map<String,Object>> getFansCountInitList(Map<String,Object> paramMap){
		return playInfoDao.getFansCountInitList(paramMap);
	}

	 /**
	 * 方法描述：选手排名(周榜) <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-7-11下午4:49:37 <br/>
	 */
	public void executeRankWeek() {
		try {
			//大赛选手鸟蛋降序排名 
			playInfoDao.updateEggRankNumWeek();
			//大赛选手粉丝降序排名
			playInfoDao.updateFansRankNumWeek();
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error("执行选手排名(周榜)方法发生异常.");
		}
		
	}

	 /**
	 * 方法描述：选手排名 (月榜)<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-7-11下午4:49:42 <br/>
	 */
	public void executeRankMonth() {
		try {
			//大赛选手鸟蛋降序排名 
			playInfoDao.updateEggRankNumMonth();
			//大赛选手粉丝降序排名
			playInfoDao.updateFansRankNumMonth();
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error("执行选手排名 (月榜)方法发生异常.");
		}
	}
	
}
