
/*
 * 系统属性值记录
 *
 * 这里设置的属性值只用于系统启动时加载（主要由spring调用，但不限于此）
 */
create table NG_SYS_PROPERTY
(
	PK_KEY		varchar(200)	primary key	comment '系统属性的key',
	C_VALUE		varchar(255)				comment '系统属性的值',
	C_MEMO		varchar(255)				comment '备注说明'
)
comment '系统属性值记录';
