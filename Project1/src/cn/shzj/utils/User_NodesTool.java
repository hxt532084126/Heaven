package cn.shzj.utils;

import java.util.ArrayList;

import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class User_NodesTool {
	public static ArrayList<String> getNodesMethod(String attribute,
			String value, String url) throws ParserException {
		ArrayList<String> li = new ArrayList<String>();
		// 实例化Parser对象
		Parser parser = new Parser();
		// 设置URL
		parser.setURL(url);
		// 设置编码
		parser.setEncoding(parser.getEncoding());
		// 根据标签的属性进行过滤
		HasAttributeFilter filter = new HasAttributeFilter(attribute, value);
		NodeList nodeList = parser.extractAllNodesThatMatch(filter);
		// 循环赋值入li
		for (int i = 0; i < nodeList.size(); i++) {
			li.add(nodeList.elementAt(i).toPlainTextString());
		}
		// 返回ArrayList集合
		return li;
	}

}
