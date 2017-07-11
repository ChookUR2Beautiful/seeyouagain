package com.xmn.saas.controller.h5.redpacket;

import com.xmn.saas.base.*;
import com.xmn.saas.base.thrift.common.ResponseData;
import com.xmn.saas.constants.RedpacketConsts;
import com.xmn.saas.controller.api.v1.common.vo.ShareRequest;
import com.xmn.saas.controller.h5.redpacket.vo.PagedListRecordRequest;
import com.xmn.saas.controller.h5.redpacket.vo.RedpacketCriteriaRequest;
import com.xmn.saas.controller.h5.redpacket.vo.RedpacketResponse;
import com.xmn.saas.controller.h5.redpacket.vo.RedpacketSaveCommonRequest;
import com.xmn.saas.entity.common.SellerAccount;
import com.xmn.saas.entity.redpacket.Redpacket;
import com.xmn.saas.entity.redpacket.RedpacketRecord;
import com.xmn.saas.service.base.RedisService;
import com.xmn.saas.service.bill.StatisticalService;
import com.xmn.saas.service.common.CommonService;
import com.xmn.saas.service.redpacket.RedpacketRecordService;
import com.xmn.saas.service.redpacket.RedpacketService;
import com.xmn.saas.service.wallet.WalletService;
import com.xmn.saas.utils.CalendarUtil;
import com.xmn.saas.utils.DataValidation;
import com.xmn.saas.utils.NumberComputeUtil;
import com.xmn.saas.utils.WebUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.helper.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 红包模块控制器
 *
 * @author dengqiang
 * @ClassName: RedpacketController
 * @Description: TODO
 * @date 2016年9月26日 下午1:44:39
 */
@SuppressWarnings("all")
@RequestMapping("/h5/redpacket")
@Controller(value = "h5-v1-redpacket-controller")
public class RedpacketController extends AbstractController {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(RedpacketController.class);

    @Autowired
    private RedpacketService redpacketService;

    @Autowired
    private RedpacketRecordService redpacketRecordService;

    @Autowired
    private GlobalConfig globalConfig;

    @Autowired
    private WalletService walletService;

    @Autowired
    private StatisticalService statisticalService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private RedisService redisService;

    private Redpacket redpacket;

    private RedpacketRecord redpacketRecord;

    private RedpacketResponse redpacketResponse;

    private Map<String, Object> paramMap;

    private Map<String, Object> resultMap;

    private Map<String, Object> data;



