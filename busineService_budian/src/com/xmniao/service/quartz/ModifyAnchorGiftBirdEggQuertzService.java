package com.xmniao.service.quartz;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.common.DateUtil;
import com.xmniao.dao.live.LiveGivedGiftDao;
import com.xmniao.domain.live.LiveSalary;
import com.xmniao.domain.live.LiverBean;
import com.xmniao.thrift.pay.LiveWalletService;
import com.xmniao.thrift.pay.ResponseData;
import com.xmniao.urs.dao.LiverDao;


/**
 * 描述 检查主播直播是有无 未接收的礼物记录 
 * @author yhl
 * 2016-8-12 16:55:53
 * */
@Service
public class ModifyAnchorGiftBirdEggQuertzService {
	
	private final Logger log = LoggerFactory.getLogger(ModifyAnchorGiftBirdEggQuertzService.class);
	/**
	 * 直播礼物Dao /service
	 * */
	
	
    @Autowired
    private LiveGivedGiftDao liveGivedGiftDao;
    
    
    @Autowired
    private LiverDao liverDao;
    
    /**
     * 注入分账系统支付服务的修改钱包余额接口的IP地址
     */
	@Resource(name="transLedgerIP")
    private String transLedgerIP;
    
    /**
     * 注入分账系统支付服务的修改钱包余额接口的端口号
     */
	@Resource(name="transLedgerPort")
    private int transLedgerPort;
	
	
	
