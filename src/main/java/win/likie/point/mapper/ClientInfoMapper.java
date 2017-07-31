package win.likie.point.mapper;

import org.apache.ibatis.annotations.Param;
import win.likie.point.dao.IBasicDao;
import win.likie.point.entity.ClientInfo;

import java.util.List;

public interface ClientInfoMapper extends IBasicDao<ClientInfo> {

    List<ClientInfo> selectAllClientInfo(@Param("clientMobile") String clientMobile, @Param("clientName") String clientName);

    ClientInfo selectByClientMobile(String clientMobile);
}