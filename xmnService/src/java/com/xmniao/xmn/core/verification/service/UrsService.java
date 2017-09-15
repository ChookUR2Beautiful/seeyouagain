package com.xmniao.xmn.core.verification.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.seller.SelleridRequest;
import com.xmniao.xmn.core.common.request.urs.UrsCollectUrsRequest;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.entity.LiverInfo;
import com.xmniao.xmn.core.thrift.ResponseData;
import com.xmniao.xmn.core.thrift.SellerService;
import com.xmniao.xmn.core.thrift.UserActionService;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.MD5;
import com.xmniao.xmn.core.verification.dao.UrsDao;
import com.xmniao.xmn.core.verification.dao.UrsInfoDao;
import com.xmniao.xmn.core.verification.entity.Urs;
import com.xmniao.xmn.core.verification.entity.UrsInfo;
import com.xmniao.xmn.core.xmer.dao.SellerInfoDao;


/**
 * 用户信息 Service实现类
 * @author Administrator
 *
 */
@Service
public class UrsService {
	
	@Autowired private UrsDao ursDao;
	
	@Autowired private UrsInfoDao ursInfoDao;
	
	@Autowired
	private SessionTokenService sessionService;
	
	@Autowired
	private SellerInfoDao sellerInfoDao;
	
	@Autowired
	private String ip_number_business;
	
	@Autowired
	private String port_business;
	
	@Autowired
	private LiveUserDao liveUserDao;
	
	
	/*//获取用户列表
	public List getList(){
		return ursDao.list();
	}*/
	
	/**
	 * 根据用户名获取用户信息
	 * @param uname
	 * @return
	 */
    public Urs getUrsByUname(String uname){
    	return ursDao.getUrsByUname(uname);
    }

    
    
    //根据手机号码注册帐号
	public int addUrs(Map<Object,Object> map) {
		Map<Object, Object> paramMap=new HashMap<Object, Object>();
		paramMap.put("uname", map.get("phone"));//登录账号
		paramMap.put("phone", map.get("phone"));//电话号码
		//对用户密码进行加密  前6位和后六为对调
		String psw = MD5.Encode(map.get("psw").toString());
		String str1=psw.substring(0,6);
		String str2=psw.substring(6,26);
		String str3=psw.substring(26);
		String password=str3+str2+str1;
		paramMap.put("password",password) ;
		paramMap.put("regtime", DateUtil.format(DateUtil.now(), "yyyy-MM-dd"));
		ursDao.addUrs(paramMap);
		return Integer.parseInt(paramMap.get("uid")+"");
				
	}


	//添加到ursInfo
	public void addUrsInfo(UrsInfo ursInfo) {
		
		ursInfoDao.insertSelective(ursInfo);
	}


