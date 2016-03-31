package uo.sdi.util.bundle;

import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

public class BundleLoader {

    private static ResourceBundle bundle;
    
    public static ResourceBundle load(String name){
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    if(bundle==null){
		bundle = facesContext.getApplication()
			.getResourceBundle(facesContext, name);
	    }
	    return bundle;
    }
}
