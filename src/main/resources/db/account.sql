create table account
(
    id                 bigint auto_increment primary key,
    activated          bit          null,
    age                int          null,
    email              varchar(255) unique null,
    password           varchar(255) null,
    username           varchar(255) null,
    created_date       datetime     null,
    last_modified_date datetime     null,
    created_by         varchar(255) not null,
    last_modified_by   varchar(255) null
)
    engine = MyISAM;