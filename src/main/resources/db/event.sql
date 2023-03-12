create table event -- 이벤트관리
(
    event_id                bigint auto_increment primary key   comment '이벤트 id',
    event_nm                varchar(500) not null               comment '이벤트 명',
    start_date              datetime     not null               comment '이벤트 시작날짜',
    end_date                datetime     not null               comment '이벤트 종료날짜',
    contents                longtext     null                   comment '이벤트 내용',
    show_yn                 varchar(2)   default 'N'            comment '이벤트 노출여부(Y:노출, N:비노출)',
    sts                     bigint       null  default'0'       comment '이벤트 상태값(0:대기, 1:진행중, 2:종료)',
    event_banner            varchar(500) null                   comment '배너 경로, 파일관리 테이블 별도확인 필요', --확인필요
    created_date            datetime     not null,
    last_modified_date      datetime     null,
    created_by              bigint       not null,
    last_modified_by        bigint       null
    --메인배너 노출 여부 (카테고리 값 추가)

    --constraint FK_EVENT_1
    --    foreign key (event_banner) references ** (**)
)
    engine = InnoDB;