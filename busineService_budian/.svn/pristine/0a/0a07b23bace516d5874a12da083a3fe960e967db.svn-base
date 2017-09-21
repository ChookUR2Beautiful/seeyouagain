package com.xmniao.service.sellerAddress;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.common.MapUtil;
import com.xmniao.dao.sellerAddress.SellerAddressDao;
import com.xmniao.thrift.busine.common.FailureException;
import com.xmniao.thrift.busine.sellerAddress.SellerAddressService;

@Service("SellerAddressServiceImpl")
public class SellerAddressServiceImpl implements SellerAddressService.Iface {

	@Autowired
	private SellerAddressDao sellerAddressDao;
	
	// 初始化日志类
	private final Logger log = Logger.getLogger(SellerAddressServiceImpl.class);

	/**
	 * 添加商家地址
	 */
	@Override
	@Transactional(rollbackFor={FailureException.class,Exception.class,RuntimeException.class})
	public Map<String, String> add(Map<String, String> sellerMap)
			throws FailureException, TException {
		log.info("添加商家地址add:" + sellerMap);
		Map<String, String> resultMap = new HashMap<>();
		try {
			if (StringUtils.isNotBlank(sellerMap.get("sellerid"))
					&& StringUtils.isNotBlank(sellerMap.get("is_default"))) {

				// 设置省名称
				if (StringUtils.isNotBlank(sellerMap.get("province_id"))) {
					Map<String, Object> province = sellerAddressDao
							.getArea(sellerMap.get("province_id"));
					sellerMap.put("province", province.get("title") + "");
				}
				// 设置市名称
				if (StringUtils.isNotBlank(sellerMap.get("city_id"))) {
					Map<String, Object> city = sellerAddressDao
							.getArea(sellerMap.get("city_id"));
					sellerMap.put("city", city.get("title") + "");
				}
				// 设置区名称
				if (StringUtils.isNotBlank(sellerMap.get("area_id"))) {
					Map<String, Object> area = sellerAddressDao
							.getArea(sellerMap.get("area_id"));
					sellerMap.put("area_name", area.get("title") + "");
				}
				
				Map<String,String> paraMap = new HashMap<>();
				paraMap.put("sellerid", sellerMap.get("sellerid"));
				paraMap.put("is_default","1");
				List<Map<String, Object>> list = sellerAddressDao.getList(paraMap);
				
				//添加新的默认地址，取消商家原默认地址
				if (list != null) {
				if (StringUtils.isNotBlank(sellerMap.get("is_default"))&& "1".equals(sellerMap.get("is_default"))){
					
						if(list.size()==1){
							paraMap.put("id",list.get(0).get("id")+"");
							paraMap.put("is_default","0");
							Integer update = sellerAddressDao.update(paraMap);
							if (update != 1) {
								log.error("取消商家原默认地址失败");
								throw new FailureException(1,"取消商家原默认地址失败");
							}
							
						}else if(list.size() != 0) {
							log.error("更新商家地址失败，数据库数据异常：商家"+sellerMap.get("sellerid")+"存在多个默认地址");
							throw new FailureException(1,"更新商家地址失败，数据库数据异常");
						}
					}else if(list.size() == 0){
						sellerMap.put("is_default","1");
					}
				}
				
				sellerMap.put("create_date",getFormatDate());//创建时间
				sellerMap.put("update_date",getFormatDate());//更新时间
				
				// 添加商家地址
				sellerAddressDao.add(sellerMap);
				log.info("添加商家地址成功");
				resultMap.put("id",String.valueOf(sellerMap.get("id")));
				return resultMap;
			} else {
				log.error("添加商家地址失败，参数异常");
				throw new FailureException(1, "添加商家地址失败，参数异常");
			}
		} catch (Exception e) {
			log.error("添加商家地址失败", e);
			throw new FailureException(1, "添加商家地址失败");
		}
	}

	/**
	 * 删除商家地址
	 */
	@Override
	@Transactional(rollbackFor={FailureException.class,Exception.class,RuntimeException.class})
	public Map<String, String> deleteSellerAddress(Map<String, String> sellerMap)
			throws FailureException, TException {
		
		log.info("删除商家地址deleteSellerAddress:" + sellerMap);
		Map<String,String> resultMap = new HashMap<>();
		
		try {
			if (StringUtils.isNotBlank(sellerMap.get("id"))) {
				
				resultMap.put("id",sellerMap.get("id"));
				
				List<Map<String, Object>> list = sellerAddressDao.getList(sellerMap);
				if (list == null || list.size()==0) {
					log.info("商家地址id："+sellerMap.get("id")+"不存在");
				}
				Integer result = sellerAddressDao.delete(sellerMap);
				if (result == 1) {
					log.info("删除商家地址成功");
					return resultMap;
				}else {
					log.error("删除商家地址成功");
					throw new FailureException(1,"删除商家地址失败");
				}
			}else {
				log.error("传入参数有误");
				throw new FailureException(1,"传入参数有误");
			}
		} catch (Exception e) {
			log.error("删除商家地址失败",e);
			throw new FailureException(1,"删除商家地址失败");
		}
	}

