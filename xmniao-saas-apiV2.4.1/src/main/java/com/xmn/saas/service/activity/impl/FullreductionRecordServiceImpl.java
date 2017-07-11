/**
 * 
 */
package com.xmn.saas.service.activity.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmn.saas.base.GlobalConfig;
import com.xmn.saas.base.thrift.common.ResponseData;
import com.xmn.saas.dao.activity.FullreductionRecordDao;
import com.xmn.saas.entity.activity.FullreductionRecord;
import com.xmn.saas.service.activity.FullreductionRecordService;
import com.xmn.saas.service.redpacket.RedpacketService;

/**   
 * 项目名称：xmniao-saas-api    
 * 类描述：   满减活动用户领取记录服务实现
 * 创建人：huangk   
 * 创建时间：2016年9月29日 下午2:54:10      
 */
@Service
public class FullreductionRecordServiceImpl implements FullreductionRecordService {
	
	/**
	 * 注入满减活动mapper接口
	 */
	@Autowired
	private FullreductionRecordDao fullreductionRecordDao;
	
	@Autowired
	private RedpacketService redpacketService;
	
	@Autowired
	private GlobalConfig globalConfig;

	/** 
	 * 根据满减活动id查询用户领取明细
	 * @param aid 满减活动id
	 * @return List<FullreductionRecord> 用户领取记录
	 * @throws SQLException 
	 */
	@Override
	public List<Map<String,Object>> queryListByAid(int aid) throws SQLException {
		List<Map<String,Object>> recordList = fullreductionRecordDao.queryListByAid(aid);
		if(recordList!=null&recordList.size()>0){
		    
		    for(Map<String,Object> coupon :recordList){
                Map<String,String> params = new HashMap<>();
                params.put("uid", coupon.get("uid")+"");
                ResponseData responseData;
                try {
                    responseData = redpacketService.getUserMsg(params);
                    if(responseData!=null && responseData.getState()==0){
                        String nname=  responseData.getResultMap().get("nname");
                        String avatar=  responseData.getResultMap().get("avatar");
                        String phone=  responseData.getResultMap().get("phone");
                        coupon.put("cname", StringUtils.isNotBlank(nname)?nname:phone);
                        if(StringUtils.isNotBlank(avatar)){
                        	coupon.put("avatar", globalConfig.getImageHost()+avatar);
                        }else{
                        	coupon.put("avatar",null);
                        }
                     }else{
                         coupon.put("cname", StringUtils.isNotBlank(coupon.get("phone").toString())?coupon.get("phone"):"匿名"); 
                     }
                } catch (Exception e) {
                    e.printStackTrace();
                }
              
            }
			return recordList;
		}
		return null;
	}

	/** 
	 * 统计活动总的免减金额
	 */
	@Override
	public BigDecimal countReductionAmountByAid(int aid) {
		return fullreductionRecordDao.countReductionAmountByAid(aid);
	}

	/** 
	 * 统计活动总的参与人数
	 */
	@Override
	public int countJoinNumByAid(int aid) {
		return fullreductionRecordDao.countJoinNumByAid(aid);
	}

	/** 
	 * 主键查询记录
	 */
	@Override
	public FullreductionRecord queryById(int id) {
		return fullreductionRecordDao.selectByPrimaryKey(id);
	}

	/** 
	 * 统计新绑定用户数
	 */
	@Override
	public int countNewuserByAid(int aid) {
		return fullreductionRecordDao.countNewuserByAid(aid);
	}

}
