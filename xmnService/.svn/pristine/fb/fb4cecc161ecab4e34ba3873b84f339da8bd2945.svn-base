package com.xmniao.xmn.core.api.controller.common;

import java.util.ArrayList;
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

import com.xmniao.xmn.core.api.controller.seller.SellerDetailApi;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.AreaListRequest;
import com.xmniao.xmn.core.common.request.IDRequest;
import com.xmniao.xmn.core.common.service.CommonService;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.xmer.entity.Area;


@RequestMapping("area")
@Controller
public class AreaListApi implements BaseVControlInf{

	private final Logger log = Logger.getLogger(AreaListApi.class);
	
	@Autowired
	private Validator validator;
	
	@Autowired
	private CommonService commonServcie;
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	@RequestMapping("/list")
	@ResponseBody
	public Object list(AreaListRequest request){
		List<ConstraintViolation> result = validator.validate(request);
		if(result.size() >0 && result != null){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new MapResponse(ResponseCode.DATAERR,result.get(0).getMessage());
		}
		return versionControl(request.getApiversion(), request);
	}

	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1: return versionControlOne(object);
		default : return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
	}
		
}

	private Object versionControlOne(Object object) {
		try {
			AreaListRequest request=(AreaListRequest)object;
			String version=propertiesUtil.getValue("area.version", "conf_common.properties");
			
			Map<Object,Object> result = new HashMap<Object, Object>();
			MapResponse mapResponse = new MapResponse(ResponseCode.SUCCESS,"成功");
			
			result.put("version", version);
			if(!request.getAreaVersion().equals(version)){
				List<Area> templist = commonServcie.queryAreaByType(0);
				List<Area> list =new ArrayList<>();
				if(templist!=null&&templist.size()>0){
					list=findSubSet(templist);
					
				}
				result.put("list", list);
			}
			
			mapResponse.setResponse(result);
			return mapResponse;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BaseResponse(ResponseCode.FAILURE,"错误");
	}
	
	/**
	 * 
	 * @Description: 查询子集
	 * @author xiaoxiong
	 * @date 2016年10月17日
	 */
	public List<Area> findSubSet(List<Area> list){
		
		List<Area> resulst=new ArrayList<>();
		
		if(list!=null && list.size()>0){
			for(Area province:list){
				//查询市
				List<Area> cityList=commonServcie.queryAreaByType(province.getAreaId());
				if(cityList!=null && cityList.size()>0){
					List<Area> cityResult=new ArrayList<>();
					for(Area city:cityList){
						//查询区
						List<Area> countyList=commonServcie.queryAreaByType(city.getAreaId());
						if(countyList!=null&&countyList.size()>0){
							city.setSubSet(countyList);
						}
					
						cityResult.add(city);
					}
					province.setSubSet(cityResult);
				}
				resulst.add(province);
			}
		}
		
		return resulst;
	}
}