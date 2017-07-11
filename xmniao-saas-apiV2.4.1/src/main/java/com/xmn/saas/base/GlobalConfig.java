package com.xmn.saas.base;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 获取properties属性值常量类
 * 
 * @author zhouxiaojian
 *
 */
@Component( value = "globalConfig" )
public class GlobalConfig {
    // 支付thrift地址
    @Value( "#{configProperties['thrift.pay.host']}" )
    private String thriftPayHost;
    // thrift端口
    @Value( "#{configProperties['thrift.pay.port']}" )
    private String thriftPayPort;
    // 分账thrift地址
    @Value( "#{configProperties['thrift.ledger.host']}" )
    private String thriftLedgerHost;
    // thrift端口
    @Value( "#{configProperties['thrift.ledger.port']}" )
    private String thriftLedgerPort;
    // 业务thrift地址
    @Value( "#{configProperties['thrift.bus.host']}" )
    private String thriftBusHost;
    // thrift端口
    @Value( "#{configProperties['thrift.bus.port']}" )
    private String thriftBusPort;

    // 短信服务调用接口
    @Value( "${sms.smsUrl}" )
    private String smsServiceSendUrl;
    // 注入 登录密码 短信模板
    @Value( "${sms.loginPasswordTemplate}" )
    private String smsLoginTemplate;
    // 注入 支付密码 短信模板
    @Value( "${sms.payPasswordTemplate}" )
    private String smsPayTemplate;
    // 注入 手势密码 短信模板
    @Value( "${sms.gesturePasswordTemplate}" )
    private String smsGestureTemplate;
    @Value( "${sms.bindingBankCard}" )
    private String bindingBankCard;

    // 已上传文件(图片)的访问域名
    @Value( "${file.upload.fastDFS.http}" )
    private String imageHost;

    // 以下提现手续费设置
    @Value( "${shop.account.underLimit}" )
    private double underLimit;
    // 以上提现手续费设置
    @Value( "${shop.account.moreLimit}" )
    private double moreLimit;
    // 提现手续费设置
    @Value( "${shop.account.limit}" )
    private double limit;

    // 协议文字
    @Value( "#{configProperties['saas.protocol']}" )
    private String saasProtocol;

    // 活动分享地址
    // 活动分享地址
    @Value( "#{configProperties['share.url']}" )
    private String shareUrl;
    @Value( "#{configProperties['share.redpacket.url']}" )
    private String redpacketShareUrl;
    @Value( "#{configProperties['share.coupon.url']}" )
    private String couponShareUrl;
    @Value( "#{configProperties['share.fullcut.url']}" )
    private String fullcutShareUrl;
    @Value( "#{configProperties['share.kill.url']}" )
    private String killShareUrl;
    @Value( "#{configProperties['share.freetry.url']}" )
    private String freetryShareUrl;
    @Value( "#{configProperties['share.roullete.url']}" )
    private String roulleteShareUrl;
    @Value( "#{configProperties['share.fcouspoints.url']}" )
    private String fcouspointsShareUrl;
    @Value( "#{configProperties['share.micrograph.url']}" )
    private String micrographShareUrl;

    // 验单时间分界线
    @Value( "#{configProperties['vertify.date']}" )
    private String vertifyDate;

    // Redis中存放SessionToken的有效时间, 单位为分
    @Value( "#{configProperties['redis.time.sessionToken']}" )
    private Integer redisTimeSessionToken;
    // Redis中存放短信验证码的有效时间, 单位为分
    @Value( "#{configProperties['redis.time.verifyCode']}" )
    private Integer reidsTimeVerifyCode;
    // 商户支付密码输错锁定时间
    @Value("#{configProperties['redis.time.payCount']}")
    private Integer payCountTime;

    // 微信公众平台支付地址
    @Value( "#{configProperties['common.codePayUrl']}" )
    private String wxPayUrl;
    
    //扫码支付key
    @Value( "#{configProperties['micro.bill.key']}" )
    private String microBillKey;
    
    //扫码支付地址
    @Value( "#{configProperties['micro.bill.host']}" )
    private String microBillHost;
   //扫码支付
    @Value( "#{configProperties['micro.bill.pay']}" )
    private String microBillPayment;
  //扫码查询
    @Value( "#{configProperties['micro.bill.query']}" )
    private String microBillQuery;
  //扫码撤销
    @Value( "#{configProperties['micro.bill.cancel']}" )
    private String microBillCancel;
    
    //#储值卡扫码
    @Value( "#{configProperties['seller.card.code.url']}" )
    private String sellerCardCodeUrl;
    
    
    
    
    public String getSellerCardCodeUrl() {
        return sellerCardCodeUrl;
    }

    public void setSellerCardCodeUrl(String sellerCardCodeUrl) {
        this.sellerCardCodeUrl = sellerCardCodeUrl;
    }

    public String getFcouspointsShareUrl() {
		return fcouspointsShareUrl;
	}

	public void setFcouspointsShareUrl(String fcouspointsShareUrl) {
		this.fcouspointsShareUrl = fcouspointsShareUrl;
	}

	public String getMicrographShareUrl() {
		return micrographShareUrl;
	}

	public void setMicrographShareUrl(String micrographShareUrl) {
		this.micrographShareUrl = micrographShareUrl;
	}

	public String getMicroBillPayment() {
        return microBillPayment;
    }

    public void setMicroBillPayment(String microBillPayment) {
        this.microBillPayment = microBillPayment;
    }

    public String getMicroBillQuery() {
        return microBillQuery;
    }

    public void setMicroBillQuery(String microBillQuery) {
        this.microBillQuery = microBillQuery;
    }

