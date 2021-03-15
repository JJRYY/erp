package erp.service;

import java.util.List;

import erp.dao.DepartmentDao;
import erp.dao.EmployeeDao;
import erp.dao.TitleDao;
import erp.dao.impl.DepartmentDaoImpl;
import erp.dao.impl.EmployeeDaoImpl;
import erp.dao.impl.TitleDaoImpl;
import erp.dto.Department;
import erp.dto.Employee;
import erp.dto.Title;

public class EmployeeService {
	private DepartmentDao deptDao = DepartmentDaoImpl.getInstance();
	private TitleDao titleDao = TitleDaoImpl.getInstance();
	private EmployeeDao empDao = EmployeeDaoImpl.getInstance();
	
	public List<Employee> showEmployees(){
		return empDao.selectEmployeeByAll();
	}
	
	public void addEmployee (Employee employee) {
		empDao.insertEmployee(employee);
	}
	
	public void removeEmployee (Employee employee) {
		empDao.deleteEmployee(employee);
	}
	
	public void updateEmployee (Employee employee) {
		empDao.updateEmployee(employee);
	}
	
	public List<Department> showDeptList() {
		return deptDao.selectDepartmentByAll();
	}
	
	public List<Title> showTitleList() {
		return titleDao.selectTitleByAll();
	}
	
	public List<Employee> selectEmployeeByDept (Department dept){
		return empDao.selectEmployeeByDept(dept);
	}
	
	
}
