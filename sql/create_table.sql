# 数据库初始化

-- 创建库
create database if not exists my_db;

-- 切换库
use my_db;

-- 用户表
create table if not exists user
(
    id            bigint auto_increment comment 'id' primary key,
    username      varchar(256)                           null comment '用户名',
    user_password varchar(512)                           null comment '密码',
    nickname      varchar(256)                           null comment '昵称',
    user_avatar   varchar(1024)                          null comment '用户头像',
    user_profile  varchar(512)                           null comment '用户简介',
    role_id       bigint                                 null comment '角色id',
    create_time   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete     tinyint      default 0                 not null comment '是否删除'
    ) comment '用户表' collate = utf8mb4_unicode_ci;

-- 角色表
create table if not exists role
(
    id         bigint auto_increment comment 'id' primary key,
    role_name    varchar(64)                            null comment '角色名称',
    role_desc    varchar(256)                           null comment '角色描述',
    role_type    varchar(2)                             null comment '角色类型',
    create_time  datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time  datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete    tinyint      default 0                 not null comment '是否删除'
    ) comment '角色表' collate = utf8mb4_unicode_ci;


-- 权限表
create table if not exists authority
(
    id             bigint auto_increment comment 'id' primary key,
    code           varchar(64)                            null comment '权限标识符',
    menu_name      varchar(64)                            null comment '菜单名称',
    order_no       int                                    null comment '菜单顺序',
    parent_id      bigint                                 null comment '父节点id',
    authority_type varchar(10)                            null comment '权限类型 menu/button',
    route_path     varchar(128)                           null comment '路由路径',
    hidden         tinyint                                null comment '是否隐藏路由菜单(0 - 否，1 - 是)',
    menu_icon      varchar(64)                            null comment '菜单图标',
    component      varchar(128)                           null comment '组件',
    component_name varchar(64)                            null comment '组件名称',
    create_time    datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time    datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete      tinyint      default 0                 not null comment '是否删除'
    ) comment '权限表' collate = utf8mb4_unicode_ci;

-- 角色权限表
create table if not exists role_authority
(
    id             bigint auto_increment comment 'id' primary key,
    authority_id   bigint                                 null comment '权限id',
    role_id        bigint                                 null comment '角色id',
    create_time    datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time    datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete      tinyint      default 0                 not null comment '是否删除'
) comment '角色权限表' collate = utf8mb4_unicode_ci;

