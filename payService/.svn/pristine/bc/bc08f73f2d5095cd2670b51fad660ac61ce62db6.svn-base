package com.xmniao.kuaiqian;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;

import com.bill99.schema.commons.Version;
import com.bill99.schema.fo.commons.Pay2bankTypeV2;
import com.bill99.schema.fo.commons.RequestHeader;
import com.bill99.schema.fo.settlement.ApplyRequestType;
import com.bill99.schema.fo.settlement.BatchSettlementApplyRequest;
import com.bill99.schema.fo.settlement.BatchSettlementApplyResponse;
import com.bill99.schema.fo.settlement.BatchidQueryRequest;
import com.bill99.schema.fo.settlement.BatchidQueryRequestType;
import com.bill99.schema.fo.settlement.BatchidQueryResponse;
import com.bill99.schema.fo.settlement.ComplexQueryRequest;
import com.bill99.schema.fo.settlement.ComplexQueryRequestType;
import com.bill99.schema.fo.settlement.ComplexQueryResponse;
import com.bill99.schema.fo.settlement.SettlementPkiApiRequest;
import com.bill99.schema.fo.settlement.SettlementPkiApiResponse;
import com.bill99.seashell.common.util.DateUtil;

/**
 * 批量付款工具类
 * */
public class CustomerUtil {
	
	private static final String ENCODING = "utf-8";
	
