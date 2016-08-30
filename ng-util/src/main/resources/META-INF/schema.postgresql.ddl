
/*
 * 系统属性值记录
 *
 * 这里设置的属性值只用于系统启动时加载（主要由spring调用，但不限于此）
 */
CREATE TABLE NG_SYS_PROPERTY
(
	PK_KEY		VARCHAR(200)	PRIMARY KEY,
	C_VALUE		VARCHAR(2000),
	C_MEMO		VARCHAR(2000)
);

COMMENT ON TABLE NG_SYS_PROPERTY IS '系统属性值记录';
COMMENT ON COLUMN NG_SYS_PROPERTY.PK_KEY  IS '系统属性的key';
COMMENT ON COLUMN NG_SYS_PROPERTY.C_VALUE IS '系统属性的值';
COMMENT ON COLUMN NG_SYS_PROPERTY.C_MEMO  IS '备注说明';
