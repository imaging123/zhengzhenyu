<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../../commons/header.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<h2>

	Response Form Page
</h2>
<p>
<center>
	Title is: ${topic1.title}
</center>
<p>
<s:form action="topicAction" method="post">
	<s:hidden name="response.id"/>
	<s:token></s:token>
	<s:textfield name="response.content" label="Content"/>
	<br>
	<br>
	<s:submit value="提交"/>
</s:form>

<%@ include file="../../commons/footer.jsp"%>