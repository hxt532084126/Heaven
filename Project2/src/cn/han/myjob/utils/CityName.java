package cn.han.myjob.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//获取城市中文名称,然后将链接(作为key,有可能中文名有重名),然后返回链接和中文名.
public class CityName {
	public static HashMap<String, String> get(String weblink) {
		//开启的时间
		long start = System.currentTimeMillis();
		
		HashSet<String> hs = TotalLinesPageLink.getTotalLines(weblink);
		HashMap<String, String> map = new HashMap<String, String>();
		Document doc = null;
		for (String link : hs) {
			try {
				doc = Jsoup.connect(link).get();
			} catch (IOException e) {
				//防止被网址防抓取机制误伤,在下静静地等待1s
				try {
					Thread.sleep(1000);
					System.out.println("--------安静的等待1s------");
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				try {
					doc = Jsoup.connect(link).get();
				} catch (IOException e1) {
					map.put(link, "未知");
					return map;
				}
			}
			// 到http://dongyang.gongjiao.com/lines_all.html里获取
			String citylink = link.replace("lines_all.html", "");
			Elements eles = doc.getElementsByAttributeValue("href",
					citylink);
			// 如果eles没有获取到,将中文名设为"未知".
			String cityname = "未知";
			for (Element e : eles) {
				cityname = e.text();
				System.out.println("获取成功 : " + cityname);
				break;
			}
			map.put(link, cityname);
		}

		// 检验城市是否完整
		// for (String key : map.keySet()) {
		//
		// String value = map.get(key);
		//
		// System.out.println("Key = " + key + ", Value = " + value);
		// }

		// 时间略长,约14分钟左右
		long end = System.currentTimeMillis();
		MyTimer.printTime("城市获取完成",start,end);
		return map;
	}
}
