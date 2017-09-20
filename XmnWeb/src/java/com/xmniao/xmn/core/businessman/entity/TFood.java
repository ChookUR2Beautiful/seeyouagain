package com.xmniao.xmn.core.businessman.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * @项目名称：XmnWeb
 * 
 * @类名称：TFood
 * 
 * @类描述： 商家菜品表
 * 
 * @创建人：zhang'zhiwen
 * 
 * @创建时间 ：2015年7月6日 上午10:33:59
 * 
 */
public class TFood extends BaseEntity {

	private static final long serialVersionUID = -4682045836712978649L;

	private Integer id;// 菜品id
	private Integer fid;// 菜品分类id:对应菜品分类别id
	private Integer sellerId;// 商家编号
	private String foodName;// 菜品名称
	private Double cprice;// 菜品价格
	private Double oprice;// 菜品原价
	private Integer atag;// 是否为广告位:0否，1是
	private Integer num;// 菜品序号
	private String bigPic;// 菜品展示大图地址
	private String smallPic;// 菜品展示小图地址
	private Integer banNum;// 限售量:商家限制售出数量，没有为0
	private Integer sellNum;// 已售数量:商家的该菜品售出的累加
	private Date sdate;// 创建时间
	private Date pdate;// 修改时间
	private Integer source;// 数据来源:1商户APP，2商户PC端，3业务系统，4其他
	private Integer datastatus;// 数据状态：1上架，2删除（标记为删除不显示），3下架
	private String remark;// 备注

	private String className;// 菜品类别名称
	private String statusName;	//数据状态名称
	
	public String getAtagText() {
		String atagText = null;
		if (this.getAtag() != null) {
			switch (this.getAtag()) {
			case 0:
				atagText = "否";
				break;
			case 1:
				atagText = "是";
				break;
			}
		}
		return atagText;
	}

	public String getSourceText() {
		String sourceText = null;
		if (this.getSource() != null) {
			switch (this.getSource()) {
			case 1:
				sourceText = "商户APP";
				break;
			case 2:
				sourceText = "商户PC端";
				break;
			case 3:
				sourceText = "业务系统";
				break;
			case 4:
				sourceText = "其他";
				break;
			default:
			}
		}
		return sourceText;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public Double getCprice() {
		return cprice;
	}

	public void setCprice(Double cprice) {
		this.cprice = cprice;
	}

	public Double getOprice() {
		return oprice;
	}

	public void setOprice(Double oprice) {
		this.oprice = oprice;
	}

	public Integer getAtag() {
		return atag;
	}

	public void setAtag(Integer atag) {
		this.atag = atag;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getBigPic() {
		return bigPic;
	}

	public void setBigPic(String bigPic) {
		this.bigPic = bigPic;
	}

	public String getSmallPic() {
		return smallPic;
	}

	public void setSmallPic(String smallPic) {
		this.smallPic = smallPic;
	}

	public Integer getBanNum() {
		return banNum;
	}

	public void setBanNum(Integer banNum) {
		this.banNum = banNum;
	}

	public Integer getSellNum() {
		return sellNum;
	}

	public void setSellNum(Integer sellNum) {
		this.sellNum = sellNum;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getSdate() {
		return sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getPdate() {
		return pdate;
	}

	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Integer getDatastatus() {
		return datastatus;
	}

	public void setDatastatus(Integer datastatus) {
		this.datastatus = datastatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getStatusName(){
		if(datastatus==null){
			statusName = "-";
		}else if(datastatus==1){
			statusName = "已上架";
		}else if(datastatus==2){
			statusName = "已删除";
		}else if(datastatus==3){
			statusName = "已下架";
		}else{
			statusName = "-";
		}
		return statusName;
	}
	@Override
	public String toString() {
		return "Food [id=" + id + ", fid=" + fid + ", sellerId=" + sellerId
				+ ", footName=" + foodName + ", cprice=" + cprice + ", oprice="
				+ oprice + ", atag=" + atag + ", num=" + num + ", bigPic="
				+ bigPic + ", smallPic=" + smallPic + ", banNum=" + banNum
				+ ", sellNum=" + sellNum + ", sdate=" + sdate + ", pdate="
				+ pdate + ", source=" + source + ", datastatus=" + datastatus
				+ ", remark=" + remark + "]";
	}

}
