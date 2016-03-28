<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" uri="http://www.uniovi.es/sdi/templating" %>
<%@ taglib prefix="n" uri="http://www.uniovi.es/sdi/navigation" %>
<t:base title="Error">
    <div class="container">
        <div class="jumbotron">
            <div class="alert alert-danger">
                <h1>Navegación invalida</h1>
            </div>
            <div class="well well-sm">
                <n:a id="listarViajes"
                     action="listarViajes">Volver a la pagina principal</n:a>
            </div>
        </div>
    </div>
</t:base>