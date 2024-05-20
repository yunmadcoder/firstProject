package print;

import java.util.List;
import java.util.Set;

import util.ScanUtil;
import vo.Member;
import vo.Prod;

public class Print {
	
	public void printBar() {
		System.out.println("----------------------------------");
	}

	public void printHome() {
		String pc = "\r\n" + 
				"    /$$$$$  /$$$$$$  /$$    /$$  /$$$$$$        /$$$$$$$   /$$$$$$  /$$$$$$$   /$$$$$$  /$$   /$$  /$$$$$$ \r\n" + 
				"   |__  $$ /$$__  $$| $$   | $$ /$$__  $$      | $$__  $$ /$$__  $$| $$__  $$ /$$__  $$| $$$ | $$ /$$__  $$\r\n" + 
				"      | $$| $$  \\ $$| $$   | $$| $$  \\ $$      | $$  \\ $$| $$  \\__/| $$  \\ $$| $$  \\ $$| $$$$| $$| $$  \\__/\r\n" + 
				"      | $$| $$$$$$$$|  $$ / $$/| $$$$$$$$      | $$$$$$$/| $$      | $$$$$$$ | $$$$$$$$| $$ $$ $$| $$ /$$$$\r\n" + 
				" /$$  | $$| $$__  $$ \\  $$ $$/ | $$__  $$      | $$____/ | $$      | $$__  $$| $$__  $$| $$  $$$$| $$|_  $$\r\n" + 
				"| $$  | $$| $$  | $$  \\  $$$/  | $$  | $$      | $$      | $$    $$| $$  \\ $$| $$  | $$| $$\\  $$$| $$  \\ $$\r\n" + 
				"|  $$$$$$/| $$  | $$   \\  $/   | $$  | $$      | $$      |  $$$$$$/| $$$$$$$/| $$  | $$| $$ \\  $$|  $$$$$$/\r\n" + 
				" \\______/ |__/  |__/    \\_/    |__/  |__/      |__/       \\______/ |_______/ |__/  |__/|__/  \\__/ \\______/ \r\n" + 
				"                                                                                                           \r\n" + 
				"                                                                                                           \r\n" + 
				"                                                                                                           \r\n" + 
				"";
		System.out.println(pc);
		System.out.println("==================================================== 홈 =====================================================");
		System.out.println("┌──────────────────────────────────────────────────────────────────────────────────────────────────────────┐");
		System.out.println("│                                                                                                          │");
		System.out.println("│                                                 메뉴 선택	                                           │");
		System.out.println("│                                                                                                          │");
		System.out.println("│                                                                                                          │");
		System.out.println("│                                                                                                          │");
		System.out.println("│                                                1. 로그인                                         	                           │");
		System.out.println("│                                                                                                          │");
		System.out.println("│                                                2. 회원가입	                                           │");
		System.out.println("│                                                                                                          │");
		System.out.println("│                                                3. ID/PW 찾기	                                           │");
		System.out.println("│                                                                                                          │");
		System.out.println("│                                                                                                          │");
		System.out.println("│                                                                                                          │");
		System.out.println("│                                                                                                          │");
		System.out.println("└──────────────────────────────────────────────────────────────────────────────────────────────────────────┘");
		
	}
	
		
	
