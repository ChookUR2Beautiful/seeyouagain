package com.xmniao.xmn.core.market.service.pay.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.request.IDRequest;
import com.xmniao.xmn.core.common.request.market.pay.EditAddressRequest;
import com.xmniao.xmn.core.common.request.market.pay.EditIsDefaultRequest;
import com.xmniao.xmn.core.market.dao.MarketReceivAddressDao;
import com.xmniao.xmn.core.market.entity.pay.ReceivingAddress;
import com.xmniao.xmn.core.market.service.pay.ShipingAddressService;
import com.xmniao.xmn.core.util.DateUtil;

/**
 * 
* @projectName: xmnService 
* @ClassName: ShipingAddressServiceImpl    
* @Description:收货地址实现类   
* @author: liuzhihao   
* @date: 2016年12月21日 上午10:26:59
 */
@Service
public class ShipingAddressServiceImpl implements ShipingAddressService{

	@Autowired
	private MarketReceivAddressDao receivAddressDao;
	
	@Autowired
	private SessionTokenService sessionTokenService;
	
	
	/**
	 * 查询收货地址列表
	 * @param baseRequest
	 * @return
	 */
	public List<Map<Object,Object>> findAll(BaseRequest baseRequest){
		List<Map<Object,Object>> result = new ArrayList<Map<Object,Object>>();
		//获取用户ID
		String uid = ObjectUtils.toString(sessionTokenService.getStringForValue(baseRequest.getSessiontoken()));
		
		//通过用户ID查询所有收货地址
		List<ReceivingAddress> ras = new ArrayList<ReceivingAddress>();
		
		//查询条件
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("uid", uid);//用户ID
		map.put("dstatus", 0);//数据状态 默认0 0正常 1已删除
		
		ras = receivAddressDao.findAllByUid(map);
		
		if(!ras.isEmpty()){
			for(ReceivingAddress ra : ras){
				Map<Object,Object> ramap = new HashMap<Object,Object>();
				ramap.put("id", ra.getId());//收货地址ID
				ramap.put("username", ObjectUtils.toString(ra.getUsername()));//用户名
				ramap.put("phone", ObjectUtils.toString(ra.getPhoneid()));//手机号码
				ramap.put("province", ObjectUtils.toString(ra.getProvince()));//省名称
				ramap.put("city", ObjectUtils.toString(ra.getCity()));//城市名称
				ramap.put("area", ObjectUtils.toString(ra.getAreaname()));//区名称
				ramap.put("address", ObjectUtils.toString(ra.getAddress()));//地址
				ramap.put("isDefault", ra.getIsdefault());//是否为默认地址
				
				result.add(ramap);
			}
		}
		
		return result;
	}
	
	

