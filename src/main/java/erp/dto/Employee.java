package erp.dto;

public class Employee {
	private int empNo;
	private String empName;
	private Title title;
	private Employee manager;
	private int salary;
	private Department dept;

	public Employee() {
	}

	public Employee(int empNo) {
		this.empNo = empNo;
	}

	public Employee(int empNo, String empName) {
		this.empNo = empNo;
		this.empName = empName;
	}

	public Employee(int empNo, String empName, Title title, Employee manager, int salary, Department dept) {
		this.empNo = empNo;
		this.empName = empName;
		this.title = title;
		this.manager = manager;
		this.salary = salary;
		this.dept = dept;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	@Override
	public String toString() {
		return String.format("%s(%s)", empName, empNo);
	}

	
	public String toString2() {
		return String.format("%s, %s, %s, %s, %s, %s", empNo,
				empName, title.gettNo(), manager.getEmpNo(), salary, dept.getDeptNo());
	}
	
//	@Override
//	public String toString() {
//		return String.format("%s, %s, %s, %s, %4d, %s, %s, %s, %s, %2d", 
//				empNo, empName, title.gettNo(), title.gettName(), manager.getEmpNo(), manager.getEmpName(), 
//				salary, dept.getDeptNo(), dept.getDeptName(), dept.getFloor());
//	}
	
}
