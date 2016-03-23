<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" uri="http://www.uniovi.es/sdi/templating" %>
<%@ taglib prefix="n" uri="http://www.uniovi.es/sdi/navigation" %>
<t:base title="Registrarse" validation="true">
    <%@include file="partials/barraDeUsuario.jsp" %>
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <h1>Registro</h1>
                <n:form action="registrarse">
                    <div class="form-group">
                        <label>Su identificador de usuario</label>
                        <input type="text"
                               name="nombreUsuario"
                               required
                               class="form-control"
                               placeholder="Usuario" />
                    </div>
                    <div class="form-group">
                        <label>Su contraseña</label>
                        <input type="password" name="password1"
                               required class="form-control"
                               placeholder="Contreña"/>
                    </div>
                    <div class="form-group">
                        <label>Repita su contraseña</label>
                        <input type="password"
                               name="password2"
                               required
                               class="form-control"
                               placeholder="Repita contraseña"/>
                    </div>
                    <div class="form-group">
                        <label>Su nombre</label>
                        <input type="text"
                               name="nombre"
                               class="form-control"
                               placeholder="Nombre"/>
                    </div>
                    <div class="form-group">
                        <label>Sus apellidos</label>
                        <input type="text"
                               name="apellidos"
                               class="form-control"
                               placeholder="Apellidos"/>
                    </div>
                    <div class="form-group">
                        <label>Su email</label>
                        <input type="text"
                               name="email"
                               class="form-control"
                               placeholder="Email"/>
                    </div>
                    <input type="submit"
                           class="btn btn-default"
                           id="submitBtn"
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