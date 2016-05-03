/**
 * 
 */
package com.vivado.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author ����
 *
 */
public class VncModel implements Serializable {

	private static final long serialVersionUID = 7856398212356824909L;

	/**����IP*/
    private String host;
    
    /**��������Vnc�˿ں�*/
    private String port;
    
    /**��������ʵ��id*/
    private String instanceId;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
    
	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }
}
