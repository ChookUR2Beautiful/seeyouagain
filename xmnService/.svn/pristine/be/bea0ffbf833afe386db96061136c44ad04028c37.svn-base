package com.xmniao.xmn.core.market.dao;

import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.market.entity.home.FreshModule;
import org.apache.ibatis.annotations.Param;

import com.xmniao.xmn.core.market.base.Page;
import com.xmniao.xmn.core.market.entity.pay.ProductInfo;
import com.xmniao.xmn.core.util.dataSource.DataSource;
import org.springframework.stereotype.Repository;

/**
 * 
* @projectName: xmnService 
* @ClassName: ProductInfoDao    
* @Description:商品详情dao   
* @author: liuzhihao   
* @date: 2016年12月22日 上午10:58:37
 */
@Repository
public interface ProductInfoDao {
	
	@DataSource("joint")
    int deleteByPrimaryKey(Integer pid);

	@DataSource("joint")
    int insert(ProductInfo record);

	@DataSource("joint")
    int insertSelective(ProductInfo record);

	@DataSource("joint")
    ProductInfo selectByPrimaryKey(Integer pid);

    @DataSource("joint")
    List<ProductInfo> selectByPrimaryKeyList(Map<Object, Object> map);
	
	@DataSource("joint")
	ProductInfo selectByCodeId(Integer pid);

	@DataSource("joint")
    int updateByPrimaryKeySelective(ProductInfo record);

	@DataSource("joint")
    int updateByPrimaryKeyWithBLOBs(ProductInfo record);

	@DataSource("joint")
    int updateByPrimaryKey(ProductInfo record);

	@DataSource("joint")
    List<ProductInfo> selectActivityProduct(@Param("activityId") Long activityId, @Param("page") Page page);


    @DataSource("joint")
    List<ProductInfo> selectSelectedProduct(@Param("typeId") Long typeId,  @Param("page") Page page);

    @DataSource("joint")
    Long countSelectedProdect(@Param("typeId") Long typeId);

    @DataSource("joint")
    List<ProductInfo> selectHotSaleProduct(@Param("typeId") Long typeId, @Param("page") Page page);

    @DataSource("joint")
    List<ProductInfo> selectRecommed(@Param("typeId") Long typeId);
    

    @DataSource("joint")
    Long countActivity(@Param("activityId") Long activityId);


    @DataSource("joint")
    Long countActivityByType(@Param("activityId") Long activityId, @Param("type") Integer type);

    @DataSource("joint")
    List<ProductInfo> selectActivityProductByActivityId(@Param("activityId") Long activityId, @Param("page") Page page);

    @DataSource("joint")
    Long countProductByCondition(@Param("typeId") Long typeId, @Param("brandId") Long brandId, @Param("maxMoney") Double maxMoney, @Param("minMoney") Double minMoney);

    @DataSource("joint")
    List<ProductInfo> queryProductByCondition(@Param("typeId") Long typeId, @Param("brandId") Long brandId, @Param("maxMoney") Double maxMoney, @Param("minMoney") Double minMoney, @Param("page") Page page);

    @DataSource("joint")
    List<ProductInfo> selectLikeWord(@Param("word") String word, @Param("page") Page page);


    @DataSource("joint")
    List<ProductInfo> selectManualActivityProduct(@Param("freshModule") FreshModule freshModule, @Param("page") Page page);

    @DataSource("joint")
    int updateProductInfoStore(@Param("codeid")String codeid,@Param("num")Integer num);

    @DataSource("joint")
    List<ProductInfo> selectSpikeProductByActivityId(@Param("activityId") Long activityId, @Param("page") Page page);

    @DataSource("joint")
    String queryBreviaryByCode();

    @DataSource("joint")
    List<String> selectHotSaleCodeid(@Param("typeId") Integer typeId, @Param("limit") int i);

    @DataSource("joint")
	List<Map<Object, Object>> queryProductsByclassaes(Map<Object, Object> paraMap);
    @DataSource("joint")
	Map<Object, Object> queryClassaByName(String typegoodHot);
}