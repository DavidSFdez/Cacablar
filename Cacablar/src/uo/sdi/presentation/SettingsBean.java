package uo.sdi.presentation;

import java.io.Serializable;
import java.util.Locale;

import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "settings")
@SessionScoped
public class SettingsBean implements Serializable {
    private static final long serialVersionUID = 2L;
    private static final Locale ENGLISH = new Locale("en");
    private static final Locale SPANISH = new Locale("es");
    private Locale locale = new Locale("es");

    @PreDestroy
    public void end() {
	System.out.println("BeanSettings - PreDestroy");
    }

    public Locale getLocale() {
	return (locale);
    }

    public void setSpanish(ActionEvent event) {
	locale = SPANISH;
    }

    public void setEnglish(ActionEvent event) {
	locale = ENGLISH;
    }

}