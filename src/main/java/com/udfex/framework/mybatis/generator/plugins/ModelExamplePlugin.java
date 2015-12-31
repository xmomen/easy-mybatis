package com.udfex.framework.mybatis.generator.plugins;

import com.udfex.framework.mybatis.model.BaseMybatisExample;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;

/**
 * Created by jengt_000 on 2014/12/23.
 */
public class ModelExamplePlugin extends PluginAdapter {

    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        topLevelClass.setSuperClass(BaseMybatisExample.class.getName());
        topLevelClass.addImportedType(BaseMybatisExample.class.getName());
        return super.modelExampleClassGenerated(topLevelClass, introspectedTable);
    }

    public boolean validate(List<String> warnings) {
        return true;
    }
}
