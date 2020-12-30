package dbcp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

/* DBCP2连接池，访问SQLite3
 * 引用库：commons-dbcp-1.4.jar, commons-pool-1.6.jar
 * 使用 dbcp2的BasicDataSource
 */
public class dbcp2sqlite {

	public static void main(String[] args) {
		
		//BasicTest();
		//FactoryTest();
		poolTest();
	}

	//set方式配置连接池
	public static void BasicTest() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			BasicDataSource ds = new BasicDataSource();
	        ds.setDriverClassName("org.sqlite.JDBC");
	        ds.setUrl("jdbc:sqlite:sample.db");

			conn = ds.getConnection();
			st = conn.createStatement();
            rs = st.executeQuery("select * from person;");
            while (rs.next()) {
                System.out.println("name = " + rs.getString("name") +
                		";  job = " + rs.getString("jobs"));
            }
            System.out.println("select * from person success!");
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

	
	//properties文件配置方式
	public static void FactoryTest() {
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			System.out.println(System.getProperty("user.dir"));//user.dir指定了当前的路径
			System.out.println(Thread.currentThread().getContextClassLoader().getResource("1.txt").getPath());
			System.out.println(Object.class.getResource("/1.txt").getPath()); //需要加“/”,配置文件和当前类的class放在一起
			//System.out.println(Object.class.getClassLoader().getResource("1.txt").getPath());
			/*读取 properties配置文件 sqlite3.properties
			  driverClassName=org.sqlite.JDBC
			  url=jdbc:sqlite:sample.db
			*/
			Properties props = new Properties();
			//三种读取属性文件的方法相同
			//props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("dbcp/sqlite3.properties"));
			props.load(Object.class.getResourceAsStream("/dbcp/sqlite3.properties"));
			//props.load(new BufferedReader(new FileReader("bin/dbcp/sqlite3.properties")));
			printAllProperty(props);
			//DBCP
			BasicDataSource ds = BasicDataSourceFactory.createDataSource(props);

			conn = ds.getConnection();
			st = conn.createStatement();
            rs = st.executeQuery("select * from person;");
            while (rs.next()) {
                System.out.println("name = " + rs.getString("name") +
                		";  job = " + rs.getString("jobs"));
            }
            System.out.println("select * from person success!");
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
	
	
	//属性文件内容打印
	private static void printAllProperty(Properties props)  
	{  
		@SuppressWarnings("rawtypes")
		Enumeration en = props.propertyNames();
		while (en.hasMoreElements()) {
			String key = (String) en.nextElement();
			String value = props.getProperty(key);
			System.out.println(key + " : " + value);
		}
	}

	
	//连接池测试
	public static void poolTest() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			BasicDataSource pool = new BasicDataSource();
			pool.setDriverClassName("org.sqlite.JDBC");
			pool.setUrl("jdbc:sqlite:sample.db");
			
			pool.setMaxTotal(5);   //最大能有多少个Connection
			pool.setInitialSize(3);  //初始化连接池时连接数
	        //ds.setMaxActive(0);    //连接池最大连接数 0-不限制
	        //ds.setMaxIdle(3);   //池里不会被释放的最多空闲连接数量。设置为0时表示无限制。

            for(int i=1; i<=6; i++){
                conn = pool.getConnection();
                System.out.println(i+"  "+conn);
                if(i==5){
                    conn.close();
                }
	            System.out.println("NumActive: " + pool.getNumActive());
	            System.out.println("NumIdle: " + pool.getNumIdle());//=Total-Active
            }

            pool.close();
            System.out.println("pool close!");

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
