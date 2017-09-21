/**
 * 
 */
package com.xmniao.test;

import java.util.HashMap;
import java.util.List;

import org.apache.thrift.TException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.dao.manor.ManorFlowerBranchChainMapper;
import com.xmniao.dao.manor.ManorFlowerBranchMapper;
import com.xmniao.dao.manor.ManorFlowerMapper;
import com.xmniao.domain.manor.ManorFlowerBranch;
import com.xmniao.service.manor.ManorFlowerService;
import com.xmniao.service.manor.ManorInfoService;
import com.xmniao.service.manor.ThriftManorServiceImpl;
import com.xmniao.thrift.busine.common.FailureException;
import com.xmniao.thrift.busine.common.ThriftResult;
import com.xmniao.urs.dao.UrsEarningsRankDao;
import com.xmniao.urs.dao.UrsEarningsRelationDao;
import com.xmniao.urs.dao.manor.ManorInfoMapper;

/**
 * 
 * 项目名称：busineService_budian
 * 
 * 类名称：黄金庄园布点
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年8月28日 下午3:22:04 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:conf/busine-base.xml" })
@Transactional
public class 黄金庄园布点 {
	
	@Autowired
	ManorFlowerService manorFlowerService;
	
	@Autowired
	ManorFlowerBranchMapper manorFlowerBranchMapper;
		
	@Autowired
	ManorFlowerBranchChainMapper  manorFlowerBranchChainMapper;
	
	@Autowired
	ManorFlowerMapper manorFlowerMapper;
	
	@Autowired
	UrsEarningsRankDao ursEarningsRankDao;
	
	@Autowired
	UrsEarningsRelationDao ursEarningsRelationDao;
	
	@Autowired
	ManorInfoMapper manorInfoMapper;
	
	Integer beforeL = 2;
	Integer afterL = 0;
	Integer beforeUid = 609292;
	Integer afterUid = 607803;
	
	@Autowired
	ThriftManorServiceImpl manorServiceImpl;
	
	@Autowired
    private ManorInfoService manorInfoService;
	
	
	@Test
	@Rollback(false)
	public void mytest() throws FailureException, TException{
		HashMap<String,String> hashMap = new HashMap<String,String>();
		hashMap.put("childId", "607121");
		hashMap.put("parentId", "609290");
		hashMap.put("location", "2");
		
		ThriftResult usrChainBindingParent = manorServiceImpl.flowerChainBindingParent(hashMap);
		System.out.println(usrChainBindingParent);
	}
	
	
	//把右节点绑到左节点
	
	public void rightToLeft(){
		
		List<ManorFlowerBranch> bs = manorFlowerBranchMapper.selectByUid(beforeUid);
		String parentId = bs.get(0).getParentId();
		//上级的坑
		ManorFlowerBranch p = manorFlowerBranchMapper.selectByPrimaryKey(parentId);
		//ManorFlowerBranch afterLocation = manorFlowerBranchMapper.selectByUidAndLocation(p.getUid(), afterL);
		//删除跟(需要变更顶级的那个坑)以上的所有与(需要变更顶级的那个坑及下级)所有关联的数据
		
		//查出上级的所有坑
		List<String> paIds = manorFlowerBranchChainMapper.selectByBranchId(p.getId());
		List<ManorFlowerBranch> selectAllSubBranchByUid = manorFlowerBranchMapper.selectAllSubBranchByUid(bs.get(0).getUid());
	
		//查询要绑定的新节点
		ManorFlowerBranch afterBranch = manorFlowerBranchMapper.selectByUidAndLocation(afterUid, afterL);
		//修改左链值
		//找出做边的那个坑
		ManorFlowerBranch left = manorFlowerBranchMapper.selectByUidAndLocation(beforeUid, 0);		
		int j=manorFlowerBranchMapper.updateZidMigrate(left.getZid(),afterBranch.getZid(),selectAllSubBranchByUid);		
		
		//改level
		int z=manorFlowerBranchMapper.updateLevelAdd(selectAllSubBranchByUid,((afterBranch.getLevel()+1)-bs.get(0).getLevel()));
		Assert.assertEquals(z, selectAllSubBranchByUid.size());
		
		
		for (ManorFlowerBranch manorFlowerBranch : selectAllSubBranchByUid) {
			for (String pId : paIds) {
				int i = manorFlowerBranchChainMapper.deleteByBranchIdAndParentId(manorFlowerBranch.getId(),pId);
				Assert.assertEquals(i, 1);
			}
		}
		
		
		
		/*List<String> afterBranchIds = manorFlowerBranchChainMapper.selectByBranchId(afterBranch.getId());
		afterBranchIds.add(afterBranch.getId());*/
		for (ManorFlowerBranch manorFlowerBranch : selectAllSubBranchByUid) {
				manorFlowerBranchMapper.insertMigrateChain(manorFlowerBranch, afterBranch);
		}
		
		//修改三个坑的parentId
		int i=manorFlowerBranchMapper.updateParentId(bs,afterBranch.getId());
		Assert.assertEquals(3, i);
		
		
		//迁移上级给下级种的花朵
		int updateFlowerByMigrate = manorFlowerMapper.updateFlowerByMigrate(selectAllSubBranchByUid,p.getId());
		
		System.out.println("迁移花朵数:"+updateFlowerByMigrate);
		
		//为上级贡献的花苗迁移 
		int updateFlowerSeedlingMigrate=manorFlowerMapper.updateFlowerSeedlingMigrate(afterBranch.getUid(),p.getUid(),beforeUid,afterBranch.getId());
		Assert.assertEquals(updateFlowerSeedlingMigrate, 1);
		
		
	}
	
	//@Test
	public void 改用户库关系链(){
		//修改庄园位置,父id
		
		//
		
		List<ManorFlowerBranch> selectAllSubBranchByUid = manorFlowerBranchMapper.selectAllSubBranchByUid(beforeUid);
		//修改用户关系链
		
		
	}
	
	
	
	
	
}
