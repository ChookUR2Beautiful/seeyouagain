/**
 * 
 */
package com.xmniao.xmn.test.live_anchor;

import java.util.Date;
import java.util.Random;





/**
 * 项目名称：XmnWeb_LVB
 * 
 * 类名称：BliverTest
 *
 * 类描述：在此处添加类描述
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-8-22上午10:40:17
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class BliverTest {
	

	/**
	 * 方法描述：在此处添加方法描述
	 * 创建人： huang'tao
	 * 创建时间：2016-8-22上午10:40:17
	 * @param args
	 */
	public static void main(String[] args) {
		/*String uidRelationChain="1232321,2453534,2342342,";
		System.out.println(uidRelationChain.substring(0,uidRelationChain.lastIndexOf(",")));*/
		
		Random random= new Random();
		for(int i=0;i<10;i++){
			System.out.println(random.nextInt(0)+1);
		}
	}
	
  
}
