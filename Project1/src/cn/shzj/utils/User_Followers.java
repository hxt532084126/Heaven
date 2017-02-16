package cn.shzj.utils;

import java.util.ArrayList;

import org.htmlparser.util.ParserException;

public class User_Followers {
	// 获取关注人数(两个)
		public static ArrayList<String> getPeopleNum(String link) throws ParserException {
			ArrayList<String> li = User_NodesTool.getNodesMethod("class", "NumberBoard-value",
					"https://www.zhihu.com/people/"+link+"/answers");
			return li;
		}
}
