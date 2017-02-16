package cn.shzj.utils;

import java.util.ArrayList;

import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class User_Topics {
	// 获取链接中的话题内容
		public static ArrayList<String> getTopic(String link) throws ParserException {
			ArrayList<String> li = new ArrayList<String>();
			// 实例化Parser对象
			Parser parser = new Parser();
			// 设置URL
			parser.setURL("https://www.zhihu.com/people/"+link+"/following/topics");
			// 设置编码
			parser.setEncoding(parser.getEncoding());
			// 根据标签的属性进行过滤
			HasAttributeFilter hasattributefilter = new HasAttributeFilter(
					"aria-haspopup", "true");
			TagNameFilter tagNameFilter = new TagNameFilter("div");
			AndFilter andFilter = new AndFilter(tagNameFilter, hasattributefilter);
			NodeList nodeList = parser.extractAllNodesThatMatch(andFilter);
			for (int i = 0; i < nodeList.size(); i++) {
				li.add(nodeList.elementAt(i).toPlainTextString());
			}
			return li;
		}
}
