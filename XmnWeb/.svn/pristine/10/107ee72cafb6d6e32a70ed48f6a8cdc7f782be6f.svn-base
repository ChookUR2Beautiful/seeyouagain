/**
 * 
 */
package com.xmniao.xmn.core.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.sun.istack.internal.logging.Logger;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：FileUpOrDownLoadUtil
 * 
 * 类描述： 文件上传或者下载的工具类
 * 
 * 创建人： lifeng
 * 
 * 创建时间：2016年8月16日 下午6:23:28
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */

public class FileUpOrDownLoadUtil {

	private static Logger log = Logger.getLogger(FileUpOrDownLoadUtil.class);

	/**
	 * 方法描述：把指定文件夹压缩到指定的目录
	 * 创建人： lifeng
	 * 创建时间：2016年8月16日下午7:07:14
	 * @param inputFile "F:\\源文件夹"
	 * @param zipFileName "F:/ziptest.zip"
	 * @param base 解压后的文件夹的名称，如果为"",则解压后为零散的文件
	 */
	public static void zip(File inputFile, String zipFileName,String base) {
		try {
			// 创建文件输出对象out,提示:注意中文支持
			FileOutputStream out = new FileOutputStream(new String(
					zipFileName.getBytes()));
			// 將文件輸出ZIP输出流接起来
			ZipOutputStream zOut = new ZipOutputStream(out);
			log.info("压缩-->开始");
			zip(zOut, inputFile, base);
			log.info("压缩-->结束");
			zOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 方法描述：把指定文件夹压缩到一个输出流
	 * 创建人： lifeng
	 * 创建时间：2016年8月18日上午9:49:09
	 * @param inputFile 文件夹或者文件
	 * @param outputStream 输出流
	 * @param base 解压后的文件夹的名称，如果为"",则解压后为零散的文件
	 */
	public static void zipToOutputStream(File inputFile, OutputStream outputStream,String base) {
		try {
			ZipOutputStream zOut = new ZipOutputStream(outputStream);
			log.info("压缩-->开始");
			zip(zOut, inputFile, base);
			log.info("压缩-->结束");
			zOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void zip(ZipOutputStream zOut, File file, String base) {
		try {
			// 如果文件句柄是目录
			if (file.isDirectory()) {
				// 获取目录下的文件
				File[] listFiles = file.listFiles();
				// 建立ZIP条目
				zOut.putNextEntry(new ZipEntry(base + "/"));
				log.info("目录名:" + file.getName() + "|加入ZIP条目:" + base + "/");
				base = (base.length() == 0 ? "" : base + "/");
				// 遍历目录下文件
				for (int i = 0; i < listFiles.length; i++) {
					// 递归进入本方法
					zip(zOut, listFiles[i], base + listFiles[i].getName());
				}
			}
			// 如果文件句柄是文件
			else {
				if (base == "") {
					base = file.getName();
				}
				// 填入文件句柄
				zOut.putNextEntry(new ZipEntry(base));
				log.info("文件名:" + file.getName() + "|加入ZIP条目:" + base);
				// 开始压缩
				// 从文件入流读,写入ZIP 出流
				writeFile(zOut, file);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private static void writeFile(ZipOutputStream zOut, File file)
			throws IOException {
		log.info("开始压缩" + file.getName());
		log.info(file.getPath());
		FileInputStream in = new FileInputStream(file);
		BufferedInputStream bufIn = new BufferedInputStream(in);
		byte[] buf = new byte[bufIn.available()];
		bufIn.read(buf);
		zOut.write(buf);
		log.info("压缩结束" + file.getName());
		bufIn.close();
	}
	
	//测试压缩
	public static void main(String[] args) {
		zip(new File("F:\\压缩测试原来的"),
				"F:/zidj价st.zip","iiidid");
	}
	
	
	/**
	 * 
	 * 方法描述：文件转字节
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月26日下午4:22:06 <br/>
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static byte[] getContent(File file) throws IOException {
        long fileSize = file.length();
        if (fileSize > Integer.MAX_VALUE) {
            System.out.println("file too big...");
            return null;
        }
        FileInputStream fi = new FileInputStream(file);
        byte[] buffer = new byte[(int) fileSize];
        int offset = 0;
        int numRead = 0;
        while (offset < buffer.length
        && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
            offset += numRead;
        }
        // 确保所有数据均被读取
        if (offset != buffer.length) {
        throw new IOException("Could not completely read file "
                    + file.getName());
        }
        fi.close();
        return buffer;
    }
	
}
