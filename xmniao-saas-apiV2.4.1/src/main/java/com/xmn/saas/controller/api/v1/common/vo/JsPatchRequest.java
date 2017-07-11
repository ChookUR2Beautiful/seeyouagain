package com.xmn.saas.controller.api.v1.common.vo;

import com.xmn.saas.base.Request;
import com.xmn.saas.entity.common.JsPatch;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by yang.qiang on 2017/2/13.
 */
public class JsPatchRequest extends Request {


    @Override
    public String toString() {
        return "JsPatchRequest{" +
            "version='" + version + '\'' +
            ", password='" + password + '\'' +
            ", code='" + code + '\'' +
            ", type=" + type +
            '}';
    }

    // 版本号
    @NotNull(message = "版本号不能为空")
    private String version;

    // 更新密码
    private String password;

    // jsPatch代码
    private String code;

    @Max(value = 2,message = "操作类型错误")
    @Min(value = 1,message = "操作类型错误")
    @NotNull(message = "操作类型不能为空")
    private Integer type;

    public String getVersion() {
        return version;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public JsPatch convertJsPatch() {
        JsPatch jsPatch = new JsPatch();
        jsPatch.setCode(this.code);
        jsPatch.setPassword(this.password);
        jsPatch.setVersion(this.version);
        return jsPatch;
    }
}
