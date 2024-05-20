package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import print.Print;
import service.AdminService;
import service.MemberService;
import util.ScanUtil;
import util.View;
import util.isValid;
import vo.Admin;
import vo.Member;

public class MainController extends Print {
	static public Map<String, Object> sessionStorage = new HashMap<>();
	MemberService memberService = MemberService.getInstance();
	AdminService adminService = AdminService.getInstance();
	
	final static public String SESSION_MEMBER = "member";
	final static public String SESSION_ADMIN = "admin";
	public static void main(String[] args) {
		MainController main = new MainController();
		
	}
public View signIn() {
		
		List<Object> param = new ArrayList<Object>();
		String id = ScanUtil.nextLine("ID 입력 >> ");
		param.add(id);
		String pass = ScanUtil.nextLine("PASS 입력 >> ");
		param.add(pass);
		Admin ad = null;
		Member mb = null;
		if(id.startsWith("master")) {
			 ad = adminService.adminSignIn(param);
		} else {
			mb = memberService.signIn(param);
		}
		
		Member mem = memberService.signInChk(id);

		if (mb != null && mem == null) {
			System.out.println("로그인 성공");
			sessionStorage.put(SESSION_MEMBER, mb);
			return View.SELECTSEAT;
			
		}else if(ad != null) {
				System.out.println("로그인 성공");
				sessionStorage.put(SESSION_ADMIN, ad);
				return View.ADMIN;
			
		} else if (mem != null) {
			System.out.println(mem.getSeat_no()+"번 좌석에서 사용중인 아이디입니다!");
			// 로그아웃 시키기 넣을지말지
			return View.HOME;
		} else {
			System.out.println("다시 로그인해주세요");
			return View.SIGNIN;
		}
	}
		


	
	public View signUp() {
		System.out.println("회원가입 정보를 입력해주세요.");
		List<Object> param = new ArrayList<Object>();
		String id;
		while (true) {
			id = ScanUtil.nextLine("아이디 입력 >> ");
			if (memberService.isValidMemId(id)) {
				List<Object> param2 = new ArrayList<Object>();
				param2.add(id);
				boolean existingChk = memberService.existingChk(param2);
				if (!existingChk) {
					System.out.println("이미 존재하는 아이디 입니다.");
				} else {
					System.out.println("유효한 아이디입니다.");
					break;
				}
			}

			else if (id.contains("master")) {
				System.out.println("사용할 수 없는 단어가 포함되어 있습니다.");
			} else {
				System.out.println("아이디는 5~20글자의 영문, 숫자만 입력 가능합니다.");
			}
		}
		
		
		String pass;
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
		String name;
		while(true) {
			name = ScanUtil.nextLine("이름 입력 >> ");
			if(memberService.isValidMemName(name)) {
				System.out.println("유효한 이름입니다.");
				break;
			} else {
				System.out.println("이름은 2~6글자의 한글만 사용가능합니다.");
			}
		}
		
		String birth;
		while(true) {
			birth = ScanUtil.nextLine("주민번호 앞 6자리 입력 >> ");
			if(memberService.isValidMemBirth(birth)) {
				System.out.println("유효한 생년월일 입니다.");
				break;
			} else {
				System.out.println("유효한 생년월일을 입력해주세요");
			}
		}
		
		String telno;
		while(true) {
			// 23차 telno 중복체크
			while(true) {
				System.out.println("ex) 010-1234-1234");
				telno = ScanUtil.nextLine("전화번호 입력 >> ");
					Member member = memberService.telChk(telno);
					if (member == null) {
						break;
					} else { 
						// 문구 수정 필요
						System.out.println("해당 전화번호로 가입된 계정이 존재합니다.");
					}
			}
	
			if(memberService.isValidMemTelno(telno)) {
				System.out.println("유효한 전화번호 입니다");
				break;
			} else {
				System.out.println("올바른 전화번호를 입력해주세요");
			}
		}	
		param.add(id);
		param.add(pass);
		param.add(name);
		param.add(birth);
		param.add(telno);
		memberService.signUp(param);
		System.out.println("회원가입에 성공하였습니다!");
		
		return View.HOME;
	}
	
	public View findIDPW() {
		printFindId();
		int select = ScanUtil.nextInt(">> ");
		switch (select) {
		case 1:
			return View.FINDID;
		case 2:
			return View.FINDPASS;
		case 3:
			return View.HOME;
		default:
			return View.FINDIDPW;
		}
	}
	
	public View findId() {
		String name = ScanUtil.nextLine("회원명을 입력하세요 >> ");
		String telno = "";
		while (true) {
			telno = ScanUtil.nextLine("전화번호를 입력하세요 >> ");
			if (memberService.isValidMemTelno(telno)) {
				break;
			} 
			System.out.println("올바른 전화번호를 입력해주세요");
		}
		List<Object> param = new ArrayList<Object>();
		param.add(name);
		param.add(telno);

		Member mb = memberService.findId(param);
		while(true) {
			if (mb == null) {
				System.out.println("회원 정보가 없습니다.");
				String selyn = ScanUtil.nextLine("회원가입 하시겠습니까 Y / N");
				if(selyn.equalsIgnoreCase("y")) {
					return View.SIGNUP;
				} else if (selyn.equalsIgnoreCase("n")) {
					return View.FINDID;
				} else {
					System.out.println("잘못 입력하셨습니다.");
				}
			} else {
				break;
			}
		}
		System.out.println("입력하신 정보로 가입된 아이디는 " + mb.getMem_id() + "입니다.");
		return View.HOME;
	}
	
	public View findPass() {
		String id = ScanUtil.nextLine("아이디를 입력하세요 >> ");
		String name = ScanUtil.nextLine("회원명을 입력하세요 >> ");
		String telno = ScanUtil.nextLine("전화번호를 입력하세요 >> ");
		List<Object> param = new ArrayList<Object>();
		param.add(id);
		param.add(name);
		param.add(telno);
		boolean exChk = memberService.exChk(param);
		if (exChk) {
			System.out.println("회원 정보가 없습니다. 다시 확인해 주세요.");
			return View.FINDIDPW;
		}
		memberService.findPass(param);
		System.out.println("비밀번호가 'a123456789' 로 초기화 되었습니다. 로그인 후 비밀번호를 변경해주세요.");

		return View.HOME;
	}
}