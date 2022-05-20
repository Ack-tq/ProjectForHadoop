package com.hadoop.resources;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
	private static String driver = null;
	private static String url = null;
	private static String username = null;
	private static String password = null;

	static{
		try {
			//通过类获取反射对象，然后使用类对象的类加载器，调用类加载器的获取资源的方法
//			InputStream resourceAsStream = JDBCUtils.class.getClassLoader().getResourceAsStream("E:\\hadoop\\Project4H\\ProjectForHadoop\\src\\main\\resources\\MySQLProperties.properties");
			//获取配置文件的properties对象
			FileInputStream fileInputStream = new FileInputStream("E:\\hadoop\\Project4H\\ProjectForHadoop\\src\\main\\resources\\MySQLProperties.properties");
			Properties properties = new Properties();
			//使用properties对象加载配置文件
			properties.load(fileInputStream/*resourceAsStream*/);
			driver = properties.getProperty("driver");
			url = properties.getProperty("url");
			username = properties.getProperty("name");
			password = properties.getProperty("password");

			//驱动只用加载一次，所以将其放在utils类中
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//获取连接
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}

	//断开连接
	public static void release(Connection conn, Statement sta, ResultSet rs) throws SQLException {
		if (rs != null) rs.close();
		if (sta != null) sta.close();
		if (conn != null) conn.close();
	}
}
