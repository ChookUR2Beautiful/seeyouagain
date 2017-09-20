package com.xmniao.xmn.core.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FakeDataUtil {
	
	/* 1    表示订单消费额数据。
	 * 2   表示订单数量数据
	 * 3 表示会员数量数据 
	 */
	public static final int data_type_01 = 1;
	public static final int data_type_02 = 2;
	public static final int data_type_03 = 3;
	
	public static BigDecimal getQueryBillAmount(Date queryStartDate,Date queryEndDate,String province,String city){
		BigDecimal amount=BigDecimal.ZERO;
		List<Integer> queryMonthList=getQueryMonthList(processStartDate(queryStartDate),processEndDate(queryEndDate));
		BigDecimal totalAmount=getQueryTotalAmount(queryMonthList,processObject(province),processObject(city));
		BigDecimal startAmount=getStartMonthAmount(data_type_01,processStartDate(queryStartDate),processObject(province),processObject(city));
		BigDecimal endAmount=getEndMonthAmount(data_type_01,processEndDate(queryEndDate),processObject(province),processObject(city));
		amount=totalAmount.subtract(startAmount).subtract(endAmount);
		return amount.abs();
	}
	public static BigDecimal getQueryBillNumber(Date queryStartDate,Date queryEndDate,String province,String city, Long totalNum){
		BigDecimal amount=BigDecimal.ZERO;
		List<Integer> queryMonthList=getQueryMonthList(processStartDate(queryStartDate),processEndDate(queryEndDate));
		BigDecimal totalAmount=getQueryTotalBill(queryMonthList,processObject(province),processObject(city));
		BigDecimal startAmount=getStartMonthAmount(data_type_02,processStartDate(queryStartDate),processObject(province),processObject(city));
		BigDecimal endAmount=getEndMonthAmount(data_type_02,processEndDate(queryEndDate),processObject(province),processObject(city));
		amount=totalAmount.subtract(startAmount).subtract(endAmount);
		return amount.abs();
	}
	public static BigDecimal getQueryMemberNum(Date queryStartDate,Date queryEndDate,String province,String city){
		BigDecimal amount=BigDecimal.ZERO;
		List<Integer> queryMonthList=getQueryMonthList(processStartDate(queryStartDate),processEndDate(queryEndDate));
		BigDecimal totalAmount=getQueryTotalMember(queryMonthList,processObject(province),processObject(city));
		BigDecimal startAmount=getStartMonthAmount(data_type_03,processStartDate(queryStartDate),processObject(province),processObject(city));
		BigDecimal endAmount=getEndMonthAmount(data_type_03,processEndDate(queryEndDate),processObject(province),processObject(city));
		amount=totalAmount.subtract(startAmount).subtract(endAmount);
		return amount.abs();
	}
	public static String processObject(String str){
		if(null==str){
			str="";
		}
		return str;
	}
	public static Date processStartDate(Date date){
		 Date compareStartDate = DateUtil.smartFormat("2015-01-01");
		if(null==date || date.compareTo(compareStartDate) ==-1){
			date=DateUtil.smartFormat("2015-01-01");
		}
		return date;
	}
	public static Date processEndDate(Date date){
		if(null==date){
			date=DateUtil.smartFormat(DateUtil.getNow("yyyy-MM-dd"));
		}
		return date;
	}
	public static List<Integer> getQueryMonthList(Date queryStartDate,Date queryEndDate){
		List<Integer> monthList=new ArrayList<Integer>();
		
		int startMonth=queryStartDate.getMonth();
		int endMonth=queryEndDate.getMonth();
		for (int i = startMonth; i < endMonth+1; i++) {
			monthList.add(i);
		}
		return monthList;
	}
	public static BigDecimal getStartMonthAmount(int dataType,Date queryStartDate,String province,String city){
		BigDecimal amount=BigDecimal.ZERO;
		int dayNum=getStartMonthDayNum(queryStartDate);
		if(dayNum!=0){
			BigDecimal averageAmount=getEachMonthAverageBillAmount(dataType,queryStartDate.getMonth(),province,city);
			amount=averageAmount.multiply(BigDecimal.valueOf(dayNum));
		}
		return amount;
		
	}
	public static BigDecimal getEndMonthAmount(int dataType,Date queryEndDate,String province,String city){
		BigDecimal amount=BigDecimal.ZERO;
		int dayNum=getEndMonthDayNum(queryEndDate);
		if(dayNum!=0){
			BigDecimal averageAmount=getEachMonthAverageBillAmount(dataType,queryEndDate.getMonth(),province,city);
			amount=averageAmount.multiply(BigDecimal.valueOf(dayNum));
		}
		return amount;
	}

	public static int getEndMonthDayNum(Date queryEndDate){
		int dayNum=getDayNumByMonth(queryEndDate.getMonth())-queryEndDate.getDate();
		return dayNum;
	}
	public static int getStartMonthDayNum(Date queryStartDate){
		return queryStartDate.getDate()-1;
	}
	public static int isGetGuangZhouData(String province,String city){
		 if(city.equals("1964")){//广州数据类型
			 return 1; 
		 }else{//非广州数据类型
			 return 0;
		 }
	}
	public static BigDecimal getEachMonthAverageBillAmount(int dataType,int month,String province,String city){
		BigDecimal averageAmount=BigDecimal.ZERO;
		BigDecimal totalAmount=getEachMonthDataByDataType(month,dataType,province,city);
		averageAmount=BigDecimal.valueOf(totalAmount.intValue()/getDayNumByMonth(month));
		return averageAmount;
	}
	public static BigDecimal getEachMonthBillAmount(int month,int isGuangZhou){
		List<Map<Integer,BigDecimal>> list=null;
		if(isGuangZhou ==1){//广州数据
			list=getGuangZhouEachMonthAmountList();
		}else{//全国数据
			list=getEachMonthAmountList();
		}
		Map<Integer,BigDecimal> map=list.get(month);
		return map.get(month);
	}
	public static BigDecimal getEachMonthBillNumber(int month,int isGuangZhou){
		List<Map<Integer,BigDecimal>> list=null;
		if(isGuangZhou ==1){//广州数据
			list=getGuangZhouEachMonthBillNumList();
		}else{//全国数据
			list=getEachMonthBillNumList();
		}
		Map<Integer,BigDecimal> map=list.get(month);
		return map.get(month);
	}
	public static BigDecimal getEachMonthMemberNum(int month,int isGuangZhou){
		List<Map<Integer,BigDecimal>> list=null;
		if(isGuangZhou ==1){//广州数据
			list=getGuangZhouEachMonthMemberNumList();
		}else{//全国数据
			list=getEachMonthMemberNumList();
		}
		Map<Integer,BigDecimal> map=list.get(month);
		return map.get(month);
	}
	/* 1    表示订单消费额数据。
	 * 2   表示订单数量数据
	 * 3 表示会员数量数据 
	 */
	public static BigDecimal getEachMonthDataByDataType(int month,int dataType,String province,String city){
		BigDecimal averageAmount=BigDecimal.ZERO;
		if(dataType ==data_type_01){
			averageAmount=getEachMonthBillAmount(month,isGetGuangZhouData(province,city));
		}else if(dataType ==data_type_02){
			averageAmount=getEachMonthBillNumber(month,isGetGuangZhouData(province,city));
		}else{
			averageAmount=getEachMonthMemberNum(month,isGetGuangZhouData(province,city));
		}
		return averageAmount;
	}
	public static int getDayNumByMonth(int month){
		int dayNum=30;
		 switch(month){
	          case 0:
	        	  dayNum=31;
	        	  break;
	          case 1:
	        	  dayNum=28;
	        	  break;
	          case 2:
	        	  dayNum=31;
	        	  break;
	          case 3:
	        	  dayNum=30;
	        	  break;
	          case 4:
	        	  dayNum=31;
	        	  break;
	          case 5:
	        	  dayNum=30;
	        	  break;
	          case 6:
	        	  dayNum=31;
	        	  break;
	          case 7:
	        	  dayNum=31;
	        	  break;
	          case 8:
	        	  dayNum=30;
	        	  break;
	          case 9:
	        	  dayNum=31;
	        	  break;
	        }
		 return dayNum;
	}
	public static BigDecimal getQueryTotalAmount(List<Integer> queryMonthList,String province,String city){
		BigDecimal total=BigDecimal.ZERO;
		Integer tempInteger=null;
		for (int i = 0; i < queryMonthList.size(); i++) {
			tempInteger=queryMonthList.get(i);
			List<Map<Integer,BigDecimal>> amountList=getBillAmountListByCondition(province,city);
			if(null!=amountList){
				Map<Integer,BigDecimal> tempMap=null;
				for (int j = 0; j < amountList.size(); j++) {
					tempMap=amountList.get(j);
					if(tempMap.containsKey(tempInteger)){
						total=total.add(tempMap.get(tempInteger));
					}
				}
			}
		}
		return total;
	}
	public static BigDecimal getQueryTotalBill(List<Integer> queryMonthList,String province,String city){
		BigDecimal total=BigDecimal.ZERO;
		Integer tempInteger=null;
		for (int i = 0; i < queryMonthList.size(); i++) {
			tempInteger=queryMonthList.get(i);
			List<Map<Integer,BigDecimal>> amountList=getBillNumListByCondition(province,city);
			Map<Integer,BigDecimal> tempMap=null;
			if(amountList!=null){
			for (int j = 0; j < amountList.size(); j++) {
				tempMap=amountList.get(j);
				if(tempMap.containsKey(tempInteger)){
					total=total.add(tempMap.get(tempInteger));
				}
			}
			}
		}
		return total;
	}
	public static BigDecimal getQueryTotalMember(List<Integer> queryMonthList,String province,String city){
		BigDecimal total=BigDecimal.ZERO;
		Integer tempInteger=null;
		for (int i = 0; i < queryMonthList.size(); i++) {
			tempInteger=queryMonthList.get(i);
			List<Map<Integer,BigDecimal>> amountList=getMemberNumListByCondition(province,city) ;
			Map<Integer,BigDecimal> tempMap=null;
			for (int j = 0; j < amountList.size(); j++) {
				tempMap=amountList.get(j);
				if(tempMap.containsKey(tempInteger)){
					total=total.add(tempMap.get(tempInteger));
				}
			}
		}
		return total;
	}
	 public static List<Map<Integer,BigDecimal>> getBillAmountListByCondition(String province,String city) {
		 if(city.equals("1964")){
			 return getGuangZhouEachMonthAmountList(); 
		 }else if("".equals(province)){
			 return getEachMonthAmountList();
		 }else{
			 return null;
		 }
	 }
	 public static List<Map<Integer,BigDecimal>> getBillNumListByCondition(String province,String city) {
		 if(city.equals("1964")){
			 return getGuangZhouEachMonthBillNumList(); 
		 }else if("".equals(province)){
			 return getEachMonthBillNumList();
		 }else{
			 return null;
		 }
	 }
	 public static List<Map<Integer,BigDecimal>> getMemberNumListByCondition(String province,String city) {
		 if(city.equals("1964")){
			 return getGuangZhouEachMonthMemberNumList(); 
		 }else if("".equals(province)){
			 return getEachMonthMemberNumList();
		 }else{
			 return null;
		 }
	 }
	public static List<Map<Integer,BigDecimal>> getEachMonthAmountList(){
		List<Map<Integer,BigDecimal>> amountList=new ArrayList<Map<Integer,BigDecimal>>();
		Map january=new HashMap();
		january.put(0, BigDecimal.valueOf(29666));
		Map february=new HashMap();
		february.put(1, BigDecimal.valueOf(493104));
		Map march=new HashMap();
		march.put(2, BigDecimal.valueOf(163861));
		Map april=new HashMap();
		april.put(3, BigDecimal.valueOf(349547));
		Map may=new HashMap();
		may.put(4, BigDecimal.valueOf(824115));
		Map june=new HashMap();
		june.put(5, BigDecimal.valueOf(1801276));
		Map july=new HashMap();
		july.put(6, BigDecimal.valueOf(5478490));
		Map august=new HashMap();
		august.put(7, BigDecimal.valueOf(10911063));
		Map september=new HashMap();
		september.put(8, BigDecimal.valueOf(13073377));
		Map october=new HashMap();
		october.put(9, BigDecimal.valueOf(15601404));
		
		amountList.add(january);
		amountList.add(february);
		amountList.add(march);
		amountList.add(april);
		amountList.add(may);
		amountList.add(june);
		amountList.add(july);
		amountList.add(august);
		amountList.add(september);
		amountList.add(october);
		return amountList;
	}
	public static List<Map<Integer,BigDecimal>> getGuangZhouEachMonthAmountList(){
		List<Map<Integer,BigDecimal>> amountList=new ArrayList<Map<Integer,BigDecimal>>();
		Map january=new HashMap();
		january.put(0, BigDecimal.valueOf(14271));
		Map february=new HashMap();
		february.put(1, BigDecimal.valueOf(71611));
		Map march=new HashMap();
		march.put(2, BigDecimal.valueOf(33782));
		Map april=new HashMap();
		april.put(3, BigDecimal.valueOf(136826));
		Map may=new HashMap();
		may.put(4, BigDecimal.valueOf(427551));
		Map june=new HashMap();
		june.put(5, BigDecimal.valueOf(1230076));
		Map july=new HashMap();
		july.put(6, BigDecimal.valueOf(2976181));
		Map august=new HashMap();
		august.put(7, BigDecimal.valueOf(8449402 ));
		Map september=new HashMap();
		september.put(8, BigDecimal.valueOf(8369827));
		Map october=new HashMap();
		october.put(9, BigDecimal.valueOf(12566861));
		
		amountList.add(january);
		amountList.add(february);
		amountList.add(march);
		amountList.add(april);
		amountList.add(may);
		amountList.add(june);
		amountList.add(july);
		amountList.add(august);
		amountList.add(september);
		amountList.add(october);
		return amountList;
	}
	public static List<Map<Integer,BigDecimal>> getEachMonthBillNumList(){
		List<Map<Integer,BigDecimal>> billNumList=new ArrayList<Map<Integer,BigDecimal>>();
		Map january=new HashMap();
		january.put(0, BigDecimal.valueOf(2653));
		Map february=new HashMap();
		february.put(1, BigDecimal.valueOf(49897));
		Map march=new HashMap();
		march.put(2, BigDecimal.valueOf(5209));
		Map april=new HashMap();
		april.put(3, BigDecimal.valueOf(12992));
		Map may=new HashMap();
		may.put(4, BigDecimal.valueOf(40746));
		Map june=new HashMap();
		june.put(5, BigDecimal.valueOf(80327));
		Map july=new HashMap();
		july.put(6, BigDecimal.valueOf(148610));
		Map august=new HashMap();
		august.put(7, BigDecimal.valueOf(114387));
		Map september=new HashMap();
		september.put(8, BigDecimal.valueOf(128060));
		Map october=new HashMap();
		october.put(9, BigDecimal.valueOf(173125));
		
		billNumList.add(january);
		billNumList.add(february);
		billNumList.add(march);
		billNumList.add(april);
		billNumList.add(may);
		billNumList.add(june);
		billNumList.add(july);
		billNumList.add(august);
		billNumList.add(september);
		billNumList.add(october);
		return billNumList;
	}
	public static List<Map<Integer,BigDecimal>> getGuangZhouEachMonthBillNumList(){
		List<Map<Integer,BigDecimal>> billNumList=new ArrayList<Map<Integer,BigDecimal>>();
		Map january=new HashMap();
		january.put(0, BigDecimal.valueOf(1417));
		Map february=new HashMap();
		february.put(1, BigDecimal.valueOf(22235));
		Map march=new HashMap();
		march.put(2, BigDecimal.valueOf(385));
		Map april=new HashMap();
		april.put(3, BigDecimal.valueOf(4240));
		Map may=new HashMap();
		may.put(4, BigDecimal.valueOf(16694));
		Map june=new HashMap();
		june.put(5, BigDecimal.valueOf(55140));
		Map july=new HashMap();
		july.put(6, BigDecimal.valueOf(57358));
		Map august=new HashMap();
		august.put(7, BigDecimal.valueOf(67131));
		Map september=new HashMap();
		september.put(8, BigDecimal.valueOf(88368));
		Map october=new HashMap();
		october.put(9, BigDecimal.valueOf(92820));
		
		billNumList.add(january);
		billNumList.add(february);
		billNumList.add(march);
		billNumList.add(april);
		billNumList.add(may);
		billNumList.add(june);
		billNumList.add(july);
		billNumList.add(august);
		billNumList.add(september);
		billNumList.add(october);
		return billNumList;
	}
	public static List<Map<Integer,BigDecimal>> getEachMonthMemberNumList(){
		List<Map<Integer,BigDecimal>> memberNumList=new ArrayList<Map<Integer,BigDecimal>>();
		Map january=new HashMap();
		january.put(0, BigDecimal.valueOf(4864));
		Map february=new HashMap();
		february.put(1, BigDecimal.valueOf(50094));
		Map march=new HashMap();
		march.put(2, BigDecimal.valueOf(3666));
		Map april=new HashMap();
		april.put(3, BigDecimal.valueOf(9419));
		Map may=new HashMap();
		may.put(4, BigDecimal.valueOf(20930));
		Map june=new HashMap();
		june.put(5, BigDecimal.valueOf(45295));
		Map july=new HashMap();
		july.put(6, BigDecimal.valueOf(58905));
		Map august=new HashMap();
		august.put(7, BigDecimal.valueOf(49033));
		Map september=new HashMap();
		september.put(8, BigDecimal.valueOf(51717));
		Map october=new HashMap();
		october.put(9, BigDecimal.valueOf(57649));
		
		memberNumList.add(january);
		memberNumList.add(february);
		memberNumList.add(march);
		memberNumList.add(april);
		memberNumList.add(may);
		memberNumList.add(june);
		memberNumList.add(july);
		memberNumList.add(august);
		memberNumList.add(september);
		memberNumList.add(october);
		return memberNumList;
	}
	public static List<Map<Integer,BigDecimal>> getGuangZhouEachMonthMemberNumList(){
		List<Map<Integer,BigDecimal>> memberNumList=new ArrayList<Map<Integer,BigDecimal>>();
		Map january=new HashMap();
		january.put(0, BigDecimal.valueOf(2631));
		Map february=new HashMap();
		february.put(1, BigDecimal.valueOf(20870));
		Map march=new HashMap();
		march.put(2, BigDecimal.valueOf(218));
		Map april=new HashMap();
		april.put(3, BigDecimal.valueOf(3217));
		Map may=new HashMap();
		may.put(4, BigDecimal.valueOf(9982));
		Map june=new HashMap();
		june.put(5, BigDecimal.valueOf(33445));
		Map july=new HashMap();
		july.put(6, BigDecimal.valueOf(13759));
		Map august=new HashMap();
		august.put(7, BigDecimal.valueOf(30624));
		Map september=new HashMap();
		september.put(8, BigDecimal.valueOf(36028));
		Map october=new HashMap();
		october.put(9, BigDecimal.valueOf(49093));
		
		memberNumList.add(january);
		memberNumList.add(february);
		memberNumList.add(march);
		memberNumList.add(april);
		memberNumList.add(may);
		memberNumList.add(june);
		memberNumList.add(july);
		memberNumList.add(august);
		memberNumList.add(september);
		memberNumList.add(october);
		return memberNumList;
	}
	public static boolean queryDateAfterOctober(Date queryStartDate,Date queryEndDate){
		 Date compareEndDate = DateUtil.smartFormat("2015-10-31");
		 if((null !=queryStartDate && queryStartDate.compareTo(compareEndDate) == 1) && (null!=queryEndDate && queryEndDate.compareTo(compareEndDate) ==1)){
			return true;
		 }
		 return false;
	}
	public static boolean isNeedQueryAfterOctoberData(Date queryStartDate,Date queryEndDate){
		 Date compareEndDate = DateUtil.smartFormat("2015-10-31");
		 if(null==queryStartDate && null ==queryEndDate){
			 return true;
		 }
		 if(null==queryStartDate && queryEndDate.compareTo(compareEndDate) !=-1){
			 return true;
		 }
		 if(null==queryEndDate && queryStartDate.compareTo(compareEndDate) !=1){
			 return true;
		 }
		 if(queryStartDate.compareTo(compareEndDate) != 1 && queryEndDate.compareTo(compareEndDate) !=-1){
			return true;
		 }
		 return false;
	}
	public static Date getEndQueryDateAfterOctober(Date queryStartDate,Date queryEndDate){
		 if(isNeedQueryAfterOctoberData(queryStartDate,queryEndDate)){
			return  queryEndDate;
		 }
		 return null;
	}
	public static boolean  isIncludeTime(Date queryStartDate,Date queryEndDate) {
		boolean flag=true;
		 Date compareStartDate = DateUtil.smartFormat("2015-01-01");
		 Date compareEndDate = DateUtil.smartFormat("2015-10-31");
		 if((queryEndDate!=null && queryEndDate.compareTo(compareStartDate)!=1) || (queryStartDate !=null && queryStartDate.compareTo(compareEndDate)!=-1)){
			 flag=false;
		 }
		return flag;
	}
	public static boolean  dateIsNull(Date queryStartDate,Date queryEndDate) {
		boolean flag=true;
		 if(queryEndDate!=null && queryStartDate !=null){
			 flag=false;
		 }
		return flag;
	}

	public static Date formatDate(Date date){
		Date returnDate=null;
		try {
			if(null!=date){
				String strDate=DateUtil.formatDate(date, "yyyy-MM-dd");
				SimpleDateFormat sdfFormat = new SimpleDateFormat("yyyy-MM-dd");
				sdfFormat.setLenient(false);
				returnDate= sdfFormat.parse(strDate);
			}
		} catch (ParseException e) {
			System.out.println("日期格式错误！");
		}
		return returnDate;
	}
}
