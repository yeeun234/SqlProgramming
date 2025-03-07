package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryStatement {

	public static void main(String[] args) {
		QueryStatement co = new QueryStatement();
		Scanner sc = new Scanner(System.in);
		Connection con = co.connect();
		if (con == null) {
			System.out.println("연결실패");
			return;
		}

//		co.mission1(con,sc);
//		co.mission2(con,sc);
//		co.mission3(con, sc);
//		co.mission4(con, sc);
//		co.mission5(con, sc);
//		co.mission6(con, sc);
		co.mission7(con, sc);

		sc.close();
	}

	private void mission2(Connection con, Scanner sc) {
		System.out.println(" 2. 국가 명의 일부 또는 국가코드를 입력받아서 해당국가의 도시의 이름과 인구를 검색해서 출력하세요.");

		// 국가 명의 일부 또는 국가코드를 입력 받는다.(Scanner) ==> pop

		String name = sc.nextLine();

		// 질의 객체 생성 (Statement) ==> st
		try {
			Statement st = con.createStatement();
			ResultSet rs = st
					.executeQuery("select Population , Name from city where CountryCode like " + "'%" + name + "%'");

			while (rs.next()) {
				System.out.println("Population :" + rs.getString("Population"));
				System.out.println("Name :" + rs.getString("Name") + "\n");
			}
			if (st != null)
				st.close();
			if (rs != null)
				rs.close();
			// close문은 트라이문에 따로 넣으면 안됨. 이유 => while문이 도는 도중에 익셉션이 발생하면 캐치문으로 넘어가는데 그러면 클로즈문이
			// 실행이 안되서 세션이 종료가 안됨.
		} catch (Exception e) {
			System.out.println("fail");
		}

		// 질의 실행 (st.executeQuery(sql)) ==> rs

		// 결과 셋 출력 ==> 커서 프로세싱 (rs.next())

		// 리소스 닫기 (rs, st)

	}

	private void mission1(Connection con, Scanner sc) {
		System.out.println("1. 인구 수를 입력받아서 그보다 많은 인구를 가진 도시를 검색해서 출력하세요.");

		// 인구 수를 입력 받는다.(Scanner) ==> pop

		int pop = sc.nextInt();

		try {
			// 질의 객체 생성 (Statement) ==> st
			Statement st = con.createStatement();

			// 질의 실행 (st.executeQuery(sql)) ==> rs
			ResultSet rs = st.executeQuery("select * from city where Population >" + pop);
			// 결과 셋 출력 ==> 커서 프로세싱 (rs.next())
			while (rs.next()) {
				System.out.println("Name: " + rs.getString("Name"));
				System.out.println("CountryCode: " + rs.getString("CountryCode"));
				System.out.println("District: " + rs.getString("District"));
				System.out.println("Population: " + rs.getString("Population") + "\n");
			}
			// 리소스 닫기 (rs, st)
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();

		} catch (Exception e) {
			System.out.println("query fail");
		}

	}

	private void mission3(Connection con, Scanner sc) {
		System.out.println("3. 대륙을 입력 받아서 해당 대륙에 위치한 국가를 검색해서 출력하세요. (Continent)");
		String cont = sc.nextLine();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select name from country where continent like" + "'" + cont + "'");
			while (rs.next()) {
				System.out.println(rs.getString("name"));
			}

		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private void mission4(Connection con, Scanner sc) {
		System.out.println("4. 넓이(10,0002 km)를 입력 받아서 입력 값보다 작은 면적을 가진 국가의 이름과 면적을 면적 오름차순으로 검색해서 출력하세요.");
		Statement st = null;
		ResultSet rs = null;
		int sur = sc.nextInt();

		try {
			st = con.createStatement();
			String query = "select name,surfacearea from country where surfacearea<" + sur
					+ " order by surfacearea asc";
			rs = st.executeQuery(query);

			while (rs.next()) {
				System.out.print(rs.getString("name") + ",");
				System.out.print(rs.getString("surfacearea") + "\n");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally { // 오류가 나든안나든 무조건 실행해야하는구문이니 파이널리로
			try { // close메소드가 throws를 가지고있어 익셉션처리(트라이캐치)해줘야함.
				if (st != null)
					st.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private void mission5(Connection con, Scanner sc) {
		Statement st=null;
		ResultSet rs=null;
		
		System.out.println("5. 대한민국의 District를 입력 받아서 해당 지역에 있는 모든 도시를 검색해서 출력하세요. (예:‘Kyonggi’)");
		
		String district = sc.next();
		
		try {
			st=con.createStatement();
			rs=st.executeQuery("select name from city where district ="+"'"+district+"' and countrycode='kor'");
			
			while(rs.next()) {
				System.out.println(rs.getString("name"));
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(st!=null)
					st.close();
				if(rs!=null)
					rs.close();
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
	}
	
	private void mission6(Connection con, Scanner sc) {
		Statement st = null;
		ResultSet rs = null;
		
		
		System.out.println("6. 언어를 입력 받아서 해당 언어가 국가 공식 언어인 국가를 검색해서 출력하세요. (예:'Spanish’)");
		String lang = sc.next();
		try {
			st = con.createStatement();
			rs = st.executeQuery("select countrycode from countrylanguage where language ="+"'"+lang+"'"+" and isofficial ='t'");
			
			List<String> code = new ArrayList<>();
			
			while(rs.next()) {
				code.add(rs.getString("countrycode"));
				//나라 코드 제대로 들어갔는지 체크
//				for(int i=0; i<code.size(); i++) 
//				System.out.println(code.get(i)); 
			}
			rs.close();
			
			for(int i =0; i<code.size(); i++) { // 이프문으로 와일문까지 안싸면 code(0) 제일 첫번째 인덱스에 들어가있는 국가코드명만 키워드가 되어 테이블을 검색함. 다른 인덱스값에 넣어둔 애들도 테이블검색해서 출력하려면 와일도 싸야함.
			rs = st.executeQuery("select name from country where code like '"+ code.get(i)+"'");
				
				while(rs.next()) {
					System.out.print(rs.getString("name")+",");
				}
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(st!=null)
					st.close();
				if(rs!=null)
					rs.close();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	private void mission7(Connection con, Scanner sc) {
		Statement st = null;
		ResultSet rs = null;
		
		
		System.out.println("7. CountryLanguage에서 사용자가 입력 비율 이상인 언어의 국가 코드와 비율을 검색해서 출력하세요.");
		int per = sc.nextInt();
		
		try {
			st =con.createStatement();
			rs = st.executeQuery("select countrycode, percentage from countrylanguage where percentage >"+per);
			
			while(rs.next()) {
				System.out.print(rs.getString("countrycode")+":");
				System.out.println(rs.getString("percentage"));
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(st!=null)
					st.close();
				if(rs!=null)
					rs.close();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
	}
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "tiger");
		} catch (Exception e) {
		}
		return con;
	}
}
