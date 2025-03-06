package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class QueryStatement1 {
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	
	public QueryStatement1(Connection con,Statement st,ResultSet rs){
		this(Statement st,ResultSet rs);
	}
	
	public QueryStatement1(){
		this();
	}
	
	public QueryStatement1(){
		this();
	}
	
	public static void main(String[] args) {
		QueryStatement co = new QueryStatement();
		
		connect(co);
		makeState(co);
		query(co,"select id,name,countrycode,district,population from city");
		exit(co);
		
	}
	
	public Connection connect(QueryStatement co) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con =DriverManager.getConnection("jdbc:mysql://localhost:3306/world","root","tiger");
			System.out.println("연결성공"+"\n");
		}catch(Exception e) {
			System.out.println("연결실패"+"\n");
		}
		return con;
		
	}
	
	public Statement makeState(QueryStatement co) {
		st = con.createStatement();
		return st;
	}
	
	// 1. 인구 수를입력받아서그보다많은인구를가진도시를검색해서출력하세요
	public ResultSet query(QueryStatement co,String msg) {
		try {
			
			rs = st.executeQuery(msg);
			while(rs.next()) {
				System.out.println(rs.getString("id")+",");
			}
		}catch(Exception e) {
			System.out.println("쿼리실패");
		}
		return rs;
	}
	
	public void exit(QueryStatement co) {
		try {
			if(rs !=null) rs.close();
			if(st !=null) st.close();
			if(con !=null) con.close();
		}catch(Exception e) {
		}
	}
}
