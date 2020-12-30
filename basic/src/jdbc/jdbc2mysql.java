package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * ��JDBC����MySQL���� (2020-12-15)
 * ���� mysql-connector-java-8.0.22.jar
 */
public class jdbc2mysql {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Jdbc to MySQL demo.");
		Connection conn = null; // ���ݿ������
		PreparedStatement stmt = null; // Ԥ�������
		ResultSet rset = null; // ����

		String driverClassName = "com.mysql.jdbc.Driver";// com.mysql.cj.jdbc.Driver
		String url = "jdbc:mysql://192.168.99.100:3306/mydb";
		String username = "root";
		String password = "mypwd";

		try {

			Class.forName(driverClassName);// ָ����������
			conn = DriverManager.getConnection(url, username, password);

			String sql = "SELECT * FROM mydb.students";
			stmt = conn.prepareStatement(sql);

			rset = stmt.executeQuery();// ִ����䣬�õ������
			while (rset.next()) {
				int uid = rset.getInt(1);
				String uname = rset.getString(2);
				String sex = rset.getString(3);
				int age = rset.getInt(4);
				String tel = rset.getString(5);
				System.out.println(uid + "\t" + uname + "\t" + sex + "\t" + age + "\t" + tel);
			} // ��ʾ����


		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			// �ر���Դ,����
            try { if (rset != null) rset.close(); } catch(Exception e) { }
            try { if (stmt != null) stmt.close(); } catch(Exception e) { }
            try { if (conn != null) conn.close(); } catch(Exception e) { }
		}

	}

}
