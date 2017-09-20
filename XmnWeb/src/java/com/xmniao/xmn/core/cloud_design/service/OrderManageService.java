/**
 * 
 */
package com.xmniao.xmn.core.cloud_design.service;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.xmniao.xmn.core.cloud_design.dao.OrderManageDao;
import com.xmniao.xmn.core.cloud_design.entity.AfterSale;
import com.xmniao.xmn.core.cloud_design.entity.CommonMaterial;
import com.xmniao.xmn.core.cloud_design.entity.CommonMaterialPics;
import com.xmniao.xmn.core.cloud_design.entity.MaterialGroup;
import com.xmniao.xmn.core.cloud_design.entity.MaterialOrder;
import com.xmniao.xmn.core.util.FileUpOrDownLoadUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：OrderManageService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2016年11月18日 下午4:11:39
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class OrderManageService {

	@Autowired
	private OrderManageDao orderManageDao;
	
	// 初始化日志类
	private final Logger log = Logger.getLogger(OrderManageService.class);
	
	private static String baseUrl=PropertiesUtil.readValue("file.upload.fastDFS.http");//图片服务器域名

	/**
	 * 
	 * 方法描述：获取物料订单列表 创建人： chenJie <br/>
	 * 创建时间：2016年11月18日下午4:51:09 <br/>
	 * 
	 * @param materialOrder
	 * @return
	 */
	public List<MaterialOrder> getList(MaterialOrder materialOrder) {
		return orderManageDao.getList(materialOrder);
	}

	/**
	 * 
	 * 方法描述：统计订单总数 创建人： chenJie <br/>
	 * 创建时间：2016年11月18日下午4:50:55 <br/>
	 * 
	 * @param materialOrder
	 * @return
	 */
	public long count(MaterialOrder materialOrder) {
		return orderManageDao.count(materialOrder);
	}

	/**
	 * 
	 * 方法描述：更新订单总金额 创建人： chenJie <br/>
	 * 创建时间：2016年11月24日下午2:43:16 <br/>
	 * 
	 * @param materialOrder
	 * @return
	 */
	public boolean updatePrice(MaterialOrder materialOrder) {
		MaterialOrder order = getList(materialOrder).get(0);
		materialOrder.setVersion(order.getVersion());// 设置版本号
		if ("002".equals(materialOrder.getType())) {
			Integer result1 = orderManageDao
					.updateCustomizeMaterial(materialOrder.getOrderNo());// 更新定制订单定制状态
			if (result1 != 1) {
				log.error("更新定制订单定制状态失败" + materialOrder.getOrderNo());
				return false;
			}
		}
		Integer update = orderManageDao.update(materialOrder);
		if (update == 1) {
			log.info("修改订单价格成功" + materialOrder.getOrderNo());
			return true;
		} else {
			log.error("修改订单价格失败" + materialOrder.getOrderNo());
			return false;
		}
	}

	/**
	 * 
	 * 方法描述：删除已关闭订单 创建人： chenJie <br/>
	 * 创建时间：2016年11月24日下午9:19:19 <br/>
	 * 
	 * @param materialOrder
	 */
	public Integer delete(MaterialOrder materialOrder) {
		return orderManageDao.delete(materialOrder);
	}

	/**
	 * 
	 * 方法描述:发货 创建人： chenJie <br/>
	 * 创建时间：2016年12月1日下午5:05:13 <br/>
	 * 
	 * @param materialOrder
	 */
	public Integer deliver(MaterialOrder materialOrder) {
		return orderManageDao.deliverUpdate(materialOrder);
	}
	
	/**
	 * 
	 * 方法描述：操作售后
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月2日上午10:59:26 <br/>
	 * @param afterSale
	 */
	public Boolean addAfterSale(AfterSale afterSale){
		MaterialOrder materialOrder = new MaterialOrder();
		materialOrder.setOrderNo(afterSale.getOrderNo());
		materialOrder.setStatus(7);
		Integer result = orderManageDao.updateOrderStatus(materialOrder);	//更新订单状态为售后状态
			if(result !=1){
				return false;
			}
		afterSale.setCreateTime(new Date());//创建时间
		afterSale.setUpdateTime(new Date());//更新时间
		orderManageDao.addAfterSale(afterSale);//插入售后记录
		
		return true;
		
	}
	
	/**
	 * 
	 * 方法描述：更新售后记录
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月2日上午11:00:21 <br/>
	 * @param afterSale
	 * @return
	 */
	public Integer updateAfterSale(AfterSale afterSale){
		afterSale.setUpdateTime(new Date());
		return orderManageDao.updateAfterSale(afterSale);
	}
	
	/**
	 * 
	 * 方法描述：根据订单号获取订单记录
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月2日下午2:05:42 <br/>
	 * @param orderNo
	 * @return
	 */
	public AfterSale getAfterSale(String orderNo){
		return orderManageDao.getAfterSale(orderNo);
	}
	
	public String formatDate(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	
	/**
	 * 
	 * 方法描述：更新订单为代发货
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月6日下午4:21:18 <br/>
	 * @param materialOrder
	 * @return
	 */
	public Integer updateOrderStatus(MaterialOrder materialOrder){
		return orderManageDao.updateOrderStatus(materialOrder);
	}
	
	/**
	 * 
	 * 方法描述：获取订单物料详细信息
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月5日下午3:56:31 <br/>
	 * @param materialOrder
	 * @return
	 */
	public CommonMaterial getCommonMaterial(MaterialOrder materialOrder){
		CommonMaterial commonMaterial = orderManageDao.getCommonMaterial(materialOrder.getOrderNo());
		List<MaterialGroup> commonUrls = orderManageDao.getCommonUrls(materialOrder.getOrderNo());
		for (MaterialGroup materialGroup : commonUrls) {
			List<CommonMaterialPics> picUrls = orderManageDao.getPicUrls(materialGroup.getId());
			materialGroup.setPics(picUrls);
		}
		commonMaterial.setMgroup(commonUrls);
		return commonMaterial;
	}
	
	/**
	 * 
	 * 方法描述：获取定制订单物料详细信息
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月5日下午3:56:31 <br/>
	 * @param materialOrder
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public CommonMaterial getCustomizeMaterial(MaterialOrder materialOrder) throws Exception{
		CommonMaterial customizeMaterial = orderManageDao.getCustomizeMaterial(materialOrder.getOrderNo());
		List<HashMap> list = JSON.parseArray(customizeMaterial.getJsonString(),HashMap.class);
		for (HashMap map : list) {
			if("预算".equals(map.get("name")+"")){
				customizeMaterial.setBudget(getString(map.get("vals")+""));//预算
			}
			if("类型".equals(map.get("name")+"")){
				customizeMaterial.setMaterialType(getString(map.get("vals")+""));//类型
			}
			if("主色调".equals(map.get("name")+"")){
				customizeMaterial.setMainColor(getString(map.get("vals")+""));//主色调
			}
			if("副色调".equals(map.get("name")+"")){
				customizeMaterial.setSecColor(getString(map.get("vals")+""));//副色调
			}
			
		}
		List<String> customizeUrls = orderManageDao.getCustomizeUrls(materialOrder.getOrderNo());
		customizeMaterial.setCustomizeUrls(customizeUrls);
		return customizeMaterial;
	}
	
	private static String getString(String vals){
		if(vals.length()<=4){
			return "";
		}else {
			return vals.replaceAll("\"","").replace('[',' ').replace(']',' ');
		}
	}
	/**
	 * 
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月13日下午2:39:36 <br/>
	 * @param orderNo
	 * @throws Exception 
	 */
	public void jointPic(String orderNo) throws Exception{
		
		log.info("物料订单合成图片jointPic"+orderNo);
		try {
			String path = this.getClass().getClassLoader().getResource("").getPath();
			String filePath = new StringBuilder().append(
					path.substring(0, path.lastIndexOf("WEB-INF"))).append(
					"resources/stickFold").toString();
			// 获取相册
			List<MaterialGroup> commonUrls = orderManageDao.getCommonUrls(orderNo);
			//循环获取相册的图片
			for (MaterialGroup materialGroup : commonUrls) {
				List<CommonMaterialPics> picUrls = orderManageDao.getPicUrls(materialGroup.getId());
				materialGroup.setPics(picUrls);
			}
			String ctm = System.currentTimeMillis()+"";
			File baseFile = new File(filePath+"/"+ctm);
			baseFile.mkdir();
			drawImage(commonUrls,filePath+"/"+ctm);
			FileUpOrDownLoadUtil.zip(baseFile,filePath+"/"+ctm+"_1.zip","materialPic");//压缩文件 
			
			String uploadPath = fileUpload(new File(filePath+"/"+ctm+"_1.zip"));//上传
			
			/**
			 * 把初稿路径写入订单
			 */
			MaterialOrder materialOrder = new MaterialOrder();
			materialOrder.setOrderNo(orderNo);
			materialOrder.setStartUrl(uploadPath);
			Integer result = orderManageDao.updateStartUrl(materialOrder);
			if(result != 1){
				log.error("初稿路径写入订单失败");
				throw new RuntimeException();
			}
		} catch (Exception e) {
			log.error("合成图片异常",e);
			throw new RuntimeException();
		}
	}
	
	/**
	 * 
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月5日下午8:49:48 <br/>
	 * @param bi
	 * @param ops
	 * @param mainPicUrl 主图路径
	 */
	public void drawImage(List<MaterialGroup> commonUrls,String filePath) {
		
		
		
		InputStream is = null;
		FileOutputStream ops=null;
		String mainUrl=null;//主图路径
		try {
			for (MaterialGroup mGroup : commonUrls) {
				String mainSize = null;
				for (CommonMaterialPics pics : mGroup.getPics()) {
					if("001".equals(pics.getType())){//类型为图片
						if("003".equals(pics.getImgType())){//主图
							mainUrl=pics.getPicUrl();
							mainSize=pics.getImgSize();
							System.out.println(pics);
						}
					}
				}
				
				//是否存在主图
				if(mainUrl == null){
					log.error("主图不存在，无法合成图片");
					throw new RuntimeException("缺少主图");
				}
				
				//是否存在主图
				if(mainSize == null || mainSize.length()<3){
					log.error("主图大小未正确设置"+mainSize);
					throw new RuntimeException("主图大小未正确设置");
				}
				String[] picSize = mainSize.split("X");
				
				int mwidth = Integer.valueOf(picSize[0]);//宽
				int mheigh = Integer.valueOf(picSize[1]);//高
				BufferedImage buffImg = new BufferedImage(mwidth,mheigh,BufferedImage.TYPE_INT_RGB);
				Graphics g = buffImg.getGraphics();//得到画笔对象
				g.drawImage(null, mwidth,mheigh,null);
				
				URL url = new URL(baseUrl+mainUrl);
				URLConnection con = url.openConnection();
				
				InputStream inputStream = con.getInputStream();
				
				BufferedImage mainImage = ImageIO.read(inputStream);
				
			/*	BufferedImage buffImg = new BufferedImage(mainImage.getWidth(),mainImage.getHeight(),BufferedImage.TYPE_INT_RGB);
				Graphics g = buffImg.getGraphics();//得到画笔对象
				g.drawImage(null, mwidth,mheigh,null);*/
				
				g.drawImage(mainImage,0,0,mwidth,mheigh,null);
						
				/*File baseFile = new File(baseUrl+mainUrl);
				BufferedImage buffImg = ImageIO.read(baseFile);*/
				
//				Graphics g = buffImg.getGraphics();//得到画笔对象
				
				for (CommonMaterialPics pics : mGroup.getPics()) {
					if("001".equals(pics.getType())){
						if("002".equals(pics.getImgType())){//贴图
							String[] indexs = pics.getImgCoordinate().split(",");//写入图片位置
							String[] indexs2 = pics.getImgSize().split(",");//写入图片的大小
							if(indexs.length<2){
								log.error("图片坐标异常");
								throw new RuntimeException();
							}
							
							int x = Integer.valueOf(indexs[0]);//x坐标
							int y = Integer.valueOf(indexs[1]);//y坐标
							
							int width = Integer.valueOf(indexs2[0]);//宽
							int heigh = Integer.valueOf(indexs2[1]);//高
							String secUrl = pics.getPicUrl();
							
							if(secUrl !=null){
								BufferedImage bi = ImageIO.read(new URL(baseUrl+secUrl).openConnection().getInputStream());
								
								g.drawImage(bi, x, y,width,heigh, null);
							}
						}
					}
				}
				
				for (CommonMaterialPics pics : mGroup.getPics()) {
					if("002".equals(pics.getType())){//类型为文字
						
						String fontSize = pics.getFontSize();//字体大小
						String fontText = pics.getFontText();//文字内容
						String[] split = pics.getFontCoordinate().split(",");
						
						int x = Integer.valueOf(split[0]);//x坐标
						int y = Integer.valueOf(split[1]);//y坐标
						
						Font font = new Font("微软雅黑",Font.BOLD,Integer.valueOf(fontSize));
						
						g.setColor(Color.BLACK);
						g.setFont(font);
						g.drawString(fontText, x, y);
					}
				}
				ops = new FileOutputStream(new File(filePath+"/"+System.currentTimeMillis()+".png"));
				// 创键编码器，用于编码内存中的图象数据。
				JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(ops);
				en.encode(buffImg);
				
				g.dispose();
				ops.close();
			}
			
		} catch (Exception e) {
			log.error("合成图片异常",e);
			throw new RuntimeException();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (Exception e) {
				log.error("关闭流异常",e);
				throw new RuntimeException();
			}
		}
	}
	
	/**
	 * 
	 * 方法描述：模拟表单提交上传文件
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月13日下午7:59:15 <br/>
	 * @throws Exception 
	 */
	public String fileUpload(File file) throws Exception{
		PostMethod filePost = new PostMethod("http://localhost:8080/XmnWeb/uploadFile.jhtml?sltflag=false");
		Part[] part={new FilePart("filedata", file)};
		filePost.setRequestEntity(new MultipartRequestEntity(part,filePost.getParams()));
		HttpClient client = new HttpClient();
		int status = client.executeMethod(filePost);
		BufferedReader bf = new BufferedReader(new InputStreamReader(filePost.getResponseBodyAsStream(),"UTF-8"));
		StringBuffer stringBuffer = new StringBuffer();
		String line;
	     while ((line = bf.readLine()) != null) {
	         stringBuffer .append(line);   
	  } 
	     bf.close();
	     
	     System.out.println("接受到的流是：" + stringBuffer + "—-" + status);
	     
	     HashMap map = JSON.parseObject(stringBuffer.toString(),HashMap.class);
	     
	     return map.get("relativePath").toString();
	}
	
	
	/**
	 * 
	 * 方法描述：更新设计信息
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月6日下午4:46:24 <br/>
	 * @param materialOrder
	 * @return
	 */
	public Integer saveMaterialPic(MaterialOrder materialOrder){
		return orderManageDao.saveMaterialPic(materialOrder);
	}
	
	
}
