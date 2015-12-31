package com.udfex.framework.web.http;

/**
 * 
 */

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * @author david.cui
 * 
 */
public class HttpClient {

	private static final String _POST = "POST";
	private static final String _GET = "GET";
	private static final String _EQUAL = "=";
	private static final String _AMPERSAND = "&";
	private static final String _UTF_8 = "UTF-8";
	private static final int connectTimeOut = 3000;
	private static final int readDataTimeOut = 300000;
	
	public static String doProcess(String reqUrl, Map<String, String> params, Map<String, String> headers, String method) throws Exception {
		if ("POST".equals(method)) {
			return prcess(reqUrl, params, headers, _POST);
		} else {
			return prcess(reqUrl, params, headers, _GET);
		}
	}

	public static String doProcess(String reqUrl, Map<String, String> params, String method) throws Exception {
		if ("POST".equals(method)) {
			return prcess(reqUrl, params, null, _POST);
		} else {
			return prcess(reqUrl, params, null, _GET);
		}
	}

	public static String doProcess(String reqUrl, String method) throws Exception {
		if ("POST".equals(method)) {
			return prcess(reqUrl, null, null, _POST);
		} else {
			return prcess(reqUrl, null, null, _GET);
		}
	}

	/**
	 * 执行get请求
	 * @param reqUrl
	 * @param params
	 * @param headers
	 * @return
	 * @throws Exception
	 */
	public static String doGet(String reqUrl, Map<String, String> params, Map<String, String> headers) throws Exception {
		return prcess(reqUrl, params, headers, _GET);
	}

	/**
	 * 执行get请求
	 * @param reqUrl
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String doGet(String reqUrl, Map<String, String> params) throws Exception {
		return prcess(reqUrl, params, null, _GET);
	}

	public static String doGet(String reqUrl) throws Exception {
		return prcess(reqUrl, null, null, _GET);
	}

	/**
	 * 执行post请求
	 * @param reqUrl 请求URL
	 * @param params post请求数据
	 * @param headers 请求http报文头
	 * @return 响应结果的文本信息
	 * @throws Exception
	 */
	public static String doPost(String reqUrl, Map<String, String> params, Map<String, String> headers) throws Exception {
		return prcess(reqUrl, params, headers, _POST);
	}

	/**
	 * 执行post请求
	 * @param reqUrl 请求URL
	 * @param params post请求数据
	 * @return 响应结果的文本信息
	 * @throws Exception
	 */
	public static String doPost(String reqUrl, Map<String, String> params) throws Exception {
		return prcess(reqUrl, params, null, _POST);
	}

	/**
	 * 
	 * @param reqUrl
	 * @param params
	 * @param headers
	 * @return
	 * @throws Exception
	 */
	private static String prcess(String reqUrl, Map<String, String> params, Map<String, String> headers, String method) throws Exception {
		StringBuilder content = new StringBuilder();
		HttpURLConnection httpConn = null;
		OutputStream outStream = null;
		InputStream inputStream = null;
		BufferedReader reader = null;
		try {
			// 1、创建连接
			URL url = new URL(reqUrl);
			httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setDoInput(true);
			httpConn.setDoOutput(true);
			httpConn.setUseCaches(false);
			httpConn.setConnectTimeout(connectTimeOut);
			httpConn.setReadTimeout(readDataTimeOut);
			// 2、设置请求头
			if (headers != null) {
				for (Map.Entry<String, String> entry : headers.entrySet()) {
//					httpConn.setRequestProperty("Authorization", "fa0337d3f6e6a3f1b97ca81e6aba781e");
					httpConn.setRequestProperty(entry.getKey(), entry.getValue());
				}
			}

			// 3、连接
			httpConn.setRequestMethod(method);
			httpConn.connect();

			// 4、设置请求参数
			//设置Http Post数据 
			StringBuilder sb = new StringBuilder();
			if (params != null && params.size() > 0) {
				for (Map.Entry<String, String> entry : params.entrySet()) {
					sb.append(_AMPERSAND);
					sb.append(entry.getKey());
					sb.append(_EQUAL);
					sb.append(entry.getValue());
					sb.append(_AMPERSAND);
				}
				outStream = httpConn.getOutputStream();
				outStream.write(sb.toString().getBytes(_UTF_8));
				outStream.flush();
				outStream.close();
			}

			// 5、获取响应结果
			if (HttpURLConnection.HTTP_OK == httpConn.getResponseCode()) {
				inputStream = httpConn.getInputStream();
				String responseStr = "";
				reader = new BufferedReader(new InputStreamReader(inputStream, _UTF_8));
				while ((responseStr = reader.readLine()) != null) {
					content.append(responseStr);
					//content.append("\n") ;
				}
				inputStream.close();
				return content.toString();
			} else {
				throw new Exception("HTTP error responseCode :" + httpConn.getResponseCode());
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (null != httpConn) {
				try {
					httpConn.disconnect();
				} catch (Exception e) {
				}
			}
			if (null != outStream) {
				try {
					outStream.close();
				} catch (Exception e) {
				}
			}
			if (null != inputStream) {
				try {
					inputStream.close();
				} catch (Exception e) {
				}
			}
			if (null != reader) {
				try {
					reader.close();
				} catch (Exception e) {
				}
			}
		}

	}

}
