package com.xmniao.service.manor;

import com.alibaba.fastjson.JSON;
import com.xmniao.dao.manor.ManorConfigMapper;
import com.xmniao.dao.manor.ManorFlowerBranchMapper;
import com.xmniao.dao.manor.ManorFlowerMapper;
import com.xmniao.domain.manor.ManorConfig;
import com.xmniao.domain.manor.ManorFlower;
import com.xmniao.domain.manor.ManorFlowerBranch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.xmniao.service.manor.ManorConstant.*;

/**
 * Created by yang.qiang on 2017/7/11.
 */
@Service
public class FlowerService {
    private final Logger logger = LoggerFactory.getLogger(FlowerService.class);
    @Autowired
    private ManorFlowerBranchMapper manorFlowerBranchMapper;
    @Autowired
    private ManorFlowerMapper manorFlowerMapper;

    @Autowired
    private ManorConfigMapper manorConfigMapper;

    /**
     * 用户开通庄园初始化3个节点
     */
    public void  initBranchNode(Integer uid){
        long startTime = System.currentTimeMillis();
        logger.info("黄金庄园-初始化分支节点 : 用户["+uid+"]插入"+DEFAULT_LOCATIONS+"个初始节点");
        ArrayList<ManorFlowerBranch> branchList = new ArrayList<>();

        for (int i = 0; i < DEFAULT_LOCATIONS; i++) {
            ManorFlowerBranch branch = new ManorFlowerBranch();

            branch.setUid(uid);
            branch.setLocation(i);
            branch.setLevel(1);
            branch.setParentId(null);
            branch.setId(UUID.randomUUID().toString().replaceAll("-",""));
            branch.setZid(branch.getId());
            branch.setParentId(branch.getId());
            branchList.add(branch);
        }

        // 批量插上数据
        manorFlowerBranchMapper.insertBatch(branchList);
        // 初始化关系链表
        manorFlowerBranchMapper.insertInitChainBatch(branchList);

        ManorConfig shareTypeConfig = manorConfigMapper.selectByType(1);

        ArrayList<ManorFlower> leftFlowers = new ArrayList<>();
        // 系统赠送左花田花朵数
        for (int i = 0; i < manorConfigMapper.selectByType(3).getValue(); i++) {
            ManorFlower manorFlower = new ManorFlower();
            manorFlower.setType(FLOWER_TYPE_SELF);
            manorFlower.setShareType(shareTypeConfig.getValue());
            manorFlower.setFloristUid(uid);
            manorFlower.setPerishTime(ManorDateUtils.getFlowerPerishDate(FLOWER_DEFAULT_DAYS));
            leftFlowers.add(manorFlower);
        }
        plantFlower(uid,FLOWER_LOCATION_LEFT,leftFlowers);

        // 系统赠送右花田花朵数
        ArrayList<ManorFlower> rightFlowers = new ArrayList<>();
        for (int i = 0; i < manorConfigMapper.selectByType(5).getValue(); i++) {
            ManorFlower manorFlower = new ManorFlower();
            manorFlower.setType(FLOWER_TYPE_SELF);
            manorFlower.setShareType(shareTypeConfig.getValue());
            manorFlower.setFloristUid(uid);
            manorFlower.setPerishTime(ManorDateUtils.getFlowerPerishDate(FLOWER_DEFAULT_DAYS));
            rightFlowers.add(manorFlower);
        }
        plantFlower(uid,FLOWER_LOCATION_RIGHT,rightFlowers);


        logger.info("黄金庄园-初始化分支节点 : 成功!!! 用户["+uid+"]插入"+DEFAULT_LOCATIONS+"个初始节点, 共计耗时:"+(System.currentTimeMillis()-startTime));
    }


