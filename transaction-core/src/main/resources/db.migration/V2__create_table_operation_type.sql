create sequence sq_operation_type_id;

alter sequence sq_operation_type_id owner to postgres;

create table tb_operation_type
(
    id_operation_type      bigint      not null
        constraint tb_operation_type_pkey
            primary key,
    txt_description varchar(50) not null
);

alter table tb_operation_type
    owner to postgres;
