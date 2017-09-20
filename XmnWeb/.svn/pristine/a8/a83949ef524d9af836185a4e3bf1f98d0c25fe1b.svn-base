package mytest;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;


public class TestProperties {
	public static void main(String[] args) {
		URL u1 = TestProperties.class.getResource("");
		URL u2 = TestProperties.class.getResource("/");
		URL u3 = TestProperties.class.getClassLoader().getResource("");
		System.out.println(u1);
		System.out.println(u2);
		System.out.println(u3);
		InputStream i = TestProperties.class.getClassLoader().getResourceAsStream("mytestsub/peopleinfo2.properties");
		InputStream i2 = TestProperties.class.getResourceAsStream("/mytestsub/peopleinfo2.properties");
		InputStream i3 = TestProperties.class.getResourceAsStream("/peopleinfo1.properties");
		Properties p = new Properties();
		Properties p2 = new Properties();
		Properties p3 = new Properties();
		try {
			p.load(i);
			p2.load(i2);
			p3.load(i3);
			i.close();
			i2.close();
			i3.close();
		} catch (IOException e) {
			Logger.getLogger(TestProperties.class).error(e);
			e.printStackTrace();
		}
		TestProperties tp = new TestProperties();
		System.out.println(tp.getClass());
		System.out.println(TestProperties.class);
		System.out.println(p.getProperty("Name"));
		System.out.println(p2.getProperty("Sex"));
		System.out.println(p3.getProperty("Phone"));
	}

}
