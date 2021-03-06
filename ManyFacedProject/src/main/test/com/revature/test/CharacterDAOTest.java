package com.revature.test;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.revature.data.CharacterDAO;
import com.revature.data.HouseDAO;
import com.revature.data.StatusDAO;
import com.revature.data.UserDAO;
import com.revature.beans.Character;
import com.revature.beans.House;
import com.revature.beans.Status;
import com.revature.beans.User;

public class CharacterDAOTest {
	
	private static ApplicationContext context; 
	private static CharacterDAO characterDAO; 
	private static StatusDAO statusDAO;
	private static HouseDAO houseDAO; 
	private static UserDAO userDAO; 
	
	@BeforeClass
	public static void initialize() {
		context = 
				new ClassPathXmlApplicationContext("classpath*:dao-beans.xml");
		System.out.println("Before context.getBean"); 
		characterDAO= context.getBean(CharacterDAO.class); 
		statusDAO = context.getBean(StatusDAO.class);
		houseDAO = context.getBean(HouseDAO.class);
		userDAO = context.getBean(UserDAO.class); 
	}
	
	@Test
	@Ignore
	@Transactional
	public void testCreate() {
		
		Status status = statusDAO.findAll().get(0); 
//		House house = houseDAO.findAll().get(0); 
		User user = userDAO.findOne(50); 
		
		Character bean = new Character(); 
		bean.setId(1);
		bean.setFirstName("Arya");
		bean.setLastName("Stark");
		bean.setGender("Female");
		bean.setHeight(90);
		bean.setAlias("Baby Lady of Winterfell");
		bean.setStatus(status); 
//		bean.setHouse(house);
		System.out.println(bean.toString());
		
		characterDAO.create(bean);
	
		System.out.println("Created");
		
	}
	
	@Test
	@Ignore
	public void testDelete() {
		
		Character bean = characterDAO.findOne(50);
		
		characterDAO.delete(bean);
	}
	
	@Test
	@Ignore
	public void testUpdate() {
		
		Character bean = characterDAO.findOne(50);
		
		bean.setFirstName("Sansa Coalskd");

		System.out.println(bean.toString());
		
		characterDAO.update(bean);
		
		System.out.println("updated: " + bean.toString());
		
		
	}
	
	@Test
	@Ignore
	
	public void testAll() {
		characterDAO.findAll();
		System.out.println("returned" + characterDAO.findAll().toString()); 
	}
	
	@Test
	public void testFindOne() {
		characterDAO.findOne(1); 
		System.out.println("returned" + characterDAO.findOne(1).toString()); 
	}
	
	@Test
	@Ignore
	public void testFindingCharactersInHouse() {
		characterDAO.findAllCharactersInHouse(1);
		System.out.println("returned" + characterDAO.findAllCharactersInHouse(1).toString());
		
	}
}
