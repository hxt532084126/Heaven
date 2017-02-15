package cn.han.myjob.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

//指定路径,获取城市的文本
public class TakeOutFileFromDisk {
	public static HashSet<String> getFile(String location){
		HashSet<String> hs = new HashSet<String>();
		String str;
		try {
			BufferedReader br = new BufferedReader(new FileReader(location));
			while((str = br.readLine())!=null){
				hs.add(str);
			}
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException");
		} catch (IOException e) {
			System.out.println("IOException");
		}
		return hs;
	}
}
