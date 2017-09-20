package com.xmniao.Template;

/**
 * 短信返回实体
 *
 * @auther LiYuanBo
 * @Create 2017/3/30
 */
public class SMSResultCode {



    private int state ;
    private String info;

    public int getState() {
        return state;
    }

    public String getInfo() {
        return info;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "SMSResultCode{" +
                "state=" + state +
                ", info='" + info + '\'' +
                '}';
    }

    public  enum Code {
        SUCCESS(100,"发送成功"),
        KEYNOT(101,"APPID应用编码验证失败"),
        OBJNULL(102,"短信内容不能为空"),
        ERRNULL(103,"手机号码不能为空"),
        ERRDATA(104,"请求参数或格式错误"),
        ERRCODE(105,"短信验证码格式不正确"),
        ERRCODEGQ(106,"短信验证码已过期"),
        ERRVCODEGQ(107,"短信验证码验证失败"),
        FAILURE(200,"发送失败"),
        BIDNULL(201,"订单编号为空"),
        SELLERNULL(202,"商户名称或商品名称为空"),
        OPENNULL(203,"微信openid为空"),
        MONEYNULL(204,"订单支付金额为空"),
        TITLENULL(205,"发送失败");


        private int state;
        private String message;

        private Code(int state,String message){
            this.state = state;
            this.message = message ;
        }

        public int getState() {
            return state;
        }

        public String getMessage() {
            return message;
        }

        public void setState(int state) {
            this.state = state;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
