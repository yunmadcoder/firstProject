package service;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

import dao.AdminDAO;
import dao.ProdDAO;
import vo.Admin;
import vo.Prod;

public class ProdService {
	// 싱글톤 패턴을 만든다.
	private static ProdService instance = null;
	private ProdService() {}
	public static ProdService getInstance() {
		if(instance == null) 
			instance = new ProdService();
		return instance;
	}
	
	// Dao를 부른다
	ProdDAO prodDao = ProdDAO.getInstance();
	AdminDAO adDao = AdminDAO.getInstance();
	public List<Object> login(List<Object> list) {
		
		Admin ad = adDao.login(list);
		Member mb = prodDao.login(list);
		List<Object> l = new ArrayList<Object>();
		if(ad != null) 
			l.add(ad);
		else
			l.add(mb);
			
			
		return l;		
	}
	public List<Prod> getGU() {
		return prodDao.getGU();
	}
	public List<Prod> foodList(List<Object> param) {
		return prodDao.foodList(param);
	}
	public void cart(List<Object> param1) {
		prodDao.cart(param1);
		
	}
	public List<Prod> myCartList(List<Object> param) {
		return prodDao.myCartList(param);
	}
	public void clearCart(List<Object> param1) {
		prodDao.clearCart(param1);
		
	}
	public Prod chkQty(String menu) {
		return prodDao.chkQty(menu);
	}
	public List<Prod> orderList() {
		return prodDao.orderList();
	}
	public List<Prod> incompOrderList() {
		return prodDao.incompOrderList();
	}
	public void compOrder(List<Object> param) {
		prodDao.compOrder(param);
		
	}

	public void chulgo(List<Object> param4) {
		prodDao.chulgo(param4);
		
	}
	
	public List<Prod> prodList(List<Object> param) {
		return prodDao.prodList(param);
	}
	public void prodAdd(List<Object> param) {
		prodDao.prodAdd(param);
		
	}
	public void prodUpdate(List<Object> param, int select) {
		prodDao.prodUpdate(param, select);
		
	}
	public void prodDel(int selectNo) {
		prodDao.prodDel(selectNo);
		
	}
	public void prodOrder(List<Object> param) {
		prodDao.prodOrder(param);
	}
	public List<Prod> prodTime() {
		return prodDao.prodTime();
	}
	public Prod timePrice(int select) {
		return prodDao.timePrice(select);
	}
	public List<Prod> salesList() {
		return prodDao.salesList();
	}
	public List<Prod> purchaseList() {
		return prodDao.purchaseList();
	}
	public Prod chkProd(int selectNo) {
		return prodDao.chkProd(selectNo);
	}
	public Prod chkOrder(String select) {
		return prodDao.chkOrder(select);
	}
	public void orderCancel(int seatNo) {
		prodDao.orderCancel(seatNo);
		
	}
	
	public Prod guChk(String gu) {
		return prodDao.guChk(gu);
	}

	
}
