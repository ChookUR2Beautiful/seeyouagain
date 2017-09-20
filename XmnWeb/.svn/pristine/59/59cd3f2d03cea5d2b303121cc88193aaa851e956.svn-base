package com.xmniao.xmn.core.fresh.entity;

import java.io.Serializable;
import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.DateUtil;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：ProductFailingSerial
 *
 * 类描述：产品导入失败序列号实体类
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-7-15下午3:05:25
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class ProductFailingSerial extends BaseEntity implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6880311704723984822L;

	private Integer cid;

    private Long importserial;

    private Date rdate;
    
    private String rdateStr;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Long getImportserial() {
        return importserial;
    }

    public void setImportserial(Long importserial) {
        this.importserial = importserial;
    }

	/**
	 * @return the rdate
	 */
	public Date getRdate() {
		return rdate;
	}

	/**
	 * @param rdate the rdate to set
	 */
	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}
	
	

	/**
	 * @return the rdateStr
	 */
	public String getRdateStr() {
		return rdateStr=rdate==null?"":DateUtil.formatDate(rdate, DateUtil.Y_M_D);
	}

	/**
	 * @param rdateStr the rdateStr to set
	 */
	public void setRdateStr(String rdateStr) {
		this.rdateStr = rdateStr;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProductFailingSerial [cid=" + cid + ", importserial="
				+ importserial + ", rdate=" + rdate + ", rdateStr=" + rdateStr
				+ "]";
	}
    
    

}