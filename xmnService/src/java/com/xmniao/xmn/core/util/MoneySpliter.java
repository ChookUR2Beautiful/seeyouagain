package com.xmniao.xmn.core.util;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

/**
 * 金额拆分器
 * @author JIAN
 *
 */
public class MoneySpliter {
	
	private final Logger log = Logger.getLogger(MoneySpliter.class);
	
	public static final String MONEY_CHECKED = "CHECKED";
	public static final String MONEY_TOO_MIN = "MONEY_TOO_MIN";
	public static final String MONEY_TOO_MAX = "MONEY_TOO_MAX";
	
	//待拆分金额
	private int money;
	//待拆分个数
	private int num;
	//单个最小金额
	private int minMoney = 1;
	//单个最大金额
	private int maxMoney = 200 * 100;
	//单个最大是平均值的倍数,防止单个金额过大
	private double times = 2.2;
	
	
	/**
	 * @param minMoney 单个最小金额
	 * @param maxMoney 单个最大金额
	 * @param money 待拆分金额
	 * @param num 待拆分个数
	 * @throws Exception
	 *  @Description:规则 minMoney * num <= money <= maxMoney * num
	 */
	public MoneySpliter(int minMoney,int maxMoney,int money,int num) throws Exception{
		this.minMoney = minMoney <= 0 ? 1 : minMoney;
		this.maxMoney = maxMoney <=0 ?  200 * 100 :  maxMoney;
		
		String checked = doCheck(money,num);
		
		if (MONEY_TOO_MIN.equals(checked)) {
			log.error("最小金额设置过大,无法进行分配");
			throw new Exception("最小金额设置过大,无法进行分配");
		}
		if (MONEY_TOO_MAX.equals(checked)) {
			log.error("最大金额设置过小,无法进行分配");
			throw new Exception("最大金额设置过小,无法进行分配");
		}
		this.money = money;
		this.num = num;
		
	}
	
	
	/**
	 * @param money
	 * @param num
	 * @return
	 * @Description: 判断待拆分金额与拆分个数是否符合规则，单个介于[minMoney,maxMoney]之间
	 */
	private String doCheck(int money, int num) {
		//最小总额
		int min = minMoney * num;
		//最大总额
		int max = maxMoney * num;
		
		//金额小于最小总额，会产生面值小于minMoney，不符合规则
		if (money < min) {
			return MONEY_TOO_MIN;
		}
		
		//金额大于最大总额，会产生面值大于maxMoney，不符合规则
		if (money > max) {
			return MONEY_TOO_MAX;
		}
		
		return MONEY_CHECKED;
	}
	
	
	/**
	 * @param money
	 * @param min
	 * @param max
	 * @param num
	 * @return
	 * @Description: 随机单个金额
	 */
	private int random(int money, int min, int max, int num) {
		// 拆分数为1或最后一份，直接返回金额
		if (num == 1) {
			return money;
		}
		
		// 最大金额和最小金额相等，直接返回金额
		if (min == max) {
			return min;
		}
		
		//计算实际允许最大金额
		int _max = max > money ? money : max;
		
		//随机产生一份，面值[min,_max]之间
		Random random = new Random();
        int one = random.nextInt(_max) % (_max - min + 1) + min;
        
        //剩余金额
		int residue = money - one;
		
		//判断剩余金额是否符合规则
		String checked = doCheck(residue, num - 1);
		if(MONEY_CHECKED.equals(checked)) {
			return one;
		}else if(MONEY_TOO_MIN.equals(checked)){
			//剩余金额过小，调整最大金额为当前随机额
			return random(money, min, one, num);
		}else if(MONEY_TOO_MAX.equals(checked)){
			//剩余金额过大，调整最小金额为当前随机额
			return random(money, one, max, num);
		}
		
		return one;
	}
	

	/**
	 * @param money
	 * @param num
	 * @return
	 * @Description: 拆分金额
	 */
	public List<Integer> split() {
		List<Integer> list = new ArrayList<Integer>();
		
		//单个最大金额为平均金额的times倍
		int max = (int) (money  / num * times);
		
		//计算实际允许最大金额
		max = max > maxMoney ? maxMoney : max;
		
		//逐个拆分
		for (int i = 0; i < num; i++) {
			int one = random(money, minMoney, max, num - i);
			list.add(one);
			money -= one;
		}
		
		return list;
	}

	
	public static void main(String[] args) throws Exception {
		MoneySpliter spliter = new MoneySpliter(11,250,250, 14);
		List<Integer> result = spliter.split();
		
		Iterator<Integer> i = result.iterator();
		BigDecimal  all = new BigDecimal(0.00);
		while(i.hasNext()){
			BigDecimal money = new BigDecimal(i.next());
//			money = money.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
			System.out.println(money.doubleValue());
			all = all.add(money);
		}
		
		System.out.println("==============");
		System.out.println(all.doubleValue());
		
	}
}
