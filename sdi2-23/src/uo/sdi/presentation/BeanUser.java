package uo.sdi.presentation;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "user")
@SessionScoped
public class BeanUser {

    private boolean publico = true;
    
    public boolean isPublico(){
	return publico;
    }
}
