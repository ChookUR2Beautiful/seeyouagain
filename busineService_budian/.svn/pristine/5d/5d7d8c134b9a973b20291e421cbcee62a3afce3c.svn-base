package com.xmniao.main;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.xmniao.thrift.busine.activity.ActivityService;
import com.xmniao.thrift.busine.common.ResponseData;
import com.xmniao.thrift.busine.common.UpdateOrderRequest;
import com.xmniao.thrift.busine.common.XmnOrderParam;
import com.xmniao.thrift.busine.common.XmnOrderParamV2;
import com.xmniao.thrift.busine.discount.DiscountService;
import com.xmniao.thrift.busine.mike.MikeService;
import com.xmniao.thrift.busine.order.MaterialOrderService;
import com.xmniao.thrift.busine.order.OrderService;
import com.xmniao.thrift.busine.refund.BusiSysOrderService;
import com.xmniao.thrift.busine.refund.LedgerSysOrderService;
import com.xmniao.thrift.busine.refund.RefundOrderService;
import com.xmniao.thrift.busine.store.StoreService;
import com.xmniao.thrift.busine.xmer.SaasOrderService;

/**
 * 客户端测试类
 * @author  LiBingBing
 * @version  [版本号, 2014-11-7]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ThriftClient
{
    //服务端的IP地址
    private static final String IP_NUMBER = "localhost";
//	private static final String IP_NUMBER = "192.168.50.109";
//	private static final String IP_NUMBER = "192.168.1.11";
    //服务端的端口号
//    private static final int PORT = 7911;
    private static final int PORT = 7911;
    
    private static TTransport transport = null;
    
    public static void main(String[] args) throws InterruptedException
    {
        try
        {
            // 设置调用的服务地址为本地，端口为 7911
            transport = new TSocket(IP_NUMBER, PORT);
            TFramedTransport frame = new TFramedTransport(transport);
            // 设置传输协议为 TBinaryProtocol
            TProtocol protocol = new TBinaryProtocol(frame);
            
            //订单服务模块
            TMultiplexedProtocol orderProtocol = new TMultiplexedProtocol(
                    protocol, "OrderService");
            OrderService.Client client = new OrderService.Client(orderProtocol);
            
            //折扣服务模块
            TMultiplexedProtocol discountProtocol = new TMultiplexedProtocol(
                    protocol, "DiscountService");
            DiscountService.Client discountClient = new DiscountService.Client(
                    discountProtocol);
            
            //向蜜客服务模块
            TMultiplexedProtocol mikeProtocol = new TMultiplexedProtocol(
                    protocol, "MikeService");
            MikeService.Client mikeClient = new MikeService.Client(mikeProtocol);
            
            //活动服务模块
            TMultiplexedProtocol phoneActivityProtocol = new TMultiplexedProtocol(
                    protocol, "ActivityService");
            ActivityService.Client phoneActivityClient = new ActivityService.Client(phoneActivityProtocol);
            
            //连锁店服务模块
            TMultiplexedProtocol storeServiceProtocol = new TMultiplexedProtocol(
                    protocol, "StoreService");
            StoreService.Client storeClient = new StoreService.Client(storeServiceProtocol);
            
            //Saas订单模块
            TMultiplexedProtocol saasOrderService = new TMultiplexedProtocol(
                    protocol, "SaasOrderService");
            SaasOrderService.Client saasOrderClient = new SaasOrderService.Client(saasOrderService);
            
            //退款服务模块
            TMultiplexedProtocol refundOrderService = new TMultiplexedProtocol(
                    protocol, "RefundOrderService");
            RefundOrderService.Client refundOrderClient = new RefundOrderService.Client(refundOrderService);
            
            //退款服务模块
            TMultiplexedProtocol busiSysOrderService = new TMultiplexedProtocol(
                    protocol, "BusiSysOrderService");
            BusiSysOrderService.Client busiSysOrderClient = new BusiSysOrderService.Client(busiSysOrderService);
            
            //物料服务模块
            TMultiplexedProtocol materialOrderService = new TMultiplexedProtocol(
                    protocol, "MaterialOrderService");
            MaterialOrderService.Client materialOrderClient = new MaterialOrderService.Client(materialOrderService);  
            
            //分账服务模块
            TMultiplexedProtocol ledgerSysOrderService = new TMultiplexedProtocol(
                    protocol, "LedgerSysOrderService");
            LedgerSysOrderService.Client ledgerSysOrderClient = new LedgerSysOrderService.Client(ledgerSysOrderService);  
            
            //直播服务模块
            TMultiplexedProtocol LiveOrderService = new TMultiplexedProtocol(
            		protocol, "LiveOrderService");
            com.xmniao.thrift.busine.live.LiveOrderService.Client  liveClient= new com.xmniao.thrift.busine.live.LiveOrderService.Client(LiveOrderService); 
            //用户服务模块
            TMultiplexedProtocol userService = new TMultiplexedProtocol(
            		protocol, "UserService");
            com.xmniao.thrift.busine.user.UserService.Client  userServiceClient= new com.xmniao.thrift.busine.user.UserService.Client(userService); 
            //商户订单服务模块
            TMultiplexedProtocol sellerOrderService = new TMultiplexedProtocol(
            		protocol, "SellerOrderService");
            com.xmniao.thrift.busine.sellerOrder.SellerOrderService.Client  sellerOrderServiceClient= new com.xmniao.thrift.busine.sellerOrder.SellerOrderService.Client(sellerOrderService); 
            //商户服务模块
            TMultiplexedProtocol sellerService = new TMultiplexedProtocol(
            		protocol, "SellerService");
            com.xmniao.thrift.busine.seller.SellerService.Client  sellerServiceClient= new com.xmniao.thrift.busine.seller.SellerService.Client(sellerService); 

            //用户服务模块
            TMultiplexedProtocol userActionService = new TMultiplexedProtocol(
            		protocol, "UserActionService");
            com.xmniao.thrift.busine.userAction.UserActionService.Client  userActionServiceClient= new com.xmniao.thrift.busine.userAction.UserActionService.Client(userActionService); 
            
            //公共服务
            TMultiplexedProtocol xmnCommonService = new TMultiplexedProtocol(
            		protocol, "XmnCommonService");
            com.xmniao.thrift.common.XmnCommonService.Client  xmnCommonServiceClient= new com.xmniao.thrift.common.XmnCommonService.Client(xmnCommonService); 
            
            //套餐订单
            TMultiplexedProtocol packageOrderService = new TMultiplexedProtocol(
            		protocol, "SellerPackageService");
            com.xmniao.thrift.busine.sellerPackage.SellerPackageService.Client  packageOrderServiceClient= new com.xmniao.thrift.busine.sellerPackage.SellerPackageService.Client(packageOrderService); 
            
            
            //套餐订单
            TMultiplexedProtocol deliverAuctionService = new TMultiplexedProtocol(
            		protocol, "DeliverActivityAuctionService");
            com.xmniao.thrift.busine.fresh.DeliverActivityAuctionService.Client  deliverAuctionServiceClient= new com.xmniao.thrift.busine.fresh.DeliverActivityAuctionService.Client(deliverAuctionService); 

            TMultiplexedProtocol debitcardService = new TMultiplexedProtocol(
            		protocol, "DebitcardService");
            com.xmniao.thrift.busine.live.DebitcardService.Client  debitcardServiceClient= new com.xmniao.thrift.busine.live.DebitcardService.Client(debitcardService); 
            
            
            //积分商城订单支付模块
            TMultiplexedProtocol mallOrderService = new TMultiplexedProtocol(
                    protocol, "MallOrderService");
            com.xmniao.thrift.busine.order.MallOrderService.Client mallOrderServiceClient = new com.xmniao.thrift.busine.order.MallOrderService.Client(mallOrderService);

            //V客团队模块
            TMultiplexedProtocol verTeamService = new TMultiplexedProtocol(
                    protocol, "VerTeamService");
            com.xmniao.thrift.busine.live.VerTeamService.Client verTeamServiceClient = new com.xmniao.thrift.busine.live.VerTeamService.Client(verTeamService);
            
            //星食尚活动大赛模块
            TMultiplexedProtocol fashionService = new TMultiplexedProtocol(
                    protocol, "FashionService");
            com.xmniao.thrift.busine.vstar.FashionService.Client fashionServiceClient = new com.xmniao.thrift.busine.vstar.FashionService.Client(fashionService);

            //星食尚活动大赛模块
            TMultiplexedProtocol manorService = new TMultiplexedProtocol(
                    protocol, "ManorService");
            com.xmniao.thrift.busine.manor.ManorService.Client manorServiceClient = new com.xmniao.thrift.busine.manor.ManorService.Client(manorService);
            
            //打开端口,开始调用
            transport.open();
            
//            Map<String,String> map = new HashMap<>();
//            map.put("orderNo","0111702241511528618");
////            map.put("payid","");
//            map.put("uid","339586");
//            map.put("status","1");
//            map.put("payType","10000");
//            map.put("totalAmount","200.00");
//            map.put("cash","0.00");
//            map.put("balance","0.00");
//            map.put("commision","0.00");
//            map.put("zbalance","0.00");
//            map.put("beans","108.00");
//            
//            ResponseData res = deliverAuctionServiceClient.updateBillActivityAuctionStatus(map);
//			if (res.state != 0)
//				System.out.println("执行结果信息" + res.getMsg());
            /*Map<String,String> map = new HashMap<>();
            map.put("orderNo","0111705251631551802");
            map.put("payid","");
            map.put("uid","604902");
            map.put("status","1");
            map.put("payType","1000011");
            map.put("totalAmount","160.00");
            map.put("cash","0.00");
            map.put("balance","0.00");
            map.put("commision","0.00");
            map.put("zbalance","0.00");
            map.put("beans","0.00");
            map.put("base","1");
            map.put("sellerCoin","0.00");
            map.put("deduction","1");
            com.xmniao.thrift.busine.common.ResponseData res = packageOrderServiceClient.updateSellerPackageOrder(map);
*/            
//            Map<String,String> map = new HashMap<>();
//            map.put("phone","15362652040,13723728427");
//            map.put("smsContent", "恭喜发财");
//            com.xmniao.thrift.common.ResponseData sendXmnSms = xmnCommonServiceClient.sendXmnSms(map);
//            System.out.println(sendXmnSms.getState());
            
