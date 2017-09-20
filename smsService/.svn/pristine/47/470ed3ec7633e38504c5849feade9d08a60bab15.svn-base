package com.sms.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

/**
 * 工具类
 * @author douk
 *
 */
public class utilClass {
	
	/**
	 * 获取6位随机数
	 * @return
	 */
	public static int RandomNum(){
		int[] array = {1,2,3,4,5,6,7,8,9};
		Random rand = new Random();
		for (int i = 9; i > 1; i--) {
		    int index = rand.nextInt(i);
		    int tmp = array[index];
		    array[index] = array[i - 1];
		    array[i - 1] = tmp;
		}
		int result = 0;
		for(int i = 0; i < 6; i++){
		    result = result * 10 + array[i];
		}
		return result;
	}
	
	/**
     * 生成10位编号
     * @return
     */
    public static String getYearInfo(){
    	String result = "";
    	try {
			Thread.sleep(1);
			String year =new SimpleDateFormat("yyMMdd").format(Calendar.getInstance().getTime());
	    	String num = System.currentTimeMillis()+"";
	    	result = year+""+num.substring(num.length()-4, num.length());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	return result;
    }
    
}
