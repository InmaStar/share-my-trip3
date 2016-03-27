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
        <th>Relación</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="entry"
               items="${listaViajes}"
               varStatus="i">
        <c:if test="${entry.hasRelationship(user.id)}">
            <tr id="item_${i.index}">
                <td>${entry.id}</td>
                <td>${entry.departure.city}</td>
                <td>${entry.destination.city}</td>
                <td>${entry.maxPax}</td>
                <td>${entry.availablePax}</td>
                <td>${entry.status}</td>
                <td>${entry.getRelationship(user.id)}</td>
            </tr>
        </c:if>
    </c:forEach>
    </tbody>
</table>