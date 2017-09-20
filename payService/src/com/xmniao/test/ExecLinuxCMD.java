package com.xmniao.test;

import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
 * java在linux环境下执行linux命令，然后返回命令返回值。
 * 
 */
public class ExecLinuxCMD {

	public static Object exec(String cmd) {
		try {
			String[] cmdA = { "/bin/sh", "-c", cmd };
			Process process = Runtime.getRuntime().exec(cmdA);
			LineNumberReader br = new LineNumberReader(new InputStreamReader(
					process.getInputStream()));
			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				sb.append(line).append("\n");
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String mkdirString = exec("mkdir /usr/local/tomcat/webapps/chunchun").toString();

		System.out.println("==========获得值=============");
		System.out.println(mkdirString);
	}
}