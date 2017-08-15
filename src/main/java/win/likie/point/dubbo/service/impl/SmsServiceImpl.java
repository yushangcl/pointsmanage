package win.likie.point.dubbo.service.impl;

import org.springframework.stereotype.Service;
import win.likie.point.api.AliyunApi;
import win.likie.point.dubbo.service.SmsService;
import win.likie.point.entity.SmsLog;
import win.likie.point.mapper.SmsLogMapper;
import win.likie.point.utils.StringUtils;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class SmsServiceImpl implements SmsService {
    private static final Integer STATUS_FAlD = 1;

    @Resource
    private SmsLogMapper smsLogMapper;

    @Override
    public Integer sendSms(String phoneNum, String date, String iCode, String sCode) {
        if (StringUtils.isEmpty(phoneNum) || StringUtils.isEmpty(date) || StringUtils.isEmpty(iCode) || StringUtils.isEmpty(sCode)) {
            return STATUS_FAlD; //发送失败
        }

        //发送短信
        Integer status = AliyunApi.sendVerificationCode(phoneNum, date, iCode, sCode);

        //记录日记
        SmsLog smsLog = new SmsLog();
        smsLog.setSmsPhone(phoneNum);
        smsLog.setSmsStatus(status);
        smsLog.setSmsSendTime(new Date());
        smsLogMapper.insert(smsLog);

        return status;
    }
}
