create table brand
(
    brand_id                 bigint auto_increment primary key,
    brand_name         varchar(50)  not null comment '브랜드명',
    intro              varchar(100)  null comment '한줄소개',
    office_tel         int(12) null comment '대표 전화번호',
    brand_url          varchar(255)  null comment '브랜드 URL',
    use_yn             varchar(2)   default 'Y' comment '사용여부(기본 Y)',
    brand_img          varchar(255) null comment '브랜드 이미지 url',
    created_date       datetime     null,
    last_modified_date datetime     null,
    created_by         bigint not null,
    last_modified_by   bigint null
)
    engine = InnoDB;