    /**
     * 红包入口
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String redpacket(@RequestParam(value = "sessionToken", required = true) String sessionToken) {
        String path = "";
        try {
            SellerAccount sellerAccount = redisService.getSessionCacheObject(sessionToken, SellerAccount.class);//获取商户id
            Integer sellerid = sellerAccount.getSellerid();
            logger.info(" 红包入口  sellerid:" + sellerid);
            paramMap = new HashMap<String, Object>();
            if (sellerid != null) {
                paramMap.put("SELLER_ID", sellerid);
            }
            List<Redpacket> redpackedList = redpacketService.findRedpacketByParams(paramMap);
            logger.info(" 红包入口  redpackedList:" + redpackedList.size());
            // 判断有没红包数据
            if (redpackedList.isEmpty()) {
                path = "forward:/h5/redpacket/introduce";
            } else {
                path = "forward:/h5/redpacket/list";
            }
            WebUtils.getRequest().setAttribute("sellerid", sellerid);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        return path;
    }

    /**
     * 红包说明
     */
    @RequestMapping(value = "/introduce", method = RequestMethod.GET)
    public String introduce() {
        try {
            Integer sellerid = (Integer) WebUtils.getRequest().getAttribute("sellerid");
            logger.info("红包说明  sellerid:" + sellerid);
            WebUtils.getRequest().setAttribute("sellerid", sellerid);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        return "redpacket/introduce";
    }

    /**
     * 红包创建类型选择
     */
    @RequestMapping(value = "/select_type", method = RequestMethod.GET)
    public String selectType(@RequestParam(value = "sellerid", required = true) Integer sellerid) {
        try {
            logger.info("红包创建类型选择  sellerid:" + sellerid);
            WebUtils.getRequest().setAttribute("sellerid", sellerid);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        return "redpacket/select-type";
    }

    /**
     * 红包列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(@RequestParam(value = "redpacketType", required = false) Integer redpacketType,
                       @RequestParam(value = "status", required = false) Integer status) {
        try {

            Integer sellerid = (Integer) WebUtils.getRequest().getAttribute("sellerid");
            if (sellerid == null) {
                if (StringUtils.isNotEmpty(WebUtils.getRequest().getParameter("sellerid"))) {
                    sellerid = Integer.valueOf(WebUtils.getRequest().getParameter("sellerid"));
                }
            }
            logger.info("红包列表  sellerid:" + sellerid);
            logger.info("红包列表  redpacketType:" + redpacketType);
            logger.info("红包列表  status:" + status);
            paramMap = new HashMap<String, Object>();
            if (sellerid != null) {
                paramMap.put("SELLER_ID", sellerid);
            }
            if (redpacketType != null) {
                paramMap.put("REDPACKET_TYPE", redpacketType);
            }
            if (status != null) {
                paramMap.put("STATUS", status);
            }

            List<Redpacket> redpacketList = redpacketService.findRedpacketByParams(paramMap);
            Integer totalCountIng = 0; // 进行中总条数
            Integer totalCountEnd = 0; // 已结束总条数

            List<RedpacketResponse> data = new ArrayList<RedpacketResponse>();
            if (!redpacketList.isEmpty()) {
                for (Redpacket redpacket : redpacketList) {

                    // 如果红包已过期,并且状态为启用
                    if (redpacketService.isExpired(redpacket) && redpacket.getStatus() == RedpacketConsts.NORMAL ||
                    	redpacket.getStatus() == RedpacketConsts.LOCK  || 
                    	redpacket.getStatus() == RedpacketConsts.SHARE) {

                        Redpacket updateRedp = new Redpacket();
                        updateRedp.setId(redpacket.getId());
                        updateRedp.setStatus(RedpacketConsts.DISABLE);
                        redpacketService.disableRedpacket(updateRedp);

                        // 设置红包状态为禁用,用于显示
                        redpacket.setStatus(RedpacketConsts.DISABLE);

                    }

                    if (redpacket.getStatus() != null) {
                        if (redpacket.getStatus() == RedpacketConsts.DISABLE) {
                            totalCountEnd++;
                        } else if (redpacket.getStatus() == RedpacketConsts.LOCK || redpacket.getStatus() == RedpacketConsts.SHARE || redpacket.getStatus() == RedpacketConsts.NORMAL) {
                            totalCountIng++;
                        }
                    }
                    
                    paramMap = new HashMap<String, Object>();
                    if (redpacket.getId() != null) {
                        paramMap.put("REDPACKET_ID", redpacket.getId());
                    }
                    List<RedpacketRecord> redpacketRecordList = redpacketService.findRedpacketRecordByParams(paramMap);
                    BigDecimal realSpending = new BigDecimal("0.00");
                    if (!redpacketRecordList.isEmpty()) {
                        for (RedpacketRecord redpacketRecord : redpacketRecordList) {
                            if (redpacketRecord!=null && redpacketRecord.getDenomination() != null) {
                                realSpending = realSpending.add(redpacketRecord.getDenomination());
                            }
                        }
                    }
                    redpacketResponse = new RedpacketResponse();
                    redpacketResponse.setRedpacketId(redpacket.getId());
                    redpacketResponse.setRealSpending(realSpending);
                    BigDecimal chargeBalance = redpacket.getTotalAmount().subtract(realSpending);
                    redpacketResponse.setChargeBalance(chargeBalance);
                    redpacketResponse.setRecordNumber(redpacketRecordList.size());
                    redpacketResponse.setSellerid(redpacket.getSellerid());
                    redpacketResponse.setRedpacketType(redpacket.getRedpacketType());
                    redpacketResponse.setRedpacketName(redpacket.getRedpacketName());
                    redpacketResponse.setBeginDate(CalendarUtil.dateFormat(redpacket.getBeginDate(), "yyyy-MM-dd"));
                    redpacketResponse.setEndDate(CalendarUtil.dateFormat(redpacket.getEndDate(), "yyyy-MM-dd"));
                    redpacketResponse.setBeginTime(redpacket.getBeginTime());
                    redpacketResponse.setEndTime(redpacket.getEndTime());
                    redpacketResponse.setTotalAmount(redpacket.getTotalAmount());
                    redpacketResponse.setViews(redpacket.getViews());
                    redpacketResponse.setStatus(redpacket.getStatus());
                    redpacketResponse.setDownType(convertType(redpacket.getRedpacketType()));
                    data.add(redpacketResponse);
                }
            }
            WebUtils.getRequest().setAttribute("sellerid", sellerid);
            WebUtils.getRequest().setAttribute("totalCountIng", totalCountIng);
            WebUtils.getRequest().setAttribute("totalCountEnd", totalCountEnd);
            WebUtils.getRequest().setAttribute("data", data);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        return "redpacket/list";
    }

    /**
     * 红包数据详情
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detail(@RequestParam(value = "sellerid", required = true) Integer sellerid,
                         @RequestParam(value = "redpacketId", required = false) Long redpacketId,
                         @RequestParam(value = "redpacketType", required = true) Integer redpacketType,
                         @RequestParam(value = "status", required = false) Integer status) {
        try {
            if (redpacketId == null && sellerid != null && redpacketType != null) {
                Redpacket param = new Redpacket();
                param.setSellerid(sellerid);
                param.setRedpacketType(redpacketType);
                param.setStatus(1);
                Redpacket result = redpacketService.findRedpacket(param);
                redpacketId = result.getId();

            }
            logger.info("红包数据详情  sellerid:" + sellerid);
            logger.info("红包数据详情  redpacketId:" + redpacketId);
            logger.info("红包数据详情  redpacketType:" + redpacketType);
            logger.info("红包数据详情  status:" + status);
            paramMap = new HashMap<>();
            if (sellerid != null) {
                paramMap.put("SELLERID", sellerid);
            }
            if (redpacketId != null) {
                paramMap.put("REDPACKET_ID", redpacketId);
            }
            if (redpacketType != null) {
                paramMap.put("REDPACKET_TYPE", redpacketType);
            }
            if (status != null) {
                paramMap.put("STATUS", status);
            }
            redpacketResponse = new RedpacketResponse();
            List<Redpacket> redpacketList = redpacketService.findRedpacketByParams(paramMap);
            if (!redpacketList.isEmpty()) {
                BigDecimal stimulateConsume = commonService.getActiveAmount(sellerid, redpacketList.get(0).getBeginDate(), redpacketList.get(0).getEndDate());//刺激消费金额
                redpacketResponse.setStimulateConsume(stimulateConsume);
                paramMap = new HashMap<>();
                redpacketResponse.setRedpacketId(redpacketList.get(0).getId());
                if (redpacketList.get(0).getId() != null) {
                    paramMap.put("REDPACKET_ID", redpacketList.get(0).getId());
                }
                List<RedpacketRecord> redpacketRecordList = redpacketService.findRedpacketRecordByParams(paramMap);
                BigDecimal realSpending = new BigDecimal("0.00");
                Integer newUserNumber = 0;
                if (!redpacketRecordList.isEmpty()) {
                    for (RedpacketRecord redpacketRecord : redpacketRecordList) {
                    	BigDecimal denomination=redpacketRecord.getDenomination();
                    	if(denomination!=null){
                    		realSpending = realSpending.add(denomination);
                    	}
                        if (redpacketRecord.getIsBinding() != null) {
                            if (redpacketRecord.getIsBinding() == 1) {
                                newUserNumber++;
                            }
                        }
                    }
                }
                redpacketResponse.setRealSpending(realSpending);
                redpacketResponse.setNewUserNumber(newUserNumber);
                BigDecimal chargeBalance = redpacketList.get(0).getTotalAmount().subtract(realSpending);
                redpacketResponse.setChargeBalance(chargeBalance);
                redpacketResponse.setRecordNumber(redpacketRecordList.size());
                redpacketResponse.setSellerid(redpacketList.get(0).getSellerid());
                redpacketResponse.setRedpacketType(redpacketList.get(0).getRedpacketType());
                redpacketResponse.setRedpacketName(redpacketList.get(0).getRedpacketName());
                redpacketResponse.setTotalAmount(redpacketList.get(0).getTotalAmount());
                redpacketResponse.setViews(redpacketList.get(0).getViews());
                redpacketResponse.setStatus(redpacketList.get(0).getStatus());

                String endTime = redpacketList.get(0).getEndTime();
				String endDate = CalendarUtil.getDateString(redpacketList.get(0).getEndDate(), CalendarUtil.FORMAT0) + " "+ endTime ;
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				Date closeDate= format.parse(endDate);
                redpacketResponse.setCloseDate(format.parse(endDate));

//                Integer dayNumberIng = CalendarUtil.distanceDay(CalendarUtil.dateFormat(redpacketList.get(0).getBeginDate(), "yyyy-MM-dd"));
//                Integer dayNumberEnd = CalendarUtil.distanceDay(CalendarUtil.dateFormat(redpacketList.get(0).getEndDate(), "yyyy-MM-dd"));

                // 已进行天数
                redpacketResponse.setDayNumberIng(CalendarUtil.startDay(redpacketList.get(0).getBeginDate()));
                // 距结束天数
                redpacketResponse.setDayNumberEnd(CalendarUtil.endDay(closeDate));
//                redpacketResponse.setDayNumberEnd(CalendarUtil.endDay(redpacketList.get(0).getEndDate()));

                redpacketResponse.setNewuserNum(redpacketList.get(0).getNewUserNumber());
                boolean stoppable = redpacketService.redpacketStoppable(redpacketList.get(0));
                WebUtils.getRequest().setAttribute("stoppable",stoppable);
            }
            String downloadUrl = WebUtils.getRequest().getScheme() + "://" + WebUtils.getRequest().getServerName() + ":" + WebUtils.getRequest().getServerPort();
            WebUtils.getRequest().setAttribute("downloadUrl", downloadUrl);
            WebUtils.getRequest().setAttribute("data", redpacketResponse);
            WebUtils.getRequest().setAttribute("title", redpacketResponse.getRedpacketName());
            WebUtils.getRequest().setAttribute("id", redpacketId);
            WebUtils.getRequest().setAttribute("type", convertType(redpacketType));


        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            ThriftBuilder.close();
        }
        return "redpacket/detail";
    }

    /**
     * 红包数据明细
     */
    @RequestMapping(value = "/detail_redpacket", method = RequestMethod.GET)
    public String detailRedpacket(@RequestParam(value = "redpacketType", required = true) Integer redpacketType,
                                  @RequestParam(value = "redpacketId", required = true) Long redpacketId) {
        String view = "";
        try {
            redpacket = redpacketService.findRedpacketByPrimaryKey(redpacketId);
            redpacketResponse = new RedpacketResponse();
            if (redpacket != null) {
                redpacketResponse.setRedpacketId(redpacket.getId());
                redpacketResponse.setSellerid(redpacket.getSellerid());
                redpacketResponse.setRedpacketType(redpacket.getRedpacketType());
                redpacketResponse.setAmountType(redpacket.getAmountType());
                redpacketResponse.setRedpacketName(redpacket.getRedpacketName());
                redpacketResponse.setShareAwardsProportion(redpacket.getShareAwardsProportion());
                redpacketResponse.setBeginDate(CalendarUtil.dateFormat(redpacket.getBeginDate(), "yyyy-MM-dd"));
                redpacketResponse.setEndDate(CalendarUtil.dateFormat(redpacket.getEndDate(), "yyyy-MM-dd"));
                redpacketResponse.setBeginTime(redpacket.getBeginTime());
                redpacketResponse.setEndTime(redpacket.getEndTime());
                redpacketResponse.setTotalAmount(redpacket.getTotalAmount());
                redpacketResponse.setRandomAmountMini(redpacket.getRandomAmountMini());
                redpacketResponse.setRandomAmountMax(redpacket.getRandomAmountMax());
                redpacketResponse.setRedpacketNumber(redpacket.getRedpacketNumber());
                redpacketResponse.setSingleAmount(redpacket.getSingleAmount());
                redpacketResponse.setNewUserAmount(redpacket.getNewUserAmount());
                redpacketResponse.setOldUserAmount(redpacket.getOldUserAmount());
                redpacketResponse.setLongitude(redpacket.getLongitude());
                redpacketResponse.setLatitude(redpacket.getLatitude());
                redpacketResponse.setAddress(redpacket.getAddress());
                redpacketResponse.setReceiveCondition(redpacket.getReceiveCondition());
                redpacketResponse.setStatus(redpacket.getStatus());
            }
            switch (redpacketType) {
                case 0:
                    view = "redpacket/detail-drainage";// 引流红包数据明细视图
                    break;
                case 1:
                    view = "redpacket/detail-limit";// 限时到店红包数据明细视图
                    break;
                case 2:
                    view = "redpacket/detail-consume";// 消费满赠红包数据明细视图
                    break;
                case 3:
                    view = "redpacket/detail-recommend";// 推荐消费红包数据明细视图
                    break;
                case 4:
                    view = "redpacket/detail-common";// 普通抽奖红包数据明细视图
                    break;
                default:
                    view = "redpacket/detail-drainage";// 分享引流数据明细视图
                    break;
            }
            WebUtils.getRequest().setAttribute("data", redpacketResponse);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        return view;
    }

    /**
     * 红包领取列表
     */
    @RequestMapping(value = "/list_record", method = RequestMethod.GET)
    public String listRecord(@RequestParam(value = "redpacketId", required = true) Long redpacketId) {
        try {
            List<RedpacketResponse> data = new ArrayList<RedpacketResponse>();
            redpacket = redpacketService.findRedpacketByPrimaryKey(redpacketId);
            BigDecimal realSpending = new BigDecimal("0.00");
            String recordNumber = null;
            if (redpacket.getId() != null) {
                paramMap = new HashMap<>();
                paramMap.put("REDPACKET_ID", redpacket.getId());
                List<RedpacketRecord> redpacketRecordList = redpacketService.findRedpacketRecordByParams(paramMap);
                recordNumber = String.valueOf(redpacketRecordList.size());
                if (!redpacketRecordList.isEmpty()) {
                    for (RedpacketRecord redpacketRecord : redpacketRecordList) {
                    	BigDecimal denomination=redpacketRecord.getDenomination();
                    	if(denomination!=null){
                    		realSpending = realSpending.add(denomination);// 领取总金额
                    	}
                        redpacketResponse = new RedpacketResponse();
                        redpacketResponse.setRedpacketId(redpacket.getId());
                        redpacketResponse.setRedpacketType(redpacket.getRedpacketType());
                        redpacketResponse.setRecordTime(CalendarUtil.dateFormat(redpacketRecord.getRecordTime(), "yyyy-MM-dd HH:mm:ss"));
                        redpacketResponse.setRedpacketRecord(redpacketRecord);

                        Map<String, String> userParamMap = new HashMap<String, String>();
                        userParamMap.put("uid", String.valueOf(redpacketRecord.getUserId()));
                        ResponseData responseData = redpacketService.getUserMsg(userParamMap);
                        if (responseData != null) {
                            if (responseData.state == 0) {
                                Map<String, String> dataMap = responseData.resultMap;
                                if (dataMap != null && !dataMap.isEmpty()) {
                                    redpacketResponse.setUserId(redpacketRecord.getUserId());

                                    // 设置用户名
                                    if (StringUtils.isNotBlank(dataMap.get("nname"))) {
                                        redpacketResponse.setNickname(dataMap.get("nname"));
                                    }else if (StringUtils.isNotBlank(dataMap.get("phone"))){
                                        redpacketResponse.setNickname(dataMap.get("phone"));
                                    }else {
                                        redpacketResponse.setNickname("匿名");
                                    }


                                    redpacketResponse.setPhone(dataMap.get("phone"));
                                    String avatar = dataMap.get("avatar");
//									redpacketResponse.setAvatar(globalConfig.getImageHost()+dataMap.get("avatar"));
                                    redpacketResponse.setAvatar(StringUtils.isBlank(avatar) ? "" : globalConfig
                                            .getImageHost() + avatar);
                                    redpacketResponse.setGenussellerid(dataMap.get("genussellerid"));
                                }
                            }
                        }
                        data.add(redpacketResponse);
                    }
                }
            }
            WebUtils.getRequest().setAttribute("recordNumber", recordNumber);
            WebUtils.getRequest().setAttribute("realSpending", realSpending);
            WebUtils.getRequest().setAttribute("redpacketId", redpacket.getId());
            WebUtils.getRequest().setAttribute("data", data);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        return "redpacket/list-record";
    }


    @ResponseBody
    @RequestMapping(value = "paged_list_record",method = RequestMethod.POST)
    public void pagedListRecord(@Valid PagedListRecordRequest request,BindingResult result) throws Exception {
        if (!request.doValidate(result)) {
            return;
        }

        // 查询领取记录, 分页查询
        Map<String, Object> resultMap = null;
        try {
            // 查询领取参数
            resultMap = redpacketRecordService.pagedRecordList(request.getRedpacketId(),request.getPageNum(),request.getPageSize());
        } catch (Exception e) {
            new Response(ResponseCode.FAILURE,"请求失败").write();
            return;
        }
        new Response(ResponseCode.SUCCESS,"请求成功",resultMap).write("yyyy-MM-dd hh:mm:ss");
        return;
    }



    /**
     * 红包领取明细
     */
    @RequestMapping(value = "/detail_record", method = RequestMethod.GET)
    public String detailRecord(@RequestParam(value = "redpacketId", required = true) Long redpacketId,
                               @RequestParam(value = "redpacketRecordId", required = true) Long redpacketRecordId,
                               @RequestParam(value = "userId", required = true) Long userId) {
        try {
            redpacketResponse = new RedpacketResponse();
            redpacket = redpacketService.findRedpacketByPrimaryKey(redpacketId);
            redpacketResponse.setRedpacketType(redpacket.getRedpacketType());
            redpacketRecord = redpacketService.findRedpacketRecordByPrimaryKey(redpacketRecordId);

            Map<String, String> userParamMap = new HashMap<String, String>();
            userParamMap.put("uid", String.valueOf(redpacketRecord.getUserId()));
            ResponseData responseData = redpacketService.getUserMsg(userParamMap);
            if (responseData != null) {
                if (responseData.state == 0) {
                    Map<String, String> dataMap = responseData.resultMap;
                    if (dataMap != null && !dataMap.isEmpty()) {
                        redpacketResponse.setUserId(redpacketRecord.getUserId());

                        // 设置用户名
                        if (StringUtils.isNotBlank(dataMap.get("nname"))) {
                            redpacketResponse.setNickname(dataMap.get("nname"));
                        }else if (StringUtils.isNotBlank(dataMap.get("phone"))){
                            redpacketResponse.setNickname(dataMap.get("phone"));
                        }else {
                            redpacketResponse.setNickname("匿名");
                        }


                        redpacketResponse.setPhone(dataMap.get("phone"));
                        String avatar = dataMap.get("avatar");
                        redpacketResponse.setAvatar(StringUtils.isBlank(avatar) ? "" : globalConfig
                                .getImageHost() + avatar);
                        redpacketResponse.setGenussellerid(dataMap.get("genussellerid"));

                    }
                }
            }

            redpacketResponse.setRecordTime(CalendarUtil.dateFormat(redpacketRecord.getRecordTime(), "yyyy-MM-dd HH:mm:ss"));
            redpacketResponse.setRedpacketRecord(redpacketRecord);
            WebUtils.getRequest().setAttribute("data", redpacketResponse);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        return "redpacket/detail-record";
    }

    /**
     * 根据条件查询红包列表数据
     */
    @ResponseBody
    @RequestMapping(value = "/list_by", method = RequestMethod.POST)
    public void listBy(@Valid RedpacketCriteriaRequest redpacketCriteriaRequest, BindingResult result) {
        try {
            if (!redpacketCriteriaRequest.doValidate(result)) {
                return;
            }
            Integer sellerid = redpacketCriteriaRequest.getSellerid();
            Integer redpacketType = redpacketCriteriaRequest.getRedpacketType();
            Integer status = redpacketCriteriaRequest.getStatus();
            paramMap = new HashMap<String, Object>();
            if (sellerid != null) {
                paramMap.put("SELLER_ID", sellerid);
            }
            if (redpacketType != null) {
                paramMap.put("REDPACKET_TYPE", redpacketType);
            }
            if (status != null) {
                paramMap.put("STATUS", status);
            }
            List<RedpacketResponse> data = new ArrayList<RedpacketResponse>();
            List<Redpacket> redpacketList = redpacketService.findRedpacketByParams(paramMap);
            if (!redpacketList.isEmpty()) {
                for (Redpacket redpacket : redpacketList) {
                    paramMap = new HashMap<String, Object>();
                    redpacketResponse = new RedpacketResponse();
                    redpacketResponse.setRedpacketId(redpacket.getId());
                    redpacketResponse.setStatus(redpacket.getStatus());
                    if (redpacket.getId() != null) {
                        paramMap.put("REDPACKET_ID", redpacket.getId());
                    }
                    List<RedpacketRecord> redpacketRecordList = redpacketService.findRedpacketRecordByParams(paramMap);
                    BigDecimal realSpending = new BigDecimal("0.00");
                    if (!redpacketRecordList.isEmpty()) {
                        for (RedpacketRecord redpacketRecord : redpacketRecordList) {
                            BigDecimal denomination=redpacketRecord.getDenomination();
                        	if(denomination!=null){
                        		realSpending = realSpending.add(denomination);
                        	}
                        }
                    }
                    redpacketResponse.setRealSpending(realSpending);
                    BigDecimal chargeBalance = redpacket.getTotalAmount().subtract(realSpending);
                    redpacketResponse.setChargeBalance(chargeBalance);
                    redpacketResponse.setRecordNumber(redpacketRecordList.size());
                    redpacketResponse.setSellerid(redpacket.getSellerid());
                    redpacketResponse.setRedpacketType(redpacket.getRedpacketType());
                    redpacketResponse.setRedpacketName(redpacket.getRedpacketName());
                    redpacketResponse.setBeginDate(CalendarUtil.dateFormat(redpacket.getBeginDate(), "yyyy-MM-dd"));
                    redpacketResponse.setEndDate(CalendarUtil.dateFormat(redpacket.getEndDate(), "yyyy-MM-dd"));
                    redpacketResponse.setBeginTime(redpacket.getBeginTime());
                    redpacketResponse.setEndTime(redpacket.getEndTime());
                    redpacketResponse.setTotalAmount(redpacket.getTotalAmount());
                    redpacketResponse.setViews(redpacket.getViews());
                    redpacketResponse.setStatus(redpacket.getStatus());
                    data.add(redpacketResponse);
                }
            }
            resultMap = new HashMap<String, Object>();
            resultMap.put("code", ResponseCode.SUCCESS);
            resultMap.put("data", data);
            new Response(ResponseCode.SUCCESS, "加载红包列表数据成功", resultMap).write();
            return;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
    }


    /**
     * 红包（添加）入口
     */
    @RequestMapping(value = "/input", method = RequestMethod.GET)
    public String input(@RequestParam(value = "redpacketType", required = true) Integer redpacketType,
                        @RequestParam(value = "sellerid", required = true) Integer sellerid,
                        @RequestParam(value = "url", required = false) String url) {
        String view = "";
        try {
            Calendar calendar = Calendar.getInstance();
            Integer year = calendar.get(Calendar.YEAR); // 获取完整的年份(4位)
            Integer month = calendar.get(Calendar.MONDAY) + 1; // 获取当前月份(1-12)
            Integer date = calendar.get(Calendar.DATE); // 获取当前日(1-31)
            String initTime = year + "年" + month + "月" + date + "日";
            String currentDate = month + "月" + date + "日";
            String defaultStartTime = "00:00";
            String defaultEndTime = "23:59";
            switch (redpacketType) {
                case 0:
                    view = "redpacket/input-drainage";// 引流红包视图
                    break;
                case 1:
                    view = "redpacket/input-limit";// 限时到店红包视图
                    break;
                case 2:
                    view = "redpacket/input-consume";// 消费满赠红包视图
                    break;
                case 3:
                    view = "redpacket/input-recommend";// 推荐消费红包视图
                    break;
                case 4:
                    WebUtils.getRequest().setAttribute("url", url);
                    view = "redpacket/input-common";// 普通抽獎红包视图
                    break;
                default:
                    view = "redpacket/input-drainage";// 分享引流视图
                    break;
            }
            WebUtils.getRequest().setAttribute("redpacketType", redpacketType);
            WebUtils.getRequest().setAttribute("sellerid", sellerid);
            WebUtils.getRequest().setAttribute("initTime", initTime);
            WebUtils.getRequest().setAttribute("currentDate", currentDate);
            WebUtils.getRequest().setAttribute("defaultStartTime", defaultStartTime);
            WebUtils.getRequest().setAttribute("defaultEndTime", defaultEndTime);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        return view;
    }

    /**
     * 添加红包信息
     *
     * @param 设定文件
     * @return void    返回类型
     * @throws
     * @Title: save
     * @Description: TODO
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save(@Valid RedpacketSaveCommonRequest request) {
        try {

            /*******以下参数客户端必填********/
            Integer sellerid = request.getSellerid();
            Integer redpacketType = request.getRedpacketType();
            String redpacketName = request.getRedpacketName();
            String beginDateParam = request.getBeginDate();
            String endDateParam = request.getEndDate();
            String beginTime = request.getBeginTime();
            String endTime = request.getEndTime();
            BigDecimal totalAmount = request.getTotalAmount();
            Date beginDate = CalendarUtil.formatDate(beginDateParam);
            Date endDate = CalendarUtil.formatDate(endDateParam);

            /***********以下参数根据redpacketType验证是否传入***********/
            BigDecimal randomAmountMini = request.getRandomAmountMini();
            BigDecimal randomAmountMax = request.getRandomAmountMax();
            BigDecimal singleAmount = request.getSingleAmount();
            Integer redpacketNumber = request.getRedpacketNumber();
            BigDecimal receiveCondition = request.getReceiveCondition();
            String longitude = request.getLongitude();
            String latitude = request.getLatitude();
            Integer amountType = request.getAmountType();
            BigDecimal newUserAmount = request.getNewUserAmount();
            BigDecimal oldUserAmount = request.getOldUserAmount();
            String shareAwardsProportion = request.getShareAwardsProportion();

            /***********以下参数客户端非必填***********/
            String address = request.getAddress();
            String paramUrl = request.getParamUrl();

            if (totalAmount.compareTo(BigDecimal.ZERO) == 0 || totalAmount.compareTo(BigDecimal.ZERO) == -1) { // 判断总金额不能小于等于零
                new Response(ResponseCode.FAILURE, "红包预计总投入必须大于零").write();
                return;
            }

            if (beginDate.compareTo(CalendarUtil.dateFormat(new Date())) == -1) { // 判断红包活动开始日期不能是当前日期已过去日期
                new Response(ResponseCode.FAILURE, "红包活动开始日期不可用").write();
                return;
            }

            if (endDate.compareTo(CalendarUtil.dateFormat(new Date())) == -1) {// 判断红包活动结束日期不能是当前日期已过去日期
                new Response(ResponseCode.FAILURE, "红包活动结束日期不可用").write();
                return;
            }

            if (newUserAmount != null) {
                if (newUserAmount.compareTo(BigDecimal.ZERO) == 0 || newUserAmount.compareTo(BigDecimal.ZERO) == -1) {// 判断红包新用户金额不能小于等于零
                    new Response(ResponseCode.FAILURE, "红包新用户金额必须大于零").write();
                    return;
                }
            }

            if (oldUserAmount != null) {
                if (oldUserAmount.compareTo(BigDecimal.ZERO) == 0 || oldUserAmount.compareTo(BigDecimal.ZERO) == -1) {// 判断红包旧用户金额不能小于等于零
                    new Response(ResponseCode.FAILURE, "红包旧用户金额必须大于零").write();
                    return;
                }
            }

            if (newUserAmount != null && oldUserAmount != null && shareAwardsProportion != null) {
                //redpacketNumber=
                if (!DataValidation.getInstance().valiAmount(totalAmount, newUserAmount, oldUserAmount, shareAwardsProportion)) { // 确保红包每个红包最少能有5个或以上
                    new Response(ResponseCode.FAILURE, "请确保红包最少能有5个").write();
                    return;
                }
            }

            if (singleAmount != null && redpacketNumber != null) {
                if (singleAmount.compareTo(totalAmount) == 1) {
                    new Response(ResponseCode.FAILURE, "单个红包金额不能大于总金额").write();
                    return;
                } else if (!DataValidation.getInstance().valiAmount(totalAmount, singleAmount) || redpacketNumber < 5) { // 确保红包最少能有5个或以上
                    new Response(ResponseCode.FAILURE, "请确保红包最少能有5个").write();
                    return;
                }
            }

            if (randomAmountMini != null) {
                if (randomAmountMini.compareTo(BigDecimal.ZERO) == 0 || randomAmountMini.compareTo(BigDecimal.ZERO) == -1) {// 判断红包随机最小金额不能小于等于零
                    new Response(ResponseCode.FAILURE, "随机红包最小金额必须大于零").write();
                    return;
                }
            }

            if (randomAmountMax != null) {
                if (randomAmountMax.compareTo(BigDecimal.ZERO) == 0 || randomAmountMax.compareTo(BigDecimal.ZERO) == -1) {// 判断红包随机最大金额不能小于等于零
                    new Response(ResponseCode.FAILURE, "随机红包最大金额必须大于零").write();
                    return;
                } else if (!DataValidation.getInstance().valiAmount(totalAmount, randomAmountMax)) { // 确保红包最少能有5个或以上
                    new Response(ResponseCode.FAILURE, "请保红包最少能有5个").write();
                    return;
                }
            }

            redpacket = new Redpacket();
            redpacket.setSellerid(sellerid);
            redpacket.setRedpacketType(redpacketType);
            redpacket.setTotalAmount(totalAmount);
            redpacket.setBeginDate(beginDate);
            redpacket.setEndDate(endDate);
            redpacket.setBeginTime(beginTime);
            redpacket.setEndTime(endTime);
            redpacket.setRedpacketName(redpacketName);
            redpacket.setNewUserAmount(newUserAmount);
            redpacket.setOldUserAmount(oldUserAmount);
            redpacket.setShareAwardsProportion(shareAwardsProportion);
            redpacket.setAmountType(amountType);

            redpacket.setRandomAmountMini(randomAmountMini);
            redpacket.setRandomAmountMax(randomAmountMax);

            redpacket.setLongitude(longitude);
            redpacket.setLatitude(latitude);

            redpacket.setAddress(address);
            redpacket.setSingleAmount(singleAmount);
            redpacket.setRedpacketNumber(redpacketNumber);

            redpacket.setReceiveCondition(receiveCondition);

            redpacket.setStatus(null); // 状态（ 0:禁用 1:启用 2:锁定 3:已分享）  --创建时为null-->支付成功后状态为：1-->为1(启用)时可以3（分享），也可以2（锁定）。
            redpacket.setPayStatus(RedpacketConsts.PAYNO); //未支付
            redpacket.setCreateTime(new Date());

            String orderNo = "06" + NumberComputeUtil.buildOrderNo();
            redpacket.setOrderNo(orderNo);
            redpacket.setVersionLock(1L);


            BigDecimal amount = new BigDecimal(0.00);// 商家钱包余额
            BigDecimal profit = new BigDecimal(0.00); // 商家收益余额
            BigDecimal balance = new BigDecimal(0.00);// 商家分账余额
            BigDecimal commision = new BigDecimal(0.00);// 商家佣金余额
            BigDecimal zbalance = new BigDecimal(0.00);// 商家赠送余额
            BigDecimal integral = new BigDecimal(0.00);// 商家积分余额
            redpacket.setAmount(amount);
            redpacket.setBalance(zbalance);
            redpacket.setCommision(commision);
            redpacket.setZbalance(zbalance);
            redpacket.setIntegral(integral);
            redpacket.setProfit(profit);
            redpacketService.addRedpacket(redpacket); // 记录红包信息进数据库

            redpacket = redpacketService.findRedpacketByOrderNo(orderNo);
            data = new HashMap<String, Object>();
            data.put("paramUrl", paramUrl);
            data.put("sellerid", sellerid);// 商家id
            data.put("orderNo", orderNo); //  订单编号
            data.put("redpacketId", redpacket.getId());    // 红包id
            data.put("type", convertType(redpacket.getRedpacketType()));

            new Response(ResponseCode.SUCCESS, "红包添加成功", data).write();
            return;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        } finally {
            ThriftBuilder.close();
        }
    }

    /**
     * 支付完成后进入预览页面
     */
    @RequestMapping(value = "/viwe_activity", method = RequestMethod.GET)
    public String payView(@RequestParam(value = "redpacketId",required = true)Long redpacketId) {

        logger.info("调用支付后预览接口");
        try {
            // 查询红包
            Redpacket redp = redpacketService.findRedpacketByPrimaryKey(redpacketId);

            // 判断红包类型, 是否跳转到红包列表
//            if (redp.getRedpacketType() == RedpacketConsts.CONSUME){
            if (false){
                WebUtils.getRequest().setAttribute("sellerid", redp.getSellerid());
                return "forward:/h5/redpacket/list";
            }

            // 获取分享链接地址
            ShareRequest shareRequest = new ShareRequest();
            shareRequest.setId(redp.getId().intValue());
            shareRequest.setTitle(redp.getRedpacketName());
            shareRequest.setType(convertType(redp.getRedpacketType()));
            String shareUrl = commonService.getShareUrl(shareRequest);

            // 重定向到分享地址
            return "redirect:" + shareUrl;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("支付后预览接口出现异常!",e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 预览界面,用户点击预览时进入
     * 2016-11-3
     */
    @RequestMapping(value = "preview", method = RequestMethod.GET)
    public String preview(@RequestParam(value = "redpacketId",required = true) Long redpacketId) throws Exception {
        // 根据redpacketId 获取红包
        Redpacket redp = redpacketService.findRedpacketByPrimaryKey(redpacketId);

        // 获取分享地址
        ShareRequest shareRequest = new ShareRequest();
        shareRequest.setId(redp.getId().intValue());
        shareRequest.setTitle(redp.getRedpacketName());
        shareRequest.setType(convertType(redp.getRedpacketType()));
        String shareUrl = commonService.getShareUrl(shareRequest);

        // 重定向到分享地址
        return "redirect:" + shareUrl;
    }


    /**
     * 终止红包活动
     *
     * @param @param redpacketType
     * @param @param redpacketId    设定文件
     * @return void    返回类型
     * @throws
     * @Title: stop
     * @Description: TODO
     */
    @ResponseBody
    @RequestMapping(value = "/stop", method = RequestMethod.POST)
    public void stop(@RequestParam(value = "redpacketId", required = true) Long redpacketId) {
        try {
            logger.info("终止红包活动 redpacketId:" + redpacketId);
            redpacket = redpacketService.findRedpacketByPrimaryKey(redpacketId);
            if (redpacket.getId() != null) {
                if (redpacket.getEndDate().compareTo(CalendarUtil.dateFormat(new Date())) == -1) {
                    new Response(ResponseCode.FAILURE, "该红包活动已结束").write();
                    return;
//                } else if (redpacket.getBeginDate().compareTo(CalendarUtil.dateFormat(new Date())) == 1) {
//                    new Response(ResponseCode.FAILURE, "该红包活动还未开始！").write();
                } else if ((
                        redpacket.getEndDate().compareTo(CalendarUtil.dateFormat(new Date())) == 1
                        || redpacket.getEndDate().compareTo(CalendarUtil.dateFormat(new Date())) == 0)
                        && (redpacket.getStatus().intValue() == RedpacketConsts.LOCK
                        || redpacket.getStatus().intValue() == RedpacketConsts.SHARE
                        || redpacket.getStatus().intValue() == RedpacketConsts.NORMAL)
                        && redpacket.getPayStatus().intValue() == RedpacketConsts.PAYYES) {
                    redpacket.setEndDate(new Date());
                    redpacket.setStatus(RedpacketConsts.DISABLE);
                    int count = redpacketService.updateRedpacket(redpacket);
                    if (count > 0) {
                        new Response(ResponseCode.SUCCESS, "终止成功！余额将于次日01:00点退回账户。", data).write();
                        return;
                    }
                }else{
                	String msg=null;
    				if(redpacket.getStatus().intValue()== RedpacketConsts.PAYNO){
    					msg="未支付";
    				}else if(redpacket.getStatus().intValue()== RedpacketConsts.PAYCALCEN){
    					msg="取消支付";
    				}else if(redpacket.getStatus().intValue()== RedpacketConsts.PAYFAILURE){
    					msg="支付失败";
    				}else if(redpacket.getStatus().intValue()== RedpacketConsts.REFUND){
    					msg="已退款";
    				}
    				if(StringUtil.isBlank(msg)){
    					new Response(ResponseCode.FAILURE, "红包状态不正确：已禁用").write();
                        return;
    				}else{
    					new Response(ResponseCode.FAILURE, "红包支付状态不正确："+msg).write();
                        return;
    				}
                }
            } else {
                new Response(ResponseCode.FAILURE, "没有此红包信息").write();
                return;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        } finally {

        }
    }


    /**
     * 调用下载物料接口,需要转换type值匹配
     *
     * @param type
     * @return
     */
    private int convertType(int type) {
        switch (type) {
            case 0:
                return 1;    // 引流红包视图
            case 1:
                return 4;    // 限时到店红包视图
            case 2:
                return 2;    // 消费满赠红包视图
            case 3:
                return 5;    // 推荐消费红包视图
            case 4:
                return 3;    // 普通抽獎红包视图
            default:
                return type;
        }
    }

}
