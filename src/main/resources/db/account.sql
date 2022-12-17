create table account
(
    id                 bigint auto_increment primary key,
    activated          bit          null comment '활성화여부',
    birth              varchar(8)   null comment '생년월일 YYYYMMDD',
    email              varchar(255) unique null comment '이메일',
    phone              varchar(12)  null comment '핸드폰번호',
    password           varchar(255) null comment '비밀번호(암호화)',
    username           varchar(255) null comment '유저명',
    created_date       datetime     null,
    last_modified_date datetime     null,
    created_by         bigint not null,
    last_modified_by   bigint null
)
    engine = MyISAM;