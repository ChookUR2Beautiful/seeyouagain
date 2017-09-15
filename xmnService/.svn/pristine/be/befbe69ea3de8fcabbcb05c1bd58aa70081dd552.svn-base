package com.xmniao.xmn.core.common.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * 业务异常
 * Created by Administrator on 2017/5/25.
 */
public class BisException extends Exception {

    private Map<String, Object> param = new HashMap<String, Object>();

    public BisException(String message) {
        super(message);
    }

    public void put(String key, Object value) {
        param.put(key, value);
    }

    public Object get(String key) {
        return param.get(key);
    }
}
