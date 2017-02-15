package cn.han.myjob.utils;

import java.io.IOException;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

//获得指定城市所有公交线路的链接
public class CertainCityBusLinesLinks {
	@Test
	public static HashMap<String, String> getLinks(String link) {
		// 创建一个map集合用来装入,公交线路名和对应的链接
		HashMap<String, String> map = new HashMap<String, String>();
		// 解析链接
		Document doc = null;
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
				map.put("未知", "未知");
				return map;
			}
		}
		// 获取 属性class为"list"的标签元素,然后获取底下的第一个子标签div
		// 再获取div下的所有<a>超链接子标签
		Elements eles = doc.getElementsByClass("list");
		Element divTag = eles.first();
		Elements aTags = divTag.getElementsByTag("a");
		// 将线路名和线路链接存入map集合中
		for (Element e : aTags) {
			// 线路链接应该是一一匹配的,但是防患于未然,加上判断
			if (e.attr("href") != null) {
				map.put(e.text(), e.attr("href"));
			} else {
				map.put(e.text(), "");
			}
		}
		// 返回HashMap集合
		return map;
	}
}
