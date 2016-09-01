<#assign security=JspTaglibs["http://www.springframework.org/security/tags"]
/><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head><@security.authentication property="principal" var="principal" />

<body><#if principal == 'anonymousUser'><input type="button" value="登录" onclick="window.location = '/security/login';" /><p>当前用户账号：游客</p><#else><form action="/security/logout" method="post"><@security.csrfInput /><input type="submit" value="退出登录" /></form><p>当前用户账号：${principal.user.account}</p></#if></body>
</html>