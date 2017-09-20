package com.xmniao.xmn.core.util.holder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class ExecutorServiceHolder {
	
	private static final  int DEFAULT_THREAD_SIZE=150;
	/**
	 * 线程池
	 */
	private static ExecutorService executorService;//线程池
	
	public void init(){
		executorService = Executors.newFixedThreadPool(DEFAULT_THREAD_SIZE, new ThreadFactory() {
			AtomicInteger atomicInteger = new AtomicInteger(1);
			@Override
			public Thread newThread(Runnable r) {
				ThreadGroup group = Thread.currentThread().getThreadGroup();
				Thread thread = new Thread(group, r, "XmnWeb-Thread-"+atomicInteger.getAndIncrement());
				return thread;
			}
		});
	}
	
	public static ExecutorService getExecutorService(){
		return executorService;
	}
	
	public void destroy(){
		executorService.shutdown();
	}

}
