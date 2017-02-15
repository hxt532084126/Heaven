package cn.han.myjob.dao;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import cn.han.myjob.bean.BaseCity;
import cn.han.myjob.bean.City;
import cn.han.myjob.bean.LineDetail;
import cn.han.myjob.bean.Station;
import cn.han.myjob.utils.CertainCityBusLinesLinks;
import cn.han.myjob.utils.CityName;
import cn.han.myjob.utils.LineDetailInfo;
import cn.han.myjob.utils.MyTimer;
import cn.han.myjob.utils.StationInfo;
import cn.han.myjob.utilsDatabase.ConnectionInstance;
import cn.han.myjob.utilsDatabase.DeleteDataBaseInfo;

public class BusDao {

	// 主函数用来调试
	@Test
	public void CheckOut() {
		String fs1 = firstStep();
		System.out.println(fs1);
		String fs2 = secondStep();
		System.out.println(fs2);
		String fs3 = thirdStep();
		System.out.println(fs3);
	}

	// 执行第1步,擦除city库并创建新citylinks表,并导入数据
	public String firstStep() {
		// 擦库,建新表
		Connection conn1 = new ConnectionInstance().getConnection1();
		boolean status = DeleteDataBaseInfo.deleteCityInfo1(conn1, "citylinks");
		// 载入数据
		storeCityLink();
		// 返回session状态码
		if (status) {
			return "1e";
		} else {
			return "fail";
		}
	}

	// 执行第2步,擦除line库中所有表,并导入数据
	public String secondStep() {
		Connection conn2 = new ConnectionInstance().getConnection2();
		boolean status = DeleteDataBaseInfo.deleteCityInfo2(conn2);
		// 载入数据
		storeLineDetailInfo();
		// 返回session状态码
		if (status) {
			return "2e";
		} else {
			return "fail";
		}
	}

	// 执行第3步,擦除station库中所有表,并导入数据
	public String thirdStep() {
		Connection conn3 = new ConnectionInstance().getConnection3();
		boolean status = DeleteDataBaseInfo.deleteCityInfo2(conn3);
		// 载入数据
		storeStationInfo();
		// 返回session状态码
		if (status) {
			return "3e";
		} else {
			return "fail";
		}
	}

	// 将城市和线路链接存入city中.
	@Test
	public void storeCityLink() {
		// 记录时间
		long start = System.currentTimeMillis();
		// 获取城市名和城市链接
		HashMap<String, String> hsMap = CityName
				.get("http://www.gongjiao.com/");

		// 拿到数据库1的连接
		Connection conn1 = new ConnectionInstance().getConnection1();
		QueryRunner runner1 = new QueryRunner();

		String sql1 = "insert into citylinks values(?,?,?)";
		for (String biglink : hsMap.keySet()) {
			String city = hsMap.get(biglink);
			// 截取链接: 范例: (http://)dongyang(.gongjiao.com/lines_all.html)
			String cityen = biglink.replace("http://", "").replace(
					".gongjiao.com/lines_all.html", "");
			try {
				runner1.update(conn1, sql1, city, cityen, biglink);
				System.out.println("存入城市 : " + city);
			} catch (SQLException e) {
				System.out.println("存入city数据库失败");
			}
		}
		// 记录总耗时,大约在14分钟左右
		long end = System.currentTimeMillis();
		MyTimer.printTime("将城市和线路链接存入", start, end);
		// 返回操作是否成功状态符
	}

