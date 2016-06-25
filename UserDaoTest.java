package test.dao;

import org.junit.Test;

import WebProject.user.dao.UserDao;
import WebProject.user.domain.User;

public class UserDaoTest {
	@Test
	public void testFindByUsername()
	
	{
		UserDao userDao =new UserDao();
	}
	@Test
	public void testAdd()
	{
		UserDao userDao =new UserDao();
		User user =new User();
		user.setUsername("666");
		user.setPassword("666");
		userDao.add(user);
	}

}
