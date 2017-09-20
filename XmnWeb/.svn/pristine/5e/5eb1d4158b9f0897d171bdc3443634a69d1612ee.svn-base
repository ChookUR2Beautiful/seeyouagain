package com.xmniao.xmn.core.live_anchor.dao;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.live_anchor.entity.AnchorRatioBean;
import com.xmniao.xmn.core.live_anchor.entity.BLiver;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 
 * 项目名称：XmnWeb_LVB
 * 
 * 类名称：BLiverDao
 *
 * 类描述：直播主播或用户实体类
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-8-12下午6:25:58
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface BLiverDao extends BaseDao<BLiver>{
	/**
	 * 
	 * 方法描述：删除主播信息
	 * 创建人： huang'tao
	 * 创建时间：2016-8-6下午3:06:13
	 * @param anchorId
	 * @return
	 */
	@DataSource("burs")
    int deleteByPrimaryKey(Integer anchorId);
	
	/**
	 * 
	 * 方法描述：根据主播ID删除关注信息
	 * 创建人：  huang'tao
	 * 创建时间：2016-9-21上午9:49:58
	 * @param anchorId
	 * @return
	 */
	@DataSource("master")
	int deleteFocusByAnchorId(Integer anchorId);

    /**
     * 
     * 方法描述：添加主播信息
     * 创建人： huang'tao
     * 创建时间：2016-8-6下午3:06:29
     * @param record
     * @return
     */
    @DataSource("burs")
    int insertSelective(BLiver record);

    /**
     * 
     * 方法描述：查询主播信息
     * 创建人： huang'tao
     * 创建时间：2016-8-6下午3:06:59
     * @param anchorId
     * @return
     */
    @DataSource("burs")
    BLiver selectByPrimaryKey(Integer anchorId);
    
    /**
     * 
     * 方法描述：查询主播/观众信息
     * 创建人： huang'tao
     * 创建时间：2016-8-6下午3:06:59
     * @param anchorId
     * @return
     */
    @DataSource("burs")
    BLiver selectBLiver(BLiver liver);

    /**
     * 
     * 方法描述：更新主播信息
     * 创建人： huang'tao
     * 创建时间：2016-8-6下午3:07:43
     * @param record
     * @return
     */
    @DataSource("burs")
    int updateByPrimaryKeySelective(BLiver record);
    
    /**
     * 
     * 方法描述：获取主播列表信息
     * 创建人： huang'tao
     * 创建时间：2016-8-6下午3:07:43
     * @param record
     * @return
     */
    @DataSource("burs")
    List<BLiver> getList(BLiver record);
    
    
    /**
     * 
     * 方法描述：获取直播会员列表信息
     * 创建人： huang'tao
     * 创建时间：2016-8-6下午3:07:43
     * @param record
     * @return
     */
    @DataSource("burs")
    List<BLiver> getLiveMemberList(BLiver record);
    
    /**
     * 
     * 方法描述：获取主播信息表纪录数
     * 创建人： huang'tao
     * 创建时间：2016-8-6下午3:07:43
     * @param record
     * @return
     */
    @DataSource("burs")
    Long count(BLiver record);
    
    
    /**
     * 
     * 方法描述：根据用户uid数组获取主播信息
     * 创建人： huang'tao
     * 创建时间：2016-8-6下午3:07:43
     * @param record
     * @return
     */
    @DataSource("burs")
    List<BLiver> selectLiversByUids(Object[] uids);
    
    /**
     * 
     * 方法描述：根据用户uid数组获级别最高的企业级推荐人信息
     * 创建人： huang'tao
     * 创建时间：2016-8-6下午3:07:43
     * @param record
     * @return
     */
    @DataSource("burs")
    BLiver selectEnterpriseByUids(Object[] uids);
    
    /**
     * 
     * 方法描述：根据用户uid数组获取主播及会员信息
     * 创建人： huang'tao
     * 创建时间：2016-8-6下午3:07:43
     * @param record
     * @return
     */
    @DataSource("burs")
    List<BLiver> selectLiverInfoByIds(Object[] ids);

	/**
	 * 方法描述：查询群组号
	 * 创建人： jianming
	 * 创建时间：2016年9月14日下午1:57:30
	 * @param anchorId
	 * @return
	 */
    @DataSource("burs")
	String getGroupId(Integer anchorId);
    
    /**
     * 
     * 方法描述：获取关注了该主播的用户ID
     * 创建人：  huang'tao
     * 创建时间：2016-9-21上午10:20:39
     * @param anchorId
     * @return
     */
    @DataSource("master")
    List<String> getLiverStartId(Integer anchorId);
    
    /**
     * 
     * 方法描述：更新用户关注主播数
     * 创建人：  huang'tao
     * 创建时间：2016-9-21上午10:46:37
     * @param ids
     * @return
     */
    @DataSource("burs")
    int updateConcernNums(Object[] ids);
    
    
    /**
     * 
     * 方法描述：分组统计企业级推荐人下线人数(已作废)<br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2016-12-26下午4:39:06 <br/>
     * @return
     */
    @DataSource("burs")
    List<BLiver> countJuniorsGroupByEUid(Map<String,Object> map);
    
    /**
     * 
     * 方法描述：统计企业级推荐人下线人数 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-1-6下午4:16:09 <br/>
     * @param map
     * @return
     */
    @DataSource("burs")
    List<BLiver> countJuniorsByEUids(Map<String,Object> map);
    
    
    /**
     * 
     * 方法描述：根据uid统计下线人数 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-1-6下午4:16:09 <br/>
     * @param uid
     * @return
     */
    @DataSource("burs")
    BLiver countJuniorsByUid(Integer uid);
    
    /**
     * 
     * 方法描述：根据直播用户Id数组更新主播分成<br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-1-15上午10:48:36 <br/>
     * @param anchorRatioBean
     * @return
     */
    @DataSource("burs")
    int updateRatioBatchByIds(AnchorRatioBean anchorRatioBean);
    
    /**
     *  
     * 方法描述：获取等下级会员 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-2-7下午4:48:12 <br/>
     * @param liver
     * @return
     */
    @DataSource("burs")
    List<BLiver> getJuniorList(BLiver liver);
    
    /**
     * 
     * 方法描述：根据主键更新会员关系链等信息 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-2-8下午2:00:54 <br/>
     * @param liver
     * @return
     */
    @DataSource("burs")
    int updateRelationChainInfo(BLiver liver);
    
    /**
     * 方法描述：查询主播名字信息<br/>
     * 创建人：  <br/>
     * 创建时间：2017年3月15日上午9:40:10 <br/>
     * @return
     */
    @DataSource("burs")
    List<BLiver> selectFloatAdvertLivesInfo(BLiver liver);
    
    /**
     * 
     * 方法描述：统计主播分类情况 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-4-13下午4:33:05 <br/>
     * @return
     */
    @DataSource("burs")
    Map<String,Object> countAnchor();
    
    /**
     * 
     * 方法描述：将会员间接上级置为null <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-5-9下午6:36:13 <br/>
     * @param liver
     * @return
     */
    @DataSource("burs")
    int updateIndirect2Null(BLiver liver);


    /**
     * 根据uid 查询一个b_liver
     * @param uid
     * @return
     */
    @DataSource("burs")
    BLiver selectByUid(Integer uid);

    @DataSource("burs")
    ArrayList<BLiver> selectSubordinate(Integer uid);

    @DataSource("burs")
    List<BLiver> selectByIndirectUid(Integer uid);

    @DataSource("burs")
    int countDirectSubordinateByUid(@Param("uid") Integer uid);

    @DataSource("burs")
    List<BLiver> selectDirectSubordinates(@Param("uid") Integer uid);

    @DataSource("burs")
    String queryLiverLevel(@Param("uid") Integer uid);

    @DataSource("burs")
    List<BLiver> likeLiverByPhone(@Param("phone") String phone);

    @DataSource("burs")
    List<BLiver> likeLiverByUid(@Param("uid") String uid);
	
    @DataSource("burs")
    BLiver getUrsDetailInfoByUid(@Param("uid") Integer uid);  //

	/**
	 * 方法描述：根据直播id查询uid
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月31日上午11:44:24 <br/>
	 * @param anchorId
	 * @return
	 */
    @DataSource("burs")
	Integer getUidByAnchirId(Integer anchorId);
    
    @DataSource("burs")
	public List<Map<String, BigDecimal>> getRecommendOrderAmountList(Object[] objects);
    
    /**
     * 
     * 方法描述：获取主播真实粉丝数 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-9-1下午2:39:19 <br/>
     * @param param
     * @return
     */
    List<BLiver> getRealFansList(Map<String,Object> param);
    
    /**
     * 
     * 方法描述：获取主播机器人粉丝数 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-9-1下午3:25:17 <br/>
     * @param param
     * @return
     */
    List<BLiver> getRobotFansList(Map<String,Object> param);
    
}