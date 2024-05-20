package controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import print.Print;
import service.MemberService;
import service.ProdService;
import util.ScanUtil;
import util.View;
import vo.Member;
import vo.Prod;

public class MemberController extends Print {

	static private Map<String, Object> sessionStorage = MainController.sessionStorage;
	MemberService memberService = MemberService.getInstance();
	ProdService prodService = ProdService.getInstance();

	public View selectSeat() {
		List<Member> list = memberService.seatList();
		// 사용 중인 좌석 목록
		// 23차 추가
		Set<Integer> seats = new HashSet<>();
		for (Member member : list) {
			seats.add(member.getSeat_no());
		}

		// 좌석 배치도 출력
		printSeatingLayout(seats);
		int seat = ScanUtil.nextInt("좌석을 선택해주세요 >> ");
		Member mem = (Member) sessionStorage.get(MainController.SESSION_MEMBER);
		String id = mem.getMem_id();
		while (true) {
			for (Member mb : list) {
				if (mb.getSeat_no() == seat) {
					System.out.println("사용 가능한 좌석입니다.");
					sessionStorage.put("seat_no", seat);
					List<Object> param1 = new ArrayList();
					param1.add(mem.getMem_id());
					Member member = memberService.timeChk(param1);
					// 시간 충전 안하는 경우 로그아웃으로 넘기기
					java.util.Date date = new Date();
					long time = date.getTime();
					Timestamp ts = new Timestamp(time);
					List<Object> param = new ArrayList();
					param.add(id);
					param.add(seat);
					param.add(ts);
					memberService.useStart(param);
					if (member.getMem_r_time() == 0) {
						String syn = ScanUtil.nextLine("충전이 필요합니다. 지금 충전하시겠습니까? Y / N ");
						if (syn.equalsIgnoreCase("y")) {
							return View.CHARGETIME;
							// 21차 충전 안할 시 자동 로그아웃 처리 했음
						} else if (syn.equalsIgnoreCase("n")) {
							System.out.println("충전이 취소되었습니다.");
							System.out.println("남은 시간이 없어 자동으로 로그아웃됩니다.");
							Member mem1 = (Member) sessionStorage.get(MainController.SESSION_MEMBER);
							java.util.Date date1 = new Date();
							long time1 = date1.getTime();
							Timestamp ts1 = new Timestamp(time1);
							List<Object> param11 = new ArrayList();
							param11.add(ts1);
							param11.add(mem.getMem_id());
							param11.add(sessionStorage.get("seat_no"));
							memberService.signOut(param11);
							List<Object> param111 = new ArrayList();
							param111.add(mem.getMem_id());
							param111.add(sessionStorage.get("seat_no"));
							Member useTime = memberService.useTime(param111);
							List<Object> param2 = new ArrayList();
							param2.add(useTime.getUse_time());
							param2.add(useTime.getUse_time());
							param2.add(mem.getMem_id());
							memberService.subTime(param2);
							sessionStorage.remove(MainController.SESSION_MEMBER);
							return View.HOME;
						} else {
							System.out.println("잘못된 입력입니다.");
							return View.SELECTSEAT;
						}
					}
					return View.MEMBER;
				} else if (seat > 100 || seat <= 0) {
					System.out.println("1~100 사이의 자연수만 입력해주세요.");
					return View.SELECTSEAT;
				}
			}
			System.out.println("사용중인 좌석입니다. 다시 선택해주세요.");
			return View.SELECTSEAT;
		}

	}

	public View member() {
		// 사용자 정보 불러오기 수정했음
		Member mem = (Member) sessionStorage.get(MainController.SESSION_MEMBER);
		String id = mem.getMem_id();
		Member member = memberService.getInfo(id);
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t\t● 회 원 명 : " + member.getMem_name());
		System.out.println("\t\t\t\t\t\t\t\t\t\t\t● 사용좌석 : " + sessionStorage.get("seat_no") + "번");
		int time = member.getMem_r_time();
		int hour = time / 3600;
		int min = time % 3600 / 60;
		System.out.println("\t\t\t\t\t\t\t\t\t\t\t● 남은시간 : " + hour + "시간 " + min + "분");
		printMember();
		int select = ScanUtil.nextInt(">> ");
		switch (select) {
		case 1:
			return View.CHARGETIME;
		case 2:
			return View.ORDER;
		case 3:
			return View.CHANGESEAT;
		case 4:
			return View.INFO;
		case 5:
			return View.SIGNOUT;
		default:
			System.out.println("잘못된 입력입니다.");
			return View.MEMBER;
		}
	}

