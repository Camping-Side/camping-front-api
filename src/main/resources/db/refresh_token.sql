create table refresh_token
(
    rt_key   varchar(255) not null
        primary key,
    rt_value varchar(255) null
)
    engine = InnoDB;