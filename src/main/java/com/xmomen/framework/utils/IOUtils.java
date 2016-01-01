package com.xmomen.framework.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.nio.channels.Channel;

import com.xmomen.framework.exception.InOutException;

public class IOUtils {

    public static void closeQuietly(final Channel channel) throws InOutException {

        if (channel == null) {
            return;
        }

        try {
            channel.close();
        } catch (final IOException ioe) {
            throw new InOutException("close Channel failure!", ioe);
        }
    }

    public static void closeQuietly(final InputStream input) throws InOutException {
        if (input == null) {
            return;
        }
        try {
            input.close();
        } catch (final IOException ioe) {
            throw new InOutException("close InputStream failure!", ioe);
        }
    }

    /**
     * 
     * @param output
     * @throws InOutException
     */
    public static void closeQuietly(final OutputStream output) throws InOutException {
        if (output == null) {
            return;
        }
        try {
            output.close();
        } catch (final IOException ioe) {
            throw new InOutException("close OutputStream failure!", ioe);
        }
    }

    /**
     * 
     * @param input
     * @throws InOutException
     */
    public static void closeQuietly(final Reader input) throws InOutException {
        if (input == null) {
            return;
        }
        try {
            input.close();
        } catch (final IOException ioe) {
            throw new InOutException("close Reader failure!", ioe);
        }
    }

    /**
     * 
     * @param output
     * @throws InOutException
     */
    public static void closeQuietly(final Writer output) throws InOutException {
        if (output == null) {
            return;
        }
        try {
            output.close();
        } catch (final IOException ioe) {
            throw new InOutException("close Writer failure!", ioe);
        }
    }

    /**
     * 
     * @param clazz
     * @param resourceName
     * @return
     */
    public static URL getResource(final Class clazz, final String resourceName) {
        return clazz.getClassLoader().getResource(resourceName);
    }

    /**
     * 
     * @param resourceName
     * @return
     */
    public static URL getResource(final String resourceName) {
        return Thread.currentThread().getContextClassLoader().getResource(resourceName);
    }
    /**
     * 
     * @param clazz
     * @param resourceName
     * @return
     */
    public static InputStream getResourceAsFile(final Class clazz, final String resourceName) {
        return clazz.getClassLoader().getResourceAsStream(resourceName);
    }

    /**
     * 
     * @param resourceName
     * @return
     */
    public static InputStream getResourceAsFile(final String resourceName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceName);
    }
}
