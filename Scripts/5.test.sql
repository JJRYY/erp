select user(), database();

select * from title;
select * from employee;

-- 해당 직책을 가지고 있는 사원목록을 검색
select e.empname, e.empno
	from employee e join title t on e.title = t.tno
	where t.tno = 5;
	
-- 해당 부서에 속한 사원목록을 검색
select e.empname, e.empno
	from employee e join department d on e.dept = d.deptno 
	where d.deptno = 1;
	
select * from employee 
	where empno = 1003;
	
-- pass 길이 확인
-- 단방향 함수(Hash:MD5)
select password('aaa'), length(password('asfsdafaseraeraaaa')) from dual;

-- emp_detail insert
INSERT INTO erp.emp_detail(empno, pic, gender, hireDate, pass)
VALUES(?, ?, ?, ?, ?);

select * from emp_detail;

select empno, pic, gender, hireDate, pass from emp_detail where empno = ?;

delete from emp_detail where empno = 1003;


update emp_detail set pic = ?, gender = ?, hireDate = ?, pass = password(?) where empno = 1003;