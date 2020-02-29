create schema sample;

use sample;






create table usertbl (
	userid varchar(20) primary key ,
    password varchar(20) not null,
    name varchar(20) not null,
    grade varchar(20) ,
    class varchar(20) ,
    position varchar(20) not null
);


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

select sysdate();

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
    groupname varchar(20),
    FOREIGN KEY (groupname) REFERENCES grouptbl(groupname),
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
insert into usertbl values('1A', '11', '1Aname', '1', 'A', 'student');
insert into usertbl values('1B', '11', '1Bname', '1', 'A', 'student');
insert into usertbl values('1C', '11', '1Cname', '1', 'B', 'student');
insert into usertbl values('1D', '11', '1Dname', '1', 'B', 'student');
insert into usertbl values('2A', '11', '2Aname', '2', 'A', 'student');
insert into usertbl values('2B', '11', '2Bname', '2', 'A', 'student');
insert into usertbl values('2C', '11', '2Cname', '2', 'B', 'student');
insert into usertbl values('2D', '11', '2Dname', '2', 'B', 'student');
insert into usertbl values('professorA', '11', 'professorA', null, null, 'professor');
insert into usertbl values('professorB', '11', 'professorB', null, null, 'professor');
insert into usertbl values ('professorC', '11' ,'professorC', null, null, 'professor');
insert into usertbl values('professorD', '11','professorD',null,null,'professor');
--

select * from usertbl;

desc grouptbl;
insert into grouptbl values('groupA',  'professorA');
insert into grouptbl values('groupB', 'professorB');


insert into groupmembertbl values('groupA', '1A');
insert into groupmembertbl values('groupB', '1A');
 
 select * from personaltbl where userid = '1A';
 
 
 select * from groupmembertbl;

insert into groupmembertbl values('groupB', 'studentAgroupB');
insert into groupmembertbl values('groupB', 'studentBgroupB');


desc documenttbl;
insert into documenttbl values(null, 'PERSONAL_TYPE', '개인문서입니다.', '1A->1B',sysdate(), '1B',null,null,'1A', null );
insert into documenttbl values(null, 'APPROVAL_TYPE', '결재문서입니다.', '안녕하세요 강석주',sysdate(), 'bb','cc','dd','aa' , null);
insert into documenttbl values(null, 'GROUP_TYPE', '그룹 문서 입니다. ' , 'groupA문서' , sysdate(), null, null, null, '1A', 'groupA');
insert into documenttbl values(null, 'PERSONAL_TYPE', '개인문서입니다.', 'ㅈㅈ하세요',sysdate(), 'bb',null,null,'cc', null );
insert into documenttbl values(null, 'GROUP_TYPE', '그룹 문서 입니다. ' , '아무내용' , sysdate(), null, null, null, 'studentAgroupB', 'groupB');
insert into documenttbl values(null, 'GROUP_TYPE', '그룹 문서 입니다. ' , '아무내용' , sysdate(), null, null, null, 'studentBgroupB', 'groupB');
insert into documenttbl values(null, 'PERSONAL_TYPE', '읽은개인문서.', '읽음확인',sysdate(), 'cc',null,null,'aa', null );
insert into documenttbl values(null, 'REPLY_TYPE_1', 're1.', '읽음확인',sysdate(), null,null,null,'1A', 'groupA' );
insert into documenttbl values(null, 'REPLY_TYPE_1', 're1.', '읽음확인',sysdate(), null,null,null,'1A', 'groupA' );


delete from documenttbl;

-- boardtbl insert
desc boardtbl;
insert into boardtbl values('groupA',799999,7);
insert into boardtbl values('groupB',1199998,12);
insert into boardtbl values('groupB',1199999,11);
select * from boardtbl;
delete from boardtbl;

update personaltbl set isdelete = 1 where docid =25 and listid = 'receiveList';
update personaltbl set isdelete = 1 where docid = 14 and listid = 'receiveList';
delete from personaltbl where docid = 16 and  listid = 'sendList';



