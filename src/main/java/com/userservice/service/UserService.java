package com.userservice.service;

import com.userservice.bean.User;
import com.userservice.dao.UserDao;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @Transactional(readOnly = false)
public class UserService {
	
	@Autowired
	private UserDao dao;

	public User save(User User) {
            return dao.save(User);		
	}

	public void update(User user) {
		dao.update(user);		
	}

	public void delete(Integer id) {
		dao.delete(id);		
	}

	public User findById(Integer id) {
		return dao.findById(id);
	}

	public List<User> findAll() {
		return dao.findAll();
	}
        
        /*
        Método Vulnerável
        */
	public User findByIdNative(Integer id) {
            return (User) dao.executeNativeSingle("select * from User where id = "+id, User.class);
	}
        /*
        Método Vulnerável
        */
	public List<User> findAllNative() {
            return dao.executeNativeList("select * from User");
	}
}