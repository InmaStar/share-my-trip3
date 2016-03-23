<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" uri="http://www.uniovi.es/sdi/templating" %>
<%@ taglib prefix="n" uri="http://www.uniovi.es/sdi/navigation" %>
<t:base title="Lista de viajes">
    <%@include file="partials/barraDeUsuario.jsp" %>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <%@include file="partials/listaViajes.jsp" %>
            </div>
        </div>
    </div>
</t:base>