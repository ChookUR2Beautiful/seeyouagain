package com.xmniao.xmn.core.live_anchor.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.ResultFile;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.businessman.entity.Card;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.live_anchor.constant.LiveConstant;
import com.xmniao.xmn.core.live_anchor.entity.AnchorRatioBean;
import com.xmniao.xmn.core.live_anchor.entity.BLiveAnchorImage;
import com.xmniao.xmn.core.live_anchor.entity.BLiver;
import com.xmniao.xmn.core.live_anchor.entity.BursEarningsRank;
import com.xmniao.xmn.core.live_anchor.entity.FansConfigureRequest;
import com.xmniao.xmn.core.live_anchor.entity.TLiveLevel;
import com.xmniao.xmn.core.live_anchor.service.BLiveAnchorImageService;
import com.xmniao.xmn.core.live_anchor.service.BursEarningsRankService;
import com.xmniao.xmn.core.live_anchor.service.TLiveAnchorService;
import com.xmniao.xmn.core.live_anchor.service.TLiveLevelService;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.FastfdsConstant;
import com.xmniao.xmn.core.util.PageConstant;
import com.xmniao.xmn.core.util.StringUtils;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;
import com.xmniao.xmn.core.util.handler.annotation.RequestToken;
import com.xmniao.xmn.core.xmnpay.entity.LiveWallet;
import com.xmniao.xmn.core.xmnpay.service.LiveWalletService;

