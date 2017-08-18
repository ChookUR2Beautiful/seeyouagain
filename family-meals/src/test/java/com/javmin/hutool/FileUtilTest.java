package com.javmin.hutool;

import java.io.File;
import java.nio.file.WatchEvent;

import org.junit.Test;

import com.xiaoleilu.hutool.io.FileUtil;
import com.xiaoleilu.hutool.io.file.FileReader;
import com.xiaoleilu.hutool.io.file.FileWriter;
import com.xiaoleilu.hutool.io.watch.WatchMonitor;
import com.xiaoleilu.hutool.io.watch.Watcher;
import com.xiaoleilu.hutool.lang.Console;

public class FileUtilTest {

	@Test
	public void fileUtil(){
		FileUtil.mkdir("D://a/b/c");
		FileUtil.touch("D://a/b/c/touch.txt");
		FileUtil.del("D://a/b");
	}
	
	@Test
	public void 文件监听() throws InterruptedException{
		File file = FileUtil.file("D://touth.txt");
		WatchMonitor watchMonitor = WatchMonitor.create(file, WatchMonitor.EVENTS_ALL);
		watchMonitor.setWatcher(new Watcher(){

		    @Override
		    public void onOverflow(WatchEvent<?> event) {
		        Console.log("EVENT overflow");
		    }

		    @Override
		    public void onModify(WatchEvent<?> event) {
		        Console.log("EVENT modify");
		    }

		    @Override
		    public void onDelete(WatchEvent<?> event) {
		    	System.out.println("=====");
		        Console.log("EVENT delete");
		    }

		    @Override
		    public void onCreate(WatchEvent<?> event) {
		        Console.log("EVENT create");
		    }
		});
		FileUtil.writeString("sdadsa", file,"utf-8");
	}
	
	
	@Test
	public void 读取资源文件(){
		FileReader fileReader = new FileReader("test.properties");
		String result = fileReader.readString();
		System.out.println(result);
	}
	
	@Test
	public void 文件写入(){
		FileWriter writer = new FileWriter("test.properties");
		writer.append("test");
	}
	
}
