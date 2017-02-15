package cn.han.myjob.test;

import java.sql.Connection;

import cn.han.myjob.utilsDatabase.CheckTableExist;
import cn.han.myjob.utilsDatabase.ConnectionInstance;


public class TestDemo1 {
	public static void main(String[] args) {
		Connection conn1 = new ConnectionInstance().getConnection1();
		String tableName = "citylinks";
		boolean b = CheckTableExist.exist(conn1, tableName);
		System.out.println(b);
	}
}
