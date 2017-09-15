package com.xmniao.xmn.core.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：SensitiveWordUtils   
* 类描述：   敏感词汇工具类
* 创建人：yezhiyong   
* 创建时间：2016年10月8日 下午2:50:22   
* @version    
*
 */
@Service
public class SensitiveWordUtil {

	/**
	 * 注入redis缓存
	 */
	@Autowired
	private StringRedisTemplate stringredisTemplate;  
	
	/**
	 * 
	* @Title: getSensitiveWord
	* @Description: 将敏感词库加载进入redis中
	* @return List<String>    返回类型
	* @throws
	 */
	public void getSensitiveWord() {
		// 加载 敏感词汇读进来....
        // 1: 找路径... 2:循环读取.... 3:存放到 List<String>
        // 类加载器 : 获取 src 路径 类路径
        String path = WordUtils.class.getClassLoader().getResource("").getPath() + "tls";
         
        File f = new File(path);
 
        if (f.exists()) {
            // 读取文件 文件过滤器 !! FileFilter xxx|1
            // 1: 获取该目录下所有文件
            File[] files = f.listFiles(new FileFilter() {
                // 文件过滤器的使用
                @Override
                public boolean accept(File f) {
                    // 过滤需要的文件
                    if (f.getName().endsWith(".txt")) {
                        return true;
                    }
                    return false;
                }
            });
            for (File file : files) {
                // 文件全部读取
                try {
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(new FileInputStream(file),"UTF-8"));// i/o 流的读取
                    // 集合添加数据
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        String[] arr = line.split("\\|"); // xxx 1 2 3
                      //将敏感词汇存入redis中
                        for (String str : arr) {
                        	if (StringUtils.isNotEmpty(str.trim())) {
                        		stringredisTemplate.opsForSet().add("sensitiveWord", str.trim());
							}
						}
                        
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
 
            }
        }
        
	}
	
	/**
	 * 
	* @Title: sensitiveWordDeal
	* @Description: 敏感词替换处理
	* @return String    返回类型
	* @throws
	 */
	public String sensitiveWordDeal(String text) {
		//过滤后的文本
		String newText = text;
		if (!stringredisTemplate.hasKey("sensitiveWord")) {
			//将敏感词库加载进入redis中
			this.getSensitiveWord();
		}
		
		for (int i = 0; i < text.length() - 1; i++) {
			for (int j = i + 1; j <= text.length(); j++) {
				String subStr = text.substring(i, j);
				Boolean result = stringredisTemplate.opsForSet().isMember("sensitiveWord", subStr);
				if (result) {
					newText = newText.replace(subStr, "***");
					i = j;
					break;
				}
			}
		}
		return newText;
	}
	
}
