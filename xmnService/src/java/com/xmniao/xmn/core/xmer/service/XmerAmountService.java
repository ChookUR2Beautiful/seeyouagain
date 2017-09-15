package com.xmniao.xmn.core.xmer.service;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.market.common.Response;
import com.xmniao.xmn.core.util.StrUtils;
import com.xmniao.xmn.core.wealth.service.IncomeInfoService;
import com.xmniao.xmn.core.xmer.dao.UrsEarningsRelationDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 我的收入
 * Created by Administrator on 2017/5/3.
 */
@Service
public class XmerAmountService {

    private Logger log = Logger.getLogger(XmerAmountService.class);

    @Autowired
    private XmerService xmerService;
    @Autowired
    private XmerOrderRecordService xmerOrderRecordService;
    @Autowired
    private SessionTokenService sessionTokenService;

    public Object getMyAmount(BaseRequest baseRequest) {
        try {
            String sessionToken = baseRequest.getSessiontoken();
            String uid = sessionTokenService.getStringForValue(sessionToken) + "";
            if(uid.equals("")||uid.equals("null")){
                return new BaseResponse(ResponseCode.FAILURE, "获取我的收入失败,sessiontoken失效");
            }
            Map<Object, Object> result = amount(Integer.parseInt(uid));
            MapResponse mapResponse = new MapResponse(ResponseCode.SUCCESS, "获取我的收入成功");
            mapResponse.setResponse(result);
            return mapResponse;
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("获取我的收入失败", e);
            return new BaseResponse(ResponseCode.FAILURE, "获取我的收入失败");
        }
    }


    public Map<Object, Object> amount(Integer uid) {
        try {
            Map<Integer, Boolean> identityMap = xmerService.identityMap(uid);
//			1 寻觅客 2中脉 3 V客
            boolean isXmer = identityMap.get(1);
            boolean isV = identityMap.get(3);
            boolean isM = identityMap.get(2);
            boolean isAnchorV = identityMap.get(4);
            isXmer = isXmer || isAnchorV;
//            List<Integer> uids = xmerOrderRecordService.getUidsByUrs(uid, isXmer, isV, isM);
            List<Integer> uids = null;
                    //钱包类型 1 寻觅客 2中脉 3 V客
            Integer expansionType  = xmerOrderRecordService.getExpansionTypeByUid(uid, isXmer, isV, isM);

//        收益类型 1:saas收益    2:店铺流水  incomeType
            // 店铺流水总收入
            Double flow = xmerOrderRecordService.getTotalAmountByDate(uid, uids, expansionType, 2, null, null);
            // 软件销售总收入
            Double income = xmerOrderRecordService.getTotalAmountByDate(uid, uids, expansionType, 1, null, null);
            flow = flow == null ? 0 : flow;
            income = income == null ? 0 : income;
            Map<Object, Object> result = new HashMap<Object, Object>();
            result.put("sellerIncome", flow);  //店铺总流水提成
            result.put("softIncome", income);  //软件总销售分账
            double totalIncome = flow + income;
            result.put("totalIncome", totalIncome);  //总收入
            return result;
        } catch (Exception e) {
            log.warn("查询总收入错误");
            e.printStackTrace();
            return null;
        }
    }

}
