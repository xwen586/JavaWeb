package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * 用JDBC访问MySQL例子 (2020-12-15)
 * 引用 mysql-connector-java-8.0.22.jar
 */
public class jdbc2mysql {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Jdbc to MySQL demo.");
		Connection conn = null; // 数据库的连接
		PreparedStatement stmt = null; // 预操作语句
		ResultSet rset = null; // 返回

		String driverClassName = "com.mysql.jdbc.Driver";// com.mysql.cj.jdbc.Driver
		String url = "jdbc:mysql://192.168.99.100:3306/mydb";
		String username = "root";
		String password = "mypwd";

		try {

			Class.forName(driverClassName);// 指定连接类型
			conn = DriverManager.getConnection(url, username, password);

			String sql = "SELECT * FROM mydb.students";
			stmt = conn.prepareStatement(sql);

			rset = stmt.executeQuery();// 执行语句，得到结果集
			while (rset.next()) {
				int uid = rset.getInt(1);
				String uname = rset.getString(2);
				String sex = rset.getString(3);
				int age = rset.getInt(4);
				String tel = rset.getString(5);
				System.out.println(uid + "\t" + uname + "\t" + sex + "\t" + age + "\t" + tel);
			} // 显示数据


		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			// 关闭资源,倒关
            try { if (rset != null) rset.close(); } catch(Exception e) { }
            try { if (stmt != null) stmt.close(); } catch(Exception e) { }
            try { if (conn != null) conn.close(); } catch(Exception e) { }
		}

	}

}
