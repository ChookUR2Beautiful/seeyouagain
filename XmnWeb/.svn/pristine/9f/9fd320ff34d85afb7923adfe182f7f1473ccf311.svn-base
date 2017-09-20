/**
 * 
 */
package com.xmniao.xmn.core.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 
 * 项目名称：XmnWeb_vstar
 * 
 * 类名称：ListUtil
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年6月8日 下午4:12:14 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public class CollectionUtil {


/**
     * 求ls对ls2的差集,即ls中有，但ls2中没有的
     *
     * @param ls
     * @param ls2
     * @return
     */
    public static <T> List<T> diff(List<T> ls, List<T> ls2) {
        List<T> list = new ArrayList(Arrays.asList(new Object[ls.size()]));
        Collections.copy(list, ls);
        list.removeAll(ls2);
        return list;
    }

    /**
     * 求2个集合的交集
     *
     * @param ls
     * @param ls2
     * @return
     */
    public static <T> List<T> intersect(List<T> ls, List<T> ls2) {
        List<T> list = new ArrayList(Arrays.asList(new Object[ls.size()]));
        Collections.copy(list, ls);
        list.retainAll(ls2);
        return list;
    }

    /**
     * 求2个集合的并集
     *
     * @param ls
     * @param ls2
     * @return
     */
    public static <T> List<T> union(List<T> ls, List<T> ls2) {
        List<T> list = new ArrayList(Arrays.asList(new Object[ls.size()]));
        Collections.copy(list, ls);//将ls的值拷贝一份到list中
        list.removeAll(ls2);
        list.addAll(ls2);
        return list;
    }

}
