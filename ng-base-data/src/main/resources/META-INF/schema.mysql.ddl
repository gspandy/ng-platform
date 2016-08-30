
/*
 * 用户表
 *
 * 记录用户的基本属性
 */
create table NG_TBL_USER
(
	PK_ID						char(25)		primary key			comment '用户id，经过处理的UID，全局唯一',
	C_PASS_HASHER				smallint(1)		default 0 not null	comment '密码的编码器',
	C_PASS_HASH					varchar(200)						comment '经过处理的用户密码，由用户对应的密码编码器处理和判断',
	IS_DELETED					tinyint(1)		default 0 not null	comment '是否已删除',
	C_CREATE_TIME				timestamp(6)	null				comment '用户创建时间（后台创建、批量导入、自主注册都记录该字段）',
	C_DELETE_TIME				timestamp(6)	null				comment '用户删除时间',
	IS_LOCKED					tinyint(1)		default 0 not null	comment '用户是否处于锁定状态',
	IS_EXPIRED					tinyint(1)		default 0 not null	comment '账号的有效期是否已到',
	RC_LOGIN_COUNT				int				default 0 not null	comment '冗余字段：用户的登录次数，对应登录记录表内的所有该用户的记录总数',
	RFK_FIRST_LOGIN_RECORD_ID	char(25)							comment '冗余字段：用户首次登录记录，可从对应的登录记录获取首次登录时间',
	RFK_LAST_LOGIN_RECORD_ID	char(25)							comment '冗余字段：用户最新登录记录，可从对应的登录记录获取最新登录时间'
)
comment '用户表，记录用户的基本属性';


/*
 * 用户信息变更的快照表
 */
create table NG_SST_USER
(
	PK_ID						char(25)		primary key			comment '快照id，经过处理的UID，全局唯一',
	C_START_TIME				timestamp(6)	null				comment '快照记录的开始时间',
	C_END_TIME					timestamp(6)	null				comment '快照记录的结束时间，为null表示正记录着用户当前的状态',
	IS_TAIL						tinyint(1)		default 0 not null	comment '标识是否为用户所有快照中最新的一条，方便下一条快照记录更新',
	FK_OWNER_ID					char(25)		not null			comment '快照记录所属的用户id',
	C_PASS_HASHER				smallint(1)							comment '快照字段：密码的编码器',
	C_PASS_HASH					varchar(200)						comment '快照字段：经过处理的用户密码',
	IS_LOCKED					tinyint(1)		default 0 not null	comment '快照字段：用户是否处于锁定状态',
	IS_EXPIRED					tinyint(1)		default 0 not null	comment '快照字段：账号的有效期是否已到',
	C_LOGIN_COUNT				int				default 0 not null	comment '快照字段：用户的登录次数，对应登录记录表内的所有该用户的记录总数',
	C_FIRST_LOGIN_TIME			timestamp(6)	null				comment '快照字段：用户首次登录记录，可从对应的登录记录获取首次登录时间',
	C_LAST_LOGIN_TIME			timestamp(6)	null				comment '快照字段：用户最新登录记录，可从对应的登录记录获取最新登录时间'
)
comment '用户信息变更的快照表';


/*
 * 鉴权标识
 */
CREATE TABLE NG_TBL_IDENTITY
(
	D_TYPE						smallint(2)		default 0 not null	comment '鉴权标识的类型',
	PK_ID						char(25)		primary key			comment '鉴权标识id',
	FK_USER_ID					char(25)		not null			comment '所属用户id',
	IS_ENABLED					tinyint(1)		default 0 not null	comment '标识当前鉴权标识是否生效，部分鉴权标识可能只是占用未完成验证的流程',
	C_CREATE_TIME				timestamp(6)						comment '创建时间'
)
comment '鉴权标识';


/*
 * 本地账号的鉴权标识
 */
CREATE TABLE NG_TBL_IDENTITY_LA
(
	PK_ID						char(25)		primary key			comment '鉴权标识id',
	UQ_ACCOUNT					varchar(255)	not null unique		comment '本地账号'
)
comment '本地账号的鉴权标识';


/*
 * 角色表
 */
create table NG_TBL_ROLE
(
	PK_ID						char(25)		primary key			comment '角色id，经过处理的UID，全局唯一',
	C_NAME						varchar(200)						comment '角色名称',
	C_CODE						varchar(20)							comment '角色代码',
	C_PROFILE_TYPE				varchar(255)						comment '角色对应的身份类型',
	C_DESCRIPTION				text								comment '对当前角色使用上的说明',
	IS_DELETED					tinyint(1)		default 0 not null	comment '是否已删除',
	C_CREATE_TIME				timestamp(6)	null				comment '角色创建时间',
	C_DELETE_TIME				timestamp(6)	null				comment '角色删除时间'
)
comment '角色表';


/*
 * 角色信息变更的快照表
 */
