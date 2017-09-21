package com.xmniao.util;

import java.util.Arrays;
import java.util.Random;

public class PayIDGenerate {
	/**
	 * 生成PayId 12位长度。如：141015 100655 1254。格式6位年月日+小时 分 秒+4位随机数
	 * 
	 * @return
	 */
	public static String createPayId() {
		return UtilDate.getDateForm() + "" + getFourDigit();
	}

	/**
	 * 四位随机数
	 * 
	 * @return
	 */
	public static String getFourDigit() {
		Random ran = new Random();
		int r = 0;
		m1: while (true) {
			int n = ran.nextInt(10000);
			r = n;
			int[] bs = new int[4];
			for (int i = 0; i < bs.length; i++) {
				bs[i] = n % 10;
				n /= 10;
			}
			Arrays.sort(bs);
			for (int i = 1; i < bs.length; i++) {
				if (bs[i - 1] == bs[i]) {
					continue m1;
				}
			}
			break;
		}
		if (String.valueOf(r).length() == 3) {
			return "0" + String.valueOf(r);
		}
		if (String.valueOf(r).length() == 2) {
			return "00" + String.valueOf(r);
		}
		if (String.valueOf(r).length() == 1) {
			return "000" + String.valueOf(r);
		}
		return String.valueOf(r);
	}

	/*public static void main(String[] args) {

		try {
			for (int i = 0; i < 200; i++) {
				Thread t1 = new Thread(new Runnable() {
					public void run() {
						for (int j = 0; j < 100; j++) {
							System.out.println(PayIDGenerate.createPayId());
						}
					}
				});
				t1.start();

				Thread t2 = new Thread(new Runnable() {
					public void run() {
						for (int j = 0; j < 100; j++) {
							System.out.println(PayIDGenerate.createPayId());
						}
					}
				});
				t2.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}*/

}
