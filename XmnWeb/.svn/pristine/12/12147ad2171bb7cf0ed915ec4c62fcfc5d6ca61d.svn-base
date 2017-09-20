package com.xmniao.xmn.core.common;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.xmniao.xmn.core.businessman.entity.SellerMatrix;
import com.xmniao.xmn.core.util.MatrixToImageWriter;
import com.xmniao.xmn.core.util.MixturePic;
import com.xmniao.xmn.core.util.PropertiesUtil;

/**
 * 生成二维码图片
 * @author Administrator
 *
 */
@Controller
public class MatrixController {
	
	protected final Logger log = Logger.getLogger(getClass());

	// 微信商城扫码支付地址
	private String WXAPI_URL = PropertiesUtil.readValue("wxapi.url");
	
	@RequestMapping(value = "getMatrix")
	public void getMatrix(@RequestParam("text") String text, HttpServletResponse response) throws IOException, WriterException{
		MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		Map<EncodeHintType, Object> hints = new HashMap<>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, 150, 150, hints);
		response.setContentType("image/jpeg");
		MatrixToImageWriter.writeToStream(bitMatrix, "jpg", response.getOutputStream());
	}
	
	@RequestMapping(value = "getBigMatrix")
	public void getBigMatrix(SellerMatrix sllerMatrix, HttpServletResponse response,HttpServletRequest request) throws IOException, WriterException{
		StringBuffer text=new StringBuffer(WXAPI_URL);
		text.append("?").append("sellerid=").append(replaceString(sllerMatrix.getSellerid()))
			.append("&sellername=").append(replaceString(sllerMatrix.getSellername()))
			.append("&aid=").append(replaceString(sllerMatrix.getAid()));
		MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		Map<EncodeHintType, Object> hints = new HashMap<>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		hints.put(EncodeHintType.MARGIN, 1);  
		BitMatrix bitMatrix = multiFormatWriter.encode(text.toString(), BarcodeFormat.QR_CODE, 1000, 1000, hints);
		String imgName=sllerMatrix.getSellerid()+"-"+sllerMatrix.getSellername();
//		File file = new File("e:/temp.jpg");
//		MatrixToImageWriter.writeToFile(bitMatrix, "jpg", file);
		
		response.setContentType("image/jpeg");
		response.setHeader("Content-Disposition","inline; filename="+ getDisponsition(request,imgName));
		BufferedImage bi = MatrixToImageWriter.toBufferedImage(bitMatrix);
		MixturePic.drawImage(bi,response.getOutputStream(),replaceString(sllerMatrix.getSellername()));
	}
	    
	/**
	 * 方法描述：下载stick三张图片，sellername_sellerid.zip
	 * 创建人： lifeng
	 * 创建时间：2016年8月18日下午4:20:41
	 * @param sellerMatrix
	 * @param mid
	 * @param response
	 * @param request
	 * @throws IOException
	 * @throws WriterException
	 */
	@RequestMapping(value = "downLoadStick2")
	public void downLoadStick(SellerMatrix sellerMatrix,String mid,  
			HttpServletResponse response,HttpServletRequest request) throws IOException, WriterException{
		Long t = System.currentTimeMillis();
		sellerMatrix.setSellerid(mid);
		getMatrixUtil(sellerMatrix, 250, 250, null, null, 0);
		getMatrixUtil(sellerMatrix, 340, 340, null, null, 1);
		getMatrixUtil(sellerMatrix, 340, 340, null, null, 2);
		//压缩并下载文件夹
		MixturePic.downLoadStick(sellerMatrix,request,response);
		log.info("下载完成,用时："+(System.currentTimeMillis()-t)+"毫秒");
	}
	
	/**
	 * 方法描述：下载stick三张图片，sellername_sellerid.zip
	 * 创建人： lifeng
	 * 创建时间：2016年8月18日下午4:20:41
	 * @param sellerMatrix
	 * @param mid
	 * @param response
	 * @param request
	 * @throws IOException
	 * @throws WriterException
	 */
	@RequestMapping(value = "downLoadStick")
	public void downLoadStick2(SellerMatrix sellerMatrix,String mid,  
			HttpServletResponse response,HttpServletRequest request) throws IOException, WriterException{
		Long t = System.currentTimeMillis();
		sellerMatrix.setSellerid(mid);
//		getMatrixUtil(sellerMatrix, 250, 250, null, null, 0);
		/*long t1 = System.currentTimeMillis();*/
		getMatrixUtil(sellerMatrix, 1000, 1000, null, null, 4);
		long t3 = System.currentTimeMillis();
		getMatrixUtil(sellerMatrix, 982, 982, null, null, 3);
		long t2 = System.currentTimeMillis();
		//压缩并下载文件夹
//		MixturePic.createThreeStick(sellerMatrix, request, response);
		MixturePic.downLoadStick(sellerMatrix,request,response);
		long t4 = System.currentTimeMillis();
		log.info("下载完成,用时："+(System.currentTimeMillis()-t)+"毫秒");
//		log.info("详细用时{T1="+(t2-t1)+"ms,T2="+(t3-t2)+"ms,T3="+(t4-t3)+"ms}");
	}
	/**
	 * 方法描述：按照顺序把寻蜜鸟的图、商家二维码、桌贴大图拼成一张图
	 * 创建人： lifeng
	 * 创建时间：2016年8月15日下午6:02:19
	 * @param sllerMatrix
	 * @param width 中间二维码的宽度
	 * @param height 中间二维码的高度
	 * @param response
	 * @param request
	 * @param type 桌贴类型：0代表门贴，1为桌贴1,2代表桌贴2
	 * @throws IOException
	 * @throws WriterException
	 */
	private  void getMatrixUtil(SellerMatrix sellerMatrix, int width, int height,  
			HttpServletResponse response,HttpServletRequest request, int type) throws IOException, WriterException{
		StringBuffer text=new StringBuffer(WXAPI_URL);
		text.append("?").append("sellerid=").append(replaceString(sellerMatrix.getSellerid()))
			.append("&sellername=").append(replaceString(sellerMatrix.getSellername()))
			.append("&aid=").append(replaceString(sellerMatrix.getAid()));
		MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		hints.put(EncodeHintType.MARGIN, 1);
		BitMatrix bitMatrix = multiFormatWriter.encode(text.toString(), BarcodeFormat.QR_CODE, width, height, hints);
		String imgName=sellerMatrix.getSellerid()+"-"+sellerMatrix.getSellername();
		
		BufferedImage bi = MatrixToImageWriter.toBufferedImage(bitMatrix);
		sellerMatrix.setSellername(sellerMatrix.getSellername().replaceAll(" ", ""));
		MixturePic.drawImage(bi,response,sellerMatrix,type);
		if(response != null && request != null){
			response.setContentType("image/jpeg");
			response.setHeader("Content-Disposition","attachment; filename="+ getDisponsition(request,imgName));
		}
	}
	
	
	
	//查看门贴
	/*@RequestMapping(value = "getDoorMatrix")
	public void getDoorMatrix(SellerMatrix sllerMatrix, 
			HttpServletResponse response,HttpServletRequest request) throws IOException, WriterException{
		getMatrixUtil(sllerMatrix, 250, 250, response, request, 0);
	}*/
	//查看桌贴1
	/*@RequestMapping(value = "getTableMatrix1")
	public void getTableMatrix1(SellerMatrix sllerMatrix,  
			HttpServletResponse response,HttpServletRequest request) throws IOException, WriterException{
		getMatrixUtil(sllerMatrix, 270, 270, response, request, 1);
	}*/
	//查看桌贴2
	/*@RequestMapping(value = "getTableMatrix2")
	public void getTableMatrix2(SellerMatrix sllerMatrix,  
			HttpServletResponse response,HttpServletRequest request) throws IOException, WriterException{
		getMatrixUtil(sllerMatrix, 270, 270, response, request, 2);
	}*/
	
	/*
	 * 去掉'&'和'  '字符  
	 */
	public String replaceString(String oldStr){
		if(oldStr == null){
			return "";
		}
		String newStr="";
		newStr = oldStr.replace("&", "");
		newStr = newStr.replace("=", "");
		newStr = newStr.replace(" ", "");
		return newStr;
	}
	
	private String getDisponsition(HttpServletRequest request,String fileName){
		try{
		 String userAgent = request.getHeader("User-Agent");  
	     byte[] bytes = userAgent.contains("MSIE") ? fileName.getBytes("GB2312") : fileName.getBytes("UTF-8"); // name.getBytes("UTF-8")处理safari的乱码问题  
	     fileName = new String(bytes, "ISO-8859-1"); // 各浏览器基本都支持ISO编码  
		}catch(Exception e){
			e.printStackTrace();
		}
	     return String.format("\"%s\"", fileName); // 文件名外的双引号处理firefox的空格截断问题  
	}
}