CREATE TABLE NG_SST_ROLE
(
	PK_ID						char(25)		primary key			comment '快照id，经过处理的UID，全局唯一',
	C_START_TIME				timestamp(6)	null				comment '快照记录的开始时间',
	C_END_TIME					timestamp(6)	null				comment '快照记录的结束时间，为null表示正记录着用户当前的状态',
	IS_TAIL						tinyint(1)		default 0 not null	comment '标识是否为用户所有快照中最新的一条，方便下一条快照记录更新',
	FK_OWNER_ID					char(25)		not null			comment '快照记录所属的用户id',
	C_NAME						varchar(200)						comment '快照字段：角色名称',
	C_CODE						varchar(20)							comment '快照字段：角色代码',
	C_PROFILE_TYPE				varchar(255)						comment '快照字段：角色对应的身份类型',
	C_DESCRIPTION				varchar(1000)						comment '快照字段：对当前角色使用上的说明'
)
comment '角色信息变更的快照表';


/*
 * 身份表
 *
 * 相当于用户与角色的关联表，只是IS_DELETED为1是才是有效的关联
 */
create table NG_TBL_PROFILE
(
	D_TYPE						smallint(2)		default 0 not null	comment '身份的类型标识，系统根据此值来转化不同的Profile类型',
	PK_ID						char(25)		primary key			comment '身份id，经过处理的UID，全局唯一',
	FK_USER_ID					char(25)		not null			comment '身份所属用户的id',
	FK_ROLE_ID					char(25)		not null			comment '身份对应角色的id',
	IS_DELETED					tinyint(1)		default 0 not null	comment '是否已删除',
	C_CREATE_TIME				timestamp(6)	null				comment '身份创建时间',
	C_DELETE_TIME				timestamp(6)	null				comment '身份删除时间'
)
comment '身份表，相当于用户与角色的关联表，只是IS_DELETED为1是才是有效的关联';


create table NG_TBL_ACTIVITY_SESSION
(
	PK_ID						varchar(100)	primary key			comment 'web容器的会话id',
	FK_SESSION_ID				char(25)		not null			comment 'web容器会话记录对应的系统回话记录',
	C_CREATE_TIME				timestamp(6)						comment '创建时间'
)
comment 'web容器中的会话记录';


/*
 * 记录每个用户的会话
 */
create table NG_TBL_SESSION
(
	PK_ID						char(25)		primary key			comment '会话id，经过处理的UID，全局唯一',
	C_STATUS					smallint(2)		default 0 not null	comment '会话状态，0：新建的；1：已登录；2：过时；3：退出（主动）；4：被逼退出',
	C_USER_AGENT				varchar(200)						comment '浏览器信息',
	C_CREATE_TIME				timestamp(6)	null				comment '创建时间',
	C_REFRESH_TIME				timestamp(6)	null				comment '最新刷新时间',
	FK_LOGIN_RECORD_ID			char(25)							comment '当前的登录记录（如果已登录的话）',
	C_VISIT_TIMES				int				default 0 not null	comment '访问次数',
	C_LOGIN_TIMES				int				default 0 not null	comment '登录次数'
)
comment '记录每个用户的会话';

-- 创建session的ip路由记录关联表
create table NG_ASS_SESSION_CREATE_IP
(
	PK_SESSION_ID				char(25)		not null,
	PK_INDEX					int				not null,
	C_CREATE_IP					varchar(20),

	PRIMARY KEY (PK_SESSION_ID, PK_INDEX)
)
comment '创建session的ip路由记录关联表';

-- 最新刷新session的ip路由记录关联表
create table NG_ASS_SESSION_REFRESH_IP
(
	PK_SESSION_ID				char(25)		not null,
	PK_INDEX					int				not null,
	C_REFRESH_IP				varchar(20),
	
	PRIMARY KEY (PK_SESSION_ID, PK_INDEX)
)
comment '最新刷新session的ip路由记录关联表';


/*
 * 登录记录表
 */
create table NG_TBL_LOGIN_RECORD
(
	D_TYPE						smallint(1)		not null			comment '登录记录的类型，没有默认为0因为不能为0',
	PK_ID						char(25)		primary key			comment '登录记录id，经过处理的UID，全局唯一',
	FK_USER_ID					char(25)		not null			comment '所属的用户id',
	FK_SESSION_ID				char(25)		not null			comment '所属的会话id',
	C_LOGIN_STATUS				smallint(2)		default 0 not null	comment '登录记录状态，参照枚举类cc.waa.ng.base.data.LoginStatus',
	C_LOGIN_TIME				timestamp(6)	null				comment '登录时间',
	C_REQUEST_TIMES				int				default 0 not null	comment '访问次数',
	C_LAST_REQUEST_TIME			timestamp(6)						comment '最后访问时间',
	C_QUIT_TIME					timestamp(6)	null				comment '记录退出登录时间',
	C_CREATE_TIME				timestamp(6)	null				comment '创建时间',
	C_LOGIN_ACCOUNT				varchar(255)						comment '本地登录时，记录用户登录用的账号'
)
comment '登录记录表';
