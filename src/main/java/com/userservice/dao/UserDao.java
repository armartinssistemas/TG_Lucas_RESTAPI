package com.userservice.dao;

import com.userservice.bean.User;
import java.io.Serializable;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends AbstractDao<User, Integer> implements Serializable{
    
}
