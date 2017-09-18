package com.achhabra.finworld.util;

public class CommonUtils {

	public static int getMonth(String val){
		String[] arr= val.split("/");
		return Integer.parseInt(arr[0].trim());
	}
	
	public static int getYear(String val){
		String[] arr= val.split("/");
		return Integer.parseInt(arr[1].trim());
	}
}
