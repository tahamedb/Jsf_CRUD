package com.example.devoir_jsf.bean;

import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import jakarta.faces.context.FacesContext;
import java.util.Locale;

@ManagedBean
public class LanguageBean implements Serializable {
    private String language = "en"; // Default language

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    // Method to change the language
    public String changeLanguage(String language) {
        this.language = language;
        Locale locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
        return ""; // Stay on the same page
    }
}
