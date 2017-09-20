package com.xmniao.xmn.core.base.Import.context;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.InitializingBean;

import com.xmniao.xmn.core.base.Import.ConfigConstant;
import com.xmniao.xmn.core.base.Import.bean.ImportModelDefinition;
import com.xmniao.xmn.core.util.StringUtils;

/**
 * 导出配置文件属性上下文
 * @author ch
 *
 */
public class DefaultContext implements InitializingBean{
	/**
	 * 默认导入实体配置文件
	 */
	private static final String DEFAULT_CONFIG_NAME=  "ExcelModeMappingl.xml"; 
	
	/**
	 * 导入实体配置文件 
	 */
	private String configName = DEFAULT_CONFIG_NAME;
	
	/**
	 * 是否启动 默认false
	 */
	private boolean activated =false;
	
	private static Map<Class<?>, Map<String, ImportModelDefinition>> models = new ConcurrentHashMap<Class<?>, Map<String, ImportModelDefinition>>(64);
	

	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	/**
	 * 后置属性设置
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		if(!activated){
			init();
			activated=true;
		}
	}
	
	/**
	 * 初始化
	 */
	private void init(){
		Element root = loadConfiguration();
		if(root!=null){
			initModel(root);
		}
	}
	
	/**
	 * 加载配置文件
	 * @return
	 */
	private Element loadConfiguration(){
		InputStream in=null;
		try {
			in = Thread.currentThread().getContextClassLoader().getResourceAsStream(configName);
			SAXReader saxReader = new SAXReader();
			Document doc = saxReader.read(in);
			return doc.getRootElement();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	/**
	 * 解析配置文件
	 * @param root
	 */
	private void initModel(Element root){
		List<?> list = root.elements(ConfigConstant.MODEL);
		Element model = null;
		for (Iterator<?> it = list.iterator(); it.hasNext();) {
				model = (Element) it.next();
				String entityClassName = model.attributeValue(ConfigConstant.MODEL_CLASS);
				if(StringUtils.hasLength(entityClassName)){
					Class<?> entityClass=getEntityClass(entityClassName);
					if(entityClass!=null){
						models.put(entityClass, getPropertyMap(model) );
					}
				}
		}
	}
	
	/**
	 * 获取配置文件属性
	 * @param model
	 * @return
	 */
	private Map<String, ImportModelDefinition> getPropertyMap(Element model) {
		Map<String, ImportModelDefinition> propertyMap = new ConcurrentHashMap<String, ImportModelDefinition>();
		List<?> list = model.elements(ConfigConstant.PROPERTY);// An attribute of a class
		Element property = null;
		ImportModelDefinition modelProperty=null;
		for (Iterator<?> it = list.iterator(); it.hasNext();) {
			property = (Element) it.next();
			modelProperty = new ImportModelDefinition();
			modelProperty.setName(property.attributeValue(ConfigConstant.PROPERTY_NAME));//property name in java bean
			
			modelProperty.setExcelTitleName(property.attributeValue(ConfigConstant.PROPERTY_EXCEL_TITLE_NAME));//table title in excel
			
			modelProperty.setDataType(property.attributeValue(ConfigConstant.PROPERTY_DATA_TYPE));//data type:[String,Date]
			
			modelProperty.setMaxLength(property.attributeValue(ConfigConstant.PROPERTY_MAX_LENGTH));
			
			//if data type is "Date"
			modelProperty.setDateFormat(property.attributeValue(ConfigConstant.PROPERTY_FORMAT));
			
			modelProperty.setDefaultValue(property.attributeValue(ConfigConstant.PROPERTY_DEFAULT));//default value
			modelProperty.finish();
			propertyMap.put(modelProperty.getExcelTitleName(), modelProperty);

		}
		return propertyMap;
	}
	
	/**
	 * 获取实体class
	 * @param entityClassName class名称
	 * @return
	 */
	private Class<?> getEntityClass(String entityClassName) {
		try {
			return  Class.forName(entityClassName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据class 与标题名称获取属性
	 * @param entityClass
	 * @param TitleName
	 * @return
	 */
	public static ImportModelDefinition getModel(Class<?> entityClass,String TitleName){
		if(models.containsKey(entityClass)){
			Map<String,ImportModelDefinition> m = models.get(entityClass);
			if(m!=null&&m.containsKey(TitleName)){
				return m.get(TitleName);
			}
		}
		return null;
	}
	
	

}
