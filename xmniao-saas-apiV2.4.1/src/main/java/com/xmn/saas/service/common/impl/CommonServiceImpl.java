package com.xmn.saas.service.common.impl;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import com.xmn.saas.entity.common.JsPatch;
import com.xmn.saas.service.base.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmn.saas.base.GlobalConfig;
import com.xmn.saas.base.ResponseCode;
import com.xmn.saas.base.thrift.common.ResponsePageList;
import com.xmn.saas.controller.api.v1.common.vo.ShareRequest;
import com.xmn.saas.dao.common.AppVersionDao;
import com.xmn.saas.dao.common.SystemAnnouncementDao;
import com.xmn.saas.entity.common.AppVersion;
import com.xmn.saas.entity.common.SystemAnnouncement;
import com.xmn.saas.entity.coupon.SellerCouponDetail;
import com.xmn.saas.exception.SaasException;
import com.xmn.saas.service.bill.StatisticalService;
import com.xmn.saas.service.common.CommonService;
import com.xmn.saas.service.coupon.CouponService;
import com.xmn.saas.utils.CalendarUtil;

/**
 * create 2016/10/21
 *
 * @author yangQiang
 */
@Service
public class CommonServiceImpl implements CommonService {
    public static String REDIS_KEY_JS_PATCH = "saas:api:js.patch:";

    private Logger log = LoggerFactory.getLogger(CommonService.class);

    @Autowired
    private StatisticalService statisticalService;
    
    @Autowired
    private SystemAnnouncementDao systemAnnouncementDao;

    @Autowired
    private GlobalConfig globalConfig;

    @Autowired
    private AppVersionDao appVersionDao;
    
    @Autowired
    private CouponService couponService;

