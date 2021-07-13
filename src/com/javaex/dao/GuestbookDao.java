package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.GuestbookVo;

public class GuestbookDao {
	
	// field
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";
	
	// connection method
	private void getConnection() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException e) {
			System.out.println("ERROR: 드라이버 로딩 실패 " + e);
		} catch (SQLException e) {
			System.out.println("ERROR: " + e);
		}
	}
	
	// close method
	private void getClose() {
		try {
			if (conn != null)
				conn.close();
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			System.out.println("ERROR: " + e);
		}
	}
	
	// INSERT DB
	public int insert(GuestbookVo g) {
		int count = -1;
		
		getConnection();
		
		try {
			pstmt = conn.prepareStatement(
					" INSERT INTO "
					+ " 		guestbook "
					+ " VALUES ( "
					+ " 	sqc_no.NEXTVAL, ?, ?, ?, sysdate ) "
					);

			pstmt.setString(1, g.getName());
			pstmt.setString(2, g.getPassword());
			pstmt.setString(3, g.getContent());
			
			count = pstmt.executeUpdate();
			
			if (count > 0) {
				System.out.println("[" + g.getName() + "]님이 글을 작성하셨습니다.");
			} else {
				System.out.println("ERROR: " + count + " [관리자에게 문의하세요]");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		getClose();
		
		return count;
	}
	
	// SELECT DB
	public List<GuestbookVo> getList() {
		List<GuestbookVo> guestbookList = new ArrayList<GuestbookVo>();
		
		getConnection();
		
		try {
			pstmt = conn.prepareStatement(
					" SELECT "
					+ " 	no, "
					+ " 	name, "
					+ " 	password, "
					+ " 	content, "
					+ " 	reg_date "
					+ " FROM "
					+ " 	guestbook "
					+ " ORDER BY "
					+ " 	no ASC "
					);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				guestbookList.add(new GuestbookVo(rs.getInt("no"), rs.getString("name"), rs.getString("password"), rs.getString("content"), rs.getString("reg_date")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		getClose();

		return guestbookList;
	}
	
	// DELETE DB
	public int delete(GuestbookVo g) {
		int count = -1;
		
		getConnection();
		
		try {
			pstmt = conn.prepareStatement(
					" DELETE FROM "
					+ " 		guestbook "
					+ " WHERE "
					+ " 	no = ? "
					+ " 	AND password = ? "
					);
			 
			pstmt.setInt(1, g.getNo());
			pstmt.setString(2, g.getPassword());
			
			count = pstmt.executeUpdate();
			
			if (count > 0) {
				System.out.println("[" + g.getNo() + "]번 글이 삭제 되었습니다.");
			} else {
				System.out.println("ERROR: " + count + " [관리자에게 문의하세요]");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		getClose();
		
		return count;
	}
	
}
