package com.xmniao.xmn.core.system_settings.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.system_settings.dao.RoleAreaDao;
import com.xmniao.xmn.core.system_settings.entity.TAuthorityArea;
import com.xmniao.xmn.core.system_settings.entity.TRole;
import com.xmniao.xmn.core.system_settings.entity.TRoleArea;
import com.xmniao.xmn.core.util.StringUtils;
import com.xmniao.xmn.core.util.handler.AuthorityAreaHandler;

@Service

public class RoleAreaService extends BaseService<TRoleArea>{
	@Autowired RoleAreaDao roleAreaDao;
	
	@Autowired
	private RoleService roleService;
	
	@Override
	protected RoleAreaDao getBaseDao() {
		return roleAreaDao;
	}
	
	public List<String> getAidByFid(Map<String, Object> map){
		return roleAreaDao.getAidByFid(map);
	}
	
	
	public TRoleArea getRoleArea(TRoleArea roleArea){
		return roleAreaDao.getRoleArea(roleArea);
	}
	
	public void save(TRoleArea[] areaList,String userName){
		if(areaList!=null&&areaList.length>0){
			
			Long authorityId =areaList[0].getAuthorityId();
		
			TAuthorityArea authorityArea = AuthorityAreaHandler.getInstance().getAuthorityIdByAuthority(authorityId);
			if(authorityArea==null){
				return;
			}
			Date date = new Date();
			Integer id = areaList[0].getId();
			Long roleId = areaList[0].getRoleId();
			TRoleArea roleArea = new  TRoleArea();
			roleArea.setRoleId(roleId);
			roleArea.setTableName(authorityArea.getTableName());
			roleArea.setAuthorityId(authorityId);
			setAreaInfo(roleArea,areaList);
			if(id==null){
				roleArea.setDateCreated(date);
				roleArea.setCreator(userName);
				roleArea.setUpdator(userName);
				roleArea.setDateUpdated(date);
				roleAreaDao.add(roleArea);
				String[] s={"角色编号",roleId.toString(),"新增区域权限"};
				fireLoginEvent(s);
			}else{
				roleArea.setId(id);
				roleArea.setUpdator(userName);
				roleArea.setDateUpdated(date);
				roleAreaDao.update(roleArea);
				String[] s={"角色编号",roleId.toString(),"修改区域权限","修改"};
				fireLoginEvent(s);
			}
			
		}
		
		
	}
	
	
	
	private void setAreaInfo(TRoleArea t,TRoleArea[] areaList){
		StringBuilder pBuilder= new StringBuilder();
		StringBuilder cBuilder= new StringBuilder();
		StringBuilder aBuilder= new StringBuilder();
		for(TRoleArea roleArea : areaList){
			String provine = roleArea.getProvince();
			if(StringUtils.hasLength(provine)){
				pBuilder.append(provine);
				pBuilder.append(",");
			}
			String city = roleArea.getCity();
			if(StringUtils.hasLength(city)){
				cBuilder.append(city);
				cBuilder.append(",");
			}
			String area = roleArea.getArea();
			if(StringUtils.hasLength(area)){
				aBuilder.append(area);
				aBuilder.append(",");
			}
			
		}
		if(pBuilder.length()>0){
			t.setProvince(pBuilder.substring(0, pBuilder.length()-1));
		}
		if(cBuilder.length()>0){
			t.setCity(cBuilder.substring(0, cBuilder.length()-1));
		}
		if(aBuilder.length()>0){
			t.setArea(aBuilder.substring(0, aBuilder.length()-1));
		}
		
	}
	

	
	public ModelAndView getAuthorityAreaInfo(ModelAndView mv,Long roleId,Long authorityId){
		
		if(authorityId!=null&&roleId!=null){
			TRole tr = roleService.getObject(roleId);
			
			if(tr!=null){
				mv.addObject("roleId",roleId);
				AuthorityAreaHandler authorityAreaHandler = AuthorityAreaHandler.getInstance();
				TAuthorityArea authorityArea = authorityAreaHandler.getAuthorityIdByAuthority(authorityId);
				if(authorityArea!=null){
					Long fid = authorityArea.getAuthorityFatherId();
					if( fid!=null){
						TAuthorityArea fauthorityArea = authorityAreaHandler.getAuthorityIdByAuthority(fid);
						mv.addObject("fname", fauthorityArea.getAuthorityName());
						mv.addObject("mname", authorityArea.getAuthorityName());
						mv.addObject("authorityId", authorityId);
					}
					TRoleArea roleArea = new TRoleArea();
					roleArea.setRoleId(roleId);
					roleArea.setAuthorityId(authorityId);
					List<TRoleArea> roleAreas = getList(roleArea);
					if(roleAreas!=null&&!roleAreas.isEmpty()){
						mv.addObject("roleArea", roleAreas.get(0));
					}
				}
				
			}
			
		}
		return mv;
	}

}
