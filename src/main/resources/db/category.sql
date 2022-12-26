create table category
(
    id                 bigint auto_increment primary key,
    category_name      varchar(50)  not null comment '카테고리명',
    icon               varchar(50)  null comment '아이콘 클래스명',
    seq                int default 0 comment '순서(기본 0)',
    level              int default 0 comment '계층레벨(기본 0)',
    use_yn             varchar(2)   default 'Y' comment '사용여부(기본 Y)',
    parent_id          bigint       null comment '부모 id',
    created_date       datetime     null,
    last_modified_date datetime     null,
    created_by         bigint not null,
    last_modified_by   bigint null,
    constraint FK_MENU_1
        foreign key (parent_id) references category (id)
)
    engine = InnoDB;