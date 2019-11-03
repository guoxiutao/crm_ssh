package cn.itcast.test;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import cn.itcast.service.UserService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DBTest {
	
	private String svnEclipseName;
	
	private String svnName;
	
	@Resource(name="sessionFactory")
	private SessionFactory sf;
	@Resource(name="userDao")
	private UserDao ud;
	@Resource(name="userService")
	private UserService us;
	@Test
	//测试Service执行
	public void fun4(){
		User u = new User();
		u.setUser_name("测试Service2");
		
		us.regist(u);
	}
	
	@Test
	//测试Dao执行
	public void fun3(){
		User u = new User();
		u.setUser_name("测试Dao");
		
		ud.save(u);
	}
	
	@Test
	//测试hibernate框架搭建是否成功
	public void fun2(){
		Session session = sf.openSession();
		
		Transaction tx = session.beginTransaction();
		//------------------------------------------------------------
		User u = new User();
		u.setUser_name("测试整合hibernate与连接池");
		
		session.save(u);
		//------------------------------------------------------------
		tx.commit();
		
		session.close();
		sf.close();
	}
	@Test
	//测试hibernate框架搭建是否成功
	public void fun1(){
		
		Configuration conf = new Configuration().configure();
		
		SessionFactory sf = conf.buildSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction tx = session.beginTransaction();
		//------------------------------------------------------------
		User u = new User();
		u.setUser_name("测试hibernate");
		
		session.save(u);
		//------------------------------------------------------------
		tx.commit();
		
		session.close();
		sf.close();
		
		
	}
}
