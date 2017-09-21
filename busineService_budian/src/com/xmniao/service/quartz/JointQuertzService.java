package com.xmniao.service.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.xmniao.common.NullU;
import com.xmniao.dao.discount.DiscountServiceDao;

public class JointQuertzService {
	 /**
     * 日志记录
     */
    private final Logger log = Logger.getLogger(JointQuertzService.class);
    
    @Autowired
    DiscountServiceDao discountServiceDao;
    /**
     * 商家折扣定时更新
     */
    public void updateSellerAgio(){
    	//更新数据(商家已经有了折扣信息的数据)
     	Map<String,Object> agioParams = new HashMap<String, Object>();
    	List<Map<String, Object>> agioRecords = discountServiceDao.findAgioRecord(agioParams);
    	for(Map<String, Object> agioRecord : agioRecords){
    		try {
    			if(NullU.isNotNull(agioRecord.get("aid"))){
    			    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    			    agioRecord.put("cdate", dateFormat.format(new Date()));
    				discountServiceDao.updateSellerAgio(agioRecord);		/*更新商户折扣设置表数据*/
    				agioRecord.put("aid", null);
    				discountServiceDao.updateBatchAgioRecord(agioRecord);  				/*将折扣设置记录表的所有时间更新为当前时间	*/
    			}
				
			} catch (Exception e) {
				log.info("SELLER_AGIO:更新了商家折扣报错 id："+agioRecord.get("id")+"\n"+e.getMessage());
			}
    	}
    	agioRecords = discountServiceDao.findAgioRecordNotSeller(agioParams);
    	for(Map<String, Object> agioRecord : agioRecords){
    		try {
    		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                agioRecord.put("cdate", dateFormat.format(new Date()));
				discountServiceDao.insertSellerAgio(agioRecord);					/*跟商户插入商家折扣表*/
				discountServiceDao.updateBatchAgioRecord(agioRecord);  				/*将折扣设置记录表的所有时间更新为当前时间	*/
			} catch (Exception e) {
				log.info("SELLER_AGIO:更新了商家折扣报错 id："+agioRecord.get("id")+"\n"+e.getMessage());
			}
    	}
    	log.info("SELLER_AGIO:更新了商家折扣");
    	//System.out.println(agioRecords);
    }
}
