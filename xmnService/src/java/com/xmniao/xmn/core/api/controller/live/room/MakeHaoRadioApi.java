package com.xmniao.xmn.core.api.controller.live.room;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.mongodb.util.Hash;
import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ObjResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.live.service.LiveLedgerRecordService;
import com.xmniao.xmn.core.util.PropertiesUtil;


/**
 * 壕赚广播
* 类名称：MakeHaoRadioApi   
* 类描述：   
* 创建人：xiaoxiong   
* 创建时间：2016年12月22日 下午4:37:43
 */
@RequestMapping("/live")
@Controller
public class MakeHaoRadioApi  implements BaseVControlInf{
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	private final Logger log = Logger.getLogger(BirdBeansListApi.class);
	@Autowired
	private Validator validator;
	
	@Autowired
	private LiveLedgerRecordService liveLedgerRecordService;
	
	
	@ResponseBody
	@RequestMapping("/makeHaoRadio")
	public Object makeHaoRadio(BaseRequest request) throws IOException{
		log.info("GetBirdBeansListRequest data:" + request.toString());
		List<ConstraintViolation> result = validator.validate(request);
		if(result.size() >0 && result != null){
			log.info("提交的数据有问题:"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,result.get(0).getMessage());
		}
		return versionControl(request.getApiversion(), request);
		
		
	}
	public static int getRoundNum(List<Integer> rounds,int i){
		int temp = 0;
		try {
			temp = (int)(Math.random()*i);
			for(int round : rounds){
				if(round==temp){
					return getRoundNum(rounds,i);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return temp;
	}

	@Override
	public Object versionControl(int v, Object object){
		switch(v){
		case 1:
			return versionControlOne(object);
			default :
				return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}
	
	/**
	 * 获取数据库真实数据
	 * @author xiaoxiong
	 * @date 2016年12月27日
	 */
	public Object versionControlTwo(Object object){
		
		return liveLedgerRecordService.queryLiveLedgerRecordOrderList();
	}
		
	/**
	 * 假数据
	 * @author xiaoxiong
	 * @date 2016年12月27日
	 */
	private Object versionControlOne(Object object) {
		
		List<Integer> round = new ArrayList<>();
		List<Map<String,String>> list = new ArrayList<>();
		try {
			String radioNanme = propertiesUtil.getValue("makeHao.radio.name", "conf_common.properties");
			
			String[] names = radioNanme.split(",");
			
			for(int i=0;i<9;i++){
				int temp = getRoundNum(round, names.length);
				round.add(temp);
				Map<String, String> map =new  HashMap<String, String>();
				
				if(temp%3==0){
					map.put("title", names[temp]+"刚刚收获一枚壕友，获得壕友充值奖励"+String.format("%.2f", Math.random()*500)+"鸟币");
				}
				if(temp%3==1){
					map.put("title", names[temp]+"打赏"+String.format("%.2f", Math.random()*500)+"鸟币");		
				}
				if(temp%3==2){
					map.put("title", names[temp]+"天降壕礼获得"+String.format("%.2f", Math.random()*100)+"鸟币");	
				}
				map.put("sdate",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				list.add(map);
			}
			
			MapResponse objResponse = new MapResponse(ResponseCode.SUCCESS, "成功");
			Map<Object,Object> resultMap = new HashMap<>();
			resultMap.put("list", list);
			objResponse.setResponse(resultMap);
			return objResponse;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new BaseResponse(ResponseCode.FAILURE,"获取广播信息失败");
	}
}
