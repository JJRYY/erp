package erp.service;

import java.util.List;

import erp.dao.DepartmentDao;
import erp.dao.EmployeeDao;
import erp.dao.impl.DepartmentDaoImpl;
import erp.dao.impl.EmployeeDaoImpl;
import erp.dto.Department;
import erp.dto.Employee;

public class DepartmentService {
	private DepartmentDao dao = DepartmentDaoImpl.getInstance();
	private EmployeeDao empDao = EmployeeDaoImpl.getInstance();
	
	public List<Department> showDepartments(){
		return dao.selectDepartmentByAll();
	}
	
	public void addDepartment(Department department) {
		dao.insertDepartment(department);
	}
	
	public void removeDepartment(Department department) {
		dao.deleteDepartment(department.getDeptNo());
	}
	
	public void updateDepartment(Department department) {
		dao.updateDepartment(department);
	}
	
	public List<Employee> selectEmployeeByDept (Department dept){
		return empDao.selectEmployeeByDept(dept);
	}
}
