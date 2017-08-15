package win.likie.point.api;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import win.likie.point.constant.Constants;
import win.likie.point.utils.DateUtil;
import win.likie.point.utils.HttpUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 阿里云api接口
 * Created by Wuhuahui on 2016/12/15.
 */

public class AliyunApi {

	private static final String methodGet = "GET";
	private static final String methodPost = "POST";

	//appKey
	private static final String appCode = Constants.ALIYUN_APPKEY;
	//签名
	private static final String signName = Constants.SIGN_NAME;
	//模板
	private static final String templateCode = Constants.TEMPLATE_CODE;

	/**
	 * 发送短信验证码接口
	 *
	 * @return
	 */
	public static int sendVerificationCode(String phoneNum, String date, String iCode, String sCode) {
		final String host = "http://sms.market.alicloudapi.com";
		final String path = "/singleSendSms";
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "APPCODE " + appCode);

		Map<String, String> querys = new HashMap<String, String>();
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("date", date);
		jsonObj.put("icode", iCode);
		jsonObj.put("scode", sCode);

		querys.put("ParamString", jsonObj.toJSONString());
		querys.put("RecNum", phoneNum);
		querys.put("SignName", signName);
		querys.put("TemplateCode", templateCode);
		try {
			HttpResponse response = HttpUtils.doGet(host, path, methodGet, headers, querys);
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
//		sendVerificationCode("18368093869", DateUtil.format(new Date(), DateUtil.DATE_FORMAT), "100","10");
	}
}
