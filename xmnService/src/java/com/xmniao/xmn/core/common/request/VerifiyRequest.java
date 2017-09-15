package com.xmniao.xmn.core.common.request;

import net.sf.oval.constraint.NotNull;

import org.springframework.beans.factory.annotation.Autowired;

import com.xmniao.xmn.core.base.BaseRequest;


/**
 * 
*    
* 项目名称：xmnService   
* 类名称：VerifiyRequest   
* 类描述：   验证码验证接口
* 创建人：xiaoxiong   
* 创建时间：2016年8月23日 下午7:28:25   
* @version    
*
 */
public class VerifiyRequest extends BaseRequest{
		
		@NotNull(message="手机号码不能为空")
		private String phone;
		
		@NotNull(message="验证码不能为空")
		private String code;

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}
		

}
