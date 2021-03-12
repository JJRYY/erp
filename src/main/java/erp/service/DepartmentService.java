package erp.service;

import java.util.List;

import erp.dao.DepartmentDao;
import erp.dao.impl.DepartmentDaoImpl;
import erp.dto.Department;

public class DepartmentService {
	private DepartmentDao dao = DepartmentDaoImpl.getInstance();
	
	public List<Department> showDepartments(){
		return dao.selectDepartmentByAll();
	}
}