//            Map<String,String> paraMap = new HashMap<String,String>();
//            paraMap.put("bid", "161118000074");
//            Map<String,String> resultMap =  client.handleLedger(paraMap);
//            System.out.println("手动分账处理结果："+resultMap);
            
            //{payId=091607161533061695, 
            // payCode=4006872001201607169103226553, 
            //		status=1, payType=1000013, 
            //		zdate=2016-07-16 15:36:40, bid=021607161532289251}

//            Map<String, String> request=new HashMap<String,String>();
//            request.put("bid", "011705181753461274");
//            request.put("status", "1");
//            request.put("payType", "1000001");
//            request.put("payId", "091607161533061695");
//            request.put("payCode", "40069");
//            request.put("giveNumber", "6");
//            request.put("zdate",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//            request.put("rtype", "1");
//            try {
////            	ResponseData result = liveClient.updateLiveOrder(request);
////            	System.out.println(result.getState());
////            	System.out.println(result.getMsg());
//				saasOrderClient.modifyXmerOrderInfo(request);
////				saasOrderClient.modifySellerOrderInfo(request);
////				System.out.println("修改成功！");
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
            
                /*UpdateOrderRequest request = new UpdateOrderRequest();
                request.setBid("161209000008");
                request.setStatus(1);
                request.setNumber("1609150950277495");
                request.setPaytype("1000003");
                request.setOrdertype(1);
                request.setPayid(1509150950277498l);
                request.setPhoneid("13723728427");
                request.setLedgertype(1);
                
                BigDecimal samount = new BigDecimal(100.99);
                BigDecimal commision = new BigDecimal(0.00);
                BigDecimal profit = new BigDecimal(0.00);
                BigDecimal giveMoney = new BigDecimal(0.00);
                
                request.setSamount(String.valueOf(samount.doubleValue()));
                request.setCommision(commision.toString());
                request.setProfit(profit.toString());
                request.setGiveMoney(giveMoney.toString());
                //request.setThirdUid(null);
                
                System.out.println(request);
              //更新订单信息
                String resBid = client.updateOrderInfo(request);
                System.out.println("======response bid::" + resBid);*/
            
            
            //获取合作商业务员的分账比例
