
create table comm_img
(
    img_id             bigint       not null comment '이미지 id',
    ref_id             bigint       null comment '참조 id',
    type               int(3)       not null comment '타입(0: 상품, 1: 게시판, 2: 캠핑인포, 98: 임시 확정, 99: 임시)',
    seq                int(2)       default 0 not null comment '순서(0: 타이틀, 1부터 서브)',
    img_path           varchar(255) null comment '이미지경로',
    created_date       datetime     null,
    last_modified_date datetime     null,
    created_by         bigint       not null,
    last_modified_by   bigint       null,
    primary key (img_id)
)
    engine = InnoDB;