package c3p0;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class c3p02sqlite {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//BasicTest();
		poolTest();
	}
		
	public static void BasicTest() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;

        try {

        	//��ȡĬ�������ļ���c3p0-config.xml �� c3p0.properties���������ڸ�Ŀ¼��
        	ComboPooledDataSource ds = new ComboPooledDataSource("sqlite3");
        	System.out.println(ds.getProperties());//������������������
        	
        	//ͨ��set������������
			ds.setDriverClass("org.sqlite.JDBC");
        	ds.setJdbcUrl("jdbc:sqlite:sample.db");
        	ds.setUser("");
        	ds.setPassword("");
        	ds.setInitialPoolSize(3);
        	ds.setMaxPoolSize(5);
            ds.setMinPoolSize(2);
            ds.setAcquireIncrement(1);
        	
            conn = ds.getConnection();
            stmt = conn.createStatement();
            rset = stmt.executeQuery("select * from person;");
            while (rset.next()) {
                System.out.println("name = " + rset.getString("name") +
                		";  job = " + rset.getString("jobs"));
            }
            System.out.println("select * from person success!");
            System.out.println("NumActive: " + ds.getNumBusyConnections());
            System.out.println("NumIdle: " + ds.getNumIdleConnections());//=init-busy
            ds.close();

        } catch(SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
			e.printStackTrace();
		} finally {
            try { if (rset != null) rset.close(); } catch(Exception e) { }
            try { if (stmt != null) stmt.close(); } catch(Exception e) { }
            try { if (conn != null) conn.close(); } catch(Exception e) { }
        }

	}

	
	//�������ӳ�
	public static void poolTest() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;

        try {
        	//��ȡĬ�������ļ�
        	ComboPooledDataSource pool = new ComboPooledDataSource();
        	pool.setDriverClass("org.sqlite.JDBC");
        	pool.setJdbcUrl("jdbc:sqlite:sample.db");
        	pool.setInitialPoolSize(3);
        	pool.setMaxPoolSize(6);
        	pool.setMinPoolSize(2);
        	pool.setAcquireIncrement(3);

        	//��ӡ���õ����Ӷ���  �����ļ��������10,�ر�һ�����ܻ���ȥһ����Ȼ����Ա���������
            for(int i=1; i<=7; i++){
                conn = pool.getConnection();
                System.out.println(i+"  "+conn);
                if(i==5){
                    conn.close();
                }
	            System.out.println("NumActive: " + pool.getNumBusyConnections());
	            System.out.println("NumIdle: " + pool.getNumIdleConnections());//=init-busy
            }
            
            pool.close();
            System.out.println("pool close!");

        } catch(SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
			e.printStackTrace();
        } finally {
            try { if (rset != null) rset.close(); } catch(Exception e) { }
            try { if (stmt != null) stmt.close(); } catch(Exception e) { }
            try { if (conn != null) conn.close(); } catch(Exception e) { }
        }
        
	}
	
	
}
