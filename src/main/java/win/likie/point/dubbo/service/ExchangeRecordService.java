package win.likie.point.dubbo.service;

import win.likie.point.entity.ClientInfo;
import win.likie.point.entity.ExchangeRecord;

import java.util.HashMap;
import java.util.List;

/**
 * Created by WuHuahui on 2017/7/24.
 */
public interface ExchangeRecordService {

	/**
	 * 查询表中的现有客户信息
	 * @param queryMap
	 * @return
	 */
	List<ExchangeRecord> selectExchangeRecord(HashMap<String,String> queryMap);

	/**
	 * 根据客户号码查询对应的客户兑换记录
	 * @param clientMobile
	 * @return
	 */
	ExchangeRecord selectExchangeRecordByMobile(String clientMobile);

	/**
	 * 增加客户信息
	 * @param queryMap
	 */
	void addExchangeRecord(HashMap<String,String> queryMap, ClientInfo clientInfo);

	/**
	 * 根据兑换记录号来查询兑换记录
	 * @param expensesRecord
	 * @return
	 */
	ExchangeRecord selectByPrimaryKey(Integer expensesRecord);

	int updateByPrimaryKey(ExchangeRecord record);

}
