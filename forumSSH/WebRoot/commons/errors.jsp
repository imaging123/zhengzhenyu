<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<p>
<center>
	<c:if test="${not empty errors}">
	${errors}
</c:if>
</center>
<p>