package com.xmniao.xmn.core.api.controller.live;

import net.sf.oval.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.common.request.live.ContributeListRequest;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.service.AnchorPersonService;
import com.xmniao.xmn.core.util.ThriftUtil;

/**
 * 
*    
* 类名称：LiveAnchorRankingListApi   
* 类描述：   主播守护榜
* 创建人：yezhiyong   
* 创建时间：2016年8月16日 下午3:07:00   
* @version    
*
 */
@Controller
public class LiveAnchorRankingListApi{

	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(LiveAnchorRankingListApi.class);
	
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
	@RequestMapping(value = "/live/anchor/ranking" ,method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object queryLiveAnchorList(ContributeListRequest contributeListRequest){
		return anchorPersonService.queryLiveAnchorList(contributeListRequest);
	}
	
}