//            double res = discountClient.inquirySplitAccount(10001);
//            System.out.println("======response percentage::" + res);
//            
//            //获取合作商业务员的总分账金额和提成折扣
//            Map<String, String> resMap = discountClient.inquiryStaffDiscount(1);
//            System.out.println("==response amount::" + resMap.get("amount")
//                    + "==response baseagio::" + resMap.get("baseagio"));
//            
            //更新订单流程接口
            //Map<String, String> paraMap = new HashMap<String, String>();
            //paraMap.put("150427017050", "9");
//            //paraMap.put("150212000058", "9");
//            //paraMap.put("150210001389", "9");
//           
            //Map<String, String> resCode = client.modifyOrderProcess(paraMap);
            //System.out.println("==response resCode::" + resCode);
//            
//            //向蜜客缴费订单更新接口
//            Map<String, String> mikeMap = new HashMap<String, String>();
//            mikeMap.put("id", "141111111123");
//            mikeMap.put("pid", "10001");
//            mikeMap.put("paytype", "1000001");
//            mikeMap.put("status", "3");
//            mikeMap.put("number", "225226554");
//            mikeMap.put("remarks", "支付10001缴费成功");
//            mikeMap.put("userid", "2");
//            String id = mikeClient.modifyMikeInviteInfo(mikeMap);
//            System.out.println("======inviteId::" + id);
            
            //订单退款
