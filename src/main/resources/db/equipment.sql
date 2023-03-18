create table equipment
(
    equipment_id        bigint auto_increment primary key,
    community_id        bigint not null,
    equipment_name      varchar(50)  not null comment '장비 이름',
    brand_id            bigint       null '브랜드 id',
    created_date        datetime     null,
    last_modified_date  datetime     null,
    created_by          bigint not null,
    last_modified_by    bigint null,
    constraint FK_EQUIPMENT_1
        foreign key (community_id) references community (community_id),
    constraint FK_EQUIPMENT_2
        foreign key (brand_id) references brand (brand_id)
)
    engine = InnoDB;