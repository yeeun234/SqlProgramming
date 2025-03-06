package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://아이피주소:3306/world","musthave","tiger");
			System.out.println("연결 성공");
			con.close();
		}catch(Exception e) {
			System.out.println("연결 실패 : " +e.getMessage());
		}

	}

}
