/**
 * 
 */
package com.xmniao.xmn.core.live.dao;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.live.entity.TMqConsumeRecord;
import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * @ClassName: MqConsumeRecordDao
 * @Description: RocketMq队列消息消费记录
 * @author hkun
 * @date 2016年9月5日 上午11:32:11
 *
 */
@Repository
public interface MqConsumeRecordDao {
	
	/**
	 * 根据消息唯一标示查询消费记录
	 * @paran messageKey 消息唯一标示
	 * @return 消息消费记录
	 */
	@DataSource("joint")
	public TMqConsumeRecord selectConsumeRecord(String messageKey);
	
	/**
	 * 插入消息消费记录
	 * @param TMqConsumeRecord 消息记录
	 * @return 影响条数
	 */
	@DataSource("joint")
	public int insertConsumeRecord(TMqConsumeRecord consumeRecord);
	
	/**
	 * 根据消息唯一标示更新消费失败的消息记录
	 * @param TMqConsumeRecord 更新的消息记录
	 * @return 影响条数
	 */
	@DataSource("joint")
	public Integer updateConsumeRecord(TMqConsumeRecord consumeRecord);
}
