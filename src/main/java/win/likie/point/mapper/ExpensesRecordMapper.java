package win.likie.point.mapper;

import org.apache.ibatis.annotations.Param;
import win.likie.point.dao.IBasicDao;
import win.likie.point.entity.ExpensesRecord;

import java.util.List;

public interface ExpensesRecordMapper extends IBasicDao<ExpensesRecord> {
    List<ExpensesRecord> selectAllExpensesRecord(String clientMobile);

}