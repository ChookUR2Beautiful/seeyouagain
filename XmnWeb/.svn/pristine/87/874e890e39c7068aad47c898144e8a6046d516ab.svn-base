/**
 * 
 */
package com.xmniao.xmn.test.live_anchor;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xmniao.xmn.core.live_anchor.entity.BLiver;
import com.xmniao.xmn.core.live_anchor.service.BLiveMemberService;
import com.xmniao.xmn.core.live_anchor.service.TLiveAnchorService;
import com.xmniao.xmn.core.util.StringUtils;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：LiveMemberServiceTest
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-10-24 下午3:24:15 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public class LiveMemberServiceTest {

	private ApplicationContext application;
	private BLiveMemberService liveMemberService;
	private TLiveAnchorService liveAnchorService;
	
	@Before
	public void before() {
		application = new ClassPathXmlApplicationContext("classpath:/com/xmniao/xmn/resource/config/spring-context.xml");
		liveMemberService = application.getBean(BLiveMemberService.class);
		liveAnchorService = application.getBean(TLiveAnchorService.class);
	}

	/**
	 * 1、不存在上级，添加上级<br/>
	 * 2、存在上级，替换上级<br/>
	 * 方法描述：绑定上级测试 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-2-8上午10:32:06 <br/>
	 */
	@Test
	public void getList(){
		BLiver liverReq=new BLiver();
		Integer uid=606467;
		liverReq.setUid(uid);
		String uidStr = StringUtils.generateUidStr(uid);
		liverReq.setUidStr(uidStr);
		System.out.println(liverReq.toString());
		//等下级会员信息
		List<BLiver> juniorList = liveMemberService.getJuniorList(liverReq);
		BLiver currentLiver = liveAnchorService.selectBLiver(liverReq);
		if(currentLiver==null){
			return;
		}
		
		//待绑定的上级信息 710
		Integer superiorId=1456;
		BLiver superiorInfo = liveAnchorService.selectByPrimaryKey(superiorId);
		String superiorUidRelationChain = superiorInfo.getUidRelationChain();
		String superiorUidRelationChainNname = superiorInfo.getUidRelationChainNname();
		//TODO 
		Integer superiorUidRelationChainLevel = superiorInfo.getUidRelationChainLevel();
		if(superiorUidRelationChainLevel.compareTo(new Integer(1))==0){
			superiorUidRelationChainNname=superiorUidRelationChainNname==null?"":superiorUidRelationChainNname;
		}
		
		System.out.println("====输出上级信息：");
		System.out.println("uidRelationChain="+superiorUidRelationChain+",uidRelationChainNname="+superiorUidRelationChainNname);
				
		Integer currentLevel = currentLiver.getUidRelationChainLevel();
		for(BLiver liverInfo:juniorList){
			String uidRelationChain = liverInfo.getUidRelationChain();
			int indexOf = uidRelationChain.indexOf(uidStr);
			String latterUidRelationChain = uidRelationChain.substring(indexOf);
			String uidRelationChainNname = liverInfo.getUidRelationChainNname();
			String latterUidRelationChainNname="";
			if(currentLevel.compareTo(new Integer(1))==0){
				//TODO
				uidRelationChainNname=uidRelationChainNname==null?"":uidRelationChainNname;
				latterUidRelationChainNname=","+uidRelationChainNname;
			}else{
				int characterPosition = StringUtils.getCharacterPosition(uidRelationChainNname, currentLevel-1, ",");
				latterUidRelationChainNname=uidRelationChainNname.substring(characterPosition);
			}
			
			StringBuffer uidRelationChainSb=new StringBuffer();
			StringBuffer uidRelationChainNnameSb=new StringBuffer();
			String cycleUidRelationChain = uidRelationChainSb.append(superiorUidRelationChain).append(",").append(latterUidRelationChain).toString();
			String cycleUidRelationChainNname = uidRelationChainNnameSb.append(superiorUidRelationChainNname).append(latterUidRelationChainNname).toString();
			
			Integer cycleUid = liverInfo.getUid();
			String  cycleUidStr = StringUtils.generateUidStr(cycleUid);
			int cycleUidRelationChainLevel = StringUtils.getArrayIndexFromStr(cycleUidRelationChain, cycleUidStr, ",");
			
			liverInfo.setUidRelationChain(cycleUidRelationChain);
			liverInfo.setUidRelationChainNname(cycleUidRelationChainNname);
			liverInfo.setUidRelationChainLevel(cycleUidRelationChainLevel);
			
			//更新层级关系
			liveMemberService.updateRelationChainInfo(liverInfo);
			Integer enterpriseUid = liverInfo.getEnterpriseUid();
			
			//更新企业级推荐人信息
			if(enterpriseUid==null){
				BLiver liverBean=new BLiver();
				liverBean.setId(liverInfo.getId());
				liverBean.setUid(liverInfo.getUid());
				liverBean.setUidRelationChain(liverInfo.getUidRelationChain());
				liveMemberService.setEnterpriseInfo(liverBean);
				liveMemberService.updateRelationChainInfo(liverBean);
			}
		}
		
	}
	
}
