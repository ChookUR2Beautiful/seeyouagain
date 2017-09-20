package com.xmniao.xmn.core.jobmanage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.jobmanage.dao.UserCVDao;
import com.xmniao.xmn.core.jobmanage.entity.RecruitTag;
import com.xmniao.xmn.core.jobmanage.entity.UserCV;

/**
 *@ClassName:BXmerService
 *@Description:寻蜜客成员管理service层
 *@author hls
 *@date:2016年5月25日上午11:14:45
 */
@Service
public class UserCVService extends BaseService<UserCV>{ 
	@Autowired
	private UserCVDao userCVDao;
	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return userCVDao;
	}
	/**
	 * @Title:selectUserCVInfoList
	 * @Description:查询用户简历信息列表
	 * @param userCV
	 * @return List<UserCV>
	 * @throw
	 */
	public List<UserCV> selectUserCVInfoList(UserCV userCV){
		
		List<UserCV> userCVList = new ArrayList<UserCV>();
		userCVList = userCVDao.selectUserCVInfoList(userCV);
		int id = 0;
		List<RecruitTag> iwantList = null;
		List<RecruitTag> doingList = null;
		List<RecruitTag> experienceList = null;
		List<RecruitTag> selfAssessmentList = null;
		Map<String,Object> map = new HashMap<>();
		//想找
		for (int i = 0; i < userCVList.size(); i++) {
			id = userCVList.get(i).getId();
			map.put("resumeId", id);
			map.put("tagType", 3);//我要找
			iwantList = userCVDao.selectTanameByID(map);
			StringBuilder iwant = new StringBuilder();
			if(iwantList!=null && !iwantList.isEmpty()){
				String name = null;
				for(int j = 0; j < iwantList.size(); j++){
					name = iwantList.get(j).getName();
					iwant = iwant.append(name + ";");
				}
			}
			userCVList.get(i).setIwant(iwant.toString());
		}
		//做过
		for (int i = 0; i < userCVList.size(); i++) {
			id = userCVList.get(i).getId();
			map.put("resumeId", id);
			map.put("tagType", 2);//我做过
			doingList = userCVDao.selectTanameByID(map);
			StringBuilder doing = new StringBuilder();
			if(doingList!=null && !doingList.isEmpty()){
				String name = null;
				for(int j = 0; j < iwantList.size(); j++){
					name = doingList.get(j).getName();
					doing = doing.append(name + ";");
				}
			}
			userCVList.get(i).setDoing(doing.toString());
		}
		//培训过
		for (int i = 0; i < userCVList.size(); i++) {
			id = userCVList.get(i).getId();
			map.put("resumeId", id);
			map.put("tagType", 4);//培训经历
			experienceList = userCVDao.selectTanameByID(map);
			StringBuilder experience = new StringBuilder();
			if(experienceList!=null && !experienceList.isEmpty()){
				String name = null;
				for(int j = 0; j < experienceList.size(); j++){
					name = experienceList.get(j).getName();
					experience = experience.append(name + ";");
				}
			}
			userCVList.get(i).setExperience(experience.toString());
		}
		//自我评价
		for (int i = 0; i < userCVList.size(); i++) {
			id = userCVList.get(i).getId();
			map.put("resumeId", id);
			map.put("tagType", 5);//自我评价
			selfAssessmentList = userCVDao.selectTanameByID(map);
			StringBuilder selfAssessment = new StringBuilder();
			if(selfAssessmentList!=null && !selfAssessmentList.isEmpty()){
				String name = null;
				for(int j = 0; j < selfAssessmentList.size(); j++){
					name = selfAssessmentList.get(j).getName();
					selfAssessment = selfAssessment.append(name + ";");
				}
			}
			userCVList.get(i).setSelfAssessment(selfAssessment.toString());
		}
		
		return userCVList;
	}
	/**
	 * @Title:userCVInfoCount
	 * @Description:查询用户简历信息列表总条数
	 * @param userCV
	 * @return long
	 * @throw
	 */
	public long userCVInfoCount(UserCV userCV){
		return userCVDao.userCVInfoCount(userCV);
	}
	
	/**
	 * @Title:exportXmerList
	 * @Description:导出用户简历信息列表
	 * @param userCV
	 * @return List<UserCV>
	 * @throw
	 */
	public List<UserCV> exportUserCVList(UserCV userCV) {
		List<UserCV> userCVList = new ArrayList<UserCV>();
		userCVList = userCVDao.exportUserCVList(userCV);
		int id = 0;
		List<RecruitTag> iwantList = null;
		List<RecruitTag> doingList = null;
		List<RecruitTag> experienceList = null;
		List<RecruitTag> selfAssessmentList = null;
		Map<String,Object> map = new HashMap<>();
		//想找
		for (int i = 0; i < userCVList.size(); i++) {
			id = userCVList.get(i).getId();
			map.put("resumeId", id);
			map.put("tagType", 3);//我要找
			iwantList = userCVDao.selectTanameByID(map);
			StringBuilder iwant = new StringBuilder();
			if(iwantList!=null && !iwantList.isEmpty()){
				String name = null;
				for(int j = 0; j < iwantList.size(); j++){
					name = iwantList.get(j).getName();
					iwant = iwant.append(name + ";");
				}
			}
			userCVList.get(i).setIwant(iwant.toString());
		}
		//做过
		for (int i = 0; i < userCVList.size(); i++) {
			id = userCVList.get(i).getId();
			map.put("resumeId", id);
			map.put("tagType", 2);//我做过
			doingList = userCVDao.selectTanameByID(map);
			StringBuilder doing = new StringBuilder();
			if(doingList!=null && !doingList.isEmpty()){
				String name = null;
				for(int j = 0; j < iwantList.size(); j++){
					name = doingList.get(j).getName();
					doing = doing.append(name + ";");
				}
			}
			userCVList.get(i).setDoing(doing.toString());
		}
		//培训过
		for (int i = 0; i < userCVList.size(); i++) {
			id = userCVList.get(i).getId();
			map.put("resumeId", id);
			map.put("tagType", 4);//培训经历
			experienceList = userCVDao.selectTanameByID(map);
			StringBuilder experience = new StringBuilder();
			if(experienceList!=null && !experienceList.isEmpty()){
				String name = null;
				for(int j = 0; j < experienceList.size(); j++){
					name = experienceList.get(j).getName();
					experience = experience.append(name + ";");
				}
			}
			userCVList.get(i).setExperience(experience.toString());
		}
		//自我评价
		for (int i = 0; i < userCVList.size(); i++) {
			id = userCVList.get(i).getId();
			map.put("resumeId", id);
			map.put("tagType", 5);//自我评价
			selfAssessmentList = userCVDao.selectTanameByID(map);
			StringBuilder selfAssessment = new StringBuilder();
			if(selfAssessmentList!=null && !selfAssessmentList.isEmpty()){
				String name = null;
				for(int j = 0; j < selfAssessmentList.size(); j++){
					name = selfAssessmentList.get(j).getName();
					selfAssessment = selfAssessment.append(name + ";");
				}
			}
			userCVList.get(i).setSelfAssessment(selfAssessment.toString());
		}
		
		return userCVList;
	}
	/**
	 * 根据用户简历id批量删除简历信息
	 * @Title:deleteUserCVById
	 * @Description:TODO
	 * @param CuserCV void
	 * @throw
	 */
	public void deleteUserCVById(UserCV CuserCV) {
		try{
			if(null != CuserCV.getIds() && !"".equals(CuserCV.getIds())){//id存在才删除
				String[] ids = CuserCV.getIds().split(",");
				userCVDao.deleteUserCVById(ids);
			}
		}catch(Exception e){
			this.log.error("删除用户简历异常：", e);
			throw new ApplicationException("删除用户简历异常",e, CuserCV);
		}
	}
//	public static void main(String[] args) {
//		Map<String,Object> m1 = new HashMap<String,Object>();
//		m1.put("tagType", 2);
//		m1.put("tagType", 3);
//		System.out.println(m1.get("tagType"));
//	}
}
