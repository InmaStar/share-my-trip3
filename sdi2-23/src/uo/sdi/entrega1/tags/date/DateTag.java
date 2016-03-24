package uo.sdi.entrega1.tags.date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import uo.sdi.entrega1.util.DateFormatter;

import java.io.IOException;

public class DateTag extends SimpleTagSupport {
    private String date = null;
    private boolean dateFormat = true;
    private boolean timeFormat = false;

    public void setDate(String date) {
        this.date = date;
    }

    public void setDateFormat(boolean dateFormat) {
        this.dateFormat = dateFormat;
    }

    public void setTimeFormat(boolean timeFormat) {
        this.timeFormat = timeFormat;
    }

    public void doTag() throws JspException, IOException {
        String formattedDate = DateFormatter.formatDate(date);
        String formattedString = "";

        if (timeFormat && dateFormat) {
            formattedString = formattedDate;
        } else if (dateFormat) {
            formattedString = formattedDate.split(" ")[0];
        } else if (timeFormat) {
            formattedString = formattedDate.split(" ")[1];
        }
        getJspContext().getOut().write(formattedString);
    }
}
