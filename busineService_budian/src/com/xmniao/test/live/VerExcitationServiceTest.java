package com.xmniao.test.live;

import com.xmniao.domain.live.LivePrivilege;
import com.xmniao.domain.live.TVerExcitationDetail;
import com.xmniao.service.live.VerExcitationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yang.qiang on 2017/5/29.
 * V客充值奖励 测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:conf/busine-base.xml")
public class VerExcitationServiceTest {
    @Autowired
    private VerExcitationService verExcitationService;
    private final Logger logger = LoggerFactory.getLogger(VerExcitationServiceTest.class);

    @Test
    public void testPlanA(){

        // 奖励方案详情列表 缓存Map
        Map<Integer,List<TVerExcitationDetail>> excitationDetailsMap = new HashMap<>();

        // 查询满足奖励条件的方案A充值记录
        List<LivePrivilege> livePrivileges = verExcitationService.queryPlanA();
        for (LivePrivilege livePrivilege : livePrivileges) {
            verExcitationService.receiveExcitation(excitationDetailsMap, livePrivilege);
        }

    }
    @Test
    public void receivePlanA() throws CloneNotSupportedException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        ArrayList<Map<String, String>> maps = verExcitationService.receiveExcitationOfPlanA(604809);
        System.out.println(maps);

    }
}
