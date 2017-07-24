package win.likie.point.mapper;

import win.likie.point.entity.ExchangeRecord;

public interface ExchangeRecordMapper {
    int deleteByPrimaryKey(Integer exchangeRecords);

    int insert(ExchangeRecord record);

    int insertSelective(ExchangeRecord record);

    ExchangeRecord selectByPrimaryKey(Integer exchangeRecords);

    int updateByPrimaryKeySelective(ExchangeRecord record);

    int updateByPrimaryKey(ExchangeRecord record);
}