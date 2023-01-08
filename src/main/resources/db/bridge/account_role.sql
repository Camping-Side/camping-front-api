create table account_roles
(
    account_id bigint not null,
    role_id    bigint not null,
    primary key (account_id, role_id),
    constraint FK_ACCOUNT_ROLE_1
        foreign key (role_id) references role (role_id),
    constraint FK_ACCOUNT_ROLE_2
        foreign key (account_id) references account (id)
)
    engine = InnoDB;