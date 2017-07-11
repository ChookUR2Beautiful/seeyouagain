package com.xmn.saas.service.address.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmn.saas.dao.address.SellerAddressDao;
import com.xmn.saas.dao.common.AreaDao;
import com.xmn.saas.entity.address.SellerAddress;
import com.xmn.saas.entity.common.Area;
import com.xmn.saas.service.address.SellerAddressService;

@Service
public class SellerAddressServiceImpl implements SellerAddressService{
	@Autowired
	private SellerAddressDao sellerAddressDao;
	
	@Autowired
	private AreaDao areaDao;

	@Override
	public List<SellerAddress> list(Integer sellerId) {
		return sellerAddressDao.list(sellerId);
	}

	@Override
	public SellerAddress selectByKey(Integer id, Integer sellerId) {
		
		return sellerAddressDao.selectByKey(id,sellerId);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void save(SellerAddress sellerAddress,Integer sellerId) {
		if(sellerAddress.getIsDefault()==1){
			sellerAddressDao.cancelDefault(sellerId);
		}
		sellerAddress.setCreateDate(new Date());
		sellerAddress.setUpdateDate(new Date());
		sellerAddress.setStatus(0);
		sellerAddressDao.insert(sellerAddress);
	}

	@Override
	public List<Area> listArea(Integer pid) {
			return  areaDao.findAreaByPid(pid);
	}

	@Override
	public String selectAreaName(Integer areaId) {
		if(areaId==null||areaId==0){
			throw new RuntimeException();
		}
		return areaDao.getName(areaId);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void update(SellerAddress sellerAddress,Integer sellerId) {
		if(sellerAddress.getIsDefault()==1){
			sellerAddressDao.cancelDefault(sellerId);
		}
		sellerAddress.setUpdateDate(new Date());
		sellerAddress.setStatus(0);
		sellerAddressDao.updateByPrimaryKey(sellerAddress);
	}

	@Override
	public void remove(Integer id, Integer sellerId) {
		sellerAddressDao.updateStatus(id,sellerId);
	}

	@Override
	public Integer countLastArea(Integer cityId) {
		return areaDao.countLastArea(cityId);
	}

	@Override
	public SellerAddress getDesfault(Integer sellerId) {
		return sellerAddressDao.selectDefault(sellerId);
	}
}
