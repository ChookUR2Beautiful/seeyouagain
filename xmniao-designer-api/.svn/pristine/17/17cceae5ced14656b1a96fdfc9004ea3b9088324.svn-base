package com.xmn.designer.controller.api.v1.order.vo;

import com.xmn.designer.base.Page;
import com.xmn.designer.entity.order.Order;
import org.springframework.beans.BeanUtils;

/**
 * create 2016/11/18
 *
 * @author yangQiang
 */

public class OrderListRequest {

    // 订单类型
//    private String type;

    // 订单状态 0 待支付  1 已支付(代发货)  2 已发货(待收货)  3 交易成功(已完成)
    private Integer status;

    // 分页数据条数
    private Integer pageSize = 5;
    // 分页页数
    private Integer pageNum = 0;

    public Order convertToOrder() {
        Order order = new Order();
        BeanUtils.copyProperties(this,order);
        return order;
    }

    public Page convertToPage(){
        Page page = new Page();
        BeanUtils.copyProperties(this,page);
        return page;
    }


    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
