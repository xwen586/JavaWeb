package dbcp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

public class dbcp2mysql {

	public static void main(String[] args) {

		try {
			Properties props = new Properties();
			props.load(Object.class.getResourceAsStream("/dbcp/mysql.properties"));
			//DBCP
			DataSource ds = BasicDataSourceFactory.createDataSource(props);

			Connection conn = ds.getConnection();		
			Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from mydb.students");
            while (rs.next()) {
                int uid = rs.getInt(1);  
                String uname = rs.getString(2);  
                String sex = rs.getString(3);  
                int age = rs.getInt(4);  
                String tel = rs.getString(5);  
                System.out.println(uid + "\t" + uname + "\t" + sex + "\t" + age + "\t" + tel );  
            }
            System.out.println("select * from students success!");
            rs.close();
            st.close();
            conn.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
