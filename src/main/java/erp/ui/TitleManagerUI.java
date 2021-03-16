package erp.ui;

import java.awt.event.ActionEvent;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import erp.dto.Employee;
import erp.dto.Title;
import erp.service.TitleService;
import erp.ui.content.AbstractContentPanel;
import erp.ui.content.TitlePanel;
import erp.ui.list.AbstractCustomTablePanel;
import erp.ui.list.TitleTablePanel;

@SuppressWarnings("serial")
public class TitleManagerUI extends AbstractManagerUI<Title> {
	private TitleService service;
	
	@Override
	protected void setService() {
		service = new TitleService();
	}

	@Override
	protected void tableLoadData() {
		((TitleTablePanel)pList).setService(service);
		pList.loadData();
	}

	@Override
	protected AbstractContentPanel<Title> createContentPanel() {
		return new TitlePanel();
	}

	@Override
	protected AbstractCustomTablePanel<Title> createTablePanel() {
		return new TitleTablePanel();
	}

	@Override
	protected void actionPerformedMenuGubun() {
		Title selTitle = pList.getItem();
		List<Employee> list = service.selectEmployeeByTitle(selTitle);
		
		if (list == null) {
			JOptionPane.showMessageDialog(null, "해당 사원 없음", "동일 직책 사원", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		List<String> strList = list.parallelStream()
				.map(s -> { return String.format("%s(%d)", s.getEmpName(), s.getEmpNo()); })
				.collect(Collectors.toList());
		JOptionPane.showMessageDialog(null, strList, "동일 직책 사원", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	protected void actionPerformedMenuUpdate() {
		Title updateTitle = pList.getItem();
		pContent.setItem(updateTitle);
		btnAdd.setText("수정");
	}

	@Override
	protected void actionPerformedMenuDelete() {
		Title delTitle = pList.getItem();
		service.removeTitle(delTitle);
		pList.loadData();
		JOptionPane.showMessageDialog(null, delTitle + " 삭제 되었습니다.");
		
	}

	@Override
	protected void actionperformedBtnUpdate(ActionEvent e) {
		Title updateTitle = pContent.getItem();
		service.updateTitle(updateTitle);
		pList.loadData();
		btnAdd.setText("추가");
		JOptionPane.showMessageDialog(null, updateTitle.gettName() + " 정보가 수정되었습니다.");
		pContent.clearTf();
		
	}

	@Override
	protected void actionPerformedBtnAdd(ActionEvent e) {
		Title title = pContent.getItem();
		service.addTitle(title);
		JOptionPane.showMessageDialog(null, title + " 추가했습니다.");
		pList.loadData();
		pContent.clearTf();
	}

}
