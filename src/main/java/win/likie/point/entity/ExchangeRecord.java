package win.likie.point.entity;

import java.util.Date;

/**
 * 兑换记录
 */
public class ExchangeRecord {
    private Integer exchangeRecords; //兑换记录号

    private String clientMobile; //客户电话

    private Date exchangeDate; //兑换日期

    private String exchangePoints; //兑换积分

    private String remarks; //备注

    public Integer getExchangeRecords() {
        return exchangeRecords;
    }

    public void setExchangeRecords(Integer exchangeRecords) {
        this.exchangeRecords = exchangeRecords;
    }

    public String getClientMobile() {
        return clientMobile;
    }

    public void setClientMobile(String clientMobile) {
        this.clientMobile = clientMobile == null ? null : clientMobile.trim();
    }

    public Date getExchangeDate() {
        return exchangeDate;
    }

    public void setExchangeDate(Date exchangeDate) {
        this.exchangeDate = exchangeDate;
    }

    public String getExchangePoints() {
        return exchangePoints;
    }

    public void setExchangePoints(String exchangePoints) {
        this.exchangePoints = exchangePoints == null ? null : exchangePoints.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}