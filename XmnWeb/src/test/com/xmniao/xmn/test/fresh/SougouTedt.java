/**
 * 
 */
package com.xmniao.xmn.test.fresh;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import org.junit.Test;

import com.xmniao.xmn.core.util.SougouLexiconUtil;
import com.xmniao.xmn.core.util.WordLibrary;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：SougouTedt
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年3月21日 上午9:57:28 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public class SougouTedt {

	@SuppressWarnings("resource")
	@Test
	public void test() throws Exception {
		List<WordLibrary> wordLibrary = SougouLexiconUtil.getWordLibrary();
		File file = new File("E://sougouCiku.txt");
		if(!file.exists()){
			file.createNewFile();
		}
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);  
		BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);  
		for (WordLibrary word: wordLibrary) {
			bufferedWriter.write(word.getWord());
			System.out.println(word.getWord());
			bufferedWriter.newLine();
		}
		
	}

}
