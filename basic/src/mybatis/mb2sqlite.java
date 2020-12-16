package mybatis;

import java.io.InputStream;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class mb2sqlite {

	public static void main(String[] args) {
		System.out.println("MyBatis to SQLite3 demo.");

		try {

			// 1.读取mybatis的配置文件
			String resource = "mybatis/SqlMapConfig.xml";
			// 使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
			//InputStream is = mb2sqlite.class.getClassLoader().getResourceAsStream(resource);
			InputStream is = Resources.getResourceAsStream("mybatis/SqlMapConfig.xml");
			// 2.创建SqlSessionFactory工厂
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

			// 3.使用工厂生产SqlSession对象
			SqlSession session = sqlSessionFactory.openSession();

			/* 映射sql的标识字符串，
			 * my.uMapper是userMapper.xml文件中<mapper namespace="">标签的namespace属性的值
			 * getUser是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
			 */
			String statement = "my.uMapper.findUserById";// 映射sql的标识字符串

			// 4.执行Sql语句,返回一个唯一user对象
			User user = session.selectOne(statement, 1);
			System.out.println(user.toString());

			// 5.释放资源
			session.close();
			is.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
		}

		
		//使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
		try {
			Reader reader = Resources.getResourceAsReader("mybatis/SqlMapConfig.xml");
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			SqlSession session = sqlSessionFactory.openSession();
			User user = session.selectOne("my.uMapper.findUserById", 2);
			System.out.println(user.toString());

			session.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
		}

	}
}
