package cn.han.myjob.utils;

import java.io.IOException;
import java.util.HashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//源码出处:https://jsoup.org/cookbook/extracting-data/example-list-links
//获取输入网页中的所有网址,并返回hashset集合,下一步使用bloom filter过滤去重
public class URLTool {
	// 校验工具
	public static void main(String[] args) throws IOException {
		HashSet<String> hs = getURLTool("https://www.baidu.com/");
		for (String link : hs) {
			System.out.println(link);
		}
	}

	// 获得页面链接
	public static HashSet<String> getURLTool(String url) throws IOException {
		HashSet<String> hs = new HashSet<String>();
		Document doc = Jsoup.connect(url).get();
				/*.data("query", "Java")
				.userAgent("Mozilla").cookie("auth", "token").timeout(1000)
				.post();
				*/
		Elements links = doc.select("a[href]"); // 获得a标签的数据
		// Elements media = doc.select("[src]"); //获得src属性的数据(备用)
		//Elements imports = doc.select("link[href]"); //获得link标签的数据(备用)

		for (Element link : links) {
			// 获得链接
			String realLink = link.attr("abs:href");
			// 去除空链接
			if (realLink != null && (!realLink.equals(""))) {
				hs.add(realLink);
				System.out.println("URLTool向hs中存放链接: " + realLink);
			}
		}
		return hs;
	}
}
