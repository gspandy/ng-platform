<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%><%@ page import="javax.persistence.*"
%><%@ page import="org.springframework.web.context.support.WebApplicationContextUtils"
%><%@ page import="org.springframework.context.ApplicationContext"
%><%

ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(application);

%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
body { font-family: 'YaHei Consolas Hybird'; font-size: 12px; }
</style>
</head>

<body><table border="1">

<thead>
<tr>
<th>对象名</th><th>对象内容</th>
</tr>
</thead>

<tbody><%

for (String name : ctx.getBeanDefinitionNames())
{
	%><tr><td><%= name %></td><td><%= ctx.getBean(name).toString() %></td></tr><%
}

%></tbody>
</table></body>
</html>