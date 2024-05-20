package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.ConvertUtils;
import util.JDBCUtil;
import util.View;
import vo.Member;
import vo.Prod;

public class MemberDAO {
	private static MemberDAO singleTon = null;
	
	private MemberDAO() {
	};

	public static MemberDAO getInstance() {
		if (singleTon == null) {
			singleTon = new MemberDAO();
		}
		return singleTon;
	}
	
	JDBCUtil jdbc = JDBCUtil.getInstance();
	

	public List seatList() {
		String sql = "select seat_no from seat where seat_useyn = 'N'"; // 현재 사용중인 좌석 번호만 가져오기
		List<Map<String, Object>> l = jdbc.selectList(sql);
		return  ConvertUtils.convertToList(l, Member.class);
	}

	public void changeSeat(List<Object> param) {
		String sql = "update use set seat_no = ? where mem_id = ? and seat_no = ?"; // 이동한 좌석 번호로 업데이트
		jdbc.update(sql, param);
		
	}

	

	public List<Member> memberInfo(List<Object> param) {
		String sql = "select mem_id, mem_name, to_char(mem_birth, 'yyyy-mm-dd'), mem_telno, mem_r_time from member where mem_id = ?"; // 회원 정보 (param = id) 가져오기
		List<Map<String, Object>> l = jdbc.selectList(sql, param);
		return ConvertUtils.convertToList(l, Member.class);
	}

	public void delete(List<Object> param) {
		String sql = "update MEMBER set mem_delyn = 'Y' where mem_ID = ?";
		jdbc.update(sql, param);
		
	}

	public void useStart(List<Object> param) {
		String sql = "insert into use(use_no, mem_id, seat_no, use_s_time) values((select max(use_no)+1 from use), ?, ?, ?)";
		jdbc.update(sql, param);
		
	}

	public void infoEdit(List<Object> param, int select) {
		String sql_front = "update MEMBER set";
		if(select == 1 || select == 4) {
			sql_front += " mem_pass = ? ";
		}
		if(select == 2 || select == 4) {
			if(select == 4) sql_front +=",";
			sql_front += " mem_telno = ? ";
		}
		if(select == 3 || select == 4) {
			if(select == 4) sql_front +=",";
			sql_front += " mem_name = ? ";
		}
		String sql_where = " where mem_id=?";
		String sql = sql_front + sql_where;
		jdbc.update(sql, param);
		
	}

	public void chargeTime(List<Object> param) {
		String sql = "update member set mem_r_time = mem_r_time + ? where mem_id = ?";
		jdbc.update(sql, param);
	}
	

	public Member signIn(List<Object> param) {
		String sql = "SELECT mem_id, mem_pass, mem_name, to_char(mem_birth, 'YY-MM-DD') mem_birth, mem_telno, mem_r_time from member where mem_id = ? and mem_pass = ?"
				+ "			and mem_delyn = 'N'";
		Map<String,Object> map = jdbc.selectOne(sql, param);
		if(map == null) {
			return null;
		}
		return ConvertUtils.convertToVo(map, Member.class);
	}
	
	public boolean idChk(String id) {
		String sql = "SELECT MEM_ID FROM MEMBER WHERE MEM_ID = ?";
		List<Object> param = new ArrayList<Object>();
		param.add(id);
		Map<String, Object> map = jdbc.selectOne(sql,param);
		if(map == null) {
			return true;
		} else {
			return false;
		}
		
		
	}
	
	public void signUp(List<Object> param) {
		String sql ="INSERT INTO MEMBER (MEM_NO, MEM_ID, MEM_PASS, MEM_NAME, MEM_BIRTH, MEM_TELNO)\r\n" + 
				"    VALUES ((select max(mem_no)+1 from member), ?, ?, ?, ?, ?)";
		jdbc.update(sql,param);
	}

