package com.xmniao.xmn.core.api.controller.xmer;

import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.xmer.service.XmerHomeService;

/**
 * 
* 项目名称：xmnService   
* 类名称：XmerHomeApi   
* 类描述：寻蜜客主页信息接口   
* 创建人：liuzhihao   
* 创建时间：2016年5月21日 下午4:01:45   
* @version    
*
 */
@Controller
public class XmerHomeApi implements BaseVControlInf{

	//日志
	private Logger log = Logger.getLogger(XmerHomeApi.class);
	
	//注入寻蜜客主页Service
	@Autowired
	private XmerHomeService xmerHomeService;
	
	//注入验证
	@Autowired
	private Validator validator;
	
	@RequestMapping(value="xmerHome",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object xmerHome(BaseRequest baseRequest){
		//验证参数
		List<ConstraintViolation> param = validator.validate(baseRequest);
		if(param.size() >0 && param != null){
			log.info("提交的数据不能为空"+param.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不能为空，请检查提交的数据");
		}
		return versionControl(baseRequest.getApiversion(),baseRequest);
	}

	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1: return versionControlOne(object);
		case 2: return versionControlOne(object);
		default : return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}

	private Object versionControlOne(Object object) {
		BaseRequest baseRequest = (BaseRequest) object;
		return xmerHomeService.xmerHome(baseRequest);
	}
	
	
	@RequestMapping(value = "/xmkintro", method = RequestMethod.GET)
    public String sayHello() {
		return "xmer/xmkintro";
    }  
	
	@RequestMapping(value = "/excellentXmk", method = RequestMethod.GET)
    public String excellentXmk() {  
		return "xmer/college";
    } 
	
	@RequestMapping(value = "/relieveRelative", method = RequestMethod.GET)
    public String relieveRelative() {  
		return "xmer/relieve_relative";
    }  
	
	@RequestMapping(value="xmkintrotwo",method=RequestMethod.GET)
	public String xmerIntroTwo(){
		return "xmer/xmkintrotwo";
	}
	
	@RequestMapping(value="agreement",method=RequestMethod.GET)
	public String agreaMent(){
		return "xmer/agreement";
	}
	
	//首次成为寻蜜客时，进入寻蜜客介绍页
	@RequestMapping(value="xmerIndex",method=RequestMethod.GET)
	public String xmerIndex(){
		return "xmer/xmkIntroduction";
	}
	
	@RequestMapping(value="xmerHomeTest")
	public String xmerHomeTest(Model model){
		BaseRequest request = new BaseRequest();
		request.setApiversion(1);
		request.setAppversion("1.0.23");
		request.setSystemversion("ios9.3.21");
		request.setSessiontoken("49477948c49bd3599d1514790a909caa");
		model.addAttribute("data", xmerHome(request));
		return "xmertest";
	}
	
	@RequestMapping(value="/whatisYz",method=RequestMethod.GET)
	public String whatisYz(){
		return "xmer/whatisyizhan";
	}
	
	//如何成为优秀寻蜜客
	@RequestMapping(value="/xmkguide",method=RequestMethod.GET)
	public String xmerGuide(){
		return "guide/index";
	}	
	@RequestMapping(value="/xmkdev",method=RequestMethod.GET)
	public String xmkDev(){
		return "guide/xmkdev";
	}
	
	@RequestMapping(value="/xmkfindconsumer",method=RequestMethod.GET)
	public String xmkFindConsumer(){
		return "guide/xmkfindconsumer";
	}
	
	@RequestMapping(value="/xmkhowsignconsumer",method=RequestMethod.GET)
	public String xmkHowSignConsumer(){
		return "guide/xmkhowsignconsumer";
	}
	
	@RequestMapping(value="/xmksignprogress",method=RequestMethod.GET)
	public String xmkSignProgress(){
		return "guide/xmksignprogress";
	}
	
	@RequestMapping(value="/xmkyunying",method=RequestMethod.GET)
	public String xmkYunYing(){
		return "guide/xmkyunying";
	}
	
	@RequestMapping(value="/booklib",method=RequestMethod.GET)
	public String xmkBookLib(){
		return "guide/booklib";
	}
	
	@RequestMapping(value="/xmkmovie",method=RequestMethod.GET)
	public String xmkMovie(){
		return "guide/xmkmovie";
	}
	
	@RequestMapping(value="/movieOne",method=RequestMethod.GET)
	public String xmkMovieOne(){
		return "guide/movie_1";
	}
	
	@RequestMapping(value="/movieThree",method=RequestMethod.GET)
	public String xmkMovieThree(){
		return "guide/movie_3";
	}
	
	@RequestMapping(value="/movieTwo",method=RequestMethod.GET)
	public String xmkMovieTwo(){
		return "guide/movie_2";
	}
	
	@RequestMapping(value="/feedback",method=RequestMethod.GET)
	public String xmkFeedBack(){
		return "guide/feedback";
	}
	
	/**
	 * 协议
	 */
	@RequestMapping(value="/serviceagreement",method=RequestMethod.GET)
	public String xmkServiceaGreement(){
		return "guide/serviceagreement";
	}
	
	/**
	 * 
	 * @Title:xmkIntroduction
	 * @Description：非寻蜜客用户进入 寻蜜客，显示寻蜜客介绍H5页面
	 * @return String 寻蜜客介绍H5页面
	 * 2017年5月10日下午2:25:43
	 */
	@RequestMapping(value="xmkIntroduction")
	public String xmkIntroduction(){
		return "xmer/xmkIntroduction";
	}
	
	/**
	 * 
	 * @Title:xmkCollege
	 * @Description:寻蜜客商学院H5页面
	 * @return String
	 * 2017年5月11日上午10:56:17
	 */
	@RequestMapping(value="xmkCollege")
	public String xmkCollege(){
		return "xmer/college";
	}
}
