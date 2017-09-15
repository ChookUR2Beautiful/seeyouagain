package com.xmniao.xmn.core.base;

/**
 * 
 * @项目名称：saasService
 * @类名称：BaseVControlInf
 * @类描述：版本控制接口
 * @创建人： zhangchangyuan
 * @创建时间 2016年3月30日 上午10:13:04
 * @version
 */
public interface BaseVControlInf {
	/**
	 * 版本控制
	 * @param v
	 * @param objects
	 * @return
	 */
	public Object versionControl(int v,Object object);
}
