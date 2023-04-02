create table community_like
(
    like_id                 bigint auto_increment primary key,
    community_id        bigint not null comment '좋아요한 게시글',
    created_date        datetime     null,
    created_by          bigint not null,
    constraint FK_LIKE_1
        foreign key (community_id) references community (community_id)
)
    engine = InnoDB;