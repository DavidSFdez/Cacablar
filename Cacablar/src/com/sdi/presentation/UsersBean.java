package com.sdi.presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.sdi.model.User;

@ManagedBean(name="users")
@RequestScoped
public class UsersBean implements Serializable{

    
    List<User> users;
    
    public UsersBean(){
	users = new ArrayList<User>();
    }
    
    @PostConstruct
    private void rellenar()
    {
	User user = new User();
	user.setLogin("lajkfa");
	user.setPassword("fdlas");
	users.add(user);
    }
}
