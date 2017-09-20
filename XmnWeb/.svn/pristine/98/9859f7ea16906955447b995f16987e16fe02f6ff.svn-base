package com.xmniao.xmn.core.util.dataAuthority.handler;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.reflection.MetaObject;

import com.xmniao.xmn.core.system_settings.entity.TAuthorityArea;
import com.xmniao.xmn.core.system_settings.entity.TRoleArea;
import com.xmniao.xmn.core.system_settings.service.RoleAreaService;
import com.xmniao.xmn.core.util.StringUtils;
import com.xmniao.xmn.core.util.dataAuthority.AbstrctDataAuthorityHandler;
import com.xmniao.xmn.core.util.handler.AuthorityAreaHandler;
import com.xmniao.xmn.core.util.handler.AuthorityHandler;
import com.xmniao.xmn.core.util.holder.DataAuthorityHolder;
import com.xmniao.xmn.core.util.holder.DataAuthorityInfo;
import com.xmniao.xmn.core.util.mybatis.interceptor.DataAuthorityInterceptor;

public class AreaDataAuthorityHandler extends AbstrctDataAuthorityHandler {

	private static final Class<RoleAreaService> CLAZZ = RoleAreaService.class;

	RoleAreaService service = null;

	private AuthorityAreaHandler authorityAreaHandler = AuthorityAreaHandler
			.getInstance();

	private AuthorityHandler authorityHandler = AuthorityHandler
			.getAuthorityHanlde();

	private static final String PROVINCE = "province";
	private static final String CITY = "city";
	private static final String AREA = "area";

	

	private boolean hashValue(Object objValue) {
		return objValue != null ? StringUtils.hasLength((String) objValue)
				: false;
	}

	@Override
	public String buildSQL(Object parameterObject) {
		if (service == null) {
			service = context.getBean(CLAZZ);
		}

		DataAuthorityInfo authorityInfo = DataAuthorityHolder
				.getDataAuthorityInfo();
		TAuthorityArea authorityArea = getAuthorityArea(authorityInfo);
		if (authorityArea != null) {
			Long roleid = authorityInfo.getRoleId();
			if (roleid == null) {
				return null;
			}
			TRoleArea roleArea = new TRoleArea();
			roleArea.setRoleId(roleid);
			roleArea.setAuthorityId(authorityArea.getAuthorityId());
			TRoleArea newRoleArea = service.getRoleArea(roleArea);
			if (null != newRoleArea) {
				return buildSQL(newRoleArea, authorityArea.getPreName(),
						parameterObject);
			}
		}
		return null;
	}

	private String buildSQL(TRoleArea roleArea, String prefix,
			Object parameterObject) {
		prefix = StringUtils.hasLength(prefix) ? prefix + "." : "";
		StringBuilder sqlBuilder = new StringBuilder();
		String provine = roleArea.getProvince();
		Collection<String> provineSet = StringUtils.strToCollection(provine,",");
		String city = roleArea.getCity();
		Collection<String> citySet = StringUtils.strToCollection(city, ",");
		String area = roleArea.getArea();
		Collection<String> areaSet = StringUtils.strToCollection(area, ",");

		MetaObject context = MetaObject.forObject(parameterObject,DataAuthorityInterceptor.DEFAULT_OBJECT_FACTORY, DataAuthorityInterceptor.DEFAULT_OBJECT_WRAPPER_FACTORY, DataAuthorityInterceptor.DEFAULT_REFLECTOR_FACTORY);
		Object provinceValue = context.getValue(PROVINCE);
		/**
		 * 判断区域权限选择
		 * 
		 * 1 选择省 或者 选择的省属于该用户所属角色的区域权限之内
		 * 	 false : 查询该用户所属角色的区域权限 拼装sql并返回
		 *   true : 进入下一步判断
		 * 2 是否选择市 或者 选择的市属于该用户所属角色的区域权限之内 
		 * 	false : 根据前台选择的省编号查询该用户所属角色的区域权限数据中属于该省下面所有的市编号  , 然后根据市编号查询该用户所属角色的区域权限数据中的区编号, 拼装sql并返回.
		 * 	true : 进入下一步判断
		 * 3 是否选择区 或者 选择的区属于该用户所属角色的区域权限之内 
		 * 	false : 根据前台选择的市编号查询该用户所属角色的区域权限数据中属于该市下面所有的区编号, 拼装sql并返回.
		 * 	true : 返回
		 */
		if (hashValue(provinceValue) && provineSet.contains(provinceValue)) {
			Map<String, Object> map = new HashMap<>(2);

			Object cityValue = context.getValue(CITY);
			if (!hashValue(cityValue) || !citySet.contains(cityValue)) {

				map.put("pids",
						new HashSet<Object>(Arrays.asList(provinceValue)));
				map.put("aids", new HashSet<Object>(citySet));
				List<String> citys = service.getAidByFid(map);
				String cityVaules = org.apache.commons.lang.StringUtils.join(
						citys, ",");

				createFieldSQL(sqlBuilder, cityVaules, prefix + CITY);
				map.put("pids", new HashSet<Object>(citys));
				map.put("aids", new HashSet<Object>(areaSet));
				List<String> areas = service.getAidByFid(map);
				createFieldSQL(sqlBuilder,
						org.apache.commons.lang.StringUtils.join(areas, ","),
						prefix + AREA);

			} else {

				Object areaValue = context.getValue(AREA);
				if (!hashValue(areaValue) || !areaSet.contains(areaValue)) {
					map.put("pids",
							new HashSet<Object>(Arrays.asList(cityValue)));
					map.put("aids", new HashSet<Object>(areaSet));
					List<String> citys = service.getAidByFid(map);
					String cityVaules = org.apache.commons.lang.StringUtils
							.join(citys, ",");
					createFieldSQL(sqlBuilder, cityVaules, prefix + AREA);
				}
			}

		} else {
			if (!provineSet.isEmpty()) {
				createFieldSQL(sqlBuilder,
						org.apache.commons.lang.StringUtils.join(provineSet,
								","), prefix + PROVINCE);
			}
			if (!citySet.isEmpty()) {
				createFieldSQL(sqlBuilder,
						org.apache.commons.lang.StringUtils.join(citySet, ","),
						prefix + CITY);
			}
			if (!areaSet.isEmpty()) {
				createFieldSQL(sqlBuilder,
						org.apache.commons.lang.StringUtils.join(areaSet, ","),
						prefix + AREA);
			}
		}

		return sqlBuilder.toString();
	}

	private void createFieldSQL(StringBuilder sqlBuilder, String fieldValue,
			String fieldName) {
		sqlBuilder.append(String.format(" AND %s in ( %s )", fieldName,
				fieldValue));
	}

	@Override
	public boolean isHandlerMethod(String currentMethod) {

		TAuthorityArea authorityArea = getAuthorityArea(DataAuthorityHolder
				.getDataAuthorityInfo());
		if (authorityArea != null) {
			String[] methods = StringUtils.paresToArray(
					authorityArea.getMethodName(), ",");
			for (String method : methods) {
				if (currentMethod.endsWith(method)) {
					return true;
				}
			}
		}
		return false;
	}

	private TAuthorityArea getAuthorityArea(DataAuthorityInfo authorityInfo) {
		if (authorityInfo == null) {
			return null;
		}
		List<TAuthorityArea> list = authorityAreaHandler
				.getFidBySubAuthorityArea(authorityHandler
						.getIdByAuthorityFid(authorityHandler
								.getUrlById(authorityInfo.getUrl())));
		return list == null || list.isEmpty() ? null : list.get(0);

	}
}
