package com.xmn.saas.controller.api.v1.account.vo;

import com.xmn.saas.base.Request;
import com.xmn.saas.entity.common.SellerAccount;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * create 2016/10/08
 *
 * @author yangQiang
 */

public class SubsidiaryPutRequest extends Request {

    // 操作类型 0:添加  1:修改
    @NotNull(message = "操作类型不能为空")
    private Integer operation;

    // 员工名称
    @NotNull(message = "员工名称不能为空")
    private String nname;

    // 员工职务
    @NotNull(message = "职务不能为空")
    private String fullname;

    // 账户手机(账户)
    @Pattern(regexp = "^(1[0-9][0-9])\\d{8}$",message = "手机号格式错误")
    @NotNull(message = "手机号不能为空")
    private String phone;

    // 账户密码(MD5密文)
    private String password;

    // 账户权限 : 老板、店长、服务员
    @NotNull(message = "账户权限不能为空")
    @Min(value = 1,message = "账户权限只能为1,2,3")
    @Max(value = 3,message = "账户权限只能为1,2,3")
    private Integer type;

    // 是否冻结 0正常，1冻结
    @Min(value = 0,message = "冻结状态只能是0或1")
    @Max(value = 1,message = "冻结状态只能是0或1")
    private Integer userstatus;

    // 账户主键
    private Integer aid;

    // 子账号是否接收收款短信 1:接收  2:不接收
    @Min(value = 1,message = "是否接收收款短信状态错误")
    @Max(value = 2,message = "是否接收收款短信状态错误")
    private Integer receiveMessage;

    public SellerAccount converBean(){
        SellerAccount sellerAccount = new SellerAccount();
        BeanUtils.copyProperties(this,sellerAccount);
        return sellerAccount;
    }


    public Integer getReceiveMessage() {
        return receiveMessage;
    }

    public void setReceiveMessage(Integer receiveMessage) {
        this.receiveMessage = receiveMessage;
    }

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }

    public String getNname() {
        return nname;
    }

    public void setNname(String nname) {
        this.nname = nname;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getUserstatus() {
        return userstatus;
    }

    public void setUserstatus(Integer userstatus) {
        this.userstatus = userstatus;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    @Override
    public String toString() {
        return "SubsidiaryPutRequest{" +
                "nname='" + nname + '\'' +
                ", fullname='" + fullname + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                ", userstatus=" + userstatus +
                '}';
    }
}
