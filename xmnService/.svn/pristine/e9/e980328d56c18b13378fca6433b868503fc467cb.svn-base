package com.xmniao.xmn.core.match.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.util.dataSource.DataSource;

@Repository
public interface VstarSignUpDao {
	
	/**查询大赛报名所有的省份
	 * @return
	 */
	@DataSource("joint")
	public List<Map<Object,Object>> findVstarProvices();
	
	/**根据省份查询所有的大赛城市
	 * @param provice
	 * @return
	 */
	@DataSource("joint")
	public List<Map<Object,Object>> findVstarCitys(Integer provice);
	
	/**根据城市查询所有的地区
	 * @param cityId
	 * @return
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> findAllAreaByCity(Integer cityId);
	
	/**根据区域Id查询地区信息
	 * @param areaId
	 * @return
	 */
	@DataSource("joint")
	public Map<Object, Object> findVstarDivison(Integer areaId);
	
	/**查询大赛报名的设置
	 * @return
	 */
	@DataSource("joint")
	public Map<Object, Object> findPlaySetting();
	
	/**插入大赛报名表
	 * @param vstarMap
	 * @return
	 */
	@DataSource("joint")
	public Integer insertVstarEntroll(Map<Object, Object> vstarMap);
	
	/**插入大赛报名图片
	 * @param vstarImg
	 */
	@DataSource("joint")
	public void insertVstarImg(Map<Object, Object> vstarImg);
	
	/**插入大赛报名详情表
	 * @param param
	 */
	@DataSource("joint")
	public void insertVstarPlayInfo(Map<Object, Object> param);
	
	
	/**根据主播id统计直播场次
	 * @param anchorId
	 * @return
	 */
	@DataSource("joint")
	public Integer countLiveNum(Integer anchorId);
	
	/**根据uid查询粉丝关注数量
	 * @param uid
	 * @return
	 */
	@DataSource("joint")
	public Integer coutLiveFans(int uid);
	
	/**根据uid查询sass签约商家数量
	 * @param uid
	 * @return
	 */
	@DataSource("joint")
	public Integer countSassSeller(int uid);
	
	/**根据uid判断用户是否报名过
	 * @param uid
	 * @return
	 */
	@DataSource("joint")
	public Integer countVstarEnroll(int uid);

	/**删除用户报名上传图片
	 * @param uid
	 */
	@DataSource("joint")
	public void deleteVstarEnrollImg(int uid);

	/**删除用户报名基础信息
	 * @param uid
	 */
	@DataSource("joint")
	public void deleteVstarEnrollByUid(int uid);

	/**删除用户报名详细信息
	 * @param uid
	 */
	@DataSource("joint")
	public void deleteVstarPlayInfo(int uid);
}
