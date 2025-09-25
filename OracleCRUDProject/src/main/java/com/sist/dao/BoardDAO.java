package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sist.vo.BoardDTO;

public class BoardDAO {
	private Connection conn;
	   private PreparedStatement ps;
	   // 변경 사항이 없다
	   private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	   /*
	    *    jdbc:업체명:@IP:port:데이터베이스명
	    *  jdbc:mysql:@localhost:3306:mydb
	    * 
	    */
	   // 각 user 당 1개의 DAO를 사용이 가능 => 싱글턴 
	   public static BoardDAO dao;
	   // 드라이버 등록
	   public BoardDAO() {
	      try {
	      Class.forName("oracle.jdbc.driver.OracleDriver");
	      // ojdbc11 => oracle.jdbc.OracleDriver
	      } catch(Exception ex) {
	         ex.printStackTrace();
	      }
	   }
	   // 싱글턴 => Spring : 등록한 모든 클래스 (싱글턴)
	   
	   public static BoardDAO newInstance() {
	      if(dao==null) {
	         dao = new BoardDAO();
	      }
	      return dao;
	   }
	   
	   public void getConnection() {
	      try {
	    	  conn = DriverManager.getConnection(URL,"hr","happy");
	      } catch(Exception ex) {
	         ex.printStackTrace();
	      }
	   }
	   public void disConnection() {
	      try {
	         if(ps!=null) {
	        	 ps.close(); // 송수신
	         }
	         if(conn!=null) {
	        	 conn.close(); // 전화
	         }
	      } catch(Exception ex) {
	    	  ex.printStackTrace();
	      }
	   }
	   
	   // 기능
	   // 목록 => 페이징
	   public List<BoardDTO> boardListData(int page) {
		   List<BoardDTO> list = new ArrayList<BoardDTO>();
		   try {
			   // 1. 연결
			   getConnection();
			   
			   // 2. SQL문장
			   String sql = "SELECT no, subject, name, TO_CHAR(regdate, 'yyyy-mm-dd'), hit "
			   		+ "FROM web_board "
			   		+ "ORDER BY no DESC "
			   		+ "OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY";
			   		// LIMIT 1, 10
			   int rowSize = 10;
			   int start = (rowSize*page)-rowSize;
			   ps=conn.prepareStatement(sql);
			   ps.setInt(1, start);
			   
			   // 결과값
			   ResultSet rs = ps.executeQuery();
			   while(rs.next()) {
				  BoardDTO vo = new BoardDTO();
				  vo.setNo(rs.getInt(1));
				  vo.setSubject(rs.getString(2));
				  vo.setName(rs.getString(3));
				  vo.setDbday(rs.getString(4));
				  vo.setHit(rs.getInt(5));
				  list.add(vo);
			   }
			   rs.close();
		   } catch(Exception ex) {
			   ex.printStackTrace();
		   }
		   finally {
			   disConnection();
		   }
		   return list;
	   }
	   
	   // 총 페이지
	   public int boardTotalPage() {
		   int total = 0;
		   try {
			   getConnection();
			   String sql = "SELECT CEIL(COUNT(*)/10.0) "
			   		+ "FROM web_board";
			   ps = conn.prepareStatement(sql);
			   
			   ResultSet rs = ps.executeQuery();
			   rs.next();
			   total = rs.getInt(1);
			   rs.close();
		   } catch(Exception ex) {
			   ex.printStackTrace();
		   }
		   finally {
			   disConnection();
		   }
		   return total;
	   }
	   
	   // 새글(C) => INSERT
	   public void boardInsert(BoardDTO vo) {
		   try {
			   getConnection();
			   String sql = "INSERT INTO web_board(no, name, subject, content, pwd) "
			   		+ "VALUES(wb_no_seq.nextval, ?, ?, ?, ?)";
			   ps = conn.prepareStatement(sql);
			   ps.setString(1, vo.getName());
			   ps.setString(2, vo.getSubject());
			   ps.setString(3, vo.getContent());
			   ps.setString(4, vo.getPwd());
			   
			   ps.executeUpdate();
		   } catch(Exception ex) {
			   ex.printStackTrace();
		   }
		   finally {
			   disConnection();
		   }
	   }
	   
	   // 상세보기 => 조회수 증가
	   public BoardDTO boardDetailData(int no) {
		   BoardDTO vo = new BoardDTO();
		   try {
			   getConnection();
			   // SQL => hit를 증가
			   String sql = "UPDATE web_board SET "
			   		+ "hit=hit+1 "
			   		+ "WHERE no=?";
			   ps = conn.prepareStatement(sql);
			   ps.setInt(1, no);
			   // 실행
			   ps.executeUpdate();
			   
			   sql = "SELECT no, name, subject, content, hit, "
			   		+ "TO_CHAR(regdate, 'yyyy-mm-dd hh24:mi:ss') "
			   		+ "FROM web_board "
			   		+ "WHERE no=?";
			   ps = conn.prepareStatement(sql);
			   ps.setInt(1, no);
			   
			   ResultSet rs = ps.executeQuery();
			   rs.next();
			   vo.setNo(rs.getInt(1));
			   vo.setName(rs.getString(2));
			   vo.setSubject(rs.getString(3));
			   vo.setContent(rs.getString(4));
			   vo.setHit(rs.getInt(5));
			   vo.setDbday(rs.getString(6));
			   rs.close();
		   } catch(Exception ex) {
			   ex.printStackTrace();
		   }
		   finally {
			   disConnection();
		   }
		   return vo;
	   }
	   
	   // 수정(U) => hidden 비밀번호 검색
	   public BoardDTO boardUpdateData(int no) {
		   BoardDTO vo = new BoardDTO();
		   try {
			   getConnection();
			   String sql = "SELECT no, name, subject, content "
			   		+ "FROM web_board "
			   		+ "WHERE no=?";
			   ps = conn.prepareStatement(sql);
			   ps.setInt(1, no);
			   
			   ResultSet rs = ps.executeQuery();
			   rs.next();
			   vo.setNo(rs.getInt(1));
			   vo.setName(rs.getString(2));
			   vo.setSubject(rs.getString(3));
			   vo.setContent(rs.getString(4));
			   rs.close();
		   } catch(Exception ex) {
			   ex.printStackTrace();
		   }
		   finally {
			   disConnection();
		   }
		   return vo;
	   }
	   
	   // 삭제(D) => hidden
	   // 검색(R) => 이름 / 제목 / 내용
}
