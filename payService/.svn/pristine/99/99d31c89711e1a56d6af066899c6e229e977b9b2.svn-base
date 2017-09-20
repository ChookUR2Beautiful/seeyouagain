package com.xmniao.test;

import java.io.File;

public class test {

	/*
	 * 该方法实现文件自动复制功能。利用系统命令将指定文件名从源路径复制到目的路径 如果目的路径不存在时，自动创建目的路径
	 */
	public static boolean copyFile(String origpath, String destpath,
			String filename) throws Exception {
		String osName = System.getProperty("os.name");
		boolean flag = false;
		/* 系统命令支持的操作系统Windows XP, 2000 2003 7 */
		if (!(osName.equalsIgnoreCase("windows XP")
				|| osName.equalsIgnoreCase("windows 2000")
				|| osName.equalsIgnoreCase("windows 2003") || osName
					.equalsIgnoreCase("windows 7"))) {
			return flag;
		}
		Runtime rt = Runtime.getRuntime();
		Process p = null;
		File f = new File(destpath);
		if (!f.exists()) {
			f.mkdirs();
		}
		int exitVal;
		p = rt.exec("cmd exe /c copy " + origpath + filename + " " + destpath);
		// 进程的出口值。根据惯例，0 表示正常终止。
		exitVal = p.waitFor();
		if (exitVal == 0) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;

	}

	public static void main(String[] args) {

		try {
			System.out.println(copyFile("D:\\DATA\\", "D:\\a\\", "131204.txt"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}