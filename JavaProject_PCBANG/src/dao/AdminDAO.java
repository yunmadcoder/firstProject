package dao;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.ConvertUtils;
import util.JDBCUtil;
import vo.Admin;
import vo.Prod;
import vo.Member;

// 데이터베이스로 쿼리를 날려서 결과를 얻는다.
public class AdminDAO {
	// 싱글톤 패턴을 만든다.
	private static AdminDAO instance = null;
	private AdminDAO() {}
	public static AdminDAO getInstance() {
		if(instance == null) 
			instance = new AdminDAO();
		return instance;
	}
	
	// JDBC를 부른다.
	JDBCUtil jdbc = JDBCUtil.getInstance();
	public Admin login(List<Object> list) {
		String sql = "SELECT ad_id, ad_pass from member where ad_id = ? and ad_pass = ?";
		Map<String,Object> m = jdbc.selectOne(sql,list);
		return ConvertUtils.convertToVo(m, Admin.class);
	}
	
	public List<Member> memList(List<Object> param) {
		String sql =" SELECT * FROM (SELECT ROWNUM rnum, A.* \r\n" + 
				"    FROM(SELECT MEM_NO, RPAD(MEM_ID, 15, ' ') mem_id, RPAD(MEM_PASS, 15, ' ') mem_pass, MEM_NAME, to_char(MEM_BIRTH,'yy-MM-DD') mem_birth, MEM_TELNO, MEM_R_TIME \r\n" + 
				"        FROM MEMBER WHERE MEM_DELYN = 'N') A)\r\n" + 
				"        WHERE rnum >= ? and rnum <= ?";
		List<Map<String,Object>> l = jdbc.selectList(sql, param);
		return ConvertUtils.convertToList(l, Member.class);
	}
	
	public Admin adminSignIn(List<Object> param) {
	
			String sql = "SELECT AD_NO, AD_ID, AD_PASS, AD_NAME FROM ADMIN WHERE AD_ID = ? and AD_PASS = ?";
			Map<String,Object> map = jdbc.selectOne(sql, param);
			if(map == null) {
				return null;
			} else {
				return ConvertUtils.convertToVo(map, Admin.class);
			}
	}
	
	public void memEdit(List<Object> param, int select) {
		String sql_front = "update member set";
		if(select == 1 || select == 4) {
			sql_front += " mem_name = ? ";
		}
		if(select == 2 || select == 4) {
			if(select == 4) sql_front +=",";
			sql_front += " mem_pass = ? ";
		}
		if(select == 3 || select == 4) {
			if(select == 4) sql_front +=",";
			sql_front += " mem_telno = ? ";
		}

		String sql_where = " where mem_no = ?";
		String sql = sql_front + sql_where;
		jdbc.update(sql, param);
	}
	public void memDel(int selectNo) {
		String sql = "update member\r\n" + 
				"    set mem_delyn = 'Y'\r\n" + 
				"        where mem_no = " + selectNo;
		jdbc.update(sql);
		
		
		
	}
	public boolean chkMem(int selectNo) {
		String sql = "select mem_no from member where mem_delyn = 'N' and mem_no = " + selectNo;
		List<Map<String, Object>> l = jdbc.selectList(sql);
		if (l == null) {
			return true;
		} else {
		return false;
		}
		
	}

	
}
