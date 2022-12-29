create table account
(
    id                 bigint auto_increment primary key,
    activated          tinyint(1)   null comment '활성화여부',
    birth              varchar(8)   null comment '생년월일 YYYYMMDD',
    email              varchar(255) unique null comment '이메일',
    phone              varchar(12)  null comment '핸드폰번호',
    password           varchar(255) null comment '비밀번호(암호화)',
    username           varchar(30) null comment '유저명',
    market_agree       tinyint(1)   null comment '마케팅 수신여부',
    leave_date         datetime     null comment '탈퇴일자',
    created_date       datetime     null,
    last_modified_date datetime     null,
    created_by         bigint not null,
    last_modified_by   bigint null
)
    engine = InnoDB;