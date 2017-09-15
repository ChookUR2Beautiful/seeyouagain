package com.xmniao.xmn.core.xmer.service;

import com.xmniao.xmn.core.thrift.ResponsePageList;
import com.xmniao.xmn.core.thrift.WalletExpansionService;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/11.
 */
@Service
public class ThriftWalletExpansionService {

    private final Logger log = Logger.getLogger(ThriftWalletExpansionService.class);
    /**
     * 支付接口地址
     */
    @Autowired
    private String ip_number_pay;

    /**
     * 支付接口端口号
     */
    @Autowired
    private String port_pay;

    /**
     * 获取寻觅客流水失败
     * @param uid
     * @param pageNo
     * @param pageSize
     * @param expansionType
     * @param incomeType
     * @param sdate
     * @param edate
     * @param orders
     * @param childrenIds
     * @return
     */
    public ResponsePageList getXmrIncomeList(Integer uid, Integer pageNo, Integer pageSize,
                                                      Integer expansionType, Integer incomeType, String sdate, String edate, String orders,
                                             String childrenIds) {
        TTransport transport = null;
        try {
            // 设置调用的服务地址为本地，端口为 7911
            transport = new TSocket(ip_number_pay,Integer.valueOf(port_pay));
            TFramedTransport frame = new TFramedTransport(transport);
            // 设置传输协议为 TBinaryProtocol
            TProtocol protocol = new TBinaryProtocol(frame);

            //商家服务模块
            TMultiplexedProtocol walletExpansionProtocol = new TMultiplexedProtocol(
                    protocol, "WalletExpansionService");
            WalletExpansionService.Client client = new WalletExpansionService.Client(walletExpansionProtocol);
            transport.open();
            Map<String, String> params = new HashMap<>();
            // 必填
            params.put("uid", String.valueOf(uid));
            params.put("expansionType", String.valueOf(expansionType));  //钱包类型 1:寻蜜客  2:中脉  3:V客
            params.put("incomeType", String.valueOf(incomeType));  //收益类型 1:saas收益    2:店铺流水
            // 非必填
            if (pageNo != null) {
                params.put("pageNo", String.valueOf(pageNo));
            }
            if (pageSize != null) {
                params.put("pageSize", String.valueOf(pageSize));
            }
            if (sdate != null) {
                params.put("sdate", sdate);
            }
            if (edate != null) {
                params.put("edate", edate);
            }
            if (orders != null) {
                params.put("orders", orders);
            }
            if (childrenIds != null) {
                params.put("childrenIds", childrenIds);
            }
            ResponsePageList responsePageList = client.getXmrIncomeList(params);
            return responsePageList;
        } catch (Exception e) {
            log.warn("获取寻觅客流水失败");
            e.printStackTrace();
        }finally{
            if(transport!=null){
                transport.close();
            }
        }
        return null;
    }

}
