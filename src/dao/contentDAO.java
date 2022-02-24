package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import dao.*;
import util.ConnectionPool;

public class contentDAO {

	public boolean insert(String jsonstr) throws NamingException, SQLException, ParseException{
		Connection conn = ConnectionPool.get();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			synchronized(this) {
				// phase 1. add "no" property -----------------------------
				String sql = "SELECT no FROM content ORDER BY no DESC LIMIT 1";
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				
				int max = (!rs.next()) ? 0 : rs.getInt("no");
				stmt.close(); rs.close();
				
				JSONParser parser = new JSONParser();
				JSONObject jsonobj = (JSONObject) parser.parse(jsonstr);
				jsonobj.put("no", max + 1);
				
				
				// phase 3. insert jsonobj to the table ------------------------
				sql = "INSERT INTO content(no,id, jsonstr) VALUES(?, ?,?)";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, max+1);
				stmt.setString(2, jsonobj.get("id").toString());
				stmt.setString(3, jsonobj.toJSONString());
				
				int count = stmt.executeUpdate();
				return (count == 1) ? true : false;
			}
		} finally {
			if (rs != null) rs.close();
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
		}
	}
	
	public boolean update(int no, String jsonstr) throws NamingException, SQLException, ParseException{
		Connection conn = ConnectionPool.get();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			synchronized(this) {
				// phase 1. add "no" property -----------------------------
				String sql = "SELECT no FROM content ORDER BY no DESC LIMIT 1";
				stmt = conn.prepareStatement(sql);
				stmt.close(); 
				

				JSONParser parser = new JSONParser();
				JSONObject jsonobj = (JSONObject) parser.parse(jsonstr);

				
				sql = "UPDATE content SET jsonstr = ? WHERE no = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, jsonobj.toJSONString());
				stmt.setInt(2, no);
				
				int count = stmt.executeUpdate();
				return (count == 1) ? true : false;
			}
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
		    String sql = "SELECT * FROM content ORDER BY no DESC";
		    stmt = conn.prepareStatement(sql);
		    rs = stmt.executeQuery();
		    
		    String str = "[";
		    int cnt = 0;
		    while(rs.next()) {
		    if (cnt++ > 0) str += ", ";
		    str += rs.getString("jsonstr");
		    }
		    return str + "]";
		    
		} finally {
			if (rs != null) rs.close(); 
			if (stmt != null) stmt.close(); 
			if (conn != null) conn.close();
		}
	}
	
	public boolean exists(String uid) throws NamingException, SQLException {
		Connection conn = ConnectionPool.get();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT id FROM users WHERE id = ?";
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

	public boolean delete(int no) throws NamingException, SQLException, ParseException{
		Connection conn = ConnectionPool.get();
		PreparedStatement stmt = null;
		try {
			String sql = "DELETE FROM content WHERE no = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, no);
	
			int count = stmt.executeUpdate();
			return (count == 1) ? true : false;
			
		} finally {
			if (stmt != null) stmt.close(); 
			if (conn != null) conn.close();
		}
	}
	
	public String getGroup(String maxNo) throws NamingException, SQLException {
		Connection conn = ConnectionPool.get();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT jsonstr FROM content";
			if (maxNo != null) {
				sql += " WHERE no <" + maxNo;
				}
			sql += " ORDER BY no DESC LIMIT 3";
			
		    stmt = conn.prepareStatement(sql);
		    rs = stmt.executeQuery();
		    
		    String str = "[";
		    int cnt = 0;
		    while(rs.next()) {
		    if (cnt++ > 0) str += ", ";
		    str += rs.getString("jsonstr");
		    }
		    return str + "]";
		    
		} finally {
			if (rs != null) rs.close(); 
			if (stmt != null) stmt.close(); 
			if (conn != null) conn.close();
		}
	}
	
	

	


}