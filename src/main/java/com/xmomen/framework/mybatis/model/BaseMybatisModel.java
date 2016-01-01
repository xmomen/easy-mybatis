package com.xmomen.framework.mybatis.model;

import com.xmomen.framework.model.BaseModel;
import com.xmomen.framework.mybatis.support.MybatisModelFieldSpec;
import com.xmomen.framework.mybatis.support.MybatisType;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import javax.persistence.Column;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.util.*;

/**
 * Created by jengt_000 on 2014/12/23.
 */
public class BaseMybatisModel extends BaseModel implements Serializable {

    public static final String PRIMARY_KEY = "PRIMARY_KEY_ID";

    public static final String PARAMETER_MODEL_CLASS = "modelClass";

    /**
     * 实体字段规范
     */
    private transient static Map<String, List<MybatisModelFieldSpec>> modelFieldSpec = new HashMap<String, List<MybatisModelFieldSpec>>();

    /**
     * 计算实体字段规范
     */
    public static <MODEL extends BaseMybatisModel> List<MybatisModelFieldSpec> getModelFieldSpec(
            Class<MODEL> clazz) {
        if (modelFieldSpec.containsKey(clazz.getName())) {
            return modelFieldSpec.get(clazz.getName());
        } else {
            List<MybatisModelFieldSpec> modelFieldSpecs = new ArrayList<MybatisModelFieldSpec>();
            BeanWrapper beanWrapper = new BeanWrapperImpl(clazz);
            for (PropertyDescriptor propertyDescriptor : beanWrapper.getPropertyDescriptors()) {
                if (beanWrapper
                        .isReadableProperty(propertyDescriptor.getName())
                        && propertyDescriptor.getReadMethod()
                        .isAnnotationPresent(Column.class)) {
                    Column column = propertyDescriptor.getReadMethod()
                            .getAnnotation(Column.class);
                    String javaType = propertyDescriptor.getPropertyType()
                            .getSimpleName();
                    modelFieldSpecs.add(new MybatisModelFieldSpec(
                            column.name(),
                            propertyDescriptor.getName(),
                            javaType,
                            MybatisType.javaTypeMap.get(javaType)));

                }
            }
            modelFieldSpec.put(clazz.getName(), modelFieldSpecs);
            return modelFieldSpecs;
        }
    }

    public static <MODEL extends BaseMybatisModel> MybatisModelFieldSpec getFieldSpecByFieldName(Class<MODEL> clazz, String fieldName) {
        Iterator iterator = getModelFieldSpec(clazz).iterator();
        while (iterator.hasNext()) {
            MybatisModelFieldSpec mybatisModelFieldSpec = (MybatisModelFieldSpec) iterator.next();
            if (mybatisModelFieldSpec.getFieldName().equals(fieldName))
                return mybatisModelFieldSpec;
        }
        return null;
    }


}
