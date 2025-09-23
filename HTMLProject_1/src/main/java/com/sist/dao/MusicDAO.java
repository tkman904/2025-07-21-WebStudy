package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MusicDAO {
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
   public static MusicDAO dao;
   // 드라이버 등록
   public MusicDAO()
   {
      try {
      Class.forName("oracle.jdbc.driver.OracleDriver");
      // ojdbc11 => oracle.jdbc.OracleDriver
      } catch(Exception ex)
      {
         ex.printStackTrace();
      }
   }
   // 싱글턴 => Spring : 등록한 모든 클래스 (싱글턴)
   
   public static MusicDAO newInstance()
   {
      if(dao==null)
         dao = new MusicDAO();
      return dao;
   }
   
   public void getConnection()
   {
      try {
         
      conn=DriverManager.getConnection(URL,"hr","happy");
      }catch(Exception ex)
      {
         ex.printStackTrace();
      }
   }
   public void disConnection()
   {
      try
      {
         if(ps!=null) ps.close(); // 송수신
         if(conn!=null) conn.close(); // 전화
      }catch(Exception ex) {ex.printStackTrace();}
   }
   //////////////////// => 모든 DAO의 공통 사항
   // 기능별 처리
   /*
    * 
    *    인덱스를 이용해서 검색 => 가급적이면 페이지 처리 권장
    * 
    */
   public List<String> MusicListData(String cno)
   {
      // 주소 / 음식종류 / 업체명
      List<String> list = new ArrayList<>();
      try
      {
         // 연결
         getConnection();
         // SQL 문장 제작
         String sql="SELECT title FROM genie_music "
                +  "WHERE cno="+cno
               +  " ORDER BY no ASC";
         //    --------------------------------------
         //                     LIKE CONCAT('%',?,'%') MySQL
         // 오라클 전송
         ps=conn.prepareStatement(sql);
         // 필요한 데이터 첨부
//         ps.setString(1, fd);
         
         // 결과값
         ResultSet rs=ps.executeQuery();
         // List에 채운다
         while(rs.next())
         {
            list.add(rs.getString(1));
         }
         // 오라클은 0(X) , 1부터 시작
         rs.close();
      }catch(Exception ex)
      {
         ex.printStackTrace();
      }
      finally
      {
         disConnection();
      }
      
      return list;
   }
   
}
