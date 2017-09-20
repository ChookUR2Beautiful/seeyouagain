package com.xmniao.xmn.core.fresh.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
* @projectName: xmnService 
* @ClassName: FreshActivityCommon    
* @Description:通用活动实体   
* @author: liuzhihao   
* @date: 2016年12月22日 下午5:33:42
 */
public class FreshActivityCommon extends BaseEntity{
    private Integer id;

    private String title;

    private String img;

    private Integer type;

    private String url;

    private Integer status;
    
    private Integer orderval;
  
    private String remark;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginDate;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;

    private Date endTime;

    private Date createTime;

    private Date updateTime;
    
    private String productJson;
    
    private List<ActivityProduct> activityProducts;
    
    private Integer proceedStatus;  //进行状态  1:未开始  2:进行中 3:已结束
    
    private Integer billNum;	//订单总数
    
    private Long spikeId;  //秒杀活动id
    
    private Integer orderLimit;  //限制购买数
    
    private List<Map<String,Object>> activityProductVo; //规格数据
    
    private Integer labelId;	//商品标签
    
    public Integer getLabelId() {
		return labelId;
	}

	public void setLabelId(Integer labelId) {
		this.labelId = labelId;
	}

	public List<Map<String, Object>> getActivityProductVo() {
		return activityProductVo;
	}

	public void setActivityProductVo(List<Map<String, Object>> activityProductVo) {
		this.activityProductVo = activityProductVo;
	}

	public Integer getOrderLimit() {
		return orderLimit;
	}

	public void setOrderLimit(Integer orderLimit) {
		this.orderLimit = orderLimit;
	}

	public Long getSpikeId() {
		return spikeId;
	}

	public void setSpikeId(Long spikeId) {
		this.spikeId = spikeId;
	}

	public Integer getBillNum() {
		return billNum;
	}

	public void setBillNum(Integer billNum) {
		this.billNum = billNum;
	}

	public Integer getProceedStatus() {
    	if(status!=null&&(status==1||status==2)){
    		return 3;
    	}
    	if(proceedStatus==null&&beginDate!=null&&endDate!=null){
    		if(new Date().getTime()>endDate.getTime()){
    			return 3;
    		}else if(new Date().getTime()<beginDate.getTime()){
    			return 1;
    		}else{
    			return 2;
    		}
    	}
		return proceedStatus;
	}

	public void setProceedStatus(Integer proceedStatus) {
		this.proceedStatus = proceedStatus;
	}

	public List<ActivityProduct> getActivityProducts() {
		return activityProducts;
	}

	public void setActivityProducts(List<ActivityProduct> activityProducts) {
		this.activityProducts = activityProducts;
	}

	public String getProductJson() {
		return productJson;
	}

	public void setProductJson(String productJson) {
		this.productJson = productJson;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

    public Integer getOrderval() {
		return orderval;
	}

	public void setOrderval(Integer orderval) {
		this.orderval = orderval;
	}

	public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}