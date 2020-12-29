package jndi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import java.rmi.registry.LocateRegistry;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.cj.jdbc.MysqlDataSource;

public class jndiDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			//LocateRegistry.createRegistry(1099);
            //System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
            //System.setProperty(Context.PROVIDER_URL, "rmi://localhost:1099");
            
            InitialContext initctx = new InitialContext();
            
            MysqlDataSource myds = new MysqlDataSource();
            //设置参数
            myds.setUrl("jdbc:mysql://192.168.99.100:3306/mydb");
            myds.setUser("root");
            myds.setPassword("mypwd");
            //myds.setDatabaseName("mydb");
            //myds.setPortNumber(3306);
            //myds.setServerName("localhost");
            
            //用JNDI绑定DataSource
            initctx.bind("myds", myds);
            MysqlDataSource  ods = (MysqlDataSource)initctx.lookup("myds");
            Connection con = ods.getConnection();
            Statement st = con.createStatement();
            
            //Context envContext = (Context) initContext.lookup("java:/comp/env/context.xml");
            //DataSource ds = (DataSource) envContext.lookup("jdbc/sqlite");
            //Connection conn = ds.getConnection();
            //PreparedStatement ps = conn.prepareStatement("select * from person;");
            //ResultSet rs = ps.executeQuery();
            ResultSet rs = st.executeQuery("select * from person;");
            while (rs.next()) {
                System.out.println("name = " + rs.getString("name") +
                		";  job = " + rs.getString("jobs"));
            }
            System.out.println("3) select * from person success!");
            System.out.println(rs.next());
            rs.close();
            con.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}

}
