package uo.sdi.presentation;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.User;

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
    public boolean isNotLogged()
    {
	return user.getId()==null;
    }
    public boolean isLogged()
    {
	return user.getId()!=null;
    }
  
    
    public String login(){
	
	user = Factories.services.createLoginService().verify(user.getLogin(), user.getPassword());
	
	return user==null? "fracaso" : "exito";
    }
}
