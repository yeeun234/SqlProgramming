package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

//executeQuery 는 Resultset을 리턴 . executeUpdate는 int를 리턴. 커넥션을 하나 만들어 계속 재사용하고 , 질의객체를 만들고, 클로즈하기 잊지말기.
public class PhoneBookDao {
	private static Scanner sc = new Scanner(System.in);
	private static String url = "jdbc:mysql://localhost:3306/myfirstdb";
	public static void main(String[] args) throws Exception {
		Connection con = DriverManager.getConnection(url,"root","tiger");
		boolean flag = true;
		while(flag) {
			System.out.println("[I]nsert/[U]pdate/[D]elete/[S]elect/[N]ative/[Q]uit:");
			char c = sc.next().toUpperCase().charAt(0);
			switch(c) {
			case 'I' : insertPhonebook(con); break;
			case 'U' : updatePhonebook(con); break;
			case 'D' : updatePhonebook(con); break;
			case 'S' : selectAllPhonebook(con); break;
			case 'N' : nativeQuery(con); break;
			case 'Q' : flag = false; break;
			}
		}
		System.out.println("bye~");
	}
	
	private static void nativeQuery(Connection con) {
		// TODO Auto-generated method stub
		System.out.println("쿼리문을 입력하세요.");
		String query = sc.nextLine();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			
			while(rs.next()) {
				System.out.println(rs.getString(1));
				System.out.println(rs.getString(2));
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally{
			try {
		}catch(Exception e) {
		}
		}
		
	}

	private static void selectAllPhonebook(Connection con) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		System.out.println("== Phonebook Table ==");
		
		String sql = "select * from phonebook";
		
		
		try {
			ps= con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
//				for(int i =1; i<6; i++)
				System.out.print(rs.getString(1)+" ");
				System.out.print(rs.getString(2)+" ");
				System.out.print(rs.getString(3)+" ");
				System.out.print(rs.getString(4)+" ");
				System.out.print(rs.getString(5)+" ");
				System.out.print(rs.getString(6)+" "+"\n");
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	private static void updatePhonebook(Connection con) {
		PreparedStatement ps = null;
		int rs = 0;
		
		try {
			System.out.println("변경할 내용을 입력하세요.");
			System.out.println("이름 : "); String name = sc.next();
			System.out.println("휴대전화 : "); String mobile = sc.next();
			System.out.println("집전화 : "); String home = sc.next();
			System.out.println("회사전화 : "); String company = sc.next();
			System.out.println("이메일 : "); String email = sc.next();
			System.out.println("id : "); String id = sc.next();
			
			
			String sql = "update phonebook set name =? ,mobile =? ,home =? ,company =? ,email =? where id =?";
			ps = con.prepareStatement(sql);
			
			ps.setString(1, name);
			ps.setString(2, mobile);
			ps.setString(3, home);
			ps.setString(4, company);
			ps.setString(5, email);
			ps.setString(6, id);
			
			rs = ps.executeUpdate();
			
			System.out.println(rs +"개가 변경되었습니다.");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(ps!=null) ps.close();
				if(con!=null)con.close();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private static void insertPhonebook(Connection con) {
		PreparedStatement ps = null;
		int rs = 0;
		try {
			System.out.println("새로운 연락처를 입력합니다.");
			System.out.println("이름 : "); String name = sc.next();
			System.out.println("휴대전화 : "); String mobile = sc.next();
			System.out.println("집전화 : "); String home = sc.next();
			System.out.println("회사전화 : "); String company = sc.next();
			System.out.println("이메일 : "); String email = sc.next();
			String sql ="insert into phonebook(name,mobile,home,company,email) values(?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, mobile);
			ps.setString(3, home);
			ps.setString(4, company);
			ps.setString(5, email);
			rs = ps.executeUpdate();
			
				System.out.println(rs +"개가 입력되었습니다.");
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(ps!=null) ps.close();
				if(con!=null)con.close();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		
	}

	

}
