package com.xmniao.service.live;

import com.xmniao.common.MapBeanUtil;
import com.xmniao.dao.coupon.TCouponDetailMapper;
import com.xmniao.dao.coupon.TCouponMapper;
import com.xmniao.dao.live.LivePrivilegeDao;
import com.xmniao.dao.live.TVerExcitationDetailDao;
import com.xmniao.dao.live.TVerExcitationReceiveDao;
import com.xmniao.domain.coupon.TCoupon;
import com.xmniao.domain.coupon.TCouponDetail;
import com.xmniao.domain.live.LivePrivilege;
import com.xmniao.domain.live.TVerExcitationDetail;
import com.xmniao.domain.live.TVerExcitationReceive;
import com.xmniao.domain.urs.BUrs;
import com.xmniao.exception.CustomException;
import com.xmniao.urs.dao.BUrsMapper;
import com.xmniao.urs.dao.UrsEarningsRankDao;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by yang.qiang on 2017/5/29.
 * V客充值奖励 服务类
 */
@Service
public class VerExcitationService  {
    private final Logger logger = LoggerFactory.getLogger(VerExcitationService.class);

    @Autowired
    private LivePrivilegeDao livePrivilegeDao;
    @Autowired
    private TVerExcitationDetailDao tVerExcitationDetailDao;
    @Autowired
    private TVerExcitationReceiveDao tVerExcitationReceiveDao;
    @Autowired
    private UrsEarningsRankDao ursEarningsRankDao;
    @Autowired
    private BUrsMapper bUrsMapper;
    @Autowired
    private TCouponMapper tCouponMapper;
    @Autowired
    private TCouponDetailMapper tCouponDetailMapper;




    /**
     * 查询满足发放奖励的方案A
     * @return
     */
    public List<LivePrivilege> queryPlanA(){
        logger.info("查询满足发放奖励的方案A充值记录");
        return livePrivilegeDao.selectRechargeRecordListForPlanA();
    }

    /**
     * 根据等级id 查询方案A奖励细节
     * @param rankId
     */
    public List<TVerExcitationDetail> queryExcitationDetailForPlanA(Integer rankId){
        logger.info("查询奖励方案A的奖励细节 rankId" + rankId);
        return tVerExcitationDetailDao.queryExcitationDetailsForPlanA(rankId);
    }

    /**
     * 根据用户id查询该用户的等级id
     * @param uid
     * @return
     */
    public Integer queryUserRankId(Integer uid){
        logger.info("查询用户的等级id, uid="+uid);
        return ursEarningsRankDao.selectRankidByUid(uid,4);
    }


    /**
     * 封装方案A 待发送会员奖励记录
     * @param livePrivilege
     * @param excitationDetails
     * @return
     */
    public static List<TVerExcitationReceive> packagingExcitationReceiveOfPlanA(LivePrivilege livePrivilege, List<TVerExcitationDetail> excitationDetails) {
        ArrayList<TVerExcitationReceive> receives = new ArrayList<>();

        // 封装参数
        for (TVerExcitationDetail excitationDetail : excitationDetails) {
            TVerExcitationReceive receive = new TVerExcitationReceive();
            receive.setOrderNo(livePrivilege.getOrderNo());
            receive.setUid(livePrivilege.getUid());
            receive.setProjectName("A");
            receive.setCid(excitationDetail.getCid());
            receive.setNum(excitationDetail.getNum().byteValue());
            receive.setType((byte) 1);
            receive.setStatus(0);
            receive.setExcitationDate(new Date());
//            receive.setReceiveDate(new Date());

            receives.add(receive);
        }

        return receives;
    }

    /**
     * 批量插入领取记录
     * @param excitationReceviceList
     */
    public void insertExcitationReceviceBatch(List<TVerExcitationReceive> excitationReceviceList) {
        tVerExcitationReceiveDao.addBatch(excitationReceviceList);
    }

    /**
     * 更新领取期数
     * @param livePrivilege
     */
    public void updateReceivedPrivilege(LivePrivilege livePrivilege){
        livePrivilegeDao.updateCurPeriodExcitation(livePrivilege);
    }

    /**
     * 发放奖励方案A
     * @param excitationDetailsMap 奖励方案细节, 用做缓存
     * @param livePrivilege         充值记录信息
     */
    public void receiveExcitation(Map<Integer, List<TVerExcitationDetail>> excitationDetailsMap, LivePrivilege livePrivilege) {
        try {
            // 获取奖励方案细节
            Integer rankId = queryUserRankId(livePrivilege.getUid());

            List<TVerExcitationDetail> excitationDetails = excitationDetailsMap.get(rankId);
            if (excitationDetails == null) {
                excitationDetails = queryExcitationDetailForPlanA(rankId);
                excitationDetailsMap.put(rankId, excitationDetails);
            }

            // 封装领取记录信息
            List<TVerExcitationReceive> excitationReceives = VerExcitationService.
                    packagingExcitationReceiveOfPlanA(livePrivilege, excitationDetails);

            // 批量插入发放信息
            insertExcitationReceviceBatch(excitationReceives);

            // 更新已领取期次
            updateReceivedPrivilege(livePrivilege);
        } catch (Exception e) {
            logger.error("发送奖励方案A失败! "+livePrivilege,e);
            e.printStackTrace();
        }
    }

