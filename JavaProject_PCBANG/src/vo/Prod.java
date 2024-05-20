package vo;

import lombok.Data;

@Data
public class Prod {
	 private int lprod_no;
	 private String lprod_gu;
	 private String lprod_name;
	 
	 
	 private int prod_no;
	 private String prod_lgu;
	 private String prod_name;
	 private int prod_price;
	 private int prod_jaego;
	 private int prod_cost;
	 
	 private int cart_qty;
	 private int cart_no;
	 
	 private String order_no;
	 private int seat_no;
	 private String ad_id;
	 private String order_yn;
	 
	 private String remain_date;
	 
	 private int amount;
	 private int amount2;
	 private int remain_ipgo;
	 private int remain_chulgo;
}
