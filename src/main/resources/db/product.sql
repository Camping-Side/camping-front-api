create table product
(
    product_id         bigint auto_increment primary key,
    category_id        bigint       not null comment '카테고리 id',
    brand_id           bigint       not null comment '브랜드 id',
    vender_id          bigint       not null comment '벤더사 id',
    name               varchar(120) null comment '상품명',
    tax_tp             int(2)       not null default 0 comment '과세 타입(0: 과세, 1: 면세, 2: 영세)',
    prd_tp             int(2)       not null default 0 comment '상품 타입(0: 일반)',
    prd_sts            int(2)       not null default 0 comment '상품 상태(0: 등록, 1: 판매중, 2: 판매중지)',
    product_desc       longtext     null comment '상품 설명',
    supply_prc         int(15)      not null comment '공급가',
    sale_prc           int(15)      not null comment '판매가',
    prd_prc            int(15)      not null comment '소비자가',
    total_cnt          int(11)      not null comment '재고수량',
    start_date         datetime     not null comment '판매시작일',
    end_date           datetime     not null comment '판매종료일',
    created_date       datetime     null,
    last_modified_date datetime     null,
    created_by         bigint       not null,
    last_modified_by   bigint       null,
    constraint FK_PRODUCT_1
        foreign key (category_id) references category (category_id),
    constraint FK_PRODUCT_2
        foreign key (brand_id) references brand (brand_id),
    constraint FK_PRODUCT_3
        foreign key (vender_id) references vender (vdr_id)
)
    engine = InnoDB;

create table product_img
(
    product_id         bigint       not null comment '상품 id',
    seq                int(2)       not null comment '순서(0: 타이틀, 1부터 서브)',
    img_path           varchar(255) null comment '이미지경로',
    created_date       datetime     null,
    last_modified_date datetime     null,
    created_by         bigint       not null,
    last_modified_by   bigint       null,
    primary key (product_id, seq),
    constraint FK_PRODUCT_IMG_1
        foreign key (product_id) references product (id)
)
    engine = InnoDB;
