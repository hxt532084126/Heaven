package cn.shzj.utils;

import java.util.ArrayList;

import org.htmlparser.util.ParserException;

public class User_Activities {
	public static ArrayList<String> getActivities(String link)
			throws ParserException {
		// 获取动作和时间
		ArrayList<String> li1 = User_NodeTool.getNodeMethod("class",
				"ActivityItem-meta", "https://www.zhihu.com/people/" + link
						+ "/activities");
		// 获取文章内容
		ArrayList<String> li2 = User_NodesTool.getNodesMethod("class",
				"ContentItem-title", "https://www.zhihu.com/people/" + link
						+ "/activities");
		ArrayList<String> li = new ArrayList<String>();
		//写入动作,写入时间,写入内容
		int j = 0;
		for (int i = 0; i < li1.size(); i++) {
			li.add(li1.get(i));
			if (i % 2 == 1) {
					li.add(li2.get(j));
					j++;
			}
		}
		return li ;
	}
}
