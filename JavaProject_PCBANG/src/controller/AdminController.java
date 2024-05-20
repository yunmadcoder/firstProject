package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import print.Print;
import service.AdminService;
import service.MemberService;
import service.ProdService;
import util.ScanUtil;
import util.View;
import vo.Admin;
import vo.Member;
import vo.Prod;

public class AdminController extends Print {
	
	static public Map<String, Object> sessionStorage = new HashMap<>();
	AdminService adminService = AdminService.getInstance();
	ProdService prodService = ProdService.getInstance();
	MemberService memberService = MemberService.getInstance();
	
	final static public String SESSION_ADMIN = "admin";
	
	public View adminSignIn() {
		List<Object> param = new ArrayList<Object>();
		String id = ScanUtil.nextLine("ID 입력 >>");
		param.add(id);
		String pass = ScanUtil.nextLine("PASS 입력 >>");
		param.add(pass);

		Admin ad = adminService.adminSignIn(param);

		if (ad != null) {
			System.out.println("로그인 성공");
			sessionStorage.put(SESSION_ADMIN, ad);
			return View.ADMIN;

		} else {
			System.out.println("다시 로그인해주세요");
			return View.ADMINSIGNIN;
		}
	}
	
	
	public View admin() {
		printAdmin();
		int select = ScanUtil.nextInt(">> ");
		switch (select) {
		case 1:
			return View.MEMLIST;
		case 2:
			return View.PRODLIST;
		case 3:
			return View.ORDERLIST;
		case 4:
			return View.SALES;
		case 5:
			return View.HOME;
		default:
			return View.ADMIN;
		}
	}
	

	
	public View memList() {
		List<Object> param = new ArrayList<Object>();
		int pageNo = 1;
		if(sessionStorage.containsKey("pageNo")) {
			pageNo = (int) sessionStorage.get("pageNo");
		}
		int start_no = 1+10*(pageNo-1);
		int last_no = 10*pageNo;
		param.add(start_no);
		param.add(last_no);	
		
		List<Member> l = adminService.memList(param);
		
		int select = 0;
		printAdminMenu();
		printMemberList(l);
		select = ScanUtil.nextInt(">> "); 
		switch (select) {
		case 2:
			sessionStorage.remove("pageNo");
			return View.ADMIN;
		case 1:
			sessionStorage.put("pageNo", pageNo-1);
			return View.MEMLIST;
		case 3:
			sessionStorage.put("pageNo", pageNo+1);
			return View.MEMLIST;
		case 4:
			return View.MEMEDIT;
		case 5:
			return View.MEMDEL;
		default:
			return View.ADMIN;
		}
	}
	

	// 회원 정보 수정 시 정규식표현에 맞춰서 수정되도록 하기
	public View memEdit() {
		int selectNo = ScanUtil.nextInt("수정 할 회원번호를 입력해주세요 >> ");
		String syn = ScanUtil.nextLine(selectNo+ "번 회원 정보를 수정하시겠습니까? Y / N");
		if(syn.equalsIgnoreCase("y")) {
			printMemberEdit();
			int select = ScanUtil.nextInt("======= 수정 할 정보를 선택하세요 =======");
			
			List<Object> param = new ArrayList<>();
			String name;
			String pass;
			String telno;
			
			if(select == 1 || select == 4) {
				while (true) {
					name = ScanUtil.nextLine("회원명 >> ");
					if (memberService.isValidMemName(name)) {
						System.out.println("유효한 이름입니다.");
						break;
					} else {
						System.out.println("이름은 2~6글자의 한글만 사용가능합니다.");
					}
				}
				param.add(name);
			}
			if(select == 2 || select == 4) {
				while(true) {
					pass = ScanUtil.nextLine("비밀번호 입력 >> ");
					if(memberService.isValidMemPass(pass)) {
						System.out.println("유효한 비밀번호입니다.");
						break;
					} else {
						System.out.println("비밀번호는 5자 이상 20자 이하의 영어, 숫자만 입력이 가능합니다.");
					}
				}
				while(true) {	
					String passChk = ScanUtil.nextLine("비밀번호 확인 >> ");
					if(passChk.equals(pass)) {
						System.out.println("입력하신 두 비밀번호가 일치합니다"); 
						break;
					} else {
						System.out.println("입력하신 두 비밀번호가 일치 하지 않습니다.");
					}			
				}
				param.add(pass);
			}
			if(select == 3 || select == 4) {
				while (true) {
					telno = ScanUtil.nextLine("전화번호 >> ");
					if (memberService.isValidMemTelno(telno)) {
						System.out.println("유효한 전화번호 입니다");
						break;
					} else {
						System.out.println("올바른 전화번호를 입력해주세요 ex) 010-1234-1234");
					}
				}
				param.add(telno);
			}
					
			param.add(selectNo);
			adminService.memEdit(param, select);
			System.out.println("회원정보 수정이 완료되었습니다.");
			return View.MEMLIST; 
		} else if (syn.equalsIgnoreCase("n")){
			System.out.println("회원정보 수정이 취소되었습니다.");
			return View.ADMIN;
		} else {
			System.out.println("잘못된 입력 입니다.");
			return View.MEMEDIT;
		}
		
	}
	// 입력한 번호가 회원번호 중에 없는경우 처리
	public View memDel() {
		int selectNo = ScanUtil.nextInt("삭제 할 회원번호를 입력해주세요");
		boolean chkMem = adminService.chkMem(selectNo);
		if (chkMem) {
			System.out.println("입력하신 회원번호에 해당하는 회원이 존재하지 않습니다");
			return View.MEMDEL;
		}
		while(true) {
			String syn = ScanUtil.nextLine(selectNo+ "번 회원 정보를 삭제하시겠습니까? Y / N");
			if(syn.equalsIgnoreCase("y")) {
				adminService.memDel(selectNo);
				return View.MEMLIST; 
			} else if (syn.equalsIgnoreCase("n")){
				System.out.println("회원 정보 삭제가 취소되었습니다.");
				return View.ADMIN;
			} else {
				System.out.println("잘못된 입력 입니다. 다시 한번 확인해 주세요.");
			}
		}
 	}
	
