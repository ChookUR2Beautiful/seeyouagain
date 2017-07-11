package com.xmn.saas.utils;

import java.util.Date;
import java.util.List;

import com.xmn.saas.entity.activity.AwardRelation;

public class ActivityUtil {
	
	/**
	 * 
	 * 方法描述：判断时间是否在时间段内
	 * 创建人：jianming  
	 * 创建时间：2016年10月15日 上午11:07:26   
	 * @param getTime	时间
	 * @param setCondition	周期 0:直接返回true  1:本天    2:本周   3:本月 
	 * @return
	 */
	public static boolean DateAtTimeQuantum(Date getTime, Integer setCondition) {
		switch (setCondition) {
		case 0:
			return true;
		case 1:
			return !CalendarUtil.isToday(getTime.getTime());
		case 2:
			return !CalendarUtil.isThisWeek(getTime.getTime());
		case 3:
			return !CalendarUtil.isThisMonth(getTime.getTime());
		default:
			break;
		}
		return false;
	}		
	
	/**
	 * 
	 * 方法描述：抽奖方法
	 * 创建人：jianming  
	 * 创建时间：2016年10月15日 上午11:55:32   
	 * @param awardProportion	获奖概率
	 * @param awardRelations  奖品列表
	 * @return
	 */
	public static AwardRelation doLottery(Double awardProportion, List<AwardRelation> awardRelations) {
		if(awardProportion>100||awardProportion<0){
			throw new RuntimeException("获奖概率无效");
		}
		if(awardRelations==null||awardRelations.size()<1){
			return null;
		}
		java.util.Random ran = new java.util.Random();
        double base = ran.nextDouble()*100;    //生成随机数1.00-100.00
        java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");  
        double format = Double.parseDouble(df.format(base));	
        if (format > awardProportion) {	//对比 随机数是否大于获奖概率
            return null;	//大于,不获奖
        }
                //TODO 打乱奖池数据
        int l = ran.nextInt(awardRelations.size());	//获奖,然后按传入的奖品类别长度
		return awardRelations.get(l);
	}
	
}
