package com.vivado.util;


import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vivado.exception.HttpExeption;;

/**
 * 处理GET请求
 */
public final class GetRequest extends Request{

	private static final Logger logger = LoggerFactory.getLogger(GetRequest.class);
	
	private HttpGet get = null;
	
	private HttpClient client = null;
	
	private static final String DEFAULTCHARSET = "UTF-8";
	
	private String charset = DEFAULTCHARSET;
	
	public GetRequest(HttpClient client,String url){
		this.client = client;
		get = new HttpGet(url);
	}
	
	public GetRequest(HttpClient httpClient,String url,
			String charset) {
		this(httpClient,url);
		this.charset = charset;
	}

	public GetRequest addHeader(String name,Object value){
		if (value==null) {
			return this;
		}
		get.addHeader(name, String.valueOf(value));
		return this;
	}
	public GetRequest addHeaders(Map<String,Object> headers){
		if (headers==null || headers.isEmpty()) {
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

	public String execute() throws HttpExeption {
		return exc(String.class);
	}

	@Override
	public JSONObject executeToJson() throws HttpExeption  {
		return exc(JSONObject.class);
	}

	@Override
	public <T> T executeToObject(Class<T> clazz) throws HttpExeption  {
		return exc(clazz);
	}

	@SuppressWarnings("unchecked")
	private <T> T exc(Class<T> clazz) throws HttpExeption {
		T obj = null;
		String result = null;
		int status = 0;
		long startTime = System.currentTimeMillis();
		try {
			get.setConfig(getRequestConfig());
			HttpResponse response = client.execute(get);
			HttpEntity entity = response.getEntity();
			if (entity!=null) {
				try {
					status = response.getStatusLine().getStatusCode();
					result = EntityUtils.toString(entity, charset);
					ContentType ct = ContentType.get(entity);
					if (clazz==null||clazz == String.class){
						if (StringUtils.isNotBlank(result)){
							obj =  (T) result;
						}
					}else if (ContentType.APPLICATION_JSON.getMimeType().equals(ct.getMimeType())) {
						if (clazz == JSONObject.class) {
							if (StringUtils.isNotBlank(result)) {
								obj = (T) JSON.parseObject(result);
							}
						} else {
							if (StringUtils.isNotBlank(result)) {
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
			logger.error("请求失败",e);
			throw new HttpExeption(e);
		}finally {
			long endTime = System.currentTimeMillis();
			if(StringUtils.defaultString(result, "").length() <= 1024){
				logger.info("executeTime={}ms,status={},url={},result={}", endTime - startTime,status,
						get.getURI(),result);
			}else{
				logger.info("executeTime={}ms,status={},url={},result={}", endTime - startTime,status,
						get.getURI(),result.substring(0, 1024));
			}
		}
		return obj;
	}

	public GetRequest setReadTimeOut(int readTimeOut){
		if (readTimeOut>0) {
			this.readTimeOut = readTimeOut;
		}
		return this;
	}
	
	public GetRequest setConnectTimeOut(int connectTimeOut){
		if (connectTimeOut>0) {
			this.connectTimeOut = connectTimeOut;
		}
		return this;
	}

	public GetRequest setWaitTimeOut(int waitTimeOut){
		if (waitTimeOut>0) {
			this.waitTimeOut = waitTimeOut;
		}
		return this;
	}
}
