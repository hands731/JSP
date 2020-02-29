create schema documentdb;

use documentdb;

show db;

create table usertbl (
	userid varchar(20) primary key ,
    password varchar(20) not null,
    name varchar(20) not null,
    grade varchar(20) not null,
    class varchar(20) not null,
    position varchar(20) not null
);


select * from usertbl where userid='asdfa' and password='bxcbx';

create table grouptbl(
	groupname varchar(20) PRIMARY key,
    -- attendingStu varchar(20) ,
    instructor varchar(20),
    -- FOREIGN KEY (attendingStu)  REFERENCES usertbl(userid),
    FOREIGN KEY (instructor)  REFERENCES usertbl(userid)
);

create table groupmembertbl(
	groupname varchar(20) ,
	userid varchar(20),
    FOREIGN KEY (groupname) REFERENCES grouptbl(groupname),
    FOREIGN KEY (userid) REFERENCES usertbl(userid)
);

drop table personaltbl;
drop table boardtbl;
drop table documenttbl;
drop table groupmembertbl;
drop table grouptbl;
drop table usertbl;


create table documenttbl(
	docid int(20) primary key AUTO_INCREMENT,
    doctype varchar(20),
    title varchar(20),
    content text,
    ctime TIMESTAMP,
    des1 varchar(20),
    des2 varchar(20),
    des3 varchar(20),
    sender varchar(20),
    FOREIGN KEY (sender) REFERENCES usertbl(userid)
);


create table personaltbl(
	userid varchar(20),
    listid varchar(20),
    isread int,
    docid int,
    state varchar(20),
    isdelete int,
    FOREIGN KEY (userid) REFERENCES usertbl(userid),
    FOREIGN KEY (docid) REFERENCES documenttbl(docid)
);

select docid from boardtbl where boardid = 'java';

create table boardtbl(
	boardid varchar(20),
    boardseq int,
    docid int,
    FOREIGN KEY (boardid) REFERENCES grouptbl(groupname),
    FOREIGN KEY (docid) REFERENCES documenttbl(docid)
);

show tables;
desc usertbl;

insert into usertbl values('aa', '11', 'sonjun', '3', 'A', 'student');
insert into usertbl values('bb', '11', 'teageon', '3', 'A', 'professor');
insert into usertbl values('cc', '11', 'suckzoo', '3', 'B', 'professor');
insert into usertbl values('dd', '11', 'minhwan', '3', 'C', 'professor');


desc grouptbl;
insert into grouptbl values('groupA',  'bb');

desc documenttbl;
insert into documenttbl values(null, 'PERSONAL_TYPE', '개인문서입니다.', '아무내용',sysdate(), 'cc',null,null,'aa' );
insert into documenttbl values(null, 'APPROVAL_TYPE', '결재문서입니다.', '아무내용',sysdate(), 'bb','cc','dd','aa' );

delete from documenttbl;
delete from personaltbl;

desc personaltbl;


delimiter &&

create trigger sampletrigger
	after insert
    on documenttbl
    for each ROW
begin
	if new.doctype = 'PERSONAL_TYPE'  then
        insert into personaltbl values(new.des1,'receiveList', 0 , new.docid, null, 0);
        insert into personaltbl values(new.sender,'sendList', 0 , new.docid, null, 0);
	END IF;
    if new.doctype = 'APPROVAL_TYPE' then
        insert into personaltbl values(new.des1, 'receiveList',0, new.docid, '결제대기', 0);
         insert into personaltbl values(new.sender, 'sendList', 0, new.docid, '결제대기', 0);
	end if;
end &&
delimiter ;

drop trigger sampletrigger;


-- 개인문서 수신목록함 bb의 수신함
select  documenttbl.docid, documenttbl.title, documenttbl.ctime, documenttbl.sender, personaltbl.isRead, personaltbl.isdelete from personaltbl 
inner join documenttbl
on documenttbl.docid = personaltbl.docid
where doctype = 'PERSONAL_TYPE' and documenttbl.des1 = 'bb' and personaltbl.listid = 'receiveList'
order by ctime desc;

-- 개인문서 발신 목록함 aa의 발신함
select  documenttbl.docid, documenttbl.title, documenttbl.ctime, documenttbl.sender, documenttbl.des1, personaltbl.isdelete from personaltbl 
inner join documenttbl
on documenttbl.docid = personaltbl.docid
where doctype = 'PERSONAL_TYPE' and documenttbl.sender = 'aa' and personaltbl.listid = 'sendList'
order by ctime desc;


-- 결재문서 수신 목록함
select  documenttbl.docid, documenttbl.title, documenttbl.ctime, documenttbl.sender, personaltbl.isRead, personaltbl.isdelete from personaltbl 
inner join documenttbl
on documenttbl.docid = personaltbl.docid
where doctype = 'APPROVAL_TYPE' and documenttbl.des1 = 'bb' and personaltbl.listid = 'receiveList'
order by ctime desc;









select * from usertbl;
select * from grouptbl;
desc personaltbl;
desc documenttbl;
select * from documenttbl;
select * from personaltbl;

