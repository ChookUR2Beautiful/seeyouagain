package com.xmniao.xmn.core.user_terminal.service;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.businessman.service.SellerService;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.system_settings.dao.CategoryDao;
import com.xmniao.xmn.core.system_settings.entity.Category;
import com.xmniao.xmn.core.user_terminal.dao.SellerTraderDao;
import com.xmniao.xmn.core.user_terminal.entity.TSellerTrader;
import com.xmniao.xmn.core.util.StringUtils;
/**
 * 用户端商家分类处理
 * @author ch
 *
 */
@Service
public class SellerTraderService extends BaseService<TSellerTrader>{

	
	@Autowired SellerTraderDao sellerTraderDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired SellerService sellerService;
	
	@Override
	protected BaseDao getBaseDao() {
		return null;
	}
	
	
	/**
	 * 获取一级分类
	 * @return
	 */
	public Pageable<Category> getFirstTraderList(Category category){
		Pageable<Category> pageable = new Pageable<>(category);
		try{
			getPageable(pageable,categoryDao.getFirstTraderList(category),categoryDao.getFirstTraderListCount(category));
		}catch(Exception e){
			log.error("获取一级分类异常", e);
			pageable.setTotal(0L);
		}
		return pageable;
	}
	
	/**
	  * 获取二级分类
	  * @param pid 父编号
	  * @return List<Category>
	  */
	public Pageable<Category> getTwoTraderListByPid(Category category){
		Pageable<Category> pageable = new Pageable<>(category);
		try{
			getPageable(pageable,categoryDao.getTwoTraderListByPid(category),categoryDao.getTwoTraderListByPidCount(category));
		}catch(Exception e){
			log.error("获取二级分类异常", e);
			pageable.setTotal(0L);
		}
		return pageable;
	}
	
	
	private <T extends BaseEntity> Pageable<T> getPageable(Pageable<T> pageable,List<T> categories,Long total){
		pageable.setContent(categories);
		pageable.setTotal(total);
		return pageable;
	}
	
	/**
	 * 查询已添加分类商家列表
	 * @param seller
	 * @return
	 */
	public Pageable<TSeller> getTwoTraderAddSellerList(TSeller seller){
		Pageable<TSeller> pageable = new Pageable<>(seller);
		try{
			if(StringUtils.hasLength(seller.getArea())){
				getPageable(pageable,sellerTraderDao.getTwoTraderAddSellerList(seller),sellerTraderDao.getTwoTraderAddSellerListCount(seller));
			}
		}catch (Exception e){
			log.error("查询商家分类已添加分类商家列表异常", e);
			pageable.setTotal(0L);
		}
		return pageable;
	}
	
	/**
	 * 查询未添加分类商家列表
	 * @param seller
	 * @return
	 */
	public Pageable<TSeller> getTwoTraderNoAddSellerList(TSeller seller){
		Pageable<TSeller> pageable = new Pageable<>(seller);
		try{
			if(StringUtils.hasLength(seller.getArea())){
				getPageable(pageable,sellerTraderDao.getTwoTraderNoAddSellerList(seller),sellerTraderDao.getTwoTraderNoAddSellerListCount(seller));
			}
		}catch (Exception e){
			log.error("查询商家分类已添加分类商家列表异常", e);
			pageable.setTotal(0L);
		}
		return pageable;
	}
	
	/**
	 * 添加商家与分类关联关系
	 * @param sellerTrader
	 * @param uid
	 * @return
	 * @throws ApplicationException
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Resultable addTSellerTrader(TSellerTrader sellerTrader,String uid)throws ApplicationException{
		Resultable resultable = null;
		String sellerId = sellerTrader.getSellerid();
		String genre = sellerTrader.getGenre();
		try{
			//检查商家编号与类别编号是否为空
			if(checkInfo(sellerId, genre)){
				resultable = new Resultable(Boolean.FALSE, "商家编号或者二级类别编号不能为空");
			}else{
				Integer sid = Integer.parseInt(sellerId);
				TSeller seller = new TSeller(sid);
				seller.setArea(genre);
				//检查该商家是否添加该区域
				if(sellerTraderDao.getTwoTraderAddSellerListCount(seller)>0L){
					resultable = new Resultable(Boolean.FALSE, "该商家已与该类别关联");
				}else{
					
					Category category =categoryDao.getObject(Long.parseLong(genre));
					if(category==null){
						resultable = new Resultable(Boolean.FALSE, "二级类别不存在");
					}else{
						Integer  categoryPid= category.getPid();
						String pid = categoryPid.toString();
						if(!pid.equals(sellerTrader.getCategory())){
							resultable = new Resultable(Boolean.FALSE, "一级类别与二级类别不存在关联关系");
						}else{
							Category firstCategory = categoryDao.getObject((long)categoryPid);
							if(firstCategory!=null){
								seller.setArea(null);
								Date date = new Date();
								sellerTrader.setCategoryName(firstCategory.getTradename());
								sellerTrader.setGenreName(category.getTradename());
								//添加关联关系
								add(sellerTrader, uid, date);
								//更新商家与类别关联关系字段,并同步到mogondb
								TSeller updateSeller = getSeller((long)sid);
								String genreIds = updateSeller.getGenreIds();
								genreIds = StringUtils.hasLength(genreIds) ? genreIds+","+genre : genre;
								sycnSeller(sid, seller, date,genreIds);	
								resultable = new Resultable(Boolean.TRUE, "添加成功");
							}else{
								resultable = new Resultable(Boolean.FALSE, "请选择二级类别与商家关联");
							}	
						}
					}
				}
			}
			return resultable;
		}catch(ApplicationException e){
			log.error(e.getMessage(), e);
			//抛出异常并回滚事务
			throw  e;
		}catch(Exception e){
			String msg= "添加商家类别关系异常";
			log.error(msg, e);
			//抛出异常并回滚事务
			throw new ApplicationException(msg, e, new Object[]{sellerTrader,uid});
		}finally{
			//记录日志
			fireLoginEvent(new String[]{"商家类别关系新增",String.format("商家编号: %s ,类别编号 : %s ", sellerId,genre),"新增"},resultable!=null && resultable.getSuccess() ?1:0);
			
		}
		
	}

	
	
	/**
	 * 同步商家
	 * @param sid
	 * @param seller
	 * @param date
	 * @param genreIds
	 */
	private void sycnSeller(Integer sid, TSeller seller, Date date,String genreIds) {
		seller.setGenreIds(genreIds);
		seller.setUdate(date);
		sellerService.update(seller);
		sellerService.InsertOrUpdateMongo(sid);
		sellerService.fireLoginEvent(new String[]{"商家类别关系信息",genreIds,"更新商家类别关系字段","更新"});
		
	}

