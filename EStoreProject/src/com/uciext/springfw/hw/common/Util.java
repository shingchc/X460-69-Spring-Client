package com.uciext.springfw.hw.common;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

public class Util {

	private static Random random = new Random();
	
    public static void waitMS(long ms) {
    	try {
    		Thread.sleep(ms);
    	}
    	catch (Exception e) {}
    }

    public static int getRandomInt() {
    	return random.nextInt(1000000);
    }
    
    public static Timestamp getTimestamp()	{
		Date date = new Date();
		return new java.sql.Timestamp(date.getTime());

    }
}
