package com.xmniao.xmn.core.vstar.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.xmniao.xmn.core.base.BaseEntity;

public class Division extends BaseEntity{
    /**
     * 赛区编号
     */
    private Integer id;

    /**
     * 赛区名称
     */
    private String divisionName;
    
    private String cityNames;

    private String cityIds;
    
    private List<String> cityIdsList;
    
    public List<String> getCityIdsList() {
    	if(StringUtils.isBlank(cityIds)){
    		return new ArrayList<>();
    	}
		return Arrays.asList(cityIds.split(","));
	}

	public void setCityIdsList(List<String> cityIdsList) {
		this.cityIdsList = cityIdsList;
	}

	public String getCityIds() {
		return cityIds;
	}

	public void setCityIds(String cityIds) {
		this.cityIds = cityIds;
	}

	public String getCityNames() {
		return cityNames;
	}

	public void setCityNames(String cityNames) {
		this.cityNames = cityNames;
	}

	/**
     * 赛区编号
     * @return id 赛区编号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 赛区编号
     * @param id 赛区编号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 赛区名称
     * @return division_name 赛区名称
     */
    public String getDivisionName() {
        return divisionName;
    }

    /**
     * 赛区名称
     * @param divisionName 赛区名称
     */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName == null ? null : divisionName.trim();
    }

	
}