create table camping_info --ppt42  어드민85
(
    campinfo_id          bigint auto_increment primary key,
    category_id          bigint    not null comment '카테고리 id',
    title                varchar(255)  null comment '제목',
    commend              varchar(255)  null comment '간략한소개(한줄평)',
    recom_type           int(12)       null comment '추천타입(0:크리에이터,1:에디터)', --코드테이블 쓸지 고민중
    thumbnail_type       int(12)       null comment '썸네일유형(0:동영상,1:사진 )',
    content              longtext      null comment '게시글 내용',
    location             varchar(255)  null comment '주소 카테고리가 장소인경우',
    lowest_prc	         int(10)       null comment '최저가 카테고리가 장소인경우',
    location_id	         bigint        null comment	'지역id 카테고리가 장소인경우',--코드테이블,
    camp_type		     bigint        null comment '캠핑장 유형카테고리가 장소인경우',--코드테이블,
    book_url	         varchar(255)  null comment '예약링크 카테고리가 장소인경우',
    status                  int(12)   not null default 0 comment '게시 여부',--0,1,2-삭제 게시여부(어드민 설정, 근데 어드민에서 강제 삭제시 사용자한테는 어떻게 표기되는지
    grade                int(12)       null comment  '금은동', --배치라고 생각,
    like_cnt             int(12)       null comment '좋아요 개수',
    disp_start_date      datetime      null comment '전시 시작일',
    disp_end_date        datetime      null comment '전시 종료일',
    created_date         datetime      null,
    last_modified_date   datetime      null,
    created_by           bigint    not null,
    last_modified_by     bigint        null
    constraint FK_CATEGORY_1
        foreign key (category_id) references category (category_id)
)
    engine = InnoDB;

