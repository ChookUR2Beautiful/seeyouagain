package com.xmniao.xmn.core.xmer.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.xmer.XmerDiscountRequest;
import com.xmniao.xmn.core.util.PropertiesUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 折扣
 * Created by Administrator on 2017/5/5.
 */
@Service
public class XmerDiscountService {

    private Logger log = Logger.getLogger(XmerDiscountService.class);

    @Autowired
    private PropertiesUtil propertiesUtil;

    public Object getDisCount(XmerDiscountRequest request) {
        try {
            String discount = getDiscountJsonByType(request.getType());
            JSONArray json = JSON.parseArray(discount);
            Object[] objs = json.toArray();
            List<String> discountList = new ArrayList<String>();
            DecimalFormat decimalFormat = new DecimalFormat("#.0");
            for (int i = 0; i < objs.length; i++) {
                Double number = Double.parseDouble(objs[i].toString());
                String snumberStr = decimalFormat.format(number);
                discountList.add(snumberStr);
            }
            MapResponse response = new MapResponse(ResponseCode.SUCCESS, "成功");
            Map<Object, Object> result = new HashMap<Object, Object>();
            result.put("discount", discountList);
            response.setResponse(result);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("获取折扣列表失败", e);
            return new BaseResponse(ResponseCode.FAILURE, "获取折扣列表失败");
        }
    }

    private String getDiscountJsonByType(int type) throws IOException {
        switch (type) {
            case 1:
                String discount =  propertiesUtil.getValue("discount", "conf_xmer.properties");
                return discount;
            default: throw new IllegalStateException("类型不对");
        }
    }
}
