
/*
 * ϵͳ����ֵ��¼
 *
 * �������õ�����ֵֻ����ϵͳ����ʱ���أ���Ҫ��spring���ã��������ڴˣ�
 */
CREATE TABLE NG_SYS_PROPERTY
(
	PK_KEY		VARCHAR2(200)	PRIMARY KEY,
	C_VALUE		VARCHAR2(2000),
	C_MEMO		VARCHAR2(2000)
);

COMMENT ON TABLE NG_SYS_PROPERTY IS 'ϵͳ����ֵ��¼';
COMMENT ON COLUMN NG_SYS_PROPERTY.PK_KEY  IS 'ϵͳ���Ե�key';
COMMENT ON COLUMN NG_SYS_PROPERTY.C_VALUE IS 'ϵͳ���Ե�ֵ';
COMMENT ON COLUMN NG_SYS_PROPERTY.C_MEMO  IS '��ע˵��';