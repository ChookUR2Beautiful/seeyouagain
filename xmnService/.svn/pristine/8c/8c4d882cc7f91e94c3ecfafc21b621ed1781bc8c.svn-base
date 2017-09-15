package com.xmniao.xmn.core.xmer.service;

import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.thrift.ResponsePageList;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.xmer.dao.UrsEarningsRelationDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 订单流水记录（saas软件，店铺流水，）
 * Created by Administrator on 2017/5/24.
 */
@Service
public class XmerOrderRecordService {

    private Logger log = Logger.getLogger(XmerOrderRecordService.class);

    @Autowired
    private XmerService xmerService;
    @Autowired
    private UrsEarningsRelationDao ursEarningsRelationDao;
    @Autowired
    private ThriftWalletExpansionService thriftWalletExpansionService;



    /**
     *
     * @param uid
     * @param uids
     * @param expansionType  //钱包类型 1:寻蜜客   2: 中脉  3:V客
     * @param incomeType  //收益类型 1:saas收益    2:店铺流水
     * @param start
     * @param end
     * @return
     */
    public Double getTotalAmountByDate(Integer uid, List<Integer> uids, Integer expansionType,
                                       Integer incomeType,
                                       String start, String end) {
        String childrenIds = toChildIdsString(uids);
        ResponsePageList responsePageListTotal = thriftWalletExpansionService.getXmrIncomeList(uid, null, null,
                expansionType, incomeType, start, end, null, childrenIds);
        if (responsePageListTotal.getDataInfo().getState() == 0) {
            String totalAmount = responsePageListTotal.getDataInfo().getResultMap().get("totalAmount");
            return Double.parseDouble(totalAmount);
        } else {
            return null;
        }
    }
    // 获取流水根据类型（已分账）
    public ResponsePageList getOrderRecordByType(int type, Integer uid, List<Integer> uids, Integer expansionType, Integer incomeType,
												 Integer page, Integer pageSize, String startdate, String enddate) {
        String childrenIds = toChildIdsString(uids);
        switch(type){
            case 0://查询全部
                return thriftWalletExpansionService.getXmrIncomeList(
                        uid, page, pageSize,expansionType,
                        incomeType, null, null, null, childrenIds);
            case 1://查询本月收入
                return thriftWalletExpansionService.getXmrIncomeList(
                        uid, page, pageSize,expansionType,
                        incomeType,
                        DateUtil.format(DateUtil.getMonthFirstDay(DateUtil.now()),"yyyy-MM-dd"),
                        DateUtil.format(DateUtil.lastDayOfMonth(DateUtil.now()),"yyyy-MM-dd"),
                        null, childrenIds);
            case 2://查询上月收入
                return thriftWalletExpansionService.getXmrIncomeList(
                        uid, page, pageSize,expansionType,
                        incomeType,
                        DateUtil.format(DateUtil.firstDayOfMonth(DateUtil.addMonth(DateUtil.now(), -1)),"yyyy-MM-dd"),
                        DateUtil.format(DateUtil.lastDayOfMonth(DateUtil.addMonth(DateUtil.now(), -1)),"yyyy-MM-dd"),
                        null, childrenIds);
            case 3://自定义查询收入
                Date sdate = DateUtil.parse(startdate, "yyyy-MM-dd");
                Date edate = DateUtil.parse(enddate, "yyyy-MM-dd");
                return thriftWalletExpansionService.getXmrIncomeList(
                        uid, page, pageSize,expansionType,
                        incomeType,
                        DateUtil.format(sdate, "yyyy-MM-dd"),
                        DateUtil.format(edate, "yyyy-MM-dd"),
                        null, childrenIds);
            default :
                throw new IllegalStateException("查询信息状态不对");
        }
    }

