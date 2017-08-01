package win.likie.point.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import win.likie.point.dubbo.service.ExpensesRecordService;

import javax.annotation.Resource;

/**
 * Created by huahui.wu on 2017/8/1.
 */
@Controller
@RequestMapping(value = "/expenses")
public class ExpensesRecordAction extends BaseAction{
    @Resource
    private ExpensesRecordService expensesRecordService;

}
