drop table if exists coupon;

create table coupon
(
    cpn_id              bigint auto_increment primary key,
    cpn_sts             int(2) not null default 0 comment '쿠폰 상태(0 중지, 1 발급)',
    cpn_tp              int(2) not null default 0 comment '쿠폰 타입(0 정액제, 1 정률제)',
    discount_base_prc   int not null default 0 comment '쿠폰 할인 기준금액',
    discount_max_prc    int not null default 0 comment '쿠폰 최대 할인 금액',
    discount_prc        int not null default 0 comment '할인 금액, 할인율',
    start_date          datetime     not null comment '발급 시작일',
    end_date            datetime     not null comment '발급 종료일',
    reissue_yn          varchar(2)  not null default 'N' comment '재발행 가능 여부',
    use_period          int          not null default 7 comment '사용기간(발급일로부터 기본 7일)',
    created_date        datetime     null,
    last_modified_date  datetime     null,
    created_by          bigint       not null,
    last_modified_by    bigint       null
)
    engine = InnoDB;