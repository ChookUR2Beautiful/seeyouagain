package com.xmniao.dao;

import java.util.List;
import java.util.Map;

public interface JointInfoMapper {
	
	
	public Map<String,Object> getIncomeWithdraw(Map<String,Object> param);
	
	public List<Map<String,Object>> getSomeIncomeWithdraws(Map<String,Object> param);
	


}
