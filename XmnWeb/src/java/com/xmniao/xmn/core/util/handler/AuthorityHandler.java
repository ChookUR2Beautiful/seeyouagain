package com.xmniao.xmn.core.util.handler;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.xmniao.xmn.core.system_settings.dao.AuthorityDao;
import com.xmniao.xmn.core.system_settings.entity.TAuthority;
import com.xmniao.xmn.core.util.StringUtils;

public final class AuthorityHandler {
	private static final AuthorityHandler authorityHandler=new AuthorityHandler();
	
	/**
	 * 所有模块容器
	 */
	private static List<TAuthority> authorities;
	
	
	/**
	 * 存储authorities 字节  方便深拷贝
	 */
	private ByteArrayOutputStream bout;
	
	/**
	 * 父模块容器 记录每个模块拥有多少个子模块
	 */
	private Map<Long,Integer> parents = new ConcurrentHashMap<Long,Integer>();
	
	
	private Map<String,Long> urls = new ConcurrentHashMap<String,Long>();
	
	/**
	 * 模块下标
	 */
	private Map<Long,Integer> authoritieIndex;
	
	
	/**
	 * 角色权限同步
	 */
	private Object SynAuMap = new Object();
	
	private Boolean ishandle = false;
	
	
	private AuthorityHandler(){}
	
	/**
	 * get
	 * 
	 * @return
	 */
	public static AuthorityHandler getAuthorityHanlde(){
		
		return authorityHandler;
	}
	
	/**
	 * 初始化
	 * @param authorityDao
	 */
	public void authorityHanlde(AuthorityDao authorityDao){
		if(!ishandle){
			ishandle=true;
			TAuthority  authority =  new TAuthority();
			Integer len = authorityDao.count(authority).intValue();
			authorities = new ArrayList<TAuthority>(len);
			authorities = authorityDao.getAuthority();
			setInfo();
			//缓存权限list数组 用于深拷贝
			cacheList();
		}
	}
	
