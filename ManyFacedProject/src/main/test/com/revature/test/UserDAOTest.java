package com.revature.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.beans.User;
import com.revature.data.UserDAO;

public class UserDAOTest {
	
	private static ApplicationContext context; 
	private static UserDAO userDAO;
	
	@BeforeClass 
	public static void initialize() {
		context =new ClassPathXmlApplicationContext("classpath*:dao-beans.xml");
		System.out.println("Before context.getBean"); 
		userDAO = context.getBean(UserDAO.class); 
	}
	
	@Test
	public void testCreate() {
		
		User bean = new User(); 
		bean.setId(1);
		bean.setUsername("Alvin");
		bean.setPassword("poop");
		
		System.out.println("User created");
		
		userDAO.create(bean); 
		
		System.out.println("Username created"); 
	}

}
