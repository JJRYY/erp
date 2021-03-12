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