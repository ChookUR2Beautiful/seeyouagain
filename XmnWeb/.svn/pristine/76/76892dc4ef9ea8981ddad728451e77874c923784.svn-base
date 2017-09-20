package com.xmniao.xmn.core.businessman.controller;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.businessman.dao.SellerAccountDao;
import com.xmniao.xmn.core.businessman.entity.SellerDetailedForm;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.businessman.entity.TSellerAccount;
import com.xmniao.xmn.core.businessman.entity.TSellerLandmark;
import com.xmniao.xmn.core.businessman.service.SellerAccountService;
import com.xmniao.xmn.core.businessman.service.SellerAgioService;
import com.xmniao.xmn.core.businessman.service.SellerService;
import com.xmniao.xmn.core.businessman.util.SellerConstants;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.util.HashUtil;
import com.xmniao.xmn.core.util.StringUtils;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;
import com.xmniao.xmn.core.util.handler.annotation.RequestToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * 项目名称：XmnWeb
 * <p>
 * 类名称：SellerController
 * <p>
 * 类描述： 商家待审核
 * <p>
 * 创建人： chenerxin
 * <p>
 * 创建时间：2014年11月11日 下午10:03:47
 * <p>
 * Copyright (c) 深圳市寻蜜鸟有限公司-版权所有
 */
@RequestLogging(name = "商家信息待审核")
@Controller
@RequestMapping(value = "businessman/sellerPending")
public class SellerPendingController extends BaseController {

    @Autowired
    private SellerService sellerService;

/*	
	@Autowired
	private AreaService areaService;*/
    /**
     * 账号信息dao
     */
    @Autowired
    private SellerAccountDao sellerAccountDao;

    @Autowired
    private SellerAccountService sellerAccountService;

    @Autowired
    private SellerAgioService sellerAgioService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 初始化未审核商家信息
     */
    @RequestMapping(value = "init")
    public String init() {
        return "businessman/sellerPending";
    }

    /**
     * 获取审核商家列表
     *
     * @param seller
     * @return
     */
    @RequestMapping(value = "init/list")
    @ResponseBody
    public Object list(TSeller seller) {
        this.log.info("SellerPendingController-->list seller=" + seller);
        Pageable<TSeller> pageable = new Pageable<TSeller>(seller);
        pageable = sellerService.getSellerInfoList(seller);
        return pageable;
    }

