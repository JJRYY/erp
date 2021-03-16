package erp.ui;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import erp.dto.Employee;
import erp.service.EmployeeService;
import erp.ui.content.AbstractContentPanel;
import erp.ui.content.EmployeePanel;
import erp.ui.list.AbstractCustomTablePanel;
import erp.ui.list.EmployeeTablePanel;

@SuppressWarnings("serial")
public class EmployeeManagerUI extends AbstractManagerUI<Employee> {
	private EmployeeService service;
	
	@Override
	protected void setService() {
		service = new EmployeeService();
	}

	@Override
	protected void tableLoadData() {
		((EmployeeTablePanel)pList).setService(service);
		pList.loadData();
	}

	@Override
	protected AbstractContentPanel<Employee> createContentPanel() {
		EmployeePanel empPanel = new EmployeePanel();
		empPanel.setService(service);
		return empPanel;
	}

	@Override
	protected AbstractCustomTablePanel<Employee> createTablePanel() {
		return new EmployeeTablePanel();
	}

	@Override
	protected void actionPerformedMenuGubun() {
		throw new UnsupportedOperationException("제공되지 않음");
	}

	@Override
	protected void actionPerformedMenuUpdate() {
		Employee updateEmp = pList.getItem();
		pContent.setItem(updateEmp);
		btnAdd.setText("수정");
	}

	@Override
	protected void actionPerformedMenuDelete() {
		Employee delEmp = pList.getItem();
		service.removeEmployee(delEmp);
		pList.loadData();
		JOptionPane.showMessageDialog(null, delEmp + " 삭제 되었습니다.");
	}

	@Override
	protected void actionperformedBtnUpdate(ActionEvent e) {
		Employee updateEmp = pContent.getItem();
		service.updateEmployee(updateEmp);
		pList.loadData();
		btnAdd.setText("추가");
		JOptionPane.showMessageDialog(null, updateEmp.getEmpName() + " 정보가 수정되었습니다.");
		pContent.clearTf();
	}

	@Override
	protected void actionPerformedBtnAdd(ActionEvent e) {
		Employee newEmp = pContent.getItem();
		service.addEmployee(newEmp);
		JOptionPane.showMessageDialog(null, newEmp + " 추가했습니다.");
		pList.loadData();
		pContent.clearTf();
	}

}
