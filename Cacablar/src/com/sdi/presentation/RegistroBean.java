package com.sdi.presentation;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.infrastructure.Factories;
import com.sdi.model.User;
import com.sdi.model.UserStatus;

@ManagedBean(name="registro")
@RequestScoped
public class RegistroBean implements Serializable{

    private String login;
    private String name;
    private String surname;
    private String email;
    private String pwd1;
    private String pwd2;
    
    public RegistroBean(){}

    
    public String registrarse(){
	
	User user = new User();
	user.setLogin(login);
	user.setName(name);
	user.setSurname(surname);
	user.setEmail(email);
	//con primeFaces ya compruebo que las 2 contraseñas sean iguales
	user.setPassword(pwd1);
	user.setStatus(UserStatus.ACTIVE);
	
	//tal vez hacer que el método save devuelva el objeto para recoger el id
	try {
	    Factories.services.createUsersService().saveUser(user);
	} catch (EntityAlreadyExistsException e) {
	    
	    e.printStackTrace();
	    return "fracaso";
	}
	
	return "exito";
    }
    
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd1() {
        return pwd1;
    }

    public void setPwd1(String pwd1) {
        this.pwd1 = pwd1;
    }

    public String getPwd2() {
        return pwd2;
    }

    public void setPwd2(String pwd2) {
        this.pwd2 = pwd2;
    }

   
    
    
    
}
