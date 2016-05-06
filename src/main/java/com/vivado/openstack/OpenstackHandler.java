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
 * 虚拟主机操作类
 * @author 德龙
 *
 */
public interface OpenstackHandler {
	
	/**
	 * 根据虚拟机实例ID获取虚拟机信息
	 * @param instanceId 虚拟机实例ID
	 * @return 虚拟机信息
	 */
	ServerModel getByInstanceId(String instanceId);
	
	/**
	 * 根据用户ID获取虚拟机信息
	 * @param userId 用户ID
	 * @return 虚拟机信息
	 */
	List<ServerModel> getByUserId(String userId);
	
	/**
	 * 获取全部虚拟机列表
	 * @return 全部虚拟机信息
	 */
	List<ServerModel> getAll();
	
	/**
	 * 创建虚拟机
	 * @param serverName 虚拟机名称
	 * @param flavorId 虚拟机规格ID
	 * @param imageId 虚拟机模板ID
	 * @param userId 用户ID
	 * @return true：创建成功；false：创建失败
	 */
	Boolean boot(String serverName, String flavorId, String imageId, String userId);
	
	/**
	 * 删除虚拟机
	 * @param instanceId 虚拟机实例ID
	 * @return true：删除成功；false：删除失败
	 */
	Boolean delete(String instanceId);
	
	/**
	 * 开启虚拟机
	 * @param instanceId 虚拟机实例ID
	 * @return true：开启成功；false：开启失败
	 */
	Boolean start(String instanceId);
	
	/**
	 * 恢复虚拟机
	 * @param instanceId 虚拟机实例ID
	 * @return true：恢复成功；false：恢复失败
	 */
	Boolean resume(String instanceId);
	
	/**
	 * 获取虚拟机vnc信息
	 * @param instanceId 虚拟机实例ID
	 * @return 虚拟机vnc信息
	 */
	VncModel getVnc(String instanceId);
	
	/**
	 * 获取所有虚拟机规格信息
	 * @return 虚拟机规格信息
	 */
	List<FlavorModel> getFlavors();
	
	/**
	 * 获取所有虚拟机模板信息
	 * @return 虚拟机模板信息
	 */
	List<ImageModel> getImages();
}
