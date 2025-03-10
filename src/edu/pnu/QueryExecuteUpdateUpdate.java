package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class QueryExecuteUpdateUpdate {
	public static void main(String[] args) {
		Connection con = null;
		int rs = 0;
		PreparedStatement psmt = null;
		try{
			
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/myfirstdb";
			
			con = DriverManager.getConnection(url,"root","tiger");
			String sql = "update phonebook set home =? where id>=?";
			psmt = con.prepareStatement(sql);
			for(int i =0; i<6; i++) {
			psmt.setString(1,"6"+i );
			psmt.setInt(2, 11);
			rs = psmt.executeUpdate();
		
				System.out.println(rs+" 수정 "+i);
			}
		}catch(Exception e){
			
		}finally {
			try {
				if(psmt != null)psmt.close();
				if(con !=null)con.close();
			}catch(Exception e) {
			}
		}
	}
}
