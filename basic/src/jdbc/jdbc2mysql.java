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
		Connection conn = null;   //���ݿ������
		Statement stat = null;   //�������
        PreparedStatement pstmt = null; //Ԥ�������
		ResultSet rs = null;  //����
		
		String driverClassName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://192.168.99.100:3306/mydb";
		String username = "root";
		String password = "mypwd";

		try {
			
			Class.forName(driverClassName);//ָ����������
			conn = DriverManager.getConnection(url, username, password);
			

		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			//�ر���Դ,����
			try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();  //����Ҫ��
			} catch (Exception e) {
			}
		}
		
	}

}
