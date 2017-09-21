import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

/**
 * 
 */
/** 
 * @author Wangxiaoping  email:paggywong@gmail.com
 * @version 创建时间：2013-4-30 下午11:19:21 
 * 类说明 
 */
public class Test {
    public static Map<String, List<String>> parserXmlClassPath(String fileName) {
	Map<String, List<String>> dataMap=new HashMap<String, List<String>>();
	SAXBuilder builder = new SAXBuilder(false);
	Element employees = null;
	try {
	    Document document = builder.build(fileName);
	    employees = document.getRootElement();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	List<Element> elList=employees.getChildren();
	List<String> src=new ArrayList<String>();
	List<String> output=new ArrayList<String>();
	for (Element element : elList) {
	   if("src".equals(element.getAttribute("kind").getValue())){
	       src.add( element.getAttribute("path").getValue());
	   }
	   if("output".equals(element.getAttribute("kind").getValue())){
	       output.add( element.getAttribute("path").getValue());
	   }
	}
	dataMap.put("src",src);
	dataMap.put("output", output);
	return dataMap;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
	
	System.out.println(parserXmlClassPath("E://MyEclipse2014code/XmnWeb/.classpath"));

    }

}
