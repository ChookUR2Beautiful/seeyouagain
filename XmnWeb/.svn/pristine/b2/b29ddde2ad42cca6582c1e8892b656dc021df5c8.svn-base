package com.xmniao.xmn.core.user_terminal.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.common.entity.TAppPush;
import com.xmniao.xmn.core.common.service.AppPushService;
import com.xmniao.xmn.core.http.entity.PUserRequestSelect;
import com.xmniao.xmn.core.http.service.PuserService;
import com.xmniao.xmn.core.user_terminal.util.UserConstants;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

@RequestLogging(name="用户消息推送管理")
@Controller("userTerminalAppPushController")
@RequestMapping(value = "user_terminal/appPush")
public class AppPushController extends BaseController {

	@Autowired
	private AppPushService appPushService;
	
	@Autowired 
	private PuserService puserService;

	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：yang'xu
	 */
	@RequestMapping(value = "init")
	public String init(Model model) {
		model.addAttribute("client", UserConstants.CLIENT);
		return "common/appPushList";
	}

	/**
	 * 
	 * list(列表数据初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(TAppPush appPush) {
		Pageable<TAppPush> pageable = new Pageable<TAppPush>(appPush);
		pageable.setContent(appPushService.getList(appPush));
		pageable.setTotal(appPushService.count(appPush));
		return pageable;
	}

	/**
	 * 导出数据
	 * @param appPush
	 * @param request
	 * @param response
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@RequestMapping(value = "export")
	public void export(TAppPush appPush, HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException {
		appPush.setLimit(-1);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", appPushService.getList(appPush));
		doExport(request, response, "common/appPush.xls", params);
	}
	
	/**
	 * 
	 * delete(删除)
	 * 
	 * @author：zhou'sheng
	 */
	@SuppressWarnings("finally")
	@RequestLogging(name="用户端消息推送删除")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(HttpServletRequest request, @RequestParam("tid") String tid) {
		Resultable resultable = null;
		try {
			Integer resultNum = appPushService.delete(tid.split(","));
			if (resultNum > UserConstants.RESULTNUM) {
				this.log.info("删除成功");
				resultable = new Resultable(true, "操作成功");
				//写入 日志记录表
				String[] s={"用户消息推送编号",tid,"删除","删除"};
				appPushService.fireLoginEvent(s,UserConstants.FIRELOGIN_SUCCESS);
			}
		} catch (Exception e) {
			this.log.error("删除异常", e);
			resultable = new Resultable(false, "操作失败");
			//写入 日志记录表
			String[] s={"用户消息推送编号",tid,"删除","删除"};
			appPushService.fireLoginEvent(s,UserConstants.FIRELOGIN_ERROR);
		} finally {
			return resultable;
		}
	}

	/**
	 * 
	 * addInit(添加初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "/add/init")
	public ModelAndView addInit() {
		ModelAndView modelAndView = new ModelAndView("common/editAppPush");
		modelAndView.addObject("isType", "add");
		return modelAndView;
	}

	/**
	 * 
	 * add(添加)
	 * 
	 * @author：zhou'sheng
	 */
	@SuppressWarnings("finally")
	@RequestLogging(name="用户端消息推送添加")
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(TAppPush appPush) {
		Resultable resultable = null;
		try {
			appPushService.add(appPush);
			this.log.info("添加成功");
			resultable = new Resultable(true, "操作成功");
			//添加到日志记录表  remark 字段
			String word = appPush.getTitle();
			String str = "";
			if (word.length() <= UserConstants.WORD_LENGTH){
				str = word;
			}else{
				str = word.substring(UserConstants.RESULTNUM_INIT, UserConstants.WORD_LENGTH)+"...";
			}
			String[] s={"用户端消息推送",str,"新增"};
			appPushService.fireLoginEvent(s,UserConstants.FIRELOGIN_SUCCESS);
		} catch (Exception e) {
			this.log.error("添加异常", e);
			resultable = new Resultable(false, "操作失败");
			//添加到日志记录表  remark 字段
			String word = appPush.getTitle();
			String str = "";
			if (word.length() <= UserConstants.WORD_LENGTH){
				str = word;
			}else{
				str = word.substring(UserConstants.RESULTNUM_INIT, UserConstants.WORD_LENGTH)+"...";
			}
			String[] s={"用户端消息推送",str,"新增"};
			appPushService.fireLoginEvent(s,UserConstants.FIRELOGIN_ERROR);
		} finally {
			return resultable;
		}
	}

	/**
	 * 
	 * updateInit(修改初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = "/update/init")
	public ModelAndView updateInit(HttpServletRequest request, @RequestParam("tid") String tid) {
		ModelAndView modelAndView = new ModelAndView("common/editAppPush");
		modelAndView.addObject("isType", "update");
		try {
			TAppPush appPush = appPushService.getObject(new Long(tid));
			this.log.info(appPush);
			modelAndView.addObject("appPush", appPush);
		} catch (NumberFormatException e) {
			this.log.error("修改初始异常", e);
		} finally {
			return modelAndView;
		}
	}

	/**
	 * 
	 * update(修改)
	 * 
	 * @author：zhou'sheng
	 */
	@SuppressWarnings("finally")
	@RequestLogging(name="用户端消息推送修改")
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(TAppPush  appPush) {
		Resultable resultable = null;
		try {
			appPushService.update(appPush);
			this.log.info("修改成功");
			resultable = new Resultable(true, "操作成功");
			//写入 日志记录表
			String[] s={"用户端消息推送编号",String.valueOf(appPush.getTid()),"修改","修改"};
			appPushService.fireLoginEvent(s,UserConstants.FIRELOGIN_SUCCESS);
		} catch (Exception e) {
			this.log.error("修改异常", e);
			resultable = new Resultable(false, "操作失败");
			//写入 日志记录表
			String[] s={"用户端消息推送编号",String.valueOf(appPush.getTid()),"修改","修改"};
			appPushService.fireLoginEvent(s,UserConstants.FIRELOGIN_SUCCESS);
		} finally {
			return resultable;
		}
	}
	
	/**
	 * 初始化
	 * @return
	 */
	@RequestMapping("init/choseMember/init")
	public ModelAndView init(){
		ModelAndView mv = new ModelAndView("user_terminal/choseMember");
		mv.addObject("requestInit", "user_terminal/appPush/init/choseMember/list");
		return mv;
	}
	
	/**
	 * 查询
	 * @param puser
	 * @return
	 */
	@RequestMapping(value = "init/choseMember/list")
	public ModelAndView list(PUserRequestSelect puser) {
		ModelAndView mv = new ModelAndView("user_terminal/choseMemberTable");
		puserService.getPuserInfo(mv,puser,true);
		return mv;
	}

}