	/**
	 * 缓存权限list 便于深拷贝
	 */
	private void  cacheList(){
		try {
			bout = new ByteArrayOutputStream(2048);
			ObjectOutputStream objOut = new ObjectOutputStream(bout);
			objOut.writeObject(authorities);
			objOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 设置必要信息
	 */
	private void setInfo(){
		int len   = authorities.size();
		authoritieIndex = new ConcurrentHashMap<Long,Integer>(len);
		int i=0;
		while (i<len) {
			TAuthority t = authorities.get(i);
			//记录下标
			authoritieIndex.put(t.getId(), i);
			String url = t.getAuthorityUrl();
			if(StringUtils.hasLength(url)){
				urls.put(url, t.getId());
			}
			
			++i;
			//记录父id 与父元素对应拥有子元素的数量
			Long pid= t.getAuthorityFatherId();
			if(parents.containsKey(pid)){
				parents.put(pid, parents.get(pid)+1);
				continue;
			};
			parents.put(pid,1);
		}
		
	}
	
	
	public Long getUrlById(String url){
		return urls.get(url);
	}
	
	public Long getIdByAuthorityFid(Long id){
		Long fid= null;
		try{
			fid = authorities.get(authoritieIndex.get(id)).getAuthorityFatherId();
		}catch(Exception e){}
		return fid;
	}
	
	
	
	/**
	 * 根据传入角色资源id列表  返回权限map
	 * @param authorityId
	 */
	public Map<Long, List<TAuthority>> getAuthorityMap(List<Long> authorityId){
			//复制
			List<TAuthority> cList = copy();
			Map<Long, List<TAuthority>> m=null;
			if(null!=cList){
				 m = new HashMap<Long, List<TAuthority>>();
				 Set<Long> set = null!=authorityId&&!authorityId.isEmpty()?new HashSet<Long>(authorityId):new HashSet<Long>(0);				
				 mapHandle(cList,0l,set,m);
			}
			return m;
		
	}
	
	/**
	 * 递归为权限分组  并且设置对应角色资源
	 * @param array
	 * @param id
	 * @param ids
	 * @param map
	 */
	private void mapHandle(List<TAuthority> array,long id ,Set<Long> ids,Map<Long , List<TAuthority>> map){
			if(!parents.containsKey(id)){
				return;
			}
			int count=0;
			int size =parents.get(id);
			for(TAuthority c:array){
				if(size==count){
					break;
				}
				if(id == c.getAuthorityFatherId().longValue()){
					++count;
					long lid = c.getId();
					//如果角色资源列表中有该资源则选中
					c.setCheck(ids.contains(lid)?true:false);
					addTAuthoritys(id,c,map,size);
					mapHandle(array,lid,ids,map);
				}
				
			}
		
	}
	
	/**
	 * 保存区域列表
	 * @param pid
	 * @param leftShowAu
	 * @param t
	 */
	private void addTAuthoritys(long pid,TAuthority t,Map<Long , List<TAuthority>> map,int size){
			List<TAuthority> sublist = !map.containsKey(pid)? new ArrayList<TAuthority>(size):map.get(pid);
			sublist.add(t);
			map.put(pid, sublist);
	}
	
	/**
	 * 复制
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<TAuthority> copy(){
		synchronized (SynAuMap) {
			List<TAuthority> cList = null;
			if(null != authorities &&!authorities.isEmpty()){
				try {
					ByteArrayInputStream  bin=new ByteArrayInputStream(bout.toByteArray());
					ObjectInputStream objIn = new ObjectInputStream(bin);
					cList = (List<TAuthority>)objIn.readObject();
					objIn.close();
				} catch (Exception e) {
				}
			}
			return cList;
		}
	}
	
	/**
	 * 获取用户权限内
	 * @param auid 用户拥有资源id
	 * @param subAu 按钮权限存放容器
	 * @param leftShowAu 左侧导航栏显示列表
	 */
	public void getUserAuth(List<Long> auid,Map<String, Boolean> btnAu,Map<Long, List<TAuthority>> leftShowAu,List<String> accessScope){
			for(long l : auid){
				//存在容器中
				if(authoritieIndex.containsKey(l)){
					//获取id对于元素下标
					int index  = authoritieIndex.get(l);
					//获取元素
					TAuthority t = authorities.get(index);
					//url
					String url = t.getAuthorityUrl();
					//将url添加到访问范围权限中
					if(!url.equals("")){
						accessScope.add(url);
					}
					//元素是父元素
					if(parents.containsKey(l)){
						long pid =t.getAuthorityFatherId();
						addLeftList(pid,leftShowAu,t,parents.get(pid));
					}else{
						//按钮元素
						btnAu.put(url, true);
					}
					
				}
			}
			sort(leftShowAu);
		
	} 
	
	
	private void sort(Map<Long, List<TAuthority>> leftShowAu){
		Set<Long> set = leftShowAu.keySet();
		for(Long key : set){
			List<TAuthority> list = leftShowAu.get(key);
			Collections.sort(list);
		}
	}
	
	/**
	 * 获取用户权限内
	 * @param auid 用户拥有资源id
	 * @param subAu 按钮权限存放容器
	 * @param leftShowAu 左侧导航栏显示列表
	 */
	public void getAdminUser(Map<String, Boolean> btnAu,Map<Long, List<TAuthority>> leftShowAu,List<String> accessScope){
			for(TAuthority t : authorities){
				//url
				String url = t.getAuthorityUrl();
				//将url添加到访问范围权限中
				if(!url.equals("")){
					accessScope.add(url);
				}
				
				//元素是父元素
				if(parents.containsKey(t.getId())){
					long pid =t.getAuthorityFatherId();
					addLeftList(pid,leftShowAu,t,parents.get(pid));
				}else{
					//按钮元素
					btnAu.put(url, true);
				}
			}	
	} 
	
	
	
	
	/**
	 * 保存界面左侧列表
	 * @param pid 元素父id
	 * @param leftShowAu 存储容器
	 * @param t 当前元素
	 * @param count 父元素中包含子元素数量
	 */
	private void addLeftList(long pid,Map<Long, List<TAuthority>> leftShowAu,TAuthority t,int count){
			List<TAuthority> sublist = !leftShowAu.containsKey(pid)?new ArrayList<TAuthority>(count):leftShowAu.get(pid);
			sublist.add(t);
			leftShowAu.put(pid, sublist);
	}

	
}
