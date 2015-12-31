package com.udfex.framework.log;

/**
 * user to generate the logger adapter
 * 
 * @author		Steven 
 * @date 		Oct 21, 2013 12:37:46 AM 
 *
 */
public class LoggerFactory {
	
	/**
	 * generate the slf4j logger adapter
	 * 
	 * @param: 			clazzClass
	 * @return: 		Logger     
	 * @throws:
	 */
	public static Logger getLogger(final Class<?> clazzClass) {
		return new LoggerSlf4jAdapter(clazzClass);
	}
}
