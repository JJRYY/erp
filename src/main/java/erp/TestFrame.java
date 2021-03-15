package erp;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import erp.dto.Department;
import erp.dto.Employee;
import erp.dto.Title;
import erp.service.EmployeeService;
import erp.ui.content.EmployeePanel;
import erp.ui.exception.InvalidCheckException;
import erp.ui.exception.SqlConstraintException;
import erp.ui.list.EmployeeTablePanel;

@SuppressWarnings("serial")
public class TestFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnAdd;
	private EmployeePanel pEmpItem;
	private JButton btnSet;
	private JButton btnCancel;
	private EmployeeTablePanel pEmpList;
	private EmployeeService service;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestFrame frame = new TestFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TestFrame() {
		service = new EmployeeService();
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 607);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		EmployeeService service = new EmployeeService();
		pEmpItem = new EmployeePanel();
		pEmpItem.setService(service);
		contentPane.add(pEmpItem);
		
		pEmpList = new EmployeeTablePanel();
		pEmpList.setService(service);
		pEmpList.loadData();
		contentPane.add(pEmpList);
		
		JPanel pBtn = new JPanel();
		contentPane.add(pBtn);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtn.add(btnAdd);
		
		btnSet = new JButton("Set");
		btnSet.addActionListener(this);
		pBtn.add(btnSet);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtn.add(btnCancel);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == btnCancel) {
				actionPerformedBtnCancel(e);
			}
			if (e.getSource() == btnSet) {
				actionPerformedBtnSet(e);
			}
			if (e.getSource() == btnAdd) {
				actionPerformedBtnAdd(e);
			}
			
		} catch (InvalidCheckException | SqlConstraintException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
	}
	protected void actionPerformedBtnAdd(ActionEvent e) {
		Employee emp = pEmpItem.getItem();
		service.addEmployee(emp);
		String message = String.format("%s(%d)%n"
				+ "title(%s)%n"
				+ "dept(%s)%n"
				+ "manager(%s)%n"
				+ "salary(%,d원)", 
				emp.getEmpName(), emp.getEmpNo(), emp.getTitle().gettName(), emp.getDept().getDeptName(),
				emp.getManager().getEmpName(), emp.getSalary());
		JOptionPane.showMessageDialog(null, message + " 추가했습니다.");
//		JOptionPane.showMessageDialog(null, emp + " 추가했습니다.");
		pEmpList.loadData();
		pEmpItem.clearTf();
	}
	protected void actionPerformedBtnSet(ActionEvent e) {
//		Employee newEmp = pEmpList.getItem();
		Employee newEmp = new Employee(1003, "조민희", new Title(3), new Employee(4377), 3000000, new Department(2));
		pEmpItem.setItem(newEmp);
	}
	protected void actionPerformedBtnCancel(ActionEvent e) {
		pEmpItem.clearTf();
	}
}
