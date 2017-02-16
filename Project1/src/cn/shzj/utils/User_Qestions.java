package cn.shzj.utils;

import java.util.ArrayList;

import org.htmlparser.util.ParserException;

public class User_Qestions {
	public static ArrayList<String> getQestions(String link) throws ParserException {
		ArrayList<String> li = User_NodesTool.getNodesMethod("class", "QuestionItem-title",
				"https://www.zhihu.com/people/"+link+"/asks");
		return li;
	}

}
