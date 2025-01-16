-- 이 파일은 DDL 문서화 목적으로 작성하였음 --
create table `user`
(
    `id`         bigint primary key auto_increment,
    `email`      varchar(255) not null unique,
    `nickname`   varchar(255) not null,
    `password`   varchar(255) not null,
    `created_at` datetime(6) not null,
    `updated_at` datetime(6) not null,
    `deleted_at` datetime(6) null
) engine=InnoDB default charset=utf8mb4;

CREATE INDEX idx_user_created_at ON `user` (`created_at`);
CREATE INDEX idx_user_deleted_at ON `user` (`deleted_at`);
