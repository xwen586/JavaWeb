package dbcp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.commons.dbcp.BasicDataSource;

/* DBCP���ӳأ�����SQLite3
 * ���ÿ⣺commons-dbcp-1.4.jar, commons-pool-1.6.jar
 * ʹ�� dbcp��BasicDataSource
 */
public class dbcp1sqlite {
	public static void main(String[] args) {

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			//�������ӳ�
			BasicDataSource ds = new BasicDataSource();
	        ds.setDriverClassName("org.sqlite.JDBC");//���ݿ�����
	        ds.setUrl("jdbc:sqlite:sample.db"); //���ݿ��ַ
	        ds.setInitialSize(5);  //��ʼ�����ӳ�ʱ������
	        ds.setMaxActive(0);    //���ӳ���������� 0-������
	        ds.setMaxIdle(3);   //���ﲻ�ᱻ�ͷŵ���������������������Ϊ0ʱ��ʾ�����ơ�

	        //�������ݿ�
			conn = ds.getConnection();
			st = conn.createStatement();
            rs = st.executeQuery("select * from person;");
            while (rs.next()) {
                System.out.println("name = " + rs.getString("name") +
                		";  job = " + rs.getString("jobs"));
            }
            System.out.println("select * from person success!");
            System.out.println("NumActive: " + ds.getNumActive());
            System.out.println("NumIdle: " + ds.getNumIdle()); //MaxIdle-Active

            ds.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
            try { if (rs != null) rs.close(); } catch(Exception e) { }
            try { if (st != null) st.close(); } catch(Exception e) { }
            try { if (conn != null) conn.close(); } catch(Exception e) { }
        }
	}
}
