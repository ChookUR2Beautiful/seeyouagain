package com.xmniao.xmn.core.live_anchor.service;

import com.xmniao.xmn.core.live_anchor.dao.BLiverDao;
import com.xmniao.xmn.core.live_anchor.dao.GroupLevelDao;
import com.xmniao.xmn.core.live_anchor.dao.TLivePayOrderDao;
import com.xmniao.xmn.core.live_anchor.entity.BLiver;
import com.xmniao.xmn.core.xmnburs.dao.BursDao;
import com.xmniao.xmn.core.xmnburs.entity.Burs;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by yang.qiang on 2017/4/27.
 */
@Service
public class LeaderPerformanceService {

    @Autowired
    private BLiverDao bLiverDao;
    @Autowired
    private BursDao bursDao;
    @Autowired
    private TLivePayOrderDao tLivePayOrderDao;
    @Autowired
    private GroupLevelDao groupLevelDao;

    /**
     * 根据uid 查询一个b_liver
     * @param uid   寻蜜鸟会员id
     * @return
     */
    public BLiver queryBliver(Integer uid){
        // 1. 根据uid查询一个 b_liver
        return bLiverDao.selectByUid(uid);
    }

    /**
     * 根据uid 查询一个b_urs
     * @param uid   寻蜜鸟会员id
     * @return
     */
    public Burs queryBurs(Integer uid){
        if (uid == null) {
            return null;
        }

        // 根据uid 查询一个 b_urs
        Burs burs = new Burs();
        burs.setUid(uid);
        return bursDao.getUrs(burs);
    }

    /**
     * 根据用户uid, 跨越层级 查询上级
     * @param bLiver   用户id
     * @param depth  上层
     * @return
     */
    public Burs querySuperiors(BLiver bLiver, Integer depth){
        String chain = bLiver.getUidRelationChain();
        if (chain == null) {
            return null;
        }
        // 切割 uidRelationChain 字段, 为uid列表
        String[] uids = chain.split(",");


        // 判断字段长度
        if (depth+1 > uids.length) {
            return null;
        }else {

            ArrayList<String> uidList = new ArrayList<>();
            Collections.addAll(uidList,uids);
            Collections.reverse(uidList);
            String uid = uidList.get(depth);

            // 调用 queryBurs(Integer uid)
            return queryBurs(Integer.valueOf(uid));
        }
    }

    /**
     * 根据uid 查询改liver的下级列表,不包括自己
     * @param uid
     * @return
     */
    public List<BLiver> querySubordinate(Integer uid) {
        ArrayList<BLiver> bLiverList = new ArrayList<>();

        // 直接通过语句查询
        bLiverList = bLiverDao.selectSubordinate(uid);

        return bLiverList;
    }

    /**
     * 根据uid, 查询该归属用户的列表
     * @param uid
     * @return
     */
    public List<BLiver> queryIndirectSubordinate(Integer uid) {
        ArrayList<BLiver> indirectSubordinateList = new ArrayList<>();
        // 查询所有归属用户
        List<BLiver> bLivers = bLiverDao.selectByIndirectUid(uid);
        indirectSubordinateList.addAll(bLivers);

        // 循环调用 querySubordinate(Integer uid)
        for (BLiver bLiver : bLivers) {
             indirectSubordinateList.addAll(querySubordinate(bLiver.getUid()));
        }

        return indirectSubordinateList;
    }



    /**
     * 统计直接下级(直接下线, 一层级)人数
     * @param uid
     * @return
     */
    public int countDirectSubordinates(Integer uid) {
        return bLiverDao.countDirectSubordinateByUid(uid);
    }


    /**
     * 查询一个团队的总业绩
     * @return
     */
    public Double queryTeamPerformance(List<BLiver> liverList){
        ArrayList<Integer> uids = new ArrayList<>();
        for (BLiver bLiver : liverList) {
            uids.add(bLiver.getUid());
        }

        if (uids.size() == 0) return 0.0;
        else return tLivePayOrderDao.statLiverPromance(uids);
    }

    /**
     * 根据uid 查询团队等级
     * @param performance   团队业绩
     * @return
     */
    public String queryTeamLevel(double performance){
        return groupLevelDao.queryLevelNameByProformance(performance);
    }

    /**
     * 根据uid查询 改用户的直接下级(一层)
     * @return
     * @param uid
     */
    public List<BLiver> queryDirectSubordinates(Integer uid) {
        return bLiverDao.selectDirectSubordinates(uid);
    }

    /**
     * 查询用户信息 : 用户编号, 用户名, 电话号码, 个人级别
     * @param liverList
     * @return
     */
    public List<Map<String,String>> queryLiverInfo(List<BLiver> liverList) {

        List<Map<String, String>> liverInfoList = new ArrayList<>();
        for (BLiver bLiver : liverList) {
            HashMap<String, String> liverInfo = new HashMap<>();
            liverInfo.put("uid",bLiver.getUid().toString());
            liverInfo.put("phone",bLiver.getPhone());
            liverInfo.put("level",queryLiverLevel(bLiver.getUid()));
            liverInfo.put("name",queryBurs(bLiver.getUid()).getNname());

            liverInfoList.add(liverInfo);
        }

        return liverInfoList;
    }

    /**
     * 查询用户V客用户等级
     * @param uid
     * @return
     */
    private String queryLiverLevel(Integer uid) {
        return bLiverDao.queryLiverLevel(uid);
    }

    /**
     * 模糊查询手机号
     * @param phone
     * @return
     */
    public List<BLiver> likeLiverByPhone(String phone) {
        if (StringUtils.isBlank(phone)) return new ArrayList<>();
        return bLiverDao.likeLiverByPhone(phone);
    }

    /**
     * 模糊查询用户id
     * @param uid
     * @return
     */
    public List<BLiver> likeLiverByUid(String uid) {
        if (StringUtils.isBlank(uid)) return new ArrayList<>();
        return bLiverDao.likeLiverByUid(uid);
    }
}
