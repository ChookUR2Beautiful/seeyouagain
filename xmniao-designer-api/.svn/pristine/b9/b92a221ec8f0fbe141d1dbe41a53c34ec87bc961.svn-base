package com.xmn.designer.service.account.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmn.designer.base.ResponseCode;
import com.xmn.designer.dao.account.UserDao;
import com.xmn.designer.entity.account.SellerAccount;
import com.xmn.designer.entity.account.User;
import com.xmn.designer.service.account.AccountService;
import com.xmn.designer.service.base.RedisService;
@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private RedisService  redisService;
    
    @Autowired
    private UserDao userDao;
    /**
     * 从商户端登录，生成自设计的token，创建自设计的用户
     * @param sessionToken
     * @return
     */
    @Transactional
    @Override
    public Map<String, Object> enter(String sessionToken) {
        SellerAccount sellerAccount = redisService.getSellerAccount(sessionToken);
        Map<String,Object> result = new HashMap<String, Object>();
        if(sellerAccount==null){
            result.put("code", ResponseCode.FAILURE);
            result.put("msg", "商户端sessionToken错误！");
            return result;
        }
        //查询数据库是否存在用户
        User user = userDao.findUserByOutId(Long.valueOf(sellerAccount.getSellerid()));
        //创建自设计的用户
        if(user==null){
            User bean =new User();
            bean.setOutId(Long.valueOf(sellerAccount.getSellerid()));
            bean.setName(sellerAccount.getNname());
            bean.setType("001");//外部登录账号
            bean.setCreateTime(new Date());
            userDao.insert(bean);
            //保存session到redis
            sessionToken =  redisService.setDesignerSessionCache(bean);
        }else{
            sessionToken =  redisService.setDesignerSessionCache(user);
        }
        result.put("code", ResponseCode.SUCCESS);
        result.put("msg", "登录成功");
        result.put("sessionToken", sessionToken);
        return result;
    }

}
