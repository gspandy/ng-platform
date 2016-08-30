<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%><%@ page import="javax.naming.*"
%><%@ page import="javax.sql.*"
%><%@ page import="java.sql.*"
%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body><%

Context ctx = new InitialContext();
Object obj = ctx.lookup("java:comp/env/jdbc/ng-demo");

if (obj instanceof DataSource)
{
	DataSource ds = (DataSource) obj;
	Connection cn = null;
	Statement stm = null;
	ResultSet rst = null;

	try
	{
		cn = ds.getConnection();
		stm = cn.createStatement();
		rst = stm.executeQuery("SELECT COUNT(*) FROM NG_TBL_USER");

		if (rst.next())
			out.write("查询数据库结果：" + rst.getInt(1));
	}
	finally
	{
		try { rst.close(); } catch (Exception e) {}
		try { stm.close(); } catch (Exception e) {}
		try {  cn.close(); } catch (Exception e) {}
	}
}

else
	out.write("不是DataSource类型");

%></body>
</html>