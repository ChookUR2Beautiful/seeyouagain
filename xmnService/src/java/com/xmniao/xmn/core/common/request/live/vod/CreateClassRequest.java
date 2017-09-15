package com.xmniao.xmn.core.common.request.live.vod;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 
*      
* 类名称：CreateClassRequest   
* 类描述：   创建视频分类请求参数
* 创建人：Administrator   
* 创建时间：2016年8月25日 上午9:41:53   
* 修改人：Administrator   
* 修改时间：2016年8月25日 上午9:41:53   
* 修改备注：   
* @version    
*
 */
public class CreateClassRequest extends BaseRequest{
	
		private String classname; //分类名称
		 
		private Integer parentId; //分类父ID

		public String getClassname() {
			return classname;
		}

		public void setClassname(String classname) {
			this.classname = classname;
		}

		public Integer getParentId() {
			return parentId;
		}

		public void setParentId(Integer parentId) {
			this.parentId = parentId;
		}
		
		

}
