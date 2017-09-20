package image;

import com.xmn.designer.service.image.ImageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * create 2016/11/16
 *
 * @author yangQiang
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context.xml")
public class ImageServiceTest {

    @Autowired
    private ImageService imageService;

    @Test
    public void testUseImage()  {
        try {
            imageService.useImage("sdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
