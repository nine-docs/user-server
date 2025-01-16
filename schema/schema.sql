-- 이 파일은 DDL 문서화 목적으로 작성하였음 --
create table `user`
(
    `id`       bigint primary key auto_increment,
    `email`    varchar(255) not null unique,
    `nickname` varchar(255) not null,
    `password` varchar(255) not null
) engine=InnoDB default charset=utf8mb4;
