package win.likie.point.mapper;

import win.likie.point.entity.ExpensesRecord;

public interface ExpensesRecordMapper {
    int deleteByPrimaryKey(Integer recordNumber);

    int insert(ExpensesRecord record);

    int insertSelective(ExpensesRecord record);

    ExpensesRecord selectByPrimaryKey(Integer recordNumber);

    int updateByPrimaryKeySelective(ExpensesRecord record);

    int updateByPrimaryKey(ExpensesRecord record);
}