/**
 * 项目名称：XmnWeb_LVB
 * 
 * 类名称：LiveAnchorController
 *
 * 类描述：主播Controller
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-8-6下午4:07:43
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@RequestLogging(name="主播管理")
@Controller
@RequestMapping(value = "anchor/manage")
public class LiveAnchorController extends BaseController {
	
	
	
	/**
	 * 注入主播服务
	 */
	@Autowired
	private TLiveAnchorService liveAnchorService;
	
	/**
	 * 注入直播钱包服务
	 */
	@Autowired
	private LiveWalletService liveWalletService;
	
	/**
	 * 注入会员活动收益等级Service
	 */
	@Autowired
	private BursEarningsRankService earningsRankService;
	
	/**
	 * 注入主播相册服务
	 */
	@Autowired
	private BLiveAnchorImageService anchorImageService;
	
	/**
	 * 注入主播等级服务
	 */
	@Autowired
	private TLiveLevelService liveLevelService;
	
	/**
	 * 主播管理列表初始页面
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "live_anchor/anchorManage";
	}
	
	/**
	 * 
	 * 方法描述：加载主播分类统计信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-4-13下午5:13:46 <br/>
	 * @return
	 */
	@RequestMapping(value="init/loadCountAnchor")
	@ResponseBody
	public Resultable loadCountAnchor(){
		Resultable result =new Resultable();
		try {
			Map<String, Object> countAnchor = liveAnchorService.countAnchor();
			result.setData(countAnchor);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("加载主播分类统计信息失败!");
		}
		
		return result;
	}
	
	/**
	 * 主播管理列表
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object initList(BLiver liveAnchor) {
		Pageable<BLiver> pageable = new Pageable<BLiver>(liveAnchor);
		//用户类型 1 主播 2 普通用户
		liveAnchor.setUtype(LiveConstant.UTYPE_ANCHOR);
		liveAnchorService.getListPage(liveAnchor, pageable);
		return pageable;
	}
	
	/**
	 * 
	 * 方法描述：初始化主播下拉框
	 * 创建人： huang'tao
	 * 创建时间：2016-8-10下午3:45:24
	 * @param liveAnchor
	 * @return
	 */
	@RequestMapping(value = "initAnchorId",method=RequestMethod.POST)
	@ResponseBody
	public Object initAnchorId(BLiver liveAnchor) {
		Pageable<BLiver> pageable = new Pageable<BLiver>(liveAnchor);
		liveAnchor.setUtype(LiveConstant.UTYPE_ANCHOR);
		liveAnchor.setStatus(true);
		List<BLiver> liveAnchorList = liveAnchorService.getList(liveAnchor);
		pageable.setContent(liveAnchorList);
		return pageable;
	}
	
	/**
	 * 
	 * 方法描述：初始化直播用户下拉框
	 * 创建人： huang'tao
	 * 创建时间：2016-8-10下午3:45:24
	 * @param liveAnchor
	 * @return
	 */
	@RequestMapping(value = "initLiverId",method=RequestMethod.POST)
	@ResponseBody
	public Object initLiverId(BLiver liveAnchor) {
		Pageable<BLiver> pageable = new Pageable<BLiver>(liveAnchor);
		liveAnchor.setOrder(PageConstant.NOT_ORDER);
		liveAnchor.setStatus(true);
		List<BLiver> liveAnchorList = liveAnchorService.getBaseInfoList(liveAnchor);
		pageable.setContent(liveAnchorList);
		return pageable;
	}
	
	/**
	 * 
	 * 方法描述：初始化直播用户下拉框,排除等下级
	 * 创建人： huang'tao
	 * 创建时间：2016-8-10下午3:45:24
	 * @param liveAnchor
	 * @return
	 */
	@RequestMapping(value = "initLiverExclude",method=RequestMethod.POST)
	@ResponseBody
	public Object initLiverExclude(BLiver liveAnchor) {
		Pageable<BLiver> pageable = new Pageable<BLiver>(liveAnchor);
		liveAnchor.setOrder(PageConstant.NOT_ORDER);
		List<BLiver> liveAnchorList = liveAnchorService.getBaseInfoExcludeList(liveAnchor);
		pageable.setContent(liveAnchorList);
		return pageable;
	}
	
	/**
	 * 主播添加页面初始化
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "add/init")
	public String addInit(BLiver liveAnchor,Model model) {
		return "live_anchor/anchorEdit";
	}
	
	/**
	 * 添加主播信息
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "add")
	@ResponseBody
	public Resultable add(BLiver liveAnchor) {
		Resultable result=new Resultable();
		try {
			//判断是否存在b_urs用户,isBinding 为Y 则直接保存主播信息，否则先创建寻蜜鸟会员
			int count=liveAnchorService.saveLiverInfo(liveAnchor);
			if(count>0){
				result.setMsg("添加成功!");
				result.setSuccess(true);
			}else{
				result.setMsg("添加失败!");
				result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error(e.getMessage(), e);
		}
		return result;
	}
	

	/**
	 * 主播修改页面初始化
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "update/init")
	public String updateInit(BLiver liveAnchor,Model model) {
		Integer anchorId = liveAnchor.getId();
		BLiver anchor = liveAnchorService.selectByPrimaryKey(anchorId);
		
		BLiveAnchorImage anchorImage =new BLiveAnchorImage();
		anchorImage.setAnchorId(anchorId);
		anchorImage.setLimit(PageConstant.PAGE_LIMIT_NO);
		List<BLiveAnchorImage> anchorImageList = anchorImageService.getList(anchorImage );
		model.addAttribute("anchor",anchor );
		model.addAttribute("anchorImageList",anchorImageList );
		return "live_anchor/anchorEdit";
	}
	
	/**
	 * 主播修改页面初始化
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "update/init/anchorView")
	public String anchorView(BLiver liveAnchor,Model model) {
		Integer anchorId = liveAnchor.getId();
		try {
			if(anchorId!=null){
				BLiver anchor = liveAnchorService.selectByPrimaryKey(anchorId);
				Integer uid = anchor.getUid();
				Object[] uids = {uid};
				List<LiveWallet> walletList = liveWalletService.selectLiveWalletByUids(uids);
				if(walletList!= null && walletList.size()>0){
					LiveWallet liveWallet = walletList.get(0);
					anchor.setBirdEggTotal(liveWallet.getEggsTotal());
					anchor.setBalance(liveWallet.getBalance());
				}
				
				Integer levelId = anchor.getLevelId();
				TLiveLevel liveLevel=new TLiveLevel();
				liveLevel.setId(levelId);
				TLiveLevel liveLevelData = liveLevelService.getLiveLevelData(liveLevel);
				BigDecimal topIncome = null;
				if(liveLevelData!=null){
					topIncome = liveLevelData.getTopIncome();
				}
				
				BLiveAnchorImage anchorImage =new BLiveAnchorImage();
				anchorImage.setAnchorId(anchorId);
				List<BLiveAnchorImage> anchorImageList = anchorImageService.getList(anchorImage );
				model.addAttribute("anchor",anchor );
				model.addAttribute("topIncome",topIncome );
				model.addAttribute("anchorImageList",anchorImageList );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "live_anchor/anchorView";
	}
	
	/**
	 * 主播详情页面初始化
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "detail/init")
	public String detailInit(BLiver liveAnchor,Model model) {
		model.addAttribute("anchor", liveAnchorService.selectByPrimaryKey(liveAnchor.getId()));
		return "live_anchor/anchorDetail";
	}
	
	/**
	 * 
	 * 方法描述：更新主播信息
	 * 创建人： huang'tao
	 * 创建时间：2016-8-8上午11:13:04
	 * @param liveAnchor
	 * @return
	 */
	@RequestMapping(value = {"update"})
	@ResponseBody
	public Resultable update(BLiver liveAnchor){
		Resultable result=new Resultable();
		try {
			liveAnchor.setPassword(null);//修改主播信息时，密码不可修改
			liveAnchor.setUpdateTime(new Date());
			int count = liveAnchorService.updateAnchorInfo(liveAnchor);
			if(count>0){
				result.setMsg("更新成功!");
				result.setSuccess(true);
			}else {
				result.setMsg("更新失败!");
				result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error(e.getMessage(), e);
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：更新主播有效状态 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-4-13下午6:12:09 <br/>
	 * @param liveAnchor
	 * @return
	 */
	@RequestMapping(value="update/init/updateStatus")
	@ResponseBody
	public Resultable updateStatus(BLiver liveAnchor){
		Resultable result = new Resultable();
		try {
			liveAnchorService.updateByPrimaryKeySelective(liveAnchor);
			result.setSuccess(true);
			result.setMsg("操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("操作失败!");
		}
		return result;
	}
	
	
	/**
	 * 
	 * 方法描述：获取主播等级信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-4-17上午10:52:40 <br/>
	 * @param liveLevel
	 * @return
	 */
	@RequestMapping(value="update/init/getAnchorLevelInfoById")
	@ResponseBody
	public Object getAnchorLevelInfoById(TLiveLevel liveLevel){
		TLiveLevel liveLevelData = liveLevelService.getLiveLevelData(liveLevel);
		return liveLevelData;
	}
	
	/**
	 * 
	 * 方法描述：删除主播信息
	 * 创建人： huang'tao
	 * 创建时间：2016-8-8下午6:26:35
	 * @param liveAnchor
	 * @return
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public Resultable delete(BLiver liveAnchor){
		Resultable result=new Resultable();
		try {
//			int count = liveAnchorService.deleteByPrimaryKey(liveAnchor.getId());
			liveAnchor.setStatus(false);
			int count = liveAnchorService.updateByPrimaryKeySelective(liveAnchor);
			//同步用户关注主播信息
			liveAnchorService.syncFocusInfo(liveAnchor.getId());
			//同步主播的通告信息
			liveAnchorService.syncRecordInfo(liveAnchor);
			//调用直播钱包服务
			/*LiveWallet liveWallet = liveWalletService.selectByUid(liveAnchor.getUid());
			if(liveWallet!=null){
				LiveWallet liveWalletParam = new LiveWallet();
				liveWalletParam.setId(liveWallet.getId());
				liveWalletParam.setStatus(3);//状态  1正常  2锁定  3注销
				liveWalletParam.setUpdateTime(new Date());
				liveWalletService.updateLiveWallet(liveWalletParam);
			}*/
			Integer uid = liveAnchor.getUid();
			liveAnchorService.updateLiveWallet(uid);
			
			if(count>0){
				result.setMsg("删除成功!");
				result.setSuccess(true);
			}else {
				result.setMsg("删除失败!");
				result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error(e.getMessage(), e);
		}
		return result;
	}
	
	/**
	 * 获取主播信息
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "getAnchorById")
	@ResponseBody
	public Object getAnchorById(BLiver liveAnchor) {
		BLiver anchor = liveAnchorService.selectByPrimaryKey(liveAnchor.getId());
		return anchor;
	}
	
	/**
	 * 获取主播信息
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "getAnchorInfo")
	@ResponseBody
	public Object getAnchorInfo(BLiver liveAnchor) {
		BLiver anchor = liveAnchorService.selectBLiver(liveAnchor);
		return anchor;
	}
	
	/**
	 * 获取用户等级信息
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "getLiverRankInfo")
	@ResponseBody
	public Object getLiverRankInfo(BursEarningsRank earningsRank) {
		BursEarningsRank bursEarningsRank = earningsRankService.selectByBean(earningsRank);
		if(bursEarningsRank==null){
			bursEarningsRank=new BursEarningsRank();
		}
		return bursEarningsRank;
	}
	/**
	 * 
	 * 方法描述：验证手机是否已注册主播
	 * 创建人： huang'tao
	 * 创建时间：2016-8-10下午7:58:55
	 * @return
	 */
	@RequestMapping(value = "checkAccount")
	@ResponseBody
	public Object checkAccount(BLiver anchor){
		String result="";
		//用户类型 1 主播 2 普通用户
		anchor.setUtype(LiveConstant.UTYPE_ANCHOR);
		Long count = liveAnchorService.count(anchor);
		if(count==0){
			result="true";
		}else {
			result= "【"+anchor.getPhone()+"】已被注册,不可重复添加";
		}
		return result;
	}
	
	/*
	 * 获取手机号码对应的寻蜜鸟会员信息,直播观众信息
	 */
	@RequestMapping(value = "/init/isConflict")
	@ResponseBody
	public Object getUrsInfo(String phone) {
		Map<String,Object> ursInfoMap = new HashMap<String,Object>();
		String role = "0";// 0 无寻蜜鸟会员和直播观众信息  1 存在寻蜜鸟会员信息，不存在直播观众信息  2存在会员和直播观众信息
//		boolean isConflict = true;	//true -冲突;false -不冲突
		BLiver liver=new BLiver();
		liver.setPhone(phone);
		BLiver liverInfo = liveAnchorService.getUrsDetailInfo(liver);
		//用户类型 1 主播 2 普通用户
		liver.setUtype(LiveConstant.UTYPE_COMMON);
		BLiver viewer = liveAnchorService.selectBLiver(liver);
		
		if(liverInfo==null){
//			isConflict=false;
			role="0";
		}else if(liverInfo != null && viewer == null){
			role="1";
			ursInfoMap.put("liverInfo", liverInfo);
		}else if(liverInfo != null && viewer !=null){
			role="2";
			ursInfoMap.put("liverInfo", liverInfo);
			ursInfoMap.put("viewer", viewer);
		}
		
//		ursInfoMap.put("isConflict", isConflict);
		ursInfoMap.put("role", role);
		return ursInfoMap;
	}
	
	/**
	 * 
	 * 方法描述：跳转到批量修改主播分成页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-1-13下午5:57:47 <br/>
	 * @return
	 */
	@RequestMapping(value="updateBatchRatio/init")
	@RequestToken(createToken=true,tokenName="anchorRatioEditToken")
	public String updateBatchRatioInit(@RequestParam("ids") String ids,Model model){
		model.addAttribute("ids", ids);
		return "live_anchor/anchorRatioEdit";
	}
	
	/**
	 * 
	 * 方法描述：批量修改主播分成比例 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-1-13下午6:13:18 <br/>
	 * @param liver
	 * @return
	 */
	@RequestMapping(value="updateBatchRatio")
	@RequestToken(removeToken=true,tokenName="anchorRatioEditToken")
	@ResponseBody
	public Object updateBatchRatio(AnchorRatioBean anchorRatioBean){
		Resultable result=new Resultable();
		try {
			result=liveAnchorService.updateBatchRatio(anchorRatioBean);
		} catch (Exception e) {
			this.log.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMsg("操作失败!");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：跳转到批量修改主播机器人粉丝页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-1-13下午5:57:47 <br/>
	 * @return
	 */
	@RequestMapping(value="configureFansBatch/init")
	@RequestToken(createToken=true,tokenName="fansEditToken")
	public String configureFansBatchInit(@RequestParam("ids") String ids,@RequestParam("uids") String uids,Model model){
		model.addAttribute("ids", ids);
		model.addAttribute("uids", uids);
		return "live_anchor/anchorFansEdit";
	}
	
	/**
	 * 
	 * 方法描述：批量修改主播粉丝配置 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-1-13下午6:13:18 <br/>
	 * @param liver
	 * @return
	 */
	@RequestMapping(value="configureFansBatch")
	@RequestToken(removeToken=true,tokenName="fansEditToken")
	@ResponseBody
	public Object configureFansBatch(FansConfigureRequest configureRequest){
		Resultable result=new Resultable();
		try {
			this.log.info(configureRequest.toString());
			liveAnchorService.saveFansConf(configureRequest);
			result.setSuccess(true);
			result.setMsg("操作成功!");
		} catch (Exception e) {
			this.log.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMsg("操作失败!");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：导出主播信息
	 * 创建人： huang'tao
	 * 创建时间：2016-8-8下午2:57:30
	 */
	@RequestMapping(value="export")
	public void export(BLiver anchor,HttpServletRequest request,HttpServletResponse response){
		anchor.setOrder(PageConstant.NOT_ORDER);
		anchor.setLimit(PageConstant.PAGE_LIMIT_NO);
		Map<String,Object> params=new HashMap<String,Object>();
		anchor.setUtype(LiveConstant.UTYPE_ANCHOR);
		params.put("list", liveAnchorService.getList(anchor));
		doExport(request,response,"live_anchor/anchor.xls",params);
		
	}
	
	/**
	 * 批量添加主主播页面初始化
	 * 创建人：ChenBo
	 * 创建时间：2017-1-22下午2:06:44
	 * @return
	 */
	@RequestMapping(value="add/import/init")
	public ModelAndView importProductInit(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("live_anchor/importAnchor");
		return mv;
	}
	
	/**
	 * 导入模版下载
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="add/import/downloadTemplate")
	public void downloadTemplate(HttpServletRequest request,HttpServletResponse response) {
		super.downloadTemplate(request, response, "live_anchor/importAnchorTemplate.xls", "批量创建模版");
	}
	
	/**
	 * 导入
	 * @throws IOException 
	 */
	@RequestMapping(value = "add/import/importData",method=RequestMethod.POST)
	public void importData(@RequestParam("importData")MultipartFile multipartFile,HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter printWriter = response.getWriter();
		try {
			List<BLiver> faileList = new ArrayList<BLiver>();
			printWriter.println(JSON.toJSON(liveAnchorService.importAnchor(multipartFile,faileList)));
			exportLocalFile(request,response,faileList);
		} catch (Exception e) {
			printWriter.println(JSON.toJSON(new ResultFile(FastfdsConstant.FILE_UPLOAD_FASTDFS_FAILURE, "导入失败,请重新导入")));
			log.error("文件上传失败", e);
		}
	}
	
	/**
	 * 
	 * 方法描述：初始化添加失败文档下载数据
	 * @return
	 */
	@RequestMapping(value = "add/initFailInfo",method=RequestMethod.POST)
	@ResponseBody
	public Object initFailingSerial() {
		List<Map<String,String>> failList = new ArrayList<Map<String,String>>();
		String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		path = path.substring(0,path.indexOf("WEB-INF"));
		path=path+"files";
		mkFileDir(path);
		traverseFolder(path,failList);
		reversemap(failList);
		return failList;
	}
	
	/**
	 * 
	 * 方法描述：将导入失败的用户生成在服务器本地excel文档<br/>
	 * 创建人： ChenBo <br/>
	 * 创建时间：2017年1月24日上午9:58:40 <br/>
	 * @param request
	 * @param response
	 * @param dataList
	 * @return
	 */
	private boolean exportLocalFile(HttpServletRequest request, HttpServletResponse response,List<BLiver> dataList){
		if(dataList==null || dataList.isEmpty()){
			return false;
		}

		String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		path = path.substring(0,path.indexOf("WEB-INF"));
		String exportPath = path +"files";
		mkFileDir(exportPath);
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("list", dataList);
		doLocal(request,exportPath+"/Anchor_"+DateUtil.formatDate(new Date(), DateUtil.YMDHMS)+".xls",path+"WEB-INF/xls/live_anchor/anchor.xls",params);
		return true;
	}
		
	/**
	 * 
	 * 方法描述：遍历所有错误文件<br/>
	 * 创建人： ChenBo <br/>
	 * 创建时间：2017年1月23日下午3:26:44 <br/>
	 * @param dirPath
	 * @param fileList
	 */
	public void traverseFolder(String dirPath,List<Map<String,String>> fileList) {
        File dirfile = new File(dirPath);
        if (dirfile.exists()) {
            File[] files = dirfile.listFiles();
            if (files.length == 0) {
                System.out.println("文件夹是空的!");
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        traverseFolder(file2.getAbsolutePath(),fileList);
                    } else {
                        if(file2.getName().startsWith("Anchor_")){
                        	Map<String,String> map = new HashMap<String,String>();
                        	map.put("name", file2.getName());
                        	String path = file2.getAbsolutePath();
                        	int s = path.indexOf("files");
                        	path = path.substring(s, path.length());
                        	path = path.replaceAll("\\\\", "/");  
                        	map.put("path", path);
                        	fileList.add(map);
                        }
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }
	
	/**
	 *   
	 * 方法描述：对List<Map>排序正序 <br/>
	 * 创建人： ChenBo <br/>
	 * 创建时间：2017年1月24日上午10:07:45 <br/>
	 * @param list
	 */
    public static void sortmap(List<Map<String,String>> list){
        Collections.sort(list, new Comparator<Map<String,String>>(){  
               public int compare(Map<String,String> o1,Map<String,String> o2){  
            	   String str1=o1.get("name");
                   String str2=o2.get("name");
                   str1=str1==null?"":str1;
                   str2=str2==null?"":str2;
                   return str1.compareTo(str2);
                  }
        }); 
	}
    
	/**
	 *   
	 * 方法描述：对List<Map>排序倒序 <br/>
	 * 创建人： ChenBo <br/>
	 * 创建时间：2017年1月24日上午10:07:45 <br/>
	 * @param list
	 */
    public static void reversemap(List<Map<String,String>> list){
    	sortmap(list);
    	Collections.reverse(list); 
    }
    
    public static void mkFileDir(String dirPath){
    	File file = new File(dirPath);
    	if(!file.exists()||!file.isDirectory()){
    		file.mkdir();
    	}
    }
    
    /**
     * 
     * 方法描述：跳转到批量添加主播相册页面 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-4-11下午2:47:11 <br/>
     * @return
     */
    @RequestMapping(value="anchorImageAddBatch/init")
    public String anchorImageAddBatchInit(){
    	
    	return "live_anchor/anchorImageAddBatch";
    }
    
    
    /**
     * 
     * 方法描述：跳转到绑定主播银行卡列表 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-4-14上午11:22:33 <br/>
     * @param request
     * @param card
     * @return
     */
	@RequestMapping(value = "/bindCardInit/init")
	@ResponseBody
	public ModelAndView bindCardInit(HttpServletRequest request, Card card,@RequestParam(value="nickname") String nickname,@RequestParam(value="uid") String uid) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("live_anchor/anchorCard/bindAnchorCardInit");
		mv.addObject("nickname", nickname);
		mv.addObject("uid", uid);
		mv.addObject("card", card);
		return mv;
	}
	
	/**
	 * @author dong'jietao
	 * @date 2015年4月30日 下午2:14:43
	 * @TODO 获取商家的银行卡list
	 * @param request
	 * @param card
	 * @return
	 */
	@RequestMapping(value = "/bindCard/bindCardList")
	@ResponseBody
	public Object bindCardList(HttpServletRequest request, Card card) {
		Pageable<Card> pageable = new Pageable<Card>(card);
		pageable = liveAnchorService.getCardList(card);
		this.log.info("anchor/manage-->list pageable=" + pageable);
		return pageable;
	}
	
	/**
	 * 
	 * 方法描述：跳转到添加主播银行卡页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-4-14上午11:22:19 <br/>
	 * @param request
	 * @param card
	 * @return
	 */
	@RequestMapping(value = "/bindCardInit/addCardInit")
	@ResponseBody
	public ModelAndView addCardInit(HttpServletRequest request, Card card,@RequestParam(value="uid") String uid) {
		ModelAndView mv = new ModelAndView();
		BLiver liver=new BLiver();
		liver.setUid(new Integer(uid));
		BLiver anchorInfo = liveAnchorService.selectBLiver(liver);
		mv.setViewName("live_anchor/anchorCard/addAnchorCardInit");
		mv.addObject("card", card);
		mv.addObject("anchorInfo", anchorInfo);
		return mv;
	}
	
	/**
	 * 
	 * 方法描述：添加主播银行卡信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-4-14下午2:34:12 <br/>
	 * @param request
	 * @param card
	 * @return
	 */
	@RequestLogging(name = "添加主播银行卡")
	@RequestMapping(value = "/bindCardInit/addCard")
	@ResponseBody
	public Object addCard(HttpServletRequest request, Card card) {
		Resultable resultable = null;
		try {
			resultable = liveAnchorService.addCard(card);
			String[] s = { "主播", card.getSellername(), "银行卡增加", "增加" };
			liveAnchorService.fireLoginEvent(s);
		} catch (Exception e) {
			resultable.setSuccess(false);
			resultable.setMsg("操作失败!");
			liveAnchorService.fireLoginEvent(StringUtils.addStrToStrArray(((ApplicationException) e).getLogInfo(), new ApplicationException("添加主播银行卡异常", e, new Object[] { request, card }).getMessage()), 0);
		}
		return resultable;
	}
    
}
