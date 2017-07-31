package win.likie.point.mapper;

import org.apache.ibatis.annotations.Param;
import win.likie.point.entity.ClientInfo;

import java.util.List;

public interface ClientInfoMapper {
    int deleteByPrimaryKey(Integer clientId);

    int insert(ClientInfo record);

    int insertSelective(ClientInfo record);

    ClientInfo selectByPrimaryKey(Integer clientId);

    int updateByPrimaryKeySelective(ClientInfo record);

    int updateByPrimaryKey(ClientInfo record);

    List<ClientInfo> selectAllClientInfo(@Param("clientMobile") String clientMobile, @Param("clientName") String clientName);

    ClientInfo selectByClientMobile(String clientMobile);
}