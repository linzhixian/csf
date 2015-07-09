package com.lzx.framework.utils;

import java.util.Calendar;

public class CSFUtils {

	/**
	 * 两个时间之间是否跨天：即是否跳过24点
	 * 
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static boolean isNextDay(long time1, long time2) {
		Calendar c1 = Calendar.getInstance();
		c1.setTimeInMillis(time1);
		Calendar c2 = Calendar.getInstance();
		c2.setTimeInMillis(time2);
		return c1.get(Calendar.DAY_OF_YEAR) != c2.get(Calendar.DAY_OF_YEAR);
	}

}