	@Test
	// 将线路信息存入数据库line中
	// 拼音名为表名,link值用来获取城市中所有线路的link值
	public void storeLineDetailInfo() {
		// 记录时间
		long start = System.currentTimeMillis();
		// 拿到数据库1的连接----从数据库中取出(城市的中文名)city,(城市拼音)cityen,(城市公交主链接)biglink
		Connection conn1 = new ConnectionInstance().getConnection1();
		QueryRunner runner1 = new QueryRunner();
		String sql1 = "select cityen,biglink from citylinks";
		List<City> li = new ArrayList<City>();
		try {
			li = runner1.query(conn1, sql1, new BeanListHandler<City>(
					City.class));
		} catch (SQLException e) {
			System.out.println("从数据库city获取数据失败!");
		}
		System.out.println("完成从数据库city获取全部数据!");

		// 拿到数据库line的连接
		Connection conn2 = new ConnectionInstance().getConnection2();
		QueryRunner runner2 = new QueryRunner();

		// 存放失败线路
		List<String> lines = new ArrayList<String>();
		// 向数据库line创建城市列表
		for (City c : li) {
			String sql2 = "CREATE TABLE "
					+ c.getCityen()
					+ "(lineId VARCHAR(30),lineName VARCHAR(30),operatingTime VARCHAR(30),intervalTime VARCHAR(30),ticketPrice VARCHAR(30),company VARCHAR(30),updateTime VARCHAR(30),lineLink VARCHAR(60))";
			try {
				runner2.update(conn2, sql2);
			} catch (SQLException e) {
				System.out.println("数据库line创建表  " + c.getCityen() + "  失败");
				Toolkit.getDefaultToolkit().beep();
			}

			if (c.getBiglink() != null && c.getBiglink() != "") {
				// 记录用时
				long start1 = System.currentTimeMillis();
				// 获取线路名和线路链接
				HashMap<String, String> hsmap = CertainCityBusLinesLinks
						.getLinks(c.getBiglink());
				// 向创建的表中插入 线路名,线路链接及线路详细信息
				for (Entry<String, String> entry : hsmap.entrySet()) {
					String lineName = entry.getKey();
					String lineLink = entry.getValue();
					if (lineLink != null && !lineLink.equals("")
							&& !lineLink.equals("未知")) {
						// 获取该条线路的详细信息
						LineDetail lds = LineDetailInfo.getLineDetails(
								lineName, lineLink);
						String sql3 = "insert into "
								+ c.getCityen()
								+ "(lineId,lineName,operatingTime,intervalTime,ticketPrice,company,updateTime,lineLink)values(?,?,?,?,?,?,?,?)";
						// 存入数据库
						try {
							runner2.update(conn2, sql3, lds.getLineId(),
									lds.getLineName(), lds.getOperatingTime(),
									lds.getIntervalTime(),
									lds.getTicketPrice(), lds.getCompany(),
									lds.getUpdateTime(), lds.getLineLink());
							System.out.println("线路--" + lds.getLineName()
									+ "--存入城市table--" + c.getCityen());
						} catch (SQLException e) {
							System.out.println("存入线路--" + lds.getLineName()
									+ "--失败");
							Toolkit.getDefaultToolkit().beep();
						}
					}
				}
				long end1 = System.currentTimeMillis();
				MyTimer.printTime("----城市:" + c.getCityen() + "-----所有线路存储完毕",
						start1, end1);
				break;           //---------此break用于中断,只导入一个城市----------
			}
		}

		// 记录总耗时,不知多少分钟,从没导完过...
		long end = System.currentTimeMillis();
		MyTimer.printTime("将所有线路名与线路链接信息存入line数据库中", start, end);
		// 打印访问失败的线路链接
		for (String s : lines) {
			System.out.println(s);
		}
		System.out.println("以上是存入失败的线路链接");
	}

	@Test
	// 功能: 将公交线路的总战数量和每站的名字存入到数据库station中
	public void storeStationInfo() {
		// 记录时间
		long start = System.currentTimeMillis();
		// 获取数据库city的连接---用来获取城市名
		Connection conn1 = new ConnectionInstance().getConnection1();
		QueryRunner runner1 = new QueryRunner();
		
		String sql1 = "select * from citylinks"; 
		List<BaseCity> li1 = null;
		try {
			li1 = runner1.query(conn1, sql1, new BeanListHandler<BaseCity>(
					BaseCity.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// cityen用来存放获取city拼音名
		ArrayList<String> cityens = new ArrayList<String>();
		for (BaseCity bs : li1) {
			cityens.add(bs.getCityen());
		}

		// 获取数据库line的连接---从库city中拿到城市名---获取线路的lineId和lineLink
		Connection conn2 = new ConnectionInstance().getConnection2();
		QueryRunner runner2 = new QueryRunner();

		// 获取数据库station的连接---存入station的站点信息
		Connection conn3 = new ConnectionInstance().getConnection3();
		QueryRunner runner3 = new QueryRunner();

		// 进行数据采集
		for (String cityen : cityens) {
			String sql2 = "select * from " + cityen;
			List<LineDetail> lds = null;
			try {
				lds = runner2.query(conn2, sql2,
						new BeanListHandler<LineDetail>(LineDetail.class));
			} catch (SQLException e) {
				System.out.println("--城市" + cityen + "--线路获取失败");
				continue;
			}
			// 获取数据库line中的lineId和LineLink的数据,全部放到liLine
			for (LineDetail ld : lds) {
				// 使用数据库station放入站点信息
				// 0.获取封装好的Station对象
				Station stationDetail = StationInfo.getStationInfo(
						ld.getLineId(), ld.getLineLink());

				// 1.创建城市列表
				String sql3a = "CREATE TABLE " + stationDetail.getLineId()
						+ "(stationName varchar(30))";
				try {
					runner3.update(conn3, sql3a);
				} catch (SQLException e) {
					System.out.println("数据库station创建表--"
							+ stationDetail.getLineId() + "--失败");
					continue;
				}
				// 2.存入站点信息
				String sql3b = "insert into " + stationDetail.getLineId()
						+ " values(?)";
				for (String stationInfo : stationDetail.getStations()) {
					try {
						runner3.update(conn3, sql3b, stationInfo);
					} catch (SQLException e) {
						try {
							runner3.update(conn3, sql3b, "未知");
						} catch (SQLException e1) {
							System.out.println("站点信息存入--"
									+ stationDetail.getLineId() + "--失败");
							continue;
						}
					}
				}
				System.out.println("站点信息已存入--" + stationDetail.getLineId());
			}
			break; //---------此break用于中断,只导入一个城市的线路信息----------
		}
		// 记录总耗时,不知多少分钟,从没爬完过...(建议就爬一个城市就够了)
		long end = System.currentTimeMillis();
		MyTimer.printTime("所有站点信息存入", start, end);
	}
}
