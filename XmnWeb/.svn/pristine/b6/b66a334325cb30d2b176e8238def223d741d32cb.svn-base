/**
 * 
 */
package com.xmniao.xmn.core.business_statistics.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.business_statistics.dao.TCelebrityDao;
import com.xmniao.xmn.core.business_statistics.entity.TCelebrity;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TCelebrityServici
 * 
 * 类描述： SaaS网红角色Service
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-12 上午11:53:29 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class TCelebrityService extends BaseService<TCelebrity> {
	
	@Autowired
	private  TCelebrityDao celebrityDao;

	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return celebrityDao;
	}

	/**
	 * 方法描述：批量更新角色冻结状态 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-23下午2:12:37 <br/>
	 * @param ids
	 * @param status
	 * @return
	 */
	public Resultable updateBatch(String ids, String status) {
		Resultable result=new Resultable();
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			if(StringUtils.isNotBlank(ids)){
				map.put("ids", ids.split(","));
				map.put("status", status);
				celebrityDao.updateBatch(map);
				result.setSuccess(true);
				result.setMsg("操作成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("操作失败");
			
		}finally{
			//写入 日志记录表
			String[] data={"SaaS角色管理,角色ID",ids,"修改上线状态","修改"};
			fireLoginEvent(data,result.getSuccess()==true?1:0);
		}
		
		return result;
	}

}
