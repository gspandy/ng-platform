<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%><%@ page import="javax.naming.*"
%><%@ page import="java.io.*"
%><%!

void showChild(Context ctx, Writer out, String treeName)
	throws Exception
{
	NamingEnumeration<NameClassPair> ne = ctx.list("");

	out.write(treeName + " -> <ul>");
	while (ne.hasMore())
	{
		NameClassPair child = ne.nextElement();
		String name = child.getName();
		Object obj = null;

		try
		{
			obj = ctx.lookup(name);
		}
		catch (Exception e)
		{
			e.printStackTrace();

			try { out.write("<li style=\"color: red;\">" + name + "(error in " + name + ")</li>"); } catch (Exception e2) {}
		}

		if (obj == null)
			continue;
		else if (obj instanceof Context)
		{
			out.write("<li>");
			showChild((Context) obj, out, name);
			out.write("</li>");
		}
		else
			out.write("<li>" + name + "</li>");
	}
	out.write("</ul>");
}

%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body><%

InitialContext ictx = new InitialContext();

showChild(ictx, out, "");

// out.write("<p>" + ictx.lookup("java:comp/env/jdbc/ng-demo") + "</p>");
%></body>
</html>