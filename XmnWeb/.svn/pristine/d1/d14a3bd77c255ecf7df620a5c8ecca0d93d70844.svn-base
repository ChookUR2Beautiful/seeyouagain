package com.xmniao.xmn.core.base.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
/**
 * 任务抽象类
 * @author ch
 *
 */
public abstract class QuartzJobBean implements Job{
	@Override
	public final void execute(JobExecutionContext context) throws JobExecutionException {
		executeInternal(context);
	}

	protected abstract void executeInternal(JobExecutionContext context) throws JobExecutionException;
}
