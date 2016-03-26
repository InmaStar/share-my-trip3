<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" uri="http://www.uniovi.es/sdi/templating" %>
<%@ taglib prefix="n" uri="http://www.uniovi.es/sdi/navigation" %>
<t:base title="Lista de viajes">
    <%@include file="partials/barraDeUsuario.jsp" %>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <table class="data-table" width="100%">
                    <thead>
                    <tr>
                        <th>Usuario</th>
                        <th>Nombre</th>
                        <th>Apellidos</th>
                        <c:if test="${trip.visible}">
                            <th></th>
                        </c:if>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="user" items="${applicants}" varStatus="i">
                        <tr id="item_${i.index}">
                            <td>${user.login}</td>
                            <td>${user.name}</td>
                            <td>${user.surname}</td>
                            <c:if test="${trip.visible}">
                                <td>
                                    <c:choose>
                                        <c:when test="${trip.isExcluded(user.id)
                                    || trip.isPending(user.id)}">
                                            <n:a action="confirmarPasajero"
                                                 params="tripId=${trip.id}&userId=${user.id}">Confirmar</n:a>
                                        </c:when>
                                        <c:otherwise>
                                            <n:a action="excluirPasajero"
                                                 params="tripId=${trip.id}&userId=${user.id}">Excluir</n:a>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</t:base>