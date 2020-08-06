package com.sc.common.study.hashmap;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class InitialCapacity{

	public static void main(String[] args) {

//		int aHundredMillion = 10000000;
//		   getThreshold(new HashMap<>());
//
//		   for (int k = 0; k < 5; k++) {
//			   Map<Integer, Integer> map3 = new HashMap<>(100000);
//			   long s7 = System.currentTimeMillis();
//			   System.out.print("遍历前threshold=" + getThreshold(map3));
//			   for (int i = 0; i < aHundredMillion; i++) {
//				   map3.put(i, i);
//			   }
//			   System.out.println("，遍历后threshold=" + getThreshold(map3));
//			   long s8 = System.currentTimeMillis();
//			   
//			   System.out.println("初始化容量为，耗时 ： " + (s8 - s7));
//		   }
		
		   
//		   Map<Integer, Integer> map2 = new HashMap<>(aHundredMillion);
//
//		   long s3 = System.currentTimeMillis();
//		   for (int i = 0; i < aHundredMillion; i++) {
//		       map2.put(i, i);
//		   }
//		   long s4 = System.currentTimeMillis();
//
//		   System.out.println("初始化容量为10000000，耗时 ： " + (s4 - s3));
//		   
//		   Map<Integer, Integer> map1 = new HashMap<>(aHundredMillion / 2);
//
//		   long s5 = System.currentTimeMillis();
//		   for (int i = 0; i < aHundredMillion; i++) {
//		       map1.put(i, i);
//		   }
//		   long s6 = System.currentTimeMillis();
//
//		   System.out.println("初始化容量5000000，耗时 ： " + (s6 - s5));
//		   
//		   Map<Integer, Integer> map = new HashMap<>();
//
//		   long s1 = System.currentTimeMillis();
//		   for (int i = 0; i < aHundredMillion; i++) {
//		       map.put(i, i);
//		   }
//		   long s2 = System.currentTimeMillis();
//
//		   System.out.println("未初始化容量，耗时 ： " + (s2 - s1));
		}
	
	
	private static int getThreshold(Map<Integer, Integer> map) {
		Class class1 = HashMap.class;
		try {
			Field field = class1.getDeclaredField("threshold");
			field.setAccessible(true);
			return (int)field.get(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}
	
	
	private static int getTNum(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= 1073741824) ? 1073741824 : n + 1;
	}
}
