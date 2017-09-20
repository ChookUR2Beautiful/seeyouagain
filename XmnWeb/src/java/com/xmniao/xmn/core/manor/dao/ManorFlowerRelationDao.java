package com.xmniao.xmn.core.manor.dao;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.manor.entity.FlowerNode;
import com.xmniao.xmn.core.manor.entity.TManorFlowerRelation;
import com.xmniao.xmn.core.xmnburs.entity.Burs;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface ManorFlowerRelationDao extends BaseDao<TManorFlowerRelation> {
	
    int deleteByPrimaryKey(Integer id);

    Integer insert(TManorFlowerRelation record);

    int insertSelective(TManorFlowerRelation record);

    TManorFlowerRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TManorFlowerRelation record);

    int updateByPrimaryKey(TManorFlowerRelation record);
    
    TManorFlowerRelation getFlowerRelationInfo(TManorFlowerRelation record);
    
    List<TManorFlowerRelation> getFlowerRelationNumberInfo(Map<String, Integer> params);

    /**
     * 查询子节点
     * @param uid
     * @return
     */
    List<FlowerNode> selectSubNodes(Integer uid);


    /**
     * 根据节点查询该节点下的说所有花
     * @param node
     * @return
     */
    List<TManorFlowerRelation> selectByNode(@Param("node") TManorFlowerRelation node);



    List<TManorFlowerRelation> getCurrentFlowerRelationCount(Object[] objects);


    List<TManorFlowerRelation> getTotalFlowerRelationCount(Object[] objects);


    /**
     * 查询用户初始节点
     * @param uid
     * @return
     */
    FlowerNode selectInitNode(Integer uid);

    List<FlowerNode> selectAllNode(@Param("initNode") FlowerNode initNode);

    Integer selectLocationByRelationChain(String relationChain);

    List<FlowerNode> selectAllNodeEx(@Param("uid") Integer uid);

    Integer countPerishedFlowers(@Param("nodeId") Integer nodeId);

    Integer countLivedFlowers(Integer nodeId);

	/**
	 * 方法描述：查询没有下级的花田
	 * 创建人： jianming <br/>
	 * 创建时间：2017年9月8日上午10:06:11 <br/>
	 * @param ursList 根据用户列表查询,可传null
	 * @return
	 */
	List<TManorFlowerRelation> getNotChildsFlowerRelation(List<Burs> ursList);
}