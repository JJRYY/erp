package erp.dao;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import erp.dao.impl.DepartmentDaoImpl;
import erp.dto.Department;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentDaoTest {
	private static DepartmentDao dao = DepartmentDaoImpl.getInstance();

	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void test04SelectDepartmentByAll() {
		System.out.printf("%s()%n", "testSelectTitleByAll");
		List<Department> departmentList = dao.selectDepartmentByAll();
		Assert.assertNotNull(departmentList);
		for (Department d : departmentList) {
			System.out.println(d);
		}
	}

	@Test
	public void test05SelectDepartmentByNo() {
		System.out.printf("%s()%n", "testSelectDepartmentByNo");
		Department department = new Department(3);
		Department searchDepartment = dao.selectDepartmentByNo(department);
		Assert.assertNotNull(searchDepartment);
		System.out.println(searchDepartment);
	}

	@Test
	public void test01InsertDepartment() {
		System.out.printf("%s()%n", "testInsertDepartment");
		Department newDepartment = new Department(5, "마케팅", 15);
		int res = dao.insertDepartment(newDepartment);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectDepartmentByNo(newDepartment));
	}

	@Test
	public void test02UpdateDepartment() {
		System.out.printf("%s()%n", "testUpdateDepartment");
		Department newDepartment = new Department(5, "인사", 20);
		int res = dao.updateDepartment(newDepartment);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectDepartmentByNo(newDepartment));
	}

	@Test
	public void test03DeleteDepartment() {
		System.out.printf("%s()%n", "testDeleteDepartment");
		int res = dao.deleteDepartment(5);
		Assert.assertEquals(1, res);
		dao.selectDepartmentByAll().stream().forEach(System.out::println);
	}

}
