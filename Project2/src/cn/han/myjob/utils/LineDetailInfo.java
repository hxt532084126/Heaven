package cn.han.myjob.utils;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.han.myjob.bean.LineDetail;

public class LineDetailInfo {
	public static LineDetail getLineDetails(String lineName, String lineLink) {
		// 建立一个HashSet<LineDetail>用来存放返回值
		LineDetail lineDetail = new LineDetail();
		// 截取链接作为线路的唯一标识
		String lineId = lineLink.substring(lineLink.lastIndexOf("/") + 1);
		// 因为很有可能有些信息会缺失,所以全部初始化为"未知"
		lineDetail.setLineId(lineId);
		lineDetail.setLineName(lineName);
		lineDetail.setLineLink(lineLink);
		lineDetail.setOperatingTime("未知");
		lineDetail.setIntervalTime("未知");
		lineDetail.setTicketPrice("未知");
		lineDetail.setCompany("未知");
		lineDetail.setUpdateTime("未知");
		// 获取以上信息
		Document doc = null;
		try {
			doc = Jsoup.connect(lineLink).get();
		} catch (IOException e) {
			//防止被网址防抓取机制误伤,在下静静地等待1s
			try {
				Thread.sleep(1000);
				System.out.println("--------安静的等待1s------");
			} catch (InterruptedException e2) {
				e2.printStackTrace();
			}
			try {
				doc = Jsoup.connect(lineLink).get();
			} catch (IOException e1) {
				return lineDetail;
			}
		}
		// 获取 属性class为"list"的标签元素,然后获取底下的第一个子标签div
		// 再获取div下的所有<a>超链接子标签
		Elements eles = doc.getElementsByTag("dd");
		for (Element e : eles) {
			if (e.text().contains("运营时间")) {
				String operatingTime = e.text()
						.replaceAll("[\u4e00-\u9fa5]", "").substring(1);
				lineDetail.setOperatingTime(operatingTime);
				// 有可能把中文的"未知"也给删了,所以加个判断
				if (operatingTime.equals("")) {
					operatingTime = "未知";
					lineDetail.setOperatingTime("未知");
				}
			}
			if (e.text().contains("发车间隔")) {
				String intervalTime = e.text().substring(5);
				lineDetail.setIntervalTime(intervalTime);
			}
			if (e.text().contains("票价信息")) {
				String ticketPrice = e.text().substring(5);
				lineDetail.setTicketPrice(ticketPrice);
			}
			if (e.text().contains("汽车公司")&&(!e.text().contains("<"))&&(!e.text().contains("/"))) {
				String company = e.text().substring(5);
				lineDetail.setCompany(company);
			}
			if (e.text().contains("更新时间")) {
				String updateTime = e.text().substring(5);
				lineDetail.setUpdateTime(updateTime);
			}
		}
		// 返回HashSet数组
		return lineDetail;

	}
}
