<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" uri="http://www.uniovi.es/sdi/templating" %>
<%@ taglib prefix="n" uri="http://www.uniovi.es/sdi/navigation" %>
<t:base title="Mis viajes" validation="true">
    <%@include file="partials/barraDeUsuario.jsp" %>
    <jsp:useBean id="user" class="uo.sdi.transport.UserDTO" scope="session"/>
    <div class="container">
        <div class="well well-sm">
            <n:a action="accederRegistroViajes">Registrar viaje</n:a>
        </div>
        <div id="viajes">
            <h2>Todos los viajes</h2>
            <%@include file="partials/listaViajes.jsp" %>

            <h2>Viajes relacionados</h2>
            <%@include file="partials/viajesRelacionados.jsp" %>

            <h2>Mis viajes</h2>
            <%@include file="partials/viajesPromotor.jsp" %>
        </div>
    </div>
</t:base>