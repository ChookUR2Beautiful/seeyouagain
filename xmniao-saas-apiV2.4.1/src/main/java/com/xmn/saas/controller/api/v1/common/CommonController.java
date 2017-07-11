package com.xmn.saas.controller.api.v1.common;

import com.xmn.saas.base.AbstractController;
import com.xmn.saas.base.GlobalConfig;
import com.xmn.saas.base.Response;
import com.xmn.saas.base.ResponseCode;
import com.xmn.saas.controller.api.v1.common.vo.*;
import com.xmn.saas.dao.activity.FcouspointsDao;
import com.xmn.saas.dao.activity.FreetryDao;
import com.xmn.saas.dao.activity.KillDao;
import com.xmn.saas.dao.activity.RoulleteDao;
import com.xmn.saas.entity.activity.*;
import com.xmn.saas.entity.common.AppVersion;
import com.xmn.saas.entity.common.JsPatch;
import com.xmn.saas.entity.common.SellerAccount;
import com.xmn.saas.entity.common.SystemAnnouncement;
import com.xmn.saas.entity.coupon.SellerCouponDetail;
import com.xmn.saas.entity.redpacket.Redpacket;
import com.xmn.saas.entity.shop.SellerInfo;
import com.xmn.saas.entity.wallet.Seller;
import com.xmn.saas.exception.SaasException;
import com.xmn.saas.service.account.SellerAccountService;
import com.xmn.saas.service.activity.FullreductionService;
import com.xmn.saas.service.bill.BillService;
import com.xmn.saas.service.common.CommonService;
import com.xmn.saas.service.coupon.CouponService;
import com.xmn.saas.service.image.ImageService;
import com.xmn.saas.service.redpacket.RedpacketService;
import com.xmn.saas.service.shop.SellerInfoService;
import com.xmn.saas.service.sms.SmsService;
import com.xmn.saas.utils.CalendarUtil;
import com.xmn.saas.utils.FileUtil;
import com.xmn.saas.utils.MD5;
import com.xmn.saas.utils.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller( "api-v1-common" )
@RequestMapping( "/api/v1/common" )
public class CommonController extends AbstractController{

    /**
     * 初始化日志类
     */
    private final Logger log = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private CommonService commonService;
    @Autowired
    private BillService billService;
    /**
     * 注入服务类
     */
    @Autowired
    private SellerAccountService sellerAccountService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private GlobalConfig globalConfig;

    @Autowired
    private SmsService smsService;
    
	@Autowired
	private CouponService couponService;
	
	@Autowired
	private RedpacketService redpacketService;
	
	@Autowired
	private SellerInfoService sellerInfoService;
	
	@Autowired
	private FullreductionService  fullreductionService;
	
	@Autowired
    private KillDao  killDao;
	
	@Autowired 
	private RoulleteDao roulleteDao;
	
	@Autowired
	private FreetryDao freetryDao;
	
	@Autowired
	private FcouspointsDao fcouspointsDao;
	
