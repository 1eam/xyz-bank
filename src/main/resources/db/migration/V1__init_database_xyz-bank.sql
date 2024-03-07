create sequence _user_seq
    increment by 50;

create sequence account_type_seq
    increment by 50;

create sequence address_seq
    increment by 50;

create sequence bank_account_seq
    increment by 50;

create sequence country_seq
    increment by 50;

create sequence currency_seq
    increment by 50;

create sequence customer_seq
    increment by 50;

create sequence role_seq
    increment by 50;

create table country
(
    id   bigint not null
        primary key,
    iso  varchar(2)
        constraint country_iso_unique
            unique,
    name varchar(255)
        constraint country_name_unique
            unique
);

create table address
(
    id           bigint not null
        primary key,
    street       varchar(255),
    house_number integer,
    postal_code  varchar(6),
    city         varchar(255),
    country_id   bigint
        constraint fke54x81nmccsk5569hsjg1a6ka
            references country
);

create table customer
(
    id            bigint not null
        primary key,
    government_id varchar(255)
        constraint "customer_government-id_unique"
            unique,
    first_name    varchar(255),
    last_name     varchar(255),
    date_of_birth date,
    address_id    bigint
        constraint fkglkhkmh2vyn790ijs6hiqqpi
            references address
);

create table role
(
    id   bigint not null
        primary key,
    role varchar(255)
        constraint role_role_unique
            unique
        constraint role_role_check
            check ((role)::text = 'USER'::text)
);

create table _user
(
    id          bigint not null
        primary key,
    username    varchar(255)
        constraint user_username_unique
            unique,
    password    varchar(255),
    customer_id bigint
        unique
        constraint fknf3xgxkbcarxe8oxvt0utqsrj
            references customer,
    role_id     bigint
        constraint fk7wki6bxsew9feo0m49h59hiec
            references role

);

create table currency
(
    id  bigint not null
        primary key,
    iso varchar(3)
        constraint currency_iso_unique
            unique
);

create table account_type
(
    id   bigint not null
        primary key,
    name varchar(255)
        constraint "account-type_name_unique"
            unique
);

create table bank_account
(
    id                 bigint       not null
        primary key,
    iban               varchar(18)
        constraint "bank-account_iban_unique"
            unique,
    "account-type_id"  bigint
        constraint fk90kun57qgpprffdo6dwoq9kvn
            references account_type,
    currency_id        bigint
        constraint fkb0rqy46m451rdbnhil6kkvlve
            references currency,
    balance            real,
    _user_id           bigint
        unique
        constraint fkcc46oty2d4542kikgjdmn5yay
            references _user,
    created_date       timestamp(6) not null,
    last_modified_date timestamp(6)
);