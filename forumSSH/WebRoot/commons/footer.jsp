<%@ page contentType="text/html; charset=UTF-8"%>

<p>
<center>
	[
	<a href="${ctx}/topic!list.action">Home</a> |
	<a href="${ctx}/user!reg.action">Reg</a> |
	<c:choose>
		<c:when test="${empty user}">
			<a href="${ctx}/user!login.action">Login</a>
		</c:when>
		<c:otherwise>
		You are logged in as ${user.userName} - <a href="${ctx}/user!logOut.action">Logout</a>
		</c:otherwise>
	</c:choose>
	]
</center>
<p />

	</body>

	</html>