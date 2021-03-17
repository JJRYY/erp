package erp.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import erp.dto.Employee;
import erp.dto.EmployeeDetail;
import erp.service.EmployeeDetailService;
import erp.ui.content.EmployeeDetailPanel;

@SuppressWarnings("serial")
public class EmployeeDetailUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPanel pBtns;
	private EmployeeDetailPanel pItem;
	private JButton btnAdd;
	private EmployeeDetailService service;
	private JButton btnCancel;
//	private JButton btnDel;
//	private JButton btnUpdate;
	
	public EmployeeDetailUI(boolean isBtns, EmployeeDetailService service) {
		this.service = service;
		initialize(isBtns);
	}
	
	private void initialize(boolean isBtns) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		pItem = new EmployeeDetailPanel();
		contentPane.add(pItem, BorderLayout.CENTER);
		
		pBtns = new JPanel();
	
		contentPane.add(pBtns, BorderLayout.SOUTH);

		btnAdd = new JButton();
		btnAdd.addActionListener(this);
		pBtns.add(btnAdd);

		btnCancel = new JButton();
		btnCancel.addActionListener(this);
		pBtns.add(btnCancel);
		
		if (isBtns) {
			btnAdd.setText("추가");
			btnCancel.setText("취소");
		} else {
			btnAdd.setText("수정");
			btnCancel.setText("삭제");
		}
		contentPane.add(pBtns, BorderLayout.SOUTH);

//		btnUpdate = new JButton("수정");
//		btnDel.addActionListener(this);
//		pBtns.add(btnUpdate);
//
//		btnDel = new JButton("삭제");
//		btnDel.addActionListener(this);
//		pBtns.add(btnDel);
		
	}
	
	public void setEmpNo(Employee empNo) {
		pItem.setTfEmpNo(empNo);
	}
	
	public void setDetailItem(EmployeeDetail empDetail) {
//		btnAdd.setText("수정");
		pItem.setItem(empDetail);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("삭제")) {
			actionPerformedBtnDel(e);
		}
		if (e.getActionCommand().equals("취소")) {
			actionPerformedBtnCancel(e);
		}
		if (e.getActionCommand().equals("추가")) {
			actionPerformedBtnAdd(e);
		}
		if (e.getActionCommand().equals("수정")) {
			actionPerformedBtnUpdate(e);
		}
	}
	
	private void actionPerformedBtnUpdate(ActionEvent e) {
		EmployeeDetail updateEmpDetail = pItem.getItem();
		service.updateEmployeeDetail(updateEmpDetail);
		JOptionPane.showMessageDialog(null, "수정 완료");
		pItem.clearTf();
		dispose();
	}

	protected void actionPerformedBtnAdd(ActionEvent e) {
		// 1. 입력된 empdetail 가져오기
		// 2. service에 적용
		EmployeeDetail newEmpDetail = pItem.getItem();
		service.addEmployeeDetail(newEmpDetail);
		JOptionPane.showMessageDialog(null, "추가 완료");
		pItem.clearTf();
		dispose();
	}
	
	protected void actionPerformedBtnCancel(ActionEvent e) {
		pItem.clearTf();
		if (btnAdd.getText().equals("수정")) {
			btnAdd.setText("추가");
		}
	}
	
	protected void actionPerformedBtnDel(ActionEvent e) {
		EmployeeDetail empDetail = pItem.getItem();
		service.removeEmployeeDetail(new Employee(empDetail.getEmpNo()));
		JOptionPane.showMessageDialog(null, "삭제 완료");
		pItem.clearTf();
	}
}
