/**
 * 
 */
package com.vivado.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * ��������
 * @author ����
 *
 */
public class FlavorModel implements Serializable {
	
	private static final long serialVersionUID = 2686763771337454367L;

	/**���Id*/
    private String flavorId;

    /**�������*/
    private String flavorName;

    /**�ڴ��С����λMB*/
    private int memoryMB;

    /**Ӳ��*/
    private int disk;

    /***/
    private int ephemeral;

    /***/
    private String swap;

    /**���⴦��������*/
    private int vcpus;

    /***/
    private double rxtxFactor;

    /**�Ƿ���*/
    private String isPublic;

    /**�Ƿ�����*/
    private String disabled;

	public String getFlavorId() {
		return flavorId;
	}

	public void setFlavorId(String flavorId) {
		this.flavorId = flavorId;
	}

	public String getFlavorName() {
		return flavorName;
	}

	public void setFlavorName(String flavorName) {
		this.flavorName = flavorName;
	}

	public int getMemoryMB() {
		return memoryMB;
	}

	public void setMemoryMB(int memoryMB) {
		this.memoryMB = memoryMB;
	}

	public int getDisk() {
		return disk;
	}

	public void setDisk(int disk) {
		this.disk = disk;
	}

	public int getEphemeral() {
		return ephemeral;
	}

	public void setEphemeral(int ephemeral) {
		this.ephemeral = ephemeral;
	}

	public String getSwap() {
		return swap;
	}

	public void setSwap(String swap) {
		this.swap = swap;
	}

	public int getVcpus() {
		return vcpus;
	}

	public void setVcpus(int vcpus) {
		this.vcpus = vcpus;
	}

	public double getRxtxFactor() {
		return rxtxFactor;
	}

	public void setRxtxFactor(double rxtxFactor) {
		this.rxtxFactor = rxtxFactor;
	}

	public String getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}
    
	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }
}
