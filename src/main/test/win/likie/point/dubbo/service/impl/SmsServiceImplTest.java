package win.likie.point.dubbo.service.impl;

import org.junit.Test;
import win.likie.point.BaseTest;
import win.likie.point.dubbo.service.SmsService;
import win.likie.point.utils.DateUtil;

import javax.annotation.Resource;

import java.util.Date;

import static org.junit.Assert.*;

public class SmsServiceImplTest extends BaseTest {

    @Resource
    private SmsService smsService;

    @Test
    public void sendSms() throws Exception {
//        Integer status = smsService.sendSms("18368093869", DateUtil.format(new Date(), DateUtil.DATE_FORMAT), "100", "10");
//        System.out.println("短信发送：" + (status == 0 ? "成功" : "失败"));
    }

}