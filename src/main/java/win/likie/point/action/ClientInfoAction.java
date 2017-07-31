package win.likie.point.action;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.Cleaner;
import win.likie.point.dubbo.service.ClientInfoService;
import win.likie.point.entity.ClientInfo;
import win.likie.point.formbean.JsonBean;
import win.likie.point.formbean.Page;
import win.likie.point.mapper.ClientInfoMapper;
import win.likie.point.utils.StringUtils;
import win.likie.point.utils.SysParamUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;


/**
 * Created by 10150 on 2017/7/25.
 */
@Controller
@RequestMapping(value = "/clientinfoaction")
public class ClientInfoAction {


    @Resource
    private ClientInfoService clientInfoService;
    @Resource
    private ClientInfoMapper clientInfoMapper;

    @RequestMapping(value="/index")
    public ModelAndView Index(HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException {
        ModelAndView mav = new ModelAndView();

        mav.setViewName("/index");
        return mav;

    }
    /**
     * 查询客户信息列表页面
     * @param
     * @param
     */
    @RequestMapping(value="/list")
    public @ResponseBody JsonBean list(
            @RequestParam(value = "clientName",defaultValue = "")String clientName,
            @RequestParam(value = "clientMobile",defaultValue = "")String clientMobile,
            @RequestParam(value = "page", defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = SysParamUtil.PAGE_SIZE) int pageSize
    ){
        System.out.println("list");
        JsonBean bean = new JsonBean();
        //clientMobile = "12234567895";
        Integer totalCount = null;
        Integer toatlPages = 1;

        HashMap<String,String> queryMap = new HashMap<String, String>();
        queryMap.put("clientMobile", clientMobile);
        queryMap.put("clientName", clientName);

        List<ClientInfo> clientInfoList =  clientInfoService.selectClientInfo(queryMap);
        List<ClientInfo> pageClientInfo = new ArrayList<ClientInfo>();
        Page<ClientInfo> result = new Page<ClientInfo>();

        totalCount = clientInfoList.size();
        //娴熟数据总条数大于每页显示数的时候,返回显示数据为当前
        //每页显示数据的最大值
        if(totalCount > pageSize){
            int indexNum = pageSize * pageNumber; //每次截取数据的开始下标
            int lastNum = pageSize * (pageNumber + 1);//每次截取数据的最后一位数据的下标
            toatlPages = (totalCount / pageSize) + 1;
            for(int i = indexNum;i<lastNum && i<totalCount;i++){
                pageClientInfo.add(clientInfoList.get(i));
            }
        } else{
           pageClientInfo.addAll(clientInfoList);
        }
        result.setPageSize(pageSize);//每页数量
        result.setIndexPage(pageNumber);
        result.setTotalPages(toatlPages);
        result.setTotalCount(clientInfoList.size());//显示数据总数
        result.setContent(pageClientInfo);
        bean.setBean(result);

        return bean;
    }


    @RequestMapping(value="/add")
    public ModelAndView addIndex(HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException {
        ModelAndView mav = new ModelAndView();
        System.out.println("add");
        mav.setViewName("/manage/clientInfo_add");
        return mav;

    }

    @RequestMapping(value="/detailIndex")
    public ModelAndView detailIndex(@RequestParam(value="clientMobile", defaultValue="")String clientMobile,
        HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException {
        ModelAndView mav = new ModelAndView();
        ClientInfo clientInfo = null;
        System.out.println("detailIndex");
        clientInfo = clientInfoService.selectClientInfoByMobile(clientMobile);
        //request.setAttribute("clientInfo",clientInfo);
        mav.addObject("clientInfo",clientInfo);
        mav.setViewName("/manage/clientInfo_add");
        return mav;

    }



    /**
     * 客户信息保存
     * @throws ParseException
     *
     */
    @RequestMapping(value="/save" , method=RequestMethod.POST)
    public @ResponseBody JsonBean save(
            @RequestParam(value="clientMobile", defaultValue="")String clientMobile,
            @RequestParam(value="clientName", defaultValue="") String clientName,
            @RequestParam(value="operMode", defaultValue="") String operMode,
            HttpServletRequest request,
            HttpServletResponse response) throws ParseException {

        String message = "";
        Map<String,String> map = null;

        ClientInfo clientInfo = null;

        JsonBean bean = new JsonBean();
        HashMap<String,String> queryMap = new HashMap<String, String>();
        if(StringUtils.isBlank(clientMobile)){
            bean.fail("操作失败！");
            return bean;
        }else{
            clientInfo = clientInfoService.selectClientInfoByMobile(clientMobile);
        }

        if("add".equals(operMode)){
            if(clientInfo != null){
                bean.fail("客户号码已存在,请更换手机号!");
                return bean;
            }else{
                queryMap.put("clientMobile", clientMobile);
                queryMap.put("clientName", clientName);
                clientInfoService.addClientInfo(queryMap);
            }
        }

        if("update".equals(operMode)){
            if(clientInfo != null){
                clientInfo.setClientName(clientName);
                clientInfo.setUpdateTime(new Date());
                clientInfoMapper.updateByPrimaryKeySelective(clientInfo);
            }else{
                bean.fail("操作失败！");
                return bean;
            }
        }
        return bean;

    }
}