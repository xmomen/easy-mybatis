package com.xmomen.framework.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropUtils {

    public static final String DEFAULT_CONFIG_SUFFIX = ".properties";

    private static final Logger LOGGER = LoggerFactory.getLogger(PropUtils.class);

    /** configuration */
    private final Properties config = new Properties();

    /**
     * Creates a new PropUtils object.
     * 
     * @param url
     *            URL
     * @throws java.io.IOException
     * 
     * @throws Exception
     */
    public PropUtils(final URL url) {
        InputStream fin = null;
        try {
            fin = url.openStream();
            config.load(fin);
            LOGGER.debug("init properties file [" + url + "] success");
        } catch (final IOException e) {
            LOGGER.error("properties file [" + url + "] not found", e);
        }
        IOUtils.closeQuietly(fin);
    }

    /**
     * <p>
     * return configuration value by key
     * </p>
     * 
     * @param itemName
     *            String
     * 
     * @return String
     */
    public String getValue(final String itemName) {
        return config.getProperty(itemName);
    }

    /**
     * <p>
     * return configuration value by key, return default value if property not
     * found
     * </p>
     * 
     * @param itemName
     *            String
     * @param defaultValue
     *            String
     * 
     * @return String
     */
    public String getValue(final String itemName, final String defaultValue) {
        return config.getProperty(itemName, defaultValue);
    }
}
