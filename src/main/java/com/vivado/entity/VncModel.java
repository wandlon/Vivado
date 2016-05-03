/**
 * 
 */
package com.vivado.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author 德龙
 *
 */
public class VncModel implements Serializable {

	private static final long serialVersionUID = 7856398212356824909L;

	/**主机IP*/
    private String host;
    
    /**虚拟主机Vnc端口号*/
    private String port;
    
    /**虚拟主机实例id*/
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
