package com.xmniao.common;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import chinapnr.SecureLink;

/**
 * 汇付天下异步数据 处理
 * 
 * @author Dongjietao
 * 
 */
public class PnRSignature {

	public static String getSign(Map<String, Object> map, PayDatas payDatas)
			throws UnsupportedEncodingException {// 签名

		String Version = payDatas.getVersion().trim();// 必须
		String CmdId = payDatas.getCmdId().trim();// 必须
		String CustId = payDatas.getCustId().trim(); // 必须
		String OrdId = String.valueOf(map.get("OrdId")).trim(); // 必须
		String OrdAmt = String.valueOf(map.get("OrdAmt")).trim(); // 必须
		String MerPriv = map.get("MerPriv").toString().trim();
		String AcctName = map.get("AcctName").toString().trim();// 必须
		String BankId = map.get("BankId").toString().trim();// 必须
		String AcctId = map.get("AcctId").toString().trim();// 必须
		String PrType = (map.get("PrType").toString().trim() + "");// 必须
		String RetUrl = payDatas.getRetUrl().trim();// 必须
		// String RetUrl = ChinaPnRInfoConfig.RetUrl.trim();//必须
		String Charset = (payDatas.getCharset().trim() + "");// 必须
		String ChkType = payDatas.getChkType().trim();// 必须
		String MerId = payDatas.getMerId().trim();
		// 签名
		// 商户私钥文件路径
		String MerKeyFile = LoadProperties.getKeyPath("MerPrK872881.key",
				"classes");
		StringBuffer sb = new StringBuffer();
		sb.append(Version);
		sb.append(CmdId);
		sb.append(CustId);
		sb.append(OrdId);
		sb.append(OrdAmt);
		sb.append(MerPriv);
		sb.append(AcctName);
		sb.append(BankId);
		sb.append(AcctId);
		sb.append(PrType);
		sb.append(RetUrl);
		sb.append(Charset);
		sb.append(ChkType);

		SecureLink sl = new SecureLink();
		int ret = sl.SignMsg(MerId, MerKeyFile, sb.toString().getBytes("GBK"));

		if (ret != 0) {
			return "签名错误 ret=" + ret;
		}
		return sl.getChkValue();

	}

