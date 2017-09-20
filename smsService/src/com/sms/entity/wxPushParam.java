package com.sms.entity;

/**
 * 微信附件美食推送消息对象
 * @author douk
 *
 */
public class wxPushParam {
	
	/**
	 * 推送OPENID
	 */
	private String touser;
	
	/**
	 * 公众帐号下模板消息ID
	 */
	private String template_id;
	
	/**
	 * 推送访问详情URL
	 */
	private String url;
	
	/**
	 * 订单编号
	 */
	private String bid;
	
	/**
	 * 商户名称或生鲜商品名称
	 */
	private String sellername;
	
	/**
	 * 订单支付金额
	 */
	private String money;
	
	/**
	 * 订单支付时间
	 */
	private String sdate;
	
	/**
	 * 通知标题
	 */
	private String title;
	
	/**
	 * 通知备注信息
	 */
	private String remarks;
	
	public wxPushParam() {
	}
	
	
	public wxPushParam(String touser,String template_id,String bid,String sellername,String money,String sdate,String title,String remarks) {
		this.touser = touser;
		this.template_id = template_id;
		this.bid = bid;
		this.sellername = sellername;
		this.money = money;
		this.sdate = sdate;
		this.title = title;
		this.remarks = remarks;
	}
	
	/**
	 * 返回附近美食模版 拼接JSON串
	 * @return
	 */
	public String ParamToJson(){
		StringBuffer buffer = new StringBuffer();
		
		if("".equals(title) || title.equals(null)){
			this.title = "尊敬的寻蜜鸟用户，您的订单已支付成功";
		}
		
		if("".equals(remarks) || remarks.equals(null)){
			this.remarks = "谢谢您的惠顾";
		}
		
		buffer.append("{\"touser\":\""+touser+"\",");
		buffer.append("\"template_id\":\"EoLKuAWT341iONykD6SD8WUwRSevcBagDEWjxv6HiFE\",");
		buffer.append("\"url\":\"\",");
		buffer.append("\"data\":{");
		buffer.append("\"first\": {\"value\":\""+title+"\",\"color\":\"#173177\"},");
		buffer.append("\"keyword1\": {\"value\":\""+bid+"\",\"color\":\"#173177\"},");
		buffer.append("\"keyword2\": {\"value\":\"支付成功\",\"color\":\"#173177\"},");
		buffer.append("\"keyword3\": {\"value\":\""+sdate+"\",\"color\":\"#173177\"},");
		buffer.append("\"keyword4\": {\"value\":\""+sellername+"\",\"color\":\"#173177\"},");
		buffer.append("\"keyword5\": {\"value\":\""+money+"元\",\"color\":\"#173177\"},");
		buffer.append("\"remark\": {\"value\":\""+remarks+"\",\"color\":\"#173177\"}");
		buffer.append("}}");
		return buffer.toString();
	}
	
	/**
	 * 返回生鲜模版 拼接JSON串
	 * @return
	 */
	public String FreshParamToJson(){
		StringBuffer buffer = new StringBuffer();
		if("".equals(title) || title.equals(null)){
			this.title = "尊敬的用户，您的订单已支付成功";
		}
		
		if("".equals(remarks) || remarks.equals(null)){
			this.remarks = "感谢您的光临";
		}
		
		buffer.append("{\"touser\":\""+touser+"\",");
		buffer.append("\"template_id\":\"GL3vGvQxwo52CnI3OLMmGv0YobQPWdYzCJHM7jjFGTw\",");
		buffer.append("\"url\":\"\",");
		buffer.append("\"data\":{");
		buffer.append("\"first\": {\"value\":\""+title+"\",\"color\":\"#173177\"},");
		buffer.append("\"keyword1\": {\"value\":\""+sellername+"\",\"color\":\"#173177\"},");
		buffer.append("\"keyword2\": {\"value\":\""+bid+"\",\"color\":\"#173177\"},");
		buffer.append("\"keyword3\": {\"value\":\""+money+"元\",\"color\":\"#173177\"},");
		buffer.append("\"keyword4\": {\"value\":\""+sdate+"\",\"color\":\"#173177\"},");		
		buffer.append("\"remark\": {\"value\":\""+remarks+"\",\"color\":\"#173177\"}");
		buffer.append("}}");
		return buffer.toString();
	}


	public String getTouser() {
		return touser;
	}


	public void setTouser(String touser) {
		this.touser = touser;
	}


	public String getTemplate_id() {
		return template_id;
	}


	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getBid() {
		return bid;
	}


	public void setBid(String bid) {
		this.bid = bid;
	}


	public String getSellername() {
		return sellername;
	}


	public void setSellername(String sellername) {
		this.sellername = sellername;
	}


	public String getMoney() {
		return money;
	}


	public void setMoney(String money) {
		this.money = money;
	}


	public String getSdate() {
		return sdate;
	}


	public void setSdate(String sdate) {
		this.sdate = sdate;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
