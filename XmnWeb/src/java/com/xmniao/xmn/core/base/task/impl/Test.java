/**
 * 
 */
package com.xmniao.xmn.core.base.task.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：Test
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-11-5 下午2:11:43 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public class Test implements Job {

	/* (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// job 的名字  
        String jobName = context.getJobDetail().getKey().getName();  
          
        // 任务执行的时间  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy 年 MM 月 dd 日  HH 时 mm 分 ss 秒");  
        String jobRunTime = dateFormat.format(Calendar.getInstance().getTime());  
		// 输出任务执行情况  
        System.out.println("任务 : " + jobName + " 在  " +jobRunTime + " 执行了 ");  
	}
	
}
