package edu.pnu;

public class DriverLoading {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("로딩 성공");
		}catch(ClassNotFoundException e) {
			System.out.println("로딩 실패 : " +e.getMessage());
		}

	}

}
