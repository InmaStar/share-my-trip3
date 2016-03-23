<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" uri="http://www.uniovi.es/sdi/templating" %>
<t:base title="Iniciar sesión">
    <%@include file="partials/barraDeUsuario.jsp" %>
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <h1>Bienvenido</h1>
                <div class="well well-sm">
                    <n:a id="listarViajes"
                         action="listarViajes">Ver viajes</n:a>
                </div>
            </div>
        </div>
    </div>
</t:base>