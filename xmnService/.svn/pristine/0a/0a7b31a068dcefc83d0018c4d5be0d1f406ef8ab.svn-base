package com.xmniao.xmn.core.api.controller.vod;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class RecordPlayInfoNotifyApi {
	
	// 日志
	private Logger log = Logger.getLogger(RecordPlayInfoNotifyApi.class);
	
	
	@RequestMapping(value="notifyInfo")
	@ResponseBody
	public void RecordPlayInfoNotify(HttpServletRequest request){
		log.info("视频录制回调。。。。。。。。。。。。。。。。。。。。。。。。");
	    Map map=request.getParameterMap();  
	    Set keSet=map.entrySet();  
	    for(Iterator itr=keSet.iterator();itr.hasNext();){  
	        Map.Entry me=(Map.Entry)itr.next();  
	        Object ok=me.getKey();  
	        Object ov=me.getValue();  
	        String[] value=new String[1];  
	        if(ov instanceof String[]){  
	            value=(String[])ov;  
	        }else{  
	            value[0]=ov.toString();  
	        }  
	        for(int k=0;k<value.length;k++){  
	            log.info("参数信息："+ok+"="+value[k]);
	        }  
	      }  
	   
		
	}

}
