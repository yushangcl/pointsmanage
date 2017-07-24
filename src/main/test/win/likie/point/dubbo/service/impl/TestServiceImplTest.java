package win.likie.point.dubbo.service.impl;

import org.junit.Test;
import win.likie.point.BaseTest;
import win.likie.point.dubbo.service.TestService;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by WuHuahui on 2017/7/24.
 */

public class TestServiceImplTest extends BaseTest{

	@Resource
	private TestService testService;
	@Test
	public void test1() throws Exception {
		testService.test();
	}

}