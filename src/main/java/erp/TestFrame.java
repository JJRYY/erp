package erp;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import erp.ui.list.TitleTablePanel;
import java.awt.GridLayout;
import erp.ui.list.DepartmentTablePanel;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class TestFrame extends JFrame {

	private JPanel contentPane;

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
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		TitleTablePanel pTitle = new TitleTablePanel();
		pTitle.setBorder(new TitledBorder(null, "\uC9C1\uCC45", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pTitle.loadData();
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		contentPane.add(pTitle);
		
		DepartmentTablePanel pDept = new DepartmentTablePanel();
		pDept.setBorder(new TitledBorder(null, "\uBD80\uC11C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pDept.loadData();
		contentPane.add(pDept);
	}

}
