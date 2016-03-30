package com.vivado.util;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.vivado.exception.HttpExeption;

/**
 * 处理post请求
 * @author huiwu
 */
public final class PostRequest extends Request{

	private static final Logger logger = LoggerFactory
			.getLogger(PostRequest.class);

	private HttpPost post = null;

	private HttpClient client = null;
	
	private HttpEntity httpEntity = null;
	
	

	private final List<NameValuePair> params = new ArrayList<NameValuePair>();

	private static final String DEFAULTCHARSET = "UTF-8";

	private String charset = DEFAULTCHARSET;

	public PostRequest(HttpClient client, String url) {
		this.client = client;
		post = new HttpPost(url);
	}

	public PostRequest(HttpClient httpClient, String url,
			String charset) {
		this(httpClient,url);
		this.charset = charset;
	}

	public PostRequest addHeader(String name, Object value) {
		if (value == null) {
			return this;
		}
		post.addHeader(name, String.valueOf(value));
		return this;
	}

	public PostRequest addHeaders(Map<String,Object> headers){
		if (headers==null||headers.isEmpty()) {
			return this;
		}
		Set<String> keySets = headers.keySet();
		Iterator<String> it = keySets.iterator();
		while (it.hasNext()){
			String key = it.next();
			addHeader(key, headers.get(key));
		}
		return this;
	}

	public PostRequest addParam(String name, Object value) {
		if (value == null) {
			return this;
		}
		params.add(new BasicNameValuePair(name, String.valueOf(value)));
		return this;
	}
	public PostRequest addParams(Map<String,Object> map) {
		if (map == null||map.isEmpty()) {
			return this;
		}
		Set<String> keys = map.keySet();
		Iterator<String> it = keys.iterator();
		while (it.hasNext()){
			String paramName = it.next();
			addParam(paramName,map.get(paramName));
		}
		return this;
	}
	
	/**
	 * 设置body后，通过addParam方法添加的参数将不会设置到请求中
	 * @param body
	 * @return
	 */
	public PostRequest setBody(String body) {
		try {
			if (body==null) {
				return this;
			}
			httpEntity = new StringEntity(body,charset);
		} catch (Exception e) {
			logger.error("set String body error ", e);
		}
		return this;
	}
	
	
	/**
	 * 添加文件参数,一个POST请求添加一个文件参数
	 * @param name 上传文件的参数名
	 * @param file
	 * @return
	 */
	public Request addFile(String name, File file) {
		try {
			addFile(name, file, ContentType.DEFAULT_BINARY);
		} catch (Exception e) {
			logger.error("set File body error ", e);
		}
		return this;
	}
	
	/**
	 * 添加文件参数,一个POST请求添加一个文件参数
	 * @param name 上传文件的参数名
	 * @param file
	 * @param contentType 默认为 application/octet-stream
	 * @return
	 */
	public Request addFile(String name, File file,ContentType contentType) {
		try {
			if (contentType==null) {
				contentType = ContentType.DEFAULT_BINARY;
			}
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			builder.addBinaryBody(name, file, contentType,file != null ? file.getName() : null);
			builder.setCharset(Charset.forName(charset));
			if (params!=null&&!params.isEmpty()) {
				for (NameValuePair nv : params) {
					builder.addTextBody(nv.getName(),nv.getValue());
				}
			}
			httpEntity = builder.build();
		} catch (Exception e) {
			logger.error("set File error ", e);
		}
		return this;
	}
	

	public String execute()throws HttpExeption {
		return exc(String.class);
	}

	@Override
	public JSONObject executeToJson()throws HttpExeption {
		return exc(JSONObject.class);
	}

	public  <T>T executeToObject(Class<T> clazz)throws HttpExeption{
		return exc(clazz);
	}

	@SuppressWarnings("unchecked")
	private <T> T exc(Class<T> clazz) throws HttpExeption {
		T obj = null;
		int status = 0;
		String result = null;
		long startTime = System.currentTimeMillis();
		try {
			if (httpEntity==null) {
				httpEntity = new UrlEncodedFormEntity(params,charset);
			}
			post.setEntity(httpEntity);
			post.setConfig(getRequestConfig());
			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			status = response.getStatusLine().getStatusCode();
			if (entity!=null) {
				try {
					ContentType ct = ContentType.get(entity);
					result = EntityUtils.toString(entity, charset);
					if (clazz==null||clazz == String.class){
						if (StringUtils.isNotBlank(result)){
							obj =  (T) result;
						}
					}else if (ContentType.APPLICATION_JSON.getMimeType().equals(ct.getMimeType())){
						if (clazz == JSONObject.class){
							if (StringUtils.isNotBlank(result)){
								obj = (T) JSON.parseObject(result);
							}
						}else{
							if (StringUtils.isNotBlank(result)){
								obj = (T) JSON.parseObject(result, clazz);
							}
						}
					}else{
						throw new RuntimeException("Not support contentType "+ ct.toString());
					}
				}finally{
					EntityUtils.consume(entity);
				}
			}
			} catch (Exception e) {
			logger.error("请求失败", e);
			throw new HttpExeption(e);
		}finally {
			long endTime = System.currentTimeMillis();
			if(StringUtils.defaultString(result, "").length() <= 256){
				logger.info("executeTime={}ms,status={},url={},params={},result={}", endTime - startTime,status,
						post.getURI(),params,result);
			}else{
				logger.info("executeTime={}ms,status={},url={},params={},result={}", endTime - startTime,status,
						post.getURI(),params,result.substring(0, 256));
			}
		}
		return obj;
	}

	public PostRequest setReadTimeOut(int readTimeOut){
		if (readTimeOut>0) {
			this.readTimeOut = readTimeOut;
		}
		return this;
	}
	
	public PostRequest setConnectTimeOut(int connectTimeOut){
		if (connectTimeOut>0) {
			this.connectTimeOut = connectTimeOut;
		}
		return this;
	}
	
	public PostRequest setWaitTimeOut(int waitTimeOut){
		if (waitTimeOut>0) {
			this.waitTimeOut = waitTimeOut;
		}
		return this;
	}

}
