# 构架探险 从零开始写Web框架


```sql
create table customer (
    id bigint(20) not null auto_increment,
    name varchar(255) default null,
    contact varchar(255) default null,
    telephone varchar(255) default null,
    remark text,
    PRIMARY KEY ( id )
) default char set=utf8;
```