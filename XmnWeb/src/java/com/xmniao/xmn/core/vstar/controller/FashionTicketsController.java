/**
 * 
 */
package com.xmniao.xmn.core.vstar.controller;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.common.service.CkeditorUpdateService;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.vstar.dao.FashionTicketsDao;
import com.xmniao.xmn.core.vstar.entity.FashionTicketSeat;
import com.xmniao.xmn.core.vstar.entity.FashionTickets;
import com.xmniao.xmn.core.vstar.entity.TicketsPrice;
import com.xmniao.xmn.core.vstar.service.FashionTicketSeatService;
import com.xmniao.xmn.core.vstar.service.FashionTicketsService;
import com.xmniao.xmn.core.vstar.service.TicketsPriceService;

/**
 * 
 * 项目名称：XmnWeb_vstar
 * 
 * 类名称：FashionTicketsController
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年6月1日 上午10:27:00
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */

@Controller
@RequestMapping("/fashionTickets")
public class FashionTicketsController extends BaseController {

	private static final String FASHIONTICKETS_URL = PropertiesUtil.readValue("http.fashionTickets.url");

	@Autowired
	private FashionTicketsService fashionTicketsService;

	@Autowired
	private FashionTicketSeatService fashionTicketSeatService;

	@Autowired
	private TicketsPriceService ticketsPriceService;

	@Autowired
	private CkeditorUpdateService ckeditorUpdateService;

	@RequestMapping("/init")
	public Object init() {
		ModelAndView modelAndView = new ModelAndView("vstar/fashionTickets/list");
		modelAndView.addObject("fashionTicketsUrl", FASHIONTICKETS_URL);
		return modelAndView;
	}

	@RequestMapping("add/init")
	public Object addInit() {
		return "vstar/fashionTickets/edit";
	}

	@RequestMapping("/add/seat/init")
	public Object seatInit(Integer id) {
		ModelAndView modelAndView = new ModelAndView("vstar/fashionTickets/seatEdit");
		if (id != null) {
			FashionTicketSeat s = fashionTicketSeatService.getObject(id.longValue());
			FashionTicketSeat fashionTicketSeat = fashionTicketSeatService.getObject(id.longValue());
			modelAndView.addObject("fashionTicketSeat", fashionTicketSeat);
		}
		return modelAndView;
	}

	@RequestMapping("/init/getSeats")
	@ResponseBody
	public Object getObject(@RequestParam(required = true) Integer id) {
		List<FashionTicketSeat> list = fashionTicketSeatService.getListByFid(id);
		return list;
	}

	@RequestMapping("/init/list")
	@ResponseBody
	public Object initList(FashionTickets fashionTickets) {
		Pageable<FashionTickets> pageable = new Pageable<>(fashionTickets);
		pageable.setContent(fashionTicketsService.getList(fashionTickets));
		pageable.setTotal(fashionTicketsService.count(fashionTickets));
		return pageable;
	}

	@RequestMapping("/add/price/init")
	public Object priceInit(Integer id) {
		ModelAndView modelAndView = new ModelAndView("vstar/fashionTickets/priceEdit");
		if (id != null) {
			TicketsPrice p = ticketsPriceService.getObject(id.longValue());
			TicketsPrice ticketsPrice = ticketsPriceService.getObject(id.longValue());
			modelAndView.addObject("ticketsPrice", ticketsPrice);
		}
		return modelAndView;
	}

	@RequestMapping("/add/seat/add")
	@ResponseBody
	public Object addSeat(FashionTicketSeat fashionTicketSeat, String seatIds) {
		try {
			if (fashionTicketSeat.getSeats() != null) {
				fashionTicketSeat.setTotalSeats(fashionTicketSeat.getSeats() * fashionTicketSeat.getNum());
				fashionTicketSeat.setZoneRangeMax(fashionTicketSeat.getZoneRangeMin() + fashionTicketSeat.getNum() - 1);
				if (fashionTicketSeatService.checkRepetition(fashionTicketSeat, seatIds)) {
					return Resultable.defeat("桌子编号不能有重复");
				}
			}
			Integer id = fashionTicketSeat.getId();
			if (id != null) {
				FashionTicketSeat s = fashionTicketSeatService.getObject(id.longValue());
				if (new Byte(Integer.valueOf(0).byteValue()).equals(s.getStatus())) {
					return Resultable.defeat("电子门票已生成,不能修改");
				}
				fashionTicketSeatService.update(fashionTicketSeat);
			} else {
				fashionTicketSeatService.add(fashionTicketSeat);
			}
			Resultable success = Resultable.success("成功");
			success.setData(fashionTicketSeat);
			return success;
		} catch (Exception e) {
			log.error("[添加电子票座失败]", e);
			return Resultable.defeat("操作失败");
		}
	}

	@RequestMapping("/getSeatsByIds")
	@ResponseBody
	public Object getSeatsByIds(String ids, Integer fashionTicketId) {
		if (fashionTicketId != null) {
			FashionTicketSeat fashionTicketSeat = new FashionTicketSeat();
			fashionTicketSeat.setFid(fashionTicketId);
			List<FashionTicketSeat> list = fashionTicketSeatService.getList(fashionTicketSeat);
			Resultable success = Resultable.success("成功");
			success.setData(list);
			return success;
		} else if (StringUtils.isNotBlank(ids)) {
			String[] split = ids.split(",");
			List<FashionTicketSeat> list = fashionTicketSeatService.getListByIds(Arrays.asList(split));
			Resultable success = Resultable.success("成功");
			success.setData(list);
			return success;
		} else {
			return Resultable.defeat("操作失败");
		}
	}

