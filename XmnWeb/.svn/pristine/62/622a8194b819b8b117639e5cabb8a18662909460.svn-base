package com.xmniao.xmn.core.fresh.entity;

import java.util.Date;
import java.util.List;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：FreshType
 * 
 * 类描述： 生产分类实体
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年1月11日 下午8:07:36 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class FreshType extends BaseEntity{

	private static final long serialVersionUID = 1L;

	private Integer id;//分类ID

    private String name;//分类名称

    private Integer fid;//父分类ID,默认0 ,表示没有父级分类

    private Integer dstatus;//数据状态, 默认0     0正常    1已删除

    private Date rdate;//创建时间

    private Date udate;//更新时间

    private Integer sort;//分类排序  数值越大 排序越靠前
    
    private String showBigImg;//首页分类显示的大图
    
    private String showSmallImg;//首页分类显示的小图
    
    private Integer hot;// 是否为热门分类0=否|1=是
    
	private Integer type; //跳转类型 1 WAP 2 APP
	
	private String url;//URL地址
    
	private String website; //type=1时，输入跳转的WAP URL地址。
	
	private String redirectType;// type=2时，seller代表跳转到商户列表页；grow代表跳转到成长记列表
	
	private List<FreshType> childs;  //下级分类
	
	private Integer likeType;	//查询类型
	
    public Integer getLikeType() {
		return likeType;
	}

	public void setLikeType(Integer likeType) {
		this.likeType = likeType;
	}

	public List<FreshType> getChilds() {
		return childs;
	}

	public void setChilds(List<FreshType> childs) {
		this.childs = childs;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getDstatus() {
        return dstatus;
    }

    public void setDstatus(Integer dstatus) {
        this.dstatus = dstatus;
    }

    public Date getRdate() {
        return rdate;
    }

    public void setRdate(Date rdate) {
        this.rdate = rdate;
    }

    public Date getUdate() {
        return udate;
    }

    public void setUdate(Date udate) {
        this.udate = udate;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
    
    

	/**
	 * @return the showBigImg
	 */
	public String getShowBigImg() {
		return showBigImg;
	}

	/**
	 * @param showBigImg the showBigImg to set
	 */
	public void setShowBigImg(String showBigImg) {
		this.showBigImg = showBigImg;
	}

	/**
	 * @return the showSmallImg
	 */
	public String getShowSmallImg() {
		return showSmallImg;
	}

	/**
	 * @param showSmallImg the showSmallImg to set
	 */
	public void setShowSmallImg(String showSmallImg) {
		this.showSmallImg = showSmallImg;
	}

	/**
	 * @return the hot
	 */
	public Integer getHot() {
		return hot;
	}

	/**
	 * @param hot the hot to set
	 */
	public void setHot(Integer hot) {
		this.hot = hot;
	}

	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	

	/**
	 * @return the website
	 */
	public String getWebsite() {
		return url;
	}

	/**
	 * @param website the website to set
	 */
	public void setWebsite(String website) {
		this.url = website;
	}

	/**
	 * @return the redirectType
	 */
	public String getRedirectType() {
		return url;
	}

	/**
	 * @param redirectType the redirectType to set
	 */
	public void setRedirectType(String redirectType) {
		this.url = redirectType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FreshType [id=" + id + ", name=" + name + ", fid=" + fid
				+ ", dstatus=" + dstatus + ", rdate=" + rdate + ", udate="
				+ udate + ", sort=" + sort + ", showBigImg=" + showBigImg
				+ ", showSmallImg=" + showSmallImg + ", hot=" + hot + ", type="
				+ type + ", url=" + url + ", website=" + website
				+ ", redirectType=" + redirectType + "]";
	}

}