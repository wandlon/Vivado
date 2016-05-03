/**
 * 
 */
package com.vivado.openstack;

import java.util.List;

import com.vivado.entity.FlavorModel;
import com.vivado.entity.ImageModel;
import com.vivado.entity.ServerModel;
import com.vivado.entity.VncModel;

/**
 * ��������������
 * @author ����
 *
 */
public interface OpenstackHandler {
	
	/**
	 * ���������ʵ��ID��ȡ�������Ϣ
	 * @param instanceId �����ʵ��ID
	 * @return �������Ϣ
	 */
	ServerModel getByInstanceId(String instanceId);
	
	/**
	 * �����û�ID��ȡ�������Ϣ
	 * @param userId �û�ID
	 * @return �������Ϣ
	 */
	List<ServerModel> getByUserId(String userId);
	
	/**
	 * ��ȡȫ��������б�
	 * @return ȫ���������Ϣ
	 */
	List<ServerModel> getAll();
	
	/**
	 * ���������
	 * @param serverName ���������
	 * @param flavorId ��������ID
	 * @param imageId �����ģ��ID
	 * @param userId �û�ID
	 * @return true�������ɹ���false������ʧ��
	 */
	Boolean boot(String serverName, String flavorId, String imageId, String userId);
	
	/**
	 * ɾ�������
	 * @param instanceId �����ʵ��ID
	 * @return true��ɾ���ɹ���false��ɾ��ʧ��
	 */
	Boolean delete(String instanceId);
	
	/**
	 * ��ȡ�����vnc��Ϣ
	 * @param instanceId �����ʵ��ID
	 * @return �����vnc��Ϣ
	 */
	VncModel getVnc(String instanceId);
	
	/**
	 * ��ȡ��������������Ϣ
	 * @return ����������Ϣ
	 */
	List<FlavorModel> getFlavors();
	
	/**
	 * ��ȡ���������ģ����Ϣ
	 * @return �����ģ����Ϣ
	 */
	List<ImageModel> getImages();
}
