package cn.han.myjob.utilsDatabase;

import java.sql.Connection;

//判断连接是否成功,不成功说明库不存在
public class CheckDataBaseExist {
	public static boolean exist1(){
		Connection conn1 = null;
		try {
			conn1 = DataSourceFactory.getConnection(DataSourceFactory.DataSourceName.test1_ds);
		} catch (Exception e1) {
			return false;
		}
		//只赋值不使用,算没用过conn1,以下代码仅仅是用来避免警告
		if(conn1 != null ){
			return true;
		}
		return true;
	}
	public static boolean exist2(){
		Connection conn2 = null;
		try {
			conn2 = DataSourceFactory.getConnection(DataSourceFactory.DataSourceName.test2_ds);
		} catch (Exception e1) {
			return false;
		}
		//只赋值不使用,算没用过conn1,以下代码仅仅是用来避免警告
		if(conn2 != null ){
			return true;
		}
		return true;
	}
	public static boolean exist3(){
		Connection conn3 = null;
		try {
			conn3 = DataSourceFactory.getConnection(DataSourceFactory.DataSourceName.test3_ds);
		} catch (Exception e1) {
			return false;
		}
		//只赋值不使用,算没用过conn1,以下代码仅仅是用来避免警告
		if(conn3 != null ){
			return true;
		}
		return true;
	}
}
