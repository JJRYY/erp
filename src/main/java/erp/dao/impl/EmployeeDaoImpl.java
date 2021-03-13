package erp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import erp.dao.EmployeeDao;
import erp.database.JdbcConn;
import erp.dto.Department;
import erp.dto.Employee;
import erp.dto.Title;

public class EmployeeDaoImpl implements EmployeeDao {
	private static EmployeeDaoImpl instance = new EmployeeDaoImpl();

	public EmployeeDaoImpl() {
	}

	public static EmployeeDaoImpl getInstance() {
		if (instance == null) {
			instance = new EmployeeDaoImpl();
		}
		return instance;
	}

	@Override
	public List<Employee> selectEmployeeByAll() {
		String sql = "select empno, empname, title_no, title_name, manager_no, "
				+ "manager_name, salary, deptno, deptname, floor " 
				+ "from vw_full_employee";
		try (Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				List<Employee> list = new ArrayList<>();
				do {
					list.add(getEmployee(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	private Employee getEmployee(ResultSet rs) throws SQLException {
		int empNo = rs.getInt("empno");
		String empName = rs.getString("empname");
		Title title = null;
		Employee manager = null;
		int salary = 0;
		Department dept = null;
		
		try {
			title = new Title(rs.getInt("title_no"));
			manager = new Employee(rs.getInt("manager_no"));
			salary = rs.getInt("salary");
			dept = new Department(rs.getInt("deptno"));
		} catch (SQLException e) {}
		
		try {
			title.settName(rs.getString("title_name"));
		} catch (SQLException e) {}
		
		try {
			manager.setEmpName(rs.getString("manager_name"));
		} catch (SQLException e) {}
		
		try {
			dept.setDeptName(rs.getString("deptname"));
		} catch (SQLException e) {}
		
		try {
			dept.setFloor(rs.getInt("floor"));
		} catch (SQLException e) {}
		
		return new Employee(empNo, empName, title, manager, salary, dept);
	}

	@Override
	public Employee selectEmployeeByNo(Employee employee) {
		String sql = "select empno, empname, title as title_no, manager as manager_no, salary, dept as deptno " 
				+ "from employee "
				+ "where empno = ?";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, employee.getEmpNo());
			try(ResultSet rs = pstmt.executeQuery()){
				if (rs.next()) {
					return getEmployee(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public int insertEmployee(Employee employee) {
		String sql = "insert into employee values (?, ?, ?, ?, ?, ?)";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, employee.getEmpNo());
			pstmt.setString(2, employee.getEmpName());
			pstmt.setInt(3, employee.getTitle().gettNo());
			pstmt.setInt(4, employee.getManager().getEmpNo());
			pstmt.setInt(5, employee.getSalary());
			pstmt.setInt(6, employee.getDept().getDeptNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateEmployee(Employee employee) {
		String sql = "update employee "
				+ "set empname = ?, title = ?, manager = ?, salary = ?, dept = ? "
				+ "where empno = ?";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, employee.getEmpName());
			pstmt.setInt(2, employee.getTitle().gettNo());
			pstmt.setInt(3, employee.getManager().getEmpNo());
			pstmt.setInt(4, employee.getSalary());
			pstmt.setInt(5, employee.getDept().getDeptNo());
			pstmt.setInt(6, employee.getEmpNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteEmployee(Employee employee) {
		String sql = "delete from employee where empno = ?";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, employee.getEmpNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Employee> selectEmployeeByTitle(Title title) {
		String sql = "select empno, empname" + 
				" from employee e join title t on e.title = t.tno" + 
				" where t.tno = ?";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, title.gettNo());
			try(ResultSet rs = pstmt.executeQuery()){
				if (rs.next()) {
					List<Employee> list = new ArrayList<>();
					do {
						list.add(getEmployee(rs));
					} while (rs.next());
					return list;
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Employee> selectEmployeeByDept(Department dept) {
		String sql = "select empno, empname	"
				+ "from employee e join department d on e.dept = d.deptno "
				+ "where d.deptno = ?";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, dept.getDeptNo());
			try(ResultSet rs = pstmt.executeQuery()){
				if (rs.next()) {
					List<Employee> list = new ArrayList<>();
					do {
						list.add(getEmployee(rs));
					} while (rs.next());
					return list;
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
