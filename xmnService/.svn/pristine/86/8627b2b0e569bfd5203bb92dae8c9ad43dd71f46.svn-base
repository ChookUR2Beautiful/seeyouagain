package com.xmniao.xmn.core.integral.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.integral.dao.BillFreshDao;
import com.xmniao.xmn.core.integral.response.IntegralBillListResponse;
import com.xmniao.xmn.core.market.dao.MarketBillFreshDao;
import com.xmniao.xmn.core.market.dao.ProductBillDao;
import com.xmniao.xmn.core.market.entity.pay.OrderResponse;
import com.xmniao.xmn.core.market.entity.pay.ProductBill;
import com.xmniao.xmn.core.market.entity.pay.ProductResponse;


@Service
public class BillFreshService {
    
    @Autowired
    private BillFreshDao BillFreshDao;
    
    @Autowired
    private String fileUrl;

	@Autowired
	private MarketBillFreshDao marketBillFreshDao;
	
	@Autowired
	private ProductBillDao productBillDao;
	
	/**
	 * 查询订单信息
	 * @author xiaoxiong
	 * @date 2016年11月25日
	 */
	public List<OrderResponse> queryBillFreshByUid(String uid,Integer type,Integer page,Integer limit) {
		try {
			return marketBillFreshDao.queryOrderList(uid, type, page, limit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 查询订单商品
	 * @author xiaoxiong
	 * @date 2016年11月25日
	 */
	public List<ProductResponse> queryBillProductByBid(Long bid) {
		
		List<ProductResponse> response = new ArrayList<ProductResponse>();
		
		try {
			
			//先查询子单
			response = productBillDao.queryProductBillBySubBid(bid);
			
			if(response.isEmpty()){
				//查询父单
				response = productBillDao.queryProductBillByBid(bid);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	/**
     * 查询订单信息
     * @author xiaoxiong
     * @date 2016年11月25日
     */
    public List<IntegralBillListResponse> queryBillFreshByUid(
            Map<String, Object> params) {
        try {
            return BillFreshDao.queryBillFreshByUid(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 查询订单商品
     * @author xiaoxiong
     * @date 2016年11月25日
     */
    public List<ProductBill> queryBillProductByBid(String bid, int type) {
        try {
            return BillFreshDao.queryBillProductByBid(bid,type,fileUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