//            Map<String, String> orderMap = new HashMap<String, String>();
//            orderMap.put("bid", "150813000149");
//            orderMap.put("refundStatus", "9");
//            //orderMap.put("remarks", "order error");
//            Map<String, String> redOrderMap = client.refundOrderInfo(orderMap);
//            System.out.println("======refund OrderId::" + redOrderMap.get("bid"));
            
            //活动服务模块
//            Map<String,String> parasMap = new HashMap<String,String>();
//            parasMap.put("bid","1502059752");
//            parasMap.put("activityType","1");
//            parasMap.put("payid","112112113");
//            parasMap.put("number", "20130114");
//            Map<String,String> resMap=phoneActivityClient.mdyMobileActivitiesInfos(parasMap);
//            System.out.println("phoneActivity resMap::" + resMap);
            
            
            //验证订单
            //boolean resFlag = client.valideBill(123434544432l);
            //System.out.println(resFlag);
               
//             String sellerId="4634";
//             double resWaterTotal = client.querySellerWaterTotal(sellerId);
//             System.out.println(resWaterTotal);
               
//               List<Map<String,String>> resList = new ArrayList<Map<String,String>>();
//               Map<String,String> paraMap = new HashMap<String,String>();
//               paraMap.put("flowid", "80001735");
//               paraMap.put("flowstatus", "3");
//               resList.add(paraMap);
//               Map<String,String> resMap=storeClient.modifyStoreWithdrawals(resList);
//               System.out.println("连锁店回调更新返回结果："+resMap);
            
               //获取调单之后的订单信息接口
//            Map<String,String> paraMap = new HashMap<String,String>();
//            paraMap.put("orderId", "150718029272");
//            paraMap.put("sellerId", "26015");
            //paraMap.put("sellerName", "苹果四号");
