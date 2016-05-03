/**
 * 
 */
package com.vivado.openstack.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.vega.openstack.Connect;
import com.vega.openstack.nova.Flavor;
import com.vega.openstack.nova.Image;
import com.vega.openstack.nova.Server;
import com.vega.openstack.nova.Vnc;
import com.vivado.entity.FlavorModel;
import com.vivado.entity.ImageModel;
import com.vivado.entity.ServerModel;
import com.vivado.entity.VncModel;
import com.vivado.openstack.OpenstackHandler;

/**
 * ������������ʵ��
 * @author ����
 *
 */

@Component
public class OpenstackHandlerImpl implements OpenstackHandler {
	
	@Value("${openstack.host}")
	private String host;
	
	@Value("${openstack.tenant.name}")
	private String tenantName;
	
	@Value("${openstack.user.name}")
	private String userName;
	
	@Value("${openstack.user.password}")
	private String userPasswd;

	private static Logger logger = LoggerFactory.getLogger(OpenstackHandlerImpl.class);

	@Override
	public ServerModel getByInstanceId(String instanceId) {
		logger.info("��ȡ�������Ϣ��instanceId={}", instanceId);
		try {
			Connect conn = getConnect();
			if (conn == null) {
				return null;
			}
			Server s = conn.getServer().getServerByInstanceId(instanceId);
			return pareseServer(s);
		} catch (Exception e) {
			logger.error("��ȡ�������Ϣʧ��", e);
		}
		return null;
	}

	private ServerModel pareseServer(Server s) {
		if (s == null) {
			return null;
		}
		ServerModel sm = new ServerModel();
		sm.setAvailabilityZone(s.getAvailabilityZone());
		sm.setCreated(s.getCreated());
		sm.setFixedIp(s.getFixedIp());
		sm.setFloatingIp(s.getFloatingIp());
		sm.setHypervisorName(s.getHypervisorName());
		sm.setImageId(s.getImageId());
		sm.setInstanceId(s.getInstanceId());
		sm.setInstanceName(s.getInstanceName());
		sm.setSecurity_groups(s.getSecurity_groups());
		sm.setServerMeta(s.getServerMeta());
		sm.setUserId(parseUserId(s.getServerMeta()));
		sm.setCreateTime(parseCreateTime(s.getServerMeta()));
		sm.setServerName(s.getServerName());
		sm.setServerState(s.getServerState());
		sm.setTaskState(s.getTaskState());
		return sm;
	}

	private Long parseCreateTime(String serverMeta) {
		if (StringUtils.isBlank(serverMeta)) {
			return null;
		}
		JSONObject meta = JSONObject.parseObject(serverMeta);
		if (meta.containsKey("create_time")) {
			return meta.getLong("create_time");
		}
		return null;
	}

	private String parseUserId(String serverMeta) {
		if (StringUtils.isBlank(serverMeta)) {
			return null;
		}
		JSONObject meta = JSONObject.parseObject(serverMeta);
		if (meta.containsKey("user_id")) {
			return meta.getString("user_id");
		}
		return null;
	}

	private Connect getConnect() {
		try {
			return new Connect(host, tenantName, userName, userPasswd);
		} catch (Exception e) {
			logger.error("��ȡopenstack����ʧ��", e);
		}
		return null;
	}

	@Override
	public List<ServerModel> getByUserId(String userId) {
		logger.info("��ȡ�û��������Ϣ��userId={}", userId);
		List<ServerModel> usmList = new ArrayList<>();
		if (StringUtils.isBlank(userId)) {
			return usmList;
		}
		try {
			List<ServerModel> smList = getAll();
			for (int i = 0; i < smList.size(); i++) {
				ServerModel sm = smList.get(i);
				if (userId.equals(sm.getUserId())) {
					usmList.add(sm);
				}
			}
		} catch (Exception e) {
			logger.error(" ��ȡ�û��������Ϣʧ��", e);
		}
		return usmList;
	}

	@Override
	public List<ServerModel> getAll() {
		logger.info("��ȡȫ���������Ϣ");
		List<ServerModel> smList = new ArrayList<ServerModel>();
		try {
			Connect conn = getConnect();
			if (conn == null) {
				return smList;
			}
			List<Server> serverList = conn.getServer().getServerList();
			if (serverList == null) {
				return smList;
			}
			for (int i = 0; i < serverList.size(); i++) {
				Server s = serverList.get(i);
				ServerModel sm = pareseServer(s);
				if (sm != null) {
					smList.add(sm);
				}
			}
		} catch (Exception e) {
			logger.error("��ȡȫ���������Ϣʧ��", e);
		}
		return smList;
	}

