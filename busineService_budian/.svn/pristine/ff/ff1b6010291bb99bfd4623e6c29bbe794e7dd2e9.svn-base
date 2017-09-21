package com.xmniao.service.refund;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.common.DateUtil;
import com.xmniao.common.ResponseState;
import com.xmniao.dao.order.BillBargainDao;
import com.xmniao.dao.refund.RefundOrderServiceDao;
import com.xmniao.domain.order.BillBargain;
import com.xmniao.domain.order.OrdRecordBean;
import com.xmniao.service.order.ModifyJointProfitDayCensusImpl;
import com.xmniao.service.refund.census.MdyJointDayCensusImpl;
import com.xmniao.service.refund.census.MdySellerDayCensusImpl;
import com.xmniao.thrift.busine.common.FailureException;
import com.xmniao.thrift.busine.refund.LedgerSysOrderService;

/**
 * 
 * 提供给分账系统服务接口实现类
 * @author  LiBingBing
 * @version  [版本号, 2015年9月6日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("ledgerSysOrderServiceImpl")
public class LedgerSysOrderServiceImpl implements LedgerSysOrderService.Iface
{
    /**
     * 日志记录
     */
    private final Logger log = Logger.getLogger(RefundOrderServiceImpl.class);
    
    /**
     * 订单退款DAO层
     */
    @Autowired
    private RefundOrderServiceDao refundDao;
    
    @Autowired
    private BillBargainDao billBargainDao;
    
    /**
     * 注入更新已分账订单退款后的修改商户订单统计实现类
     */
    @Autowired
    private MdySellerDayCensusImpl sellerDayCensusImpl;
    
    /**
     * 注入更新已分账订单退款后的修改合作商订单统计实现类
     */
    @Autowired
    private MdyJointDayCensusImpl jointDayCensusImpl;
    
    /**
     * 注入更新已分账订单退款后的修改合作商日收益订单统计实现类
     */
    @Autowired
    private ModifyJointProfitDayCensusImpl jointProfitDayCensusImpl;
    
    /**
     * 已分账退款订单服务接口
     * @param paraMap [请求参数]
     * @throws FailureException
     * @throws TException
     */
    @Override
    @Transactional(readOnly=false,isolation=Isolation.DEFAULT,
    propagation=Propagation.REQUIRED,rollbackFor=FailureException.class)
    public Map<String, String> hasLedgerRefundOrder(Map<String, String> paraMap)
            throws FailureException, TException
    {
        log.info("hasLedgerRefundOrder start:"+paraMap);
        Map<String,String> resultMap = new HashMap<String,String>();
        if(paraMap.get("orderType")==null){
        	resultMap.put("bid", paraMap.get("bid")==null?"":paraMap.get("bid"));
        	resultMap.put("recode", "107");
        	resultMap.put("remark", "订单类型不能为空");
        }else if(paraMap.get("orderType").equals("1")){
        	resultMap = xmniaoOrderLedgerRefund(paraMap);
        }else if(paraMap.get("orderType").equals("2")){
        	resultMap = offlineOrderLedgerRefund(paraMap);
        }else if(paraMap.get("orderType").equals("3")){
        	resultMap.put("bid", paraMap.get("bid")==null?"":paraMap.get("bid"));
        	resultMap.put("recode", "107");
        	resultMap.put("remark", "暂不支付线上积分订单退款");
        }else{
        	resultMap.put("bid", paraMap.get("bid")==null?"":paraMap.get("bid"));
        	resultMap.put("recode", "107");
        	resultMap.put("remark", "未知订单类型");
        }
        return resultMap;
    }
    
    /**
     * 已分账线下积分订单退款
     * @Title: offlineOrderLedgerRefund 
     * @Description:
     */
    public Map<String, String> offlineOrderLedgerRefund(Map<String, String> paraMap)throws FailureException{

        log.info("offlineOrderLedgerRefund start:"+paraMap);
        Map<String, String> resMap=new HashMap<String,String>();
        
        try
        {
        	Long bid = Long.parseLong(paraMap.get("bid"));
            //获取订单信息
            BillBargain billBargain=billBargainDao.getByBid(bid);
            //校验订单是否为空
            if(null==billBargain)
            {
                resMap.put("bid", paraMap.get("bid"));
                resMap.put("recode", ResponseState.ORDERNULL+"");
                resMap.put("remark", "没有获取到订单号为:【"+paraMap.get("bid")+"】订单信息");
                return resMap;
            }
            int status = billBargain.getStatus();
            int hstatus = billBargain.getHstatus();
            //校验订单是否为已分账
            if(status!=1 && hstatus!=9) 
            {
                resMap.put("bid", paraMap.get("bid"));
                resMap.put("recode", ResponseState.ORDER_READY_LEDGER+"");
                resMap.put("remark", "该订单【"+paraMap.get("bid")+"】必须为已分账");
                return resMap;
            }
            
            //组装修改订单信息MAP
//            Map<String, String> mdyOrderMap=new HashMap<String,String>();
//            mdyOrderMap.put("bid", paraMap.get("bid"));
//            mdyOrderMap.put("status", "13");
//            mdyOrderMap.put("hstatus", "0");
            BillBargain mdyBillBargain = new BillBargain();
            mdyBillBargain.setHstatus(0);
            mdyBillBargain.setStatus(13);
            mdyBillBargain.setBid(bid);
            billBargainDao.modifyBillBargainStatus(mdyBillBargain);
            
            //插入订单处理记录
//            Map<String, String> recordMap = new HashMap<String, String>();
//            recordMap.put("bid", paraMap.get("bid"));
//            recordMap.put("status", "3");
//            recordMap.put("explains", "已分账订单退款");
//            recordMap.put("remarks", "已分账订单退款");
//            insertBillRecord(recordMap);
            
            //组装插入订单退款记录MAP
            Map<String, Object> orderRefundMap = new HashMap<String, Object>();
            orderRefundMap.put("bid",paraMap.get("bid"));
            orderRefundMap.put("sellerid", String.valueOf(billBargain.getSellerid()));
            orderRefundMap.put("sellername", String.valueOf(billBargain.getSellername()));
            orderRefundMap.put("apply", "已分账订单退款");
            orderRefundMap.put("status", "1");
            orderRefundMap.put("sdate", DateUtil.getCurrentTimeStr());
            orderRefundMap.put("remarks", "已分账订单退款");
            //插入订单退款记录
            billBargainDao.insertOrderRefundRecord(orderRefundMap);
            
            //获取更新订单后的订单信息
//            reOrderMap=refundDao.queryOrderInfo(paraMap.get("bid"));
//            String zdateStr=String.valueOf(reOrderMap.get("zdate"));
//            Date zdate=DateUtil.convertStringToDate("yyyy-MM-dd", zdateStr);
//            String tempdate=DateUtil.dateFormatY1(zdate);
//            if(DateUtil.isCurrentTime(tempdate)==false)
//            {
//                //调用修改商户订单统计接口
//                sellerDayCensusImpl.modifySellerDayCensus(reOrderMap);
//                
//                //调用修改合作商订单统计接口
//                jointDayCensusImpl.modifyJointDayCensus(reOrderMap);
//                
//                //调用修改合作商日收益订单统计接口
//                jointProfitDayCensusImpl.modifyJointProfitDayCensus(reOrderMap, reOrderMap);
//            }
            
            //操作成功
            resMap.put("recode", "100");
            resMap.put("bid", paraMap.get("bid"));
            resMap.put("remark", "订单【"+paraMap.get("bid")+"】已分账退款操作处理成功");
        }
        catch (Exception e)
        {
            log.error("已分账退款更新数据异常",e);
            throw new FailureException(ResponseState.ELSEEROR,"已分账退款更新数据异常");
        }
        log.info("offlineOrderLedgerRefund end");
        return resMap;
    	
    }
    
    /**
     * 已分账消费订单退款
     * @throws FailureException 
     * @Title: xmniaoOrderLedgerRefund 
     * @Description:
     */
    public Map<String, String> xmniaoOrderLedgerRefund(Map<String, String> paraMap) throws FailureException{

        log.info("xmniaoOrderLedgerRefund start:"+paraMap);
        Map<String, String> resMap=new HashMap<String,String>();
        
        try
        {
            //获取订单信息
            Map<String,Object> reOrderMap=refundDao.queryOrderInfo(paraMap.get("bid"));
            //校验订单是否为空
            if(null==reOrderMap)
            {
                resMap.put("bid", paraMap.get("bid"));
                resMap.put("recode", ResponseState.ORDERNULL+"");
                resMap.put("remark", "没有获取到订单号为:【"+paraMap.get("bid")+"】订单信息");
                return resMap;
            }
            int status = Integer.valueOf(reOrderMap.get("status").toString()).intValue();
            int hstatus = Integer.valueOf(reOrderMap.get("hstatus").toString()).intValue();
            //校验订单是否为已分账
            if(status!=2 && hstatus!=9) 
            {
                resMap.put("bid", paraMap.get("bid"));
                resMap.put("recode", ResponseState.ORDER_READY_LEDGER+"");
                resMap.put("remark", "该订单【"+paraMap.get("bid")+"】必须为已分账");
                return resMap;
            }
            
            //组装修改订单信息MAP
            Map<String, String> mdyOrderMap=new HashMap<String,String>();
            mdyOrderMap.put("bid", paraMap.get("bid"));
            mdyOrderMap.put("status", "13");
            mdyOrderMap.put("hstatus", "0");
            refundDao.modifyOrderInfo(mdyOrderMap);
            
            //插入订单处理记录
            Map<String, String> recordMap = new HashMap<String, String>();
            recordMap.put("bid", paraMap.get("bid"));
            recordMap.put("status", "3");
            recordMap.put("explains", "已分账订单退款");
            recordMap.put("remarks", "已分账订单退款");
            insertBillRecord(recordMap);
            
            //组装插入订单退款记录MAP
            Map<String, String> orderRefundMap = new HashMap<String, String>();
            orderRefundMap.put("bid",paraMap.get("bid"));
            orderRefundMap.put("sellerid", String.valueOf(reOrderMap.get("sellerid")));
            orderRefundMap.put("sellername", String.valueOf(reOrderMap.get("sellername")));
            orderRefundMap.put("apply", "已分账订单退款");
            orderRefundMap.put("status", "1");
            orderRefundMap.put("sdate", DateUtil.getCurrentTimeStr());
            orderRefundMap.put("remarks", "已分账订单退款");
            //插入订单退款记录
            refundDao.insertOrderRefundRecord(orderRefundMap);
            
            //获取更新订单后的订单信息
            reOrderMap=refundDao.queryOrderInfo(paraMap.get("bid"));
            
            
            String zdateStr=String.valueOf(reOrderMap.get("zdate"));
            Date zdate=DateUtil.convertStringToDate("yyyy-MM-dd", zdateStr);
            String tempdate=DateUtil.dateFormatY1(zdate);
            if(DateUtil.isCurrentTime(tempdate)==false)
            {
                //调用修改商户订单统计接口
                sellerDayCensusImpl.modifySellerDayCensus(reOrderMap);
                
                //调用修改合作商订单统计接口
                jointDayCensusImpl.modifyJointDayCensus(reOrderMap);
                
                //调用修改合作商日收益订单统计接口
                jointProfitDayCensusImpl.modifyJointProfitDayCensus(reOrderMap, reOrderMap);
            }
            
            //操作成功
            resMap.put("recode", "100");
            resMap.put("bid", paraMap.get("bid"));
            resMap.put("remark", "订单【"+paraMap.get("bid")+"】已分账退款操作处理成功");
        }
        catch (Exception e)
        {
            log.error("已分账退款更新数据异常",e);
            throw new FailureException(ResponseState.ELSEEROR,"已分账退款更新数据异常");
        }
        log.info("xmniaoOrderLedgerRefund end");
        return resMap;
    	
    }
    
    /**
     * 订单处理记录方法
     * @param reqMap [请求参数MAP]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public void insertBillRecord(Map<String,String> reqMap)
    {
        log.info("inserBillRecord param start:"+reqMap);
        try
        {
            OrdRecordBean reqBean = new OrdRecordBean();
            //订单号
            reqBean.setBid(reqMap.get("bid"));
            //订单处理状态
            reqBean.setStatus(Integer.valueOf(reqMap.get("status")));
            //订单处理说明
            reqBean.setExplains(reqMap.get("explains"));
            reqBean.setRemarks(reqMap.get("remarks"));
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            reqBean.setCdate(dateFormat.format(new Date()));
            //开始插入订单处理记录表
            refundDao.insertBillRecord(reqBean);
        }
        catch (Exception e)
        {
            log.error("订单处理记录异常",e);
        }
        log.info("inserBillRecord end");
    }
}
