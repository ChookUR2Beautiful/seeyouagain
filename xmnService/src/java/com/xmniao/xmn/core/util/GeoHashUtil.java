package com.xmniao.xmn.core.util;

import java.util.BitSet;
import java.util.HashMap;

/**
 * 
* @projectName: xmnService 
* @ClassName: GeoHashUtil    
* @Description:通过经纬度获取HASH值工具类   
* @author: liuzhihao   
* @date: 2016年11月9日 下午4:15:29
 */
public class GeoHashUtil {

	private static int numbits = 6 * 5;  
    final static char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8',  
            '9', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n', 'p',  
            'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };  
      
    final static HashMap<Character, Integer> lookup = new HashMap<Character, Integer>();  
    static {  
        int i = 0;  
        for (char c : digits)  
            lookup.put(c, i++);  
    }  
  
    public double[] decode(String geohash) {  
        StringBuilder buffer = new StringBuilder();  
        for (char c : geohash.toCharArray()) {  
  
            int i = lookup.get(c) + 32;  
            buffer.append( Integer.toString(i, 2).substring(1) );  
        }  
          
        BitSet lonset = new BitSet();  
        BitSet latset = new BitSet();  
          
        //even bits  
        int j =0;  
        for (int i=0; i< numbits*2;i+=2) {  
            boolean isSet = false;  
            if ( i < buffer.length() )  
              isSet = buffer.charAt(i) == '1';  
            lonset.set(j++, isSet);  
        }  
          
        //odd bits  
        j=0;  
        for (int i=1; i< numbits*2;i+=2) {  
            boolean isSet = false;  
            if ( i < buffer.length() )  
              isSet = buffer.charAt(i) == '1';  
            latset.set(j++, isSet);  
        }  
          
        double lon = decode(lonset, -180, 180);  
        double lat = decode(latset, -90, 90);  
          
        return new double[] {lat, lon};       
    }  
      
    public static double decode(BitSet bs, double floor, double ceiling) {  
        double mid = 0;  
        for (int i=0; i<bs.length(); i++) {  
            mid = (floor + ceiling) / 2;  
            if (bs.get(i))  
                floor = mid;  
            else  
                ceiling = mid;  
        }  
        return mid;  
    }  
      
    /**
     * 生产一个HASH值
     * @param lat
     * @param lon
     * @return
     */
    public static String encode(double lat, double lon) {  
        BitSet latbits = getBits(lat, -90, 90);  
        BitSet lonbits = getBits(lon, -180, 180);  
        StringBuilder buffer = new StringBuilder();  
        for (int i = 0; i < numbits; i++) {  
            buffer.append( (lonbits.get(i))?'1':'0');  
            buffer.append( (latbits.get(i))?'1':'0');  
        }  
        return base32(Long.parseLong(buffer.toString(), 2));  
    }  
  
    public static BitSet getBits(double lat, double floor, double ceiling) {  
        BitSet buffer = new BitSet(numbits);  
        for (int i = 0; i < numbits; i++) {  
            double mid = (floor + ceiling) / 2;  
            if (lat >= mid) {  
                buffer.set(i);  
                floor = mid;  
            } else {  
                ceiling = mid;  
            }  
        }  
        return buffer;  
    }  
  
    public static String base32(long i) {  
        char[] buf = new char[65];  
        int charPos = 64;  
        boolean negative = (i < 0);  
        if (!negative)  
            i = -i;  
        while (i <= -32) {  
            buf[charPos--] = digits[(int) (-(i % 32))];  
            i /= 32;  
        }  
        buf[charPos] = digits[(int) (-i)];  
  
        if (negative)  
            buf[--charPos] = '-';  
        return new String(buf, charPos, (65 - charPos));  
    }  
    
    /**
     * 获取两个坐标之间的距离
     * @param lon1 A点经度
     * @param lat1 A点纬度
     * @param lon2 B点经度
     * @param lat2 B点纬度
     * @return
     */
    public static double getDistance(Double Alon1,Double Alat1,Double Blon2,Double Blat2){
    	/**
    	 * Alon1、Alat1为A点的经度和纬度
    	 * Blon2、Blat2为B点的经度和纬度
    	 * A点的经度和纬度 小于 B点的经度和纬度
    	 */
    	//(地球是圆的，所以这个取是一个幅度值，地球中两点之间存在细微幅度)
    	Double lon1 = Math.toRadians(Alon1);//A点经度弧度值
    	Double lon2 = Math.toRadians(Blon2);//B点经度弧度值
    	Double lat1 = Math.toRadians(Alat1);//A点纬度弧度值
    	Double lat2 = Math.toRadians(Blat2);//B点纬度弧度值
    	//B点与A点的纬度之差
    	Double lat = lat2 - lat1;
    	//B点与A点经度之差
    	Double lon = lon2 - lon1;
    	//采用勾股定理计算两点之间的距离
    	double s = 2 * Math. asin(Math .sqrt(
            Math.pow (Math.sin(lat / 2), 2) + Math.cos (lat1) * Math.cos (lat2) * Math.pow (Math.sin(lon / 2), 2)
            	)
    		) ;
    	s = s * 6378137.0 ;// 取WGS84标准参考椭球中的地球长半径(单位:m)
    	s = Math. round(s * 10000) / 10000 ;
    	return s;
    }

    public static void main(String[] args) {
    	Double Alon1 = 113.3445887586806;
    	Double Alat1 = 23.12586697048611;
    	Double Blon2 = 113.32712;
    	Double Blat2 = 23.132913;
    	Double d = getDistance(Alon1, Alat1, Blon2, Blat2);
    	System.out.println(d);
	}
}
