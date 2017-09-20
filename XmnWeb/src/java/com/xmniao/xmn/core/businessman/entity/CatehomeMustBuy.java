package com.xmniao.xmn.core.businessman.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

public class CatehomeMustBuy extends BaseEntity{
    /**
     * 主键
     */
    private Long id;

    /**
     * 商品分类
     */
    private Integer typeId;

    /**
     * 商品标签 (1:精选  2:潮玩  3:送礼   4:海淘)
     */
    private Integer type;

    /**
     * 状态 01 正常 02 已删除
     */
    private Integer state;

    /**
     * 排序号 排序值越大排序越前
     */
    private Integer sort;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
    
    
    private String typeName;
    
    private String typeStr;

    public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeStr() {
		if(type==null){
			return typeStr;
		}
		switch (type) {
		case 1:
			return "精选";
		case 2:
			return "潮玩";
		case 3:
			return "送礼";
		case 4:
			return "海淘";
		default:
			break;
		}
		return typeStr;
	}

	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}

	/**
     * 主键
     * @return id 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 商品分类
     * @return type_id 商品分类
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * 商品分类
     * @param typeId 商品分类
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * 商品标签 (1:精选  2:潮玩  3:送礼   4:海淘)
     * @return type 商品标签 (1:精选  2:潮玩  3:送礼   4:海淘)
     */
    public Integer getType() {
        return type;
    }

    /**
     * 商品标签 (1:精选  2:潮玩  3:送礼   4:海淘)
     * @param type 商品标签 (1:精选  2:潮玩  3:送礼   4:海淘)
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 状态 01 正常 02 已删除
     * @return state 状态 01 正常 02 已删除
     */
    public Integer getState() {
        return state;
    }

    /**
     * 状态 01 正常 02 已删除
     * @param state 状态 01 正常 02 已删除
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 排序号 排序值越大排序越前
     * @return sort 排序号 排序值越大排序越前
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 排序号 排序值越大排序越前
     * @param sort 排序号 排序值越大排序越前
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}