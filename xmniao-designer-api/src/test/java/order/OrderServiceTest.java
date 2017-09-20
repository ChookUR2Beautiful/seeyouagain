package order;

import com.xmn.designer.service.order.OrderService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * create 2016/11/21
 *
 * @author yangQiang
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context.xml")
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    public void testQueryExpInfo() throws IOException {

    }

}
