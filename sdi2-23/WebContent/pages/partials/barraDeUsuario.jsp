<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="n" uri="http://www.uniovi.es/sdi/navigation" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <n:a action="listarViajes" styles="navbar-brand">Share My Trip</n:a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <c:choose>
                <c:when test="${sessionScope.user==null}">
                    <li><a href="#"><strong>Visitante</strong></a></li>
                    <li><n:a action="accederValidacion" id="validarse">Iniciar
                        sesión</n:a></li>
                    <li><n:a action="accederRegistro" id="registrarse">Registrarse</n:a></li>
                </c:when>
                <c:otherwise>
                    <li id="rol"><a href="#">Usuario:
                        <strong>${sessionScope.user.login}</strong></a></li>
                    <li><n:a action="listarDatos" id="perfil">Perfil</n:a></li>
                    <li><n:a action="listarViajes">Viajes</n:a></li>
                    <li>
                        <n:a action="accederRegistroViajes" id="nuevoViaje">Nuevo viaje</n:a>
                    </li>
                    <li><n:a action="cerrarSesion" id="cerrarSesion">Cerrar sesión</n:a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>