	/**
	 * BatchSettlementApplyRequest转换为xml格式 
	 * @param request 付款请求（原文）
	 * @return String xml 字符串
	 */
	public static String batchSettlementApplyRequestToXml(BatchSettlementApplyRequest request) {
		String result = "";
		try {
			IBindingFactory bfact = BindingDirectory
					.getFactory(BatchSettlementApplyRequest.class);
			IMarshallingContext mctx = bfact.createMarshallingContext();
			mctx.setIndent(2);
			StringWriter sw = new StringWriter();
			mctx.setOutput(sw);
			mctx.marshalDocument(request);
			result = sw.toString();
			return result;
		} catch (JiBXException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * SettlementPkiApiRequest转换为xml格式 
	 * @param request 付款请求（密文）
	 * @return String xml 字符串
	 */
	public static String settlementPkiApiRequestToXml(SettlementPkiApiRequest request) {
		try {
			IBindingFactory bfact = BindingDirectory
					.getFactory(SettlementPkiApiRequest.class);
			IMarshallingContext mctx = bfact.createMarshallingContext();
			mctx.setIndent(2);
			StringWriter sw = new StringWriter();
			mctx.setOutput(sw);
			mctx.marshalDocument(request);
			return sw.toString();
		} catch (JiBXException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * BatchidQueryRequest转换为xml格式 
	 * @param request 查询请求(根据批次号batchNo查询)
	 * @return String xml 字符串
	 */
	public static String batchidQueryRequestToXml(BatchidQueryRequest request) {
		String result = "";
		try {
			IBindingFactory bfact = BindingDirectory
					.getFactory(BatchidQueryRequest.class);
			IMarshallingContext mctx = bfact.createMarshallingContext();
			mctx.setIndent(2);
			StringWriter sw = new StringWriter();
			mctx.setOutput(sw);
			mctx.marshalDocument(request);
			result = sw.toString();
			return result;
		} catch (JiBXException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * ComplexQueryRequest转换为xml格式 
	 * @param request 查询请求(多条件查询类)
	 * @return String xml 字符串
	 */
	public static String complexQueryRequestToXml(ComplexQueryRequest request) {
		String result = "";
		try {
			IBindingFactory bfact = BindingDirectory
					.getFactory(ComplexQueryRequest.class);
			IMarshallingContext mctx = bfact.createMarshallingContext();
			mctx.setIndent(2);
			StringWriter sw = new StringWriter();
			mctx.setOutput(sw);
			mctx.marshalDocument(request);
			result = sw.toString();
			return result;
		} catch (JiBXException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 把输入流转换为SettlementPkiApiRequest 
	 * @param Input 请求
	 * @return SettlementPkiApiRequest 请求
	 */
	public static SettlementPkiApiRequest xmlToSettlementPkiApiRequest(String responseXml) {
		try {
			InputStream input = new ByteArrayInputStream(responseXml.getBytes(ENCODING));
			IBindingFactory bfact = BindingDirectory
					.getFactory(SettlementPkiApiRequest.class);
			IUnmarshallingContext uctx = bfact.createUnmarshallingContext();
			SettlementPkiApiRequest response = (SettlementPkiApiRequest) uctx
					.unmarshalDocument(input, null);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 把xml转换为SettlementPkiApiResponse 
	 * @param input 返回应答
	 * @return SettlementPkiApiResponse 返回应答
	 * @throws IOException 
	 */
	public static SettlementPkiApiResponse xmlToSettlementPkiApiResponse(InputStream input){
		try {
			IBindingFactory bfact = BindingDirectory
					.getFactory(SettlementPkiApiResponse.class);
			IUnmarshallingContext uctx = bfact.createUnmarshallingContext();
			SettlementPkiApiResponse response = (SettlementPkiApiResponse) uctx
					.unmarshalDocument(input, null);
			return response;
		} catch (JiBXException e) {
			e.printStackTrace();
		} finally{
			if(input!=null){
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	/**
	 * 把xml转换为SettlementPkiApiResponse 
	 * @param responseXml 返回应答信息xml字符串
	 * @return SettlementPkiApiResponse 返回应答
	 */
	public static SettlementPkiApiResponse xmlToSettlementPkiApiResponse(String responseXml) {
		InputStream input =null;
		try {
			input = new ByteArrayInputStream(responseXml.getBytes(ENCODING));
			IBindingFactory bfact = BindingDirectory
					.getFactory(SettlementPkiApiResponse.class);
			IUnmarshallingContext uctx = bfact.createUnmarshallingContext();
			SettlementPkiApiResponse response = (SettlementPkiApiResponse) uctx
					.unmarshalDocument(input, null);
			return response;
		} catch (JiBXException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}finally{
			if(input!=null){
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	/**
	 * 把xml转换为BatchidQueryResponse，根据批次号查询
	 * @param responseXml 返回应答信息xml字符串
	 * @return BatchidQueryResponse 返回应答
	 */
	public static BatchidQueryResponse xmlToBatchidQueryResponse(String responseXml) {
		InputStream input =null;
		try {
			IBindingFactory bfact = BindingDirectory
					.getFactory(BatchidQueryResponse.class);
			input = new ByteArrayInputStream(responseXml
					.getBytes(ENCODING));
			IUnmarshallingContext uctx = bfact.createUnmarshallingContext();
			BatchidQueryResponse response = (BatchidQueryResponse) uctx
					.unmarshalDocument(input, null);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if(input!=null){
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 把xml转换为BatchSettlementApplyResponse，付款返回应答
	 * @param responseXml 返回应答信息xml字符串
	 * @return BatchSettlementApplyResponse 返回应答
	 */
	public static BatchSettlementApplyResponse xmlToBatchSettlementApplyResponse(String responseXml) {
		InputStream input =null;
		try {
			IBindingFactory bfact = BindingDirectory
					.getFactory(BatchSettlementApplyResponse.class);
			input = new ByteArrayInputStream(responseXml
					.getBytes(ENCODING));
			IUnmarshallingContext uctx = bfact.createUnmarshallingContext();
			BatchSettlementApplyResponse response = (BatchSettlementApplyResponse) uctx
					.unmarshalDocument(input, null);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if(input!=null){
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 把xml转换为ComplexQueryResponse，根据多条件查询
	 * @param responseXml 返回应答信息xml字符串
	 * @return ComplexQueryResponse 返回应答信息类
	 */
	public static ComplexQueryResponse xmlToComplexQueryResponse(String responseXml) {
		InputStream input =null;
		try {
			IBindingFactory bfact = BindingDirectory
					.getFactory(ComplexQueryResponse.class);

			input = new ByteArrayInputStream(responseXml
					.getBytes(ENCODING));
			IUnmarshallingContext uctx = bfact.createUnmarshallingContext();
			ComplexQueryResponse response = (ComplexQueryResponse) uctx
					.unmarshalDocument(input, null);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if(input!=null){
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 把请求信息数据设置到BatchSettlementApplyRequest类中
	 * @param dealInfo 请求信息数据
	 * @return BatchSettlementApplyRequest 请求付款信息类(原文)
	 */
	public static BatchSettlementApplyRequest getBatchSettlementApplyRequest(DealInfoEntity dealInfo) {
		BatchSettlementApplyRequest request = new BatchSettlementApplyRequest();
		RequestHeader head = new RequestHeader();
		Version ve = new Version();
		head.setTime(DateUtil.formatDateTime("yyyyMMddHHmmss", new Date()));
		ve.setService(dealInfo.getServiceType());
		ve.setVersion(dealInfo.getVersion());
		head.setVersion(ve);
		request.setRequestHeader(head);
		ApplyRequestType body = new ApplyRequestType();
		body.setApplyDate(dealInfo.getApplyDate()==null?"":dealInfo.getApplyDate());
		body.setAutoRefund(dealInfo.getAutoRefund()==null?"":dealInfo.getAutoRefund());
		body.setBatchFail(dealInfo.getBatchFail()==null?"":dealInfo.getBatchFail());
		body.setBatchNo(dealInfo.getBatchNo()==null?"":dealInfo.getBatchNo());
		body.setCheckAmtCnt(dealInfo.getCheckAmtCnt()==null?"":dealInfo.getCheckAmtCnt());
		body.setCur(dealInfo.getCur()==null?"":dealInfo.getCur());
		body.setFeeType(dealInfo.getFeeType()==null?"":dealInfo.getFeeType());
		body.setMerchantMemo1(dealInfo.getMerchantMemo1()==null?"":dealInfo.getMerchantMemo1());
		body.setMerchantMemo2(dealInfo.getMerchantMemo2()==null?"":dealInfo.getMerchantMemo2());
		body.setMerchantMemo3(dealInfo.getMerchantMemo3()==null?"":dealInfo.getMerchantMemo3());
		body.setName(dealInfo.getName()==null?"":dealInfo.getName());
		body.setPayerAcctCode(dealInfo.getPayerAcctCode()==null?"":dealInfo.getPayerAcctCode());
		body.setPhoneNoteFlag(dealInfo.getPhoneNoteFlag()==null?"":dealInfo.getPhoneNoteFlag());
		body.setRechargeType(dealInfo.getRechargeType()==null?"":dealInfo.getRechargeType());
		body.setTotalAmt(dealInfo.getTotalAmt()==null?"":dealInfo.getTotalAmt());
		body.setTotalCnt(dealInfo.getTotalCnt()==null?"":dealInfo.getTotalCnt());
		body.setPay2bankLists(getPay2BankList(dealInfo));
		request.setRequestBody(body);
		return request;
	}

	/**
	 * 把请求查询信息数据设置到BatchidQueryRequest类中
	 * @param dealInfo 请求信息数据
	 * @return BatchidQueryRequest 请求查询信息类(原文）
	 */
	public static BatchidQueryRequest getBatchidQueryRequest(DealInfoEntity dealInfo) {
		BatchidQueryRequest request = new BatchidQueryRequest();
		RequestHeader head = new RequestHeader();
		Version ve = new Version();
		head.setTime(DateUtil.formatDateTime("yyyyMMddHHmmss", new Date()));
		ve.setService(dealInfo.getServiceType());
		ve.setVersion(dealInfo.getVersion());
		head.setVersion(ve);
		request.setRequestHeader(head);
		BatchidQueryRequestType body = new BatchidQueryRequestType();
		body.setBatchNo(dealInfo.getBatchNo()==null?"":dealInfo.getBatchNo());
		body.setListFlag(dealInfo.getListFlag()==null?"":dealInfo.getListFlag());
		body.setPage(dealInfo.getPage()==null?"":dealInfo.getPage());
		body.setPageSize(dealInfo.getPageSize()==null?"":dealInfo.getPageSize());
		request.setRequestBody(body);
		return request;
	}
	
	/**
	 * 把请求信息数据设置到ComplexQueryRequest类中
	 * @param dealInfo 请求信息数据
	 * @return ComplexQueryRequest 请求付款信息类(原文)
	 */
	public static ComplexQueryRequest getComplexQueryRequest(DealInfoEntity dealInfo) {
		ComplexQueryRequest request = new ComplexQueryRequest();
		RequestHeader head = new RequestHeader();
		Version ve = new Version();
		head.setTime(DateUtil.formatDateTime("yyyyMMddHHmmss", new Date()));
		ve.setService(dealInfo.getServiceType());
		ve.setVersion(dealInfo.getVersion());
		head.setVersion(ve);
		request.setRequestHeader(head);
		ComplexQueryRequestType body = new ComplexQueryRequestType();
		body.setBankCardNo(dealInfo.getBankCardNo()==null?"":dealInfo.getBankCardNo());
		body.setBeginApplyTime(dealInfo.getBeginApplyDate()==null?"":dealInfo.getBeginApplyDate());
		body.setBranchBank(dealInfo.getBranchBank()==null?"":dealInfo.getBranchBank());
		body.setCity(dealInfo.getCity()==null?"":dealInfo.getCity());
		body.setBank(dealInfo.getBank()==null?"":dealInfo.getBank());
		body.setEndApplyTime(dealInfo.getEndApplyDate()==null?"":dealInfo.getEndApplyDate());
		body.setMerchantId(dealInfo.getMerchantId()==null?"":dealInfo.getMerchantId());
		body.setProvince(dealInfo.getProvince()==null?"":dealInfo.getProvince());
		body.setName(dealInfo.getName()==null?"":dealInfo.getName());
		body.setOrderBankErrorCode(dealInfo.getOrderBankErrorCode()==null?"":dealInfo.getOrderBankErrorCode());
		body.setOrderErrorCode(dealInfo.getOrderErrorCode()==null?"":dealInfo.getOrderErrorCode());
		body.setOrderStatus(dealInfo.getOrderStatus()==null?"":dealInfo.getOrderStatus());
		body.setPayeeType(dealInfo.getPayeeType()==null?"":dealInfo.getPayeeType());
		body.setPage(dealInfo.getPage()==null?"":dealInfo.getPage());
		body.setPageSize(dealInfo.getPageSize()==null?"":dealInfo.getPageSize());
		request.setRequestBody(body);
		return request;
	}
	
	/**
	 * 把付款详细信息设置到Pay2bankTypeV2类中，并且保存在LIST列表表
	 * @param dealInfo 付款请求信息数据
	 * @return List<Pay2bankTypeV2> 保存有付款信息的列表类
	 */
	public static List<Pay2bankTypeV2> getPay2BankList(DealInfoEntity dealInfo) {
		List<Pay2bankTypeV2> list = new ArrayList<Pay2bankTypeV2>();
		List<OrderInfoEntity> batchUpLoadBeanList = dealInfo.getOrdersInfo();
			for (OrderInfoEntity orderDto : batchUpLoadBeanList) {
				Pay2bankTypeV2 pay2bankType = new Pay2bankTypeV2();
				pay2bankType.setAmt(orderDto.getAmt()==null?"":orderDto.getAmt());
				pay2bankType.setBank(orderDto.getBank()==null?"":orderDto.getBank());
				pay2bankType.setBankCardNo(orderDto.getBankCardNo()==null?"":orderDto.getBankCardNo());
				pay2bankType.setBankMemo(orderDto.getBankMemo()==null?"":orderDto.getBankMemo());
				pay2bankType.setBankPurpose(orderDto.getBankPurpose()==null?"":orderDto.getBankPurpose());
				pay2bankType.setBranchBank(orderDto.getBranchBank()==null?"":orderDto.getBranchBank());
				pay2bankType.setCity(orderDto.getCity()==null?"":orderDto.getCity());
				pay2bankType.setMemo(orderDto.getMemo()==null?"":orderDto.getMemo());
				pay2bankType.setMerchantId(orderDto.getMerchantId()==null?"":orderDto.getMerchantId());
				pay2bankType.setMerchantMemo1(orderDto.getMerchantMemo1()==null?"":orderDto.getMerchantMemo1());
				pay2bankType.setMerchantMemo2(orderDto.getMerchantMemo2()==null?"":orderDto.getMerchantMemo2());
				pay2bankType.setMerchantMemo3(orderDto.getMerchantMemo3()==null?"":orderDto.getMerchantMemo3());
				pay2bankType.setName(orderDto.getName()==null?"":orderDto.getName());
				pay2bankType.setPayeeEmail(orderDto.getPayeeEmail()==null?"":orderDto.getPayeeEmail());
				pay2bankType.setPayeeMobile(orderDto.getPayeeMobile()==null?"":orderDto.getPayeeMobile());
				pay2bankType.setPayeeNote(orderDto.getPayeeNote()==null?"":orderDto.getPayeeNote());
				pay2bankType.setPayeeType(orderDto.getPayeeType()==null?"":orderDto.getPayeeType());
				pay2bankType.setPeriod(orderDto.getPeriod()==null?"":orderDto.getPeriod());
				pay2bankType.setProvince(orderDto.getProvince()==null?"":orderDto.getProvince());
				list.add(pay2bankType);
			}
		return list;
	}
	
	/**
	 * 生成报盘文件
	 * @param request 请求报文
	 * @param dealInfo 请求信息
	 * @return String 返回文件完整信息，包括文件路径
	 * */
	public static String fileRequest(SettlementPkiApiRequest request,DealInfoEntity dealInfo) {
		BufferedOutputStream io = null;
		String filename = "INBOUND_" + dealInfo.getMemberCode() + "_"
				+ DateUtil.formatDateTime("yyyyMMddHHmmss", new Date()) + "_"
				+ dealInfo.getBatchNo() + ".PKI";
		try {
			File name = new File(filename);
			if (name.exists()) {
				name.delete();
				name.createNewFile();
			} else {
				name.createNewFile();
			}
			filename = name.getAbsolutePath();
			String org = CustomerUtil.settlementPkiApiRequestToXml(request);
			io = new BufferedOutputStream(new FileOutputStream(name));
			io.write(org.getBytes("utf-8"));
			return filename;
		} catch (IOException e) {
			e.printStackTrace();
			return filename;
		} finally {
			if (io != null) {
				try {
					io.flush();
					io.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
