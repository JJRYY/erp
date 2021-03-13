package erp.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import erp.dto.Department;
import erp.dto.Employee;
import erp.service.DepartmentService;
import erp.ui.content.DeptPanel;
import erp.ui.exception.InvalidCheckException;
import erp.ui.exception.NotSelectedException;
import erp.ui.exception.SqlConstraintException;
import erp.ui.list.DepartmentTablePanel;

@SuppressWarnings("serial")
public class DepartmentManager extends JFrame implements ActionListener {

	private JPanel contentPane;
	private DepartmentService service;
	private DepartmentTablePanel pList;
	private JButton btnCancel;
	private DeptPanel pContent;
	private JButton btnAdd;

	public DepartmentManager() {
		service = new DepartmentService();
		initialize();
	}
	private void initialize() {
		setTitle("부서관리");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(200, 200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		pContent = new DeptPanel();
		contentPane.add(pContent);
		
		JPanel pBtn = new JPanel();
		contentPane.add(pBtn);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtn.add(btnAdd);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtn.add(btnCancel);
		
		pList = new DepartmentTablePanel();
		pList.setService(service);
		pList.loadData();
		contentPane.add(pList);
		
		JPopupMenu popupMenu = createPopupMenu();
		pList.setPopupMenu(popupMenu);
	}

	private JPopupMenu createPopupMenu() {
		JPopupMenu popMenu = new JPopupMenu();
		
		JMenuItem updateItem = new JMenuItem("수정");
		updateItem.addActionListener(popupMenuListener);
		popMenu.add(updateItem);
		
		JMenuItem deleteItem = new JMenuItem("삭제");
		deleteItem.addActionListener(popupMenuListener);
		popMenu.add(deleteItem);
		
		JMenuItem empListByDeptItem = new JMenuItem("동일 부서 사원 보기");
		empListByDeptItem.addActionListener(popupMenuListener);
		popMenu.add(empListByDeptItem);
		
		return popMenu;
	}
	
	ActionListener popupMenuListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (e.getActionCommand().equals("삭제")) {
					Department delDept = pList.getItem();
					service.removeDepartment(delDept);
					pList.loadData();
					JOptionPane.showMessageDialog(null, delDept.getDeptName() + " 삭제 되었습니다.");
				}
				if (e.getActionCommand().equals("수정")) {
					btnAdd.setText("수정");
					pContent.setDepartment(pList.getItem());
					pContent.getTfDeptNo().setEnabled(false);
				}
				if (e.getActionCommand().equals("동일 부서 사원 보기")) {
					Department selDept = pList.getItem();
					List<Employee> list = service.selectEmployeeByDept(selDept);
					if (list == null) {
						JOptionPane.showMessageDialog(null, "해당 부서 사원 없음");
					} else {
						JOptionPane.showMessageDialog(null, list);
					}
				}
				
			} catch (NotSelectedException | SqlConstraintException e2) {
				JOptionPane.showMessageDialog(null, e2.getMessage());
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
	};
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			actionPerformedBtnCancel(e);
		}
		try {
			if (e.getSource() == btnAdd) {
				if (btnAdd.getText().equals("추가")) {
					actionPerformedBtnAdd(e);
				}
				if (btnAdd.getText().equals("수정")) {
					actionPerformedBtnUpdate(e);
				}
			}
		} catch (InvalidCheckException | SqlConstraintException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	private void actionPerformedBtnUpdate(ActionEvent e) {
		Department updateDept = pContent.getDepartment();
		service.updateDepartment(updateDept);
		pList.loadData();
		pContent.clearTf();
		btnAdd.setText("추가");
		pContent.getTfDeptNo().setEnabled(true);
		JOptionPane.showMessageDialog(null, updateDept.getDeptName() + " 수정했습니다.");
		
	}
	protected void actionPerformedBtnCancel(ActionEvent e) {
		pContent.clearTf();
	}
	
	protected void actionPerformedBtnAdd(ActionEvent e) {
		Department dept = pContent.getDepartment();
		service.addDepartment(dept);
		JOptionPane.showMessageDialog(null, dept.getDeptName() + " 추가했습니다.");
		pList.loadData();
		pContent.clearTf();
	}
}
