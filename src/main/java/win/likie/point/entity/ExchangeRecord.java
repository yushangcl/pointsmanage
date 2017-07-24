package win.likie.point.entity;

import java.util.Date;

public class ExchangeRecord {
    private Integer exchangeRecords;

    private String clientMobile;

    private Date exchangeDate;

    private String exchangePoints;

    private String remarks;

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