package com.xmn.saas.entity.shop;

import java.util.List;

/**
 * 类名称：ShopInfo
 * 类描述：   店铺资料基本资料类
 * 创建人：xiaoxiong
 * 创建时间：2016年9月24日 下午5:01:19
 * 修改人：xiaoxiong
 * 修改时间：2016年9月24日 下午5:01:19
 * 修改备注：
 */
public class SellerInfo {


    /**
     * 商家id
     */
    private Integer sellerid;
    /**
     * 商家名称
     */
    private String sellerName;
    /**
     * 商圈ID
     */
    private Integer zoneId;
    /**
     * 商圈名称
     */
    private String zoneName;
    /**
     * 地址
     */
    private String address;
    /**
     * 联系电话
     */
    private String phone;

    /**
     * 经营类型
     */
    private String typeName;

    /**
     * 营业时间
     */
    private String openDate;
    /**
     * 店铺详细资料实体类
     */
    private SellerDetailed detailed;
    /**
     * 商家坐标信息
     */
    private SellerLandMark landMark;

    /**
     * 是否有正在审核的申请
     */
    private int status;

    /**
     * 修改时间
     */
    private String udate;
    /**
     * 商家折扣
     */
    private double agio;

    /**
     * 二级类别名称
     */
    private String tradename;

    /**
     * 一级类别编号
     */
    private Integer category;

    /**
     * 二级类别编号
     */
    private Integer genre;

    /**
     * 法人姓名
     */
    private String fullname;

    /**
     * 注册时间
     */
    private String signdate;


    //省市
    private String province;

    //城市
    private String city;

    //地区
    private String area;

    private int provinceNo;

    private int cityNo;

    private int areaNo;

    private String tagIds;

    // 标签列表
    private List<LiveClassifyTag> tags;


    public List<LiveClassifyTag> getTags() {
        return tags;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public void setTags(List<LiveClassifyTag> tags) {
        this.tags = tags;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getProvinceNo() {
        return provinceNo;
    }

    public void setProvinceNo(int provinceNo) {
        this.provinceNo = provinceNo;
    }

    public int getCityNo() {
        return cityNo;
    }

    public void setCityNo(int cityNo) {
        this.cityNo = cityNo;
    }

    public int getAreaNo() {
        return areaNo;
    }

    public void setAreaNo(int areaNo) {
        this.areaNo = areaNo;
    }

    public String getSigndate() {
        return signdate;
    }

    public void setSigndate(String signdate) {
        this.signdate = signdate;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getTradename() {
        return tradename;
    }

    public void setTradename(String tradename) {
        this.tradename = tradename;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getGenre() {
        return genre;
    }

    public void setGenre(Integer genre) {
        this.genre = genre;
    }

    public double getAgio() {
        return agio;
    }

    public void setAgio(double agio) {
        this.agio = agio;
    }

    public String getUdate() {
        return udate;
    }

    public void setUdate(String udate) {
        this.udate = udate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public SellerDetailed getDetailed() {
        return detailed;
    }

    public void setDetailed(SellerDetailed detailed) {
        this.detailed = detailed;
    }

    public SellerLandMark getLandMark() {
        return landMark;
    }

    public void setLandMark(SellerLandMark landMark) {
        this.landMark = landMark;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Integer getZoneId() {
        return zoneId;
    }

    public void setZoneId(Integer zoneId) {
        this.zoneId = zoneId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    public Integer getSellerid() {
        return sellerid;
    }

    public void setSellerid(Integer sellerid) {
        this.sellerid = sellerid;
    }


}

	
