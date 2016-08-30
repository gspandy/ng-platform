<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%><%@ page import="org.springframework.web.context.support.WebApplicationContextUtils"
%><%@ page import="org.springframework.context.ApplicationContext"
%><%@ page import="org.springframework.security.core.context.SecurityContextHolder"
%><%@ page import="cc.waa.ng.demo.security.DemoUserDetails"
%><%@ page import="java.util.*"
%><%@ page import="cc.waa.ng.base.data.dao.*"
%><%@ page import="cc.waa.ng.base.data.dao.auth.*"
%><%@ page import="cc.waa.ng.base.data.obj.auth.*"
%><%@ page import="cc.waa.ng.base.data.obj.*"
%><%@ page import="cc.waa.ng.base.data.*"
%><%@ page import="cc.waa.ng.weixin.dao.*"
%><%

ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(application);

//LoginRecordDao loginRecordDao = ctx.getBean(LoginRecordDao.class);
//UserDao userDao = ctx.getBean(UserDao.class);
//ActivitySessionDao activitySessionDao = ctx.getBean(ActivitySessionDao.class);
//SessionDao sessionDao = ctx.getBean(SessionDao.class);
//IdentityDao identityDao = ctx.getBean(IdentityDao.class);


//User user = userDao.findOne("100ikx4ex269eq4hvuq0of56o");
//ActivitySession activity = activitySessionDao.findOne("309136FE25C9AF21E5C0988D9DFE1F11");
//Session sess = sessionDao.findOne("100ha73h4xc9ceui8ir2mdm9v");
//List<LoginRecord> recs = loginRecordDao.findByUserLoginStatusIn(user, LoginStatus.LOGINED);
//List<ActivitySession> ass = activitySessionDao.findBySession(sess);
//List<ActivitySession> ass = sess.getActivities();

//for (ActivitySession sss : ass)
//	System.out.println(sss.getCreateTime());

//Identity iden = identityDao.findOne("100frcl6nr75ndzhcdjjqz66a");

%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body><%= ctx.getBean(WeixinMessageDao.class).count() %></body>
</html>