	@Override
	public Boolean boot(String serverName, String flavorId, String imageId, String userId) {
		logger.info("�����������serverName={}, flavorId={}, imageId={}, userId={}", serverName, 
				flavorId, imageId, userId);
		Boolean result = false;
		try {
			Connect conn = getConnect();
			if (conn == null) {
				return result;
			}
			String instanceId = conn.getServer().boot(serverName, imageId, flavorId);
			if (StringUtils.isBlank(instanceId)) {
				return result;
			}
			Map<String, String> params = new HashMap<String, String>();
			params.put("user_id", userId);
			params.put("create_time", String.valueOf(System.currentTimeMillis()));
			conn.getServer().serverMeta(instanceId, params);
		} catch (Exception e) {
			logger.error("���������ʧ��", e);
		}
		return result;
	}

	@Override
	public Boolean delete(String instanceId) {
		logger.info("ɾ���������instanceId={}", instanceId);
		Boolean result = false;
		try {
			Connect conn = getConnect();
			if (conn == null) {
				return result;
			}
			String ret = conn.getServer().deleteServer(instanceId);
			if (StringUtils.isBlank(ret)) {
				result = true;
			}
		} catch (Exception e) {
			logger.error("ɾ�������ʧ��", e);
		}
		return result;
	}

	@Override
	public VncModel getVnc(String instanceId) {
		logger.info("��ȡ�����vnc��Ϣ��instanceId={}", instanceId);
		try {
			Connect conn = getConnect();
			if (conn == null) {
				return null;
			}
			Vnc vnc = conn.getServer().getWebSocketVNCByInstanceId(instanceId);
			return parseVnc(vnc);
		} catch (Exception e) {
			logger.error("��ȡ�����vnc��Ϣʧ��, e");
		}
		return null;
	}

	private VncModel parseVnc(Vnc vnc) {
		if (vnc == null) {
			return null;
		}
		VncModel vm = new VncModel();
		vm.setHost(vnc.getHost());
		vm.setInstanceId(vnc.getInstanceId());
		vm.setPort(vnc.getPort());
		return vm;
	}

	@Override
	public List<FlavorModel> getFlavors() {
		logger.info("��ȡ����������Ϣ");
		List<FlavorModel> fmList = new ArrayList<FlavorModel>();
		try {
			Connect conn = getConnect();
			if (conn == null) {
				return fmList;
			}
			List<Flavor> flavorList = conn.getFlavor().getFlavorList();
			if (flavorList == null) {
				return fmList;
			}
			for (int i = 0; i < flavorList.size(); i++) {
				Flavor flavor = flavorList.get(i);
				FlavorModel fm = parseFlavor(flavor);
				if (fm != null) {
					fmList.add(fm);
				}
			}
		} catch (Exception e) {
			logger.error("��ȡ����������Ϣʧ��", e);
		}
		return fmList;
	}

	private FlavorModel parseFlavor(Flavor flavor) {
		if (flavor == null) {
			return null;
		}
		FlavorModel fm = new FlavorModel();
		fm.setDisabled(flavor.getDisabled());
		fm.setDisk(flavor.getDisk());
		fm.setEphemeral(flavor.getEphemeral());
		fm.setFlavorId(flavor.getFlavorId());
		fm.setFlavorName(flavor.getFlavorName());
		fm.setIsPublic(flavor.getIsPublic());
		fm.setMemoryMB(flavor.getMemoryMB());
		fm.setRxtxFactor(flavor.getRxtxFactor());
		fm.setVcpus(flavor.getVcpus());
		return fm;
	}

	@Override
	public List<ImageModel> getImages() {
		logger.info("��ȡ�����ģ����Ϣ");
		List<ImageModel> imList = new ArrayList<ImageModel>();
		try {
			Connect conn = getConnect();
			if (conn == null) {
				return imList;
			}
			List<Image> imageList = conn.getImage().getImageList();
			for (int i = 0; i < imageList.size(); i++) {
				Image image = imageList.get(i);
				ImageModel im = parseImage(image);
				if (im != null) {
					imList.add(im);
				}
			}
		} catch (Exception e) {
			logger.error("��ȡ�����ģ����Ϣʧ��", e);
		}
		return imList;
	}

	private ImageModel parseImage(Image image) {
		if (image == null) {
			return null;
		}
		ImageModel im = new ImageModel();
		im.setImageDesc(image.getImageDesc());
		im.setImageId(image.getImageId());
		im.setImageMeta(image.getImageMeta());
		im.setImageName(image.getImageName());
		im.setImagePath(image.getImagePath());
		im.setImageSize(image.getImageSize());
		im.setImageState(image.getImageState());
		im.setImageType(image.getImageType());
		return im;
	}
	
	
}
