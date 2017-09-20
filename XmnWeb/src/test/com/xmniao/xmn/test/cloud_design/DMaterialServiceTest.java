/**
 * 
 */
package com.xmniao.xmn.test.cloud_design;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xmniao.xmn.core.cloud_design.entity.DMaterial;
import com.xmniao.xmn.core.cloud_design.entity.DMaterialAttr;
import com.xmniao.xmn.core.cloud_design.entity.DMaterialAttrGroup;
import com.xmniao.xmn.core.cloud_design.entity.DMaterialCategory;
import com.xmniao.xmn.core.cloud_design.entity.DMaterialCategoryAttr;
import com.xmniao.xmn.core.cloud_design.entity.DMaterialCategoryAttrVal;
import com.xmniao.xmn.core.cloud_design.entity.DMaterialTag;
import com.xmniao.xmn.core.cloud_design.service.DMaterialAttrGroupService;
import com.xmniao.xmn.core.cloud_design.service.DMaterialAttrService;
import com.xmniao.xmn.core.cloud_design.service.DMaterialCategoryAttrService;
import com.xmniao.xmn.core.cloud_design.service.DMaterialCategoryAttrValService;
import com.xmniao.xmn.core.cloud_design.service.DMaterialCategoryService;
import com.xmniao.xmn.core.cloud_design.service.DMaterialService;
import com.xmniao.xmn.core.cloud_design.service.DMaterialTagService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：DMaterialTagServiceTest
 * 
 * 类描述： 物料服务测试类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-9-28 上午9:37:27 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public class DMaterialServiceTest {
	private ApplicationContext application;
	private DMaterialTagService materialTagService;
	private DMaterialCategoryAttrService categoryAttrService;
	private DMaterialCategoryAttrValService categoryAttrValService;
	private DMaterialCategoryService materialCategoryService;
	private DMaterialService materialService;
	private DMaterialAttrService materialAttrService;
	private DMaterialAttrGroupService attrGroupService;

	@Before
	public void before() {
		application = new ClassPathXmlApplicationContext("classpath:/com/xmniao/xmn/resource/config/spring-context.xml");
		materialTagService = application.getBean(DMaterialTagService.class);
		categoryAttrService = application.getBean(DMaterialCategoryAttrService.class);
		categoryAttrValService = application.getBean(DMaterialCategoryAttrValService.class);
		materialCategoryService = application.getBean(DMaterialCategoryService.class);
		materialService = application.getBean(DMaterialService.class);
		materialAttrService = application.getBean(DMaterialAttrService.class);
		attrGroupService = application.getBean(DMaterialAttrGroupService.class);
	}
	
//	@Test
	public void add(){
		DMaterialTag record=new DMaterialTag();
		record.setName("葡萄酒");
		record.setParentId(7L);
		materialTagService.add(record);
	}
	
//	@Test
	public void getList(){
		DMaterialTag materialTag = new DMaterialTag();
		List<DMaterialTag> list = materialTagService.getList(materialTag);
		for(DMaterialTag materialTagInfo:list){
			System.out.println(materialTagInfo.toString());
		}
	}
	
//	@Test
	public void addCategoryAttr(){
		DMaterialCategoryAttr categoryAttr =new DMaterialCategoryAttr();
//		categoryAttr.setCategoryId(-1L);//物料分类主键
		categoryAttr.setName("颜色");
		categoryAttr.setSortVal(1);
		categoryAttr.setIsCustomize("001");//是否定制（001定制；002非定制）
		categoryAttr.setIsMultiple("002");//是否多选（001 多选 ；002 单选）,只有定制规格才有是否多选
		
		categoryAttrService.add(categoryAttr);
		
	}
	
//	@Test
	public void getCategoryAttr(){
//		DMaterialCategoryAttr materialCategoryAttr = categoryAttrService.selectById(1l);
//		System.out.println(materialCategoryAttr.toString());
		DMaterialCategoryAttr categoryAttr = new DMaterialCategoryAttr();
		List<DMaterialCategoryAttr> list = categoryAttrService.getList(categoryAttr);
		for(DMaterialCategoryAttr attr:list){
			System.out.println(attr.toString());
		}
	}
	
//	@Test
	public void addCategoryAttrVal(){
		DMaterialCategoryAttrVal materialCategoryAttrVal = new DMaterialCategoryAttrVal();
		materialCategoryAttrVal.setCategoryAttrId(1l);
		materialCategoryAttrVal.setVal("玫瑰金");
		materialCategoryAttrVal.setSortVal(2);
		
		int count = categoryAttrValService.add(materialCategoryAttrVal);
		if(count>0){
			System.out.println("添加成功!");
		}
		
	}
	
//	@Test
	public void getCategoryAttrVal(){
		DMaterialCategoryAttrVal materialCategoryAttrVal = categoryAttrValService.selectById(1l);
		System.out.println(materialCategoryAttrVal.toString());
	}
	
//	@Test
	public void addCategory(){
		DMaterialCategory category = new DMaterialCategory();
		category.setName("海报");
		category.setOrderVal(1);
		materialCategoryService.add(category);
	}
	
//	@Test
	public void getMaterialList(){
		DMaterial material=new DMaterial();
		List<DMaterial> list = materialService.getList(material);
		for(DMaterial materialInfo:list ){
			System.out.println(materialInfo.toString());
		}
	}
	
//	@Test
	public void materialAttrAddBatch(){
		List<DMaterialAttr> materialAttrList = new ArrayList<DMaterialAttr>();
		DMaterialAttr materialAttr1=new DMaterialAttr();
		materialAttr1.setMaterialId(1l);
		materialAttr1.setCategoryId(1l);
		materialAttr1.setCategoryAttrId(1l);
		materialAttr1.setName("Test one");
		materialAttr1.setSortVal(1);
		DMaterialAttr materialAttr2=new DMaterialAttr();
		materialAttr2.setMaterialId(2l);
		materialAttr2.setCategoryId(2l);
		materialAttr2.setCategoryAttrId(2l);
		materialAttr2.setName("Test two");
		materialAttr2.setSortVal(2);
		materialAttrList.add(materialAttr1);
		materialAttrList.add(materialAttr2);
		
//		materialAttrService.addBatch(materialAttrList);
		DMaterialAttr materialAttr= new DMaterialAttr();
		materialAttr.setMaterialId(1l);
		
		List<DMaterialAttr> list = materialAttrService.getList(materialAttr);
		for(DMaterialAttr materialAttrInfo:list){
			System.out.println(materialAttrInfo.toString());
		}
		
	}
	
	@Test
	public void attrGroupAddBatch(){
		List<DMaterialAttrGroup> list=new ArrayList<DMaterialAttrGroup>();
		DMaterialAttrGroup attrGroup=new DMaterialAttrGroup();
		attrGroup.setMaterialId(1l);
		attrGroup.setMaterialAttrIds("1,2,4");
		attrGroup.setMaterialAttrVals("红色,A4,磨砂");
		attrGroup.setAmount(new BigDecimal(129));
		attrGroup.setSortVal(0);
		list.add(attrGroup);
//		attrGroupService.addBatch(list);
		List<DMaterialAttrGroup> list2 = attrGroupService.getList(attrGroup);
		for(DMaterialAttrGroup attrGroupInfo:list2){
			System.out.println(attrGroupInfo.toString());
		}
	}
	
}
