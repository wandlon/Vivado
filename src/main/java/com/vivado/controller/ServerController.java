/**
 * 
 */
package com.vivado.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vivado.entity.FlavorModel;
import com.vivado.entity.ImageModel;
import com.vivado.entity.ServerModel;
import com.vivado.entity.VncModel;
import com.vivado.openstack.OpenstackHandler;

/**
 * 虚拟机相关
 * @author 德龙
 *
 */

@Controller
@RequestMapping("/server")
public class ServerController {

	private static final long TIMEOUT = 30 * 1000;
	
	@Resource
	private OpenstackHandler openstackHandler;
	
	@RequestMapping(value = "/list", method=RequestMethod.GET)
	public String list(String userId, Model model){
		List<ServerModel> smList = openstackHandler.getByUserId(userId);
		model.addAttribute("smList", smList);
		return "server/server";
	}
	
	@RequestMapping(value = "/apply", method=RequestMethod.GET)
	public String list(Model model){
//		List<FlavorModel> fmList = openstackHandler.getFlavors();
//		List<ImageModel> imList = openstackHandler.getImages();
//		model.addAttribute("fmList", fmList);
//		model.addAttribute("imList", imList);
		return "server/server_add";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Object delete(String instanceId) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (StringUtils.isBlank(instanceId)) {
			result.put("msg", "无效的实例ID");
			return result;
		}
		Boolean ret = openstackHandler.delete(instanceId);
		if (ret) {
			result.put("msg", "success");
		} else {
			result.put("msg", "删除虚拟机失败");
		}
		return result;
	}
	
	@RequestMapping(value = "/boot", method = RequestMethod.POST)
	@ResponseBody
	public Object boot(String serverName, String flavorId, String imageId, String userId) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (StringUtils.isBlank(serverName)) {
			result.put("msg", "无效的虚拟机名称");
			return result;
		}
		if (StringUtils.isBlank(flavorId)) {
			result.put("msg", "无效的虚拟机规格ID");
			return result;
		}
		if (StringUtils.isBlank(imageId)) {
			result.put("msg", "无效的虚拟机模板ID");
			return result;
		}
		if (StringUtils.isBlank(userId)) {
			result.put("msg", "无效的用户ID");
			return result;
		}
		Boolean ret = openstackHandler.boot(serverName, flavorId, imageId, userId);
		if (ret) {
			result.put("msg", "success");
		} else {
			result.put("msg", "创建虚拟机失败");
		}
		return result;
	}
	
	@RequestMapping(value = "/connect", method = RequestMethod.POST)
	@ResponseBody
	public Object connect(String instanceId) {
		Map<String, Object> result = new HashMap<String, Object>();
		ServerModel sm = openstackHandler.getByInstanceId(instanceId);
		if (sm == null) {
			result.put("msg", "虚拟机不存在");
			return result;
		}
		String state = sm.getServerState();
		if ("ERROR".equals(state)) {
			result.put("msg", "虚拟机错误");
			return result;
		}
		if ("SHUTOFF".equals(state)) {
			openstackHandler.start(instanceId);
		}
		if ("SUSPEND".equals(state)) {
			openstackHandler.resume(instanceId);
		}
		long start = System.currentTimeMillis();
		Boolean flag = false;
		while (true) {
			long end = System.currentTimeMillis();
			if (end - start >= TIMEOUT) {
				result.put("msg", "连接虚拟机超时");
				break;
			}
			if ("ACTIVE".equals(openstackHandler.getByInstanceId(instanceId).getServerState())) {
				flag = true;
				break;
			}
			sleep();
		}
		if (flag) {
			VncModel vm = openstackHandler.getVnc(instanceId);
			result.put("msg", "success");
			result.put("vnc", vm);
		}
		return result;
	}

	private void sleep() {
		try {
			Thread.sleep(1000 * 2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

