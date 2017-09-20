package com.xmniao.xmn.core.manor.service;

import com.xmniao.xmn.core.exception.CustomException;
import com.xmniao.xmn.core.manor.dao.ManorFlowerBranchMapper;
import com.xmniao.xmn.core.manor.dao.ManorInfoDao;
import com.xmniao.xmn.core.manor.entity.ManorFlowerBranch;
import com.xmniao.xmn.core.manor.entity.ManorFlowerCount;
import com.xmniao.xmn.core.manor.entity.TManorInfo;
import com.xmniao.xmn.core.manor.entity.treant.Chart;
import com.xmniao.xmn.core.manor.entity.treant.Node;
import com.xmniao.xmn.core.manor.entity.treant.Text;
import com.xmniao.xmn.core.manor.entity.treant.Treant;
import com.xmniao.xmn.core.reward_dividends.dao.BursEarningsRelationDao;
import com.xmniao.xmn.core.reward_dividends.entity.BursEarningsRelation;
import com.xmniao.xmn.core.xmnburs.dao.BursDao;
import com.xmniao.xmn.core.xmnburs.entity.Burs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yang.qiang on 2017/8/2.
 */
@Service
public class ManorRelationService {

    @Autowired
    private BursDao bursDao;
    @Autowired
    private ManorFlowerBranchMapper manorFlowerBranchMapper;
    @Autowired
    private BursEarningsRelationDao bursEarningsRelationDao;
    @Autowired
    private ManorInfoDao manorInfoDao;

    /** 根据 uname 查询用户信息 */
    public Burs queryUser(String uname) {
        return bursDao.queryBurByUname(uname);
    }

    /** 根据 uid 查询用户 */
    public Burs queryUser(Integer uid ){
        return  bursDao.queryBurByUid(uid);
    }

    /** 查询花朵关系链 */
    public Treant queryFlowerRelation(Burs user) {

        // 初始化 Chart 设置结构图属性
        Node nodeStructure = new Node(new Text("("+user.getUid()+")"+user.getNname(),user.getUname()));

        // 查询所有下级节点
        LinkedList<ManorFlowerBranch> branchs = new LinkedList<>(manorFlowerBranchMapper.selectAllSubBranch(user.getUid()));

        if(branchs.size() < 3){
            throw new CustomException("该用户没有庄园信息");
        }

        // 加载用户信息
        loadUserInfo(branchs);

        // HashMap
        HashMap<String, Node> nodeBuffer = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            ManorFlowerBranch branch = branchs.pop();
            Node subNode = new Node(branch.getId(), new Text("(" + branch.getUid() + ")" + branch.getNname(), branch.getUname()),true);
            TreanUtils.insertNode(nodeStructure, subNode);
            nodeBuffer.put(subNode.getHtmlId(),subNode);
        }

        List<Node> nodes = TreanUtils.convertNodes(nodeBuffer,branchs);
        TreanUtils.packagingNode(nodeBuffer,nodes);