	public View signOut() {
		String syn = ScanUtil.nextLine("로그아웃 하시겠습니까? Y / N ");
		if (syn.equalsIgnoreCase("y")) {
			Member mem = (Member) sessionStorage.get(MainController.SESSION_MEMBER);
			java.util.Date date = new Date();
			long time = date.getTime();
			Timestamp ts = new Timestamp(time);
			List<Object> param = new ArrayList();
			param.add(ts);
			param.add(mem.getMem_id());
			param.add(sessionStorage.get("seat_no"));
			memberService.signOut(param);
			List<Object> param1 = new ArrayList();
			param1.add(mem.getMem_id());
			param1.add(sessionStorage.get("seat_no"));
			Member useTime = memberService.useTime(param1);
			List<Object> param2 = new ArrayList();
			param2.add(useTime.getUse_time());
			param2.add(useTime.getUse_time());
			param2.add(mem.getMem_id());
			memberService.subTime(param2);
			sessionStorage.remove(MainController.SESSION_MEMBER);
			return View.HOME;
		} else if (syn.equalsIgnoreCase("n")) {
			return View.MEMBER;
		} else {
			System.out.println("잘못된 입력입니다.");
			return View.MEMBER;
		}
	}

	public View chargeTime() {
		List<Prod> list = prodService.prodTime();
		printProdTime(list);
		int select = ScanUtil.nextInt("충전할 시간 요금을 선택해주세요 >> ");
		Prod prod = prodService.timePrice(select);
		int time;
		switch (select) {
		case 1:
			time = 3600;
			break;
		case 2:
			time = 7200;
			break;
		case 3:
			time = 5 * 60 * 60;
			break;
		case 4:
			time = 11 * 60 * 60;
			break;
		case 5:
			time = 35 * 60 * 60;
			break;
		default:
			System.out.println("선택할 수 없는 요금제 번호 입니다.");
			return View.CHARGETIME;
		}
		Member mem = (Member) sessionStorage.get(MainController.SESSION_MEMBER);
		List<Object> param = new ArrayList();
		param.add(time);
		param.add(mem.getMem_id());
		memberService.chargeTime(param);
		List<Object> param1 = new ArrayList();
		param1.add(prod.getProd_price());
		param1.add(mem.getMem_id());
		memberService.addCost(param1);
		while (true) {
			String syn = ScanUtil.nextLine("추가 충전 하시겠습니까? Y / N ");
			if (syn.equalsIgnoreCase("y")) {
				return View.CHARGETIME;
			} else if (syn.equalsIgnoreCase("n")) {
				return View.MEMBER;
			} else {
				System.out.println("잘못 입력하셨습니다. 다시 확인해 주세요.");
			}
		}

	}

	public View order() {
		// 음식주문 카테고리 선택 후 각 리스트 출력
		// 이후 주문하면 cart에 담김
		List<Prod> list = prodService.getGU();
		printGU(list);
		String gu = "";
		int select = ScanUtil.nextInt("카테고리를 선택해주세요 >> ");
		switch (select) {
		case 1:
			gu = "식사류";
			break;
		case 2:
			gu = "사이드메뉴";
			break;
		case 3:
			gu = "간식류";
			break;
		case 4:
			gu = "커피류";
			break;
		case 5:
			gu = "음료류";
			break;
		default:
			System.out.println("잘못 입력하셨습니다. 다시 확인해 주세요.");
			return View.ORDER;
		}
		List<Object> param = new ArrayList<Object>();
		param.add(gu);
		List<Prod> foodList = prodService.foodList(param);
		printFoodList(foodList);
		String menu = ScanUtil.nextLine("주문하실 메뉴 명을 입력해주세요 >> ");
		Prod prod1 = prodService.chkQty(menu);
		if (prod1 == null) {
			System.out.println("존재하지 않는 상품입니다.");
			return View.ORDER;
		}
		if (prod1.getProd_jaego() == 0) {
			System.out.println("해당 상품은 품절되어 주문할 수 없습니다.");
			return View.ORDER;
		}

		int num = ScanUtil.nextInt("구매 수량을 입력해주세요 >> ");
		if (num > prod1.getProd_jaego()) {
			System.out.println("재고 수량을 초과하여 구매할 수 없습니다.(" + prod1.getProd_jaego() + "개 이하 구매 가능)");
			return View.ORDER;
		}
		List<Object> param1 = new ArrayList<Object>();
		param1.add(menu);
		param1.add(sessionStorage.get("seat_no"));
		param1.add(num);
		prodService.cart(param1);
		while (true) {
			String add = ScanUtil.nextLine("추가 구매 하시겠습니까? Y / N ");
			if (add.equalsIgnoreCase("y")) {
				return View.ORDER;
			} else if (add.equalsIgnoreCase("n")) {
				return View.MYCART;
			} else {
				System.out.println("잘못 입력하셨습니다. 다시 한번 확인해 주세요.");
			}
		}
	}

