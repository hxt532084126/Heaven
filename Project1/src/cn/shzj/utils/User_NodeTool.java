package cn.shzj.utils;

import java.util.ArrayList;

import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

//获取所有父节点下子节点的内容
public class User_NodeTool {
	public static ArrayList<String> getNodeMethod(String attribute,
			String value, String url) throws ParserException {
		// 实例化Parser对象
		Parser parser = new Parser();
		// 设置URL
		parser.setURL(url);
		// 设置编码
		parser.setEncoding(parser.getEncoding());
		// 根据标签的属性进行过滤
		HasAttributeFilter filter = new HasAttributeFilter(attribute, value);
		NodeList nodeList = parser.extractAllNodesThatMatch(filter);
		ArrayList<String> li = new ArrayList<String>();
		// 循环赋值入li
		for (int i = 0; i < nodeList.size(); i++) {
			NodeList children = nodeList.elementAt(i).getChildren();
			for (int j = 0; j < children.size(); j++) {
				li.add(children.elementAt(j).toPlainTextString());
			}
		}
		// 返回ArrayList集合
		return li;
	}
}
