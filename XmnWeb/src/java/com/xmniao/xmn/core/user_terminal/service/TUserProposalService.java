package com.xmniao.xmn.core.user_terminal.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.common.entity.TAdvertising;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.system_settings.entity.TUser;
import com.xmniao.xmn.core.user_terminal.dao.BrandSellerDao;
import com.xmniao.xmn.core.user_terminal.dao.TUserProposalDao;
import com.xmniao.xmn.core.user_terminal.entity.TUserProposal;
import com.xmniao.xmn.core.util.ResultUtil;
@Service
public class TUserProposalService extends BaseService<TUserProposal> {

	@Autowired
	private TUserProposalDao tUserProposalDao;
	@Override
	protected BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return tUserProposalDao;
	}
	
	public void putPageable(TUserProposal tUserProposal,Pageable<TUserProposal> pageable){
		pageable.setContent(tUserProposalDao.getList(tUserProposal));
		pageable.setTotal(tUserProposalDao.count(tUserProposal));
	}
	/**
	 * 
	 * @author dong'jt
	 * 创建时间：2015年9月25日 下午5:01:10
	 * 描述：用户吐槽处理更新
	 * @param tUserProposal
	 * @param request
	 * @return
	 */
	public Resultable updateStatus(TUserProposal tUserProposal,HttpServletRequest request){
		Resultable Resultable=null;
		int num=0;
		try {
			num = updateUserProposal(tUserProposal,request);
		} catch (Exception e) {
			this.log.error("用户处理：", e);
			throw new ApplicationException("用户处理",e,new Object[]{tUserProposal,request},new String[]{"用户处理",tUserProposal.getPhoneid(),"处理","处理"});
		}
		Resultable=this.updateResInfo(num);
		return Resultable;
	}
	public int updateUserProposal(TUserProposal tUserProposal,HttpServletRequest request){//数据更新执行
		int num=0;
		try {
			this.putUserProposalParams(tUserProposal,request);
			num=tUserProposalDao.update(tUserProposal);
		} catch (Exception e) {
			this.log.error("处理：", e);
			throw new ApplicationException("处理",e,new Object[]{tUserProposal,request});
		}
		super.fireLoginEvent(new String[]{"用户账号",tUserProposal.getPhoneid(),"处理","处理"},num==1?1:0);
		return num;
	}
	public void putUserProposalParams(TUserProposal tUserProposal,HttpServletRequest request){//数据参数组装
		TUser tUser=ResultUtil.getCurrentUser(request);
		tUserProposal.setPersonHandling(tUser.getUsername());
		tUserProposal.setPersonHandlingNo(tUser.getUserId().intValue());
		tUserProposal.setDateHandling(new Date());
	}
	public Resultable updateResInfo(int num){//返回数据组装
		if(num==1){
			return new Resultable(true,"操作成功");
		}else{
			return new Resultable(false,"操作失败！");
		}
	}
}
