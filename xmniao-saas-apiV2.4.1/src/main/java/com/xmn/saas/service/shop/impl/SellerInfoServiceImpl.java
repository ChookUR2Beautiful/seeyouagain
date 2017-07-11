package com.xmn.saas.service.shop.impl;

import com.xmn.saas.base.GlobalConfig;
import com.xmn.saas.base.ThriftBuilder;
import com.xmn.saas.dao.common.AreaDao;
import com.xmn.saas.dao.common.SellerAccountDao;
import com.xmn.saas.dao.shop.LiveClassifyDao;
import com.xmn.saas.dao.shop.LiveClassifyTagDao;
import com.xmn.saas.dao.shop.SellerInfoDao;
import com.xmn.saas.entity.common.Area;
import com.xmn.saas.entity.common.SellerAccount;
import com.xmn.saas.entity.shop.*;
import com.xmn.saas.service.base.SynthesizeService;
import com.xmn.saas.service.shop.SellerInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
*      
* 类名称：SellerInfoServiceImpl   
* 类描述：   商家基本资料服务类
* 创建人：xiaoxiong   
* 创建时间：2016年9月24日 下午5:10:55   
* 修改人：xiaoxiong   
* 修改时间：2016年9月24日 下午5:10:55   
* 修改备注：   
* @version    
*
 */
@Service
public class SellerInfoServiceImpl implements SellerInfoService{

	// 初始化日志类
    private final Logger logger = LoggerFactory.getLogger(SellerInfoServiceImpl.class);

	/**
	 * 注入店铺基本资料dao
	 */
	@Autowired
	private SellerInfoDao sellerInfoDao;

	@Autowired
	private GlobalConfig config;

	@Autowired
	private SellerAccountDao sellerAccountDao;

	@Autowired
	private AreaDao areaDao;

	@Autowired
	private LiveClassifyDao liveClassifyDao;
	@Autowired
	private LiveClassifyTagDao liveClassifyTagDao;

