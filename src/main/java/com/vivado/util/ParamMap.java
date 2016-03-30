package com.vivado.util;

import java.util.HashMap;
import java.util.Map;

public class ParamMap<K,V> {

	private Map<K,V> paramMap = null;
	
	
	private ParamMap(){
		paramMap = new HashMap<K,V>();
	}
	
	public static ParamMap createParamMap(){
		return new ParamMap();
	}

	public ParamMap<K,V> addParam(K key,V value){
		paramMap.put(key, value);
		return this;
	}
	
	public  Map<K,V> build(){
		return paramMap;
	}
}
