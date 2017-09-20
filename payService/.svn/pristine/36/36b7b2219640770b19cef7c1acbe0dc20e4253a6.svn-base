package com.xmniao.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.thrift.transport.TTransport;
//import org.junit.Test;

import com.xmniao.service.UPayService;
import com.xmniao.service.impl.UPayServiceImpl;
import com.xmniao.thrift.ledger.FailureException;

public class UPayServiceTest {

    // 服务端的IP地址
    private static final String IP_NUMBER = "localhost";
    // 服务端的端口号
    private static final int PORT = 7911;

    private static TTransport transport = null;

    // public static void main(String[] args)
    // {
    // try
    // {
    // // 设置调用的服务地址为本地，端口为 7911
    // transport = new TSocket(IP_NUMBER, PORT);
    // TFramedTransport frame = new TFramedTransport(transport);
    // // 设置传输协议为 TBinaryProtocol
    // TProtocol protocol = new TBinaryProtocol(frame);
    // TMultiplexedProtocol ManagerProtocol = new TMultiplexedProtocol(protocol,
    // "UPayService");
    // UPayService.Client client = new Client(ManagerProtocol);
    //
    // //打开端口,开始调用
    // transport.open();
    //
    // long sdate = System.currentTimeMillis();
    //
    // client.uPayRefund("", "", "", 11, 11);
    //
    // long edate = System.currentTimeMillis();
    // long result=edate-sdate;
    //
    // System.out.println("程序运行时间： "+result+"ms ");
    // } catch (TException e) {
    // // TODO 自动生成的 catch 块
    // e.printStackTrace();
    // }finally{
    // transport.close();
    // }
    //
    // }

//    @Test
    public void testUpayService() throws FailureException {

	UPayService uPayService = new UPayServiceImpl();

	// uPayService.uPayRefund("1412121212121212",
	// String.valueOf(System.currentTimeMillis()), "", 1.2, 1.2);
	Map<String, Object> map = new HashMap<String, Object>();

	map.put("userType", "1");
	map.put("orderId", "333333443"); // 商户订单号
	map.put("merDate", "20150211"); // 商户订单日期 yyyyMMdd
	map.put("orderAmount", "0.01"); // 以分为单位
	map.put("recvAccount", "6212264000022804666");// 收款方账号
	map.put("recvUserName", "曾浩明");// 收款方户名
	map.put("identityType", "");// 收款方证件类型
				    // 01-----身份证（收款方账户属性值为0：对私时，该字段需传递，值为1：对公时，该字段不传递）
	map.put("identityCode", "");// 收款方平台预留证件号码
				    // 使用平台公钥进行RSA加密（收款方身份证号码。长度为15-18位数字与字母的组合，中间不允许有空格。(对私付款时必填)
	map.put("identityHolder", "曾浩明");// 证件持有人真实姓名
					// 使用平台公钥进行RSA加密
					// 收款方身份证持有人真实姓名。长度小于等于30个汉字。(对私付款时必填)
	map.put("mediaType", "");// 媒介类型
				 // 取值范围：MOBILE（手机号）（收款方账户属性值为0：对私时，该字段需传递，值为1：对公时，该字段不传递）
	map.put("mediaId", "");// 媒介值
			       // 付款方平台预留手机号码（收款方账户属性值为0：对私时，该字段需传递，值为1：对公时，该字段不传递）
	map.put("recvGateId", "ICBC");// 支持银行列表
	map.put("purpose", "测试测试");// 摘要
	map.put("provName", "广东省");// 省
	map.put("cityName", "深圳市");// 市
	map.put("bankBrhname", "中国工商银行深圳梅林支行");// 开户行支行全称

	/*
	 * map.put("userType", "1"); map.put("orderId", "123456"); // 商户订单号
	 * map.put("merDate", "20141224"); // 商户订单日期 yyyyMMdd
	 * map.put("orderAmount", "0.1"); // 以分为单位 map.put("recvAccount",
	 * "6013822000646753536");// 收款方账号 map.put("recvUserName", "杨京");//
	 * 收款方户名 map.put("identityType", "01");// 收款方证件类型 //
	 * 01-----身份证（收款方账户属性值为0：对私时，该字段需传递，值为1：对公时，该字段不传递）
	 * map.put("identityCode", "430621199308099015");// 收款方平台预留证件号码 //
	 * 使用平台公钥进行RSA加密（收款方身份证号码。长度为15-18位数字与字母的组合，中间不允许有空格。(对私付款时必填)
	 * map.put("identityHolder", "杨京");// 证件持有人真实姓名 // 使用平台公钥进行RSA加密 //
	 * 收款方身份证持有人真实姓名。长度小于等于30个汉字。(对私付款时必填) map.put("mediaType", "MOBILE");//
	 * 媒介类型 // 取值范围：MOBILE（手机号）（收款方账户属性值为0：对私时，该字段需传递，值为1：对公时，该字段不传递）
	 * map.put("mediaId", "18664879600");// 媒介值 //
	 * 付款方平台预留手机号码（收款方账户属性值为0：对私时，该字段需传递，值为1：对公时，该字段不传递）
	 * map.put("recvGateId", "BOC");// 支持银行列表 map.put("purpose", "测试测试");//
	 * 摘要 map.put("provName", "广东");// 省 map.put("cityName", "深圳");// 市
	 * map.put("bankBrhname", "中国银行深圳侨城支行");// 开户行支行全称
	 */
	uPayService.uPay(map);
    }
}
