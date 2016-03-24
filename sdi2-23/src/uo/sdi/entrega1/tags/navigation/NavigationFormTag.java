package uo.sdi.entrega1.tags.navigation;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import uo.sdi.entrega1.servlets.util.Navigation;

public class NavigationFormTag extends SimpleTagSupport {
    private String action;
    private String styles = null;
    private String id = null;

    public void setAction(String action) {
        this.action = action;
    }

    public void setStyles(String styles) {
        this.styles = styles;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void doTag() throws JspException, IOException {
        String tag = "<form method=\"post\"";

        if (id != null) {
            tag += "id=\"" + id + "\" ";
        }

        if (styles != null) {
            tag += "class=\"" + styles + "\" ";
        }

        tag += "action=\"" + Navigation.getUrlByAction(action) + "\" >";

        getJspContext().getOut().write(tag);
        getJspBody().invoke(getJspContext().getOut());
        getJspContext().getOut().write("</form>");
    }
}
