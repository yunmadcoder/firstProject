package vo;

import lombok.Data;

@Data
public class Member {

	private int seat_no;

	private int mem_no;
	private String mem_id;
	private String mem_pass;
	private String mem_name;
	private String mem_birth;
	private String mem_telno;
	private int mem_r_time;
	private String mem_delyn;

	private int use_time;
	
}
