package com.xmniao.dao.live;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmniao.domain.live.LiveSalary;
import com.xmniao.domain.live.LiveSalaryData;
import com.xmniao.domain.live.LiverBean;

public interface LiveSalaryDao {
    int deleteByPrimaryKey(Integer id);

    int insert(LiveSalary record);

    int insertSelective(LiveSalary record);

    LiveSalary selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LiveSalary record);

    int updateByPrimaryKey(LiveSalary record);
    
    /**
     * 
     * 方法描述：获取上月统计礼物数
     * 创建人：jianming  
     * 创建时间：2017年3月31日 下午6:04:18   
     * @param beginDate
     * @param endDate
     * @return
     */
	List<LiveSalaryData> getLastMonGive(@Param("beginDate")Date beginDate,@Param("endDate") Date endDate);
	
	/**
	 * 
	 * 方法描述：批量插入
	 * 创建人：jianming  
	 * 创建时间：2017年4月1日 下午3:26:45   
	 * @param liveSalarys
	 * @return
	 */
	int addBatch(List<LiveSalary> liveSalarys);
	
	
	/**
	 * 
	 * 方法描述：插入失败记录
	 * 创建人：jianming  
	 * 创建时间：2017年4月5日 上午9:41:15   
	 * @param fail
	 * @param endDate 
	 * @param beginDate 
	 */
	void addFailBatch(HashMap<String, Object> hashMap);
	
	/**
	 * 
	 * 方法描述：获取指定月份的工资信息
	 * 创建人：jianming  
	 * 创建时间：2017年4月10日 下午8:34:18   
	 * @param integer
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	LiveSalary selectAnchorAtTime(@Param("anchorId")Integer anchorId, @Param("countTime") String countTime);

	
	/**
     * 
     * 方法描述：获取上月统计礼物数
     * 创建人：jianming  
     * 创建时间：2017年3月31日 下午6:04:18   
     * @param beginDate
     * @param endDate
     * @return
     */
	LiveSalaryData getLastMonGiveByAnchorId(@Param("beginDate")Date beginDate, @Param("endDate")Date endDate, @Param("anchorId") Integer leverId);
	
	/**
	 * 
	 * 方法描述：修改失败状态为已处理
	 * 创建人：jianming  
	 * 创建时间：2017年4月11日 下午2:20:26   
	 * @param leverId
	 * @param updateTime
	 * @return
	 */
	int updateFailToSuccess(@Param("anchorId")Integer anchorId,@Param("countTime") String updateTime);
	
	/**
	 * 
	 * 方法描述：修改失败信息
	 * 创建人：jianming  
	 * 创建时间：2017年4月11日 下午2:23:01   
	 * @param liverBean
	 * @param leverId
	 * @param updateTime
	 * @return
	 */
	int updateFail(@Param("liverBean")LiverBean liverBean,@Param("anchorId") Integer anchorId,@Param("countTime") String updateTime);

	 /**
	 * 方法描述：查询本月工资条数量
	 * 创建人： jianming <br/>
	 * 创建时间：2017年7月6日下午5:42:32 <br/>
	 * @return
	 */
	long hasThisMonSalary();
	
}