	public void signOut(List<Object> param) {
		String sql =  "update use set use_e_time = ? where mem_id = ? and seat_no = ?";
		jdbc.update(sql, param);
		
	}
	

		
	public Member findId(List<Object> param) {
		String sql = "select MEM_ID from member\r\n" + 
					"where mem_name = ? and mem_telno = ?";
		Map<String,Object> map = jdbc.selectOne(sql,param);
		return ConvertUtils.convertToVo(map, Member.class);
		}

	public void findPass(List<Object> param) {
		String sql = "update MEMBER \r\n" + 
					"    set MEM_PASS = 'a123456789'\r\n" + 
					"    where  MEM_ID = ? and mem_name = ? and mem_telno = ?";
		jdbc.update(sql,param);
	}

	public Member useTime(List<Object> param1) {
		String sql = "select round((use_e_time - use_s_time)*24*60*60) use_time from (select * from use where mem_id = ? and seat_no = ? order by use_no desc) where rownum = 1";
		Map<String,Object> map = jdbc.selectOne(sql,param1);
		return ConvertUtils.convertToVo(map, Member.class);
	}

	public void subTime(List<Object> param2) {
		String sql = "update member \r\n" + 
				"set mem_r_time = \r\n" + 
				"    case when mem_r_time - ? < 0 \r\n" + 
				"    then 0 \r\n" + 
				"    else mem_r_time - ? \r\n" + 
				"    end \r\n" + 
				"where mem_id = ?";
		jdbc.update(sql, param2);
		
	}

	public Member timeChk(List<Object> param1) {
		String sql = "select mem_r_time from member where mem_id = ?";
		Map<String,Object> map = jdbc.selectOne(sql,param1);
		return ConvertUtils.convertToVo(map, Member.class);
	}

	public void putOrderList(List<Object> param1) {
		String sql = "insert into orderlist(order_no, cart_no, seat_no, ad_id) values((select to_char(sysdate, 'yymmdd')  || lpad(to_char(substr(max(order_no), -4)+1), 4, '0') from orderlist), ?, ?, 'master1')";
		jdbc.update(sql, param1);
		
	}

	public void addCost(List<Object> param3) {
		String sql = "update use set use_total = use_total + ? where mem_id = ? and use_e_time is null";
		jdbc.update(sql, param3);
		
	}

	public void updateSeat(List<Object> param2) {
		String sql = "update seat set seat_useyn = 'N', mem_id = null where seat_no = ?";
		jdbc.update(sql, param2);
		
	}

	public void moveSeat(List<Object> param3) {
		String sql = "update seat set seat_useyn = 'Y', mem_id = ? where seat_no = ?";
		jdbc.update(sql, param3);
	}

	public Member signInChk(String id) {
		String sql = "select mem_id, seat_no from seat where mem_id = '" + id + "'";
		Map<String,Object> map = jdbc.selectOne(sql);
		return ConvertUtils.convertToVo(map, Member.class);
	}

	
	public boolean existingChk(List<Object> param2) {
		String sql = "select mem_id from member where mem_id = ?";
		List<Map<String, Object>> l = jdbc.selectList(sql, param2);
		if(l == null) {
			return true;
		} else {
			return false;
		}
		
		
	}

	public boolean exChk(List<Object> param) {
		String sql = "select mem_id, mem_name, mem_telno from member where mem_id = ? and mem_name = ? and mem_telno = ?";
		List<Map<String, Object>> l = jdbc.selectList(sql, param);
		if (l == null) {
			return true;
		} else {
			return false;
		}
		
	}

	public Member getInfo(String id) {
		String sql = "select mem_id, mem_name, mem_r_time from member where mem_id = '" + id + "'";
		Map<String,Object> map = jdbc.selectOne(sql);
		return ConvertUtils.convertToVo(map, Member.class);
	}
	
	public Member telChk(String telno) {
		String sql = "select mem_telno from member where mem_telno = '" + telno + "'";
		Map<String,Object> map = jdbc.selectOne(sql);
		return ConvertUtils.convertToVo(map, Member.class);
	}	
	
	

}
