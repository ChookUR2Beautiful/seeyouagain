/**
 * 
 */
package com.xmn.saas.utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.xmn.saas.utils.matrix.MatrixToImageWriter;

/**
 * 项目名称：xmniao-saas-api 类描述： 文件操作 创建人：huangk 创建时间：2016年10月21日 下午5:29:55
 */

public class FileUtil {

    private static final int BLACK = 0xFF000000;

    private static final int WHITE = 0xFFFFFFFF;

    /**
     * 生成二维码(200x200)
     * 
     * @param content 内容
     * @param file 写入目标文件
     * @return filePath 文件路劲
     */
    @SuppressWarnings( "all" )
    public static File gerateQRCode(String content, String basePath) throws Exception {
        // 定义二维码文件
        String codeFile = System.currentTimeMillis() + UUID.randomUUID().toString() + ".jpg";
        File file = createFile(basePath, codeFile);
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        Map hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        System.out.println("content：======================================================"+content);
        
        BitMatrix bitMatrix =
                multiFormatWriter.encode(content.trim(), BarcodeFormat.QR_CODE, 1150, 1150, hints);
        MatrixToImageWriter.writeToFile(bitMatrix, "jpg", file);
        return file;
    }

    /**
     * 创建文件
     * 
     * @param basePath 根目录
     * @param fileName 文件名称
     * @return File 返回类型
     */
    public static File createFile(String basePath, String fileName) throws IOException {
        File file = new File(basePath, fileName);
        file.createNewFile();
        return file;
    }

    /**
     * 生成水印并返回java.awt.image.BufferedImage
     * 
     * @param file 源文件(图片)
     * @param waterFile 水印文件(图片)
     * @param x 距离右下角的X偏移量
     * @param y 距离右下角的Y偏移量
     * @param alpha 透明度, 选择值从0.0~1.0: 完全透明~完全不透明
     * @return byte[] 字节数组
     */
    public static byte[] mergeImage(File file, File waterFile, int x, int y, float alpha,String sellerName,String dateTime)
            throws IOException {
        // 获取底图
        BufferedImage buffImg = ImageIO.read(file);
        // 获取层图
        BufferedImage waterImg = ImageIO.read(waterFile);
        // 创建Graphics2D对象，用在底图对象上绘图
        Graphics2D g2d = buffImg.createGraphics();
        int waterImgWidth = waterImg.getWidth();// 获取层图的宽度
        int waterImgHeight = waterImg.getHeight();// 获取层图的高度
        // 在图形和图像中实现混合和透明效果
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
        // 绘制
        g2d.drawImage(waterImg, x, y, waterImgWidth, waterImgHeight, null);
        
        Font font = new Font("微软雅黑", Font.BOLD, 150/2);
        g2d.setColor(Color.black);
        g2d.setFont(font);
        g2d.drawString(dateTime,
                1260/2, 6500/2);
        g2d.setColor(new Color(0, 0, 0));
        
        g2d.setColor(Color.WHITE);
        g2d.setFont(font);
        int k = g2d.getFontMetrics()
                .stringWidth(sellerName);
        g2d.drawString(sellerName,
                2550/2-k/2, 6900/2);
        g2d.setColor(new Color(0, 0, 0));
        
        g2d.dispose();// 释放图形上下文使用的系统资源

        // 将BufferedImage转成二进制返回
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        ImageOutputStream imOut;
        imOut = ImageIO.createImageOutputStream(bs);
        ImageIO.write(buffImg, "jpg", imOut);
        if(waterFile.exists()){
            waterFile.deleteOnExit();
        }
        return bs.toByteArray();
    }

    /**
     * 下载活动宣传海报
     * 
     * @param url 活动地址
     * @param basePath 文件父目录
     * @param posterImgpath 活动海报底图路径
     * @return InputStream 返回类型
     */
    public static byte[] downloadQRCode(String url, String basePath, String posterImgpath,String sellerName,String dateTime)
            throws Exception {
        // 生成返回二维码图片
        File qrcodeFile = gerateQRCode(url, basePath);
        // 活动海报底图
        File poseterImg = new File(basePath + posterImgpath);
        // 返回活动二维码的宣传海报
        byte[] bytes = mergeImage(poseterImg, qrcodeFile, 700, 1925, 1.0f,sellerName,dateTime);
//        // 删除临时二维码图片
//        if (qrcodeFile.exists()) {
//            qrcodeFile.delete();
//        }
        return bytes;
    }

    public static void main(String[] args) throws Exception {
        // String codeFile = gerateQRCode("http://www.baidu.com","D:/test/");
        // String sourceFilePath = "D://share-background.jpg";
        // String saveFilePath = "D://test//merge.jpg";
        // // 构建叠加层
        // BufferedImage buffImg = mergeImage(new File(sourceFilePath), new File(codeFile), 1400,
        // 3850, 1.0f);
        //
        // // 输出水印图片
        // generateWaterFile(buffImg, saveFilePath);
        gerateQRCode("www.google.com", "c://");
    }

}
