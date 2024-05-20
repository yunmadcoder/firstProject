package service;

import java.util.List;

import dao.AdminDAO;
import vo.Admin;
import vo.Member;

public class AdminService {
	private static AdminService singleTon = null;

	private AdminService() {
	};

	public static AdminService getInstance() {
		if (singleTon == null) {
			singleTon = new AdminService();
		}
		return singleTon;
	}
	
	AdminDAO adminDao = AdminDAO.getInstance();
	
	public List<Member> memList(List<Object> param) {
		return adminDao.memList(param);
	}

	public Admin adminSignIn(List<Object> param) {
		return adminDao.adminSignIn(param);
	}
	

	
	public void memEdit(List<Object> param, int select) {
		 adminDao.memEdit(param, select);
	}

	public void memDel(int selectNo) {
		adminDao.memDel(selectNo);
		
	}

	public boolean chkMem(int selectNo) {
		return adminDao.chkMem(selectNo);
	}
	
}
