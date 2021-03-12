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
import erp.service.TitleService;
import erp.ui.content.TitlePanel;
import erp.ui.exception.InvalidCheckException;
import erp.ui.list.TitleTablePanel;

@SuppressWarnings("serial")
public class TitleManager extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnAdd;
	private TitleTablePanel pList;
	private TitlePanel pContent;
	private TitleService service;
	
	public TitleManager() {
		service = new TitleService();
		initialize();
	}

	private void initialize() {
		setTitle("직책관리");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(200, 200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		pContent = new TitlePanel();
		contentPane.add(pContent);

		JPanel pBtn = new JPanel();
		contentPane.add(pBtn);

		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtn.add(btnAdd);

		JButton btnCelar = new JButton("취소");
		pBtn.add(btnCelar);

		pList = new TitleTablePanel();
		pList.setService(service);
		pList.loadData();
		contentPane.add(pList);
		
		JPopupMenu popupMenu = createPopupMenu();
		pList.setPopupMenu(popupMenu);
	}

	private JPopupMenu createPopupMenu() {
		JPopupMenu popMenu = new JPopupMenu();
		
		JMenuItem updateItem = new JMenuItem("수정");
		popMenu.add(updateItem);
		
		JMenuItem deleteItem = new JMenuItem("삭제");
		deleteItem.addActionListener(popupMenuListener);
		popMenu.add(deleteItem);
		
		return popMenu;
	}
	
	ActionListener popupMenuListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("삭제")) {
				Title delTitle = pList.getItem();
				service.removeTitle(delTitle);
				pList.loadData();
				JOptionPane.showMessageDialog(null, delTitle + " 삭제 되었습니다.");
			}
		}
	};

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == btnAdd) {
				actionPerformedBtnAdd(e);
			}
		} catch (InvalidCheckException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	protected void actionPerformedBtnAdd(ActionEvent e) {
		Title title = pContent.getTitle();
		service.addTitle(title);
		JOptionPane.showMessageDialog(null, title + " 추가했습니다.");
		pList.loadData();
	}
}
