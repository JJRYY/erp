package erp.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import erp.dto.Title;
import erp.ui.content.DeptPanel;
import erp.ui.content.EmployeePanel;
import erp.ui.content.AbstractContent;
import erp.ui.content.TitlePanel;
import erp.ui.exception.InvalidCheckException;
import erp.ui.exception.NotSelectedException;
import erp.ui.exception.SqlConstraintException;
import erp.ui.list.AbstractCustomTablePanel;
import erp.ui.list.DepartmentTablePanel;
import erp.ui.list.EmployeeTablePanel;
import erp.ui.list.TitleTablePanel;

@SuppressWarnings("serial")
public abstract class AbstractManager<T> extends JFrame implements ActionListener {
	private JPanel contentPane;
	protected JButton btnAdd;
	private JButton btnCancel;
	
	protected AbstractCustomTablePanel<T> pList;
	protected AbstractContent<T> pContent;
	
//	private TitleService service;
	
	public AbstractManager() {
//		service = new TitleService();
		initialize();
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(200, 200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		pContent = getItemPanel();
		contentPane.add(pContent);

		JPanel pBtn = new JPanel();
		contentPane.add(pBtn);

		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtn.add(btnAdd);

		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtn.add(btnCancel);

		pList = getItemList();
		
//		((TitleTablePanel)pList).setService(service);
		
		contentPane.add(pList);
		
		JPopupMenu popupMenu = createPopupMenu();
		pList.setPopupMenu(popupMenu);
	}
	
	/*
	 * ((TitleTablePanel)pList).setService(service);
	 */
	public abstract void setService();	
		

	public abstract AbstractCustomTablePanel<T> getItemList();

	public abstract AbstractContent<T> getItemPanel();

	public JPopupMenu createPopupMenu() {
		JPopupMenu popMenu = new JPopupMenu();
		
		JMenuItem updateItem = new JMenuItem("수정");
		updateItem.addActionListener(popupMenuListener);
		popMenu.add(updateItem);
		
		JMenuItem deleteItem = new JMenuItem("삭제");
		deleteItem.addActionListener(popupMenuListener);
		popMenu.add(deleteItem);
		
		JMenuItem empListByTitleItem = new JMenuItem("동일 직책 사원 보기");
		empListByTitleItem.addActionListener(popupMenuListener);
		popMenu.add(empListByTitleItem);
		
		return popMenu;
	}
	
	
	ActionListener popupMenuListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (e.getActionCommand().equals("삭제")) {
//					T delItem = pList.getItem();
//					service.removeTitle(delItem);
					removeItemService();
//					pList.loadData();
//					JOptionPane.showMessageDialog(null, delItem + " 삭제 되었습니다.");
				}
				if (e.getActionCommand().equals("수정")) {
					T updateItem = pList.getItem();
					pContent.setItem(updateItem);
//					pContent.getTfTitleNo().setEnabled(false);
					btnAdd.setText("수정");
				}
				if (e.getActionCommand().equals("동일 직책 사원 보기")) {
					/* 
					 * 1. EmployeeDao -> selectEmployeeByTitle() 추가
					 * 2. EmployeeDaoImpl -> selectEmployeeByTitle() 구현
					 * 3. EmployeeDaoTest -> Test 하기
					 * 4. TitleService -> EmployeeDaoImpl Field 추가 및 메서드 추가
					 * 5. 아래 기능 추가
					 * 6. 예외 찾아서 추가하기 (신규 직책 추가 시 NullPointException)
					 */
//					if (list == null) {
//						JOptionPane.showMessageDialog(null, "해당 직책 사원 없음");
//					} else {
//						JOptionPane.showMessageDialog(null, list);
//					}
					showEqualsByGuBun();
//					T selItem = pList.getItem();
//					List<Employee> list = service.selectEmployeeByTitle(selTitle);
//					
//					if (list == null) {
//						JOptionPane.showMessageDialog(null, "해당 사원 없음", "동일 직책 사원", JOptionPane.INFORMATION_MESSAGE);
//						return;
//					}
//					
//					List<String> strList = list.parallelStream()
//							.map(s -> { return String.format("%s(%d)", s.getEmpName(), s.getEmpNo()); })
//							.collect(Collectors.toList());
//					JOptionPane.showMessageDialog(null, strList, "동일 직책 사원", JOptionPane.INFORMATION_MESSAGE);
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
					actionperformedBtnUpdate(e);
				}
			}
			
		} catch (InvalidCheckException | SqlConstraintException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
//			pContent.clearTf();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	protected abstract void showEqualsByGuBun();

	protected void actionperformedBtnUpdate(ActionEvent e) {
		/* 
		 * pContent에서 수정된 title 가져오기
		 * update 수행
		 * pList 갱신
		 * pContent clearTf() 호출하여 초기화
		 * btnAdd 텍스트 변경 수정 -> 추가
		 */
//		T updateItem = pContent.getItem();
		modifyItemService();
//		pList.loadData();
//		btnAdd.setText("추가");
//		pContent.getTfTitleNo().setEnabled(true);
//		JOptionPane.showMessageDialog(null, updateItem + " 정보가 수정되었습니다.");
//		pContent.clearTf();
	}
	
	protected void actionPerformedBtnAdd(ActionEvent e) {
//		T item = pContent.getItem();
		addItemService();;
//		JOptionPane.showMessageDialog(null, item + " 추가했습니다.");
//		pList.loadData();
//		pContent.clearTf();
	}
	
	protected void actionPerformedBtnCancel(ActionEvent e) {
//		pContent.getTfTitleNo().setEnabled(true);
		pContent.clearTf();
	}
	
	/**
	 * service.removeTitle(delItem);
	 */
	public abstract void removeItemService();
	
	public abstract void addItemService();
	
	public abstract void modifyItemService();
}



