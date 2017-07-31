package win.likie.point.dubbo.service.impl;

import org.junit.Test;
import win.likie.point.BaseTest;
import win.likie.point.dubbo.service.ClientInfoService;
import win.likie.point.dubbo.service.ExchangeRecordService;
import win.likie.point.entity.ClientInfo;
import win.likie.point.entity.ExchangeRecord;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by WuHuahui on 2017/7/24.
 */

public class TestServiceImplTest extends BaseTest{

	@Resource
	private ClientInfoService clientInfoService;

	@Resource
	private ExchangeRecordService exchangeRecordService;

	@Test
	public void test1() throws Exception {
		clientInfoService.insertClientInfo(new ClientInfo());
	}

	@Test
	public void test2() throws Exception{
		/*ClientInfo clientInfo = null;
		clientInfo = clientInfoService.selectClientInfoByMobile("12234567895");
		System.out.println("clientInfo:"+clientInfo);*/


		/*String clientMobile = "";

		HashMap<String,String> queryMap = new HashMap<String, String>();
		queryMap.put("clientMobile", clientMobile);
		List<ExchangeRecord> exchangeRecordList =  exchangeRecordService.selectExchangeRecord(queryMap);
		System.out.println("exchangeRecordList:"+exchangeRecordList.size());*/

		ExchangeRecord exchangeRecord = exchangeRecordService.selectByPrimaryKey(1);
		System.out.println(exchangeRecord.getClientMobile());
	}
}