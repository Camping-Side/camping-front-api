create table roles_resources
(
    resource_id bigint not null,
    role_id     bigint not null,
    primary key (resource_id, role_id),
    constraint FK_ROLES_RESOURCES_1
        foreign key (resource_id) references resources (resource_id),
    constraint FK_ROLES_RESOURCES_2
        foreign key (role_id) references role (role_id)
)
    engine = MyISAM;