	/**
	 * 描述：对直播未接收成功的礼物 补偿给主播 累计鸟蛋
	 * 		 查询出未接收的礼物记录，根据直播记录ID 和 主播ID anchorId 进行补偿
	 * 
	 * */
	public void modifyGiftsToBirdEgg(){
		
		try {
			log.info("观众赠送主播礼物累计鸟蛋定时任务开启： "+com.xmniao.common.DateUtil.dateFormat1(new Date()));
			//查询24小时内 已经结束的直播记录列表  做主播鸟蛋 补偿操作
			List<Map<String,Object>> recordInfoList = liveGivedGiftDao.queryLiveRecordByEnd();
			log.info("共查询到跨天直播记录总数："+recordInfoList.size());
			//批量执行累计鸟蛋集合
			List<Map<String, String>> batchWalletList = new ArrayList<>();			//预接收 鸟蛋状态集合
			List<Map<Object, Object>>  preGivedGiftInfos = new ArrayList<>();
			//未接收 鸟蛋状态集合
			List<Map<Object, Object>>  notGivedGiftInfos = new ArrayList<>();
			
			if (recordInfoList.size()>0) {
				for (int i = 0; i < recordInfoList.size(); i++) {
					Map<String,Object> info = recordInfoList.get(i);
					//获取主播信息 拿到UID 
					Map<String,Object> liverInfo = liverDao.queryLiverInfoByAnchorId((Integer) info.get("anchorId"));
					
					Map<Object, Object> viewRecordMap = new HashMap<>();
					viewRecordMap.put("live_record_id", info.get("id"));//直播ID
					viewRecordMap.put("anchor_id", info.get("anchorId"));//直播主播ID
					//查询直播送礼物主播接收鸟蛋 “advanced_status”  “未接收” | “已接收”的记录
					//advanced_status  '预处理状态  1 未接受  2 预接收 3 已接收'。
					List<Map<String,Object>>  givGiftList = liveGivedGiftDao.queryLiveGivedGiftByAdvancedStatus(viewRecordMap);
					if (givGiftList.size()>0) {
						Long balance = 0L;
						for (int j = 0; j < givGiftList.size(); j++) {
							Map<String,Object> givedInfo = givGiftList.get(j);
							//接收状态 等于 2 标示预接收  标示主播接收鸟蛋成功 直接修改为 已接收
							if (((Integer)givedInfo.get("advancedStatus")) == 2) {
								//组装接收成功的 集合
								//预接收  未接收的map 集合
								Map<Object, Object> preMap = new HashMap<>();
								preMap.put("id", givedInfo.get("id"));
								preMap.put("advanced_status", "3");
								preMap.put("update_time", DateUtil.dateFormat1(new Date()));
								preGivedGiftInfos.add(preMap);
								
							}else if (((Integer)givedInfo.get("advancedStatus")) == 1 && ((Integer)givedInfo.get("isfailed")) == 1) { //等于1 则累计鸟蛋失败了 重新累计
								//组装修改发礼物表记录集合
								Map<Object, Object> notMap = new HashMap<Object, Object>();
								notMap.put("id", givedInfo.get("id"));
								notMap.put("anchorId", givedInfo.get("anchorId"));
								notMap.put("liveRecordId", givedInfo.get("liveRecordId"));
								notMap.put("giftPrice", givedInfo.get("giftPrice"));
								notMap.put("advanced_status", "3");
								notMap.put("update_time", DateUtil.dateFormat1(new Date()));
								notGivedGiftInfos.add(notMap);
								
								//循环累计主播鸟蛋 统一执行累计主播鸟蛋
								balance = balance+Long.parseLong(givedInfo.get("percentAmount").toString());
							}
						}
						//balance大于0 标示有鸟蛋需要累计到主播
						if (balance>0) {
							//组装修改更新集合
							Map<String, String> walletMap = new HashMap<>();
							walletMap.put("uid", liverInfo.get("uid").toString());
							walletMap.put("balance", balance.toString());
							walletMap.put("rtype", "30");
							batchWalletList.add(walletMap);
						}
					}
				}
				
				//优先执行预接收状态的记录 预接收为成功累计了鸟蛋的记录  无需再次累计鸟蛋
				if (preGivedGiftInfos.size()>0) {
					liveGivedGiftDao.modifyBatchLiveGivedGiftById(preGivedGiftInfos);
					log.info("累计鸟蛋定时任务此次共执行“预接收”状态记录数："+preGivedGiftInfos.size());
				}
				
				
				TTransport transport = null;
		        try
		        {
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
		            log.info("需要同步鸟蛋："+batchWalletList.size());
					ResponseData result = client.updateLiveWalletsForList(batchWalletList);
					
					if (result.getState() == 0) {
						//批量修改观众赠送礼物记录信息 
						log.info("未接收的记录："+notGivedGiftInfos.size());
						if (notGivedGiftInfos.size()>0) {
							liveGivedGiftDao.modifyBatchLiveGivedGiftById(notGivedGiftInfos);
							log.info("累计鸟蛋定时任务此次共执行“未接收”状态记录数："+notGivedGiftInfos.size());
						}
						
					}else {
						log.info("定时任务批量累计主播鸟蛋异常");
					}
		            
		            
		     
		        }
		        catch (TException e)
		        {
		            //若调用抛出异常,则返回标识为-1
		            log.error("调用支付服务修改用户商家额度接口异常", e);
		        }
		        finally
		        {
		            //关闭连接
		            transport.close();
		        }
				
				//提供一个批量修改用户直播钱包的记录接口
				
			}
		} catch (Exception e) {
			log.info("执行定时任务异常!");
			e.printStackTrace();
		
		}
		log.info("观众赠送主播礼物累计鸟蛋定时任务执行完毕： "+DateUtil.dateFormat1(new Date()));
		
	}
	
	
	/**
	 * 方法描述：归还丢失礼物打赏鸟币
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月28日下午6:06:42 <br/>
	 * @param list
	 * @return
	 * @throws TTransportException 
	 */
	public void giveBack() throws TTransportException {
		log.info("[开始执行还原丢失打赏鸟蛋方法]");
		List<Map<String,Object>>  loseGifts = liveGivedGiftDao.getLoseGifts();
		//往钱包添加鸟币	新增rtype 订单号填礼物记录id
		TTransport transport = null;
       
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
        
    
		HashMap<String,String> paramMap = new HashMap<>();
		paramMap.put("option", "0");
		paramMap.put("rtype", "30");
		for (Map<String,Object> loseGift : loseGifts) {
			try {
				//获取主播信息 拿到UID 
				Map<String,Object> liverInfo = liverDao.queryLiverInfoByAnchorId((Integer) loseGift.get("anchorId"));
				paramMap.put("uid",liverInfo.get("uid").toString());
				paramMap.put("balance", loseGift.get("percentAmount").toString());
				paramMap.put("remarks", loseGift.get("id").toString());
				paramMap.put("description", loseGift.get("giftName").toString());
				paramMap.put("liveRecordId", loseGift.get("liveRecordId").toString());
				ResponseData responseData = client.liveWalletOption(paramMap);
				if(0==responseData.getState()){
					//更改礼物状态
					//累加通告鸟币
				  liveGivedGiftDao.updateBackState((Integer)loseGift.get("id"));
				  liveGivedGiftDao.updateIncomeEggNums((Integer)loseGift.get("liveRecordId"),(Double)loseGift.get("percentAmount"));
				}
			} catch (Exception e) {
				log.error("调用支付服务返回鸟蛋出现异常   礼物记录id为"+loseGift.get("id"),e);
			}
		}
		transport.close();
	}

}
