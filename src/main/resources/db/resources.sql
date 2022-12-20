create table resources
(
    resource_id        bigint auto_increment not null primary key,
    http_method        varchar(10)  null comment 'http 메소드(POST, GET, PATCH 등)',
    order_num          int          null comment '노출 우선순위',
    resource_name      varchar(50) null comment '리소스명',
    resource_type      varchar(20) default 'url' comment '리소스 타입(기본 url)',
    created_date       datetime(6)  null,
    last_modified_date datetime(6)  null,
    created_by         bigint not null,
    last_modified_by   bigint null
)
    engine = InnoDB;