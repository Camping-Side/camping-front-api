create table keyword
(
    keyword_id          bigint auto_increment primary key,
    campinfo_id        bigint not null,
    keyword             varchar(255) not null comment '키워드',
    created_date         datetime      null,
    last_modified_date   datetime      null,
    created_by           bigint    not null,
    last_modified_by     bigint        null
        
    constraint FK_LIKE_1
    foreign key (campinfo_id) references community (campinfo_id)
)

    engine = InnoDB;

