package com.xmniao.xmn.core.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
/**
 * mongodb 处理service
 * @author Administrator
 *
 */
@Service
public class MongoBaseService {

	/**
	 * mongodb的操作对象，处理所有对mongodb的增删改查操作 
	 */
	@Autowired
	private MongoTemplate mongoTemplate;
	
	/**
	 * mongodb  集合 名
	 */
	private String collectionname;
	
	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public String getCollectionname() {
		return collectionname;
	}

	public void setCollectionname(String collectionname) {
		this.collectionname = collectionname;
	}
	

	/**
	 * 添加数据 
	 * @param t
	 */
	public <T> void InsertOrUpdate(T t){
		mongoTemplate.save(t,collectionname);
	}
	
	
	/**
	 * 移除元素
	 * @param fieldName 元素关键字
	 * @param fieldValue 关键字值
	 */
	public void delete(String fieldName ,String fieldValue){
		mongoTemplate.remove(buildQueryConditions(fieldName,fieldValue), collectionname);
	}
	
	/**
	 * 查询单个对象
	 * @param fieldName
	 * @param fieldValue
	 * @param entityClass
	 * @return
	 */
	public <T> T findOne(String fieldName ,String fieldValue,Class<T> entityClass){
		return mongoTemplate.findOne(buildQueryConditions(fieldName,fieldValue), entityClass,collectionname);
	}

	
	
	/**
	 * 
	* @Title: buildQueryLikeConditions
	* @Description: 模糊查询
	* @return void
	 */
	public <T> List<T> findLikeKeyword(String fieldName,String keyword,Class<T> entityClass){
		Criteria criteria 	=	new Criteria(fieldName).regex(".*"+keyword+".*");
		return 	mongoTemplate.find(new Query(criteria), entityClass,collectionname);
//		return mongoTemplate.findAll(entityClass, collectionname);
	}
	
	/**
	 * 
	* @Title: buildQueryLikeConditions
	* @Description: 查询全部
	* @return void
	 */
	public <T> List<T> findAll(Class<T> entityClass){
//		Criteria criteria 	=	new Criteria(fieldName).regex(".*"+keyword+".*");
		return mongoTemplate.findAll(entityClass, collectionname);
		
	}
	
	/**
	 * 根据条件查询
	 * @author xiaoxiong
	 * @date 2016年12月8日 09:37:27
	 */
	public <T> List<T> findQuery(Query query,Class<T> entityClass,String tableName){
		
		return mongoTemplate.find(query, entityClass,tableName);
		
	} 
	
	/**
	 * 构建查询条件
	 * @param fieldName 查询字段名
	 * @param fieldValue 查询值
	 * @return
	 */
	protected  Query buildQueryConditions(String fieldName ,String fieldValue){
		Criteria criteria = Criteria.where(fieldName);
		return (new Query(criteria));
	}
	
	public void test(){
		for (String str : mongoTemplate.getCollectionNames()) {
			System.out.println(str);
		}
		
	}
	
}
