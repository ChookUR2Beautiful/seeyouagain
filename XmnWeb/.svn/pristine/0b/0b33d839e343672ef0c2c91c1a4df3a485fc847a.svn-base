package com.xmniao.xmn.core.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.common.dao.TBankDao;
import com.xmniao.xmn.core.common.entity.TBank;
@Service
public class TBankService extends BaseService<TBank> {
	@Autowired
	private TBankDao tBankDao;
	@Override
	protected BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return tBankDao;
	}
	
	public Object getObjects(String abbrev){
		Resultable resultable = new Resultable();
		List<TBank> tbanks= tBankDao.getObject();
		StringBuffer  options=new StringBuffer();
		for(TBank tbank:tbanks){
			if(!(null==abbrev)&&tbank.getAbbrev().equals(abbrev)){
				options.append("<option value='"+tbank.getAbbrev()+"'selected = selected>"+tbank.getBankname()+"</option>");
			}else{
				options.append("<option value='"+tbank.getAbbrev()+"'>"+tbank.getBankname()+"</option>");
			}
		}
		resultable.setData(options.toString());
		resultable.setMsg("Success");
		resultable.setSuccess(true);
	    return resultable;
	}
	
	public TBank getTBank(TBank tBank){
		return tBankDao.getTBank(tBank);
	}
}
