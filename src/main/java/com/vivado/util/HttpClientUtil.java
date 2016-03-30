package com.vivado.util;

import java.io.IOException;
import java.nio.charset.CodingErrorAction;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.Consts;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 鍒涘缓Http璇锋眰宸ュ叿绫�
 * @author huiwu
 *
 */
public class HttpClientUtil {

	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

	private static PoolingHttpClientConnectionManager connectionManager = null;
	
	// 鏈�澶ц繛鎺ユ暟
	private static final int MAX_TOTAL_CONNECTIONS = 400;
	// 姣忎釜璺敱鏈�澶ц繛鎺ユ暟
	private static final int MAX_ROUTE_CONNECTIONS = 40;
	
	/**
	 * 鍒濆鍖栭厤缃�
	 */
	static{
		SSLContext sslContext = null;
		try {
			sslContext = SSLContexts.custom().useTLS().build();
			sslContext.init(null, new TrustManager[] { new X509TrustManager() {
				@Override
				public void checkClientTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
				}

				@Override
				public void checkServerTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
				}

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			
			} }, null);
		} catch (KeyManagementException e) {
			logger.error("KeyManagementException", e);
		} catch (NoSuchAlgorithmException e) {
			logger.error("NoSuchAlgorithmException", e);
		}
		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
				.<ConnectionSocketFactory> create()
				.register("http", PlainConnectionSocketFactory.INSTANCE)
				.register("https",(new SSLConnectionSocketFactory(sslContext == null ? SSLContexts.createDefault() : sslContext,SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER))).build();
		connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		ConnectionConfig connectionConfig = ConnectionConfig.custom()
				.setMalformedInputAction(CodingErrorAction.IGNORE)
				.setUnmappableInputAction(CodingErrorAction.IGNORE)
				.setCharset(Consts.UTF_8).build();
		connectionManager.setDefaultConnectionConfig(connectionConfig);
		connectionManager.setMaxTotal(MAX_TOTAL_CONNECTIONS);
		connectionManager.setDefaultMaxPerRoute(MAX_ROUTE_CONNECTIONS);
	}
	
	/**鍒涘缓 POST璇锋眰 **/
	public static PostRequest buildPostRequest(String url){
		return new PostRequest(getHttpClient(), url);
	}
	public static PostRequest buildPostRequest(String url,int retryCount){
		return new PostRequest(getHttpClient(retryCount), url);
	}

	public static PostRequest buildPostRequest(String url,String charset){
		return new PostRequest(getHttpClient(), url,charset);
	}
	public static PostRequest buildPostRequest(String url,int retryCount,String charset){
		return new PostRequest(getHttpClient(retryCount), url,charset);
	}

	public static GetRequest buildGetRequest(String url){
		return new GetRequest(getHttpClient(), url);
	}
	public static GetRequest buildGetRequest(String url,int retryCount){
		return new GetRequest(getHttpClient(retryCount), url);
	}

	public static GetRequest buildGetRequest(String url,String charset){
		return new GetRequest(getHttpClient(), url,charset);
	}
	public static GetRequest buildGetRequest(String url,int retryCount,String charset){
		return new GetRequest(getHttpClient(retryCount), url,charset);
	}

	private static HttpClient getHttpClient(){
		return HttpClients.custom().setConnectionManager(connectionManager).build();
	}
	private static HttpClient getHttpClient(int retryCount){
		return HttpClients.custom().setRetryHandler(createHttpRequestRetryHandler(retryCount)).setConnectionManager(connectionManager).build();
	}

	private static HttpRequestRetryHandler createHttpRequestRetryHandler(final int retryCount){
		return new HttpRequestRetryHandler() {
			public boolean retryRequest(
					IOException exception,
					int executionCount,
					HttpContext context) {
				logger.info("retry retryCount={},executionCount={}",retryCount,executionCount);
				if (executionCount >= retryCount) {
					return false;
				}
				logger.error("request error",exception);
				return true;
			}
		};
	}
}


