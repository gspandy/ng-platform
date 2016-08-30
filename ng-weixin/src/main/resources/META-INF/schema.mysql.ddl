
/*
 * 微信推送的事件（抽象）
 */
create table NG_TBL_WEIXIN_EVENT
(
	D_TYPE						smallint(2)		default 0 not null	comment '事件类型标识',
	PK_ID						char(25)		primary key			comment '事件记录id，经过处理的UID，全局唯一',
	C_MSG_TYPE					varchar(100)						comment '事件被封装成消息时的分类标识，这里恒为event',
	C_FROM_USER_NAME			varchar(200)						comment '发送方帐号（一个OpenID）',
	C_TO_USER_NAME				varchar(200)						comment '开发者微信号',
	C_ORIGIN_TIME				timestamp(6)	null				comment '事件创建时间',
	C_CREATE_TIME				timestamp(6)	null				comment '事件记录在本地创建的时间',
	C_EVENT						varchar(100)						comment '事件类型',
	C_EVENT_KEY					varchar(200)						comment '事件KEY值'
)
comment '微信推送的事件（抽象）';


/*
 * 带二维码ticket的关注事件
 */
create table NG_TBL_WEIXIN_EVENT_TICKET
(
	PK_ID						char(25)		primary key			comment '事件记录id，经过处理的UID，全局唯一',
	C_TICKET					varchar(200)						comment '二维码的ticket，可用来换取二维码图片'
)
comment '带二维码ticket的关注事件';


/*
 * 上报地理位置事件
 */
create table NG_TBL_WEIXIN_EVENT_LOCATION
(
	PK_ID						char(25)		primary key			comment '事件记录id，经过处理的UID，全局唯一',
	C_LATITUDE					double								comment '地理位置纬度',
	C_LONGITUDE					double								comment '地理位置经度',
	C_PRECISION					double								comment '地理位置精度'
)
comment '上报地理位置事件';


/*
 * 微信推送的消息（抽象）
 */
create table NG_TBL_WEIXIN_MESSAGE
(
	D_TYPE						smallint(2)		default 0 not null	comment '消息类型标识',
	PK_ID						char(25)		primary key			comment '消息记录id，经过处理的UID，全局唯一',
	C_MSG_TYPE					varchar(100)						comment '消息分类标识',
	C_MSG_ID					varchar(200)						comment '微信消息的id',
	C_FROM_USER_NAME			varchar(200)						comment '发送消息的用户',
	C_TO_USER_NAME				varchar(200)						comment '接收消息的用户',
	C_ORIGIN_TIME				timestamp(6)	null				comment '消息附带的createTime',
	C_CREATE_TIME				timestamp(6)	null				comment '消息记录在本地创建的时间'
)
comment '微信推送的消息';


/*
 * 微信推送的文字信息
 */
create table NG_TBL_WEIXIN_MESSAGE_TEXT
(
	PK_ID						char(25)		primary key			comment '消息记录id，经过处理的UID，全局唯一',
	C_CONTENT					text								comment '文字信息内容'
)
comment '微信推送的文字信息';


/*
 * 微信推送的图片信息
 */
create table NG_TBL_WEIXIN_MESSAGE_IMAGE
(
	PK_ID						char(25)		primary key			comment '消息记录id，经过处理的UID，全局唯一',
	C_PIC_URL					varchar(255)						comment '图片地址',
	C_MEDIA_ID					varchar(255),
	C_MEDIA_TYPE				varchar(50),
	C_MEDIA_CONTENT				longblob
)
comment '微信推送的图片信息';


/*
 * 微信推送的语音信息
 */
create table NG_TBL_WEIXIN_MESSAGE_VOICE
(
	PK_ID						char(25)		primary key			comment '消息记录id，经过处理的UID，全局唯一',
	C_MEDIA_ID					varchar(255)						comment '语音消息媒体id，可以调用多媒体文件下载接口拉取数据',,
	C_MEDIA_TYPE				varchar(50),
	C_MEDIA_CONTENT				longblob
	C_FORMAT					varchar(100)						comment '语音格式，如amr，speex等',
	C_RECOGNITION				varchar(255)						comment '语音识别结果，使用UTF8编码'
)
comment '微信推送的语音信息';


/*
 * 微信推送的视频/小视频信息
 */
create table NG_TBL_WEIXIN_MESSAGE_VIDEO
(
	PK_ID						char(25)		primary key			comment '消息记录id，经过处理的UID，全局唯一',
	C_MEDIA_ID					varchar(255)						comment '视频消息媒体id，可以调用多媒体文件下载接口拉取数据',
	C_MEDIA_TYPE				varchar(50),
	C_MEDIA_CONTENT				longblob,
	C_THUMB_MEDIA_ID			varchar(255)						comment '视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据',
	C_THUMB_MEDIA_TYPE			varchar(50),
	C_THUMB_MEDIA_CONTENT		longblob
)
comment '微信推送的视频/小视频信息';


/*
 * 微信推送的位置信息
 */
create table NG_TBL_WEIXIN_MESSAGE_LOCATION
(
	PK_ID						char(25)		primary key			comment '消息记录id，经过处理的UID，全局唯一',
	C_LOCATION_X				double								comment '纬度',
	C_LOCATION_Y				double								comment '经度',
	C_SCALE						smallint							comment '比例',
	C_LABEL						varchar(255)
)
comment '微信推送的位置信息';


/*
 * 微信推送的链接消息
 */
create table NG_TBL_WEIXIN_MESSAGE_LINK
(
	PK_ID						char(25)		primary key			comment '消息记录id，经过处理的UID，全局唯一',
	C_TITLE						varchar(255)						comment '消息标题',
	C_DESCRIPTION				varchar(255)						comment '消息描述',
	C_URL						varchar(255)						comment '消息链接'
)
comment '微信推送的链接消息';
