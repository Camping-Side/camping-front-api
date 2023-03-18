create table comment
(
    comment_id          bigint auto_increment primary key,
    community_id        bigint not null,
    content             longtext     null comment '게시글 내용',
    parent_id           bigint       null comment '부모 id',
    status              int(2) null comment '게시 여부(0:비공개,1:게시)',
    created_date        datetime     null,
    last_modified_date  datetime     null,
    created_by          bigint not null,
    last_modified_by    bigint null,
    constraint FK_COMMENT_1
        foreign key (community_id) references community (community_id)
)
    engine = InnoDB;
--위에 fk_cont_1 필요한지..