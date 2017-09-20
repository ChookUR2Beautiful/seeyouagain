package com.xmniao.xmn.core.base.task;

import java.io.IOException;
import java.util.Properties;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.simpl.SimpleThreadPool;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class QuartzScheduler implements InitializingBean,DisposableBean{
	
	public static final String PROP_THREAD_COUNT = "org.quartz.threadPool.threadCount";

	public static final int DEFAULT_THREAD_COUNT = 10;
	
	private static final String  JOB_INITIALIZATION_PLUGIN_NAME = StdSchedulerFactory.PROP_PLUGIN_PREFIX+".jobInitializer";
	
	private String config;
	
	private Scheduler quartzScheduler;
	
	public QuartzScheduler(String config) {
		this.config = config;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		SchedulerFactory schedulerFactory  = new StdSchedulerFactory();
		initSchedulerFactory(schedulerFactory);	
		quartzScheduler = schedulerFactory.getScheduler();
		quartzScheduler.start();
		
	}
	
	private void initSchedulerFactory(SchedulerFactory schedulerFactory) throws SchedulerException, IOException {
		Properties mergedProps = new Properties();
		mergedProps.setProperty(JOB_INITIALIZATION_PLUGIN_NAME+".class", "org.quartz.plugins.xml.XMLSchedulingDataProcessorPlugin");
		mergedProps.setProperty(JOB_INITIALIZATION_PLUGIN_NAME+".fileNames",config);
		mergedProps.setProperty(StdSchedulerFactory.PROP_THREAD_POOL_CLASS, SimpleThreadPool.class.getName());
		mergedProps.setProperty(PROP_THREAD_COUNT, Integer.toString(DEFAULT_THREAD_COUNT));

		((StdSchedulerFactory) schedulerFactory).initialize(mergedProps);
	}

	@Override
	public void destroy() throws Exception {
		quartzScheduler.shutdown();
		
	}

}
