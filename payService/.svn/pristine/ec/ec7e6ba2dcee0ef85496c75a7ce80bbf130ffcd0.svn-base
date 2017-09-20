package com.xmniao.common;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Random;

/**
 * @author liyuanbo
 * @create 2017-06-13 17:15
 **/
public class XmnUtils {

    public static int floor(double value) {
        return (int) Math.floor(value);
    }

    public static double formatDouble2(double d) {
        // 新方法，如果不需要四舍五入，可以使用RoundingMode.DOWN
        BigDecimal bg = new BigDecimal(d).setScale(2, RoundingMode.DOWN);
        return bg.doubleValue();
    }

    public static double formatDouble(double d, int offset) {
        // 新方法，如果不需要四舍五入，可以使用RoundingMode.DOWN
        BigDecimal bg = new BigDecimal(d).setScale(offset, RoundingMode.DOWN);
        return bg.doubleValue();
    }

    public static double sub(double value1, double value2) {
        BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
        BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
        return b1.subtract(b2).doubleValue();
    }
    //随机生成红包
    public static double [] getMoney(double money, int num){
        Random r = new Random();
        DecimalFormat format = new DecimalFormat(".##");

        double middle = Double.parseDouble(format.format(money/num));
        double [] dou = new double[num];
        double redMoney = 0;
        double nextMoney = money;
        double sum = 0;
        int index = 0;
        for(int i=num;i>0;i--){
            if(i == 1){
                dou[index] = nextMoney;
            }else{
                while(true){
                    String str = format.format(r.nextDouble()*nextMoney);
                    redMoney = Double.parseDouble(str);
                    if(redMoney>0 && redMoney < middle){
                        break;
                    }
                }
                nextMoney = Double.parseDouble(format.format(nextMoney - redMoney));
                sum = sum + redMoney;
                dou[index] = redMoney;
                middle = Double.parseDouble(format.format(nextMoney/(i-1)));
                index++;
            }
        }
        return dou;
    }


}
