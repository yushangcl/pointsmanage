package win.likie.point.action;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import win.likie.point.dubbo.service.ExpensesRecordService;
import win.likie.point.entity.ExchangeRecord;
import win.likie.point.entity.ExpensesRecord;
import win.likie.point.formbean.JsonBean;
import win.likie.point.formbean.Page;
import win.likie.point.utils.SysParamUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by huahui.wu on 2017/8/1.
 */
@Controller
@RequestMapping(value = "/expenses")
public class ExpensesRecordAction extends BaseAction{
    @Resource
    private ExpensesRecordService expensesRecordService;

    @RequestMapping(value = "/index")
    public ModelAndView Index(HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/manage/expenses_index");
        return mav;

    }

    /**
     * 查询客户信息列表页面
     *
     * @param
     * @param
     */
    @RequestMapping(value = "/list")
    public @ResponseBody
    JsonBean list(
            @RequestParam(value = "clientMobile", defaultValue = "") String clientMobile,
            @RequestParam(value = "page", defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = SysParamUtil.PAGE_SIZE) int pageSize
    ) {
        JsonBean bean = new JsonBean();
        Integer totalCount = null;
        Integer toatlPages = 1;

        HashMap<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("clientMobile", clientMobile);

        List<ExpensesRecord> exchangeRecordList = expensesRecordService.selectExpensesRecord(clientMobile);
        List<ExpensesRecord> pageExchangeRecord = new ArrayList<ExpensesRecord>();
        Page<ExpensesRecord> result = new Page<ExpensesRecord>();

        totalCount = exchangeRecordList.size();
        //娴熟数据总条数大于每页显示数的时候,返回显示数据为当前
        //每页显示数据的最大值
        if (totalCount > pageSize) {
            int indexNum = pageSize * pageNumber; //每次截取数据的开始下标
            int lastNum = pageSize * (pageNumber + 1);//每次截取数据的最后一位数据的下标
            toatlPages = (totalCount / pageSize) + 1;
            for (int i = indexNum; i < lastNum && i < totalCount; i++) {
                pageExchangeRecord.add(exchangeRecordList.get(i));
            }
        } else {
            pageExchangeRecord.addAll(exchangeRecordList);
        }
        result.setPageSize(pageSize);//每页数量
        result.setIndexPage(pageNumber);
        result.setTotalPages(toatlPages);
        result.setTotalCount(totalCount);//显示数据总数
        result.setContent(pageExchangeRecord);
        bean.setBean(result);

        return bean;
    }

    @RequestMapping(value = "/add")
    public ModelAndView addIndex(HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException {
        System.out.println("add");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/manage/expensesrecord_add");
        return mav;

    }

    @RequestMapping(value = "/detailIndex")
    public ModelAndView detailIndex(@RequestParam(value = "expensesRecords", defaultValue = "") String expensesRecordsStr,
                                    HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException {
        ModelAndView mav = new ModelAndView();
        Integer expensesRecords = null;

        ExpensesRecord  expensesRecord = null;
        if (!"".equals(expensesRecordsStr)) {
            expensesRecords = Integer.valueOf(expensesRecordsStr);
//            exchangeRecord = expensesRecordService.selectByPrimaryKey(expensesRecords);
        }
        mav.addObject("expensesRecord", expensesRecord);
        mav.setViewName("/manage/expenses_add");
        return mav;

    }

}
