package cn.han.myjob.utils;

import java.awt.Toolkit;

//功能:计算运行时间
public class MyTimer {
	public static void printTime(String operation,long start, long end){
		int period = (int) (end - start) / 1000;
		System.out.println("*****************************************");
		System.out.println("operating time : " + period + "s");
		System.out.println(operation + "-----done!");
		System.out.println("*****************************************");
		//提示音
		Toolkit.getDefaultToolkit().beep();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