    // 软件销售总收入（总收入，本月收入，上个月收入）
    public Map<Object, Object> totalIncome(Integer uid, List<Integer> uids, Integer expansionType, Integer incomeType) {
        Double tincome = getTotalAmount(uid, uids, expansionType, incomeType);
        Double mincome = getTotalAmountCurrentMonth(uid, uids, expansionType, incomeType);
        Double upmincome = getTotalAmountLastMonth(uid, uids, expansionType, incomeType);
        Map<Object, Object> resultMap = new HashMap<>();
        resultMap.put("totalincome", tincome == null ? 0d : tincome);  //总收入
        resultMap.put("mouthincome", mincome == null ? 0d : mincome);  //本月
        resultMap.put("upmouthincome", upmincome == null ? 0d : upmincome);  //上个月
        return resultMap;
    }
    // 销售总收入
    public Double getTotalAmount(Integer uid, List<Integer> uids, Integer expansionType, Integer incomeType) {
        return getTotalAmountByDate(uid, uids, expansionType, incomeType, null, null);
    }
    // 销售收入（当月）
    public Double getTotalAmountCurrentMonth(Integer uid, List<Integer> uids, Integer expansionType, Integer incomeType) {
        return getTotalAmountByDate(uid, uids, expansionType, incomeType,
                DateUtil.format(DateUtil.firstDayOfMonth(DateUtil.now()),"yyyy-MM-dd"),
                DateUtil.format(DateUtil.lastDayOfMonth(DateUtil.now()),"yyyy-MM-dd")
        );
    }
    // 销售收入（上个月）
    public Double getTotalAmountLastMonth(Integer uid, List<Integer> uids, Integer expansionType, Integer incomeType) {
        return getTotalAmountByDate(uid, uids, expansionType, incomeType,
                DateUtil.format(DateUtil.firstDayOfMonth(DateUtil.addMonth(DateUtil.now(), -1)),"yyyy-MM-dd"),
                DateUtil.format(DateUtil.lastDayOfMonth(DateUtil.addMonth(DateUtil.now(), -1)),"yyyy-MM-dd")
        );
    }
    // 下级和下下级uid
    public String toChildIdsString(List<Integer> uids) {
        String childrenIds = null;  //
        if (uids != null && uids.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (Integer muid : uids) {
                sb.append(muid);
                sb.append(",");
            }
            childrenIds = sb.substring(0, sb.length() - 1);
        }
        return childrenIds;
    }
    // 获取钱包类型 1:寻蜜客   2:V客   3:中脉
    public Integer getExpansionTypeByUid(Integer uid) {
        Map<Integer, Boolean> identityMap = xmerService.identityMap(uid);
        // 1 寻觅客 2中脉 3 V客
        boolean isXmer = identityMap.get(1);
        boolean isV = identityMap.get(3);
        boolean isM = identityMap.get(2);
        boolean isAnchorV = identityMap.get(4);
        isXmer = isAnchorV || isXmer;
        return getExpansionTypeByUid(uid, isXmer, isV, isM);
    }
    // 获取钱包类型 1 寻觅客 2中脉 3 V客
    public Integer getExpansionTypeByUid(Integer uid, boolean isXmer, boolean isV, boolean isM) {
//        if (isV && isM) {
//            log.warn("用户身份有冲突：V客不能和脉客同时存在");
//        }
//        log.warn("用户身份：isXmer=" + String.valueOf(isXmer) + " isV=" + String.valueOf(isV) + " isM=" + String.valueOf(isM));
        Integer expansionType  = 1; //钱包类型 1:寻蜜客   2:V客   3:中脉
        if (isXmer) {
            expansionType = 1;
        } else if (isV) {
            expansionType = 3;
        } else if (isM) {
            expansionType = 2;
        } else {
//            throw new IllegalStateException("普通用户，uid=" + String.valueOf(uid));
        }
        return expansionType;
    }

    // 获取下级及下下级，用户uid列表
    public List<Integer> getUidsByUrs(Integer uid, boolean isXmer, boolean isV, boolean isM) {
        List<Integer> uids = new ArrayList<Integer>();
        List<Integer> uidToOnes = ursEarningsRelationDao.queryRelationList(uid);
        if (uidToOnes != null) {
            uids.addAll(uidToOnes);
        }
        return uids;
    }
}
