package cn.han.myjob.utils;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashSet;


//获取一个HashSet<String>,向指定磁盘位置link存入数据
public class SaveToDiskFile {
	public static void toDisk(HashSet<String> hs,String location){
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(location, true)));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		for (String s : hs) {
			try {
				bw.write(s.toCharArray());
				bw.newLine();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {

			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
