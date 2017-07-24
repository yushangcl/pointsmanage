package win.likie.point.entity;

public class ClientInfoKey {
    private Integer clientId; //客户编号

    private String clientMobile; //客户电话

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getClientMobile() {
        return clientMobile;
    }

    public void setClientMobile(String clientMobile) {
        this.clientMobile = clientMobile == null ? null : clientMobile.trim();
    }
}