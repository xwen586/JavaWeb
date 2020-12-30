package dbcp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

/* DBCP2���ӳأ�����SQLite3
 * ���ÿ⣺commons-dbcp-1.4.jar, commons-pool-1.6.jar
 * ʹ�� dbcp2��BasicDataSource
 */
public class dbcp2sqlite {

	public static void main(String[] args) {
		
		//BasicTest();
		//FactoryTest();
		poolTest();
	}

	//set��ʽ�������ӳ�
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

	
	//properties�ļ����÷�ʽ
	public static void FactoryTest() {
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			System.out.println(System.getProperty("user.dir"));//user.dirָ���˵�ǰ��·��
			System.out.println(Thread.currentThread().getContextClassLoader().getResource("1.txt").getPath());
			System.out.println(Object.class.getResource("/1.txt").getPath()); //��Ҫ�ӡ�/��,�����ļ��͵�ǰ���class����һ��
			//System.out.println(Object.class.getClassLoader().getResource("1.txt").getPath());
			/*��ȡ properties�����ļ� sqlite3.properties
			  driverClassName=org.sqlite.JDBC
			  url=jdbc:sqlite:sample.db
			*/
			Properties props = new Properties();
			//���ֶ�ȡ�����ļ��ķ�����ͬ
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
	
	
	//�����ļ����ݴ�ӡ
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

	
	//���ӳز���
	public static void poolTest() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			BasicDataSource pool = new BasicDataSource();
			pool.setDriverClassName("org.sqlite.JDBC");
			pool.setUrl("jdbc:sqlite:sample.db");
			
			pool.setMaxTotal(5);   //������ж��ٸ�Connection
			pool.setInitialSize(3);  //��ʼ�����ӳ�ʱ������
	        //ds.setMaxActive(0);    //���ӳ���������� 0-������
	        //ds.setMaxIdle(3);   //���ﲻ�ᱻ�ͷŵ���������������������Ϊ0ʱ��ʾ�����ơ�

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
