package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import service.AdminService;
import service.MemberService;
import service.ProdService;
import util.ScanUtil;
import util.View;
import vo.Admin;

public class admin {
	
	public static void main(String[] args) {
		adminSignIn();
	}
static public Map<String , Object>sessionStorage = AdminController.sessionStorage;
	static AdminService adminService = AdminService.getInstance();
	ProdService prodService = ProdService.getInstance();
	MemberService memberService = MemberService.getInstance();
	
	final static public String SESSION_ADMIN = "admin";
	
	public static View adminSignIn() {
		List<Object> param = new ArrayList<>();
    	param.add(ScanUtil.nextLine("id>>"));
    	param.add(ScanUtil.nextLine("pass"));
    	
    	Admin AD = adminService.adminSignIn(param);
    	
    	System.out.println(AD);
    	
    	System.out.println(AD);
    	
		
		
		
		
		return null;
	}
}
