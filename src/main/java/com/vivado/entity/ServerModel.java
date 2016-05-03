/**
 * 
 */
package com.vivado.entity;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 虚拟主机实体类
 * @author 德龙
 *
 */
public class ServerModel implements Serializable {

	private static final long serialVersionUID = 4586840409158213038L;

	/**虚拟主机名称*/
    private String serverName;

    /**虚拟机实例ID*/
    private String instanceId;

    /**虚拟机实例名称*/
    private String instanceName;

    /**floating-ip*/
    private String floatingIp;

    /**fixed-ip*/
    private String fixedIp;

    /**虚拟机状态*/
    private String serverState;

    /**虚拟机模板ID*/
    private String imageId;

    /**虚拟机规格ID*/
    private String flavorId;

    /**所在物理机*/
    private String hypervisorName;

    private String taskState;

    private String availabilityZone;

    private String created;

    private String security_groups;

    private List<String> volumesAttached;

    private String serverMeta;
    
    private String userId;
    
    private Long createTime;

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public String getInstanceName() {
		return instanceName;
	}

	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}

	public String getFloatingIp() {
		return floatingIp;
	}

	public void setFloatingIp(String floatingIp) {
		this.floatingIp = floatingIp;
	}

	public String getFixedIp() {
		return fixedIp;
	}

	public void setFixedIp(String fixedIp) {
		this.fixedIp = fixedIp;
	}

	public String getServerState() {
		return serverState;
	}

	public void setServerState(String serverState) {
		this.serverState = serverState;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getFlavorId() {
		return flavorId;
	}

	public void setFlavorId(String flavorId) {
		this.flavorId = flavorId;
	}

	public String getHypervisorName() {
		return hypervisorName;
	}

	public void setHypervisorName(String hypervisorName) {
		this.hypervisorName = hypervisorName;
	}

	public String getTaskState() {
		return taskState;
	}

	public void setTaskState(String taskState) {
		this.taskState = taskState;
	}

	public String getAvailabilityZone() {
		return availabilityZone;
	}

	public void setAvailabilityZone(String availabilityZone) {
		this.availabilityZone = availabilityZone;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getSecurity_groups() {
		return security_groups;
	}

	public void setSecurity_groups(String security_groups) {
		this.security_groups = security_groups;
	}

	public List<String> getVolumesAttached() {
		return volumesAttached;
	}

	public void setVolumesAttached(List<String> volumesAttached) {
		this.volumesAttached = volumesAttached;
	}

	public String getServerMeta() {
		return serverMeta;
	}

	public void setServerMeta(String serverMeta) {
		this.serverMeta = serverMeta;
	}
	
	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }
}
