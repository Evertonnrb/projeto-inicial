package br.com.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * @author - Everton Ribeiro
 * @email - everton.nrb@gmail.com
 **/
public class FacesUtils {

    public static void addErrorMessage(String key) {
        addMessage(FacesMessage.SEVERITY_ERROR, key);
    }

    public static void addWarningMessage(String key) {
        addMessage(FacesMessage.SEVERITY_WARN, key);
    }

    public static void addSucessMessage(String key) {
        addMessage(FacesMessage.SEVERITY_INFO, key);
    }

    private static void addMessage(FacesMessage.Severity severity, String msg) {
        final FacesMessage facesMessage = new FacesMessage(severity, msg, "");
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setRedirect(true);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

    private static String getBundleValue(String value) {
        return FacesContext.
                getCurrentInstance().
                getApplication().
                getResourceBundle(FacesContext.getCurrentInstance(), "n")
                .getString(value);
    }
}
