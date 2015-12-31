/**
 * 
 */
package com.udfex.framework.support;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.udfex.framework.utils.MD5Utils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * @author Jeng
 *
 */
public class PropertyPlaceholderConfigurerExt extends PropertyPlaceholderConfigurer{
	private static PropertyPlaceholderConfigurerExt propertyPlaceholderConfigurer = null;

    private static Map<String, Object> ctxPropertiesMap;

    private String salt = "0b2f505363d2dfcf5e06879d529d7a7a";

    private String[] encryptPropNames;

    public String[] getEncryptPropNames() {
        return encryptPropNames;
    }

    public void setEncryptPropNames(String[] encryptPropNames) {
        this.encryptPropNames = encryptPropNames;
    }

    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
        if (isEncryptProp(propertyName)) {
            return MD5Utils.encrypt(MD5Utils.encrypt(MD5Utils.encrypt(MD5Utils.encrypt(propertyValue, salt), salt), salt), salt);
        } else {
            return propertyValue;
        }
    }

    /**
     * 判断是否是加密的属性
     *
     * @param propertyName
     * @return
     */
    private boolean isEncryptProp(String propertyName) {
        if(encryptPropNames == null){
            return false;
        }
        for (String encryptpropertyName : encryptPropNames) {
            if (encryptpropertyName.equals(propertyName))
                return true;
        }
        return false;
    }

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
            throws BeansException {
    	
        super.processProperties(beanFactoryToProcess, props);
        ctxPropertiesMap = new HashMap<String, Object>();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            ctxPropertiesMap.put(keyStr, value);
        }
    }

    public String getContextProperty(String name) {
        return (String) ctxPropertiesMap.get(name);
    }

    public static PropertyPlaceholderConfigurerExt getInstance() {
        if (propertyPlaceholderConfigurer == null) {
            propertyPlaceholderConfigurer = (PropertyPlaceholderConfigurerExt) SpringContextUtil.getBean("propertyPlaceholderConfigurer");
        }
        return propertyPlaceholderConfigurer;
    }
}

