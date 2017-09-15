package com.xmniao.xmn.core.util;

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
/**
 * 
* 项目名称：saasService   
* 类名称：ImageUtil   
* 类描述： 判断二进制数据是否为图片格式类
* 创建人：liuzhihao   
* 创建时间：2016年3月29日 下午5:48:28   
* @version    
*
 */
public class ImageUtil {
	
    public static boolean isImage(File imageFile) { 
        if (!imageFile.exists()) { 
            return false; 
        } 
        Image img = null; 
        try { 
            img = ImageIO.read(imageFile); 
            if (img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0) { 
                return false; 
            } 
            return true; 
        } catch (Exception e) { 
            return false; 
        } finally { 
            img = null; 
        } 
    }
    
}