//            Map<String,String> resMap=client.queryAdjustOrderInfo(paraMap);
//            System.out.println("queryAdjustOrderInfo resMap::"+resMap);
               
             //获取调单之前的订单信息接口
//             Map<String,String> paraMap = new HashMap<String,String>();
//             paraMap.put("bid", "150718029315");
//             paraMap.put("sellerId", "18138");
//             Map<String,String> resMap=client.queryAdjBeforeOrderInfo(paraMap);
//             System.out.println("queryAdjBeforeOrderInfo resMap::"+resMap);
                
                //修改调单后的订单信息
//                Map<String,String> paraMap = new HashMap<String,String>();
//                paraMap.put("orderId", "150718029272");
//                paraMap.put("sellerId", "23480");
//                paraMap.put("bpartnerId", "10109");
//                paraMap.put("bpartnerName", "消费补贴测试");
//                paraMap.put("sellerName", "苹果四号");
//                paraMap.put("genussellerid", "23480");
//                paraMap.put("genusname", "苹果四号");
//                paraMap.put("cpartnerId", "10109");
//                paraMap.put("cpartnerName", "消费补贴测试");
//                paraMap.put("sellerAreaId", "5010");
//                paraMap.put("code", "1");
//                Map<String,String> resMap=client.modifyAdjustOrderInfo(paraMap);
//                System.out.println("modifyAdjustOrderInfo resMap::"+resMap);
              
            //  Map<String, String> resMap=client.getOrderInfo(150930000002l);
            //  Map<String, String> resMap=client.getOrderInfo(151001000017l);
            //  System.out.println("getOrderInfo resMap:"+resMap);
                
            //报障订单进入退款流程
//            Map<String,String> paramMap = new HashMap<String,String>();
//            paramMap.put("bid", "160725000015");
//            paramMap.put("bidtype", "14");
//            paramMap.put("number", "1201478521458745");
//            paramMap.put("paytype", "1000001");
//            paramMap.put("ordertype", "1");
//            paramMap.put("payid", "147854214587452");
//            paramMap.put("samount", "60.00");
//            paramMap.put("commision", "0.00");
//            paramMap.put("profit", "0.00");
//            paramMap.put("giveMoney", "33.00");
//            paramMap.put("thirdUid", "14785412");
//            Map<String,String> resultMap = refundOrderClient.payFailRefundOrder(paramMap);
//            System.out.println("报障订单进入退款流程:"+resultMap);
            
            //报障订单恢复为正常订单
//            Map<String,String> paraMap = new HashMap<String,String>();
//            paraMap.put("bid","160725000015");
//            Map<String,String> resultMap = busiSysOrderClient.payFailRecoverOrder(paraMap);
//            System.out.println("报障订单恢复为正常订单："+resultMap);
            
//            Map<String,String> reqMap = new HashMap<String,String>();
//            reqMap.put("orderSn", "041607141840468136");
//            materialOrderClient.notifyForPayComplete(reqMap);
       /*     Map<String,String> paraMap = new HashMap<String,String>();
            paraMap.put("bid", "160801000004");
            paraMap.put("orderType", "2");
            Map<String,String> resultMap = ledgerSysOrderClient.hasLedgerRefundOrder(paraMap);
            System.out.println("调用结果："+resultMap);*/
//            HashMap<String, String> hashMap = new HashMap<>();
//            saasOrderClient.modifyXmerOrderInfo(hashMap);
          /*  hashMap.put("uid","586430");
            com.xmniao.thrift.user.ResponseData userMsg = userServiceClient.getUserMsg(hashMap);
            System.out.println(userMsg);*/
