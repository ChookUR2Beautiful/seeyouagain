package com.xmniao.xmn.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;


public class SougouLexiconUtil {

	/**
	 * @param args
	 * @return 
	 * @throws Exception 
	 */
	public static List<WordLibrary> getWordLibrary() throws Exception {
		ClassLoader classLoader = Thread.currentThread()  
	            .getContextClassLoader();  
	    if (classLoader == null) {  
	        classLoader = ClassLoader.getSystemClassLoader();  
	    }  
	    java.net.URL url = classLoader.getResource("");  
	    String ROOT_CLASS_PATH = url.getPath() + "/";  
	    File rootFile = new File(ROOT_CLASS_PATH);  
	    String WEB_INFO_DIRECTORY_PATH = rootFile.getParent() + "/";  
	    File webInfoDir = new File(WEB_INFO_DIRECTORY_PATH);  
	    String SERVLET_CONTEXT_PATH = webInfoDir.getParent() + "/";  
	  
	               //这里 SERVLET_CONTEXT_PATH 就是WebRoot的路径  
	  
	    String path = SERVLET_CONTEXT_PATH + "/" + "files/sougou.scel";  
	    path = path.replaceAll("%20", " ");  
	    File file = new File(path);  
		
		FileInputStream input = new FileInputStream(file);
		try {
			byte[] str = new byte[128];
			Map<Integer , String> pyDic = new HashMap<Integer,String>() ;
			List<WordLibrary> pyAndWord = new ArrayList<WordLibrary>();
			
			
			byte[] num;
			int hzPosition = 0;
			input.read(str, 0, 128) ;
			
			if (str[4] == 0x44)
			{
			    hzPosition = 0x2628;
			}
			if (str[4] == 0x45)
			{
			    hzPosition = 0x26C4;
			}
			/********************字库名称开始**************************/
			/*input.getChannel().position(0x130);
			int lenc = input.read(str, 0, 128);
			System.out.println("字库名称:" + toString(str,lenc));*/
			
			/********************字库名称结束*************************/


			/********************字库类别开始**************************/
			/*input.getChannel().position(0x338);
			lenc = input.read(str, 0, 128);
			System.out.println("字库类别:" + toString(str,lenc));*/
			
			/********************字库类别结束*************************/
			
			/********************字库信息开始**************************/
			/*input.getChannel().position(0x540);
			lenc = input.read(str, 0, 128);
			System.out.println("字库信息:" + toString(str,lenc));*/
			
			/********************字库信息结束*************************/
			
			/********************字库示例开始**************************/
			/*input.getChannel().position(0xd40);
			lenc = input.read(str, 0, 128);
			System.out.println("字库示例:" + toString(str,lenc));
			*/
			/********************字库示例结束*************************/
			
			input.getChannel().position(0x1540) ;
			str = new byte[4];
			input.read(str, 0, 4);

			while (true)
			{
			    num = new byte[4];
			    input.read(num, 0, 4);
			    int mark = num[0] + num[1]*256;
			    str = new byte[128];
			    if(num[2]>0){
			    	input.read(str, 0, (num[2]));
			        String py = new String(str,0,num[2]);
			        py = py.replaceAll("\0", "");
			        pyDic.put(mark, py);
			        if (py == "zuo") //最后一个拼音
			        {
			            break;
			        }
			    }else{
			    	break ;
			    }
			}
			input.getChannel().position(hzPosition);
			int i = 0, count = 0, samePYcount = 0;
			one:while (true)
			{
				num = new byte[4];
				input.read(num, 0, 4);
				samePYcount = num[0] + num[1]*256;
				count = num[2] + num[3]*256;
				//接下来读拼音
				str = new byte[256];
				for (i = 0; i < count; i++)
				{
					str[i] = (byte) input.read();
				}
				String wordPY = "";
				for (i = 0; i < count/2; i++)
				{
					int key = str[i*2] + str[i*2 + 1]*256;
					wordPY += pyDic.get(key) + " ";
				}
				//接下来读词语
				for (int s = 0; s < samePYcount; s++) //同音词，使用前面相同的拼音
				{
					num = new byte[2];
					input.read(num, 0, 2);
					int hzBytecount = num[0] + num[1]*256;
					str = new byte[hzBytecount];
					int len = input.read(str, 0, hzBytecount);
					String word = toString(str , len);
					if(StringUtils.isNotBlank(word)){
						pyAndWord.add(new WordLibrary(word , wordPY));
					}
					byte[] temp = new byte[12];
					for (i = 0; i < 12; i++)
					{
						temp[i] = (byte) input.read();
					}
				}
				if (file.length() == input.getChannel().position()) //判断文件结束
				{
					input.close();
					break;
				}
			}
			pyAndWord.remove(null);
			return pyAndWord;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}finally {
			input.close();
		}
	}
	/**
	 * 
	 * @param data
	 * @return
	 */
	public static String toString(byte[] data , int len){
		StringBuffer strb = new StringBuffer() ;
		for(int ix=0 ; ix<len ; ix+=2){
			if(data[ix+1]<=0 && data[ix]<=0){
				continue ;
			}
			int d = data[ix]+data[ix+1]*256 ;
			if(data[ix]<=0){
				d = d+256 ;
			}
			if(d!=0){
				strb.append((char)d);
			}
		}
		return strb.toString() ;
	}
}

