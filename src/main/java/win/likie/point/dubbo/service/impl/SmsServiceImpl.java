package win.likie.point.dubbo.service.impl;

import org.springframework.stereotype.Service;
import win.likie.point.api.AliyunApi;
import win.likie.point.constant.Constants;
import win.likie.point.dubbo.service.SmsService;
import win.likie.point.entity.SmsLog;
import win.likie.point.mapper.SmsLogMapper;
import win.likie.point.utils.StringUtils;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class SmsServiceImpl implements SmsService {
    private static final Integer STATUS_FAlD = 1;
    private static final String templateCode_1 = Constants.TEMPLATE_CODE_1;
    private static final String templateCode_2 = Constants.TEMPLATE_CODE_2;


    @Resource
    private SmsLogMapper smsLogMapper;

    @Override
    public Integer sendSms(String phoneNum, String date, String iCode, String sCode, Integer code) {
        if (StringUtils.isEmpty(phoneNum) || StringUtils.isEmpty(date) || StringUtils.isEmpty(iCode) || StringUtils.isEmpty(sCode)) {
            return STATUS_FAlD; //发送失败
        }

        //发送短信
        Integer status = AliyunApi.sendVerificationCode(phoneNum, date, iCode, sCode, code == 1 ? templateCode_1 : templateCode_2);

        //记录日记
        SmsLog smsLog = new SmsLog();
        smsLog.setSmsPhone(phoneNum);
        smsLog.setSmsStatus(status);
        smsLog.setSmsSendTime(new Date());
        smsLogMapper.insert(smsLog);

        return status;
    }
}