	public View prodList() {
		List<Object> param = new ArrayList<Object>();
		int pageNo = 1;
		if(sessionStorage.containsKey("pageNo")) {
			pageNo = (int) sessionStorage.get("pageNo");
		}
		int start_no = 1+10*(pageNo-1);
		int last_no = 10*pageNo;
		param.add(start_no);
		param.add(last_no);	
		
		List<Prod> l = prodService.prodList(param);
		printAdminProdMenu();
		printProdList(l);	
		int select = ScanUtil.nextInt(">> ");
		System.out.println();
		switch (select) {
		case 2:
			sessionStorage.remove("pageNo");
			return View.ADMIN;
		case 1:
			sessionStorage.put("pageNo", pageNo-1);
			return View.PRODLIST;
		case 3:
			sessionStorage.put("pageNo", pageNo+1);
			return View.PRODLIST;
		case 4:
			return View.PRODADD;
		case 5:
			return View.PRODUPDATE;
		case 6:
			return View.PRODDEL;
		case 7:
			return View.PRODORDER;
		default:
			return View.ADMIN;
		}
	}
	
	public View prodAdd() {
		String syn = ScanUtil.nextLine("상품을 추가하시겠습니까? Y / N");
		if(syn.equalsIgnoreCase("y")) {
			List<Object> param = new ArrayList<Object>();
			// 26 구분코드 잘못 입력 시 다시 입력하도록 수정
			while (true) {
				String gu = ScanUtil.nextLine("구분코드를 입력해주세요 >> ");
				param.add(gu);
				Prod prod = prodService.guChk(gu);
				if (prod == null) {
					System.out.println("잘못된 구분코드 입니다. 다시 입력해주세요.");
				} else {
					break;
				}
			}
			param.add(ScanUtil.nextLine("상품명을 입력해주세요 >> "));
			param.add(ScanUtil.nextInt("가격을 입력해주세요 >> "));
			param.add(ScanUtil.nextInt("원가를 입력해주세요 >> "));
			prodService.prodAdd(param);
			System.out.println("상품등록이 완료 되었습니다.");
			return View.PRODLIST;
		} else {
			System.out.println("관리자 메뉴로 돌아갑니다.");
			return View.ADMIN;
		}
			
	}
	
	public View prodUpdate() {
		int selectNo = ScanUtil.nextInt("수정 할 상품번호를 입력해주세요 >> ");
		String syn = ScanUtil.nextLine(selectNo+ "번 상품 정보를 수정하시겠습니까? Y / N");
		if(syn.equalsIgnoreCase("y")) {
			List<Object> param = new ArrayList<>();
			int select = 0;
		while(true) {
			printProdUpdate();
			select = ScanUtil.nextInt("======= 수정 할 정보를 선택하세요 =======");
			
			if(select == 1 || select == 4) {
				param.add(ScanUtil.nextLine("상품명 >> "));
				break;
			}
			if(select == 2 || select == 4) {
				param.add(ScanUtil.nextInt("가격 >> "));
				break;
			}
			if(select == 3 || select == 4) {
				param.add(ScanUtil.nextInt("원가 >> "));
				break;
			}
			if (select >= 5 || select <= 0) {
				System.out.println("잘못입력하셨습니다.");
			}
		}
			param.add(selectNo);
			prodService.prodUpdate(param, select);
			System.out.println("상품정보 수정이 완료되었습니다.");
			return View.PRODLIST; 
		} else {
			
			return View.ADMIN;
		}
	}
	// 없는 상품번호 입력 시 처리
	public View prodDel() {
		int selectNo = ScanUtil.nextInt("삭제 할 상품의 번호를 입력해주세요");
		Prod prod = prodService.chkProd(selectNo);
		if (prod == null) {
			System.out.println("상품 정보가 존재하지 않습니다.");
			return View.PRODDEL;
		}
		String syn = ScanUtil.nextLine(selectNo + "번 상품을 삭제하시겠습니까? Y / N");
		if(syn.equalsIgnoreCase("y")) {
			prodService.prodDel(selectNo);
			System.out.println(selectNo + "번 상품 삭제가 완료되었습니다.");
			return View.PRODLIST;
		} else {
			return View.ADMIN;
			
		}
	}
	
