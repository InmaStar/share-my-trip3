package uo.sdi.tags.templating;

import alb.util.log.Log;
import uo.sdi.servlets.util.Navigation;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

public class BaseTemplateTag extends BodyTagSupport {
    /**
     * 
     */
    private static final long serialVersionUID = 7858701582320717634L;
    private String title = null;
    private boolean validation = false;

    public void setValidation(String validation) {
        if (validation.equals("true")) {
            this.validation = true;
        }
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int doStartTag() throws JspException {
        if (validation
                && pageContext.getRequest()
                .getAttribute("jspSiguiente") == null) {
            try {
                Log.info("Se está intentando hacer una navegación invalida");
                pageContext.forward(Navigation.ERROR_403);
            } catch (Exception e) {
                Log.error("Ha habido un problemas redireccionado a la página " +
                        "de error");
            }
        }


        StringBuilder res = new StringBuilder();

        res.append("<!DOCTYPE html>\n")
                .append("<html>\n")
                .append("<head>\n")
                .append("<meta charset=\"utf-8\" /> \n")


                .append("<link rel=\"stylesheet\" type=\"text/css\" " +
                        "href=\"//cdn.datatables.net/1.10.11/css/" +
                        "jquery.dataTables.min.css\">\n")

                .append("<script type=\"text/javascript\" " +
                        "language=\"javascript\" " +
                        "src=\"//code.jquery.com/jquery-1.12.0.min.js\">" +
                        "</script>\n")

                .append("<script type=\"text/javascript\" " +
                        "src=\"//cdn.datatables.net/1.10.11/js/jquery" +
                        ".dataTables.min.js\"></script>\n")

                .append("<link rel=\"stylesheet\"" +
                        " href=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3" +
                        ".6/css/bootstrap.min.css\" />\n")

                .append("<title>");
        if (title != null) {
            res.append(title).append(" - ");
        }
        res.append("ShareMyTrip</title>\n")
                .append("</head>\n")
                .append("<body>\n");

        try {
            final JspWriter out = pageContext.getOut();
            out.println(res.toString());
        } catch (Exception ignored) {
        }
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() throws JspException {
        final JspWriter out = pageContext.getOut();
        try {
            out.print("<script>\n");
            out.print("$(function() {\n");
            out.print(" $('.data-table').DataTable({\n");
            out.print(" 'language': {\n" +
                    "'url': '//cdn.datatables.net/plug-ins/1.10.11/i18n/" +
                    "Spanish.json'}\n");
            out.print(" });\n");
            out.print("});\n");
            out.print("</script>\n");
            out.println("</body>\n</html>\n");
        } catch (IOException ignored) {
        }
        return 6;
    }
}
