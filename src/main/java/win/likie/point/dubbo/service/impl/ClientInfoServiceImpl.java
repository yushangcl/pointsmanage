package win.likie.point.dubbo.service.impl;

import org.springframework.stereotype.Service;
import win.likie.point.dubbo.service.ClientInfoService;
import win.likie.point.entity.ClientInfo;
import win.likie.point.entity.ExchangeRecord;
import win.likie.point.entity.ExpensesRecord;
import win.likie.point.mapper.ClientInfoMapper;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
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
		clientInfo.setClientName("test");
		clientInfo.setClientMobile("12234567895");
		clientInfo.setConvertedPoints(12);
		clientInfo.setPurchasedPoints(10);
		clientInfo.setRemainingPoints(2);
		clientInfoMapper.insertSelective(clientInfo);

	}

	@Override
	public void addClientInfo(HashMap<String, String> queryMap) {
		String clientMobile = queryMap.get("clientMobile");
		String clientName = queryMap.get("clientName");

		ClientInfo clientInfo = new ClientInfo();
		clientInfo.setClientMobile(clientMobile);
		clientInfo.setClientName(clientName);
		clientInfo.setConvertedPoints(0);
		clientInfo.setPurchasedPoints(0);
		clientInfo.setRemainingPoints(0);
		clientInfo.setCreateTime(new Date());
		clientInfo.setUpdateTime(new Date());

		clientInfoMapper.insert(clientInfo);
	}

	/**
	 * 查询客户信息表中所有客户信息
	 * @param queryMap
	 * @return
	 */
	@Override
	public List<ClientInfo> selectClientInfo(HashMap<String, String> queryMap) {

		String clientMobile = queryMap.get("clientMobile");
		String clientName = queryMap.get("clientName");

		List<ClientInfo> clientInfoList = new ArrayList<ClientInfo>();

		if(("").equals(clientMobile)){
			clientMobile = null;
		}else {
			clientMobile = "%" + clientMobile + "%";//用于模糊查询
		}

		if(("").equals(clientName)){
			clientName = null;
		}else {
			clientName = "%" + clientName + "%";//用于模糊查询
		}

		clientInfoList = clientInfoMapper.selectAllClientInfo(clientMobile,clientName);
		return clientInfoList;
	}

	/**
	 * 根据客户号码查询对应的客户信息
	 * @param clientMobile
	 * @return
	 */
	@Override
	public ClientInfo selectClientInfoByMobile(String clientMobile) {
		ClientInfo clientInfo = null;
		clientInfo = clientInfoMapper.selectByClientMobile(clientMobile);
		return clientInfo;
	}

	@Override
	public int updateByPrimaryKeySelective(ClientInfo record) {
		return clientInfoMapper.updateByPrimaryKeySelective(record);
	}


}
