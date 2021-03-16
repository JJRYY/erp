package erp.dto;

import java.util.Arrays;
import java.util.Date;

public class EmployeeDetail {
	private int empNo;
	private boolean gender;
	private Date hireDate;
	private String pass;
	private byte[] pic;

	public EmployeeDetail() {
	}

	public EmployeeDetail(int empNo) {
		this.empNo = empNo;
	}

	public EmployeeDetail(int empNo, boolean gender, Date hireDate, byte[] pic) {
		this.empNo = empNo;
		this.gender = gender;
		this.hireDate = hireDate;
		this.pic = pic;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public byte[] getPic() {
		return pic;
	}

	public void setPic(byte[] pic) {
		this.pic = pic;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public String toString() {
		return String.format("EmpDetail [empNo=%s, gender=%s, hireDate=%s, pic=%s]", empNo, gender, hireDate,
				Arrays.toString(pic));
	}

}
