package com.xmniao.xmn.core.base;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mongodb.WriteResult;

/**
 * mongodb 处理service
 * @author Administrator
 *
 */
public class MongoBaseService {

	/**
	 * mongodb的操作对象，处理所有对mongodb的增删改查操作 
	 */
	private MongoTemplate mongoTemplate;
	
	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	/**
	 * 添加数据 
	 * @param t
	 */
	public <T> void insertOrUpdate(String collectionname,T t){
		mongoTemplate.save(t,collectionname);
	}
	
	/**
	 * 添加数据 
	 * @param t
	 */
	public <T> void insertAll(String collectionname,Collection<T> t){
		mongoTemplate.insert(t,collectionname);
	}
	
	/**
	 * 移除元素
	 */
	public void delete(String collectionname,Query query){
		mongoTemplate.remove(query, collectionname);
	}
	
	/**
	 * 查询单个对象
	 * @param <T>
	 * @param fieldName
	 * @param fieldValue
	 * @param entityClass
	 * @return
	 */
	public <T> List<T> findAll(String collectionname,Criteria criteria,Class<T> entityClass){
		return mongoTemplate.find(new Query(criteria), entityClass,collectionname);
	}
	
	/**
	 * 查询单个对象
	 * @param fieldName
	 * @param fieldValue
	 * @param entityClass
	 * @return
	 */
	public <T> T findOne(String collectionname,Criteria criteria,Class<T> entityClass){
		return mongoTemplate.findOne(new Query(criteria), entityClass,collectionname);
	}
	
	/**
	 * 更新所有对象
	 */
	public int updateAll(String collectionname,Criteria criteria,Map<String,Object> updateMap){
		Query query = new Query(criteria);
		Update update = new Update();
		
		for(String filed:updateMap.keySet()){
			update.set(filed, updateMap.get(filed));
		}		
		WriteResult writeResult = mongoTemplate.updateMulti(query,update,collectionname);  
		return writeResult.getN();
	}
	
	/**
	 * 更新单个对象
	 * @param fieldName
	 * @param fieldValue
	 * @param entityClass
	 * @return
	 */
	public int updateOne(String collectionname,Criteria criteria,Map<String,Object> updateMap){
		Query query = new Query(criteria);
		Update update = new Update();
		
		for(String filed:updateMap.keySet()){
			update.set(filed, updateMap.get(filed));
		}		
		WriteResult writeResult = mongoTemplate.updateFirst(query,update,collectionname);  
		return writeResult.getN();
	}
	
	/**
	 * 构建查询条件
	 * @param fieldName 查询字段名
	 * @param fieldValue 查询值
	 * @return
	 */
	protected  Query buildQueryConditions(String collectionname,String fieldName ,String fieldValue){
		Criteria criteria = Criteria.where(fieldName).is(fieldValue);
		return (new Query(criteria));
	}
	
	
	/**
	 * 更新单个对象
	 * @param fieldName
	 * @param fieldValue
	 * @param entityClass
	 * @return
	 */
	public void updateInc(String collectionname,Criteria criteria,Map<String,Number> incMap){
		Query query = new Query(criteria);
		Update update = new Update();
		
		for(String filed:incMap.keySet()){
			update.inc(filed, incMap.get(filed));
		}		
		WriteResult result =mongoTemplate.updateFirst(query,update,collectionname); 
		System.out.println(result);
	}
	
	/**
	 * 查询单个对象
	 * @param fieldName
	 * @param fieldValue
	 * @param entityClass
	 * @return
	 */
	public long count(String collectionname,Query query){
		return mongoTemplate.count(query,collectionname);
	}

	
}
