<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%><%@ page import="javax.persistence.*"
%><%@ page import="javax.persistence.criteria.*"
%><%@ page import="java.util.*"
%><%@ page import="cc.waa.ng.util.lang.GUID"
%><%@ page import="org.springframework.web.context.support.WebApplicationContextUtils"
%><%@ page import="org.springframework.context.ApplicationContext"
%><%@ page import="cc.waa.ng.base.data.*"
%><%@ page import="cc.waa.ng.base.data.entity.*"
%><%@ page import="cc.waa.ng.base.data.entity.auth.*"
%><%@ page import="cc.waa.ng.weixin.entity.*"
%><%

ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(application);
EntityManagerFactory factory = (EntityManagerFactory) ctx.getBean("entityManagerFactory");

EntityManager em = null;

try
{
	em = factory.createEntityManager();

	em.getTransaction().begin();

	SessionEntity se = em.find(SessionEntity.class, "100e2a6sh9iirkd9jru62muww");

	List<String> ipRoute = se.getRefreshIpRoute();	// ["113.105.152.162","157.122.183.226"]

	if (ipRoute == null)
		se.setRefreshIpRoute(ipRoute = new ArrayList<String>());

	System.out.println(ipRoute.size());

	//*
//	ipRoute.clear();
//	ipRoute.add("192.168.16.160");
//	ipRoute.add("113.105.152.162");
//	ipRoute.add("157.122.183.226");
	ipRoute.remove(0);

//	if (ipRoute instanceof IndirectList)
//		((IndirectList<String>) ipRoute).setIsListOrderBrokenInDb(true);

//	System.out.println(ipRoute.size());
	/**/

	for (String ip : ipRoute)
		System.out.println(ip);

	em.getTransaction().commit();
}
catch (Exception e)
{
	e.printStackTrace();

	em.getTransaction().rollback();
}
finally
{
	try { em.close(); } catch (Exception e) {}
}

%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body></body>
</html>