package win.likie.point;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by WuHuahui on 2017/7/24.
 * 所有单元测试继承BaseTest
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ="classpath*:spring-config-mvc-test.xml")
public class BaseTest {

}
