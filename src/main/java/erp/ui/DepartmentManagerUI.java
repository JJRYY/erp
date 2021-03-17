package erp.ui;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JOptionPane;

import erp.dto.Department;
import erp.dto.Employee;
import erp.service.DepartmentService;
import erp.ui.content.AbstractContentPanel;
import erp.ui.content.DeptPanel;
import erp.ui.list.AbstractCustomTablePanel;
import erp.ui.list.DepartmentTablePanel;

@SuppressWarnings("serial")
public class DepartmentManagerUI extends AbstractManagerUI<Department> {
	private DepartmentService service;
	
	public DepartmentManagerUI() {
		empListByTitleItem.setText(AbstractManagerUI.DEPT_MENU);
	}

	@Override
	protected void setService() {
		service = new DepartmentService();
	}

	@Override
	protected void tableLoadData() {
		((DepartmentTablePanel)pList).setService(service);
		pList.loadData();
	}

	@Override
	protected AbstractContentPanel<Department> createContentPanel() {
		return new DeptPanel();
	}

	@Override
	protected AbstractCustomTablePanel<Department> createTablePanel() {
		return new DepartmentTablePanel();
	}

	@Override
	protected void actionPerformedMenuGubun() {
		Department selDept = pList.getItem();
		List<Employee> list = service.selectEmployeeByDept(selDept);
		if (list == null) {
			JOptionPane.showMessageDialog(null, "해당 부서 사원 없음");
		} else {
			JOptionPane.showMessageDialog(null, list);
		}
		
	}

	@Override
	protected void actionPerformedMenuUpdate() {
		btnAdd.setText("수정");
		pContent.setItem(pList.getItem());
		
	}

	@Override
	protected void actionPerformedMenuDelete() {
		Department delDept = pList.getItem();
		service.removeDepartment(delDept);
		pList.loadData();
		JOptionPane.showMessageDialog(null, delDept.getDeptName() + " 삭제 되었습니다.");
	}

	@Override
	protected void actionperformedBtnUpdate(ActionEvent e) {
		Department updateDept = pContent.getItem();
		service.updateDepartment(updateDept);
		pList.loadData();
		pContent.clearTf();
		btnAdd.setText("추가");
		JOptionPane.showMessageDialog(null, updateDept.getDeptName() + " 수정했습니다.");
		
	}

	@Override
	protected void actionPerformedBtnAdd(ActionEvent e) {
		Department dept = pContent.getItem();
		service.addDepartment(dept);
		JOptionPane.showMessageDialog(null, dept.getDeptName() + " 추가했습니다.");
		pList.loadData();
		pContent.clearTf();
		
	}

}
