package win.likie.point.entity;

import java.util.Date;

/**
 * 客户信息
 */
public class ClientInfo {
	private Integer clientId; // 客户编号

	private String clientName; //客户姓名

	private String clientMobile; //客户电话

	private Integer purchasedPoints; //已购积分

	private Integer convertedPoints; //已换积分

	private Integer remainingPoints; //已换积分

	private Date createTime; //创建时间

	private Date updateTime; //更新时间

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName == null ? null : clientName.trim();
	}

	public String getClientMobile() {
		return clientMobile;
	}

	public void setClientMobile(String clientMobile) {
		this.clientMobile = clientMobile == null ? null : clientMobile.trim();
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

	@Override
	public String toString() {
		return "ClientInfo{" +
				"clientId=" + clientId +
				", clientName='" + clientName + '\'' +
				", clientMobile='" + clientMobile + '\'' +
				", purchasedPoints=" + purchasedPoints +
				", convertedPoints=" + convertedPoints +
				", remainingPoints=" + remainingPoints +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				'}';
	}
}