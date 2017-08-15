package win.likie.point.entity;

import java.util.Date;

public class SmsLog {
    private Integer smsLogId;

    private String smsPhone;

    private Integer smsStatus;

    private Date smsSendTime;

    public Integer getSmsLogId() {
        return smsLogId;
    }

    public void setSmsLogId(Integer smsLogId) {
        this.smsLogId = smsLogId;
    }

    public String getSmsPhone() {
        return smsPhone;
    }

    public void setSmsPhone(String smsPhone) {
        this.smsPhone = smsPhone == null ? null : smsPhone.trim();
    }

    public Integer getSmsStatus() {
        return smsStatus;
    }

    public void setSmsStatus(Integer smsStatus) {
        this.smsStatus = smsStatus;
    }

    public Date getSmsSendTime() {
        return smsSendTime;
    }

    public void setSmsSendTime(Date smsSendTime) {
        this.smsSendTime = smsSendTime;
    }
}