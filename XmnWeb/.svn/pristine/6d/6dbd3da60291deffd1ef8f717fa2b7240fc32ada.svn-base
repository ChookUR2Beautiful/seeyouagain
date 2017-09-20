package com.xmniao.xmn.core.user_terminal.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.common.entity.TAdvertising;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.user_terminal.dao.TSellerNoticeDao;
import com.xmniao.xmn.core.user_terminal.entity.TSellerNotice;
import com.xmniao.xmn.core.user_terminal.util.UserConstants;
import com.xmniao.xmn.core.util.ResultUtil;

@Service
public class TSellerNoticeService extends BaseService<TSellerNotice> {

	@Autowired
	private TSellerNoticeDao tSellerNoticeDao;

	@Override
	protected BaseDao getBaseDao() {
		return tSellerNoticeDao;
	}

	public void putPageable(TSellerNotice tSellerNotice,Pageable<TSellerNotice> pageable) {
		pageable.setContent(tSellerNoticeDao.getList(tSellerNotice));
		pageable.setTotal(tSellerNoticeDao.count(tSellerNotice));
	}
    
	public Resultable addTSellerNotice(HttpServletRequest request,TSellerNotice tSellerNotice) {
		Resultable resultable = null;
		try {
			this.putTSellerNoticeParams(request, tSellerNotice);
			tSellerNoticeDao.addReturnId(tSellerNotice);
			resultable=addTSellerNoticeResInfo(tSellerNotice);
		} catch (Exception e) {
			this.log.error("用户须知新增：", e);
			throw new ApplicationException("用户须知新增",e,new Object[]{request,tSellerNotice},infoStr(tSellerNotice));
		}
		return resultable;
	}
	public void putTSellerNoticeParams(HttpServletRequest request,TSellerNotice tSellerNotice){
		tSellerNotice.setSTATUS(1);
		tSellerNotice.setCreator(ResultUtil.getCurrentUser(request).getUsername());
		tSellerNotice.setDateCreated(new Date());
		tSellerNotice.setUpdator(ResultUtil.getCurrentUser(request).getUsername());
		tSellerNotice.setDateUpdated(new Date());
	}
	
	public Resultable addTSellerNoticeResInfo(TSellerNotice tSellerNotice){
		this.log.info("添加成功");
		Resultable resultable = new Resultable(true, "操作成功");
		super.fireLoginEvent(new String[]{"用户须知编号",String.valueOf(tSellerNotice.getId()),"新增","新增"},UserConstants.FIRELOGIN_SUCCESS);
		return resultable;
	}
	public String[] infoStr(TSellerNotice tSellerNotice){
		String word = tSellerNotice.getRemark();
		String str = "";
		if (word.length() <= 12){
			str = word;
		}else{
			str = word.substring(0, 12)+"...";
		}
		String[] s={"用户须知",str,"新增","新增"};
		return s;
	}
	
	public void updateTSellerNoticeInitData(ModelAndView modelAndView, String id) {
		modelAndView.addObject("isType", "update");
		TSellerNotice tSellerNotice = super.getObject(new Long(id));
		this.log.info(id);
		modelAndView.addObject("tSellerNotice", tSellerNotice);
	}

	public Resultable updateTSellerNoticeData(HttpServletRequest request,TSellerNotice tSellerNotice) {
		Resultable resultable = null;
		try {
			tSellerNotice.setUpdator(ResultUtil.getCurrentUser(request).getUsername());
			tSellerNotice.setDateUpdated(new Date());
			super.update(tSellerNotice);
			resultable = this.updateTSellerNoticeinfo(tSellerNotice);
		} catch (Exception e) {
			this.log.error("用户须知修改：", e);
			throw new ApplicationException("用户须知修改", e,new Object[] {request,tSellerNotice }, new String[] {"用户须知编号",String.valueOf(tSellerNotice.getId()), "修改", "修改" });
		}
		return resultable;
	}

	public Resultable updateTSellerNoticeinfo(TSellerNotice tSellerNotice) {
		this.log.info("修改成功");
		String[] s = { "用户须知编号", String.valueOf(tSellerNotice.getId()),"修改", "修改" };
		super.fireLoginEvent(s, UserConstants.FIRELOGIN_SUCCESS);
		return new Resultable(true, "操作成功");
	}
	
    
}
