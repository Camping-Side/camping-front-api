create table campinfo_like --커뮤니티 의 좋아요 테이블이랑 같은 기능
(
    like_id                 bigint auto_increment primary key,
    campinfo_id        bigint not null comment '좋아요한 게시글',
    created_date        datetime     null,
    created_by          bigint not null comment '좋아요한 사람',
    constraint FK_LIKE_1
        foreign key (campinfo_id) references community (campinfo_id)
)
    engine = InnoDB;