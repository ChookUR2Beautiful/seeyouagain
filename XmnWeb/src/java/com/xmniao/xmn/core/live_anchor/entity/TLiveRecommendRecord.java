package com.xmniao.xmn.core.live_anchor.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

public class TLiveRecommendRecord extends BaseEntity{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 2017381349763938911L;

	private Integer id;

    private Integer rid;

    private Integer rtype;

    private Integer isNationwide;  // 是否全国通用 (0:不是 1:是 )

    private Integer provinceId;

    private Integer cityId;

    private Integer homeSort;
    
    private Integer moduleType;
    
    private Date createTime;
    
    private Date updateTime;
    
    /* 自定义字段专区*/
    private String title;
    
    private String topicTypeDesc;  //专题类型
    
	private String provinceName;// 省名称
	
	private String cityName;// 市名称
	
	private Integer anchorId;// 主播用户id
	
	private String nname;// 用户昵称
	
	private String zhiboTitle;// 直播/回放标题
	
	private Integer sellerid;// 直播商家ID

	private String sellername;// 直播商家名称
	
    private String anchorName;
    
    public String getAnchorName() {
		return anchorName;
	}

	public void setAnchorName(String anchorName) {
		this.anchorName = anchorName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTopicTypeDesc() {
		return topicTypeDesc;
	}

	public void setTopicTypeDesc(String topicTypeDesc) {
		this.topicTypeDesc = topicTypeDesc;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}	
	
	
	/* *******END********* */


	public Integer getAnchorId() {
		return anchorId;
	}

	public void setAnchorId(Integer anchorId) {
		this.anchorId = anchorId;
	}

	public String getNname() {
		return nname;
	}

	public void setNname(String nname) {
		this.nname = nname;
	}

	public String getZhiboTitle() {
		return zhiboTitle;
	}

	public void setZhiboTitle(String zhiboTitle) {
		this.zhiboTitle = zhiboTitle;
	}

	public Integer getSellerid() {
		return sellerid;
	}

	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}

	public String getSellername() {
		return sellername;
	}

	public void setSellername(String sellername) {
		this.sellername = sellername;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getRtype() {
        return rtype;
    }

    public void setRtype(Integer rtype) {
        this.rtype = rtype;
    }

    public Integer getIsNationwide() {
        return isNationwide;
    }

    public void setIsNationwide(Integer isNationwide) {
        this.isNationwide = isNationwide;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getHomeSort() {
        return homeSort;
    }

    public void setHomeSort(Integer homeSort) {
        this.homeSort = homeSort;
    } 

    public Integer getModuleType() {
		return moduleType;
	}

	public void setModuleType(Integer moduleType) {
		this.moduleType = moduleType;
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

	@Override
	public String toString() {
		return "TLiveRecommendRecord [id=" + id + ", rid=" + rid + ", rtype="
				+ rtype + ", isNationwide=" + isNationwide + ", provinceId="
				+ provinceId + ", cityId=" + cityId + ", homeSort=" + homeSort
				+ ", moduleType=" + moduleType + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", title=" + title
				+ ", topicTypeDesc=" + topicTypeDesc + ", provinceName="
				+ provinceName + ", cityName=" + cityName + ", anchorId="
				+ anchorId + ", nname=" + nname + ", zhiboTitle=" + zhiboTitle
				+ ", sellerid=" + sellerid + ", sellername=" + sellername + "]";
	}




    
}