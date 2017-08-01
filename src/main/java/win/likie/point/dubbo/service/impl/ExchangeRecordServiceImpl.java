package win.likie.point.dubbo.service.impl;

import org.springframework.stereotype.Service;
import win.likie.point.dubbo.service.ExchangeRecordService;
import win.likie.point.entity.ClientInfo;
import win.likie.point.entity.ExchangeRecord;
import win.likie.point.mapper.ClientInfoMapper;
import win.likie.point.mapper.ExchangeRecordMapper;
import win.likie.point.utils.DateUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by WuHuahui on 2017/7/24.
 * 兑换记录service
 */
@Service
public class ExchangeRecordServiceImpl implements ExchangeRecordService {

    @Resource
    private ExchangeRecordMapper exchangeRecordMapper;

    @Resource
    private ClientInfoMapper clientInfoMapper;


    @Override
    public List<ExchangeRecord> selectExchangeRecord(HashMap<String, String> queryMap) {
        String clientMobile = queryMap.get("clientMobile");


        if (("").equals(clientMobile)) {
            clientMobile = null;
        } else {
            clientMobile = "%" + clientMobile + "%";//用于模糊查询
        }



        return exchangeRecordMapper.selectAllExchangeRecord(clientMobile);
    }

    @Override
    public ExchangeRecord selectExchangeRecordByMobile(String clientMobile) {

        ExchangeRecord exchangeRecord = null;
        exchangeRecord = exchangeRecordMapper.selectByClientMobile(clientMobile);
        return exchangeRecord;
    }

    /**
     * 保存客户兑换记录
     *
     * @param queryMap
     */
    @Override
    public void addExchangeRecord(HashMap<String, String> queryMap, ClientInfo clientInfo) {

        String clientMobile = queryMap.get("clientMobile");
        String exchangePointsStr = queryMap.get("exchangePoints");//兑换积分
        String exchangeDate = queryMap.get("exchangeDate");
        String remarks = queryMap.get("remarks");

        Integer convertedPoints = null;//已换积分
        Integer purchasedPoints = null; //已购积分
        Integer remainingPoints = null; //剩余积分

        Integer exchangePoints = Integer.valueOf(exchangePointsStr);//兑换积分

        ExchangeRecord exchangeRecord = new ExchangeRecord();

        convertedPoints = clientInfo.getConvertedPoints();
        purchasedPoints = clientInfo.getPurchasedPoints();
        remainingPoints = clientInfo.getRemainingPoints();

        if (exchangePoints > remainingPoints) { //当兑换积分>剩余积分时，会出错
            return;
        }

        exchangeRecord.setClientMobile(clientMobile);
        exchangeRecord.setExchangePoints(exchangePointsStr);
        exchangeRecord.setRemarks(remarks);
        exchangeRecord.setExchangeDate(DateUtil.parse(exchangeDate, DateUtil.DATE_FORMAT));
        convertedPoints += exchangePoints;
        remainingPoints = purchasedPoints - convertedPoints;

        clientInfo.setUpdateTime(new Date());
        clientInfo.setConvertedPoints(convertedPoints);
        clientInfo.setRemainingPoints(remainingPoints);

        clientInfoMapper.updateByPrimaryKeySelective(clientInfo);
        exchangeRecordMapper.insert(exchangeRecord);


    }

    @Override
    public ExchangeRecord selectByPrimaryKey(Integer expensesRecord) {
        ExchangeRecord exchangeRecord = null;
        exchangeRecord = exchangeRecordMapper.selectByPrimaryKey(expensesRecord);
        return exchangeRecord;
    }

    @Override
    public int updateByPrimaryKey(ExchangeRecord record) {
        return exchangeRecordMapper.updateByPrimaryKey(record);
    }
}