    public String getMicroBillCancel() {
        return microBillCancel;
    }

    public void setMicroBillCancel(String microBillCancel) {
        this.microBillCancel = microBillCancel;
    }

    public String getMicroBillKey() {
        return microBillKey;
    }

    public void setMicroBillKey(String microBillKey) {
        this.microBillKey = microBillKey;
    }

    public String getMicroBillHost() {
        return microBillHost;
    }

    public void setMicroBillHost(String microBillHost) {
        this.microBillHost = microBillHost;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getWxPayUrl() {
        return wxPayUrl;
    }

    public void setWxPayUrl(String wxPayUrl) {
        this.wxPayUrl = wxPayUrl;
    }

    public String getThriftPayHost() {
        return thriftPayHost;
    }

    public void setThriftPayHost(String thriftPayHost) {
        this.thriftPayHost = thriftPayHost;
    }

    public String getThriftPayPort() {
        return thriftPayPort;
    }

    public void setThriftPayPort(String thriftPayPort) {
        this.thriftPayPort = thriftPayPort;
    }

    public String getThriftLedgerHost() {
        return thriftLedgerHost;
    }

    public void setThriftLedgerHost(String thriftLedgerHost) {
        this.thriftLedgerHost = thriftLedgerHost;
    }

    public String getThriftLedgerPort() {
        return thriftLedgerPort;
    }

    public void setThriftLedgerPort(String thriftLedgerPort) {
        this.thriftLedgerPort = thriftLedgerPort;
    }

    public String getThriftBusHost() {
        return thriftBusHost;
    }

    public void setThriftBusHost(String thriftBusHost) {
        this.thriftBusHost = thriftBusHost;
    }

    public String getThriftBusPort() {
        return thriftBusPort;
    }

    public void setThriftBusPort(String thriftBusPort) {
        this.thriftBusPort = thriftBusPort;
    }

    public String getSmsServiceSendUrl() {
        return smsServiceSendUrl;
    }

    public void setSmsServiceSendUrl(String smsServiceSendUrl) {
        this.smsServiceSendUrl = smsServiceSendUrl;
    }

    public String getSmsLoginTemplate() {
        return smsLoginTemplate;
    }

    public void setSmsLoginTemplate(String smsLoginTemplate) {
        this.smsLoginTemplate = smsLoginTemplate;
    }

    public String getSmsPayTemplate() {
        return smsPayTemplate;
    }

    public void setSmsPayTemplate(String smsPayTemplate) {
        this.smsPayTemplate = smsPayTemplate;
    }

    public String getSmsGestureTemplate() {
        return smsGestureTemplate;
    }

    public void setSmsGestureTemplate(String smsGestureTemplate) {
        this.smsGestureTemplate = smsGestureTemplate;
    }

    public String getBindingBankCard() {
        return bindingBankCard;
    }

    public void setBindingBankCard(String bindingBankCard) {
        this.bindingBankCard = bindingBankCard;
    }

    public String getImageHost() {
        return imageHost;
    }

    public void setImageHost(String imageHost) {
        this.imageHost = imageHost;
    }

    public double getUnderLimit() {
        return underLimit;
    }

    public void setUnderLimit(double underLimit) {
        this.underLimit = underLimit;
    }

    public double getMoreLimit() {
        return moreLimit;
    }

    public void setMoreLimit(double moreLimit) {
        this.moreLimit = moreLimit;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public String getSaasProtocol() {
        return saasProtocol;
    }

    public void setSaasProtocol(String saasProtocol) {
        this.saasProtocol = saasProtocol;
    }

    public String getRedpacketShareUrl() {
        return redpacketShareUrl;
    }

    public void setRedpacketShareUrl(String redpacketShareUrl) {
        this.redpacketShareUrl = redpacketShareUrl;
    }

    public String getCouponShareUrl() {
        return couponShareUrl;
    }

    public void setCouponShareUrl(String couponShareUrl) {
        this.couponShareUrl = couponShareUrl;
    }

    public String getFullcutShareUrl() {
        return fullcutShareUrl;
    }

    public void setFullcutShareUrl(String fullcutShareUrl) {
        this.fullcutShareUrl = fullcutShareUrl;
    }

    public String getKillShareUrl() {
        return killShareUrl;
    }

    public void setKillShareUrl(String killShareUrl) {
        this.killShareUrl = killShareUrl;
    }

    public String getFreetryShareUrl() {
        return freetryShareUrl;
    }

    public void setFreetryShareUrl(String freetryShareUrl) {
        this.freetryShareUrl = freetryShareUrl;
    }

    public String getRoulleteShareUrl() {
        return roulleteShareUrl;
    }

    public void setRoulleteShareUrl(String roulleteShareUrl) {
        this.roulleteShareUrl = roulleteShareUrl;
    }

    public String getVertifyDate() {
        return vertifyDate;
    }

    public void setVertifyDate(String vertifyDate) {
        this.vertifyDate = vertifyDate;
    }

    public Integer getRedisTimeSessionToken() {
        return redisTimeSessionToken;
    }

    public void setRedisTimeSessionToken(Integer redisTimeSessionToken) {
        this.redisTimeSessionToken = redisTimeSessionToken;
    }

    public Integer getReidsTimeVerifyCode() {
        return reidsTimeVerifyCode;
    }

    public void setReidsTimeVerifyCode(Integer reidsTimeVerifyCode) {
        this.reidsTimeVerifyCode = reidsTimeVerifyCode;
    }

    public Integer getPayCountTime() {
        return payCountTime;
    }

    public void setPayCountTime(Integer payCountTime) {
        this.payCountTime = payCountTime;
    }
}
