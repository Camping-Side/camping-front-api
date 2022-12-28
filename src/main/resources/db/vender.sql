create table vender
(
    vdr_id                 bigint auto_increment primary key,
    vdr_nm             varchar(50) not null comment '벤더명',
    vdr_sts             int(2) not null default 0 comment '벤더 상태(0: 신청, 1: 심사중, 2: 승인, 3: 거절)',
    created_date       datetime     null,
    last_modified_date datetime     null,
    created_by         bigint not null,
    last_modified_by   bigint null
)
    engine = InnoDB;