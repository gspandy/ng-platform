
create database if not exists demo default character set utf8;
use demo;

/*
 * 初始化系统属性值
 */
insert into NG_SYS_PROPERTY (PK_KEY, C_VALUE, C_MEMO) values ('jpa.loadtimeweaver.class', 'org.springframework.instrument.classloading.tomcat.TomcatLoadTimeWeaver', '设置与服务器相对应的JPA的LoadtimeWeaver类');
insert into NG_SYS_PROPERTY (PK_KEY, C_VALUE, C_MEMO) values ('ng.id.user.system', '100ipl6vc9k377kyc2zyfwg00', '平台的“系统”用户');
insert into NG_SYS_PROPERTY (PK_KEY, C_VALUE, C_MEMO) values ('weixin.verify.token', 'ng_verify_token', '接入微信公众平台，用于验证的令牌');


/*
 * 初始化用户数据
 */
insert into NG_TBL_USER (PK_ID, C_CREATE_TIME) values ('100ipl6vc9k377kyc2zyfwg00', now());
