package com.xmniao.xmn.core.api.controller.live;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.util.ArithUtil;


/**
 * 
*      
* 类名称：UsergradeApi   
* 类描述：   我的等级页面
* 创建人：xiaoxiong   
* 创建时间：2016年9月8日 下午5:40:13   
* 修改人：xiaoxiong   
* 修改时间：2016年9月8日 下午5:40:13   
* 修改备注：   
* @version    
*
 */
@Controller
public class UsergradeApi {
	
	

		/**
		 * 日志
		 */
		private final Logger log = Logger.getLogger(UsergradeApi.class);
		
		/**
		 * 注入验证
		 */
		@Autowired
		private Validator validator;
		
		@Autowired
		private SessionTokenService sessionTokenService;
	
		@Autowired
		private LiveUserDao liveUserDao;
		
		/**
		 * 
		 * @Description: 查询我的等级
		 * @author xiaoxiong
		 * @date 2016年9月8日
		 * @version
		 */
		@RequestMapping(value="/userGrade")
		public String userGrade(BaseRequest request,org.springframework.ui.Model model){
			//验证
			List<ConstraintViolation> result = validator.validate(request);
			if(result.size() >0 && result != null){
				log.info("提交的数据有问题"+result.get(0).getMessage());
				model.addAttribute("info", result.get(0).getMessage());
				return "live/error";
			}
			
			try {
				String uid=sessionTokenService.getStringForValue(request.getSessiontoken())+"";
				if(uid.equals("")||uid.equals("null")){
					model.addAttribute("info", "sessionToken错误或已过期");
					return "live/error";
				}
				
				Map<Object,Object> resultMap=new HashMap<>();
				
				//查询用户信息
				Map<Object,Object> liveMap=liveUserDao.queryLiverInfoByUid(Integer.parseInt(uid));
				if(liveMap==null){
					model.addAttribute("info","没有找到用户信息");
					return "live/error";
				}
				
				String rank_no=liveMap.get("rank_no")==null?"1":liveMap.get("rank_no")+"";//我的等级等级
				
				String current_expe=liveMap.get("current_expe")==null?"0":liveMap.get("current_expe")+"";//当前经验
				String achievement=liveMap.get("achievement")+"";//当前等级头衔
				if(achievement.equals("")||achievement.equals("null")){
					Map<Object, Object> rankMap = liveUserDao.queryMemberRankByExp(Integer.valueOf(current_expe));
					if(rankMap==null)rankMap=new HashMap<>();
					achievement=rankMap.get("member_rank_name")==null?"":rankMap.get("member_rank_name")+"";
				}
				//查询下一级所需经验
				int nextLevleExpe=0;
//				int nowLevleExpe=0;
//				Map<String,Object> levelMap2=liveUserDao.queryLevelByRankNo(Integer.parseInt(rank_no));
//				if(levelMap2.get("upgrade_experience")!=null){
//					nowLevleExpe=Integer.parseInt(current_expe)-Integer.parseInt(levelMap2.get("rank_end_expe")+"");//当前持有经验
////					resultMap.put("nowLevleExpe", nowLevleExpe);//当前等级名称
//				}
				double vader=0D;
				Map<String,Object> levelMap=liveUserDao.queryLevelByRankNo(Integer.parseInt(rank_no)+1);
				if(levelMap!=null){
//					nextLevleExpe=Integer.parseInt(levelMap.get("upgrade_experience")+"")-Integer.parseInt(current_expe);
					//升级所需经验（下一等级所需经验-当前持有经验）
					nextLevleExpe=Integer.parseInt(levelMap.get("rank_start_expe")+"")-Integer.parseInt(current_expe);
					resultMap.put("nextLevleName", levelMap.get("member_rank_name"));//下一级等级名称
					vader=ArithUtil.div(Double.parseDouble(current_expe),Double.parseDouble(levelMap.get("rank_start_expe")+""));
				}
				resultMap.put("nextLevleExpe", nextLevleExpe);//下级所需经验
				
				resultMap.put("achievement", achievement);//当前等级名称
				
				resultMap.put("rankNo", rank_no);	//当前等级
				
				resultMap.put("vader", ArithUtil.mul(vader,100)+"%");	//当前持有经验百分比
				
				resultMap.put("current_expe", current_expe);	//当前持有经验
				
				model.addAttribute("data", resultMap);
				
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("info","未知错误");
				return "live/error";
			}
			
			return "live/usergrade";
		}
		
		
		/**
		 * 
		 * @Description: 跳转到专属图标
		 * @author xiaoxiong
		 * @date 2016年9月9日
		 * @version
		 */
		@RequestMapping(value="/exclusiveicon")
		public String exclusiveicon(){
			
			return "live/exclusiveicon";
		}
		
}