	public void printAdmin() {
		System.out.println("============================================= 관리자 > 관리자메뉴 ===============================================");
		System.out.println("┌──────────────────────────────────────────────────────────────────────────────────────────────────────────┐");
		System.out.println("│                                                                                                          │");
		System.out.println("│                                                 메뉴 선택	                                           │");
		System.out.println("│                                                                                                          │");
		System.out.println("│                                                                                                          │");
		System.out.println("│                                                                                                          │");
		System.out.println("│                                               1. 회원관리                                                                                        │");
		System.out.println("│                                                                                                          │");
		System.out.println("│                                               2. 재고관리                                                                                        │");
		System.out.println("│                                                                                                          │");
		System.out.println("│                                               3. 주문내역확인                                                                                   │");
		System.out.println("│                                                                                                          │");
		System.out.println("│                                               4. 매출관리                                                                                        │");
		System.out.println("│                                                                                                          │");
		System.out.println("│                                               5. 로그아웃                                                                                        │");
		System.out.println("│                                                                                                          │");
		System.out.println("│                                                                                                          │");
		System.out.println("│                                                                                                          │");
		System.out.println("└──────────────────────────────────────────────────────────────────────────────────────────────────────────┘");
		
	}
	
//	public void printAdMenu() {
//		
//		System.out.println("============================================== 회원 > ID/PW 찾기 ===============================================");
//		System.out.println("┌──────────────────────────────────────────────────────────────────────────────────────────────────────────┐");
//		System.out.println("│                                                                                                          │");
//		System.out.println("│                                                 메뉴 선택	                                           │");
//		System.out.println("│                                                                                                          │");
//		System.out.println("│                                               1. 신규상품 등록                                                                                  │");
//		System.out.println("│                                                                                                          │");
//		System.out.println("│                                               2. 상품정보 수정                                                                                  │");
//		System.out.println("│                                                                                                          │");
//		System.out.println("│                                               3. 상품정보 삭제                                                                                  │");
//		System.out.println("│                                                                                                          │");
//		System.out.println("└──────────────────────────────────────────────────────────────────────────────────────────────────────────┘");
//		
//	}
	
	public void printMember() {
		System.out.println("=============================================== 회원 > 회원메뉴 ================================================");
		System.out.println("┌──────────────────────────────────────────────────────────────────────────────────────────────────────────┐");
		System.out.println("│                                                                                                          │");
		System.out.println("│                                                 메뉴 선택	                                           │");
		System.out.println("│                                                                                                          │");
		System.out.println("│                                                                                                          │");
		System.out.println("│                                                                                                          │");
		System.out.println("│                                               1. 시간충전                                                                                        │");
		System.out.println("│                                                                                                          │");
		System.out.println("│                                               2. 상품주문                                                                                        │");
		System.out.println("│                                                                                                          │");
		System.out.println("│                                               3. 자리이동                                                                                        │");
		System.out.println("│                                                                                                          │");
		System.out.println("│                                               4. 회원정보                                                                                        │");
		System.out.println("│                                                                                                          │");
		System.out.println("│                                               5. 사용종료                                                                                        │");
		System.out.println("│                                                                                                          │");
		System.out.println("│                                                                                                          │");
		System.out.println("│                                                                                                          │");
		System.out.println("└──────────────────────────────────────────────────────────────────────────────────────────────────────────┘");

	}
	
	public void printOrder() {
		System.out.println("=============================================== 회원 > 상품구매 ================================================");
		System.out.println("┌──────────────────────────────────────────────────────────────────────────────────────────────────────────┐");
		System.out.println("│                                                                                                          │");
		System.out.println("│                                                 메뉴 선택	                                           │");
		System.out.println("│                                                                                                          │");
		System.out.println("│                                               1. 상품구매  	                                               │");
		System.out.println("│                                                                                                          │");
		System.out.println("│                                               2. 장바구니                                                                                        │");
		System.out.println("│                                                                                                          │");
		System.out.println("│                                               3. 구매상품 조회                                                                                  │");
		System.out.println("│                                                                                                          │");
		System.out.println("└──────────────────────────────────────────────────────────────────────────────────────────────────────────┘");		

	}
	
	
	public void printInfo(List<Member> mem) {
		System.out.println("=============================================== 회원 > 회원정보 ================================================");
		System.out.println("┌──────────────────────────────────────────────────────────────────────────────────────────────────────────┐");
		System.out.println("│        1. 회원정보 수정                                               2. 회원탈퇴                                        3. 이전으로 돌아가기  	           │");
		System.out.println("└──────────────────────────────────────────────────────────────────────────────────────────────────────────┘");
	}

