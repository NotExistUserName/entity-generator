package com.xx.vo;

import java.io.Serializable;

/**
 * @author xiexing
 * @discription 数据库字段实体类ＶＯ
 * @date 2020/3/30
 */
public class ColumnVO implements Serializable {
    private static final long serialVersionUID = -5540929686993572515L;

    /**
     * 是否是主键,默认为false
     */
    private boolean primaryKey = false;


    /**
     * 字段名
     */
    private String colName;

    /**
     * 字段备注
     */
    private String colRemark;

    /**
     * 数据类型
     */
    private Class dataType;

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public String getColRemark() {
        return colRemark;
    }

    public void setColRemark(String colRemark) {
        this.colRemark = colRemark;
    }

    public Class getDataType() {
        return dataType;
    }

    public void setDataType(Class dataType) {
        this.dataType = dataType;
    }
}
