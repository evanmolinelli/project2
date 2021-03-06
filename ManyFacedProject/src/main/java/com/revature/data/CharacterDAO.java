package com.revature.data;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.Character;
import com.revature.beans.House;
import com.revature.beans.Location;

public class CharacterDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void create(Character character) {
		//opens a session, beings transaction
		sessionFactory.getCurrentSession().save(character);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(Character character) {
		sessionFactory.getCurrentSession().saveOrUpdate(character);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Character character) {
		sessionFactory.getCurrentSession().delete(character);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Character> findAll() {
		return sessionFactory.getCurrentSession()
				.createCriteria(Character.class).list();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Character findOne(int id) {
		return (Character) sessionFactory.getCurrentSession().createCriteria(Character.class)
				.add(Restrictions.eq("id", id)).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Character> findAllCharactersInHouse(int id) {
		ArrayList<Character> list = (ArrayList<Character>) sessionFactory.getCurrentSession()
				.createCriteria(Character.class)
				.add(Restrictions.eq("house.id", id))
				.list();
		Set<Character> s = new LinkedHashSet<Character>(list);
		return s;
	}

}