	/**
	 * 查看收货地址信息
	 * @param idRequest
	 * @return
	 */
	public Map<Object,Object> findOneById(IDRequest idRequest){
		
		Map<Object,Object> map = new HashMap<Object,Object>();
		
		//通过收货地址ID查询收货详情
		ReceivingAddress ra = receivAddressDao.selectByPrimaryKey(idRequest.getId());
		
		if(ra != null){
			map.put("id", ra.getId());
			map.put("username", ObjectUtils.toString(ra.getUsername()));//用户姓名
			map.put("phone", ObjectUtils.toString(ra.getPhoneid()));//用户联系电话
			map.put("provinceId", ObjectUtils.toString(ra.getProvinceid()));
			map.put("province", ObjectUtils.toString(ra.getProvince()));
			map.put("cityId", ObjectUtils.toString(ra.getCityid()));
			map.put("city", ObjectUtils.toString(ra.getCity()));
			map.put("areaId", ObjectUtils.toString(ra.getAreaid()));
			map.put("area", ObjectUtils.toString(ra.getAreaname()));
			map.put("address", ObjectUtils.toString(ra.getAddress()));
			map.put("isDefault", ra.getIsdefault());//默认0 0不是默认地址 1是默认地址，添加第1条地址时默认为1
		}
		
		return map;
	}
	
	
	/**
	 * 新增收货地址
	 * @param addAddressRequest
	 * @return
	 */
	public int add(EditAddressRequest editAddressRequest){
		int result = 0;//返回结果
		//获取用户ID
		String uid = ObjectUtils.toString(sessionTokenService.getStringForValue(editAddressRequest.getSessiontoken()));
		//初始化收货地址对象
		ReceivingAddress ra = new ReceivingAddress();
		ra.setIsdefault(0);//默认0 0不是默认地址 1是默认地址，添加第1条地址时默认为1
		//判断是否设置为默认地址
		if(editAddressRequest.getType() == 1){
			//查询是否存在已有的默认地址
			Map<Object,Object> isDefaultMap = new HashMap<Object,Object>();
			isDefaultMap.put("uid", uid);
			isDefaultMap.put("isDefault", 1);
			try{
				ReceivingAddress address = receivAddressDao.findOneByUid(isDefaultMap);
				if(address != null){
					address.setIsdefault(0);//历史默认收货地址信息状态修改为非默认状态
					
					result = receivAddressDao.updateByPrimaryKeySelective(address);
					
					if(result != 0){//修改成功 则设置新增地址默认状态为默认状态
						ra.setIsdefault(1);//默认0 0不是默认地址 1是默认地址，添加第1条地址时默认为1
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}else{
			//判断用户新增的收货地址是否为第一条
			Map<Object,Object> summap = new HashMap<Object,Object>();
			summap.put("uid", uid);
			summap.put("dstatus", 0);//数据状态 默认0 0正常 1已删除
			try{
				result = receivAddressDao.sumReceivingAddressCounts(summap);
			}catch(Exception e){
				e.printStackTrace();
			}
			if(result == 0){
				//为第一条收货地址信息,则收货地址状态为默认1
				ra.setIsdefault(1);////默认0 0不是默认地址 1是默认地址，添加第1条地址时默认为1
			}
		}
		ra.setUsername(editAddressRequest.getUsername());//收件人姓名
		ra.setPhoneid(editAddressRequest.getPhone());//联系电话
		ra.setProvinceid(editAddressRequest.getProvinceId());//省ID
		ra.setProvince(editAddressRequest.getProvinceName());//省名称
		ra.setCityid(editAddressRequest.getCityId());//城市ID
		ra.setCity(editAddressRequest.getCityName());//城市名称
		ra.setAreaid(editAddressRequest.getAreaId());//区ID
		ra.setAreaname(editAddressRequest.getAreaName());//区名称
		ra.setAddress(editAddressRequest.getAddress());//详细地址
		ra.setUid(Integer.parseInt(uid));//用户ID
		ra.setDstatus(0);//正常
		ra.setRdate(DateUtil.now());//创建时间
		
		try{
			result = receivAddressDao.insertSelective(ra);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}


	/**
	 * 修改收回地址信息
	 * @param editAddressRequest
	 * @return
	 */
	public int update(EditAddressRequest editAddressRequest){
		int result = 0;
		//获取用户ID
		String uid = ObjectUtils.toString(sessionTokenService.getStringForValue(editAddressRequest.getSessiontoken()));
		
		//现任对象
		ReceivingAddress updatera = new ReceivingAddress();
		//前任对象
		ReceivingAddress beforera = new ReceivingAddress();
		try{
			updatera = receivAddressDao.selectByPrimaryKey(editAddressRequest.getId());
		}catch(Exception e){
			e.printStackTrace();
		}
		if(updatera != null){
			//判断用户是否修改了地址的默认状态
			if(editAddressRequest.getType() == 1){
				//查询用户之前是否存在默认地址
				Map<Object,Object> summap = new HashMap<Object,Object>();
				summap.put("uid", uid);
				summap.put("isDefault", 1);//默认0 0不是默认地址 1是默认地址，添加第1条地址时默认为1
				try{
					beforera = receivAddressDao.findOneByUid(summap);
				}catch(Exception e){
					e.printStackTrace();
				}
				
				if(beforera != null){
					//把曾经的默认地址修改为非默认地址
					beforera.setIsdefault(0);//默认0 0不是默认地址 1是默认地址，添加第1条地址时默认为1
					
					try{
						result = receivAddressDao.updateByPrimaryKeySelective(beforera);
					}catch(Exception e){
						e.printStackTrace();
					}
					
					if(result != 0){
						//修改当前的收货地址默认状态
						updatera.setIsdefault(1);//默认0 0不是默认地址 1是默认地址，添加第1条地址时默认为1
						updatera.setUsername(editAddressRequest.getUsername());//收件人姓名
						updatera.setPhoneid(editAddressRequest.getPhone());//收件人电话
						updatera.setProvinceid(editAddressRequest.getProvinceId());//省ID
						updatera.setProvince(editAddressRequest.getProvinceName());//省名称
						updatera.setCityid(editAddressRequest.getCityId());//城市ID
						updatera.setCity(editAddressRequest.getCityName());//城市名称
						updatera.setAreaid(editAddressRequest.getAreaId());//区ID
						updatera.setAreaname(editAddressRequest.getAreaName());//区名称
						updatera.setAddress(editAddressRequest.getAddress());//收货地址
						updatera.setUdate(DateUtil.now());//修改时间
						
						try{
							result = receivAddressDao.updateByPrimaryKeySelective(updatera);//修改现任对象
						}catch(Exception e){
							e.printStackTrace();
						}
					}
				}
				
			}else{
				//获取现任对象的原始状态
				int isDefault = updatera.getIsdefault();
				
				updatera.setUsername(editAddressRequest.getUsername());//收件人姓名
				updatera.setPhoneid(editAddressRequest.getPhone());//收件人电话
				updatera.setProvinceid(editAddressRequest.getProvinceId());//省ID
				updatera.setProvince(editAddressRequest.getProvinceName());//省名称
				updatera.setCityid(editAddressRequest.getCityId());//城市ID
				updatera.setCity(editAddressRequest.getCityName());//城市名称
				updatera.setAreaid(editAddressRequest.getAreaId());//区ID
				updatera.setAreaname(editAddressRequest.getAreaName());//区名称
				updatera.setAddress(editAddressRequest.getAddress());//收货地址
				updatera.setUdate(DateUtil.now());//修改时间
				
				if(isDefault == 1){
					//从前任对象中随机一个对象修改收货默认状态
					Map<Object,Object> map = new HashMap<Object,Object>();
					map.put("uid", uid);
					map.put("dstatus", 0);//收货地址状态 0 正常 1删除
					map.put("isDefault", 0);//默认地址
					List<ReceivingAddress> ras = receivAddressDao.findAllIsNotDefaultByUid(map);
					if(ras != null && !ras.isEmpty()){
						beforera = ras.get(0);
						
						beforera.setIsdefault(1);
						try{
							result = receivAddressDao.updateByPrimaryKeySelective(beforera);
						}catch(Exception e){
							e.printStackTrace();
						}
					}else{
						isDefault = 1;
					}
				}
				
				updatera.setIsdefault(isDefault);
				
				try{
					result = receivAddressDao.updateByPrimaryKeySelective(updatera);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	
	/**
	 * 修改收货地址状态
	 * @return
	 */
	public int updateIsDefault(EditIsDefaultRequest editIsDefaultRequest){
		int result = 0;
		//获取用户ID
		String uid = ObjectUtils.toString(sessionTokenService.getStringForValue(editIsDefaultRequest.getSessiontoken()));
		
		//现任对象
		ReceivingAddress updatera = new ReceivingAddress();
		//前任对象
		ReceivingAddress beforera = new ReceivingAddress();
		try{
			updatera = receivAddressDao.selectByPrimaryKey(editIsDefaultRequest.getId());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(updatera != null){
			Integer isDefault = updatera.getIsdefault();
			if(isDefault == 1){
				result = 1;//不做操作
			}else{
				//修改前对象的默认状态
				Map<Object,Object> beforemap = new HashMap<Object,Object>();
				beforemap.put("uid", uid);
				beforemap.put("isDefault", 1);
				beforemap.put("dstatus", 0);
				try{
					beforera = receivAddressDao.findOneByUid(beforemap);
				}catch(Exception e){
					e.printStackTrace();
				}
				
				if(beforera != null){
					beforera.setIsdefault(0);//修改前任默认状态
					
					try{
						result = receivAddressDao.updateByPrimaryKeySelective(beforera);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				
				if(result != 0){
					//修改现任默认状态
					updatera.setIsdefault(1);
					
					try{
						
						result = receivAddressDao.updateByPrimaryKeySelective(updatera);
						
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		}
		
		return 0;
	}

	
	/**
	 * 删除收货地址
	 * @param idRequest
	 * @return
	 */
	public int remove(IDRequest idRequest){
		int result = 0;
		
		//获取用户ID
		String uid = ObjectUtils.toString(sessionTokenService.getStringForValue(idRequest.getSessiontoken()));
		
		//查询收货地址详情
		ReceivingAddress ra = receivAddressDao.selectByPrimaryKey(idRequest.getId());
		
		if(ra != null){
			int isDefault = ra.getIsdefault();
			
			//删除收货地址
			try{
				result = receivAddressDao.deleteByPrimaryKey(idRequest.getId());
			}catch(Exception e){
				e.printStackTrace();
			}
			
			if(result != 0){//删除成功
				
				if(isDefault == 1){//默认地址
					Map<Object,Object> map = new HashMap<Object,Object>();
					map.put("uid", uid);
					map.put("dstatus", 0);//收货地址状态 0 正常 1删除
					map.put("isDefault", 0);//默认地址
					List<ReceivingAddress> ras = receivAddressDao.findAllIsNotDefaultByUid(map);
					
					if(ras != null && !ras.isEmpty()){
						
						ReceivingAddress updatera = ras.get(0);
						
						updatera.setIsdefault(1);//默认0 0不是默认地址 1是默认地址，添加第1条地址时默认为1
						
						//修改
						try{
							result = receivAddressDao.updateByPrimaryKeySelective(updatera);
						}catch(Exception e){
							e.printStackTrace();
						}
					}
				}
			}
		}
		return result;
	}


}
