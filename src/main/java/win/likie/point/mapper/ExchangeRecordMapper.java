package win.likie.point.mapper;

import org.apache.ibatis.annotations.Param;
import win.likie.point.entity.ClientInfo;
import win.likie.point.entity.ExchangeRecord;

import java.util.List;

public interface ExchangeRecordMapper {
    int deleteByPrimaryKey(Integer exchangeRecords);

    int insert(ExchangeRecord record);

    int insertSelective(ExchangeRecord record);

    ExchangeRecord selectByPrimaryKey(Integer exchangeRecords);

    int updateByPrimaryKeySelective(ExchangeRecord record);

    int updateByPrimaryKey(ExchangeRecord record);

    List<ExchangeRecord> selectAllExchangeRecord(String clientMobile);

    ExchangeRecord selectByClientMobile(String clientMobile);
}