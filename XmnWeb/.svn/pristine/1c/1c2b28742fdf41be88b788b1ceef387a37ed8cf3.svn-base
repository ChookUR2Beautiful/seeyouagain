package com.xmniao.xmn.core.util.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.xmniao.xmn.core.common.dao.AreaDao;
import com.xmniao.xmn.core.common.entity.TArea;



public final class AreaHandler {
	
	private static AreaHandler areaHandler=new AreaHandler();
	private  Map<Integer , List<TArea>> map ;
	private  Map<Integer , String> areaInfo ;
	
	//private boolean isHandler =false;
	private AreaHandler(){};
	public static  AreaHandler getAreaHandler(){
		return areaHandler;
	}
	
	/**
	 * 设置区域
	 * @param array
	 * @param id
	 * @param ids
	 */
	private  void setArea(List<TArea> array,int id){
		for(TArea c:array){
			int pid  = c.getPid().intValue();
			if(id == pid){
				addTAreas(pid,c);
				setArea(array,c.getAreaId());
			}
		}
	}
	
	/**
	 * 保存区域列表
	 * @param pid
	 * @param leftShowAu
	 * @param t
	 */
	private void addTAreas(int pid,TArea t){
		List<TArea> sublist = !map.containsKey(pid)? new ArrayList<TArea>():map.get(pid);
		sublist.add(t);
		map.put(pid, sublist);
	}
	
	private void setArea(List<TArea> array){
		setMapByAreaIdAndTitle(array);
		setArea(array,0);
	}
	
	public void areaHanlde(AreaDao areaDao){
		//清除数据
		map = new ConcurrentHashMap<Integer, List<TArea>>();
		areaInfo=  new ConcurrentHashMap<Integer,String>();
		
		TArea t = new TArea();
		//查询数据
		int len =areaDao.count(t).intValue();
		List<TArea> tareas = new ArrayList<TArea>(len);
		tareas = areaDao.getArea();
		//设置
		setArea(tareas);
		tareas =null;
	}
	
	/**
	 * 将地区id 查名字存入容器
	 * @param tareas
	 */
	private void setMapByAreaIdAndTitle(List<TArea> tareas){
		for(TArea t: tareas){
			areaInfo.put(t.getAreaId(), t.getTitle());
		}
	}
	
	/**
	 * 根据地区id 查名字
	 * @param areaId
	 * @return
	 */
	public String getAreaIdByTitle(Integer areaId){
		return areaInfo.get(areaId);
	}
	
	
	
	/**
	 * 获取区域
	 * @return
	 */
	public Map<Integer , List<TArea>> getArea(){
		return map;
	}

}
