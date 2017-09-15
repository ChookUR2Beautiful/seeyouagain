package com.xmniao.xmn.core.api.controller.live;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.log4j.Logger;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.ContributeListRequest;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.entity.LiverInfo;
import com.xmniao.xmn.core.live.service.AnchorPersonService;
import com.xmniao.xmn.core.thrift.LiveWalletService;
import com.xmniao.xmn.core.thrift.LiveWalletService.Client;
import com.xmniao.xmn.core.thrift.WalletRecord;
import com.xmniao.xmn.core.util.ThriftUtil;

/**
 * 
*    
* 项目名称：xmnService_3.1.2_zb   
* 类名称：ContributeListApi   
* 类描述：   土豪榜/房间贡献榜/贡献榜
* 创建人：yezhiyong   
* 创建时间：2016年8月16日 下午3:07:00   
* @version    
*
 */
@Controller
@RequestMapping("/live/anchor")
public class ContributeListApi implements BaseVControlInf {

	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(ContributeListApi.class);
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	/**
	 * 主播|观众通用dao
	 * */
	@Autowired
	private LiveUserDao liveUserDao;
	
	/**
	 * 注入anchorPersonService
	 */
	@Autowired 
	private AnchorPersonService anchorPersonService;
	
	@Autowired
	private ThriftUtil thriftUtil;

	/**
	 * 
	* @Title: queryAnchorAnnunciateList
	* @Description: 房间贡献榜/贡献榜列表
	* @return Object    返回类型
	* @author
	* @throws
	 */
	@RequestMapping(value = "/contributeList" ,method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object queryContributeList(ContributeListRequest contributeListRequest){
		//日志
		log.info("ContributeListRequest data:" + contributeListRequest.toString());
		//验证
		List<ConstraintViolation> result = validator.validate(contributeListRequest);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		
		//类型
		Integer type = contributeListRequest.getType();
		//直播记录id
		Integer liveRecordId = contributeListRequest.getLiveRecordId();
		//主播id
		Integer anchorId = contributeListRequest.getAnchorId();
		if (type == 1 || type == 2) {
			if (liveRecordId == null) {
				return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
			}
			
		}else if (type == 4) {
			if (anchorId == null) {
				return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
			}
		}
		
		return versionControl(contributeListRequest.getApiversion(), contributeListRequest);
	}
	
	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1:
			return versionOne(object);
			default :
			return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}
	
	private Object versionOne(Object object) {
		ContributeListRequest contributeListRequest = (ContributeListRequest) object;
		return anchorPersonService.queryContributeList(contributeListRequest);
	}
	
	/**
	 * 
	* @Title: queryContributeList
	* @Description: 土豪榜
	* @return Object    返回类型
	* @throws
	 */
	@RequestMapping(value = "/richList" ,method=RequestMethod.GET)
	@ResponseBody
	public Object queryRichList(){
		//结果集
		List<Map<String, String>> resultList = new ArrayList<>();
		Map<Object, Object> resultMap = new HashMap<>();
		MapResponse response = null;
		
		//调用支付服务创建直播钱包
		LiveWalletService.Client client = null;
		try {
			
			StringBuffer  buffer = new StringBuffer();
			//排除UID 为 测试的内部账号
			List<LiverInfo> liverInfoList = liveUserDao.queryLiverInfosByIsinside();
			if (liverInfoList.size()>0) {
				for (int i = 0; i < liverInfoList.size(); i++) {
					LiverInfo info = liverInfoList.get(i);
					if (i<liverInfoList.size()-1) {
						buffer.append(info.getUid()).append(",");
					}else {
						buffer.append(info.getUid());
					}
				}
			}
			log.info("需要过来的测试账号UID："+buffer.toString());
			//组装参数
			Map<String, String> walletMap = new HashMap<>();
			walletMap.put("pageNo", "1");
			walletMap.put("pageSize", Constant.PAGE_LIMIT.toString());
			//土豪榜(前20名)
			walletMap.put("rtype", "1");//1.平台鸟币消费排行 2.每场直播消费排行 3.主播个人的消费排行
			walletMap.put("uid", buffer.toString());//1.平台鸟币消费排行 2.每场直播消费排行 3.主播个人的消费排行
			TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("LiveWalletService");
			client = new Client(tMultiplexedProtocol);
			thriftUtil.openTransport();
			WalletRecord walletRecord =	client.birdCoinList(walletMap);
			
			if (walletRecord != null && walletRecord.getWalletList() != null && walletRecord.getWalletList().size() > 0) {
				resultList = walletRecord.getWalletList();
			}
			log.info("获取土豪榜排行列表成功,");
			
		}catch (Exception e){
			log.error("获取土豪榜排行列表失败,错误信息："+e.getMessage());
			return new BaseResponse(ResponseCode.FAILURE,"获取土豪榜排行列表失败");
			
		}finally {
			thriftUtil.coloseTransport();
		}
		
		if (resultList.size() > 0) {
			//获取土豪头像,昵称,性别,等级
			resultList = anchorPersonService.getRichInfo(resultList);
		}
		
		//响应
		resultMap.put("contributeList", resultList);
		response = new MapResponse(ResponseCode.SUCCESS, "获取土豪榜排行列表成功");
		response.setResponse(resultMap);
		return response;
	}
}
