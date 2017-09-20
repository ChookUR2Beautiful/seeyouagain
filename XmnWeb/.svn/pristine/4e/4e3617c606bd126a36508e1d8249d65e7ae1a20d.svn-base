package com.xmniao.xmn.core.user_terminal.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.common.dao.AreaDao;
import com.xmniao.xmn.core.common.entity.TArea;
import com.xmniao.xmn.core.marketingmanagement.dao.HotWordsDao;
import com.xmniao.xmn.core.marketingmanagement.entity.HotWords;
import com.xmniao.xmn.core.util.StringUtils;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;
/**
 * 搜索标签管理 Service
 * @author ch
 *
 */
@Service
public class SearchTagsService extends BaseService<HotWords>{

	@Autowired
	private HotWordsDao hotWordsDao;
	
	@Autowired
	private AreaDao areaDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return hotWordsDao;
	}
	
	
	
	
	/**
	 * 添加搜索标签
	 * @param hotWords 关键字实例
	 * @param userName 添加用户名称
	 * @return Resultable
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Resultable add(HotWords hotWords,String userId){
		Resultable resultable = null;
		try{
			//城市与搜索标签必须填写
			if (checkhotWordAndAreaId(hotWords)) {
				// 检查是否在同一城市有相同搜索标签
				if (checkSearchTagOfArea(hotWords) > 0L) {
					resultable = new Resultable(Boolean.FALSE, "搜索标签在该城市已存在！");
				} else if (checkSearchTagOfArea(hotWords) >= 6L) {
					//标签数等于6
					resultable = new Resultable(Boolean.FALSE, "搜索标签在该城市数量已满，请先删除！");
				} else {

					// 设置默认属性
					Date date = new Date();
					hotWords.setCreatorId(userId);
					hotWords.setCreatedTime(date);
					hotWords.setUpdateUserId(userId);
					hotWords.setUpdateTime(date);
					hotWords.setHotStatus("1");
					hotWordsDao.add(hotWords);
					resultable = new Resultable(Boolean.TRUE, "添加成功");
				}
			}else{
				resultable = new Resultable(Boolean.FALSE, "城市与搜索标签必须填写！");
			}
		}catch(Exception e){
			log.error("添加搜索标签异常", e);
			resultable = new Resultable(Boolean.FALSE, "添加异常");
		}finally{
			fireLoginEvent(new String[]{"搜索标签新增",hotWords.getHotWord(),"新增"}, resultable.getSuccess() ? 1 : 0);
		}	
		return resultable;
	}
	
	/**
	 * 检查城市搜索标签 数量
	 * @param hotWords 关键字实例
	 * @return
	 */
	public Long checkSearchTagOfArea(HotWords hotWords){
		return hotWordsDao.isHotWordsOfArea(hotWords);
	}
	
	/**
	 * 删除搜索标签
	 * @param hotWords 标签信息
	 * @param userName 删除用户名称
	 * @return Resultable
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Resultable deleteSearchTag(HotWords hotWords,String userId){
		Resultable resultable = new Resultable();
		try{
			//城市与搜索标签必须填写
			if(StringUtils.hasLength(hotWords.getAreaId())&&hotWords.getHid()!=null){
				//检查是否在同一城市有相同搜索标签
				if(checkSearchTagOfArea(hotWords)==0L){
					resultable = new Resultable(Boolean.FALSE, "该城市不存在所删除的搜索标签！请先添加！");
				}else{
					//设置默认属性
					hotWords.setUpdateUserId(userId);
					hotWords.setUpdateTime(new Date());
					hotWords.setHotStatus("0");
					hotWordsDao.update(hotWords);
					resultable = new Resultable(Boolean.TRUE, "删除成功");
				}
			}else{
				resultable = new Resultable(Boolean.FALSE, "城市编号或者搜索标签编号不能为空！");
			}
		}catch(Exception e){
			log.error("删除搜索标签异常", e);
			resultable = new Resultable(Boolean.FALSE, "删除搜索标签异常！");
		}finally{
			fireLoginEvent(new String[]{"搜索标签信息",String.format("编号: %s 区域编号: %s", hotWords.getHid(),hotWords.getAreaId()),"删除搜索标签","删除"}, resultable.getSuccess() ? 1 : 0);
		}	
		return resultable;
	}
	
	/**
	 * 检查搜索标签 的  areaId hotWord属性是否为空
	 * @param hotWords
	 * @return
	 */
	public boolean checkhotWordAndAreaId(HotWords hotWords){
		return (StringUtils.hasLength(hotWords.getAreaId())&&StringUtils.hasLength(hotWords.getHotWord()));
	}
	
	/**
	 * 判断城市是否有商圈
	 * @param areaId
	 * @return
	 */
	public boolean isCityInBusiness(String areaId){
		return (hotWordsDao.isCityInBusiness(areaId)>0);
	}
	
	/**
	 * 根据城市编号获取搜索标签
	 * @param areaId
	 * @return
	 */
	public List<HotWords> getSearchTags(String areaId){
		try{

			if(isCityInBusiness(areaId)){
				return hotWordsDao.getSearchTags(areaId);
			}
		}catch(Exception e){
			log.error("查询搜索标签异常 城市编号: "+areaId, e);
		}
		return null;
	}
	
	/**
	 * 根据城市编号与热词状态 查询城市热词 或者 城市热词历史记录
	 * @param hotWords
	 * @return
	 */
	public Pageable<HotWords> getHotSearchTags(HotWords hotWords){
		Pageable<HotWords> pageable = new Pageable<>(hotWords);
		try{
			if(StringUtils.hasLength(hotWords.getAreaId())){
				pageable.setContent(hotWordsDao.getHotSearchTags(hotWords));
				pageable.setTotal(hotWordsDao.getHotSearchTagsCount(hotWords));
			}else{
				pageable.setTotal(0L);
			}
		}catch(Exception e){
			log.error("查询城市关键词异常", e);
			pageable.setTotal(0L);
		}
		return pageable;
	}
	
	/**
	 * 设置展示关键字
	 * @param hotWords
	 * @param userId
	 * @return
	 */
	public Resultable showSearchTag(HotWords hotWords,String userId){
		Resultable resultable = new Resultable();
		try{
			//城市与搜索标签必须填写
			if(StringUtils.hasLength(hotWords.getAreaId())&&hotWords.getHid()!=null){
				//检查是否在同一城市有相同搜索标签
				if(checkSearchTagOfArea(hotWords)>=6L){
					resultable = new Resultable(Boolean.FALSE, "该城市展示关键字已满！");
				}else{
					//设置默认属性
					hotWords.setUpdateUserId(userId);
					hotWords.setUpdateTime(new Date());
					hotWords.setHotStatus("1");
					hotWordsDao.update(hotWords);
					resultable = new Resultable(Boolean.TRUE, "设置展示成功");
				}
			}else{
				resultable = new Resultable(Boolean.FALSE, "城市编号或者搜索标签编号不能为空！");
			}
		}catch(Exception e){
			log.error("设置展示搜索标签异常", e);
			resultable = new Resultable(Boolean.FALSE, "设置展示索标签异常！");
		}finally{
			fireLoginEvent(new String[]{"设置展示搜索标签信息",String.format("编号: %s 区域编号: %s", hotWords.getHid(),hotWords.getAreaId()),"设置展示搜索标签","展示"}, resultable.getSuccess() ? 1 : 0);
		}	
		return resultable;
	}
	
	 /**
	 * 查询开通商圈的城市
	 * @return
	 */
	public List<TArea> getOpenBusinessCity(){
		return areaDao.getOpenBusinessCity();
	}

}
