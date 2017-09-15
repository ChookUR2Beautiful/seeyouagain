package com.xmniao.xmn.core.timer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.TLSUtil;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：ModifyTlsInfoQuertz   
* 类描述：   更改腾讯云资料信息
* 创建人：yezhiyong   
* 创建时间：2016年11月7日 下午6:54:31   
* @version    
*
 */
@Service
public class ModifyTlsInfoQuertz {
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(DescribeLVBChannelQuertz.class);

	/**
	 * 注入propertiesUtil
	 */
	@Autowired
	private LiveUserDao liveUserDao;
	
	/**
	 * 注入propertiesUtil
	 */
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	/**
	 * 注入fileUrl
	 */
	@Autowired
	private String fileUrl;
	
	/**
	 * 注入stringRedisTemplate
	 */
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * @throws Exception 
	 * @throws IOException 
	 * 
	* @Title: modifyTlsInfo
	* @Description: 更改腾讯云资料信息
	* @return void    返回类型
	* @throws
	 */
	public void modifyTlsInfo() throws Exception {
		//从redis中,获取管理员签名
		String adminSig = stringRedisTemplate.opsForValue().get("adminSig");
		
		String sdkAppid = propertiesUtil.getValue("SdkAppid", "conf_live.properties");
		String identifier = propertiesUtil.getValue("identifier", "conf_live.properties");
		
		if (adminSig == null) {
			//调用tls,获取管理员tls的sig
			adminSig = TLSUtil.getTLSSig(sdkAppid, identifier);
			stringRedisTemplate.opsForValue().set("adminSig", adminSig);
			stringRedisTemplate.expire("adminSig", 180, TimeUnit.DAYS);
		}
		
		List<Map<Object, Object>> liverInfoList = liveUserDao.queryliverInfoList();
		for (Map<Object, Object> map : liverInfoList) {
			try {
				map.put("sdkAppid", sdkAppid);
				map.put("identifier", identifier);
				map.put("account", map.get("phone").toString());
				map.put("nickName", map.get("nname")==null?"":map.get("nname"));
				map.put("avatar", map.get("avatar")==null?"":map.get("avatar"));
				map.put("adminSig", adminSig);
				map.put("selfSignature", map.get("sign")==null?"":map.get("sign"));
				//导入账号信息
				Map<Object, Object> paramMap = new HashMap<>();
				paramMap.put("sdkAppid", map.get("sdkAppid").toString());
				paramMap.put("identifier", map.get("identifier").toString());
				paramMap.put("account", map.get("account").toString());
				paramMap.put("nickName", map.get("nickName").toString());
				paramMap.put("image", fileUrl + map.get("avatar").toString());
				
				paramMap.put("tlsSig", map.get("adminSig").toString());
				
				try {
					//导入账号
					boolean importResult = TLSUtil.accountImport(paramMap);
					if (!importResult) {
						log.info("导入自有账号失败");
					}
				} catch (Exception e) {
					e.printStackTrace();
					log.info("导入自有账号失败");
				}
				
				//上传个性签名
				paramMap.put("selfSignature", map.get("selfSignature").toString());
				paramMap.remove("nickName");
				paramMap.remove("image");
				
				try {
					//上传腾讯云资料
					boolean setResult = TLSUtil.setTlsUserInfo(paramMap);
					
					if (!setResult) {
						log.info("上传腾讯云资料失败");
					}
				} catch (Exception e) {
					e.printStackTrace();
					log.info("上传腾讯云资料失败");
				}
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
			
		}
		
	}
}