//            List<Integer> parList = new ArrayList<>();
//            parList.add(225054);
//            List<Integer> list= userServiceClient.getAnchorId(parList);
//            System.out.println(list);
//           Map<String,String> req = new HashMap<String,String>();
//           req.put("order_no", "061609141419216100");
//           req.put("sellerid", "118");
//           req.put("status", "2");
//           req.put("pay_type", "1000014");
//           req.put("pay_id", "111");
//           req.put("pay_code", "111111111");
//           req.put("total_amount", "100.00");
//           req.put("balance", "0.00");
//           req.put("commision", "0.00");
//           req.put("zbalance", "0.00");
//           req.put("integral", "0.00");
//           req.put("profit", "0.00");
//          Map<String,String> resultMap = sellerOrderServiceClient.modifyRedPacketOrder(req);
//           System.out.println("支付红包金额订单结果："+resultMap);
            
         /*  Map<String,String> req2 = new HashMap<String,String>();
           req2.put("id", "2764");
           req2.put("status", "1");
           Map<String,String> resultMap2 = sellerOrderServiceClient.modifyRedPacketRecord(req2);
           System.out.println("领取红包到账结果:"+resultMap2);*/
            
            //秒杀
            /*Map<String,String> req2 = new HashMap<String,String>();
            req2.put("uid", "605859");
            req2.put("amount", "50.00");
            req2.put("number", "16120218055453555");
            req2.put("status", "1");
            Map<String, String> updateKillOrder = sellerOrderServiceClient.updateKillOrder(req2);
            System.out.println(updateKillOrder);*/
            
            //更新粉丝券订单
//            Map<String,String> req = new HashMap<String,String>();
//            req.put("orderNo", "051611051745012442");
////            req.put("status", "1");
//            req.put("uid", "604931");
//            req.put("status", "1");
//            req.put("payType", "1000000");
//            req.put("payid", "1111111");
//            req.put("thirdUid", "111111111");
//            req.put("thirdSerial", "111111111");
//            req.put("totalAmount", "100.00");
//            req.put("cash", "0.00");
//            req.put("balance", "0.00");
//            req.put("commision", "100.00");
//            req.put("zbalance", "0.00");
//            req.put("integral", "0.00");
//            req.put("beans", "0.00");
//            Map<String, String> result = liveClient.updateCouponOrder(req);
//            System.out.println(result);
            
//            SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            List<Map<String,String>> list = new ArrayList<Map<String,String>>();
//            Map<String,String> map = new HashMap<String,String>();
//            map.put("uid", "388107");
//            map.put("xmntype", "1");
//            map.put("sellerid", "29903");
//            map.put("operate", "1");
//            map.put("actiontype", "1");
//            map.put("lastTime", sdf.format(new Date()));
//            Map<String,String> map2 = new HashMap<String,String>();
//            map2.put("uid", "222");
//            map2.put("sellerid", "33585");
//            map2.put("operate", "1");
//            map2.put("actiontype", "1");
//            map2.put("lastTime", sdf.format(new Date()));
//            Map<String,String> map3 = new HashMap<String,String>();
//            map3.put("uid", "222");
//            map3.put("sellerid", "33585");
//            map3.put("operate", "2");
//            map3.put("actiontype", "1");
//            map3.put("lastTime", sdf.format(new Date()));
//            list.add(map);
//            list.add(map2);
//            list.add(map3);
//            list.add(map2);
//            userActionServiceClient.userActionService(list);
            
//            long s=System.currentTimeMillis();
//            List<Map<String,String>> list= new ArrayList<Map<String,String>>();
//            Map<String,String> a = new HashMap<String,String>();
//            a.put("bid", "161202000109");
//            a.put("btype", "1");
//            Map<String,String> b = new HashMap<String,String>();
//            b.put("bid", "16120217063817303");
//            b.put("btype", "2");
//            Map<String,String> c = new HashMap<String,String>();
//            c.put("bid", "20161206202244313029");
//            c.put("btype", "3");
//            Map<String,String> d = new HashMap<String,String>();
//            d.put("bid", "46363344");
//            d.put("btype", "");
//            list.add(c);
//            list.add(a);list.add(b);
//            list.add(c);list.add(d);
//            list.add(a);list.add(b);
//            list.add(c);list.add(d);
//            list.add(a);list.add(b);
//            list.add(c);list.add(d);
//            list.add(a);list.add(b);
//            list.add(c);list.add(d);
//            list.add(a);list.add(b);
//            list.add(c);list.add(d);
//            Map<String,ResponseData> data= client.getOrderLedgerInfoList(list);
//            System.out.println((System.currentTimeMillis()-s)+"ms,"+data);
        
