package com.xmniao.xmn.core.live_anchor.controller;

import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.live_anchor.entity.BLiver;
import com.xmniao.xmn.core.live_anchor.service.LeaderPerformanceService;
import com.xmniao.xmn.core.xmnburs.entity.Burs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yang.qiang on 2017/4/27.
 */
@Controller
@RequestMapping(value = "leader-performnace")
public class LeaderPerformanceController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LeaderPerformanceService leaderPerformanceService;

    /**
     * 团队等级  :   个人业绩 + 下级业绩 + 归属下级业绩
     * 下级     :   所有下级
     * 归属下级  :   所有通过 indirect_uid,关联过来的b_liver 及其下线
     * 直属下级  :   直属下级(一层, 直接下线)列表
     * @param uid
     */
    @RequestMapping(value = "info",method = RequestMethod.GET)
    @ResponseBody
    public Resultable info_get(Integer uid){
        logger.info("调用 接口[leader-performnace/info GET] 参数 phone:" + uid);
        Resultable result =new Resultable(false,"请求失败");
        try {
            HashMap<String, Object> resultMap = new HashMap<>();

            // 查询根据uid 查询一个b_liver
            BLiver bLiver = leaderPerformanceService.queryBliver(uid);
            if (bLiver == null) {
                result.setMsg("未查询到用户");
                return result;
            }
            resultMap.put("bLiver",bLiver);

            // 根据uid, 加载该用户详情
            Burs burs = leaderPerformanceService.queryBurs(uid);
            burs.setPassword(null);
            resultMap.put("burs",burs);

            // 根据uid, 加载b_liver 上级
            resultMap.put("superior1",leaderPerformanceService.querySuperiors(bLiver, 1));
            // 根据uid, 加载b_liver 上上级
            resultMap.put("superior2",leaderPerformanceService.querySuperiors(bLiver, 2));

            // 根据indirect_uid, 加载业绩归属用户
            resultMap.put("indirectUrs",leaderPerformanceService.queryBurs(bLiver.getIndirectUid()));


            // 根据uid, 下级列表
            List<BLiver> subordinateList =  leaderPerformanceService.querySubordinate(uid);
            // 根据uid, 查询归属下级列表
            List<BLiver> indrectSobordinateList = leaderPerformanceService.queryIndirectSubordinate(uid);

            // 根据 下级列表 统计所有下级总人数
            resultMap.put("countSubordinate",subordinateList.size());
            // 根据 归属下级列表 统计业绩下级人数
            resultMap.put("countInrectSobordinate",indrectSobordinateList.size());
            // 根据uid, 统计直属下级总人数
            resultMap.put("countDirectSubordinate",leaderPerformanceService.countDirectSubordinates(uid));

            // 根据 下级列表 统计直属下级总业绩(算上自己)
            ArrayList<BLiver> teamList = new ArrayList<>();
            teamList.addAll(subordinateList);
            teamList.add(bLiver);
            Double teamPerformance = leaderPerformanceService.queryTeamPerformance(teamList);
            resultMap.put("teamPerformance",teamPerformance);

            // 根据 归属下级 列表统计归属下级总业绩
            Double indrectTeamPerformance = leaderPerformanceService.queryTeamPerformance(indrectSobordinateList);
            resultMap.put("indrectTeamPerformance",indrectTeamPerformance);

            // 加载团队等级 根据总业绩(直属总业绩+归属总业绩)
            resultMap.put("teamLevel", leaderPerformanceService.queryTeamLevel(
                    teamPerformance + indrectTeamPerformance));

            result.setMsg("请求成功!");
            result.setSuccess(true);
            result.setData(resultMap);
        } catch (Exception e) {
            result.setMsg("请求失败");
            result.setSuccess(false);
            logger.error("调用 接口[leader-performnace/info GET] 出现异常",e);
        }

        return result;
    }


    // 获取直属下级(一层)
    @RequestMapping(value = "subordinate/direct",method = RequestMethod.GET)
    @ResponseBody
    public Resultable subordinateDirect_get(Integer uid){
        logger.info("调用[获取直接下级(一层) leader-performnace/subordinate/direct GET], 参数:" + uid);
        Resultable result =new Resultable();
        try {

            List<BLiver> liverList = leaderPerformanceService.queryDirectSubordinates(uid);
            List<Map<String,String>> resultData = leaderPerformanceService.queryLiverInfo(liverList);

            result.setData(resultData);
            result.setMsg("请求成功");
            result.setSuccess(true);
        } catch (Exception e) {
            result.setMsg("请求失败");
            result.setSuccess(false);
            logger.error("调用 接口[leader-performnace/info GET] 出现异常",e);
        }
        return result;
    }


    /**
     * @name        获取所有下级(多层)
     * @description 获取所有下级(多层)
     * @url         leader-performnace/subordinate/all
     * @method      GET
     */
    @ResponseBody
    @RequestMapping(value = "subordinate/all",method = RequestMethod.GET)
    public Resultable subordinateAll_get(Integer uid) throws Exception {
        logger.info("调用[获取所有下级(多层) leader-performnace/subordinate/all GET] 参数:" + uid);
        Resultable result =new Resultable();
        try {

            List<BLiver> bLivers = leaderPerformanceService.querySubordinate(uid);
            List<Map<String, String>> resultData = leaderPerformanceService.queryLiverInfo(bLivers);

            result.setData(resultData);
            result.setMsg("请求成功");
            result.setSuccess(true);
        } catch (Exception e) {
            result.setMsg("请求失败");
            result.setSuccess(false);
            logger.error("调用 接口[获取所有下级(多层) leader-performnace/subordinate/all GET] 出现异常",e);
        }
        return result;


    }

    /**
     * @name        获取业绩下层
     * @description 获取归属下级信息
     * @url         leader-performnace/subordinate/indirect
     * @method      GET
     */
    @ResponseBody
    @RequestMapping(value = "subordinate/indirect",method = RequestMethod.GET)
    public Resultable subordinateIndirect_get(Integer uid) throws Exception {
        logger.info("调用[获取业绩下层 leader-performnace/subordinate/indirect GET] 参数:" + uid);
        Resultable result =new Resultable();
        try {

            List<BLiver> bLivers = leaderPerformanceService.queryIndirectSubordinate(uid);
            List<Map<String, String>> resultData = leaderPerformanceService.queryLiverInfo(bLivers);

            result.setData(resultData);
            result.setMsg("请求成功");
            result.setSuccess(true);
        } catch (Exception e) {
            result.setMsg("请求失败");
            result.setSuccess(false);
            logger.error("调用 接口[获取业绩下层 leader-performnace/subordinate/indirect GET] 出现异常",e);
        }
        return result;
    }

    /**
     * @name        模糊匹配用户手机号
     * @description 模糊匹配用户手机号
     * @url         leader-performnace/search/phone
     * @method      GET
     */
    @ResponseBody
    @RequestMapping(value = "search/phone",method = RequestMethod.GET)
    public Resultable searchPhone(String phone) throws Exception {
        logger.info("调用[模糊匹配用户手机号 leader-performance/search/phone GET] 参数:" + phone);
        Resultable result =new Resultable();
        try {
            List<HashMap<String, String>> resultData = new ArrayList<>();
            List<BLiver> bLivers = leaderPerformanceService.likeLiverByPhone(phone);
            for (BLiver bLiver : bLivers) {
                HashMap<String, String> liverInfo = new HashMap<>();
                Burs burs = leaderPerformanceService.queryBurs(bLiver.getUid());
                liverInfo.put("name",burs.getNname());
                liverInfo.put("phone",bLiver.getPhone());
                liverInfo.put("uid",bLiver.getUid().toString());
                resultData.add(liverInfo);
            }

            result.setData(resultData);
            result.setMsg("请求成功");
            result.setSuccess(true);
        } catch (Exception e) {
            result.setMsg("请求失败");
            result.setSuccess(false);
            logger.error("调用 接口[模糊匹配用户手机号 leader-performance/search/phone GET] 出现异常",e);
        }
        return result;

    }

    /**
     * @name        模糊匹配用户uid
     * @description 模糊匹配用户uid
     * @url         leader-performance/search/uid
     * @method      GET
     */
    @ResponseBody
    @RequestMapping(value = "search/uid",method = RequestMethod.GET)
    public Resultable searchUid(String uid) throws Exception {
        logger.info("调用[模糊匹配用户uid leader-performance/search/uid GET] 参数:" + uid);
        Resultable result =new Resultable();
        try {
            List<HashMap<String, String>> resultData = new ArrayList<>();

            List<BLiver> bLivers = leaderPerformanceService.likeLiverByUid(uid);
            for (BLiver bLiver : bLivers) {
                HashMap<String, String> liverInfo = new HashMap<>();
                Burs burs = leaderPerformanceService.queryBurs(bLiver.getUid());
                liverInfo.put("name",burs.getNname());
                liverInfo.put("phone",bLiver.getPhone());
                liverInfo.put("uid",bLiver.getUid().toString());
                resultData.add(liverInfo);
            }

            result.setData(resultData);
            result.setMsg("请求成功");
            result.setSuccess(true);
        } catch (Exception e) {
            result.setMsg("请求失败");
            result.setSuccess(false);
            logger.error("调用 接口[模糊匹配用户uid leader-performance/search/uid GET] 出现异常",e);
        }
        return result;


    }



}