	public void printTime() {
		System.out.println(); // 요금 정보 넣기
	}
	
	public void printGU(List<Prod> list) {
		System.out.println("┌────────────────────────────┐");
		int cnt = 1;
		for (Prod prod : list) {
			System.out.println("\t   "+cnt + ". " + prod.getLprod_name());
			cnt++;
		}
		System.out.println("└────────────────────────────┘");
	}
	
	public void printFoodList(List<Prod> list) { 
		System.out.println("====================================================================");
		System.out.println("상품명"+"\t\t\t"+"가격"+"\t\t\t"+"수량");
		System.out.println("====================================================================");
		for (Prod prod : list) {
			System.out.println(prod.getProd_name() + "\t\t" + prod.getProd_price() + "\t\t\t" + prod.getProd_jaego());
			System.out.println("────────────────────────────────────────────────────────────────────");
		}
	}
	
	public void printmyCartList(List<Prod> lsit) {
		int cost = 0;
		System.out.println("====================================================================");			
		System.out.println("상품명		수량		가격		합계");
		System.out.println("====================================================================");
		for (Prod prod : lsit) {
			System.out.println(prod.getProd_name() + "\t" + prod.getCart_qty() + "\t\t" + prod.getProd_price() + "\t\t"
					+ prod.getProd_price() * prod.getCart_qty());
			cost += prod.getProd_price() * prod.getCart_qty();
		}
		System.out.println("────────────────────────────────────────────────────────────────────");
		System.out.println("                                                 "+cost+"원");
	}
	
	public void printMemberList(List<Member> l) {
		System.out.println("=================================================================================================");
		System.out.println("회원번호	  회원ID		 패스워드		회원명		   생년월일	 	  전화번호		잔여시간(분)");
		System.out.println("=================================================================================================");
		for (Member m : l) {
			System.out.println(m.getMem_no()+"\t"+m.getMem_id()+"\t"+m.getMem_pass()+"\t"+m.getMem_name()+"   	 \t"+m.getMem_birth()+"\t"+m.getMem_telno()+"\t  "+m.getMem_r_time()/60);
			System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────────");
		}
	}
	
	public void printMemberEdit() {
		System.out.println("========================================= 관리자 > 회원관리 > 회원정보 수정 ==========================================");
		System.out.println("┌──────────────────────────────────────────────────────────────────────────────────────────────────────────┐");
		System.out.println("│                                                                                                          │");
		System.out.println("│                                                 메뉴 선택	                                           │");
		System.out.println("│                                                                                                          │");
		System.out.println("│                                               1. 회 원 명  	                                           │");
		System.out.println("│                                                                                                          │");
		System.out.println("│                                               2. 비밀번호                                                                                        │");
		System.out.println("│                                                                                                          │");
		System.out.println("│                                               3. 전화번호                                                                                        │");
		System.out.println("│                                                                                                          │");
		System.out.println("│                                               4. 전체수정                                                                                        │");
		System.out.println("│                                                                                                          │");
		System.out.println("└──────────────────────────────────────────────────────────────────────────────────────────────────────────┘");
		
		
	}
	
	public void printFindId() {		
		System.out.println("============================================== 회원 > ID/PW 찾기 ===============================================");
		System.out.println("┌──────────────────────────────────────────────────────────────────────────────────────────────────────────┐");
		System.out.println("│                                                                                                          │");
		System.out.println("│                                                 메뉴 선택	                                           │");
		System.out.println("│                                                                                                          │");
		System.out.println("│                                               1. 아이디 찾기  	                                           │");
		System.out.println("│                                                                                                          │");
		System.out.println("│                                               2. 비밀번호 찾기                                                                                  │");
		System.out.println("│                                                                                                          │");
		System.out.println("│                                               3. 뒤로가기                                                                                        │");
		System.out.println("│                                                                                                          │");
		System.out.println("└──────────────────────────────────────────────────────────────────────────────────────────────────────────┘");
	}
	
