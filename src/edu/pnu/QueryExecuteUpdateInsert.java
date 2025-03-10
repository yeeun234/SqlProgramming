package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class QueryExecuteUpdateInsert {
	
	public static void main(String[] args) {
		Connection con = null;
		int rs = 0;
		PreparedStatement psmt = null;
		try{
			
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/myfirstdb";
			
			con = DriverManager.getConnection(url,"root","tiger");
			String sql = "insert into phonebook(name, mobile) values(?,?)";
			psmt = con.prepareStatement(sql);
			
			for(int i =0; i<6; i++) {
			psmt.setString(1, "김민"+i);
			psmt.setString(2, "0"+i);
			rs = psmt.executeUpdate();
		
				System.out.println(rs+" 입력 "+i);
			}
		
		}catch(Exception e){
			
		}finally {
			try {
				//앞에서 rs할당중에서 오류나면 rs가 널이라 아래구문이 실행이안되니 조건적어둠
				if(psmt != null)psmt.close();
				if(con !=null)con.close();
			}catch(Exception e) {
			}
		}
	}

}

