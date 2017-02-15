package cn.han.myjob.utilsDatabase;

import java.sql.Connection;

//获取连接器conn1,conn2,conn3
public class ConnectionInstance {
	public Connection getConnection1(){
		Connection conn1 = null;
		try {
			conn1 = DataSourceFactory.getConnection(DataSourceFactory.DataSourceName.test1_ds);
		} catch (Exception e1) {
			System.out.println(e1);
		}
		return conn1;
	}
	public Connection getConnection2(){
		Connection conn2 = null;
		try {
			conn2 = DataSourceFactory.getConnection(DataSourceFactory.DataSourceName.test2_ds);
		} catch (Exception e1) {
			System.out.println(e1);
		}
		return conn2;
	}
	public Connection getConnection3(){
		Connection conn3 = null;
		try {
			conn3 = DataSourceFactory.getConnection(DataSourceFactory.DataSourceName.test3_ds);
		} catch (Exception e1) {
			System.out.println(e1);
		}
		return conn3;
	}
}
