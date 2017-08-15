package win.likie.point.api;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import win.likie.point.constant.Constants;
import win.likie.point.utils.HttpUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 阿里云api接口
 * Created by Wuhuahui on 2016/12/15.
 */

public class AliyunApi {

    private static final String methodGet = "GET";
    private static final String methodPost = "POST";

    private static final String appCode = Constants.ALIYUN_APPKEY;

    /**
     * 发送短信验证码接口
     *
     * @param SignName     签名名称
     * @param TemplateCode 模板CODE
     * @param phoneNum     目标手机号,多条记录可以英文逗号分隔
     * @param code         验证码
     * @param name         用户名
     * @return
     */
    public static int sendVerificationCode(String SignName, String TemplateCode, String phoneNum, String code, String name) {
        final String host = "http://sms.market.alicloudapi.com";
        final String path = "/singleSendSms";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appCode);
        Map<String, String> querys = new HashMap<String, String>();
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("name", name);
        jsonObj.put("msg", code);
        querys.put("ParamString", jsonObj.toJSONString());
        querys.put("RecNum", phoneNum);
        querys.put("SignName", SignName);
        querys.put("TemplateCode", TemplateCode);
        try {
            HttpResponse response = HttpUtils.doGet(host, path, methodGet, headers, querys);
            System.out.println(response);
        } catch (Exception e) {
            System.out.println("发送失败：" + e);
            return 1;
        }
        return 0;
    }


    private static JSONObject request(String host, String path, String methodGet, Map<String, String> headers, Map<String, String> querys) {
        try {
            HttpResponse response = HttpUtils.doGet(host, path, methodGet, headers, querys);
            //获取response的body
            return JSONObject.parseObject(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }

    public static void main(String[] args) {
        sendVerificationCode("懒人科技", "SMS_34305396", "18368093869", "54385", "吴华辉");
    }
}