	public void printOrderList(List<Prod> orderList) {
		System.out.println("============================================================================================================");
		System.out.println("주문번호			좌석		상품명		수량		금액		완료여부");
		System.out.println("============================================================================================================");
		for (Prod prod : orderList) {
			System.out.println(prod.getOrder_no() + "\t\t" + prod.getSeat_no() + "\t\t" + prod.getProd_name() + "\t\t" + prod.getCart_qty() + "\t\t" + prod.getProd_price() * prod.getCart_qty() + "\t\t" + prod.getOrder_yn());
			System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────");  
			
			
		}
	}
	
	public void printOrderListMenu() {
		System.out.println("============================================= 관리자 > 주문내역확인 ==============================================");
		System.out.println("┌──────────────────────────────────────────────────────────────────────────────────────────────────────────┐");
		System.out.println("│        1. 주문완료 처리                                           2. 미완료 주문조회                                   3. 이전으로 돌아가기  	           │");
		System.out.println("└──────────────────────────────────────────────────────────────────────────────────────────────────────────┘");	
	}
	
	public void printProdUpdate() {
		System.out.println("============================================== 관리자 > 재고관리 ================================================");
		System.out.println("┌──────────────────────────────────────────────────────────────────────────────────────────────────────────┐");
		System.out.println("│                                                                                                          │");
		System.out.println("│                                                 메뉴 선택	                                           │");
		System.out.println("│                                                                                                          │");
		System.out.println("│                                               1. 상 품 명  	                                           │");
		System.out.println("│                                                                                                          │");
		System.out.println("│                                               2. 가격수정                                                                                        │");
		System.out.println("│                                                                                                          │");
		System.out.println("│                                               3. 원가수정                                                                                        │");
		System.out.println("│                                                                                                          │");
		System.out.println("│                                               4. 전체수정                                                                                        │");
		System.out.println("│                                                                                                          │");
		System.out.println("└──────────────────────────────────────────────────────────────────────────────────────────────────────────┘");
		
	}
	
	public void printAdminProdMenu() {
		System.out.println("======================================= 관리자 메뉴 > 재고관리 =======================================");
		System.out.println("┌──────────────────────────────────────────────────────────────────────────────────────────────┐");
		System.out.println("│                 이전페이지 ◀  :  1번                      관리자메뉴 : 2번                     3번 :  ▶   다음페이지                          │");
		System.out.println("│                                                                                              │");
		System.out.println("│      신규상품등록 : 4번                 상품수정  : 5번                    상품삭제 : 6번         	         상품발주 : 7번	       │");
		System.out.println("└──────────────────────────────────────────────────────────────────────────────────────────────┘");
	}
	
	
	public void printProdList(List<Prod> l) {
		System.out.println("===============================================================================================");
		System.out.println("상품번호		구분코드		상품명			가격		재고		원가");
		System.out.println("===============================================================================================");
		for (Prod p : l) {
			System.out.println(p.getProd_no()+"\t\t"+p.getProd_lgu()+"\t\t"+p.getProd_name()+"\t\t"+p.getProd_price()+"\t\t"+p.getProd_jaego()+"\t\t"+p.getProd_cost());
			System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────");
		}
	}
	
	public void printProdTime(List<Prod> list) {
		System.out.println("======================================================");
		System.out.println("번호"+"\t\t\t"+"충전시간"+"\t\t\t"+"가격");
		System.out.println("======================================================");
		for (Prod prod : list) {
			System.out.println(prod.getProd_no()+ "\t\t\t" + prod.getProd_name() + "\t\t" + prod.getProd_price());
			System.out.println("──────────────────────────────────────────────────────");
			

		}
	}
	
