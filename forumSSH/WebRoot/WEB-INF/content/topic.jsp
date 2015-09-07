<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../../commons/header.jsp"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<h2>
	Current topics<br>
	User:${sessionScope.user.userName }
</h2>

<p>
<table width="100%" border="1">

	<tr>
		<td>
			<b>Title</b>
		</td>
		<td>
			<b>Content</b>
		</td>
		<td>
			<b>Responses</b>
		</td>
	</tr>
	<s:iterator value="topices" var="topic">
		<tr>
			<td>
				<a href="${ctx}/topic!response.action?id=${topic.id}">${topic.title}</a>
			</td>
			<td>
				${topic.content}
			</td>
			<td>
				${topic.size}
			</td>
		</tr>
	</s:iterator>
</table>
<p />
<center>
	
	[<a href="${ctx }/topic!create.action">create topic</a>]
</center>
<%@ include file="../../commons/footer.jsp"%>