package cn.shzj.utils;

import java.util.ArrayList;

import org.htmlparser.util.ParserException;

public class User_Answer {
	public static ArrayList<String> getAnswers(String link) throws ParserException {
		ArrayList<String> li = User_NodesTool.getNodesMethod("class", "ContentItem-title",
				"https://www.zhihu.com/people/"+link+"/answers");
		return li;
	}
}
