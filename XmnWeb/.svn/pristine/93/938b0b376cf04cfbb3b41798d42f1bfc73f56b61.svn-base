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
import com.xmniao.xmn.core.businessman.dao.ExpertManagementDao;
import com.xmniao.xmn.core.businessman.entity.TexpertComment;
import com.xmniao.xmn.core.exception.ApplicationException;

/**
 *@ClassName:ExpertManagementService
 *@Description:达人评论管理service层
 *@author hls
 *@date:2016年3月25日下午5:00:04
 */
@Service
public class ExpertManagementService extends BaseService<TexpertComment> {
    /**
     * 添加日志记录方法
     */
    protected final Logger log = Logger.getLogger(getClass());

  
    @Autowired
    private ExpertManagementDao expertManagementDao;
    
    /**
     * @Title:getTexpertComment
     * @Description:查询达人评论
     * @param expertComment
     * @return List<TexpertComment>
     * @throw
     */
    public List<TexpertComment> getTexpertComment(TexpertComment expertComment) {
        return expertManagementDao.getTexpertComment(expertComment);
    }
    /**
     * @Title:getTexpertCommentCount
     * @Description:查询达人评论记录条数
     * @param expertComment
     * @return Long
     * @throw
     */
    public Long getTexpertCommentCount(TexpertComment expertComment) {
        return expertManagementDao.getTexpertCommentCount(expertComment);
    }

    /**
     * @Title:addSalesman
     * @Description:添加达人评论信息
     * @param staff
     * @return Integer
     * @throw
     */
    public Integer addTexpertComment(TexpertComment expertComment){
        try{
            expertManagementDao.addReturnId(expertComment);
            this.log.info("达人评论信息添加成功");
        }catch(Exception e){
            this.log.info("达人评论信息添加失败: " + e);
            throw new ApplicationException("添加达人评论信息异常", e, new Object[]{expertComment});
        }
        return expertComment.getId();
    }

    /**
     * @Title:updateTexpertCommentCount
     * @Description:修改达人评论信息
     * @param expertComment void
     * @throw
     */
    public void updateTexpertComment(TexpertComment expertComment){

        try{
        	expertManagementDao.update(expertComment);
            this.log.info("达人评论信息修改成功");
        }catch(Exception e){
            this.log.info("达人评论信息修改失败: " + e);
            throw new ApplicationException("修改达人评论信息异常", e, new Object[]{expertComment});
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
    public ApplicationException getExceptionObject(boolean flag, TexpertComment expertComment, Exception e){
        if(!flag){  //修改
            return new ApplicationException("达人评论信息修改", e, new Object[]{expertComment}, new String[]{"达人评论表id", expertComment.getId().toString(), "修改", "修改"});
        }else{ //添加
            return new ApplicationException("达人评论信息添加", e, new Object[]{expertComment}, new String[]{"达人姓名", expertComment.getName(), "添加", "添加"});
        }
    }
    /**
     * @Title:getTexpertCommentInfo
     * @Description:根据达人Id查询达人评论信息用于初始化达人评论修改页面
     * @param id
     * @param model void
     * @throw
     */
    public void getTexpertCommentInfo(String id, ModelAndView model) {
    	TexpertComment expertComment = expertManagementDao.getTexpertCommentById(new Long(id));
        this.log.info(expertComment);
        model.addObject("expertComment", expertComment);
    }
    /**
     * @Title:deleteJoinInfo
     * @Description:删除达人评论
     * @param jointid
     * @return int
     * @throw
     */
	@Transactional(propagation = Propagation.REQUIRED)
	public int deleteTexpertCommentInfo(String id) {
		int row = 0;
		if (null != id) {
			row = delete(id.split(","));
			expertManagementDao.delete(id.split(","));
		}
		return row;
	}
	/**
	 * 覆盖父类方法
	 */
	@Override
	protected BaseDao<TexpertComment> getBaseDao() {
		return expertManagementDao;
	}
	/**
	 * @Title:getSellerLabelCount
	 * @Description:查询商家标签条数
	 * @param expertComment
	 * @return Long
	 * @throw
	 */
	public Integer getSellerLabelCount(TexpertComment expertComment) {
        return expertManagementDao.getSellerLabelCount(expertComment);
    }
	
}
