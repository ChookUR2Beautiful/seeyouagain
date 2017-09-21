package com.xmniao.service.manor;

import com.xmniao.domain.manor.ManorFlowerCount;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 庄园花朵统计统计类
 * Created by yang.qiang on 2017/6/13.
 */
public class ManorFlowerStatUtils {

    /**
     * 统计全部花朵/花苗
     * @return
     */
    public static int statAll(List<ManorFlowerCount> flowerCountMaps){
        int countAll = 0;
        for (ManorFlowerCount flowerCount : flowerCountMaps) {
                countAll += flowerCount.getCount();
        }
        return countAll;
    }

    /**
     * 统计花苗/花朵数量
     * @param flowerCountMaps
     * @param location          花种植位置
     * @return
     */
    public static int statAll(List<ManorFlowerCount> flowerCountMaps,
                              Integer location){
        int countAll = 0;
        for (ManorFlowerCount flowerCount : flowerCountMaps) {
            if (flowerCount.getLocation().equals(location)) {
                countAll += flowerCount.getCount();
            }
        }
        return countAll;
    }

    /**
     * 获取统计结果
     * @param flowerCountMaps
     * @return
     */
    public static Map<String,String> statResult(List<HashMap<String, Integer>> flowerCountMaps){
        HashMap<String, String> statResult = new HashMap<>();
        for (HashMap<String, Integer> countResult : flowerCountMaps) {
            String key = countResult.get("location") + "-" + countResult.get("type");
            statResult.put(key, countResult.get("count")+"");
        }
        return statResult;
    }
}
