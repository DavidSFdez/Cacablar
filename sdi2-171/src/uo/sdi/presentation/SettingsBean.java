package uo.sdi.presentation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import alb.util.log.Log;

@ManagedBean(name = "settings")
@SessionScoped
public class SettingsBean implements Serializable {
    private static final long serialVersionUID = 2L;
    // private static final Locale ENGLISH = new Locale("en");
    // private static final Locale SPANISH = new Locale("es");
    private Locale locale = new Locale("es");

    private Map<String, Idioma> mapaIdiomas = new HashMap<String, Idioma>();

    List<Idioma> idiomas;

    private final static String PATH_TO_PROJECT = "S:\\";

    public SettingsBean() {

    }

    @PostConstruct
    private void process() {
	locale = new Locale("es");
	File directory = new File(PATH_TO_PROJECT);
	for (File element : directory.listFiles()) {
	    if (element.getName().contains("messages")) {

		String s = element.getName();
		String name = s.substring(s.indexOf("_") + 1, s.indexOf("."));

		Properties properties = new Properties();
		try {
		    properties.load(new FileInputStream(element));
		} catch (IOException e) {
		    throw new RuntimeException("No se encuentra el fichero.");
		}

		Idioma idioma = new Idioma();
		idioma.setCode(name);
		System.out.println("name: " + name);
		idioma.setLocale(new Locale(name));
		System.out.println("locale: " + idioma.getLocale());
		idioma.setLanguage(properties.getProperty("meta.idioma"));
		System.out.println("idioma: " + idioma.getLanguage());
		mapaIdiomas.put(name, idioma);
	    }
	}
    }

    @PreDestroy
    public void end() {
	Log.info("BeanSettings - PreDestroy");
    }

    public Locale getLocale() {
	return (locale);
    }

    public String localeSelected;

    public void setLocaleSelected(String localeSelected) {
	this.localeSelected = localeSelected;
    }

    public void changeLanguage(ActionEvent event) {
	System.out.println(localeSelected);
	if (mapaIdiomas.containsKey(localeSelected))
	    locale = mapaIdiomas.get(localeSelected).getLocale();
    }

    public List<Idioma> getIdiomas() {
	idiomas = new LinkedList<>();
	for (Map.Entry<String, Idioma> entry : mapaIdiomas.entrySet())
	    idiomas.add(entry.getValue());
	return idiomas;
    }

    // public String setSpanish(ActionEvent event) {
    // Log.info("Cambiando idioma a Español.");
    // locale = SPANISH;
    // return null;
    // }
    //
    // public String setEnglish(ActionEvent event) {
    // Log.info("Cambiando idioma a Inglés.");
    // locale = ENGLISH;
    // return null;
    // }

}
