package uo.sdi.presentation;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

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

	user = new User();

	return "exito";
    }

    public void checkIfNotLogged() throws IOException {
	if (isLogged()) {
	    ExternalContext ec = FacesContext.getCurrentInstance()
		    .getExternalContext();
	    ec.redirect(ec.getRequestContextPath() + "/error.xhtml");
	}
    }
    
    public void checkIfLogged() throws IOException {
   	if (!isLogged()) {
   	    ExternalContext ec = FacesContext.getCurrentInstance()
   		    .getExternalContext();
   	    ec.redirect(ec.getRequestContextPath() + "/error.xhtml");
   	}
       }
}
