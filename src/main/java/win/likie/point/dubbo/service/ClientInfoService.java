package win.likie.point.dubbo.service;

import win.likie.point.entity.ClientInfo;

import java.util.HashMap;
import java.util.List;

/**
 * Created by WuHuahui on 2017/7/24.
 */
public interface ClientInfoService {

	/**
	 * 保存客户信息
	 * @param clientInfo 客户信息
	 * @throws Exception
	 */
	void insertClientInfo(ClientInfo clientInfo) throws Exception;

	/**
	 * 增加客户信息
	 * @param queryMap
	 */
	void addClientInfo(HashMap<String,String> queryMap);

	/**
	 * 查询表中的现有客户信息
	 * @param queryMap
	 * @return
	 */
	List<ClientInfo> selectClientInfo(HashMap<String,String> queryMap);

	/**
	 * 根据客户号码查询对应的客户信息
	 * @param clientMobile
	 * @return
	 */
	ClientInfo selectClientInfoByMobile(String clientMobile);

	int updateByPrimaryKeySelective(ClientInfo record);
}
