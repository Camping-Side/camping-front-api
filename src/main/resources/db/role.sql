drop table if exists role;
create table role
(
    role_id        bigint auto_increment not null primary key,
    role_desc          varchar(255) null,
    role_nm          varchar(50) null,
    created_date       datetime(6)  null,
    last_modified_date datetime(6)  null,
    created_by         bigint not null,
    last_modified_by   bigint null
)
    engine = InnoDB;