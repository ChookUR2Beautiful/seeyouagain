package com.xmn.saas.entity.bill;

import java.util.Date;


/**
 * 
*      
* 类名称：BillRrecord   
* 类描述： 订单记录  
* 创建人：xiaoxiong   
* 创建时间：2016年10月14日 下午4:54:47     
*
 */
public class BillRecord {
	
		private Integer id;

	    private Long bid;

	    private Integer status;

	    private Date cdate;

	    private String remarks;

	    private String explains;
	    
	    private String datetime;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Long getBid() {
			return bid;
		}

		public void setBid(Long bid) {
			this.bid = bid;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

		public Date getCdate() {
			return cdate;
		}

		public void setCdate(Date cdate) {
			this.cdate = cdate;
		}

		public String getRemarks() {
			return remarks;
		}

		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}

		public String getExplains() {
			return explains;
		}

		public void setExplains(String explains) {
			this.explains = explains;
		}

		public String getDatetime() {
			return datetime;
		}

		public void setDatetime(String datetime) {
			this.datetime = datetime;
		}
	    
	    
	    
	    
}