        return new Treant(new Chart(),nodeStructure);
    }

    /** 查询用户关系链结构 */
    public Treant queryUserRelations(Burs burs) {
        // 查询所有关系链中的用户
        LinkedList<BursEarningsRelation> allUsers = new LinkedList<>(bursEarningsRelationDao.selectSubNodeList(burs.getUid()));
        loadUserInfoByRelation(allUsers);

        HashMap<Integer, TManorInfo> manorMap = new HashMap<>();
        if (allUsers.size()>0) {
            for (TManorInfo manorInfo : manorInfoDao.selectByUids(allUsers)) {
                manorMap.put(manorInfo.getUid(),manorInfo);
            }
        }

        // 创建初始节点
        BursEarningsRelation pop = allUsers.pop();
        Node initNode = new Node(pop.getUid() + "", new Text("(" + pop.getUid() + ")" + pop.getNname(), pop.getUname()),true);
        TManorInfo manorInfo = manorMap.get(pop.getUid());
        if (manorInfo == null) {
            initNode.setHtmlClass("manor-unactivated");
        }else {
            initNode.setHtmlClass("manor-activated");
        }


        // 创建缓存
        HashMap<String, Node> nodeBuffer = new HashMap<>();
        nodeBuffer.put(initNode.getHtmlId(),initNode);



        List<Node> nodes = TreanUtils.convertNodesByRelation(nodeBuffer,allUsers,manorMap);


        TreanUtils.packagingNode(nodeBuffer,nodes);

        return new Treant(new Chart(),initNode);
    }

    private void loadUserInfoByRelation(List<BursEarningsRelation> allUsers) {
        HashMap<Integer, Burs> userBufferMap = new HashMap<>();
        for (BursEarningsRelation user : allUsers) {
            Burs burs = userBufferMap.get(user.getUid());
            if (burs == null) {
                burs = bursDao.queryBurByUid(user.getUid());
            }
            user.setUname(burs.getUname());
            user.setNname(burs.getNname());
        }
    }

    /** 加载用户信息 */
    public void loadUserInfo(List<ManorFlowerBranch> branchList ){
        HashMap<Integer, Burs> userBufferMap = new HashMap<>();
        for (ManorFlowerBranch branch : branchList) {
            Burs burs = userBufferMap.get(branch.getUid());
            if (burs == null) {
                burs = bursDao.queryBurByUid(branch.getUid());
            }
            branch.setUname(burs.getUname());
            branch.setNname(burs.getNname());
        }

    }

    public Burs querySupper(Burs user) {
        Integer parentUid = bursEarningsRelationDao.selectParentIdByOriented(user.getUid(),9);
        return bursDao.queryBurByUid(parentUid);
    }

    /** 根据 uid或uname查询 上级或本人*/
    public Burs queryUser(Integer uid, String uname, boolean queryParent) {
        Burs burs;
        if (uid != null){
            burs = bursDao.queryBurByUid(uid);
            if (burs == null) throw new CustomException("没有查询到用户编号为:"+uid+"的用户!");
        }else {
            burs = bursDao.queryBurByUname(uname);
            if (burs == null) throw new CustomException("没有查询到帐号为:"+uname+"的用户!");
        }

        if(queryParent){
            Burs superBurs = querySupper(burs);
            if (superBurs == null) throw new CustomException("该用户没有上级");
            return superBurs;
        }else {
            return burs;
        }
    }

    /** 查询节点参数 */
    public ManorFlowerBranch queryBranch(String branchId) {
        return manorFlowerBranchMapper.selectByPrimaryKey(branchId);
    }

    /** 查询所有花朵*/
    public ManorFlowerCount countAllFlowerInBranch(ManorFlowerBranch branch) {
        ManorFlowerCount manorFlowerCount = manorFlowerBranchMapper.countFlowerByTypeInBranch(branch, new int[]{1,2}, 4, new int[]{1});
        ManorFlowerCount systemCount = manorFlowerBranchMapper.countFlowerByTypeAndFloristInBranch(branch, new int[]{1, 2}, branch.getUid(), 4, new int[]{2});
        manorFlowerCount.addCount(systemCount.getCount());

        return manorFlowerCount;
    }

    /** 查询自己种植的花朵数*/
    public ManorFlowerCount countSelfFlowerInBranch(ManorFlowerBranch branch) {
        int[] type = {1};
        int[] shareTypes = {1,2};
        return manorFlowerBranchMapper.countFlowerByTypeAndFloristInBranch(branch,type,branch.getUid(),4,shareTypes);
    }

    /** 查询自己种植的花苗 */
    public ManorFlowerCount countSelfSeedingInBranch(ManorFlowerBranch branch) {
        int[] type = {0};
        int[] shareTypes = {1,2};
        return manorFlowerBranchMapper.countFlowerByTypeAndFloristInBranch(branch,type,branch.getUid(),4,shareTypes);
    }

    /** 查询仅计算本人的系统赠送花朵数*/
    public ManorFlowerCount countSelfSystemGiveInBranch(ManorFlowerBranch branch) {
        int[] type = {2};
        int[] shareTypes = {2};
        return manorFlowerBranchMapper.countFlowerByTypeAndFloristInBranch(branch,type,branch.getUid(),4,shareTypes);
    }
}
