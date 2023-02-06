drop table if exists product;

create table product
(
    product_id         bigint auto_increment primary key,
    category_id        bigint       not null comment '카테고리 id',
    brand_id           bigint       not null comment '브랜드 id',
    vdr_id             bigint       not null comment '벤더사 id',
    name               varchar(120) null comment '상품명',
    tax_tp             int(2)       not null default 0 comment '과세 타입(0: 과세, 1: 면세, 2: 영세)',
    prd_tp             int(2)       not null default 0 comment '상품 타입(0: 일반)',
    prd_sts            int(2)       not null default 0 comment '상품 상태(0: 등록, 1: 판매중, 2: 판매중지)',
    product_desc       longtext     null comment '상품 설명',
    supply_prc         int(10)      not null comment '공급가',
    sale_prc           int(10)      not null comment '판매가',
    prd_prc            int(10)      not null comment '소비자가',
    total_cnt          int(10)      not null comment '재고수량',
    start_date         datetime     not null comment '판매시작일',
    end_date           datetime     not null comment '판매종료일',
    delry_cd           varchar(10)  not null comment '택배사코드',
    delry_tp           varchar(2)   not null comment '배송비 부과 타입(1: 조건부 무료, 2: 유료)',
    delry_base_start_prc int(10)    null comment '배송비 부과 기준시작금액',
    delry_prc          int(10)      not null comment '기본 배송비',
    delry_side_prc     int(10)      not null comment '도서산간 배송비',
    delry_jeju_prc     int(10)      not null comment '제주도 배송비',
    delry_out_addr     varchar(255) not null comment '출고지 주소',
    delry_out_addr2    varchar(255) not null comment '출고지 상세주소',
    delry_ref_addr     varchar(255) not null comment '반품/교환 주소',
    delry_ref_addr2    varchar(255) not null comment '반품/교환 상세주소',
    opt_tp           int(2)       not null comment '옵션 타입(0: 단건형, 1: 단독형, 2: 조합형)',
    opt_title1         varchar(100) null comment '옵션타이틀1',
    opt_title2         varchar(100) null comment '옵션타이틀2',
    opt_title3         varchar(100) null comment '옵션타이틀3',
    created_date       datetime     null,
    last_modified_date datetime     null,
    created_by         bigint       not null,
    last_modified_by   bigint       null,
    constraint FK_PRODUCT_1
        foreign key (category_id) references category (category_id),
    constraint FK_PRODUCT_2
        foreign key (brand_id) references brand (brand_id),
    constraint FK_PRODUCT_3
        foreign key (vdr_id) references vender (vdr_id)
)
    engine = InnoDB;

drop table product;

