package win.likie.point.formbean;

public class JsonBean {

    private String code = "success";
    private String message = "success";
    private String errorInfo = "success";

    private Object bean;
    private Object subBean;

    public String getCode() {
        return code;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }


    public Object getSubBean() {
        return subBean;
    }

    public void setSubBean(Object subBean) {
        this.subBean = subBean;
    }

    /**
     * 根据失败原因调用该方法
     *
     * @param reason
     */
    public void fail(String reason) {
        this.setCode("fail");
        this.setMessage(reason);
        this.bean = null;
    }

    /**
     * 根据失败原因调用该方法
     *
     * @param reason
     */
    public void error(String reason, String errorInfo) {
        this.setCode("fail");
        this.setMessage(reason);
        this.setErrorInfo(errorInfo);
        this.bean = null;
    }

    /**
     * 根据失败原因调用该方法
     *
     * @param reason
     */
    public void success(String reason) {
        this.setCode("success");
        this.setMessage(reason);
    }

    /**
     * 根据失败原因调用该方法
     *
     * @param
     */
    public void fail4hasLogout() {
        this.setCode("logout");
        this.bean = null;
    }
}
