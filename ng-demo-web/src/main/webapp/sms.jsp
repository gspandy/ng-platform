<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%><%@ page import="org.apache.commons.lang3.StringUtils"
%><%@ page import="org.springframework.web.context.support.WebApplicationContextUtils"
%><%@ page import="org.springframework.context.ApplicationContext"
%><%@ page import="org.springframework.jdbc.core.JdbcTemplate"
%><%@ page import="org.springframework.jdbc.core.PreparedStatementSetter"
%><%@ page import="java.sql.*"
%><%@ page import="java.text.DecimalFormat"
%><%!

/*
建表语句：

create table ng_tbl_sms
(
	pk_id					number			primary key,
	fk_thread_id			number,
	c_address				varchar2(50),
	c_person_id				number,
	c_date					timestamp,
	c_date_sent				timestamp,
	c_protocol				number(1),
	is_read					number(1),
	c_status				number(3),
	c_type					number(1),
	c_reply_path_present	number,
	c_subject				varchar2(255),
	c_body					varchar2(2000),
	c_service_center		varchar2(50),
	is_locked				number(1),
	c_sub_id				number,
	c_error_code			number,
	c_creator				varchar2(500),
	is_seen					number(1)
);

column comment on ng_tbl_sms.pk_id is '短消息序号';
column comment on ng_tbl_sms.fk_thread_id is '对话的序号（关联threads表）';
column comment on ng_tbl_sms.c_address is '发件人地址，手机号';
column comment on ng_tbl_sms.c_person_id is '发件人，返回一个数字就是联系人列表里的序号，陌生人为null';
column comment on ng_tbl_sms.c_date is '日期，long型';
column comment on ng_tbl_sms.c_date_sent is '';
column comment on ng_tbl_sms.c_protocol is '协议，0：SMS_RPOTO；1：MMS_PROTO';
column comment on ng_tbl_sms.is_read is '是否阅读，0：未读；1：已读';
column comment on ng_tbl_sms.c_status is '状态，-1：接收；0：complete；64：pending；128：failed';
column comment on ng_tbl_sms.c_type is '类型，1：inbox（是接收到的）；2：sent（是已发出）；3：draft；4：outbox；5：failed；6：queued';
column comment on ng_tbl_sms.c_reply_path_present is 'TP-Reply-Path位的值（0/1）';
column comment on ng_tbl_sms.c_subject is '短信的主题';
column comment on ng_tbl_sms.c_body is '短消息内容';
column comment on ng_tbl_sms.c_service_center is '短信服务中心号码编号';
column comment on ng_tbl_sms.is_locked is '此条短信是否已由用户锁定，0：未锁定；1：已锁定';
column comment on ng_tbl_sms.c_sub_id is '';
column comment on ng_tbl_sms.c_error_code is '错误代码，有哪些值暂时未知';
column comment on ng_tbl_sms.c_creator is '';
column comment on ng_tbl_sms.c_seen is '用于指明该消息是否已被用户看到（非阅读，点开会话列表即可，不用打开会话），仅对收到的消息有用';
 */

final String SQL = StringUtils.join("insert into NG_TBL_SMS (",
		"PK_ID, FK_THREAD_ID, C_ADDRESS, C_PERSON_ID, C_DATE, ",
		"C_DATE_SENT, C_PROTOCOL, IS_READ, C_STATUS, C_TYPE, ",
		"C_REPLY_PATH_PRESENT, C_SUBJECT, C_BODY, C_SERVICE_CENTER, IS_LOCKED, ",
		"C_SUB_ID, C_ERROR_CODE, C_CREATOR, IS_SEEN) ",
		"values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

%><%

request.setCharacterEncoding("UTF-8");

String param;

if (StringUtils.equalsIgnoreCase(request.getMethod(), "post") &&
	StringUtils.equalsIgnoreCase(param = request.getParameter("action"), "receive"))
{
	long start = System.currentTimeMillis();

	ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(application);
	ctx.getBean(JdbcTemplate.class).update(SQL, new PreparedStatementSetter()
	{
		@Override
		public void setValues(PreparedStatement ps)
			throws SQLException
		{
			setNumberOrNull(ps, 1, request.getParameter("_id"));
			setNumberOrNull(ps, 2, request.getParameter("thread_id"));
			ps.setString(3, request.getParameter("address"));
			setNumberOrNull(ps, 4, request.getParameter("person"));
			setDateOrNull(ps, 5, request.getParameter("date"));
			setDateOrNull(ps, 6, request.getParameter("date_sent"));
			setNumberOrNull(ps, 7, request.getParameter("protocol"));
			ps.setBoolean(8, "1".equals(request.getParameter("read")));
			setNumberOrNull(ps, 9, request.getParameter("status"));
			setNumberOrNull(ps, 10, request.getParameter("type"));
			setNumberOrNull(ps, 11, request.getParameter("reply_path_present"));
			ps.setString(12, request.getParameter("subject"));
			ps.setString(13, request.getParameter("body"));
			ps.setString(14, request.getParameter("service_center"));
			ps.setBoolean(15, "1".equals(request.getParameter("locked")));
			setNumberOrNull(ps, 16, request.getParameter("sub_id"));
			setNumberOrNull(ps, 17, request.getParameter("error_code"));
			ps.setString(18, request.getParameter("creator"));
			ps.setBoolean(19, "1".equals(request.getParameter("seen")));
		}

		private void setNumberOrNull(PreparedStatement ps, int parameterIndex, String value)
			throws SQLException
		{
			if (StringUtils.isEmpty(value))
				ps.setNull(parameterIndex, Types.NUMERIC);
			else
				ps.setInt(parameterIndex, Integer.parseInt(value));
		}

		private void setDateOrNull(PreparedStatement ps, int parameterIndex, String value)
			throws SQLException
		{
			if (StringUtils.isEmpty(value))
				ps.setNull(parameterIndex, Types.TIMESTAMP);
			else
				ps.setTimestamp(parameterIndex, new Timestamp(Long.parseLong(value)));
		}
	});

	long end = System.currentTimeMillis();

	System.out.println("处理第" + request.getParameter("index") +  "个任务，耗时：" + new DecimalFormat("#,##0.00").format((end - start) / 1000d) + "秒");
}

%>success