    /**
     * 用户领取奖励A
     * @param uid
     */
    @Transactional
    public ArrayList<Map<String, String>> receiveExcitationOfPlanA(Integer uid) throws CloneNotSupportedException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ArrayList<Map<String, String>> couponMapList  = new ArrayList<>();

        // 查询用户可领取的记录
        List<TVerExcitationReceive> receives = tVerExcitationReceiveDao.selectUnclaimedByType(1,uid);
        if (receives.size() == 0) {
            logger.info("用户["+uid+"], 没有可领取的奖励方案A");
            return couponMapList;
        }

        logger.info("用户"+uid+"可领取的奖励方案A记录有 "+receives.size() +" 条");
        BUrs bUrs = bUrsMapper.selectByPrimaryKey(uid);

        // 封装coupon_detail列表
        List<TCouponDetail> couponDetails = new ArrayList<>();
        ArrayList<TCoupon> couponList = new ArrayList<>();
        for (TVerExcitationReceive receive : receives) {
            TCoupon couponInfo = tCouponMapper.selectByCid(receive.getCid());
            TCoupon tCoupon = (TCoupon)BeanUtils.cloneBean(couponInfo);
            tCoupon.setUseNum(receive.getNum().intValue());
            couponList.add(tCoupon);

            // 封装优惠劵详情列表
            for (Byte i = 0; i < receive.getNum(); i++) {
                TCouponDetail tCouponDetail = packagingCouponDetail(receive, bUrs, tCoupon);
                couponDetails.add(tCouponDetail);
            }
        }

        // 发放优惠劵(插入coupon_detail)
        logger.info("用户["+uid+"], 共计发放"+couponDetails.size()+"张优惠劵.");
        tCouponDetailMapper.insertBatch(couponDetails);

        // 更新领取记录表
        int i = tVerExcitationReceiveDao.updateStatusBatch(new Date(),new Integer(1), receives);
        if (i <= 0) {
            throw new CustomException("领取奖励方案A失败!");
        }

        // 返回已领取的优惠劵
        for (TCoupon tCoupon : couponList) {
            Map<String, String> couponMap = MapBeanUtil.convertMap(tCoupon,
                    "cid", "denomination","useNum","cname","condition");
            couponMapList.add(couponMap);
        }

        return couponMapList;

    }

    /**
     * 封装优惠劵领取信息
     * @param receive
     * @param bUrs
     * @param coupon
     * @return
     */
    public TCouponDetail packagingCouponDetail(TVerExcitationReceive receive, BUrs bUrs, TCoupon coupon) {
        TCouponDetail couponDetail = new TCouponDetail();

        try {
            couponDetail.setCid(receive.getCid());
            couponDetail.setDenomination(coupon.getDenomination());
            couponDetail.setSerial(generatorUUID());
            couponDetail.setGetWay((byte) 4);   //直接发放
            Integer dayNum = coupon.getDayNum();
            if (dayNum != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date startDate = sdf.parse(sdf.format(new Date()));
                couponDetail.setStartDate(startDate);
                couponDetail.setEndDate(DateUtils.addDays(startDate, dayNum));
            } else {
                couponDetail.setStartDate(coupon.getStartDate());//特别注意：优惠券有效期开始时间和结束时间一定要设置好，不然APP端获取不到优惠券的
                couponDetail.setEndDate(coupon.getEndDate());
            }
            couponDetail.setGetStatus((byte) 1);
            couponDetail.setUserStatus((byte) 0);//默认未使用
            couponDetail.setUid(bUrs.getUid());
            couponDetail.setPhone(bUrs.getPhone());
            couponDetail.setDateIssue(new Date());//发行时间是，指优惠券生成的时间
            couponDetail.setSendStatus(0);
            couponDetail.setCtype(coupon.getCtype());
        } catch (Exception e) {
            logger.error("封装优惠劵详情信息, 出现异常",e.getMessage());
            e.printStackTrace();
        }

        return couponDetail;
    }

    public static String  generatorUUID(){
        String uuid = UUID.randomUUID().toString();
        String[] uidArray=uuid.split("-");
        return uidArray[uidArray.length-1].concat(uidArray[uidArray.length-2]);
    }
}
