package win.likie.point.dubbo.service.impl;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import win.likie.point.BaseTest;
import win.likie.point.dubbo.service.ClientInfoService;
import win.likie.point.entity.ClientInfo;

import javax.annotation.Resource;

/**
 * Created by WuHuahui on 2017/7/24.
 */
@Rollback
public class TestServiceImplTest extends BaseTest{

	@Resource
	private ClientInfoService clientInfoService;

	@Test
	public void test1() throws Exception {
		clientInfoService.insertClientInfo(new ClientInfo());
	}

}