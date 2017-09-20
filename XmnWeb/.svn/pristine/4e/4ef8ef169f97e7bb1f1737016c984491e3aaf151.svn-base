package com.xmniao.xmn.core.businessman.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.business_cooperation.util.PartnerConstants;
import com.xmniao.xmn.core.businessman.entity.TCommentLabel;
import com.xmniao.xmn.core.businessman.entity.TexpertComment;
import com.xmniao.xmn.core.businessman.service.ExpertManagementService;
import com.xmniao.xmn.core.businessman.service.SellerLabelService;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.util.StringUtils;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 *@ClassName:ExpertManagementController
 *@Description:达人评论管理控制层
 *@author hls
 *@date:2016年3月25日下午4:54:11
 */
@RequestLogging(name="达人评论管理")
@Controller
@RequestMapping(value = "businessman/expert")
public class ExpertManagementController extends BaseController {

    @Autowired
    private ExpertManagementService expertManagementService;
    
    @Autowired
    private SellerLabelService sellerLabelService;

    /**
     * @Title:init
     * @Description:初始化达人评论管理页面
     * @return String
     * @throw
     */
    @RequestMapping(value = "init")
    public ModelAndView init(TexpertComment expertComment) {
    	ModelAndView modelAndView = new ModelAndView("businessman/expertcommentmanage/expertManagementList");
    	Integer sellerid = expertComment.getSellerid();
    	String sellername = expertComment.getSellername();
    	modelAndView.addObject("sellerid",sellerid);
    	modelAndView.addObject("sellername",sellername);
        return modelAndView;
    }

    /**
     * @Title:list
     * @Description:查询达人评论管理列表
     * @param joint
     * @return Object
     * @throw
     */
	@RequestMapping(value = "init/list")
    @ResponseBody
    public Object list(TexpertComment expertComment) {
        Pageable<TexpertComment> pageable = new Pageable<TexpertComment>(expertComment);
        pageable.setContent(expertManagementService.getTexpertComment(expertComment));
        pageable.setTotal(expertManagementService.getTexpertCommentCount(expertComment));
        return pageable;
    }
	
	 /**
	  * @Title:labelnum
	  * @Description:查询商家标签数
	  * @param expertComment
	  * @return Object
	  * @throw
	  */
	@RequestMapping(value = "init/labelnum")
    @ResponseBody
    public Object labelnum(TexpertComment expertComment) {
        Integer labelNum = expertManagementService.getSellerLabelCount(expertComment);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("labelNum", labelNum);
        return JSON.toJSON(map);
    }
	

    /**
     * @Title:addInit
     * @Description:添加达人评论
     * @return ModelAndView
     * @throw
     */
    @RequestMapping(value = "/add/init")
    @ResponseBody
    public ModelAndView addInit(TexpertComment expertComment) {
    	Integer sellerid = expertComment.getSellerid();
    	String sellername = expertComment.getSellername();
        ModelAndView modelAndView = new ModelAndView("businessman/expertcommentmanage/addExpertComment");
        modelAndView.addObject("isType", "add");
        modelAndView.addObject("sellerid", sellerid);
        modelAndView.addObject("sellername",sellername);
        return modelAndView;
    }

    /**
     * @Title:updateInit
     * @Description:修改达人评论初始化
     * @param request
     * @param staffid
     * @return ModelAndView
     * @throw
     */
    @SuppressWarnings("finally")
    @RequestMapping(value = "/update/init")
    @ResponseBody
    public ModelAndView updateInit(TexpertComment expertComment) {
        ModelAndView modelAndView = new ModelAndView("businessman/expertcommentmanage/addExpertComment");
        try {
        	Integer id = expertComment.getId();
        	Integer sellerid = expertComment.getSellerid();
        	String sellername = expertComment.getSellername();
        	expertManagementService.getTexpertCommentInfo(id.toString(), modelAndView);
            modelAndView.addObject("isType", "update");
            modelAndView.addObject("sellerid", sellerid);
            modelAndView.addObject("sellername",sellername);
        } catch (NumberFormatException e) {
            this.log.error("修改初始化异常", e);
        } finally {
            return modelAndView;
        }
    }
    /**
     * @Title:update
     * @Description:达人评论修改
     * @param joint
     * @return Object
     * @throw
     */
    @RequestLogging(name="达人评论修改")
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(TexpertComment expertComment) {
        Resultable resultable = null;
        try {
        	expertComment.setUdate(new Date());
        	expertManagementService.updateTexpertComment(expertComment);
                this.log.info("修改成功");
                resultable = new Resultable(true, "修改成功");
                recordUpdateLog(expertComment.getId(), PartnerConstants.FIRELOGIN_NUMA);

        } catch (Exception e) {
            this.log.error("修改异常", e);
            resultable = new Resultable(false, "操作失败");
            expertManagementService.fireLoginEvent(StringUtils.addStrToStrArray(((ApplicationException)e).getLogInfo(), new ApplicationException("修改达人评论异常", e, new Object[]{expertComment}).getMessage()), 0);
        }
        return resultable;
    }

