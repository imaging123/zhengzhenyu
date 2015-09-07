<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<%@ include file="../../commons/header.jsp"%>

<h2>
	Register Page
</h2>
<p>
<%@ include file="../../commons/errors.jsp"%>
<s:form action="user!register.action" method="post">
<s:hidden name="user.id"/>
	<s:textfield name="user.userName" label="User Name"/>
	<br>
	<s:textfield name="user.passWord" label="PassWord"/>
	<br>
	<br>
	<s:submit value="注册"/>
</s:form>

<%@ include file="../../commons/footer.jsp"%>