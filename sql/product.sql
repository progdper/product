----계정삭제
--DROP USER c##product CASCADE;
----계정생성
--CREATE USER c##product IDENTIFIED BY product1234
--    DEFAULT TABLESPACE users
--    TEMPORARY TABLESPACE temp
--    PROFILE DEFAULT;
----계정권한부여
--GRANT CONNECT, RESOURCE TO c##product;
--GRANT CREATE VIEW, CREATE SYNONYM TO c##product;
--GRANT UNLIMITED TABLESPACE TO c##product;
----락 풀기
--ALTER USER c##product ACCOUNT UNLOCK;

--테이블 삭제
drop table product;
--테이블 생성
create table product (
    pid          number(10),
    pname        varchar2(50) not null,
    pquantity    number(10) not null,
    pprice       number(10) not null
);

--시퀀스 삭제
drop sequence product_product_id_seq;
--시퀀스 생성
create sequence product_product_id_seq;

--기본키생성
alter table product add constraint product_pid_pk primary key (pid);

--샘플데이터
delete product;
insert into product (pid, pname, pquantity, pprice) values (product_product_id_seq.nextval, '상품1', 100, 1000);
insert into product (pid, pname, pquantity, pprice) values (product_product_id_seq.nextval, '상품2', 300, 1500);
insert into product (pid, pname, pquantity, pprice) values (product_product_id_seq.nextval, '상품3', 120, 500);

commit;

