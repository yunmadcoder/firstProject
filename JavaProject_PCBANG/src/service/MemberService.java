package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Pattern;

import controller.Controller;
import controller.MainController;
import dao.MemberDAO;
import vo.Member;
import vo.Prod;

public class MemberService {
	private static MemberService singleTon = null;

	private MemberService() {
	};

	public static MemberService getInstance() {
		if (singleTon == null) {
			singleTon = new MemberService();
		}
		return singleTon;
	}
	MemberDAO memberDao = MemberDAO.getInstance();

	

	public List seatList() {
		return memberDao.seatList();
	}

	public void changeSeat(List<Object> param) {
		memberDao.changeSeat(param);
		
	}



	public List<Member> memberInfo(List<Object> param) {
		// TODO Auto-generated method stub
		return memberDao.memberInfo(param);
	}

	public void delete(List<Object> param) {
		memberDao.delete(param);
		
	}

	public void useStart(List<Object> param) {
		memberDao.useStart(param);
		
	}

	public void infoEdit(List<Object> param, int select) {
		memberDao.infoEdit(param, select);
		
	}

	public void chargeTime(List<Object> param) {
		memberDao.chargeTime(param);
		
	}
	
	public void signUp(List<Object> param) {
		memberDao.signUp(param);
	}
		
	
	public Member signIn(List<Object> param) {
		return memberDao.signIn(param);
	
	}
	public List<Object> loginSuccess(){
		List<Object> list = (List<Object>)MainController.sessionStorage.get("list");
		
		
		
		return null;
	}

	public boolean idPassChk(String string) {
		boolean pass = true;
		for(char ch : string.toCharArray()) {
			// 숫자 체크
			if(('0'<= ch && ch <='9' || 'a'<= ch && ch<='z' ||'A'<= ch && ch<='Z') 
					&& (string.length() > 4 && string.length() < 20)) {
				pass = true;
			} else {
				pass = false;
			}
		}
		return pass;
		}

	public boolean idChk(String id) {
		
		return memberDao.idChk(id);
		
	}

	public boolean nameChk(String name) {
		boolean pass = true;
		for (char ch : name.toCharArray()) {
			if (('가' <= ch && ch <= '힇') && (name.length() >= 2 && name.length() <= 6)) {
				pass = true;
			} else {
				pass = false;
			}
		}
		return pass;
	}

	public  boolean  birthChk(String checkDate){

		   try{
		         SimpleDateFormat  dateFormat = new  SimpleDateFormat("yyMMdd");

		         dateFormat.setLenient(false);
		         dateFormat.parse(checkDate);
		         return  true;

		       }catch (ParseException  e){
		         return  false;
		       }

		}

	public boolean telnoChk(String telno) {
		boolean pass = true;
		for (char ch : telno.toCharArray()) {
			if(('0'<= ch && ch <='9' && telno.length() == 11)) {
				pass = true;
			}else {
				pass = false;
			}
		}
		return pass;
	}
	
	public boolean isValidMemId(String id) {

		String regexId = "^(?!.*master)[a-zA-Z][a-zA-Z0-9]{4,19}$";

		Pattern pattern = Pattern.compile(regexId);

		return pattern.matcher(id).matches();
	}

	public static boolean isValidMemPass(String pass) {

		String regexPass = "^[a-zA-Z0-9][a-zA-Z0-9]{4,19}$";

		Pattern pattern = Pattern.compile(regexPass);

		return pattern.matcher(pass).matches();
	}

	public static boolean isValidMemName(String name) {

		String regexPass = "^[가-힣]{2,6}$";

		Pattern pattern = Pattern.compile(regexPass);

		return pattern.matcher(name).matches();
	}

	public static boolean isValidMemBirth(String birth) {

		String regexPass = "^(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))$";

		Pattern pattern = Pattern.compile(regexPass);

		return pattern.matcher(birth).matches();
	}

	public static boolean isValidMemTelno(String telno) {

		String regexPass = "^\\d{3}-\\d{4}-\\d{4}$";

		Pattern pattern = Pattern.compile(regexPass);

		return pattern.matcher(telno).matches();
	}

	public boolean existingChk(List<Object> param2) {
		return memberDao.existingChk(param2);
	}

	public void signOut(List<Object> param) {
		memberDao.signOut(param);

	}

	public Member findId(List<Object> param) {

		return memberDao.findId(param);
	}

	public void findPass(List<Object> param) {
		memberDao.findPass(param);
	}

	public Member useTime(List<Object> param1) {
		return memberDao.useTime(param1);
	}

	public void subTime(List<Object> param2) {
		memberDao.subTime(param2);

	}

	public Member timeChk(List<Object> param1) {
		return memberDao.timeChk(param1);
	}

	public void putOrderList(List<Object> param1) {
		memberDao.putOrderList(param1);

	}

	public void addCost(List<Object> param3) {
		memberDao.addCost(param3);

	}

	public void updateSeat(List<Object> param2) {
		memberDao.updateSeat(param2);

	}

	public void moveSeat(List<Object> param3) {
		memberDao.moveSeat(param3);

	}

	public Member signInChk(String id) {
		return memberDao.signInChk(id);
	}

	public boolean exChk(List<Object> param) {
		return memberDao.exChk(param);
	}
	
	public Member getInfo(String id) {
		return memberDao.getInfo(id);
	}
	
	public Member telChk(String telno) {
		return memberDao.telChk(telno);
	}

}
