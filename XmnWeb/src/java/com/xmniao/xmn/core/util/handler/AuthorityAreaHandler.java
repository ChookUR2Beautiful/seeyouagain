package com.xmniao.xmn.core.util.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.system_settings.dao.RoleAreaDao;
import com.xmniao.xmn.core.system_settings.entity.TAuthorityArea;

public class AuthorityAreaHandler {
	
	private static final AuthorityAreaHandler AUTHORITY_AREA_HANDLER=new AuthorityAreaHandler();
	
	
	private List<TAuthorityArea> parent = new ArrayList<>();
	
	private Map<Long, List<TAuthorityArea>> sub =new HashMap<Long, List<TAuthorityArea>>();
	
	private Map<String, Long> urls =new HashMap<>();
	
	private Map<Long, TAuthorityArea> AuthorityAreas =new HashMap<>();
	
	
	private boolean ishandler=false;
	
	private AuthorityAreaHandler(){};
	
	
	public static AuthorityAreaHandler getInstance(){
		return AUTHORITY_AREA_HANDLER;
	}
	
	
	
	
	public void handler(RoleAreaDao roleAreaDao){
		if(!ishandler){
			List<TAuthorityArea> list = roleAreaDao.getAuthorityAreaList();
			setParentoRSubList(list);
			ishandler=true;
		}
	}
	
	
	private void setParentoRSubList(List<TAuthorityArea> list){
		for(TAuthorityArea authorityArea : list){
			Long authorityId = authorityArea.getAuthorityId();
			Long fid = authorityArea.getAuthorityFatherId();
			if(fid==null){
				parent.add(authorityArea);
			}else{
				
				List<TAuthorityArea> subList = sub.get(fid);
				subList = subList==null?new ArrayList<TAuthorityArea>():list;
				subList.add(authorityArea);
				sub.put(fid, subList);
				urls.put(authorityArea.getUrl(), authorityId);
			}
			AuthorityAreas.put(authorityId, authorityArea);
		}
		if(!parent.isEmpty()){
			Collections.sort(parent);
		}
		
	}
	
	public List<TAuthorityArea> getParentAuthorityAreaList(){
		return parent;
	}
	
	
	public Map<Long, List<TAuthorityArea>> getSubAuthorityArea(){
		return sub;
	}
	
	public List<TAuthorityArea> getFidBySubAuthorityArea(Long fid){
		return sub.get(fid);
	}
	
	public Long getUrlByAuthorityId(String url){
		return urls.get(url);
	}
	
	public TAuthorityArea getUrlByAuthority(String url){
		Long authorityId = getUrlByAuthorityId(url);
		if(authorityId==null){
			return null;
		}
		return getAuthorityIdByAuthority(authorityId);
	}
	
	public TAuthorityArea getAuthorityIdByAuthority(Long authorityId){
		return AuthorityAreas.get(authorityId);
	}
	
	
	
	

}
