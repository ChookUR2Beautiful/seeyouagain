package com.xmniao.domain.live;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.xmniao.common.DateUtil;

public class SalaryAdmin {
	
	private final Logger log = Logger.getLogger(SalaryAdmin.class);
	
	private List<LiverBean> fail=new ArrayList<>();	//统计失败集合
	
	/**
	 * 开始时间
	 */
	private Date beginDate;
	
	/**
	 * 结束时间
	 */
	private Date endDate;
	
	public SalaryAdmin(){
		Calendar c = Calendar.getInstance();
		int datenum=c.get(Calendar.DATE);
		/*if(datenum!=1){  //判断是否是一号
			throw new RuntimeException("统计工作日期不是一号");
		}*/
		beginDate = DateUtil.getLastMonFirst();
		endDate=DateUtil.getThisMonFirst();
	}
	
	public SalaryAdmin(Date beginDate,Date endDate){
		this.beginDate=beginDate;
		this.endDate=endDate;
	}
	
	public SalaryAdmin(String updateTime) throws ParseException {
		Integer year = Integer.valueOf(updateTime.substring(0, 4));
		Integer month = Integer.valueOf(updateTime.substring(4, 6));
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.MONTH, month - 1);
		String date1 = DateUtil.dateFormat(c.getTime(), DateUtil.Y_M_D);
		Date beginDate = DateUtil.convertStringToDate(DateUtil.Y_M_D,date1);
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		String date2 = DateUtil.dateFormat(c.getTime(), DateUtil.Y_M_D);
		Date endDate = DateUtil.convertStringToDate(DateUtil.Y_M_D,date2);
		this.beginDate=beginDate;
		this.endDate=endDate;
	}

	/**
	 * 
	 * 方法描述：统计工资
	 * 创建人：jianming  
	 * 创建时间：2017年3月31日 下午5:38:44   
	 * @param liverBeans
	 * @return
	 */
	public List<LiveSalary> accounting(List<LiverBean> liverBeans) {
		List<LiveSalary>  list=new ArrayList<>();
		for (LiverBean liverBean : liverBeans) {
			try {
				if(liverBean.getLiveSalaryData()!=null){
					LiveSalary liveSalary=accountOne(liverBean);
					if(liveSalary.getBaseSalary().compareTo(BigDecimal.valueOf(0))>=1){	//主播工资大于0
						list.add(liveSalary);
					}
				}
			} catch (Exception e) {
				log.error("计算某主播工资时出现异常  主播id为: "+liverBean.getId(),e);
				liverBean.setMsg("计算主播工资时出现异常");
				fail.add(liverBean);
				continue;
			}
		}
		return list;
	}
	
	/**
	 * 
	 * 方法描述：计算某个主播工资
	 * 创建人：jianming  
	 * 创建时间：2017年4月1日 上午11:17:42   
	 * @param liverBean
	 * @return
	 */
	public LiveSalary accountOne(LiverBean liverBean) {
		LiveLever liveLever = liverBean.getLiveLever();
		LiveSalaryData liveSalaryData = liverBean.getLiveSalaryData();
		BigDecimal percent = new BigDecimal(accountPercent(liverBean)).setScale(2,BigDecimal.ROUND_DOWN);	//完成率
		BigDecimal percentAmount=SalaryAdmin.xmnEggToCash(BigDecimal.valueOf(liveSalaryData.getPercentAmount()));  //总获鸟蛋转现金
		BigDecimal salary;
		if(liveSalaryData!=null&&percentAmount.compareTo(liveLever.getLevelIncome())>=0){
			//礼物超出基本工资
			//（级别保底薪酬*完成率）+浮动奖罚*完成率*（80%）
			salary = this.accountSalary1(liveLever.getLevelIncome(),percent,liveLever.getFloatPerformance(),percentAmount);
			
		}else{
			//礼物少于基本工资
			//（级别保底薪酬*完成率）*（80%）
			salary = this.accountSalary2(liveLever.getLevelIncome(),percent);
		}
		LiveSalary liveSalary = new LiveSalary();
		liveSalary.setAnchorId(liverBean.getId());
		liveSalary.setSignType(liverBean.getSignType());
		liveSalary.setStatus(0);
		liveSalary.setBaseSalary(salary);
		liveSalary.setCountTime(DateUtil.dateFormat(beginDate, "yyyyMM"));
		liveSalary.setLevelIncome(liveLever.getLevelIncome());
		liveSalary.setFloatPerformance(liveLever.getFloatPerformance());
		liveSalary.setTopIncome(liveLever.getTopIncome());
		liveSalary.setCreateTime(new Date());
		liveSalary.setUpdateTime(new Date());
		liveSalary.setUid(liverBean.getUid());
		liveSalary.setPercent(percent);
		if(liveSalaryData==null){
			liveSalary.setBalance(BigDecimal.valueOf(0));
			liveSalary.setBout(0);
		}else{
			liveSalary.setBalance(BigDecimal.valueOf(liveSalaryData.getPercentAmount()));
			liveSalary.setBout(liveSalaryData.getBout().intValue());
		}
		return liveSalary;
	}
	
	/**
	 * 
	 * 方法描述：获得礼物不满足最底工资的算法
	 * //礼物少于基本工资
			//（级别保底薪酬*完成率）*（80%）
	 * 创建人：jianming  
	 * 创建时间：2017年4月1日 下午2:12:40   
	 * @param levelIncome
	 * @param percent
	 * @return
	 */
	private BigDecimal accountSalary2(BigDecimal levelIncome, BigDecimal percent) {
		return levelIncome.multiply(percent).multiply(BigDecimal.valueOf(0.8)).setScale(2,BigDecimal.ROUND_UP);
	}

	/**
	 * 
	 * 方法描述：获得礼物满足最底工资的算法
	 * //礼物超出基本工资
			//（级别保底薪酬*完成率）+浮动奖罚*完成率*（80%）
	 * 创建人：jianming  
	 * 创建时间：2017年4月1日 下午1:50:52   
	 * @param levelIncome  保底薪酬
	 * @param percent 完成率
	 * @param floatPerformance 浮动奖罚
	 * @param percentAmount  鸟蛋价值现金
	 * @return
	 */
	private BigDecimal  accountSalary1(BigDecimal levelIncome,BigDecimal percent,BigDecimal floatPerformance, BigDecimal percentAmount){
		BigDecimal multiply = levelIncome.multiply(percent);
		BigDecimal subtract = percentAmount.subtract(levelIncome);
		if(subtract.compareTo(floatPerformance)>0){
			subtract=floatPerformance;
		}
		BigDecimal multiply2 = subtract.multiply(percent).multiply(BigDecimal.valueOf(0.8));
		return multiply.add(multiply2).setScale(2,BigDecimal.ROUND_UP);
	}
	
	/**
	 * 
	 * 方法描述：计算直播完成率
	 * 创建人：jianming  
	 * 创建时间：2017年4月1日 上午11:48:39   
	 * @param liverBean
	 * @return
	 */
	public  Float  accountPercent(LiverBean liverBean){
		LiveLever liveLever = liverBean.getLiveLever();
		LiveSalaryData liveSalaryData = liverBean.getLiveSalaryData();
		if(liveSalaryData==null){
			return 0F;
		}
		Long bout = liveSalaryData.getBout();  //有效场次
		
		if(bout>=liveLever.getPercentComplete()){
			return Float.valueOf(1L);
		}else if(bout>=liveLever.getPercentComplete80()){
			return  Float.valueOf(0.8F);
		}else if(bout>=liveLever.getPercentComplete60()){
			return  Float.valueOf(0.6F);
		}else if(bout>=liveLever.getPercentComplete40()){
			return  Float.valueOf(0.4F);
		}else{
			return  Float.valueOf(0F);
		}
	}

	/**
	 * 
	 * 方法描述：鸟豆转现金
	 * 创建人：jianming  
	 * 创建时间：2017年4月1日 上午10:50:01   
	 * @return
	 */
	public static BigDecimal xmnBeanToCash(BigDecimal bean){
		if(bean==null||bean.compareTo(BigDecimal.valueOf(0))<1){
			return BigDecimal.valueOf(0);
		}
		return bean.divide(BigDecimal.valueOf(1000)).setScale(2,BigDecimal.ROUND_UP);
	}
	
	/**
	 * 
	 * 方法描述：鸟豆转鸟蛋
	 * 创建人：jianming  
	 * 创建时间：2017年4月1日 下午3:23:01   
	 * @param bean
	 * @return
	 */
	public static BigDecimal xmnBeanToBalance(BigDecimal bean){
		if(bean==null||bean.compareTo(BigDecimal.valueOf(0))<1){
			return BigDecimal.valueOf(0);
		}
		return bean.multiply(BigDecimal.valueOf(5)).setScale(2,BigDecimal.ROUND_UP);
	}
	
	/**
	 * 
	 * 方法描述：鸟蛋转现金
	 * 创建人：jianming  
	 * 创建时间：2017年4月13日 上午10:41:39   
	 * @param egg
	 * @return
	 */
	public static BigDecimal xmnEggToCash(BigDecimal egg){
		return egg.divide(BigDecimal.valueOf(1000));
	}
	

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<LiverBean> getFail() {
		return fail;
	}

	public void setFail(List<LiverBean> fail) {
		this.fail = fail;
	}

	
	
}
