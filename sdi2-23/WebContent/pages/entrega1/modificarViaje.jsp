<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" uri="http://www.uniovi.es/sdi/templating" %>
<%@ taglib prefix="n" uri="http://www.uniovi.es/sdi/navigation" %>
<%@ taglib prefix="d" uri="http://www.uniovi.es/sdi/date" %>
<t:base title="Modificar viaje" validation="true">
    <%@ include file="partials/barraDeUsuario.jsp" %>
    <div class="container">
        <div class="row">
            <n:form action="modificarViaje">
            <input type="hidden" name="id" value="${viaje.id}">
            <div class="col-md-3">
                <h2>Salida</h2>
                <h3>Dirección</h3>
                <div class="form-group">
                    <label>Calle</label>
                    <input type="text"
                           name="calleSalida"
                           required
                           class="form-control"
                           value="${viaje.departure.address}"/>
                </div>
                <div class="form-group">
                    <label>Ciudad</label>
                    <input type="text"
                           name="ciudadSalida"
                           required
                           class="form-control"
                           value="${viaje.departure.city}"/>
                </div>
                <div class="form-group">
                    <label>Provincia</label>
                    <input type="text"
                           name="provinciaSalida"
                           required
                           class="form-control"
                           value="${viaje.departure.state}"/>
                </div>
                <div class="form-group">
                    <label>País</label>
                    <input type="text"
                           name="paisSalida"
                           required
                           class="form-control"
                           value="${viaje.departure.country}"/>
                </div>
                <div class="form-group">
                    <label>Código postal</label>
                    <input type="text"
                           name="codigoPostalSalida"
                           required
                           class="form-control"
                           value="${viaje.departure.zipCode}"/>
                </div>
                <h4>Coordenadas GPS</h4>
                <div class="form-group">
                    <label>Latitud</label>
                    <input type="number"
                           name="latSalida"
                           max="80" min="-80"
                           class="form-control"
                           value="${viaje.departure.lat}"/>
                </div>
                <div class="form-group">
                    <label>Longitud</label>
                    <input type="number"
                           name="lonSalida"
                           max="80" min="-80"
                           class="form-control"
                           value="${viaje.departure.lon}"/>
                </div>
            </div>
            <div class="col-md-3">
                <h2>Detalles</h2>
                <div class="form-group">
                    <label>Fecha de salida</label>
                    <input type="text"
                           name="fechaSalida"
                           required
                           class="form-control"
                           placeholder="DD/MM/YYYY"
                           value="<d:format date="${viaje.departureDate}" dateFormat="true"/>"/>
                </div>
                <div class="form-group">
                    <label>Hora de salida</label>
                    <input type="text"
                           name="horaSalida"
                           required
                           class="form-control"
                           placeholder="HH:MM"
                           value="<d:format date="${viaje.departureDate}"
						dateFormat="false" timeFormat="true" />"/>
                </div>

            </div>
            <div class="col-md-3">
                <h2>Destino</h2>
                <h3>Dirección</h3>
                <div class="form-group">
                    <label>Calle</label>
                    <input type="text" name="calleDestino"
                           required
                           class="form-control"
                           value="${viaje.destination.address}"/>
                </div>
                <div class="form-group">
                    <label>Ciudad</label>
                    <input type="text"
                           name="ciudadDestino"
                           required class="form-control"
                           value="${viaje.destination.city}"/>
                </div>
                <div class="form-group">
                    <label>Provincia</label>
                    <input type="text"
                           name="provinciaDestino"
                           required
                           class="form-control"
                           value="${viaje.destination.state}"/>
                </div>
                <div class="form-group">
                    <label>País</label>
                    <input type="text"
                           name="paisDestino"
                           required
                           class="form-control"
                           value="${viaje.destination.country}"/>
                </div>
                <div class="form-group">
                    <label>Código postal</label>
                    <input type="text"
                           name="codigoPostalDestino"
                           required
                           class="form-control"
                           value="${viaje.destination.zipCode}"/>
                </div>
                <h4>Coordenadas GPS</h4>
                <div class="form-group">
                    <label>Latitud</label>
                    <input type="number"
                           name="latDestino"
                           max="80" min="-80"
                           class="form-control"
                           value="${viaje.destination.lat}"/>
                </div>
                <div class="form-group">
                    <label>Longitud</label>
                    <input type="number"
                           name="lonDestino"
                           max="180" min="-180"
                           class="form-control"
                           value="${viaje.destination.lon}"/>
                </div>
                <div class="form-group">
                    <label>Fecha de llegada</label>
                    <input type="text"
                           name="fechaLlegada"
                           required
                           class="form-control"
                           placeholder="DD/MM/YYYY"
                           value="<d:format date="${viaje.arrivalDate}" dateFormat="true"/>"/>
                </div>
                <div class="form-group">
                    <label>Hora de llegada</label>
                    <input type="text"
                           name="horaLlegada"
                           required
                           class="form-control"
                           placeholder="HH:MM"
                           value="<d:format date="${viaje.arrivalDate}" dateFormat="false" timeFormat="true" />"/>
                </div>

            </div>
            <div class="col-md-3">
                <h2>Detalles</h2>
                <div class="form-group">
                    <label>Fecha límite para apuntarse</label>
                    <input type="text"
                           name="fechaLimite"
                           required
                           class="form-control"
                           placeholder="DD/MM/YYYY"
                           value="<d:format date="${viaje.closingDate}" dateFormat="true"/>"/>
                </div>
                <div class="form-group">
                    <label>Coste estimado</label>
                    <input type="number"
                           name="costeEstimado"
                           min="0"
                           required
                           class="form-control"
                           value="${viaje.estimatedCost}"/>
                </div>
                <div class="form-group">
                    <label>Comentarios</label>
                     <textarea class="form-control"
                               name="comentarios"
                               placeholder="Comentarios">${viaje.comments}</textarea>
                </div>

                <div class="form-group">
                    <label>Plazas máximas</label>
                    <input type="number" min="1"
                           name="maxPlazas"
                           required
                           class="form-control"
                           value="${viaje.maxPax}">
                </div>

                <div class="form-group">
                    <label>Plazas disponibles</label>
                    <input type="number"
                           min="0"
                           name="plazasDisponibles"
                           required
                           class="form-control"
                           value="${viaje.availablePax}">
                </div>

                <input type="submit" class="btn btn-default" value="Modificar"/>
                </n:form>
            </div>
            <%@ include file="partials/mensajeError.jsp" %>
        </div>
</t:base>