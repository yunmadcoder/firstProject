package dao;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.ConvertUtils;
import util.JDBCUtil;
import vo.Prod;

// 데이터베이스로 쿼리를 날려서 결과를 얻는다.
public class ProdDAO {
	// 싱글톤 패턴을 만든다.
	private static ProdDAO instance = null;
	private ProdDAO() {}
	public static ProdDAO getInstance() {
		if(instance == null) 
			instance = new ProdDAO();
		return instance;
	}
	
	// JDBC를 부른다.
	JDBCUtil jdbc = JDBCUtil.getInstance();
	public Member login(List<Object> list) {
		String sql = "SELECT mem_id, mem_pass from member where mem_id = ? and mem_pass = ?";
		Map<String,Object> l = jdbc.selectOne(sql,list);
		return ConvertUtils.convertToVo(l, Member.class);
	}
	
	public List<Prod> getGU() {
		String sql = "select lprod_name from lprod where lprod_gu not like \'T%\'";
		List<Map<String, Object>> l = jdbc.selectList(sql);
		return ConvertUtils.convertToList(l, Prod.class);
	}
	
	public List<Prod> foodList(List<Object> param) {
		String sql = "select RPAD(PROD_NAME, 16, ' ') prod_name, prod_price, prod_jaego from prod where prod_lgu = (select lprod_gu from lprod where lprod_name = ?) and prod_delyn = 'N' and prod_jaego > 0";
		List<Map<String, Object>> l = jdbc.selectList(sql, param);
		return ConvertUtils.convertToList(l, Prod.class);
	}
	
	public void cart(List<Object> param1) {
		String sql = "insert into cart(cart_no, prod_no, seat_no, cart_qty) values((select max(cart_no)+1 from cart), (select prod_no from prod where prod_name = ?), ?, ?)";
		jdbc.update(sql, param1);
	}
	
	public List<Prod> myCartList(List<Object> param) {
		String sql = "select p.prod_no prod_no, rpad(p.prod_name,16,' ') prod_name, c.cart_qty cart_qty, p.prod_price prod_price, c.cart_no cart_no from cart c, prod p where seat_no = (select seat_no from seat where mem_id = ?) and\r\n" + 
				"    c.prod_no = p.prod_no and c.cart_yn = 'N'";
		List<Map<String, Object>> l = jdbc.selectList(sql, param);
		return ConvertUtils.convertToList(l, Prod.class);
	}
	
	public void clearCart(List<Object> param1) {
		String sql = "update cart set cart_yn = 'Y' where cart_no = ? and seat_no = ?";
		jdbc.update(sql, param1);
		
	}
	
	public Prod chkQty(String menu) {
		String sql = "select prod_jaego from prod where prod_name = '" + menu + "'";
		Map<String,Object> l = jdbc.selectOne(sql);
		return ConvertUtils.convertToVo(l, Prod.class);
	}
	
	public List<Prod> orderList() {
		String sql = "select o.order_no order_no, o.cart_no cart_no, o.seat_no seat_no, o.ad_id ad_id, \r\n" + 
				"        o.order_yn order_yn, c.prod_name prod_name, c.cart_qty cart_qty, c.prod_price prod_price \r\n" + 
				"            from orderlist o, (select p.prod_name prod_name, c.cart_qty cart_qty, p.prod_price prod_price, \r\n" + 
				"                                    c.cart_no cart_no from cart c, prod p where seat_no in (select seat_no from seat) and\r\n" + 
				"								    c.prod_no = p.prod_no and c.cart_yn = 'Y') c where o.cart_no = c.cart_no and substr(order_no, 0, 6) = to_char(sysdate, 'yymmdd')";
		List<Map<String, Object>> l = jdbc.selectList(sql);
		return ConvertUtils.convertToList(l, Prod.class);
	}
	
	public List<Prod> incompOrderList() {
		String sql = "select o.order_no order_no, o.cart_no cart_no, o.seat_no seat_no, o.ad_id ad_id, o.order_yn order_yn, \r\n" + 
				"                c.prod_name prod_name, c.cart_qty cart_qty, c.prod_price prod_price \r\n" + 
				"                from orderlist o, (select p.prod_name prod_name, c.cart_qty cart_qty, p.prod_price prod_price, \r\n" + 
				"                                    c.cart_no cart_no from cart c, prod p where seat_no in (select seat_no from seat) and\r\n" + 
				"							    c.prod_no = p.prod_no and c.cart_yn = 'Y') c where o.cart_no = c.cart_no and order_yn != 'Y' and substr(order_no, 0, 6) = to_char(sysdate, 'yymmdd')";
		List<Map<String, Object>> l = jdbc.selectList(sql);
		return ConvertUtils.convertToList(l, Prod.class);
	}
	
	public void compOrder(List<Object> param) {
		String sql = "update orderlist set order_yn = 'Y' where order_no = ?";
		jdbc.update(sql, param);
		
	}
	
	
	public void chulgo(List<Object> param4) {
		String sql = "insert into remain(remain_no, prod_no, remain_chulgo, remain_date) values((select max(remain_no)+1 from remain), ?, ?, sysdate)";
		jdbc.update(sql, param4);
		
	}
	