select * from documenttbl;
select * from personaltbl;

select * from grouptbl;
select * from groupmembertbl;


delete from documenttbl;
delete from personaltbl;

select documenttbl.docid, documenttbl.title, documenttbl.ctime, documenttbl.sender, documenttbl.groupname from documenttbl inner join boardtbl on boardtbl.docid = documenttbl.docid where groupname = 'groupA'; 

desc personaltbl;

-- insert trigger
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

desc boardtbl;
select * from boardtbl;
drop trigger sampletrigger;

drop trigger droptrigger;
-- drop trigger
delimiter &&
create trigger droptrigger
	after delete
    on personaltbl
    for each ROW
begin
	declare var1 varchar(20);
    
    
	select userid into var1 from personaltbl where docid=old.docid;
    
    insert into ss values(var1);
    
   if var1 is null  then
		delete from documenttbl where docid=old.docid;
	end if;

	
end &&
delimiter ;
create table ss(
	ss varchar(20)
);


select * from ss;
select * from personaltbl ;
select * from documenttbl;

delete from personaltbl where docid = 4 and  listid = "sendList";
delete from personaltbl where docid = 4 and  listid = "receiveList";


-- 개인문서 cc의 수신, 발신 목록함
select  documenttbl.docid, documenttbl.title, documenttbl.ctime, documenttbl.sender, personaltbl.isRead, documenttbl.des1 from personaltbl 
inner join documenttbl
on documenttbl.docid = personaltbl.docid
where doctype = 'PERSONAL_TYPE' and documenttbl.des1 = 'cc' and personaltbl.listid = 'receiveList' and isdelete = 0 and isRead = 0
order by ctime desc;

-- 개인문서 발신 목록함 aa의 발신함
select  documenttbl.docid, documenttbl.title, documenttbl.ctime, documenttbl.sender, documenttbl.des1 from personaltbl 
inner join documenttbl
on documenttbl.docid = personaltbl.docid
where doctype = 'PERSONAL_TYPE' and documenttbl.sender = 'aa' and personaltbl.listid = 'sendList' and isdelete = 0
order by ctime desc;


-- 결재문서 수신, 발신 목록함
select  documenttbl.docid, documenttbl.title, documenttbl.ctime, documenttbl.sender, personaltbl.isRead,documenttbl.des1, documenttbl.des2, documenttbl.des3, personaltbl.state from personaltbl 
inner join documenttbl
on documenttbl.docid = personaltbl.docid
where doctype = 'APPROVAL_TYPE' and documenttbl.des1 = 'bb' and personaltbl.listid = 'receiveList'  and isdelete = 0 and isRead =0
order by ctime desc;



-- 결재문서 발신 목록함
select  documenttbl.docid, documenttbl.title, documenttbl.ctime, documenttbl.sender, documenttbl.des1, personaltbl.state from personaltbl 
inner join documenttbl
on documenttbl.docid = personaltbl.docid
where doctype = 'APPROVAL_TYPE' and documenttbl.sender = 'aa' and personaltbl.listid = 'sendList'  and isdelete = 0
order by ctime desc;


-- 읽지 않은 개인문서 목록
select  documenttbl.docid, documenttbl.title, documenttbl.ctime, documenttbl.sender from personaltbl 
inner join documenttbl
on documenttbl.docid = personaltbl.docid
where doctype = 'PERSONAL_TYPE' and documenttbl.des1 = 'bb' and personaltbl.listid = 'receiveList' and isread = 0 and isdelete = 0
order by ctime desc;

-- 읽지 않은 결제문서 목록
select  documenttbl.docid, documenttbl.title, documenttbl.ctime, documenttbl.sender from personaltbl 
inner join documenttbl
on documenttbl.docid = personaltbl.docid
where doctype = 'APPROVAL_TYPE' and documenttbl.des1 = 'bb' and personaltbl.listid = 'receiveList' and isread =0 and isdelete = 0
order by ctime desc;

