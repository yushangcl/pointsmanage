package win.likie.point.action;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import win.likie.point.dubbo.service.ClientInfoService;
import win.likie.point.dubbo.service.ExpensesRecordService;
import win.likie.point.dubbo.service.SmsService;
import win.likie.point.entity.ClientInfo;
import win.likie.point.entity.ExpensesRecord;
import win.likie.point.formbean.JsonBean;
import win.likie.point.formbean.Page;
import win.likie.point.utils.DateUtil;
import win.likie.point.utils.RegexUtils;
import win.likie.point.utils.StringUtils;
import win.likie.point.utils.SysParamUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by huahui.wu on 2017/8/1.
 */
@Controller
@RequestMapping(value = "/expensesrecordaction")
public class ExpensesRecordAction extends BaseAction {
    @Resource
    private ExpensesRecordService expensesRecordService;
    @Resource
    private ClientInfoService clientInfoService;
    @Resource
    private SmsService smsService;

    @RequestMapping(value = "/index")
    public ModelAndView Index(HttpServletRequest request) throws IOException {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/manage/expensesrecord_index");
        return mav;

    }

    /**
     * 查询客户消费记录
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
    public ModelAndView addIndex(@RequestParam(value = "clientMobile", defaultValue = "") String clientMobile,
                                 HttpServletRequest request) throws IOException {
        ModelAndView mav = new ModelAndView();
        Date date = new Date();
        String startTime = DateUtil.toDateString(date);
        mav.addObject("startTime",startTime);
        mav.addObject("clientMobile",clientMobile);
        mav.setViewName("/manage/expensesrecord_add");
        return mav;

    }

    //addQuery
    @RequestMapping(value = "/addQuery")
    public @ResponseBody
    JsonBean addQuery(
            @RequestParam(value = "clientMobile", defaultValue = "") String clientMobile,
            HttpServletRequest request,HttpServletResponse response) throws ParseException {

        ClientInfo clientInfo = null;

        JsonBean bean = new JsonBean();
        if(StringUtils.isBlank(clientMobile)){
            bean.fail("手机号码不能为空");
            return bean;
        }else if (!RegexUtils.checkMobile(clientMobile)) {
            bean.fail("手机号码不正确");
            return bean;
        }

        clientInfo = clientInfoService.selectClientInfoByMobile(clientMobile);

        if (clientInfo == null) {
            bean.fail("该客户不存在，请先在客户信息页面增加该客户！");
            return bean;
        }

        return bean;

    }

    @RequestMapping(value = "/detailIndex")
    public ModelAndView detailIndex(@RequestParam(value = "expensesRecords", defaultValue = "") String expensesRecordsStr,
                                    HttpServletRequest request) throws IOException {
        ModelAndView mav = new ModelAndView();
        Integer expensesRecords = null;
        String clientMobile = null;
        String startTime = null;

        ExpensesRecord expensesRecord = null;
        if (!"".equals(expensesRecordsStr)) {
            expensesRecords = Integer.valueOf(expensesRecordsStr);
            expensesRecord = expensesRecordService.selectByPrimaryKey(expensesRecords);
            clientMobile = expensesRecord.getClientMobile();
            startTime = DateUtil.toDateString(expensesRecord.getConsumptionDate());
        }
        mav.addObject("clientMobile", clientMobile);
        mav.addObject("startTime",startTime);
        mav.addObject("expensesRecord", expensesRecord);
        mav.setViewName("/manage/expensesrecord_add");
        return mav;

    }

    /**
     * 客户消费记录保存
     *
     * @throws ParseException
     */
    @Transactional
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody
    JsonBean save(
            @RequestParam(value = "recordNumber", defaultValue = "") String recordNumber,
            @RequestParam(value = "clientMobile", defaultValue = "") String clientMobile,
            @RequestParam(value = "amount", defaultValue = "0") Double amount,
            @RequestParam(value = "consumptionDate", defaultValue = "") String consumptionDate,
            @RequestParam(value = "remarks", defaultValue = "") String remarks,
            @RequestParam(value = "operMode", defaultValue = "") String operMode,
            HttpServletRequest request,
            HttpServletResponse response) throws ParseException {

        ClientInfo clientInfo = null;


        JsonBean bean = new JsonBean();
        HashMap<String, String> queryMap = new HashMap<String, String>();
        if (!RegexUtils.checkMobile(clientMobile)) {
            bean.fail("手机号码不正确");
            return bean;
        }

        clientInfo = clientInfoService.selectClientInfoByMobile(clientMobile);

        if (clientInfo == null) {
//                bean.fail("该客户不存在，请先在客户信息页面增加该客户！");
            //新增客户
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("clientMobile", clientMobile);
            map.put("clientName", "");
            clientInfoService.addClientInfo(map);
        }
        if(amount < 0) {
            bean.fail("消费金额必须大于1元");
        }
        clientInfo = clientInfoService.selectClientInfoByMobile(clientMobile);

        Integer purchased = clientInfo.getPurchasedPoints(); //已购积分
        Integer remaining = clientInfo.getRemainingPoints(); //剩余积分
        Date date = null;
        if (!consumptionDate.isEmpty()) {
            date = DateUtil.parse(consumptionDate, DateUtil.DATE_FORMAT);
        } else {
            date = new Date();
        }
        if ("add".equals(operMode)) {

            //添加消费金额记录
            ExpensesRecord expensesRecord = new ExpensesRecord();
            expensesRecord.setAmount(amount);
            expensesRecord.setClientMobile(clientMobile);

            expensesRecord.setConsumptionDate(date);
            expensesRecord.setRemarks(remarks);
            expensesRecordService.insert(expensesRecord);

            //计算积分信息

            clientInfo.setPurchasedPoints(purchased + amount.intValue());
            clientInfo.setRemainingPoints(remaining + amount.intValue());
            clientInfoService.updateByPrimaryKeySelective(clientInfo);


        }


        if ("update".equals(operMode)) {
            //更新消费金额记录
            ExpensesRecord expensesRecord = expensesRecordService.selectByPrimaryKey(Integer.valueOf(recordNumber));
            Double amountOld = expensesRecord.getAmount();
            expensesRecord.setAmount(amount);
            expensesRecord.setConsumptionDate(date);
            expensesRecord.setRemarks(remarks);
            expensesRecordService.updateByExpensesRecordId(expensesRecord);
            // 更新积分信息 t
            if (purchased - amount.intValue() + amountOld.intValue() < 0) {
                bean.fail("更新失败，积分不足");
                return bean;
            }
            clientInfo.setPurchasedPoints(purchased + amount.intValue() - amountOld.intValue());
            clientInfo.setRemainingPoints(remaining + amount.intValue() - amountOld.intValue());
            clientInfoService.updateByPrimaryKeySelective(clientInfo);
        }
        //发送短信
        clientInfo = clientInfoService.selectClientInfoByMobile(clientMobile);
        smsService.sendSms(clientInfo.getClientMobile(), DateUtil.format(new Date(),
                DateUtil.DATE_FORMAT), StringUtils.toString(amount),
                StringUtils.toString(clientInfo.getRemainingPoints()), 2);
        return bean;

    }

    /**
     * 删除用户消费金额信息
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/delete")
    public @ResponseBody JsonBean deleteExpenses() throws IOException {
        JsonBean bean = new JsonBean();
        // 删除对记录&&删除总共积分和消费积分
        return bean;

    }

}
