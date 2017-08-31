package win.likie.point.constant;

import java.util.ResourceBundle;

/**
 * Created by Wuhuahui on 2016/12/13.
 */
public class Constants {
    public static final ResourceBundle rbn = ResourceBundle.getBundle("service-config");

    //appkey
    public final static String ALIYUN_APPKEY = rbn.getString("aliyun.key");
    public final static String SIGN_NAME = rbn.getString("aliyun.sms.sign_name");
    public final static String TEMPLATE_CODE_1 = rbn.getString("aliyun.sms.template_code_1");
    public final static String TEMPLATE_CODE_2 = rbn.getString("aliyun.sms.template_code_2");

}
