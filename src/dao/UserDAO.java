package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javax.naming.NamingException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import util.ConnectionPool;

public class UserDAO {
	public boolean insert(String uid,String jsonstr) throws NamingException, SQLException {
		Connection conn = ConnectionPool.get();
		PreparedStatement stmt = null;
		try {
			String sql = "INSERT INTO user(id, jsonstr) VALUES(?, ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, uid);
			stmt.setString(2, jsonstr);
	
			int count = stmt.executeUpdate();
			return (count == 1) ? true : false;
			
		} finally {
			if (stmt != null) stmt.close(); 
			if (conn != null) conn.close();
		}
	}

	public boolean exists(String uid) throws NamingException, SQLException {
		Connection conn = ConnectionPool.get();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT id FROM user WHERE id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, uid);
	
			rs = stmt.executeQuery();
			return rs.next();
			
		} finally {
			if (rs != null) rs.close();
			if (stmt != null) stmt.close(); 
			if (conn != null) conn.close();
		}
	}

	public boolean delete(String uid) throws NamingException, SQLException {
		Connection conn = ConnectionPool.get();
		PreparedStatement stmt = null;
		try {
			String sql = "DELETE FROM user WHERE id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, uid);
	
			int count = stmt.executeUpdate();
			return (count == 1) ? true : false;
			
		} finally {
			if (stmt != null) stmt.close(); 
			if (conn != null) conn.close();
		}
	}

	public int login(String uid,String upass) throws NamingException, SQLException,ParseException {
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    try {	    	
	    	String sql = "SELECT jsonstr FROM user WHERE id = ?";
	    	conn= ConnectionPool.get();
	    	stmt = conn.prepareStatement(sql);
	    	stmt.setString(1, uid);
	    	
	    	
	        rs = stmt.executeQuery();
	        if (!rs.next()) return 1;
	        
	        
	        String jsonstr = rs.getString("jsonstr");
	        JSONObject obj = (JSONObject) (new JSONParser()).parse(jsonstr);
	        String Pass = obj.get("ps").toString();

	      
	        if (!upass.equals(Pass)) return 2;

	        return 0;
	        
	    } finally {
			if (rs != null) rs.close();
			if (stmt != null) stmt.close(); 
			if (conn != null) conn.close();
	    }
	}	

	public String getList() throws NamingException, SQLException {
		Connection conn = ConnectionPool.get();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
		    String sql = "SELECT * FROM user ORDER BY ts DESC";
		    stmt = conn.prepareStatement(sql);
		    rs = stmt.executeQuery();
		    
		   JSONArray users = new JSONArray();
		   while(rs.next()) {
			   JSONObject usrobj = new JSONObject();
			   usrobj.put("id", rs.getString("id"));
			   usrobj.put("name", rs.getString("name"));
			   usrobj.put("ts", rs.getString("ts"));
			   users.add(usrobj);
		   }
		   
		    return users.toJSONString();
			
		} finally {
			if (rs != null) rs.close(); 
			if (stmt != null) stmt.close(); 
			if (conn != null) conn.close();
		}
	}
	//회원 정보 수정
		public int usrMod(String id, String jsonstr) throws Exception {
			Connection conn = null;
			PreparedStatement st = null;
		
			try {
				conn = ConnectionPool.get();
				
				String sql = "UPDATE user SET name = ?, password = ? WHERE id = ?";
				st = conn.prepareStatement(sql);; 
				st.setString(1, id);
				st.setString(2, jsonstr);
				
				return st.executeUpdate();
			}
			
			finally {
				if (st != null) st.close(); 
				if (conn != null) conn.close();
			}
			}
}
