/**
 * 
 */
package com.vivado.task;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.vivado.entity.ServerModel;
import com.vivado.openstack.OpenstackHandler;

/**
 * 虚拟机任务：虚拟机存在规定时间后系统自动回收
 * @author 德龙
 *
 */

@Component
public class ServerTask {

	@Value("${server.invalid.time}")
	private String invalidTime;
	
	@Resource
	private OpenstackHandler openstackHandler;
	
	private static Logger logger = LoggerFactory.getLogger(ServerTask.class);
	
	@Scheduled(fixedRate=60000)
	public void check() {
		logger.info("开始查找过期虚拟机...");
		List<ServerModel> smList = openstackHandler.getAll();
		for (int i = 0; i < smList.size(); i++) {
			ServerModel sm = smList.get(i);
			long curTime = System.currentTimeMillis();
			Long createTime = sm.getCreateTime();
			if (createTime == null) {
				createTime = System.currentTimeMillis();
			}
			if (curTime - createTime > Integer.parseInt(invalidTime) * 60 * 60 * 1000) {
				deleteServer(sm.getInstanceId());
				sleep();
			}
		}
	}

	private void sleep() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void deleteServer(String instanceId) {
		openstackHandler.delete(instanceId);
	}
}
