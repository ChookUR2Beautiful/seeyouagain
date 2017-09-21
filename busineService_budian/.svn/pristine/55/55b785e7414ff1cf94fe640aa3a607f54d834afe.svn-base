package com.xmniao.service.maibao.impl;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.common.DateUtil;
import com.xmniao.common.HttpUtil;
import com.xmniao.common.MD5Util;
import com.xmniao.common.PreciseComputeUtil;
import com.xmniao.common.PropertiesUtil;
import com.xmniao.dao.maibao.MaibaoLedgerMapper;
import com.xmniao.domain.maibao.MaibaoLedger;
import com.xmniao.domain.maibao.MaibaoLedgerNotifyBean;
import com.xmniao.service.maibao.MaibaoService;

import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;

/**
 * Created by yang.qiang on 2017/5/15.
 */
@Service
public class MaibaoServiceImpl implements MaibaoService {
    private final Logger logger = LoggerFactory.getLogger(MaibaoServiceImpl.class);
    @Autowired
    private MaibaoLedgerMapper maibaoLedgerMapper;
        
    /**
     * 根据分账信息id 通知脉宝公司分账信息
     * @param ledgerId   分账信息id
     */
    @Override
    public void advenceLedgerMessage(Integer ledgerId) {
        try {
            // 查询出脉宝分账记录
            MaibaoLedger maiBaoLedger = getMaiBaoLedger(ledgerId);
            // 发送通知
            if (maiBaoLedger != null) sendLedger(maiBaoLedger);
            else logger.info("未查询到id为["+ledgerId+"]的脉宝分账信息!");
        } catch (Exception e) {
            logger.error("通知脉宝分账出现异常! 脉宝分账信息:"+ledgerId,e);
            e.printStackTrace();
        }
    }

    /**
     * 根据id 查询脉宝分账信息
     * @param ledgerId
     * @return
     */
    private MaibaoLedger getMaiBaoLedger(Integer ledgerId){
        return maibaoLedgerMapper.selectByPrimaryKey(ledgerId);
    }

    /**
     * 向脉宝发送请求
     * @param maibaoLedger
     * @throws IOException
     */
    @Override
    public void sendLedger(MaibaoLedger maibaoLedger) throws IOException {
        logger.info("向脉宝发送分账信息...  maibaoLedger="+maibaoLedger.getTransNo());

        if (maibaoLedger.getNotifyState() == 1) {
            logger.info("通知状态为:"+maibaoLedger.getNotifyState()+", 分账信息已通知过!");
            return;
        }
        MaibaoLedgerNotifyBean notifyBean = null;
		try {
			notifyBean = this.getNoticeBean(maibaoLedger);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			logger.info("数据异常，处理失败",e);
			e.printStackTrace();
			return ;
		}
		String maibaoUrl = PropertiesUtil.readValue("maibao.http.url", "busine_sundry.properties");
		int notifyState = 0;
		String notifyResult = null;
        // 向脉宝发送HTTP请求
        HttpUtil httpUtil = HttpUtil.getInstance();
        try{
	        HttpResponse httpResponse  = httpUtil.postJsonData(maibaoUrl, JSONObject.toJSONString(notifyBean));
	        BufferedReader in = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
	        StringBuffer sb = new StringBuffer("");  
	        String line = "";  
	        String NL = System.getProperty("line.separator");  
	        while ((line = in.readLine()) != null) {  
	            sb.append(line + NL);  
	        }  
	        in.close();  
	        logger.info("【"+maibaoLedger.getTransNo()+"】请求收到返回消息:"+sb.toString());
	        notifyResult = sb.toString();
	        JSONObject json = JSONObject.parseObject(sb.toString());
	        String code=json.getString("code");
	        if(code!=null && code.equals("1001")){
	        	notifyState=1;
	        }else{
	        	notifyState=2;
	        }
        }catch(Exception e){
        	logger.error("请求异常:",e);
        	notifyState=3;
        }

        // 通知成功后更新分账通知记录
        MaibaoLedger record = new MaibaoLedger();
        record.setId(maibaoLedger.getId());
        record.setNotifyState(notifyState);
        record.setNotifyResult(notifyResult);
        record.setUpdateTime(new Date());
        maibaoLedgerMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 获取没有通知的分账信息
     * 获取两个小时之前插入的记录
     * @return
     */
    public List<MaibaoLedger> getNoAdvenceLedger(){
        Date queryDate = new Date(System.currentTimeMillis() - 7200000);
        return maibaoLedgerMapper.getNoAdvenceLedger(queryDate);
    }

    private MaibaoLedgerNotifyBean getNoticeBean(MaibaoLedger maibaoLedger) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException{
    
    	MaibaoLedgerNotifyBean notifyBean = new MaibaoLedgerNotifyBean();
    	notifyBean.setEcno(maibaoLedger.getEcno());
    	notifyBean.setMobile(maibaoLedger.getMobile());
    	notifyBean.setTransNo(maibaoLedger.getTransNo());
    	notifyBean.setAmount(PreciseComputeUtil.keepTwoPointStr(maibaoLedger.getAmount(),2));
    	notifyBean.setDiscount(PreciseComputeUtil.keepTwoPointStr(maibaoLedger.getDiscount(),2));
    	notifyBean.setMerchantType(maibaoLedger.getMerchantType()) ;
    	notifyBean.setMerchantName(maibaoLedger.getMerchantName());
    	notifyBean.setSignedEcno(maibaoLedger.getSignedEcno());
    	notifyBean.setLedgerAmount(PreciseComputeUtil.keepTwoPointStr(maibaoLedger.getLedgerAmount(),2));
    	notifyBean.setLedgerTime(DateUtil.format(maibaoLedger.getLedgerTime(),"yyyy-MM-dd HH:mm:ss")) ;
    	String signSource = notifyBean.getSignSource(PropertiesUtil.readValue("maibao.http.key", "busine_sundry.properties"));
    	logger.info("待签名字符串:"+signSource);		
    	notifyBean.setSign(MD5Util.md5(signSource));
    	return notifyBean; 
    }
}