	/**
	 * 添加关系
	 * @param sellerTrader
	 * @param uid
	 * @param date
	 */
	private void add(TSellerTrader sellerTrader, String uid, Date date) {
		sellerTrader.setCreator(uid);
		sellerTrader.setDateCreated(date);
		sellerTrader.setUpdator(uid);
		sellerTrader.setDateUpdated(date);
		sellerTraderDao.add(sellerTrader);
	}
	
	/**
	 * 移除商家与分类关联关系
	 * @param sellerTrader
	 * @param uid
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Resultable removeTSellerTrader(TSellerTrader sellerTrader,String uid){
		Resultable resultable = null;
		String sellerId = sellerTrader.getSellerid();
		String genre = sellerTrader.getGenre();
		try{
			//检查商家编号与类别编号是否为空
			if(checkInfo(sellerId, genre)){
				resultable = new Resultable(Boolean.FALSE, "商家编号或者二级类别编号不能为空");
			}else{
				Integer sid = Integer.parseInt(sellerId);
				TSeller seller = new TSeller(sid);
				seller.setArea(genre);
				//检查该商家是否添加该区域
				if(sellerTraderDao.getTwoTraderAddSellerListCount(seller)==0L){
					resultable = new Resultable(Boolean.FALSE, "找不到相关联的商家");
				}else{
					seller.setArea(null);
					//删除关联关系
					sellerTraderDao.delete(sellerTrader);
					//更新商家与类别关联关系字段,并同步到mogondb
					TSeller updateSeller = getSeller((long)sid);
					String genreIds = updateSeller.getGenreIds();
					if(StringUtils.hasLength(genreIds)){
						Set<String> set = new LinkedHashSet<>();
						StringUtils.paresToList(genreIds, set, ",");
						if(set.contains(genre)){
							set.remove(genre);
							genreIds = org.apache.commons.lang.StringUtils.join(set, ",");
							Date date = new Date();
							sycnSeller(sid, seller, date,genreIds);	
						}else{
							throw new ApplicationException("商家与分类不存在关联关系", new Object[]{genre,genreIds});
						}
					}else{
						throw new ApplicationException("商家与分类不存在关联关系", new Object[]{genre,genreIds});
					}
					resultable = new Resultable(Boolean.TRUE, "删除成功");
				}
			}
			return resultable;
		}catch(ApplicationException e){
			log.error(e.getMessage(), e);
			//抛出异常并回滚事务
			throw  e;
		}catch(Exception e){
			String msg= "删除商家类别关系异常";
			log.error(msg, e);
			//抛出异常并回滚事务
			throw new ApplicationException(msg, e, new Object[]{sellerTrader,uid});
		}finally{
			//记录日志
			fireLoginEvent(new String[]{"商家类别关系信息",String.format("商家编号: %s ,类别编号 : %s ", sellerId,genre),"删除关系","删除成功"},resultable!=null && resultable.getSuccess() ?1:0);
			
		}
	}
	
	/**
	 * 检查信息
	 * @param sellerId 商家编号
	 * @param genre 类别编号
	 * @return
	 */
	private boolean checkInfo(String sellerId,String genre){
		return (!StringUtils.hasLength(sellerId) || !StringUtils.hasLength(genre));
	}
	
	/**
	 * 根据编号获取商家
	 * @param sid
	 * @return
	 */
	private TSeller getSeller(Long sid)throws ApplicationException{
		TSeller updateSeller = sellerService.getObject(sid);
		if(updateSeller!=null){
			return updateSeller;
		}
		throw new ApplicationException("找不到对于的商家",  new Object[]{sid});
	}
	
	
}
