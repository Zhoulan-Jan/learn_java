drop database if exists sushi;

create database sushi character set utf8mb4;

use sushi;

-- 创建用户表
create table t_user(
    id int primary key auto_increment,
    username varchar (20) not null unique ,
    password varchar (32) not null ,
    email varchar (200)
);

insert into t_user (username, password, email) values ('admin','admin','admin@163.com');

select * from t_user;

-- 创建寿司表
create  table t_sushi(
    id int primary key auto_increment,
    name varchar (100),
    price decimal (11,2),
    sales int,
    stock int,
    img_path varchar (200)
);

-- 插入初始化测试数据
insert into t_sushi(name , price , sales , stock , img_path) values('金枪鱼寿司'  , 45 , 49 , 999 , 'static/img/金枪鱼寿司.jpeg');
insert into t_sushi(name , price , sales , stock , img_path) values('鸡蛋卷寿司'  , 60 , 15 , 999 , 'static/img/鸡蛋卷寿司.jpeg');
insert into t_sushi(name , price , sales , stock , img_path) values('鸡蛋羹'  , 65 , 20 , 999 , 'static/img/鸡蛋羹.jpeg');
insert into t_sushi(name , price , sales , stock , img_path) values('鲭鱼寿司'  , 55 , 16 , 999 , 'static/img/鲭鱼寿司.jpeg');
insert into t_sushi(name , price , sales , stock , img_path) values('三文鱼籽寿司'  , 55 , 27 , 999 , 'static/img/三文鱼籽寿司.jpeg');
insert into t_sushi(name , price , sales , stock , img_path) values('鳗鱼寿司'  , 70 , 30 , 999 , 'static/img/鳗鱼寿司.jpeg');
insert into t_sushi(name , price , sales , stock , img_path) values('天妇罗'  , 65 , 52 , 999 , 'static/img/天妇罗.jpeg');
insert into t_sushi(name , price , sales , stock , img_path) values('三文鱼籽紫菜卷'  , 80 , 28 , 999 , 'static/img/三文鱼籽紫菜卷.jpeg');
insert into t_sushi(name , price , sales , stock , img_path) values('章鱼寿司'  , 75 , 42 , 999, 'static/img/章鱼寿司.jpeg');
insert into t_sushi(name , price , sales , stock , img_path) values('海胆籽紫菜卷'  , 85 , 39 , 999 , 'static/img/海胆籽紫菜卷.jpeg');
insert into t_sushi(name , price , sales , stock , img_path) values('海胆籽寿司'  , 60 , 26 , 999 , 'static/img/海胆籽寿司.jpeg');
insert into t_sushi(name , price , sales , stock , img_path) values('绿茶'  , 45 , 67 , 999 , 'static/img/绿茶.jpeg');

select * from t_sushi;

create table t_order(
    order_id varchar (50) primary key,
    create_time datetime,
    price decimal (11,2),
    status int,
    user_id int,
    foreign key(user_id) references t_user(id)
);

create table t_order_item(
    id int primary  key auto_increment,
    name varchar (100),
    count int,
    price decimal (11,2),
    total_price decimal (11,2),
    order_id varchar (50),
    foreign key(order_id) references t_order(order_id)
);