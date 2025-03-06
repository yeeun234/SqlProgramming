package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class QueryByStatment {
	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/world";
			//왜 ip로는 접속이 안되는가? root아이디는 접근권한이 sql이 설치된 피시에서만 접근가능하게 설정되어있음
			
			con = DriverManager.getConnection(url,"root","tiger"); //커넥션객체 생성
			st = con.createStatement(); //질의객체생성
			rs = st.executeQuery("select id,name,countrycode,district,population from city");
			
			while(rs.next()) {
				System.out.println(rs.getInt("id")+",");//타입잘모르겟으면 다 겟스트링으로받아도됨. 필드명말고 인덱스적어도됨.근데 0부터시작안하고 1부터시작함. 이름으로하면 인덱스넘버랑 상관이없으니 걍 이름으로 호출하는게 나음
				System.out.println(rs.getString("name")+",");
				System.out.println(rs.getString("countrycode")+",");
				System.out.println(rs.getString("district")+",");
				System.out.println(rs.getInt("population")+"\n");
			}
		}catch(Exception e) {
			System.out.println("연결 실패 : " +e.getMessage());
		}finally {//정상적실행이든아니든 무조건실행
			try {
				if( rs != null) rs.close(); //앞에서 rs할당중에서 오류나면 rs가 널이라 아래구문이 실행이안되니 조건적어둠
				if(st != null)st.close();
				if(con !=null)con.close();
				
			}catch(Exception e) {}
		}
	}

}
