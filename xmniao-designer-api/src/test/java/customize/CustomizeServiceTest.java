package customize;

import com.xmn.designer.entity.material.MaterialCategoryAttr;
import com.xmn.designer.service.customize.CustomizeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * create 2016/11/16
 *
 * @author yangQiang
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context.xml")
public class CustomizeServiceTest {

    @Autowired
    private CustomizeService customizeService;

    @Test
    public void testGetCustomStandard(){
        List<MaterialCategoryAttr> customStandard = customizeService.getCustomStandard();
        System.out.println(customStandard);
    }


}
