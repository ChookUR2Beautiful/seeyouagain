/**
 * 
 */
package com.xmniao.xmn.test.live_anchor;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xmniao.xmn.core.live_anchor.constant.LiveConstant;
import com.xmniao.xmn.core.live_anchor.entity.BLiveAnchorImage;
import com.xmniao.xmn.core.live_anchor.service.BLiveAnchorImageService;
import com.xmniao.xmn.core.live_anchor.service.TLiveRobotService;

/**
 * 项目名称：XmnWeb_LVB
 * 
 * 类名称：LiveAnchorImageServiceTest
 *
 * 类描述：主播相册服务测试类
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-8-6下午3:47:30
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class LiveAnchorImageServiceTest {
	private ApplicationContext application;
	private BLiveAnchorImageService anchorImageService;
	private TLiveRobotService liveRobotService;

	@Before
	public void before() {
		application = new ClassPathXmlApplicationContext("classpath:/com/xmniao/xmn/resource/config/spring-context.xml");
		anchorImageService = application.getBean(BLiveAnchorImageService.class);
		liveRobotService = application.getBean(TLiveRobotService.class);
	}
	
//	@Test
	public void insertTest(){
		BLiveAnchorImage image=new BLiveAnchorImage();
		image.setAnchorId(3);
		image.setImageComment("一塌糊涂");
		image.setCreateTime(new Date());
		anchorImageService.add(image);
	}
	
	@Test
	public void getListTest(){
		BLiveAnchorImage record=new BLiveAnchorImage();
		record.setAnchorId(984);
		List<BLiveAnchorImage> list = anchorImageService.getList(record);
		for(BLiveAnchorImage image:list){
			System.out.println(image.toString());
		}
	}
	
//	@Test
	public void deleteAll(){
		int count = liveRobotService.deleteAll();
		System.out.println("删除"+count+"个机器人");
	}
	
//	@Test
	public void deleteImages(){
		int count = anchorImageService.deleteImages(LiveConstant.IMAGETYPE_ROBOT);
		System.out.println("删除"+count+"张图片");
	}
	
}
