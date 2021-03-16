package erp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import erp.dao.EmployeeDetailDao;
import erp.database.JdbcConn;
import erp.dto.Employee;
import erp.dto.EmployeeDetail;
import erp.ui.exception.SqlConstraintException;

public class EmployeeDetailDaoImpl implements EmployeeDetailDao {
	private static EmployeeDetailDaoImpl instance = new EmployeeDetailDaoImpl();

	public static EmployeeDetailDaoImpl getInstance() {
		if (instance == null) {
			instance = new EmployeeDetailDaoImpl();
		}
		return instance;
	}
	
	private EmployeeDetailDaoImpl() {
	}

	@Override
	public EmployeeDetail selectEmployeeDetailByNo(Employee employee) {
		String sql = "select empno, pic, gender, hireDate" // 원래 pass는 받아오지 않는게 좋음.
				+ " from emp_detail"
				+ " where empno = ?";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, employee.getEmpNo());
			try(ResultSet rs = pstmt.executeQuery()){
				if (rs.next()) {
					return getEmployeeDetail(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private EmployeeDetail getEmployeeDetail(ResultSet rs) throws SQLException {
		int empNo = rs.getInt("empno");
		boolean gender = rs.getBoolean("gender");
		Date hireDate = rs.getTimestamp("hireDate");
		byte[] pic = rs.getBytes("pic");
		
		return new EmployeeDetail(empNo, gender, hireDate, pic);
	}

	@Override
	public int insertEmployeeDetail(EmployeeDetail empDetail) {
		String sql = "INSERT INTO erp.emp_detail(empno, pic, gender, hireDate, pass)" 
				+ " VALUES(?, ?, ?, ?, password(?))";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, empDetail.getEmpNo());
			pstmt.setBytes(2, empDetail.getPic());
			pstmt.setBoolean(3, empDetail.isGender());
			// util.Date -> sql.Date 로 변환
			pstmt.setTimestamp(4, new Timestamp(empDetail.getHireDate().getTime()));
			pstmt.setString(5, empDetail.getPass());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new SqlConstraintException(e.getMessage(), e);
		}
	}

	@Override
	public int updateEmployeeDetail(EmployeeDetail empDetail) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteEmployeeDetail(Employee employee) {
		String sql = "delete from emp_detail where empno = ?";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, employee.getEmpNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
