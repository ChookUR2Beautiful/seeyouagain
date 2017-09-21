package com.xmniao.dao.manor;

import com.xmniao.domain.manor.ManorFlowerCount;
import com.xmniao.domain.manor.ManorFlowerRelation;
import com.xmniao.domain.manor.ManorOperate;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public interface ManorFlowerRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ManorFlowerRelation record);

    int insertSelective(ManorFlowerRelation record);

    ManorFlowerRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ManorFlowerRelation record);

    int updateByPrimaryKey(ManorFlowerRelation record);

    /**
     * @param uid
     * @return
     */
    ManorFlowerRelation selectInitialNode(Integer uid);

    /**
     * 更新花朵左值
     * 左值大于level的左值 + 2
     * @param level
     */
    void updateFlowerLeft(Integer level);

    /**
     * 更新花朵右值
     * 右值大于level的右值 + 2
     * @param level
     */
    void updateFlowerRight(Integer level);

    /**
     * 根据zid获取父级节点
     * @return
     * @param zid
     */
    ManorFlowerRelation selectParentNodeByZid(Integer zid);

    /**
     * 查询底层节点
     * @param leftNode
     * @param rightNode
     * @param location
     * @return
     */
    ManorFlowerRelation selectFootNode(@Param("leftNode") Integer leftNode, @Param("rightNode")
            Integer rightNode, @Param("location") Integer location);

    /**
     * 查询最大的id
     * @return
     */
    Integer selectMaxId();

    /**
     * 更新花苗状态
     * @param fromUid   用户庄园初始花朵链节点
     * @param operate       操作信息
     * @param endTime       花朵枯萎时间
     * @return
     */
    int updateSeedling(@Param("fromUid") Integer fromUid,
                       @Param("operate") ManorOperate operate, @Param("endTime") Date endTime);

    /**
     * 更新花朵时间
     * @param fromUid   用户庄园实话花朵链节点
     * @param operate       操作信息
     * @param days          延期天数
     * @return
     */
    int updateFlower(@Param("fromUid") Integer fromUid,
                     @Param("operate") ManorOperate operate, @Param("days") Integer days);

    /**
     * 根据uid统计花朵/花苗数量
     * 包括node自身
     * @param node
     */
    List<ManorFlowerCount> countFlowerByNode(@Param("node") ManorFlowerRelation node);

    /**
     * 根据pid 和 位置查询下级节点
     * @param id
     * @param location
     * @return
     */
    ManorFlowerRelation selectByPidAndLocation(@Param("pid") Integer pid, @Param("location") Integer location);

    /**
     * 查询子节点
     * @param uid
     * @return
     */
    List<ManorFlowerRelation> selectSubNodes(Integer uid);

    /**
     * 统计子节点 已种植花数量
     * @param subNode
     * @param fromUid
     */
    long countFlowerByNodeAndFromuid(@Param("subNode") ManorFlowerRelation subNode, @Param("fromUid") int fromUid);

    /**
     * 查询子节点
     * @param uid
     * @param location
     * @return
     */
    ManorFlowerRelation selectSubNode(@Param("uid") int uid, @Param("location") int location);

    /**
     * 插入节点到数中
     * @param flowerInfo
     * @param parentNode
     */
    void insertFlowerToBranch(@Param("flowerInfo") ManorFlowerRelation flowerInfo, @Param("parentNode") ManorFlowerRelation parentNode);

    /**
     * 更新左值
     * @param target    修改的目标值
     * @param preZid    之前的zid
     */
    int updateZid(@Param("target") Integer target, @Param("preZid") Integer preZid);

    /**
     * 更新关系链 和 level
     * @param levelDifference   层级差
     * @param initialNode       初始节点信息
     * @param parentNode
     */
    int updateLevelAndChainByInitNode(@Param("levelDifference") int levelDifference,
                                      @Param("initialNode") ManorFlowerRelation initialNode,
                                      @Param("parentNode") ManorFlowerRelation parentNode);

    /**
     * 根据uid 更新节点花类型
     * @param uid
     * @param flowerType
     */
    int updateFlowerType(@Param("uid") Integer uid, @Param("flowerType") Integer flowerType);

    /**
     * 查询子节点下已枯萎的花朵
     * @param uid
     * @param location
     * @return
     */
    LinkedList<ManorFlowerRelation> selectSprishedFlowers(@Param("uid") Integer uid, @Param("location") Integer location);
}