package win.likie.point.dubbo.service.impl;

import org.springframework.stereotype.Service;
import win.likie.point.dubbo.service.ClientInfoService;
import win.likie.point.entity.ClientInfo;
import win.likie.point.mapper.ClientInfoMapper;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by WuHuahui on 2017/7/24.
 * service层与数据打交道，实现业务逻辑
 */
@Service
public class ClientInfoServiceImpl implements ClientInfoService {

	@Resource
	private ClientInfoMapper clientInfoMapper;

	@Override
	public void insertClientInfo(ClientInfo clientInfo) throws Exception {
		//插入方法
		clientInfo.setClientName("huahui");
		clientInfo.setClientMobile("18368093869");
		clientInfo.setConvertedPoints(12);
		clientInfo.setPurchasedPoints(10);
		clientInfo.setRemainingPoints(2);
		clientInfoMapper.insertSelective(clientInfo);

		//根据map查询结果
		HashMap map = new HashMap();
		map.put("client_name","test");
		List<ClientInfo> clientInfos = clientInfoMapper.getsByMap(map);

		System.out.println(clientInfos);
	}

}
