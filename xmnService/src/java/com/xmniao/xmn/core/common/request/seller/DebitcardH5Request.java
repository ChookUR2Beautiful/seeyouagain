package com.xmniao.xmn.core.common.request.seller;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.Min;
import net.sf.oval.constraint.NotNull;
/**
 * 
* @projectName: xmnService 
* @ClassName: DebitcardH5Rquest    
* @Description:领取专享卡操作类  
* @author: wdh   
* 
 */
public class DebitcardH5Request extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4307320682482367538L;
	/**
	 * 
	 */
	
	@NotNull(message="type不能为空")
	private Integer type=1; //type默认为1

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	
}
