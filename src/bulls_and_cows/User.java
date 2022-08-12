package bulls_and_cows;

import java.util.Scanner;

public class User {
	private String userId; // 아이디
	private int password; //비밀번호
	private String record; //기록 저장
	
	//디폴트 생성자
	public User() {}

	//모든 파라미터를 받는 생성자
	public User(String userId, int password, String record) {
		this.userId = userId;
		this.password = password;
		this.record = record;
	}

	//Getter and Setter
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getPassword() {
		return password;
	}

	public void setPassword(int password) {
		this.password = password;
	}

	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}
}
