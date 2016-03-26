<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${requestScope.mensajeDeError!=null}">
<div class="alert alert-warning">
	<p id="error"><strong>${requestScope.mensajeDeError}</strong></p>
</div>
</c:if>