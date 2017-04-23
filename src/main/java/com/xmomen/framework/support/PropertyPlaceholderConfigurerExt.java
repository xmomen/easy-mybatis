/**
 * 
 */
package com.xmomen.framework.support;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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

    private String[] encryptPropNames;

    public String[] getEncryptPropNames() {
        return encryptPropNames;
    }

    public void setEncryptPropNames(String[] encryptPropNames) {
        this.encryptPropNames = encryptPropNames;
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

