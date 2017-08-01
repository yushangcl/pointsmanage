package win.likie.point.action;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import win.likie.point.dubbo.service.ClientInfoService;
import win.likie.point.dubbo.service.ExchangeRecordService;
import win.likie.point.entity.ClientInfo;
import win.likie.point.entity.ExchangeRecord;
import win.likie.point.formbean.JsonBean;
import win.likie.point.formbean.Page;
import win.likie.point.utils.RegexUtils;
import win.likie.point.utils.StringUtils;
import win.likie.point.utils.SysParamUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 兑换记录
 * Created by 10150 on 2017/7/30.
 */
@Controller
@RequestMapping(value = "/exchangerecordaction")
public class ExchangeRecordAction extends BaseAction {
    @Resource
    private ExchangeRecordService exchangeRecordService;
    @Resource
    private ClientInfoService clientInfoService;


    @RequestMapping(value = "/index")
    public ModelAndView Index(HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/manage/exchangerecord_index");
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
        System.out.println("exchangerecordaction/list");
        JsonBean bean = new JsonBean();
        //clientMobile = "12234567895";
        Integer totalCount = null;
        Integer toatlPages = 1;

        HashMap<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("clientMobile", clientMobile);

        List<ExchangeRecord> exchangeRecordList = exchangeRecordService.selectExchangeRecord(queryMap);
        List<ExchangeRecord> pageExchangeRecord = new ArrayList<ExchangeRecord>();
        Page<ExchangeRecord> result = new Page<ExchangeRecord>();

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
        mav.setViewName("/manage/exchangerecord_add");
        return mav;

    }

    @RequestMapping(value = "/detailIndex")
    public ModelAndView detailIndex(@RequestParam(value = "exchangeRecords", defaultValue = "") String exchangeRecordsStr,
                                    HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException {
        ModelAndView mav = new ModelAndView();
        Integer exchangeRecords = null;

        ExchangeRecord exchangeRecord = null;
        if (!"".equals(exchangeRecordsStr)) {
            exchangeRecords = Integer.valueOf(exchangeRecordsStr);
            exchangeRecord = exchangeRecordService.selectByPrimaryKey(exchangeRecords);
        }
        mav.addObject("exchangeRecord", exchangeRecord);
        mav.setViewName("/manage/exchangerecord_add");
        return mav;

    }


    /**
     * 客户兑换记录保存
     *
     * @throws ParseException
     */
    @Transactional
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody
    JsonBean save(
            @RequestParam(value = "exchangeRecords", defaultValue = "") String exchangeRecordsStr,
            @RequestParam(value = "clientMobile", defaultValue = "") String clientMobile,
            @RequestParam(value = "exchangePoints", defaultValue = "0") Integer exchangePoints,
            @RequestParam(value = "exchangeDate", defaultValue = "") String exchangeDate,
            @RequestParam(value = "remarks", defaultValue = "") String remarks,
            @RequestParam(value = "operMode", defaultValue = "") String operMode,
            HttpServletRequest request,
            HttpServletResponse response) throws ParseException {

        ClientInfo clientInfo = null;

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        JsonBean bean = new JsonBean();
        HashMap<String, String> queryMap = new HashMap<String, String>();
        if (!RegexUtils.checkMobile(clientMobile)) {
            bean.fail("手机号码不正确");
            return bean;
        }

        clientInfo = clientInfoService.selectClientInfoByMobile(clientMobile);


        if ("add".equals(operMode)) {
            if (clientInfo == null) {
                bean.fail("该客户不存在，请先在客户信息页面增加该客户！");
                return bean;
            }

            Integer remainingPoints = clientInfo.getRemainingPoints();
            if (exchangePoints > remainingPoints) { //当兑换积分>剩余积分时，会出错
                bean.fail("该客户积分不够，无法实现兑换，请查清用户剩余积分");
                return bean;
            }

            queryMap.put("clientMobile", clientMobile);
            queryMap.put("exchangePoints", String.valueOf(exchangePoints));
            queryMap.put("exchangeDate", exchangeDate);
            queryMap.put("remarks", remarks);
            exchangeRecordService.addExchangeRecord(queryMap, clientInfo);
        }


        if ("update".equals(operMode)) {
            ExchangeRecord exchangeRecord = null;

            if (!"".equals(exchangeRecordsStr)) {
                exchangeRecord = exchangeRecordService.selectByPrimaryKey(Integer.valueOf(exchangeRecordsStr));
            }

            if (clientInfo == null || exchangeRecord == null) {
                bean.fail("操作失败！");
                return bean;
            }
            Integer remainingPoints = clientInfo.getRemainingPoints();//剩余积分
            Integer convertedPoints = clientInfo.getConvertedPoints();//已换积分
            String exchangePointsStr = exchangeRecord.getExchangePoints();
            Integer exchangePointsOld = Integer.valueOf(exchangePointsStr);//获取编辑之前的记录

            //说明编辑之前的记录是要作废的，那么剩余积分加上上次扣掉的积分，才是当前的剩余积分
            remainingPoints += exchangePointsOld;
            convertedPoints -= exchangePointsOld;

            if (exchangePoints > remainingPoints) { //当兑换积分>剩余积分时，会出错
                bean.fail("该客户积分不够，无法实现兑换，请查清用户剩余积分");
                return bean;
            }
            exchangeRecord.setExchangeDate(df.parse(exchangeDate));
            exchangeRecord.setRemarks(remarks);
            exchangeRecord.setExchangePoints(String.valueOf(exchangePoints));

            clientInfo.setUpdateTime(new Date());
            clientInfo.setRemainingPoints(remainingPoints);
            clientInfo.setConvertedPoints(convertedPoints);

            exchangeRecordService.updateByPrimaryKey(exchangeRecord);
            clientInfoService.updateByPrimaryKeySelective(clientInfo);

        }
        return bean;

    }

}
