package com.xmniao.xmn.core.coupon.service.thread;

import com.xmniao.xmn.core.coupon.entity.TCoupon;
import com.xmniao.xmn.core.coupon.entity.TCouponDetail;
import com.xmniao.xmn.core.coupon.service.SystemPushCouponService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yang.qiang on 2017/5/26.
 */
public class SaveSystemPushInfoThread implements Runnable{
    private final Logger logger = LoggerFactory.getLogger(SaveSystemPushInfoThread.class);

    private SystemPushCouponService systemPushCouponService;
    private TCoupon couponInfo;
    private Integer userType;
    private ArrayList<HashMap<String, String>> selectUserList;
    private Integer rankid;
    
    private Integer getWay;//获取方式，1摇一摇，2满返，3短信获取 4直接发放,5：订单后刮优惠劵；6:分享后刮优惠劵;7.系统推送; 8.庄园激活奖励;9.新时尚大赛奖励

    /**
     * @param systemPushCouponService
     * @param selectUserList            已选取的用户列表{uid=xx,phone=xx}
     * @param couponInfo                优惠劵信息
     * @param userType                  用户类型 0:按等级  1:指定用户
     * @param rankid                    用户等级id
     * @param getWay					获取方式，1摇一摇，2满返，3短信获取 4直接发放,5：订单后刮优惠劵；6:分享后刮优惠劵;7.系统推送; 8.庄园激活奖励;9.新时尚大赛奖励
     */
    public SaveSystemPushInfoThread(SystemPushCouponService systemPushCouponService,
                                    ArrayList<HashMap<String, String>> selectUserList,
                                    TCoupon couponInfo,Integer userType,Integer rankid,Integer getWay) {
        this.selectUserList = selectUserList;
        this.systemPushCouponService = systemPushCouponService;
        this.couponInfo = couponInfo;
        this.userType = userType;
        this.rankid = rankid;
        this.getWay=getWay;
    }

    @Override
    public void run() {
        logger.info("开启线程"+Thread.currentThread()+"生成优惠劵, cid="+ couponInfo.getCid() +",useNum="+ couponInfo.getUseNum()+",getWay="+getWay);

        // 封装需要发放的优惠劵
        ArrayList<TCouponDetail> couponDetailList = new ArrayList<>();
        if(userType == 1 || userType == 2){
            logger.info("用户类型为指定用户, 共"+selectUserList.size()+"个用户需要发送优惠劵");
            couponDetailList = packagingCouponDetailList(couponInfo, selectUserList,getWay);

        }else if(userType == 0){
            List<HashMap<String, String>> users = systemPushCouponService.queryRankUsers(rankid, selectUserList);
            logger.info("用户类型为指定类型, 共"+users.size()+"个用户需要发送优惠劵");
            couponDetailList = packagingCouponDetailList(couponInfo, users,getWay);
        }

        logger.info("一共需要发放"+couponDetailList.size()+"张优惠劵");

        // 以20张为一批向数据库中插入优惠劵
        for (int i=0,quantity = 20; i<couponDetailList.size(); i+=quantity){
            int subIndex = couponDetailList.size()-1 < i+quantity ? couponDetailList.size() : i+quantity;
            logger.info("向coupon_detail中插入"+(subIndex-i) + "条数据");
            systemPushCouponService.addBatch(couponDetailList.subList(i,subIndex));
        }


    }

    /**
     * 封装优惠劵详情
     * @param coupon 优惠劵信息
     * @param users  {uid=xx,phone=xx}
     */
	private ArrayList<TCouponDetail> packagingCouponDetailList(TCoupon coupon, List<HashMap<String, String>> users,Integer getWay) {
        ArrayList<TCouponDetail> couponDetailList = new ArrayList<>();

        for (HashMap<String, String> userMap : users) {
            for (int i = 0; i < coupon.getUseNum(); i++) {
                TCouponDetail couponDetail = new TCouponDetail();
                // 封装优惠劵详情信息
                systemPushCouponService.setCouponDetailInfo(couponDetail, coupon, userMap.get("uid"), userMap.get("phone"),getWay);
                couponDetailList.add(couponDetail);
            }

        }
        return couponDetailList;
    }
}
