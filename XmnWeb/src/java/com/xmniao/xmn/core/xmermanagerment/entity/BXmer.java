package com.xmniao.xmn.core.xmermanagerment.entity;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.DateUtil;

/**
 *@ClassName:BXmer
 *@Description:寻蜜客成员实体类
 *@author hls
 *@date:2016年5月25日上午11:51:07
 */
public class BXmer extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6061893813365721197L;

	/**
	 * 表b_xmer
	 */
	private Integer id;//寻蜜客id
	
	private Integer uid;//寻蜜客用户身份编号
	
	private Integer uidtogetpart;//用于查询寻蜜客伙伴的UID
	
	private Integer rtype;//1=个人寻密客  2=企业寻密客
	
	private String phoneid;//手机号码
	
	private String email;//邮箱
	
	private String weixinid;//微信号 
	
	private Integer age;//年龄
	
	private String achievement;//头衔名称
	
	private Integer partnerNums;//伙伴数量
	
	private Integer sellerNums;//商铺数量
	
	private Integer parentid;//上级ID
	
	private Integer levels;//级别
	
	private String levelSwap;//等级分区
	
	private Integer stock;//套餐库存
	
	private Integer soldNums;//卖出数量
	
	private Date sdate;//加入时间
	private String sdateStr;//加入时间
	
	private Date edate;//加入时间结束范围
	
	private Integer status;//默认0启用 1停用
	
	private Date updateDate;//更新时间
	
	/**
	 * 表b_urs_info
	 */
	private String avatar;//头像
	
	private String name;//寻蜜客用户姓名
	
	private Integer sex;//性别
	private String sexStr;//性别
	
	private Date birthday;//生日
	
	private String birthdayStr;//生日
    
	/**
	 * 表b_wallet
	 */
	private Integer accountid;
	private BigDecimal income;//总收入
	private BigDecimal commision;//佣金
	private BigDecimal balance;//流水收入
	private Long sellCount;//客户端出售总数
	private Double sellerCrrentCount;//平台店铺流水总额
	private String sellerCrrentCountStr;
	private String incomeStr;
	private String commisionStr;
	private String balanceStr;
	
	/**
	 * 表t_bill
	 */
    private Double sumMoney;//用户累计消费总金额
    private String sumMoneyStr;
    
    private Double difference;//余额    =   佣金  + 流水收入 + 押金 - 消费总金额(此计算方式已作废)
    private String differenceStr;
    
    /**
     * 表t_saas_order
     */
    private Integer nums;//签约数量
    private Integer eightFoldNums;//576(8折)套餐单价剩余套数
    private Integer sevenFoldNums;//504(7折)套餐单价剩余套数
    private String numsStr;
    private String stockTotal;//剩余套餐总数 显示格式：(576套餐单价剩余套数 + 504套餐单价剩余套数)/签约数量
    private String stockTotalPrice;//剩余软件总价/元 = 576套餐单价剩余套数*576 + 504套餐单价剩余套数*504
    
    /**
     * 表t_xmer_wallet
     */
    private Integer xid;//寻蜜客钱包id
    private BigDecimal profit;//收益余额
    private BigDecimal trunout;//转出总额
    
    DecimalFormat  df = new DecimalFormat("0.00");
    
    private List<String> ids ;
    
	public String getStockTotalPrice() {
		if(stockTotalPrice == null || "".equals(stockTotalPrice)) return "0.00"; 
		return stockTotalPrice;
	}

	public void setStockTotalPrice(String stockTotalPrice) {
		this.stockTotalPrice = stockTotalPrice;
	}

	public String getNumsStr() {
		if(nums == null || "".equals(nums)) return "-";
		numsStr = getNums().toString();
		return numsStr;
	}

	public void setNumsStr(String numsStr) {
		numsStr = nums.toString();
		this.numsStr = numsStr;
	}

	public Integer getNums() {
		return nums;
	}

	public void setNums(Integer nums) {
		this.nums = nums;
	}

	public Integer getEightFoldNums() {
		if(eightFoldNums == null || "".equals(eightFoldNums)) return 0;
		return eightFoldNums;
	}

	public void setEightFoldNums(Integer eightFoldNums) {
		this.eightFoldNums = eightFoldNums;
	}

	public Integer getSevenFoldNums() {
		if(sevenFoldNums == null || "".equals(sevenFoldNums)) return 0;
		return sevenFoldNums;
	}

	public void setSevenFoldNums(Integer sevenFoldNums) {
		this.sevenFoldNums = sevenFoldNums;
	}

	public String getStockTotal() {
		if(stockTotal == null || "".equals(stockTotal)) return "0/0"; 
		return stockTotal;
	}

	public void setStockTotal(String stockTotal) {
		this.stockTotal = stockTotal;
	}

	public Integer getUidtogetpart() {
		return uidtogetpart;
	}

	public void setUidtogetpart(Integer uidtogetpart) {
		this.uidtogetpart = uidtogetpart;
	}

	public Integer getSellerNums() {
		return sellerNums;
	}

	public void setSellerNums(Integer sellerNums) {
		this.sellerNums = sellerNums;
	}

	public Double getDifference() {
//		BigDecimal sumMoneyD = new BigDecimal(getSumMoney());
//		difference = (getCommision().add(getBalance())).subtract(sumMoneyD).doubleValue();
		return difference;
	}

	public void setDifference(Double difference) {
		this.difference = difference;
	}

	public String getDifferenceStr() {
		if(getDifference() == null || "".equals(getDifference())) return "0.00";
		differenceStr = df.format(getDifference());
		return differenceStr;
	}

	public void setDifferenceStr(String differenceStr) {
		this.differenceStr = differenceStr;
	}

	public Integer getAccountid() {
		return accountid;
	}

	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}

	public String getIncomeStr() {
		if(income == null || "".equals(income)) return "0.00";
		incomeStr = df.format(income);
		return incomeStr;
	}

	public void setIncomeStr(String incomeStr) {
		this.incomeStr = incomeStr;
	}

	public String getCommisionStr() {
		if(commision == null || "".equals(commision)) return "0.00";
		commisionStr = df.format(commision);
		return commisionStr;
	}

	public void setCommisionStr(String commisionStr) {
		this.commisionStr = commisionStr;
	}

	public String getBalanceStr() {
		if(balance == null || "".equals(balance)) return "0.00";
		balanceStr = df.format(balance);
		return balanceStr;
	}

	public void setBalanceStr(String balanceStr) {
		this.balanceStr = balanceStr;
	}


	public String getSumMoneyStr() {
		if(sumMoney == null || "".equals(sumMoney)) return "0.00";
		sumMoneyStr = df.format(sumMoney);
		return sumMoneyStr;
	}

	public void setSumMoneyStr(String sumMoneyStr) {
		this.sumMoneyStr = sumMoneyStr;
	}

	public Double getSumMoney() {
		if(sumMoney == null || "".equals(sumMoney)){
			sumMoney = new Double(0.00);
		} 
		return sumMoney;
	}

	public void setSumMoney(Double sumMoney) {
		this.sumMoney = sumMoney;
	}


	

	public BigDecimal getCommision() {
		if(commision == null || "".equals(commision)){
			commision = new BigDecimal(0.00);
		}
		return commision;
	}

	public void setCommision(BigDecimal commision) {
		this.commision = commision;
	}

	public BigDecimal getBalance() {
		if(balance == null || "".equals(balance)){
			balance = new BigDecimal(0.00);
		} 
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getIncome() {
		return income;
	}

	public void setIncome(BigDecimal income) {
		this.income = income;
	}

	public String getBirthdayStr() {
		if(birthday == null || "".equals(birthday)) return "-";
		birthdayStr = DateUtil.formatDate(birthday,"yyyy-MM-dd");
		return birthdayStr;
	}
	
	public String getSdateStr() {
		if(sdate == null || "".equals(sdate)) return "-";
		sdateStr = DateUtil.formatDate(sdate,"yyyy-MM-dd");
		return sdateStr;
	}

	public void setSdateStr(String sdateStr) {
		this.sdateStr = sdateStr;
	}

	public void setBirthdayStr(String birthdayStr) {
		this.birthdayStr = birthdayStr;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getRtype() {
		return rtype;
	}

	public void setRtype(Integer rtype) {
		this.rtype = rtype;
	}

	public String getPhoneid() {
		return phoneid;
	}

	public void setPhoneid(String phoneid) {
		this.phoneid = phoneid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWeixinid() {
		return weixinid;
	}

	public void setWeixinid(String weixinid) {
		this.weixinid = weixinid;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAchievement() {
		return achievement;
	}

	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}

	public Integer getPartnerNums() {
		return partnerNums;
	}

	public void setPartnerNums(Integer partnerNums) {
		this.partnerNums = partnerNums;
	}

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public Integer getLevels() {
		return levels;
	}

	public void setLevels(Integer levels) {
		this.levels = levels;
	}

	public String getLevelSwap() {
		return levelSwap;
	}

	public void setLevelSwap(String levelSwap) {
		this.levelSwap = levelSwap;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getSoldNums() {
		return soldNums;
	}

	public void setSoldNums(Integer soldNums) {
		this.soldNums = soldNums;
	}

	public Date getSdate() {
		return sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	public Date getEdate() {
		return edate;
	}

	public void setEdate(Date edate) {
		this.edate = edate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	public String getSexStr() {
		if(sex == null || "".equals(sex)) return "-";
		if(sex==1) return "男";
		if(sex==2) return "女";
		return sexStr;
	}

	public void setSexStr(String sexStr) {
		this.sexStr = sexStr;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Long getSellCount() {
		return sellCount;
	}

	public void setSellCount(Long clientSellCount) {
		this.sellCount = clientSellCount;
	}

	public Double getSellerCrrentCount() {
		return sellerCrrentCount;
	}

	public void setSellerCrrentCount(Double sellerCrrentCount) {
		this.sellerCrrentCount = sellerCrrentCount;
	}

	public String getSellerCrrentCountStr() {
		if(sellerCrrentCount == null || "".equals(sellerCrrentCount)) return "0.00";
		sellerCrrentCountStr = df.format(sellerCrrentCount);
		return sellerCrrentCountStr;
	}

	public void setSellerCrrentCountStr(String sellerCrrentCountStr) {
		this.sellerCrrentCountStr = sellerCrrentCountStr;
	}
	
	
	

	/**
	 * @return the xid
	 */
	public Integer getXid() {
		return xid;
	}

	/**
	 * @param xid the xid to set
	 */
	public void setXid(Integer xid) {
		this.xid = xid;
	}

	/**
	 * @return the profit
	 */
	public BigDecimal getProfit() {
		return profit;
	}

	/**
	 * @param profit the profit to set
	 */
	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	/**
	 * @return the trunout
	 */
	public BigDecimal getTrunout() {
		return trunout;
	}

	/**
	 * @param trunout the trunout to set
	 */
	public void setTrunout(BigDecimal trunout) {
		this.trunout = trunout;
	}

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	@Override
	public String toString() {
		return "BXmer [id=" + id + ", uid=" + uid + ", uidtogetpart="
				+ uidtogetpart + ", rtype=" + rtype + ", phoneid=" + phoneid
				+ ", email=" + email + ", weixinid=" + weixinid + ", age="
				+ age + ", achievement=" + achievement + ", partnerNums="
				+ partnerNums + ", sellerNums=" + sellerNums + ", parentid="
				+ parentid + ", levels=" + levels + ", levelSwap=" + levelSwap
				+ ", stock=" + stock + ", soldNums=" + soldNums + ", sdate="
				+ sdate + ", sdateStr=" + sdateStr + ", edate=" + edate
				+ ", status=" + status + ", updateDate=" + updateDate
				+ ", avatar=" + avatar + ", name=" + name + ", sex=" + sex
				+ ", sexStr=" + sexStr + ", birthday=" + birthday
				+ ", birthdayStr=" + birthdayStr + ", accountid=" + accountid
				+ ", income=" + income + ", commision=" + commision
				+ ", balance=" + balance + ", sellCount=" + sellCount
				+ ", sellerCrrentCount=" + sellerCrrentCount
				+ ", sellerCrrentCountStr=" + sellerCrrentCountStr
				+ ", incomeStr=" + incomeStr + ", commisionStr=" + commisionStr
				+ ", balanceStr=" + balanceStr + ", sumMoney=" + sumMoney
				+ ", sumMoneyStr=" + sumMoneyStr + ", difference=" + difference
				+ ", differenceStr=" + differenceStr + ", nums=" + nums
				+ ", eightFoldNums=" + eightFoldNums + ", sevenFoldNums="
				+ sevenFoldNums + ", numsStr=" + numsStr + ", stockTotal="
				+ stockTotal + ", stockTotalPrice=" + stockTotalPrice
				+ ", xid=" + xid + ", profit=" + profit + ", trunout="
				+ trunout + ", df=" + df + ", ids=" + ids + "]";
	}



}
