package win.likie.point.mapper;

import win.likie.point.entity.ClientInfo;

public interface ClientInfoMapper {
    int deleteByPrimaryKey(Integer clientId);

    int insert(ClientInfo record);

    int insertSelective(ClientInfo record);

    ClientInfo selectByPrimaryKey(Integer clientId);

    int updateByPrimaryKeySelective(ClientInfo record);

    int updateByPrimaryKey(ClientInfo record);
}