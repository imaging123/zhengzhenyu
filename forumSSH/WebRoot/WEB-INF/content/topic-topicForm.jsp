<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../../commons/header.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<h2>
	Topic Page
</h2>
<p>
	<%@ include file="../../commons/errors.jsp"%>
<s:form action="topicAction2" method="post">
	<%--<input type="hidden" name="method" value="create" />--%>
	<s:hidden name="topic.id"/>
	<s:token></s:token>
	<s:textfield name="topic.title" label="Title"/>
	<br>
	
	<s:textfield name="topic.content" label="Content"/>
	<br>
	<br>
	<s:submit value="保存"/>
</s:form>

<%@ include file="../../commons/footer.jsp"%>