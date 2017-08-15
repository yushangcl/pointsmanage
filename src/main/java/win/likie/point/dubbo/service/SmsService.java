package win.likie.point.dubbo.service;

public interface SmsService {

    /**
     * 发送短信
     * @param phoneNum 手机号码
     * @param date 时间
     * @param iCode 兑换积分
     * @param sCode 剩余积分
     * @return 发送状态
     * @throws Exception
     */
    Integer sendSms(String phoneNum, String date, String iCode, String sCode);
}
