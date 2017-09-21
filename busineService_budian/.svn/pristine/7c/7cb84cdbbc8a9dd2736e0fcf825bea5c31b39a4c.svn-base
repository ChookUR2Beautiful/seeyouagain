package com.xmniao.urs.dao;

import com.xmniao.domain.urs.BursEarningsRelationChain;
import com.xmniao.domain.urs.UrsEarningsRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UrsEarningsRelationDao {

	UrsEarningsRelation getUrsEarningsRelation(UrsEarningsRelation relation);

    int insertUrsEarningsRelation(UrsEarningsRelation record);

    int updateUrsEarningsRelation(UrsEarningsRelation record);
    
	/**
	 * 查询v客的所有间接下级
	 * 方法描述：
	 * 创建人： ChenBo
	 * 创建时间：2017年4月28日
	 * @param uid
	 * @return List<Integer>
	 */
	List<String> getVerIndirectSubordinateList(Integer uid);

	/**
	 * 查询用户关系链
	 * @param uid               用户uid
	 * @param objectOriented    会员渠道来源
	 * @return
	 */
    String selectUserRelation(@Param("uid") Integer uid, @Param("objectOriented") Integer objectOriented);

	/**
	 * 查询用户关系链
	 * @param uid               用户uid
	 * @param objectOriented    会员渠道来源
	 * @return
	 */
	int countUserRelation(@Param("uid") Integer uid, @Param("objectOriented") Integer objectOriented);

	/**
	 * 更新关系链中的庄园的等级
	 * @param uid
	 * @param no
	 */
    void updateManorLevel(@Param("uid") Integer uid, @Param("no") Integer no);

    void insertInitChain(@Param("uid") Integer uid, @Param("parentUid") Integer parentUid, @Param("object_oriented") Integer object_oriented);

    /** 查询上级 uid */
	Integer selectSuperUidByUid(@Param("uid") Integer uid, @Param("objectOriented") Integer objectOriented);

	List<UrsEarningsRelation> selectAll();

	UrsEarningsRelation selectByUid(Long parentUid);

	void insertChain(@Param("subUid") Integer subUid, @Param("parentUid") Long parentUid);

	/** 查询所有下级UID */
    List<Integer> selectUidBySuperUid(@Param("parentUid") Integer parentUid);

	 /**
	 * 方法描述：查询所有上级
	 * 创建人： jianming <br/>
	 * 创建时间：2017年9月7日上午10:58:50 <br/>
	 * @param uid
	 * @param ObjcetOrientd
	 * @return
	 */
	List<UrsEarningsRelation> getParentsByUid(@Param("uid")Integer uid,@Param("objectOriented") Integer objcetOrientd);

	 /**
	 * 方法描述：批量添加关系链chain
	 * 创建人： jianming <br/>
	 * 创建时间：2017年9月7日上午11:11:12 <br/>
	 * @param list
	 * @return
	 */
	int addChainBatch(List<BursEarningsRelationChain> list);

	 /**
	 * 方法描述：删除与上级的所有关系链
	 * 创建人： jianming <br/>
	 * 创建时间：2017年9月11日下午3:01:46 <br/>
	 * @param childIds
	 * @param beforeUids
	 * @return
	 */
	int deleteChains(@Param("childIds")List<Integer> childIds,@Param("beforeUids") List<Integer> beforeUids);

	 /**
	 * 方法描述：批量更新等级 (相加)
	 * 创建人： jianming <br/>
	 * 创建时间：2017年9月11日下午3:12:37 <br/>
	 * @param childIds
	 * @param level
	 */
	int updateManorLevels(@Param("childIds")List<Integer> childIds,@Param("level") Integer lever);

	 /**
	 * 方法描述：获取所有下级uid(包括自己)
	 * 创建人： jianming <br/>
	 * 创建时间：2017年9月11日下午3:51:36 <br/>
	 * @param uid
	 * @return
	 */
	List<Integer> getManorChildsUid(Integer uid);

	 /**
	 * 方法描述：修改b_urs_earnings_relation parent_uid
	 * 创建人： jianming <br/>
	 * 创建时间：2017年9月11日下午4:09:51 <br/>
	 * @param childId
	 * @param parentId
	 * @param objcetOrientd 
	 */
	int updateParentUid(@Param("childId") Integer childId,@Param("parentId") Integer parentId,@Param("objectOriented") Integer objectOriented);



}