	public static String getVeriSign(Map<String, String> map) { // 同步返回数据验签
		String CmdId = map.get("CmdId").toString().trim();
		String RespCode = map.get("RespCode").toString().trim();
		String RespDesc = map.get("RespDesc").toString().trim();
		String CustId = map.get("CustId").toString().trim();
		String SubAcctId = "";
		if (map.containsKey("SubAcctId")) {
			SubAcctId = map.get("SubAcctId").toString().trim();
		} else {

		}
		String OrdId = map.get("OrdId").toString().trim();
		String TransStat = map.get("TransStat").toString().trim();
		String HandleDate = map.get("HandleDate").toString().trim();
		String HandleSeqId = map.get("HandleSeqId").toString().trim();
		String OrdAmt = map.get("OrdAmt").toString().trim();
		String MerPriv = map.get("MerPriv").toString().trim();
		String AcctName = map.get("AcctName").toString().trim();
		String BankId = map.get("BankId").toString().trim();
		String AcctId = map.get("AcctId").toString().trim();
		String UserMp = map.get("UserMp").toString().trim();
		String CertType = map.get("CertType").toString().trim();
		String CertId = map.get("CertId").toString().trim();
		String PrType = map.get("PrType").toString().trim();
		String ProvName = map.get("ProvName").toString().trim();
		String AreaName = map.get("AreaName").toString().trim();
		String BranchName = map.get("BranchName").toString().trim();
		String PrPurpose = map.get("PrPurpose").toString().trim();
		String Charset = map.get("Charset").toString().trim();
		String ChkType = map.get("ChkType").toString().trim();
		String ChkValue = map.get("ChkValue").toString().trim();
		// 验签
		String PgKeyFile = LoadProperties.getKeyPath("PgPubk.key", "classes");
		// 获取公钥名称
		String MerData = CmdId + RespCode + RespDesc + CustId + SubAcctId
				+ OrdId + TransStat + HandleDate + HandleSeqId +
				OrdAmt + MerPriv + AcctName + BankId + AcctId + PrType
				+ Charset + ChkType; // 拼接验签数据体
		SecureLink sl = new SecureLink();
		int ret = 1;

		try {

			String md1 = new String(MerData.getBytes(), "GBK");
			String md2 = new String(MerData.getBytes("GBK"), "GBK");
			String md3 = new String(MerData.getBytes("UTF-8"), "GBK");
			String md4 = new String(MerData.getBytes("UTF-8"), "UTF-8");
			String md5 = new String(MerData.getBytes("GBK"), "UTF-8");
			ret = sl.VeriSignMsg(PgKeyFile, md1, ChkValue);
			ret = sl.VeriSignMsg(PgKeyFile, md2, ChkValue);
			ret = sl.VeriSignMsg(PgKeyFile, md3, ChkValue);
			ret = sl.VeriSignMsg(PgKeyFile, md4, ChkValue);
			ret = sl.VeriSignMsg(PgKeyFile, md5, ChkValue);
			return "S";

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (ret != 0) {
			return "E"; // 验签失败
		} else {
			if (RespCode.equals("000")) {
				// 交易成功
				// 根据订单号 进行相应业务操作
				// 在些插入代码
				return "S";
			} else {
				// 交易失败
				// 根据订单号 进行相应业务操作
				// 在些插入代码
				return "F";
			}
		}
	}

	public static String getVeriSignR(Map map) { // 异步回调验签
		String CmdId = map.get("CmdId").toString().trim();
		String RespCode = map.get("RespCode").toString().trim();
		String RespDesc = map.get("RespDesc").toString().trim();
		String CustId = map.get("CustId").toString().trim();
		String SubAcctId = "";
		if (map.containsKey("SubAcctId") & map.get("SubAcctId") != null
				& map.get("SubAcctId") != "null") {
			SubAcctId = (String) map.get("SubAcctId");
		}

		String OrdId = map.get("OrdId").toString().trim();
		String TransStat = map.get("TransStat").toString().trim();
		String HandleDate = map.get("HandleDate").toString().trim();
		String HandleSeqId = map.get("HandleSeqId").toString().trim();
		String OrdAmt = map.get("OrdAmt").toString().trim();
		String MerPriv = map.get("MerPriv").toString().trim();
		String AcctName = map.get("AcctName").toString().trim();
		String BankId = map.get("BankId").toString().trim();
		String AcctId = map.get("AcctId").toString().trim();
		String UserMp = map.get("UserMp").toString().trim();
		String CertType = map.get("CertType").toString().trim();
		String CertId = map.get("CertId").toString().trim();
		String PrType = map.get("PrType").toString().trim();
		String ProvName = map.get("ProvName").toString().trim();
		String AreaName = map.get("AreaName").toString().trim();
		String BranchName = map.get("BranchName").toString().trim();
		String PrPurpose = map.get("PrPurpose").toString().trim();
		String Charset = map.get("Charset").toString().trim();
		String ChkType = map.get("ChkType").toString().trim();
		String ChkValue = map.get("ChkValue").toString().trim();

		// 验签
		String PgKeyFile = LoadProperties.getKeyPath("PgPubk.key", "classes");// 获取公钥名称
		String MerData = CmdId + RespCode + RespDesc + CustId + SubAcctId
				+ OrdId + TransStat + HandleDate + HandleSeqId +

				OrdAmt + MerPriv + AcctName + BankId + AcctId + PrType
				+ Charset + ChkType;

		SecureLink sl = new SecureLink();
		int ret = sl.VeriSignMsg(PgKeyFile, MerData, ChkValue);

		if (ret != 0) {
			return "E"; // 验签失败
		} else {
			if (RespCode.equals("000")) {
				// 交易成功
				// 根据订单号 进行相应业务操作
				// 在些插入代码
				return "S";
			} else {
				// 交易失败
				// 根据订单号 进行相应业务操作
				// 在些插入代码
				return "F";
			}
		}
	}

	public static String getQuerySign(String orderId, String ordAmt,PayDatas payDatas) throws UnsupportedEncodingException {
		
		String Version = payDatas.getVersion().trim();// 必须
		String CmdId = payDatas.getQueryCmdId().trim();// 必须
		String CustId = payDatas.getCustId().trim(); // 必须
		String Charset = (payDatas.getCharset().trim() + "");// 必须
		String ChkType = payDatas.getChkType().trim();// 必须
		String MerId = payDatas.getMerId().trim();
		// 签名
		// 商户私钥文件路径
		String MerKeyFile = LoadProperties.getKeyPath("MerPrK872881.key",
				"classes");
		StringBuffer sb = new StringBuffer();
		sb.append(Version);
		sb.append(CmdId);
		sb.append(CustId);
		sb.append(orderId);
		sb.append(ordAmt);
		sb.append(Charset);
		sb.append(ChkType);

		SecureLink sl = new SecureLink();
		int ret = sl.SignMsg(MerId, MerKeyFile, sb.toString().getBytes("GBK"));

		if (ret != 0) {
			return "签名错误 ret=" + ret;
		}
		return sl.getChkValue();
		
	}
}