    @Autowired
    private RedisService redisService;
    /**
     * 计算获取活动期间刺激消费金额=活动期间总营业额度  - 非活动期间总营业额度
     *
     * @param sellerid  商户id
     * @param beginDate 开始时间
     * @param endDate   结束时间
     * @return BigDecimal    返回类型
     * @throws
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public BigDecimal getActiveAmount(int sellerid, Date beginDate, Date endDate) {
              BigDecimal stimulateConsume = new BigDecimal("0.00");//刺激消费金额
       try {
    		   Map<String, String> walletMap = new HashMap<>();
   	   	       Calendar calendar = Calendar.getInstance(); //得到日历
   	   	       String endTime = CalendarUtil.sdf.format(endDate);
   	   	       if (CalendarUtil.sdf.parse(CalendarUtil.sdf.format(new Date())).compareTo(endDate) == -1) {
   	   	            endTime = CalendarUtil.dateFormat(new Date(), CalendarUtil.FORMAT1);
   	   	            //calendar.setTime(CalendarUtil.sdf.parse(endTime));
      	           // calendar.add(Calendar.DAY_OF_MONTH, -1);  //设置为前一天
      	          // endTime = CalendarUtil.sdf.format(calendar.getTime());
   	   	       }
   	   	        
   	   	       walletMap.put("uId", String.valueOf(sellerid));
   	           walletMap.put("userType", "2");
   	           walletMap.put("sdate", CalendarUtil.sdf.format(beginDate));
   	   	       walletMap.put("edate", endTime);
   	   	       ResponsePageList responseList1 = statisticalService.getBusinessList(walletMap); //获取活动期的总营业额
   	           	
               int days = CalendarUtil.daysBetween(CalendarUtil.sdf.parse(walletMap.get("sdate")), CalendarUtil.sdf.parse(walletMap.get("edate"))) + 1;
               String sdate = CalendarUtil.distanceDay(beginDate, days);
               calendar.setTime(beginDate);
               calendar.add(Calendar.DAY_OF_MONTH, -1);  //设置为前一天
               String edate = CalendarUtil.sdf.format(calendar.getTime());//得到前一天的时间;
               walletMap.put("sdate", sdate);
               walletMap.put("edate", edate);
               ResponsePageList responseList = statisticalService.getBusinessList(walletMap); //获取非活动期的总营业额
            
               if (responseList.getDataInfo().getState() == 0 && responseList1.getDataInfo().getState() == 0) {
                  /*
                   * 活动期间总营业额度  - 非活动期间总营业额度  = 刺激消费
                   */
                  BigDecimal totalAmount = new BigDecimal("0.00"); //获取活动期的总营业额
                  BigDecimal totalAmountNo = new BigDecimal("0.00"); //获取非活动期的总营业额
                  if (responseList1.getDataInfo().resultMap != null) {
                      totalAmount = new BigDecimal(responseList1.getDataInfo().resultMap.get("totalAmount"));
                  }
                  if (responseList.getDataInfo().resultMap != null) {
                      totalAmountNo = new BigDecimal(responseList.getDataInfo().resultMap.get("totalAmount"));
                  }
                  stimulateConsume = totalAmount.subtract(totalAmountNo); //获得刺激消费
              }
          } catch (Exception e) {
            log.error("获取活动刺激消费金额异常={}", e.toString());
            e.printStackTrace();
          }
          return stimulateConsume;
    }





    /**
     * 获取app更新相关信息
     *
     * @param systemVersion
     * @param appVersion
     * @return
     */
    public Map<String, Object> appUpdate(String systemVersion, String version) throws SaasException, IOException {
        Map<String, Object> result = new LinkedHashMap<>();
        AppVersion appVersion = new AppVersion();
        // 设置APP类型为商户端
        appVersion.setApptype(2);

        if (systemVersion.matches("android.*")) {    // 校验是否为android
            // 设置APP平台为Android
            appVersion.setVtype(1);
            appVersion = appVersionDao.selectCurrentAppVersion(appVersion);
            // 安卓需要设置URL前缀 http://gzdev.xmniao.com:88/
            appVersion.setUrl(globalConfig.getImageHost()+appVersion.getUrl());
        } else if (systemVersion.matches("ios.*")) { // 校验是否为ios
            // 设置APP平台为IOS
            appVersion.setVtype(2);
            // 查询appVersion
            appVersion = appVersionDao.selectCurrentAppVersion(appVersion);
        } else {
            throw new SaasException("系统版本号输入错误", ResponseCode.PARAM_ERROR);
        }

        result.put("appVersion",appVersion);

        // 读取配置文件获取mark(地区,首页菜单)
        Resource resource = new ClassPathResource("/properties/conf-version.properties");
        Properties properties = new Properties();
        try(InputStream in = resource.getInputStream()){
            properties.load(in);
            // 从配置文件中获取标记
            String homeMenuMark = properties.getProperty("sass.mark.homeMenu");
            String areaMark = properties.getProperty("sass.mark.area");

            result.put("homeMenuMark",homeMenuMark);
            result.put("areaMark",areaMark);
        }

        // 当前版本信息
        return result;
    }




    /**
     * 获取分享地址
     * @return
     */
    @Override
    public String getShareUrl(ShareRequest request) {
        String url = "";
        switch (request.getType()) {
            case 1://分享引流红包
                url = globalConfig.getRedpacketShareUrl()+"?id="+request.getId();
                request.setTitle("红包要分享，也要私藏，拼的就是人品！");
                request.setDesc("红包，美食，我都要！");
                break;
            case 2://满赠红包
                url = globalConfig.getRedpacketShareUrl()+"?id="+request.getId();
                request.setTitle("你吃饭 , 我掏钱，就怕你不\"约\"!");
                request.setDesc("吃饱了，来个红包压压惊！");
                break;
            case 3://普通红包
                url = globalConfig.getRedpacketShareUrl()+"?id="+request.getId();
                request.setTitle("红包如戏全拼运气，认真你就赢了！");
                request.setDesc("我给你发了一个红包，赶紧去拆！");
                break;
            case 4://限时到店红包
                url = globalConfig.getRedpacketShareUrl()+"?id="+request.getId();
                request.setTitle("派红包，攒人气！不要白不要！要了还想要！");
                request.setDesc("你来或不来，红包都在这儿！");
                break;
            case 5://推荐红包
                url = globalConfig.getRedpacketShareUrl()+"?id="+request.getId();
                request.setTitle("尝不到他的鲜，拿他点钱也不错！");
                request.setDesc("美食齐享，分享有赏！");
                break;
            case 6://现金抵用券
                SellerCouponDetail  sellerCouponDetail =   couponService.selectByPrimaryKey(request.getId());
                request.setTitle("一大波巅峰钜惠，正在接近中！ ");
                request.setDesc("老板向你扔来一张现金券！");
                url = globalConfig.getCouponShareUrl()+"?s_id="+sellerCouponDetail.getSellerid()+"&c_type=3";
                break;
            case 7://赠品券
                SellerCouponDetail  couponDetail =   couponService.selectByPrimaryKey(request.getId());
                request.setTitle("美味送送送，吃货的幸福时光！ ");
                request.setDesc("免费的美餐，原来是真的！");
                url = globalConfig.getCouponShareUrl()+"?s_id="+couponDetail.getSellerid()+"&c_type=4";
                break;
            case 8://满就减
                url = globalConfig.getFullcutShareUrl()+"?re_id="+request.getId();
                request.setTitle("满满都是爱，减减更健康！");
                request.setDesc("满额买单送优惠哦！");
                break;
            case 9://秒杀
                url = globalConfig.getKillShareUrl()+"?act_id="+request.getId();
                request.setTitle("嘴巴享受心里想瘦，就在这一秒！");
                request.setDesc("超低价秒美食，玩的就是心跳！");
                break;
            case 10://大转盘
                url = globalConfig.getRoulleteShareUrl()+"?act_id="+request.getId();
                request.setTitle("幸运转出来，轻松一动，尽享好运来！");
                request.setDesc("海量礼品，等你来抽！");
                break;
            case 11://免费尝新
                url = globalConfig.getFreetryShareUrl()+"?act_id="+request.getId();
                request.setTitle("真爱从不辜负吃货，有新就让你尝！");
                request.setDesc("新鲜出炉的新品，免费尝！");
                break;
            case 12://微图助力
            	url=globalConfig.getFcouspointsShareUrl()+"?act_id="+request.getId();
            	request.setTitle("会员集点送好礼！");
            	request.setDesc("缤纷好礼，等你来领");
        }
        
        String shareUrl= "";
        if(request.getType()==9){
            shareUrl = url+"#flag"+"&id="+request.getId()+"&title="+request.getTitle()+"&downloadType="+request.getType()+"&desc="+request.getDesc() ;
        }else{
            shareUrl = globalConfig.getShareUrl()+url+"#flag"+"&id="+request.getId()+"&title="+request.getTitle()+"&downloadType="+request.getType()+"&desc="+request.getDesc();
        }

        return shareUrl;
    }





    @Override
    public SystemAnnouncement selectOne() throws SaasException{
        return systemAnnouncementDao.selectOne();
    }

    // 获取JsPatch存储的代码
    @Override
    public JsPatch getJsPatch(JsPatch jsPatch) {
        JsPatch result = new JsPatch();
        result.setVersion(jsPatch.getVersion());
        String code = redisService.getString(REDIS_KEY_JS_PATCH + jsPatch.getVersion());
        if (code !=null) {
            result.setCode(code);
        }
        return result;
    }

    // 更新jsPatch存储
    @Override
    public void updateJsPatch(JsPatch jsPatch) throws IOException {
        // TODO 验证密码
        Resource resource = new ClassPathResource("properties/conf-config.properties");
        Properties properties = new Properties();
        properties.load(resource.getInputStream());
        if (!jsPatch.getPassword().equals(properties.getProperty("js.patch.update.password"))) {
            throw new SaasException("密码错误");
        }
        redisService.setString(REDIS_KEY_JS_PATCH + jsPatch.getVersion(), jsPatch.getCode());

    }


}
