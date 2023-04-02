create table community
(
    community_id        bigint auto_increment primary key,
    code                bigint       null comment '게시글 타입',
    title               longtext     null comment '게시글 제목',
    content             longtext     null comment '게시글 내용',
    location            varchar(255) null comment '위치',
    status              int(12) null comment '게시 여부(0:비공개,1:게시,2:삭제)',
    like_cnt            int(20) null comment '좋아요 개수',
    created_date        datetime     null,
    last_modified_date  datetime     null,
    created_by          bigint not null,
    last_modified_by    bigint null
)
    engine = InnoDB;