package com.xmniao.xmn.core.businessman.dao;


import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.businessman.entity.TCommentLabel;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 *@ClassName:SalesmanManagementDao
 *@Description:合作商业务员管理Dao层
 *@author hls
 *@date:2016年3月15日上午9:26:50
 */
public interface SellerLabelDao extends BaseDao<TCommentLabel>{
	/**
	 * @Title:getCommentLable
	 * @Description:获取商家标签
	 * @param tCommentLable
	 * @return List<TCommentLable>
	 * @throw
	 */
    public List<TCommentLabel> getCommentLabel(TCommentLabel commentLabel);
    /**
     * @Title:getCommentLableCount
     * @Description:获取商家标签信息条数
     * @param tCommentLable
     * @return Long
     * @throw
     */
    public Long getCommentLabelCount(TCommentLabel commentLabel);
    /**
     * @Title:getCommentLabelById
     * @Description:根据id获取商家标签信息用于初始化修改页面
     * @param id
     * @return TCommentLabel
     * @throw
     */
    public TCommentLabel getCommentLabelById(Long id);
   
    /**
     * @Title:getSellerLabel
     * @Description:修改商家标签
     * @param TCommentLabel
     * @return Long
     * @throw
     */
    public void updateSellerLabel(TCommentLabel commentLabel);
    
    /**
	 * @Title:vailStaff
	 * @Description:验证商家标签是否存在
	 * @return Integer
	 * @throw
	 */
	@DataSource("slave")
	public Integer vaillabelname(TCommentLabel commentLabel);
}
