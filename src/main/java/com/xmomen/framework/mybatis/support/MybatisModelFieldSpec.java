package com.xmomen.framework.mybatis.support;

/**
 * Created by jengt_000 on 2014/12/27.
 */
public class MybatisModelFieldSpec {

    private String columnName;
    private String fieldName;
    private String javaType;
    private String jdbcType;

    public MybatisModelFieldSpec(String columnName, String fieldName, String javaType, String jdbcType) {
        this.columnName = columnName;
        this.fieldName = fieldName;
        this.javaType = javaType;
        this.jdbcType = jdbcType;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }
}
