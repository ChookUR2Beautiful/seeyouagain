package com.xmniao.xmn.core.vstar.dao;

import com.xmniao.xmn.core.util.dataSource.DataSource;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/2.
 */
@Repository
public interface VStarPlayerInfoDao {

    /**
     * 通过uid查询vstar用户信息
     * @param uid
     * @return
     */
    @DataSource("joint")
    Map<Object, Object> selectVStarPlayerInfoByUid(@Param("uid") Integer uid);

    /**
     * 通过uid查询vstar用户信息
     * @param uid
     * @return
     */
    @DataSource("joint")
    Map<Object, Object> selectVStarInfoByUid(@Param("uid") Integer uid);
    
    /**
     * 通过uid列表，批量查询vstar用户信息
     * @param uidList
     * @return
     */
    @DataSource("joint")
    List<Map<Object, Object>> selectVStarPlayerInfoByUidList(@Param("uidList") List<Integer> uidList);

	/**
	 * 查询主播大赛报名是否有实名认证 返回int
	 * @param map
	 * @return int
	 * */
    @DataSource("joint")
	public int queryVStarRealNameState(Map<Object, Object> map);
    
    /**
	 * 查询主播大赛报名是否 通告复赛 返回int
	 * @param map
	 * @return int
	 * */
    @DataSource("joint")
	public int queryVStarRetestState(Map<Object, Object> map);
    
    /**
     * 根据uid查询主播的大赛选手id，推荐人uid
     * @Title:queryPlayerAndReferrerByUid
     * @param uid
     * @return Map<Object,Object> 主播uid，大赛选手id，推荐人uid
     * 2017年8月7日下午5:30:46
     */
    @DataSource("joint")
	Map<Object, Object> queryPlayerAndReferrerByUid(String uid);
	
    
}