	/**
	 * 根据sellerid查询店铺基本资料信息
	 */
	@Override
	public SellerInfo querySellerBySellerid(Integer sellerid) {
		try {
			//查询店铺基本信息
			SellerInfo sellerInfo = sellerInfoDao.querySellerBySellerId(sellerid);

//			//查询店铺详细信息
//			SellerDetailed sellerDetailed=sellerInfoDao.querySellerDetailedBySellerid(sellerid);
//
//			//查询店铺坐标信息
//			SellerLandMark sellerLandMark=sellerInfoDao.queryLandMaryBySellerid(sellerid);
//
//			sellerInfo.setSellerDetailed(sellerDetailed);

            // 加载商户标签
            List<LiveClassifyTag> tags = new ArrayList<>();
            try {
                if (sellerInfo.getTagIds() != null) {
                    tags.addAll(liveClassifyTagDao.selectByIds(sellerInfo.getTagIds().split(",")));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            sellerInfo.setTags(tags);
		    return sellerInfo;
        } catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 查询店铺详细信息
	 */
	@Override
	public SellerDetailed querySellerDetailedBySellerId(Integer sellerid) {
		try {
			
			return sellerInfoDao.querySellerDetailedBySellerid(sellerid);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 查询商家坐标信息
	 */
	@Override
	public SellerLandMark querySellerLandMarkBySellerid(Integer sellerid) {
		
		try {
			
			return sellerInfoDao.queryLandMarkBySellerid(sellerid);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 查询商家商圈信息
	 */
	@Override
	public Business queryBusinessBySellerid(Integer zoneId) {
		try {
			
			return sellerInfoDao.queryBusinessBySellerid(zoneId);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 修改店铺资料（没用，商家修改资料先提交到申请表不直接修改）
	 */
	@Override
	@Transactional
	public void save(SellerInfo sellerInfo) {
		
		/**
		 * 修改店铺基本资料
		 */
		sellerInfoDao.saveSeller(sellerInfo);
		
		/**
		 *  修改店铺详细资料
		 */
		sellerInfoDao.saveSellerDetailed(sellerInfo.getDetailed());
		
		/**
		 * 修改店铺坐标信息
		 */
		sellerInfoDao.saveSellerLandMark(sellerInfo.getLandMark());
		
	}
	
	/**
	 * 添加商家修改资料申请
	 */
	@Override
	@Transactional
	public void insertSellerApply(SellerApply sellerApply) {
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		/**
		 * 0基本资料修改 1图片修改
		 */
		sellerApply.setSource(1);//设置申请记录修改类型
		
		
		sellerApply.setSdate(sdf.format(new Date()));//设置申请时间
		
		/**
		 * 根据省市区名称获取省市区编号
		 */
		getAreaNo(sellerApply);
		
		/**
		 * 添加申请记录
		 */
		sellerInfoDao.insertSellerApply(sellerApply);
		
		/**
		 * 修改基础资料
		 */
		SellerInfo sellerInfo=new SellerInfo();
		sellerInfo.setTypeName(sellerApply.getTypeName());//经营类型
		sellerInfo.setOpenDate(sellerApply.getOpenDate());//营业时间
		sellerInfo.setSellerid(sellerApply.getSellerid());
		sellerInfo.setUdate(sdf.format(new Date()));//修改时间
		sellerInfo.setCategory(sellerApply.getCategory());
		sellerInfo.setGenre(sellerApply.getGenre());
		sellerInfo.setTradename(sellerApply.getTradename());
		sellerInfo.setZoneId(sellerApply.getZoneId());
		sellerInfo.setTagIds(sellerApply.getTagIds());
		sellerInfoDao.saveSeller(sellerInfo);
		
		/**
		 * 修改详细资料
		 */
		SellerDetailed detailed=new SellerDetailed();
		detailed.setIsWifi(sellerApply.getIsWifi());//是否有wifi
		detailed.setIsParking(sellerApply.getIsParking());//是否有停车场
		detailed.setConsume(sellerApply.getConsume());//人居消费
		detailed.setSellerid(sellerApply.getSellerid());
		
		if(detailed.getIsWifi()!=null||detailed.getIsParking()!=null||detailed.getConsume()!=null){
			sellerInfoDao.saveSellerDetailed(detailed);
		}
		
	}
	
	/**
	 * 查询修改申请待审核数量
	 * type:类型 基本资料和图片资料
	 */
	@Override
	public int querySellerApplyCount(int type,Integer sellerid) {
		
		return sellerInfoDao.querySellerApplyCount(type,sellerid);
	}

	/**
	 * 查询审核表基础数据
	 */
	@Override
	public SellerApply querySellerApplyBySellerid(int type, Integer sellerid) {
		
		return sellerInfoDao.querySellerApply(type,sellerid);
	}

	/**
	 * 查询商家图片列表
	 */
	@Override
	public List<SellerPic> querySellerPicBySellerId(Integer sellerid,Integer type) {
		try {
			
			return sellerInfoDao.querySellerPicBySellerId(sellerid,config.getImageHost(),type);
			    
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 根据申请记录ID查询审核图片表中的图片信息
	 */
	@Override
	public List<SellerPic> querySellerPicApplyByid(Integer id) {
		try {
			
		return sellerInfoDao.querySellerPicApplyByid(id,config.getImageHost());
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询审核图片信息失败ID:"+id);
		}
		return null;
	}
	
	/**
	 * 添加商家资料审核（图片审核）
	 */
	@Override
	public void insertSellerApply(List<SellerPic> list,Integer sellerid) {
		
		if(list.size()==0){
			return;
		}
		
		SellerApply sellerApply=new SellerApply();
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		/**
		 * 01基本资料修改 2图片修改
		 */
		sellerApply.setSource(2);//设置申请记录修改类型
		
		sellerApply.setSellerid(sellerid);
		
		
		sellerApply.setSdate(sdf.format(new Date()));//设置申请时间
		
		sellerInfoDao.insertSellerApply(sellerApply);
		
		boolean logoFlag=true;
		boolean coverFlag=true;
		boolean picFlag=true;
		
		for(SellerPic sellerPic:list){
			sellerPic.setId(sellerApply.getId());//记录ID
			sellerPic.setUdate(sdf.format(new Date()));
			sellerPic.setSellerid(sellerApply.getSellerid());
			sellerPic.setUrl(sellerPic.getUrl().replace(config.getImageHost(), ""));
			sellerInfoDao.inserSellerPicApply(sellerPic);
			
			if(sellerPic.getType()==0){
				logoFlag=false;
			}
			if(sellerPic.getType()==1){
				picFlag=false;	
			}
			if(sellerPic.getType()==2){
				coverFlag=false;
			}
		}
		//查询修改前的logo
		if(logoFlag){
			List<SellerPic> logoSellerPic=sellerInfoDao.querySellerPicBySellerId(sellerid,"",1);
			if(logoSellerPic!=null&&logoSellerPic.size()>0){
				SellerPic sellerPic=logoSellerPic.get(0);
				sellerPic.setType(0);
				sellerPic.setId(sellerApply.getId());//记录ID
				sellerPic.setUdate(sdf.format(new Date()));
				sellerPic.setSellerid(sellerApply.getSellerid());
				sellerInfoDao.inserSellerPicApply(sellerPic);
			}
		}
		//查询以前的环境图
		if(picFlag){
			List<SellerPic> picSellerPic=sellerInfoDao.querySellerPicBySellerId(sellerid,"",0);
			if(picSellerPic!=null&&picSellerPic.size()>0){
				for(SellerPic sellerPic:picSellerPic){
					sellerPic.setType(1);
					sellerPic.setId(sellerApply.getId());//记录ID
					sellerPic.setUdate(sdf.format(new Date()));
					sellerPic.setSellerid(sellerApply.getSellerid());
					sellerInfoDao.inserSellerPicApply(sellerPic);
				}
			}
		}
		//查询修改前的封面图
		if(coverFlag){
			List<SellerPic> coverPic=sellerInfoDao.querySellerPicBySellerId(sellerid,"",2);
			if(coverPic!=null&&coverPic.size()>0){
				SellerPic sellerPic=coverPic.get(0);
				sellerPic.setType(2);
				sellerPic.setId(sellerApply.getId());//记录ID
				sellerPic.setUdate(sdf.format(new Date()));
				sellerPic.setSellerid(sellerApply.getSellerid());
				sellerInfoDao.inserSellerPicApply(sellerPic);
			}
			
		}
		
	}
	
	/**
	 *  获取离商家最近的一个商圈
	 */
	@Override
	public Business queryBusinessBylongAndlat(Double longitude, Double latitude) {
		
		return sellerInfoDao.queryBusinessBylongAndlat(longitude,latitude);
	}
	
	/**
	 * 获取商家账户信息
	 */
	@Override
	public Account queryAccount(int sellerid, int aid) {
		
		Account account=new Account();
		
		SellerInfo sellerInfo=querySellerBySellerid(sellerid);
		if(sellerInfo==null){
			return null;
		}
		
		account.setAgio(sellerInfo.getAgio());//折扣
		account.setSellerid(sellerInfo.getSellerid());//商家编号
		account.setName(sellerInfo.getFullname());	//法人姓名
		account.setPhone(sellerInfo.getPhone());//负责人联系号码
		account.setSdate(sellerInfo.getSigndate());//注册时间
		account.setLimit(config.getLimit());		//提现设置
		account.setMoreLimit(config.getMoreLimit());//超出提现设置手续费
		account.setUnderLimit(config.getUnderLimit());//低于提现手续费设置
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SellerAccount sellerAccount=sellerAccountDao.selectByPrimaryKey(aid);
		account.setEdate(sdf.format(sellerAccount.getEdate()));
		
		getMentionAccount(account);
		
		SellerDetailed detaile=querySellerDetailedBySellerId(sellerid);
		if(detaile!=null){
			account.setOperating(detaile.getOperating());
		}
		
		return account;
	}

	private void getMentionAccount(Account account) {
		try {
			/**
			 * 业务服务地址
			 */
			String host=config.getThriftPayHost();
			/**
			 * 业务服务端口
			 */
			String port=config.getThriftPayPort();
			
			SynthesizeService.Client client =ThriftBuilder.build(host, Integer.parseInt(port), "SynthesizeService", SynthesizeService.Client.class);
			
			ThriftBuilder.open();
			
			List<Map<String,String>> accountList=client.getMentionAccount(account.getSellerid()+"", 2);
			
			if(accountList.size()>0){
				Map<String,String> map=accountList.get(0);
				account.setStatus(1);
				if(map.get("ispublic").equals("0")){
					account.setCarded(map.get("identity"));	
				}
				return ;
			}
			account.setStatus(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ThriftBuilder.close();
		}
	}
	
	/**
	 * 删除图片
	 */
	@Override
	@Transactional
	public int deleteSellerPic(Integer sellerid,String url) {
		Map<String,Object> map=new HashMap<>();
		map.put("sellerid", sellerid);
		map.put("url",url.replace(config.getImageHost(),""));
		
		return sellerInfoDao.deleteSellerPic(map);
	}

	@Override
	public int deleteSellerPicApply(Integer sellerid, String url) {
		
		return sellerInfoDao.deleteSellerPicApply(sellerid,url.replace(config.getImageHost(),""));
	}

    /**
     * 查询商户标签
     * @return
     */
    @Override
    public List<LiveClassify> querySellerTag() {
        List<LiveClassify> tagClasses = liveClassifyDao.selectSellerTagClass();
        for (LiveClassify tagClass : tagClasses) {
            tagClass.setTags(liveClassifyTagDao.selectByClassId(tagClass.getId()));
        }
        return tagClasses;
    }

    /**
     * 查询标签
     * @return
     */
    @Override
    public List<LiveClassifyTag> queryTags() {
        return null;
    }

    private void getAreaNo(SellerApply sellerApply){
		Map<String,Object> paramMap=new HashMap<>();
		
		int province=0;
		if(sellerApply.getProvince()!=null){
			paramMap.put("name",sellerApply.getProvince().replace("省",""));
			paramMap.put("pid",0);
			List<Area> pList=areaDao.selectAreaByidOrname(paramMap);
			if(pList!=null&&pList.size()>0){
				province=pList.get(0).getId();
			}
		}
		
		int city=0;
		if(sellerApply.getCity()!=null){
			paramMap.put("name",sellerApply.getCity().replace("市",""));
			paramMap.put("pid",province);
			List<Area> cList=areaDao.selectAreaByidOrname(paramMap);
			if(cList!=null&&cList.size()>0){
				city=cList.get(0).getId();
			}
		}
		paramMap.put("pid",city);
		paramMap.put("name",sellerApply.getArea());
		int area=0;
		if(sellerApply.getArea()!=null){
			List<Area> aList=areaDao.selectAreaByidOrname(paramMap);
			if(aList!=null&&aList.size()>0){
				area=aList.get(0).getId();
			}
		}
		
		sellerApply.setProvinceNo(province);
		sellerApply.setCityNo(city);
		sellerApply.setAreaNo(area);
	}
}
