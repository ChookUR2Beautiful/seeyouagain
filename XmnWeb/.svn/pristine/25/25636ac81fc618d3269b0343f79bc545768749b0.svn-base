package com.xmniao.xmn.core.base;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.util.ServiceConstant;
import com.xmniao.xmn.core.util.helper.ListenerHelperSupport;
import com.xmniao.xmn.core.util.listener.AccessRemarkRecordListener;
import com.xmniao.xmn.core.util.listener.LdHandlerListener;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：BaseService
 * 
 * 类描述： 基础业务
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月3日 下午8:24:14
 * 
 * Copyright (c) 深圳市XXX有限公司-版权所有
 */
public abstract class BaseService<T>{
	
	private BaseDao baseDao;
	protected abstract BaseDao getBaseDao();
	protected final Logger log = Logger.getLogger(getClass());
	
	
	private  ListenerHelperSupport listenerHelper =new ListenerHelperSupport();
	
	public BaseService(){
		addListener(new AccessRemarkRecordListener());
		addListener(new LdHandlerListener());
	}
	
	protected  void addListener(BaseListener listener){
		if(listenerHelper.findListener(listener)==null){
			listenerHelper.addListener(listener);
		}
	}
	
	public void fireLoginEvent(Object data){
		fireLoginEvent(data,null);
	}
	
	public void fireLoginEvent(Object data,Integer state){
		fireLoginEvent(null,data,state);
	}
	
	public void fireEvent(String type,Object data){
		fireLoginEvent(type,data,null);
	}
	
	public void fireLoginEvent(String type,Object data,Integer state){
		listenerHelper.fireEvent(this,type, data,state);
	}

	/**
	 * @方法名：addBatchLog
	 * 
	 * @方法描述：添加
	 * 
	 * @传入参数：List<TLog> logList（插入集合）
	 * 
	 * @返回值：Integer （影响条数）
	 * 
	 * @创建人：zhou'sheng
	 * 
	 * @创建时间：2014年11月03日16时05分05秒
	 * 
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer addBatch(List<T> list) {

		if (null == list) {
			return new Integer(0);
		}

		if (list.isEmpty()) {
			return new Integer(0);
		}

		int divisor = list.size() / ServiceConstant.INSERT_LIMIT;
		int friction = list.size() % ServiceConstant.INSERT_LIMIT;
		int page = friction == 0 ? divisor : divisor + 1;
		int resultNum = 0;

		for (int i = 0; i < page; i++) {
			if (i == (page - 1)) {
				if (0 == friction) {
					resultNum += getBaseDao().addBatch(list.subList(i * ServiceConstant.INSERT_LIMIT, (i + 1) * ServiceConstant.INSERT_LIMIT));
				} else {
					resultNum += getBaseDao().addBatch(list.subList(i * ServiceConstant.INSERT_LIMIT, i * ServiceConstant.INSERT_LIMIT + friction));
				}
			} else {
				resultNum += getBaseDao().addBatch(list.subList(i * ServiceConstant.INSERT_LIMIT, (i + 1) * ServiceConstant.INSERT_LIMIT));
			}
		}

		return resultNum;
	}

	/**
	 * @方法名：add
	 * 
	 * @方法描述：添加
	 * 
	 * @传入参数：T t（对象）
	 * 
	 * @创建人：zhou'sheng
	 * 
	 * @创建时间：2014年10月31日17时42分21秒
	 * 
	 */
	public void add(T t) {
		try{
			getBaseDao().add(t);
		}catch(Exception e){
			throw new ApplicationException("添加信息异常",e, new Object[]{t});
		}
		
	}
	
	
	/**
	 * @方法名：addReturnId
	 * 
	 * @方法描述：添加 并返回 插入id
	 * 
	 * @传入参数：TLog log（对象）
	 * 
	 * @创建人：zhou'sheng
	 * 
	 * @创建时间：2014年10月31日17时42分21秒
	 * 
	 */
	public int addReturnId(T t){
		return getBaseDao().addReturnId(t);
	}

	/**
	 * @方法名：delete
	 * 
	 * @方法描述：删除
	 * 
	 * @传入参数：Object[] objects（Array）
	 * 
	 * @返回值：Integer （影响条数）
	 * 
	 * @创建人：zhou'sheng
	 * 
	 * @创建时间：2014年11月03日16时05分05秒
	 * 
	 */
	public Integer delete(Object[] objects) {
		try{
			return getBaseDao().delete(objects);
		}catch(Exception e){
			throw new ApplicationException("删除信息异常",e, objects);
		}
		
	}
	
	
	public int deleteByPrimaryKey(Long id) {
		try{
			return getBaseDao().deleteByPrimaryKey(id);
		}catch(Exception e){
			throw new ApplicationException("删除信息异常",e, id);
		}
		
	}

	/**
	 * @方法名：update
	 * 
	 * @方法描述：修改
	 * 
	 * @传入参数：T t（对象）
	 * 
	 * @返回值：Integer （影响条数）
	 * 
	 * @创建人：zhou'sheng
	 * 
	 * @创建时间：2014年11月03日16时05分05秒
	 * 
	 */
	@SuppressWarnings("unchecked")
	public Integer update(T t) {
		try{
			return getBaseDao().update(t);
		}catch(Exception e){
			throw new ApplicationException("修改信息异常",e, new Object[]{t});
		}
		
	}

	/**
	 * @方法名：getList
	 * 
	 * @方法描述：查询
	 * 
	 * @传入参数：T t（对象）
	 * 
	 * @返回值：List<T > （T 集合）
	 * 
	 * @创建人：zhou'sheng
	 * 
	 * @创建时间：2014年11月03日16时05分05秒
	 * 
	 */
	public List<T> getList(T t) {
		return getBaseDao().getList(t);
	}

	/**
	 * @方法名：getLog
	 * 
	 * @方法描述：查询
	 * 
	 * @传入参数：TLog log（对象）
	 * 
	 * @返回值：TLog log（对象）
	 * 
	 * @创建人：zhou'sheng
	 * 
	 * @创建时间：2014年11月03日16时05分05秒
	 * 
	 */
	public T getObject(T t) {
		List<T> list = getBaseDao().getList(t);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	/**
	 * @方法名：getMaxId
	 * 
	 * @方法描述：查询
	 * 
	 * @返回值：Integer（Max ID）
	 * 
	 * @创建人：zhou'sheng
	 * 
	 * @创建时间：2014年11月03日16时05分05秒
	 * 
	 */
	public Long getMaxId() {
		Long id = getBaseDao().getMaxId();
		return id == null ? 0 : id;
	}
	
	/**
	 * @方法名：getObject
	 * 
	 * @方法描述：查询
	 * 
	 * @传入参数：Long id
	 * 
	 * @返回值：T （T 对象）
	 * 
	 * @创建人：zhou'sheng
	 * 
	 * @创建时间：2014年10月31日17时42分21秒
	 * 
	 */
	public T getObject(Long id){
		if(null == id){
			return null;
		}
		return (T) getBaseDao().getObject(id);
	}

	/**
	 * @方法名：count
	 * 
	 * @方法描述：统计
	 * 
	 * @传入参数：T t（对象）
	 * 
	 * @返回值：Long （总条数）
	 * 
	 * @创建人：zhou'sheng
	 * 
	 * @创建时间：2014年11月03日16时05分05秒
	 * 
	 */
	public Long count(T t) {
		return getBaseDao().count(t);
	}
	




}