	/**
	 * 更新商家地址
	 */
	@Override
	@Transactional(rollbackFor={FailureException.class,Exception.class,RuntimeException.class})
	public Map<String, String> update(Map<String, String> sellerMap)
			throws FailureException, TException {
		log.info("更新商家地址update:" + sellerMap);
		Map<String,String> resultMap = new HashMap<>();
		
		try {
			if (StringUtils.isNotBlank(sellerMap.get("id"))&&StringUtils.isNotBlank(sellerMap.get("sellerid"))) {
				
				resultMap.put("id",sellerMap.get("id"));
				
				//修改新的默认地址，取消商家原默认地址
				if (StringUtils.isNotBlank(sellerMap.get("is_default"))&& "1".equals(sellerMap.get("is_default"))){
					Map<String,String> paraMap = new HashMap<>();
					paraMap.put("sellerid", sellerMap.get("sellerid"));
					paraMap.put("is_default","1");
					List<Map<String, Object>> list = sellerAddressDao.getList(paraMap);
					if (list != null) {
						if(list.size()==1){
							paraMap.put("id",list.get(0).get("id")+"");
							paraMap.put("is_default","0");
							Integer update = sellerAddressDao.update(paraMap);
							if (update != 1) {
								log.error("取消商家原默认地址失败");
								throw new FailureException(1,"取消商家原默认地址失败");
							}
						}else if(list.size() != 0){
							log.error("更新商家地址失败，数据库数据异常：商家"+sellerMap.get("sellerid")+"存在多个默认地址");
							throw new FailureException(1,"更新商家地址失败，数据库数据异常");
						}
					}
				}
				sellerMap.put("update_date",getFormatDate());
				
				// 设置省名称
				if (StringUtils.isNotBlank(sellerMap.get("province_id"))) {
					Map<String, Object> province = sellerAddressDao
							.getArea(sellerMap.get("province_id"));
					sellerMap.put("province", province.get("title") + "");
				}
				// 设置市名称
				if (StringUtils.isNotBlank(sellerMap.get("city_id"))) {
					Map<String, Object> city = sellerAddressDao
							.getArea(sellerMap.get("city_id"));
					sellerMap.put("city", city.get("title") + "");
				}
				// 设置区名称
				if (StringUtils.isNotBlank(sellerMap.get("area_id"))) {
					Map<String, Object> area = sellerAddressDao
							.getArea(sellerMap.get("area_id"));
					sellerMap.put("area_name", area.get("title") + "");
				}
				
				//更新商家地址
				Integer result = sellerAddressDao.update(sellerMap);
				
				if (result==1) {
					log.info("更新商家地址成功");
					return resultMap;
				}else {
					log.error("更新商家地址失败");
					throw new FailureException(1,"更新商家地址失败");
				}
			}else {
				log.error("传入参数有误,记录id或sellerid不能为空");
				throw new FailureException(1,"传入参数有误");
			}
		} catch (Exception e) {
			log.error("更新商家地址失败",e);
			throw new FailureException(1,"更新商家地址失败");
		}
	}

	/**
	 * 获取商家地址
	 */
	@Override
	public List<Map<String, String>> getSellerAddress(
			Map<String, String> sellerMap) throws FailureException, TException {
		log.info("获取商家地址getSellerAddress:" + sellerMap);
		List<Map<String,String>> resultList = new ArrayList<>();
		try {
			if (StringUtils.isNotBlank(sellerMap.get("sellerid"))) {
				
				List<Map<String, Object>> list = sellerAddressDao.getList(sellerMap);
				for (Map<String, Object> map : list) {
					resultList.add(MapUtil.formatMapStr(map));
				}
				
				log.info("获取商家地址成功");
				return resultList;
				
			}else {
				log.error("商家id不能为空");
				throw new FailureException(1,"商家id不能为空");
			}
		} catch (Exception e) {
			log.error("获取商家地址失败");
			throw new FailureException(1,"获取商家地址失败");
		}
	}
	
	private String getFormatDate(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	
	/**
	 * 获取区域下级信息
	 */
	@Override
	public List<Map<String, String>> getAreaChildren(
			Map<String, String> sellerMap) throws FailureException, TException {
		log.info("获取区域下级信息getAreaChildren:"+sellerMap);
		List<Map<String,String>> list = new ArrayList<>();
		try {
			if (StringUtils.isNotBlank(sellerMap.get("area_id"))) {
				List<Map<String, Object>> areaList = sellerAddressDao.areaList(sellerMap);
				for (Map<String, Object> map : areaList) {
					list.add(MapUtil.formatMapStr(map));
				}
				log.info("获取区域下级信息成功");
				return list;
			}else {
				log.error("传入参数id为空");
				throw new FailureException(1,"传入参数id为空");
			}
		} catch (Exception e) {
			log.error("获取区域下级信息失败",e);
			throw new FailureException(1,"获取区域下级信息失败");
		}
	}
	
	/**
	 * 根据区域名称获取区域信息
	 */
	@Override
	public Map<String, String> getAreaByName(Map<String, String> sellerMap)
			throws FailureException, TException {
		log.info("根据区域名称获取区域信息getAreaByName:"+sellerMap);
		
		if (StringUtils.isNotBlank(sellerMap.get("title"))) {
			Map<String, Object> areaByTitle = sellerAddressDao.getAreaByTitle(sellerMap);
			if (areaByTitle == null) {
				log.error("该区域不存在"+sellerMap);
				throw new FailureException(1,"该区域不存在");
			}
			return MapUtil.formatMapStr(areaByTitle);
		}else {
			log.error("传入参数title为空");
			throw new FailureException(1,"传入参数title为空");
		}
	}
}
