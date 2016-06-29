package uo.sdi.presentation;

import java.util.Locale;

public class Idioma {

    private Locale locale;
    private String code;
    private String language;

    public Locale getLocale() {
	return locale;
    }

    public void setLocale(Locale locale) {
	this.locale = locale;
    }

    public String getLanguage() {
	return language;
    }

    public void setLanguage(String language) {
	this.language = language;
    }

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

}
