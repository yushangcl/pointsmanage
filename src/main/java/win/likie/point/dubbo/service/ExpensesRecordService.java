package win.likie.point.dubbo.service;

import win.likie.point.entity.ExchangeRecord;
import win.likie.point.entity.ExpensesRecord;

import java.util.HashMap;
import java.util.List;

/**
 * Created by WuHuahui on 2017/7/24.
 */
public interface ExpensesRecordService {

    /**
     * 查询兑换记录
     * @param clientMobile
     * @return
     */
    List<ExpensesRecord> selectExpensesRecord(String clientMobile);

    ExpensesRecord selectByPrimaryKey(Integer expensesId);

    Integer insert(ExpensesRecord expensesRecord);

    Integer updateByExpensesRecordId(ExpensesRecord expensesRecord);


}
