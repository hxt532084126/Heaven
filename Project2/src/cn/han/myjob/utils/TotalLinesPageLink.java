package cn.han.myjob.utils;

import java.util.HashSet;


//获取所有的城市的线路网址
public class TotalLinesPageLink {
	//@Test
	public static HashSet<String> getTotalLines(String weblink){
		//获取link网址下的第一层第二层的链接.
		HashSet<String> AllLink = TotalURL.getTotalURL(weblink);
		HashSet<String> cityLink = new HashSet<String>();
		//遍历所有链接,截取/lines_all.html结尾的的所有链接
		for(String str : AllLink){
			if(str.endsWith("/lines_all.html")){
				cityLink.add(str);
			}
		}
		//SaveToDiskFile.toDisk(cityLink, "C://cityLines.txt");
		return cityLink;
	}
}
