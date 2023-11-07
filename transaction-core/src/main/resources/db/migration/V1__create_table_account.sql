create sequence sq_account_id;

alter sequence sq_account_id owner to postgres;

create table tb_account
(
    id_account      bigint      not null
        constraint tb_account_pkey
            primary key,
    int_document bigint not null
        constraint tb_account_accountname_unique
            unique
);

alter table tb_account
    owner to postgres;