	public View prodOrder() {
		int selectNo = ScanUtil.nextInt("발주 할 상품의 번호를 입력해주세요 >> ");
		Prod prod = prodService.chkProd(selectNo);
		if (prod == null) {
			System.out.println("상품 정보가 존재하지 않습니다.");
			return View.PRODADD;
		}
		int qty = ScanUtil.nextInt("수량을 입력해주세요 >> ");
		// 21차 y/n 이외 입력 상황 처리 완료
		while(true) {
			String syn = ScanUtil.nextLine(selectNo + "번 상품을" + qty + "개 발주하시겠습니까? Y / N ");
			if(syn.equalsIgnoreCase("y")) {
				List<Object> param = new ArrayList<Object>();
				param.add(selectNo);
				param.add(qty);
				prodService.prodOrder(param);
				System.out.println("발주가 완료되었습니다.");
				return View.PRODLIST;
			} else if (syn.equalsIgnoreCase("n")){
				System.out.println("상품 발주를 취소하였습니다.");
				return View.ADMIN;
			} else {
				System.out.println("잘못 입력하셨습니다. 다시 한번 확인해 주세요.");
			}
		}	
	}
	
	public View checkOrder() {
		List<Prod> orderList = prodService.orderList();
		printOrderListMenu();
		printOrderList(orderList);
		int select = ScanUtil.nextInt(">> ");
		switch (select) {
		case 1:
			return View.COMPORDER;
		case 2:
			return View.INCOMPLITEORDER;
		case 3:
			return View.ADMIN;
			// 21차 안내 문구 추가
		default:
			System.out.println("잘못된 입력 입니다. 다시 한번 확인해 주세요.");
			return View.CHECKORDER;
		}
	}
	
	public View compOrder() {
		// orderlist 완료 처리하기
		List<Prod> orderList = prodService.orderList();
		printOrderList(orderList);
		List<Object> param = new ArrayList<Object>();
		String select = ScanUtil.nextLine("완료처리할 주문 번호를 입력해주세요. "); // 없는 주문번호 입력시 처리
		param.add(select);
		Prod prod = prodService.chkOrder(select);
		if (prod == null) {
			System.out.println("주문 번호를 잘못 입력하셨습니다");
			return View.COMPORDER;
		}
		prodService.compOrder(param);
		System.out.println("완료처리 되었습니다.");
		System.out.println("1. 계속하기");
		System.out.println("2. 이전으로");
		int select2 = ScanUtil.nextInt("메뉴를 선택해주세요 >> ");
		switch (select2) {
		case 1:
			return View.COMPORDER;
		case 2:
			return View.CHECKORDER;
		default:
			return View.COMPORDER;
		}
	}

	public View incompOrder() {
		List<Prod> incompOrderList = prodService.incompOrderList();
		printOrderList(incompOrderList);

		System.out.println("1. 완료처리");
		System.out.println("2. 이전으로");
		int select2 = ScanUtil.nextInt("메뉴를 선택해주세요 >> ");
		switch (select2) {
		case 1:
			String syn = ScanUtil.nextLine("완료처리 선택 >> ");
			if (syn.equalsIgnoreCase("y")) {
				return View.COMPORDER;
			} else if (syn.equalsIgnoreCase("n")) {
				return View.INCOMPLITEORDER;
			}
			return View.CHECKORDER;
		case 2:
			return View.CHECKORDER;
		default:
			return View.ADMIN;
		}
		
	
	}
	
	public View sales() {
		List<Prod> salesList = prodService.salesList();
		List<Prod> purchaseList = prodService.purchaseList();
		int salePrice = printSList(salesList);
		int cost = printPList(purchaseList);
		System.out.println("=============================================== < 정산 내역 > =================================================");
		System.out.println("● 매     출 : " + salePrice + "원");
		System.out.println("● 매     입 : " +cost + "원");
		System.out.println("● 영업이익 : " + (salePrice - cost) + "원");
		System.out.println("============================================================================================================");
	
		String syn = ScanUtil.nextLine("이전으로 이동하시겠습니까? Y / N ");
		if (syn.equalsIgnoreCase("y")) {
			return View.ADMIN;
		} else {
			return View.SALES;
		}
	}
}
