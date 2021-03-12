package erp.service;

import java.util.List;

import erp.dao.TitleDao;
import erp.dao.impl.TitleDaoImpl;
import erp.dto.Title;

public class TitleService {
	private TitleDao dao = TitleDaoImpl.getInstance();
	
	public List<Title> showTitles(){
		return dao.selectTitleByAll();
	}
	
	public void addTitle(Title title) {
		dao.insertTitle(title);
	}
	
	public void removeTitle(Title title) {
		dao.deleteTitle(title.gettNo());
	}
}
