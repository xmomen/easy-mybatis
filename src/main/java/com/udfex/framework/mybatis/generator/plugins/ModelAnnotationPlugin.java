package com.udfex.framework.mybatis.generator.plugins;


import com.udfex.framework.model.BaseModel;
import com.udfex.framework.mybatis.generator.plugins.utils.PluginUtils;
import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;

/**
 * Created by jengt_000 on 2014/12/23.
 */
public class ModelAnnotationPlugin extends PluginAdapter {
    private static final String VERSION_COLUMN_NAME = "versionColumnName";
    private String versionColumnName;

    /**
     * TODO 未知作用
     */
    public ModelAnnotationPlugin() {
        this.versionColumnName = ((String) getProperties().get("versionColumnName"));
        this.versionColumnName = ((this.versionColumnName == null) || (this.versionColumnName.length() < 1) ? BaseModel.FIELD_RECORD_VERSION : this.versionColumnName);
    }

    /**
     * 添加数据库字段注释
     * @param field
     * @param topLevelClass
     * @param introspectedColumn
     * @param introspectedTable
     * @param modelClassType
     * @return
     */
    public boolean modelFieldGenerated(Field field,
                                       TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
                                       IntrospectedTable introspectedTable,
                                       ModelClassType modelClassType) {
        field.addJavaDocLine("/**");
        field.addJavaDocLine(" * " + introspectedColumn.getRemarks());
        field.addJavaDocLine(" */");
        return super.modelFieldGenerated(field, topLevelClass, introspectedColumn, introspectedTable, modelClassType);
    }

    /**
     * 添加Getter方法的注解
     * @param method
     * @param topLevelClass
     * @param introspectedColumn
     * @param introspectedTable
     * @param modelClassType
     * @return
     */
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        if ((introspectedColumn.getActualColumnName() != null) && (introspectedColumn.getActualColumnName().length() > 0)) {
            method.addAnnotation("@Column(name = \"" + introspectedColumn.getActualColumnName() + "\")");
        }

        if (PluginUtils.isPrimaryKeyColumn(introspectedColumn, introspectedTable)) {
            method.addAnnotation("@Id");
            method.addAnnotation("@GeneratedValue(generator = \"UUIDGenerator\")");
        }

        if (introspectedColumn.getActualColumnName().equals(this.versionColumnName)) {
            method.addAnnotation("@Version");
        }
        return super.modelGetterMethodGenerated(method, topLevelClass, introspectedColumn, introspectedTable, modelClassType);
    }

    /**
     * 添加Setter方法的有效字段方法<br/>
     * 注：用于MybatisDao接口过滤对象属性中不为空的字段
     * @param method
     * @param topLevelClass
     * @param introspectedColumn
     * @param introspectedTable
     * @param modelClassType
     * @return
     */
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        method.addBodyLine("if(" + introspectedColumn.getJavaProperty() + " == null){");
        method.addBodyLine("  removeValidField(\"" + introspectedColumn.getJavaProperty() + "\");");
        method.addBodyLine("  return;");
        method.addBodyLine("}");
        method.addBodyLine("addValidField(\""+introspectedColumn.getJavaProperty()+"\");");
        return super.modelSetterMethodGenerated(method, topLevelClass, introspectedColumn, introspectedTable, modelClassType);
    }

    /**
     * 添加实体类中的类包路径引用及表头注解
     * @param topLevelClass
     * @param introspectedTable
     * @return
     */
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        topLevelClass.addImportedType("javax.persistence.Column");
        topLevelClass.addImportedType("javax.persistence.Entity");
        topLevelClass.addImportedType("javax.persistence.GeneratedValue");
        topLevelClass.addImportedType("javax.persistence.Id");
        topLevelClass.addImportedType("javax.persistence.Table");
        topLevelClass.addImportedType("javax.persistence.Version");
        topLevelClass.addAnnotation("@Entity");
        topLevelClass.addAnnotation("@Table(name = \"" + introspectedTable.getTableConfiguration().getTableName() + "\")");

        return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
    }

    public boolean validate(List<String> warnings) {
        return true;
    }
}
