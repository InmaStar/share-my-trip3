<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table class="data-table">
    <thead>
    <tr>
        <th>ID viaje</th>
        <th>Origen</th>
        <th>Destino</th>
        <th>Límite de plazas</th>
        <th>Plazas disponibles</th>
        <th>Estado</th>
        <th></th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="entry"
               items="${listaViajes}"
               varStatus="i">
        <c:if test="${entry.isPromoter(user.id)}">
            <tr id="item_${i.index}">
                <td>${entry.id}</td>
                <td>${entry.departure.city}</td>
                <td>${entry.destination.city}</td>
                <td>${entry.maxPax}</td>
                <td>${entry.availablePax}</td>
                <td>${entry.status}</td>
                <td><n:a action="solicitudes"
                         params="id=${entry.id}">Ver solicitudes</n:a></td>
                <td>
                    <c:if test="${entry.open}">
                        <n:a action="accederModificarViaje"
                             params="id=${entry.id}">Modificar viaje</n:a>
                    </c:if>
                </td>
                <td>
                    <c:if test="${entry.open}">
                        <n:a action="cancelarViaje"
                             params="id=${entry.id}">Cancelar</n:a>
                    </c:if>
                </td>
            </tr>
        </c:if>
    </c:forEach>
    </tbody>
</table>