	public View myCart() {
		// 장바구니 cart 테이블 데이터 가져오기
		// 가져온 후 구매여부 물어서 y하면 추가 안하면 삭제
		List<Object> param = new ArrayList<Object>();
		Member mem = (Member) sessionStorage.get(MainController.SESSION_MEMBER);
		param.add(mem.getMem_id());
		List<Prod> list = prodService.myCartList(param);
		printmyCartList(list);
		while (true) {
			String syn = ScanUtil.nextLine("상품을 주문하시겠습니까? Y / N ");
			if (syn.equalsIgnoreCase("y")) {
				// cart에 있는거 orderlist로 넘겨주기
				List<Object> param1 = new ArrayList<Object>();
				int cost = 0;
				for (Prod prod : list) {
					param1.add(prod.getCart_no());
					param1.add(sessionStorage.get("seat_no"));
					memberService.putOrderList(param1);
					prodService.clearCart(param1);
					param1.clear();
					cost += prod.getProd_price() * prod.getCart_qty();
				}
				System.out.println("주문이 완료되었습니다. 결제 금액 : " + cost + "원");
				List<Object> param3 = new ArrayList<Object>();
				param3.add(cost);
				param3.add(mem.getMem_id());
				memberService.addCost(param3); // 결제 금액을 use 테이블의 사용금액에 추가
				for (Prod prod : list) {
					List<Object> param4 = new ArrayList<Object>();
					param4.add(prod.getProd_no());
					param4.add(prod.getCart_qty());
					prodService.chulgo(param4);
				}
				return View.MEMBER;
			} else if (syn.equalsIgnoreCase("n")) {
				System.out.println("주문이 취소되었습니다.");
				int seatNo = (int) sessionStorage.get("seat_no");
				prodService.orderCancel(seatNo);
				return View.MEMBER;
			} else {
				System.out.println("잘못 입력하셨습니다. 다시 한번 확인해 주세요.");
			}
		}
	}

	public View changeSeat() {
		List<Member> list = memberService.seatList();
		Member mem = (Member) sessionStorage.get(MainController.SESSION_MEMBER);
		Set<Integer> seats = new HashSet<>();
		for (Member member : list) {
			seats.add(member.getSeat_no());
		}

		// 좌석 배치도 출력
		printSeatingLayout(seats);
		int seat = ScanUtil.nextInt("이동하실 좌석 번호를 입력해주세요 >> ");
		while (true) {
			for (Member mb : list) {
				if (mb.getSeat_no() == seat) {
					String syn = ScanUtil.nextLine("이동 가능한 좌석입니다. 이동하시겠습니까? Y / N ");
					if (syn.equalsIgnoreCase("y")) {
						List<Object> param = new ArrayList<Object>();
						param.add(seat);
						param.add(mem.getMem_id());
						param.add(sessionStorage.get("seat_no"));
						memberService.changeSeat(param);
						List<Object> param2 = new ArrayList<Object>();
						param2.add(sessionStorage.get("seat_no"));
						memberService.updateSeat(param2); // 기존 자리 비우기
						List<Object> param3 = new ArrayList<Object>();
						param3.add(mem.getMem_id());
						param3.add(seat);
						memberService.moveSeat(param3); // 이동한 자리 채우기
						sessionStorage.put("seat_no", seat);
						return View.MEMBER;
					} else if (syn.equalsIgnoreCase("n")) {
						System.out.println("자리 이동이 취소되었습니다.");
						return View.MEMBER;
					} else {
						System.out.println("잘못된 입력 입니다. 다시 선택해주세요.");
						return View.CHANGESEAT;
					}
				} else if (seat > 100 || seat <= 0) {
					System.out.println("1~100 사이의 자연수만 입력해주세요.");
					return View.CHANGESEAT;
				}
			}
			System.out.println("사용중인 좌석입니다. 다시 선택해주세요.");
			return View.CHANGESEAT;
		}
	}

