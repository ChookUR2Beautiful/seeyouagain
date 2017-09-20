/**   
 * 文件名：TSeller.java   
 *    
 * 日期：2014年11月11日15时22分21秒  
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司     
 */
package com.xmniao.xmn.core.businessman.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：TSeller
 * 
 * 类描述：商家(商户)
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月11日15时22分21秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TSeller extends BaseEntity {

	private static final long serialVersionUID = -3422913519345379987L;

	private Integer sellerid;// 商家ID
	private Integer staffid;// 员工ID
	private Integer jointid;// 合作商ID
	private String sellername;// 商家名称
	private String province;// 省编号
	private String ptitle;// 省名称
	private String city;// 市编号
	private String ctitle;// 市名称
	private String area;// 区编号
	private String atitle;// 区名称
	private Integer zoneid;// 商圈编号
	private String btitle;// 商圈标题
	private String category;// 经营类别
	private String genre;// 二级类别
	private String genreIds;// 二级分类编号串，APP专用需要同步到mongdb，各编号以“,”隔开
	private String bewrite;// 类别描述
	private String address;// 商家地址
	private String tel;// 联系电话
	private String fullname;// 法人姓名
	private String identity;// 法人身份证
	private String phoneid;// 联系人手机
	private String orgid;// 机构代码
	private String licenseid;// 营业执照
	private String licenseurl;// 执照电子版URL
	private String sdate;// 营业时间
	private String sexplain;// 使用说明
	private Date svalidity;// 有效期开始
	private Date evalidity;// 有效期结束
	private Integer status;// 默认0未验证|1审核中|2未通过|3已签约|4未签约|5暂停合作
	private String examineinfo;// 审批说明，审批失败原因
	private Date signdate;// 签约时间
	private Date udate;// 修改时间
	private Integer give;// 默认0不是
	private Integer fatherid;// 默认为0
	private String remarks;// 备注
	private String email;// 法人EMAIL
	private String identityzurl;// 身份证附件正面
	private String identityfurl;// 身份证附件反面
	private String identitynurl;//手持身份证正面照
	private String licensefurl;// 营业执照电子版反面URL
	private String agreement;// 商户合同一URL
	private String agreement2;// 商户合同二URL
	private String agreement3;// 商户合同三URL
	private String agreement4;// 商户合同四URL
	private String lssellername;// 连锁店名称
	private String typename;// 经营类名称
	private String tradename;// 二级分类名称
	private Integer isonline;// 0：未上线 1：已上线 2:预上线 3：已下线 默认为：0
	private Integer agioAgio;// '0全单 1折上折'
	private String agioInstruction;//折上折说明
	private Integer ismultiple;// '默认0否 1是'
	private Integer allianceId;// 联盟店id
	private Integer sellerGrade;// 商家等级
	private String sellerGradeStr;// 商家等级
	private Integer isLock;//是否锁定提现功能   0 解锁提现    1锁定提现
	private Integer picid;//商家图片id
    private Integer detailedId;//明细表id,判断明细表是否存在当前商家明细信息
    private Integer isfees;//默认为0， 0扣取支付手续费。1不扣取支付手续费,寻蜜客、合作商收益中则无需扣取支付手续费
    private Double debit;//平台扣款比例，默认为0%. 当商户常规折扣为100%时，业务后台可以设置此参数。计算分账JSON时 根据此参数，从商户营收中扣取相应金额归平台所有。
	private Integer brandId;//大牌美食ID
    
	
	private String brandName;//品牌店名称
    // add chenerxin by 2014-11-11 start
	// 根据需求添加字段
	private String title;// 区域名称
	private String areaTitle;// 商圈名称
	private Date signdateStart;// 加入时间开始（搜索条件）
	private Date signdateEnd;// 加入时间结束 （搜索条件
	private String corporate;// 归属合作商
	private String salesman;// 归属业务员
	private Object[] array;// 批量跟新时id集合
	private String ids;// 前台id集合
	private String isType;// add:

	private Integer aid;// 折扣id
	private Double baseagio;// 当前折扣，商家折扣
	private Double weekend;// 周末折扣
	private Double special;// 特殊折扣
	private Integer agioStatus;// 1=启用=2关闭常规折扣类型默认开启|周末和自定义折扣类型默认关闭
	private Double income;// 营业收入
	private Double sledger;// 商户分账
	private Double yledger;// 用户分账
	private Double pledger;// 平台分账

	private Integer isprotocol;// 是否同意合作协议 0未同意 1已同意
	private Integer isforce;// 是否是美食星势力商户 0不是 1是
	private Integer agioType;// 折扣类型
	private Date agioTime;// 最后一次修改折扣时间
	private Double entry;// 用户流水
	private String url;// 商家logoURL
	private String picUrl;// 商家图片URL
	private Double flatAgio;// 平台补贴折扣，此折扣 必须大于 用户占比。如 用户占比为0.12 此值必须大于0.12
	private Integer isVirtual;// 是否虚拟商户，默认为0 不是虚拟商户 1虚拟商户
	private String account;// 商家帐号
	private Integer accountid;// 帐号ID
	private String nname;// 账号昵称
	private String accountName;// 账号姓名
	private String password;// 登录密码
	private String iostoken;// ios令牌
	private String landmark;// 参考地表
	private Integer type;// 帐号类型
	private String phone;// 注册手机号
	private Integer uid;// 员工绑定的会员id
	// add chenerxin by 2014-11-11 end
	private Date dateOperate;// 上线时间
	private Long commentCount;
	private Long commentCountStart;
	private Long commentCountEnd;

	private String sdate1;// 营业时间开始
	private String sdate2;// 营业时间结束
	private Integer dataSource;// 数据来源 业务系统录入，1：合作商app录入，2：商家app录入，默认为 1';
	private String offlineReason;// 下线原因
	private int[] isonlines;// 下线原因

	private Integer isFirst;// 是否首单
	private Integer isattend;// 是否参与营销
	// Add by Zhang'zhiwen 2015-4-24
	private List<TSeller> sellerList;// 批量更新TSeller
	private Integer sellerMaketingId;// sellerMaketingId
	private Integer cid;// 优惠券id
	
	
	private String hyText;//所属行业名
	
	private String cityids;//城市编号
	private Integer isOpenBooking;//
	private Integer foodNum;
	private Integer foodClassNum;
	private Integer subShopNum; //连锁店数量
	
	//add by wangzhimin 2015-08-13
	private Double ratio; //佣金补贴比例(商户补贴比例)，补给商户向蜜客额外的钱。以活动形式发放。补贴比例 0-100%
	
	private Integer userType;  //用户类型，共重置提现密码时使用
	private Integer isChain; //连锁店子店过滤条件
	
	//add by chenjie 2016-07-08
//	private Integer materielStatus;//物料发送状态  0：未发送 1：已发送

	private Integer order;  //订单基数
	private Double saveMoney;  //节省钱基数
	private String isKa;  //是否为KA商户 0：否  1：是
	private Double agioAgioNum;  //全单折扣
	private String label;  //商家标签
	private Integer ledgerMode;//商家分账模式
	private List<TSellerTraderRef> traderRefs; //经营行业
	//2016-04-19 add by hls
	List<TSellerPic> purlList;//商家环境图片
	
	private String xmerName;//寻蜜客姓名
	
	private String mainAccount; //主账号
	
	private Double longitude;//商家经度
	private Double latitude;//商家纬度
	
	private Integer liveLedgerOperating;	//是否开启直播分账
	private TLiveSellerLedger liveLedger;//直播商家分账设置
	
	private Integer liveCoinPay;	//是否开启鸟币支付
	
	private Integer isPublic;//公开商户
	
	private Integer isPaid;//付费商家
	
	private Integer joinDividend;//参与分红
	
	private Double totalLimitTurnover;//总交易限额
	private Double dailyLimitTurnover;//日常交易限额
	private Double dailyLimitWithdraw;//日常提现限额


	private Integer useValueCard;//是否可用储值卡 0 限制 1可用
	private Integer saasType;   //saas类型，1 寻觅客 2中脉 3 V客


	private String hygienicLicenseImg; // 卫生许可证图片
	private String businessLicenseName;		// 营业执照名称

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getHygienicLicenseImg() {
		return hygienicLicenseImg;
	}

	public void setHygienicLicenseImg(String hygienicLicenseImg) {
		this.hygienicLicenseImg = hygienicLicenseImg;
	}

	public String getBusinessLicenseName() {
		return businessLicenseName;
	}

	public void setBusinessLicenseName(String businessLicenseName) {
		this.businessLicenseName = businessLicenseName;
	}

	public Integer getSaasType() {
		return saasType;
	}

	public void setSaasType(Integer saasType) {
		this.saasType = saasType;
	}

	public String getMainAccount() {
		return mainAccount;
	}

	public void setMainAccount(String mainAccount) {
		this.mainAccount = mainAccount;
	}

	public List<TSellerPic> getPurlList() {
		return purlList;
	}

	public void setPurlList(List<TSellerPic> purlList) {
		this.purlList = purlList;
	}


	public Integer getAccountid() {
		return accountid;
	}

	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}

	public List<TSellerTraderRef> getTraderRefs() {
		return traderRefs;
	}

	public String getIsKa() {
		return isKa;
	}

	public void setIsKa(String isKa) {
		this.isKa = isKa;
	}

	public Double getAgioAgioNum() {
		return agioAgioNum;
	}

	public void setAgioAgioNum(Double agioAgioNum) {
		this.agioAgioNum = agioAgioNum;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setSaveMoney(Double saveMoney) {
		this.saveMoney = saveMoney;
	}

	public void setTraderRefs(List<TSellerTraderRef> traderRefs) {
		this.traderRefs = traderRefs;
	}

	public String getIsOpenBookingText(){
		if(isOpenBooking!=null){
			switch(isOpenBooking){
			case 0 :return "否";
			case 1 :return "是";
			}
		}
		return null;
	}
	
	public String getIsattendText(){
		if(isattend!=null){
			switch(isattend){
			case 0 :return "已参与";
			case 1 :return "已暂停";
			}
		}
		return null;
	}
	
	public String getHyText() {
		StringBuilder sb = new StringBuilder();
		if(StringUtils.hasLength(typename)){
			sb.append(typename);
		}
		if(StringUtils.hasLength(tradename)){
			sb.append("-");
			sb.append(tradename);
		}
		hyText = sb.toString();
		return hyText;
	}

	public void setHyText(String hyText) {
		this.hyText = hyText;
	}

	public Integer getSellerMaketingId() {
		return sellerMaketingId;
	}

	public void setSellerMaketingId(Integer sellerMaketingId) {
		this.sellerMaketingId = sellerMaketingId;
	}

	/**
	 * 取得是否开启首次
	 * 
	 * @return
	 */
	public String getIsFirstText() {
		if (null == isFirst) {
			return null;
		}
		return isFirst == 0 ? "否" : "是";
	}

	public List<TSeller> getSellerList() {
		return sellerList;
	}

	public void setSellerList(List<TSeller> sellerList) {
		this.sellerList = sellerList;
	}

	public Integer getIsFirst() {
		return isFirst;
	}

	public void setIsFirst(Integer isFirst) {
		this.isFirst = isFirst;
	}

	public int[] getIsonlines() {
		return isonlines;
	}

	public void setIsonlines(int[] isonlines) {
		this.isonlines = isonlines;
	}

	public String getOfflineReason() {
		return offlineReason;
	}

	public void setOfflineReason(String offlineReason) {
		this.offlineReason = offlineReason;
	}

	public Integer getDataSource() {
		return dataSource;
	}

	public void setDataSource(Integer dataSource) {
		this.dataSource = dataSource;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getDateOperate() {
		return dateOperate;
	}

	public void setDateOperate(Date dateOperate) {
		this.dateOperate = dateOperate;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getSellerGrade() {
		return sellerGrade;
	}

	public void setSellerGrade(Integer sellerGrade) {
		this.sellerGrade = sellerGrade;
	}

	public String getSellerGradeStr() {
		if (this.sellerGrade == null) {
			return "";
		}
		if (this.sellerGrade == 1)
			return "A 级";
		if (this.sellerGrade == 2)
			return "B+级";
		if (this.sellerGrade == 3)
			return "B 级";
		if (this.sellerGrade == 4)
			return "C+级";
		if (this.sellerGrade == 5)
			return "C 级";
		return "";
	}

	public void setSellerGradeStr(String sellerGradeStr) {
		this.sellerGradeStr = sellerGradeStr;
	}

	// 省 市 区 商圈 组合字符串
	private String sellerArea;

	public String getIsonlineText() {
		if (isonline == null)
			return null;
		if (isonline == 0)
			return "未上线";
		if (isonline == 1)
			return "已上线";
		if (isonline == 3)
			return "已下线";
		return null;
	}


	public String getStatusText() {
		if (status == null)
			return null;
		if (status == 0)
			return "未验证";
		if (status == 1)
			return "审核中";
		if (status == 2)
			return "未通过";
		if (status == 3)
			return "已签约";
		if (status == 4)
			return "未签约";
		if (status == 5)
			return "暂停合作";
		if (status == 6)
			return "已注销";
		return null;
	}

	public String getSaasTypeTexe(){
		if( saasType == null)  return "--";

		switch (saasType){
			case 1: return "寻蜜客";
			case 2: return "脉宝云店";
			case 3: return "V客";
			default: return  "--";
		}

	}

	/**
	 * @return the weekend
	 */
	public Double getWeekend() {
		return weekend;
	}

	/**
	 * @param weekend
	 *            the weekend to set
	 */
	public void setWeekend(Double weekend) {
		this.weekend = weekend;
	}

	/**
	 * @return the special
	 */
	public Double getSpecial() {
		return special;
	}

	/**
	 * @param special
	 *            the special to set
	 */
	public void setSpecial(Double special) {
		this.special = special;
	}

	/**
	 * @return the allianceId
	 */
	public Integer getAllianceId() {
		return allianceId;
	}

	/**
	 * @param allianceId
	 *            the allianceId to set
	 */
	public void setAllianceId(Integer allianceId) {
		this.allianceId = allianceId;
	}

	/**
	 * @return the accountName
	 */
	public String getAccountName() {
		return accountName;
	}

	/**
	 * @param accountName
	 *            the accountName to set
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the ismultiple
	 */
	public Integer getIsmultiple() {
		return ismultiple;
	}

	/**
	 * @param ismultiple
	 *            the ismultiple to set
	 */
	public void setIsmultiple(Integer ismultiple) {
		this.ismultiple = ismultiple;
	}

	public String getSdate1() {
		if (sdate1 != null) {
			return sdate1;
		}
		if (sdate != null) {
			return sdate.split("-")[0];
		}
		return null;
	}

	public void setSdate1(String sdate1) {
		this.sdate1 = sdate1;
	}

	public String getSdate2() {
		if (sdate2 != null) {
			return sdate2;
		}
		if (sdate != null) {
			String[] arrSdate = sdate.split("-");
			// update by lifeng 20160704 14:58:10
			if(arrSdate.length>1){
				return arrSdate[1];
			}else{
				return "";
			}
		}
		return null;
	}

	public void setSdate2(String sdate2) {
		this.sdate2 = sdate2;
	}

	/**
	 * 创建一个新的实例 TSeller.
	 * 
	 */
	public TSeller() {
		super();
	}

	/**
	 * @return the landmark
	 */
	public String getLandmark() {
		return landmark;
	}

	/**
	 * @param landmark
	 *            the landmark to set
	 */
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	/**
	 * @return the iostoken
	 */
	public String getIostoken() {
		return iostoken;
	}

	/**
	 * @param iostoken
	 *            the iostoken to set
	 */
	public void setIostoken(String iostoken) {
		this.iostoken = iostoken;
	}

	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * @param account
	 *            the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * @return the ptitle
	 */
	public String getPtitle() {
		return ptitle;
	}

	/**
	 * @param ptitle
	 *            the ptitle to set
	 */
	public void setPtitle(String ptitle) {
		this.ptitle = ptitle;
	}

	/**
	 * @return the ctitle
	 */
	public String getCtitle() {
		return ctitle;
	}

	/**
	 * @param ctitle
	 *            the ctitle to set
	 */
	public void setCtitle(String ctitle) {
		this.ctitle = ctitle;
	}

	/**
	 * @return the atitle
	 */
	public String getAtitle() {
		return atitle;
	}

	/**
	 * @param atitle
	 *            the atitle to set
	 */
	public void setAtitle(String atitle) {
		this.atitle = atitle;
	}

	/**
	 * @return the agioAgio
	 */
	public Integer getAgioAgio() {
		return agioAgio;
	}

	/**
	 * @param agioAgio
	 *            the agioAgio to set
	 */
	public void setAgioAgio(Integer agioAgio) {
		this.agioAgio = agioAgio;
	}

	public String getSellerArea() {
		StringBuilder sb = new StringBuilder();
		if (StringUtils.hasLength(ctitle)) {
			sb.append(ctitle);
		}
		if (StringUtils.hasLength(atitle)) {
			sb.append("-");
			sb.append(atitle);
		}
		sellerArea = sb.toString();

		return sellerArea;
	}

	public void setSellerArea(String sellerArea) {
		this.sellerArea = sellerArea;
	}

	/**
	 * @return the isonline
	 */
	public Integer getIsonline() {
		return isonline;
	}

	/**
	 * @param isonline
	 *            the isonline to set
	 */
	public void setIsonline(Integer isonline) {
		this.isonline = isonline;
	}

	/**
	 * @return the picUrl
	 */
	public String getPicUrl() {
		return picUrl;
	}

	/**
	 * @param picUrl
	 *            the picUrl to set
	 */
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	/**
	 * @return the isVirtual
	 */
	public Integer getIsVirtual() {
		return isVirtual;
	}

	/**
	 * @param isVirtual
	 *            the isVirtual to set
	 */
	public void setIsVirtual(Integer isVirtual) {
		this.isVirtual = isVirtual;
	}

	/**
	 * @return the flatAgio
	 */
	public Double getFlatAgio() {
		return flatAgio;
	}

	/**
	 * @param flatAgio
	 *            the flatAgio to set
	 */
	public void setFlatAgio(Double flatAgio) {
		this.flatAgio = flatAgio;
	}

	public TSeller(Integer sellerid) {
		this.sellerid = sellerid;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the aid
	 */
	public Integer getAid() {
		return aid;
	}

	/**
	 * @param aid
	 *            the aid to set
	 */
	public void setAid(Integer aid) {
		this.aid = aid;
	}

	/**
	 * sellerid
	 * 
	 * @return the sellerid
	 */
	public Integer getSellerid() {
		return sellerid;
	}

	/**
	 * @param sellerid
	 *            the sellerid to set
	 */
	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}

	/**
	 * staffid
	 * 
	 * @return the staffid
	 */
	public Integer getStaffid() {
		return staffid;
	}

	/**
	 * @param staffid
	 *            the staffid to set
	 */
	public void setStaffid(Integer staffid) {
		this.staffid = staffid;
	}

	/**
	 * jointid
	 * 
	 * @return the jointid
	 */
	public Integer getJointid() {
		return jointid;
	}

	/**
	 * @param jointid
	 *            the jointid to set
	 */
	public void setJointid(Integer jointid) {
		this.jointid = jointid;
	}

	/**
	 * sellername
	 * 
	 * @return the sellername
	 */
	public String getSellername() {
		return sellername;
	}

	/**
	 * @param sellername
	 *            the sellername to set
	 */
	public void setSellername(String sellername) {
		this.sellername = sellername;
	}

	/**
	 * province
	 * 
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province
	 *            the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * city
	 * 
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * area
	 * 
	 * @return the area
	 */
	public String getArea() {
		return area;
	}

	/**
	 * @param area
	 *            the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * zoneid
	 * 
	 * @return the zoneid
	 */
	public Integer getZoneid() {
		return zoneid;
	}

	/**
	 * @param zoneid
	 *            the zoneid to set
	 */
	public void setZoneid(Integer zoneid) {
		this.zoneid = zoneid;
	}

	/**
	 * category
	 * 
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * genre
	 * 
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * @param genre
	 *            the genre to set
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	
	public String getGenreIds() {
		return genreIds;
	}

	public void setGenreIds(String genreIds) {
		this.genreIds = genreIds;
	}

	/**
	 * bewrite
	 * 
	 * @return the bewrite
	 */
	public String getBewrite() {
		return bewrite;
	}

	/**
	 * @param bewrite
	 *            the bewrite to set
	 */
	public void setBewrite(String bewrite) {
		this.bewrite = bewrite;
	}

	/**
	 * address
	 * 
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * tel
	 * 
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * @param tel
	 *            the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * fullname
	 * 
	 * @return the fullname
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * @param fullname
	 *            the fullname to set
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	/**
	 * identity
	 * 
	 * @return the identity
	 */
	public String getIdentity() {
		return identity;
	}

	/**
	 * @param identity
	 *            the identity to set
	 */
	public void setIdentity(String identity) {
		this.identity = identity;
	}

	/**
	 * phoneid
	 * 
	 * @return the phoneid
	 */
	public String getPhoneid() {
		return phoneid;
	}

	/**
	 * @param phoneid
	 *            the phoneid to set
	 */
	public void setPhoneid(String phoneid) {
		this.phoneid = phoneid;
	}

	/**
	 * orgid
	 * 
	 * @return the orgid
	 */
	public String getOrgid() {
		return orgid;
	}

	/**
	 * @param orgid
	 *            the orgid to set
	 */
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	/**
	 * licenseid
	 * 
	 * @return the licenseid
	 */
	public String getLicenseid() {
		return licenseid;
	}

	/**
	 * @param licenseid
	 *            the licenseid to set
	 */
	public void setLicenseid(String licenseid) {
		this.licenseid = licenseid;
	}

	/**
	 * licenseurl
	 * 
	 * @return the licenseurl
	 */
	public String getLicenseurl() {
		return licenseurl;
	}

	/**
	 * @param licenseurl
	 *            the licenseurl to set
	 */
	public void setLicenseurl(String licenseurl) {
		this.licenseurl = licenseurl;
	}

	/**
	 * sdate
	 * 
	 * @return the sdate
	 */
	public String getSdate() {
		return sdate;
	}

	/**
	 * @param sdate
	 *            the sdate to set
	 */
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	/**
	 * sexplain
	 * 
	 * @return the sexplain
	 */
	public String getSexplain() {
		return sexplain;
	}

	/**
	 * @param sexplain
	 *            the sexplain to set
	 */
	public void setSexplain(String sexplain) {
		this.sexplain = sexplain;
	}

	/**
	 * svalidity
	 * 
	 * @return the svalidity
	 */
	public Date getSvalidity() {
		return svalidity;
	}

	/**
	 * @param svalidity
	 *            the svalidity to set
	 */
	public void setSvalidity(Date svalidity) {
		this.svalidity = svalidity;
	}

	/**
	 * evalidity
	 * 
	 * @return the evalidity
	 */
	public Date getEvalidity() {
		return evalidity;
	}

	/**
	 * @param evalidity
	 *            the evalidity to set
	 */
	public void setEvalidity(Date evalidity) {
		this.evalidity = evalidity;
	}

	/**
	 * status
	 * 
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * examineinfo
	 * 
	 * @return the examineinfo
	 */
	public String getExamineinfo() {
		return examineinfo;
	}

	/**
	 * @param examineinfo
	 *            the examineinfo to set
	 */
	public void setExamineinfo(String examineinfo) {
		this.examineinfo = examineinfo;
	}

	/**
	 * signdate
	 * 
	 * @return the signdate
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getSigndate() {
		return signdate;
	}

	/**
	 * @param signdate
	 *            the signdate to set
	 */
	public void setSigndate(Date signdate) {
		this.signdate = signdate;
	}

	/**
	 * udate
	 * 
	 * @return the udate
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getUdate() {
		return udate;
	}

	/**
	 * @param udate
	 *            the udate to set
	 */
	public void setUdate(Date udate) {
		this.udate = udate;
	}

	/**
	 * give
	 * 
	 * @return the give
	 */
	public Integer getGive() {
		return give;
	}

	/**
	 * @param give
	 *            the give to set
	 */
	public void setGive(Integer give) {
		this.give = give;
	}

	/**
	 * fatherid
	 * 
	 * @return the fatherid
	 */
	public Integer getFatherid() {
		return fatherid;
	}

	/**
	 * @param fatherid
	 *            the fatherid to set
	 */
	public void setFatherid(Integer fatherid) {
		this.fatherid = fatherid;
	}

	/**
	 * remarks
	 * 
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks
	 *            the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * email
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * identityzurl
	 * 
	 * @return the identityzurl
	 */
	public String getIdentityzurl() {
		return identityzurl;
	}

	/**
	 * @param identityzurl
	 *            the identityzurl to set
	 */
	public void setIdentityzurl(String identityzurl) {
		this.identityzurl = identityzurl;
	}

	/**
	 * identityfurl
	 * 
	 * @return the identityfurl
	 */
	public String getIdentityfurl() {
		return identityfurl;
	}

	/**
	 * @param identityfurl
	 *            the identityfurl to set
	 */
	public void setIdentityfurl(String identityfurl) {
		this.identityfurl = identityfurl;
	}
	
	/**
	 * identitynurl
	 * @return: the identitynurl
	 */
	public String getIdentitynurl() {
		return identitynurl;
	}

	/**
	 * @Param:identitynurl
	 * 				the identityfurl to set
	 */
	public void setIdentitynurl(String identitynurl) {
		this.identitynurl = identitynurl;
	}

	/**
	 * licensefurl
	 * 
	 * @return the licensefurl
	 */
	public String getLicensefurl() {
		return licensefurl;
	}

	/**
	 * @param licensefurl
	 *            the licensefurl to set
	 */
	public void setLicensefurl(String licensefurl) {
		this.licensefurl = licensefurl;
	}

	public String getNname() {
		return nname;
	}

	public void setNname(String nname) {
		this.nname = nname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAreaTitle() {
		return areaTitle;
	}

	public void setAreaTitle(String areaTitle) {
		this.areaTitle = areaTitle;
	}

	public Date getSigndateStart() {
		return signdateStart;
	}

	public void setSigndateStart(Date signdateStart) {
		this.signdateStart = signdateStart;
	}

	public Date getSigndateEnd() {
		return signdateEnd;
	}

	public void setSigndateEnd(Date signdateEnd) {
		this.signdateEnd = signdateEnd;
	}

	public Double getBaseagio() {
		return baseagio;
	}

	/**
	 *  当前折扣，商家折扣
	 * @param baseagio
	 */
	public void setBaseagio(Double baseagio) {
		this.baseagio = baseagio;
	}

	public String getCorporate() {
		return corporate;
	}

	public void setCorporate(String corporate) {
		this.corporate = corporate;
	}

	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}

	public Object[] getArray() {
		return array;
	}

	public void setArray(Object[] array) {
		this.array = array;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Long getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Long commentCount) {
		this.commentCount = commentCount;
	}

	public Long getCommentCountStart() {
		return commentCountStart;
	}

	public void setCommentCountStart(Long commentCountStart) {
		this.commentCountStart = commentCountStart;
	}

	public Long getCommentCountEnd() {
		return commentCountEnd;
	}

	public void setCommentCountEnd(Long commentCountEnd) {
		this.commentCountEnd = commentCountEnd;
	}

	public String getAgreement() {
		return agreement;
	}

	public void setAgreement(String agreement) {
		this.agreement = agreement;
	}

	public String getLssellername() {
		return lssellername;
	}

	public void setLssellername(String lssellername) {
		this.lssellername = lssellername;
	}

	public String getIsType() {
		return isType;
	}

	public void setIsType(String isType) {
		this.isType = isType;
	}

	/**
	 * @return the typename
	 */
	public String getTypename() {
		return typename;
	}

	/**
	 * @param typename
	 *            the typename to set
	 */
	public void setTypename(String typename) {
		this.typename = typename;
	}

	/**
	 * @return the tradename
	 */
	public String getTradename() {
		return tradename;
	}

	/**
	 * @param tradename
	 *            the tradename to set
	 */
	public void setTradename(String tradename) {
		this.tradename = tradename;
	}

	/**
	 * @return the agioStatus
	 */
	public Integer getAgioStatus() {
		return agioStatus;
	}

	/**
	 * @param agioStatus
	 *            the agioStatus to set
	 */
	public void setAgioStatus(Integer agioStatus) {
		this.agioStatus = agioStatus;
	}

	/**
	 * @return the income
	 */
	public Double getIncome() {
		return income;
	}

	/**
	 * @param income
	 *            the income to set
	 */
	public void setIncome(Double income) {
		this.income = income;
	}

	/**
	 * @return the sledger
	 */
	public Double getSledger() {
		return sledger;
	}

	/**
	 * @param sledger
	 *            the sledger to set
	 */
	public void setSledger(Double sledger) {
		this.sledger = sledger;
	}

	/**
	 * @return the yledger
	 */
	public Double getYledger() {
		return yledger;
	}

	/**
	 * @param yledger
	 *            the yledger to set
	 */
	public void setYledger(Double yledger) {
		this.yledger = yledger;
	}

	/**
	 * @return the pledger
	 */
	public Double getPledger() {
		return pledger;
	}

	/**
	 * @param pledger
	 *            the pledger to set
	 */
	public void setPledger(Double pledger) {
		this.pledger = pledger;
	}

	/**
	 * @return the isprotocol
	 */
	public Integer getIsprotocol() {
		return isprotocol;
	}

	/**
	 * @param isprotocol
	 *            the isprotocol to set
	 */
	public void setIsprotocol(Integer isprotocol) {
		this.isprotocol = isprotocol;
	}

	/**
	 * @return the isforce
	 */
	public Integer getIsforce() {
		return isforce;
	}

	/**
	 * @param isforce
	 *            the isforce to set
	 */
	public void setIsforce(Integer isforce) {
		this.isforce = isforce;
	}

	/**
	 * @return the agioType
	 */
	public Integer getAgioType() {
		return agioType;
	}

	/**
	 * @param agioType
	 *            the agioType to set
	 */
	public void setAgioType(Integer agioType) {
		this.agioType = agioType;
	}

	/**
	 * @return the agioTime
	 */
	public Date getAgioTime() {
		return agioTime;
	}

	/**
	 * @param agioTime
	 *            最后一次修改折扣时间
	 */
	public void setAgioTime(Date agioTime) {
		this.agioTime = agioTime;
	}

	/**
	 * @return the entry
	 */
	public Double getEntry() {
		return entry;
	}

	/**
	 * @param entry
	 *            the entry to set
	 */
	public void setEntry(Double entry) {
		this.entry = entry;
	}

	/**
	 * @return the btitle
	 */
	public String getBtitle() {
		return btitle;
	}

	/**
	 * @param btitle
	 *            the btitle to set
	 */
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public Double getFlatAgioPercentValue() {
		return this.getFlatAgio() != null ? this.getFlatAgio()*100: 0;
	}

	public Integer getIsattend() {
		return isattend;
	}

	public void setIsattend(Integer isattend) {
		this.isattend = isattend;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getCityids() {
		return cityids;
	}

	public void setCityids(String cityids) {
		this.cityids = cityids;
	}

	public Integer getIsOpenBooking() {
		return isOpenBooking;
	}

	public void setIsOpenBooking(Integer isOpenBooking) {
		this.isOpenBooking = isOpenBooking;
	}

	public Integer getFoodNum() {
		return foodNum;
	}

	public void setFoodNum(Integer foodNum) {
		this.foodNum = foodNum;
	}

	public Integer getFoodClassNum() {
		return foodClassNum;
	}

	public void setFoodClassNum(Integer foodClassNum) {
		this.foodClassNum = foodClassNum;
	}
	
	public Integer getSubShopNum() {
		return subShopNum;
	}

	public void setSubShopNum(Integer subShopNum) {
		this.subShopNum = subShopNum;
	}

	public Integer getIsLock() {
		return isLock;
	}

	public void setIsLock(Integer isLock) {
		this.isLock = isLock;
	}

	public Double getRatio() {
		return ratio;
	}

	public String getRatioStr() {
		if(ratio != null&&ratio!=0) {
			DecimalFormat  dft  = new DecimalFormat("######0.00"); 
			return dft.format(new BigDecimal(ratio).multiply(new BigDecimal(100)));
		}
		return "0";
	}
	
	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}

	public String getAgioInstruction() {
		return agioInstruction;
	}

	public void setAgioInstruction(String agioInstruction) {
		this.agioInstruction = agioInstruction;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getPicid() {
		return picid;
	}

	public void setPicid(Integer picid) {
		this.picid = picid;
	}

	public Integer getDetailedId() {
		return detailedId;
	}

	public void setDetailedId(Integer detailedId) {
		this.detailedId = detailedId;
	}

	public Integer getIsfees() {
		return isfees;
	}

	public void setIsfees(Integer isfees) {
		this.isfees = isfees;
	}

	public Double getDebit() {
		return debit;
	}

	public void setDebit(Double debit) {
		this.debit = debit;
	}
	


	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Double getSaveMoney() {
		return saveMoney;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public Integer getIsChain() {
		return isChain;
	}

	public void setIsChain(Integer isChain) {
		this.isChain = isChain;
	}

	public String getAgreement2() {
		return agreement2;
	}

	public void setAgreement2(String agreement2) {
		this.agreement2 = agreement2;
	}

	public String getAgreement3() {
		return agreement3;
	}

	public void setAgreement3(String agreement3) {
		this.agreement3 = agreement3;
	}

	public String getAgreement4() {
		return agreement4;
	}

	public void setAgreement4(String agreement4) {
		this.agreement4 = agreement4;
	}
	

	public String getXmerName() {
		return xmerName;
	}

	public void setXmerName(String xmerName) {
		this.xmerName = xmerName;
	}
	
	

	/**
	 * @return the longitude
	 */
	public Double getLongitude() {
		return longitude;
	}

	public Integer getLedgerMode() {
		return ledgerMode;
	}

	public void setLedgerMode(Integer ledgerMode) {
		this.ledgerMode = ledgerMode;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the latitude
	 */
	public Double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public TLiveSellerLedger getLiveLedger() {
		return liveLedger;
	}

	public void setLiveLedger(TLiveSellerLedger liveLedger) {
		this.liveLedger = liveLedger;
	}

	public Integer getLiveLedgerOperating() {
		return liveLedgerOperating;
	}

	public void setLiveLedgerOperating(Integer liveLedgerOperating) {
		this.liveLedgerOperating = liveLedgerOperating;
	}
	

	public Integer getLiveCoinPay() {
		return liveCoinPay;
	}

	public void setLiveCoinPay(Integer liveCoinPay) {
		this.liveCoinPay = liveCoinPay;
	}
	
	public String getLiveCoinPayStr() {
		if(liveCoinPay!=null){
			if(liveCoinPay==0){
				return "已关闭";
			}else if(liveCoinPay==1){
				return "已开启";
			}
		}
		return "-";
	}

	public Integer getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Integer isPublic) {
		this.isPublic = isPublic;
	}

	public Integer getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(Integer isPaid) {
		this.isPaid = isPaid;
	}

	public Integer getJoinDividend() {
		return joinDividend;
	}

	public void setJoinDividend(Integer joinDividend) {
		this.joinDividend = joinDividend;
	}
	
	public Double getTotalLimitTurnover() {
		return totalLimitTurnover;
	}

	public void setTotalLimitTurnover(Double totalLimitTurnover) {
		this.totalLimitTurnover = totalLimitTurnover;
	}

	public Double getDailyLimitTurnover() {
		return dailyLimitTurnover;
	}

	public void setDailyLimitTurnover(Double dailyLimitTurnover) {
		this.dailyLimitTurnover = dailyLimitTurnover;
	}

	public Double getDailyLimitWithdraw() {
		return dailyLimitWithdraw;
	}

	public void setDailyLimitWithdraw(Double dailyLimitWithdraw) {
		this.dailyLimitWithdraw = dailyLimitWithdraw;
	}

	public Integer getUseValueCard() {
		if(useValueCard == null){
			return 0;
		}else{
		return useValueCard;
		}
	}

	public void setUseValueCard(Integer useValueCard) {
		this.useValueCard = useValueCard;
	}

	@Override
	public String toString() {
		return "TSeller [sellerid=" + sellerid + ", staffid=" + staffid
				+ ", jointid=" + jointid + ", sellername=" + sellername
				+ ", province=" + province + ", ptitle=" + ptitle + ", city="
				+ city + ", ctitle=" + ctitle + ", area=" + area + ", atitle="
				+ atitle + ", zoneid=" + zoneid + ", btitle=" + btitle
				+ ", category=" + category + ", genre=" + genre + ", genreIds="
				+ genreIds + ", bewrite=" + bewrite + ", address=" + address
				+ ", tel=" + tel + ", fullname=" + fullname + ", identity="
				+ identity + ", phoneid=" + phoneid + ", orgid=" + orgid
				+ ", licenseid=" + licenseid + ", licenseurl=" + licenseurl
				+ ", sdate=" + sdate + ", sexplain=" + sexplain
				+ ", svalidity=" + svalidity + ", evalidity=" + evalidity
				+ ", status=" + status + ", examineinfo=" + examineinfo
				+ ", signdate=" + signdate + ", udate=" + udate + ", give="
				+ give + ", fatherid=" + fatherid + ", remarks=" + remarks
				+ ", email=" + email + ", identityzurl=" + identityzurl
				+ ", identityfurl=" + identityfurl + ", identitynurl="
				+ identitynurl + ", licensefurl=" + licensefurl
				+ ", agreement=" + agreement + ", agreement2=" + agreement2
				+ ", agreement3=" + agreement3 + ", agreement4=" + agreement4
				+ ", lssellername=" + lssellername + ", typename=" + typename
				+ ", tradename=" + tradename + ", isonline=" + isonline
				+ ", agioAgio=" + agioAgio + ", agioInstruction="
				+ agioInstruction + ", ismultiple=" + ismultiple
				+ ", allianceId=" + allianceId + ", sellerGrade=" + sellerGrade
				+ ", sellerGradeStr=" + sellerGradeStr + ", isLock=" + isLock
				+ ", picid=" + picid + ", detailedId=" + detailedId
				+ ", isfees=" + isfees + ", debit=" + debit + ", brandId="
				+ brandId + ", brandName=" + brandName + ", title=" + title
				+ ", areaTitle=" + areaTitle + ", signdateStart="
				+ signdateStart + ", signdateEnd=" + signdateEnd
				+ ", corporate=" + corporate + ", salesman=" + salesman
				+ ", array=" + Arrays.toString(array) + ", ids=" + ids
				+ ", isType=" + isType + ", aid=" + aid + ", baseagio="
				+ baseagio + ", weekend=" + weekend + ", special=" + special
				+ ", agioStatus=" + agioStatus + ", income=" + income
				+ ", sledger=" + sledger + ", yledger=" + yledger
				+ ", pledger=" + pledger + ", isprotocol=" + isprotocol
				+ ", isforce=" + isforce + ", agioType=" + agioType
				+ ", agioTime=" + agioTime + ", entry=" + entry + ", url="
				+ url + ", picUrl=" + picUrl + ", flatAgio=" + flatAgio
				+ ", isVirtual=" + isVirtual + ", account=" + account
				+ ", accountid=" + accountid + ", nname=" + nname
				+ ", accountName=" + accountName + ", password=" + password
				+ ", iostoken=" + iostoken + ", landmark=" + landmark
				+ ", type=" + type + ", phone=" + phone + ", uid=" + uid
				+ ", dateOperate=" + dateOperate + ", commentCount="
				+ commentCount + ", commentCountStart=" + commentCountStart
				+ ", commentCountEnd=" + commentCountEnd + ", sdate1=" + sdate1
				+ ", sdate2=" + sdate2 + ", dataSource=" + dataSource
				+ ", offlineReason=" + offlineReason + ", isonlines="
				+ Arrays.toString(isonlines) + ", isFirst=" + isFirst
				+ ", isattend=" + isattend + ", sellerList=" + sellerList
				+ ", sellerMaketingId=" + sellerMaketingId + ", cid=" + cid
				+ ", hyText=" + hyText + ", cityids=" + cityids
				+ ", isOpenBooking=" + isOpenBooking + ", foodNum=" + foodNum
				+ ", foodClassNum=" + foodClassNum + ", subShopNum=" + subShopNum + ", ratio=" + ratio
				+ ", userType=" + userType + ", isChain=" + isChain
				+ ", order=" + order + ", saveMoney=" + saveMoney + ", isKa="
				+ isKa + ", agioAgioNum=" + agioAgioNum + ", label=" + label
				+ ", ledgerMode=" + ledgerMode + ", traderRefs=" + traderRefs
				+ ", purlList=" + purlList + ", xmerName=" + xmerName
				+ ", mainAccount=" + mainAccount + ", longitude=" + longitude
				+ ", latitude=" + latitude + ", liveLedgerOperating="
				+ liveLedgerOperating + ", liveLedger=" + liveLedger
				+ ", liveCoinPay=" + liveCoinPay + ", isPublic=" + isPublic
				+ ", isPaid=" + isPaid + ", joinDividend=" + joinDividend
				+ ", totalLimitTurnover=" + totalLimitTurnover
				+ ", dailyLimitTurnover=" + dailyLimitTurnover
				+ ", dailyLimitWithdraw=" + dailyLimitWithdraw
				+ ", sellerArea=" + sellerArea + "]";
	}

}
