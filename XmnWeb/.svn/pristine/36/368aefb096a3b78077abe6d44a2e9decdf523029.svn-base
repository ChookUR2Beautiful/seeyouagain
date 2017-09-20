package com.xmniao.xmn.core.coupon.service;

import com.xmniao.xmn.core.base.MapBeanUtil;
import com.xmniao.xmn.core.base.Page;
import com.xmniao.xmn.core.coupon.controller.vo.CouponDetailParams;
import com.xmniao.xmn.core.coupon.dao.CouponDetailDao;
import com.xmniao.xmn.core.coupon.entity.TCouponDetail;
import com.xmniao.xmn.core.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by yang.qiang on 2017/8/11.
 */
@Service
public class CouponDetailService {

    @Autowired
    private CouponDetailDao couponDetailDao;

    /** 分页查询优惠劵 */
    public List<Map<String, Object>> queryCouponDetailList(CouponDetailParams params, Page page) {
        Map<String, String> paramMap = MapBeanUtil.convertMap(params, "uid", "cname", "phone", "ctype");
        return couponDetailDao.selectByPage(paramMap,page);
    }

    /** 统计已发送优惠劵
     * @param params*/
    public long countCouponDetail(CouponDetailParams params) {
        Map<String, String> paramMap = MapBeanUtil.convertMap(params, "uid", "cname", "phone", "ctype");
        return couponDetailDao.countCouponDetail(paramMap);

    }

    public void delectCoupon(Long cdid) {
        TCouponDetail tCouponDetail = couponDetailDao.selectByCdid(cdid);
        if (tCouponDetail.getUserStatus().intValue() != 0) {
            throw new CustomException("优惠劵已被使用, 无法删除!",1);
        }
        int count = couponDetailDao.deleteByCdid(cdid);
        if (count < 1){
            throw new CustomException("删除失败!",1);
        }
    }
}
