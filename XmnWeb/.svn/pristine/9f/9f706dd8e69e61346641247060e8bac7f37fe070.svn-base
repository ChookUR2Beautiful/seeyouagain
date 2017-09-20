/**
 * 
 */
package com.xmniao.xmn.core.vstar.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.vstar.dao.FashionTicketSeatDao;
import com.xmniao.xmn.core.vstar.dao.FashionTicketsDao;
import com.xmniao.xmn.core.vstar.dao.TicketsDetailDao;
import com.xmniao.xmn.core.vstar.dao.TicketsPriceDao;
import com.xmniao.xmn.core.vstar.entity.FashionTicketSeat;
import com.xmniao.xmn.core.vstar.entity.FashionTickets;
import com.xmniao.xmn.core.vstar.entity.TicketsDetail;

/**
 * 
 * 项目名称：XmnWeb_vstar
 * 
 * 类名称：FashionTicketsService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年6月1日 上午10:27:47 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class FashionTicketsService extends BaseService<FashionTickets>{

	@Autowired
	private FashionTicketsDao fashionTicketsDao;
	
	@Autowired
	private FashionTicketSeatDao fashionTicketSeatDao;
	
	@Autowired
	private TicketsPriceDao ticketsPriceDao;
	
	@Autowired
	private TicketsDetailDao ticketsDetailDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return fashionTicketsDao;
	}

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月2日下午6:36:43 <br/>
	 * @param asList
	 * @return
	 */
	public boolean checkSeatHasPrice(List<String> asList) {
		List<Map<String,Object>> list=fashionTicketsDao.checkSeatHasPrice(asList);
		for (Map<String, Object> map : list) {
			if(0>=((Long)map.get("count"))){
				return false;
			}
		}
		return true;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void add(FashionTickets fashionTickets){
		fashionTicketsDao.add(fashionTickets);
		List<String> sids = Arrays.asList(fashionTickets.getFids().split(","));
		Byte supportSelectSeats = fashionTickets.getSupportSelectSeats();
		if(supportSelectSeats.intValue()==0){
			Integer totalSeats = fashionTickets.getTotalSeats();
			FashionTicketSeat fashionTicketSeat = fashionTicketSeatDao.getObject(Long.valueOf(sids.get(0)));
			fashionTicketSeat.setDefaultSeats(totalSeats);
			fashionTicketSeat.setTotalSeats(totalSeats);
			fashionTicketSeat.setStatus(new Byte("0"));
			fashionTicketSeat.setFid(fashionTickets.getId());
			fashionTicketSeat.setZoneRangeMin(1);
			fashionTicketSeat.setZoneRangeMax(1);
			fashionTicketSeat.setSeats(totalSeats);
			fashionTicketSeat.setNum(1);
			fashionTicketSeatDao.update(fashionTicketSeat);
			List<TicketsDetail> list= new LinkedList<>();
			for(int i=1;i<=totalSeats;i++){
				TicketsDetail ticketsDetail = new TicketsDetail();
				ticketsDetail.setFid(fashionTickets.getId());
				ticketsDetail.setSid(fashionTicketSeat.getId());
				ticketsDetail.setSellStatus(0);
				ticketsDetail.setUseStatus(0);
				ticketsDetail.setZoneNo(1);
				ticketsDetail.setSeatNo(i);
				list.add(ticketsDetail);
			}
			ticketsDetailDao.addBatch(list);
		}else{
			addSeat(fashionTickets, sids);
		}
		ticketsPriceDao.updateStateByIds(sids);
		
	}

	private void addSeat(FashionTickets fashionTickets, List<String> sids) {
		List<TicketsDetail> list= new LinkedList<>();
		fashionTicketSeatDao.activateIds(sids,fashionTickets.getId());
		List<FashionTicketSeat> fashionTicketSeats= fashionTicketSeatDao.getListByIds(sids);
		for (FashionTicketSeat fashionTicketSeat : fashionTicketSeats) {
			Integer zoneRangeMin = fashionTicketSeat.getZoneRangeMin();
			Integer seats = fashionTicketSeat.getSeats();
			Integer num = fashionTicketSeat.getNum();
			for(int i=1;i<=num;i++,zoneRangeMin++){
				for(int j=1;j<=seats;j++){
					TicketsDetail ticketsDetail = new TicketsDetail();
					ticketsDetail.setZoneNo(zoneRangeMin);
					ticketsDetail.setSeatNo(j);
					ticketsDetail.setFid(fashionTickets.getId());
					ticketsDetail.setSid(fashionTicketSeat.getId());
					ticketsDetail.setSellStatus(0);
					ticketsDetail.setUseStatus(0);
					list.add(ticketsDetail);
				}
				
			}
			
		}
		ticketsDetailDao.addBatch(list);
	}

	/**
	 * 方法描述：
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月3日下午1:43:45 <br/>
	 * @param id
	 * @param status 
	 */
	public void end(Integer id, Integer status) {
		fashionTicketsDao.end(id,status);
	}

	/**
	 * 方法描述：修改操作
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月6日下午5:19:47 <br/>
	 * @param fashionTickets
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateMethod(FashionTickets fashionTickets) {
		fashionTicketsDao.update(fashionTickets);
		List<String> sids = Arrays.asList(fashionTickets.getFids().split(","));
		List<FashionTicketSeat> seats = fashionTicketSeatDao.getByIdAndStatus(sids);
		List<String> updateSids = new ArrayList<>();
		for (FashionTicketSeat seat : seats) {
			updateSids.add(seat.getId().toString());
		}
		Byte supportSelectSeats = fashionTickets.getSupportSelectSeats();
		if(supportSelectSeats.intValue()==1&&updateSids.size()>0){
			addSeat(fashionTickets, updateSids);
		}
		ticketsPriceDao.updateStateByIds(sids);
	}

	/**
	 * 方法描述：获得出售中的场次
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月12日下午2:22:16 <br/>
	 * @return
	 */
	public List<FashionTickets> getSellIngTickets() {
		return fashionTicketsDao.getSellIngTickets();
	}

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月12日下午2:57:54 <br/>
	 * @param id
	 * @return
	 */
	public List<TicketsDetail> getSellingByTicketsId(Integer id) {
		return ticketsDetailDao.getSellingByTicketsId(id);
	}
	
}
