package com.xmniao.xmn.core.manor.controller;

import com.xmniao.xmn.core.exception.CustomException;
import com.xmniao.xmn.core.manor.entity.HttpResult;
import com.xmniao.xmn.core.manor.entity.ManorFlowerBranch;
import com.xmniao.xmn.core.manor.entity.treant.Treant;
import com.xmniao.xmn.core.manor.service.ManorRelationService;
import com.xmniao.xmn.core.xmnburs.entity.Burs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * 黄金庄园-关系管理 Controller
 * Created by yang.qiang on 2017/8/2.
 */
@Controller
@RequestMapping("manor/relation")
public class ManorRelationController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ManorRelationService manorRelationService;

    @RequestMapping(value = "")
    public String relation(){
        return "golden_manor/manorRelation";

    }

    /** 花朵关系结构 */
    @ResponseBody
    @RequestMapping(value = "flower")
    public HttpResult flower(Integer uid, String uname,boolean queryParent){
        logger.info("调用[黄金庄园-关系管理-花朵关系结构], 参数 uid:"+uid + "  uname:" + uname);

        Treant treant = null;
        try {
            Burs burs = manorRelationService.queryUser(uid, uname, queryParent);
            treant = manorRelationService.queryFlowerRelation(burs);
        } catch (CustomException e){
            logger.error(e.getMessage());
            return new HttpResult(e.getCode(),e.getMessage());
        } catch (Exception e) {
            logger.error("调用失败!!!! [黄金庄园-关系管理-花朵关系结构], 参数 uid:"+uid + "  uname:" + uname,e);
            return new HttpResult(2,"查询失败!",treant);
        }
        return new HttpResult(0,"请求成功!",treant);
    }

    /** 用户关系结构 */
    @ResponseBody
    @RequestMapping(value = "users")
    public HttpResult users(Integer uid, String uname,boolean queryParent){
        logger.info("调用[黄金庄园-关系管理-用户关系结构], 参数 uid:"+uid + "  uname:" + uname);

        Treant treant= null;
        try {
            Burs burs = manorRelationService.queryUser(uid, uname, queryParent);
            treant = manorRelationService.queryUserRelations(burs);

        }catch (CustomException e){
            logger.error(e.getMessage());
            return new HttpResult(e.getCode(),e.getMessage());
        } catch (Exception e) {
            logger.error("调用失败!!! [黄金庄园-关系管理-用户关系结构], 参数 uid:"+uid + "  uname:" + uname,e);
            return new HttpResult(2,"查询失败!",treant);
        }

        return new HttpResult(0,"请求成功!",treant);
    }

    /** 花朵信息 */
    @ResponseBody
    @RequestMapping(value = "branch/info")
    public HttpResult branchInfo(String branchId){
        logger.info("调用[黄金庄园-关系管理-查询节点信息], 参数 branchId:"+branchId);
        HashMap<String, Object> content = new HashMap<>();


        try {
            ManorFlowerBranch branch = manorRelationService.queryBranch(branchId);
            content.put("branch",branch);
            Burs user = manorRelationService.queryUser(branch.getUid());
            content.put("user",user);
            // 自种种子数
            content.put("selfSeedling",manorRelationService.countSelfSeedingInBranch(branch).getCount());

            // 自种花朵数
            Integer selfFlower = manorRelationService.countSelfFlowerInBranch(branch).getCount();
            content.put("selfFlower", selfFlower);

            // 仅计算为自己的花朵数
            Integer selfSystemFlowers = manorRelationService.countSelfSystemGiveInBranch(branch).getCount();
            content.put("selfSystemGive", selfSystemFlowers);

            // 节点花朵数
            Integer allFlower = manorRelationService.countAllFlowerInBranch(branch).getCount();
            content.put("allFlower", allFlower);


        }catch (CustomException e){
            logger.error(e.getMessage());
            return new HttpResult(e.getCode(),e.getMessage());
        } catch (Exception e) {
            logger.error("调用失败!!! [黄金庄园-关系管理-查询节点信息], 参数 branchId:"+branchId,e);
            return new HttpResult(2,"查询失败!");
        }


        return new HttpResult(0,"请求成功!",content);
    }

    /** 查询黄金庄园用户信息 */
    public HttpResult userInfo(Integer uid){
        logger.info("调用[黄金庄园-关系管理-查询用户信息], 参数 uid:"+uid);
        HashMap<String, Object> content = new HashMap<>();
        try {
            Burs burs = manorRelationService.queryUser(uid);
            Burs burs1 = manorRelationService.querySupper(burs);
        }catch (CustomException e){
            logger.error(e.getMessage());
            return new HttpResult(e.getCode(),e.getMessage());
        } catch (Exception e) {
            logger.error("调用失败!!! [黄金庄园-关系管理-查询用户信息], 参数 uid:"+uid,e);
            return new HttpResult(2,"查询失败!");
        }


        return new HttpResult(0,"请求成功!",content);


    }
}