    /**
     * 用户登陆接口,返回会话令牌,令牌有效时间为1个月
     *
     * @param request
     * @param bindingResult
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping( value = "login" , method = RequestMethod.POST )
    public void login(@Valid LoginRequest request, BindingResult bindingResult) throws Exception {
        log.info("【调用登陆接口】-【 post /api/v1/common/login】，参数 : " + request.toString());
        // 校验数据
        if (!request.doValidate(bindingResult)) {
            return;
        }

        try {
            // 创建商户账户,传入登录名和密码
            SellerAccount sellerAccount = request.converToBean();
            HashMap<String, Object> result = sellerAccountService.login(sellerAccount);

            new Response(ResponseCode.SUCCESS, "登陆成功", result).write();
            return;
        } catch (SaasException e) {
            new Response(ResponseCode.FAILURE, e.getMessage()).write();
            return;
        } catch (Exception e) {
            log.error("登陆异常!", e);
            new Response(ResponseCode.FAILURE, "失败!").write();
            return;
        }
    }
 

    /**
     * 首页图片接口
     */
    @ResponseBody
    @RequestMapping(value = "home_menu")
    public void homeIcon() throws IOException {
        log.info("【调用首页图标接口】-【 post /api/v1/common/home_menu】");
        Map<String, List<Map<String, String>>> icons;
        try {
            icons = imageService.getMenus();
        } catch (Exception e) {
            log.error("调用首页图标接口异常!",e);
            new Response(ResponseCode.FAILURE,"获取图片失败!").write();
            return;
        }
        new Response(ResponseCode.SUCCESS,"操作成功!",icons).write();
        return;
    }
    
    
    /**
     * 协议接口
     *
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping( value = "protocol" )
    public void protocol() throws IOException {
        log.info("【调用获取协议接口】-【 post /api/v1/common/protocol】");
        String str = globalConfig.getSaasProtocol();
        Map<String, String> map = new HashMap<>();
        map.put("text", str);
        new Response(ResponseCode.SUCCESS, "操作成功!", map).write();
        return;
    }
    /**
     * 分享地址
     * @param request
     * @param bindingResult
     * @param req
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping( value = "activity_share_url" )
    public void activityShareUrl(@Valid ShareRequest request, BindingResult bindingResult,
            HttpServletRequest req) throws Exception {
        log.info("【调用获取活动分享地址接口】-【 post /api/v1/common/activity_share_url】" + "参数为："
                + request.toString());
        // 校验数据
        if (!request.doValidate(bindingResult)) {
            return;
        }
        String url  =  commonService.getShareUrl(request);
        Map<String, String> map = new HashMap<>();
        map.put("url", url);
        new Response(ResponseCode.SUCCESS, "操作成功!", map).write();  
        return;
    }
    /**
     * 下载物料接口
     * @param request
     * @param bindingResult
     * @param response
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping( value = "download_material" )
    public void downloadMaterial(@Valid DownloadMaterialRequest request, 
    		BindingResult bindingResult, HttpServletResponse response) throws Exception {
        if (!request.doValidate(bindingResult)) {
            return;
        }
    	HttpServletRequest req = WebUtils.getRequest();
        log.info("【调用下载物料接口】-【 post /api/v1/common/download_material】" + "参数为："
                + request.toString());
        String url = "";//H5活动页面地址
        String posterImg = "";//对应的活动海报底图
        String sellerName ="";//商家名称
        String dateTime ="";//开始结束日期
        SellerCouponDetail  sellerCouponDetail = null;
        Fullreduction fullreduction =null;
        if(request.getType()==1||request.getType()==2||request.getType()==3||request.getType()==4||request.getType()==5){
            //红包详情
           Redpacket redpacket =  redpacketService.findRedpacketByPrimaryKey(Long.valueOf(request.getId()));
           dateTime = CalendarUtil.getDateString(redpacket.getBeginDate(), "yyyy-MM-dd")+"至"+CalendarUtil.getDateString(redpacket.getEndDate(), "yyyy-MM-dd");
           //商户详情
           SellerInfo  sellerInfo= sellerInfoService.querySellerBySellerid(redpacket.getSellerid());
           sellerName = sellerInfo.getSellerName();
        }else if(request.getType()==6||request.getType()==7){
             sellerCouponDetail =   couponService.selectByPrimaryKey(request.getId());
             dateTime = sellerCouponDetail.getStartDate().substring(0,10)+"至"+sellerCouponDetail.getEndDate().substring(0,10);
             //商户详情
             SellerInfo  sellerInfo= sellerInfoService.querySellerBySellerid(sellerCouponDetail.getSellerid());
             sellerName = sellerInfo.getSellerName();
        }else if(request.getType()==8){
           fullreduction =   fullreductionService.selectByPrimaryKey(request.getId());
           dateTime = fullreduction.getBeginDate().substring(0,10)+"至"+fullreduction.getEndDate().substring(0,10);
           //商户详情
           SellerInfo  sellerInfo= sellerInfoService.querySellerBySellerid(fullreduction.getSellerid());
           sellerName = sellerInfo.getSellerName();
        }else if(request.getType()==9){
          Kill kill =   killDao.findByPrimaryKey(request.getId());
          dateTime =CalendarUtil.getDateString(kill.getBeginDate(), "yyyy-MM-dd") +"至"+CalendarUtil.getDateString(kill.getEndDate(), "yyyy-MM-dd");
          //商户详情
          SellerInfo  sellerInfo= sellerInfoService.querySellerBySellerid(kill.getSellerid());
          sellerName = sellerInfo.getSellerName();
        }else if(request.getType()==10){
            Roullete roullete =   roulleteDao.findByPrimaryKey(request.getId());
            dateTime =CalendarUtil.getDateString(roullete.getBeginDate(), "yyyy-MM-dd") +"至"+CalendarUtil.getDateString(roullete.getEndDate(), "yyyy-MM-dd");
            //商户详情
            SellerInfo  sellerInfo= sellerInfoService.querySellerBySellerid(roullete.getSellerid());
            sellerName = sellerInfo.getSellerName();
         }else if(request.getType()==11){
             Freetry freetry =   freetryDao.findByPrimaryKey(request.getId());
             dateTime =CalendarUtil.getDateString(freetry.getBeginDate(), "yyyy-MM-dd") +"至"+CalendarUtil.getDateString(freetry.getEndDate(), "yyyy-MM-dd");
             //商户详情
             SellerInfo  sellerInfo= sellerInfoService.querySellerBySellerid(freetry.getSellerid());
             sellerName = sellerInfo.getSellerName();
          }else if(request.getType()==12){
        	  Fcouspoints fcouspoints = fcouspointsDao.selectByPrimaryKey(request.getId());
        	  dateTime =CalendarUtil.getDateString(fcouspoints.getBeginDate(), "yyyy-MM-dd") +"至"+CalendarUtil.getDateString(fcouspoints.getEndDate(), "yyyy-MM-dd");
        	  SellerInfo  sellerInfo= sellerInfoService.querySellerBySellerid(fcouspoints.getSellerid());
        	  sellerName = sellerInfo.getSellerName();
          }
        dateTime = "有效期："+dateTime;
        //sellerName = "本活动最终解释权归"+sellerName+"商家所有";
        
        
        try {
        	switch(request.getType()){
        	case 1://分享引流红包
        		url = globalConfig.getRedpacketShareUrl()+"?id="+request.getId();
        		posterImg = "/redpacket/drainage-redpacket.jpg";
        		break;
        	case 2://满赠红包
        		url = globalConfig.getRedpacketShareUrl()+"?id="+request.getId();
        		posterImg = "/redpacket/consume-redpacket.jpg";
        		break;
        	case 3://普通红包
        		url = globalConfig.getRedpacketShareUrl()+"?id="+request.getId();
        		posterImg = "/redpacket/common-redpacket.jpg";
        		break;
        	case 4://限时到店红包
        		url = globalConfig.getRedpacketShareUrl()+"?id="+request.getId();
        		posterImg = "/redpacket/limit-redpacket.jpg";
        		break;
        	case 5://推荐红包
        		url = globalConfig.getRedpacketShareUrl()+"?id="+request.getId();
        		posterImg = "/redpacket/recommend-redpacket.jpg";
        		break;
        	case 6://现金抵用券
                url = globalConfig.getCouponShareUrl()+"?s_id="+sellerCouponDetail.getSellerid()+"&c_type=3";
        		posterImg = "/coupon/cash-coupon.jpg";
        		break;
        	case 7://赠品券
                url = globalConfig.getCouponShareUrl()+"?s_id="+sellerCouponDetail.getSellerid()+"&c_type=4";
        		posterImg = "/coupon/gift-coupon.jpg";
        		break;
        	case 8://满就减
        		url = globalConfig.getFullcutShareUrl()+"?re_id="+request.getId();
        		posterImg = "/activity/fullcut-activity.jpg";
        		break;
        	case 9://秒杀
        		url = globalConfig.getKillShareUrl()+"?act_id="+request.getId();
        		posterImg = "/activity/kill-activity.jpg";
        		break;
        	case 10://大转盘
        		url = globalConfig.getRoulleteShareUrl()+"?act_id="+request.getId();
        		posterImg = "/activity/roullete-activity.jpg";
        		break;
        	case 11://免费尝新
        		url = globalConfig.getFreetryShareUrl()+"?act_id="+request.getId();
        		posterImg = "/activity/freetry-activity.jpg";
        		break;
        	case 12://集点活动
        		url=globalConfig.getFcouspointsShareUrl()+"?act_id="+request.getId();
        		posterImg = "/activity/fcouspoints-activity.jpg";
        		break;
        	}
        	String localPath = req.getSession().getServletContext().getRealPath("/imgs");
        	//生成活动海报图片
        	String shareUrl= "";
            if(request.getType()==9){
                shareUrl = url+"&codeType=99";
            }else{
                shareUrl = globalConfig.getShareUrl()+url+"&codeType=99";
            }
        	
        	byte[] bos = FileUtil.downloadQRCode(shareUrl, localPath, posterImg,sellerName,dateTime);
        	response.setContentType("image/png");
        	OutputStream os = response.getOutputStream();
        	os.write(bos);
        	os.close();
        } catch (Exception e) {
        	log.error("downloadMaterial occured error={}",e);
        }
    }

    /**
     * 忘记登录密码
     */
    @ResponseBody
    @RequestMapping( value = "forget_password" , method = RequestMethod.POST )
    public void forgetPassword(@Valid CommonForgetPasswordRequest request, BindingResult result)
            throws Exception {
        log.info("【调用忘记登录密码接口】-【 post /api/v1/common/forget_password】");

        if (!request.doValidate(result)) {
            return;
        }

        SellerAccount sellerAccount = request.converSellerAccount();
        String verifyCode = request.getVerifyCode();
        Integer operation = request.getOperation();

        try {
            sellerAccountService.forgetPassword(sellerAccount, verifyCode, operation);
        } catch (SaasException e) {
            new Response(e.getCode(), e.getMessage()).write();
            return;
        } catch (Exception e) {
            log.error("调用忘记登录密码接口出现异常!", e);
            new Response(ResponseCode.FAILURE, "操作失败!").write();
            return;
        }

        new Response(ResponseCode.SUCCESS, "操作成功!").write();
        return;
    }

