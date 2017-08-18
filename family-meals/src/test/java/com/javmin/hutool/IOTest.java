package com.javmin.hutool;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;

import org.junit.Test;

import com.xiaoleilu.hutool.io.FileUtil;
import com.xiaoleilu.hutool.io.IoUtil;

public class IOTest {
	
	@Test
	public void IoCopy() throws IOException{
		BufferedInputStream in = FileUtil.getInputStream("d:/test.txt");
		BufferedOutputStream out = FileUtil.getOutputStream("d:/test2.txt");
		long copySize = IoUtil.copy(in, out, IoUtil.DEFAULT_BUFFER_SIZE);
		IoUtil.close(in);
		IoUtil.close(out);
	}
	
	
	
	
	
}
