package com.xmniao.xmn.core.api.controller.common;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.log4j.Logger;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.StorageClient1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.base.UploadClientFactory;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.util.PropertiesUtil;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

@Controller
public class FileUploadApi {
	/**
	 * 初始日志类
	 */
	private final Logger log = Logger.getLogger(FileUploadApi.class);

	/**
	 * FastDfs客户端工厂
	 */
	@Autowired
	private UploadClientFactory uploadClient;

	/**
	 * 验证
	 */
	@Autowired
	private Validator validator;

	/**
	 * 注入缓存
	 */
	@Autowired
	private SessionTokenService sessionTokenService;

	/**
	 * 注入会话令牌SESERVICE
	 */
	@Resource(name = "fileUrl")
	private String fileUrl;

	@Resource
	private PropertiesUtil propertiesUtil;
	/**
	 * 
	 * @param text
	 * @return
	 */
	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Object RequestHandle(BaseRequest baseRequest, HttpServletRequest request) {
		List<ConstraintViolation> result = validator.validate(baseRequest);
		if (result.size() > 0) {
			log.info("提交的数据有问题");
			return new BaseResponse(ResponseCode.DATAERR, "提交的数据有问题");
		}
		String token = baseRequest.getSessiontoken();
		if (StringUtils.isEmpty(token)) {
			return new BaseResponse(ResponseCode.TOKENERR, "无效token");
		}
		StorageClient1 client = null;
		byte[] bre_img = new byte[4096]; // 字节数组
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultiValueMap<String, MultipartFile> MultiMap = multipartRequest.getMultiFileMap();
			// 检查是否有图片数据
			log.info("================================================" + MultiMap.size());
			if (MultiMap.size() < 1) {
				return new BaseResponse(ResponseCode.DATAERR, "无图片数据！");
			}
			// 获取MultipartFile对象
			List<MultipartFile> MultiList = new ArrayList<MultipartFile>();
			for (Entry<String, List<MultipartFile>> entry : MultiMap.entrySet()) {
				MultiList = entry.getValue();
			}
			List<Map<Object, Object>> imgList = new ArrayList<Map<Object, Object>>();
			for (MultipartFile mf : MultiList) {
				MultipartFile file = mf;
				if (!file.getContentType().contains("image")) {
					continue;
				}

				if (file.getSize() <= 0 || file.getBytes().length <= 0) {
					return new BaseResponse(ResponseCode.FAILURE, "上传文件异常,请重试");
				}
				String value = propertiesUtil.getValue("videoLimitSize", "conf_common.properties");
				Long videoLimitSize = Long.parseLong(value);

				// 获取连接
				client = uploadClient.getStorageClients();
				String remark = file.getOriginalFilename();
				String fileExtName = "jpg";
				if (remark.indexOf(".") != -1) {
					fileExtName = remark.substring(remark.lastIndexOf(".") + 1, remark.length());
				}
				String fileName = DigestUtils.md5Hex(remark + System.currentTimeMillis()) + "";
				NameValuePair[] metaList = new NameValuePair[3];
				metaList[0] = new NameValuePair("fileName", fileName);
				metaList[1] = new NameValuePair("fileExtName", fileExtName);
				metaList[2] = new NameValuePair("fileLength", String.valueOf(file.getSize()));

				String fileurl = client.upload_file1(file.getBytes(), fileExtName, metaList);
				String breurl = "";
				// 判断图片大小
				/*
				 * if(file.getSize()>102400){
				 * log.info("fileUrl+fileurl="+fileUrl+fileurl); bre_img =
				 * cut(fileUrl+fileurl); if(null!=bre_img){ NameValuePair[]
				 * bremetaList = new NameValuePair[3]; bremetaList[0] = new
				 * NameValuePair("fileName", fileName+"_bre"); bremetaList[1] =
				 * new NameValuePair("fileExtName", fileExtName); bremetaList[2]
				 * = new NameValuePair("fileLength",
				 * String.valueOf(bre_img.length)); breurl =
				 * client.upload_file1(bre_img,fileExtName, metaList);
				 * 
				 * } }
				 */
				if (fileurl != "") {
					Map<Object, Object> picmap = new HashMap<Object, Object>();
					picmap.put("fileurl", fileurl);
					picmap.put("remark", remark);
					// picmap.put("breurl", breurl);
					picmap.put("picurl", fileUrl + fileurl);
					imgList.add(picmap);
				}
			}
			MapResponse mapResponse = new MapResponse(ResponseCode.SUCCESS, "上传成功");
			Map<Object, Object> response = new HashMap<Object, Object>();
			response.put("pics", imgList);
			mapResponse.setResponse(response);
			return mapResponse;
		} catch (Exception e) {
			log.error("上传图片异常!", e);
			return new BaseResponse(ResponseCode.FAILURE, "上传图片失败");
		} finally {
			if (client != null) {
				uploadClient.closeConnect();
			}
		}
	}

	public byte[] cut(String srcImageFile) {
		try {
			byte[] b = new byte[4096];
			URL url = new URL(srcImageFile);
			BufferedImage bi = ImageIO.read(url);
			System.out.println(url);
			int srcWidth = bi.getWidth();
			int srcHeight = bi.getHeight();
			if (srcWidth > 0 && srcHeight > 0 && (srcWidth > 240 || srcHeight > 160)) {
				Image img = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				BufferedImage tag = new BufferedImage(240, 160, BufferedImage.TYPE_INT_RGB);
				Graphics g = tag.getGraphics();
				g.drawImage(img, 0, 0, 240, 160, null);
				g.dispose();
				ImageIO.write(tag, "JPEG", out);
				b = out.toByteArray();
				return b;
			} else {
				return null;
			}

		} catch (Exception e) {
			log.error("缩略图片错误", e);
			return null;
		}
	}

	@RequestMapping(value = "/videoUpload", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Object videoUpload(BaseRequest baseRequest, HttpServletRequest request) {

		List<ConstraintViolation> result = validator.validate(baseRequest);
		if (result.size() > 0) {
			log.info("提交的数据有问题");
			return new BaseResponse(ResponseCode.DATAERR, "提交的数据有问题");
		}
		String token = baseRequest.getSessiontoken();
		if (StringUtils.isEmpty(token)) {
			return new BaseResponse(ResponseCode.TOKENERR, "无效token");
		}
		
		
		StorageClient1 client = null;
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultiValueMap<String, MultipartFile> MultiMap = multipartRequest.getMultiFileMap();
			// 检查是否有文件数据
			log.info("================================================" + MultiMap.size());
			if (MultiMap.size() < 1) {
				return new BaseResponse(ResponseCode.DATAERR, "无视频数据！");
			}
			// 获取MultipartFile对象
			List<MultipartFile> MultiList = new ArrayList<MultipartFile>();
			for (Entry<String, List<MultipartFile>> entry : MultiMap.entrySet()) {
				MultiList = entry.getValue();
			}
			List<Map<Object, Object>> fileList = new ArrayList<Map<Object, Object>>();
			for (MultipartFile file : MultiList) {
				if (!file.getContentType().contains("video") && !file.getContentType().contains("image")) {
					continue;
				}
				String originalName = file.getOriginalFilename();
				
				Long videoLimitSize = Long.parseLong(propertiesUtil.getValue("videoLimitSize", "conf_common.properties"));

				if (file.getSize() <= 0 || file.getBytes().length <= 0) {
					log.info("文件大小异常：filename=" + originalName+",大小为:"+file.getSize()+"字节");
					return new BaseResponse(ResponseCode.FAILURE, originalName+"文件无数据！");
				}
				
				if (file.getSize() > videoLimitSize) {
					log.info("文件大小超出限制：filename=" + originalName +",大小为:"+file.getSize()+"字节");
					return new BaseResponse(ResponseCode.FAILURE, originalName+"文件太大啦！");
				}

				// 获取连接
				client = uploadClient.getStorageClients();
				
				int index = originalName.lastIndexOf(".");
				if (index == -1) {
					log.info("获取文件后缀名失败：filename=" + originalName);
					return new BaseResponse(ResponseCode.FAILURE, originalName+"文件不是媒体文件哦！");
				}
				String fileExtName = originalName.substring(index + 1, originalName.length());
				if(file.getContentType().contains("video") && !"mp4".equals(fileExtName)){
					log.info("文件格式异常：filename=" + originalName );
					return new BaseResponse(ResponseCode.FAILURE, originalName+"不是MP4文件！");
				}
				String fileName = DigestUtils.md5Hex(originalName + System.currentTimeMillis()) + "";
				NameValuePair[] metaList = new NameValuePair[3];
				metaList[0] = new NameValuePair("fileName", fileName);
				metaList[1] = new NameValuePair("fileExtName", fileExtName);
				metaList[2] = new NameValuePair("fileLength", String.valueOf(file.getSize()));

				String fileurl = client.upload_file1(file.getBytes(), fileExtName, metaList);
				if (fileurl != "") {
					Map<Object, Object> picmap = new HashMap<Object, Object>();
					picmap.put("fileurl", fileurl);
					picmap.put("originalName", originalName);
					picmap.put("picurl", fileUrl + fileurl);
					fileList.add(picmap);
				}
			}
			MapResponse mapResponse = new MapResponse(ResponseCode.SUCCESS, "上传成功");
			Map<Object, Object> response = new HashMap<Object, Object>();
			response.put("fileList", fileList);
			mapResponse.setResponse(response);
			return mapResponse;
		} catch (Exception e) {
			log.error("上传文件异常!", e);
			return new BaseResponse(ResponseCode.FAILURE, "文件上传失败");
		} finally {
			if (client != null) {
				uploadClient.closeConnect();
			}
		}
	}
}
