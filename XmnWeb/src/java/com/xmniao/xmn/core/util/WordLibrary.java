/**
 * 
 */
package com.xmniao.xmn.core.util;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：WordLibrary
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年3月2日 下午2:21:40 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public class WordLibrary {
	 private int count = 1;
	    private String[] pinYin;
	    private String pinYinString = "";
	    private String word;
	    public WordLibrary(String word , String pinYinString){
	    	this.word = word ;
	    	this.pinYinString = pinYinString ;
	    }
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
		public String[] getPinYin() {
			return pinYin;
		}
		public void setPinYin(String[] pinYin) {
			this.pinYin = pinYin;
		}
		public String getPinYinString() {
			return pinYinString;
		}
		public void setPinYinString(String pinYinString) {
			this.pinYinString = pinYinString;
		}
		public String getWord() {
			return word;
		}
		public void setWord(String word) {
			this.word = word;
		}

}
