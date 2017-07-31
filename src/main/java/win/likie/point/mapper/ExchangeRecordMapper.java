package win.likie.point.mapper;

import win.likie.point.dao.IBasicDao;
import win.likie.point.entity.ExchangeRecord;

import java.util.List;

public interface ExchangeRecordMapper extends IBasicDao<ExchangeRecord> {

	List<ExchangeRecord> selectAllExchangeRecord(String clientMobile);

	ExchangeRecord selectByClientMobile(String clientMobile);
}