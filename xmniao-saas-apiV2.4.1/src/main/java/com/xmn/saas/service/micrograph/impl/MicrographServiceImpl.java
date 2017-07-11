package com.xmn.saas.service.micrograph.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmn.saas.constants.SaasConsts;
import com.xmn.saas.dao.celebrity.TagDao;
import com.xmn.saas.dao.micrograph.MicrographModuleDao;
import com.xmn.saas.dao.micrograph.MicrographModuleShareDao;
import com.xmn.saas.dao.micrograph.MicrographPageDao;
import com.xmn.saas.dao.micrograph.MicrographPageShareDao;
import com.xmn.saas.dao.micrograph.MicrographSearchDao;
import com.xmn.saas.dao.micrograph.MicrographTemplateDao;
import com.xmn.saas.dao.micrograph.MicrographTemplateShareDao;
import com.xmn.saas.entity.celebrity.Tag;
import com.xmn.saas.entity.micrograph.MicrographModule;
import com.xmn.saas.entity.micrograph.MicrographModuleShare;
import com.xmn.saas.entity.micrograph.MicrographPage;
import com.xmn.saas.entity.micrograph.MicrographPageShare;
import com.xmn.saas.entity.micrograph.MicrographSearch;
import com.xmn.saas.entity.micrograph.MicrographTemplate;
import com.xmn.saas.entity.micrograph.MicrographTemplateShare;
import com.xmn.saas.entity.shop.SellerInfo;
import com.xmn.saas.service.micrograph.MicrographService;
import com.xmn.saas.service.shop.SellerInfoService;

@Service
public class MicrographServiceImpl implements MicrographService {
	
	 private final Logger logger = LoggerFactory.getLogger(MicrographServiceImpl.class);
	
	@Autowired
	private MicrographTemplateDao micrographTemplateDao;
	
	@Autowired
	private TagDao tagDao;
	
	@Autowired
	private MicrographSearchDao micrographSearchDao;
	
	@Autowired
	private MicrographPageDao  micrographPageDao;
	
	@Autowired
	private MicrographModuleDao micrographModuleDao;
	
	@Autowired
	private MicrographPageShareDao micrographPageShareDao;
	
	@Autowired
	private MicrographModuleShareDao micrographModuleShareDao;
	
	@Autowired
	private MicrographTemplateShareDao micrographTemplateShareDao;
	
	@Autowired
	private SellerInfoService infoService;
	
	@Override
	public List<MicrographTemplate> list(MicrographSearch searchModel){
		List<MicrographTemplate> list = null;
		/*if (tag==null&&searchName != null) {
			//有搜索条件
		} else {*/
			String searchName = searchModel.getTitle();
			Integer pageIndex = searchModel.getPageIndex();
			Integer pageSize = searchModel.getPageSize();
			Integer serialType = searchModel.getSerialType();
			Integer tag = searchModel.getTag();
			if(StringUtils.isBlank(searchName)){
				searchName=null;
			}else{
				try {
					MicrographSearch old=micrographSearchDao.selectTitle(searchName,searchModel.getSellerid());
					if(old!=null){
						old.setCreateTime(new Date());
						micrographSearchDao.updateCreateTime(old);
					}else{
						searchModel.setCreateTime(new Date());
						searchModel.setStatus(0);
						micrographSearchDao.insert(searchModel);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (SaasConsts.MICROGRAPH_SERIAL_TYPE_DOWN == serialType) {
				list = micrographTemplateDao.selecDownSerial(pageSize, pageIndex,tag,searchName);
			} else if (SaasConsts.MICROGRAPH_SERIAL_TYPE_UP == serialType) {
				list = micrographTemplateDao.selectUpSerial(pageSize, pageIndex,tag,searchName);
			} else {
				list = micrographTemplateDao.selectBySerial(pageSize, pageIndex,tag,searchName);
			}
		/*}*/
		return list;
	}

	@Override
	public List<Tag> getTagsBySerial() {
		return tagDao.selectTagsBySerial();
	}

	@Override
	public List<Map<String, String>> searchLike(String name) {
		return micrographTemplateDao.searchLike(name);
	}

	@Override
	public List<MicrographSearch> getMicrographSearch(Integer sellerId) {
		return micrographSearchDao.selectBySellerid(sellerId);
	}

	@Override
	public void clearSearch(Integer sellerId) {
		micrographSearchDao.deleteSearch(sellerId);
	}

	@Override
	public List<MicrographPage> pageList(Integer id) {
		 List<MicrographPage> micrographPages = micrographPageDao.selectListByPrimaryKey(id);
		 for (MicrographPage micrographPage : micrographPages) {
			List<MicrographModule> micrographModule = micrographModuleDao.selectByPageId(micrographPage.getId());
			micrographPage.setMicrographModule(micrographModule);
		 }
		 return micrographPages;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Integer saveShare(List<MicrographModuleShare> list, Integer sellerId) {
		try {
			MicrographModuleShare micrographModuleShare2 = list.get(0);
			MicrographTemplate template = micrographTemplateDao.selectByPageId(micrographModuleShare2.getPageId());
			//使用次数加一
			micrographTemplateDao.updateSoldTimes(template.getId());
			List<MicrographPage> pages = micrographPageDao.selectListByPrimaryKey(template.getId());
			MicrographTemplateShare micrographTemplateShare = new  MicrographTemplateShare();
			micrographTemplateShare.setCreateTime(new Date());
			micrographTemplateShare.setSellerId(sellerId);
			micrographTemplateShare.setStatus(0);
			micrographTemplateShare.setTemplateId(template.getId());
			micrographTemplateShareDao.insert(micrographTemplateShare);
			for (MicrographPage micrographPage : pages) {
				List<MicrographModule> micrographModules=micrographModuleDao.selectByPageId(micrographPage.getId());
				MicrographPageShare pageShare = new MicrographPageShare();
				pageShare.setBackgroundImage(micrographPage.getBackgroundImage());
				pageShare.setCreateTime(new Date());
				pageShare.setPage(micrographPage.getPage());
				pageShare.setSellerid(sellerId);
				pageShare.setStatus(0);
				pageShare.setTemplateShareId(micrographTemplateShare.getId());
				micrographPageShareDao.insert(pageShare);
				two:for (int i = 0; i < list.size(); i++) {
					MicrographModuleShare micrographModuleShare = list.get(i);
					if(micrographModuleShare.getPageId()!=micrographPage.getId()){
						break two;
					}
					MicrographModule micrographModule = micrographModules.get(i);
					list.remove(i);
					micrographModules.remove(i);
					i--;
					micrographModuleShare.setFontSize(micrographModule.getFontSize());
					micrographModuleShare.setHeight(micrographModule.getHeight());
					micrographModuleShare.setWidth(micrographModule.getWidth());
					micrographModuleShare.setTop(micrographModule.getTop());
					micrographModuleShare.setLeftStyle(micrographModule.getLeftStyle());
					micrographModuleShare.setCreateTime(new Date());
					micrographModuleShare.setPageId(pageShare.getId());
					micrographModuleShare.setStatus(1);
					micrographModuleShareDao.insert(micrographModuleShare);
				}
			}
			return micrographTemplateShare.getId();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("调用分享接口出错");
		}
	}

	@Override
	public SellerInfo getSellerMsg(Integer id) {
		MicrographTemplateShare templateShare = micrographTemplateShareDao.selectByPrimaryKey(id);
		return infoService.querySellerBySellerid(templateShare.getSellerId());
	}

}
