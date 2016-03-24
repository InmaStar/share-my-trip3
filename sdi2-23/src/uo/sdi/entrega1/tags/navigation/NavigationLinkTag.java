package uo.sdi.entrega1.tags.navigation;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import uo.sdi.entrega1.servlets.util.Navigation;

import java.io.IOException;

/**
 * Prints the value of a property into a JSP
 *
 * @author UO238739
 */
public class NavigationLinkTag extends SimpleTagSupport {
    private String action;
    private String styles = null;
    private String id = null;
    private String params = null;

    public void setAction(String action) {
        this.action = action;
    }

    public void setStyles(String styles) {
        this.styles = styles;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public void doTag() throws JspException, IOException {
        String tag = "<a ";

        if (id != null) {
            tag += "id=\"" + id + "\" ";
        }

        if (styles != null) {
            tag += "class=\"" + styles + "\" ";
        }

        tag += "href=\"" + Navigation.getUrlByAction(action);

        if (params != null) {
            tag += "?" + params;
        }
        tag += "\" >";

        getJspContext().getOut().write(tag);
        getJspBody().invoke(getJspContext().getOut());
        getJspContext().getOut().write("</a>");
    }
}
