package com.xmn.designer.base;

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
    
    @Value( "#{configProperties['thrift.order.host']}" )
    private String thriftOrderHost;
    // thrift端口
    @Value( "#{configProperties['thrift.order.port']}" )
    private String thriftOrderPort; 


    // 已上传文件(图片)的访问域名
    @Value( "${file.upload.fastDFS.http}" )
    private String imageHost;
    
    // Redis中存放SessionToken的有效时间, 单位为分
    @Value( "#{configProperties['redis.time.sessionToken']}" )
    private Integer redisTimeSessionToken;
    
    // 商户支付密码输错锁定时间
    @Value("#{configProperties['redis.time.payCount']}")
    private Integer payCountTime;



    // 快递接口相关配置信息
    @Value("#{configProperties['express.api.url']}")
    private String expInfoApiUrl;       // 接口URl
    @Value("#{configProperties['express.api.appkey']}")
    private String expInfoApiAppkey;    // 接口appkey


    @Value("#{configProperties['order.customize.high.spec.name']}")
    private String highName;         //定制物料 高亮显示规格
    
    @Value("#{configProperties['order.send.url']}")
    private String orderSendUrl;         //平面物料通知合成图URL
    
    

    public String getOrderSendUrl() {
        return orderSendUrl;
    }

    public void setOrderSendUrl(String orderSendUrl) {
        this.orderSendUrl = orderSendUrl;
    }

    public String getHighName() {
        return highName;
    }

    public void setHighName(String highName) {
        this.highName = highName;
    }

    public String getExpInfoApiUrl() {
        return expInfoApiUrl;
    }

    public void setExpInfoApiUrl(String expInfoApiUrl) {
        this.expInfoApiUrl = expInfoApiUrl;
    }

    public String getExpInfoApiAppkey() {
        return expInfoApiAppkey;
    }

    public void setExpInfoApiAppkey(String expInfoApiAppkey) {
        this.expInfoApiAppkey = expInfoApiAppkey;
    }

    public Integer getRedisTimeSessionToken() {
        return redisTimeSessionToken;
    }

    public void setRedisTimeSessionToken(Integer redisTimeSessionToken) {
        this.redisTimeSessionToken = redisTimeSessionToken;
    }

    public String getImageHost() {
        return imageHost;
    }

    public void setImageHost(String imageHost) {
        this.imageHost = imageHost;
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

	public Integer getPayCountTime() {
		return payCountTime;
	}

	public void setPayCountTime(Integer payCountTime) {
		this.payCountTime = payCountTime;
	}

	public String getThriftOrderHost() {
		return thriftOrderHost;
	}

	public void setThriftOrderHost(String thriftOrderHost) {
		this.thriftOrderHost = thriftOrderHost;
	}

	public String getThriftOrderPort() {
		return thriftOrderPort;
	}

	public void setThriftOrderPort(String thriftOrderPort) {
		this.thriftOrderPort = thriftOrderPort;
	}
    
	
    
}