    private void recordUpdateLog(Integer id, int flag){
        String[] s={"达人评论id",String.valueOf(id),"修改","修改"};
        expertManagementService.fireLoginEvent(s, flag);
    }

    /**
     * @Title:addStaff
     * @Description:添加达人评论信息
     * @param staff
     * @return Object
     * @throw
     */
    @SuppressWarnings("finally")
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object addTexpertComment(TexpertComment expertComment) {
        Resultable resultable = null;
        try {
        	expertComment.setSdate(new Date());
            Integer id = expertManagementService.addTexpertComment(expertComment);
            this.log.info("添加成功");
            resultable = new Resultable(true, "操作成功");
            resultable.setData(id);
            String[] s={"达人评论id",String.valueOf(id),"添加","添加"};
            expertManagementService.fireLoginEvent(s,PartnerConstants.FIRELOGIN_NUMA);//添加到日志记录表
        } catch (Exception e) {
            this.log.error("添加异常", e);
            resultable = new Resultable(false, "操作失败");
        } finally {
            return resultable;
        }
    }
    /**
     * @Title:delete
     * @Description:删除达人评论信息
     * @param request
     * @param tid
     * @return Object
     * @throw
     */
    @SuppressWarnings("finally")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(HttpServletRequest request, @RequestParam("id") String id) {
		Resultable resultable = null;
		try {
			Integer resultNum = expertManagementService.delete(id.split(","));
			if (resultNum > 0) {
				this.log.info("删除成功");
				resultable = new Resultable(true, "操作成功");
				//写入日志记录表
				String[] s={"达人评论信息id",id,"删除","删除"};
				expertManagementService.fireLoginEvent(s,PartnerConstants.FIRELOGIN_NUMA);
			}
		} catch (Exception e) {
			this.log.error("删除异常", e);
			resultable = new Resultable(false, "操作失败");
			//写入日志记录表
			String[] s={"达人评论信息id",id,"删除","删除"};
			expertManagementService.fireLoginEvent(s,PartnerConstants.FIRELOGIN_NUMB);
		} finally {
			return resultable;
		}
	}
    
    /**
     * @Title:sellerlabel
     * @Description:商家标签管理初始化页面
     * @param expertComment
     * @return Object
     * @throw
     */
    @RequestMapping(value = "/sellerlabel/init")
    @ResponseBody
    public Object sellerlabel(TexpertComment expertComment) {
    	ModelAndView modelAndView = new ModelAndView("businessman/expertcommentmanage/sellerlabelList");
    	Integer sellerid = expertComment.getSellerid();
    	String sellername = expertComment.getSellername();
    	modelAndView.addObject("sellerid",sellerid);
    	modelAndView.addObject("sellername",sellername);
        return modelAndView;
    }
    
    /**
     * @Title:list
     * @Description:查询商家标签列表
     * @param joint
     * @return Object
     * @throw
     */
	@RequestMapping(value = "/sellerlabel/list")
    @ResponseBody
    public Object labellist(TCommentLabel commentLabel) {
        Pageable<TCommentLabel> pageable = new Pageable<TCommentLabel>(commentLabel);
        pageable.setContent(sellerLabelService.getCommentLabel(commentLabel));
        pageable.setTotal(sellerLabelService.getCommentLableCount(commentLabel));
        return pageable;
    }
    
