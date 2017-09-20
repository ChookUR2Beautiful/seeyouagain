package com.xmniao.xmn.core.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.media.imageio.plugins.tiff.TIFFTag;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageEncoder;
import com.sun.media.jai.codec.TIFFEncodeParam;
import com.sun.media.jai.codec.TIFFField;
import com.sun.media.jai.codecimpl.TIFFImageEncoder;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：ResizeImage
 * 
 * 类描述： 缩放图片大小
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2016年8月24日 下午3:57:46 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public class ResizeImage {
	 private Image   srcImage    = null;  
     private File    srcFile  = null;  
     private File    destFile    = null;  
     private String  fileSuffix  = null;  
       
     private int imageWidth = 0;  
     private int imageHeight = 0;  

     public ResizeImage(String fileName) throws IOException {  
         this(new File(fileName));  
     }  
     
     public ResizeImage(File fileName) throws IOException {  
         File _file = fileName;  
//         _file.setReadOnly();  
         this.srcFile = _file;  
         this.fileSuffix = _file.getName().substring(  
                 (_file.getName().indexOf(".") + 1),  
                 (_file.getName().length()));  
         this.destFile = new File(this.srcFile.getPath().substring(0,  
                 (this.srcFile.getPath().lastIndexOf(".")))  
                 + "." + this.fileSuffix);  
         srcImage = javax.imageio.ImageIO.read(_file);  
         //得到图片的原始大小， 以便按比例压缩。  
         imageWidth = srcImage.getWidth(null);  
         imageHeight = srcImage.getHeight(null);  
      /*   System.out.println("width: " + imageWidth);  
         System.out.println("height: " + imageHeight);      */   
     }  

     /** 
      * 强制压缩/放大图片到固定的大小 (按比例)
      * @param w int 新宽度 
      * @param h int 新高度 
      * @throws IOException 
      */  
    /* public BufferedImage resize(int w, int h) throws IOException {  
         //得到合适的压缩大小，按比例。  
         if ( imageWidth >= imageHeight)  
         {  
             w = w;  
             h = (int)Math.round((imageHeight * w * 1.0 / imageWidth));  
         }  
         else   
         {  
             h = h;  
             w = (int)Math.round((imageWidth * h * 1.0 / imageHeight));  
         }  

         //构建图片对象  
         BufferedImage _image = new BufferedImage(w, h,  
                 BufferedImage.TYPE_INT_RGB);  
         //绘制缩小后的图  
         _image.getGraphics().drawImage(srcImage, 0, 0, w, h, null);  
         //输出到文件流  
         String fileName = RandomUtils.nextInt(1000)+"";
         String str = "E://"+fileName+".jpg";
         FileOutputStream out = new FileOutputStream(destFile);  
         JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
         encoder.encode(_image);  
         out.flush();  
         out.close();  
         return _image;
     }  */
     
     /** 
      * 
      * 强制压缩/放大图片到固定的大小 
      * @param w int 新宽度 
      * @param h int 新高度 
      * @throws IOException 
      */  
     public BufferedImage resize(int w, int h) throws IOException {  
         //得到合适的压缩大小，按比例。  
        
         //构建图片对象  
         BufferedImage _image = new BufferedImage(w, h,  
                 BufferedImage.TYPE_INT_RGB);  
         //绘制缩小后的图  
         _image.getGraphics().drawImage(srcImage, 0, 0, w, h, null);  
         //输出到文件流  
       /*  String fileName = RandomUtils.nextInt(1000)+"";
         String str = "E://"+fileName+".jpg";*/
         FileOutputStream out = new FileOutputStream(destFile);  
         JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
         encoder.encode(_image);  
         out.flush();  
         out.close();  
         return changeDPI(destFile, _image);
     }  
     
     /**
 	 * 
 	 * 方法描述：修改图片的dpi(水平和处置分辨率)
 	 * 创建人： chenJie
 	 * 创建时间：2016年8月30日下午3:26:28
 	 * @throws IOException 
 	 */
 	public static BufferedImage changeDPI(File file ,BufferedImage buffImg) throws IOException{
 		int DPI = 150;
 		FileOutputStream fileOutputStream = new FileOutputStream(file);
 		TIFFEncodeParam param = new TIFFEncodeParam();
 		param.setCompression(TIFFEncodeParam.COMPRESSION_NONE);
 		TIFFField[] extras = new TIFFField[2];
 		extras[0] = new TIFFField(282, TIFFTag.TIFF_RATIONAL, 1,
 				(Object) new long[][] { { (long) DPI, 1 }, { 0, 0 } });
 		extras[1] = new TIFFField(283, TIFFTag.TIFF_RATIONAL, 1,
 				(Object) new long[][] { { (long) DPI, 1 }, { 0, 0 } });
 		param.setExtraFields(extras);
 		TIFFImageEncoder encode = new TIFFImageEncoder(fileOutputStream, param);
 		encode.encode(buffImg);
 		fileOutputStream.flush();
 		fileOutputStream.close();
 		return buffImg;
 	}
 	
 	/**
 	 * 
 	 * 方法描述：tiff格式转jpg
 	 * 创建人： chenJie
 	 * 创建时间：2016年9月1日上午10:50:39
 	 * @param args
 	 * @throws Exception
 	 */
 	  public static void main(String[] args) throws Exception {  
 		  
 	        /* tif转换到jpg格式 */  
 	        String input2 = "d:/1472697169053_table4.tiff";  
 	        String output2 = "d:/a.jpg";  
 	        RenderedOp src2 = JAI.create("fileload", input2);  
 	        OutputStream os2 = new FileOutputStream(output2);  
// 	        JPEGEncodeParam param2 = new JPEGEncodeParam();  
 	        //指定格式类型，jpg 属于 JPEG 类型  
 	        ImageEncoder enc2 = ImageCodec.createImageEncoder("JPEG", os2, null);  
 	        enc2.encode(src2);  
 	        os2.close();  
 	        }
}
