create table special_plan -- 기획전 관리
(
    special_plan_id         bigint auto_increment primary key           comment '기획전 id',
    special_plan_nm         varchar(500)    not null                    comment '기획전 명',
    category_id             bigint          not null                    comment '기획전 카테고리(category : category_id 참조)',
    desc                    varchar(1000)   null                        comment '기획전 설명',
    start_date              datetime        not null                    comment '기획전 시작날짜',
    end_date                datetime        not null                    comment '기획전 종료날짜',
    show_yn                 varchar(2)      default 'N'                 comment '기획전 노출여부(Y:노출, N:비노출)',
    sts                     bigint          null  default'0'            comment '기획전 상태값(0:대기, 1:진행중, 2:종료)',
    created_date            datetime        not null,
    last_modified_date      datetime        null,
    created_by              bigint          not null,
    last_modified_by        bigint          null,
    constraint FK_SPECIAL_PLAN_1
        foreign key (category_id) references category (category_id)
)
    engine = InnoDB;