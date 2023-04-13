create table campinfo_scrap
(
    scrap_id          bigint auto_increment primary key,
    campinfo_id        bigint not null,
    created_date        datetime     null,
    created_by          bigint not null,
    constraint FK_SCRAP_1
        foreign key (campinfo_id) references community (campinfo_id)
)
    engine = InnoDB;
