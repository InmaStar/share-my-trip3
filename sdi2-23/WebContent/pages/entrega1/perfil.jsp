<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" uri="http://www.uniovi.es/sdi/templating" %>
<%@ taglib prefix="n" uri="http://www.uniovi.es/sdi/navigation" %>
<t:base title="Datos del usuario" validation="true">
    <%@include file="partials/barraDeUsuario.jsp" %>

    <div class="container">
    <h1>Datos de perfil</h1>
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <jsp:useBean id="user" class="uo.sdi.transport.UserDTO"
                             scope="session"/>
                <n:form action="modificarDatos">
                    <div class="form-group">
                        <label>Login</label>
                        <input type="text"
                               readonly
                               name="nombreUsuario"
                               class="form-control"
                               value="${user.login}"
                               id="login"/>
                    </div>
                    <div class="form-group">
                        <label>Contraseña</label>
                        <input type="password"
                               name="password1"
                               class="form-control"
                               placeholder="Contraseña"
                               id="password1"/>
                    </div>
                    <div class="form-group">
                        <label>Repita su contraseña</label>
                        <input type="password"
                               name="password2"
                               class="form-control"
                               placeholder="Repita contraseña"/>
                    </div>
                    <div class="form-group">
                        <label>Nombre</label>
                        <input type="text"
                               name="nombre"
                               class="form-control"
                               value="${user.name}"
                               placeholder="Nombre"
                               id="name"/>
                    </div>
                    <div class="form-group">
                        <label>Apellidos</label>
                        <input type="text"
                               name="apellidos"
                               class="form-control"
                               value="${user.surname}"
                               placeholder="Apellidos"
                               id="surname"/>
                    </div>
                    <div class="form-group">
                        <label>Email</label>
                        <input type="text"
                               name="email"
                               class="form-control"
                               value="${user.email}"
                               placeholder="Email"
                               id="email"/>
                    </div>
                    <div class="form-group">
                        <input type="submit"
                               class="btn btn-default"
                               value="Modificar"/>
                    </div>
                </n:form>
                <%@ include file="partials/mensajeError.jsp" %>

                <div class="well well-sm">
                    <n:a id="listarViajes"
                         action="listarViajes">Página principal</n:a>
                    <n:a id="cerrarSesión"
                         action="cerrarSesion">Cerrar sesión</n:a>
                </div>
            </div>
        </div>
    </div>

</t:base>
