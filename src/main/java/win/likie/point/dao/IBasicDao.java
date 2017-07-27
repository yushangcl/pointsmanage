package win.likie.point.dao;

import org.apache.ibatis.annotations.Param;
import win.likie.point.entity.ExpensesRecord;

import java.util.HashMap;
import java.util.List;

/**
 * 持久层基本的接口定义 所有的DAO接口都必须继承该接口
 */
public interface IBasicDao<T> {
    int deleteByPrimaryKey(Integer orderUkid);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Integer ukid);

    int updateByPrimaryKey(T record);

    int updateByPrimaryKeySelective(T record);

    List<T> getsByMap(@Param("map") HashMap params);

    int getCountByMap(@Param("map") HashMap params);
}