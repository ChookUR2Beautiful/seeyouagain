package com.xmn.saas.constants;

public class SaasConsts {

    public static final String USER_INFO = "userinfo";          // 登录的用户信息

    public static final String OUTER_TOKEN = "outertoken";      // 外部用户登录令牌

    public static final String SESSION_TOKEN = "sessionToken";  // 内部注册用户登录令牌
    // TODO 修改appid
    public static final Object APP_ID = "998899";               // appId



    // 网红晒照 服务类型
    public static final Integer CELEBRITY_TASK_SERVICE_TYPE_PICTURE = 1;
    // 网红分享 服务类型
    public static final Integer CELEBRITY_TASK_SERVICE_TYPE_SHARE = 2;
  
    //微图助力排序默认
    public static final Integer MICROGRAPH_SERIAL_TYPE_DEFALUT=1;
    
    //微图助力排序从高到底
    public static final Integer MICROGRAPH_SERIAL_TYPE_UP=2;
  //微图助力排序从底到高
    public static final Integer MICROGRAPH_SERIAL_TYPE_DOWN=3;

    // 不可以外部实例化该类
    private SaasConsts() {
    }
}
