package com.xmniao.domain.push;

import java.io.Serializable;

/**
 * 系统信息发布表t_system_msg的实体类
 * @author  LiBingBing
 * @version  [版本号, 2015年5月12日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class PushSysMsgBean implements Serializable
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = -5515709400984291732L;
    
    /**
     * 记录ID
     */
    private int sid;
    
    /**
     * 创建时间
     */
    private String sdate;
    
    /**
     * 发送时间
     */
    private String tdate;
    
    /**
     * 过期时间
     */
    private String edate;
    
    /**
     * 发送状态 0待发送  1已发送
     */
    private String status;
    
    /**
     * 信息内容
     */
    private String content;
    
    /**
     * 阅读数
     */
    private String number;
    
    /**
     * 发送对象
     */
    private String object;
    
    /**
     * 是否推送
     */
    private String ispush;
    
    /**
     * 后续动作 1打开指定网页  2打开activity
     */
    private String actiontype;
    
    /**
     * 填网址或activity
     */
    private String action;
    
    /**
     * 0 用户系统通知   1向蜜客系统通知 2商户通知
     */
    private String type;

    
    public String getSdate()
    {
        return sdate;
    }

    public void setSdate(String sdate)
    {
        this.sdate = sdate;
    }

    public int getSid()
    {
        return sid;
    }

    public void setSid(int sid)
    {
        this.sid = sid;
    }

    public String getTdate()
    {
        return tdate;
    }

    public void setTdate(String tdate)
    {
        this.tdate = tdate;
    }

    public String getEdate()
    {
        return edate;
    }

    public void setEdate(String edate)
    {
        this.edate = edate;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public String getObject()
    {
        return object;
    }

    public void setObject(String object)
    {
        this.object = object;
    }

    public String getIspush()
    {
        return ispush;
    }

    public void setIspush(String ispush)
    {
        this.ispush = ispush;
    }

    public String getActiontype()
    {
        return actiontype;
    }

    public void setActiontype(String actiontype)
    {
        this.actiontype = actiontype;
    }

    public String getAction()
    {
        return action;
    }

    public void setAction(String action)
    {
        this.action = action;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }
}