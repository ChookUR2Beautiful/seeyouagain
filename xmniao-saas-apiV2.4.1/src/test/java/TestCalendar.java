import org.apache.commons.lang.time.DurationFormatUtils;
import org.junit.Test;

import java.util.Date;

/**
 * create 2016/11/23
 *
 * @author yangQiang
 */

public class TestCalendar {

    @Test
    public void testFormatPeriod(){
        String start = DurationFormatUtils.formatPeriod(new Date("2016/10/10").getTime(), new Date().getTime(),"d");
        String end = DurationFormatUtils.formatPeriod(new Date().getTime(), new Date("2016/11/30").getTime(),"d");
        // TODO delete output statement
        System.out.println(start);
        // TODO delete output statement
        System.out.println(Integer.valueOf(end)+1);


    }

}
