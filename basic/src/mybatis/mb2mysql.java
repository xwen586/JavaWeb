package mybatis;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class mb2mysql {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("MyBatis to MySQL demo.");

		try {
			Reader reader = Resources.getResourceAsReader("mybatis/SqlMapConfig.xml");
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			SqlSession session = sqlSessionFactory.openSession();
			
			User newuser = new User();
			newuser.setId(10);
			newuser.setUsername("Gandaulf");
			newuser.setSex("M");
			newuser.setBirthday("1020-02-02");
			newuser.setAddress("Blizzard");
			
			//插入新记录
			int ret = session.insert("my.uMapper.insertUser", newuser);
			System.out.println("Insert a new user. ret=" + ret);
			System.out.println(newuser.toString());
			session.commit();
			
			//查询表
			User user = session.selectOne("my.uMapper.findUserById", 3);
			System.out.println(user.toString());

			//删除新记录
			int retdel = session.delete("my.uMapper.deleteUserById", newuser.getId());
			System.out.println("Delete a user. ret=" + retdel);
			session.commit();

			session.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
		}

	}

}
;