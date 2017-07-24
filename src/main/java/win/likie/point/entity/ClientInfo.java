package win.likie.point.entity;

import java.util.Date;

public class ClientInfo extends ClientInfoKey {
    private String clientName; //客户姓名

    private Integer purchasedPoints; //已购积分

    private Integer convertedPoints; //已换积分

    private Integer remainingPoints; //剩余积分

    private Date createTime;

    private Date updateTime;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName == null ? null : clientName.trim();
    }

    public Integer getPurchasedPoints() {
        return purchasedPoints;
    }

    public void setPurchasedPoints(Integer purchasedPoints) {
        this.purchasedPoints = purchasedPoints;
    }

    public Integer getConvertedPoints() {
        return convertedPoints;
    }

    public void setConvertedPoints(Integer convertedPoints) {
        this.convertedPoints = convertedPoints;
    }

    public Integer getRemainingPoints() {
        return remainingPoints;
    }

    public void setRemainingPoints(Integer remainingPoints) {
        this.remainingPoints = remainingPoints;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}