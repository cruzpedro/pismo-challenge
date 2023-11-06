create sequence sq_transaction_id;

alter sequence sq_transaction_id owner to postgres;

create table tb_transaction
(
    id_transaction    bigint      not null
        constraint tb_transaction_pkey
            primary key,
    id_account        bigint      not null
        constraint tb_transaction_id_account_fk
            references tb_account,
    id_operation_type bigint      not null
        constraint tb_transaction_id_operation_type_fk
            references tb_operation_type,
    dat_event timestamp not null,
    num_amount numeric not null
);

alter table tb_transaction
    owner to postgres;