    /**
     * 用户种植花朵
     */
    @Transactional
    public void plantFlower(Integer uid,Integer location ,List<ManorFlower> flowers){
        long startTime = System.currentTimeMillis();
        logger.info("黄金庄园-种植花朵 : 用户["+uid+"] location["+location+"], 种植["+flowers.size()+"]朵花");

        // 种植数量为0
        if (flowers.size() < 1) return;

        // 查询用户location下最下级branch
        ManorFlowerBranch branch = manorFlowerBranchMapper.selectLowestByUidAndLocation(uid,location);
        for (ManorFlower flower : flowers) {
            flower.setUid(null);
            flower.setBranchId(branch.getId());
            flower.setFloristUid(uid);
        }
        logger.info("黄金庄园-种植花朵 : 花朵列表:"+ JSON.toJSON(flowers));
        int insertCount =manorFlowerMapper.insertBatch(flowers);

        logger.info("黄金庄园-种植花朵 : 种植花朵成功,共种植["+insertCount+"]朵花, 耗时"+(System.currentTimeMillis()-startTime));
    }

    /**
     * 迁移用户到指定目标下, 并种植种子
     * @param parentUid     上级uid
     * @param location      迁移位置
     * @param subUid        下级uid
     */
    @Transactional
    public void migrateBranch(Integer parentUid,Integer location,Integer subUid){
        logger.info("黄金庄园-迁移节点 将下级用户["+subUid+"], 迁移到上级用户["+parentUid+"]的 ["+location+"]位置下");
        long startTime = System.currentTimeMillis();

        String zid = null;
        // 根据父级uid 和 location 找到左下叶子节点 作为父级节点
        ManorFlowerBranch parentBranch = manorFlowerBranchMapper.selectLowestByUidAndLocation(parentUid, location);
        // 查询下级uid 下面所包含的所有下级
        List<ManorFlowerBranch> subBranchList = manorFlowerBranchMapper.selectAllSubBranchByUid(subUid);
        for (ManorFlowerBranch branch : subBranchList) {
            // 绑定上级节点与该节点的关系
            manorFlowerBranchMapper.insertMigrateChain(branch,parentBranch);
            branch.setLevel(parentBranch.getLevel()+branch.getLevel());
            if (branch.getLocation().equals(0) && branch.getUid().equals(subUid) ) {
                zid = branch.getZid();
            }
            if(branch.getUid().equals(subUid)){
                branch.setParentId(parentBranch.getId());
            }
        }

        // 批量所有下级节点
        manorFlowerBranchMapper.updateBatch(subBranchList,zid,parentBranch, subUid);

        // 种植下级的种子到下级的节点
        try {
            ManorFlower seedingFlower = new ManorFlower();
            seedingFlower.setBranchId(parentBranch.getId());
            seedingFlower.setPerishTime(ManorDateUtils.getFlowerPerishDate(FLOWER_DEFAULT_DAYS));
            seedingFlower.setType(FLOWER_TYPE_SEEDING);
            seedingFlower.setUid(subUid);
            seedingFlower.setFloristUid(parentUid);
            seedingFlower.setShareType(MANOR_FLOWER_SHARE_TYPE_SUBCHAIN);
            manorFlowerMapper.insertSelective(seedingFlower);
        } catch (Exception e) {
            logger.error("黄金庄园-迁移节点 种植种子失败! 将下级用户["+subUid+"], 迁移到上级用户["+parentUid+"]的 ["+location+"]位置下",e);
        }


        logger.info("黄金庄园-迁移节点 迁移完成, 更新["+subBranchList.size()+"]个子节点 共计耗时"+(System.currentTimeMillis()-startTime));
    }

    /**
     * 根据uid更新该uid的种子为花朵
     */
    @Transactional
    public void updateFlowerTypeByUid(Integer uid,Integer flowerType) {
        logger.info("黄金庄园-更新花朵类型 : 更新用户["+uid+"]的花朵类型为["+flowerType+"]");

        int updateCount = manorFlowerMapper.updateTypeByUid(uid,flowerType);

        System.out.println("黄金庄园-更新花朵类型 : 更新完成,共更新["+updateCount+"]朵花");
    }
}


