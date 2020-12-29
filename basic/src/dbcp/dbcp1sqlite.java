package dbcp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.commons.dbcp.BasicDataSource;

/* DBCP连接池，访问SQLite3
 * 引用库：commons-dbcp-1.4.jar, commons-pool-1.6.jar
 * 使用 dbcp的BasicDataSource
 */
public class dbcp1sqlite {
	public static void main(String[] args) {

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			//创建连接池
			BasicDataSource ds = new BasicDataSource();
	        ds.setDriverClassName("org.sqlite.JDBC");//数据库驱动
	        ds.setUrl("jdbc:sqlite:sample.db"); //数据库地址
	        ds.setInitialSize(5);  //初始化连接池时连接数
	        ds.setMaxActive(0);    //连接池最大连接数 0-不限制
	        ds.setMaxIdle(3);   //池里不会被释放的最多空闲连接数量。设置为0时表示无限制。

	        //访问数据库
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
