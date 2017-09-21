package com.xmniao.service.common;

import java.util.Collection;
import java.util.List;
import java.util.Map;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.WriteResult;
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
	public int delete(String collectionname,Criteria criteria){
		WriteResult writeResult = mongoTemplate.remove(new Query(criteria), collectionname);
		if(writeResult!=null){
			return writeResult.getN();
		}
		return 0;
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
	
	public List<BasicDBObject> sumCount(Aggregation aggregation,String collectionname){
//        TypedAggregation<News> agg = Aggregation.newAggregation(
//                News.class,
//                project("evaluate")
//                ,group("evaluate").count().as("totalNum")
//                ,match(Criteria.where("totalNum").gte(85))
//                ,sort(Sort.Direction.DESC, "totalNum")
//            );

            AggregationResults<BasicDBObject> result = mongoTemplate.aggregate(aggregation, collectionname, BasicDBObject.class);
            return result.getMappedResults();
//            System.out.println(aggregation.toString());
          //执行语句差不多
            //{ "aggregate" : "__collection__" , "pipeline" : [ { "$project" : { "evaluate" : 1}} , { "$group" : { "_id" : "$evaluate" , "totalNum" : { "$sum" : 1}}} , { "$match" : { "totalNum" : { "$gte" : 85}}} , { "$sort" : { "totalNum" : -1}}]}
//            System.out.println(result.getMappedResults());
            //查询结果简洁明了
            //[{ "_id" : 0 , "totalNum" : 10047}, { "_id" : 1 , "totalNum" : 9955}]
            
            //使用此方法，如果封装好了某一个类，类里面的属性和结果集的属性一一对应，那么，Spring是可以直接把结果集给封装进去的
            //就是AggregationResults<BasicDBObject> result = mongoTemplate.aggregate(agg, BasicDBObject);中的BasicDBObject改为自己封装的类
            //但是感觉这样做有点不灵活，其实吧，应该是自己现在火候还不到，还看不到他的灵活性，好处在哪里；等火候旺了再说呗
            //所以，就用这个万能的BasicDBObject类来封装返回结果
//            List<BasicDBObject> resultList = result.getMappedResults();
//            NewsNumDTO dto = new NewsNumDTO();
//            for(BasicDBObject dbo : resultList){
//                int eval = dbo.getInt("_id");
//                long num = dbo.getLong("totalNum");
//                if(eval == 1){
//                    dto.setPositiveNum(num);
//                }else {
//                    dto.setNegativeNum(num);
//                }
//            }
//            System.out.println(dto.getPositiveNum());
	}
	
}
