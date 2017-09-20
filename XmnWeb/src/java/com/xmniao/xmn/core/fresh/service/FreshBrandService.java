/**
 * 
 */
package com.xmniao.xmn.core.fresh.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.ResultFile;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.base.Import.PoiImport;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.fresh.dao.FreshBrandDao;
import com.xmniao.xmn.core.fresh.dao.FreshTypeDao;
import com.xmniao.xmn.core.fresh.entity.FreshBrand;
import com.xmniao.xmn.core.fresh.entity.FreshType;
import com.xmniao.xmn.core.util.FastfdsConstant;

/**
 * 
 * 项目名称：XmnWeb1
 * 
 * 类名称：FreshBrandService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2016年12月22日 下午3:13:17 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class FreshBrandService extends BaseService<FreshBrand>{
	@Autowired
	private FreshBrandDao freshBrandDao;
	
	@Autowired
	private FreshTypeDao freshTypeDao;

	/**
	 * 方法描述：分页加载
	 * 创建人： jianming <br/>
	 * 创建时间：2016年12月22日下午4:21:23 <br/>
	 * @param brand
	 * @return
	 */
	public List<FreshBrand> getList(FreshBrand brand) {
		log.info("查询品牌列表");
		return freshBrandDao.selectByPage(brand);
	}

	/**
	 * 方法描述：统计条数
	 * 创建人： jianming <br/>
	 * 创建时间：2016年12月22日下午4:33:27 <br/>
	 * @param brand
	 * @return
	 */
	public Long getTotal(FreshBrand brand) {
		return freshBrandDao.countTotal(brand);
	}

	/**
	 * 方法描述：添加
	 * 创建人： jianming <br/>
	 * 创建时间：2016年12月22日下午7:48:36 <br/>
	 * @param brand
	 */
	public void add(FreshBrand brand) {
		freshBrandDao.insert(brand);
		log.info("添加品牌成功");
	}

	/**
	 * 方法描述：修改
	 * 创建人： jianming <br/>
	 * 创建时间：2016年12月22日下午8:22:53 <br/>
	 * @param brand
	 */
	public Integer update(FreshBrand brand) {
		return freshBrandDao.updateByPrimaryKey(brand);
	}

	/* (non-Javadoc)
	 * @see com.xmniao.xmn.core.base.BaseService#getBaseDao()
	 */
	@Override
	protected BaseDao getBaseDao() {
		return freshBrandDao;
	}


	/**
	 * 方法描述：删除
	 * 创建人： jianming <br/>
	 * 创建时间：2016年12月23日上午10:35:01 <br/>
	 * @param ids
	 */
	public void delete(String ids) {
		freshBrandDao.deleteMine(ids);
		log.info("删除品牌成功 ids="+ids);
	}

	/**
	 * 方法描述：获取导出列表
	 * 创建人： jianming <br/>
	 * 创建时间：2016年12月24日下午2:21:07 <br/>
	 * @param brand
	 * @return
	 */
	public List<FreshBrand> getExport(FreshBrand brand) {
		return freshBrandDao.selectExport(brand);
	}

	/**
	 * 方法描述：导入品牌
	 * 创建人： jianming <br/>
	 * 创建时间：2016年12月24日下午5:28:37 <br/>
	 * @param multipartFile
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public ResultFile importBrandInfo(MultipartFile multipartFile) {
		ResultFile rf=null;
		try {
			List<FreshBrand> brandInfo = PoiImport.dataImport(multipartFile, FreshBrand.class);
			if(brandInfo!=null&&brandInfo.size()>0){
				if(brandInfo.size()>1000){
					rf = new ResultFile(FastfdsConstant.FILE_UPLOAD_FASTDFS_FAILURE, String.format("导入数据不能超过一千条"));
					return rf;
				}
				Resultable resultable =new Resultable();
				rf=savaBrandInfo( brandInfo,resultable);
			}else{
				rf = new ResultFile(FastfdsConstant.FILE_UPLOAD_FASTDFS_FAILURE, String.format("导入失败,模板错误或数据为空!"));
			}
			return rf;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("文件上传失败", e);
			throw new ApplicationException("导入品牌信息异常",e);
		}finally {
			fireLoginEvent( new String[]{"积分超市-品牌管理-品牌导入","导入操作","积分超市-品牌管理-品牌导入",""}, rf==null?0:rf.getStatus());
		}
	}

	/**
	 * 方法描述：批量添加
	 * 创建人： jianming <br/>
	 * 创建时间：2016年12月24日下午5:32:48 <br/>
	 * @param brandInfo
	 * @param resultable
	 * @return 
	 */
	private ResultFile savaBrandInfo(List<FreshBrand> brandInfo, Resultable resultable) {
		List<FreshType> freshTypes=freshTypeDao.findAll();
		for (FreshBrand freshBrand : brandInfo) {
			String typeName = freshBrand.getTypeName();
			for (FreshType freshType : freshTypes) {
				if(typeName.equals(freshType.getName())){
					freshBrand.setTypeId(freshType.getId());
					break;
				}
			}
			if(freshBrand.getTypeId()==null){
				ResultFile resultFile = new ResultFile(FastfdsConstant.FILE_UPLOAD_FASTDFS_FAILURE, String.format("导入失败,没有\""+freshBrand.getTypeName()+"\"此分类"));
				return resultFile;
			}
			freshBrand.setCreateTime(new Date());
			freshBrand.setUpdateTime(new Date());
		}
		freshBrandDao.addBatchMine(brandInfo);
		resultable.setMsg("成功导入"+brandInfo.size()+"条数据");
		return new ResultFile(FastfdsConstant.FILE_UPLOAD_FASTDFS_SUCCESS, String.format(resultable.getMsg()));
	}

	/**
	 * 方法描述：获取所有品牌
	 * 创建人： jianming <br/>
	 * 创建时间：2016年12月26日下午6:01:10 <br/>
	 * @param freshBrand
	 * @return
	 */
	public List<FreshBrand> getAll(FreshBrand freshBrand) {
		return freshBrandDao.findAll();
	}

	/**
	 * 方法描述：判断是否包含商品
	 * 创建人： jianming <br/>
	 * 创建时间：2017年1月11日下午6:19:37 <br/>
	 * @param ids
	 * @return
	 */
	public boolean hasProduct(String ids) {
		List<FreshBrand> brands = freshBrandDao.hasProduct(ids);
		if(brands.size()>0){
			return true;
		}
		return false;
	}
}
