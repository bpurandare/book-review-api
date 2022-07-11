create table Book(
    book_id number not null,
    isin varchar2(20) not null,
    book_name varchar2(50) not null,
    book_author varchar2(50) not null,
    edition number not null,
    publisher varchar2(50) not null,
    publish_date date not null,
    tags varchar2(100)
);

create table book_review(
 review_id number,
 review_content varchar2(2000),
 review_date date,
 book_id number,
 user_id number
);

create table User(
    user_id number,
    username varchar2(20),
    password varchar2(20),
    role varchar2(20),
    enabled varchar2(2),
    email varchar2(50),
    city varchar2(20)
);