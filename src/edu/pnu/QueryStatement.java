package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.ResultSet;

public class QueryStatement {
	
	public static void main(String[] args) {
		QueryStatement co = new QueryStatement();
		Scanner sc = new Scanner(System.in);
		Connection con = co.connect();
		if (con == null) {
			System.out.println("연결실패");
			return;
		}

		co.mission1(con,sc);
		co.mission2(con,sc);
		sc.close();
	}
	
	private void mission2(Connection con,Scanner sc) {
		System.out.println(" 2. 국가 명의 일부 또는 국가코드를 입력받아서 해당국가의 도시의 이름과 인구를 검색해서 출력하세요.");
		
		// 국가 명의 일부 또는 국가코드를 입력 받는다.(Scanner) ==> pop
		
		String name = sc.nextLine();
		
		
		// 질의 객체 생성 (Statement) ==> st
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select Population , Name from city where CountryCode like "+"\"%"+name+"%\"");
			
			while(rs.next()) {
				System.out.println("Population :"+rs.getString("Population"));
				System.out.println("Name :"+rs.getString("Name"));
			}
			if(st!=null) st.close();
			if(rs!=null) rs.close();
		}catch(Exception e) {
			System.out.println("fail");
		}
		
		// 질의 실행 (st.executeQuery(sql)) ==> rs
		
		// 결과 셋 출력 ==> 커서 프로세싱 (rs.next())
		
		// 리소스 닫기 (rs, st)
		
	}

	private void mission1(Connection con,Scanner sc) {
		System.out.println("1. 인구 수를 입력받아서 그보다 많은 인구를 가진 도시를 검색해서 출력하세요.");
		
		// 인구 수를 입력 받는다.(Scanner) ==> pop
		
		int pop = sc.nextInt();
		
		
		try {
		// 질의 객체 생성 (Statement) ==> st
		Statement st = con.createStatement();
		
		// 질의 실행 (st.executeQuery(sql)) ==> rs
		ResultSet rs = st.executeQuery("select * from city where Population >"+pop);
		// 결과 셋 출력 ==> 커서 프로세싱 (rs.next())
		while(rs.next()) {
			System.out.println("Name: " +rs.getString("Name"));
			System.out.println("CountryCode: "+rs.getString("CountryCode"));
			System.out.println("District: "+rs.getString("District"));
			System.out.println("Population: "+rs.getString("Population")+"\n");
		}
		// 리소스 닫기 (rs, st)
		if(rs!=null) rs.close();
		if(st!=null) st.close();
		
		}catch(Exception e) {System.out.println("query fail");
		}
		
	}

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world","root","tiger");
		}catch(Exception e) {}
		return con;
	}
}
