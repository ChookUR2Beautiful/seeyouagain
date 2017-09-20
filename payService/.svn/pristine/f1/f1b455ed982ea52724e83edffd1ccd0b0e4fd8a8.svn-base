package com.xmniao.util;

import com.xmniao.common.MD5;
import com.xmniao.entity.LiveWallet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yang.qiang on 2017/5/4.
 */
public class LiveWalletUtil {
    private final static Logger logger = LoggerFactory.getLogger(LiveWallet.class);


    /**
     * 验证直播钱包签名是否正确
     * @param liveWallet
     * @return
     */
    public static boolean verifySign(LiveWallet liveWallet){
        boolean result = false;
        try {
            String sign = generateLiveWalletSign(liveWallet);
            result = sign.equals(liveWallet.getSign());
        } catch (Exception e) {
            logger.error("校验直播钱包签名出现异常,liveWallet=" + liveWallet,e);
        }
        return result;
    }

    /**
     * 校验直播钱包状态是否正常
     * @param liveWallet
     * @return
     */
    public static boolean verifyStatus(LiveWallet liveWallet){
        if(liveWallet == null) return false;
        if(liveWallet.getStatus() == null) return false;
        return liveWallet.getStatus() == 1;
    }

    /**
     * 直播钱包签名
     */
    public  static String generateLiveWalletSign(Map<String,String> signMap){
        logger.info("要签名的信息：" + signMap);
        StringBuffer sb = new StringBuffer();
        sb.append(String.valueOf(signMap.get("uid")));
        sb.append(String.format("%.2f", Double.valueOf(String.valueOf(signMap.get("balance")))));
        sb.append(String.format("%.2f", Double.valueOf(String.valueOf(signMap.get("commision")))));
        sb.append(String.format("%.2f", Double.valueOf(String.valueOf(signMap.get("zbalance")))));
        sb.append(String.format("%.2f", Double.valueOf(String.valueOf(signMap.get("sellerCoin")))));
        logger.info("钱包加密：" + sb.toString());
        return MD5.Encode(sb.toString());
    }

    /**
     * 直播钱包签名
     * @param liveWallet
     * @return
     */
    public static String generateLiveWalletSign(LiveWallet liveWallet){
        Map<String, String> signMap = new HashMap<>();
        signMap.put("uid", liveWallet.getUid().toString());
        signMap.put("balance", liveWallet.getBalance().toString());
        signMap.put("zbalance", liveWallet.getZbalance().toString());
        signMap.put("commision", liveWallet.getCommision().toString());
        signMap.put("sellerCoin", liveWallet.getSellerCoin().toString());
        return generateLiveWalletSign(signMap);
    }

}
