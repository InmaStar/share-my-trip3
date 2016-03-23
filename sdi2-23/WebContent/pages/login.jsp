<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" uri="http://www.uniovi.es/sdi/templating" %>
<%@ taglib prefix="n" uri="http://www.uniovi.es/sdi/navigation" %>
<t:base title="Iniciar sesión" validation="true">
    <%@include file="partials/barraDeUsuario.jsp" %>
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <h1>Inicie sesión</h1>
                <n:form action="validarse">
                    <div class="form-group">
                        <label>Su identificador de usuario</label>
                        <input class="form-control"
                               type="text"
                               name="nombreUsuario"
                               placeholder="Usuario"
                               required/>
                    </div>
                    <div class="form-group">
                        <label>Su contraseña</label>
                        <input class="form-control"
                               type="password"
                               name="password"
                               placeholder="Contraseña"
                               required/>
                    </div>
                    <input type="submit" class="btn btn-default"
                           value="Enviar"/>
                </n:form>
                <%@ include file="partials/mensajeError.jsp" %>
                <div class="well well-sm">
                    <n:a id="listarViajes"
                         action="listarViajes">Página principal</n:a>
                </div>
            </div>
        </div>
    </div>
</t:base>