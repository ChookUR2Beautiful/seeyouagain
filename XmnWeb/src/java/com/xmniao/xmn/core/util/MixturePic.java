package com.xmniao.xmn.core.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.aspectj.util.FileUtil;
import org.springframework.util.FileCopyUtils;

import sun.util.logging.resources.logging;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.xmniao.xmn.core.businessman.entity.SellerMatrix;
import com.xmniao.xmn.core.cloud_design.entity.CommonMaterialPics;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：MixturePic
 * 
 * 类描述： 贴图，将生成的二维码，合成到微信推广底图上
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年4月27日 上午10:53:17
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class MixturePic {

	private static final String PIC_PATH_ICON = "stickFold//base//icon.jpg";// 中间小鸟
	private static final String PIC_PATH_MATRIXBG = "stickFold//base//matrixbg.jpg";// 二维码背景图片
	private static final String PIC_PATH_DOOR_STICK = "stickFold//base//door.jpg";// 门贴背景
	private static final String PIC_PATH_TABLE_STICK_01 = "stickFold//base//table_01.jpg";// 桌贴背景1
	private static final String PIC_PATH_TABLE_STICK_02 = "stickFold//base//table_02.jpg";// 桌贴背景2
	private static final String PIC_PATH_DOOR_STICK_2 = "stickFold//base//door3.jpg";// 桌贴背景1
	private static final String PIC_PATH_TABLE_STICK_2 = "stickFold//base//table4.jpg";// 桌贴背景2
	public static final String PIC_PATH_STICK_FOLD = "stickFold";// 存放所有图片的文件夹
	private static final Color fontColor = new Color(68, 168, 56); 
	
	public static void drawImage(BufferedImage bi, OutputStream ops,String text) {
		InputStream is = null;
		try {
			// 1.jpg是你的 主图片的路径
			File baseFile = new File(getUnderlayPicPath("smzf.jpg"));
			BufferedImage buffImg = ImageIO.read(baseFile);
			// 得到画笔对象
			Graphics g = buffImg.getGraphics();
			
			// 将小图片绘到大图片上
			g.drawImage(bi, 412, 480, null);
			
			// //2.3在底图上写文字
			g.setColor(Color.black);
			Integer stringWidth=getFont(g,bi.getWidth(),text,112);
			g.drawString(text,(int) (908 - stringWidth / 2), 450);
			g.setColor(new Color(0, 0, 0));
			
			g.dispose();

			// 创键编码器，用于编码内存中的图象数据。
			JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(ops);
			en.encode(buffImg);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void drawImage(BufferedImage bi,
			HttpServletResponse response, SellerMatrix sellerMatrix, int type) {
		if (type == 0) {// 门贴
			int[][] iconParam = { { 1, 4 }, { 1, 4 } };// 最上面的小鸟的图片的缩放参数，表示横向缩小1/4,纵向缩小1/4
			int[] params = { 974, 1370 };
			drawImageUtil(bi, response, PIC_PATH_DOOR_STICK, sellerMatrix,
					iconParam, params);
		} else if (type == 1) {// 桌贴1
			int[][] iconParam = { { 1, 3 }, { 1, 3 } };// 最上面的小鸟的图片的缩放参数
			int[] params = { 1495, 974 };// 中间图片在底部图片上的偏移坐标1492,974
			drawImageUtil(bi, response, PIC_PATH_TABLE_STICK_01, sellerMatrix,
					iconParam, params);
		} else if (type == 2) {// 桌贴2
			int[][] iconParam = { { 1, 3 }, { 1, 3 } };// 最上面的小鸟的图片的缩放参数
			int[] params = { 1495, 974 };
			drawImageUtil(bi, response, PIC_PATH_TABLE_STICK_02, sellerMatrix,
					iconParam, params);
		} else if (type == 3) {// 桌贴1
			int[][] iconParam = { { 1, 2 }, { 1, 2 } };// 最上面的小鸟的图片的缩放参数
			int[] params = { 392, 340, 885, 335 };// 中间图片在底部图片上的偏移坐标1492,974
			drawImageUtil3(bi, response, PIC_PATH_DOOR_STICK_2, sellerMatrix,
					iconParam, params, false);
		} else if (type == 4) {// 桌贴2
			int[][] iconParam = { { 1, 2 }, { 1, 2 } };// 最上面的小鸟的图片的缩放参数
			int[] params = { 412, 450, 908, 450 };//依次为 二维码相对底图左上角X坐标、Y坐标以及文字相对底图左上角X坐标、Y坐标
			drawImageUtil2(bi, response, PIC_PATH_TABLE_STICK_2, sellerMatrix,
					iconParam, params, false);
		}
	}

	/**
	 * 方法描述：把一个文件夹压缩成zip并发送到浏览器下载 创建人： lifeng 创建时间：2016年8月16日下午6:16:11
	 * 
	 * @param request
	 * @param response
	 */
	public static void downLoadStick(SellerMatrix sellerMatrix,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// 1.获得商家标签图片的文件夹
		String sellerStickFoldStr = getUnderlayPicPath(PIC_PATH_STICK_FOLD)
				+ "//" + sellerMatrix.getSellername().replaceAll(" ", "") + "_"
				+ sellerMatrix.getSellerid();
		File fileDir = new File(sellerStickFoldStr);
		// 2.压缩该商家的标签的文件成"商家名称_商家id.zip"
		File file = new File(sellerStickFoldStr + ".zip");
		OutputStream toClient = new BufferedOutputStream(
				response.getOutputStream());
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ new String(file.getName().getBytes("UTF-8"), "ISO-8859-1"));
		FileUpOrDownLoadUtil
				.zipToOutputStream(
						fileDir,
						toClient,
						sellerMatrix.getSellername() + "_"
								+ sellerMatrix.getSellerid());
	}

	/**
	 * 方法描述：三张图片的叠加(最上面的为小鸟、中间为二维码、最下面为底图),在这里最上面的二维码中间的小鸟的图片的大小为192*192 创建人：
	 * lifeng 创建时间：2016年8月15日下午5:48:56
	 * 
	 * @param bi
	 *            商家传来的二维码图片
	 * @param ops
	 *            输出流
	 * @param path
	 *            最大图片的路径，这里指的是最下面的背景图片的路径
	 * @param params
	 *            [0] 最上面图片在中间图片中的偏移的x坐标，这里代表二维码中间小鸟的偏移x坐标
	 * @param params
	 *            [1] 最上面图片在中间图片中的偏移的y坐标，这里代表二维码中间小鸟的偏移y坐标
	 * @param params
	 *            [2] 中间图片（二维码）在底图上偏移的位置x坐标
	 * @param params
	 *            [3] 中间图片（二维码）在底图上偏移的位置y坐标
	 * @param params
	 *            [4] 中间图片上文字的大小
	 */
	private static void drawImageUtil(BufferedImage bi,
			HttpServletResponse response, String path,
			SellerMatrix sellerMatrix, int[][] iconParam, int[] params) {
		drawImageUtil(bi, response, path, sellerMatrix, iconParam, params, true);
	}

	private static void drawImageUtil(BufferedImage bi,
			HttpServletResponse response, String path,
			SellerMatrix sellerMatrix, int[][] iconParam, int[] params,
			boolean hasLogo) {
		InputStream is = null;
		try {

			// 1.把小鸟的图像画到二维码中间
			if (hasLogo) {
				File iconFile = new File(getUnderlayPicPath(PIC_PATH_ICON));
				BufferedImage iconBuffImg = changeImgSize(iconFile,
						iconParam[0], iconParam[1]);
				Graphics icon_g = bi.getGraphics();
				icon_g.drawImage(iconBuffImg,
						(bi.getWidth() - iconBuffImg.getWidth()) / 2,
						(bi.getHeight() - iconBuffImg.getHeight()) / 2, null);
				icon_g.dispose();
			}
			/**
			 * 2.把二维码的图片画到二维码背景图上，背景图比二维码图高度高一个商家的名字高度
			 */
			int w = bi.getWidth();// 二维码宽度
			int h = bi.getHeight();// 二维码高度
			// 定义文字的内容和大小（商家名字）
			Font font = new Font(sellerMatrix.getSellername(), Font.BOLD,
					h / 13);
			// 2.1创建二维码的背景图，背景图的高度比二维码的图高一个商家名字的高度
			File matrixbgFile = new File(getUnderlayPicPath(PIC_PATH_MATRIXBG));
			Image matrixbgImage = ImageIO.read(matrixbgFile);
			int width = matrixbgImage.getWidth(null);// 二维码底图原来的宽度
			int height = matrixbgImage.getHeight(null);// 二维码底图原来的高度
			// 改变宽度和高度后的二维码底图
			BufferedImage matrixbgImg = changeImgSize(matrixbgFile, new int[] {
					w, width }, new int[] { h + h / 32, height });
			Graphics matrixbgGraphics = matrixbgImg.getGraphics();
			// 2.2在底图上面画二维码
			matrixbgGraphics.drawImage(bi, 0, 0, w, h, null);
			// 2.3在底图上写文字
			matrixbgGraphics.setColor(Color.BLACK);
			matrixbgGraphics.setFont(font);
			FontRenderContext frc = matrixbgGraphics.getFontMetrics()
					.getFontRenderContext();
			Rectangle2D stringBounds = font.getStringBounds(
					sellerMatrix.getSellername(), frc);
			double snLen = stringBounds.getWidth();
			matrixbgGraphics.drawString(font.getName(),
					(int) ((w - snLen) / 2), h + h / 40);
			matrixbgGraphics.setColor(new Color(0, 0, 0));
			matrixbgGraphics.dispose();
			//
			// 3.jpg是你的 主图片的路径
			File baseFile = new File(getUnderlayPicPath(path));
			BufferedImage buffImg = ImageIO.read(baseFile);
			// 3.1得到画笔对象
			Graphics g = buffImg.getGraphics();
			// 3.2将小图片绘到大图片上。
			g.drawImage(matrixbgImg, params[0], params[1], null);
			// 3.3创键编码器，用于编码内存中的图象数据。
			if (response != null) {
				JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(response
						.getOutputStream());
				en.encode(buffImg);
			}

			// 保存到文件夹../resources/商家名称_商家id/图片名称
			String sellerStickFoldStr = getUnderlayPicPath(PIC_PATH_STICK_FOLD)
					+ "//" + sellerMatrix.getSellername() + "_"
					+ sellerMatrix.getSellerid();
			File sellerStickFold = new File(sellerStickFoldStr);
			if (!sellerStickFold.exists()) {
				sellerStickFold.mkdir();
			}
			if (sellerStickFold.list().length > 2) {// 如果文件夹中原来有图片则删除
				FileUtil.deleteContents(sellerStickFold);
			}
			File file = new File(sellerStickFoldStr + "//"
					+ System.currentTimeMillis() + "_" + baseFile.getName());
			file.createNewFile();
			FileUtils.copyInputStreamToFile(getImageStream(buffImg), file);// 把生成的二维码的图片保存到项目路径下名字为:商家名_贴纸名.jpg
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取底图路径
	 */
	public static String getUnderlayPicPath(String fileName) {
		String path = Thread.currentThread().getContextClassLoader()
				.getResource("").getPath();
		/*
		 * 测试结果 java Web项目： path =
		 * /D:/MyWork/Tomcat7/webapps/XmnWeb/WEB-INF/classes/
		 * 
		 * java项目： path =
		 * /D:/Workspaces/JavaSpaces/XmnWeb/WebRoot/WEB-INF/classes/
		 */
		StringBuilder filePath = new StringBuilder()
				.append(path.substring(0, path.indexOf("WEB-INF")))
				.append("resources/").append(fileName);
		return filePath.toString();
	}

	/**
	 * 方法描述：工具方法：改变一个图片文件的大小，按比例缩小 创建人： lifeng 创建时间：2016年8月15日下午5:47:46
	 * 
	 * @param file
	 * @param widthRate
	 *            图片宽度缩放比率，例如{1,2}表示图片缩小为原来的1/2
	 * @param heightRate
	 *            图片高度缩放比率，例如{1,2}表示图片缩小为原来的1/2
	 * @return BufferedImage
	 * @throws IOException
	 */
	public static BufferedImage changeImgSize(File file, int[] widthRate,
			int[] heightRate) throws IOException {
		Image image = ImageIO.read(file);
		int width = image.getWidth(null);
		int height = image.getHeight(null);
		BufferedImage bufferedImage = new BufferedImage((width * widthRate[0])
				/ widthRate[1], (height * heightRate[0]) / heightRate[1],
				BufferedImage.TYPE_INT_RGB);
		bufferedImage.getGraphics().drawImage(image, 0, 0,
				(width * widthRate[0]) / widthRate[1],
				(height * heightRate[0]) / heightRate[1], null);
		return bufferedImage;
	}

	/**
	 * 方法描述：工具方法：把bufferedImage转换成为InputStream 创建人： lifeng
	 * 创建时间：2016年8月16日下午6:16:29
	 * 
	 * @param bi
	 * @return
	 */
	private static InputStream getImageStream(BufferedImage bi) {
		InputStream is = null;
		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		ImageOutputStream imOut;
		try {
			imOut = ImageIO.createImageOutputStream(bs);
			ImageIO.write(bi, "png", imOut);
			is = new ByteArrayInputStream(bs.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return is;
	}

	/**
	 * 方法描述：这是测试/////////////////////////////////////////////////////////////
	 * 创建人： lifeng 创建时间：2016年8月18日上午10:23:47
	 * 
	 * @param args
	 */
	/*public static void main(String[] args) {
		// testDraw();
		String x = " 我的  世界  ";
		System.out.println("-" + x.replaceAll(" ", "") + "-");
		String a = "a.bc.com";
		String fmt = a.lastIndexOf(".") == -1 ? "jpg" : a.substring(a
				.lastIndexOf(".") + 1);
		System.out.println("fmt=" + fmt);
	}*/

	public static void testDraw() {
		long stime = System.currentTimeMillis();
		InputStream is = null;
		InputStream is2 = null;
		OutputStream os = null;
		try {

			// 1.jpg是你的 主图片的路径
			File baseFile = new File(getUnderlayPicPath("smzf.jpg"));
			BufferedImage base = ImageIO.read(baseFile);

			// 创建你要附加的图象。
			// 2.jpg是你的小图片的路径
			File sFile = new File("e:/ewm.png");
			BufferedImage bi = ImageIO.read(sFile);

			// 创建新的图象
			BufferedImage buffImg = new BufferedImage(base.getWidth(),
					base.getHeight(), BufferedImage.TYPE_INT_RGB);
			// 得到画笔对象
			Graphics g = buffImg.getGraphics();
			// 将小图片绘到大图片上。
			// 5,300 .表示你的小图片在大图片上的位置。
			g.drawImage(base, 0, 0, null);
			g.drawImage(bi, 420, 766, null);

			g.dispose();

			os = new FileOutputStream("E:/test1.jpg");
			// 创键编码器，用于编码内存中的图象数据。
			JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os);
			en.encode(buffImg);

			/*
			 * //1.jpg是你的 主图片的路径 is = new
			 * FileInputStream(getUnderlayPicPath("smzf.jpg"));
			 * 
			 * //通过JPEG图象流创建JPEG数据流解码器 JPEGImageDecoder jpegDecoder =
			 * JPEGCodec.createJPEGDecoder(is); //解码当前JPEG数据流，返回BufferedImage对象
			 * BufferedImage buffImg = jpegDecoder.decodeAsBufferedImage();
			 * //得到画笔对象 Graphics g = buffImg.getGraphics();
			 * 
			 * //创建你要附加的图象。 //2.jpg是你的小图片的路径 is2 = new
			 * FileInputStream("e:/ewm.jpg"); //通过JPEG图象流创建JPEG数据流解码器
			 * JPEGImageDecoder jpegDecoder2 = JPEGCodec.createJPEGDecoder(is2);
			 * //解码当前JPEG数据流，返回BufferedImage对象 BufferedImage bi =
			 * jpegDecoder2.decodeAsBufferedImage();
			 * 
			 * 
			 * 
			 * //将小图片绘到大图片上。 //5,300 .表示你的小图片在大图片上的位置。
			 * g.drawImage(bi,460,796,null); g.dispose();
			 * 
			 * os = new FileOutputStream("E:/test2.jpg"); //创键编码器，用于编码内存中的图象数据。
			 * JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os);
			 * en.encode(buffImg);
			 */
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (is2 != null) {
					is2.close();
				}
				if (os != null) {
					os.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("耗时" + (System.currentTimeMillis() - stime)
					+ "ms");
		}

	}

	/**
	 * 方法描述：三张图片的叠加(最上面的为小鸟、中间为二维码、最下面为底图),在这里最上面的二维码中间的小鸟的图片的大小为192*192 创建人：
	 * lifeng 创建时间：2016年8月15日下午5:48:56
	 * 
	 * @param bi
	 *            商家传来的二维码图片
	 * @param ops
	 *            输出流
	 * @param path
	 *            最大图片的路径，这里指的是最下面的背景图片的路径
	 * @param params
	 *            [0] 最上面图片在中间图片中的偏移的x坐标，这里代表二维码中间小鸟的偏移x坐标
	 * @param params
	 *            [1] 最上面图片在中间图片中的偏移的y坐标，这里代表二维码中间小鸟的偏移y坐标
	 * @param params
	 *            [2] 中间图片（二维码）在底图上偏移的位置x坐标
	 * @param params
	 *            [3] 中间图片（二维码）在底图上偏移的位置y坐标
	 * @param params
	 *            [4] 中间图片上文字的大小
	 * @param hasLogo
	 *            是否带LOGO图
	 */
	private static void drawImageUtil2(BufferedImage bi,
			HttpServletResponse response, String path,
			SellerMatrix sellerMatrix, int[][] iconParam, int[] params,
			boolean hasLogo) {
		InputStream is = null;
		try {
			long t1 = System.currentTimeMillis();
			// 1.把小鸟的图像画到二维码中间
			if (hasLogo) {
				File iconFile = new File(getUnderlayPicPath(PIC_PATH_ICON));
				BufferedImage iconBuffImg = changeImgSize(iconFile,
						iconParam[0], iconParam[1]);
				Graphics icon_g = bi.getGraphics();
				icon_g.drawImage(iconBuffImg,
						(bi.getWidth() - iconBuffImg.getWidth()) / 2,
						(bi.getHeight() - iconBuffImg.getHeight()) / 2, null);
				icon_g.dispose();
			}


			//
			// 3.jpg是你的 主图片的路径
			File baseFile = new File(getUnderlayPicPath(path));
			BufferedImage buffImg = ImageIO.read(baseFile);
			// 3.1得到画笔对象
			Graphics g = buffImg.getGraphics();
			// 3.2将小图片绘到大图片上。
			g.drawImage(bi, params[0], params[1], null);

			long t2 = System.currentTimeMillis();
			// //2.3在底图上写文字
			g.setColor(Color.black);
			// 定义文字的内容和大小（商家名字）
//			Font font = new Font("微软雅黑", Font.BOLD, 112);
//			g.setFont(font);
//			int x = g.getFontMetrics()
//					.stringWidth(sellerMatrix.getSellername());
			
			Integer stringWidth=getFont(g,bi.getWidth(),sellerMatrix.getSellername(),112);
			g.drawString(sellerMatrix.getSellername(),
					(int) (params[2] - stringWidth / 2), params[3]);
			g.setColor(new Color(0, 0, 0));
			g.dispose();

			// 3.3创键编码器，用于编码内存中的图象数据。
			if (response != null) {
				 JPEGImageEncoder en =
				 JPEGCodec.createJPEGEncoder(response.getOutputStream());
				 en.encode(buffImg);
			}
			long t3 = System.currentTimeMillis();

			// 保存到文件夹../resources/商家名称_商家id/图片名称
			String sellerStickFoldStr = getUnderlayPicPath(PIC_PATH_STICK_FOLD)
					+ "//" + sellerMatrix.getSellername() + "_"
					+ sellerMatrix.getSellerid();
			File sellerStickFold = new File(sellerStickFoldStr);
			if (!sellerStickFold.exists()) {
				sellerStickFold.mkdir();
			}
			if (sellerStickFold.list().length > 2) {// 如果文件夹中原来有图片则删除
				FileUtil.deleteContents(sellerStickFold);
			}
			
			
			File file = new File(sellerStickFoldStr + "//"
					+ "台卡.tiff");
			/*String fmt = baseFile.getName().lastIndexOf(".") == -1 ? "jpg"
					: baseFile.getName().substring(
							baseFile.getName().lastIndexOf(".") + 1);*/

			String fmt="ftl";
//			ImageIO.write(buffImg, fmt, file);
			ImageIO.write(ResizeImage.changeDPI(file, buffImg), fmt, file);

			// file.createNewFile();
			// FileUtils.copyInputStreamToFile(getImageStream(buffImg),file);//把生成的二维码的图片保存到项目路径下名字为:商家名_贴纸名.jpg
			long t4 = System.currentTimeMillis();
			// System.out.println("贴图用时{T1="+(t2-t1)+"ms,T2="+(t3-t2)+"ms,T3="+(t4-t3)+"ms}");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			long t2 = System.currentTimeMillis();
		}
	}

	private static void drawImageUtil3(BufferedImage bi,
			HttpServletResponse response, String path,
			SellerMatrix sellerMatrix, int[][] iconParam, int[] params,
			boolean hasLogo) {
		InputStream is = null;
		try {
			long t1 = System.currentTimeMillis();
			// 1.把小鸟的图像画到二维码中间
			if (hasLogo) {
				File iconFile = new File(getUnderlayPicPath(PIC_PATH_ICON));
				BufferedImage iconBuffImg = changeImgSize(iconFile,
						iconParam[0], iconParam[1]);
				Graphics icon_g = bi.getGraphics();
				icon_g.drawImage(iconBuffImg,
						(bi.getWidth() - iconBuffImg.getWidth()) / 2,
						(bi.getHeight() - iconBuffImg.getHeight()) / 2, null);
				icon_g.dispose();
			}

			
			//
			// 3.jpg是你的 主图片的路径
			File baseFile = new File(getUnderlayPicPath(path));
			BufferedImage buffImg = ImageIO.read(baseFile);
			// 3.1得到画笔对象
			Graphics g = buffImg.getGraphics();
			// 3.2将小图片绘到大图片上。
			g.drawImage(bi, params[0], params[1], null);

			long t2 = System.currentTimeMillis();
			// //2.3在底图上写文字
			g.setColor(Color.black);
			// 定义文字的内容和大小（商家名字）
//			Font font = new Font("微软雅黑", Font.BOLD, 112);
//			g.setFont(font);
//			int x = g.getFontMetrics()
//					.stringWidth(sellerMatrix.getSellername());
			int stringWidth=getFont(g,bi.getWidth(),sellerMatrix.getSellername(),112);
			
			g.drawString(sellerMatrix.getSellername(),
					(int) (params[2] - stringWidth / 2), params[3]);
			g.setColor(new Color(0, 0, 0));
			g.dispose();

			// 3.3创键编码器，用于编码内存中的图象数据。
			if (response != null) {
				JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(response
						.getOutputStream());
				en.encode(buffImg);
			}
			long t3 = System.currentTimeMillis();

			// 保存到文件夹../resources/商家名称_商家id/图片名称
			String sellerStickFoldStr = getUnderlayPicPath(PIC_PATH_STICK_FOLD)
					+ "//" + sellerMatrix.getSellername() + "_"
					+ sellerMatrix.getSellerid();
			File sellerStickFold = new File(sellerStickFoldStr);
			if (!sellerStickFold.exists()) {
				sellerStickFold.mkdir();
			}
			if (sellerStickFold.list().length > 2) {// 如果文件夹中原来有图片则删除
				FileUtil.deleteContents(sellerStickFold);
			}
			
			File file = new File(sellerStickFoldStr + "//"
					+ "桌贴(大).tiff");
			/*String fmt = baseFile.getName().lastIndexOf(".") == -1 ? "jpg"
					: baseFile.getName().substring(
							baseFile.getName().lastIndexOf(".") + 1);*/

			String fmt="ftl";
			ImageIO.write(buffImg, fmt, file);
			ImageIO.write(ResizeImage.changeDPI(file, buffImg), fmt, file);
			// 根据大图生成中和小两张图片
			File file2 = new File(sellerStickFoldStr + "//"
					+ "桌贴(中).tiff");
			FileCopyUtils.copy(file, file2);
			File file3 = new File(sellerStickFoldStr + "//"
					+ "桌贴(小).tiff");
			FileCopyUtils.copy(file, file3);

			ImageIO.write(new ResizeImage(file2).resize(1478, 1772), fmt, file2);
			ImageIO.write(new ResizeImage(file3).resize(1181, 1415), fmt, file3);
			
			// file.createNewFile();
			// FileUtils.copyInputStreamToFile(getImageStream(buffImg),file);//把生成的二维码的图片保存到项目路径下名字为:商家名_贴纸名.jpg
			long t4 = System.currentTimeMillis();
			// System.out.println("贴图用时{T1="+(t2-t1)+"ms,T2="+(t3-t2)+"ms,T3="+(t4-t3)+"ms}");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			long t2 = System.currentTimeMillis();
		}
	}
	
	/**
	 * 
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月5日下午8:49:48 <br/>
	 * @param bi
	 * @param ops
	 * @param mainPicUrl 主题路径
	 */
	public static void drawImage(CommonMaterialPics materialPics, OutputStream ops,String mainPicUrl) {
		InputStream is = null;
		try {
			// 1.jpg是你的 主图片的路径
			File baseFile = new File(mainPicUrl);
			BufferedImage buffImg = ImageIO.read(baseFile);
			
			BufferedImage bi = ImageIO.read(new File(materialPics.getPicUrl()));
			// 得到画笔对象
			Graphics g = buffImg.getGraphics();

			// 将小图片绘到大图片上
			g.drawImage(bi, 200, 200, null);
			g.dispose();

			// 创键编码器，用于编码内存中的图象数据。
			JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(ops);
			en.encode(buffImg);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static int getFont(Graphics g,int maxWidth,String str,int fontsize){
		int width=0;
		Font font = new Font("微软雅黑", Font.PLAIN, fontsize);
		g.setFont(font);
		width = g.getFontMetrics().stringWidth(str);
		if(width>maxWidth){
			return getFont(g,maxWidth,str,fontsize-8);
		}else{
		return width;
		}
	}
	
	public static void main(String[] args) {
		try {
			System.out.println("start");
			CommonMaterialPics materialPics = new CommonMaterialPics();
			materialPics.setPicUrl("D:/smile.jpg");
			OutputStream osp = new FileOutputStream(new File("D:/big.jpg"));
			drawImage(materialPics,osp,"D:/example.jpg");
			System.out.println("end");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