	@RequestMapping("/getSeatsChooseByIds")
	@ResponseBody
	public Object getSeatsChooseByIds(@RequestParam(required = true) String ids) {
		String[] split = ids.split(",");
		List<FashionTicketSeat> list = fashionTicketSeatService.getListByIds(Arrays.asList(split));
		Pageable<FashionTicketSeat> pageable = new Pageable<FashionTicketSeat>();
		pageable.setContent(list);
		return pageable;
	}

	@RequestMapping("add/price/add")
	@ResponseBody
	public Object priceAdd(TicketsPrice ticketsPrice) {
		try {
			if (ticketsPriceService.checkIsRepetition(ticketsPrice)) {
				return Resultable.defeat("该规格已存在");
			}
			if (ticketsPrice.getId() != null) {
				/*TicketsPrice p = ticketsPriceService.getObject(ticketsPrice.getId().longValue());
				if (new Byte(Integer.valueOf(0).byteValue()).equals(p.getStatus())) {
					return Resultable.defeat("电子门票已生成,不能修改");
				}*/
				ticketsPriceService.update(ticketsPrice);
			} else {
				ticketsPrice.setStatus(Byte.valueOf("0"));
				ticketsPriceService.add(ticketsPrice);
			}
			Resultable success = Resultable.success("成功");
			success.setData(ticketsPrice);
			return success;
		} catch (Exception e) {
			log.error("添加门票价钱失败", e);
			return Resultable.defeat("操作失败");
		}

	}

	@RequestMapping("/getPricesBySeatsIds")
	@ResponseBody
	public Object getPricesBySeatsIds(String seatsIds) {
		if (StringUtils.isBlank(seatsIds)) {
			return Resultable.success("成功");
		}
		List<TicketsPrice> list = ticketsPriceService.getPricesBySeatsIds(Arrays.asList(seatsIds.split(",")));
		Resultable success = Resultable.success("成功");
		success.setData(list);
		return success;
	}

	@RequestMapping("add/price/delete")
	@ResponseBody
	public Object priceDelete(@RequestParam(required = true) Integer id) {
		try {
			/*TicketsPrice p = ticketsPriceService.getObject(id.longValue());
			if (new Byte(Integer.valueOf(0).byteValue()).equals(p.getStatus())) {
				return Resultable.defeat("电子门票已生成,不能修改");
			}*/
			ticketsPriceService.delete(id);
			return Resultable.success("成功");
		} catch (Exception e) {
			log.error(e);
			return Resultable.defeat("失败");
		}
	}

	@RequestMapping("/add")
	@ResponseBody
	public Object addTickets(FashionTickets fashionTickets) {
		String fids = fashionTickets.getFids();
		if (!fashionTicketsService.checkSeatHasPrice(Arrays.asList(fids.split(",")))) {
			return Resultable.defeat("有部分座位没添加价格");
		}
		fashionTicketsService.add(fashionTickets);
		return Resultable.success("成功");
	}

	@RequestMapping("/end")
	@ResponseBody
	public Object end(@RequestParam(required = true) Integer id, @RequestParam(required = true) Integer status) {
		try {
			fashionTicketsService.end(id, status);
			return Resultable.success("操作成功");
		} catch (Exception e) {
			log.error(e);
			return Resultable.defeat("操作失败");
		}
	}

	@RequestMapping(value = "add/ckeditorUpload", method = { RequestMethod.POST })
	public void uploadFile3(@RequestParam("upload") MultipartFile filedata, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("文件上传Controller3");
		ckeditorUpdateService.ckeditorUpdate(filedata, request, response);
	}

	@RequestMapping(value = "edit/init")
	public Object editInit(@RequestParam(required = true) Integer id) {
		ModelAndView modelAndView = new ModelAndView("vstar/fashionTickets/editEx");
		FashionTickets fashionTickets = fashionTicketsService.getObject(id.longValue());
		modelAndView.addObject("fashionTickets", fashionTickets);
		return modelAndView;
	}

	@RequestMapping("add/seat/delete")
	@ResponseBody
	public Object seatDelete(@RequestParam(required = true) Integer id) {
		try {
			FashionTicketSeat s = fashionTicketSeatService.getObject(id.longValue());
			if (new Byte(Integer.valueOf(0).byteValue()).equals(s.getStatus())) {
				return Resultable.defeat("电子门票已生成,不能修改");
			}
			fashionTicketSeatService.delete(id);
			return Resultable.success("成功");
		} catch (Exception e) {
			log.error(e);
			return Resultable.defeat("失败");
		}
	}

	@RequestMapping("edit")
	@ResponseBody
	public Object update(FashionTickets fashionTickets){
		try {
			String fids = fashionTickets.getFids();
			if (!fashionTicketsService.checkSeatHasPrice(Arrays.asList(fids.split(",")))) {
				return Resultable.defeat("有部分座位没添加价格");
			}
			fashionTicketsService.updateMethod(fashionTickets);
			return Resultable.success("成功");
		} catch (Exception e) {
			log.error("修改电子门票失败",e);
			return Resultable.defeat("操作失败");
		}
	}
	
	
	@RequestMapping("init/getSellIngTickets")
	@ResponseBody
	public Object getSellIngTickets(FashionTickets fashionTickets){
		Pageable<FashionTickets> pageable = new Pageable<>(fashionTickets);
		pageable.setContent(fashionTicketsService.getSellIngTickets());
		return pageable;
	}
	
	
	@RequestMapping("init/getSellingByTicketsId")
	@ResponseBody
	public Object getSellingByTicketsId(@RequestParam(required=true)Integer id){
		return fashionTicketsService.getSellingByTicketsId(id);
	}
	
}
