
    drop table if exists phone CASCADE ;

    drop table if exists user CASCADE ;


    create table phone (
       id varchar(255) not null,
        citycode integer not null,
        countrycode varchar(255),
        number bigint not null,
        user_id varchar(255) not null,
        primary key (id)
    );

    create table user (
       id varchar(255) not null,
        created timestamp,
        email varchar(255) not null,
        is_active boolean,
        last_login timestamp,
        modified timestamp,
        name varchar(255),
        password varchar(255) not null,
        token varchar(500),
        primary key (id)
    );

    alter table user
       add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email);

    alter table phone
       add constraint FKb0niws2cd0doybhib6srpb5hh
       foreign key (user_id)
       references user;