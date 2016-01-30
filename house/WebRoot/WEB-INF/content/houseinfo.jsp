
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>青鸟租房 - 首页</title>
<meta content="text/html; charset=utf-8" http-equiv=Content-Type>
<link rel=stylesheet type=text/css href="${ctx }/css/style.css">
<meta name=GENERaTOR content="MShtml 8.00.7601.17514">
</head>
<body>
	<div id=header class=wrap>
		<div id=logo>
			<img src="${ctx }/images/logo.gif">
		</div>
	</div>
	<div id="navbar" class="wrap">
		<dl class="search clearfix">
			<s:form id="sform" method="post" action="houseinfo!query.action">
				<s:hidden name="hc.id" />
				<dt>
					<ul>
						<li class=bold>房屋信息</li>
						<li>标题：<s:textfield class="text" name="hc.title" /> <label
							class=ui-blue><s:submit value="搜索房屋" name="search" /></label>
						</li>
					</ul>
				</dt>
				<dd>
					<ul>
						<li class=first>价格区间</li>
						<li><s:textfield name="hc.minPrice" width="200px" />--<s:textfield
								name="hc.maxPrice" width="200px" /></li>
					</ul>
				</dd>
				<dd>
					<ul>
						<li class=first>房屋位置</li>
						<li><s:select name="hc.street.id" list="streets"
								listValue="name" listKey="id" headerValue="请选择"
								headerKey="0" /></li>
					</ul>
				</dd>
				<dd>
					<ul>
						<li class=first>房型</li>
						<li><s:select name="hc.roomtype.id" list="types"
								listValue="name" listKey="id" headerValue="请选择"
								headerKey="0" /></li>
					</ul>
				</dd>
				<dd>
					<ul>
						<li class=first>面积区间</li>
						<li><s:textfield name="hc.minArea" width="200px" />--<s:textfield
								name="hc.maxArea" width="200px" /></li>
					</ul>
				</dd>
			</s:form>
		</dl>
	</div>
	<div class="main wrap">
		<table class=house-list>
			<tbody>
				<s:iterator value="page.data" var="info">
					<tr>
						<td class=house-thumb><span><a href="details.htm"
								target="_blank"><img src="${ctx }/images/thumb_house.gif"
									width="100" height="75" alt=""></a></span></td>
						<td>
							<dl>
								<dt>
									<a href="${ctx }/houseinfo!detail.action?id=${info.id}" target="_blank">${info.title }</a>
								</dt>
								<dd>
									${info.street.name },${info.area }平米<BR>联系方式：${info.conn }
								</dd>
							</dl>
						</td>
						<td class=house-type>${info.roomtype.name }</td>
						<td class=house-price><span>${info.price }</span>元/月</td>
					</tr>
				</s:iterator>
				<tr>无租房信息
				</tr>
			</tbody>
		</table>
		<div class=pager>
			<ul>
				<li class=current><a
					href="houseinfo!list.action?currentNo=${1 }">首页</a></li>
				<li><s:if test="page.pre">
						<a href="houseinfo!list.action?currentNo=${page.currentNo-1 }">上一页</a>
					</s:if></li>
				<li><s:if test="page.next">
						<a href="houseinfo!list.action?currentNo=${page.currentNo+1 }">下一页</a>
					</s:if></li>
				<li><a
					href="houseinfo!list.action?currentNo=${page.pageCount }">尾页</a></li>
			</ul>
			<span class=total>${page.currentNo }/${page.pageCount }页</span>
		</div>
	</div>
	<div id=footer class=wrap>
		<dl>
			<dt>青鸟租房 © 2010 北大青鸟 京ICP证1000001号</dt>
			<dd>关于我们 · 联系方式 · 意见反馈 · 帮助中心</dd>
		</dl>
	</div>
</body>
</html>