-- 'groupB' 의 문서 목록(서치까지)
select documenttbl.docid, documenttbl.title, documenttbl.ctime, documenttbl.sender, documenttbl.groupname from documenttbl inner join boardtbl on boardtbl.docid = documenttbl.docid where groupname = 'groupb' and sender like '%studentb%' order by boardtbl.boardseq desc;



desc boardtbl;
desc documenttbl;


-- 하나만 나오게해야됨
select  * from documenttbl left outer join personaltbl on documenttbl.docid = personaltbl.docid where documenttbl.docid = 8 limit 1;

-- 검색 1.개인, 결재문서
select  documenttbl.docid, documenttbl.title, documenttbl.ctime, documenttbl.sender, personaltbl.isRead, documenttbl.des1 from personaltbl 
inner join documenttbl
on documenttbl.docid = personaltbl.docid
where  documenttbl.des1 ='cc' and doctype = 'PERSONAL_TYPE'  and personaltbl.listid = 'receiveList' and isdelete = 0 and isRead = 0 and documenttbl.sender like '%a%'
order by ctime desc;

-- 검색 2.그룹문서
select docid, title, ctime, sender, groupname from documenttbl where groupname = 'groupB' and sender like '%studentA%' ;

-- 휴지통 목록함 보여주기
select documenttbl.doctype, documenttbl.sender, documenttbl.title, documenttbl.ctime from personaltbl inner join documenttbl on documenttbl.docid = personaltbl.docid where isdelete = 1 and des1 = 'aa' order by ctime;


select * from usertbl where userid = 'aa';

select * from usertbl;
select * from grouptbl;
select * from groupmembertbl;
desc personaltbl;
desc documenttbl;
select * from documenttbl; 
where docId = 5;
select * from personaltbl;
select * from boardtbl;
desc boardtbl;


-- 학생 그룹리스트
select groupName from groupmembertbl where userId = 'aaa';

-- 교수 그룹리스트
select groupName from grouptbl where instructor ='bb';



select * from documenttbl;
select * from boardtbl;


select * from documenttbl inner join personaltbl on documenttbl.docid = personaltbl.docid;

select * from documenttbl;
select * from usertbl;
select * from groupmembertbl;
select * from personaltbl;

delete from documenttbl;

select des1, des2, des3 from documenttbl where docid=2;
select * from documenttbl inner join personaltbl on documenttbl.docid = personaltbl.docid;
update personaltbl set state = '결재취소' where docid=4;
select des1, des2, des3 from documenttbl where docid=4 limit 1;
desc documenttbl;
select * from personaltbl;

select documenttbl.docid, documenttbl.title, documenttbl.ctime, documenttbl.sender, personaltbl.isRead,documenttbl.des1, documenttbl.des2, documenttbl.des3, personaltbl.state from personaltbl inner join documenttbl on documenttbl.docid = personaltbl.docid where doctype = 'APPROVAL_TYPE' and documenttbl.des1 = '1B' and personaltbl.listid = 'receiveList'  and isdelete = 0;

select documenttbl.docid, documenttbl.title, documenttbl.ctime, documenttbl.sender, personaltbl.isRead,documenttbl.des1, documenttbl.des2, documenttbl.des3, personaltbl.state from personaltbl inner join documenttbl on documenttbl.docid = personaltbl.docid where doctype = 'APPROVAL_TYPE' and personaltbl.userid='1B' and personaltbl.listid = 'receiveList'  and isdelete = 0; 
select personaltbl.docid, documenttbl.docid, documenttbl.title, documenttbl.ctime, documenttbl.sender, personaltbl.isRead,documenttbl.des1, documenttbl.des2, documenttbl.des3, personaltbl.state from personaltbl inner join documenttbl on documenttbl.docid = personaltbl.docid where doctype = 'APPROVAL_TYPE' and personaltbl.docid='2B' and personaltbl.listid = 'receiveList'  and isdelete = 0;


select * from documenttbl; 


desc groupmembertbl;
select * from groupmembertbl where userid = '1A';
select userid from groupmembertbl where groupname = 'groupA';