//            Map<String,String> c = new HashMap<String,String>();
//            c.put("bid", "170308000109");
//            c.put("btype", "1");
//            ResponseData data= client.getOrderLedgerInfo(c);
//            System.out.println(data);
            
//            Map<String,String> selMap = new HashMap<String,String>();
//            selMap.put("sellerid","31884");
//            selMap.put("type","");
//            com.xmniao.thrift.busine.common.ResponseData responseData =  liveClient.getSellerLiveCountInfo(selMap);
//            System.out.println("返回结果："+responseData);
            
//            Map<String,String> paramMap = new HashMap<String,String>();
//            paramMap.put("sellerid", "33585");
//            paramMap.put("type", "1");
//            ResponseData data = sellerServiceClient.getSellerAnalysisInfo(paramMap);
//            System.out.println(data);
            
//          Map<String,String> paramMap = new HashMap<String,String>();
//          paramMap.put("sellerid", "32196");
//          paramMap.put("operate", "1");
//          ResponseData data = sellerServiceClient.dealSellerAnalysisInfo(paramMap);
//          System.out.println(data);
            
//            Map<String,String> selMap = new HashMap<String,String>();
//            selMap.put("actiontype", "0");
//            selMap.put("operate", "0");
//            Map<String,String> resultMap = userActionServiceClient.initActionService(selMap);
//            System.out.println(resultMap);
            
            /*Map<String,String> reqMap = new HashMap<String,String>();
            reqMap.put("sellerid", "6543");
            reqMap.put("infoType", "3");
            reqMap.put("operation", "-1");
            ResponseData data = sellerServiceClient.updateSellerCountInfo(reqMap);
            System.out.println(data);*/
            /*List<Integer> sellerList = new ArrayList<Integer>();
            sellerList.add(33496);
            sellerList.add(32196);
            int result = userActionServiceClient.removeViewActionService(604938,1,sellerList);
            System.out.println("用户本次删除了"+result+"条商家浏览记录");*/
            
            /*Map<String,String> microMap = new HashMap<String,String>();
            microMap.put("orderNumber", "20161210170310093033");
            microMap.put("payType", "1000019");
            microMap.put("authCode", "http://testwxapi.xmniao.com/bill/billlook.html?sellerid=33497");
            microMap.put("totalAmount", "0.01");
            microMap.put("payStatus", "1");
            microMap.put("sellerid", "33512");
            Map<String,String> resultMap = sellerOrderServiceClient.updateMicroOrder(microMap);
            System.out.println(resultMap);*/
           
//           XmnOrderParamV2 xmnOrderParamV2 = new XmnOrderParamV2();
//           xmnOrderParamV2.setBid("161223000046");
//           xmnOrderParamV2.setStatus("1");
//           xmnOrderParamV2.setZdate("2016-12-24 12:00:00");
//           xmnOrderParamV2.setUid("337542");
//           xmnOrderParamV2.setPhoneid(phoneid);
//           xmnOrderParamV2.setPayid(payid);
//           xmnOrderParamV2.setNumber(number);
//           xmnOrderParamV2.setThirdUid(thirdUid);
//           xmnOrderParamV2.setPaytype("1000015");
//           xmnOrderParamV2.setOrdertype("1");
//           xmnOrderParamV2.setIsbalance("1");
//           xmnOrderParamV2.setMoney("500.00");
//           xmnOrderParamV2.setPreferential("0");
//           xmnOrderParamV2.setPayamount("500");
//           xmnOrderParamV2.setSamount("0");
//           xmnOrderParamV2.setCommision("0");
//           xmnOrderParamV2.setProfit("0");
//           xmnOrderParamV2.setGiveMoney("0");
//           xmnOrderParamV2.setLiveCoin("600.00");
//           xmnOrderParamV2.setLiveCoinArrivedMoney("500");
//           xmnOrderParamV2.setLiveCoinRatio("0.3");
//           client.updateXmnOrderInfoV2(xmnOrderParamV2);
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            Map<String,String> liveMap = new HashMap<String,String>();
            liveMap.put("bid", "051707061128496695");
            liveMap.put("status", "1");
            liveMap.put("payType", "1000003");
            liveMap.put("payId", "");
            liveMap.put("payCode", "");
            liveMap.put("zdate", "2017-07-06 15:39:41");
            ResponseData responseData = liveClient.updateLiveOrder(liveMap);
            System.out.println("充值结果："+responseData);
