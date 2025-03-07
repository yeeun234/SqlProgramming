package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class QueryPreparedStatement {
	public static void main(String[] args) {
		QueryPreparedStatement pre = new QueryPreparedStatement();
		Scanner sc = new Scanner(System.in);
		Connection co = pre.connect();
		if (co == null) {
			System.out.println("연결실패");
			return;
		}

		pre.mission1(co, sc);
	}

	public void mission1(Connection co, Scanner sc) {
		PreparedStatement psmt = null;
		ResultSet rs = null;
		System.out.println("1. 인구 수를 입력 받아서 그보다 많은 인구를 가진 도시를 검색해서 출력하세요.");
		int pop = sc.nextInt();

		try {
			psmt = co.prepareStatement("select * from city where Population > ?");
			psmt.setInt(1, pop);
			rs = psmt.executeQuery();

			while (rs.next()) {
				System.out.println("Name: " + rs.getString("Name"));
				System.out.println("CountryCode: " + rs.getString("CountryCode"));
				System.out.println("District: " + rs.getString("District"));
				System.out.println("Population: " + rs.getString("Population") + "\n");
			}

		} catch (Exception e) {
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (psmt != null)
					psmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world","musthave","tiger");
		} catch (Exception e) {
		}
		return con;
	}
}
