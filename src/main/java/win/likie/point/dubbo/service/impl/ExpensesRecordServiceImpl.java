package win.likie.point.dubbo.service.impl;

import org.springframework.stereotype.Service;
import win.likie.point.dubbo.service.ExpensesRecordService;
import win.likie.point.entity.ExpensesRecord;
import win.likie.point.mapper.ExpensesRecordMapper;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by WuHuahui on 2017/7/24.
 * 消费记录
 */
@Service
public class ExpensesRecordServiceImpl implements ExpensesRecordService {

    @Resource
    private ExpensesRecordMapper expensesRecordMapper;
    @Override
    public List<ExpensesRecord> selectExpensesRecord(String clientMobile) {
        if (("").equals(clientMobile)) {
            clientMobile = null;
        } else {
            clientMobile = "%" + clientMobile + "%";//用于模糊查询
        }
        return expensesRecordMapper.selectAllExpensesRecord(clientMobile);
    }

    @Override
    public ExpensesRecord selectByPrimaryKey(Integer expensesId) {
        return expensesRecordMapper.selectByPrimaryKey(expensesId);
    }
}
