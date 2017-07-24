package win.likie.point.dubbo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import win.likie.point.dubbo.service.TestService;
import win.likie.point.entity.ClientInfo;
import win.likie.point.mapper.ClientInfoMapper;

import javax.annotation.Resource;

/**
 * Created by huahui.wu on 2017/7/24.
 */

@Service
public class TestServiceImpl implements TestService {
    public static Logger _log = LoggerFactory.getLogger(TestServiceImpl.class);

    @Resource
    private ClientInfoMapper clientInfoMapper;
    @Override
    public void test() throws Exception {
        ClientInfo clientInfo = new ClientInfo();
        clientInfo.setClientName("test");
        clientInfo.setClientMobile("12234567895");
        clientInfo.setConvertedPoints(12);
        clientInfo.setPurchasedPoints(10);
        clientInfo.setRemainingPoints(2);
        clientInfoMapper.insert(clientInfo);
    }
}