    /**
     * app 更新接口
     * @param request
     * @param result
     */
    @ResponseBody
    @RequestMapping(value = "app_update",method = RequestMethod.POST)
    public void appUpdate(@Valid CommonAppUpdateRequest request, BindingResult result) throws Exception {
        log.info("【调用App更新接口】-【 post /api/v1/common/app_update】");
        if (!request.doValidate(result)) {
            return;
        }
        // 获取SystemVersion
        String systemVersion = request.getSystemVersion();

        Map<String, Object> map;
        try {
            // 获取响应数据
            map = commonService.appUpdate(systemVersion,request.getAppVersion());
        } catch (SaasException e) {
            new Response(e.getCode(),e.getMessage()).write();
            return;
        } catch (Exception e) {
            log.error("调用系统更新接口出现异常!",e);
            new Response(ResponseCode.FAILURE,"操作失败").write();
            return;
        }
        new Response(ResponseCode.SUCCESS,"操作成功!",map).write(
                new HashMap<Class<?>, String[]>(){{
                    put(AppVersion.class,new String[]{"version","url","content", "sdate","mustUpdate"});
                }},
                "yyyy-MM-dd");
    }
    
    
    
    /**
     * 粉丝券登录页面
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "Login")
    public String fansLogin() throws Exception {
        return "/login/login";
    }
    
    /**
     * 粉丝券登录页面
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "submitform")
    public String submitform(SellerAccount sellerAccount) throws Exception {
        try {
            sellerAccount.setPassword(MD5.Encode(sellerAccount.getPassword()));
            SellerAccount account = sellerAccountService.fansLogin(sellerAccount);
            
            this.list("1", "20", account.getSellerid(),1);
            WebUtils.getRequest().setAttribute("sellerid", account.getSellerid());
            WebUtils.getRequest().setAttribute("type", 1);
            return "login/billList";
        } catch (Exception e) {
            new Response(1,e.getMessage()).write();
        }
        return null;
    }
    /**
     * 获取粉丝券分页数据
     * @param page
     * @param rows
     * @param sellerid
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "list")
    public void list(String page,String rows,int sellerid,int type) throws Exception {
        try {
            Map<Object, Object> list =  billService.list(Integer.valueOf(page), Integer.valueOf(rows), sellerid,type);
            new Response(ResponseCode.SUCCESS,"操作成功!",list).write();
        } catch (Exception e) {
            new Response(1,e.getMessage()).write();
        }
    }
    
    /**
     * 查询最新一条公告
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "announcement")
    public void announcement() throws Exception {
        try {
            success(commonService.selectOne(),new HashMap<Class<?>, String[]>() {
                {
                    put(SystemAnnouncement.class, new String[] { "id" , "content" ,"sdate","status"});
                }
            }, "yyyy-MM-dd HH:mm:ss" );
        } catch (Exception e) {
            failure("查询公告失败");
        }
        return;
    }

    /**
     * 为IOS提供 jsPatch 更新支持
     */
    @RequestMapping(value = "js_patch",method = RequestMethod.POST)
    @ResponseBody
    public void jsPatch(@Valid JsPatchRequest request,BindingResult result) throws Exception {
        log.info("调用[IOS热更新jsPatch支持接口 /api/v1/common/js_patch POST] 参数:"+request.toString());
        if (!request.doValidate(result)) {
            return;
        }
        try {
            JsPatch jsPatch = request.convertJsPatch();
            if (request.getType() == 1) {
                success(commonService.getJsPatch(jsPatch));
            }
            if(request.getType() == 2){
                commonService.updateJsPatch(jsPatch);
                success();
            }
        } catch (SaasException e) {
            failure(e);
        } catch (Exception e) {
            log.error("调用[IOS热更新jsPatch支持接口 /api/v1/common/js_patch POST] 出现异常",e);
            failure();
        }
    }


    /**
     * @name    查询账户关联的店铺列表
     * @url     /api/v1/common/shop_list
     * @method  POST
     * @param account   账户
     */
    @RequestMapping(value = "shop_list",method = RequestMethod.POST)
    @ResponseBody
    public void shopList(@Valid ShopListRequest request,BindingResult result) throws Exception {
        log.info("调用[店铺列表接口 /api/v1/common/shop_list POST]参数:"+request.toString());
        if (!request.doValidate(result)) {
            return;
        }
        try {
            List<Seller> sellers = sellerAccountService.queryShopList(request.getAccount().trim());
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("sellers",sellers);
            success(resultMap,new HashMap<Class<?>, String[]>(){{
                put(Seller.class,new String[]{"sellername","sellerid"});
            }});
        } catch (SaasException e) {
            failure(e);
        } catch (Exception e) {
            log.error("调用[店铺列表接口 /api/v1/common/shop_list POST] 出现异常",e);
            failure();
        }

    }

}
