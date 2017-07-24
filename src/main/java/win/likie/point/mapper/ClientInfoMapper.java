package win.likie.point.mapper;

import win.likie.point.entity.ClientInfo;
import win.likie.point.entity.ClientInfoKey;

public interface ClientInfoMapper {
    int deleteByPrimaryKey(ClientInfoKey key);

    int insert(ClientInfo record);

    int insertSelective(ClientInfo record);

    ClientInfo selectByPrimaryKey(ClientInfoKey key);

    int updateByPrimaryKeySelective(ClientInfo record);

    int updateByPrimaryKey(ClientInfo record);
}