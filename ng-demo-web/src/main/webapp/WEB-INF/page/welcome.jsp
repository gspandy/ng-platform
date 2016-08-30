<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"
%><%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"
%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head><s:authentication property="principal" scope="page" var="principal" />

<body><c:choose><c:when
	test="${principal == 'anonymousUser'}"><input type="button" value="登录" onclick="window.location = '/security/login';" /></c:when><c:otherwise><form action="/security/logout" method="post">
<s:csrfInput />
<input type="submit" value="退出登录" /></form></c:otherwise></c:choose><p>当前用户账号：${principal == 'anonymousUser' ? '游客' : principal.user.account}</p></body>
</html>