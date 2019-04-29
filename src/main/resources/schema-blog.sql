drop table if exists `article`;
create table `article`(
    article_id                  int primary key auto_increment,
    title                       varchar(50),
    content                     text not null,
    allow_comment               boolean not null default(0),
    hit                         int default(0),
    create_timestamp            timestamp default(now()) not null,
    last_modified_timestamp     timestamp default(now()) not null
);

drop table if exists `comment`;
create table `comment`(
    comment_id                  int primary key auto_increment,
    article_id                  int not null,
    content                     text not null,
    user_name                   char(20),
    user_email                  varchar(50),
    create_timestamp            timestamp default(now()) not null
);

drop table if exists `category`;
create table `category`(
    category_id                 int primary key auto_increment,
    name                        varchar(20) not null
);

drop table if exists `tag`;
create table `tag`(
    tag_id                      int primary key auto_increment,
    name                        varchar(20) not null
);

drop table if exists `category_relation`;
create table `category_relation`(
    category_relation_id        int primary key auto_increment,
    category_id                 int not null,
    article_id                  int not null
);

drop table if exists `tag_relation`;
create table `tag_relation`(
    tag_relation_id             int primary key auto_increment,
    tag_id                      int not null,
    article_id                  int not null
);
