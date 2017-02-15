package cn.han.myjob.utilsDatabase;

//出处:http://icycream.blog.51cto.com/8432709/1346693


import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import com.mchange.v2.c3p0.ComboPooledDataSource;

//import common.Logger;(这个包没找到,用来记录日志的,如何记录?)
public class DataSourceFactory {

	// private static final Logger1 log =
	// Logger.getLogger(DataSourceFactory.class);

	public enum DataSourceName {
		
		test1_ds(C.TEST1_DRIVER, C.TEST1_URL, C.TEST1_USER, C.TEST1_PASSWORD), test2_ds(
				C.TEST2_DRIVER, C.TEST2_URL, C.TEST2_USER, C.TEST2_PASSWORD), test3_ds(
						C.TEST3_DRIVER, C.TEST3_URL, C.TEST3_USER, C.TEST3_PASSWORD);
		private String _driver, _url, _user, _password;

		private DataSourceName(String driver, String url, String user,
				String password) {
			this._driver = driver;
			this._url = url;
			this._user = user;
			this._password = password;
		}

		public String getDriver() {
			return _driver;
		}

		public String getUrl() {
			return _url;
		}

		public String getUser() {
			return _user;
		}

		public String getPassword() {
			return _password;
		}
	}

	private static HashMap<DataSourceName, ComboPooledDataSource> dss = new HashMap<DataSourceName, ComboPooledDataSource>();

	private synchronized static ComboPooledDataSource getDataSource(
			final DataSourceName dsName) {
		ComboPooledDataSource ds = null;
		if ((ds = dss.get(dsName)) == null) {
			ds = new ComboPooledDataSource();
			try {
				ds.setDriverClass(dsName.getDriver());
				ds.setUser(dsName.getUser());
				ds.setPassword(dsName.getPassword());
				ds.setJdbcUrl(dsName.getUrl());
				ds.setInitialPoolSize(5);
				ds.setMinPoolSize(1);
				ds.setMaxPoolSize(15);
				ds.setMaxStatements(50);
				ds.setMaxIdleTime(60);
				dss.put(dsName, ds);
			} catch (Exception e) {

				throw new RuntimeException("创建连接池失败：" + dsName.getUrl());
			}
			dss.put(dsName, ds);
		}
		return ds;
	}

	public static Connection getConnection(DataSourceName dsName) {
		ComboPooledDataSource ds = getDataSource(dsName);
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (Exception e) {
			// log.error(e, e);
			throw new RuntimeException("获取连接失败：" + dsName.getUrl());
		}
		return conn;
	}

	public static void close(Connection conn) {
		if (conn != null) {
			try {
				if (!conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				// log.error(e);
			}
			conn = null;
		}
	}
}