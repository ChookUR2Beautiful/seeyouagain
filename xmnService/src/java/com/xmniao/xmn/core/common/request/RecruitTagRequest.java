package com.xmniao.xmn.core.common.request;


import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

public class RecruitTagRequest extends BaseRequest{
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		@NotNull(message="标签类型不能为null")
		private Integer type;				
		@NotNull(message="商户ID不能为空")
		private Integer uid;

	

		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}

		public Integer getUid() {
			return uid;
		}

		public void setUid(Integer uid) {
			this.uid = uid;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

	
		
		
}
