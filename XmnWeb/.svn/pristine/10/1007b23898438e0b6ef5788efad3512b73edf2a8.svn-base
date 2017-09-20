package com.xmniao.xmn.core.base.Import.bean;

/**
 * 导出属性
 * @author ch
 *
 */
public class ImportModelDefinition {
	/***
	 * property name in java bean
	 */
	private String name = null;
	
	/***
	 * excel title，not data
	 */
	private String excelTitleName = null;
	/***
	 * data type
	 */
	private String dataType = null;
	private String maxLength = null;
//	private String fixity = null;
//	private String codeTableName = null;
	/***
	 * date format ,if property type is "Date"
	 */
	private String dateFormat=null;
	/***
	 * default value is java bean
	 */
	private String defaultValue = null;
	private boolean isFinish=false;
	
	
	public void finish() {
		this.isFinish = true;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		if(!this.isFinish){
			this.dataType = dataType;
		}
		
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		if(!this.isFinish){
			this.defaultValue = defaultValue;
		}
		
	}
	public String getExcelTitleName() {
		return excelTitleName;
	}
	public void setExcelTitleName(String excelTitleName) {
		if(!this.isFinish){
			this.excelTitleName = excelTitleName;
		}
		
	}
	public String getMaxLength() {
		return maxLength;
	}
	public void setMaxLength(String maxLength) {
		if(!this.isFinish){
			this.maxLength = maxLength;
		}
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if(!this.isFinish){
			this.name = name;
		}
		
	}

	public String getDateFormat() {
		return dateFormat;
	}
	public void setDateFormat(String dateFormat) {
		if(!this.isFinish){
			this.dateFormat = dateFormat;
		}
		
	}
	
	
	
}
