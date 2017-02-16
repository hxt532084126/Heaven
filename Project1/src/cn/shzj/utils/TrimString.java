package cn.shzj.utils;

public class TrimString {
	public static String getTrimString(String str){
		String str1 = str.replace("https://www.zhihu.com/people/", "").split("/")[0];
		return str1;
	}
}
