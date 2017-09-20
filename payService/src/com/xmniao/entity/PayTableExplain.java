package com.xmniao.entity;

public class PayTableExplain {
    private Integer id;

    private String tableName;

    private String columnName;

    private String columnValue;

    private String valueExplain;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName == null ? null : columnName.trim();
    }

    public String getColumnValue() {
        return columnValue;
    }

    public void setColumnValue(String columnValue) {
        this.columnValue = columnValue == null ? null : columnValue.trim();
    }

    public String getValueExplain() {
        return valueExplain;
    }

    public void setValueExplain(String valueExplain) {
        this.valueExplain = valueExplain == null ? null : valueExplain.trim();
    }
}