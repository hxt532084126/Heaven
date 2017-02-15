package cn.han.myjob.utilsDatabase;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.Test;

//删除City库中的citylinks表,并创建新表
public class DeleteDataBaseInfo {
	//校验用主函数
	public static void main(String[] args) {
		Connection conn1 = new ConnectionInstance().getConnection1();
		boolean b = deleteCityInfo2(conn1);
		System.out.println(b);
	}

	@Test
	// 删除数据库中的数据,并创建新citylinks表
	public static boolean deleteCityInfo1(Connection conn,String tableName) {;
		try {
			// 这一大段都放在try-catch里面不知道是否合适
			// 1.判断表是否存在
			if (CheckTableExist.exist(conn, tableName)) {
				// 2.删除表
				deleteCityInfo2(conn);
				// 3.创建新表
				Statement st = conn.createStatement();
				String sql2 = "create table "
						+ tableName
						+ "( city varchar(30),cityen varchar(30),biglink varchar(60))";
				st.executeUpdate(sql2);
			} else {
				// 3.创建新表
				Statement st = conn.createStatement();
				String sql2 = "create table "
						+ tableName
						+ "( city varchar(30),cityen varchar(30),biglink varchar(60))";
				st.executeUpdate(sql2);
			}
			return true;
		} catch (SQLException e) {
			// 处理过程中发生异常返回false
			return false;
		}
	}

	// 删除数据库1中的数据,并创建新citylinks表
	@Test
	public static boolean deleteCityInfo2(Connection conn) {
		try {
			// ****使用元数据获取表名
			// 出处:http://www.tuicool.com/articles/2eQNZf
			ArrayList<String> li = new ArrayList<String>();
			DatabaseMetaData dbmd = conn.getMetaData();
			String[] types = { "TABLE" };
			ResultSet rs = dbmd.getTables(null, null, "%", types);
			while (rs.next()) {
				li.add(rs.getString("TABLE_NAME")); // 表名
			}

			// 删除所有表
			for (String tableName : li) {
				System.out.println(tableName);
				Statement st = conn.createStatement();
				String sql2 = "drop table " + tableName;
				st.executeUpdate(sql2);
			}
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
}
