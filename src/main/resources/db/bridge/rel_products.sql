create table rel_products -- 기획전의 연관 상품 관리 테이블
{
    rel_products_id         bigint auto_increment primary key   comment '연관상품 id',
    product_id              bigint       not null               comment '상품번호, product : product_id 참조',
    rel_id                  bigint       not null               comment '연관 이벤트 번호 special_plan : special_plan_id 또는 event : event_id 참조',
    rel_category            char(1)      not null               comment 'E: 이벤트, S: 기획전',
    created_date            datetime     not null,
    last_modified_date      datetime     null,
    created_by              bigint       not null,
    last_modified_by        bigint       null,
    constraint FK_REL_PRODUCTS_1
           foreign key (product_id) references product (product_id)

}
     engine = InnoDB;
