package controller;

import java.util.HashMap;
import java.util.Map;

import print.Print;

import service.ProdService;
import util.ScanUtil;
import util.View;

public class Controller extends Print {
	// 세션
	static public Map<String, Object> sessionStorage = new HashMap<>();
	
	MainController main = new MainController();
	MemberController mem = new MemberController();
	AdminController admin = new AdminController();
	
	ProdService prodService = ProdService.getInstance();

	public static void main(String[] args) {
//		new Controller().start();
		Controller con = new Controller();
		con.start();
		
	}

	private void start() {
		View view = View.HOME;
		while (true) {
			switch (view) {
			case HOME:
				view = home();
				break;
			case MEMBER:
				view = mem.member();
				break;
			case ADMIN:
				view = admin.admin();
				break;
			case SIGNIN:
				view = main.signIn();
				break;
			case SIGNUP:
				view = main.signUp();
				break;
			case FINDIDPW:
				view = main.findIDPW();
				break;
			case FINDID:
				view = main.findId();
				break;
			case FINDPASS:
				view = main.findPass();
				break;
			case CHARGETIME:
				view = mem.chargeTime();
				break;
			case ORDER:
				view = mem.order();
				break;
			case INFO:
				view = mem.info();
				break;
			case INFOEDIT:
				view = mem.infoEdit();
				break;
			case INFODEL:
				view = mem.infoDel();
				break;
			case SIGNOUT:
				view = mem.signOut();
				break;
			case MEMLIST:
				view = admin.memList();
				break;
			case MEMEDIT:
				view = admin.memEdit();
				break;
			case MEMDEL:
				view = admin.memDel();
				break;
			case PRODLIST:
				view = admin.prodList();
				break;
			case PRODADD:
				view = admin.prodAdd();
				break;
			case PRODUPDATE:
				view = admin.prodUpdate();
				break;
			case PRODDEL:
				view = admin.prodDel();
				break;
			case PRODORDER:
				view = admin.prodOrder();
				break;
			case ORDERLIST:
				view = admin.checkOrder();
				break;
			case CHECKORDER:
				view = admin.checkOrder();
				break;
			case INCOMPLITEORDER:
				view = admin.incompOrder();
				break;
			case SALES:
				view = admin.sales();
				break;
			case SELECTSEAT:
				view = mem.selectSeat();
				break;
			case MYCART:
				view = mem.myCart();
				break;
			case CHANGESEAT:
				view = mem.changeSeat();
				break;
			case COMPORDER:
				view = admin.compOrder();
				break;
			}
		}
	}


	private View home() {
		printHome();
		int select = ScanUtil.nextInt(">> ");
		switch (select) {
		case 1:
			return View.SIGNIN;
		case 2:
			return View.SIGNUP;
		case 3:
			return View.FINDIDPW;
		default:
			return View.HOME;
		}
	}
}
