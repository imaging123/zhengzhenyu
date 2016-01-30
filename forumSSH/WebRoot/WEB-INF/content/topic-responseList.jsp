<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../../commons/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<h2>
	Response List Page
</h2>

<p>
<table width="100%" border="1">

	<tr>
		<td>
			<b>Author:${user.userName}</b>
		</td>
		<td>
			<b>Content</b>
		</td>
		<td>
			<b>Status</b>
		</td>
		<td>
			<b>Operation</b>
		</td>
	</tr>
	<s:iterator value="responses" var="theResponse">
		<tr>
			<td>
				${theResponse.user.userName}
			</td>
			<td>
				${theResponse.content}
			</td>
			<td>
				${theResponse.status}
			</td>
			<c:choose>
				<c:when test="${theResponse.deleted == true}">
					<td>
						<a href="${ctx}/topic!rollBack.action?id=${theResponse.id}">
							rollback </a>
					</td>
				</c:when>
				<c:otherwise>
					<td>
						<a href="${ctx}/topic!remove.action?id=${theResponse.id}">
							remove </a>
					</td>
				</c:otherwise>
			</c:choose>
		</tr>
	</s:iterator>
</table>
<p />
<center>
	[
	<a href="${ctx}/topic!addResp.action">create response</a>]
</center>
<%@ include file="../../commons/footer.jsp"%>