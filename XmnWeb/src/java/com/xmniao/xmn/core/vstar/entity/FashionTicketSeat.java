package com.xmniao.xmn.core.vstar.entity;

import com.xmniao.xmn.core.base.BaseEntity;

public class FashionTicketSeat extends BaseEntity{
    /**
     * ID
     */
    private Integer id;

    /**
     * t_fashion_tickets主键
     */
    private Integer fid;

    /**
     * 座席名
     */
    private String seatName;

    /**
     * 座位数每桌
     */
    private Integer seats;

    /**
     * 多少桌
     */
    private Integer num;

    /**
     * 不支持选座时,默认总座位数
     */
    private Integer defaultSeats;

    /**
     * 该座席总位数(=桌数*单桌位数或默认总位数)
     */
    private Integer totalSeats;

    /**
     * 0正常 1无效
     */
    private Byte status;

    /**
     * 桌号区间最小值
     */
    private Integer zoneRangeMin;

    /**
     * 桌号区间最大值
     */
    private Integer zoneRangeMax;

    /**
     * ID
     * @return id ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * ID
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * t_fashion_tickets主键
     * @return fid t_fashion_tickets主键
     */
    public Integer getFid() {
        return fid;
    }

    /**
     * t_fashion_tickets主键
     * @param fid t_fashion_tickets主键
     */
    public void setFid(Integer fid) {
        this.fid = fid;
    }

    /**
     * 座席名
     * @return seat_name 座席名
     */
    public String getSeatName() {
        return seatName;
    }

    /**
     * 座席名
     * @param seatName 座席名
     */
    public void setSeatName(String seatName) {
        this.seatName = seatName == null ? null : seatName.trim();
    }

    /**
     * 座位数每桌
     * @return seats 座位数每桌
     */
    public Integer getSeats() {
        return seats;
    }

    /**
     * 座位数每桌
     * @param seats 座位数每桌
     */
    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    /**
     * 多少桌
     * @return num 多少桌
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 多少桌
     * @param num 多少桌
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 不支持选座时,默认总座位数
     * @return default_seats 不支持选座时,默认总座位数
     */
    public Integer getDefaultSeats() {
        return defaultSeats;
    }

    /**
     * 不支持选座时,默认总座位数
     * @param defaultSeats 不支持选座时,默认总座位数
     */
    public void setDefaultSeats(Integer defaultSeats) {
        this.defaultSeats = defaultSeats;
    }

    /**
     * 该座席总位数(=桌数*单桌位数或默认总位数)
     * @return total_seats 该座席总位数(=桌数*单桌位数或默认总位数)
     */
    public Integer getTotalSeats() {
        return totalSeats;
    }

    /**
     * 该座席总位数(=桌数*单桌位数或默认总位数)
     * @param totalSeats 该座席总位数(=桌数*单桌位数或默认总位数)
     */
    public void setTotalSeats(Integer totalSeats) {
        this.totalSeats = totalSeats;
    }

    /**
     * 0正常 1无效
     * @return status 0正常 1无效
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 0正常 1无效
     * @param status 0正常 1无效
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 桌号区间最小值
     * @return zone_range_min 桌号区间最小值
     */
    public Integer getZoneRangeMin() {
        return zoneRangeMin;
    }

    /**
     * 桌号区间最小值
     * @param zoneRangeMin 桌号区间最小值
     */
    public void setZoneRangeMin(Integer zoneRangeMin) {
        this.zoneRangeMin = zoneRangeMin;
    }

    /**
     * 桌号区间最大值
     * @return zone_range_max 桌号区间最大值
     */
    public Integer getZoneRangeMax() {
        return zoneRangeMax;
    }

    /**
     * 桌号区间最大值
     * @param zoneRangeMax 桌号区间最大值
     */
    public void setZoneRangeMax(Integer zoneRangeMax) {
        this.zoneRangeMax = zoneRangeMax;
    }
}