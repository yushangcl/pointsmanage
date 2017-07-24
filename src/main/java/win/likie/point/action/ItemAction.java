package win.likie.point.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import win.likie.point.dubbo.service.ClientInfoService;
import win.likie.point.entity.ClientInfo;

import javax.annotation.Resource;

/**
 * Created by huahui.wu on 2017/7/24.
 * 示例控制器
 */
@Controller
@ResponseBody
@RequestMapping("/api")
public class ItemAction {

	@Resource
	private ClientInfoService clientInfoService;

	@RequestMapping(value = "/login")
	public void login() throws Exception {
		// 测试方法
		clientInfoService.insertClientInfo(new ClientInfo());
		System.out.println("Test Action");
	}
}