	public View info() {
		Member mem = (Member) sessionStorage.get(MainController.SESSION_MEMBER);
		List<Object> param = new ArrayList();
		param.add(mem.getMem_id());
		List<Member> list = memberService.memberInfo(param);
		printInfo(list);
		int select = ScanUtil.nextInt("메뉴를 선택해주세요 >> ");
		switch (select) {
		case 1:
			return View.INFOEDIT;
		case 2:
			return View.INFODEL;
		case 3:
			return View.MEMBER;
		default:
			System.out.println("잘못 선택하셨습니다.");
			return View.INFO;
		}
	}

	// 회원정보 수정 시 정규식표현에 맞게 수정되도록 하기
	public View infoEdit() {
		String pass = ScanUtil.nextLine("비밀번호를 입력해주세요 >> ");
		Member mem = (Member) sessionStorage.get(MainController.SESSION_MEMBER);
		if (pass.equals(mem.getMem_pass())) {
			while (true) {

				printInfoEditMenu();
				int select;
				List<Object> param = new ArrayList<>();
				String pass2;
				String telno;
				String name;
				while (true) {
					select =  ScanUtil.nextInt("수정할 정보를 선택하세요 >> ");
					if (select == 1 || select == 4) {
						while (true) {
							pass2 = ScanUtil.nextLine("새로운 비밀번호 입력 >> ");
							if (memberService.isValidMemPass(pass2)) {
								System.out.println("유효한 비밀번호입니다.");
								break;
							} else {
								System.out.println("비밀번호는 5자 이상 20자 이하의 영어, 숫자만 입력이 가능합니다.");
							}
						}
						while (true) {
							String passChk = ScanUtil.nextLine("새로운 비밀번호 확인 >> ");
							if (passChk.equals(pass2)) {
								System.out.println("입력하신 두 비밀번호가 일치합니다");
								break;
							} else {
								System.out.println("입력하신 두 비밀번호가 일치 하지 않습니다.");
							}
						}
						param.add(pass2);
						break;

					}
					if (select == 2 || select == 4) {
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
						break;
					}
					if (select == 3 || select == 4) {
						while (true) {
							name = ScanUtil.nextLine("이름 >> ");
							if (memberService.isValidMemName(name)) {
								System.out.println("유효한 이름입니다.");
								break;
							} else {
								System.out.println("이름은 2~6글자의 한글만 사용가능합니다.");
							}
						}
						param.add(name);
						break;
					}

					if (select >= 5 || select <= 0) {
						System.out.println("잘못입력하셨습니다.");
					}

				}
				while (true) {
					String syn = ScanUtil.nextLine("수정 하시겠습니까? Y / N");
					if (syn.equalsIgnoreCase("y")) {
						param.add(mem.getMem_id());
						memberService.infoEdit(param, select);
						return View.MEMBER;
					} else if (syn.equalsIgnoreCase("n")) {
						System.out.println("회원정보 수정이 취소되었습니다.");
						return View.MEMBER;
					} else {
						System.out.println("잘못된 입력입니다. 다시 한번 확인해 주세요.");
					}

				}

			}
		} else {
			System.out.println("비밀번호가 일치하지 않습니다.");
			return View.INFOEDIT;
		}
	}

	public View infoDel() {
		String pass = ScanUtil.nextLine("비밀번호를 입력해주세요. ");
		Member mem = (Member) sessionStorage.get(MainController.SESSION_MEMBER);
		if (pass.equals(mem.getMem_pass())) {
			while (true) {
				String delyn = ScanUtil.nextLine("정말 탈퇴하시겠습니까? Y / N ");
				if (delyn.equalsIgnoreCase("y")) {
					List<Object> param = new ArrayList();
					Member mem1 = (Member) sessionStorage.get(MainController.SESSION_MEMBER);
					java.util.Date date1 = new Date();
					long time1 = date1.getTime();
					Timestamp ts1 = new Timestamp(time1);
					List<Object> param11 = new ArrayList();
					param11.add(ts1);
					param11.add(mem.getMem_id());
					param11.add(sessionStorage.get("seat_no"));
					memberService.signOut(param11);
					param.add(mem.getMem_id());
					memberService.delete(param);
					return View.HOME;
				} else if (delyn.equalsIgnoreCase("n")) {
					System.out.println("회원 탈퇴가 취소되었습니다.");
					return View.MEMBER;
				} else {
					System.out.println("잘못된 입력입니다.");
				}
			}
		} else {
			System.out.println("비밀번호가 일치하지 않습니다.");
			return View.INFODEL;
		}

	}

}