	/**
	 * 
	 * @Description: 店铺收藏
	 * @author xiaoxiong
	 * @date 2016年11月17日
	 */
	public Object ursCollectSellerInsert(SelleridRequest request) {
		try {
			String uid = sessionService.getStringForValue(request.getSessiontoken())+"";
			if(uid.equals("")||uid.equals("null")){
				return new BaseResponse(ResponseCode.DATAERR,"sessionToken错误或已过期，请重新登入！");
			}
			Map<String,Object> params=new HashMap<String, Object>();
			params.put("uid", uid);
			params.put("sellerid",request.getSellerid());
			params.put("cdate",new Date());
			params.put("type",request.getType());
			/**
			 * 验证是否已经收藏
			 */
			int count = ursDao.isCollectSeller(params);
			if(count>0){
				return new BaseResponse(ResponseCode.FAILURE,"亲，你已收藏该店铺啦！");
			}
			
			int flag = ursDao.ursCollectSellerInsert(params);
			if(flag>0){
				//调用业务服务接口，更新mongdb
				try {
					userActionService(3,1,Integer.parseInt(uid),request.getSellerid(),1);
				} catch (Exception e) {
					e.printStackTrace();
					
				}
				return new BaseResponse(ResponseCode.SUCCESS,"成功");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BaseResponse(ResponseCode.FAILURE,"错误");
	}
	/**
	 * 跟新mongdb统计信息
	 * @author xiaoxiong
	 * @date 2016年12月14日
	 */
	public void updateSellerCountInfo(String sellerid,String operation){
		/**
		 * 调用业务服务修改权重
		 */
		TTransport transport = null;
		try {
			 // 设置调用的服务地址为本地，端口为 7911
            transport = new TSocket(ip_number_business,Integer.valueOf(port_business));
            TFramedTransport frame = new TFramedTransport(transport);
            // 设置传输协议为 TBinaryProtocol
            TProtocol protocol = new TBinaryProtocol(frame);
            
            //商家服务模块
            TMultiplexedProtocol orderProtocol = new TMultiplexedProtocol(
                    protocol, "SellerService");
            SellerService.Client client = new SellerService.Client(orderProtocol);
            transport.open();
            
            Map<String,String> params = new HashMap<String,String>();
            params.put("infoType", "3");
            params.put("operation", operation);
            params.put("sellerid", sellerid);
            client.updateSellerCountInfo(params);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(transport!=null){
				transport.close();
			}
		}
		
	}
	/**
	 * 
	 * @author xiaoxiong
	 * @date 2016年12月17日
	 * @param operate  1 浏览 2 消费  3收藏
	 * @param update +1 -1
	 * @param 
	 */
	public void userActionService(int operate,int update,int uid,int sellerid,int actiontype){
		TTransport transport = null;
		try {
			 // 设置调用的服务地址为本地，端口为 7911
            transport = new TSocket(ip_number_business,Integer.valueOf(port_business));
            TFramedTransport frame = new TFramedTransport(transport);
            // 设置传输协议为 TBinaryProtocol
            TProtocol protocol = new TBinaryProtocol(frame);
            
            //用户服务服务模块
            TMultiplexedProtocol orderProtocol = new TMultiplexedProtocol(
                    protocol, "UserActionService");
            UserActionService.Client client = new UserActionService.Client(orderProtocol);
            transport.open();
            /*查询直播用户信息*/
            LiverInfo liverInfo = liveUserDao.queryLiverByUid(Long.valueOf(uid));
            
            String utype="1";
            if(liverInfo!=null){
            	utype = liverInfo.getUtype()==1?"2":"1";
            }
            
            List<Map<String,String>> list = new ArrayList<>();
            Map<String,String> params =new HashMap<>();
            params.put("actiontype",actiontype+"");
            params.put("xmntype", utype);/*1普通用户2主播*/
            params.put("operate", operate+"");
            params.put("sellerid",sellerid+"");
            params.put("uid", uid+"");
            params.put("update", update+"");
            list.add(params);
            client.userActionService(list);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(transport!=null){
				transport.close();
			}
		}
	} 
	
	/**
	 * 删除浏览记录
	 * @author xiaoxiong
	 * @date 2016年12月14日
	 */
	public void removeViewActionService(int sellerid,int uid,int type){
		/**
		 * 调用业务服务修改权重
		 */
		TTransport transport = null;
		try {
			 // 设置调用的服务地址为本地，端口为 7911
            transport = new TSocket(ip_number_business,Integer.valueOf(port_business));
            TFramedTransport frame = new TFramedTransport(transport);
            // 设置传输协议为 TBinaryProtocol
            TProtocol protocol = new TBinaryProtocol(frame);
            
            //商家服务模块
            TMultiplexedProtocol orderProtocol = new TMultiplexedProtocol(
                    protocol, "UserActionService");
            UserActionService.Client client = new UserActionService.Client(orderProtocol);
            transport.open();
            List<Integer> list = new ArrayList<>();
            list.add(sellerid);
            client.removeViewActionService(uid, 0, list);
          
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(transport!=null){
				transport.close();
			}
		}
		
	}
	/**
	 * 
	 * @Description: 用户关注
	 * @author xiaoxiong
	 * @date 2016年11月15日
	 */
	public int ursCollectUrsInsert(Map<String,Object> params) {
		
		return ursDao.ursColletUrsInsert(params);
	}
	
	/**
	 * 
	 * @Description: 查询用户是否已经关注
	 * @author xiaoxiong
	 * @date 2016年11月15日
	 * @param uid
	 * @param cuid
	 */
	public int ursColletUrsCount(Map<String, Object> params) {
		
		return ursDao.ursCollectUrsCount(params);
	}
	/**
	 * 
	 * @Description: 查询是否在同一个店消费
	 * @author xiaoxiong
	 * @date 2016年11月15日
	 */
	public int isInSellerCons(String uid, String cuid) {
		try {
			
			return ursDao.isInSellerCons(uid , cuid);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 
	 * @Description: 查询是否有同一关注用户
	 * @author xiaoxiong
	 * @date 2016年11月15日
	 */
  	public int ursColletUrsCount(String uid, String cuid) {
		try {
			return ursDao.ursColletUrsCount(uid , cuid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 
	 * @Description: 查询是否有同一收藏记录
	 * @author xiaoxiong
	 * @date 2016年11月15日
	 */
	public int ursCollectSellerCount(String uid, String cuid) {
		try {
			return ursDao.ursCollectSellerCount(uid , cuid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 
	 * @Description: 查询是否有同一浏览记录
	 * @author xiaoxiong
	 * @date 2016年11月15日
	 */
	public int ursBrowsedCount(String uid, String cuid) {
		try {
			return ursDao.ursBrowsedCount(uid,cuid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}



	public int isCollectSeller(int uid, int sellerid) {
		try {
			Map<String,Object> params=new HashMap<String, Object>();
			params.put("uid", uid);
			params.put("sellerid",sellerid);
			return ursDao.isCollectSeller(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 
	 * @Description: 查询是否收藏
	 * @author xiaoxiong
	 * @date 2016年11月17日
	 */
	public int queryBrowsedCountByUidAndSellerid(int uid, int sellerid) {
		try {
			return ursDao.queryBrowsedCountByUidAndSellerid(uid,sellerid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 
	 * @Description: 取消用户收藏
	 * @author xiaoxiong
	 * @date 2016年11月17日
	 */
	public int deleteUrsCollectByUidAndSellerid(Integer sellerid, int uid) {
		
		
		return ursDao.deleteUrsCollectByUidAndSellerid(sellerid,uid);
	}
	
	/**
	 * 
	 * @Description: 取消用户关注
	 * @author xiaoxiong
	 * @date 2016年11月17日
	 */
	public int deleteLiveFocus(Long uid, Long cuid) {
		
		return ursDao.deleteLiveFocus(uid,cuid);
	}




	
}
