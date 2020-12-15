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
			
			String sql = "SELECT * FROM mydb.students"; // WHERE username=?
			pstmt = conn.prepareStatement(sql);
			//pstmt.setNString(1, username);

			rs = pstmt.executeQuery();//ִ����䣬�õ������
			while(rs.next()) {
                int uid = rs.getInt(1);  
                String uname = rs.getString(2);  
                String sex = rs.getString(3);  
                int age = rs.getInt(4);  
                String tel = rs.getString(5);  
                System.out.println(uid + "\t" + uname + "\t" + sex + "\t" + age + "\t" + tel );  
            }//��ʾ����  
			rs.close();
			pstmt.close();  
			conn.close();
			

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
