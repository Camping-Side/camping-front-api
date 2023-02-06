create table menu
(
    id                 bigint auto_increment primary key,
    menu_nm          varchar(50)  not null comment '메뉴 이름',
    icon               varchar(50)  null comment '아이콘 클래스명',
    path               varchar(255) null comment '메뉴 이동경로',
    menu_seq           int default 0 comment '메뉴 순서(기본 0)',
    menu_level         int default 0 comment '메뉴 계층레벨(기본 0)',
    menu_use_yn        varchar(2)   default 'Y' comment '사용여부(기본 Y)',
    parent_id          bigint       null comment '부모 id',
    created_date       datetime     null,
    last_modified_date datetime     null,
    created_by         bigint not null,
    last_modified_by   bigint null,
    constraint FK_MENU_1
        foreign key (parent_id) references menu (id)
)
    engine = InnoDB;