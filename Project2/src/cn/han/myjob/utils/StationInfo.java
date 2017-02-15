package cn.han.myjob.utils;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.han.myjob.bean.Station;

//获取获取
public class StationInfo {
	public static Station getStationInfo(String lineId,String lineLink){
		Station station = new Station();
		//给station设定初值
		station.setLineId(lineId);
		ArrayList<String> s = new ArrayList<String>();
		station.setStations(s);
		//获取链接中的信息
		Document doc = null;
		try {
			doc = Jsoup.connect(lineLink).get();
		} catch (IOException e) {
			//防止被网址防抓取机制误伤,在下静静地等待1s
			try {
				Thread.sleep(1000);
				System.out.println("--------安静的等待1s------");
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			try {
				doc = Jsoup.connect(lineLink).get();
			} catch (IOException e1) {
				s.add("未知");
				return station;
			}
		}
		// 到http://dongyang.gongjiao.com/lines_all.html里获取
		Elements eles1 = doc.getElementsByClass("gj01_line_img JS-up clearfix");
		//不知道为什么Elements first()不能用...只能循环了
		for(Element e1:eles1){
			Elements eles2=e1.getElementsByTag("li");
			for(Element e2 : eles2){
				s.add(e2.text());
			}
			break;
		}
		station.setStations(s);	
		return station;
	}
}