    /**
     * @Title:addsellerlabel
     * @Description:添加商家标签初始化页面O
     * @param commentLable
     * @return Object
     * @throw
     */
    @RequestMapping(value = "/sellerlabel/add/init")
    @ResponseBody
    public Object addsellerlabel(TCommentLabel commentLabel) {
    	Integer sellerid = commentLabel.getSellerid();
    	String sellername = commentLabel.getSellername();
        ModelAndView modelAndView = new ModelAndView("businessman/expertcommentmanage/addCommentLabel");
        modelAndView.addObject("isType", "add");
        modelAndView.addObject("sellerid", sellerid);
        modelAndView.addObject("sellername",sellername);
        return modelAndView;
    }
    /**
	 * @Title:vaillabelname
	 * @Description:根据商家id和标签名称查询标签是否已经存在
	 * @param commentLabel
	 * @return boolean
	 * @throw
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = "/sellerlabel/vaillabelname")
	@ResponseBody
	public Boolean vaillabelname(TCommentLabel commentLabel) {
		String istype = commentLabel.getIstype();
		boolean existFlag = false;
		try {
			if("update".equals(istype)){
				existFlag = true;
			}else{
				existFlag  = sellerLabelService.vaillabelname(commentLabel);
			}
			this.log.info("验证标签成功");
		} catch (Exception e) {
			this.log.error("验证标签异常", e);
		} finally {
			return existFlag;
		}
	}
	
	/**
	 * @Title:addCommentLabel
	 * @Description:添加商家标签信息
	 * @param commentLabel
	 * @return Object
	 * @throw
	 */
    @SuppressWarnings("finally")
    @RequestMapping(value = "/sellerlabel/add")
    @ResponseBody
    public Object addCommentLabel(TCommentLabel commentLabel) {
        Resultable resultable = null;
        try {
        	commentLabel.setSdate(new Date());
            Integer id = sellerLabelService.addCommentLabel(commentLabel);
            this.log.info("添加成功");
            resultable = new Resultable(true, "操作成功");
            resultable.setData(id);
            String[] s={"标签id",String.valueOf(id),"添加","添加"};
            sellerLabelService.fireLoginEvent(s,PartnerConstants.FIRELOGIN_NUMA);//添加到日志记录表
        } catch (Exception e) {
            this.log.error("添加异常", e);
            resultable = new Resultable(false, "操作失败");
        } finally {
            return resultable;
        }
    }
    /**
     * @Title:deleteCommentLabel
     * @Description:删除商家标签信息
     * @param request
     * @param id
     * @return Object
     * @throw
     */
    @SuppressWarnings("finally")
	@RequestMapping(value = "/sellerlabel/delete")
	@ResponseBody
	public Object deleteCommentLabel(HttpServletRequest request, @RequestParam("id") String id) {
		Resultable resultable = null;
		try {
			Integer resultNum = sellerLabelService.delete(id.split(","));
			if (resultNum > 0) {
				this.log.info("删除成功");
				resultable = new Resultable(true, "操作成功");
				//写入日志记录表
				String[] s={"标签id",id,"删除","删除"};
				sellerLabelService.fireLoginEvent(s,PartnerConstants.FIRELOGIN_NUMA);
			}
		} catch (Exception e) {
			this.log.error("删除异常", e);
			resultable = new Resultable(false, "操作失败");
			//写入日志记录表
			String[] s={"标签id",id,"删除","删除"};
			sellerLabelService.fireLoginEvent(s,PartnerConstants.FIRELOGIN_NUMB);
		} finally {
			return resultable;
		}
	}
    /**
     * @Title:updateCommentLabelInit
     * @Description:修改商家标签初始化
     * @param commentLabel
     * @return ModelAndView
     * @throw
     */
    @SuppressWarnings("finally")
    @RequestMapping(value = "/sellerlabel/update/init")
    @ResponseBody
    public ModelAndView updateCommentLabelInit(TCommentLabel commentLabel) {
        ModelAndView modelAndView = new ModelAndView("businessman/expertcommentmanage/addCommentLabel");
        try {
        	Integer id = commentLabel.getId();
        	Integer sellerid = commentLabel.getSellerid();
        	String sellername = commentLabel.getSellername();
        	sellerLabelService.getCommentLabelById(id.toString(), modelAndView);
            modelAndView.addObject("isType", "update");
            modelAndView.addObject("sellerid", sellerid);
            modelAndView.addObject("sellername",sellername);
        } catch (NumberFormatException e) {
            this.log.error("修改初始化异常", e);
        } finally {
            return modelAndView;
        }
    }
    /**
     * @Title:updateCommentLabel
     * @Description:商家标签修改
     * @param commentLabel
     * @return Object
     * @throw
     */
    @RequestLogging(name="商家标签修改")
    @RequestMapping(value = "/sellerlabel/update")
    @ResponseBody
    public Object updateCommentLabel(TCommentLabel commentLabel) {
        Resultable resultable = null;
        try {
        	commentLabel.setUdate(new Date());
        	sellerLabelService.updateCommentLabel(commentLabel);
            this.log.info("修改成功");
            resultable = new Resultable(true, "修改成功");
            recordUpdateLabelLog(commentLabel.getId(), PartnerConstants.FIRELOGIN_NUMA);

        } catch (Exception e) {
            this.log.error("修改异常", e);
            resultable = new Resultable(false, "操作失败");
            sellerLabelService.fireLoginEvent(StringUtils.addStrToStrArray(((ApplicationException)e).getLogInfo(), new ApplicationException("修改商家标签异常", e, new Object[]{commentLabel}).getMessage()), 0);
        }
        return resultable;
    }

    private void recordUpdateLabelLog(Integer id, int flag){
        String[] s={"标签id",String.valueOf(id),"修改","修改"};
        sellerLabelService.fireLoginEvent(s, flag);
    }
}