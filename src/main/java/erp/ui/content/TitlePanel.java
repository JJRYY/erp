package erp.ui.content;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import erp.dto.Title;
import erp.ui.exception.InvalidCheckException;

@SuppressWarnings("serial")
public class TitlePanel extends AbstractContentPanel<Title>{
	private JTextField tfTitleNo;
	private JTextField tfTitleName;

	public JTextField getTfTitleNo() {
		return tfTitleNo;
	}
	
	public TitlePanel() {
		
		initialize();
	}
	
	public void initialize() {
		setBorder(new TitledBorder(null, "직책 정보", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new GridLayout(0, 2, 10, 10));
		
		JLabel lblTitleNo = new JLabel("직책번호");
		lblTitleNo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblTitleNo);
		
		tfTitleNo = new JTextField();
		add(tfTitleNo);
		tfTitleNo.setColumns(10);
		
		JLabel lblTitleName = new JLabel("직책명");
		lblTitleName.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblTitleName);
		
		tfTitleName = new JTextField();
		tfTitleName.setColumns(10);
		add(tfTitleName);
	}
	
//	public void setTitle(Title title) {
//		tfTitleNo.setText(title.gettNo() + "");
//		tfTitleName.setText(title.gettName() + "");
//	}
//	
//	public Title getTitle() {
//		validCheck();
//		int titleNo = Integer.parseInt(tfTitleNo.getText().trim());
//		String titleName = tfTitleName.getText().trim();
//		return new Title(titleNo, titleName);
//	}
//	
//	private void validCheck() {
//		if (tfTitleNo.getText().contentEquals("") || tfTitleName.getText().equals("")) {
//			throw new InvalidCheckException();
//		} 
//	}
	
	@Override
	public void clearTf() {
		tfTitleNo.setText("");
		tfTitleName.setText("");
		
		if (!tfTitleNo.isEditable()) {
			tfTitleNo.setEditable(true);
		}
	}


	@Override
	public void setItem(Title item) {
		tfTitleNo.setText(item.gettNo() + "");
		tfTitleName.setText(item.gettName() + "");
		
		tfTitleNo.setEditable(false);
		
	}

	@Override
	public Title getItem() {
		validCheck();
		int titleNo = Integer.parseInt(tfTitleNo.getText().trim());
		String titleName = tfTitleName.getText().trim();
		return new Title(titleNo, titleName);
	}

	@Override
	public void validCheck() {
		if (tfTitleNo.getText().contentEquals("") || tfTitleName.getText().equals("")) {
			throw new InvalidCheckException();
		} 
		
	}

}
