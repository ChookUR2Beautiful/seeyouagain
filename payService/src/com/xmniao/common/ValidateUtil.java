package com.xmniao.common;

import java.util.Map;

/**
 * 参数校验工具
 * Created by yang.qiang on 2017/4/12.
 */
public class ValidateUtil {

    /**
     * 检验一个map里面, 是否保存指定key的值
     * @param map
     * @param keys
     * @return
     */
    public static boolean validateNull(Map map,String... keys){
        if (map == null) {
            return false;
        }

        for (String key : keys) {
            if (map.get(key) == null) {
                return false;
            }
        }

        return true;
    }

}
