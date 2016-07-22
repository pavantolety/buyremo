package com.buyremo.util;

import java.text.NumberFormat;
import java.util.Locale;

public class NumberUtil {

	public static String getCurrencyFormat(Double amount){
		String amountOut;
	    NumberFormat numberFormatter;
	    numberFormatter = NumberFormat.getNumberInstance(Locale.US);
	    amountOut = numberFormatter.format(amount);
	    //System.out.println(amountOut);
	    
		return amountOut;
	}
	
	public static String formatNumber(Double amount){
		String amountOut;
	    NumberFormat numberFormatter;
	    numberFormatter = NumberFormat.getNumberInstance(Locale.US);
	    amountOut = numberFormatter.format(amount);
	    //System.out.println(amountOut);
	    
		return amountOut;
	}
	
	public static double getDouble(String amount){
		
		double amountOut = 0;
		try{
			amountOut = Double.valueOf(amount);
		}catch(NumberFormatException nfe){
			System.out.println("Number Format Exception :: " + nfe);
		}
	    
		return amountOut;
	}

	//public static String
}
