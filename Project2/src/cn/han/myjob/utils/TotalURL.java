package cn.han.myjob.utils;

import java.io.IOException;
import java.util.HashSet;

//获得所有网址
public class TotalURL {
	// 静态变量,bloomFilter过滤器和HashSet<String>的集合容器
	public static BloomFilter b = new BloomFilter();

	//获得所有第二层(第三层)所有链接
	public static HashSet<String> getTotalURL(String LineLink) {
		//启用时间
		long start = System.currentTimeMillis();
		HashSet<String> hs0 = getUrl(LineLink);
		HashSet<String> hs1 = new HashSet<String>();
		for (String str1 : hs0) {
			HashSet<String> hs2 = getUrl(str1);
			for (String str2 : hs2) {
				hs1.add(str2);
			}
		}
		System.out.println("the second layer scanned");
		
		/*
		 * HashSet<String> hs4 = new HashSet<String>(); int k = 0;
		 * System.out.println("k : " + k++); for (String str1 : hs1) {
		 * HashSet<String> hs5 = getUrl(str1); for (String str2 : hs5) {
		 * hs4.add(str2); } }
		 * System.out.println("the third layer scanned");
		 */
		
		long end = System.currentTimeMillis();
		MyTimer.printTime("TotalURL获取所有网页并返回", start, end);
		
		return hs1;
	}

	// 扫描单页链接
	public static HashSet<String> getUrl(String link) {
		// 创建HashSet<String>装入传入链接页面下的所有href链接
		HashSet<String> hs = new HashSet<String>();
		HashSet<String> AllURL = null;
		try {
			AllURL = URLTool.getURLTool(link);
		} catch (IOException e) {
			try {
				Thread.sleep(1000);
				System.out.println("--------安静的等待1s------");
			} catch (InterruptedException e1) {
				System.out.println("链接获取失败");
			}
			try {
				AllURL = URLTool.getURLTool(link);
			} catch (IOException e1) {
				System.out.println("链接获取失败");
			}
			System.out.println("错误的链接是:" + link);
		}
		if (AllURL != null) {
			for (String url1 : AllURL) {
				//判断是否扫描过
				if (!b.contains(url1)) {
					b.addValue(url1);
					hs.add(url1);
				}

			}
		}
		return hs;
	}
}
