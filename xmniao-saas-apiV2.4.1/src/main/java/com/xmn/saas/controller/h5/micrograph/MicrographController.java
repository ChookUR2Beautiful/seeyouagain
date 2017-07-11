package com.xmn.saas.controller.h5.micrograph;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xmn.saas.base.AbstractExController;
import com.xmn.saas.base.GlobalConfig;
import com.xmn.saas.base.Response;
import com.xmn.saas.base.ResponseCode;
import com.xmn.saas.controller.api.v1.common.vo.ShareRequest;
import com.xmn.saas.controller.h5.micrograph.vo.ListRequest;
import com.xmn.saas.entity.celebrity.Tag;
import com.xmn.saas.entity.image.Image;
import com.xmn.saas.entity.micrograph.MicrographModuleShare;
import com.xmn.saas.entity.micrograph.MicrographPage;
import com.xmn.saas.entity.micrograph.MicrographSearch;
import com.xmn.saas.entity.micrograph.MicrographTemplate;
import com.xmn.saas.entity.shop.SellerInfo;
import com.xmn.saas.exception.SaasException;
import com.xmn.saas.service.common.CommonService;
import com.xmn.saas.service.image.ImageService;
import com.xmn.saas.service.micrograph.MicrographService;

@RequestMapping("h5/micrograph")
@Controller("h5-micrograph-controller")
public class MicrographController extends AbstractExController{
	
    // 初始化日志类
    private final Logger logger = LoggerFactory.getLogger(MicrographController.class);
	
	@Autowired
	private MicrographService micrographService;
	
	@Autowired
    private ImageService imageService;
	
	@Autowired
	private GlobalConfig globalConfig;
	
	@Autowired
	private CommonService commonService;
	
