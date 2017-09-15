package com.xmniao.xmn.core.xmer.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.util.Base64;
import com.xmniao.xmn.core.xmer.dao.XmerDao;


/**
 * 
*    
* 项目名称：xmnService   
* 类名称：BannerServcie   
* 类描述：   广告
* 创建人：xiaoxiong   
* 创建时间：2016年7月16日 下午2:58:16   
* @version    
*
 */
@Service
public class BannerServcie {

		//注入寻蜜客Dao
		@Autowired
		private XmerDao xmerDao;
	
		//注入根目录
		@Autowired
		private String fileUrl;
		
	public List getBannerList(){
		//查询首页广告图
		List<JSONObject> list=new ArrayList<>();
		List<Map<Object,Object>> bannerList=xmerDao.queryBannerList();
		 if(bannerList!=null&&bannerList.size()>0){
			for(Map<Object,Object> bmap:bannerList){
				try {
					String banjson= Base64.getFromBase64(bmap.get("objjson")+"");
					JSONArray bjArray=JSONArray.parseArray(banjson);
					for(int i=0;i<bjArray.size();i++){
						JSONObject bjson=bjArray.getJSONObject(i);
						bjson.put("id", bmap.get("id"));
						if(bjson.get("pic_url")==null||bjson.get("pic_url").toString().equals("")){
							continue;
						}
						bjson.put("pic_url", fileUrl+bjson.get("pic_url"));
						list.add(bjson);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		 Collections.sort(list, new Comparator<JSONObject>(){
	            public int compare(JSONObject arg0, JSONObject arg1) {
	                return arg0.get("sort").toString().compareTo(arg1.get("sort").toString());
	            }
	        }); 
		return list;
		
	}
}
