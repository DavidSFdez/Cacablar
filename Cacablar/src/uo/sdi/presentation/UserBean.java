package uo.sdi.presentation;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.User;
import uo.sdi.model.UserStatus;

@ManagedBean(name = "user")
@SessionScoped
public class UserBean implements Serializable {

    private static final long serialVersionUID = -6981760995314343162L;
    
    private User user;

    public UserBean() {
	user = new User();
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public boolean isNotLogged() {
	return user.getId() == null;
    }

    public boolean isLogged() {
	return user.getId() != null;
    }

    public String register() {

	user.setStatus(UserStatus.ACTIVE);

	// tal vez hacer que el método save devuelva el objeto para recoger el
	// id
	try {
	    user = Factories.services.createUsersService().saveUser(user);
	} catch (EntityAlreadyExistsException e) {

	    e.printStackTrace();
	    return "fracaso";
	}

	return "exito";
    }

    public String login() {

	user = Factories.services.createLoginService().verify(user.getLogin(),
		user.getPassword());

	return user == null ? "fracaso" : "exito";
    }

    public String logout() {

	//if (user.getId() != null)
	  //  return "fracaso";

	user = new User();

	return "exito";
    }
}
