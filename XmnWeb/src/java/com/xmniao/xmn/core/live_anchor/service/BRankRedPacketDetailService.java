/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.live_anchor.dao.BRankRedPacketDetailDao;
import com.xmniao.xmn.core.live_anchor.entity.BRankRedPacketDetail;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：BRankRedPacketDetailService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-2-24 下午4:10:25 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class BRankRedPacketDetailService extends BaseService<BRankRedPacketDetail> {
	
	@Autowired
	private BRankRedPacketDetailDao rankRedPacketDetailDao;

	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return rankRedPacketDetailDao;
	}
	
	/**
	 * 
	 * 方法描述：根据等级详情id删除模式比例配置 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-2-24下午4:04:34 <br/>
	 * @param rankDetailId
	 * @return
	 */
    public int deleteByRankDetailId(Integer rankDetailId){
    	return rankRedPacketDetailDao.deleteByRankDetailId(rankDetailId);
    }


    /**
     * 
     * 方法描述：新增级别返还模式比例 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-2-24下午3:45:11 <br/>
     * @param id
     * @return
     */
    public void add(BRankRedPacketDetail record){
    	 rankRedPacketDetailDao.add(record);
    }
    
    /**
     * 
     * 方法描述：批量新增级别返还模式比例 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-2-24下午3:45:11 <br/>
     * @param id
     * @return
     */
    public Integer addBatch(List<BRankRedPacketDetail> recordList){
    	return rankRedPacketDetailDao.addBatch(recordList);
    }

    /**
     * 
     * 方法描述：根据主键Id查询级别返还模式比例 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-2-24下午3:45:11 <br/>
     * @param id
     * @return
     */
    public BRankRedPacketDetail selectById(Integer id){
    	return rankRedPacketDetailDao.selectById(id);
    }
    
    /**
     * 
     * 方法描述：查询级别返还模式比例列表 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-2-24下午3:45:11 <br/>
     * @param id
     * @return
     */
    public List<BRankRedPacketDetail> getList(BRankRedPacketDetail record){
    	return rankRedPacketDetailDao.getList(record);
    }

    /**
     * 
     * 方法描述：更新级别返还模式比例配置 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-2-24下午3:45:11 <br/>
     * @param id
     * @return
     */
    public Integer update(BRankRedPacketDetail record){
    	return rankRedPacketDetailDao.update(record);
    }
	
}
