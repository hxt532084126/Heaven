package cn.han.myjob.utilsDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

//出处:http://blog.csdn.net/believejava/article/details/38587879
//虽然说似乎所有数据库都适用,但博主说可能适用,运用前还需校验(mysql数据库运行下表现正常)


//判断表tableName是否存在与conn代表的库中
public class CheckTableExist {
	public static boolean exist(Connection conn, String tableName) {
		try {
			ResultSet rs = conn.getMetaData().getTables(null, null,
					tableName, null);
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			return false;
		}
	}
}