	public List<Prod> prodList(List<Object> param) {
		String sql = " SELECT * FROM (SELECT ROWNUM rnum, A.* \r\n" + 
				"    FROM(SELECT PROD_NO, PROD_LGU, RPAD(PROD_NAME, 14, ' ') prod_name, PROD_PRICE, PROD_JAEGO, PROD_COST \r\n" + 
				"        FROM PROD WHERE PROD_DELYN = 'N') A)\r\n" + 
				"        WHERE rnum >= ? and rnum <= ?";
		List<Map<String, Object>> l = jdbc.selectList(sql, param);
		return ConvertUtils.convertToList(l, Prod.class);
	}
	public void prodAdd(List<Object> param) {
		String sql = "INSERT INTO PROD(PROD_NO, PROD_LGU, PROD_NAME, PROD_PRICE, PROD_COST) \r\n" + 
				"    VALUES((SELECT MAX(PROD_NO)+1 FROM PROD), ?, ?, ?, ?)";
		jdbc.update(sql, param);
	}
	public void prodUpdate(List<Object> param, int select) {
		String sql_front = "update PROD set";
		if(select == 1 || select == 4) {
			sql_front += " prod_name = ? ";
		}
		if(select == 2 || select == 4) {
			if(select == 4) sql_front +=",";
			sql_front += " prod_price = ? ";
		}
		if(select == 3 || select == 4) {
			if(select == 4) sql_front +=",";
			sql_front += " prod_cost = ? ";
		}
		String sql_where = " where prod_no = ?";
		String sql = sql_front + sql_where;
		jdbc.update(sql, param);
	}
	public void prodDel(int selectNo) {
		String sql = "UPDATE PROD\r\n" + 
				"    SET PROD_DELYN = 'Y'\r\n" + 
				"        WHERE PROD_NO = " + selectNo;
		jdbc.update(sql);
		
	}
	public void prodOrder(List<Object> param) {
		String sql = "insert into remain(REMAIN_NO, PROD_NO, REMAIN_IPGO)\r\n" + 
				"    values((select max(remain_no)+1 from remain), ?, ?)";
		jdbc.update(sql, param);
	}
	public List<Prod> prodTime() {
		String sql = "select prod_no, RPAD(PROD_NAME, 16, ' ') prod_name, prod_price from prod where prod_lgu like 'T%'";
		List<Map<String, Object>> l = jdbc.selectList(sql);
		return ConvertUtils.convertToList(l, Prod.class);
	}
	public Prod timePrice(int select) {
		String sql = "select prod_price from prod where prod_no = " + select;
		Map<String,Object> map = jdbc.selectOne(sql);
		return ConvertUtils.convertToVo(map, Prod.class);
	}
	public List<Prod> salesList() {
		String sql = "select prod_lgu, RPAD(PROD_NAME, 16, ' ') prod_name, remain_chulgo, prod_price, amount, to_char(remain_date, 'yyyy-mm-dd hh24:mi:ss') remain_date "
				+ "from view_sales where remain_chulgo > 0 and substr(to_char(remain_date, 'yyyy-mm-dd'), 0, 10) = to_char(sysdate, 'yyyy-mm-dd')"
				+ "order by remain_date";
		List<Map<String, Object>> l = jdbc.selectList(sql);
		return ConvertUtils.convertToList(l, Prod.class);
	}
	public List<Prod> purchaseList() {
		String sql = "select prod_lgu, RPAD(PROD_NAME, 16, ' ') prod_name, remain_ipgo, prod_cost, amount2, to_char(remain_date, 'yyyy-mm-dd hh24:mi:ss') remain_date "
				+ "from view_sales2 where remain_ipgo > 0 and substr(to_char(remain_date, 'yyyy-mm-dd'), 0, 10) = to_char(sysdate, 'yyyy-mm-dd')"
				+ "order by remain_date";
		List<Map<String, Object>> l = jdbc.selectList(sql);
		return ConvertUtils.convertToList(l, Prod.class);
	}
	public Prod chkProd(int selectNo) {
		String sql = "select prod_no from prod where prod_delyn = 'N' and prod_no = " + selectNo;
		Map<String,Object> map = jdbc.selectOne(sql);
		return ConvertUtils.convertToVo(map, Prod.class);
	}
	public Prod chkOrder(String select) {
		String sql = "select * from orderlist where order_yn = 'N' and order_no = '" + select + "'";
		Map<String,Object> map = jdbc.selectOne(sql);
		return ConvertUtils.convertToVo(map, Prod.class);
	}
	
	public void orderCancel(int seatNo) {
		String sql = "update cart set cart_yn = 'Y' where seat_no = " + seatNo;
		jdbc.update(sql);
		
	}

	public Prod guChk(String gu) {
		String sql = "select lprod_gu from lprod where lprod_gu = '" + gu + "'";
		Map<String,Object> map = jdbc.selectOne(sql);
		return ConvertUtils.convertToVo(map, Prod.class);
	}
	
}
