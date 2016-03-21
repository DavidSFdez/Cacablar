package com.sdi.presentation;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.sdi.infrastructure.Factories;
import com.sdi.model.User;

@ManagedBean(name="user")
@SessionScoped
public class UserBean implements Serializable{

    private User user;
    
    public UserBean(){
	user = new User();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public String registrarse(){
	
	if(user.getId()!=null){
	    return "fracaso";
	}
	
	return "exito";
    }
    
    public String login(){
	
	
	//user = Factories.services.createLoginService().verify(user.getName(), user.getPassword());
	
	
	return user==null? "fracaso" : "exito";
    }
}
