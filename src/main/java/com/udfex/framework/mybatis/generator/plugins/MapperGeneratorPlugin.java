package com.udfex.framework.mybatis.generator.plugins;

import com.udfex.framework.mybatis.mapper.MybatisMapper;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;

public class MapperGeneratorPlugin extends PluginAdapter {

    public boolean clientGenerated(Interface interfaze,
                                   TopLevelClass topLevelClass,
                                   IntrospectedTable introspectedTable) {
        interfaze.addSuperInterface(new FullyQualifiedJavaType(MybatisMapper.class.getName()));
        interfaze.addImportedType(new FullyQualifiedJavaType(MybatisMapper.class.getName()));
        return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
    }

    public boolean validate(List<String> warnings) {
        return true;
    }
}
