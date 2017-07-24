package win.likie.point.dubbo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import win.likie.point.dubbo.service.TestService;

/**
 * Created by huahui.wu on 2017/7/24.
 */

@Service
public class TestServiceImpl implements TestService {
    public static Logger _log = LoggerFactory.getLogger(TestServiceImpl.class);
    @Override
    public void test() throws Exception {
        System.out.println();
    }
}
