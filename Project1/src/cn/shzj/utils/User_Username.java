package cn.shzj.utils;

import java.util.ArrayList;

import org.htmlparser.util.ParserException;

public class User_Username {
	public static String getUserName(String link) throws ParserException {
		ArrayList<String> li = User_NodesTool.getNodesMethod("class", "ProfileHeader-name",
				"https://www.zhihu.com/people/"+ link +"/answers");
		return li.get(0);
	}
}