    /**
     * @param request
     * @param response
     * @throws FileNotFoundException
     * @throws IOException
     */
    @RequestMapping(value = "export")
    public void export(TSeller seller, HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException {
        seller.setLimit(SellerConstants.PAGE_LIMIT_NO);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("list", sellerService.getSellerList(seller));
        doExport(request, response, "businessman/sellerPending.xls", params);
    }

    /**
     * 商家审批
     *
     * @param seller
     * @return
     */
    @RequestLogging(name = "商家消息待审核审批")
    @ResponseBody
    @RequestMapping(value = "updateSellerStatus")
    @RequestToken(removeToken = true, tokenName = "updateStatusToken")
    public Object updateSellerStatus(TSeller seller, HttpServletRequest request) {
        Resultable resultable = null;
        try {
            String ids = seller.getIds();
            log.info("[updateSellerStatus]待审核商家ids:" + ids);
            if (StringUtils.hasLength(ids)) {

                String[] objects = StringUtils.paresToArray(ids, ",");
                StringBuilder resultMsg = new StringBuilder();
                TSeller persistentSeller = null;
                List<TSeller> sellerList = new ArrayList<TSeller>();

                for (String sellerIdStr : objects) {
                    persistentSeller = sellerService.getObject(new Long(Integer.parseInt(sellerIdStr)));    //获取当前商家信息
                    sellerList.add(persistentSeller);
                    String result = sellerService.prePending(persistentSeller, seller.getStatus());            //检查商家信息完整性
                    resultMsg.append(result == null ? "" : result);
                }

                if (resultMsg.length() != 0) {
                    resultable = new Resultable(false, resultMsg.toString());//没有绑定合作商提示的信息
                } else {
                    int fail = 0;
                    StringBuilder failSeller = new StringBuilder();
                    seller.setUdate(new Date());
                    for (TSeller sellerInfo : sellerList) {                        //更新商家状态
                        if (sellerInfo.getStatus() != seller.getStatus()) {
                            try {
                                sellerService.updateSellerStatusOptimized(sellerInfo, seller);
                                sellerService.ledgerAndReturn(sellerInfo, seller);
                            } catch (Exception e) {
                                failSeller.append("商家【" + sellerInfo.getSellerid() + "-" + sellerInfo.getSellername() + "】审核失败\r\n");
                                log.error("商家【" + sellerInfo.getSellerid() + "-" + sellerInfo.getSellername() + "】审核失败", e);
                                fail++;
                            }
                        } else {
                            log.error("该商家【" + sellerInfo.getSellerid() + "】状态与当前审核状态一致，无需修改");
                        }
                    }
                    if (fail == 0) {
                        resultable = new Resultable(true, "批量审核操作成功！");
                    } else {
                        resultable = new Resultable(false, "共有" + fail + "家审核失败，分别是：" + failSeller);
                    }
                }

//				String[] objects = StringUtils.paresToArray(ids, ",");
//				sellerService.batchUpdateSellerStatusOptimized(objects, seller);
//				sellerService.ledgerAndReturn(objects,seller);
//				resultable = new Resultable(true, "操作成功！");
            } else {
                resultable = new Resultable(false, "操作失败！");
            }
        } catch (Exception e) {
            this.log.error("修改异常", e);
            sellerService.fireLoginEvent(StringUtils.addStrToStrArray(((ApplicationException) e).getLogInfo(), new ApplicationException("审核商家异常", e, new Object[]{seller, request}).getMessage()), 0);
            resultable = new Resultable(false, "操作失败！");
        }
        return resultable;

    }

    /**
     * 初始化批量不通过页面
     *
     * @author：zhou'sheng
     */
    @RequestMapping(value = "updateSellerStatus/sellerState")
    public ModelAndView sellerState() {
        ModelAndView modelAndView = new ModelAndView("businessman/examineinfoSeller");
        return modelAndView;
    }


    /**
     * 商家信息待审核->修改
     */
    @RequestMapping(value = "update/init")
    public ModelAndView updateInit(ModelAndView model,
                                   @RequestParam("sellerid") Integer sellerid) {
        sellerService.findSellerInfo(sellerid, model);
        model.setViewName("businessman/editSeller");
        return model;
    }

    /**
     * 商家信息待审核->查看
     *
     * @param model
     * @param sellerid
     * @return
     */
    @RequestMapping(value = "getInit")
    @RequestToken(createToken = true, tokenName = "updateStatusToken")
    public ModelAndView getInit(ModelAndView model,
                                @RequestParam("sellerid") Integer sellerid) {
        sellerService.findSellerInfo(sellerid, model);
        model.setViewName("businessman/viewSeller");
        return model;
    }


    /**
     * 修改商家信息
     *
     * @param seller zhoude
     * @return
     */
    @RequestLogging(name = "商家信息修改")
    @ResponseBody
    @RequestMapping(value = "update/updateSeller")
    public Object updateSeller(TSeller seller, HttpServletRequest request) {
        Resultable resultable = null;
        try {
            //修改商户信息
            sellerService.addAndUpdateSellerOptimize(seller, request);
            resultable = new Resultable(true, "基本信息修改成功！");
            this.log.info("修改成功!");
            String[] s = {"商家编号", seller.getSellerid().toString(), "修改", "修改"};
            sellerService.fireLoginEvent(s);
        } catch (Exception e) {
            this.log.error("修改异常", e);
            resultable = new Resultable(false, "操作失败");
            String[] s = {"商家编号", seller.getSellerid().toString(), "修改", "修改"};
            sellerService.fireLoginEvent(s, 0);
        } finally {
            return resultable;
        }
    }


    /**
     * 批量删除商家
     *
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "beachDeleteSeller")
    public Object beachDeleteSeller(String ids) {
        Resultable resultable = null;
        Object[] objects = {};
        if (null != ids) {
            objects = ids.split(",");
        }
        try {
            Integer resultNum = sellerService.deleteSeller(objects);
            if (resultNum > SellerConstants.COMMON_PARAM_Z) {
                this.log.info("删除成功");
                resultable = new Resultable(true, "操作成功");
            }
        } catch (Exception e) {
            this.log.error("删除异常", e);
            resultable = new Resultable(false, "操作失败");
        } finally {
            return resultable;
        }
    }


    /**
     * 更新商家账号信息
     *
     * @param infoList
     * @return
     */
    @RequestLogging(name = "商家信息更新")
    @RequestMapping(value = "update/updateSellerAccount")
    @ResponseBody
    public Object updateSellerAccount(@RequestBody TSellerAccount[] infoList) {
        Resultable resultable = null;
        try {
            sellerService.addAndUpdateAccount(infoList);
            this.log.info("修改成功!");
            Long aid = sellerAccountDao.getMaxId();
            resultable = new Resultable(true, "账号信息更新成功！");
            resultable.setData(aid);
        } catch (Exception e) {
            this.log.error("修改异常", e);
            resultable = new Resultable(false, "操作失败");
        } finally {
            return resultable;
        }
    }

    /**
     * 更新详细信息
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("update/updateSellerDetailed")
    @RequestLogging(name = "更新详细信息")
    public Object updateSellerDetailed(SellerDetailedForm sellerDetailedForm, HttpServletRequest request) {
        Resultable resultable = null;
        try {
            Map<String, String> res = sellerService.updateSellerDetailed(sellerDetailedForm, request);
            sellerService.updateSellerDateTime(sellerDetailedForm.getSellerDetailed().getSellerid());//更新主表t_seller 数据操作时间
            this.log.info("修改成功!");
            resultable = new Resultable(true, "详细信息更新成功！");
            resultable.setData(res);
        } catch (Exception e) {
            this.log.error("修改异常", e);
            resultable = new Resultable(false, "操作失败");
        } finally {
            return resultable;
        }
    }

    /**
     * 更新经纬度
     *
     * @param sellerLandmark
     * @return
     */
    @ResponseBody
    @RequestMapping("update/updateSellerLandmark")
    @RequestLogging(name = "更新经纬度")
    public Object updateSellerLandmark(TSellerLandmark sellerLandmark) {
        Resultable resultable = null;
        try {
            //latitude 纬度 longitude 经度(计算HASH值)
            String geohashs = "";
            try {
                geohashs = HashUtil.getInstance().getGeoHash(sellerLandmark.getLatitude(), sellerLandmark.getLongitude());
            } catch (Exception e) {
                this.log.error("经纬度格式不正确", e);
                resultable = new Resultable(false, "经纬度格式不正确");
                //e.printStackTrace();
                return resultable;
            }
            sellerLandmark.setGeohash(geohashs);
            sellerLandmark.setSdate(new Date());
            sellerService.updateSellerDateTime(sellerLandmark.getSellerid());//更新主表t_seller 数据操作时间
            int res = sellerService.updateSellerLandmark(sellerLandmark);
            this.log.info("修改成功!");
            resultable = new Resultable(true, "详细信息更新成功！");
            if (res != 0) {
                resultable.setData(res);
            }

        } catch (Exception e) {
            this.log.error("修改异常", e);
            resultable = new Resultable(false, "操作失败");
        } finally {
            return resultable;
        }
    }


    /**
     * 初始化分店信息
     */
    @RequestMapping(value = "init/splitShop")
    public ModelAndView initSplitShop(ModelAndView model,
                                      @RequestParam("sellerid") Integer sellerid) {
        model.setViewName("businessman/sellerPendingSplitShop");
        model.addObject("sellerid", sellerid);
        return model;
    }

    /**
     * 获取分店列表
     *
     * @param seller
     * @return
     */
    @RequestMapping(value = "init/listSplitShop")
    @ResponseBody
    public Object listSplitShop(TSeller seller) {
        Pageable<TSeller> pageable = new Pageable<TSeller>(seller);
        pageable = sellerService.getSplitShopList(seller);
        return pageable;
    }


    /**
     * addInit(商户添加初始化)
     *
     * @author：zhou'sheng
     */
    @RequestMapping(value = "/add/init")
    public ModelAndView addInit() {
        ModelAndView modelAndView = new ModelAndView("businessman/addSeller");
        modelAndView.addObject("isType", "add");
        return modelAndView;
    }


    /**
     * 查看页面审批初始化(编辑时初始化)
     *
     * @author：zhou'sheng
     */
    @RequestMapping(value = "updateSellerStatus/state/init")
    @RequestToken(removeToken = true, tokenName = "updateStatusToken")
    public ModelAndView stateInit() {
        ModelAndView modelAndView = new ModelAndView("businessman/examineinfoView");
        return modelAndView;
    }

    /**
     * 商家审批
     *
     * @param seller
     * @return
     */
//	@RequestLogging(name="商家信息待审核审批")
//	@ResponseBody
//	@RequestMapping(value = "beachStatusOld")
//	public Object beachStatus(TSeller seller,HttpServletRequest request) {
//		Resultable resultable = null;
//		try {
//			String ids= seller.getIds();
//			if (StringUtils.hasLength(ids)) {
//				String[] objects = StringUtils.paresToArray(ids, ",");
//				Map<String, Object> batchRes = sellerService.batchUpdateSellerStatusOptimized(objects, seller);
//				if(batchRes.get("jointMsg")==null || batchRes.get("jointMsg").equals("")){
//					sellerService.ledgerAndReturn(objects,seller);
//					resultable = new Resultable(true, "操作成功！");
//				}else{
//					resultable = new Resultable(false, batchRes.get("jointMsg")+"");//该商家相关的信息不完整，不能审核通过
//				}
//			}else{
//				resultable = new Resultable(false, "操作失败！");
//			}
//		} catch (Exception e) {
//			this.log.error("修改异常", e);
//			try{
//				sellerService.fireLoginEvent(StringUtils.addStrToStrArray(((ApplicationException)e).getLogInfo(),new ApplicationException("审核商家异常",e,new Object[]{seller,request}).getMessage()),0);
//			}catch(Exception e1){
//				log.info("审批异常", e);//add by lifeng 20160704
//				resultable = new Resultable(false, "操作失败！");
//			}
//			resultable = new Resultable(false, "操作失败！");
//		}
//		return resultable;
//		
//	}

    /**
     * 商家审批
     *
     * @param seller
     * @return
     */
    @RequestLogging(name = "商家信息待审核审批")
    @ResponseBody
    @RequestMapping(value = "beachStatus")
    public Object beachStatus2(TSeller seller, HttpServletRequest request) {
        Resultable resultable = null;
        try {
            String ids = seller.getIds();
            log.info("[beachStatus2]待审核商家ids:" + ids);
            if (StringUtils.hasLength(ids)) {
                String[] selleridList = StringUtils.paresToArray(ids, ",");
                StringBuilder resultMsg = new StringBuilder();
                TSeller persistentSeller = null;
                List<TSeller> perSellerList = new ArrayList<TSeller>();

                for (String selleridstr : selleridList) {
                    //获取当前商家信息
                    persistentSeller = sellerService.getObject(new Long(Integer.parseInt(selleridstr)));
                    perSellerList.add(persistentSeller);
                    //检查商家信息完整性
                    String result = sellerService.prePending(persistentSeller, seller.getStatus());
                    resultMsg.append(result == null ? "" : result);
                }

                if (resultMsg.length() != 0) {
                    //没有绑定合作商提示的信息
                    resultable = new Resultable(false, resultMsg.toString());
                } else {
                    int fail = 0;   // 审核失败统计
                    StringBuilder failResult = new StringBuilder();
                    seller.setUdate(new Date());
                    //更新商家状态
                    for (TSeller sellerInfo : perSellerList) {
                        if (sellerInfo.getStatus() != seller.getStatus()) {
                            try {
                                sellerService.updateSellerStatusOptimized(sellerInfo, seller);
                                // 分账及更新寻蜜客套餐销售信息
                                sellerService.ledgerAndReturn(sellerInfo, seller);
                            } catch (Exception e) {
                                failResult.append("商家【" + sellerInfo.getSellerid() + "-" + sellerInfo.getSellername() + "】审核失败\r\n");
                                log.error("商家【" + sellerInfo.getSellerid() + "-" + sellerInfo.getSellername() + "】审核失败", e);
                                fail++;
                            }
                        } else {
                            log.error("该商家【" + sellerInfo.getSellerid() + "】状态与当前审核状态一致，无需修改");
                        }
                    }
                    if (fail == 0) {
                        resultable = new Resultable(true, "批量审核操作成功！");
                    } else {
                        resultable = new Resultable(false, "共有" + fail + "家审核失败，分别是：" + failResult);
                    }
                }
            } else {
                resultable = new Resultable(false, "操作失败！");
            }
        } catch (Exception e) {
            this.log.error("修改异常", e);
            try {
                sellerService.fireLoginEvent(StringUtils.addStrToStrArray(((ApplicationException) e).getLogInfo(), new ApplicationException("审核商家异常", e, new Object[]{seller, request}).getMessage()), 0);
            } catch (Exception e1) {
                log.info("审批异常", e);//add by lifeng 20160704
                resultable = new Resultable(false, "操作失败！");
            }
            resultable = new Resultable(false, "操作失败！");
        }
        return resultable;

    }

    @RequestMapping("redis")
    @ResponseBody
    public Object redisTest(HttpServletRequest req, HttpServletResponse res) {
        String key = "highscore2";
        for (int i = 0; i < 15; i++) {
            redisTemplate.opsForZSet().add(key, "player" + i + 100, i);
            System.out.println("player" + i + 100);
        }
        Set<String> result = redisTemplate.opsForZSet().rangeByScore(key, 1.5, 8.0);
        System.out.println("result:" + result);
        Set<TypedTuple<String>> topscores = redisTemplate.opsForZSet().reverseRangeWithScores(key, 0, 4);
        for (TypedTuple<String> score : topscores) {
            System.out.println(score.getValue() + " " + score.getScore());
        }
        return "SUCCESS";
    }
/*
	@RequestMapping(value = "init/ld" ,method = RequestMethod.POST)
	public void getLd(HttpServletRequest request, HttpServletResponse response) throws IOException {
			Long rid  =  ResultUtil.getCurrentUser(request).getRoleId();
			Long auid = AuthorityAreaHandler.getInstance().getUrlByAuthorityId("businessman/sellerPending/init/list");
			response.getWriter().print(areaService.getLd(rid, auid));
	}*/


}