	/**
	 * 
	 * 方法描述：列表页面
	 * 创建人：jianming  
	 * 创建时间：2016年11月30日 下午6:10:49   
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "list_init", method = RequestMethod.GET)
	@ResponseBody
	private ModelAndView listInit(ListRequest listRequest) throws IOException{
		logger.info("加载页面列表"+listRequest.getPageIndex());
		logger.info("[测试]默认编码"+listRequest.getSearchName());
		ModelAndView modelAndView = new ModelAndView("micrograph/list");
		String searchName=null;
		try {
			if(StringUtils.isNotBlank(listRequest.getSearchName())){
				searchName=StringUtils.trim(listRequest.getSearchName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelAndView.addObject("searchName",searchName);
		modelAndView.addObject("serialType", listRequest.getSerialType());
		modelAndView.addObject("tag",listRequest.getTag());
		modelAndView.addObject("imageHost",globalConfig.getImageHost());
		List<Tag> list=micrographService.getTagsBySerial();
		modelAndView.addObject("tags",list);
		List<MicrographSearch> micrographSearch=micrographService.getMicrographSearch(this.getSellerId());
		modelAndView.addObject("micrographSearch",micrographSearch);
		return modelAndView;
	}
	

	/**
	 * 
	 * 方法描述：加载列表
	 * 创建人：jianming  
	 * 创建时间：2016年11月30日 下午4:44:52   
	 * @param pageSize
	 * @param pageIndex
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(ListRequest listRequest) throws IOException{
		logger.info("[加载微图助力列表]searchName="+listRequest.getSearchName());
		MicrographSearch searchModel = listRequest.convertRequestToBean();
		searchModel.setSellerid(this.getSellerId());
		List<MicrographTemplate> micrographs = micrographService.list(searchModel);
		return new Response(ResponseCode.SUCCESS, "成功",micrographs);
	}
	
	
	/**
	 * 
	 * 方法描述：关键字模糊搜索
	 * 创建人：jianming  
	 * 创建时间：2016年12月1日 下午2:00:22   
	 * @return
	 */
	@RequestMapping(value = "search_like", method = RequestMethod.POST)
	@ResponseBody
	public Object searchLike(@RequestParam("name")String name){
		logger.info("[加载微图助力模糊搜索列表]searchName="+name);
		name=StringUtils.trim(name);
		List<Map<String,String>> searchLike  =micrographService.searchLike(name);
		return new Response(ResponseCode.SUCCESS, "成功",searchLike);
	}
	
	
	/**
	 * 
	 * 方法描述：清楚搜索记录
	 * 创建人：jianming  
	 * 创建时间：2016年12月2日 上午10:10:49   
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "clear_search", method = RequestMethod.POST)
	@ResponseBody
	public Object clearSearch() throws IOException{
		logger.info("[清除微图助力模糊搜索]sellerid="+this.getSellerId());
		micrographService.clearSearch(this.getSellerId());
		return new Response(ResponseCode.SUCCESS, "成功");
	}
	
	
	
	/**
	 * 
	 * 方法描述：编辑页面
	 * 创建人：jianming  
	 * 创建时间：2016年12月2日 下午2:46:02   
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "input")
	@ResponseBody
	public Object pageList(@RequestParam(required=true)Integer id,HttpServletRequest request){
		logger.info("[加载微图助力模板编辑]id="+id);
		ModelAndView modelAndView = new ModelAndView("micrograph/input");
		List<MicrographPage> micrographPage = micrographService.pageList(id);
		modelAndView.addObject("micrographPage",micrographPage);
		modelAndView.addObject("imageHost",globalConfig.getImageHost());
		modelAndView.addObject("sessionToken",this.getCookieToken());
	    StringBuffer url = request.getRequestURL();  
	    String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();  
		modelAndView.addObject("tempContextUrl",tempContextUrl);
		return modelAndView;
	}
	
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	@ResponseBody
	public  Object upload(@RequestParam(value="file",required=false) MultipartFile file,HttpServletRequest httpServletRequest,HttpServletResponse response){
		logger.info("[调用微图助力图片上传接口 ]");
		response.setContentType("application/json;charset=UTF-8");
        try {
        	// 获取上传的文件
            MultipartHttpServletRequest request = (MultipartHttpServletRequest) httpServletRequest;
            MultiValueMap<String, MultipartFile> multiFileMap = request.getMultiFileMap();
            List<MultipartFile> mfiles = multiFileMap.get("image");
        	
            // 判断是否上传了文件
            if (file == null &&mfiles==null) {
            	logger.info("[未上传图片!]");
            }
            if(mfiles!=null&&mfiles.size()>0){
            	file=mfiles.get(0);
            }
           
                // 图片大小不能超过2MB
                if (file.getSize() > 2097152*5) {     // 1024*1024*2 = 2097152
                	return new Response(ResponseCode.FAILURE, "单个文件不能超过10MB");
                    
                }
                // 判断文件是否为图片
                if (!file.getContentType().matches("image/\\w+")) {
                	logger.info("[文件格式有误]fileName"+file.getName());
                	 return new Response(ResponseCode.FAILURE, "请选择图片文件");
                   
                }
            

            // 将图片上传到文件服务器
                List<MultipartFile> files=new ArrayList<MultipartFile>();
                files.add(file);
            final List<Image> imageList = imageService.upload(files);
          return  new Response(ResponseCode.SUCCESS, "图片上传成功!", imageList.get(0));

        } catch (SaasException e) {
           return new Response(ResponseCode.FAILURE,e.getMessage());
        } catch (Exception e) {
            logger.error("图片上传接口出现异常!",e);
        }
        return new Response(ResponseCode.FAILURE,"网络不稳定,请稍后再试!");
	}
	
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	private Object save(@RequestParam(required=true) String model) throws IOException{
		logger.info("[保存用户分享记录], 请求参数:[sellerid="+this.getSellerId()+" json数据="+ model.toString() + "]");
		try {
			ObjectMapper mapper=new ObjectMapper();
			List<MicrographModuleShare> list =  mapper.readValue(model, new TypeReference<List<MicrographModuleShare>>() {});   
			Integer pageId=micrographService.saveShare(list,this.getSellerId());
			ShareRequest reqParams = new ShareRequest();
			reqParams.setId(pageId);// 活动id
			reqParams.setTitle("测试标题");// 标题
			reqParams.setType(12);// 现金抵用券类型
			// 请求获取分享的地址
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("id",pageId);
			return  new Response(ResponseCode.SUCCESS, "分享数据创建成功!",map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ResponseCode.FAILURE,"分享数据创建成功失败!");
		
	}
	

	@RequestMapping(value = "share", method = RequestMethod.POST)
	@ResponseBody
	private Object share(@RequestParam(value="id",required=true)Integer id){
		logger.info("[调用生成微图助力分享接口]id="+id);
		if(id==null){
			return new Response(ResponseCode.FAILURE,"分享id为空!");
		}
		SellerInfo sellerInfo=micrographService.getSellerMsg(id);
		Map<String,String> map=new HashMap<String, String>();
		String url=globalConfig.getShareUrl()+globalConfig.getMicrographShareUrl()+"?id="+id+"#flag"+"&id="+id+"&title="+sellerInfo.getSellerName()+"&type=0&desc="+sellerInfo.getAddress();
		map.put("url", url);
		return  new Response(ResponseCode.SUCCESS, "生成分享地址成功!",map);
		
	}
}
