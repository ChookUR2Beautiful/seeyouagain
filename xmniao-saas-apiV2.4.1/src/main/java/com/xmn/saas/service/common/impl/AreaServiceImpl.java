package com.xmn.saas.service.common.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.xmn.saas.dao.common.AreaDao;
import com.xmn.saas.entity.common.Area;
import com.xmn.saas.service.base.RedisService;
import com.xmn.saas.service.common.AreaService;


@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaDao areaDao;

    @Autowired
    private RedisService redisService;


    @Override
	public Object list() {
	    String areaListStr = redisService.getString("saas:api:area");
	    String areaVersion = redisService.getString("saas:api:area.version");//redis中area的版本信息
	    String areaMark ="";//配置文件中area的版本信息
	   // 读取配置文件获取mark(地区)
        Resource resource = new ClassPathResource("/properties/conf-version.properties");
        Properties properties = new Properties();
        try(InputStream in = resource.getInputStream()){
            properties.load(in);
            // 从配置文件中获取标记
            areaMark = properties.getProperty("sass.mark.area");
            in.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
	    
	    if(StringUtils.isBlank(areaListStr)||StringUtils.isBlank(areaVersion) || !areaVersion.equals(areaMark)){
	        // 查询一级分类
	        List<Area> resultList=areaDao.findAreaByPid(0);
	        List<Area>  result=  findSubSet(resultList);
	        redisService.setString("saas:api:area", JSON.toJSONString(result));
	        redisService.setString("saas:api:area.version", areaMark);
	        
	        return result;
	    }
	    List<Area> areaList =JSON.parseArray(areaListStr, Area.class);
	    return areaList;
		
		
		
		
	}

    /**
     * 
     * @Description: 查询子集
     * @author xiaoxiong
     * @date 2016年10月17日
     */
    public List<Area> findSubSet(List<Area> list) {

        List<Area> resulst = new ArrayList<>();

        if (list != null && list.size() > 0) {
            for (Area province : list) {
                // 查询市
                List<Area> cityList = areaDao.findAreaByPid(province.getId());
                if (cityList != null && cityList.size() > 0) {
                    List<Area> cityResult = new ArrayList<>();
                    for (Area city : cityList) {
                        // 查询区
                        List<Area> countyList = areaDao.findAreaByPid(city.getId());
                        if (countyList != null && countyList.size() > 0) {
                            city.setSubset(countyList);
                        }
                        city.setName(city.getName() + "市");
                        cityResult.add(city);
                    }
                    province.setSubset(cityResult);
                }
                province.setName(province.getName() + "省");
                resulst.add(province);
            }
        }

        return resulst;
    }

    @Override
    public String selectAreaById(int id) {
        return areaDao.selectAreaById(id);
    }

}
