package win.likie.point.dubbo.service;

import win.likie.point.entity.ClientInfo;

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
}
