package dbcp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.h2.jdbcx.JdbcConnectionPool;

/*
 * 
 * classpath commons-pool2-2.9.jar:commons-dbcp2-2.8.0.jar:commons-logging-1.2.jar:h2-1.4.197.jar
 */
public class pool2h2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();

	}

	//H2自带jdbc连接池
	public static void test() {
		Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;
        String url = "jdbc:h2:E:/workspace/sts4/JavaWeb/basic/h2db";
        
        try {
            JdbcConnectionPool cp = JdbcConnectionPool.create(
            		url, "root", "root");
            System.out.println("Setting up data source Done.");
            
            conn = cp.getConnection();
            stmt = conn.createStatement();
            rset = stmt.executeQuery("SELECT * from students");
            
            int numcols = rset.getMetaData().getColumnCount();
            while(rset.next()) {
                for(int i=1;i<=numcols;i++) {
                    System.out.print("\t" + rset.getString(i));
                }
                System.out.println("");
            }
            
            System.out.println("Finished !");
            
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rset != null) rset.close(); } catch(Exception e) { }
            try { if (stmt != null) stmt.close(); } catch(Exception e) { }
            try { if (conn != null) conn.close(); } catch(Exception e) { }
        }
        
	}
	
	
	public static void test1() {
		Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;
        String url = "jdbc:h2:E:/workspace/sts4/JavaWeb/basic/h2db;user=root;password=root";
        
        try {
        	
            Class.forName("org.h2.Driver");
            
            ConnectionFactory connFactory =
                    new DriverManagerConnectionFactory(url, null);
            PoolableConnectionFactory poolableConnectionFactory =
                    new PoolableConnectionFactory(connFactory, null);
            ObjectPool<PoolableConnection> connectionPool =
                    new GenericObjectPool<>(poolableConnectionFactory);
            PoolingDataSource<PoolableConnection> ds =
                    new PoolingDataSource<>(connectionPool);
            System.out.println("Setting up data source Done.");
            
            conn = ds.getConnection();
            stmt = conn.createStatement();
            rset = stmt.executeQuery("SELECT * from students");//
            
            int numcols = rset.getMetaData().getColumnCount();
            while(rset.next()) {
                for(int i=1;i<=numcols;i++) {
                    System.out.print("\t" + rset.getString(i));
                }
                System.out.println("");
            }
            
            System.out.println("Finished !");
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rset != null) rset.close(); } catch(Exception e) { }
            try { if (stmt != null) stmt.close(); } catch(Exception e) { }
            try { if (conn != null) conn.close(); } catch(Exception e) { }
        }
        
	}
}
