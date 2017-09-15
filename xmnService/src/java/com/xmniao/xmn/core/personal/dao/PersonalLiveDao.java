package com.xmniao.xmn.core.personal.dao;

import com.xmniao.xmn.core.market.entity.pay.ReceivingAddress;
import com.xmniao.xmn.core.util.dataSource.DataSource;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *
 */
@Repository
public interface PersonalLiveDao {

	/**
	 * 查询直播动态
	 * sql从AnchorLiveRecordsMapper.xml, 同名方法拷贝，做了写特殊处理
	 * @param param
	 * @return
	 */
	@DataSource("joint")
    List<Map<Object, Object>> queryLiveRecordDynamic(Map<Object, Object> param);

	/**
	 * 查询参加过的直播活动
	 * @param param
	 * @return
	 */
	@DataSource("joint")
	List<Map<Object, Object>> queryAttendLiveRecordList(Map<Object, Object> param);


	/**
	 * 查询美食探店点评
	 * @param param
	 * @return
	 */
	@DataSource("joint")
	List<Map<Object, Object>> getCommentRecord(Map<Object, Object> param);

	@DataSource("joint")
	List<Map<Object, Object>> getCommentPicNumber(Map<Object, Object> param);


	@DataSource("joint")
	List<Map<Object, Object>> getCommentPicByIdList(Map<Object, Object> param);


	/**
	 * 统计点评数量
	 * @param uid
	 * @return
	 */
	@DataSource("joint")
	Integer selectCommentRecordCount(@Param("uid") Integer uid);


}