//            Map<String,String> paraMap = new HashMap<>();
//            paraMap.put("uid","604827");
//            paraMap.put("consumeAmount","5010");
//            paraMap.put("returnRatio","0.10");
//            paraMap.put("liveRecordId","234");
//            paraMap.put("anchorId","32523");
//            paraMap.put("description","黄瓜");
//            ResponseData rd = liveClient.consumeGift(paraMap);
//            System.out.println(rd);
           /* Map<String,String> paramMap = new HashMap<String,String>();
            paramMap.put("uid", "604809");
            paramMap.put("recordId", "50358");
            paramMap.put("redpacketAmount", "8.22");
            ResponseData responseData = liveClient.receiveDailyRedpacket(paramMap);
            System.out.println("领取每日红包结果:"+responseData);*/
//            Map<String,String> reqMap = new HashMap<>();
//            reqMap.put("orderNo", "051704251532288481");
//            reqMap.put("payState", "1");
//            ResponseData responseData = debitcardServiceClient.exchangeDebitcard(reqMap);//兑换储值卡
//            System.out.println("兑换储值卡结果："+responseData);
            
//            Map<String,String> paymap = new HashMap<String,String>();
//            //更新订单状态
//			paymap.put("bid", "1000007187");
//			paymap.put("status", "1");//支付成功
//			paymap.put("payType", "1000011");//支付类型 优惠卷支付
//			paymap.put("payid", "0");
//			paymap.put("orderAmount", "0.03");//支付金额
//			paymap.put("integral", "0.01");//支付积分
//			paymap.put("freight", "14.00");//运费
//			paymap.put("cuser", "100.00");//优惠卷面额
//			paymap.put("payment", "0.00");
//			paymap.put("balance", "0.00");
//			paymap.put("zbalance", "0.00");
//			paymap.put("commision", "0.00");
//			paymap.put("birdCoin", "0.00");
//            Map<String, String> responseDatac =mallOrderServiceClient.notifyForPayComplete(paymap);
//            System.out.println("积分商城订单支付更新结果："+responseDatac);

            /*Map<String,String> paramMap = new HashMap<>();
            paramMap.put("uid", "604809");
            ResponseData resData =  verTeamServiceClient.getTeamPerformance(paramMap);
            System.out.println(resData);*/
            
//            Map<String,String> paramMap = new HashMap<>();
//            paramMap.put("orderNo", "170527123457");
//            paramMap.put("uid", "604809");
//            paramMap.put("totalAmount", "1000");
//            paramMap.put("payType", "1000013");
////            paramMap.put("payId", "98745632415322");
//            paramMap.put("liveCoin", "500");
//            paramMap.put("walletAmount", "500");
//            paramMap.put("samount", "0");
//            paramMap.put("preferential", "0");
//            paramMap.put("payState", "2");
//
//            fashionServiceClient.upateTicketsOrder(paramMap);
            
            /*Map<String,String> request = new HashMap<>();
            request.put("uid", "606616");
            request.put("transNo", "332480801570824192");
            request.put("number", "2");
            ResponseData responseData = manorServiceClient.receiveDailyEarnings(request);
            System.out.println("领取花蜜结果:"+responseData);*/
        }
        catch (TException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            transport.close();
        }
    }
}