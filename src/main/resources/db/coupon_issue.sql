drop table if exists coupon_issue;

create table coupon_issue
(
    cpn_issue_id        bigint auto_increment primary key,
    cpn_id              bigint comment '쿠폰 id',
    account_id          bigint comment '회원 id',
    issue_cpn_sts       int(2) not null default 0 comment '발급쿠폰 상태(0 발급, 1 사용, 2 취소)',
    start_date          datetime     not null comment '사용 시작일',
    end_date            datetime     not null comment '사용 종료일',
    reissue_yn          varchar(2)  not null default 'N' comment '재발행여부',
    use_period          int          not null default 7 comment '사용기간(발급일로부터 기본 7일)',
    created_date        datetime     null,
    last_modified_date  datetime     null,
    created_by          bigint       not null,
    last_modified_by    bigint       null
)
    engine = InnoDB;