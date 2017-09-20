package com.xmniao.xmn.core.businessman.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.businessman.dao.SellerLabelDao;
import com.xmniao.xmn.core.businessman.entity.TCommentLabel;
import com.xmniao.xmn.core.exception.ApplicationException;

/**
 *@ClassName:ExpertManagementService
 *@Description:达人评论管理service层
 *@author hls
 *@date:2016年3月25日下午5:00:04
 */
@Service
public class SellerLabelService extends BaseService<TCommentLabel> {
    /**
     * 添加日志记录方法
     */
    protected final Logger log = Logger.getLogger(getClass());

  
    @Autowired
    private SellerLabelDao sellerLabelDao;
    
    /**
     * @Title:getTexpertComment
     * @Description:查询商家标签
     * @param tCommentLable
     * @return List<TCommentLable>
     * @throw
     */
    public List<TCommentLabel> getCommentLabel(TCommentLabel commentLabel) {
        return sellerLabelDao.getCommentLabel(commentLabel);
    }
    /**
     * @Title:getCommentLableCount
     * @Description:查询商家标签记录条数
     * @param tCommentLable
     * @return Long
     * @throw
     */
    public Long getCommentLableCount(TCommentLabel commentLabel) {
        return sellerLabelDao.getCommentLabelCount(commentLabel);
    }

    /**
     * @Title:addCommentLabel
     * @Description:添加商家标签信息
     * @param tCommentLable
     * @return Integer
     * @throw
     */
    public Integer addCommentLabel(TCommentLabel commentLabel){
        try{
        	sellerLabelDao.addReturnId(commentLabel);
            this.log.info("商家标签信息添加成功");
        }catch(Exception e){
            this.log.info("商家标签信息添加失败: " + e);
            throw new ApplicationException("添加商家标签信息异常", e, new Object[]{commentLabel});
        }
        return commentLabel.getId();
    }

    /**
     * @Title:updateCommentLabel
     * @Description:修改商家标签信息
     * @param tCommentLable void
     * @throw
     */
    public void updateCommentLabel(TCommentLabel commentLabel){

        try{
        	sellerLabelDao.updateSellerLabel(commentLabel);
            this.log.info("商家标签信息修改成功");
        }catch(Exception e){
            this.log.info("商家标签信息修改失败: " + e);
            throw new ApplicationException("修改达人评论信息异常", e, new Object[]{commentLabel});
        }
    }
    /**
     * @Title:getExceptionObject
     * @Description:获取异常对象
     * @param flag
     * @param joint
     * @param e
     * @return ApplicationException
     * @throw
     */
    public ApplicationException getExceptionObject(boolean flag, TCommentLabel commentLabel, Exception e){
        if(!flag){  //修改
            return new ApplicationException("商家标签信息修改", e, new Object[]{commentLabel}, new String[]{"商家标签id", commentLabel.getId().toString(), "修改", "修改"});
        }else{ //添加
            return new ApplicationException("商家标签信息添加", e, new Object[]{commentLabel}, new String[]{"商家标签名称", commentLabel.getLabelname(), "添加", "添加"});
        }
    }
    /**
     * @Title:getCommentLabelById
     * @Description:根据id获取商家标签信息用于初始化修改页面
     * @param id
     * @param model void
     * @throw
     */
    public void getCommentLabelById(String id, ModelAndView model) {
    	TCommentLabel commentLabel = sellerLabelDao.getCommentLabelById(new Long(id));
        this.log.info(commentLabel);
        model.addObject("commentLabel", commentLabel);
    }
  /**
   * @Title:deleteCommentLabelInfo
   * @Description:删除商家标签
   * @param id
   * @return int
   * @throw
   */
	@Transactional(propagation = Propagation.REQUIRED)
	public int deleteCommentLabelInfo(String id) {
		int row = 0;
		if (null != id) {
			row = delete(id.split(","));
			sellerLabelDao.delete(id.split(","));
		}
		return row;
	}
	/**
	 * 覆盖父类方法
	 */
	@Override
	protected BaseDao<TCommentLabel> getBaseDao() {
		return sellerLabelDao;
	}
	/**
	 * @Title:vaillabelname
	 * @Description:根据商家id和标签名称查询标签是否已经存在
	 * @param commentLabel
	 * @return boolean
	 * @throw
	 */
	public boolean vaillabelname(TCommentLabel commentLabel){
		int idcount = sellerLabelDao.vaillabelname(commentLabel);
		if(idcount > 0){
			return false;
		}
		return true;
	}
}
