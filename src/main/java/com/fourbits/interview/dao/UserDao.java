package com.fourbits.interview.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fourbits.interview.model.Role;
import com.fourbits.interview.model.User;

@Repository
public class UserDao {
 
    public User loadUserByUsername(final String username) {
    	System.out.println("loading usr");
    	//TODO: Load user from DB
    	
        User user = new User();
        user.setFirstName("firstName");
        user.setLastName("lastName");
        
        Role r = new Role();
if(username.equals("sup")) {
	user.setUsername("sup");
    user.setPassword("sup");
	r.setName("ROLE_SUPERVISOR");	
    	} else {
    		user.setUsername("user");
            user.setPassword("user");
    		r.setName("ROLE_USER");
    	}
        
        List<Role> roles = new ArrayList<Role>();
        roles.add(r);
        user.setAuthorities(roles);
        return user;
    }
}