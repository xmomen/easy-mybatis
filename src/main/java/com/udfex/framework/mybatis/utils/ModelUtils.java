package com.udfex.framework.mybatis.utils;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanWrapperImpl;

import com.udfex.framework.mybatis.model.BaseMybatisModel;

/**
 * Created by jengt_000 on 2014/12/23.
 */
public class ModelUtils {

    public static String getTableName(Class<? extends BaseMybatisModel> modelClass) {
        if (modelClass.isAnnotationPresent(Table.class)) {
            return ((Table) modelClass.getAnnotation(Table.class)).name();
        }
		if (modelClass.getSuperclass().isAnnotationPresent(Table.class)) {
			return ((Table) modelClass.getSuperclass().getAnnotation(Table.class)).name();
		}
        return null;
    }

    public static String getPrimaryKeyFieldName(Class<? extends BaseMybatisModel> modelClass) {
        for (Method method : modelClass.getMethods()) {
            if (method.isAnnotationPresent(Id.class)) {
                String methodName = method.getName();
                return methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
            }
        }
        return null;
    }

    public static String getPrimaryKeyColumnName(Class<? extends BaseMybatisModel> modelClass) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        for (Method method : modelClass.getMethods()) {
            if ((method.isAnnotationPresent(Id.class)) && (method.isAnnotationPresent(Column.class))) {
                for (int i = 0; i < method.getAnnotations().length; i++) {
                    Annotation annotation = method.getAnnotations()[i];
                    if (annotation.annotationType().equals(Column.class)) {
                        Method annotationMethod = annotation.getClass().getDeclaredMethod("name", method.getParameterTypes());
                        return annotationMethod.invoke(annotation, null).toString();
                    }
                }
            }
        }
        return null;
    }

    public static <MODEL extends BaseMybatisModel> Serializable getPrimaryKeyValue(MODEL model) {
        return getFieldValueByFieldName(model, getPrimaryKeyFieldName(model.getClass()));
    }



    public static String getRecVersionFieldName(Class<? extends BaseMybatisModel> modelClass) {
        for (Method method : modelClass.getMethods()) {
            String methodName = method.getName();
            if (method.isAnnotationPresent(Version.class)) {
                return methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
            }
        }
        return null;
    }

    public static String getColumnName(Class<? extends BaseMybatisModel> modelClass, String name) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        for (Method method : modelClass.getMethods()) {
            for (int i = 0; i < method.getAnnotations().length; i++) {
                Annotation annotation = method.getAnnotations()[i];
                if (annotation.annotationType().equals(Column.class)) {
                    Method annotationMethod = annotation.getClass().getDeclaredMethod("name", method.getParameterTypes());
                    String columnName = annotationMethod.invoke(annotation, null).toString();
                    if(columnName != null && name.equals(columnName)){
                        return columnName;
                    }
                }
            }
        }
        return null;
    }

    public static String getRecVersionColumnName(Class<? extends BaseMybatisModel> modelClass) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        for (Method method : modelClass.getMethods()) {
            if (method.isAnnotationPresent(Version.class)) {
                for (int i = 0; i < method.getAnnotations().length; i++) {
                    Annotation annotation = method.getAnnotations()[i];
                    if (annotation.annotationType().equals(Column.class)) {
                        Method annotationMethod = annotation.getClass().getDeclaredMethod("name", method.getParameterTypes());
                        return annotationMethod.invoke(annotation, null).toString();
                    }
                }
            }
        }
        return null;
    }

    public static Method getReadMethodByFieldName(Class<? extends BaseMybatisModel> entityClass, String fieldName) {
        if (!StringUtils.isEmpty(fieldName)) {
            BeanWrapperImpl beanWrapper = new BeanWrapperImpl(entityClass);
            return beanWrapper.getPropertyDescriptor(fieldName).getReadMethod();
        }
        return null;
    }

    public static Method getWriteMethodByFieldName(Class<? extends BaseMybatisModel> entityClass, String fieldName) {
        if (!StringUtils.isEmpty(fieldName)) {
            BeanWrapperImpl beanWrapper = new BeanWrapperImpl(entityClass);
            return beanWrapper.getPropertyDescriptor(fieldName).getWriteMethod();
        }
        return null;
    }

    public static Serializable getFieldValueByFieldName(Object obj, String fieldName) {
        if (!StringUtils.isEmpty(fieldName)) {
            return (Serializable) new BeanWrapperImpl(obj).getPropertyValue(fieldName);
        }
        return null;
    }

    public static String underscoreName(String name) {
        StringBuilder result = new StringBuilder();
        if ((name != null) && (name.length() > 0)) {
            result.append(name.substring(0, 1).toUpperCase());

            for (int i = 1; i < name.length(); i++) {
                String s = name.substring(i, i + 1);

                if ((s.equals(s.toUpperCase())) && (!Character.isDigit(s.charAt(0)))) {
                    result.append("_");
                }

                result.append(s.toUpperCase());
            }
        }
        return result.toString();
    }

    public static String camelName(String name) {
        StringBuilder result = new StringBuilder();

        if ((name == null) || (StringUtils.isEmpty(name))) {
            return "";
        }
        if (!name.contains("_")) {
            return name.substring(0, 1).toLowerCase() + name.substring(1);
        }

        String[] camels = name.split("_");
        for (String camel : camels) {
            if (StringUtils.isEmpty(camel)) {
                continue;
            }
            if (result.length() == 0) {
                result.append(camel.toLowerCase());
            } else {
                result.append(camel.substring(0, 1).toUpperCase());
                result.append(camel.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }
}
