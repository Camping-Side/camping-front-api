-- 옵션타입의 경우
-- 단건형 : 옵션 구성이 따로 없음
-- 단독형 : 옵션 구성이 있지만 구성단위를 제외한 모든 것은 상품데이터에 종속
-- 조합형 : 각 옵션 구성에 옵션가, 재고수량
-- 옵션 기준은
drop table if exists product_option;

create table product_option
(
    opt_id bigint auto_increment primary key,
    product_id bigint not null comment '상품 id',
    opt_value1 varchar(150) not null comment '상품옵션값1',
    opt_value2 varchar(150) null comment '상품옵션값2',
    opt_value3 varchar(150) null comment '상품옵션값3',
    opt_supply_prc int(10) not null comment '옵션 공급가',
    opt_prc int(10) not null comment '옵션 금액',
    opt_cnt int(10) not null comment '옵션 재고수량',
    created_date       datetime     null,
    last_modified_date datetime     null,
    created_by         bigint       not null,
    last_modified_by   bigint       null,
    constraint FK_PRODUCT_OPTION_1
        foreign key (product_id) references product(product_id)
)
    engine = InnoDB;