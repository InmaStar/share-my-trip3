<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="n" uri="http://www.uniovi.es/sdi/navigation" %>
<table class="data-table" width="100%">
    <thead>
    <tr>
        <th>Origen</th>
        <th>Destino</th>
        <th>Plazas libres</th>
        <th>Promotor</th>
        <c:if test="${user!=null}">
            <th></th>
        </c:if>

    </tr>
    </thead>
    <tbody>
    <c:forEach var="entry" items="${listaViajes}" varStatus="i">
        <c:if test="${entry.visible}">
            <tr id="item_${i.index}">
                <td>${entry.departure.city}</td>
                <td>${entry.destination.city}</td>
                <td>${entry.availablePax}</td>
                <td>${entry.promoter.login}</td>
                <c:if test="${user!=null}">
                    <c:choose>
                        <c:when test="${entry.isPending(user.id)}">
                            <td><n:a action="cancelarPlaza"
                                     params="id=${entry.id}"
                                     id="cancelarPlaza${entry.id}">Cancelar plaza</n:a></td>
                        </c:when>
                        <c:otherwise>
                            <td><n:a action="solicitarPlaza"
                                     params="id=${entry.id}"
                                     id="solicitarPlaza${entry.id}">Solicitar plaza</n:a></td>
                        </c:otherwise>
                    </c:choose>
                </c:if>
            </tr>
        </c:if>
    </c:forEach>
    </tbody>
</table>