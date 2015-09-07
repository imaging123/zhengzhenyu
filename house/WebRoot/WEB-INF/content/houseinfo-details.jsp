<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- saved from url=(0044)http://localhost:8080/HouseRent/page/add.jsp -->
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<TITLE>青鸟租房 -发布房屋信息</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
<LINK rel=stylesheet type=text/css href="${ctx }/css/style.css">
<META name=GENERATOR content="MSHTML 8.00.7601.17514">
</HEAD>
<BODY>

	<DIV id=header class=wrap>
		<DIV id=logo>
			<IMG src="${ctx }/images/logo.gif">
		</DIV>
		<DIV class=search>
			<FORM method=get>
				<INPUT class=text type=text name=keywords> <LABEL
					class="ui-green searchs"><a href="list.htm" target="_blank">搜索房屋</a></LABEL>
			</FORM>
		</DIV>
	</DIV>
	<DIV id=navbar class=wrap>
		<DL class="search clearfix">
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
								listValue="name" listKey="id" headerValue="请选择" headerKey="0" /></li>
					</ul>
				</dd>
				<dd>
					<ul>
						<li class=first>房型</li>
						<li><s:select name="hc.roomtype.id" list="types"
								listValue="name" listKey="id" headerValue="请选择" headerKey="0" /></li>
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
		</DL>
	</DIV>
	<DIV id=position class=wrap>当前位置：青鸟租房网 - 浏览房源</DIV>
	<DIV id=view class="main wrap">
		<DIV class=intro>
			<DIV class=lefter>
				<H1>${info.title }</H1>
				<DIV class=subinfo>${info.createdate }</DIV>
				<DIV class=houseinfo>
					<P>
						户 型：<SPAN>${info.roomtype.name }</SPAN>
					</P>
					<P>
						面 积：<SPAN>${info.area }m<SUP>2</SUP></SPAN>
					</P>
					<P>
						位 置：<SPAN>${info.street.district.name }${info.street.name }</SPAN>
					</P>
					<P>
						联系方式：<SPAN>${info.user.phone }</SPAN>
					</P>
				</DIV>
			</DIV>
			<DIV class=side>
				<P>
					<A class=bold href="http://localhost:8080/House-2/#">北京青鸟房地产经纪公司</A>
				</P>
				<P>资质证书：有</P>
				<P>内部编号:1000</P>
				<P>联 系 人：${info.user.name }</P>
				<P>
					联系电话：${info.user.phone }<SPAN></SPAN>
				</P>
				<P>
					手机号码：<SPAN>暂无</SPAN>
				</P>
			</DIV>
			<DIV class=clear></DIV>
			<DIV class=introduction>
				<H2>
					<SPAN><STRONG>房源详细信息</STRONG></SPAN>
				</H2>
				<DIV class=content>
					<P>${info.detail }</P>
				</DIV>
			</DIV>
		</DIV>
	</DIV>
	<DIV id=footer class=wrap>
		<DL>
			<DT>青鸟租房 © 2010 北大青鸟 京ICP证1000001号</DT>
			<DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD>
		</DL>
	</DIV>
</BODY>
</HTML>