	public int printSList(List<Prod> salesList) {
		System.out.println("=============================================== < 판매 내역 > =================================================");
		System.out.println("종류		상품명			판매수량		판매가		총액		판매날짜");
		System.out.println("============================================================================================================");
		int salePrice = 0;
		for (Prod prod : salesList) {
			System.out.println(prod.getProd_lgu()+"\t\t"+prod.getProd_name()+"\t\t"+prod.getRemain_chulgo()+"\t\t"+prod.getProd_price()+"\t\t"+prod.getAmount()+"\t\t"+prod.getRemain_date());
			salePrice += prod.getProd_price()*prod.getRemain_chulgo();
		}
		return salePrice;
		//int return으로 변경하기 
	}
		
		public int printPList(List<Prod> purchaseList) {
			System.out.println("=============================================== < 구매 내역 > =================================================");
			System.out.println("종류		상품명			구매수량		구매가		총액		구매날짜");
			System.out.println("============================================================================================================");
			int cost = 0;
			for (Prod prod : purchaseList) {
				System.out.println(prod.getProd_lgu()+"\t\t"+prod.getProd_name()+"\t\t"+prod.getRemain_ipgo()+"\t\t"+prod.getProd_cost()+"\t\t"+prod.getAmount2()+"\t\t"+prod.getRemain_date());
				cost += prod.getProd_cost()*prod.getRemain_ipgo();
			}
			return cost;
	}
		
		public void printInfoEditMenu() {
			System.out.println("┌──────────────────────────────────────────────────────────────────────────────────────────────────────────┐");
			System.out.println("│                                                                                                          │");
			System.out.println("│                                                 메뉴 선택	                                           │");
			System.out.println("│                                                                                                          │");
			System.out.println("│                                               1. 비밀번호  	                                           │");
			System.out.println("│                                                                                                          │");
			System.out.println("│                                               2. 전화번호                                                                                        │");
			System.out.println("│                                                                                                          │");
			System.out.println("│                                               3. 이     름                                                                                        │");
			System.out.println("│                                                                                                          │");
			System.out.println("│                                               4. 전체수정                                                                                        │");
			System.out.println("│                                                                                                          │");
			System.out.println("└──────────────────────────────────────────────────────────────────────────────────────────────────────────┘");
			

		}
		
	public void printAdminMenu() {
		System.out.println("======================================== 관리자 메뉴 > 회원관리 ========================================");
		System.out.println("┌──────────────────────────────────────────────────────────────────────────────────────────────┐");
		System.out.println("│                 이전페이지 ◀  :  1번                      관리자메뉴 : 2번                     3번 :  ▶   다음페이지                          │");
		System.out.println("│                                                                                              │");
		System.out.println("│                            회원정보수정 : 4번                  회원정보삭제  : 5번                                                 │");
		System.out.println("└──────────────────────────────────────────────────────────────────────────────────────────────┘");
	}
	
	
	public static void printSeatingLayout(Set<Integer> seats) {
		// 10x10 좌석 배치도 패턴
		int seatNumber = 1;
		System.out.println(
				"┌────────────────┬────────────────┬────────────────┬────────────────┬────────────────┬────────────────┬────────────────┬────────────────┬────────────────┬────────────────┐");
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {
				int currentSeat = seatNumber++;
				String seatStatus;
				if (seats.contains(currentSeat)) {
					seatStatus = "사용가능";
				} else {
					seatStatus = "사용불가";
				}
				System.out.printf("%2d번 %s              ", currentSeat, seatStatus);

				// 각 행의 마지막에 세로줄 추가
				if (col == 9) {
					System.out.println(" ");
					if (row != 9) {
						System.out.println(
								"├────────────────┼────────────────┼────────────────┼────────────────┼────────────────┼────────────────┼────────────────┼────────────────┼────────────────┼────────────────┤");
					}
				}
			}
		}
		System.out.println(
				"└────────────────┴────────────────┴────────────────┴────────────────┴────────────────┴────────────────┴────────────────┴────────────────┴────────────────┴────────────────┘");
	}
	

}
